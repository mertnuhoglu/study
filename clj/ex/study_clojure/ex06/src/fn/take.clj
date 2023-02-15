(ns fn.take)

; (take n)
; (take n coll)
; Returns a lazy sequence of the first n items in coll, or all items if
; there are fewer than n.  Returns a stateful transducer when
; no collection is provided.

;; return a lazy seq of the first 3 items
(take 3 '(1 2 3 4 5 6))
;;=> (1 2 3)

(take 3 [1 2 3 4 5 6])
;;=> (1 2 3)

;; returns all items if there are fewer than n
(take 3 [1 2])
;;=> (1 2)

(take 1 [])
;;=> ()

(take 1 nil)
;;=> ()

(take 0 [1])
;;=> ()

(take -1 [1])
;;=> ()

'(1 2 nil)
;=> (1 2 nil)

(take 3 '(1 2 nil))
;=> (1 2 nil)
