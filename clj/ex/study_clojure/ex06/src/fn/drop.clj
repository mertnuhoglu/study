(ns fn.drop)

; rfr: video/20230223-mert-clj-egzersiz-50.mp4
; rfr: fn/take.clj
; rfr: fn/take-last.clj
; rfr: fn/drop.clj
; rfr: fn/drop-last.clj

; (drop n)
; (drop n coll)
; Returns a lazy sequence of all but the first n items in coll.
; Returns a stateful transducer when no collection is provided.
;

;; although negative (or zero) drop-item-counts are accepted they do nothing
(drop -1 [1 2 3 4])
;;=> (1 2 3 4)

(drop 0 [1 2 3 4])
;;=> (1 2 3 4)

(drop 2 [1 2 3 4])
;;=> (3 4)

;; dropping more items than are present is allowed, and all items are dropped.
(drop 5 [1 2 3 4])
;;=> ()