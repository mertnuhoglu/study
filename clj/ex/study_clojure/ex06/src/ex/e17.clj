(ns ex.e17)

; Tarih: 20230403

; D05 problemini rich comment formunda debug etmek

;Girdi:
;
;```clojure
;[1 {:id 1 :name "ali" :surname "veli"}
; 2 {:id 2 :name "batu" :surname "can"}]
;```
;
;Çıktı:
;Task 5 <---
;```clojure
;[[1 "ali" "veli"]
; [2 "batu" "can"]]

(def v
  [1 {:id 1 :name "ali" :surname "veli"}
   2 {:id 2 :name "batu" :surname "can"}])

; en dış koleksiyonu map'e dönüştürür.
(defn transform-outer-coll-to-map [coll]
  (apply hash-map coll))

(def m (transform-outer-coll-to-map v))
(identity m)
;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

(mapv
  (fn [data] [(:id data) (:name data) (:surname data)])
  (for [len (range 1 (+ 1 (count m)))]
    (m len)))

;=> [[1 "ali" "veli"] [2 "batu" "can"]]

(comment
  (def coll (for [len (range 1 (+ 1 (count m)))]
              (m len)))
  (identity coll)
  ;=> ({:id 1, :name "ali", :surname "veli"} {:id 2, :name "batu", :surname "can"})

  (mapv
    (fn [data] [(:id data) (:name data) (:surname data)])
    (for [len (range 1 (+ 1 (count m)))]
      (m len)))
  ;=> [[1 "ali" "veli"] [2 "batu" "can"]]
  ; = denktir:
  (mapv
    (fn [data] [(:id data) (:name data) (:surname data)])
    coll)
  ;=> [[1 "ali" "veli"] [2 "batu" "can"]]

  (def f (fn [data] [(:id data) (:name data) (:surname data)]))

  ; map'in iterasyonları
  ; 1. tur:
  (f {:id 1, :name "ali", :surname "veli"})
  ;=> [1 "ali" "veli"]
  (f {:id 2, :name "batu", :surname "can"})
  ;=> [2 "batu" "can"]

  ; end
  ,)
