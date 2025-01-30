(ns mert.datalog-04-10)
(require '[datomic.client.api :as d])
(def cfg {:server-type :datomic-local
          :system "datomic-samples"})
(def client (d/client cfg))
(d/create-database client {:db-name "tutorial"})
(def conn (d/connect client {:db-name "tutorial"}))
;; [[datalog_04_10.clj]]

(def db (d/db conn))
(d/q
 '[:find ?sku
   :in $ ?inv
   :where
   [?item :item/id ?inv]
   [?order :order/items ?item]
   [?order :order/items ?other-item]
   [?other-item :item/id ?other-inv]
   [?other-inv :inv/sku ?sku]]
 db [:inv/sku "SKU-25"]) 
; [["SKU-25"] ["SKU-26"]]

(def rules
  '[[(ordered-together ?inv ?other-inv)
     [?item :item/id ?inv]
     [?order :order/items ?item]
     [?order :order/items ?other-item]
     [?other-item :item/id ?other-inv]]])

(d/q
 '[:find ?sku
   :in $ % ?inv
   :where
   (ordered-together ?inv ?other-inv)
   [?other-inv :inv/sku ?sku]]
 db rules [:inv/sku "SKU-25"]) 
; [["SKU-25"] ["SKU-26"]]
