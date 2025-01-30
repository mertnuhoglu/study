(ns mert.datalog-04-01)

; #d/20250129
; spcs: New Datomic Project 20250129 || ((7e9d0f4e-cba7-4c93-8f04-0d149eb23565))
; Datomic Pro vs Datomic Local || ((5f8e6607-5407-4108-af56-2462d92739bb))

(require '[datomic.api :as d])

(def db-uri "datomic:dev://localhost:4334/hello")

(d/create-database db-uri)

(def conn (d/connect db-uri))

(identity conn)

@(d/transact conn [{:db/doc "Hello world"}])
; {:db-before datomic.db.Db@57b5f126,
;  :db-after datomic.db.Db@a044c264,
;  :tx-data
;  [#datom[13194139534314 50 #inst "2025-01-29T08:30:23.076-00:00" 13194139534314 true] #datom[17592186045419 62 "Hello world" 13194139534314 true]],
;  :tempids {-9223300668110598133 17592186045419}}

