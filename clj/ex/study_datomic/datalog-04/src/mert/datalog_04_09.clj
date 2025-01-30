(ns mert.datalog-04-09)
(require '[datomic.client.api :as d])
(def cfg {:server-type :datomic-local
          :system "datomic-samples"})
(def client (d/client cfg))
(d/create-database client {:db-name "tutorial"})
(def conn (d/connect client {:db-name "tutorial"}))
;; [[datalog_04_09.clj]]

(def order-schema
  [{:db/ident :order/items
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/many
    :db/isComponent true}
   {:db/ident :item/id
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}
   {:db/ident :item/count
    :db/valueType :db.type/long
    :db/cardinality :db.cardinality/one}])

(d/transact conn {:tx-data order-schema})

(def add-order
  {:order/items
   [{:item/id [:inv/sku "SKU-25"]
     :item/count 10}
    {:item/id [:inv/sku "SKU-26"]
     :item/count 20}]})

(d/transact conn {:tx-data [add-order]})
; {:db-before
;  #datomic.core.db.Db{:id "tutorial", :basisT 15, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil},
;  :db-after
;  #datomic.core.db.Db{:id "tutorial", :basisT 16, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil},
;  :tx-data
;  [#datom[13194139533328 50 #inst "2025-01-29T12:52:37.116-00:00" 13194139533328 true] #datom[92358976733337 77 92358976733338 13194139533328 true] #datom[92358976733338 78 92358976733294 13194139533328 true] #datom[92358976733338 79 10 13194139533328 true] #datom[92358976733337 77 92358976733339 13194139533328 true] #datom[92358976733339 78 92358976733295 13194139533328 true] #datom[92358976733339 79 20 13194139533328 true]],
;  :tempids
;  {"datomic.temp-1000122" 92358976733339,
;   "datomic.temp-1000121" 92358976733338}}
