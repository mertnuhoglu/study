(ns ex.20250125_exmp_21)
;; [[20250125-exmp-21.clj]]

(take 3 (repeat "a")) ; ("a" "a" "a")
(repeat 3 "b") ; ("b" "b" "b")

(range 3) ; (0 1 2)
(range -2 2) ; (-2 -1 0 1)
(range -2 2 2) ; (-2 0)
