(ns fn.as->)

; (as-> expr name & forms)
; Binds name to expr, evaluates the first form in the lexical context
; of that binding, then binds name to that result, repeating for each
; successive form, returning the result of the last form.
;

; -> ve ->> içinde arg ya en başta ya en sona ekleniyor
; as-> ile data argümanı istediğin yere koyabilirsin.

; [Learn clojure in Y Minutes](https://learnxinyminutes.com/docs/clojure/)
; When you are in a situation where you want more freedom as where to
; put the result of previous data transformations in an
; expression, you can use the as-> macro. With it, you can assign a
; specific name to transformations' output and use it as a
; placeholder in your chained expressions:

(as-> [1 2 3] input
  (map inc input);=> You can use last transform's output at the last position
  (nth input 2) ;=>  and at the second position, in the same expression
  (conj [4 5 6] input 8 9 10)) ;=> or in the middle !
; [4 5 6 4 8 9 10]

