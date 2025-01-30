(ns ex.20250125_exmp_08)
;; [[20250125-exmp-08.clj]]

(flatten [1 [2]])  ; (1 2)
(reverse [1 2]) ; (2 1)

(sort [3 1 2]) ; (1 2 3)
(sort > [3 1 2]) ; (3 2 1)

(sort-by count ["aaa" "bb"]) ; ("bb" "aaa")
(shuffle [1 2 3]) ; [2 3 [[20250125-exmp-]]
