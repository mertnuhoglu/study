(ns mertnuhoglu.datomic.datomic-01b)

;; Lab: Assertion Tutorial <url:file:///~/prj/study/clj/articles-datomic.md#r=g13506>

;; [Assertion | Datomic](https://docs.datomic.com/cloud/tutorial/assertion.html)

(require '[datomic.client.api :as d])

;; Memory storage
(def client (d/client {:server-type :dev-local
                       :storage-dir :mem
                       :system "ci"}))

(d/create-database client {:db-name "tutorial"})

(def conn (d/connect client {:db-name "tutorial"}))

(d/transact
  conn
  {:tx-data [{:db/ident :red}
             {:db/ident :green}
             {:db/ident :blue}
             {:db/ident :yellow}]})
;=>
;{:db-before #datomic.core.db.Db{:id "19c724ee-3905-4f85-92d7-0d0623302e69",
;                                :basisT 5,
;                                :indexBasisT 0,
;                                :index-root-id nil,
;                                :asOfT nil,
;                                :sinceT nil,
;                                :raw nil},
; :db-after #datomic.core.db.Db{:id "19c724ee-3905-4f85-92d7-0d0623302e69",
;                               :basisT 6,
;                               :indexBasisT 0,
;                               :index-root-id nil,
;                               :asOfT nil,
;                               :sinceT nil,
;                               :raw nil},
; :tx-data [#datom[13194139533318 50 #inst"2022-11-11T11:28:45.337-00:00" 13194139533318 true]
;           #datom[101155069755465 10 :red 13194139533318 true]
;           #datom[101155069755466 10 :green 13194139533318 true]
;           #datom[101155069755467 10 :blue 13194139533318 true]
;           #datom[101155069755468 10 :yellow 13194139533318 true]],
; :tempids {}}

(defn make-idents
  [x]
  (mapv #(hash-map :db/ident %) x))

(def sizes [:small :medium :large :xlarge])
(make-idents sizes)
;; => [#:db{:ident :small} #:db{:ident :medium} #:db{:ident :large} #:db{:ident :xlarge}]

(def types [:shirt :pants :dress :hat])
(def colors [:red :green :blue :yellow])

(d/transact conn {:tx-data (make-idents sizes)})
(d/transact conn {:tx-data (make-idents colors)})
(d/transact conn {:tx-data (make-idents types)})

;; Schema

;; Şu ana kadar sadece :db/ident kullanmıştık
;; Şimdi kendi schemamızı tanımlayacağız

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

(defn create-sample-data
  [colors sizes types]
  "Create a vector of maps of all permutations of args"
  (->> (for [color colors size sizes type types]
         {:inv/color color
          :inv/size size
          :inv/type type})
    (map-indexed
      (fn [idx map]
        (assoc map :inv/sku (str "SKU-" idx))))
    vec)) ;; 64 (4 x 4 x 4) maps

@(def sample-data (create-sample-data colors sizes types))
;;=>
;[#:inv{:color :red, :size :small, :type :shirt, :sku "SKU-0"}
; #:inv{:color :red, :size :small, :type :pants, :sku "SKU-1"}
; #:inv{:color :red, :size :small, :type :dress, :sku "SKU-2"}]
; ...

@(def sample-data-transaction (d/transact conn {:tx-data sample-data}))

;; [Read | Datomic](https://docs.datomic.com/cloud/tutorial/read.html)

(require '[datomic.client.api :as d])

;; Memory storage
(def client (d/client {:server-type :dev-local
                       :storage-dir :mem
                       :system "ci"}))

(def conn (d/connect client {:db-name "tutorial"}))

(def db (d/db conn))

;; Pull
(d/pull
  (d/db conn)
  [{:inv/color [:db/ident]}
   {:inv/size [:db/ident]}
   {:inv/type [:db/ident]}]
  [:inv/sku "SKU-42"])
;;=> #:inv{:color #:db{:ident :blue}, :size #:db{:ident :large}, :type #:db{:ident :dress}}

;; Database as a value

; Daha önce tx çalıştırınca veritabanı değerini sample-data-transaction içine koymuştuk

(d/pull
  (:db-before sample-data-transaction)
  [{:inv/color [:db/ident]}
   {:inv/size [:db/ident]}
   {:inv/type [:db/ident]}]
  [:inv/sku "SKU-42"])
;;=> => #:inv{:color #:db{:ident :blue}, :size #:db{:ident :large}, :type #:db{:ident :dress}}

;; Yukarıdaki sorguyla aynı veriyi çektik

(d/q
  '[:find ?sku
    :where
    [?e :inv/sku "SKU-42"]
    [?e :inv/color ?color]
    [?e2 :inv/color ?color]
    [?e2 :inv/sku ?sku]]
  db)
;;=>
;[["SKU-40"]
; ["SKU-43"]
; ["SKU-32"]]
; ...

;; [Accumulate | Datomic](https://docs.datomic.com/cloud/tutorial/accumulate.html) id=g13513

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

(def add-order
  {:order/items
   [{:item/id [:inv/sku "SKU-25"]
     :item/count 10}
    {:item/id [:inv/sku "SKU-26"]
     :item/count 20}]})

(d/transact conn {:tx-data [add-order]})

;; Burada dikkat ederseniz, nested bir entity map kullandık. Datomic tx otomatik olarak bunu :db/add listesine çevirdi.

;; [Read Revisited: More Query | Datomic](https://docs.datomic.com/cloud/tutorial/read-revisited.html)

; Öncelikle veritabanının son değerini yeniden çekelim.
; Çünkü tx'ler veritabanında değişikliğe neden oldular.

(def db (d/db conn))

; Parameterized Query

; Örnek: "SKU-25" malzemesinin satıldığı tüm siparişleri (order) bul. Bu siparişlerdeki diğer malzemelerin sku'larını çek.
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
;;=> [["SKU-25"] ["SKU-26"]]

; Rules

(def rules
  '[[(ordered-together ?inv ?other-inv)
     [?item :item/id ?inv]
     [?order :order/items ?item]
     [?order :order/items ?other-item]
     [?other-item :item/id ?other-inv]]])

; Bu kuralları sorguda kullanmak için `:in` cümleceğinde `%` sembolünü kullanmalıyız:

(d/q
  '[:find ?sku
    :in $ % ?inv
    :where
    (ordered-together ?inv ?other-inv)
    [?other-inv :inv/sku ?sku]]
  db rules [:inv/sku "SKU-25"])
;;=> [["SKU-25"] ["SKU-26"]]

;; [Retract | Datomic](https://docs.datomic.com/cloud/tutorial/retract.html)

; Explicit Retract

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

; SKU-22 için herhangi bir envanterimiz yoktur. Düzeltme yapalım:
(d/transact
  conn
  {:tx-data [[:db/retract [:inv/sku "SKU-22"] :inv/count 7]
             [:db/add "datomic.tx" :db/doc "remove incorrect assertion"]]})

; Burada retract yaparken aynı zamanda bir de assertion yaptık
; Bu eklediğimiz kayıt, tx için bir kayıt. Çünkü tempid olarak "datomic.tx" olarak kullandık.
; Bu şekilde tx'lere cümle ekleyebiliriz.

; Implicit Retract

; SKU-42 için 100 yerine 1000 adet olmalıydı. retract yapmadan doğrudan add ile bunu düzeltebiliriz.

(d/transact
  conn
  {:tx-data [[:db/add [:inv/sku "SKU-42"] :inv/count 1000]
             [:db/add "datomic.tx" :db/doc "correct data entry error"]]})

(def db (d/db conn))

; Envanter veritabanının güncel hali:

(d/q
  '[:find ?sku ?count
    :where
    [?inv :inv/sku ?sku]
    [?inv :inv/count ?count]]
  db)
;;=> [["SKU-42" 1000] ["SKU-21" 7]]

;; [History | Datomic](https://docs.datomic.com/cloud/tutorial/history.html)

; Pre-requisite
; Envanter Veritabanına başka malzemeler de ekleyelim:

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

; Verilerde düzeltmeler yapalım
(d/transact
  conn
  {:tx-data [[:db/retract [:inv/sku "SKU-22"] :inv/count 7]
             [:db/add "datomic.tx" :db/doc "Retract incorrect assertion"]]})

(d/transact
  conn
  {:tx-data [[:db/add [:inv/sku "SKU-42"] :inv/count 1000]
             [:db/add "datomic.tx" :db/doc "Update inventory"]]})

; as-of Query

; Geçmişteki tx'leri ne zaman yaptığını hatırlamayabilirsin.
; Bu durumda son tx'leri sorguyla çekebilirsin:

@(def recent-txns
   (d/q
     '[:find (max 3 ?tx)
       :where
       [?tx :db/txInstant]]
     (d/db conn)))
;=> [[[13194139533339 13194139533338 13194139533337]]]

; En güncel
;; Get the earliest transaction id
@(def txid
   (->> recent-txns
     ffirst
     (apply min)))
;=> 13194139533337

@(def db-before (d/as-of db txid))

(d/q
  '[:find ?sku ?count
    :where
    [?inv :inv/sku ?sku]
    [?inv :inv/count ?count]]
  db-before)

; history Query

(require '[clojure.pprint :as pp])

(def db-hist (d/history (d/db conn)))

(->> (d/q
       '[:find ?tx ?sku ?val ?op
         :where
         [?inv :inv/count ?val ?tx ?op]
         [?inv :inv/sku ?sku]]
       db-hist)
  (sort-by first)
  (pp/pprint))
;([13194139533333 "SKU-22" 7 true]
; [13194139533333 "SKU-42" 100 true]
; [13194139533333 "SKU-21" 7 true]
; [13194139533334 "SKU-22" 7 false]
; [13194139533335 "SKU-42" 100 false]
; [13194139533335 "SKU-42" 1000 true]
; [13194139533337 "SKU-22" 7 true]
; [13194139533337 "SKU-42" 100 true]
; [13194139533337 "SKU-42" 1000 false]
; [13194139533338 "SKU-22" 7 false]
; [13194139533339 "SKU-42" 100 false]
; [13194139533339 "SKU-42" 1000 true])
