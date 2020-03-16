--- 
title: "Book Notes: Clojure, Practicalli"
date: 2020-03-15T21:10:33+03:00 
draft: false
description: ""
tags:
categories: clojure
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

# Book: Clojure, Practicalli

https://practicalli.github.io/clojure/

Check `/Users/mertnuhoglu/codes/clojure/clojure-through-code`

## Clojure Big Ideas

Stuart Halloway: "Clojure in 10 big ideas"

- Extensible Data Notation
- Persistent Data Structures
- Sequences
- Transducers
- Specification
- Dynamic Development
- Async Programming
- Protocols
- ClojureScript
- Logic query / Logic Programming
- Atomic Succession Model

## Learning Clojure

### Practice Clojure standard library

- basic values and persistent collections 
- binding names and their scopes (def, defn, let)
- calling functions, defining functions, arity options
- higher order functions
- edn, data manipulation

Activities:

- 4clojure.org
- kata exercises
- coding dojo

## Basic Clojure

### Whats my environment

``` clojure
*clojure-version*
  ##> {:major 1, :minor 9, :incremental 0, :qualifier nil}
``` 

Class path:

``` clojure
*compile-path*
  ##> "/Users/mertnuhoglu/codes/clojure/clojure-through-code/target/classes"
``` 

Current namespace:

``` clojure
*ns*
  ##> #object[clojure.lang.Namespace 0x209a5cf "user"]
``` 

Last 3 values:

``` clojure
(+ 1 2 3)
(+ 1 2 3 4)
(+ 1 2 3 5)
(str *1 *2 *3)
"11106"
``` 

### Reading your Project configuration

``` clojure
(slurp "project.clj")
  ##> "(defproject clojure-through-code \"20.1.5-SNAPSHOT\"\n  :description \"Learning Clojure by evaluating code on the fly\"\n  :url \"...
``` 

Tidy up the result:

``` clojure
(read-string (slurp "project.clj"))
  ##> (defproject clojure-through-code "20.1.5-SNAPSHOT" :description "Learning Clojure by evaluating code on the fly" :url "
``` 

`nth` nth element

``` clojure
(nth (read-string (slurp "project.clj")) 1)
  ##> clojure-through-code
``` 

### Threading macros

Read from left-to-right instead of inside-out.

``` clojure
(->
 "./project.clj"
 slurp
 read-string
 (nth 2))
  ##> "20.1.5-SNAPSHOT"
``` 

Thread-last macro:

``` clojure
(->>
 (str " This")
 (str " is")
 (str " backwards"))
;; => backwards is This"
``` 

### Show me the docs

doc string

``` clojure
(defn fn01
	"this is docstring"
	[] (1))
``` 

`doc` and `source` functions:

You should be in `user` namespace

Or switch back `(ns 'user)`

Or `(use 'cloujure.repl)`

``` clojure
(doc doc)
(doc map)
(doc filter)
(doc cons)
``` 

### Assigning Names

When you assign a name to a value, that name is called a symbol.

### Namespace

Using a function from another namespace:

``` clojure
(ns my-namespace.core :require [clojure.java.io])
(defn read-the-file [filename]
  (line-seq (clojure.java.io/reader filename)))
(read-the-file "project.clj")
``` 

Use alias:

``` clojure
(ns my-namespace.core :require [clojure.java.io :as java-io])
(defn read-the-file [filename]
  (line-seq (java-io/reader filename)))
``` 

No qualifier:

``` clojure
(ns my-namespace.core :require [clojure.java.io :refer [reader]])
(defn read-the-file [filename]
  (line-seq (reader filename)))
``` 

Multiple namespaces:

``` clojure
(ns duct-test.main
  (:require [clojure.java.io :as io]
            [com.stuartsierra.component :as component]
            [duct.middleware.errors :refer [wrap-hide-errors]]
	))
``` 

External libraries: put into project file:

``` clojure
(defproject duct-test "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.122"]
                 [com.stuartsierra/component "0.3.0"]
                 [compojure "1.4.0"]
                 [duct "0.4.4"]
                 [environ "1.0.1"]])
``` 

### Control flow

If:

``` clojure
(if (> 3 2)
  "Higher"
  "Lower")
``` 

When: no else expression

``` clojure
    (when (> 3 2)
      "Higher")
``` 

Case: multiple conditions:

``` clojure
(case (inc 3)
  1 "Not even close"
  2 "I wish I was that young"
  3 "That was my previous value"
  4 "You sunk my battleship"
  "I dont think you understood the question")

=> "You sunk my battleship"
``` 

cond: multiple conditions

``` clojure
(cond
  (= 7 (inc 2)) "(inc 2) is not 7, so this condition is false"
  (= 16 (* 8 2)) "This is the first correct condition so its associated expression is returned"
  (zero? (- (* 8 8) 64)) "This is true but not returned as a previous condition is true"
  :otherwise "None of the above are true")

;; => "This is the first correct condition so its associated expression is returned"
``` 

for comprehension: `:when` or `:while`

``` clojure
(for [x (range 10) :when (odd? x)] x)

(for [x (range 10) :while (even? x)] x)

(for [x (range 10)
      y (range 10)]
  [x y])
``` 

