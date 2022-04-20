(ns mertnuhoglu.tutorial-ground-up.e01-metadata)

(use '[datomic.api :only [q db] :as d])
(use '[mertnuhoglu.tutorial-ground-up.e01 :only [conn] :as e01])

; List all attributes
(q '[:find ?n :where [:db.part/db :db.install/attribute ?a] [?a :db/ident ?n]] (db conn))
;=>
#_#{[:db/code]
    [:db.sys/reId]
    [:db/doc]
    [:job/title]
    [:db/fn]
    [:db.install/function]
    [:db/excise]
    [:db/cardinality]
    [:db/txInstant]
    [:db.excise/attrs]
    [:db.alter/attribute]
    [:db/noHistory]
    [:db/isComponent]
    [:db/fulltext]
    [:fressian/tag]
    [:db/index]
    [:person/job]
    [:db/lang]
    [:db.excise/before]
    [:job/salary]
    [:db.excise/beforeT]
    [:db.sys/partiallyIndexed]
    [:person/address]
    [:db.install/valueType]
    [:db.install/partition]
    [:db/valueType]
    [:db/unique]
    [:db/ident]
    [:person/age]
    [:db.install/attribute]
    [:person/name]}


; a pull within the find clause causes the entire entity to be projected
(q '[:find (pull ?a [*]) :where [?a :db/ident :db.install/attribute]] (db conn))
;=>
#_[[#:db{:id 13,
         :ident :db.install/attribute,
         :valueType #:db{:id 20},
         :cardinality #:db{:id 36},
         :doc "System attribute with type :db.type/ref. Asserting this attribute on :db.part/db with value v will install v as an attribute."}]]

;The type and cardinality are just opaque integers. But don't worry, the entity API comes to the rescuse

(-> (db conn) (d/entity 36) (:db/ident))
#_:db.cardinality/many
(-> (db conn) (d/entity 20) (:db/ident))
#_:db.type/ref

;That makes sense. :db.install/attribute is a ref because it "points" to other attribute definitions and it's cardinality is many obviously because there are "many" attributes.

;By now, something should be obvious. Everything is an entity in Datomic, even attribute definitions. The system is bootstrapped using some basic entities which set up the attribute types etc and everything else is built on top of that

(for [id (range 0 20)] (-> (db conn) (d/entity id) (:db/ident)))
;=>
#_(:db.part/db :db/add :db/retract :db.part/tx :db.part/user nil nil nil nil nil :db/ident :db.install/partition :db.install/valueType :db.install/attribute :db.install/function :db/excise :db.excise/attrs :db.excise/beforeT :db.excise/before :db.alter/attribute)


