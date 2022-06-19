(ns mertnuhoglu.tutorial-ground-up.e01-ref)

(use '[datomic.api :only [q db] :as d])
(use '[mertnuhoglu.tutorial-ground-up.e01 :only [conn] :as e01])

;Instead we can model a job entity and allow the person to "point" to it. This is where db.type/ref makes its entry.

; job attributes
(d/transact conn [{:db/id #db/id[db.part/db] :db/ident :job/title :db/valueType :db.type/string :db/cardinality :db.cardinality/one :db.install/_attribute :db.part/db}])
(d/transact conn [{:db/id #db/id[db.part/db] :db/ident :job/salary :db/valueType :db.type/double :db/cardinality :db.cardinality/one :db.install/_attribute :db.part/db}])

; person references job
(d/transact conn [{:db/id #db/id[db.part/db] :db/ident :person/job :db/valueType :db.type/ref :db/cardinality :db.cardinality/one :db.install/_attribute :db.part/db}])

; Lucy finds a job
(d/transact conn [{:db/id 500 :job/title "Rockstar programmer" :job/salary 500000.00}])
(d/transact conn [{:db/id 103 :person/job 500}])

(q '[:find ?j . :where [?e :person/name "Lucy"] [?e :person/job ?jb] [?jb :job/title ?j]] (db conn)) ; "Rockstar programmer"
;=> "Rockstar programmer"

; ### db.part/db

; There's a better way to tie an entity to a reference without having to spell out its id.
; The expression :db/id #db/id[db.part/db 500] always generates the same unique id
; so we could also associate Lucy with a job this way:
(d/transact conn [{:db/id #db/id[db.part/db 500] :job/title "Rockstar programmer2" :job/salary 200000.00}])
(d/transact conn [{:db/id 103 :person/job #db/id[db.part/db 500]}])

(q '[:find ?j . :where [?e :person/name "Lucy"] [?e :person/job ?jb] [?jb :job/title ?j]] (db conn)) ; "Rockstar programmer"
;=> "Rockstar programmer2"

; ### Reverse ref id=g12897

;Here, we created a job entity and pointed a person to it. Instead, we could create a job and reverse-point a person to it, all in one step. i.e.
(d/transact conn [{:db/id #db/id[:db.part/user] :job/title "Startup founder" :job/salary 50000.00 :person/_job 103}])

(q '[:find ?e ?v :where [?e :job/title ?v]] (db conn))
#_#{[500 "Rockstar programmer2"] [17592186045433 "Startup founder"]}

