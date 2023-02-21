(ns fn.do)

; rfr: video/20230221-mert-clj-egzersiz-46.mp4

; do    #nclk/önemli
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

;; do is used to evaluate multiple expressions in order, usually for the
;; purpose of evaluating exprs that have side-effects (such as printing
;; or I/O).  do returns the value of its last expression.
;;
;; do w/o args returns nil.

(do
  (println "LOG: Computing...")
  (+ 1 1))
;LOG: Computing...
;2

(do)
;nil

(str (do (println "merhaba") "ali") " dostum")
;merhaba
;=> "ali dostum"

