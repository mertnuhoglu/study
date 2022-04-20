(ns mertnuhoglu.datahike.e02-datoms
  (:use [mertnuhoglu.datahike.e02])
  (:require [datahike.api :as dh]))

(dh/datoms @db {:index :eavt})
;=>
#_(#datahike/Datom[1 :db/cardinality :db.cardinality/one 536870913 true]
    #datahike/Datom[1 :db/ident :name 536870913 true]
    #datahike/Datom[1 :db/type :db.type/string 536870913 true]
    #datahike/Datom[2 :db/cardinality :db.cardinality/many 536870913 true]
    #datahike/Datom[2 :db/ident :likes 536870913 true]
    #datahike/Datom[2 :db/index true 536870913 true]
    #datahike/Datom[2 :db/type :db.type/string 536870913 true]
    #datahike/Datom[3 :db/cardinality :db.cardinality/many 536870913 true]
    #datahike/Datom[3 :db/ident :friends 536870913 true]
    #datahike/Datom[3 :db/type :db.type/ref 536870913 true]
    #datahike/Datom[4 :friends 5 536870914 true]
    #datahike/Datom[4 :likes "fries" 536870914 true]
    #datahike/Datom[4 :likes "pizza" 536870914 true]
    #datahike/Datom[4 :name "Ivan" 536870914 true]
    #datahike/Datom[5 :likes "candy" 536870915 true]
    #datahike/Datom[5 :likes "pie" 536870915 true]
    #datahike/Datom[5 :likes "pizza" 536870915 true]
    #datahike/Datom[5 :name "Oleg" 536870915 true]
    #datahike/Datom[536870913 :db/txInstant #inst"2022-04-18T07:15:33.913-00:00" 536870913 true]
    #datahike/Datom[536870914 :db/txInstant #inst"2022-04-18T07:15:38.693-00:00" 536870914 true]
    #datahike/Datom[536870915 :db/txInstant #inst"2022-04-18T07:15:40.465-00:00" 536870915 true])
