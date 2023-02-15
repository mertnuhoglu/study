(ns fn.aget)

; (aget array idx)
; (aget array idx & idxs)
; Returns the value at the index/indices. Works on Java arrays of all
; types.

; not: java array kullanımı tavsiye edilmiyor:
; [Arrays and loops in Clojure - Stack Overflow](https://stackoverflow.com/questions/50157387/arrays-and-loops-in-clojure)
; Those docs list some functions for working with Java arrays primarily when they're required for Java interop or "to support mutation or higher performance operations". In nearly all cases Clojurists just use a vector.

;; create two arrays
(def a1 (double-array '(1.0 2.0 3.0 4.0)))
;;=> #'user/a1
(def a2 (int-array '(9 8 7 6)))
;;=> #'user/a2

;; get an item by index
(aget a1 2)
;;=> 3.0
(aget a2 3)
;;=> 6

