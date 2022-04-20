(ns mertnuhoglu.tutorial-ground-up.e01-querying)

(use '[datomic.api :only [q db] :as d])
(use '[mertnuhoglu.tutorial-ground-up.e01 :only [conn] :as e01])

;; Querying

; A Transaction = A Fact
; Query: associates facts to form a relation

; all entities that have a name and age

; return list of scalars
(q '[:find ?n :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
;=> #{["Lucy"] ["Jerry"]}

; return single scalar value
(q '[:find ?n . :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
;=> "Lucy"

; return list of tuples
(q '[:find ?n ?a :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
;=> #{["Jerry" 44] ["Lucy" 32]}

; return list of scalars
(q '[:find [?n ...] :where [?e :person/name ?n] [?e :person/age ?a]] (db conn))
;=> ["Lucy" "Jerry"]

