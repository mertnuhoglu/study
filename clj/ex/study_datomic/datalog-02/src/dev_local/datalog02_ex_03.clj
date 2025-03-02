(ns dev-local.datalog02-ex-03)

; Konu: Transact Datoms ve Basic Queries
;
; Barış'la Datomic Çalışmaları
; Tarih: 20230202
; rfr: video/20230202-mert-clj-egzersiz-14.mp4

; rfr: sndgrsm > 20230202-Barışla-Datomic-Çalışma
; https://docs.google.com/spreadsheets/d/12IY0eoK8ny1i_EICUMxPas7UHHz_WfoW5b6xh8wUpCE/edit#gid=0

(require '[datomic.client.api :as d])

;; Memory storage
(def client (d/client {:server-type :dev-local
                       :storage-dir :mem
                       :system "ci"}))

(d/create-database client {:db-name "db03"})

(def conn (d/connect client {:db-name "db03"}))

; [Transact a Schema | Datomic](https://docs.datomic.com/on-prem/getting-started/transact-schema.html)

(def movie-schema
  [{:db/ident :movie/title
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc "The title of the movie"}])
; Fark: Daha önce her bir atributa doğrudan isim vermiştik. Burada namespace ile isim veriyoruz
; :movie/title
; Terim: uzun namespace ile tanımlanmış isimlere, qualified name denir.

; :db/doc Bu atributun ne anlama geldiğine dair bir dokümantasyon oluşturuyor. Bir nevi comment gibi.

(def movie-schema
  [{:db/ident :movie/title
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc "The title of the movie"}
   {:db/ident :movie/genre
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc "The genre of the movie"}
   {:db/ident :movie/release-year
    :db/valueType :db.type/long
    :db/cardinality :db.cardinality/one
    :db/doc "The year the movie was released in theaters"}])

; şimdi bu schemayı veritabanına kaydedelim

(d/transact conn {:tx-data movie-schema})
;=>
;{:db-before #datomic.core.db.Db{:id "321780d1-5c49-4e24-9ada-05d91fc51179",
;                                :basisT 6,
;                                :indexBasisT 0,
;                                :index-root-id nil,
;                                :asOfT nil,
;                                :sinceT nil,
;                                :raw nil},
; :db-after #datomic.core.db.Db{:id "321780d1-5c49-4e24-9ada-05d91fc51179",
;                               :basisT 7,
;                               :indexBasisT 0,
;                               :index-root-id nil,
;                               :asOfT nil,
;                               :sinceT nil,
;                               :raw nil},
; :tx-data [#datom[13194139533319 50 #inst"2023-02-02T08:10:16.151-00:00" 13194139533319 true]],
; :tempids {}}

; Dikkat edersek, d/transact fonksiyonu sonucu dönen obje bir map
; 2. Bu mapin 4 tane anahtar-değer (kv pair) çifti var.
; :db-before
; :db-after
; :tx-data
; :tempids
; Bu 4 tane anahtar-değer ikilisinden :tx-data bizim asıl datomlarımızı gösterir.
; Yani biz bu işlem sonucu veritabanına hangi datomları kaydettik, bunu :tx-data içinde görürüz

; Şimdi veritabanımıza yeni satırlar ekleyelim
(def first-movies [{:movie/title "The Goonies"
                    :movie/genre "action/adventure"
                    :movie/release-year 1985}
                   {:movie/title "Commando"
                    :movie/genre "action/adventure"
                    :movie/release-year 1985}
                   {:movie/title "Repo Man"
                    :movie/genre "punk dystopia"
                    :movie/release-year 1984}])
(d/transact conn {:tx-data first-movies})
;=>
;{:db-before #datomic.core.db.Db{:id "321780d1-5c49-4e24-9ada-05d91fc51179",
;                                :basisT 7,
;                                :indexBasisT 0,
;                                :index-root-id nil,
;                                :asOfT nil,
;                                :sinceT nil,
;                                :raw nil},
; :db-after #datomic.core.db.Db{:id "321780d1-5c49-4e24-9ada-05d91fc51179",
;                               :basisT 8,
;                               :indexBasisT 0,
;                               :index-root-id nil,
;                               :asOfT nil,
;                               :sinceT nil,
;                               :raw nil},
; :tx-data [#datom[13194139533320 50 #inst"2023-02-02T08:16:34.424-00:00" 13194139533320 true]
;           #datom[101155069755468 73 "The Goonies" 13194139533320 true]
;           #datom[101155069755468 74 "action/adventure" 13194139533320 true]
;           #datom[101155069755468 75 1985 13194139533320 true]
;           #datom[101155069755469 73 "Commando" 13194139533320 true]
;           #datom[101155069755469 74 "action/adventure" 13194139533320 true]
;           #datom[101155069755469 75 1985 13194139533320 true]
;           #datom[101155069755470 73 "Repo Man" 13194139533320 true]
;           #datom[101155069755470 74 "punk dystopia" 13194139533320 true]
;           #datom[101155069755470 75 1984 13194139533320 true]],
; :tempids {}}

