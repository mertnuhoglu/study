(ns mertnuhoglu.datomic.datomic-02-assertion)

; Copied from: [Assertion | Datomic](https://docs.datomic.com/on-prem/tutorial/assertion.html)

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


; Section: Transactions

; adds four colors to the database:
(d/transact
  conn
  {:tx-data [{:db/ident :red}
             {:db/ident :green}
             {:db/ident :blue}
             {:db/ident :yellow}]})


; Section: Programming with Data

(defn make-idents
  [x]
  (mapv #(hash-map :db/ident %) x))

(def sizes [:small :medium :large :xlarge])
(make-idents sizes)
;=> [#:db{:ident :small} #:db{:ident :medium} #:db{:ident :large} #:db{:ident :xlarge}]

(def types [:shirt :pants :dress :hat])
(def colors [:red :green :blue :yellow])
(d/transact conn {:tx-data (make-idents sizes)})
(d/transact conn {:tx-data (make-idents types)})

; Section: Schema

(def schema-1
  [{:db/ident :inv/sku
    :db/valueType :db.type/string
    :db/unique :db.unique/identity
    :db/cardinality :db.cardinality/one}
   {:db/ident :inv/color
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}
   {:db/ident :inv/size
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}
   {:db/ident :inv/type
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}])
(d/transact conn {:tx-data schema-1})

; Section: Sample Data

(def sample-data
  (->> (for [color colors size sizes type types]
         {:inv/color color
          :inv/size size
          :inv/type type})
       (map-indexed
         (fn [idx map]
           (assoc map :inv/sku (str "SKU-" idx))))
    vec))

sample-data
;=> ...
#_[#:inv{:color :red, :size :small, :type :shirt, :sku "SKU-0"}
   #:inv{:color :red, :size :small, :type :pants, :sku "SKU-1"}
   #:inv{:color :red, :size :small, :type :dress, :sku "SKU-2"}]

(d/transact conn {:tx-data sample-data})
