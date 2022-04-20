(ns mertnuhoglu.datahike.e02-schema
  (:use [mertnuhoglu.datahike.e02])
  (:require [datahike.api :as dh]))

(dh/schema @db)
;=>
#_{:name #:db{:id 1, :ident :name, :cardinality :db.cardinality/one},
   :likes #:db{:ident :likes, :index true, :cardinality :db.cardinality/many, :id 2},
   :friends #:db{:ident :friends, :cardinality :db.cardinality/many, :id 3}}
