(ns sof.sof26)

; [Find index of an element matching a predicate in Clojure? - Stack Overflow](https://stackoverflow.com/questions/8641305/find-index-of-an-element-matching-a-predicate-in-clojure)

;With Clojure, how do I find the first index with a positive value in this vector [-1 0 3 7 9]?

(first (filter pos? [-1 0 99 100 101]))
;=> 99
;This code returns the value 99. The answer I want is the index which is 2.

; a01: keep-indexed
(defn indices [pred coll]
  (keep-indexed #(when (pred %2) %1) coll))

(first (indices pos? [-1 0 99 100 101]))
;2

; a02: map-indexed
(defn index-of-pred
  [pred coll]
  (ffirst (filter (comp pred second) (map-indexed list coll))))

(index-of-pred pos? [-1 -2 -5 0 3 4 1 -100])
;=> 4



