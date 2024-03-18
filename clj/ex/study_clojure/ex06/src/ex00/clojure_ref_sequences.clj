(ns ex00.clojure_ref_sequences
  (:require [clojure.string :as str]))

; https://clojure.org/reference/sequences

;; # seq -> seq

;; sect01: shorter seq id=g11359
;;   id:: eea4fac2-4bad-47a4-b744-30544f492490

(distinct [1 2 3 1])
;; => (1 2 3)

(filter even? [1 2 3])
;; => (2)
(filter #(even? (second %)) {:a 1 :b 2})
;; => ([:b 2])

(remove pos? [-1 0 1])
;; => (-1 0)

(for
  [x [0 1 2]
   :let [y (inc x)]
   :when (even? y)]
  x)
;; => (1)

(keep pos? [-1 0 1])
;; => (false false true)

(keep-indexed #(if (odd? %1) %2) [:a :b :c])
;; => (:b)

;; sect02: longer seq id=g11360
;;   id:: 7f38f31d-27e5-4500-ad15-275514345396

(cons 1 [2 3])
;; => (1 2 3)
(cons 1 '(2 3))
;; => (1 2 3)
(concat [1 2] [3 4])
;; => (1 2 3 4)
(lazy-cat [1 2] [3 4])
;; => (1 2 3 4)
(mapcat reverse [[1 2] [3 4]]) ; foldMap
;; => (2 1 4 3)
(take 5 (cycle [1 3]))  ; rep
;; => (1 3 1 3 1)
(interleave [:a :b] [1 2]) ; zip
;; => (:a 1 :b 2)

(interpose ", " ["ali" "veli"])
;; => ("ali" ", " "veli")
(apply str (interpose ", " ["ali" "veli"]))
;; => "ali, veli"
(str/join ", " ["ali" "veli"])
;; => "ali, veli"

;; sect03: head-items missing id=g11361
;;   id:: fcf96470-a485-40e5-a2b4-fcc7bbb5e810

(rest [1 2 3])
;; => (2 3)
(next [:a :b :c])
;; => (:b :c)
(rest [:a]) ; ()
;; => ()
(next [:a]) ; nil
;; => nil

(fnext [1 2 3])
;; => 2
(nnext [1 2 3])
;; => (3)

(drop 2 [1 2 3])
;; => (3)
(drop -1 [1 2 3])
;; => (1 2 3)
(drop 5 [1 2 3])
;; => ()

(drop-while pos? [1 3 -2 -5 4])
;; => (-2 -5 4)

(nthnext [1 2 3] 2)
;; => (3)

;; sect04: tail-items missing id=g11362
;;   id:: 6b2497ce-5f5f-4821-acfd-a439feebbed8

(take 2 [1 2 3])
;; => (1 2)
(take-nth 2 [1 2 3 4 5])
;; => (1 3 5)
(take-while pos? [1 2 -1 3])
;; => (1 2)
(butlast [1 2 3])
;; => (1 2)
(drop-last 2 [1 2 3])
;; => (1)

;; sect05: rearrangement of a seq id=g11363
;;   id:: 966716ae-efe0-41e6-a72c-04ffd73e8f97

(flatten [1 [2 3]])
;; => (1 2 3)
(reverse [1 2 3])
;; => (3 2 1)
(sort [3 1 2])
;; => (1 2 3)
(sort-by count ["ali" "x"])
;; => ("x" "ali")
(shuffle [1 2 3])
;; => [1 2 3]

;; sect06: nested seqs id=g11364
;;   id:: c976af8b-46aa-4cc8-8bfb-2b7e9d46e817

(split-at 2 [1 2 3 4 5])
;; => [(1 2) (3 4 5)]

(split-with pos? [1 2 -1 3])
;; => [(1 2) (-1 3)]
(split-with (partial > 2) [1 2 -1 0])
;; => [(1) (2 -1 0)]

(partition 2 (range 5))
;; => ((0 1) (2 3))
(partition-all 2 (range 5))
;; => ((0 1) (2 3) (4))
(partition-by pos? [-1 3 0 -3])
;; => ((-1) (3) (0 -3))

;; sect07: partition each item to create a new seq id=g11365
;;   id:: a587c3dc-7e1c-4a05-ac74-4aed14a967e4

(map inc [1 2 3])
;; => (2 3 4)
(map + [1 2] [3 4])
;; => (4 6)
(apply map vector [[:a :b :c]
                   [:d :e :f]
                   [:g :h :i]])
;; => ([:a :d :g] [:b :e :h] [:c :f :i])
(pmap inc [1 2 3])
;; => (2 3 4)
(mapcat reverse [[1 2] [3 4]]) ; foldMap
;; => (2 1 4 3)
(for
  [x [0 1 2]
   :let [y (inc x)]]
  y)
;; => (1 2 3)
(replace [:a :b :c :d :e] [0 3 0])
;; => [:a :d :a]
(reductions + [1 2 3])
;; => (1 3 6)

(map-indexed #(when (< % 2) (str % %2)) [:a :b :c])
;;=> ("0:a" "1:b" nil)
(keep-indexed #(when (< % 2) (str % %2)) [:a :b :c])
;;=> ("0:a" "1:b")

;; # using a seq

;; sect01: extract a specific-numbered item id=g11366
;;   id:: 22ac5a82-3980-4efb-b362-2517e2d228be

(first [1 2 3])
;; => 1
(ffirst [[3 4] [1 2]])
;; => 3
(nfirst [[3 4] [1 2]])
;; => (4)
(second [1 2 3])
;; => 2
(nth [:a :b :c] 2)
;; => :c
(when-first [a [1 2]] a)
;; => 1
(last [1 2 3])
;; => 3
(rand-nth [1 2 3])
;; => 2

;; sect02: seq -> collection id=g11367
;;   id:: 9abef4c4-8d7c-407f-a840-afdd71426590

(zipmap [:a :b] [1 2])
;; => {:a 1, :b 2}
(into (sorted-map) {:b 1 :a 2})
;; => {:a 2, :b 1}
(reduce + [1 2 3])
;; => 6
(set [1 2 1])
;; => #{1 2}
(vec '(1 2 3))
;; => [1 2 3]
(frequencies [:a :b :a])
;; => {:a 2, :b 1}
(group-by count ["ali" "ab" "axe"])
;; => {3 ["ali" "axe"], 2 ["ab"]}

;; sect03: pass items to a function id=g11368
;;   id:: 5f501113-afb0-44cc-acb1-1ed710638879

(apply str ["a" "b" "c"])
;; => "abc"
(apply str [1 2 3])
;; => "123"
(str [1 2 3])
;; => "[1 2 3]"
(str 123)
;; => "123"

;; sect04: compute boolean id=g11369
;;   id:: 397f8aa9-c83d-40e9-9797-e6d961b321d7

(not-empty [1 2])
;; => [1 2]
(not-empty "hello")
;; => "hello"
(some even? [1 2 3])
;; => true
(seq? [1 2 3])
;; => false
(every? even? [1 2])
;; => false
(not-every? even? [1 2])
;; => true
(not-any? even? [1 2])
;; => false
(empty? [1 2])
;; => false

;; sect05: search a seq



