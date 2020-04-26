(ns clojure_ref_sequences
  (:require [clojure.string :as str]))

;; # seq -> seq

;; sect01: shorter seq

(distinct [1 2 3 1])

(filter even? [1 2 3])
(filter #(even? (second %)) {:a 1 :b 2})

(remove pos? [-1 0 1])

(for
  [x [0 1 2]
   :let [y (inc x)]
   :when (even? y)]
  x)

(keep pos? [-1 0 1])

(keep-indexed #(if (odd? %1) %2) [:a :b :c])

;; sect02: longer seq

(cons 1 [2 3])
(cons 1 '(2 3))
(concat [1 2] [3 4])
(lazy-cat [1 2] [3 4])
(mapcat reverse [[1 2] [3 4]]) ; foldMap
(take 5 (cycle [1 3]))  ; rep
(interleave [:a :b] [1 2]) ; zip

(interpose ", " ["ali" "veli"])
(apply str (interpose ", " ["ali" "veli"]))
(str/join ", " ["ali" "veli"])

;; sect03: head-items missing

(rest [1 2 3])
(next [:a :b :c])
(rest [:a]) ; ()
(next [:a]) ; nil

(fnext [1 2 3])
(nnext [1 2 3])

(drop 2 [1 2 3])
(drop -1 [1 2 3])
(drop 5 [1 2 3])

(drop-while pos? [1 3 -2 -5 4])

(nthnext [1 2 3] 2)
(drop 2 [1 2 3])

;; sect04: tail-items missing

(take 2 [1 2 3])
(take-nth 2 [1 2 3 4 5])
(take-while pos? [1 2 -1 3])
(butlast [1 2 3])
(drop-last 2 [1 2 3])

;; sect05: rearrangement of a seq

(flatten [1 [2 3]])
(reverse [1 2 3])
(sort [3 1 2])
(sort-by count ["ali" "x"])
(shuffle [1 2 3])

;; sect06: nested seqs

(split-at 2 [1 2 3 4 5])

(split-with pos? [1 2 -1 3])
(split-with (partial > 2) [1 2 -1 0])

(partition 2 (range 5))
(partition-all 2 (range 5))
(partition-by pos? [-1 3 0 -3])

;; sect07: partition each item to create a new seq

(map inc [1 2 3])
(map + [1 2] [3 4])
(apply map vector [[:a :b :c]
                   [:d :e :f]
                   [:g :h :i]])
(pmap inc [1 2 3])
(mapcat reverse [[1 2] [3 4]]) ; foldMap
(for
  [x [0 1 2]
   :let [y (inc x)]]
  y)
(replace [:a :b :c :d :e] [0 3 0])
(reductions + [1 2 3])

(map-indexed #(when (< % 2) (str % %2)) [:a :b :c])
;;=> ("0:a" "1:b" nil)
(keep-indexed #(when (< % 2) (str % %2)) [:a :b :c])
;;=> ("0:a" "1:b")

;; # using a seq

;; sect01: extract a specific-numbered item

(first [1 2 3])
(ffirst [[3 4] [1 2]])
(nfirst [[3 4] [1 2]])
(second [1 2 3])
(nth [:a :b :c] 2)
(when-first [a [1 2]] a)
(last [1 2 3])
(rand-nth [1 2 3])

;; sect02: seq -> collection

(zipmap [:a :b] [1 2])
(into (sorted-map) {:b 1 :a 2})
(reduce + [1 2 3])
(set [1 2 1])
(vec '(1 2 3))
(frequencies [:a :b :a])
(group-by count ["ali" "ab" "axe"])

;; sect03: pass items to a function

(apply str ["a" "b" "c"])
(apply str [1 2 3])
(str [1 2 3])
(str 123)

;; sect04: compute boolean

(not-empty [1 2])
(not-empty "hello")
(some even? [1 2 3])
(seq? [1 2 3])
(every? even? [1 2])
(not-every? even? [1 2])
(not-any? even? [1 2])
(empty? [1 2])

;; sect05: search a seq



