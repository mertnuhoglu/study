(ns fn.interleave)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; (interleave)
; (interleave c1)
; (interleave c1 c2)
; (interleave c1 c2 & colls)
; Returns a lazy seq of the first item in each coll, then the second etc.
;

(interleave [:a :b :c] [1 2 3])
;;=> (:a 1 :b 2 :c 3)

;; The shortest input stops interleave:
(interleave [:a :b :c] [1 2])
;;=> (:a 1 :b 2)

(interleave [:a :b] [1 2 3])
;;=> (:a 1 :b 2)
