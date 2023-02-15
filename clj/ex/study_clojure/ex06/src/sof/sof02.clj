(ns sof.sof02)

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

