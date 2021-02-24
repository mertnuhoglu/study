(ns core01
  (:require [clojure.string :as str]))

;; printing https://clojuredocs.org/clojure.core/pr id=g_11321

(println "foo")
(print "foo")
(prn "foo")
(pr "foo")

(println [1 2 3])
(prn [1 2 3])

; character literal syntax: \{letter}
(pr ['a :b "\n" \space "c"])
;; [a :b "\n" \space "c"]nil

(print ['a :b "\n" \space "c"])
;; [a :b
;;  c]nil


; variadic function id=g_11358

(defn f [x & xs]
  (str x xs))
;; => #'user/f
(f 1 2 3)
;; => "1(2 3)"

; slurp: read file url id=g_11404
; https://clojuredocs.org/clojure.core/slurp

(spit "tmp.txt" "test")
(slurp "tmp.txt")

; Threading macro to shorten code

(-> {:a {:b 3}}
    :a
    :b)
;; => 3

; record id=g_11410
; ref: If several maps have keys in common, create a record: <url:file:///~/projects/study/clj/book_programming_in_clojure.md#r=g_11411>

(defrecord Book [title author])
(->Book "title01" "author01")
;; => #user.Book{:title "title01", :author "author01"}
(def b (->Book "title01" "author01"))
(:title b)
;; "title01"

; complement id=g_11434
; ref: complement <url:file:///~/projects/study/clj/clojure.md#r=g_11433>)

(def not-empty? (complement empty?))
(not-empty? [])    ;;=> false
(not-empty? [1 2]) ;;=> true

; keep  id=g_11436
; ref: 	keep  <url:file:///~/projects/study/clj/clojure.md#r=g_11435>

(keep #(if (odd? %) %) (range 4))
;;=> (1 3 5 7 9)

(map #(if (odd? %) %) (range 4))
;;=> (nil 1 nil 3 nil 5 nil 7 nil 9)

(for [ x (range 4) :when (odd? x)] x)
;;=> (1 3 5 7 9)

(filter odd? (range 4))
;;=> (1 3 5 7 9)

; reduce-kv
; ref: reduce-kv <url:file:///~/projects/study/clj/clojure.md#r=g_11444>

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
(.indexOf "foo" "a")
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

(->> "aaaabbbcca"
     (partition-by identity)
     (map #(vector (first %) (count %))))
;; => ([\a 4] [\b 3] [\c 2] [\a 1])

(def p {:name "James" :age 26})
(update p :age inc)
;;=> {:name "James", :age 27}
(update p :age + 10)
;;=> {:name "James", :age 36}

;; Here we see that the keyed object is 
;; the first argument in the function call.
;; i.e. :age (- 26 10) => 16
(update p :age - 10)
;;=> {:name "James", :age 16}

(letfn [(twice [x]
           (* x 2))
        (six-times [y]
           (* (twice y) 3))]
  (println "Twice 15 =" (twice 15))
  (println "Six times 15 =" (six-times 15)))
;; Twice 15 = 30
;; Six times 15 = 90
;;=> nil

;; with-redefs: Temporarily redefines Vars while executing the body

[(type []) (class [])]
;[clojure.lang.PersistentVector clojure.lang.PersistentVector]

(with-redefs [type (constantly java.lang.String)
              class (constantly 10)]
  [(type [])
   (class [])])
;[java.lang.String 10]

;; assoc

(assoc {} :a :v1 :b :v2)
; {:a :v1, :b :v2}
(assoc [1 2 3] 0 10)
; [10 2 3]

;; some

; check if a coll contains some elements:
(some #{:a} #{:a :b})
; :a
(some #{:a} #{:b})
; nil

;; juxt

;; Extract values from a map, treating keywords as functions.
((juxt :a :b) {:a 1 :b 2 :c 3 :d 4})
;;=> [1 2]

;; "Explode" a value.

((juxt identity name) :keyword)
;;=> [:keyword "keyword"]

(juxt identity name)
...is the same as:
(fn [x] [(identity x) (name x)])

;; eg. to create a map:

(into {} (map (juxt identity name) [:a :b :c :d]))
;;=> {:a "a" :b "b" :c "c" :d "d"}

;; doall

;; Nothing is printed because map returns a lazy-seq
(def foo (map println [1 2 3]))
;#'user/foo

;; doall forces the seq to be realized
(def foo (doall (map println [1 2 3])))
;1
;2
;3
;#'user/foo

;; where
(doall (map println [1 2 3]))
;1
;2
;3
;(nil nil nil)

;; partial

(def incrementer (partial + 1)) ; <1>
(incrementer 1 1) ; <2>
;; 3

;; re-seq

(re-seq #"\w+" "foo bar")
;; ("foo" "bar")

;; some->>

(some->> "foo bar"
  (re-seq #"\w+"))
;; ("foo" "bar")

(map peek '(1 2 3))
; (err) Error printing return value (ClassCastException) at null (REPL:1).

(peek '(1 2 3))
;; 1

(peek '("foo" "bar"))
;; "foo"

(def r0 
  (some->> "foo bar"
    (re-seq #"\w+")))
(identity r0)
;; ("foo" "bar")

(def r0 
  (re-seq #"\w+" "foo bar"))
(identity r0)
;; ("foo" "bar")

(peek r0)
; (err) Execution error (ClassCastException) at (REPL:1).

(peek '("foo" "bar"))
;; "foo"

(= r0 '("foo" "bar"))
;; true

(type r0)
;; clojure.lang.Cons

(type '("foo"))
;; clojure.lang.PersistentList

(seq? r0)
;; true

(coll? r0)
;; true

(->> '("foo" "bar") peek)
;; "foo"

(->> r0 peek)
; (err) Execution error (ClassCastException) at (REPL:1).

(some->> "foo bar"
  (re-seq #"\w+")
  (peek))
; (err) Error printing return value (ClassCastException) at null (REPL:1).

(some->> "foo bar"
  (re-seq #"\w+")
  peek)
; (err) Error printing return value (ClassCastException) at null (REPL:1).

(some-> "foo bar"
  (re-seq #"\w+")
  peek)
; (err) java.lang.String cannot be cast to java.util.regex.Pattern

(some-> "foo bar"
  (re-seq #"\w+")
  (peek))
; (err) java.lang.String cannot be cast to java.util.regex.Pattern

(some->> "foo bar"
  (re-seq #"\w+")
  (map peek))
; (err) Error printing return value (ClassCastException) at null (REPL:1).

(def doc2 "foo bar")

(some->> doc2
  (re-seq #"\w+")
  (map peek))
; (err) Error printing return value (ClassCastException) at null (REPL:1).

(def doc "<html><head>
            <title>Once upon a time</title>
            <title>Kingston upon Thames</title>
        </head></html>")

(some->> doc                        ; <2>
  (re-seq #"<title>(.+?)</title>")  ; <3>
  (map peek))                      ; <4>
;; ("Once upon a time" "Kingston upon Thames")

(some->> doc                        ; <2>
  (re-seq #"<title>(.+?)</title>"))  ; <3>
;; (["<title>Once upon a time</title>" "Once upon a time"
;;   ["<title>Kingston upon Thames</title>" "Kingston upon Thames"]])

(def r 
  (some-> "foo bar"
    (re-seq #"\w+")))
(identity r)
;; #object[clojure.lang.Var$Unbound 0x511d8c37 "Unbound: #'core01/r"]

(list 1 2 3)

; Predicates

(true? true)
;; true
(true? "foo")
;; false

(nil? [])
;; false
(nil? nil)
;; true

(zero? 0.0)
;; true
(zero? (/ 22 7))
;; false

; find all predicates
(require '[clojure.repl :refer :all])
(find-doc #"\?$")