; Not: first-movies içinde toplam 3 farklı obje (entity) (map) var.
; Her entity içinde de üçer tane atribut ve değeri var.
; Dolayısıyla toplam 9 atribut değeri var.
; Bizim tx sonucunda da toplam 9 datom dönmüş. Artı bir tane de fazladan datom var. İlk datom.
; Bunlar bizim verilerimizin datomları:
;           #datom[101155069755468 73 "The Goonies" 13194139533320 true]
;           #datom[101155069755468 74 "action/adventure" 13194139533320 true]
;           #datom[101155069755468 75 1985 13194139533320 true]
;           #datom[101155069755469 73 "Commando" 13194139533320 true]
;           #datom[101155069755469 74 "action/adventure" 13194139533320 true]
;           #datom[101155069755469 75 1985 13194139533320 true]
;           #datom[101155069755470 73 "Repo Man" 13194139533320 true]
;           #datom[101155069755470 74 "punk dystopia" 13194139533320 true]
;           #datom[101155069755470 75 1984 13194139533320 true]],
; İlk baştaki datomsa:
; #datom[13194139533320 50 #inst"2023-02-02T08:16:34.424-00:00" 13194139533320 true]
; Bu ilk datom, transactionın bilgilerini içeren datom.

; Transaction ne demek?
; transaction: işlem
; Veritabanında yaptığımız her bir ekleme, silme, düzeltme, birer işlemdir.
; Bizim `d/transact` fonksiyonunu her çağırışımız bir transaction kaydı oluşturur.
; Neden böyle bir transaction kaydını veritabanına yazmak isteyebiliriz?
; Daha sonra ihtiyacımız olacaktır çünkü.
; Mesela ben bu verileri ne zaman kaydettim?
; Benim bu verileri kaydetme tarihim, yukarıdaki business datasından farklı bir veri türü.
; Bu bir meta veri (aşkın veri).
; Meta: beyond, aşkın, ötesi.
; Meta: kelimesini genellikle bir şeyin özünü tarif etmek için kullanırız.

; Business data (iş verisi): Bu esas iş alanına (business domain) ait olan verileri ifade eder.
; Biz enformasyon sistemi kurarken, aslında hem iş verisini, hem de meta verileri saklamamız gerekiyor.
; İş verisi, esas kullanıcının işine yarayan verilerdir.
; Meta verilerse, güvenlik açısından, kontrol açısından veya başka idari amaçlarla ihtiyaç olabilecek verilerdir.

