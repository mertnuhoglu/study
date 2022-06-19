(ns mertnuhoglu.tutorial-ground-up.e01)

; Copied from: [Datomic from the ground up · subhash/clj-stack Wiki](https://github.com/subhash/clj-stack/wiki/Datomic-from-the-ground-up)

(use '[datomic.api :only [q db] :as d])
; (def uri "datomic:mem://movies")
(def uri "datomic:dev://localhost:4334/ground-up")
; error: datomic:dev ile bağlanamazsan, datomic-free'yi bağımlılıklardan çıkartıp dene
(d/create-database uri)
(def conn (d/connect uri))

; define attributes
(d/transact conn [{:db/id 101 :db/ident :person/name :db/valueType :db.type/string :db/cardinality :db.cardinality/one :db.install/_attribute :db.part/db}])
(d/transact conn [{:db/id 102 :db/ident :person/age :db/valueType :db.type/long :db/cardinality :db.cardinality/one :db.install/_attribute :db.part/db}])

; add data
(d/transact conn [{:db/id 103 :person/name "Lucy" :person/age 32}])
(d/transact conn [{:db/id 104 :person/name "Fred"}])

; Now, add a new attribute
(d/transact conn [{:db/id 105 :db/ident :person/address :db/valueType :db.type/string :db/cardinality :db.cardinality/one :db.install/_attribute :db.part/db}])
; add person including new attribute
(d/transact conn [{:db/id 106 :person/name "Jerry" :person/age 44 :person/address "32 Hounds St"}])

; It will quickly get tiring to manually assign unique ids to entities.
; Luckily, Datomic has a solution for this:
; {:db/id #db/id[db.part/db]}
; will automatically generate unique ids.
; db.part/db indicates a "partition" within which this id will be unique
; and is usually used for attribute definitions.
; For user data, we use db.part/user
(d/transact conn [{:db/id #db/id[db.part/db] :person/name "Ali" :person/age 54}])


; ## Querying: e01-querying.clj

; ## References: e01-ref.clj

; ## Meta Data: e01-metadata.clj
