(ns dev-local.datalog02-ex-10)

; Barış'la Datomic Çalışmaları
; Tarih: 20230206
; rfr: video/20230206-mert-clj-egzersiz-19.mp4

; rfr: [Learn Datalog Today!](https://www.learndatalogtoday.org/chapter/4)

; Konu: Meta Model Sorgulamaları

; Attributes:
; Şu ana kadar hep alana (domain) ait verilerle ilgili sorgulamalar yaptık.
; Datomic'te her şey veri olarak tutulur.
; Alan (veri) modelinin kendisi de bir veri olarak tutulur.
; Dolayısıyla nasıl bir veri modeline sahip olduğumuzu sorgularla bulabiliriz.
; Başka bir deyişle mesela bir varlığın hangi atributları var, bunu sorgulayarak öğrenebiliriz.

; Alan modeli (veya veri modeli) deyince biz aslında schema ile tanımladığımız dizayn yapısını anlıyoruz.
; Yani bizim örneğimizden bakarsak:
; (def db-schema
;     [{:db/ident :product/name
;       :db/valueType :db.type/string
;       :db/cardinality :db.cardinality/one}
;      {:db/ident :product/color
;       :db/valueType :db.type/ref
;       :db/cardinality :db.cardinality/one}])
; Burada 2 tane atribut tanımlamışız.
; product/name, product/color
; Normalde SQL veritabanlarında sadece atribut tanımlamayız, bu atributların bulunduğu tabloyla başlarız.
; Datomic'te tablo kavramı olmadığı için, sadece atribut tanımlarız.
; Ama biz kolaylık olsun diye, yine SQL gibi düşünebiliriz.
; Yani aslında "Product" tablomuz var. Bu 2 kolon da o tabloya ait gibi düşünebiliriz.

; Daha sonra bir tablo daha eklemiştik. Order isminde
; (def order-schema
;     [{:db/ident :order/product
;       :db/valueType :db.type/ref
;       :db/cardinality :db.cardinality/one}
;      {:db/ident :order/size
;       :db/valueType :db.type/long
;       :db/cardinality :db.cardinality/one}])

; Bu iki tabloya ait atributlar bizim alan modelimizi oluşturuyor.
; Niye alan modeli diyoruz?
; Domain = alan
; Problem alanı veya business domain (iş alanıyla) alakalı verileri burada tutacağız

; Meta model dediğimiz bir de kavram var.
; Bizim bu atributların bilgisini de veritabanında saklıyoruz
; :db/ident diye bizim bir kolonumuz var
; Schemayı tanımlarken bu meta modelin atributlarını kullanıyoruz.
; Örneğin bir atribut tanımlarken şu 4 değeri tanımlıyoruz:
#_[{:db/ident :ogrenci_id
    :db/valueType :db.type/long
    :db/unique :db.unique/identity
    :db/cardinality :db.cardinality/one}]
; Dolayısıyla aslında Datomic veritabanında bizden önce tanımlanmış olan en az 4 tane kolon (atribut) var.
; Bunlara meta modelin atributları diyoruz.
; db/ident, db/valueType, db/unique, db/cardinality

; [Db]
;| db/ident        | db/valueType     | db/unique   | db/cardinality       |
;|---------------  |----------------  |-----------  |--------------------  |
;| product/name    | db.type/string   | NA          | db.cardinality/one   |
;| product/color   | db.type/ref      | NA          | db.cardinality/one   |
;| order/product   | db.type/ref      | NA          | db.cardinality/one   |
;| order/size      | db.type/long     | NA          | db.cardinality/one   |

; Meta model, işte bu Attributes tablosu ve onun kolonlarına (atribut) denk geliyor
; db/ident, db/valueType, db/unique, db/cardinality

; Biz veri modelimizi tanımlarken, bu meta modelin yapılarını kullanarak tanımlıyoruz.
; Biz kendi alan modelimizi tanımladıktan sonra, sorgularımızı bu alan modeli üzerinde yapıyoruz.

; Standart: Genel bir isimlendirme standardı olarak,
; Atributların namespacei, o atributların içinde bulunduğu tablo gibi kullanılıyor.
; Bizim durumumuzda: product ve order diye iki tablo (namespace) var.

; Peki meta model üzerinde nasıl sorgulama yaparız?

