(ns ex.20250125_exmp_09)
;; [[20250125-exmp-09.clj]]

(split-at 2 (range 5)) ; [(0 1) (2 3 4)]
(take 2 (range 5)) ; (0 1)
(drop 2 (range 5)) ; (2 3 4)

(splitv-at 2 (range 5))  ; [[0 1] (2 3 4)]

; [Gemini](https://gemini.google.com/app/a50afe91806fd754)
;; split-at: Returns two lazy sequences.
;; splitv-at: Returns two vectors.

(split-at 3 (range 5)) ; [(0 1 2) (3 4)]
(splitv-at 3 (range 5))  ; [[0 1 2] (3 4)]

(split-with odd? [1 3 5 6 7 9]) ; [(1 3 5) (6 7 9)]
(split-with (partial > 2) [1 2 3])  ; [(1) (2 3)]

(partition 2 (range 5)) ; ((0 1) (2 3))
(partition 2 5 (range 10))  ; ((0 1) (5 6))
(partition 2 1 (range 4)) ; ((0 1) (1 2) (2 3))
(partition 2 2 "a" (range 5)) ; ((0 1) (2 3) (4 \a))

(partition-all 2 (range 5)) ; ((0 1) (2 3) (4))

(partition-by even? (range 5)) ; ((0) (1) (2) (3) (4))
(partition-by even? [1 1 2 3 3]) ; ((1 1) (2) (3 3))
