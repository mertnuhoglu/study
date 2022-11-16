(ns mertnuhoglu.day-of-datomic.cloud.provenance)

(require '[datomic.client.api :as d])

(def client (d/client {:server-type :dev-local :system "day-of-datomic-cloud"}))
(d/create-database client {:db-name "provenance"})
(def conn (d/connect client {:db-name "provenance"}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; schema - similar to schema in social-news.repl, with
;; the addition of a source/user attribute.

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
   {:db/ident :comments
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
    :db/cardinality :db.cardinality/one}

   ;; sources of data
   {:db/ident :source/user
    :db/valueType :db.type/ref
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

(def stu [:user/email "stuarthalloway@datomic.com"])

;; Stu loves to promote his own blog posts...
(def tx-result (d/transact
                 conn
                 {:tx-data
                  [{:story/title "ElastiCache in 6 minutes"
                    :story/url "http://blog.datomic.com/2012/09/elasticache-in-5-minutes.html"}
                   {:story/title "Keep Chocolate Love Atomic"
                    :story/url "http://blog.datomic.com/2012/08/atomic-chocolate.html"}
                   {:db/id "datomic.tx"
                    :source/user stu}]}))

;; database t of tx1-result
@(def db-t (:t (:db-after tx-result)))
;=> 12

(def editor [:user/email "editor@example.com"])

;; fix spelling error in title
;; note auto-upsert and attribution
@(def db (:db-after (d/transact
                      conn
                      {:tx-data
                       [{:story/title "ElastiCache in 5 minutes"
                         :story/url "http://blog.datomic.com/2012/09/elasticache-in-5-minutes.html"}
                        {:db/id "datomic.tx"
                         :source/user editor}]})))
;=> #datomic.core.db.Db{:id "provenance", :basisT 13, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil}

(def story [:story/url "http://blog.datomic.com/2012/09/elasticache-in-5-minutes.html"])

;; what is the title now?
(d/pull db '[:story/title] story)
;=> #:story{:title "ElastiCache in 5 minutes"}

;; what was the title as of earlier point in time?
(d/pull (d/as-of db db-t) '[:story/title] story)
;=> #:story{:title "ElastiCache in 6 minutes"}

;; who changed the title, and when?
(->> (d/q '[:find ?e ?v ?email ?inst ?added
            :in $ ?e
            :where
            [?e :story/title ?v ?tx ?added]
            [?tx :source/user ?user]
            [?tx :db/txInstant ?inst]
            [?user :user/email ?email]]
       (d/history (d/db conn))
       story)
  (sort-by #(nth % 3)))
;=>
;([[:story/url "http://blog.datomic.com/2012/09/elasticache-in-5-minutes.html"]
;  "ElastiCache in 6 minutes"
;  "stuarthalloway@datomic.com"
;  #inst"2022-11-15T15:08:12.528-00:00"
;  true]
; [[:story/url "http://blog.datomic.com/2012/09/elasticache-in-5-minutes.html"]
;  "ElastiCache in 5 minutes"
;  "editor@example.com"
;  #inst"2022-11-15T15:08:12.536-00:00"
;  true])
; ...

;; what is the entire history of entity e?
(->> (d/q '[:find ?aname ?v ?tx ?inst ?added
            :in $ ?e
            :where
            [?e ?a ?v ?tx ?added]
            [?a :db/ident ?aname]
            [?tx :db/txInstant ?inst]]
       (d/history (d/db conn))
       story)
  seq
  (sort-by #(nth % 2)))
;=>
;([:story/url
;  "http://blog.datomic.com/2012/09/elasticache-in-5-minutes.html"
;  13194139533320
;  #inst"2022-11-15T15:08:12.528-00:00"
;  true]
; [:story/title "ElastiCache in 6 minutes" 13194139533320 #inst"2022-11-15T15:08:12.528-00:00" true])

