--- 
title: "Article Notes: Clojure Distilled"
date: 2020-03-15T21:07:10+03:00 
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

# Clojure Distilled

https://yogthos.net/ClojureDistilled.html

## The Core

### Data Types

Vars: mutable storage locations

Numbers: integers, doubles, floats, fractions

Symbols: identifiers for variables

Keywords: symbols that reference themselves and denoted by a colon. Often used as keys in maps

Strings: denoted by double quotes

Regular expressions: strings prefixed with a hash symbol

Literal notation:

- List: `'(1 2 3)'`
- Vector: `[1 2 3]`
- Map: `{:foo "a" :bar "b"}`
- Set: `#{"a" "b" "c"}`

Clojure logic is written using its data structures. Using the same syntax for both data and logic allows for powerful metaprogramming features.

Code is data and data is code.

## Functions

```clj
(function-name param1 param2)
``` 

The function call is simply a list containing the function name and its parameters. A list is reserved for creating callable expressions. 

Creating a list:

```clj
(list 1 2 3)
``` 

### Anonymous Functions

Functions that aren't bound to a name.

```clj
(fn [arg] (println arg))
``` 

We can call this function by settings it as a first item in a list and its argument as the second.

```clj
((fn [arg] (println arg)) "hello")
  ##> hello
``` 

Syntactic sugar for defining anonymous functions using the `#` notation

```clj
  #(println %)
``` 

`%` indicates an unnamed argument

If multiple arguments exist:

```clj
  #(println %1 %2 %3)
``` 

### Named Functions

Creating global variables using `def`

```clj
(def double
  (fn [x] (* 2 x)))
``` 

It accepts a name and the body.

Syntactic sugar for this: `defn`

```clj
(defn square [x]
  (* x x))
``` 

The body can consist of multiple expressions:

```clj
(defn bmi [height weight]
  (println "height:" height)
  (println "weight:" weight)
  (/ weight (* height height)))
``` 

Clojure uses a single pass compiler. So, the functions must be declared before they are used.

```clj
(declare down)

(defn up [n]
  (if (< n 10)
    (down (+ 2 n)) n))

(defn down [n]
  (up (dec n)))
``` 

### Higher-Order Functions

```clj
(map #(* % %) [1 2 3 4 5]) => (1 4 9 16 25)
(filter even? [1 2 3 4 5]) => (2 4)
(filter even?
  (map #(* 3 %) [1 2 3 4 5]))
  ##> =>(6 12)
(defn concat-fields [& fields]
  (clojure.string/join ", " (remove empty? fields)))
(concat-fields "" "1 Main street" "Toronto" nil "Canada")
  ##> => "1 Main street, Toronto, Canada"
``` 

### Closures

Functions that return other functions as their result.

One use is to mimic constructors in oop languages.

```clj
(defn greeting [greeting-string]
  (fn [guest]
    (println greeting-string guest)))

(let [greet (greeting "Welcome to the wonderful world of Clojure")]
  (greet "Jane")
  (greet "John"))
``` 

`greeting` is a closure because it closes over its parameters and makes them available to the function that it returns. 

`let` binds `greet` symbol and makes it available to expressions inside it. This is like declaring variables in imperative languages.

### Threading Expressions

Reading nested expressions is difficult. 

```clj
(reduce + (interpose 5 (map inc (range 10))))
``` 

We want to read them in linear form:

```clj
(->> (range 10) (map inc) (interpose 5) (reduce +))
``` 

`->>` works as a pipe. The result of each expression is passed as the last argument of the next expression. 

`->` as first argument.

### Laziness

## Code Structure

```clj
(println
  (filter #(= (mod % 2) 0)
    (map #(* % %) (range 1 6))))
``` 

Flattened steps:

```clj
(->> (range 1 6)
     (map #(* % %))
     (filter #(= (mod % 2) 0))
     (println))
``` 

Each function returns a new value instead of modifying existing data in place.

Clojure uses persistent data structures that create in-memory revisions of the data.

### Destructuring id=g11903

```clj
(let [[smaller bigger] (split-with #(< % 5) (range 10))]
    (println smaller bigger))

  ##> =>(0 1 2 3 4) (5 6 7 8 9)
``` 

`split-with` returns two elements: numbers less than 5 and greater than 5. They are bound to `smaller` and `bigger` variables.

