(ns fn.take)

; rfr: video/20230223-mert-clj-egzersiz-50.mp4
; rfr: fn/take.clj
; rfr: fn/take-last.clj
; rfr: fn/drop.clj
; rfr: fn/drop-last.clj

; (take n)     #nclk/çok-önemli
; (take n coll)
; Returns a lazy sequence of the first n items in coll, or all items if
; there are fewer than n.  Returns a stateful transducer when
; no collection is provided.

(def a (take 10 (iterate inc 1)))
(identity a)
;=> (1 2 3 4 5 6 7 8 9 10)

(take 3 a) ; başta 3 öğeyi al
;=> (1 2 3)
(drop 3 a) ; baştan 3 öğeyi sil
;=> (4 5 6 7 8 9 10)
(drop-last 3 a) ; sondan 3 öğeyi sil
;=> (1 2 3 4 5 6 7)
(take-last 3 a) ; sondan 3 öğeyi al
;=> (8 9 10)

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
