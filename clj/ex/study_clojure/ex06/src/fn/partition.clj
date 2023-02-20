(ns fn.partition)

; (partition n coll)  #nclk/orta-seviye
; (partition n step coll)
; (partition n step pad coll)
; Returns a lazy sequence of lists of n items each, at offsets step
; apart. If step is not supplied, defaults to n, i.e. the partitions
; do not overlap. If a pad collection is supplied, use its elements as
; necessary to complete last partition upto n items. In case there are
; not enough padding elements, return a partition with less than n items.
;

(partition 2 [10 20 30 40])
;=> ((10 20) (30 40))

(partition 2 [10 20 30 40 50 60])
;=> ((10 20) (30 40) (50 60))

;; partition a list of 20 items into 5 (20/4) lists of 4 items
(partition 4 (range 20))
;;=> ((0 1 2 3) (4 5 6 7) (8 9 10 11) (12 13 14 15) (16 17 18 19))

;; partition a list of 22 items into 5 (20/4) lists of 4 items
;; the last two items do not make a complete partition and are dropped.
(partition 4 (range 22))
;;=> ((0 1 2 3) (4 5 6 7) (8 9 10 11) (12 13 14 15) (16 17 18 19))
