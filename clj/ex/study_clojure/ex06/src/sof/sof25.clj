(ns sof.sof25)

; rfr: video/20230222-mert-clj-egzersiz-48.mp4

; [How can I merge two sequences in clojure? - Stack Overflow](https://stackoverflow.com/questions/12658341/how-can-i-merge-two-sequences-in-clojure)
; Hem birleştirmek hem de tekrar eden öğeleri silmek istiyoruz

; a01: distinct concat
(distinct (concat '(1 2 3) '(2 3 4)))
;=> (1 2 3 4)

(concat '(1 2 3) '(2 3 4))
;=> (1 2 3 2 3 4)
(distinct '(1 2 3 2 3 4))
;=> (1 2 3 4)

; a02: set union
(require '[clojure.set :refer [union]])

(union #{1 2 3} #{3 4 5})
;=> #{1 2 3 4 5}

; a03: distinct into
(distinct (into [1 2 3] [3 4 5]))
;=> (1 2 3 4 5)

(into [1 2 3] [3 4 5])
;=> [1 2 3 3 4 5]

; a04: into union
(into #{} (clojure.set/union '(1,2,3) '(3,4,5)))
;#{1 2 3 4 5}

(clojure.set/union '(1,2,3) '(3,4,5))
;=> (5 4 3 1 2 3)

;or if you want to get a list

(into '() (into #{} (clojure.set/union '(1, 2, 3) '(3, 4, 5))))
;=> (5 2 3 4 1)
(seq (into #{} (clojure.set/union '(1, 2, 3) '(3, 4, 5))))
;=> (1 4 3 2 5)

; a05: set into
(-> #{}
  (into [1 2 3])
  (into [3 4 5])
  seq)
;=> (1 4 3 2 5)

; ≣
(into #{} [1 2 3])
;=> #{1 3 2}
(into (into #{} [1 2 3]) [3 4 5])
;=> #{1 4 3 2 5}
(seq (into (into #{} [1 2 3]) [3 4 5]))
;=> (1 4 3 2 5)

; a06: concat (duplike öğelere izin verir)
(concat '(1 2 3 ) '(4 5 6 1) '(2 3))
;=> (1 2 3 4 5 6 1 2 3)
