(ns core01
  (:require [clojure.string :as str]))

;; printing https://clojuredocs.org/clojure.core/pr id=g_11321
;;   id:: cbe69a05-4d85-4b6b-a60e-1818b54f76ca

(println "ali")
(print "ali")
(prn "ali")
(pr "ali")

(println [1 2 3])
(prn [1 2 3])

(pr ['a :b "\n" \space "c"])
;; [a :b "\n" \space "c"]nil

(print ['a :b "\n" \space "c"])
;; [a :b
;;  c]nil

; Threading macro to shorten code

(-> {:a {:b 3}}
  :a
  :b)
;; => 3

; slurp: read file url id=g_11404
;   id:: b89c9c97-d5bc-45cb-81ba-c18ea4ff0458
; https://clojuredocs.org/clojure.core/slurp

(spit "tmp.txt" "test")
(slurp "tmp.txt")

; record id=g_11410
;   id:: 2bfbea8a-f9f9-4db6-b6de-61729cb18023
; ref: If several maps have keys in common, create a record: <url:file:///~/projects/study/clj/book_programming_in_clojure.md#r=g_11411>

(defrecord Book [title author])
(->Book "title01" "author01")
;; => #user.Book{:title "title01", :author "author01"}

; complement id=g_11434
;   id:: c1d9a688-de98-4f92-8a61-ef0cadb6fddb
; rfr: complement || ((203e51e3-3362-4c72-b392-505a8b83a2a6))

(def not-empty? (complement empty?))
(not-empty? [])    ;;=> false
(not-empty? [1 2]) ;;=> true

; keep  id=g_11436
;   id:: 77ad19d0-3f1d-4cba-9988-bb76059495c4
; keep  || ((513c62cc-9c4c-410b-a12b-d761e430ecea))

(keep #(if (odd? %) %) (range 4))
;;=> (1 3 5 7 9)

(map #(if (odd? %) %) (range 4))
;;=> (nil 1 nil 3 nil 5 nil 7 nil 9)

(for [ x (range 4) :when (odd? x)] x)
;;=> (1 3 5 7 9)

(filter odd? (range 4))
;;=> (1 3 5 7 9)

; reduce-kv
;   id:: 11824791-e3e3-4e27-9b00-2e7b6c3f704b
; reduce-kv || ((81b92318-1132-4fe9-ac34-7318a8c67558))

(reduce-kv #(assoc %1 %3 %2) {} {:a 1 :b 2})
;; => {1 :a, 2 :b}

(def data [{:a 1 :b 2} {:a 3}])
(defn update-map [m f] 
  (reduce-kv 
    (fn [m k v] 
      (assoc m k (f v))) 
    {} m))

(map #(update-map % inc) data)
;; => ({:a 2, :b 3} {:a 4})

(reduce-kv 
  (fn [m k v] 
    (assoc m k (inc v))) 
  {} {:a 1 :b 2})
;; => {:a 2, :b 3}

; find the last odd number's index
; note: acc is the last odd number's index
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) nil {2 3})
;; => 2
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) nil {1 2, 2 3})
;; => 2
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) nil {1 2, 2 3, 3 4})
;; => 2
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) nil {1 2, 2 3, 3 4, 4 5})
;; => 4
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) [] {2 3})
;; => 2
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) [] {1 2, 2 3, 3 4})
;; => 2
(reduce-kv (fn [acc i v] (if (odd? v) i {})) [] {2 3})
;; => 2
(reduce-kv (fn [acc i v] (if (odd? v) i {})) [] {2 3, 3 4})
;; => {}
(reduce-kv (fn [acc i v] (if (odd? v) acc i)) [] {2 3, 3 4})
;; => 3
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) [] {2 3, 3 4})
;; => 2
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) nil {2 4})
;; => nil


; for

; apply a function to hashmap entries:
(def data [{:a 1 :b 2} {:c 3}])
(let [m data] m)
;; => [{:a 1, :b 2} {:c 3}]
(let [[k v] data] [k v])
;; => [{:a 1, :b 2} {:c 3}]
(for [[k v] data] [k v]) ; error
(for [[k v] data] (inc v)) ; error
(for [[k v] (first data)] [k v]) 
;; => ([:a 1] [:b 2])
(for [[k v] (first data) ] [k (inc v)])
;; => ([:a 2] [:b 3])

