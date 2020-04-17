(ns clojure_by_example_kimh
  (:require [clojure.string :as str]))

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
(first [5 2])
(second [5 2])
(last [2 7 1])
(.indexOf [3 5 7] 5)

;; Sets

#{1 2 3}
