(ns mert.e12)

; Barış'la Datomic Çalışmaları
; Tarih: 20230206
; rfr: video/20230206-mert-clj-egzersiz-19.mp4

; rfr: [Learn Datalog Today!](https://www.learndatalogtoday.org/chapter/6)

(require '[datomic.client.api :as d])
(use '[mert.e04 :only [conn] :as e04])
(require '[mert.e05 :as e05])
(def db (d/db conn))

; Konu: Transformation Functions

(defn multiply_by [factor1 factor2]
  (* factor1 factor2))
(d/q
  '[:find ?order ?size ?result
    :where
    [?order :order/size ?size]
    [(mert.e12/multiply_by ?size 10) ?result]]
  db)
;=> [[96757023244376 6 60] [92358976733271 4 40] [92358976733270 5 50]]
; Dikkat: `multiply_by` fonksiyonunun sonucunu ?result değişkeninin içine koyduk

; Burada transformasyon fonksiyonu scalar (primitif) bir değer döndü.
; Eğer transformasyon fonksiyonunun sonucu tuple, collection, veya relation ise o zaman binding yapmamız gerekir.

;| Binding Form | Binds      |
;|--------------|------------|
;| ?a           | scalar     |
;| [?a ?b]      | tuple      |
;| [?a …]       | collection |
;| [ [?a ?b ] ] | relation   |

; Tuple Bağlama (Binding):
(defn to_tuple [factor1 factor2]
  [(* factor1 factor2) (+ factor1 factor2)])
(d/q
  '[:find ?order ?size ?a1 ?a2
    :where
    [?order :order/size ?size]
    [(mert.e12/to_tuple ?size 10) [?a1 ?a2]]]
  db)
;=> [[92358976733271 4 40 14] [96757023244376 6 60 16] [92358976733270 5 50 15]]

; Collection Bağlama (Binding):
(defn to_coll [arg]
  (range arg))

(d/q
  '[:find ?order ?size ?xs
    :where
    [?order :order/size ?size]
    [(< ?size 5)]
    [(mert.e12/to_coll ?size) [?xs ...]]]
  db)
;=> [[92358976733271 4 3] [92358976733271 4 2] [92358976733271 4 1] [92358976733271 4 0]]

(d/q
  '[:find ?e ?product-name ?color ?product-price
    :in $ [[?product-name ?product-price]]
    :where
    [?e :product/name ?product-name]
    [?e :product/color ?color]]
  db [["Kalem" 120] ["Defter" 250]])

; Relation Bağlama (Binding):
(defn to_rel [arg]
  (take
    (mod arg 7)
    [[:a 1] [:b 2] [:c 3] [:d 4] [:e 5] [:f 6]]))
(to_rel 3)
;=> ([:a 1] [:b 2] [:c 3])

(d/q
  '[:find ?order ?size ?a ?b
    :where
    [?order :order/size ?size]
    [(< ?size 5)]
    [(mert.e12/to_rel ?size) [[?a ?b]]]]
  db)
;=> [[92358976733271 4 :a 1] [92358976733271 4 :c 3] [92358976733271 4 :b 2] [92358976733271 4 :d 4]]
