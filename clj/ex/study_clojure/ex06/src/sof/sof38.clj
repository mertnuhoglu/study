(ns sof.sof38)

; [Into or vec: converting sequence back to vector in Clojure - Stack Overflow](https://stackoverflow.com/questions/12044181/into-or-vec-converting-sequence-back-to-vector-in-clojure)

(def f inc)
(def args [10 20 30])

; a01: mapv
(mapv f args)
;=> [11 21 31]

; a02: into vec map
(into (vector) (map f args))
;=> [11 21 31]

(into [] (map f args))
;=> [11 21 31]

; BURDA KALDIK: [Highest scored 'clojure' questions - Page 15 - Stack Overflow](https://stackoverflow.com/questions/tagged/clojure?tab=votes&page=15&pagesize=50)