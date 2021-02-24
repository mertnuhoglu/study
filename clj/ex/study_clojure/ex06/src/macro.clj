(ns macro)

(macroexpand '(-> c (+ 3) (* 2)))
; (* (+ c 3) 2)
