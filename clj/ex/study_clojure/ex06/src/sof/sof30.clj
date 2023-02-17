(ns sof.sof30)

; [In clojure, how to apply 'and' to a list? - Stack Overflow](https://stackoverflow.com/questions/9218044/in-clojure-how-to-apply-and-to-a-list)

; In closure, how can we apply and or any other macro to a list?
; This doesn't work:

#_(apply and '(true false))

;Because apply can't take value of a macro.

; a01: every? identity
(every? identity '(true false))
;=> false

; a02: apply #(macro)

; macros are not first class things they don't compose quite like functions,
; and you cant pass them to other functions,
; and you can't apply them.
; This is because they are finished and done with before any of the applying would be done.
; It is customary to wrap macros in functions to apply or pass them to functions

;(defmacro my-macro [x y z] ...)
;
;(apply #(my-macro %1 %2 %3) [1 2 3])
;
;(map #(my-macro) [1 2 3] [:a :b :c] [a b c])

(apply #(and %1 %2) '(true false))
;=> false

; a03: apply = true

(apply = true '(true false))
;=> false

; a04:

(eval `(and ~@(list true false)))
;=> false

(eval `(and ~@ (list true true 1)))
;=> 1
