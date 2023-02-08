(ns mert.e01)

; Barış'la Datomic Çalışmaları
; Tarih: 20230207
; rfr: video/20230207-mert-clj-egzersiz-24.mp4

; Konu: mbrainz database setup

; rfr: Article: mbrainz tutorial <url:file:///~/prj/study/clj/mbrainz-tutorial-rtc.md#r=g12871>
; rfr: [Datomic - Datomic MusicBrainz sample database](https://blog.datomic.com/2013/07/datomic-musicbrainz-sample-database.html)

; Relationship Diagram
; http://github.com/Datomic/mbrainz-sample/raw/master/schema.pdf
; ER Diagram (model) = Entity-Relationship Model
; Varlıklarla, o varlıkların arasındaki ilişkilerin modeli

; Class model ile ER Model birbirine çok benzer

; 1. Önce datomic transactor programını çalıştır
; rfr:
; datomic-pro maven setup <url:file:///~/prj/study/clj/datomic-pro-maven-setup.md#r=g13697>
; run datomic console <url:file:///~/prj/study/clj/run-datomic-console.md#r=g12858>

(use '[datomic.api :only [q db] :as d])
; (def uri "datomic:mem://movies")
(def uri "datomic:dev://localhost:4334/mbrainz-1968-1973")
(d/create-database uri)
(def conn (d/connect uri))

;; qry01: tüm attribute'ları listele
(q '[:find ?n
     :where
     [:db.part/db :db.install/attribute ?a]
     [?a :db/ident ?n]]
  (db conn))
