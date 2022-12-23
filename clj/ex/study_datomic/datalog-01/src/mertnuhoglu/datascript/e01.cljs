(ns mertnuhoglu.datascript.e01
  (:require [datascript.core :as d]))

;; rfr: Getting Started <url:file:///~/projects/study/logbook/log_20220924.md#r=g13580>
;; Source: [Getting Started](https://github.com/kristianmandrup/datascript-tutorial/blob/master/datascript_getting_started.md)

;;; Create a DataScript "connection" (an atom with the current DB value)
(def conn (d/create-conn))

;; Define datoms to transact
(def datoms [{:db/id -1 :name "Bob" :age 30}
             {:db/id -2 :name "Sally" :age 15}])

;;; Add the datoms via transaction
(d/transact! conn datoms)

;;; Query to find names for entities (people) whose age is less than 18
(def q-young '[:find ?n
               :in $ ?min-age
               :where
               [?e :name ?n]
               [?e :age ?a]
               [(< ?a ?min-age)]])

;; execute query: q-young, passing 18 as min-age
(d/q q-young @conn 18)

;; Query Result
;; => [["Sally"]]
