(ns fn.repeat)

; rfr: video/20230215-mert-clj-egzersiz-36.mp4

; (repeat x) #nclk/önemli
; (repeat n x)
; Returns a lazy
; (infinite!, or length n if supplied) sequence of xs.

(repeat 3 "foo")
;=> ("foo" "foo" "foo")

; take ve cycle'ın birlikte kullanımına denk gelir
(take 3 (cycle ["foo"]))
;=> ("foo" "foo" "foo")
