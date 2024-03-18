(ns ex00.doc_partial)

; https://clojuredocs.org/clojure.core/partial

(def hundred-times (partial * 100))
(hundred-times 5)

; https://practicalli.github.io/clojure/thinking-functionally/partial-functions.html

(defn sum
  [a b]
  (+ a b))
(map (partial sum 2) [1 2 3])

(reduce + [1 2 3] [4 5 6]) ; error
(map (partial reduce +) [[1 2 3] [4 5 6]])
