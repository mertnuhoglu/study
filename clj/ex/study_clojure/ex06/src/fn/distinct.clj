(ns fn.distinct)

; rfr: video/20230222-mert-clj-egzersiz-48.mp4

; (distinct)
; (distinct coll)
; Returns a lazy sequence of the elements of coll with duplicates removed.
; Returns a stateful transducer when no collection is provided.
;

(distinct [1 2 3 1])
;=> (1 2 3)

(distinct (concat '(1 2 3) '(2 3 4)))
;=> (1 2 3 4)

