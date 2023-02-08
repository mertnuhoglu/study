(ns mert.e02)

; Barış'la Datomic Çalışmaları
; Tarih: 20230208
; rfr: video/20230208-mert-clj-egzersiz-26.mp4

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

; Burada ne yapıyoruz?
; :db ns'yi daha önce nerelerde kullanmıştık?
; :db/ident :db/id :db...
; :db ns'indeki tüm keywordler datomic'in kendi ön tanımlı keywordleri
; dolayısıyla biz kendi ns'lerimizi oluştururken :db nsini kullanmayalım ki karışıklık olmasın

; :db.part/db    bizim verit
; :db.install/attribute

