(ns mertnuhoglu.day-of-datomic.cloud.social-news)

;; [cognitect-labs/day-of-datomic-cloud](https://github.com/cognitect-labs/day-of-datomic-cloud)
;; rfr: Example: Day of Datomic Cloud Sample Data <url:file:///~/prj/study/clj/archive-datomic.md#r=g13521>

(require '[datomic.client.api :as d])
(require '[clojure.data.generators :as gen])

(import '(java.util UUID))

(def client (d/client {:server-type :dev-local :system "day-of-datomic-cloud"}))
(d/create-database client {:db-name "social-news"})
(def conn (d/connect client {:db-name "social-news"}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; schema

(def schema
  [;; stories
   {:db/ident :story/title
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :story/url
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/unique :db.unique/identity}
   {:db/ident :story/slug
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}

   ;; comments
   {:db/ident :news/comments
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/many
    :db/isComponent true}
   {:db/ident :comment/body
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :comment/author
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}

   ;; users
   {:db/ident :user/firstName
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :user/lastName
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :user/email
    :db/unique :db.unique/identity
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :user/passwordHash
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :user/upVotes
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/many}

   ;; publish time
   {:db/ident :publish/at
    :db/valueType :db.type/instant
    :db/cardinality :db.cardinality/one}])

(d/transact conn {:tx-data schema})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; data

(def data
  [;; stories
   {:story/title "Teach Yourself Programming in Ten Years"
    :story/url "http://norvig.com/21-days.html"}
   {:story/title "Clojure Rationale"
    :story/url "http://clojure.org/rationale"}
   {:story/title "Beating the Averages"
    :story/url "http://www.paulgraham.com/avg.html"}

   ;; users
   {:user/firstName "Stu"
    :user/lastName "Halloway"
    :user/email "stuarthalloway@datomic.com"}
   {:user/firstName "Ed"
    :user/lastName "Itor"
    :user/email "editor@example.com"}])

(d/transact conn {:tx-data data})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; point in time db value
(def db (d/db conn))

(def all-stories
  (-> (d/q '[:find ?e
             :where [?e :story/url]]
        (d/db conn))
    flatten
    vec))

(def new-user-id "user-1")

(def upvote-all-stories
  "Transaction data for new-user-id to upvote all stories"
  (mapv
    (fn [story] [:db/add new-user-id :user/upVotes story])
    all-stories))

(def new-user
  "Transaction data for a new user"
  [{:db/id new-user-id
    :user/email "john@example.com"
    :user/firstName "John"
    :user/lastName "Doe"}])

@(def upvote-tx-result
  "In a single transaction, create new user and upvote all stories"
  (->> (concat upvote-all-stories new-user)
    (hash-map :tx-data)
    (d/transact conn)))

@(def change-user-name-result
  "Demonstrates upsert. Tempid will resolve to existing id to
   match specified :user/email."
  (d/transact
    conn
    {:tx-data
     [{:user/email "john@example.com" ;; this finds the existing entity
       :user/firstName "Johnathan"}]}))

(def john [:user/email "john@example.com"])

@(def johns-upvote-for-pg
  (ffirst
    (d/q '[:find ?story
           :in $ ?e
           :where [?e :user/upVotes ?story]
           [?story :story/url "http://www.paulgraham.com/avg.html"]]
      (d/db conn)
      john)))
;=>
;#datomic.core.db.Db{:id "social-news",
;                    :basisT 23,
;                    :indexBasisT 0,
;                    :index-root-id nil,
;                    :asOfT nil,
;                    :sinceT nil,
;                    :raw nil}

@(def db (:db-after (d/transact
                     conn
                     {:tx-data [[:db/retract john :user/upVotes johns-upvote-for-pg]]})))

;; should now be only two, since one was retracted
(d/pull db '[:user/upVotes] john)
;=> #:user{:upVotes [#:db{:id 74766790688853} #:db{:id 74766790688854}]}

@(def data-that-retracts-johns-upvotes
  (let [db (d/db conn)]
    (->> (d/q '[:find ?op ?e ?a ?v
                :in $ ?op ?e ?a
                :where [?e ?a ?v]]
           db
           :db/retract
           john
           :user/upVotes)
      (into []))))
;=>
;[[:db/retract [:user/email "john@example.com"] :user/upVotes 74766790688853]

@(def db (:db-after (d/transact conn {:tx-data data-that-retracts-johns-upvotes})))

;; all gone
(d/pull db '[:user/upVotes] john)
;=> nil

(defn choose-some
  "Pick zero or more items at random from a collection"
  [coll]
  (take (gen/uniform 0 (count coll))
    (gen/shuffle coll)))

(defn gen-users-with-upvotes
  "Make transaction data for example users, possibly with upvotes"
  [stories email-prefix n]
  (mapcat
    (fn [n]
      (let [user-id (str "new-user-" (UUID/randomUUID))
            upvotes (map (fn [story] [:db/add user-id :user/upVotes story])
                       (choose-some stories))]
        (conj
          upvotes
          {:db/id user-id
           :user/email (str email-prefix "-" n "@example.com")})))
    (range n)))

@(def ten-new-users
   (gen-users-with-upvotes all-stories "user" 10))
;=>
;({:db/id "new-user-9bfc1172-b283-4d25-9462-c72220596132", :user/email "user-0@example.com"}
; [:db/add "new-user-9bfc1172-b283-4d25-9462-c72220596132" :user/upVotes 74766790688854]
; [:db/add "new-user-9bfc1172-b283-4d25-9462-c72220596132" :user/upVotes 74766790688853]
; {:db/id "new-user-4b70d603-2784-467c-a506-301960333249", :user/email "user-1@example.com"})

(d/transact conn {:tx-data ten-new-users})

;; how many users are there?
(d/q '[:find (count ?e)
       :where [?e :user/email ?v]] (d/db conn))
;=> [[13]]

;; how many users have upvoted something?
(d/q '[:find (count ?e)
       :where [?e :user/email]
       [?e :user/upVotes]]
  (d/db conn))
;=> [[7]]

;; Datomic does not need a left join to keep entities missing
;; some attribute. Just leave that attribute out of the :where,
;; and then pull it during the :find.
@(def users-with-upvotes
   (d/q '[:find (pull ?e [:user/email {:user/upVotes [:story/url]}])
          :where [?e :user/email]] (d/db conn)))
;=>
;[[#:user{:email "stuarthalloway@datomic.com"}]
; [#:user{:email "editor@example.com"}]
; [#:user{:email "john@example.com"}]
; [#:user{:email "user-0@example.com",
;         :upVotes [#:story{:url "http://norvig.com/21-days.html"} #:story{:url "http://clojure.org/rationale"}]}]
; [#:user{:email "user-1@example.com",
;         :upVotes [#:story{:url "http://norvig.com/21-days.html"} #:story{:url "http://www.paulgraham.com/avg.html"}]}]
; [#:user{:email "user-2@example.com"}]
; [#:user{:email "user-3@example.com"}]
; [#:user{:email "user-4@example.com"}]
; [#:user{:email "user-5@example.com", :upVotes [#:story{:url "http://clojure.org/rationale"}]}]
; [#:user{:email "user-6@example.com", :upVotes [#:story{:url "http://norvig.com/21-days.html"}]}]
; [#:user{:email "user-7@example.com", :upVotes [#:story{:url "http://clojure.org/rationale"}]}]
; [#:user{:email "user-8@example.com",
;         :upVotes [#:story{:url "http://clojure.org/rationale"} #:story{:url "http://www.paulgraham.com/avg.html"}]}]
; [#:user{:email "user-9@example.com", :upVotes [#:story{:url "http://www.paulgraham.com/avg.html"}]}]]

