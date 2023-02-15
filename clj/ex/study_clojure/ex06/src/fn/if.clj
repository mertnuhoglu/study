(ns fn.if)

; rfr: video/20230215-mert-clj-egzersiz-38.mp4

;if (special form)  #nclk/çok-önemli
;(if test then else?)
;Evaluates test.
;If not the singular values nil or false, evaluates and yields then, otherwise, evaluates and yields else.
;If else is not supplied it defaults to nil.

; [Clojure - Special Forms](https://clojure.org/reference/special_forms)
(if true 3 1)
;=> 3

(if (< 3 1) :a :b)
;=> :b

(if (< 3 1) (+ 1 2) (* 3 5))
;=> 15
