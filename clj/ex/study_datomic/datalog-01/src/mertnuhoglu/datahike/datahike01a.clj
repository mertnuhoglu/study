(ns mertnuhoglu.datahike01a
  (:require [datahike.api :as dh]))

; Adapted from:
; [replikativ/datahike: A durable Datalog implementation adaptable for distribution.](https://github.com/replikativ/datahike)
; [datahike/schema.md at main · replikativ/datahike](https://github.com/replikativ/datahike/blob/main/doc/schema.md)
; [datahike.api — io.replikativ/datahike 0.4.1491](https://cljdoc.org/d/io.replikativ/datahike/0.4.1491/api/datahike.api#datoms)

(def cfg {:store {:backend :mem :id "schemaless"} :schema-flexibility :read})
(dh/create-database cfg)
(def db (dh/connect cfg))

; Note: Now we can skip schema definition as first step because of `:schema-flexibility`:
;(dh/transact conn [{:db/ident :name
;                    :db/valueType :db.type/string
;                    :db/cardinality :db.cardinality/one}
;                   {:db/ident :age
;                    :db/valueType :db.type/long
;                    :db/cardinality :db.cardinality/one}])

;; lets add some data and wait for the transaction
(dh/transact db [{:name "Alice", :age 20}
                 {:name  "Bob", :age   30}
                 {:name  "Charlie", :age   40}
                 {:age 15}])

;; list all eav tuples
(dh/q '[:find ?e ?a ?v
        :where
        [?e ?a ?v]]
  @db)
; =>
;#{[1 :name "Alice"]
;  [3 :age 40]
;  [1 :age 20]
;  [2 :age 30]
;  [536870913 :db/txInstant #inst"2022-04-17T11:47:26.322-00:00"]
;  [2 :name "Bob"]
;  [3 :name "Charlie"]
;  [4 :age 15]}

; list all eav tuples sorted by eavt
(dh/datoms @db {:index :eavt})
; =>
;(#datahike/Datom[1 :age 20 536870913 true]
; #datahike/Datom[1 :name "Alice" 536870913 true]
; #datahike/Datom[2 :age 30 536870913 true]
; #datahike/Datom[2 :name "Bob" 536870913 true]
; #datahike/Datom[3 :age 40 536870913 true]
; #datahike/Datom[3 :name "Charlie" 536870913 true]
; #datahike/Datom[4 :age 15 536870913 true]
; #datahike/Datom[536870913 :db/txInstant #inst"2022-04-17T11:47:26.322-00:00" 536870913 true])

(dh/datoms @db {:index        :eavt
                :components [1 :age]})
;=> (#datahike/Datom[1 :age 20 536870913 true])

(dh/datoms @db {:index        :eavt
                :components [1]})
;(#datahike/Datom[1 :age 20 536870913 true]
; #datahike/Datom[1 :name "Alice" 536870913 true])

; get entity ids
(->>
  (dh/datoms @db {:index        :eavt
                  :components [1]})
  (map :e))
;=> (1 1)

; Find all entities with a specific attribute
(->> (dh/datoms @db {:index        :aevt
                     :components [:name]})
     (map :e))
;=> (1 2 3)

; get entity by id
(dh/entity @db 1)
;=> #:db{:id 1}

;If entity does not exist, nil is returned:
(dh/entity @db -1) ; => nil

;Entity attributes can be lazily accessed through key lookups:
(:name (dh/entity @db 1)) ;=> "Alice"
(get (dh/entity @db 1) :name) ;=> "Alice"

;; you might need to release the connection for specific stores
(dh/release db)

;; clean up the database if it is not need any more
(dh/delete-database cfg)
