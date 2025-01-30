(ns ex.20250125_exmp_10)
;; [[20250125-exmp-10.clj]]

(map inc [1 2]) ; (2 3)
(map + [1 2] [3 4]) ; (4 6)

(pmap inc [1 2]) ; (2 3)

(replace {:k1 :a, :k2 :b} [:k2 :k3]) ; [:b :k3]
(replace [10 9 8 7 6] [0 2 4]) ; [10 8 6]
(replace {2 :a, 4 :b} [1 2 3 4]) ; [1 :a 3 :b]
(replace {2 :a, 4 :b} [4 2]) ; [:b :a]
(replace {2 :a, 4 :b} [3 2]) ; [3 :a]

(reductions + [1 2 3]) ; (1 3 6)

(map-indexed vector [1 2 3])  ; ([0 1] [1 2] [2 3])