; İş verilerinin datomlarına baktığımızda, her bir entityye ait olan datomların aynı entity id'ye sahip olduğunu görürüz.
; Örnek:
;           #datom[101155069755468 73 "The Goonies" 13194139533320 true]
;           #datom[101155069755468 74 "action/adventure" 13194139533320 true]
;           #datom[101155069755468 75 1985 13194139533320 true]
; Bu üç datom hepsi aynı entity_id'ye sahip: 101155069755468
; Neden böyle?
; Çünkü biz bu veriyi kaydederken hepsini aynı mapin içinde göndermiştik:
;{:movie/title "The Goonies"
; :movie/genre "action/adventure"
; :movie/release-year 1985}

; [Query the Data | Datomic](https://docs.datomic.com/on-prem/getting-started/query-the-data.html)

(def db (d/db conn))
(def all-movies-q '[:find ?e
                    :where [?e :movie/title]])
(d/q all-movies-q db)
;=> [[101155069755468] [101155069755469] [101155069755470]]

; Sorgu cümleciklerine data pattern deniyor
; [?e :movie/title]
; data pattern: Entity Attribute Value formatında yazılıyor
; fakat burada iki tane öğe var.
; bu durumda bu EA'ya karşılık gelir.
; dolayısıyla bu sorgu şu anlama geliyor:
; Bana :movie/title atributu olan tüm entityleri bul. Bunları ?e değişkenine koy
; Buradaki entity_id yukarıdaki entity_id mi acaba?
; Evet
;           #datom[101155069755470 73 "Repo Man" 13194139533320 true]
;           #datom[101155069755470 74 "punk dystopia" 13194139533320 true]
;           #datom[101155069755470 75 1984 13194139533320 true]],

(def all-titles-q '[:find ?movie-title
                    :where [_ :movie/title ?movie-title]])
(d/q all-titles-q db)
;=> [["Commando"] ["The Goonies"] ["Repo Man"]]
; Buradaki sorgu cümleciğimiz: [_ :movie/title ?movie-title]
; Entity yerine `_` kullanmış. underscore, bunu ihmal et anlamına gelir. Buraya ne geldiği önemli değil.
; Bunu bellekte saklama.
; Buradaki sorguyu şöyle okumalı:
; Bana içinde :movie/title atributu olan tüm entityleri bul.
; Bu entityleri saklama. Ama entitylerin :movie/title atribut değerlerini ?movie-title değişkeni içine koy.

; Genel olarak datalogda ? ile başlayan kelimelere, değişken diyoruz.
; Daha doğrusu, bunların doğru ismi: mantıksal değişken (logical variable)

; Neden mantıksal değişken deniyor?
; Aslında datalog daha geçmişinde, bir mantık motoru olarak tasarlanıyor.
; Şunu yapmak amaç: Mantıksal çıkarımlar yapmak.
; Örnek:
; Tüm insanlar ölümlüdür.
; Sokrat bir insandır.
; Öyleyse Sokrat ölümlüdür.
; Burada, ilk iki cümle birer varsayım (bilgi). Buna fact de deniyor.
; Üçüncü cümleyse, bir çıkarım (conclusion).
; Datalogla bunu yapmak mümkün.
; Yani biz bilgi olarak veritabanına ilk iki cümleyi yazarsak, üçüncü cümleyi çıkartabiliriz, otomatikman yazılımla.

; Yapay zekanın iki farklı kolu var:
; İlki, sembolik yapay zeka. Bu işte, şimdi bahsettiğimiz mantıksal çıkarımlara dayanıyor.
; İkincisiyse, istatistiksel yapay zeka. Machine learning.

(def titles-from-1985 '[:find ?title
                        :where
                        [?e :movie/title ?title]
                        [?e :movie/release-year 1985]])
(d/q titles-from-1985 db)
;=> [["Commando"] ["The Goonies"]]
; buradaki sorgu:
; önce bana içinde :movie/title olan tüm entityleri bul. Bunların :movie/title değerlerini ?title değişkenine koy.
; sonra bu entitilerden :movie/release-year değeri 1985 olanları filtrele.
; Bu filtrelenmiş (yani kalan) entitylerin ?title değerlerini göster.

