(ns dev-local.datalog02-ex-04)

; Barış'la Datomic Çalışmaları
; Tarih: 20230202
; rfr: video/20230202-mert-clj-egzersiz-15.mp4

; rfr: sndgrsm > 20230202-Barışla-Datomic-Çalışma
; https://docs.google.com/spreadsheets/d/12IY0eoK8ny1i_EICUMxPas7UHHz_WfoW5b6xh8wUpCE/edit#gid=0

; Konu: :db/ident

; [Schema Modeling | Datomic](https://docs.datomic.com/on-prem/schema/schema-modeling.html)

; Normalde enum
; enum = kategorik kümeler, domain set, enumeration
; enum: sınırlı sayıda elementi olan değer kümelerine enum diyebiliriz.
; Örnekler:
; Renkler kümesi: kırmızı, sarı, mavi
; Ülkeler kümesi: türkiye, almanya, amerika vs.
; Değer kümeleri, ihtiyaç olursa varlık (entity) olarak da veritabanında kaydedilebilir.
; Varlık ve değer arasındaki fark neydi?
; Varlık: Kimlikleriyle tanımlanır (2 kişinin isimleri tıpatıp aynı bile olsa, ikisi birbirinden farklıdır.)
; Değer objeleri: Değerleriyle tanımlanır (2 tane 100 TL varsa, benim için ikisi birbirine eşittir.)

; Her şey yeri geldiğinde değer objesi olabilir, yeri geldiğinde varlık objesi olabilir.
; Bir şeyi bizim değer olarak mı varlık olarak mı kaydedeceğimiz, bizim yazılımımızın amacına (bağlamına) bağlı.
; Merkez Bankası için: Para objeleri birer varlıktır. Çünkü her bir banknot parayı takip eder.

; Normalde biz enum'ları Javada nasıl implemente ediyoruz?
; enum Level {
;  LOW,
;  MEDIUM,
;  HIGH
;}
; Peki Clojureda nasıl tanımlarız?
; keyword kullanarak:
; :LOW, :MEDIUM, :HIGH
; Datomic'te ne yapıyoruz?
; :db/ident ile tanımlıyoruz.

(require '[datomic.client.api :as d])

;; Memory storage
(def client (d/client {:server-type :dev-local
                       :storage-dir :mem
                       :system "ci"}))

(d/delete-database client {:db-name "db04"})
(d/create-database client {:db-name "db04"})

(def conn (d/connect client {:db-name "db04"}))

(d/transact
  conn
  {:tx-data [{:db/ident :red}
             {:db/ident :green}
             {:db/ident :blue}]})
;=>
;{:db-before #datomic.core.db.Db{:id "0ed69520-4256-44cb-b2cb-81dc23d7b185",
;                                :basisT 5,
;                                :indexBasisT 0,
;                                :index-root-id nil,
;                                :asOfT nil,
;                                :sinceT nil,
;                                :raw nil},
; :db-after #datomic.core.db.Db{:id "0ed69520-4256-44cb-b2cb-81dc23d7b185",
;                               :basisT 6,
;                               :indexBasisT 0,
;                               :index-root-id nil,
;                               :asOfT nil,
;                               :sinceT nil,
;                               :raw nil},
; :tx-data [#datom[13194139533318 50 #inst"2023-02-02T13:56:06.161-00:00" 13194139533318 true]
;           #datom[79164837199945 10 :red 13194139533318 true]
;           #datom[79164837199946 10 :green 13194139533318 true]
;           #datom[79164837199947 10 :blue 13194139533318 true]],
; :tempids {}}

; Not: Daha önceki örneklerde hep önce schema oluşturuyorduk.
; Burada schema oluşturmadık.

; Neden böyle?
; Şimdi öncelikle enum dediğimiz şeyler, daha önceki schemadaki gibi birer atribut değil.
; Yani bir varlık tipinin bir kolonu (atributu) değil.
; Daha önce Öğrenci varlık tipini (tablosunu) oluştururken, öğrencinin atributlarını tanımlıyorduk schema içinde
; Neydi bu atributlar? öğrenci_ismi, öğrenci_adresi, ...
; Fakat :red, :green gibi renkler, herhangi bir kolon ismi veya bir tablonun bir kolonu veya atributu değil.

; Fakat ben renk kümesini, örneğin diğer enum tiplerinden ayırmak isteyebilirim.
; Mesela giysi tipi diye başka bir kategorik kümem olsun.
; Giysi_Tipi kümesinde, 3 tane enum değeri olsun: :shirt :dress :hat
; Şimdi bunları :db/ident ile tanımlayabilirim.
; Peki ama bu enumlar renk enumlarıyla karışmaz mı bu durumda?
; Nasıl ayırt edebilirim bunları?
; namespace ile ayırt edebiliriz

(d/transact
  conn
  {:tx-data [{:db/ident :color/red}
             {:db/ident :color/green}
             {:db/ident :color/blue}]})

(d/transact
  conn
  {:tx-data [{:db/ident :clothing/shirt}
             {:db/ident :clothing/dress}
             {:db/ident :clothing/hat}]})

; q: schemadaki atributları tanımlarken de :db/ident kullanmıştık, burada da.
; neden?
; Dokümantasyondaki tanıma bakalım
; [Schema Data Reference | Datomic](https://docs.datomic.com/cloud/schema/schema-reference.html)
; The :db/ident attribute specifies a unique programmatic name for an entity.
; Idents are required for schema entities and are optional for all other entities.
; Burada schema entity kavramıyla neyi kast ediyor?
; Schema entityleri nelerdir?
; Kolonlar schema entitysidir
; Bizim veritabanımızı oluşturan varlıklar.
; Meta varlıklar bunlar.
; Biz kendi alan modelimizdeki (domain model) varlıklara, normal varlık (entity) diyoruz.
; Fakat meta modeldeki varlıklara, schema entity diyoruz.

; Konu: :db.valueType/ref

; Örnek problem:
; Benim bir mağazam var.
; Mağazada 2 farklı ürünüm var:
; 1. Defter
; 2. Kalem
; Benim defterlerim iki renkte olabilir: Kırmızı veya yeşil.
; Kalemlerim de iki renkte olabilir: Kırmızı, mavi.
; Şimdi bunları nasıl saklayacağım veritabanımda?

; Mağazada sattığım ürünlere: Product diyelim
; Her bir ürün için o ürünün ismi tutacağım
; Bir de o ürünün rengini tutmak istiyorum.

(def db-schema
  [{:db/ident :product/name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :product/color
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}])
(d/transact conn {:tx-data db-schema})
;=>
;{:db-before #datomic.core.db.Db{:id "0ed69520-4256-44cb-b2cb-81dc23d7b185",
;                                :basisT 8,
;                                :indexBasisT 0,
;                                :index-root-id nil,
;                                :asOfT nil,
;                                :sinceT nil,
;                                :raw nil},
; :db-after #datomic.core.db.Db{:id "0ed69520-4256-44cb-b2cb-81dc23d7b185",
;                               :basisT 9,
;                               :indexBasisT 0,
;                               :index-root-id nil,
;                               :asOfT nil,
;                               :sinceT nil,
;                               :raw nil},
; :tx-data [#datom[13194139533321 50 #inst"2023-02-02T14:22:58.401-00:00" 13194139533321 true]
;           #datom[73 10 :product/name 13194139533321 true]
;           #datom[73 40 23 13194139533321 true]
;           #datom[73 42 38 13194139533321 true]
;           #datom[73 41 35 13194139533321 true]
;           #datom[74 10 :product/color 13194139533321 true]
;           #datom[74 40 20 13194139533321 true]
;           #datom[74 41 35 13194139533321 true]
;           #datom[0 13 73 13194139533321 true]
;           #datom[0 13 74 13194139533321 true]],
; :tempids {}}

(def product-list
  [{:product/name "Kalem"
    :product/color :color/red}
   {:product/name "Kalem"
    :product/color :color/blue}
   {:product/name "Defter"
    :product/color :color/red}
   {:product/name "Defter"
    :product/color :color/green}])
(d/transact conn {:tx-data product-list})
;=>
;{:db-before #datomic.core.db.Db{:id "3fae223c-e155-42f6-b723-bc07307cb5b0",
;                                :basisT 9,
;                                :indexBasisT 0,
;                                :index-root-id nil,
;                                :asOfT nil,
;                                :sinceT nil,
;                                :raw nil},
; :db-after #datomic.core.db.Db{:id "3fae223c-e155-42f6-b723-bc07307cb5b0",
;                               :basisT 10,
;                               :indexBasisT 0,
;                               :index-root-id nil,
;                               :asOfT nil,
;                               :sinceT nil,
;                               :raw nil},
; :tx-data [#datom[13194139533322 50 #inst"2023-06-10T14:02:12.620-00:00" 13194139533322 true]
;           #datom[96757023244370 73 "Kalem" 13194139533322 true]
;           #datom[96757023244370 74 92358976733260 13194139533322 true]
;           #datom[96757023244371 73 "Kalem" 13194139533322 true]
;           #datom[96757023244371 74 92358976733262 13194139533322 true]
;           #datom[96757023244372 73 "Defter" 13194139533322 true]
;           #datom[96757023244372 74 92358976733260 13194139533322 true]
;           #datom[96757023244373 73 "Defter" 13194139533322 true]
;           #datom[96757023244373 74 92358976733261 13194139533322 true]],
; :tempids {}}

