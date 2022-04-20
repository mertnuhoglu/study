(ns mertnuhoglu.datahike.e02
  (:require [datahike.api :as dh]))

; Adapted from:
; [replikativ/datahike: A durable Datalog implementation adaptable for distribution.](https://github.com/replikativ/datahike)
; [datahike.api — io.replikativ/datahike 0.4.1491](https://cljdoc.org/d/io.replikativ/datahike/0.4.1491/api/datahike.api#datoms)

(def cfg {:store {:backend :mem :id "schemaless"} :schema-flexibility :read})
(dh/create-database cfg)
(def db (dh/connect cfg))

; Add referenced data
(dh/transact db [{:db/ident :name
                  :db/type :db.type/string
                  :db/cardinality :db.cardinality/one}
                 {:db/ident :likes
                  :db/type :db.type/string
                  :db/index true
                  :db/cardinality :db.cardinality/many}
                 {:db/ident :friends
                  :db/type :db.type/ref
                  :db/cardinality :db.cardinality/many}])

(dh/transact db [{:db/id 4 :name "Ivan"}
                 {:db/id 4 :likes "fries"}
                 {:db/id 4 :likes "pizza"}
                 {:db/id 4 :friends 5}])

(dh/transact db [{:db/id 5 :name "Oleg"}
                 {:db/id 5 :likes "candy"}
                 {:db/id 5 :likes "pie"}
                 {:db/id 5 :likes "pizza"}])
