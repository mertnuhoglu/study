(ns mert.e10)

; Barış'la Datomic Çalışmaları
; Tarih: 20230204
; rfr: video/20230204-mert-clj-egzersiz-19.mp4

; rfr: [Learn Datalog Today!](https://www.learndatalogtoday.org/chapter/4)

; Konu: Meta Model Sorgulamaları

; Attributes:
; Şu ana kadar hep alana (domain) ait verilerle ilgili sorgulamalar yaptık.
; Datomic'te her şey veri olarak tutulur.
; Alan (veri) modelinin kendisi de bir veri olarak tutulur.
; Dolayısıyla nasıl bir veri modeline sahip olduğumuzu sorgularla bulabiliriz.
; Başka bir deyişle mesela bir varlığın hangi atributları var, bunu sorgulayarak öğrenebiliriz.

(require '[datomic.client.api :as d])
(use '[mert.e04 :only [conn] :as e04])
(def db (d/db conn))

(d/q
  '[:find ?attr
    :where
    [?eid :product/name]
    [?eid ?a]
    [?a :db/ident ?attr]]
  db)
;=> [[:product/name] [:product/id] [:product/color]]
; Türkçesi:
; :product/name attributu bulunan tüm entityleri bul (?eid)
; bu entitylerin tüm attributlarını bul (?a)
; bu attributların isimlerini bul (?attr)

; Hatırlarsanız, veri tabanını oluşturduktan sonra ilk yaptığımız işlem schemayı kaydetmekti.
; Schemayı kaydettiğimizde çeşitli datomlar almıştık.
; Bu datomlar aslında schemada tanımladığımız atributlara ait verileri içeriyordu.
; Aslında her bir atributun kendisi veritabanında sakladığımız bir entitydir.
; Fakat bu entityleri, esas verilerimizden farklı düşünmeliyiz.
; Esas verilerimize, alan modeline (domain model) ait veriler diyebiliriz.
; Atributların entityleri ve bu entitylere ait verilereyse, meta modele ait veriler diyebiliriz.

; Not: SQL veritabanlarında yukarıda yaptığımıza benzer işlemleri yapmak oldukça zordur.
; Her veritabanı sunucusunda farklı işlemler yapmak gerekir.
; Datomicteyse, her şey veridir. Veri modelinin (domain model veya data model) kendisi de veridir.
; Dolayısıyla düz sorgulamalarla veri modelini keşfedebiliriz.

; Transactions

; Atributların tanımlarını veri olarak sakladığımız gibi, transactionları da veri olarak saklarız.
; Bu özellik de datomice hastır. SQL veritabanlarında böyle bir şey ya yoktur, ya da çok daha zordur.
; Varsayılan olarak her tx'in gerçekleştiği zaman otomatikman kaydedilir o tx varlığının :db/txInstant atributuna

(d/q
  '[:find ?timestamp
    :where
    [?eid :product/id 1 ?tx]
    [?tx :db/txInstant ?timestamp]]
  db)
;=> [[#inst"2023-02-03T09:57:53.923-00:00"]]
; Bu sorguda, önce :product/id değeri 1 olan varlığı buluruz (?eid)
; Bu varlığa bu değeri tanımladığımız transaction ?tx değişkenine bağlanır
; Bu ?tx işleminin gerçekleştiği zaman bilgisi ?timestamp değişkenine bağlanır.
