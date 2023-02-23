(ns sof.sof26)

; rfr: video/20230222-mert-clj-egzersiz-48.mp4
; rfr: video/20230222-mert-clj-egzersiz-49.mp4

; [Find index of an element matching a predicate in Clojure? - Stack Overflow](https://stackoverflow.com/questions/8641305/find-index-of-an-element-matching-a-predicate-in-clojure)

;With Clojure, how do I find the first index with a positive value in this vector [-1 0 3 7 9]?

(first (filter pos? [-1 0 99 100 101]))
;=> 99
;This code returns the value 99. The answer I want is the index which is 2.

(comment
  (filter pos? [-1 0 99 100 101])
  ;=> (99 100 101)
  ; pozitif sayıları filtreledik
  (first '(99 100 101))
  ;=> 99
  ,)

; rfr: video/20230222-mert-clj-egzersiz-49.mp4

; a01: keep-indexed
(defn indices [pred coll]
  (keep-indexed #(when (pred %2) %1) coll))

(first (indices pos? [-1 0 99 100 101]))
;2

(comment
  (def pred pos?)
  (def f #(when (pred %2) %1))
  ; 1. tur
  (f 0 -1)
  ;=> nil
  ; 2. tur
  (f 1 0)
  ;=> nil
  ; 3. tur
  (f 2 99)
  ;=> 2

  ,)

; a02: map-indexed
(defn index-of-pred
  [pred coll]
  (ffirst (filter (comp pred second) (map-indexed list coll))))

(index-of-pred pos? [-1 -2 -5 0 3 4 1 -100])
;=> 4

(comment
  (def coll [-1 -2 -5 0 3 4 1 -100])
  (def pred pos?)
  (map-indexed list coll)
  ;=> ((0 -1) (1 -2) (2 -5) (3 0) (4 3) (5 4) (6 1) (7 -100))
  (def f (comp pred second))
  ; pred = pos? olduğundan f fonksiyonu kendisine verilen ikilinin ikinci öğesini alıyor ve pos? olup olmadığına bakıyor
  ; filter'ın 1. turu:
  (f '(0 -1))
  ;=> false
  ; 2. tur:
  (f '(1 -2))
  ;=> false
  ; 3. tur:
  (f '(2 -5))
  ;=> false
  ; 5. tur:
  (f '(4 3))
  ;=> true
  ; filter f'in true döndüğü öğeleri dönüyordu dolayısıyla (4 3)'ü dönecek
  (filter (comp pred second) (map-indexed list coll))
  ;=> ((4 3) (5 4) (6 1))
  (first '((4 3) (5 4) (6 1)))
  ;=> (4 3)
  (ffirst '((4 3) (5 4) (6 1)))
  ;=> 4
  ,)

; kodu aklımızda debug ederken, indentation (içeri doğru hizalayarak) kodu yazarsak, daha kolay aklımızda canlandırabiliriz
(ffirst (filter (comp pred second) (map-indexed list coll)))
; ≣
(ffirst
  (filter
    (comp pred second)
    (map-indexed list coll)))


