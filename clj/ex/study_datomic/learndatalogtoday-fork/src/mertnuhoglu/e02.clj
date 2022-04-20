(ns mertnuhoglu.e02
  (:use [mertnuhoglu.e01])
  (:require
    [datomic.api :as d]))

; Copied from: [Learn Datalog Today!](http://www.learndatalogtoday.org/chapter/1)

; Find the entity ids of movies made in 1987
(d/q
  '[:find ?e
    :where [?e :movie/year 1987]]
  db)
#_#{[17592186045470] [17592186045471] [17592186045472]}

; Find the titles of movies made in 1987
(d/q
  '[:find ?t
    :where
    [?e :movie/year 1987]
    [?e :movie/title ?t]]
  db)
#_#{["RoboCop"] ["Lethal Weapon"] ["Predator"]}
