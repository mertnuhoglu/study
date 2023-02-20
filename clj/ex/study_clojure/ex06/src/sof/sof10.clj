(ns sof.sof10)

; Barış'la Clojure Veri Analizi Çalışmaları
; Tarih: 20230220
; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; [hashmap - clojure convert lazy-seq to hash map - Stack Overflow](https://stackoverflow.com/questions/7034685/clojure-convert-lazy-seq-to-hash-map)

; How do I create a map from a lazySeq?

(def fields [:name :age :color])
(def values ["joe" 32 "red"])
(def record (interleave fields values))
(identity record)
; (:name "joe" :age 32 :color "red")
; bu seq'i map'e çevirmek istiyoruz

;; (get mymap :age)
;; 32

; a01:

(apply hash-map record)
; {:age 32, :color "red", :name "joe"}

; a02: Since you actually already have separate keys and values, I would suggest skipping the interleave step and instead writing

(zipmap fields values)
; {:name "joe", :age 32, :color "red"}
; not: burada önden interleave ile birleştirmeye gerek kalmamış

; Or if you have your heart set on into, you could

(into {} (map vector fields values))
; {:name "joe", :age 32, :color "red"}

(comment
  (map vector fields values)
  ;=> ([:name "joe"] [:age 32] [:color "red"])

  ; vector yerine hash-map ile kullansaydık ne olurdu?
  (map hash-map fields values)
  ;=> ({:name "joe"} {:age 32} {:color "red"})
  (hash-map :name "joe")
  ;=> {:name "joe"}
  (apply merge (map hash-map fields values))
  ;=> {:age 32, :color "red", :name "joe"}

  ,)

; a03: This isn't sensible at all, but since the original question wanted to use into with record:

(into {} (map vec (partition 2 record)))
; {:name "joe", :age 32, :color "red"}

