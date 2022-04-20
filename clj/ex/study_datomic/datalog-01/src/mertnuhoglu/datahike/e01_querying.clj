(ns mertnuhoglu.datahike.e01-querying
  (:use [mertnuhoglu.datahike.e01])
  (:require [datahike.api :as dh]))

; # Querying

;; search data using query vector
; :find <pattern-variables> :where <data-patterns>
(dh/q
  '[:find ?e ?n ?a
    :where
    [?e :name ?n]
    [?e :age ?a]]
  @conn)
;; => #{[3 "Alice" 20] [4 "Bob" 30] [5 "Charlie" 40]}

;; add new entity data using a hash map
(dh/transact conn {:tx-data [{:db/id 3 :age 25}]})

;; if you want to work with queries like in ; https://grishaev.me/en/datomic-query/,
;; you may use a hashmap
(dh/q
  {:query '{:find [?e ?n ?a]
            :where [[?e :name ?n]
                    [?e :age ?a]]}
   :args [@conn]})
;; => #{[5 "Charlie" 40] [4 "Bob" 30] [3 "Alice" 25]}

;; query the history of the data
(dh/q '[:find ?a
        :where
        [?e :name "Alice"]
        [?e :age ?a]]
  (dh/history @conn))
;; => #{[20] [25]}
