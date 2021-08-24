(ns destructuring)

; destructuring id=g11408
; ref: Destructuring <url:file:///~/projects/study/clj/book_clojure_practicalli.md#r=g11407>

(let [[a b & c :as d] [1 2 3 4]] [a b])
;; => [1 2]

(let [[a b & c :as d] [1 2 3 4]] [c])
;; => [(3 4)]

(let [[a b & c :as d] [1 2 3 4]] [d])
;; => [[1 2 3 4]]

(let [{a :a, c :c}  {:a 5 :c 6}]
  [a c])
;; [5 6]
(let [{a :a, :as m} {:a 2 :b 3}]
  [a m])
;; => [2 {:a 2, :b 3}]

