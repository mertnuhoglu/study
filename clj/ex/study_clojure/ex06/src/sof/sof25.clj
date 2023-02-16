(ns sof.sof25)

; [How can I merge two sequences in clojure? - Stack Overflow](https://stackoverflow.com/questions/12658341/how-can-i-merge-two-sequences-in-clojure)

; a01: distinct concat
(distinct (concat '(1 2 3) '(2 3 4)))
;=> (1 2 3 4)

; a02: set
(require '[clojure.set :refer [union]])

(union #{1 2 3} #{3 4 5})
;=> #{1 2 3 4 5}

; a03: distinct into
(distinct (into [1 2 3] [3 4 5]))
;=> (1 2 3 4 5)

; a04: union
(into #{} (clojure.set/union '(1,2,3) '(3,4,5)))
;#{1 2 3 4 5}

;or if you want to get a list

(into #{} (clojure.set/union '(1,2,3) '(3,4,5)))
;(5 4 3 2 1)

; a05: set into
(-> #{}
  (into [1 2 3])
  (into [3 4 5])
  seq)

; a06: concat (duplike öğelere izin verir)
(concat '(1 2 3 ) '(4 5 6 1) '(2 3))
;=> (1 2 3 4 5 6 1 2 3)