; q: acaba ben buradaki reflerin yerinde daha önceden kayıtlı olmayan bir enum kullansaydım ne olurdu?
(def product-list-2
  [{:product/name "Kalem"
    :product/color :color/purple}])
#_(d/transact conn {:tx-data product-list-2})
;:db.error/not-an-entity Unable to resolve entity: :color/purple in datom [-9223301668109598072 :product/color :color/purple]
; Hata veriyor. Çünkü ref verdiğim :color/purple objesini bulamıyor.

(type :color/red)
;=> clojure.lang.Keyword

; Konu: Başka varlık tiplerine referans vermek (enum yerine)

; Markette bir ürün listemiz olur.
; Bir de bu ürünlerin siparişleri olur.
; Her bir ürün farklı siparişlerde satılıp alınabilir.
; Dolayısıyla Sipariş tipinde bir varlık tipim olması lazım.

(def order-schema
  [{:db/ident :order/product
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}
   {:db/ident :order/size
    :db/valueType :db.type/long
    :db/cardinality :db.cardinality/one}])
(d/transact conn {:tx-data order-schema})

(def order-list
  [{:order/product 74766790688850
    :order/size 5}
   {:order/product 74766790688852
    :order/size 4}])
(d/transact conn {:tx-data order-list})
; Not: Ürünlere referans vermek için onların entity_id'lerini kullandık.
; entity_id'leri repl'daki transaction kayıtlarından bulduk.

