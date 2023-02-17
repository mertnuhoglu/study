(ns fn.class)

; (class x)
; Returns the Class of x
;

; [class - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/class)
; [Learn clojure in Y Minutes](https://learnxinyminutes.com/docs/clojure/)

; Clojure uses Java's object types for booleans, strings and numbers.
; Use `class` to inspect them.
(class 1)
;=> java.lang.Long
(class 1.)
;=> java.lang.Double
(class "")
;=> java.lang.String
(class false)
;=> java.lang.Boolean
(class nil)
;=> nil

