(ns ex.20250125_exmp_13)
;; [[20250125-exmp-13.clj]]

(zipmap [:a :b] [1 2]) ; {:a 1, :b 2}
(into [] {1 2, 3 4})   ; [[1 2] [3 4]]
(reduce + 1 [2 3]) ; 6

(into-array (range 3))  ; [0, 1, 2]

(to-array-2d [[1 2 3] [4 5 6]]) ; [[1, 2, 3], [4, 5, 6]]

(frequencies [:a :b :a]) ; {:a 2, :b 1}

(group-by odd? (range 5)) ; {false [0 2 4], true [1 3]}
