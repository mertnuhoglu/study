(ns mert.e02)

; Barış'la Datomic Çalışmaları
; Tarih: 20230208
; rfr: video/20230208-mert-clj-egzersiz-26.mp4
; rfr: video/20230208-mert-clj-egzersiz-27.mp4

; Konu: mbrainz örnekler

; rfr: Article: mbrainz tutorial <url:file:///~/prj/study/clj/mbrainz-tutorial-rtc.md#r=g12871>
; rfr: [Datomic - Datomic MusicBrainz sample database](https://blog.datomic.com/2013/07/datomic-musicbrainz-sample-database.html)

(require '[datomic.api :as d])
; (def uri "datomic:mem://movies")
(def uri "datomic:dev://localhost:4334/mbrainz-1968-1973")
(def conn (d/connect uri))

;; qry01: tüm attribute'ları listele
(d/q '[:find ?n
       :where
       [:db.part/db :db.install/attribute ?a]
       [?a :db/ident ?n]]
  (d/db conn))
; #{[:medium/trackCount] [:db/code] [:db.sys/reId] [:medium/tracks] [:release/packaging] [:track/name] [:release/month] [:db/fn] [:release/year] [:release/artistCredit] [:artist/name] [:db/txInstant] [:release/labels] [:abstractRelease/name] [:db.alter/attribute] [:db/noHistory] [:db/isComponent] [:release/language] [:release/gid] [:abstractRelease/artists] [:artist/endDay] [:fressian/tag] [:db/index] [:db/lang] [:abstractRelease/artistCredit] [:release/script] [:language/name] [:db.excise/beforeT] [:db.sys/partiallyIndexed] [:db.install/valueType] [:artist/country] [:abstractRelease/type] [:script/name] [:artist/endMonth] [:artist/startMonth] [:db.install/attribute] [:track/position] [:artist/sortName] [:label/country] [:release/status] [:db/system-tx] [:label/startMonth] [:label/gid] [:db/doc] [:label/endYear] [:track/artists] [:artist/startDay] [:db.install/function] [:release/name] [:db/cardinality] [:db/excise] [:country/name] [:db.excise/attrs] [:artist/type] [:release/artists] [:artist/gid] [:release/day] [:artist/gender] [:abstractRelease/gid] [:label/sortName] [:db/fulltext] [:medium/position] [:track/artistCredit] [:release/barcode] [:db.excise/before] [:label/startYear] [:label/type] [:release/country] [:release/abstractRelease] [:db.install/partition] [:release/media] [:label/name] [:db/valueType] [:label/endMonth] [:artist/startYear] [:db/unique] [:medium/name] [:db/ident] [:label/endDay] [:track/duration] [:label/startDay] [:medium/format] [:artist/endYear]}
; Veritabanımızdaki tüm atributların listesini çekmiş olduk bu şekilde
; Sadece kendi alan modelimiz değil, meta modeldeki atributları da çekiyoruz bu şekilde.
; Nereden anlıyoruz meta model atributlarını çektiğimizi?
; Çünkü ismi db ns içindeki atributlar da bulunuyor: [:db/code] gibi

; Burada ne yapıyoruz?
; :db ns'yi daha önce nerelerde kullanmıştık?
; :db/ident :db/id :db...
; :db ns'indeki tüm keywordler datomic'in kendi ön tanımlı keywordleri
; dolayısıyla biz kendi ns'lerimizi oluştururken :db nsini kullanmayalım ki karışıklık olmasın

; :db.part/db    bizim veritabanımız
; :db.install/attribute  attributelar kolonu
; rfr:
; https://docs.datomic.com/on-prem/schema/schema.html#partitions
; [Datomic Internals / Francis Avila | Observable](https://observablehq.com/@favila/datomic-internals)

;; qry02: tüm ident'leri (tüm keywordlerim veya tüm enumlarım) listele
(d/q '[:find ?id
       :where
       [?e :db/ident ?id]]
  (d/db conn))
; #{[:language/ukw] [:language/bow] [:media/part_48] [:artist/part_15] [:language/tij] [:language/cre] [:language/ciy] [:language/dlg] [:script/Tagb] [:script/Zyyy] [:language/nsl] [:language/nka] [:language/lwm] [:language/mht] [:language/yuk] [:language/gbh] [:language/jrr] [:language/ylo] [:language/ncg] [:language/dts] [:language/hmb] [:language/omo] [:language/nsm] [:language/jal] [:language/pha] [:language/kcx] [:language/amj] [:language/bwr] [:db/retract] [:language/aug] ...

; toplam kaç ident'imiz var?
(d/q '[:find (count ?id)
       :where
       [?e :db/ident ?id]]
  (d/db conn))
