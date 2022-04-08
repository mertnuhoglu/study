(ns mertnuhoglu.datomic-05-retract)

; Copied from: [Retract | Datomic](https://docs.datomic.com/on-prem/tutorial/retract.html)

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

; Section: Explicit Retract

; Feature: Keep a count of items in inventory

(def inventory-counts
  [{:db/ident :inv/count
    :db/valueType :db.type/long
    :db/cardinality :db.cardinality/one}])

(d/transact conn {:tx-data inventory-counts})

; Now we assert that we have 7 SKU-21 and 1000 SKU-42 in the inventory:
;; deliberate mistakes here!
(def inventory-update
  [[:db/add [:inv/sku "SKU-21"] :inv/count 7]
   [:db/add [:inv/sku "SKU-22"] :inv/count 7]
   [:db/add [:inv/sku "SKU-42"] :inv/count 100]])

(d/transact conn {:tx-data inventory-update})

; Retract the wrong data entry
(d/transact
  conn
  {:tx-data [[:db/retract [:inv/sku "SKU-22"] :inv/count 7]
             [:db/add "datomic.tx" :db/doc "remove incorrect assertion"]]})


; Section: Implicit Retract

; Correct: 1000 instead of 100
; Burada eski veriyi retract etmiyoruz, doğrudan doğru veriyi add ediyoruz
; Çünkü :cardinality/one kısıtından dolayı datomic bunu otomatik düzeltir
(d/transact
  conn
  {:tx-data [[:db/add [:inv/sku "SKU-42"] :inv/count 1000]
             [:db/add "datomic.tx" :db/doc "correct data entry error"]]})

(def db (d/db conn))
(d/q '[:find ?sku ?count
       :where [?inv :inv/sku ?sku]
              [?inv :inv/count ?count]]
  db)
;=> [["SKU-42" 1000] ["SKU-21" 7]]
