(ns fn.lazy-cat)

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

(lazy-cat [1 2 3] [4 5 6])
;(1 2 3 4 5 6)

(concat [1 2 3] [4 5 6])
;(1 2 3 4 5 6)
