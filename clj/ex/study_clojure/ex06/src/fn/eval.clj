(ns fn.eval)

; (eval form)
; Evaluates the form data structure
; (not text!) and returns the result.

; [eval - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/eval)

(eval (read-string "(+ 1 2)"))
;=> 3
; rfr: fn/read-string.clj

(eval '(+ 1 2))
;=> 3

(def foo "(println [1 2 3])")
;#'user/*foo*

foo
;"(println [1 2 3])"

(eval foo)   ; Notice eval'ing a string does not work.
;"(println [1 2 3])"

(eval (read-string foo))
;[1 2 3]
;nil
