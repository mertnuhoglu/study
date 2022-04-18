(ns mert.db
  (:require [clojure.java.io :as io]
          [xtdb.api :as xt]))

;; Copied from: [Quickstart · XTDB Docs](https://docs.xtdb.com/guides/quickstart/)

(defn start-xtdb! []
  (letfn [(kv-store [dir]
            {:kv-store {:xtdb/module 'xtdb.rocksdb/->kv-store
                        :db-dir (io/file dir)
                        :sync? true}})]
    (xt/start-node
      {:xtdb/tx-log (kv-store "data/dev/tx-log")
       :xtdb/document-store (kv-store "data/dev/doc-store")
       :xtdb/index-store (kv-store "data/dev/index-store")})))

(def xtdb-node (start-xtdb!))

(defn stop-xtdb! []
  (.close xtdb-node))

(comment
  (xt/submit-tx xtdb-node [[::xt/put
                            {:xt/id "hi2u"
                             :user/name "zig"}]])
  ;; => #::xt{:tx-id 0, :tx-time #inst "2021-03-11T02:27:09.176-00:00"}

  (xt/q (xt/db xtdb-node) '{:find [e]
                            :where [[e :user/name "zig"]]})
  ;; => #{["hi2u"]}

  (stop-xtdb!)
  ;; => nil
  ,)