; Sorun: entity_id'yi ben eğer tx sonrası kaydı almasaydım nasıl bulacaktım?
; Sorgulayarak
; entity_id'yi bulma sorgusunu yazalım o zaman.
(def db (d/db conn))
(d/q
  '[:find ?e
    :where
    [?e :product/name "Kalem"]
    [?e :product/color :color/red]]
  db)
;=> [[74766790688850]]
; ama bu gelen sonuç bir vektörün içindeki vektör olarak geldi
; içindeki datayı çekmek için: ffirst
(def product-id
  (ffirst (d/q
            '[:find ?e
              :where
              [?e :product/name "Kalem"]
              [?e :product/color :color/red]]
            db)))
(identity product-id)
;=> 74766790688850

; o zaman artık yukarıda hard-code ettiğim entity_id yerine bu `product_id` değişkenini kullanabilirim
(def order-list
  [{:order/product product-id
    :order/size 6}])
(d/transact conn {:tx-data order-list})

; şimdi bir de bu ilişkili varlık tipleri üzerinden bir sorgu yazalım
; Benim kırmızı kalemlerimle ilgili kaç adet ürün sipariş edilmiş?
(def db (d/db conn))
(d/q
  '[:find ?order ?size
    :where
    [?e :product/name "Kalem"]
    [?e :product/color :color/red]
    [?order :order/product ?e]
    [?order :order/size ?size]]
  db)
;=> [[74766790688850 6] [74766790688850 5]]

; Konu: Pull API

; Şu ana kadar sonuç içinde döndürmek istediğimiz tüm atributları find içine tek tek yazıyorduk
; Bunun daha pratik bir yolu yok mu?
; Var: Pull API

(d/q
  '[:find (pull ?order [*])
    :where
    [?e :product/name "Kalem"]
    [?e :product/color :color/red]
    [?order :order/product ?e]]
  db)
;=>
;[[{:db/id 92358976733267, :order/product #:db{:id 74766790688850}, :order/size 5}]
; [{:db/id 96757023244373, :order/product #:db{:id 74766790688850}, :order/size 6}]]

; keyword referansı sorgu parametresi olarak vermek
(d/q
  '[:find (pull ?order [*])
    :in $ ?color
    :where
    [?e :product/name "Kalem"]
    [?e :product/color ?color]
    [?order :order/product ?e]]
  db :color/red)
;=>
;[[{:db/id 79164837199958, :order/product #:db{:id 74766790688850}, :order/size 5}]
; [{:db/id 83562883711064, :order/product #:db{:id 74766790688850}, :order/size 6}]]

; birden çok keywork referansı sorgu parametresi olarak vermek
(d/q
  '[:find (pull ?order [*])
    :in $ [?colors ...]
    :where
    [?e :product/name "Kalem"]
    [?e :product/color ?colors]
    [?order :order/product ?e]]
  db [:color/red])
;=>
;[[{:db/id 79164837199958, :order/product #:db{:id 74766790688850}, :order/size 5}]
; [{:db/id 83562883711064, :order/product #:db{:id 74766790688850}, :order/size 6}]]

(+ (+ 2 3) 4)
