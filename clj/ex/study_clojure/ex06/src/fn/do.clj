(ns fn.do)

; Evaluates the expressions in order and returns the value of the last. If no
; expressions are supplied, returns nil. See http://clojure.org/special_forms
; for more information.
;

; [Learn clojure in Y Minutes](https://learnxinyminutes.com/docs/clojure/)

; Group statements together with do
(do
  (print "Hello")
  "result value")
;Hello
;=> "result value"

; [do - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/do)