(require '[datomic.client.api :as d])
(use '[dev-local.e04 :only [conn] :as e04])
(def db (d/db conn))

(d/q
      '[:find ?attr
        :where
        [?eid :product/name]
        [?eid ?a] ; EA
        [?a :db/ident ?attr]] ; EAV
      db)
;=> [[:product/name] [:product/id] [:product/color]]
; Türkçesi:
; :product/name atributu bulunan tüm entityleri bul (?eid)
; bu entitylerin tüm atributlarını bul (?a)
; bu atributların isimlerini bul (?attr)

; bir data pattern
;| değişken            | form    |
;|-------------------  |-------  |
;| ?e ?a               | EA      |
;| ?e ?a ?v            | EAV     |
;| ?db ?e ?a ?v        | DEAV    |
;| ?db ?e ?a ?v ?txt   | DEAVT   |

(d/q
      '[:find ?attr ?vt
        :where
        [?eid :product/name]
        [?eid ?a] ; EA
        [?a :db/ident ?attr]
        [?a :db/valueType ?vt]] ; EAV
      db)
;=> [[:product/name 23] [:product/id 22] [:product/color 20]]

(d/q
      '[:find (pull ?a [*])
        :where
        [?eid :product/name]
        [?eid ?a]] ; EA
      db)
