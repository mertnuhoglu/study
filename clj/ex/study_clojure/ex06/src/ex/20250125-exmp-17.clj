(ns ex.20250125_exmp_17)
;; [[20250125-exmp-17.clj]]

(doseq [a [1 2]
        b [3 4]]
  (prn (* a b))) ; nil
; (out) 3
; (out) 4
; (out) 6
; (out) 8

(dorun 3 (repeatedly #(println "hi"))) ; nil
; (out) hi
; (out) hi
; (out) hi
; (out) hi

(doall (map println [1 2 3])) ; (nil nil nil)
; (out) 1
; (out) 2
; (out) 3