(mapv #(for [[k v] %] [k (inc v)]) data)
;; => [([:a 2] [:b 3]) ([:c 4])]

(defn apply-fn-to-hashmap [f m]
  (for [[k v] m] [k (inc v)]))
(mapv #(apply-fn-to-hashmap inc %) data)
;; => [([:a 2] [:b 3]) ([:c 4])]

(defn apply-fn-to-hashmap [f m]
  (into {} (for [[k v] m] [k (inc v)])))
(mapv #(apply-fn-to-hashmap inc %) data)
;; => [{:a 2, :b 3} {:c 4}]

(defn inc-even [n]
  (if (even? n) (inc n) n))
(defn apply-fn-to-hashmap [f m]
  (into {} (for [[k v] m] [k (inc-even v)])))
(mapv #(apply-fn-to-hashmap inc-even %) data)
;; => [{:a 1, :b 3} {:c 3}]

;; # Collections

;; ## Generic ops

(count [1 2 3])
;; => 3
(empty [1])
;; => []
(not-empty [1])
;; => [1]
(not-empty [])
;; => nil
(into [] #{1 2})
;; => [1 2]
(into (sorted-map) [[1 2] [3 4]])
;; => {1 2, 3 4}
(conj [1 2] 3)
;; => [1 2 3]

;; ## Content tests

(distinct? 1 2 2)
;; => false
(empty? ())
;; => true
(every? even? [1 2])
;; => false
(not-every? even? [1 2])
;; => true
(some even? [1 2])
;; => true
(not-any? even? [1 2])
;; => false

;; ## Capabilities

(sequential? [1])
;; => true
(associative? [1])
;; => true
(sorted? [])
;; => false
(counted? "a")
;; => false
(reversible? {})
;; => false

;; ## Type tests

(coll? [])
;; => true
(list? [])
;; => false
(vector? [])
;; => true
(set? #{})
;; => true
(map? {})
;; => true
(seq? [])
;; => false
(record? [])
;; => false
(map-entry? (first {:a 1}))
;; => true

;; # Lists (conj, pop, peek at beginning)

;; ## Create

'(1 2)
;; => (1 2)
(list 1 2)
;; => (1 2)
(list* 1 [2 3])
;; => (1 2 3)
(list 1 [2 3])
;; => (1 [2 3])

;; ## Examine

(first '(1 2))
;; => 1
(nth '(1 2) 1)
;; => 2
(peek '(1 2))
;; => 1
(.indexOf "ali" "a")
;; => 0
(.lastIndexOf "aba" "a")
;; => 2

;; ## Change

(cons 1 '(2 3))
;; => (1 2 3)
(conj '(1 2) 3)
;; => (3 1 2)
(rest '(1 2))
;; => (2)
(pop '(1 2))
;; => (2)

;; ## Vectors (conj, pop, peek at end)

;; # Sets

;; ## Set ops

(clojure.set/select odd? #{1 2})
;; => #{1}

;; # Maps

;; ## Change

(assoc {} :k 1)
;; => {:k 1}
(dissoc {:k 1} :k)
;; => {}
(merge {} {})
;; => {}

;; # Sequences

;; ## Creating

(take 3 (range))
;; => (0 1 2)
(range 10)
;; => (0 1 2 3 4 5 6 7 8 9)
(range -5 5)
;; => (-5 -4 -3 -2 -1 0 1 2 3 4)
(range 100 0 -10)
;; => (100 90 80 70 60 50 40 30 20 10)

(repeat 5 "a")
;; => ("a" "a" "a" "a" "a")
(take 5 (repeat "a"))
;; => ("a" "a" "a" "a" "a")

(repeatedly 5 rand)
;; => (0.16775666879239537 0.012787920336573722 0.9320162884691133 0.9435585564351228 0.5306635736994048)

(take 5 (cycle [1 2]))
;; => (1 2 1 2 1)

(take 5 ( iterate dec 5))
;; => (5 4 3 2 1)

(lazy-seq inc 1)

;; ## Using a Seq

(apply str ["a" "b"])
;; => "ab"