;=> [[8493]]

; q: Bu veritabanı sorgularının çalışması için sunucunun çalışması gerekiyor mu?
; Evet. Sunucuyu biz terminalden şu şekilde çalıştırmıştık:
;❯ bin/transactor config/samples/dev-transactor-template.properties
;Launching with Java options -server -Xms1g -Xmx1g -XX:+UseG1GC -XX:MaxGCPauseMillis=50
;System started
; Bunu ^C ile kapatırsak, deneyince, yine sorgular çalışıyor. Ama data yazamayız.
; Sorguların çalışması da datomic'in client tarafında veritabanını bir nevi keşlemesinden kaynaklanıyor.
; Ancak REPL'ı yeniden başlattığımızda artık connection yapamıyoruz.

; Her seferinde (d/db conn) yazmayalım. Bu objeye bir db ismi verelim. Daha kısa olur.
(def db (d/db conn))
(d/q '[:find (count ?id)
       :where
       [?e :db/ident ?id]]
  db)

; mbrainz'in veri modeli dediğimiz şey, schemasının ER diyagramı olarak gösterilir.
; https://github.com/Datomic/mbrainz-sample/blob/master/schema.pdf

; ER diyagramı = ER şeması = veri modeli = class model = uml class model = alan modeli = domain model = data model

; q: Bu ER diyagramı bizim ne işimize yarar?
; Bu veri modelinin şematize edilmiş halleri, bizim veritabanı üzerinde kolayca sorgular ve işlemler yapmamızı sağlar.
; Sadece şemaya bakarak sorgu yazabiliriz.
; şema ~ schema
; schema: plan veya dizayn anlamında kullanılıyor.

#_(d/q '[:find (pull ?r [*])
         :where
         [?r :release/script _]]
    db)
; Bu şekilde bir sorguyu çalıştırmayın çünkü veritabanı çok büyük olduğundan, sorgunun bitmesi çok zaman alır.
; Result seti limit ile sınırlandırmamız gerekiyor.
; Datomic'te limit diye bir özellik var. SQL'de de var aynısı.
; Ancak :limit cümleciği (clause) ancak query map'lerde çalışır.
; Bizim şu ana kadar kullandığımız tüm sorgu örnekleri query list'ti.
; [Datomic Queries and Rules | Datomic](https://docs.datomic.com/on-prem/query/query.html)
; Burada query map kullanımı anlatılıyor.
; Datomic'in bu tarz dokümantasyonlarının ilk başında bir gramer tanımlaması (spesifikasyonu) olur.
; /Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20230208_130546.jpg
; Bu gramer spekleri, BNF adı verilen başka bir dil kullanır.
; [Backus–Naur form - Wikiwand](https://www.wikiwand.com/en/Backus%E2%80%93Naur_form)
; Bu gramer tanımlama dili, temelde bir cebir gibi tanımlama yapar.
; Yani önce yapıtaşlarını tanımlar.
; Sonra bu yapıtaşlarıyla değişkenler tanımlar.
; Sonra bu değişkenler birbirine referans vererek (placeholder veya yer tutucu gibi) daha kompleks (bileşke) ifadeler oluşturmayı sağlar.
; https://www.google.com/search?q=bnf+examples

; Konu: Query Map

; Şu ana kadar query fonksiyonu olan `d/q` fonksiyonunu hep list formatında kullandık.
; Bir de map formatında sorgu cümlesini yazabiliriz.

; Query list formatı:
(d/q '[:find (count ?id)
       :where
       [?e :db/ident ?id]]
  db)

; a01: :limit cümleciğiyle çözüm
; Bu sorgunun query map formatındaki yazımı:
#_(d/q
    {:query '[:find  ?id
              :where [?e :db/ident ?id]]
     :args  [db]})
; TODO query map problemi.
; rfr: mert/e02b.clj
; Hata veriyor:
; Execution error (IllegalArgumentException) at datomic.query/validate-query (query.clj:313).
; No :find clause specified
; https://docs.datomic.com/cloud/query/query-data-reference.html#query-example şöyle diyor:
; ;; The previous example also works with datomic.client.api used for Datomic Cloud
;;; Example query using the datomic.client.api arity-1
; Acaba bizim kullandığımız api datomic.client.api değil datomic.api olduğundan mı böyle?

