(ns fn.union)

; rfr: video/20230222-mert-clj-egzersiz-48.mp4

; (union)
; (union s1)
; (union s1 s2)
; (union s1 s2 & sets)
; Return a set that is the union of the input sets
;
(require '[clojure.set :refer [union]])

(union #{1 2 3} #{3 4 5})
;=> #{1 2 3 4 5}
