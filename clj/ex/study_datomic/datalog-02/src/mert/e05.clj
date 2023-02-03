(ns mert.e05)

(require '[datomic.client.api :as d])
(use '[mert.e04 :only [conn] :as e04])

; Barış'la Datomic Çalışmaları
; Tarih: 20230203
; rfr: video/20230203-mert-clj-egzersiz-16.mp4

; e04'ün devamı

; Konu: Identity erişimi

; Yukarıdaki sorgularda "Kırmızı kalem" siparişlerine ulaşmak için iki tane data pattern yazmamız gerekti.
; Bu çok pratik bir yöntem değil.
; Normalde biz alıştığımız veritabanı sorgularında bir tane FK referansıyla bunu hallederiz.
; Benzeri bir yaklaşımı burada nasıl uygularız?

; Bir tane identity PK kolonu tanımlarız
(def product-schema-2
  [{:db/ident :product/id
    :db/valueType :db.type/long
    :db/unique :db.unique/identity ; 👈 bu spesifikasyonla (spek) bu atributu PK kolonu haline getirdik
    :db/cardinality :db.cardinality/one}])
(d/transact conn {:tx-data product-schema-2})

; Şimdi mevcut varlıklarımıza bu id değerini eklememiz lazım elbette
; Bunun için önce mevcut varlıklarımızın listesini çekelim.
(def db (d/db conn))
(d/q
  '[:find (pull ?e [*])
    :where
    [?e :product/name _]]
  db)
;=>
;[[{:db/id 92358976733263, :product/name "Kalem", :product/color #:db{:id 96757023244361, :ident :color/red}}]
; [{:db/id 92358976733264, :product/name "Kalem", :product/color #:db{:id 96757023244363, :ident :color/blue}}]
; [{:db/id 92358976733265, :product/name "Defter", :product/color #:db{:id 96757023244361, :ident :color/red}}]
; [{:db/id 92358976733266, :product/name "Defter", :product/color #:db{:id 96757023244362, :ident :color/green}}]]

; Dikkat: Başka varlıklara verilen referanslar, diğer atributlardan farklı olarak bir map olarak görünüyor:
; 👉 :product/color #:db{:id 96757023244361, :ident :color/red} 👈
; Diğer normal atributların değerleri ise primitif olarak geliyor:
; :product/name "Kalem"

; Terim: primitif (primitive), obje (aggregate, alt parçası olan objeler, composite, map gibi)

; Bu ürün listesinde içiçe iki seviye (nested) vektör bulunuyor. [[...][...]]
; Tek seviye olmasını sağlamak için, flatten fonksiyonunu kullanalım
; Terim: flatten = düzleştirme. Çok seviyeli (nested, içiçe) olan vektör, liste türü koleksiyonları, tek seviyeye indirmek.
(-> (d/q
      '[:find (pull ?e [*])
        :where
        [?e :product/name _]]
      db)
  flatten)
;=>
;({:db/id 92358976733263, :product/name "Kalem", :product/color #:db{:id 96757023244361, :ident :color/red}}
; {:db/id 92358976733264, :product/name "Kalem", :product/color #:db{:id 96757023244363, :ident :color/blue}}
; {:db/id 92358976733265, :product/name "Defter", :product/color #:db{:id 96757023244361, :ident :color/red}}
; {:db/id 92358976733266, :product/name "Defter", :product/color #:db{:id 96757023244362, :ident :color/green}})

; Şimdi tek seviyelik liste oldu. Ancak bizim liste değil vektör olmasına ihtiyacımız var, kolayca assoc-in ile öğeleri dolaşabilmek için
(-> (d/q
      '[:find (pull ?e [*])
        :where
        [?e :product/name _]]
      db)
  flatten
  vec)
;=>
;[{:db/id 92358976733263, :product/name "Kalem", :product/color #:db{:id 96757023244361, :ident :color/red}}
; {:db/id 92358976733264, :product/name "Kalem", :product/color #:db{:id 96757023244363, :ident :color/blue}}
; {:db/id 92358976733265, :product/name "Defter", :product/color #:db{:id 96757023244361, :ident :color/red}}
; {:db/id 92358976733266, :product/name "Defter", :product/color #:db{:id 96757023244362, :ident :color/green}}]
(def product-list-3
  (-> (d/q
        '[:find (pull ?e [*])
          :where
          [?e :product/name _]]
        db)
    flatten
    vec))

; Şimdi bu ürün listesindeki her bir ürün öğesine bir product/id ekleyelim
(def product-list-4
  (-> product-list-3
    (assoc-in [0 :product/id] 1)
    (assoc-in [1 :product/id] 2)
    (assoc-in [2 :product/id] 3)
    (assoc-in [3 :product/id] 4)))

(identity product-list-4)
;=>
;[{:db/id 92358976733263,
;  :product/name "Kalem",
;  :product/color #:db{:id 96757023244361, :ident :color/red},
;  :product/id 1}
; {:db/id 92358976733264,
;  :product/name "Kalem",
;  :product/color #:db{:id 96757023244363, :ident :color/blue},
;  :product/id 2}
; {:db/id 92358976733265,
;  :product/name "Defter",
;  :product/color #:db{:id 96757023244361, :ident :color/red},
;  :product/id 3}
; {:db/id 92358976733266,
;  :product/name "Defter",
;  :product/color #:db{:id 96757023244362, :ident :color/green},
;  :product/id 4}]

; Şimdi bu güncel ürün listesi verilerini veritabanına kaydedelim.
(d/transact conn {:tx-data product-list-4})
;=>
;{:db-before #datomic.core.db.Db{:id "40972bd2-7839-4cb5-9bcf-85f95a328d7a",
;                                :basisT 14,
;                                :indexBasisT 0,
;                                :index-root-id nil,
;                                :asOfT nil,
;                                :sinceT nil,
;                                :raw nil},
; :db-after #datomic.core.db.Db{:id "40972bd2-7839-4cb5-9bcf-85f95a328d7a",
;                               :basisT 15,
;                               :indexBasisT 0,
;                               :index-root-id nil,
;                               :asOfT nil,
;                               :sinceT nil,
;                               :raw nil},
; :tx-data [#datom[13194139533327 50 #inst"2023-02-02T22:06:29.905-00:00" 13194139533327 true]
;           #datom[92358976733263 77 1 13194139533327 true]
;           #datom[92358976733264 77 2 13194139533327 true]
;           #datom[92358976733265 77 3 13194139533327 true]
;           #datom[92358976733266 77 4 13194139533327 true]],
; :tempids {}}

; Not: tx dışında 4 datom kaydedildi veritabanına.
; Her bir datom aslında bir Product varlığının product/id değerini içeriyor.

; Şimdi doğru kayıt ettiğimizden emin olmak için tüm ürün listesini tekrar çekelim veritabanından.
(def db (d/db conn))
(d/q
  '[:find (pull ?e [*])
    :where
    [?e :product/name _]]
  db)
;=>
;[[{:db/id 92358976733263,
;   :product/name "Kalem",
;   :product/color #:db{:id 96757023244361, :ident :color/red},
;   :product/id 1}]
; [{:db/id 92358976733264,
;   :product/name "Kalem",
;   :product/color #:db{:id 96757023244363, :ident :color/blue},
;   :product/id 2}]
; [{:db/id 92358976733265,
;   :product/name "Defter",
;   :product/color #:db{:id 96757023244361, :ident :color/red},
;   :product/id 3}]
; [{:db/id 92358976733266,
;   :product/name "Defter",
;   :product/color #:db{:id 96757023244362, :ident :color/green},
;   :product/id 4}]]

; Not: (d/transact conn {:tx-data product-list-4}) ifadesiyle güncel ürün listesini veritabanına kaydetmiştik.
; Dikkat ederseniz, product-list-4 içinde :product/id dışında 3 atribut daha bulunuyordu.
; Peki datomic neden bu 4 varlık için, yeni entity kayıtları oluşturmak yerine, mevcut kayıtları güncelledi?
; Cevap: Çünkü :db/id ile mevcut varlıkların id'lerini vermiştik.
; Datomic bu db/id değerlerine ait varlıkları buldu veritabanından. Sonra bunların atribut değerlerini güncelledi.
; Yeni varlık kaydı eklemedi.

; Şimdi yeni bir sipariş daha verelim.
; Daha önceki sipariş tanımlama kodumuz şu şekildeydi:
;(def order-list
;  [{:order/product product-id
;    :order/size 6}])
; Bu kodun sorunu `product-id` isimli entity_id değerini bulmak için bir hayli ek iş yapmak gerekiyordu.
; Artık her bir ürün kaydımız için tekil bir PK atributumuz (:product/id) bulunduğundan, işimiz çok daha kolay.
(def order-list-3
  [{:order/product [:product/id 2]
    :order/size 7}])
(d/transact conn {:tx-data order-list-3})
;=>
;:tx-data [#datom[13194139533328 50 #inst"2023-02-02T22:17:28.633-00:00" 13194139533328 true]
;          #datom[74766790688854 75 92358976733264 13194139533328 true]
;          #datom[74766790688854 76 7 13194139533328 true]],

; Not: Eskiden entity_id ile ilişkili varlıklara referans veriyorduk.
; Şimdi ise [:product/id 2] formuyla ilişkili ürün varlığına referans verdik.
; Bunu yapmamızı sağlayan şey, `:product/id` atributunun `identity` olarak tanımlanmış olması schemada.
