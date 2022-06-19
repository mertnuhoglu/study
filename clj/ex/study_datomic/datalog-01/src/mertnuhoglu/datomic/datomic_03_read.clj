(ns mertnuhoglu.datomic.datomic-03-read)

; Copied from: [Read | Datomic](https://docs.datomic.com/on-prem/tutorial/read.html)

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

; Step02: Depends on sample_data in datomic_02

; Section: Database Values

; db() returns latest database value from a connection
; connection ~ source code repository
; db ~ checkout of a source code repository
(def db (d/db conn))

; Section: Pull

(d/pull db
  [{:inv/color [:db/ident]}
   {:inv/size [:db/ident]}
   {:inv/type [:db/ident]}]
  [:inv/sku "SKU-42"])
;=> #:inv{:color #:db{:ident :blue}, :size #:db{:ident :large}, :type #:db{:ident :dress}}

; [:inv/sku "SKU-42"] is a lookup ref
; lookup ref: two element list of unique attribute+value that identifies an entity

; Section: Query

; ex: querying with recursion
; find the skus of all products that share a color with SKU-42
(d/q '[:find ?sku
       :where [?e :inv/sku "SKU-42"]
       [?e :inv/color ?color]
       [?e2 :inv/color ?color]
       [?e2 :inv/sku ?sku]]
  db)
;=>
;[["SKU-42"]
; ["SKU-32"]
; ...]

; for each list in :where clause:
; 1. element: matches the entity id `?e`
; 2. element: matches an attribute
; 3. element: matches an attribute's value

; Symbols with question mark are datalog variables
; When same symbol occurs repeatedly, it causes a join
; ?e joins SKU-42 to its color
; ?e2 joins to all entities having the same color
; ?sku joins all ?entities to their skus


