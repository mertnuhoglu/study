(ns mert.datalog-04-12)
(require '[datomic.client.api :as d])
(def cfg {:server-type :datomic-local
          :system "datomic-samples"})
(def client (d/client cfg))
(d/create-database client {:db-name "tutorial"})
(def conn (d/connect client {:db-name "tutorial"}))
;; [[datalog_04_12.clj]]

(def db (d/db conn))

(def inventory-counts
  [{:db/ident :inv/count
    :db/valueType :db.type/long
    :db/cardinality :db.cardinality/one}])

(d/transact conn {:tx-data inventory-counts})

(def inventory-update
  [[:db/add [:inv/sku "SKU-21"] :inv/count 7]
   [:db/add [:inv/sku "SKU-22"] :inv/count 7]
   [:db/add [:inv/sku "SKU-42"] :inv/count 100]])

(d/transact conn {:tx-data inventory-update})

(d/transact
 conn
 {:tx-data [[:db/retract [:inv/sku "SKU-22"] :inv/count 7]
            [:db/add "datomic.tx" :db/doc "Retract incorrect assertion"]]})

(d/transact
 conn
 {:tx-data [[:db/add [:inv/sku "SKU-42"] :inv/count 1000]
            [:db/add "datomic.tx" :db/doc "Update inventory"]]})

@(def recent-txns
   (d/q
    '[:find (max 3 ?tx)      ;; max
      :where
      [?tx :db/txInstant]]
    (d/db conn))) 
; [[[13194139533336 13194139533335 13194139533334]]]

;; Get the earliest transaction id
(def txid
  (->> recent-txns
       ffirst
       (apply min)))

@(def db-before (d/as-of db txid))

(d/q
 '[:find ?sku ?count
   :where
   [?inv :inv/sku ?sku]
   [?inv :inv/count ?count]]
 db-before) 
; [["SKU-42" 100] ["SKU-21" 7] ["SKU-22" 7]]

(def db-hist (d/history (d/db conn)))

(->> (d/q
      '[:find ?tx ?sku ?val ?op
        :where
        [?inv :inv/count ?val ?tx ?op]
        [?inv :inv/sku ?sku]]
      db-hist)
     (sort-by first))
; ([13194139533330 "SKU-42" 100 true]
;  [13194139533330 "SKU-22" 7 true]
;  [13194139533330 "SKU-21" 7 true]
;  [13194139533331 "SKU-22" 7 false]
;  [13194139533332 "SKU-42" 100 false]
;  [13194139533332 "SKU-42" 1000 true]
;  [13194139533334 "SKU-42" 1000 false]
;  [13194139533334 "SKU-42" 100 true]
;  [13194139533334 "SKU-22" 7 true]
;  [13194139533335 "SKU-22" 7 false]
;  [13194139533336 "SKU-42" 100 false]
;  [13194139533336 "SKU-42" 1000 true])
