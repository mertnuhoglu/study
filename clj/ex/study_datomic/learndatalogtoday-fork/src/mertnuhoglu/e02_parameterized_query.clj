(ns mertnuhoglu.e02-parameterized-query
  (:use [mertnuhoglu.e01])
  (:require
    [datomic.api :as d]))

; # Parameterized Query

(d/q
  '[:find ?name
    :in $ ?name
    :where
    [?p :person/name ?name]]
  db "Sylvester Stallone")
#_ #{["Sylvester Stallone"]}

(d/q
  '[:find ?title
    :in $ ?name
    :where
    [?p :person/name ?name]
    [?m :movie/cast ?p]
    [?m :movie/title ?title]]
  db "Sylvester Stallone")

(d/q
  {:query '[:find ?title
            :in $ ?name
            :where
            [?p :person/name ?name]
            [?m :movie/cast ?p]
            [?m :movie/title ?title]]
   :args  [db "Sylvester Stallone"]})
#_ #{["First Blood"] ["Rambo III"] ["Rambo: First Blood Part II"]}
