--- 
title: "Book Notes: Clojure for the Brave and True"
date: 2020-03-15T20:59:51+03:00 
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

# Book: Clojure for the Brave and True

## Introduction 

https://www.braveclojure.com/introduction/

## Chapter 1: Building, Running, and the REPL | Clojure for the Brave and True

``` clojure
cd /Users/mertnuhoglu/projects/study/clj/ex/study_clojure/book_clojure_brave
``` 

``` clojure
lein new app clojure-boob
``` 

Edit `~/projects/study/clj/ex/study_clojure/book_clojure_brave/clojure-noob/src/clojure_noob/core.clj`

``` clojure
(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Teapot"))
``` 

``` clojure
cd clojure-noob
lein run
  ##> Teapot
``` 

`lein run` executes `-main` function.

### Building the Clojure Project

``` clojure
lein uberjar
  ##> Created /Users/mertnuhoglu/projects/study/clj/ex/study_clojure/book_clojure_brave/clojure-noob/target/uberjar/clojure-noob-0.1.0-SNAPSHOT-standalone.jar
java -jar target/uberjar/clojure-noob-0.1.0-SNAPSHOT-standalone.jar
  ##> Teapot
``` 

### Using the REPL

``` clojure
lein repl
``` 

``` clojure
clojure-noob.core=> (-main)
Teapot
``` 

REPL interacts with a running clojure process.

## Chapter 2: How to Use Emacs, an Excellent Clojure Editor | Clojure for the Brave and True

Escape: `^G`

### Emacs Buffers

		^x b		| new buffer

## Chapter 3: Do Things: A Clojure Crash Course | Clojure for the Brave and True

### Syntax

#### Forms

Two kinds of structures:

- literal representations of data structures
- operations

`form` refers to valid code. Also `expression`

Clojure `evaluates` every form to produce a value.

Operations: how you do things. They take the form: `opening paranthesis`, `operator`, `operands`, `closing paranthesis`

``` clojure
(operator operand1 operand2 ... operandn)
``` 

There are no commas. 

#### Control flow

`if`

``` clojure
(if boolean-form
  then-form
	optional-else-form)
``` 

`do`: wrap up multiple forms in parantheses

``` clojure
(if true
	(do (form1) "value"))
``` 

`when`: combination of `if` and `do` with no `else`

``` clojure
(when true
	(form1)
	(form2))
``` 

`nil` and `false` are `falsy` values.

`or` and `and`

``` clojure
(or nil)
(or (= 0 1) (= "yes" "no"))
``` 

#### Naming Values with def

`def` to bind a name to a value.

Other languages have assignment of a value to a variable.

### Data Structures

### Functions

``` clojure
((or + -) 1 2 3)
  ##> 6
((and (= 1 1) +) 1 2 3)
  ##> 6
((first [+ 0]) 1 2 3)
  ##> 6
``` 

Higher-order functions: Clojure supports first-class functions.

``` clojure
(map inc [0 1 2])
  ##> (1 2 3)
``` 

#### Function Calls, Macro Calls, and Special Forms

Two other kinds of expressions: `macro calls` and `special forms`

`Special forms` are special because they don't always evaluate all of their operands.

You can't use special forms as arguments to functions.

Macros: They evaluate their operands differently too. They also can't be passed as arguments.
#### Defining Functions

5 parts:

- `defn`
- Function name
- docstring (optional)
- [parameters] 
- (function body)

Arity overloading:

``` clojure
(defn multi-arity
  ;; 3-arity arguments and body
  ([first-arg second-arg third-arg]
     (do-things first-arg second-arg third-arg))
  ;; 2-arity arguments and body
  ([first-arg second-arg]
     (do-things first-arg second-arg))
  ;; 1-arity arguments and body
  ([first-arg]
     (do-things first-arg)))
``` 

Rest parameter: `& rest`

Destructuring: bind names to values within a collection

``` clojure
;; Return the first element of a collection
(defn my-first
  [[first-thing]] ; Notice that first-thing is within a vector
  first-thing)

(my-first ["oven" "bike" "war-axe"])
; => "oven"
``` 

``` clojure
(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.33})
; => Treasure lat: 100
; => Treasure lng: 50
``` 

short way with `:keys`

``` clojure
(defn announce-treasure-location
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))
``` 

retain original map using `:as`

``` clojure
(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))

  ;; One would assume that this would put in new coordinates for your ship
  (steer-ship! treasure-location))
``` 

#### Anonymous Functions

``` clojure
(fn [params] body)
``` 

short way:

``` clojure
  #(* % 3)
``` 

calling:

``` clojure
(#(* % 3) 8)
``` 

multiple args: `%1 %2 %&`

## Chapter 4: Core Functions in Depth | Clojure for the Brave and True

### first, rest, and cons

### seq

`map` is implemented in terms of `cons`, `first`, and `rest`.

``` clojure
(seq '(1 2 3))
; => (1 2 3)

(seq [1 2 3])
; => (1 2 3)

(seq #{1 2 3})
; => (1 2 3)

(seq {:name "Bill Compton" :occupation "Dead mopey guy"})
; => ([:name "Bill Compton"] [:occupation "Dead mopey guy"])
``` 

1. seq returns a value that looks like a list

2. seq of a map consists of key-value vectors

`into` convert seq back into a map

``` clojure
(into {} (seq {:a 1 :b 2 :c 3}))
; => {:a 1, :c 3, :b 2}
``` 

### map

``` clojure
(map inc [1 2 3])
; => (2 3 4)
``` 

multiple collections:

``` clojure
(map str ["a" "b" "c"] ["A" "B" "C"])
; => ("aA" "bB" "cC")
``` 

### reduce

``` clojure
(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {}
        {:max 30 :min 10})
; => {:max 31, :min 11}
``` 

this is equivalent to:

``` clojure
(assoc (assoc {} :max (inc 30))
       :min (inc 10))
``` 


