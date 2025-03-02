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
;
; q: Bu tuple: `[:inv/sku "SKU-25"]` hangi değişkene bağlanıyor? `?inv` değişkenine.
;   id:: 2803ff84-04f9-405d-a337-6945821002a5
; Ancak tuple iki değerden oluşuyor. Hangi değer `?inv` değişkenine bağlanıyor?
; ans: Bu tuple `:item/id` fk sının değeri olan `?inv` değişkeniyle entity id üretiyor olmalı.
; Nasıl? Çünkü schema tanımında `:inv/sku` atributu pk olarak tanımlanmış:
; rfr: def schema-1 || ((7509f916-1f37-4b38-b167-319cccce4c81))

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
