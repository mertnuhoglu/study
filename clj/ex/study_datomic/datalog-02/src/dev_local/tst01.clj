(ns dev-local.tst01)

; Tarih: 20230609

(require '[datomic.client.api :as d])

;; Memory storage
(def client (d/client {:server-type :dev-local
                       :storage-dir :mem
                       :system "ci"}))

(d/delete-database client {:db-name "db"})
(d/create-database client {:db-name "db"})
(def conn (d/connect client {:db-name "db"}))

; a01:
(d/transact conn [{:db/ident       :title
                   :db/valueType   :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/unique      :db.unique/identity}])
(def tx-result
  (d/transact conn [{:title "un01"}
                    {:title "un02"}]))

(d/q '[:find ?e ?name
       :where [?e :title ?name]]
  (d/db conn))
;Execution error (ExceptionInfo) at datomic.core.error/raise (error.clj:55).
;:db.error/not-an-entity Unable to resolve entity: :title

; #grsm/tst Yukarıdaki `not-an-entity` hatasının sebebi ve çözümü nedir? 
;   id:: 30c695af-98a0-410c-90b9-6883ce46c1b0
