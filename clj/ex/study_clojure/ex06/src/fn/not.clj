(ns fn.not)

; rfr: video/20230218-mert-clj-egzersiz-43.mp4
; rfr: fn/complement.clj

; (not x)
; Returns true if x is logical false, false otherwise.
;

(not true)
;false
(not false)
;true
(not nil)
;true

;; acts as complement of `boolean`
(boolean "a string")
;true
(not "a string")
;false
(boolean 1)
;true
(not 1)
;false
