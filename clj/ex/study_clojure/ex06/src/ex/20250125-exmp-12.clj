(ns ex.20250125_exmp_12)
;; [[20250125-exmp-12.clj]]

(ffirst {:b 2 :a 1 :c 3}) ; :b
(nfirst {:b 2 :a 1 :c 3}) ; (2)
(second {:b 2 :a 1 :c 3})  ; [:a 1]
(nth [1 2 3] 1) ; 2
(when-first [a [1 2]] a) ; 1
(when-first [a []] :x) ; nil
(when-first [a nil] a) ; nil
(last [1 2 3]) ; 3
(rand-nth [1 2 3]) ; 2
