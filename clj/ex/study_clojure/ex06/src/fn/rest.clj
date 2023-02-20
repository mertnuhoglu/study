(ns fn.rest)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; rfr: fn/next.clj

; (rest coll)
; Returns a possibly empty seq of the items after the first. Calls seq on its
; argument.
;

(rest [1 2 3 4 5])
;;=> (2 3 4 5)
(rest ["a" "b" "c" "d" "e"])
;;=> ("b" "c" "d" "e")

(rest nil)
;;=> ()

;; A simple (re-)implementation of 'map' using 'rest' for recursing over a collection.
;; Note that (seq coll) is used as the test.
(defn my-map [func coll]
  (when-let [s (seq coll)]
    (cons (func (first s))
      (my-map func (rest s)))))

(my-map #(* % %) [2 3 5 7 11 13])
;;=> (4 9 25 49 121 169)
