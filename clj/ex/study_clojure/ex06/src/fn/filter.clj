(ns fn.filter)

; rfr: video/20230216-mert-clj-egzersiz-40.mp4

; (filter pred)
; (filter pred coll)
; Returns a lazy sequence of the items in coll for which
;
; (pred item) returns logical true. pred must be free of side-effects.
; Returns a transducer when no collection is provided.

(def m1 {:k1 :v1 :k2 :v2})

(def m2 {:k1 :v3 :k2 :v4})

(def Ms [m1 m2])

(filter
  (fn [kv] (= (:k1 kv) :v1))
  Ms)

(filter odd? [1 2 3])
;=> (1 3)

(comment
  ; dikkat: odd? fonksiyonu true/false döner
  ; ama filter true/false dönmez
  ; verdiğin coll'deki öğelerden bir kısmını döner
  (odd? 1)
  ;=> true
  (odd? 2)
  ;=> false
  (filter odd? [1 2 3])
  ;=> (1 3)
  ,)

(def m {:a 1 :b 2 :c nil})
(filter second m)
;=> ([:a 1] [:b 2])

(comment
  (second [:a 1])    ; -> truey
  ;=> 1
  (second [:b 2])    ; -> truey
  ;=> 2
  (second [:c nil])  ; -> falsy
  ;=> nil
  ; nil, falsy bir değerdir. clojure'un logical fonksiyonları için false değerine denktir

  (filter second m)
  ;=> ([:a 1] [:b 2])
  ,)

(filter
  (fn [v] (= 3 v))
  [3 5 7])
;=> (3)

(filter
  (fn [p] (:k1 p))
  {:k1 1 :k2 2})
;=> ()

(filter
  #(:k1 %)
  {:k1 1 :k2 2})
;=> ()

(filter
  #(= (:k1 %) 1)
  {:k1 1 :k2 2})
;=> ()

(filter #(> (second %) 100)
  {:a 1
   :b 2
   :c 101
   :d 102
   :e -1})
;;=> ([:c 101] [:d 102])

(filter #(= (second %) 1)
  {:a 1
   :b 2})
;=> ([:a 1])

(second [:a :b :c])
;=> :b

(filter
  #(= (second %) 1)
  {:k1 1 :k2 2})
;=> ([:k1 1])

(filter #(= (second %) 1)
  {:a 1
   :b 2
   :c 1})
;=> ([:a 1] [:c 1])

