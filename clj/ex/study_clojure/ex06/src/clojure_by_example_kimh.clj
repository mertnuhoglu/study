(ns clojure_by_example_kimh
  (:require [clojure.string :as str]))
;; => nil

; Most codes taken or adapted from https://github.com/kimh/clojure-by-example/blob/master/source/index.html.md

(println "merhaba")

"h"

true

(str "each " "line " "is a" " form")

(str "forms are " "expressions")

(def a-binding "is an assignment in other languages")

(def bindings "map a name to a value")

(type 'quoted-symbols-are-not-evaluated)

(unresolved-symbol-error) ;; error

(let [binds "a value to a local name"] (str binds))

(let [a 10
      b 20] (+ 10 20))

; narrow indentation
(let
 [a 10
  b 20]
 (+ 10 20))

; clojure style convention
(let [a 10
      b 20]
  (+ 10 20))

(def def-bindings "are global")

(defn a-function
  [name]
  (str name))

(defn documented
  "This is documentation"
  []
  (str ""))

(meta (var documented))
(meta #'documented)

;; Anonymous functions

(fn [] (str ""))
(def anon-function (fn [] (str "")))
(anon-function)
#(str "anon function")
(#(str "evaluate " "anon function"))
#(str "function argument: " %)
(#(str "function argument: " %) "arg value")
(let [multiple-args #(+ %1 %2)]
  (multiple-args 50 20))

;; Higher-order-functions

(defn higher-order-function [fun]
  (fun 10))
(higher-order-function inc)

(defn closure [outer-arg] #(inc outer-arg))
(def outer (closure 20))
(outer)

;; Namespaces

(in-ns 'user)
(outer) ; unable to resolve symbol

(in-ns 'clojure_by_example_kimh)
(outer)

(in-ns 'user)
(require 'clojure_by_example_kimh)
(clojure_by_example_kimh/outer)

(require '[clojure_by_example_kimh :as cbe])
(cbe/outer)

;; Control Flow

(if (< 3 5)
  "then"
  "else")

(if-let [a nil]
  "then"
  "else")

(if-let [a 0]
  "then"
  "else")

(when true "10")
(when-let [a []] "then")
(when-let [a false] "then")

(let [n 2]
  (case n
    1 "a"
    2 "b"
    "other"))

(let [n 3]
  (cond
    (< n 3) "a"
    (< n 5) "b"
    :else "other"))

(let [n 2]
  (condp < n
    3 "a"
    5 "b"
    "else"))

;; Boolean

(boolean false)
(boolean nil)
(boolean 0) ; truthy
(boolean []) ; truthy
(boolean :a)

;; Strings

(str "join " "strings")
(str "no " "string interpolation" " like ${message}")
(format "%s %s" "this is" "me")

;; Numbers

(/ 4 3)
(* (/ 4 3) 3)
(max 1 2 3)
(mod 3 2)

;; Lists

'(1 2 3)
(conj '(1 2) 3)
(nth '(3 5 2) 1)
(count '(5 3))

;; Vectors

[3 2 5]
(conj [4 1] 7)
(nth [3 2] 0)
(last [2 7 1])
(.indexOf [3 5 7] 5)

;; Sets

#{1 2 3}
(conj #{1 2 3} 4)
(disj #{1 2 3} 3)
(sort #{1 2 3})
(contains? #{1 2 3} 2)
(clojure.set/subset? #{1 2} #{1 2 3})
(clojure.set/superset? #{1 2 3} #{1 2})

;; Maps
{:a 1 :b 2}
(get {:a 1 :b 2} :a)
({:a 1 :b 2} :a)
({:a 1 :b 2} :c "default")
(:a {:a 1 :b 2})
(assoc {:a 1} :c 3)
(merge {:a 1} {:c 3})
(keys {:a 1 :b 2})
(vals {:a 1 :b 2})

;; Sequences

(seq '(1 2))
(seq [1 2])
(seq #{1 2})

(first [5 2])
(first "string")
(second [5 2])
(rest [1 2 3])

(cons 0 [1 2])
(concat '(1 2) '(3 4))

(map inc [1 2])
(map inc `(1 2))
(map inc #{1 2})
(map key {:a 1 :b 2})
(map #(inc (val %1)) {:a 1 :b 2})

(reduce + [1 2 3])
(reduce (fn [rst x] (+ rst x)) [1 2 3])
(reduce + -10 [1 2 3])

(into [1 2] '(3 4))
(into [] '(1 2))
(into (list) [1 2])
(into #{} [1 2])
(into {} [[:a 1] [:b 2]])
(into [] {:a 1 :b 2})

(reverse [1 2])

;; iterations

(take 3 (iterate inc 3))
(range 1 3)
(repeatedly 3 #(println "hi"))
(doseq [a [1 2 3]] (println a))

;; take

(take 5 (range 0 100))
(take-while pos? [1 2 -3 4])
(drop 3 (range 0 10))
(drop-while pos? [1 2 -3 4])

;; filter

(filter pos? [1 2 -3 4])
(remove pos? [1 2 -3 4])

;; grouping

(partition-by pos? [1 2 -3])
(group-by pos? [1 2 -3])

;; list comprehension

(for [x [1 2 3]] (+ 5 x))
(for [x [-1 2 3] :when (< 0 x)] x)  ;; {x | x > 0}
(for [x [1 2 3]
      :let [y (+ x 1)]
      :when (even? y)]
  y)
(for [x (range 3) :while (< x 2)] x)
(for
 [x ['a 'b]
  y [1 2]]
 [x y])

;; recursion

; https://practicalli.github.io/clojure/thinking-functionally/recursion.html
(defn length [xs]
  (if (empty? xs)
    0
    (+ 1 (length (rest xs)))))
(length [])
(length [:a :b])

(defn length2 [result xs]
  (if (empty? xs)
    result
    (recur (+ 1 result) (rest xs))))
(length2 0 [3 4])

(defn count-down [result n]
  (if (= n 0)
    result
    (recur (conj result n) (dec n))))
(count-down [] 3)

;; loop

(loop [i 0]
  (if (= i 3)
    (println "done!")
    (do
      (println i)
      (recur (inc i)))))

;; macros

(defmacro unless [cond then]
  (list
   'if
   (list 'not cond)
   then))
(unless false 1)

(macroexpand '(unless false 1))

;; quotes

(+ 1 2)
(quote (+ 1 2))
'(+ 1 2)

`(+ 1 2) ; syntax-quoting
`(+ 1 ~(inc 1)) ; syntax-quoting allows unquoting to evaluate its expression
`(+ ~(list 1 2))
`(+ ~@(list 1 2)) ; unqoute splice `~@` expands a seq

;; threading macros

(conj (conj [] 1) 2)
(-> []
    (conj 1)
    (conj 2))
(->> [1 2]
     (map inc)
     (map #(* 2 %)))

;; delay

(def later (do [] (prn "Adding") (+ 1 2)))
(def later (delay [] (prn "Adding") (+ 1 2)))

(do
  (future
    (Thread/sleep 3000)
    (println "after sleep"))
  "hello")

(deref (future 1))
@(future 1)
(let [x (future 1)]
  (deref x))

(def p (promise))
(defn listen []
  (future (println "callback: " @p)))
(defn job []
  (Thread/sleep 3000)
  (deliver p "value"))
(listen) (job)

;; atom

(def a (atom 1))
(deref a)
@a
(reset! a 2)
@a

(swap! a #(inc %))

(defn multiple-by [an-atom n] (* an-atom n))
(swap! a multiple-by 3)

;; thread safety

(def g 0)
(repeatedly 10
            #(def g (inc g)))
g

(def g 0)
(repeatedly 10
            (fn [] (future (def g (inc g)))))
g

(def g (atom 0))
(repeatedly 10
            (fn [] (future (swap! g inc))))
@g

;; ref

(def r (ref 0))
(deref r)
@r

;; transaction

(dosync
 (ref-set r 1)
 (ref-set r 2))
@r

(dosync
 (alter r
        (fn [a-ref]
          (inc a-ref))))

(def rec (ref {}))
(dosync
 (alter rec merge {:name "ali"})
 (throw (Exception. "wrong"))
 (alter rec merge {:age 40}))
@rec

;; java

(new java.util.Date)
(java.util.Date. "2016/2/19")

(let [d (java.util.Date.)]
  (str d))

(Math/pow 2 3)
(let [d (java.util.Date.)]
  (.toString d))
(let [d (java.util.Date.)]
  (. d toString))
(let [d1 (java.util.Date.)
      d2 (java.util.Date.)]
  (.equals d1 d2))

;; yeni3
