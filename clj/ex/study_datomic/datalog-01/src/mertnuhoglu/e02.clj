(ns mertnuhoglu.e02)

; Barış'la Datomic Egzersizleri
; Tarih: 20230201
; rfr: video/20230201-mert-clj-egzersiz-13.mp4

; rfr: sndgrsm > 20230201-Barışla-Datomic-Çalışma
; https://docs.google.com/spreadsheets/d/12IY0eoK8ny1i_EICUMxPas7UHHz_WfoW5b6xh8wUpCE/edit#gid=0

; Hedef:
;
;| öğrenci_id   | isim          | şehir     |
;|------------  |-------------  |---------  |
;| 101          | Ali Niyazi    | Bursa     |
;| 102          | Veli Şimşek   | Kütahya   |

; Bu tablonun karşılık geldiği EAV tablosu şu şekilde olacak:

;| E     | A       | V             |
;|-----  |-------  |-------------  |
;| 101   | isim    | Ali Niyazi    |
;| 101   | şehir   | Bursa         |
;| 102   | isim    | Veli Şimşek   |
;| 102   | şehir   | Kütahya       |

; Bizim amacımız bu EAV tablosunu datalog ile kaydetmek.

(require '[datomic.client.api :as d])

;; Memory storage
(def client (d/client {:server-type :dev-local
                       :storage-dir :mem
                       :system "ci"}))

(d/create-database client {:db-name "db02"})

(def conn (d/connect client {:db-name "db02"}))

; şimdi schemayı oluşturmamız gerekiyor.
; bizim yukarıdaki ilişkisel (flat) tablomuz 3 tane kolondan oluşuyor
; bu kolonların (atribut) datalog schemada tanımlanması gerekiyor.

; SQL'de bu yukarıdaki tabloyu tanımlamak için bir DDL cümlesi yazarız:
;CREATE TABLE Ogrenci(
;                      ogrenci_id INT NOT NULL,
;                      isim VARCHAR(64),
;                      sehir VARCHAR(128),
;                      PRIMARY KEY(vehicleId))
;                      ;

; Bunun datomic'teki karşılığı aşağıdaki gibi bir schema objesidir:

(def schema-1
  [{:db/ident :ogrenci_id
    :db/valueType :db.type/long
    :db/unique :db.unique/identity
    :db/cardinality :db.cardinality/one}
   {:db/ident :isim
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :sehir
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}])

; q: Datomic'te herhangi bir tablo ile sınırlandırılmıyorduk. Şimdi böyle bir schema tanımlayınca sınırlandırılmış olmuyor muyuz?
; cevap: Yine bir sınır yok.
; Yeni bir tablo veya kolon eklemek istersen, yeni bir schema objesi tanımlarsın.
; O kadar.
;(def schema-1
;  [{:db/ident :ogrenci_id2
;    :db/valueType :db.type/long
;    :db/unique :db.unique/identity
;    :db/cardinality :db.cardinality/one}])

; Dikkat edersek: yukarıda tanımladığımız schema-1 objesi kendisi de düz bir data structure.
; list içinde map içeren bir data objesi.
; Fakat dikkat ederseniz: yukarıdaki SQL için tanımladığımız DDL cümlesi bir data değil.
; Peki nedir bu DDL cümlesi?
; Kendisine özgü bir dilde tanımlanmış bir stringdir.
; Senin bu stringi anlamlı hale getirmen için, onu o dili parse edebilen bir parser ve compiler ile işlemen gerekir.

; Datalog schemalarının veri olması sayesinde ben kolaylıkla programatik bir şekilde schemayı değiştirebilirim.
; Mesela: 3. kolonun ismi `sehir` değil `city` olsun. Bunu programatik olarak değiştirelim.
(assoc-in schema-1 [2 :db/ident] "city")
;=>
;[#:db{:ident :ogrenci_id, :valueType :db.type/long, :unique :db.unique/identity, :cardinality :db.cardinality/one}
; #:db{:ident :isim, :valueType :db.type/string, :cardinality :db.cardinality/one}
; #:db{:ident "city", :valueType :db.type/string, :cardinality :db.cardinality/one}]

; Peki bu yaptığımız işlemin aynısını yukarıdaki DDL cümlesi için yapabilir miyiz?
; Programatik olarak oldukça zor.

; Şu ana bu kadar bu schema düz clojure verisi (plain clojure data).
; POJO: plain old java object.
; prensip: düz veri objeleriyle çalışmak her zaman iyidir.

; Neden genel olarak düz veri objeleriyle çalışmak tercih edilir programlamada?
; rfr: Rich Hickey, data passive vs. terimleriyle alakalı

; Bu düz veriyi henüz veritabanına koymadık.
; Bunu veritabanına koymak için `transact` fonksiyonunu kullanırız.
(d/transact conn {:tx-data schema-1})
;=>
;{:db-before #datomic.core.db.Db{:id "598cb3a2-b9ac-446c-a261-13c9aba469eb",
;                                :basisT 5,
;                                :indexBasisT 0,
;                                :index-root-id nil,
;                                :asOfT nil,
;                                :sinceT nil,
;                                :raw nil},
; :db-after #datomic.core.db.Db{:id "598cb3a2-b9ac-446c-a261-13c9aba469eb",
;                               :basisT 6,
;                               :indexBasisT 0,
;                               :index-root-id nil,
;                               :asOfT nil,
;                               :sinceT nil,
;                               :raw nil},
; :tx-data [#datom[13194139533318 50 #inst"2023-02-01T12:00:34.709-00:00" 13194139533318 true]
;           #datom[73 10 :ogrenci_id 13194139533318 true]
;           #datom[73 40 22 13194139533318 true]
;           #datom[73 42 38 13194139533318 true]
;           #datom[73 41 35 13194139533318 true]
;           #datom[74 10 :isim 13194139533318 true]
;           #datom[74 40 23 13194139533318 true]
;           #datom[74 41 35 13194139533318 true]
;           #datom[75 10 :sehir 13194139533318 true]
;           #datom[75 40 23 13194139533318 true]
;           #datom[75 41 35 13194139533318 true]
;           #datom[0 13 73 13194139533318 true]
;           #datom[0 13 74 13194139533318 true]
;           #datom[0 13 75 13194139533318 true]],
; :tempids {}}

; bu işlemle tablomuzun kolonlarını tanımlamış olduk sadece
;| öğrenci_id   | isim          | şehir     |
; şimdi buna satırları da eklememiz lazım.

;| 101          | Ali Niyazi    | Bursa     |
;| 102          | Veli Şimşek   | Kütahya   |
@(def data
   [{:ogrenci_id 101
     :isim "Ali Niyazi"
     :sehir "Bursa"}
    {:ogrenci_id 102
     :isim "Veli Şimşek"
     :sehir "Kütahya"}])
;=> [{:ogrenci_id 101, :isim "Ali Niyazi", :sehir "Bursa"} {:ogrenci_id 102, :isim "Veli Şimşek", :sehir "Kütahya"}]
(d/transact conn {:tx-data data})
;=>
;{:db-before #datomic.core.db.Db{:id "598cb3a2-b9ac-446c-a261-13c9aba469eb",
;                                :basisT 6,
;                                :indexBasisT 0,
;                                :index-root-id nil,
;                                :asOfT nil,
;                                :sinceT nil,
;                                :raw nil},
; :db-after #datomic.core.db.Db{:id "598cb3a2-b9ac-446c-a261-13c9aba469eb",
;                               :basisT 7,
;                               :indexBasisT 0,
;                               :index-root-id nil,
;                               :asOfT nil,
;                               :sinceT nil,
;                               :raw nil},
; :tx-data [#datom[13194139533319 50 #inst"2023-02-01T12:06:17.722-00:00" 13194139533319 true]
;           #datom[79164837199948 73 101 13194139533319 true]
;           #datom[79164837199948 74 "Ali Niyazi" 13194139533319 true]
;           #datom[79164837199948 75 "Bursa" 13194139533319 true]
;           #datom[79164837199949 73 102 13194139533319 true]
;           #datom[79164837199949 74 "Veli Şimşek" 13194139533319 true]
;           #datom[79164837199949 75 "Kütahya" 13194139533319 true]],
; :tempids {}}

