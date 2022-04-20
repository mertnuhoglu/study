(ns mertnuhoglu.e01-list-all-attributes
  (:require
    [datomic.api :as d]
    [mertnuhoglu.e01 :as e01]))

; Adapted from: [Datomic: It turtles all the way down!](http://subhash.github.io/datomic/2015/01/24/datomic-turtles.html)

; List all attributes in datomic database
(d/q '[:find [?n ...] :where [:db.part/db :db.install/attribute ?a] [?a :db/ident ?n]] e01/db)
;=>
[:db/code
 :db.sys/reId
 :db/doc
 :movie/year
 :movie/sequel
 :db/fn
 :db.install/function
 :db/excise
 :db/cardinality
 :person/born
 :db/txInstant
 :trivia
 :db.excise/attrs
 :db.alter/attribute
 :db/noHistory
 :db/isComponent
 :db/fulltext
 :movie/director
 :fressian/tag
 :db/index
 :person/death
 :db/lang
 :db.excise/before
 :db.excise/beforeT
 :movie/cast
 :db.sys/partiallyIndexed
 :db.install/valueType
 :db.install/partition
 :db/valueType
 :db/unique
 :db/ident
 :movie/title
 :db.install/attribute
 :person/name]

; So :db.install/attribute would be defined someplace, right?
(d/q '[:find (pull ?a [*]) :where [?a :db/ident :db.install/attribute]] e01/db)
;=>
[[#:db{:id 13,
       :ident :db.install/attribute,
       :valueType #:db{:id 20},
       :cardinality #:db{:id 36},
       :doc "System attribute with type :db.type/ref. Asserting this attribute on :db.part/db with value v will install v as an attribute."}]]

;the type and cardinality information is hiding behind opaque ids. No worries, entity API to the rescue!
(-> e01/db (d/entity 36) (:db/ident))
;=> :db.cardinality/many

(-> e01/db (d/entity 20) (:db/ident))
;:=> db.type/ref

;what about :db.type/ref?
(d/q '[:find (pull ?a [*]) :where [?a :db/ident :db.type/ref ]] e01/db)
;=>
[[{:db/id 20,
   :db/ident :db.type/ref,
   :fressian/tag :ref,
   :db/doc "Value type for references. All references from one entity to another are through attributes with this value type."}]]

