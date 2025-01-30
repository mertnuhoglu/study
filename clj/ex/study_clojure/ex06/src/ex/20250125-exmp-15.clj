(ns ex.20250125_exmp_15)
;; [[20250125-exmp-15.clj]]

(not-empty [1])  ; [1]
(not-empty [])  ; nil

(some even? [1 2 3]) ; true
(some #{:a} [:a :b :c]) ; :a

(seq? 1) ; false
(seq? {}) ; false
(seq? [1 2])  ; false
(seq? (seq [1 2])) ; true
(seq? (seq [])) ; false

(every? even? [1 2 3]) ; false
(every? even? [0 2 4]) ; true
(every? #{1 2} [1 2 1]) ; true

(not-every? even? [1 2 3]) ; true

(not-any? even? [1 2 3]) ; false

(empty? ()) ; true