; Şimdi EAV tablomuzu veritabanımıza kaydetmiş olduk.
;| E     | A       | V             |
;|-----  |-------  |-------------  |
;| 101   | isim    | Ali Niyazi    |
;| 101   | şehir   | Bursa         |
;| 102   | isim    | Veli Şimşek   |
;| 102   | şehir   | Kütahya       |

; Şimdi sorgulamalar yapabiliriz

; Sorgulama yapmadan önce veritabanının son halini çekelim
(def db (d/db conn))

(d/q
  '[:find ?e
    :where
    [?e :sehir "Bursa"]]
  db)
;=> [[79164837199948]]

(d/q
  '[:find ?i
    :where
    [?e :sehir "Bursa"]
    [?e :isim ?i]]
  db)
;=> [["Ali Niyazi"]]

; q: entity'nin id'si neden 101 gelmedi de 791... geldi?
; onun için `ogrenci_id` atributunu çekmemiz lazım
(d/q
  '[:find ?oid
    :where
    [?e :sehir "Bursa"]
    [?e :ogrenci_id ?oid]]
  db)
;=> [[101]]

; q: peki 791... numarası nereden geliyor?
; datayı ilk kaydettiğimizde transact fonksiyonuyla bize otomatik olarak bu entity idlerini oluşturup kendi içinde sakladı
;           #datom[79164837199948 73 101 13194139533319 true]
;           #datom[79164837199948 74 "Ali Niyazi" 13194139533319 true]
;           #datom[79164837199948 75 "Bursa" 13194139533319 true]

