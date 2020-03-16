--- 
title: "Book Notes: Clojure - Learn Clojure"
date: 2020-03-15T21:05:32+03:00 
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

# Clojure - Learn Clojure 

## Clojure - Learn Clojure - Syntax

https://clojure.org/guides/learn/syntax

### Strings (character types)

``` clojure
"hello"         ; string
\e              ; character
  #"[0-9]+"       ; regular expression
``` 

### Symbols and idents:

		map             ; symbol
		+               ; symbol - most punctuation allowed
		clojure.core/+  ; namespaced symbol
		nil             ; null value
		true false      ; booleans
		:alpha          ; keyword
		:release/alpha  ; keyword with namespace

Literal collections:

``` clojure
'(1 2 3)     ; list
[1 2 3]      ; vector
  #{1 2 3}     ; set
{:a 1, :b 2} ; map
``` 

### Structure vs Semantics

A clojure expression: 

/Users/mertnuhoglu/Pictures/screenshots/20191105131739.png

Syntax in green (data structure produced by the Reader)

Semantics in blue (how that data is understood by clojure runtime)

Most literal clojure forms evaluate to themselves, except symbols and lists.

Symbols refer to something else. They return what they refer to when evaluated.

Lists are evaluated as invocation.

`(+ 3 4)` is read as a list. the first element is called "function position". It is invoked.

- `3` and `4` evaluate to themselves
- `+` evaluates to a function that implements `+`
- evaluating list invokes `+` function

Some languages have both statements and expressions. Statements have stateful effect but don't return a value. In clojure, everything is an expression that evaluates to a value.

### Delaying evaluation with quoting

Sometimes, symbols should just be a symbol without looking up what it refers to. Then we suspend evaluation with quoting:

``` clojure
'x
``` 

Sometimes, a list should just be a list of data values:

``` clojure
'(1 2 3)
``` 

### REPL

Clojure is always compiled to JVM bytecode. There is no clojure interpreter.

History of values of last expressions: `*1` `*2`...

``` clojure
user=> (+ 3 4)
7
user=> (+ 10 *1)
17
``` 

#### Helper functions

Helper functions: `doc`, `find-doc`, `apropos`, `source`, `dir`

``` clojure
(require '[clojure.repl :refer :all])
user=> (doc +)
``` 

You can use the `apropos` command to find functions that match a particular string or regular expression.

``` clojure
user=> (apropos "+")
(clojure.core/+ clojure.core/+')
``` 

`find-doc` search inside documentation 

`dir` list all functions in a namespace

``` clojure
(dir clojure.repl)
``` 

### Clojure basics

#### def var

``` clojure
(def x 7)
  ##> #'user/x
``` 

`def` associates symbol `x` with value `7`. 

This association is called a `var`

`#'user/x` is the return value. It is literal representation for a var `#'` 

`user` is the default namespace.

Symbols are evaluated by looking up what they refer to:

``` clojure
(+ x x)
  ##> 14
``` 

#### Printing

`println` and `print` for human-readable printing.

`prn` and `pr` for machine-readable printing. It puts quotes around strings.

``` clojure
user=> (println "What is this:" (+ 1 2))
What is this: 3
``` 

`print` and `println` functions have side-effects and returns nil as result.

## Clojure - Learn Clojure - Functions

`defn` defines a named function:

``` clojure
;;    name   params         body
;;    -----  ------  -------------------
(defn greet  [name]  (str "Hello, " name) )
``` 

### Multi-arity functions

A function can take different numbers of parameters:

``` clojure
(defn messenger
  ([]     (messenger "Hello world!"))
  ([msg]  (println msg)))
``` 

### Variadic functions

A function can define a variable number of parameters. 

The variable parameters must occur at the end of the parameter list.

``` clojure
(defn hello [greeting & who]
  (println greeting who))
``` 

Additional parameters are collected in a list `who`

``` clojure
user=> (hello "Hello" "world" "class")
Hello (world class)
``` 

### Anonymous Functions

``` clojure
;;    params         body
;;   ---------  -----------------
(fn  [message]  (println message) )
``` 

Usually it is passed to another function.

### defn vs fn

`defn` is both `def` and `fn`. `fn` defines the function. `def` binds it to a name

``` clojure
(defn greet [name] (str "Hello, " name))

(def greet (fn [name] (str "Hello, " name)))
``` 

### Anonymous function syntax

``` clojure
;; Equivalent to: (fn [x] (+ 6 x))
  #(+ 6 %)

;; Equivalent to: (fn [x y] (+ x y))
  #(+ %1 %2)

;; Equivalent to: (fn [x y & zs] (println x y zs))
  #(println %1 %2 %&)
``` 

### Gotcha

How to define an anonymous function that takes an element and wrapsi it in a vector?

3 options:

``` clojure
  #(vector %)
(fn [x] [x])
vector
``` 

### Applying Functions

`apply` is useful when arguments are handed to you as a sequence but you must invoke the function with the values in the sequence

For example, you can use apply to avoid writing this:

``` clojure
(defn plot [shape coords]   ;; coords is [x y]
  (plotxy shape (first coords) (second coords)))
``` 

Instead you can simply write:

``` clojure
(defn plot [shape coords]
  (apply plotxy shape coords))
``` 

### Locals and Closures

#### let

`let` binds symbols to values in a lexical scope.

``` clojure
;;      bindings     name is defined here
;;    ------------  ----------------------
(let  [name value]  (code that uses name))
``` 

``` clojure
(let [x 1
      y 2]
  (+ x y))
``` 

#### Closures

