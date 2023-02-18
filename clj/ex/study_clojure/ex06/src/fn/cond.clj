(ns fn.cond)

; rfr: video/20230218-mert-clj-egzersiz-43.mp4

; (cond & clauses)
; Takes a set of test/expr pairs. It evaluates each test one at a
; time.  If a test returns logical true, cond evaluates and returns
; the value of the corresponding expr and doesn't evaluate any of the
; other tests or exprs.
; (cond) returns nil.
;

; switch-case statement'ına denk gelir

; [cond - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/cond)

(let [grade 85]
  (cond
    (>= grade 90) "A"
    (>= grade 80) "B"
    (>= grade 70) "C"
    (>= grade 60) "D"
    :else "F"))
;"B"

(defn pos-neg-or-zero
  "Determines whether or not n is positive, negative, or zero"
  [n]
  (cond
    (< n 0) "negative"
    (> n 0) "positive"
    :else "zero"))

(pos-neg-or-zero 5)
;"positive"
(pos-neg-or-zero -1)
;"negative"
(pos-neg-or-zero 0)
;"zero"