; a02: take fonksiyonuyla çözüm
(take 3 (d/q '[:find (pull ?r [*])
               :where
               [?r :release/script _]]
          db))
;=>
;([{:release/script #:db{:id 17592186053542},
;   :release/name "Imputazione di omicidio per uno studente",
;   :release/artists [#:db{:id 624522604590589}],
;   :release/country #:db{:id 17592186045495},
;   :release/gid #uuid"fd9726f9-ad1d-403b-a01d-d3bf00bada6d",
;   :release/labels [#:db{:id 17592186069424}],
;   :release/language #:db{:id 17592186052542},
;   :release/status "Official",
;   :release/artistCredit "Ennio Morricone",
;   :db/id 17592186069764,
;   :release/year 1972,
;   :release/media [{:db/id 905997581421012,
;                    :medium/tracks [{:db/id 905997581421013,
;                                     :track/artists [#:db{:id 624522604590589}],
;                                     :track/artistCredit "Ennio Morricone",}]}]}])

(->> (d/q '[:find ?name
            :where
            [?r :release/name ?name]]
       db)
  (take 3))
;=> (["Osmium"] ["Hela roept de akela"] ["Ali Baba"])

(->> (d/q '[:find (pull ?artists [*])
            :where
            [?r :release/name ?name]
            [?r :release/artists ?artists]]
       db)
  (take 3))
;=>
;([{:db/id 527765581341755,
;   :artist/gid #uuid"21dbcaa3-47ad-4c12-977c-66ddc891c938",
;   :artist/name "Juan y Junior",
;   :artist/sortName "Juan y Junior",
;   :artist/type #:db{:id 17592186045424}}]
; [{:db/id 527765581341813,
;   :artist/gid #uuid"9084afa7-7cfd-4f41-9a4b-ea43eef9c4b8",
;   :artist/name "The Neutral Spirits",
;   :artist/sortName "Neutral Spirits, The"}]
; [{:db/id 527765581341825,
;   :artist/gid #uuid"21c5fb53-6fbc-4080-989a-b26d4540f4fd",
;   :artist/name "Cymande",
;   :artist/sortName "Cymande",
;   :artist/type #:db{:id 17592186045424}}])

; q: Neden ikinci artistte :artist/type atributu yok, diğerlerinde var?
; Bu Datomic'in ayırt edici özelliklerinden birisi.
; Normal SQL veritabanlarında bir tablodaki her satırda aynı atributlar bulunur.
; Fakat Datomic'te böyle bir zorunluluk yok.
; Datomic'te her bir entity, diğer entity ile aynı tipten bile olsa, birbirinden farklı atributlara sahip olabilir.
; TODO opsiyonel atributların artı ve eksilerini anlatalım

(->> (d/q '[:find (pull ?country [*])
            :where
            [?r :release/name ?name]
            [?r :release/artists ?artists]
            [?artists :artist/name ?artist-name]
            [?artists :artist/country ?country]]
       db)
  (take 3))
;=>
;([{:db/id 17592186045483, :db/ident :country/ZA, :country/name "South Africa"}]
; [{:db/id 17592186045485, :db/ident :country/PE, :country/name "Peru"}]
; [{:db/id 17592186045488, :db/ident :country/CH, :country/name "Switzerland"}])

(->> (d/q '[:find ?name ?artist-name ?country-name
            :where
            [?r :release/name ?name]
            [?r :release/artists ?artists]
            [?artists :artist/name ?artist-name]
            [?artists :artist/country ?country]
            [?country :country/name ?country-name]]
       db)
  (take 3))
;=>
;(["Comin' Right at Ya" "Asleep at the Wheel" "United States"]
; ["Je t'aime, je t'aime" "Michel Sardou" "France"]
; ["I Guess I'll Miss the Man" "The Supremes" "United States"])

; Daha önce mbrainz sorgu örnekleri:
; rfr: /Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/mbrainz/e01.clj
; rfr: [Datomic - Datomic MusicBrainz sample database](https://blog.datomic.com/2013/07/datomic-musicbrainz-sample-database.html)
; [Queries · Datomic/mbrainz-sample Wiki](https://github.com/Datomic/mbrainz-sample/wiki/Queries)
; https://github.com/Datomic/mbrainz-sample/blob/master/examples/clj/datomic/samples/mbrainz.clj
; https://github.com/Datomic/mbrainz-sample/blob/master/src/clj/datomic/samples/query.clj
