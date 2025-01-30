(ns ex.20250125_exmp_19)
;; [[20250125-exmp-19.clj]]

(seq [1]) ; (1)
(seq "ali") ; (\a \l \i)
(seq []) ; nil
(every? seq [[1] {:a 2}]) ; true
(seq {:a 1 :b 2})  ; ([:a 1] [:b 2])

(vals {:a 1 :b 2}) ; (1 2)
(keys {:a 1 :b 2}) ; (:a :b)

(rseq (vec (range 3))) ; (2 1 0)

(subseq (sorted-set 1 2 3) > 2) ; (3)
(rsubseq (sorted-set 1 2 3 4 5) > 2) ; (5 4 3)
