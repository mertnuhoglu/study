(ns mert.datalog-04-08)
(require '[datomic.client.api :as d])
(def cfg {:server-type :datomic-local
          :system "datomic-samples"})
(def client (d/client cfg))
(d/create-database client {:db-name "tutorial"})
(def conn (d/connect client {:db-name "tutorial"}))
;; [[datalog_04_08.clj]]

(def db (d/db conn))

(d/pull
  (d/db conn)
  [{:inv/color [:db/ident]}
   {:inv/size [:db/ident]}
   {:inv/type [:db/ident]}]
  [:inv/sku "SKU-42"])
; #:inv{:color #:db{:ident :blue},
;       :size #:db{:ident :large},
;       :type #:db{:ident :dress}}

(def tx1
  (d/transact conn {:tx-data
                    [{:inv/color {:db/ident :blue}
                      :inv/size {:db/ident :large}
                      :inv/type {:db/ident :dress}}]}))
(d/pull
  (:db-before tx1)
  [{:inv/color [:db/ident]}
   {:inv/size [:db/ident]}
   {:inv/type [:db/ident]}]
  [:inv/sku "SKU-42"])
; #:inv{:color #:db{:ident :blue},
;       :size #:db{:ident :large},
;       :type #:db{:ident :dress}}

(d/q
  '[:find ?sku
    :where
    [?e :inv/sku "SKU-42"]
    [?e :inv/color ?color]
    [?e2 :inv/color ?color]
    [?e2 :inv/sku ?sku]]
  db)
; [["SKU-40"]
;  ["SKU-43"]
;  ["SKU-32"]
;  ["SKU-44"]
;  ["SKU-33"]
;  ["SKU-41"]
;  ["SKU-42"]
;  ["SKU-47"]
;  ["SKU-36"]
;  ["SKU-37"]
;  ["SKU-45"]
;  ["SKU-34"]
;  ["SKU-46"]
;  ["SKU-35"]
;  ["SKU-38"]
;  ["SKU-39"]]

; select *
; from inv e
; left join inv e2 on e2.color = e.color
; where e.sku = "SKU-42"

