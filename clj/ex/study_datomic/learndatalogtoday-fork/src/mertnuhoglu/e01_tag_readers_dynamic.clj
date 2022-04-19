(ns mertnuhoglu.e01-tag-readers-dynamic
  (:refer-clojure :exclude (read-string))
  (:require
    [datomic.api :as d]
    [clojure.edn :as edn]
    [clojure.java.io :refer (resource)]))

; Adapted from: [datomic-extras/extras.clj at master · juxt/datomic-extras](https://github.com/juxt/datomic-extras/blob/master/src/juxt/datomic/extras.clj)

(defn read-string [s]
  ;; We can't guarantee that Datomic was in the classpath when Clojure
  ;; was loaded, so these bindings may not have been picked up.
  (edn/read-string
    {:readers {'db/id datomic.db/id-literal
               'db/fn datomic.function/construct
               'base64 datomic.codec/base-64-literal}}
    s))

(def schema (read-string (slurp "resources/db/schema.edn")))
(def seed-data (read-file "resources/db/data.edn"))
(def uri "datomic:mem://movies")
(def conn
  (do (d/delete-database uri)
    (d/create-database uri)
    (d/connect uri)))
(d/transact conn schema)
(d/transact conn seed-data)
