tags:: study, clj/datomic, rtc

# 20230524-rtc-Stackoverflow-Questions--Datomic id=g14447

rfr: [Highest scored 'datomic' questions - Stack Overflow](https://stackoverflow.com/questions/tagged/datomic)

## [clojure - How do I undo or reverse a transaction in datomic? - Stack Overflow](https://stackoverflow.com/questions/25389807/how-do-i-undo-or-reverse-a-transaction-in-datomic)

## [clojure - Retrieve Most Recent Entity from Datomic - Stack Overflow](https://stackoverflow.com/questions/19071694/retrieve-most-recent-entity-from-datomic)

a01:

```clj
(defn return-posts
  "grabs all posts from Datomic"
  [uri]
  (d/q '[:find ?title ?body ?slug ?ts
         :where
         [?e :post/title ?title ?tx]
         [?e :post/slug ?slug]
         [?e :post/body ?body]
         [?tx :db/txInstant ?ts]] (d/db (d/connect uri))))
```

a02:

```clj
[[:db/add tempid :post/slug "..."]
 [:db/add tempid :post/title "A title"]
 [:db/add tempid :created-at (java.util.Date.)]
 [:db/add tempid :changed-at (java.util.Date.)]]

; Then for updates:

[[:db/add post-eid :post/title "An updated title"]
 [:db/add post-eid :changed-at (java.util.Date.)]]

; That way all I have to do is to read out the :created-at attribute of the entity, which will be ready and waiting in the index.

(defmacro find-one-entity
  "Returns entity when query matches, otherwise nil"
  [q db & args]
  `(when-let [eid# (ffirst (d/q ~q ~db ~@args))]
     (d/entity ~db eid#)))

(defn find-post-by-slug
  [db slug]
  (find-one-entity
    '[:find ?e
      :in $ ?slug
      :where
      [?e :post/slug ?slug]]
    db
    slug))

;; Get timestamp
(:created-at (find-post-by-slug db "my-post-slug"))
```

## [clojure - modelling multiple many-to-many relationships in datomic - Stack Overflow](https://stackoverflow.com/questions/14724991/modelling-multiple-many-to-many-relationships-in-datomic)

[Datomic: data model for a simple blog](https://gist.github.com/a2ndrade/5651419)

```clj
(d/q '[:find ?cid ?c
   :in $ ?u
   :where
   [?uid :user/username ?u]
   [?aid :article/category ?cid]
   [?aid :article/author ?uid]
   [?cid :category/name ?c]]
 (d/db conn) "john.smith")
```

> An important modeling decision is to have articles point to comments and mark the relationship as :db/isComponent since comments should not exist on their own but as part of an article.

> If you want to enforce application-specific consistency rules (e.g. articles and comments must have a author, comments must be of certain length, etc) you need to use database functions.

```clj
(def add-user-code
  '(if (every? umap [:name :email])
     [{:user/name (:name umap)
       :user/email (:email umap)}]
     (datomic.api/cancel {:cognitect.anomalies/category :cognitect.anomalies/incorrect
                          :cognitect.anomalies/message "User map must contain :email and :name"})))

```

## [clojure - Getting the id of an inserted entity in datomic? - Stack Overflow](https://stackoverflow.com/questions/17190334/getting-the-id-of-an-inserted-entity-in-datomic)

Use d/resolve-tempid. If you were to transact a single entity, looking at :tx-data would work but if your transaction contained more than one entity, then you wouldn't know the order in which they appear in :tx-data.

What you should do is give temporary ids to your entities (before transacting them) using either (d/tempid) or its literal representation #db/id[:db.part/user _negativeId_]

```clj
(d/resolve-tempid (d/db conn) (:tempids tx) (d/tempid :db.part/user _negativeId_))
```

[Datomic: Getting the id of an inserted entity](https://gist.github.com/a2ndrade/5814355)

```clj
;; create an atribute
(d/transact conn [{:db/id #db/id[:db.part/db]
                   :db/ident :some/attribute
                   :db/valueType :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db.install/_attribute :db.part/db}])

;; transact multiple entities with that attribute
(def tx @(d/transact conn
                     [[:db/add #db/id [:db.part/user -100] :some/attribute "A"]
                      [:db/add #db/id [:db.part/user -200] :some/attribute "B"]
                      [:db/add #db/id [:db.part/user -300] :some/attribute "C"]]))

;; get back the real entity id from a temporary id
(def bEid (d/resolve-tempid (d/db conn) (:tempids tx) (d/tempid :db.part/user -200)))
;; 17592186045440. Go from -200 (tempid) to 17592186045440 (real entity id)

;; verify you got the right one
(:some/attribute (d/entity (d/db conn) bEid))
;; "B"
```

## [clojure - Equivalent of SQL "limit" clause in Datomic - Stack Overflow](https://stackoverflow.com/questions/27162566/equivalent-of-sql-limit-clause-in-datomic)

a01: idiomatic

```clj
(d/q '[:find [(rand 2 ?name) (sample 2 ?name)]
       :where [_ :artist/name ?name]]
     db)
```

a02: seek-datoms

```clj
(->> (d/seek-datoms (d/db conn) :avet :artist/sortName "Bea")
     (take 20))
```

Of course, you can map a fn that prettifies the output or adds more attributes, etc. such as in this example:

```clj
(let [db (d/db conn)]
  (->> (d/seek-datoms db :avet :artist/sortName "Bea")
       (take 20)
       (map #(merge {:artist/name (:v %)
                     :artist/type (-> (d/pull db [{:artist/type [:db/ident]}] (:e %))
                                      :artist/type
                                      :db/ident)}))))
```

a03: `:limit` in query-map

```clj
 (d/q {:query '[:find ?c ?n :where [?c :my-thing/its-attribute ?n]] 
       :offset 1
       :limit 10
       :args [(d/db conn)]})
```

## [clojure - In Datomic, how do I get a timeline view of the changes made to the values of a particular entity? - Stack Overflow](https://stackoverflow.com/questions/11025434/in-datomic-how-do-i-get-a-timeline-view-of-the-changes-made-to-the-values-of-a)

The following query finds all artists whose start year is not recorded in the database.

```clj
[:find ?name
 :where [?artist :artist/name ?name]
        [(missing? $ ?artist :artist/startYear)]]
;; inputs
db
;=>
#{["Sigmund Snopek III"] ["De Labanda's"] ["Baby Whale"] ...}
```

## [clojure - Datomic - requires explicit manual coding of unique IDs? - Stack Overflow](https://stackoverflow.com/questions/14559620/datomic-requires-explicit-manual-coding-of-unique-ids)

:db/id #db/id[:db.part/user -1000001],

this appears to explicitly require a manually generated unique ID sequence number when preparing data for insert.

### answer:

No Datomic doesn't require the end user to generate identifiers. What you see in the seattle example are temporary ids.

why do you have to use this temporary ids in the first place? Temporary ids are needed to express relationships between all new entities in one single transaction

Now I have to explain the data literal (other link) #db/id[:db.part/user -1000001]. #db/id is the tag for a Datomic temporary id. The tag is followed by a vector of two components :db.part/user and -1000001. The first part is the database partition and is mandatory. The second part is optional. If you write just #db/id[:db.part/user], you get a fresh (different) temporary id every time this literal occurs. If you write #db/id[:db.part/user -1000001] you get the same temporary id every time you use the negative index -1000001

## [datomic - How to sort result in a Datalog query - Stack Overflow](https://stackoverflow.com/questions/29621159/how-to-sort-result-in-a-datalog-query)

If you want to sort your result set, you will need to do this outside of the query, on the returned result set. 

```clj
(def hist (d/history db))

(->> (d/q '[:find ?tx ?v ?op
            :in $ ?e ?attr
            :where [?e ?attr ?v ?tx ?op]]
        hist
        editor-id
        :user/firstName)
   (sort-by first))

=> ([13194139534319 "Ed" true]
    [13194139534335 "Ed" false]
    [13194139534335 "Edward" true])


```

a02: min aggregate function

```clj
[:find ?movie (min 10 ?e)]
```

## [clojure - Possible to get enum value via Datomic pull syntax? - Stack Overflow](https://stackoverflow.com/questions/31821752/possible-to-get-enum-value-via-datomic-pull-syntax)

This is as close as I could get:

```clj
[:find (pull ?e [:artist/name {:artist/type [:db/ident]}])
 :where
 [?e :artist/name "Ray Charles"]
]

;;=> [[{:artist/name "Ray Charles", :artist/type {:db/ident :artist.type/person}}]]
```

Is it possible to use pull syntax to reshape the result into something like this?

```clj
;;=> [[{:artist/name "Ray Charles", :artist/type :artist.type/person}]]
```


### answer: tupelo

I don't think you can do it using the Pull API the way you are seeking. You may find that it is easier to use the Tupelo Datomic library:

```clj
(require '[tupelo.datomic :as td]
         '[tupelo.core :refer [spyx]] )
(let [x1      (td/query-scalar  :let     [$ db-val]
                                :find    [ ?e ]
                                :where   [ [?e :artist/name "Ray Charles"] ] )
      x2      (td/entity-map db-val x1)
     ]
  (spyx x1)
  (spyx x2)
)
;; x1 => 17592186049074

;; x2 => {:artist/sortName "Charles, Ray", :artist/name "Ray Charles", :artist/type :artist.type/person, :artist/country :country/US, :artist/gid #uuid "2ce02909-598b-44ef-a456-151ba0a3bd70", :artist/startDay 23, :artist/endDay 10, :artist/startYear 1930, :artist/endMonth 6, :artist/endYear 2004, :artist/startMonth 9, :artist/gender :artist.gender/male}
```

### a02: specter

```clj
(->> pull-result                                                                                     
     (sp/transform (sp/walker :db/ident) :db/ident))
```

### a03: postwalk

```clj
(defn flatten-ident [coll]
  (clojure.walk/postwalk
   (fn [item] (get item :db/ident item)) coll))
```

## [Recommended way to declare Datomic schema in Clojure application - Stack Overflow](https://stackoverflow.com/questions/31416378/recommended-way-to-declare-datomic-schema-in-clojure-application)

- Raw maps are verbose. 
- Advantages over high level API:
	- Schema is in transaction form.
	- It is not tied to a particular library.
	- It is serializable.
	- It can be stored in distributed environments.

