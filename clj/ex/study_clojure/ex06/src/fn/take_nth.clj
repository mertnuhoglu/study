(ns fn.take-nth)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; (take-nth n)
; (take-nth n coll)
; Returns a lazy seq of every nth item in coll.  Returns a stateful
; transducer when no collection is provided.
;

; nth: n'inci
; coll'ın her n'inci öğesini alır

; ilk öğe: n * 0
; ikinci öğe: n * 1
; üçüncü öğe: n * 2

(take-nth 2 (range 10))
;(0 2 4 6 8)

;; N <= 0 is a special case
(take 3 (take-nth 0 (range 2)))
;;=> (0 0 0)


