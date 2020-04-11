(ns crux01.core
  (:require [crux.api :as crux]))

(def crux
  (crux/start-node
    {:crux.node/topology :crux.standalone/topology
     :crux.node/kv-store "crux.kv.memdb/kv"
     :crux.standalone/event-log-dir "data/eventlog-1"
     :crux.kv/db-dir "data/db-dir-1"
     :crux.standalone/event-log-kv-store "crux.kv.memdb/kv"}))

(def manifest
  {:crux.db/id :manifest
   :pilot-name "Johanna"
   :id/rocket "SB002-sol"
   :id/employee "22910x2"
   :badges "SETUP"
   :cargo ["stereo" "gold fish" "slippers" "secret note"]})

(crux/submit-tx crux [[:crux.tx/put manifest]])

(crux/entity (crux/db crux) :manifest)
