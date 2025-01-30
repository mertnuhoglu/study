(ns ex.20250125_exmp_06)
;; [[20250125-exmp-06.clj]]

(rest [1 2]) ; (2)
(rest [1]) ; ()
(rest nil) ; ()
(rest []) ; ()

(next [1 2]) ; (2)
(next (next [1 2])) ; nil
(next (next (next [1 2]))) ; nil

(fnext [1 2]) ; 2
(nnext [1 2 3]) ; (3)

(drop 2 [1 2 3 4]) ; (3 4)
(drop-while odd? [1 2 3 4]) ; (2 3 4)
(drop-while odd? [1 5 2 3 4]) ; (2 3 4)
(drop-while odd? [4 2 3 4]) ; (4 2 3 4)

(nthnext [1 2 3] 2) ; (3)
