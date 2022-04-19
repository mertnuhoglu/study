(ns mertnuhoglu.learndatalogtoday.e01
  (:require [clojure.edn :as edn]))

; Copied from: [Connect to a Database | Datomic](https://docs.datomic.com/on-prem/getting-started/connect-to-a-database.html)

; Step01: Run datomic server first:

; cd /Users/mertnuhoglu/codes/clj/lib/datomic-pro-${VERSION}
; bin/run -m datomic.peer-server -h localhost -p 8998 -a myaccesskey,mysecret -d hello,datomic:mem://hello

(require '[datomic.client.api :as d])

(def cfg {:server-type :peer-server
          :access-key "myaccesskey"
          :secret "mysecret"
          :endpoint "localhost:8998"
          :validate-hostnames false})
;#'user/cfg

(def client (d/client cfg))
;#'user/client
(def conn (d/connect client {:db-name "movies"}))
;#'user/conn

(defn read-file [s]
  (read-string (slurp s)))

(def schema (read-file "/Users/mertnuhoglu/codes/clj/content/learndatalogtoday/resources/db/data.edn"))
(def seed-data (read-file "resources/db/data.edn"))

(comment
  (require 'datomic.db
      'datomic.function
      'datomic.codec)
  (edn/read
    {:readers {'db/id  datomic.db/id-literal
               'db/fn  datomic.function/construct
               'base64 datomic.codec/base-64-literal}}
    "/Users/mertnuhoglu/codes/clj/content/learndatalogtoday/resources/db/data.edn"))



