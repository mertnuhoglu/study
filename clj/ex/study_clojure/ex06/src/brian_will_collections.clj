(ns brian-will-collections
  (:require [clojure.string :as str]))

; Examples from [(288) Clojure - collections (2/6) - YouTube](https://www.youtube.com/watch?v=J4M5XPWEWYI&list=PLAC43CFB134E85266&index=10)

(count {3 5})
;;=> 1
(count [3 5])
;;=> 2

(contains? {3 5} 3)
;;=> true
(contains? {3 5} "a")
;;=> false
(contains? [1 2 3] 4)
;;=> false
;; (contains? '(1 2 3) 3) ; lists don't support contains?

(conj {3 5} [10 20])
;;=> {3 5, 10 20}
(conj [1 2 3] 4)
;;=> [1 2 3 4]
(conj '(1 2 3) 4)
;;=> (4 1 2 3)

(assoc {3 5} 10 20)
;;=> {3 5, 10 20}
(assoc [1 2 3] 3 4)
;;=> [1 2 3 4]

(dissoc {3 5} 10)
;;=> {3 5}
(dissoc {3 5} 3)
;;=> {}

(merge {3 5} {10 20})
;;=> {3 5, 10 20}

(get {3 5} 3)
;;=> 5
(get {3 5} 10)
;;=> nil
(get [10 20 30] 1)
;;=> 20

(pop [10 20 30])
;;=> [10 20]
(pop '(10 20 30))
;;=> (20 30)

(peek [10 20 30])
;;=> 30
(peek '(10 20 30))
;;=> 10

(seq [10 20 30])
;;=> (10 20 30)
(seq '(10 20 30))
;;=> (10 20 30)
(seq {3 5 10 20})
;;=> ([3 5] [10 20])
(keys {3 5 10 20})
;;=> (3 10)
(vals {3 5 10 20})
;;=> (5 20)
(first {3 5 10 20})
;;=> [3 5]
(rest {3 5 10 20})
;;=> ([10 20])

(def ys [])
#_(loop [xs [1 2 3]]
    (conj ys xs)
    (recur (rest xs)))
(identity ys)
(count ys)

(def x (cons 3 nil))
(first x)
;;=> 3
(rest x)
;;=> ()

(def y (cons 4 '(7 8 9)))
(first y)
;;=> 4
(rest y)
;;=> (7 8 9)

(range 2 6)
;;=> (2 3 4 5)
(range 1 10 2)
;;=> (1 3 5 7 9)
(range 11 1 -2)
;;=> (11 9 7 5 3)
(range 1 3 0.5)
;;=> (1 1.5 2.0 2.5)

(repeat 3 5)
;;=> (5 5 5)

(repeatedly 3 rand)
;;=> (0.3078531513774885 0.8110098109596463 0.8760944100794883)

(take 10 (cycle [5 2 3]))
;;=> (5 2 3 5 2 3 5 2 3 5)
(take 5 (iterate dec 2))
;;=> (2 1 0 -1 -2)

(def x (lazy-seq '(1 2 3 4)))
(first x)
;;=> 1
(rest x)
;;=> (2 3 4)

(defn zeroes []
  (lazy-seq
    (cons 0 (zeroes))))
(first (zeroes))
;;=> 0
(first (rest (zeroes)))
;;=> 0
(first (rest (rest (zeroes))))
;;=> 0

(defn increasing [n]
  (lazy-seq
    (cons n (increasing (inc n)))))
(first (increasing 2))
;;=> 2
(first (rest (increasing 2)))
;;=> 3
(first (rest (rest (increasing 2))))
;;=> 4

(defn increasingTo7 [n]
  (if (> n 7)
    nil
    (lazy-seq
      (cons n (increasingTo7 (inc n))))))
(rest (increasingTo7 3))
;;=> (4 5 6 7)
(first (increasingTo7 3))
;;=> 3

(rest '())
;;=> ()
(next '())
;;=> nil
(rest '(1))
;;=> ()
(next '(1))
;;=> nil
(rest '(1 2))
;;=> (2)
(next '(1 2))
;;=> (2)

(nth [1 2 3] 1)
;;=> 2

(apply str ["a" "b" "c"])
;;=> "abc"
(str "a" "b" "c")
;;=> "abc"
(apply + [1 3 5])
;;=> 9
(+ 1 3 5)
;;=> 9
(apply + 1 [3 5])
;;=> 9

(map + [1 2 3] [4 5 6 7])
;;=> (5 7 9)

(reduce + [1 2 3])
;;=> 6
(reduce + 10 [1 2 3])
;;=> 6

(reductions + [1 2 3])
;;=> (1 3 6)
(reductions + 10 [1 2 3])
;;=> (10 11 13 16)

(filter even? [1 3 5 8])
;;=> (8)
(filter odd? [1 3 5 8])
;;=> (1 3 5)

(remove even? [1 3 5 8])
;;=> (1 3 5)
(remove odd? [1 3 5 8])
;;=> (1 3 5)

(take 2 [1 3 5 8])
;;=> (1 3)
(take-last 2 [1 3 5 8])
;;=> (5 8)
(take-nth 2 [1 3 5 8])
;;=> (1 5)

(concat [1 2] [3] [4 5 6])
;;=> (1 2 3 4 5 6)

(interleave [1 2 3] [10 20])
;;=> (1 10 2 20)
(interleave [1 2 3] [10 20] [100 200])
;;=> (1 10 100 2 20 200)

(interpose 3 [10 20 30])
;;=> (10 3 20 3 30)

(distinct [1 1 2 3])
;;=> (1 2 3)
(reverse [1 1 2 3])
;;=> (3 2 1 1)

(flatten [1 2 [3 5]])
;;=> (1 2 3 5)

(sort [3 5 -1])
;;=> (-1 3 5)
(compare 3 5)
;;=> -1
(compare 9 5)
;;=> 1
(compare 3 3)
;;=> 0
(sort > [3 5 -1])
;;=> (5 3 -1)
