(ns sof.sof11)

; [Return a map from a list of values in Clojure - Stack Overflow](https://stackoverflow.com/questions/26256753/return-a-map-from-a-list-of-values-in-clojure?noredirect=1&lq=1)

; tek sayıda öğe de olsa listeyi map yapmak istiyor

(defn seq->map
  [s]
  (let [s+ (concat s [nil])]
    (zipmap (take-nth 2 s+)
      (take-nth 2 (rest s+)))))

(seq->map '(1 2 3 4))   ; {3 4, 1 2}
(seq->map '(1 2 3 4 5)) ; {5 nil, 3 4, 1 2}
(seq->map [1 2 3 4])    ; {3 4, 1 2}
(seq->map [1 2 3 4 5])  ; {5 nil, 3 4, 1 2}

(comment
  (def s [1 2 3 4])
  (def s+ (concat s [nil]))
  (identity s+)
  ; (1 2 3 4 nil)
  (take-nth 2 s+)
  ; (1 3 nil)
  (take-nth 2 (rest s+))
  ; (2 4)
  ,)

; a02:

(defn to-map [v]
  (apply hash-map
    (if (odd? (count v))
      (conj v nil)
      v)))
(to-map [1 2 3])
;=> {1 2, 3 nil}