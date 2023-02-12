(ns fn.reduced)

; [reduced - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/reduced)

;; Suppose you want to short-circuit a sum like:
(reduce (fn [a v] (+ a v)) (range 10))
;;=> 45

;; So that it returns the sum of the integers if less than 100:
(reduce (fn [a v] (if (< a 100) (+ a v) (reduced :big))) (range 10))
;;=> 45

;; But the keyword :big otherwise:
(reduce (fn [a v] (if (< a 100) (+ a v) (reduced :big))) (range 20))
;;=> :big
