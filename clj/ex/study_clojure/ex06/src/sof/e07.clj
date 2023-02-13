(ns sof.e07)

; [hashmap - clojure convert lazy-seq to hash map - Stack Overflow](https://stackoverflow.com/questions/7034685/clojure-convert-lazy-seq-to-hash-map)

; How do I create a map from a lazySeq?

(def fields [:name :age :color])
(def values ["joe" 32 "red"])
(def record (interleave fields values))
(identity record) ; (:name "joe" :age 32 :color "red")
;; (get mymap :age)
;; 32

; a01:

(apply hash-map record) ; {:age 32, :color "red", :name "joe"}

; a02: Since you actually already have separate keys and values, I would suggest skipping the interleave step and instead writing

(zipmap fields values) ; {:name "joe", :age 32, :color "red"}

; Or if you have your heart set on into, you could

(into {} (map vector fields values)) ; {:name "joe", :age 32, :color "red"}

; a03: This isn't sensible at all, but since the original question wanted to use into with record:

(into {} (map vec (partition 2 record))) ; {:name "joe", :age 32, :color "red"}


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
