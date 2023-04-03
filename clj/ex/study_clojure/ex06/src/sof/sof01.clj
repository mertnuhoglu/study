(ns sof.sof01)

; Barış'la Clojure Veri Analizi Çalışmaları
; Tarih: 20230208
; rfr: video/20230208-mert-clj-egzersiz-25.mp4

; [Is there an equivalent for the Zip function in Clojure Core or Contrib? - Stack Overflow](https://stackoverflow.com/questions/2588227/is-there-an-equivalent-for-the-zip-function-in-clojure-core-or-contrib)

; Goal:
;> (zip '(1 2 3) '(4 5 6))
;((1 4) (2 5) (3 6))

; a01: map ile
(map vector '(1 2 3) '(4 5 6)) 
; ([1 4] [2 5] [3 6])

; neden?
(vector 1 4) ; [1 4]
(vector 2 5) ; [2 5]
(vector 3 6) ; [3 6]

; a02: interleave ile
(interleave [1 2 3 4] [5 6 7 8])
; (1 5 2 6 3 7 4 8)
(partition 2 (interleave [1 2 3 4] [5 6 7 8]))  
; ((1 5) (2 6) (3 7) (4 8))

; a03: interleave ve genel
(interleave [1 2 3] [5 6 7] [8 9 10]) ; (1 5 8 2 6 9 3 7 10)
(partition 3 (interleave [1 2 3] [5 6 7] [8 9 10])) ; ((1 5 8) (2 6 9) (3 7 10))

(defn zip [& colls]
  (partition (count colls) (apply interleave colls)))

(zip [1 2 3] [5 6 7] [8 9 10]) ; ((1 5 8) (2 6 9) (3 7 10))

; q: & Ampersand sembolü ne işe yarar?

(defn g [a & b]
  [a b])
(g 1)  ; [1 nil]
(g 1 2) ; [1 (2)]
(g 1 2 3) ; [1 (2 3)]
(g 1 2 3 4) ; [1 (2 3 4)]
; Demek ki, & sonrası argüman, ilk argümandan sonra gelen tüm argümanları bir liste olarak topluyor.
; FP dillerinde buna rest argument operator

; Yukarıdaki örneği ele alalım:
(defn zip [& colls]
  (partition (count colls) (apply interleave colls)))

(zip [1 2 3] [5 6 7] [8 9 10]) ; ((1 5 8) (2 6 9) (3 7 10))
; Burada zip fonksiyonuna verilen 3 arg colls değişkeni içine bir liste olarak konuyor
(def colls '([1 2 3] [5 6 7] [8 9 10]))

(partition (count colls) (apply interleave colls)) ; ((1 5 8) (2 6 9) (3 7 10))

; Bu & ile yazılan fonksiyonlara: Multi-Arity and Variadic functions
; Multi-Arity: çok argümanlı
; Variadic: değişken sayıda
; Yani değişken sayıda çok argümanlı fonksiyonlar bunlar

; Dikkat edersek yukarıdaki g fonksiyonuna ister 1 arg ister 4 arg ister n arg vereyim. Her türlü çalışır.

; & % # vs. sembollerin isimlerini de öğrenmek işimize çok yarar.
; Bu sembollerin birden çok ismi bulunur.
; En yaygın isimlerini öğrenmek için:
; [ASCII Pronunciation Rules for Programmers](https://blog.codinghorror.com/ascii-pronunciation-rules-for-programmers/)
