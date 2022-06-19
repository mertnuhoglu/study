(ns mertnuhoglu.datomic.datomic-06-history)

; Copied from [History | Datomic](https://docs.datomic.com/on-prem/tutorial/history.html)

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

; Section: asOf Query

; Query the system for the most recent transactions

(def db (d/db conn))
(d/q '[:find (max 3 ?tx)
       :where [?tx :db/txInstant]]
  db)
;=> [[[13194139534407 13194139534406 13194139534405]]]

; Note: (max 3 ..)
; En güncel 3 transaction

(def txid (->> (d/q '[:find (max 3 ?tx)
                      :where [?tx :db/txInstant]]
                 db)
            first first last))

(def db-before (d/as-of db txid))

(d/q '[:find ?sku ?count
       :where [?inv :inv/sku ?sku]
              [?inv :inv/count ?count]]
  db-before)
;=> [["SKU-42" 100] ["SKU-21" 7] ["SKU-22" 7]]

; Section: history Query

(require '[clojure.pprint :as pp])
(def db-hist (d/history db))

(->> (d/q '[:find ?tx ?sku ?val ?op
            :where [?inv :inv/count ?val ?tx ?op]
                   [?inv :inv/sku ?sku]]
       db-hist)
  (sort-by first)
  (pp/pprint))
;=>
([13194139534405 "SKU-21" 7 true]
 [13194139534405 "SKU-42" 100 true]
 [13194139534405 "SKU-22" 7 true]
 [13194139534406 "SKU-22" 7 false]
 [13194139534407 "SKU-42" 1000 true]
 [13194139534407 "SKU-42" 100 false])

