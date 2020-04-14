(ns clojure_by_example_kimh
  (:require [clojure.string :as str]))

; Most codes taken from https://github.com/kimh/clojure-by-example/blob/master/source/index.html.md

(println "merhaba")

"h"

true

(str "each " "line " "is a" " form")

(str "forms are " "expressions")

(def a-binding "is an assignment in other languages")

(def bindings "map a name to a value")

(type 'quoted-symbols-are-not-evaluated)

(unresolved-symbol-error)

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

(fn [] (str ""))
(def anon-function (fn [] (str "")))
(anon-function)
#(str "anon function")
(#(str "evaluate " "anon function"))
#(str "function argument: " %)
(#(str "function argument: " %) "arg value")
(let [multiple-args #(+ %1 %2)]
  (multiple-args 50 20))

(defn higher-order-function [fun]
  (fun 10))
(higher-order-function inc)

