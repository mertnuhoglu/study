(ns ex.20250125_exmp_01)
;; [[20250125-exmp-01.clj]]

(+ 2 3) ; 5

(for [x [1 2 3]
      y [4 5 6]
      :when (even? (+ x y))]
  (* x y)) ; (5 8 12 15)

(def numbers [1 2 3 4 5 6])

