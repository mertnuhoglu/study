(ns mertnuhoglu.peerserver.e01)

; Adapted from: [Clojure in the Database · subhash/clj-stack Wiki](https://github.com/subhash/clj-stack/wiki/Clojure-in-the-Database)

; step 0:
; cd ~/codes/clj/lib/datomic-pro-1.0.6397
; bin/transactor -Ddatomic.printConnectionInfo=true dev-transactor.properties

(require '[datomic.api :as d])
(def uri "datomic:dev://localhost:4334/test")
(d/create-database uri)
; Error:
;Execution error (NullPointerException) at datomic.kv-cluster/kv-cluster (kv_cluster.clj:355).
;Cannot invoke "clojure.lang.IFn.invoke()" because the return value of "clojure.lang.IFn.invoke(Object)" is null

; a01:
; run repl
; bin/repl

(def conn (d/connect uri))
(d/q '[:find ?a :where [:db.part/db :db.install/attribute ?a]] (d/db conn))
; #{[39] [40] [41] [10] [42] [11] [43] [12] [44] [13] [45] [14] [46] [15] [47] [16] [17] [18] [50] [19] [51] [52] [62]}
