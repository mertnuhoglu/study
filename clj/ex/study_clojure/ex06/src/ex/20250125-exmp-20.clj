(ns ex.20250125_exmp_20)
;; [[20250125-exmp-20.clj]]

(defn pos-nums
  ([] (pos-nums 1))
  ([n] (lazy-seq (cons n (pos-nums (inc n))))))

(take 3 (pos-nums)) ; (1 2 3)

(repeatedly 3 #(rand-int 11)) ; (2 3 10)
(repeat 3 (rand-int 11)) ; (4 4 4)

(take 3 (iterate inc 10)) ; (10 11 12)
