(ns mertnuhoglu.learndatalogtoday.study-literal-tag-db-id
  (:refer-clojure :exclude (read-string))
  (:require
    [fipp.edn :refer [pprint] :rename {pprint fipp}]
    [datomic.api :as d]
    [clojure.edn :as edn]))

; Adapted from: [Reader macros and why you can go wrong with them](http://subhash.github.io/datomic/reader/2015/05/12/reader-macros.html)

(defn read-string [s]
  ;; We can't guarantee that Datomic was in the classpath when Clojure
  ;; was loaded, so these bindings may not have been picked up.
  (edn/read-string
    {:readers {'db/id datomic.db/id-literal
               'db/fn datomic.function/construct
               'base64 datomic.codec/base-64-literal}}
    s))

(def schema (read-string (slurp "resources/db/data02.edn")))

(def id (datomic.db/id-literal [:db.part/user]))

(fipp id)
;=> #datomic.db.DbId{:part :db.part/user, :idx -1000001}

(def id (datomic.db/id-literal [:db/id]))
(fipp id)
;=> #datomic.db.DbId{:part :db/id, :idx -10000021

(associative? id)
;true
(keys id)
;(:part :idx)
(vals id)
;=> (:db/id -1000002)