; bunu biraz inceleyelim
; burada öncelikle datom denilen bir kavram var

; q: Datom ne anlama gelir?
; İlişkisel veritabanlarındaki birim varlığımız (öğemiz, elementimiz), satırlardır.
; örneğin:
;| 101   | isim    | Ali Niyazi    |
; Ancak datalog veritabanlarında birim yapıtaşımız satır değil, datomdur.
; datom: data + atom kelimesinin birleşimi
; atom: bir yapıtaşı diye düşünelim
; her bir datom bir satırdaki belli bir atributun değerini içerir
; mesela burada 3 tane datom var:
;           #datom[79164837199948 73 101 13194139533319 true]
;           #datom[79164837199948 74 "Ali Niyazi" 13194139533319 true]
;           #datom[79164837199948 75 "Bursa" 13194139533319 true]
; ilki: yukarıdaki satırın ogrenci_id atributunun değerini içerir yani 101 değerini
; 2: aynı satırın `isim` atributunun değerini içerir: "Ali Niyazi"
; 3: aynı satırın `sehir` atributunun değerini içerir: "Bursa"

; bu 3 datom aynı entity'ye ait.
; o entity de aslında bizim düz tablomuzdaki bir satıra denk geliyor:
;| 101   | isim    | Ali Niyazi    |
; dolayısıyla yukarıdaki `d/q` sorgu cümlelerinde `?e` değişkeniyle bu entity'ye referans veriyorum hep

(d/q
  '[:find ?oid
    :where
    [?e :sehir "Bursa"]
    [?e :ogrenci_id ?oid]]
  db)

; sehir değeri Bursa olan entitylerin tüm atributlarını görmek istiyorum
(d/q
  '[:find ?a
    :where
    [?e :sehir "Bursa"]
    [?e ?a _]]
  db)
;=> [[73] [74] [75]]
; bu şekilde yapınca 3 tane atributumuz vardı ya, ogrenci_id, isim, sehir.
; bunların entity_id'lerini almış olduk
; peki bu atributların id'lerini değil de isimlerini alabilir miyiz?

(d/q
  '[:find ?aname
    :where
    [?e :sehir "Bursa"]
    [?e ?a _]
    [?a :db/ident ?aname]]
  db)
;=> [[:isim] [:ogrenci_id] [:sehir]]

; schemayı ilk transact kaydettiğimizde bize şöyle bir data dönmüştü:
;           #datom[73 10 :ogrenci_id 13194139533318 true]
;           #datom[73 40 22 13194139533318 true]
;           #datom[73 42 38 13194139533318 true]
;           #datom[73 41 35 13194139533318 true]
;           #datom[74 10 :isim 13194139533318 true]
;           #datom[74 40 23 13194139533318 true]
;           #datom[74 41 35 13194139533318 true]
;           #datom[75 10 :sehir 13194139533318 true]
;           #datom[75 40 23 13194139533318 true]
;           #datom[75 41 35 13194139533318 true]
; bu datomlar bizim schemadaki atributlarımızın key-value ikililerine denk geliyor:
; örneğin:
;           #datom[73 10 :ogrenci_id 13194139533318 true]
;           #datom[73 40 22 13194139533318 true]
;           #datom[73 42 38 13194139533318 true]
;           #datom[73 41 35 13194139533318 true]
; bu `ogrenci_id` atributunun datomları
;{:db/ident :ogrenci_id
; :db/valueType :db.type/long
; :db/unique :db.unique/identity
; :db/cardinality :db.cardinality/one}
; Dikkat: 4 tane kv ikilimiz var. Yukarıda da 4 tane datom var
; Demek ki, her bir kv ikilisi için, bir datom tanımlanmış.