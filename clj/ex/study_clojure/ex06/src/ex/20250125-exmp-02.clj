(ns ex.20250125_exmp_02)
;; [[20250125-exmp-02.clj]]

;; #d/20250127
;; Clojure: Give an example for set comprehension #f/prmp
;;   id:: eeae0bc9-fc8a-44f2-8eee-0070418ed147
;; [Gemini](https://gemini.google.com/app/6fc050d8335fe731)
;;
(require '[clojure.set :as set])

;; Basic set comprehension: squares of even numbers from 0 to 10
(set (for [x (range 11) :when (even? x)] (* x x)))
;;=> #{0 4 16 36 64 100}

;; More complex example: Cartesian product of two sets, filtered
(let [set1 #{1 2 3}
      set2 #{4 5 6}]
  (set (for [x set1
              y set2
              :when (> (+ x y) 7)]
         [x y]))) 
; #{[3 6] [2 6] [3 5]}

;; Example using `set/select`: filtering a set based on a predicate
(let [my-set #{1 2 3 4 5 6}]
  (set/select even? my-set))
;;=> #{2 4 6}

;; Combining for and set/select for more advanced filtering
(let [data [{:name "Alice" :age 30}
            {:name "Bob" :age 25}
            {:name "Charlie" :age 35}
            {:name "David" :age 28}]]
  (set (for [person data :when (> (:age person) 29)]
         (:name person))))
;;=> #{"Alice" "Charlie"}

;; Example demonstrating the difference between a sequence and a set comprehension
(let [numbers (range 5)]
  (println "Sequence comprehension:")
  (println (for [x numbers] (* x 2))) ; Returns a sequence
  (println "Set comprehension:")
  (println (set (for [x numbers] (* x 2))))) ; Returns a set
;; Output:
; Sequence comprehension:
; (0 2 4 6 8)
; Set comprehension:
; #{0 2 4 6 8}

;; Example using `distinct` to simulate set behavior within a sequence comprehension (less efficient)
(distinct (for [x (range 5) y (range 2)] (+ x y))) ; Duplicates removed, but still a sequence
;;=> (0 1 2 3 4)

;; Showing the use of `into` for building a set from a sequence comprehension
(into #{} (for [x (range 5)] (* x 2)))
;;=> #{0 2 4 6 8}

;; Example with nested `for` and `:let` for intermediate calculations
(set (for [x (range 1 6)
            :let [x-squared (* x x)]
            :when (even? x-squared)]
       [x x-squared]))
;;=> #{[2 4] [4 16]}
;;
;;Explanation:
;; for macro: The for macro is the core of Clojure's comprehensions. It generates a sequence.
;; set function: To make it a set comprehension, you wrap the for expression with the set function. This eliminates duplicates and produces a set.
;; :when clause: The :when clause acts as a filter, including only elements that satisfy the given predicate.
;; :let clause: The :let clause allows you to introduce local bindings within the comprehension, making it easier to perform intermediate calculations.
;; clojure.set/select: The clojure.set/select function provides a more direct way to filter an existing set.
;; Difference between sequence and set: It's crucial to understand that for by itself creates a sequence. You need set to create a set. The examples clearly illustrate this difference.
;; distinct vs. set: While distinct can remove duplicates from a sequence, using set is generally more efficient and idiomatic for creating sets.
;; into for set construction: The into function can also be used to create a set from a sequence, which can be useful in certain situations.

(for [x [0 1 2 3 4 5]
      :let [y (* x 3)]
      :when (even? y)]
  y) 
; (0 6 12)

; Instead of using :let clause can I introduce the variable y in the following form: #f/prmp
;   id:: bce94f9b-bf94-4e48-92b9-05de6d59961e
(for [x [0 1 2 3 4 5]
      y (* x 3)
      :when (even? y)]
  y) 
;; If you try to write y (* x 3) directly after x [0 1 2 3 4 5, Clojure will interpret it incorrectly. It will try to interpret (* x 3) as a collection to iterate over, which will lead to an error because (* x 3) is an expression that produces a number, not a collection.

;; The :let clause is specifically designed for introducing local bindings within the scope of each iteration of the for loop. It allows you to define variables based on the current values of the bound variables.
;;
;; :let ensures that y is recalculated for each value of x
;;
;; Alternative using map and filter:

(->> [0 1 2 3 4 5]
   (map #(* % 3))
   (filter even?)) 
; (0 6 12)
