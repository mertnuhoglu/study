(ns mert.datahike-02
  (:require [datahike.api :as d]
          [datahike.migrate :refer [export-db]])
  (:gen-class))


;; Copied from: [tvaisanen.com: Getting Started With Datalog and Datahike](https://tvaisanen.com/posts-output/2021-01-14-getting-started-with-datalog-and-datahike/)

; Create database

(def cfg {:schema-flexibility :read ;; allow write without explicit schema
          :store {:backend :mem}})  ;; use in-memory database

(d/create-database cfg)

(def conn (d/connect cfg))

(d/transact conn {:tx-data [{:db/id 4
                             :job "Clojure Fullstack"
                             :position :fullstack-developer
                             :languages #{:javascript :clojure}}
                            {:db/id -1
                             :job "Clojure Backend Developer"
                             :position :backend-developer
                             :languages #{:clojure}}
                            {:db/id -1
                             :job "Javascript Fullstack"
                             :position :fullstack-developer
                             :languages #{:javascript}}]})

;(export-db @conn "./eavt-dump")
(export-db conn "./eavt-dump")

(d/q '[:find ?e ?title ?langs
       :where
       [?e :position ?title]
       [?e :languages ?langs]]
  @conn)