``` clojure
(defn messenger-builder [greeting]
  (fn [who] (println greeting who))) ; closes over greeting

;; greeting provided here, then goes out of scope
(def hello-er (messenger-builder "Hello"))

;; greeting value still available because hello-er is a closure
(hello-er "world!")
;; Hello world!
``` 

### Java Interop

#### Invoking java code

``` clojure
	| Task            | Java              | Clojure          |
	| Instantiation   | new Widget("foo") | (Widget. "foo")  |
	| Instance method | rnd.nextInt()     | (.nextInt rnd)   |
	| Instance field  | object.field      | (.-field object) |
	| Static method   | Math.sqrt(25)     | (Math/sqrt 25)   |
	| Static field    | Math.PI           | Math/PI          |
``` 

## Clojure - Learn Clojure - Sequential Collections

https://clojure.org/guides/learn/sequential_colls

### Vectors

``` clojure
[1 2 3]
user=> (get ["abc" false 99] 0)
  ##> "abc"
user=> (get ["abc" false 99] 1)
  ##> false
user=> (count [1 2 3])
3
user=> (vector 1 2 3)
[1 2 3]
user=> (conj [1 2 3] 4 5 6)
[1 2 3 4 5 6]
``` 

### Lists

Quote a list to prevent evaluation.

``` clojure
(def cards '(10 :ace :jack 9))
(first cards)
  ##> 10
(rest cards)
  ##> '(:ace :jack 9)
(conj cards :queen)
  ##> (:queen 10 :ace :jack 9)
(def stack '(:a :b))
  ##> #'user/stack
(peek stack)
  ##> :a
(pop stack)
  ##> (:b)
``` 


## Hashed Collections

https://clojure.org/guides/learn/hashed_colls

### Sets

``` clojure
(def players #{"Alice", "Bob", "Kelly"})
``` 

Adding to a set: `conj`

``` clojure
(conj players "Fred")
  ##> #{"Alice", "Bob", "Kelly", "Fred"}
``` 

Removing from a set: `disj`

``` clojure
(disj players "Fred")
  ##> #{"Alice", "Bob", "Kelly"}
``` 

Checking containment: `contains?`

``` clojure
(contains? players "Fred")
  ##> true
``` 

Concatenate two collections: `into`

``` clojure
(into players new-players)
``` 

### Maps

Two uses:

1. association of keys to values (like dictionaries in python)
2. domain application data

Creating a literal map:

``` clojure
(def scores {"Fred" 1400
  "Bob" 1240})
``` 

Adding new key-value pairs: `assoc`

``` clojure
(assoc scores "Sally" 0)
``` 

Removing: `dissoc`

``` clojure
(dissoc scores "Bob")
``` 

#### Look up: 

opt01: `get`

``` clojure
(get scores "Fred")
  ##> 1400
``` 

opt02: invoke map itself like a function

``` clojure
(def directions {:north 0
                        :east 1
                        :south 2
                        :west 3})
  #'user/directions
directions :north)
  ##> 0
``` 

Default value:

``` clojure
(get scores "Sam" 0)
  ##> 0
``` 

Checking contains:

``` clojure
(contains? scores "Fred")
  ##> true
(find scores "Fred")
  ##> ["Fred" 1400]
``` 

Get keys or values:

``` clojure
(keys scores)
  ##> ("Fred" "Bob" "Angela")
(vals scores)
  ##> (1400 1240 1024)
``` 

### Representing application domain information

Map with keyword keys:

``` clojure
(def person
	{:first-name "Kelly"
	 :last-name "Keen"
	 :age 32})
``` 

#### Field accessors

Looking up by key:

``` clojure
(get person :age)
  ##> 32
(person :age)
  ##> 32
(:age person) ; invoking keyword as function
  ##> 32
(:age person 30) ; default value
	##> 32
``` 

When a keyword is invoked, it looks itself up in the associative data structure that it was passed. 

Keywords are also functions like maps and sets.

Updating fields: `assoc`

Removing fields: `dissoc`

#### Nested entities

``` clojure
(def company
  {:name "WidgetCo"
   :address {:street "123 Main St"
             :city "Springfield"
             :state "IL"}})
``` 

Access using `get-in`

``` clojure
(get-in company [:address :city])
``` 

Add new field: `assoc-in`
Removing: `dissoc-in`

### Records

## Clojure - Learn Clojure - Flow Control

https://clojure.org/guides/learn/flow

``` clojure
(str "2 is " (if (even? 2) "even" "odd"))
  ##> 2 is even

(if (true? false) "impossible!") ;; else is optional
  ##> nil
``` 

Only false values are `false` and `nil`

Use `do` if there are multiple expressions.

``` clojure
(if (even? 5)
  (do (println "even")
      true)
  (do (println "odd")
      false))
``` 

`when` is an `if` with only a `then` branch:

``` clojure
(when (neg? x)
  (throw (RuntimeException. (str "x must be positive: " x))))
``` 

`cond` is multiple if:

``` clojure
(let [x 5]
  (cond
    (< x 2) "x is less than 2"
    (< x 10) "x is less than 10"))
``` 

`case` is switch:

``` clojure
user=> (defn foo [x]
         (case x
           5 "x is 5"
           10 "x is 10"))
  #'user/foo

user=> (foo 10)
x is 10

``` 

#### Iteration for Side Effects

`dotimes`

``` clojure
user=> (dotimes [i 3]
         (println i))
0
1
2
nil
``` 

`for` is list comprehension

``` clojure
user=> (for [letter [:a :b]
             number (range 3)] ; list of 0, 1, 2
         [letter number])
([:a 0] [:a 1] [:a 2] [:b 0] [:b 1] [:b 2])
``` 


