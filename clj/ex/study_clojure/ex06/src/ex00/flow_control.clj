(ns ex00.flow-control)

; From: Programming Clojure, Alex Miller

(defn is-small? [number]
  (if (< number 100) "yes"))
(def is-small-with-if? is-small?)

; loop recur 01
(loop [result [] x 5]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))
;; [5 4 3 2 1]

; function recur 01
(defn countdown [result x]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))
(countdown []  5)
;; [5 4 3 2 1]

(into [] (take 5 (iterate dec 5)))
;; [5 4 3 2 1]

(into [] (drop-last (reverse (range 6))))
;; [5 4 3 2 1]

(into [] (reverse (rest (range 6))))
;; [5 4 3 2 1]

; StringUtils.indexOfAny implementation in clojure: id=g11994
(defn indexed [coll] (map-indexed vector coll))

(indexed "abcde")
;; ([0 \a] [1 \b] [2 \c] [3 \d] [4 \e])

(defn index-filter [pred coll]
  (when pred 
    (for [[idx elt] (indexed coll) :when (pred elt)] idx)))

(for [[idx elt] (indexed "abcde") ] idx)
;;=> (0 1 2 3 4)

(index-filter #{\a \b} "abcdbb")
;; (0 1 4 5)

(index-filter #{\a \b} "xyz")
;;=> ()

(defn index-of-any [pred coll]
  (first (index-filter pred coll)))

(index-of-any #{\a \b} "abcdbb")
;;=> 0

(nth 
  (index-filter #{:h} [:t :t :h :t :h :h])
  2)
;;=> 5
