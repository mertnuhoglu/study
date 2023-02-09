(ns sof.e02)

; Barış'la Clojure Veri Analizi Çalışmaları
; Tarih: 20230209
; rfr: video/20230209-mert-clj-egzersiz-27.mp4

; [Clojure: reduce vs. apply - Stack Overflow](https://stackoverflow.com/questions/3153396/clojure-reduce-vs-apply)

; apply fonksiyonunun örnekleri için:
; rfr: ex06/src/brian_will_collections.clj

(reduce + (list 1 2 3 4 5))
; translates to: (+ (+ (+ (+ 1 2) 3) 4) 5)

(apply + (list 1 2 3 4 5))
; translates to: (+ 1 2 3 4 5)


; [Can someone explain Clojure Transducers to me in Simple terms? - Stack Overflow](https://stackoverflow.com/questions/26317325/can-someone-explain-clojure-transducers-to-me-in-simple-terms)

; Prior to version 1.7 of Clojure you had three ways to write dataflow queries:

; a01: nested calls

(reduce + (filter odd? (map #(+ 2 %) (range 0 10)))) ; 35

; a02: functional composition

(def xform
  (comp
    (partial filter odd?)
    (partial map #(+ 2 %))))
(reduce + (xform (range 0 10))) ; 35

; a03: threading macro

(defn xform [xs]
  (->> xs
       (map #(+ 2 %))
       (filter odd?)))
(reduce + (xform (range 0 10))) ; 35

; With transducers you will write it like:

(def xform
  (comp
    (map #(+ 2 %))
    (filter odd?)))
(transduce xform + (range 0 10)) ; 35

; The order of combinators is like you write it with threading macro (natural order)

; Compared to composing calls to the old map, filter, reduce etc. you get better performance because you don't need to build intermediate collections between each step, and repeatedly walk those collections.


