(ns fn.lazy-cat)

; rfr: video/20230223-mert-clj-egzersiz-52.mp4

; (lazy-cat & colls)
; Expands to code which yields a lazy sequence of the concatenation
; of the supplied colls.  Each coll expr is not evaluated until it is
; needed.
;
; (lazy-cat xs ys zs) ===
; (concat
; (lazy-seq xs)
; (lazy-seq ys)
; (lazy-seq zs))
;

; lazy-cat ile concat tamamen aynı şeyi döner
; aralarındaki tek fark: birisi lazy çalışır, öbürü eager (hemen)

(lazy-cat [1 2 3] [4 5 6])
;(1 2 3 4 5 6)

(concat [1 2 3] [4 5 6])
;(1 2 3 4 5 6)
