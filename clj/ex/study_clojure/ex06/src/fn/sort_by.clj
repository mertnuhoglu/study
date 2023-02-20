(ns fn.sort-by)

; (sort-by keyfn coll)
; (sort-by keyfn comp coll)
; Returns a sorted sequence of the items in coll, where the sort
; order is determined by comparing
; (keyfn item).  If no comparator is
; supplied, uses compare.  comparator must implement
; java.util.Comparator.  Guaranteed to be stable: equal elements will
; not be reordered.  If coll is a Java array, it will be modified.  To
; avoid this, sort a copy of the array.
;

(sort-by count ["aaa" "bb" "c"])
;("c" "bb" "aaa")

(sort-by first [[1 2] [2 2] [2 3]])
;([1 2] [2 2] [2 3])

(sort-by first > [[1 2] [2 2] [2 3]])
;([2 2] [2 3] [1 2])

(sort-by :rank [{:rank 2} {:rank 3} {:rank 1}])
;({:rank 1} {:rank 2} {:rank 3})

(def x [{:foo 2 :bar 11}
        {:bar 99 :foo 1}
        {:bar 55 :foo 2}
        {:foo 1 :bar 77}])

;sort by :foo, and where :foo is equal, sort by :bar
(sort-by (juxt :foo :bar) x)
;=>({:foo 1, :bar 77} {:bar 99, :foo 1} {:foo 2, :bar 11} {:bar 55, :foo 2})
