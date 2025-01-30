(ns dev-local.datalog02-ex-19)

; Barış'la Datomic Çalışmaları
; Tarih: 20230208
; rfr: video/20230208-mert-clj-egzersiz-25.mp4

; Konu: Nested entity map

(require '[datomic.client.api :as d])
(use '[dev-local.e04 :only [conn] :as e04])
(require '[dev-local.e05 :as e05])
(def db (d/db conn))

;
