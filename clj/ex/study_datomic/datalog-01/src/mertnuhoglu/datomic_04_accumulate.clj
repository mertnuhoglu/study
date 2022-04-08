(ns mertnuhoglu.datomic-04-accumulate)

; Copied from: [Accumulate | Datomic](https://docs.datomic.com/on-prem/tutorial/accumulate.html)

; Step01: Run datomic server first:

; cd /Users/mertnuhoglu/codes/clj/lib/datomic-pro-${VERSION}
; bin/run -m datomic.peer-server -h localhost -p 8998 -a myaccesskey,mysecret -d hello,datomic:mem://hello

(require '[datomic.client.api :as d])

(def cfg {:server-type :peer-server
          :access-key "myaccesskey"
          :secret "mysecret"
          :endpoint "localhost:8998"
          :validate-hostnames false})
;#'user/cfg

(def client (d/client cfg))
;#'user/client
(def conn (d/connect client {:db-name "hello"}))
;#'user/conn

; Step02: Depends on datomic_03

; Section: More Schema

; Feature: Track orders
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

; Section: More Data

(def add-order
  {:order/items
   [{:item/id [:inv/sku "SKU-25"]
     :item/count 10}
    {:item/id [:inv/sku "SKU-26"]
     :item/count 20}]})

; this is a nested entity map
; top level: :order/items
; nested level: two line items

(d/transact conn {:tx-data [add-order]})

; Chapter: Read Revisited

; Copied from: [Read Revisited | Datomic](https://docs.datomic.com/on-prem/tutorial/read-revisited.html)

(def db (d/db conn))

; Section: Parameterized Query

; ex: Given any item, find all the other items that have ever appeared in the same order
; This query requires one additional parameter:
; - an item's entity id
(d/q '[:find ?sku
       :in $ ?inv
       :where [?item :item/id ?inv]
              [?order :order/items ?item]
              [?order :order/items ?other-item]
              [?other-item :item/id ?other-inv]
              [?other-inv :inv/sku ?sku]]
  db [:inv/sku "SKU-25"])
;=> [["SKU-25"] ["SKU-26"]]

; Parameters enter query being named by a corresponding :in clause
; `$` name is a placeholder for database value `db`

; Section: Rules

; Create a rule named ordered-together
; It binds two variables ?inv and ?other-inv
; if these inventory items ever appear in the same order
(def rules
  '[[(ordered-together ?inv ?other-inv)
     [?item :item/id ?inv]
     [?order :order/items ?item]
     [?order :order/items ?other-item]
     [?other-item :item/id ?other-inv]]])

; Pass `rules` to a query, using special :in name `%`
(d/q '[:find ?sku
       :in $ % ?inv
       :where (ordered-together ?inv ?other-inv)
              [?other-inv :inv/sku ?sku]]
  db rules [:inv/sku "SKU-25"])
;=> [["SKU-25"] ["SKU-26"]]
