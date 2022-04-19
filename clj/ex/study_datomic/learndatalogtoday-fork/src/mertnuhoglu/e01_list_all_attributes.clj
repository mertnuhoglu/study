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
