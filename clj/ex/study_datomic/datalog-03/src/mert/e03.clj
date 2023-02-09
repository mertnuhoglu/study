(ns mert.e03)

; Barış'la Datomic Çalışmaları
; Tarih: 20230209
; rfr: video/20230209-mert-clj-egzersiz-28.mp4

; Konu: mbrainz örnekler

(require '[datomic.api :as d])
; (def uri "datomic:mem://movies")
(def uri "datomic:dev://localhost:4334/mbrainz-1968-1973")
(def conn (d/connect uri))
(def db (d/db conn))

;q03 databasedeki release date'i 1975 olan bütün şarkıların isimlerini döndürelim.
(take 10 (d/q '[:find ?year ?title
                :where
                [?date :release/year ?year]
                [(= ?year 1970)]
                [_ :track/name ?title]]
           db))
;=>
;([1970 "You're Everything"]
; [1970 "Clyde"]
; [1970 "Whisper"]
; [1970 "The Ringmaster"]
; [1970 "Sidewalk Cafe"]
; [1970 "No, I'm Never Gonna Give Ya Up (instrumental)"]
; [1970 "Relax"]
; [1970 "Main Shair To Nahin"]
; [1970 "Il ratto della chitarra"]
; [1970 "Wailing of the Willow"])

(take 10 (d/q '[:find ?year ?title
                :in $ ?date
                :where
                [?date :release/year ?year]
                [(= ?year ?date)]
                [_ :track/name ?title]]
           db 1970))
;=> ()

; Öncelikle elimizdeki problemi minimize edelim.
(take 10 (d/q '[:find ?year
                :in $ ?date
                :where
                [?date :release/year ?year]
                [(= ?year ?date)]]
           db 1970))
;=> ()

(take 10 (d/q '[:find ?year
                :in $ ?date
                :where
                [_ :release/year ?year]
                [(= ?year ?date)]]
           db 1970))
;=> ([1970])