; Burada datalogun bir join işlemini yaptık.
; İki sorgu cümleciğimiz var.
; Bu iki sorgu cümleciğinin kesişim kümesini alıyoruz aslında.
; İlk sorgu cümleciğinde ?e-?title kümesi çıkıyor. [?e :movie/title ?title]
; İkinci sorguda başka bir ?e kümesi çıkıyor. [?e :movie/release-year 1985]
; İlk sorgudaki ?e kümesiyle ikinci ?e kümesinin ortak kesişimini alıyoruz.
; Bu ortak kesişimdeki ?title değerlerini alıyoruz sonra.
; İki tane kümeyi aslında join ediyoruz.

; q: Buradaki ?e değişkenini herhangi bir yerde kullanmadık. find yanında kullansak mantıklı olacak
; ?e değişkenini find içinde kullanabiliriz
(def titles-from-1985b '[:find ?title ?e
                         :where
                         [?e :movie/title ?title]
                         [?e :movie/release-year 1985]])
(d/q titles-from-1985b db)
;=> [["The Goonies" 101155069755468] ["Commando" 101155069755469]]
; Dikkat: ?e entity_id'leri getiriyor. Anlamlı birer sayı değil bunlar. Sadece tekilliği sağlamak için varlar.
; join işlemi yapmak, bir mantıksal değişkeni birden çok data pattern (sorgu cümleciği) içinde kullanmak anlamına geliyor.
;[?e :movie/title ?title]
;[?e :movie/release-year 1985]
; Ben ?e'yi her iki cümlecikte kullanıyorum.
; Dolayısıyla sonuç kümedeki ?e değişkeninin her iki cümlecikteki kriteri de karşılaması gerekiyor.

; Bunun SQL karşılığını yazmaya çalışalım
; SELECT title, entity_id
; FROM Movie m
; WHERE
; m.release-year = 1985 AND
; m.title IS NOT NULL;

; Bu SQL cümlesindeki şu iki cümlecik bizim yukarıdaki where cümleciklerine denk geliyor:
; m.release-year = 1985 AND
; m.title IS NOT NULL;
; ==
; [?e :movie/title ?title]
; [?e :movie/release-year 1985])
;
; Yalnız SQL cümlesinde join yok. Biz datalog'da ise join yaptık diyoruz.
; Neden böyle?
; Aslında SQL cümlesinde de bir join var dolaylı olarak:
; `m.` ifadesi iki farklı cümlecikteki sonuçların birbirine bağlanmasını sağlıyor.
; Bu `m.` ifadesi datalogdaki `?e` değişkeninin gördüğü işlevi görüyor.
;
(def all-data-from-1985 '[:find ?title ?year ?genre
                          :where 
                          [?e :movie/title ?title]
                          [?e :movie/release-year ?year]
                          [?e :movie/genre ?genre]
                          [?e :movie/release-year 1985]])
(d/q all-data-from-1985 db)
;=> [["The Goonies" 1985 "action/adventure"] ["Commando" 1985 "action/adventure"]]

; Historical Values
; [See Historic Data | Datomic](https://docs.datomic.com/on-prem/getting-started/see-historic-data.html)

(d/q '[:find ?e
       :where [?e :movie/title "Commando"]]
  db)
;=> [[101155069755469]]
; bu entity_id'yi bir değişkene atayalım def kullanarak:
(def commando-id
  (ffirst (d/q '[:find ?e
                 :where [?e :movie/title "Commando"]]
            db)))

; ffirst = (first (first))
(first [[101155069755469]])
;=> [101155069755469]
(ffirst [[101155069755469]])
;=> 101155069755469