while

``` clojure
(while (condition) 
  (do something))
``` 

### Data Structures

Common features: immutable, persistent, shared memory, dynamically typed

#### List

``` clojure
(list 1 2 3 4)
(list :cat 1 "fish" 22/7 (str "fish" "n" "chips"))
(list 1 2 "three" [4] five '(6 7 8 9))
(list )

( 1 2 3 4)

(quote (1 2 3 4))
'(1 2 3 4)

;; Duplicate elements in a list ?
(list 1 2 3 4 1)
(list "one" "two" "one")
(list :fred :barney :fred)
``` 

Binding to a name:

``` clojure
(def my-list (list 1 2 3))
``` 

#### Map

``` clojure
{:key "value"}
(:key 42)
{:key :value}
{"key" "value"}
("key" :value)
{:a 1 :b 2 :c 3}
{:monday 1 :tuesday 2 :wednesday 3 :thursday 4 :friday 5 :saturday 6 :sunday 7}
``` 

``` clojure
(def starwars-characters
   {:luke   {:fullname "Luke Skywarker" :skill "Targeting Swamp Rats"}
    :vader  {:fullname "Darth Vader"    :skill "Crank phone calls"}
    :jarjar {:fullname "JarJar Binks"   :skill "Upsetting a generation of fans"}})
``` 

`get` returns all information

``` clojure
(get starwars-characters :luke)
(get (get starwars-characters :luke) :fullname)
``` 

`get-in` returns a specific information

``` clojure
(get-in starwars-characters [:luke :fullname])
(get-in starwars-characters [:vader :fullname])

``` 

Use map directly:

``` clojure
(starwars-characters :luke)
(:fullname (:luke starwars-characters))
(:skill (:luke starwars-characters))

(starwars-characters :vader)
(:skill (:vader starwars-characters))
(:fullname (:vader starwars-characters))
``` 

Threading macro to shorten code:

``` clojure
(-> starwars-characters
    :luke)

(-> starwars-characters
    :luke
    :fullname)

(-> starwars-characters
    :luke
    :skill)
``` 

This is called destructuring

### Vector

``` clojure
(vector? [5 10 15])
(= [] [])
(= [] [1])

(first [5 10 15])
(rest [5 10 15])
(nth [5 10 15] 1)
(count [5 10 15])

(conj [5 10] 15)
``` 

Lookup:

``` clojure
([1 2 3] 1)
``` 

### Set

``` clojure
(set `(1 2 3 4))
(set `(1 2 1 2 3 4))

 #{1 2 3 4}
 #{:a :b :c :d}
;; duplicate key error
 #{1 2 3 4 1}
``` 

Lookup

``` clojure
(#{:a :b :c} :c)
(#{:a :b :c} :z)
(contains?  #{"Palpatine" "Darth Vader" "Boba Fett" "Darth Tyranus"} "Darth Vader")
``` 

### Naming data structures

``` clojure
(def people ["Jane Doe" "Samuel Peeps"])
``` 

Data structures are immutable names are mutable

``` clojure
(def a 2)
(def a 3)
``` 

## Using data structures

`concat` concats lists/vectors

``` clojure
(concat [1 2] '(3 4))
``` 

`filter` `map`

`reduce`

`cons`

`conj` reverse order of arguments from `cons`

### Sequences

Common functions:

		first
		second
		rest
		cons

### Lazy sequences

``` clojure
(range 4)
(take 4 (range))
``` 

### Destructuring

``` clojure
(let [[a b c & d :as e] [1 2 3 4 5 6 7]]
  [a b c d e])
  ##> [1 2 3 (4 5 6 7) [1 2 3 4 5 6 7]]
[1 2 3 (4 5 6 7) [1 2 3 4 5 6 7]]
(let [[a b c & d :as e] [1 2 3 4 5 6 7]]
  d)
  ##> (4 5 6 7)
(let [[a b c & d :as e] [1 2 3 4 5 6 7]]
  e)
  ##> [1 2 3 4 5 6 7]
``` 

``` clojure
(let [[[x1 y1][x2 y2]] [[1 2] [3 4]]]
  [x1 y1 x2 y2])
  ##> [1 2 3 4]

;; with strings
(let [[a b & c :as str] "asdjhhfdas"]
  [a b c str])
  ##> [\a \s (\d \j \h \h \f \d \a \s) "asdjhhfdas"]

;; with maps
(let [{a :a, b :b, c :c, :as m :or {a 2 b 3}}  {:a 5 :c 6}]
  [a b c m])
  ##> [5 3 6 {:a 5, :c 6}]
``` 

``` clojure
(let [{a :a, c :c}  {:a 5 :c 6}]
  [a c])
  ##> [5 6]
(let [{:keys [a c]}  {:a 5 :c 6}]
  [a c])
  ##> [5 6]

``` 

### Mapping functions

``` clojure
(map + [1 2 3] [1 2 3])
;; => (2 4 6)
(map + [1 2 3] [1 2])
;; => (2 4)
(map + [1 2 3] [])
;; => ()
(map + [1 2 3])
;; => (1 2 3)
``` 

## Modifying data structures


