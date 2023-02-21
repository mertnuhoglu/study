(ns fn.doseq)

; rfr: video/20230221-mert-clj-egzersiz-47.mp4
; rfr: fn/for.clj

; (doseq seq-exprs & body)
; Repeatedly executes body
; (presumably for side-effects) with
; bindings and filtering as provided by "for".  Does not retain
; the head of the sequence. Returns nil.
;

; rfr: sof20.clj

(doseq [i (range 4)]
  (println "i=" (+ i 1)))
;i= 1
;i= 2
;i= 3
;i= 4
;=> nil

;; Multiple sequences results in a Cartesian cross of their values.
(doseq [a [1 2]
        b [3 4]]
  (println a b))
;1 3
;1 4
;2 3
;2 4
;nil
; içiçe iki tane loop gibi çalıştı