; Şimdi bu entity_id'ye ait olan filmin genre'sını değiştirelim
(d/transact conn {:tx-data [{:db/id commando-id :movie/genre "futuristic"}]})
;=>
;{:db-before #datomic.core.db.Db{:id "321780d1-5c49-4e24-9ada-05d91fc51179",
;                                :basisT 8,
;                                :indexBasisT 0,
;                                :index-root-id nil,
;                                :asOfT nil,
;                                :sinceT nil,
;                                :raw nil},
; :db-after #datomic.core.db.Db{:id "321780d1-5c49-4e24-9ada-05d91fc51179",
;                               :basisT 9,
;                               :indexBasisT 0,
;                               :index-root-id nil,
;                               :asOfT nil,
;                               :sinceT nil,
;                               :raw nil},
; :tx-data [#datom[13194139533321 50 #inst"2023-02-02T09:10:24.174-00:00" 13194139533321 true]
;           #datom[101155069755469 74 "futuristic" 13194139533321 true]
;           #datom[101155069755469 74 "action/adventure" 13194139533321 false]],
; :tempids {}}

; Tekrar kodu inceleyelim:
; (d/transact conn {:tx-data [{:db/id commando-id :movie/genre "futuristic"}]})
; Dikkat: `:db/id commando-id `
; Bu ne anlama geliyor?
; `:db/id` bir entitinin idsini belirtiyor
; Yani mevcut bir entiti var. Ona erişmemizi sağlıyor.
; Daha önce hep sıfırdan entiti oluşturuyorduk. Bu yüzden `:db/id` kullanmıyorduk
; Çünkü idleri zaten datomic kendisi otomatik üretiyordu.
;
; Şimdi ne yaptık?
; İki tane datom ekledik, tx datomuna ek olarak
;           #datom[101155069755469 74 "futuristic" 13194139533321 true]
;           #datom[101155069755469 74 "action/adventure" 13194139533321 false]],
; Dikkat: true ve false değerlerine dikkat edin.
;
; İlk datom futuristic diyor. Yani bizim genre'mızın yeni değerini bildiriyor.
; İkinci datomsa, bizim eski değerimiz. "action/adventure"
; İlk datomun son öğesi: true
; İkincinin son öğesi: false
; Bu true false değerleri, bizim bir datomumuzun geçerlilik durumunu gösteriyor.
; Türkçe çevirirsek:
; Bu an itibariyle, artık bu entity için genre değeri "futuristic"tir.
; Eski değer olan "action/adventure" artık geçerli değildir.
;
; Hangi an itibariyle?
; tx numarası 13194139533321 itibariyle.
; Peki  bu tx ne zaman gerçekleşti?
; "2023-02-02T09:10:24.174-00:00" tarihinde.
; Nerede yazıyor bu tarih?
; :tx-data [#datom[13194139533321 50 #inst"2023-02-02T09:10:24.174-00:00" 13194139533321 true]
;

; Dolayısıyla, artık, veritabanımın güncellendiği andan öncesi ve sonrasına ait verileri de takip edebiliyorum.
; Bu özellik datalog'a has bir özellik.
; SQL veritabanlarında bu yok.

; Bu konuyla ilgili detaylı bilgi için:
; [(83) The Database as a Value - Rich Hickey - YouTube](https://www.youtube.com/watch?v=D6nYfttnVco&t=2880s)
; q: Veritabanında tüm geçmiş datayı tutmak şişmeye neden sebep olmaz?
; Persistent data structurelar sayesinde
; [(83) Persistent Data Structures and Managed References - Rich Hickey - YouTube](https://www.youtube.com/watch?v=toD45DtVCFM)
; Aslında clojure'un map, list, vector gibi collection data typeları. Bunlarda da aynı özellik var.
; Bunlar da persistent data structure.
; Bu yüzden bir map üzerinde assoc veya update ile bir değişiklik yaptığında, sana yeni bir map objesi döner.
; Immutabledır her bir obje.
; Bu da clojure'un alamet-i farikalarından birisidir.
; Bu sayede side-effect problemleri ciddi anlamda çözülüyor.

; şimdi biraz önceki en son sorgumuzu tekrar çalıştıralım
(d/q all-data-from-1985 db)
;=> [["The Goonies" 1985 "action/adventure"] ["Commando" 1985 "action/adventure"]]
; Hala "action/adventure" görünüyor. Neden?
; Çünkü sorguyu üzerinde çalıştırdığımız db objesini güncellemedik.
; db objesini güncellemek için tekrar conn'dan db'yi çekeriz
(def db (d/db conn))
(d/q all-data-from-1985 db)
;=> [["The Goonies" 1985 "action/adventure"] ["Commando" 1985 "futuristic"]]
; Şimdi "futuristic" geldi. Yani güncel veri.

; Peki db'nin değerlerine bakalım
(identity db)
;=>
;#datomic.core.db.Db{:id "321780d1-5c49-4e24-9ada-05d91fc51179",
;                    :basisT 9,
;                    :indexBasisT 0,
;                    :index-root-id nil,
;                    :asOfT nil,
;                    :sinceT nil,
;                    :raw nil}
; Not: Bu db'nin basisT (taban zamanı, basis Time gibi düşün): 9
; Dolayısıyla bundan bir önceki veritabanı durumu: 8 olur

; 8 anındaki veritabanını çekmek istiyorum şimdi:
(def old-db (d/as-of db 7))
(identity old-db)
; Şimdi bu eski veritabanı üzerinde sorgulama yapalım:
(d/q all-data-from-1985 old-db)
;=> [["The Goonies" 1985 "action/adventure"] ["Commando" 1985 "action/adventure"]]
; Eski veriye ulaşabildik

; d/history fonksiyonuyla tüm geçmiş veritabanlarını çekeriz
(def hdb (d/history db))
; bununla da tüm geçmiş verileri görebiliriz
(d/q '[:find ?genre
       :where [?e :movie/title "Commando"]
       [?e :movie/genre ?genre]]
  hdb)
;=> [["futuristic"] ["action/adventure"]]
; Türkçesi:
; Tüm geçmiş veritabanlarında (hdb) bir sorgu yap
; :movie/title değeri "Commando" olan tüm entityleri bul
; Bunların :movie/genre değerlerini ?genre değişkeni içine koy ve göster.

(identity hdb)
;=>
;#datomic.core.db.Db{:id "321780d1-5c49-4e24-9ada-05d91fc51179",
;                    :basisT 9,
;                    :indexBasisT 0,
;                    :index-root-id nil,
;                    :asOfT nil,
;                    :sinceT nil,
;                    :raw true}
(type hdb)
;=> datomic.core.db.Db

; q: yukarıdaki ?e yerine `_` koysak ne olur?
(def titles-from-1985c '[:find ?title
                         :where
                         [_ :movie/title ?title]
                         [_ :movie/release-year 1985]])
(d/q titles-from-1985c db)
;=> [["Commando"] ["The Goonies"] ["Repo Man"]]

; q: datom kelimesi nereden geliyor?
; Datalog dili eski bir dil. 1970'lerden kalma.
; Datalog'da: EAV cümleleri var. Bu cümlelere fact deniyor. Belki önerme diyebiliriz.
; Datomic'teyse, datalog'un EAV cümlesine ek bir tümleç daha ekleniyor. Transaction. T.
; EAV -> EAVT oluyor
; Transaction: Bir factin gerçekleşme anı olarak düşünebiliriz.
; Belli bir an itibariyle ben bu facte (önermeye) inanıyorum, doğru kabul ediyorum, diyebiliriz.
; Daha sonra 1 hafta sonra belki bu önermenin doğruluğuna inanmıyorum. Yanlışlıyorum.
; Bu durumda aynı EAV'yi farklı bir T ile birleştirerek, veritabanına tekrar kaydediyorum.
; Datom: EAVT cümlesinin toplamı oluyor.
; Fact: EAV oluyor.
