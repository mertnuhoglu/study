(ns macro)

tmacroexpand '(-> c (+ 3) (* 2))
; (* (+ c 3) 2)
