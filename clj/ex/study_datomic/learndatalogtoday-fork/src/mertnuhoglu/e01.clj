(ns mertnuhoglu.e01
  (:require
    [datomic.api :as d]))

(defn read-file [s]
  (read-string (slurp s)))

(def schema (read-file "resources/db/schema.edn"))
(def seed-data (read-file "resources/db/data.edn"))
(def uri "datomic:mem://movies")
(def conn
  (do (d/delete-database uri)
    (d/create-database uri)
    (d/connect uri)))
(d/transact conn schema)
(d/transact conn seed-data)
(def db (d/db conn))

(d/q
  '{:find (?title), :where ([_ :movie/title ?title])}
  db)
;=>
#{["Alien"]
  ["First Blood"]
  ["Terminator 2: Judgment Day"]}

(d/q
  '[:find ?name
    :where
    [?m :movie/title "Lethal Weapon"]
    [?m :movie/cast ?p]
    [?p :person/name ?name]]
  db)
#_#{["Mel Gibson"] ["Gary Busey"] ["Danny Glover"]}
