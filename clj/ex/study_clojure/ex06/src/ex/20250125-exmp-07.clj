(ns ex.20250125_exmp_07)
;; [[20250125-exmp-07.clj]]

(take 2 [1 2 3]) ; (1 2)
(take-nth 2 (range 5))  ; (0 2 4)
(take-while neg? [-2 -1 0 -3]) ; (-2 -1)
(butlast [1 2 3]) ; (1 2)
(drop-last 2 [1 2 3]) ; (1)
