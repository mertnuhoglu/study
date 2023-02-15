(ns sof.sof05)

; Barış'la Clojure Veri Analizi Çalışmaları
; Tarih: 20230213
; rfr: video/20230213-mert-clj-egzersiz-34.mp4

; [How to Iterate over Map Keys and Values in Clojure? - Stack Overflow](https://stackoverflow.com/questions/6685916/how-to-iterate-over-map-keys-and-values-in-clojure)

; Map öğelerini dolaşmak için:
(seq {:foo 1 :bar 2}) ; ([:foo 1] [:bar 2])

(def s (seq {:foo 1 :bar 2}))
(for [t s] (second t))
;=> (1 2)
(for [t s] (first t))
;=> (:foo :bar)

; for ve destructuring ile map öğeleri işlenebilir:
(for [[k v] {1 2 3 4}] (+ k v))  ; (3 7)

(map (fn [[k v]] [k v]) {:a 1 :b 2}) ; ([:a 1] [:b 2])

; [clojure - Return first item in a map/list/sequence that satisfies a predicate - Stack Overflow](https://stackoverflow.com/questions/10192602/return-first-item-in-a-map-list-sequence-that-satisfies-a-predicate)

; a01
(defn find-first
  [f coll]
  (first (filter f coll)))

(find-first #(< % 3) [3 4 1 2]) ; 1

(comment ; rich-comment #nclk/çok-önemli
  ; doğrudan argümanları buraya gömebiliriz
  (filter #(< % 3) [3 4 1 2])
  ;=> (1 2)

  ; o argümanlara bir isim verip kodu direk kopyala yapıştır yapıp adım adım çağırabiliriz
  (def f #(< % 3))
  (def coll [3 4 1 2])
  (filter f coll)
  ;=> (1 2)
  ,)

; a02
(some #{1} [1 2 3 4]) ; 1
; How it works: #{1} is a set literal. A set is also a function evaluating to its arg if the arg is present in the set and to nil otherwise

(->> [nil nil nil nil 42 nil nil] (some identity)) ; 42
