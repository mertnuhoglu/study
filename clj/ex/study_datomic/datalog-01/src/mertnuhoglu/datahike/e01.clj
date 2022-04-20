(ns mertnuhoglu.datahike.e01
  (:require [datahike.api :as dh]))

; Copied from: [replikativ/datahike: A durable Datalog implementation adaptable for distribution.](https://github.com/replikativ/datahike)

(def cfg {:store {:backend :file :path "/tmp/datahike_01_zipper"}})
(dh/create-database cfg)
(def conn (dh/connect cfg))

;; the first transaction will be the schema we are using
;; you may also add this within database creation by adding :initial-tx
;; to the configuration
(dh/transact conn [{:db/ident :name
                    :db/valueType :db.type/string
                    :db/cardinality :db.cardinality/one}
                   {:db/ident :age
                    :db/valueType :db.type/long
                    :db/cardinality :db.cardinality/one}])

;; lets add some data and wait for the transaction
(dh/transact conn [{:name  "Alice", :age   20}
                   {:name  "Bob", :age   30}
                   {:name  "Charlie", :age   40}
                   {:age 15}])

; # Querying e01_querying.clj

