(ns ex.20250125_exmp_16)
;; [[20250125-exmp-16.clj]]

(some even? [1 2 3]) ; true
(some even? [1 3]) ; nil
(some #(when (even? %) %) [1 2 3]) ; 2
(some {:a 1 :b 2} [:a :c]) ; 1

(filter even? [1 2 3]) ; (2)