;=>
;[[#:db{:id 73,
;       :ident :product/name,
;       :valueType #:db{:id 23, :ident :db.type/string},
;       :cardinality #:db{:id 35, :ident :db.cardinality/one}}]
; [#:db{:id 74,
;       :ident :product/color,
;       :valueType #:db{:id 20, :ident :db.type/ref},
;       :cardinality #:db{:id 35, :ident :db.cardinality/one}}]
; [#:db{:id 77,
;       :ident :product/id,
;       :valueType #:db{:id 22, :ident :db.type/long},
;       :cardinality #:db{:id 35, :ident :db.cardinality/one},
;       :unique #:db{:id 38, :ident :db.unique/identity}}]]

; Hatırlarsanız, veri tabanını oluşturduktan sonra ilk yaptığımız işlem schemayı kaydetmekti.
; Schemayı kaydettiğimizde çeşitli datomlar almıştık.
; Bu datomlar aslında schemada tanımladığımız atributlara ait verileri içeriyordu.
; Aslında her bir atributun kendisi de veritabanında sakladığımız bir entitydir.
; Fakat bu entityleri, esas verilerimizden farklı düşünmeliyiz.
; Esas verilerimize, alan modeline (domain model) ait veriler diyebiliriz.
; Atributların entityleri ve bu entitylere ait verilereyse, meta modele ait veriler diyebiliriz.

; Not: SQL veritabanlarında yukarıda yaptığımıza benzer işlemleri yapmak oldukça zordur.
; Her veritabanı sunucusunda farklı işlemler yapmak gerekir.
; Datomicteyse, her şey veridir. Veri modelinin (domain model veya data model) kendisi de veridir.
; Dolayısıyla düz sorgulamalarla veri modelini keşfedebiliriz.

; Transactions

; Meta modeli veritabanında saklıyoruz.
; Veritabanında yaptığımız işlemleri de veritabanında saklıyoruz.
; İşlem derken: update, insert, delete işlemleri
; CRUD: create (insert), retrieve (select), update, delete

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

; Not: `#inst` kelimesine tagged literal deniyor.
; https://clojure.org/reference/reader#_built_in_tagged_literals

; q: Transactionı nerelerde kullanırız?
; Geçmiş dataya ait sorgulamaları yaparken.
; Örnek kullanım alanı:
; 1. Bankasın sen. Müşteri şikayet ediyor. Ben bu işlemi yapmadım.
; O zaman o işlemin yapılış tarihini, o işlemi kimin yaptığını ve benzeri detayları kaydediyor olman lazım.

(d/q
      '[:find (pull ?tx [*])
        :where
        [?eid :product/id 1 ?tx]]
      db)
;=> [[#:db{:id 13194139533327, :txInstant #inst"2023-02-03T09:57:53.923-00:00"}]]

; İşlem yaparken bu işlemle ilgili istediğimiz herhangi bir bilgiyi kaydedebiliriz.
; En çok ihtiyaç duyulan bilgilerden bir tanesi, bu işlemi kimin yaptığı.
; Server-client (sunucu-istemci) mantığıyla çalışır veritabanı yazılımları.
; Server: uygulama sunucusu
; Client: Web tarayıcısında çalışan uygulama
; Backend tarafı veritabanıyla iletişim kurar.
; Bir connection oluşturursun
; Bu connectionı başlatırken, kullanıcı adı ve şifre girersin
; Hangi kullanıcının adını gireceğiz burada veritabanına bağlantı oluştururken?
; Veritabanı kullanıcısını gireceğiz.
; Client tarafında uygulamayı kullanan bir kişi. Son kullanıcı diyelim.
; Son kullanıcının şifresini mi gireceğiz?
; Veritabanına erişme yetkisi olan kullanıcının bilgisini gireceğiz.
; Bir tane kullanıcı hesabı oluşturulur.
; Admin kullanıcısı.
; Tek bir admin kullanıcısının bağlantısı üzerinden tüm veritabanı işlemleri gerçekleştirilecek.
; Peki soru şu:
; Bu durumda, ben yeni bir Sipariş (Order) eklediğim vakit, bu işlemi gerçekleştiren kullanıcı olarak kimi veritabanına kaydetmeliyim?
; Son kullanıcı mı, admin kullanıcı mı?
; Transactiona bu bilgiyi nasıl ekleriz?

; Önce transactiona ekleyeceğimiz bilgiler için bir attribute eklemeliyiz schemaya
(def tx-schema
      [{:db/ident       :tx/user
        :db/valueType   :db.type/string
        :db/cardinality :db.cardinality/one}])
(d/transact conn {:tx-data tx-schema})
;:tx-data [#datom[13194139533329 50 #inst"2023-02-06T12:51:47.070-00:00" 13194139533329 true]
;          #datom[78 10 :tx/user 13194139533329 true]
;          #datom[78 40 23 13194139533329 true]
;          #datom[78 41 35 13194139533329 true]
;          #datom[0 13 78 13194139533329 true]],

(def order-list-4
      [{:order/product [:product/id 3]
        :order/size 2}
       {:order/product [:product/id 4]
        :order/size 3}
       {:db/id "datomic.tx"
        :tx/user "mertnuhoglu"}])
; Not: the temporary id "datomic.tx" always identifies the current transaction
; rfr: https://docs.datomic.com/on-prem/transactions/transactions.html#creating-temp-id
; Dikkat: Transactionla ilgili bir veri kaydedeceksen, mutlaka bunun için "datomic.tx" entity idyi kullanman gerekiyor.
; Bu "datomic.tx" aslında o tx'in gerçek entity idsi değil. Geçici olarak bunu kullanıyor datomic.
; Gerçek entity id'yi işlem gerçekleştikten sonra sana bildiriyor.

(d/transact conn {:tx-data order-list-4})
;=>
;{:db-before #datomic.core.db.Db{:id "a39457f0-2870-4d83-891f-e31c2af60e96",
;                                :basisT 17,
;                                :indexBasisT 0,
;                                :index-root-id nil,
;                                :asOfT nil,
;                                :sinceT nil,
;                                :raw nil},
; :db-after #datomic.core.db.Db{:id "a39457f0-2870-4d83-891f-e31c2af60e96",
;                               :basisT 18,
;                               :indexBasisT 0,
;                               :index-root-id nil,
;                               :asOfT nil,
;                               :sinceT nil,
;                               :raw nil},
; :tx-data [#datom[13194139533330 50 #inst"2023-02-06T12:55:01.370-00:00" 13194139533330 true]
;           #datom[101155069755482 75 92358976733268 13194139533330 true]
;           #datom[101155069755482 76 2 13194139533330 true]
;           #datom[101155069755483 75 92358976733269 13194139533330 true]
;           #datom[101155069755483 76 3 13194139533330 true]
;           #datom[13194139533330 78 "mertnuhoglu" 13194139533330 true]],
; :tempids {"datomic.tx" 13194139533330}}
; Not: tx-data içindeki ilk datom transaction entitynin datomudur
; Dolayısıyla bu tx'in gerçek kalıcı entity idsi: 13194139533330
; ; :tx-data [#datom[13194139533330 50 #inst"2023-02-06T12:55:01.370-00:00" 13194139533330 true]

; Şimdi bu transaction verisi için sorgulama yapalım
(def db (d/db conn))
(d/q
      '[:find (pull ?tx [*])
        :where
        [_ :order/product [:product/id 4] ?tx]]
      db)
;=> [[
; {:db/id 13194139533330,
; :db/txInstant #inst"2023-02-06T12:55:01.370-00:00",
; :tx/user "mertnuhoglu"}]]
