(ns ex.20250125_exmp_18)
;; [[20250125-exmp-18.clj]]

(def p (promise))
(realized? p) ; false
(deliver p 42) ; #<Promise@42ffc2a4: 42>
(realized? p) ; true
(deref p) ; 42
