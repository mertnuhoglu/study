(ns dev-local.datalog02-ex-22)

; Tarih: 20230607
; Hata: not-an-entity Unable to resolve entity

(require '[datomic.client.api :as d])

;; Memory storage
(def client (d/client {:server-type :dev-local
                       :storage-dir :mem
                       :system "ci"}))

(d/delete-database client {:db-name "db22"})
(d/create-database client {:db-name "db22"})
(def conn (d/connect client {:db-name "db22"}))

; a01:
(d/transact conn [{:db/ident       :user/name
                   :db/valueType   :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/unique      :db.unique/identity}])
(def tx-result
  (d/transact conn [{:user/name    "jillosaurus"}
                    {:user/name    "jonnyboy"}]))

(d/q '[:find ?e ?name
       :where [?e :user/name ?name]]
  (d/db conn))
;Execution error (ExceptionInfo) at datomic.core.error/raise (error.clj:55).
;:db.error/not-an-entity Unable to resolve entity: :user/name

; a02:
(def schema
  [{:db/ident       :user/name2
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity}])
(d/transact conn {:tx-data schema})
(def data [{:user/name2 "un01"}])

(d/transact conn {:tx-data data})

(d/q
  '[:find ?e
    :where
    [?e :user/name2 "un01"]]
  (d/db conn))
;=> [[83562883711050]]

; Problemin sebebi:
; a01 ve a02 arasındaki tek fark, schema ilkinde transact fonksiyonuna :tx-data olmadan paslanmış
; ikincisinde :tx-data ile paslanmış.

; Dikkat:
; a01:
;(d/transact conn [{:db/ident       :user/name}])
; a02:
;(d/transact conn {:tx-data schema})