```clj
(defn print-user [[name address phone]]
  (println name "-" address phone))

(print-user ["John" "397 King street, Toronto" "416-936-3218"])
=> "John - 397 King street, Toronto 416-936-3218"
``` 

`print-user` takes 3 arguments: `name`, `address`, and `phone`

```clj
(defn print-args [& args]
  (println args))

(print-args "a" "b" "c") => (a b c)
``` 

`print-args` takes variable arguments.

Variable arguments can be destructured too:

```clj
(defn print-args [arg1 & [arg2]]
  (println
    (if arg2
      "got two arguments"
      "got one argument")))

(print-args "bar")
=>"got one argument"

(print-args "bar" "baz")
=>"got two arguments"
``` 

Destructuring maps. Supply the names for the local bindings pointing to the keys of the original map:

```clj
(let [{foo :foo bar :bar} {:foo "foo" :bar "bar"}]
  (println foo bar))
  ##> foo bar
``` 

```clj
(let [{f :foo b :bar} {:foo "hello" :bar "world"}]
  (println f b))
  ##> hello world
``` 

Destructuring nested maps:

```clj
(let [{[a b c] :items id :id} {:id "foo" :items [1 2 3]}]
  (println id "->" a b c))
  ##> foo -> 1 2 3
``` 

`:keys` Syntactic sugar for extracting keys from maps:

ex01: long form

```clj
(let [{foo :foo bar :bar} {:foo "foo" :bar "bar"}]
  (println foo bar))
  ##> foo bar
``` 

ex05: :keys short form

```clj
(let [{:keys [foo bar]} {:foo "foo" :bar "bar"}]
  (println foo bar))
  ##> foo bar
``` 

Error:

```clj
(let [:keys [foo bar] {:foo "foo" :bar "bar"}]
  (println foo bar))
  ##> [:keys [foo bar] {:foo "foo", :bar "bar"}] - failed: even-number-of-forms? at: [:bindings] spec: :clojure.core.specs.alpha/bindings
``` 

ex02:

```clj
(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))
(announce-treasure-location {:lat 28.22 :lng 81.33})
; => Treasure lat: 100
; => Treasure lng: 50

(defn announce-treasure-location
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))
``` 

ex03:

```clj
(defn login [{:keys [user pass]}]
 (and (= user "bob") (= pass "secret")))

(login {:user "bob" :pass "secret"})
``` 

`:as` Another useful destructuring option allows us to extract some keys while preserving the original map:

ex01:

```clj
(defn register [{:keys [id pass repeat-pass] :as user}]
  (cond
    (nil? id) "user id is required"
    (not= pass repeat-pass) "re-entered password doesn't match"
    :else user))
``` 

ex02:

```clj
(let [{:keys [a b], :as all} {:a 1, :b 2, :c 3}]
  (println a b all))
  ##> 1 2 {:a 1, :b 2, :c 3}
``` 

### Namespaces

```clj
(ns colors)

(defn hex->rgb [[_ & rgb]]
    (map #(->> % (apply str "0x") (Long/decode))
         (partition 2 rgb)))
``` 

Calling a function from another namespace: `:use` or `require`

```clj
(ns myns
  (:use colors))

(hex->rgb "#33d24f")
``` 

`:use` makes all variables implicitly available

`:only` restricts imported variables:

```clj
(ns myns
  (:use [colors :only [rgb->hex]]))
``` 

`:require` requires variables to be prefixed:

```clj
(ns myns (:require colors))

(colors/hex->rgb "#324a9b")
``` 

`:as` alias for imported variables.

```clj
(ns myotherns
  (:require [colors :as c]))

(c/hex->rgb "#324a9b")
``` 

### Dynamic Variables

### Polymorphism

#### Multimethods

```clj
(defmulti area :shape)

(defmethod area :circle [{:keys [r]}]
  (* Math/PI r r))

(defmethod area :rectangle [{:keys [l w]}]
  (* l w))

(defmethod area :default [shape]
  (throw (Exception. (str "unrecognized shape: " shape))))

(area {:shape :circle :r 10})
=> 314.1592653589793

(area {:shape :rectangle :l 5 :w 10})
=> 50
``` 

#### Protocols

Abstract set of functions

```clj
(defprotocol Foo
  "Foo doc string"
  (bar [this b] "bar doc string")
  (baz [this] [this b] "baz doc string"))
``` 

`Foo` protocol specifies two methods: `bar` and `baz`


