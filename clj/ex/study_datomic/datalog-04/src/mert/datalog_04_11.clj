(ns mert.datalog-04-11)
(require '[datomic.client.api :as d])
(def cfg {:server-type :datomic-local
          :system "datomic-samples"})
(def client (d/client cfg))
(d/create-database client {:db-name "tutorial"})
(def conn (d/connect client {:db-name "tutorial"}))
;; [[datalog_04_11.clj]]

(def db (d/db conn))

(def inventory-counts
  [{:db/ident :inv/count
    :db/valueType :db.type/long
    :db/cardinality :db.cardinality/one}])

(d/transact conn {:tx-data inventory-counts})

;; deliberate mistakes here!
(def inventory-update
  [[:db/add [:inv/sku "SKU-21"] :inv/count 7]
   [:db/add [:inv/sku "SKU-22"] :inv/count 7]
   [:db/add [:inv/sku "SKU-42"] :inv/count 100]])

(d/transact conn {:tx-data inventory-update})
; {:db-before
;  #datomic.core.db.Db{:id "tutorial", :basisT 17, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil},
;  :db-after
;  #datomic.core.db.Db{:id "tutorial", :basisT 18, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil},
;  :tx-data
;  [#datom[13194139533330 50 #inst "2025-01-29T13:00:59.908-00:00" 13194139533330 true] #datom[92358976733290 80 7 13194139533330 true] #datom[92358976733291 80 7 13194139533330 true] #datom[92358976733311 80 100 13194139533330 true]],
;  :tempids {}}

(d/transact
 conn
 {:tx-data [[:db/retract [:inv/sku "SKU-22"] :inv/count 7]
            [:db/add "datomic.tx" :db/doc "remove incorrect assertion"]]})
; {:db-before
;  #datomic.core.db.Db{:id "tutorial", :basisT 18, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil},
;  :db-after
;  #datomic.core.db.Db{:id "tutorial", :basisT 19, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil},
;  :tx-data
;  [#datom[13194139533331 50 #inst "2025-01-29T13:01:20.282-00:00" 13194139533331 true] #datom[92358976733291 80 7 13194139533331 false] #datom[13194139533331 63 "remove incorrect assertion" 13194139533331 true]],
;  :tempids {"datomic.tx" 13194139533331}}

(d/transact
 conn
 {:tx-data [[:db/add [:inv/sku "SKU-42"] :inv/count 1000]
            [:db/add "datomic.tx" :db/doc "correct data entry error"]]})
; {:db-before
;  #datomic.core.db.Db{:id "tutorial", :basisT 19, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil},
;  :db-after
;  #datomic.core.db.Db{:id "tutorial", :basisT 20, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil},
;  :tx-data
;  [#datom[13194139533332 50 #inst "2025-01-29T13:01:33.754-00:00" 13194139533332 true] #datom[92358976733311 80 1000 13194139533332 true] #datom[92358976733311 80 100 13194139533332 false] #datom[13194139533332 63 "correct data entry error" 13194139533332 true]],
;  :tempids {"datomic.tx" 13194139533332}}

(def db (d/db conn))

(d/q
 '[:find ?sku ?count
   :where
   [?inv :inv/sku ?sku]
   [?inv :inv/count ?count]]
 db) 
; [["SKU-42" 1000] ["SKU-21" 7]]


