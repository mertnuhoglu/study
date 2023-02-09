(ns mert.e04)

; Barış'la Datomic Çalışmaları
; Tarih: 20230209
; rfr: video/20230209-mert-clj-egzersiz-29.mp4
; rfr: sndgrsm > 20230209-Barış'la-Datomic-Çalışmaları

; rfr: https://github.com/Bariscanates404/study e01.clj

(require '[datomic.api :as d])
; (def uri "datomic:mem://movies")
(def uri "datomic:dev://localhost:4334/mbrainz-1968-1973")
(def conn (d/connect uri))
(def db (d/db conn))

;q03 databasedeki release date'i 1975 olan bütün şarkıların isimlerini döndürelim.
(d/q '[:find ?year ?title
       :in $ ?date
       :where
       [?date :release/year ?year]
       [(= ?year ?date)]
       [_ :track/name ?title]]
  db 1970)
;=> #{}

;bir önceli örnekte ?date değişkenin birden fazla yerde kullanıldığını görüyoruz, bu hata sebebiyle çalışmadı
(take 5 (d/q '[:find ?year ?title
               :in $ ?date
               :where
               [_ :release/year ?year]
               [(= ?year ?date)]
               [_ :track/name ?title]]
          db 1970))
;=> ([1970 "You're Everything"] [1970 "Clyde"] [1970 "Whisper"] [1970 "The Ringmaster"] [1970 "Sidewalk Cafe"])

; aşağıdaki sorgu bitmiyor (çok uzun sürüyor)
#_(take 1 (d/q '[:find ?year ?title ?arts
                 :in $ ?date
                 :where
                 [?d :release/year ?year]
                 [(= ?year ?date)]
                 [_ :track/name ?title]
                 [_ :track/artists ?arts]]
            db 1970))

; yukarıdaki çalışmayan sorgumuzun üzerinde sadeleştirme yaparak deneyelim.
(take 1 (d/q '[:find ?year ?arts
               :in $ ?date
               :where
               [?d :release/year ?year]
               [(= ?year ?date)]
               [_ :track/artists ?arts]]
          db 1970))
;=> ([1970 804842511544756])

; :track/artists yerine :track/name i sorgulayalım.
(take 1 (d/q '[:find ?year ?title
               :in $ ?date
               :where
               [?d :release/year ?year]
               [(= ?year ?date)]
               [_ :track/name ?title]]
          db 1970))
;=> ([1970 "You're Everything"])

; bu ikisi birlikte olunca sorgu bitmiyor
(take 1 (d/q '[:find ?title ?arts
               :where
               [_ :track/name ?title]
               [_ :track/artists ?arts]]
          db))

; hipotez: kartezyen çarpım yapıyor olabilir
; bunu test edelim.
(take 1 (d/q '[:find ?title ?arts
               :where
               [?t :track/name ?title]
               [?t :track/artists ?arts]]
          db))
;=> (["Shine on Me" 624522604589940])

(d/q '[:find (count ?t)
       :where
       [?t :track/name ?title]
       [?t :track/artists ?arts]]
  db)
;=> [[98502]]

(d/q '[:find (count ?title)
       :where
       [_ :track/name ?title]
       [_ :track/artists ?arts]]
  db)
; Bu sorgu da bitmiyor.
; Bu sorgunun bitmeyişinin nedeni count fonksiyonunun çalışabilmesi için ?title result setinin tamamen bitmiş olması şart
; Bu result set de muhtemelen 10^5^2 = 10^10 yani 10 milyar kayıt içerdiğinden çok uzun sürüyor olabilir.

; Bu varsayımımızı test etmek için küçük bir data set üzerinde deney yapalım.

; q: Üstteki sorgunun çalışmasının sebebi ?t olunca bir limitleme mi oluyor otomatikman?
; Muhtemelen hayır. Fakat şöyle bir şey oluyor olabilir:
; _ kullanınca, ?title ve ?arts kümeleri diye iki tane ara result set üretiliyor.
; Bu iki kümenin kartezyen çarpımını alıyor.
; Fakat ?t olunca, result set ?t'nin kendisi oluyor. Tek bir tane result set kümemiz oluyor.
; Öbüründe iki tane birbirinin aynı büyüklte küme var.
; İlkindeyse tek bir tane bir küme var.
; Dolayısıyla o iki kümenin kartezyen çarpımını alıyor.

; Küçük bir data set üzerinde deney yapalım.
; Hatırladığım kadarıyla datomic db parametreleri illa datomicten gelmesi şart değil.
; Kendi db'ni kendin oluşturabilirsin.
(def mindb
  [{:track/name "a" :track/artists {:artist/name "a1"}}
   {:track/name "b" :track/artists {:artist/name "b1"}}
   {:track/name "c" :track/artists {:artist/name "c1"}}])
(d/q '[:find (count ?t)
       :where
       [?t :track/name ?title]
       [?t :track/artists ?arts]]
  mindb)
; Çalışmadı. Muhtemelen datomic'te çalışmıyor. Belki datascript çalıştırıyordu.

; O zaman verileri datomic'e kaydedip sorgulama yapalım.
; Fakat datomic'te yeni schema tanımlamam gerekecek önce.
; Bu zahmete girmemek için datascript ile yapalım.
(require '[datascript.core :as ds])
(def connds (ds/create-conn))
(ds/transact! connds mindb)

(ds/q '[:find (count ?t)
        :where
        [?t :track/name ?title]
        [?t :track/artists ?arts]]
  mindb)
; Doğrudan mindb data seti burada da çalışmadı. Demek ki veritabanı üzerinden sorgu yapmak gerekiyor.

(ds/q '[:find ?title ?arts
        :where
        [?t :track/name ?title]
        [?t :track/artists ?arts]]
  @connds)
;=> #{["b" #:artist{:name "b1"}] ["c" #:artist{:name "c1"}] ["a" #:artist{:name "a1"}]}
; Burada veriyi düz bir şekilde getiriyor. Kartezyen çarpım yok.

; Underscore ile deneyelim
(ds/q '[:find ?title ?arts
        :where
        [_ :track/name ?title]
        [_ :track/artists ?arts]]
  @connds)
;=>
;#{["b" #:artist{:name "b1"}]
;  ["c" #:artist{:name "c1"}]
;  ["b" #:artist{:name "a1"}]
;  ["c" #:artist{:name "b1"}]
;  ["b" #:artist{:name "c1"}]
;  ["a" #:artist{:name "b1"}]
;  ["a" #:artist{:name "c1"}]
;  ["c" #:artist{:name "a1"}]
;  ["a" #:artist{:name "a1"}]}
; Kartezyen çarpım yapıyor.

(d/q '[:find (count ?title)
       :where
       [_ :track/name ?title]
       [_ :track/artists ?arts]]
  db)
; Açıklama: Demek ki, buradaki sorguda, iki sorgu cümleciği arasında herhangi bir join bulunmadığından,
; iki tane oluşan result set (küme) arasında kartezyen çarpım yapılıyor.
; Bu da 10^10 satırdan oluşan bir küme oluşturuyor. O yüzden aşırı uzun sürüyor.