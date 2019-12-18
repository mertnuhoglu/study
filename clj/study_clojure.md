---
title: "Study clojure"
date: 2019-11-01T14:35:49+03:00 
draft: false
description: ""
tags:
categories: datomic, datalog
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

Ref:

		~/projects/study/clj/anki_clojure.md


# Questions

## lisp - Why does Clojure have "keywords" in addition to "symbols"? - Stack Overflow

https://stackoverflow.com/questions/1527548/why-does-clojure-have-keywords-in-addition-to-symbols

> Keywords are symbolic identifiers that evaluate to themselves. They provide very fast equality tests...

> Symbols are identifiers that are normally used to refer to something else. They can be used in program forms to refer to function parameters, let bindings, class names and global vars...

> Keywords are generally used as lightweight "constant strings", e.g. for the keys of a hash-map or the dispatch values of a multimethod. Symbols are generally used to name variable and functions and it's less common to manipulate them as objects

## mapv function

> Returns a vector consisting of the result of applying f to the
> set of first items of each coll, followed by applying f to the set
> of second items in each coll, until any one of the colls is
> exhausted.

Usage:

``` bash
(mapv f coll)
       (mapv f c1 c2)
       (mapv f c1 c2 c3)
``` 

``` bash
(mapv + [1 2] [3 4])
  ##> [4 6]
``` 

## hash-map function

https://clojuredocs.org/clojure.core/hash-map

``` bash
(hash-map)
  ##> {}
{}
  ##> {}
(hash-map :key1 1, :key1 2) ; overwrites repeated keys
  ##> {:key1 2}
(hash-map :key1 'val1)
  ##> {:key1 val1}
(hash-map [:compound :key] nil) ; compound key
  ##> {[:compound :key] nil} 
``` 

``` bash
(map #(hash-map % 0) (seq "abcdefgh"))
  ##> ({\a 0} {\b 0} {\c 0} {\d 0} {\e 0} {\f 0} {\g 0} {\h 0}) 
(apply hash-map (.split "a 1 b 2 c 3" " "))
  ##> {"a" "1", "b" "2", "c" "3"}
``` 

## namespaced keyword notations

``` bash
user=> #:a{:b :c}
  ##> #:a{:b :c}
user=> {:a/b :c}
  ##> #:a{:b :c}
``` 

## map-indexed function

``` bash
user=> (map-indexed (fn [idx itm] [idx itm]) "foobar")
([0 \f] [1 \o] [2 \o] [3 \b] [4 \a] [5 \r])
user=> (map-indexed hash-map "foobar")
({0 "f"} {1 "o"} {2 "o"} {3 "b"} {4 "a"} {5 "r"})
``` 

## vec function

``` bash
user=> (vec '(1 2 3))
[1 2 3]

user=> (vec [1 2 3])
[1 2 3]

user=> (vec #{1 2 3})
[1 3 2]

user=> (vec {:a 1 :b 2 :c 3})
[[:c 3] [:b 2] [:a 1]]

user=> (vec '())
[]

user=> (vec nil)
[]
``` 

# Freecodecamp Clojure

## Hashmaps

https://guide.freecodecamp.org/clojure/hashmaps/

Two ways to construct:

1. constructor function

``` bash
(hash-map :a 1 :b 2)
  ##> {:b 2, :a 1}
``` 

2. hashmap literal `{}`

``` bash
{:a 1 :b 2}
  ##> {:a 1, :b 2}
``` 

### Converting other collections to hashmaps

``` bash
(hash-map [:a 1 :b 2 :c 3])
  ##> ; => IllegalArgumentException No value supplied for key: [:a 1 :b 2 :c 3]
``` 

We need to use `apply`

It destructures a collection before applying a function to it:

``` bash
(apply + [1 2 3])
  ##> 6
(apply hash-map [:a 1 :b 2 :c 3])
  ##> ; => {:c 3, :b 2, :a 1}
``` 

# Setup Clojure

https://clojure.org/guides/deps_and_cli

``` bash
brew install clojure
clj
``` 

``` bash
(+ 2 3)
``` 

Create a deps.edn file to declare the dependency: `~/projects/study/clj/ex/study_clojure/ex01/deps.edn`

``` bash
cd ~/projects/study/clj/ex/study_clojure/ex01
clj
``` 

``` bash
(require '[clj-time.core :as t])
(str (t/now))
  ##> "2019-11-01T11:53:40.214Z"
``` 

## Writing a program

Edit `~/projects/study/clj/ex/study_clojure/ex01/src/hello.clj`

``` bash
clj -m hello
  ##> Hello world, the time is 02:57 PM
``` 

# Setup Leiningen

https://leiningen.org/

``` bash
wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
mv lein ~/lein
chmod a+x ~/bin/lein
lein
``` 

``` bash
lein help tutorial
``` 

unset $CLASSPATH variable

``` bash
unset CLASSPATH
``` 

# Clojure - Learn Clojure 

## Clojure - Learn Clojure - Syntax

https://clojure.org/guides/learn/syntax

### Strings (character types)

``` bash
"hello"         ; string
\e              ; character
  #"[0-9]+"       ; regular expression
``` 

### Symbols and idents:

``` bash
map             ; symbol
+               ; symbol - most punctuation allowed
clojure.core/+  ; namespaced symbol
nil             ; null value
true false      ; booleans
:alpha          ; keyword
:release/alpha  ; keyword with namespace
``` 

Literal collections:

``` bash
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

``` bash
'x
``` 

Sometimes, a list should just be a list of data values:

``` bash
'(1 2 3)
``` 

### REPL

Clojure is always compiled to JVM bytecode. There is no clojure interpreter.

History of values of last expressions: `*1` `*2`...

``` bash
user=> (+ 3 4)
7
user=> (+ 10 *1)
17
``` 

#### Helper functions

Helper functions: `doc`, `find-doc`, `apropos`, `source`, `dir`

``` bash
(require '[clojure.repl :refer :all])
user=> (doc +)
``` 

You can use the `apropos` command to find functions that match a particular string or regular expression.

``` bash
user=> (apropos "+")
(clojure.core/+ clojure.core/+')
``` 

`find-doc` search inside documentation 

`dir` list all functions in a namespace

``` bash
(dir clojure.repl)
``` 

### Clojure basics

#### def var

``` bash
(def x 7)
  ##> #'user/x
``` 

`def` associates symbol `x` with value `7`. 

This association is called a `var`

`#'user/x` is the return value. It is literal representation for a var `#'` 

`user` is the default namespace.

Symbols are evaluated by looking up what they refer to:

``` bash
(+ x x)
  ##> 14
``` 

#### Printing

`println` and `print` for human-readable printing.

`prn` and `pr` for machine-readable printing. It puts quotes around strings.

``` bash
user=> (println "What is this:" (+ 1 2))
What is this: 3
``` 

`print` and `println` functions have side-effects and returns nil as result.

## Clojure - Learn Clojure - Functions

`defn` defines a named function:

``` bash
;;    name   params         body
;;    -----  ------  -------------------
(defn greet  [name]  (str "Hello, " name) )
``` 

### Multi-arity functions

A function can take different numbers of parameters:

``` bash
(defn messenger
  ([]     (messenger "Hello world!"))
  ([msg]  (println msg)))
``` 

### Variadic functions

A function can define a variable number of parameters. 

The variable parameters must occur at the end of the parameter list.

``` bash
(defn hello [greeting & who]
  (println greeting who))
``` 

Additional parameters are collected in a list `who`

``` bash
user=> (hello "Hello" "world" "class")
Hello (world class)
``` 

### Anonymous Functions

``` bash
;;    params         body
;;   ---------  -----------------
(fn  [message]  (println message) )
``` 

Usually it is passed to another function.

### defn vs fn

`defn` is both `def` and `fn`. `fn` defines the function. `def` binds it to a name

``` bash
(defn greet [name] (str "Hello, " name))

(def greet (fn [name] (str "Hello, " name)))
``` 

### Anonymous function syntax

``` bash
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

``` bash
  #(vector %)
(fn [x] [x])
vector
``` 

### Applying Functions

`apply` is useful when arguments are handed to you as a sequence but you must invoke the function with the values in the sequence

For example, you can use apply to avoid writing this:

``` bash
(defn plot [shape coords]   ;; coords is [x y]
  (plotxy shape (first coords) (second coords)))
``` 

Instead you can simply write:

``` bash
(defn plot [shape coords]
  (apply plotxy shape coords))
``` 

### Locals and Closures

#### let

`let` binds symbols to values in a lexical scope.

``` bash
;;      bindings     name is defined here
;;    ------------  ----------------------
(let  [name value]  (code that uses name))
``` 

``` bash
(let [x 1
      y 2]
  (+ x y))
``` 

#### Closures

``` bash
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

``` bash
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

``` bash
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

``` bash
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

``` bash
(def players #{"Alice", "Bob", "Kelly"})
``` 

Adding to a set: `conj`

``` bash
(conj players "Fred")
  ##> #{"Alice", "Bob", "Kelly", "Fred"}
``` 

Removing from a set: `disj`

``` bash
(disj players "Fred")
  ##> #{"Alice", "Bob", "Kelly"}
``` 

Checking containment: `contains?`

``` bash
(contains? players "Fred")
  ##> true
``` 

Concatenate two collections: `into`

``` bash
(into players new-players)
``` 

### Maps

Two uses:

1. association of keys to values (like dictionaries in python)
2. domain application data

Creating a literal map:

``` bash
(def scores {"Fred" 1400
  "Bob" 1240})
``` 

Adding new key-value pairs: `assoc`

``` bash
(assoc scores "Sally" 0)
``` 

Removing: `dissoc`

``` bash
(dissoc scores "Bob")
``` 

#### Look up: 

opt01: `get`

``` bash
(get scores "Fred")
  ##> 1400
``` 

opt02: invoke map itself like a function

``` bash
(def directions {:north 0
                        :east 1
                        :south 2
                        :west 3})
  #'user/directions
directions :north)
  ##> 0
``` 

Default value:

``` bash
(get scores "Sam" 0)
  ##> 0
``` 

Checking contains:

``` bash
(contains? scores "Fred")
  ##> true
(find scores "Fred")
  ##> ["Fred" 1400]
``` 

Get keys or values:

``` bash
(keys scores)
  ##> ("Fred" "Bob" "Angela")
(vals scores)
  ##> (1400 1240 1024)
``` 

### Representing application domain information

Map with keyword keys:

``` bash
(def person
	{:first-name "Kelly"
	 :last-name "Keen"
	 :age 32})
``` 

#### Field accessors

Looking up by key:

``` bash
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

``` bash
(def company
  {:name "WidgetCo"
   :address {:street "123 Main St"
             :city "Springfield"
             :state "IL"}})
``` 

Access using `get-in`

``` bash
(get-in company [:address :city])
``` 

Add new field: `assoc-in`
Removing: `dissoc-in`

### Records

## Clojure - Learn Clojure - Flow Control

https://clojure.org/guides/learn/flow

``` bash
(str "2 is " (if (even? 2) "even" "odd"))
  ##> 2 is even

(if (true? false) "impossible!") ;; else is optional
  ##> nil
``` 

Only false values are `false` and `nil`

Use `do` if there are multiple expressions.

``` bash
(if (even? 5)
  (do (println "even")
      true)
  (do (println "odd")
      false))
``` 

`when` is an `if` with only a `then` branch:

``` bash
(when (neg? x)
  (throw (RuntimeException. (str "x must be positive: " x))))
``` 

`cond` is multiple if:

``` bash
(let [x 5]
  (cond
    (< x 2) "x is less than 2"
    (< x 10) "x is less than 10"))
``` 

`case` is switch:

``` bash
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

``` bash
user=> (dotimes [i 3]
         (println i))
0
1
2
nil
``` 

`for` is list comprehension

``` bash
user=> (for [letter [:a :b]
             number (range 3)] ; list of 0, 1, 2
         [letter number])
([:a 0] [:a 1] [:a 2] [:b 0] [:b 1] [:b 2])
``` 

# Clojure Documentation | Clojure Docs

http://clojure-doc.org/articles/content.html#essentials

## Getting Started

### Trying out the REPL

``` bash
lein repl
``` 

### Your first project

``` bash
lein new app proj01
``` 

Check `~/projects/study/clj/ex/study_clojure/ex02/proj01`

``` bash
cd proj01
lein run
  ##> Retrieving org/clojure/clojure/1.10.0/clojure-1.10.0.pom from central
  ##> Retrieving org/clojure/clojure/1.10.0/clojure-1.10.0.jar from central
  ##> Hello, World!
``` 

### Interactive Development

``` bash
cd ~/projects/study/clj/ex/study_clojure/ex02/proj01
lein repl
(-main)
  ##> Hello, World!
  ##> nil
``` 

Edit `~/projects/study/clj/ex/study_clojure/ex02/proj01/src/proj01/core.clj`

Reload the code:

``` bash
(require 'proj01.core :reload)
(-main)
  ##> Hello, World!2
``` 

## Introduction to Clojure | Clojure Documentation | Clojure Docs

http://clojure-doc.org/articles/tutorials/introduction.html

``` bash
5
"hi"
[1 2 3]
(+ 1 2)
(if true "yes" "no")
(println "hello!")
``` 

Sub-expressions:

``` bash
(+ 1
	(* 2 3)
	(/ 10 2)
)
  ##> 12
``` 

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

Cloujure logic is written using its data structures. Using the same syntax for both data and olgic allows for powerful metaprogramming features.

Code is data and data is code.

## Functions

``` bash
(function-name param1 param2)
``` 

The function call is simply a list containing the function name and its parameters. A list is reserved for creating callable expressions. 

Creating a list:

``` bash
(list 1 2 3)
``` 

### Anonymous Functions

Functions that aren't bound to a name.

``` bash
(fn [arg] (println arg))
``` 

We can call this function by settings it as a first item in a list and its argument as the second.

``` bash
((fn [arg] (println arg)) "hello")
  ##> hello
``` 

Syntactic sugar for defining anonymous functions using the `#` notation

``` bash
  #(println %)
``` 

`%` indicates an unnamed argument

If multiple arguments exist:

``` bash
  #(println %1 %2 %3)
``` 

### Named Functions

Creating global variables using `def`

``` bash
(def double
  (fn [x] (* 2 x)))
``` 

It accepts a name and the body.

Syntactic sugar for this: `defn`

``` bash
(defn square [x]
  (* x x))
``` 

The body can consist of multiple expressions:

``` bash
(defn bmi [height weight]
  (println "height:" height)
  (println "weight:" weight)
  (/ weight (* height height)))
``` 

Clojure uses a single pass compiler. So, the functions must be declared before they are used.

``` bash
(declare down)

(defn up [n]
  (if (< n 10)
    (down (+ 2 n)) n))

(defn down [n]
  (up (dec n)))
``` 

### Higher-Order Functions

``` bash
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

``` bash
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

``` bash
(reduce + (interpose 5 (map inc (range 10))))
``` 

We want to read them in linear form:

``` bash
(->> (range 10) (map inc) (interpose 5) (reduce +))
``` 

`->>` works as a pipe. The result of each expression is passed as the last argument of the next expression. 

`->` as first argument.

### Laziness

## Code Structure

``` bash
(println
  (filter #(= (mod % 2) 0)
    (map #(* % %) (range 1 6))))
``` 

Flattened steps:

``` bash
(->> (range 1 6)
     (map #(* % %))
     (filter #(= (mod % 2) 0))
     (println))
``` 

Each function returns a new value instead of modifying existing data in place.

Clojure uses persistent data structures that create in-memory revisions of the data.

### Destructuring

``` bash
(let [[smaller bigger] (split-with #(< % 5) (range 10))]
    (println smaller bigger))

  ##> =>(0 1 2 3 4) (5 6 7 8 9)
``` 

`split-with` returns two elements: numbers less than 5 and greater than 5. They are bound to `smaller` and `bigger` variables.

``` bash
(defn print-user [[name address phone]]
  (println name "-" address phone))

(print-user ["John" "397 King street, Toronto" "416-936-3218"])
=> "John - 397 King street, Toronto 416-936-3218"
``` 

`print-user` takes 3 arguments: `name`, `address`, and `phone`

``` bash
(defn print-args [& args]
  (println args))

(print-args "a" "b" "c") => (a b c)
``` 

`print-args` takes variable arguments.

Variable arguments can be destructured too:

``` bash
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

``` bash
(let [{foo :foo bar :bar} {:foo "foo" :bar "bar"}]
  (println foo bar))
  ##> foo bar
``` 

``` bash
(let [{f :foo b :bar} {:foo "hello" :bar "world"}]
  (println f b))
  ##> hello world
``` 

Destructuring nested maps:

``` bash
(let [{[a b c] :items id :id} {:id "foo" :items [1 2 3]}]
  (println id "->" a b c))
  ##> foo -> 1 2 3
``` 

Syntactic sugar for extracting keys from maps:

``` bash
(defn login [{:keys [user pass]}]
 (and (= user "bob") (= pass "secret")))

(login {:user "bob" :pass "secret"})
``` 

Another useful destructuring option allows us to extract some keys while preserving the original map:

``` bash
(defn register [{:keys [id pass repeat-pass] :as user}]
  (cond
    (nil? id) "user id is required"
    (not= pass repeat-pass) "re-entered password doesn't match"
    :else user))
``` 

### Namespaces

``` bash
(ns colors)

(defn hex->rgb [[_ & rgb]]
    (map #(->> % (apply str "0x") (Long/decode))
         (partition 2 rgb)))
``` 

Calling a function from another namespace: `:use` or `require`

``` bash
(ns myns
  (:use colors))

(hex->rgb "#33d24f")
``` 

`:use` makes all variables implicitly available

`:only` restricts imported variables:

``` bash
(ns myns
  (:use [colors :only [rgb->hex]]))
``` 

`:require` requires variables to be prefixed:

``` bash
(ns myns (:require colors))

(colors/hex->rgb "#324a9b")
``` 

`:as` alias for imported variables.

``` bash
(ns myotherns
  (:require [colors :as c]))

(c/hex->rgb "#324a9b")
``` 

### Dynamic Variables

### Polymorphism

#### Multimethods

``` bash
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

``` bash
(defprotocol Foo
  "Foo doc string"
  (bar [this b] "bar doc string")
  (baz [this] [this b] "baz doc string"))
``` 

`Foo` protocol specifies two methods: `bar` and `baz`

# edn-format/edn: Extensible Data Notation

https://github.com/edn-format/edn

edn: extensible dat anotation

Superset of edn is clojure programs. 

edn is for values. It is not a type system. It has no schemas. It is not for representing object since there are no reference types. 

There is no enclosing element at the top level. Thus it is suitable for streaming.

Base set of elements covers basic set of data structures. 

## General Considerations

`#` dispatch character: Tokens beginning with `#` are reserved. 

## Built-in elements

`nil`: nil, null or nothing.

`booleans`: `true` and `false`

`strings` are enclosed in `"double quotes"`. Multiple lines +. `\t \r \n \\ \"`

`characters` are preceded by a backslash `\c, \newline \return \space \tab \uNNNN`

`symbols`: represent identifiers.

`/`: `namespace/foo`

`keywords`: identifiers that designate themselves. `:fred :my/fred`. correspond to enumeration values.

`integers`

`floating point numbers`

`M` suffix exact precision is desired

`lists` (a b 42)

`vectors` like lists but supports random access. `[a b 42]`

`maps` associations between keys and values. `{:a 1, "foo" :bar, [1 2 3] four}`

keys and values can of any type.o

commas are parsed as whitespace

`sets`: `#{a b [1 2 3]}`

## tagged elements

`tagged elements`

``` bash
#myapp/Person {:first "Fred" :last "Mertz"}
``` 

### built-in tagged elements

Instant in time

``` bash
#inst "1985-04-12T23:20:50.52Z"
``` 

UUID

---

commens: `;`

# Book: Programming in Clojure - Alex Miller, Stuart Halloway, Aaron Bedra

## Chapter 1 - Getting Started

### Clojure is Elegant

``` bash
(defn blank? [str]
  (every? #(Character/isWhitespace %) str))
``` 

No for loop. No if branching. No mutable state.

``` bash
user=> (defrecord Person [first-name last-name])
  ##> user.Person
user=> (def foo (->Person "Aaron" "Bedra"))
  ##> #'user/foo
user=> (:first-name foo)
  ##> "Aaron"
``` 

### Clojure is Lisp Reloaded

Clojure itself is built out of macros such as `defrecord`

``` bash
(defrecord name [arg1 arg2 arg3])
``` 

If you need different semantics, write your own macro.

Lisp is homoiconic. That is, Lisp code is Lisp data. Therefore programs can easily write new programs.

The whole language is there. 

Clojure generalizes Lisp's list into a sequence. This extends that power to other data structures.

Symbol resolution and syntax quoting makes writing macros easier.

Function parameters are in a vector `[]` not a list `()`

Commas to separate elements are optional: `[1 2 3]`

No parantheses more than necessary.

Clojure avoids extra parantheses:

``` bash
(cond (= x 10) "equal"
      (> x 10) "more")
``` 

### Clojure is a functional language.

But it is not pure functional like haskell.

- functions as first-class objects
- Data is immutable
- Functions are pure

Ex: 

``` bash
(for [c compositions :when (= (:name c) "Requiem")] (:composer c))
``` 

`for` makes a list comprehension read as: "For each `c` in `compositions`, where the name of `c` is `Requiem`, yield the `composer` of `c`"

- Simple: no loops, variables, mutable state
- Thread safe: no locking needed
- Parallelizable: without changing code
- Generic: `compositions` can be set, XML, database.

Why has fp not taken over? Clojure gives new benefits:

- Pure fp makes modelling state awkward.
- Dynamic typing is easier. Specs let describe data and functions with better expression power than static types.

### Clojure simplifies concurrent programming

1. Immutable data

2. Software transactional memory

Ex:

``` bash
(def accounts (ref #{}))
(defrecord Account [id balance])
(dosync
  (alter accounts conj (->Account "CLJ" 100.00)))
``` 

`ref`: creates a reference to the database

`dosync` updates database in a transaction.

Transaction guarantees thread safety.

### Clojure embraces the JVM

``` bash
(System/getProperties)
``` 

``` java
"hello".getClass().getProtectionDomain()
``` 

``` bash
(.. "hello" getClass getProtectionDomain)
``` 

All clojure functions implement `Callable` and `Runnable`

``` bash
(.start (new Thread (fn [] (println "Hello" (Thread/currentThread)))))
``` 

### Clojure Coding Quick Start

``` bash
(println "hello world")
(defn hello [name] (str "Hello, " name))
(hello "Stu")
``` 

#### Special Variables

`*1 *2 ...`: result of most recent evaluation

``` bash
(str *1 " and " *2)
``` 

`(pst)` print stack trace

Load file from REPL:

``` bash
(load-file "file.clj")
``` 

#### Adding Shared State

Empty set: `#{}`

`conj`: conjoin = add new item = concatenate
 
``` bash
(conj #{} "Ali")
  ##> -> #{"Ali"}
``` 

Reference types (refs):

- atom

``` bash
(atom initial-state)
``` 

Name using `def`

``` bash
(def symbol initial-value?)
``` 

`def` is more general than `defn`

`def` can define functions or data

`atom` creates an atom

``` bash
(def visitors (atom #{}))
  ##> #'user/visitors
``` 

Update reference using `swap!` function

``` bash
(swap! r update-fn & args)
``` 

`swap!` applies `update-fn` to reference `r` with optional `args`

``` bash
(swap! visitors conj "Stu")
  ##> #{"Stu"}
``` 

Check the ref with `deref` or `@`

``` bash
(deref visitors)
  ##> #{"Stu"}
@visitors
  ##> #{"Stu"}
``` 

Check `~/codes/clojure/programming_in_clojure/code/test01/src/examples/introduction.clj`

``` bash
(defn hello 
  "Writes hello message to *out*. Calls you by username.
  Knows if you have been here before."
  [username]
  (swap! visitors conj username)
  (str "Hello, " username))
``` 

``` bash
(hello "Rich")
@visitors
  ##> #{"Stu" "Rich"}
``` 

### Navigating Clojure Libraries

import a namespace (package)

``` bash
(require 'some-namespace)
(require 'clojure.java.io)
``` 

Leading single quote `'`: it quotes library name

``` bash
(require 'examples.introduction)
``` 

#### Error: classpath

``` bash
(require 'examples.introduction)
  ##> Could not locate examples/introduction__init.class, examples/introduction.clj or examples/introduction.cljc on classpath.
``` 

opt01: leiningen

``` bash
lein new app test01
cd test01
lein repl
``` 

``` bash
(require 'examples.introduction)
``` 

Now, it works

---


``` bash
(take 10 examples.introduction/fibs)
  ##> (0 1 1 2 3 5 8 13 21 34)
``` 

`doc`:

``` bash
### test01.core=> (doc str)
clojure.core/str
([] [x] [x & ys])
  With no args, returns the empty string. With one arg x, returns
  x.toString().  (str nil) returns the empty string. With more than
  one arg, returns the concatenation of the str values of the args.
nil
``` 

``` bash
(defn hello
	"This is doc string"
	[username]
	(println (str "Hello, " username))
)
``` 

`find-doc`: search for anything

``` bash
(find-doc "reduce")
``` 

`clojure.repl/source`: source code 

``` bash
(require '[clojure.repl :refer [source]])
(source identity)
  ##> (defn identity
  ##>   "Returns its argument."
  ##>   {:added "1.0"
  ##>    :static true}
  ##>   [x] x)
  ##> nil
``` 

``` bash
(instance? java.util.Collection [1 2 3])
  ##> true
``` 

#### Conventions for Parameter Names

Don't use a function's name as parameter name or the function will be shadowed.

## Chapter 2: Exploring Clojure

### Reading Clojure

``` bash
test01.core=> (+ 2 3)
5
test01.core=> (+ 1 2 3)
6
test01.core=> (+)
0
``` 

Arbitrary-precision numbers: `BigDecimal`: append `M` to a number

``` bash
(+ 1 0.0001M)
  ##> 1.0001M
``` 

Arbitrary-size integers: `BigInt`: append `N`

### Symbols

Symbols name all sorts of things:

- Functions
- Operators
- Java classes
- Namespaces and packages

They cannot start with a number. They can contain: `+ - * / ! ? . _ '`

`/ .` are used to support namespaces

### Collections

4 primary collection types: lists, vectors, sets, and maps.

All collections are heterogeneous

Equality is based on their contents.

Vectors: indexed collections

Lists: linked list

We can't create a literal list as we can with vectors. Function calls are represented as ilsts. 

To make a list we need to `quote`: 

``` bash
(quote (1 2 3))
``` 

``` bash
test01.core=> (quote (1 2 3))
(1 2 3)
test01.core=> (1 2 3)
Execution error (ClassCastException) at test01.core/eval1601 (form-init4977993031106662145.clj:1).
java.lang.Long incompatible with clojure.lang.IFn
``` 

Short form of `quote`

``` bash
'(1 2 3)
``` 

Sets: unordered collections

- Fast element addition/removal
- Uniqueness
- Check if a set contains a value

Maps: collections of key/value pairs

``` bash
{"a" 1 "b" 2}
``` 

Any data structure can be a key in a map.

Most common key type is the clojure keyword.

Keyword: begin with colon. They resolve to themselves.

``` bash
:foo
``` 

If several maps have keys in common, create a record:

``` bash
(defrecord Book [title author])
``` 

Instantiate that record with `->Book` constructor function:

``` bash
(->Book "title01" "author01")
  ##> #test01.core.Book{:title "title01", :author "author01"}
``` 

### Strings and Characters

`str`: takes any objects, converts them to strings and concatenates them

``` bash
(str 1 2 nil 3)
  ##> "123"
``` 

Characters: `\{letter} \space \n \r \t`

### Booleans and nil

- `true` `false`
- `nil` evaluates to `false`
- Everything else evaluates to `true`

Predicate: a function that returns true or false.

Naming convention: trailing question mark for predicates:

``` bash
(true? expr)
(false? expr)
(nil? expr)
(zero? expr)
``` 

Only `nil` is `nil?`, only `true` is `true?`

``` bash
(true? true)
  ##> true
(true? "foo")
  ##> false
``` 

Find other predicate functions:

``` bash
(find-doc #"\?$")
``` 

`#"\?$"` is a literal regex.

### Functions

``` bash
(str "hello" " " "world")
  ##> "hello world"
``` 

A function call is a list whose first element resolves to a function.

Function names are usually hyphenated.

Predicate functions end with a question mark.

``` bash
(string? "hello")
(keyword? :hello)
(symbol? 'hello)
``` 

To define functions: `defn`

``` bash
(defn name doc-string? attr-map? [params*] prepost-map? body)
``` 

`attr-map`: associates metadata with function's var

`prepost-map?`: defines pre and postconditions

`&`: variable arity

``` bash
(defn date [person-1 person-2 & chaperones]
  (println person-1 "and" person-2 
	   "went out with" (count chaperones) "chaperones."))
(date "Ali" "Ayse" "Ahmet" "Doktor")
  ##> Ali and Ayse went out with 2 chaperones.
``` 

### Anonymous Functions

`fn`: define anonymous function

`defn`: define named function

Ex: filter words longer than 2 letters

opt01: Using `fn`

``` bash
(filter (fn [w] (> (count w) 2)) (str/split "A fine day" #"\W+"))
  ##> ("fine" "day")
``` 

opt02: Using `#(body)`

`#(body)`: shorter form for anonymous functions

``` bash
(filter #(> (count %) 2) (str/split "A fine day" #"\W+"))
``` 

Parameters named: `%1 %2` final `%&` collects the rest

`%` is also first parameter

opt03: Using `let`

``` bash
(defn indexable-words [text]
  (let [indexable-word? (fn [w] (> (count w) 2))
		(filter indexable-word? (str/split text #"\W+"))]))
``` 

`let`: defines a new name inside the lexical scope

opt04: create a function dynamically 

``` bash
(defn make-greeter [greeting-prefix]
  (fn [username] (str greeting-prefix ", " username)))
``` 

You can use `def` to name functions created by `make-greeter`

``` bash
(def hello-greeting (make-greeter "Hello"))
``` 

Or 

``` bash
((make-greeter "Howdy") "pardner")
``` 

#### When to use Anonymous Functions

### Vars, Bindings, and Namespaces

A namespace is a collection of names (symbols) that refer to vars.

Each var is bound to a value.

#### Vars

`def` and `defn` defines an object that is stored in var. 

``` bash
(def foo 10)
  ##> #'test01.core/foo
``` 

This creates a var named `user/foo`

`user/foo` symbol refers to a var that is bound to the value `10`

root binding: The initial value of a var 

Referring to a var directly: `var`

``` bash
(var a-symbol)
(var foo)
  ##> #'test01.core/foo
``` 

`var`: equivalent reader macro `#'`

``` bash
 #'foo
  ##> #'test01.core/foo
``` 

Why refer to a var directly?

#### Bindings

``` bash
(defn triple [number] (* 3 number))
(triple 10)
  ##> 30
``` 

`let` creates a set of lexical bindings

``` bash
(let [bindings*] exprs*)
``` 

`bindings`: multiple bindings 

Value of `let` expression is the value of last `exprs`

``` bash
(defn square-corners [bottom left size]
  (let [top (+ bottom size)
	right (+ left size)]
    [[bottom left] [top left] [top right] [bottom right]]))
``` 

#### Destructuring

opt01: binding the complete data structure 

``` bash
(defn greet-author-1 [author]
  (println "Hello," (:first-name author)))
``` 

Calling it:

``` bash
(greet-author-1 {:last-name "X" :first-name "Ali"})
  ##> Hello, Ali
``` 

opt02: binding only part of the collection

``` bash
(defn greet-author-2 [{fname :first-name}]
  (println "Hello," fname))
``` 

``` bash
(greet-author-2 {:last-name "X" :first-name "Ali"})
  ##> Hello, Ali
``` 

`{fname :first-name}` binds `fname` to `:first-name` of the function argument

Ex: bind only the first two vars in a binding:

``` bash
(let [[x y] [1 2 3]]
  [x y])
  ##> [1 2]
``` 

Ex: skip elements

``` bash
(let [[_ _ z] [1 2 3]]
  z)
	##> 3
``` 

Ex: bind both a collection and elements

``` bash
(let [[x y :as coords] [1 2 3 4]]
  (str "x " x ", y " y " total coords " (count coords)))
  ##> "x 1, y 2 total coords 4"
``` 

Ex: take a string and return first three words

``` bash
(require '[clojure.string :as str])
(defn ellipsize [words]
  (let [[w1 w2 w3] (str/split words #"\s+")]
    (str/join " " [w1 w2 w3 "..."])))
``` 

``` bash
(ellipsize "The quick brown fox jumps")
  ##> "The quick brown ..."
``` 

#### Namespaces

Root bindings live in a namespace.

`resolve`: returns var that a symbol will resolve to in the current namespace

``` bash
(resolve 'foo)
  ##> #'test01.core/foo
``` 

`in-ns`: create/switch namespace

``` bash
(in-ns 'myapp)
``` 

You should `use` `clojre.core` namespace when you move to a new namespace.

``` bash
(clojure.core/use 'clojure.core)
``` 

Class names outside `java.lang` must be fully qualified:

``` bash
java.io.File/separator
  ##> "/"
``` 

`import` allows using short names:

``` bash
(import '(java.io InputStream File))
  ##> java.io.File
``` 

`import` is for java classes.

`require` and `:as` is for clojure functions

``` bash
(require 'clojure.string)
(clojure.string/split "Ali,Veli,Ayse" #",")
  ##> ["Ali" "Veli" "Ayse"]
(split "Ali,Veli,Ayse" #",")
  ##> Unable to resolve symbol: split in this context
(require '[clojure.string :as str])
(str/split "Ali,Veli,Ayse" #",")
  ##> ["Ali" "Veli" "Ayse"]
``` 

`ns` macro: sets current namespace and imports other namespaces

``` bash
(ns name & references)
``` 

`references` can include `:import` `:require` and `:use`

``` bash
(ns examples.exploring
  (:require [clojure.string :as str])
	(:import (java.io File)))
``` 

#### Metadata

Metadata is data that is orthogonal to the logical value of an object. 

``` bash
(meta #'str)
  ##> {:added "1.0", :ns #object[clojure.lang.Namespace 0x1caefa76 "clojure.core"], :name str, :file "clojure/core.clj", :static true, :column 1, :line 544, :tag java.lang.String, :arglists ([] [x] [x & ys]), :doc "With no args, returns the empty string. With one arg x, returns\n  x.toString().  (str nil) returns the empty string. With more than\n  one arg, returns the concatenation of the str values of the args."}
``` 

Common metadata keys:

``` bash
:ns	Namespace
...
``` 

Add your own key/value pairs:

``` bash
^metadata form
``` 

``` bash
(defn ^{:tag String} shout [^{:tag String} s] (clojure.string/upper-case s))
``` 

``` bash
(meta #'shout)
  ##> {:tag java.lang.String, :arglists ([s]), :line 1, :column 1, :file "/private/var/folders/f9/d201s84j0gb95830cjhp09_m0000gq/T/form-init4977993031106662145.clj", :name shout, :ns #object[clojure.lang.Namespace 0x463928b7 "user"]}
``` 

`^Classname`: short form for `^{:tag Classname}`

``` bash
(defn ^String shout [^String s] (clojure.string/upper-case s))
``` 

#### Calling Java

##### trailing dot for new

``` bash
(new classname)
``` 

``` bash
(new java.util.Random)
``` 

`.`: trailing dot. short form for `new`

``` bash
(java.util.Random.)
``` 

``` bash
(def rnd (java.util.Random.))
``` 

##### dot syntax

Call methods: using dot special form

``` bash
(. class-or-instance member-symbol & args)
(. class-or-instance (member-symbol & args))
``` 

``` bash
(. rnd nextInt)
  ##> -981148039
(. rnd nextInt 10)
  ##> 8
(def p (java.awt.Point. 10 20))
(. p x)
  ##> 10
(. Math PI)
  ##> 3.141592653589793
``` 

`.` syntax used for:

- calling class/instance/static methods/fields

`-`: prefix for fields only

``` bash
(. p -x)
  ##> 10
``` 

##### short form for dot syntax

``` bash
(.method instance & args)
(.field instance)
(.-field instance)
(Class/method & args)
Class/field
``` 

``` bash
(.nextInt rnd 10)
  ##> 10
(.x p)
  ##> 10
(.-x p)
  ##> 10
(System/lineSeparator)
  ##> "\n"
Math/PI
  ##> 3.141592653589793
``` 

##### import

``` bash
(import (package-symbol & class-name-symbols)*)
``` 

``` bash
(import '(java.util Random Locale)
  '(java.text MessageFormat))
Random
  ##> java.util.Random
Locale
  ##> java.util.Locale
``` 

`javadoc`

``` bash
(require '[clojure.java.javadoc :as jdoc])
(jdoc/javadoc java.net.URL)
``` 

### Comments

`;`: create comment

``` bash
;; this is a comment
``` 

`#_`: ignore line

``` bash
 #_(println "hello")
``` 

### Flow Control

#### if

``` bash
(defn is-small? [number]
  (if (< number 100) "yes"))
``` 

``` bash
(is-small? 50)
  ##> "yes"
``` 

`else` is third argument

``` bash
(defn is-small? [number]
  (if (< number 100) "yes" "no"))
(is-small? 500)
  ##> "no"
``` 

#### do

`do` is for side effects

``` bash
(defn is-small? [number]
  (if (< number 100)
    "yes"
    (do 
      (println "Saw a big number" number)
      "no")))
``` 

``` bash
(is-small? 500)
  ##> Saw a big number 500
  ##> "no"
``` 

#### loop/recur

opt01: use `loop` and `recur`

``` bash
(loop [bindings*] exprs*)
``` 

`loop` sets a recursion point, which is targeted by `recur`

``` bash
(recur exprs*)
``` 

``` bash
(loop [result [] x 5]
	(if (zero? x)
		result
		(recur (conj result x) (dec x))))
``` 

`loop` binds `result` to `[]` and `x` to `5`

`recur` rebinds `x` and `result`

opt02: use function and `recur`

``` bash
(defn countdown [result x]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))
``` 

``` bash
(countdown [] 5)
  ##> [5 4 3 2 1]
``` 

opt03: use sequence library

``` bash
(into [] (take 5 (iterate dec 5)))
  ##> [5 4 3 2 1]
(into [] (drop-last (reverse (range 6))))
  ##> [5 4 3 2 1]
(vec (reverse (rest (range 6))))
  ##> [5 4 3 2 1]
``` 

#### Where's My for Loop?

There is no `for` loop.

`StringUtils.indexOfAny` implementation in clojure:

``` bash
(defn indexed [coll] (map-indexed vector coll))
(indexed "abcde")
  ##> ([0 \a] [1 \b] [2 \c] [3 \d] [4 \e])
(defn index-filter [pred coll]
  (when pred 
    (for [[idx elt] (indexed coll) :when (pred elt)] idx)))
(index-filter #{\a \b} "abcdbbbb")
  ##> (0 1 4 5 6 7)
(defn index-of-any [pred coll]
  (first (index-filter pred coll)))
(index-of-any #{\z \a} "zzabyccdxx")
  ##> 0
``` 

## Chapter 3 - Unifying Data with Sequences

At the lowest level, we have data structures: strings, lists, vectors, maps, trees, sets.

At the higher level, we have data structures again: xml (tree), database result sets (lists), directory hierarchies (tree), files (string)

`seq`: single abstraction for all these data structures

seq is a logical list. 

Logical: it is not a concrete implementation

seq-able collections:

- all clojure collections
- all java collections
- java arrays and strings
- regex matches
- directory structures
- IO streams
- XML trees

### Everything Is a Sequence

Every aggregate data structure is a sequence.

`first` item in a sequence

``` bash
(first aseq)
``` 

`rest` everything after first item

``` bash
(rest aseq)
``` 

`cons` construct a new sequence by adding an item to the front:

``` bash
(cons elem aseq)
``` 

They are declared in interface: `clojure.lang.ISeq`

`seq` return a seq an any seq-able collection:

``` bash
(seq coll)
``` 

`next`: return seq of item after the first

``` bash
(next aseq)
``` 

Equivalent to: `(seq (rest aseq))`

``` bash
(first '(1 2 3))
  ##> 1
(rest '(1 2 3))
  ##> (2 3)
(cons 0 '(1 2 3))
  ##> (0 1 2 3)
``` 

#### The Origin of Cons

Sequence is an abstraction based on Lisp's concrete lists.

In Lisp, three basic list operations are: `car`, `cdr`, and `cons`

`car` and `cdr` are acronyms that come from the implementation of Lisp on IBM 704.

Now, they are named as `first` and `rest`

`cons` as a name or adjective: new data structure is called a cons cell or just a cons

`cons` constructs a new sequence but with one element added.

---

Result of `rest` or `cons` on a vector is a seq, not a vector. 

seqs printed like lists.

Check the returned type by `seq?` predicate

``` bash
(seq? (rest [1 2 3]))
  ##> true
``` 

maps as seqs:

``` bash
(first {:fname "Ali" :lname "Ak"})
  ##> [:fname "Ali"]
(rest {:fname "Ali" :lname "Ak"})
  ##> ([:lname "Ak"])
(cons [:mname "Bey"] {:fname "Ali" :lname "Ak"})
  ##> ([:mname "Bey"] [:fname "Ali"] [:lname "Ak"])
``` 

sets as seqs:

``` bash
(first #{:the :quick :brown})
  ##> :the
(rest #{:the :quick :brown})
  ##> (:quick :brown)
(cons :fox #{:the :quick :brown})
  ##> (:fox :the :quick :brown)
``` 

To get a reliable order in sets use:

``` bash
(sorted-set & elements)
``` 

``` bash
(sorted-set :the :quick :brown)
  ##> #{:brown :quick :the}
``` 

To get a reliable order in maps use:

``` bash
(sorted-map & elements)
``` 

`conj`: adds elements to a collection

`into`: adds items in a collection to another

``` bash
(conj coll element & elements)
(into to-coll from-coll)
``` 

``` bash
(conj '(1 2 3) :a)
  ##> (:a 1 2 3)
(cons :a '(1 2 3))
  ##> (:a 1 2 3)
(into [1 2 3] [:a :b])
  ##> [1 2 3 :a :b]
``` 

The sequence functions always return a seq. 

``` bash
(list? (rest [1 2 3]))
  ##> false
(seq? (rest [1 2 3]))
  ##> true
``` 

But REPL prints lists and sequences in the same way. 

Sequences are logical lists not concrete lists.

Sequences are 

- suited for large (even infinite) data.

- mostly lazy.

- immutable

### Using the Sequence Library

#### Creating Sequences

`range`

``` bash
(range start? end? step?)
``` 

``` bash
(range 10)
(range 10 20)
(range 1 25 2)
(range 0 -1 -0.25)
(range 1/2 4 1)
``` 

`repeat n x`

``` bash
(repeat 5 1)
  ##> (1 1 1 1 1)
(repeat 3 "x")
  ##> ("x" "x" "x")
``` 

`iterate`: continues forever.

``` bash
(iterate f x)
``` 

``` bash
(take 3 (iterate inc 1))
  ##> (1 2 3)
``` 

`take`: first n items 

``` bash
(take n sequence)
``` 

use case: finite view onto an infinite collection

``` bash
(def whole-numbers (iterate inc 1))
``` 

`repeat`: lazy, infinite sequence

``` bash
(repeat x)
``` 

``` bash
(take 3 (repeat 1))
  ##> (1 1 1)
``` 

`cycle`: cycles a collection infinitely

``` bash
(cycle coll)
``` 

``` bash
(take 5 (cycle (range 2)))
  ##> (0 1 0 1 0)
``` 

`interleave`: takes multiple collections. interleaves values from each collection until one of the collections is exhausted.

``` bash
(interleave & colls)
``` 

``` bash
(interleave whole-numbers ["a" "b" "c"])
  ##> (1 "a" 2 "b" 3 "c")
``` 

`interpose`: like `interleave` but elements separated by a separator

``` bash
(interpose separator coll)
``` 

``` bash
(interpose "," ["a" "b" "c"])
  ##> ("a" "," "b" "," "c")
``` 

use case: to produce output strings with `apply str`

``` bash
(apply str (interpose "," ["a" "b" "c"]))
  ##> "a,b,c"
``` 

This idiom is done by: `clojure.string/join`

``` bash
(join separator sequence)
``` 

``` bash
(require '[clojure.string :refer [join]])
(join \, ["a" "b" "c"])
  ##> "a,b,c"
``` 

Create a collection of that type:

``` bash
(list & elements)
(vector & elements)
(hash-set & elements)
(hash-map key-1 val-1)
``` 

`set`: similar to `hash-set`. This expects a collection

``` bash
(set [1 2 3])
  ##> #{1 3 2}
``` 

``` bash
(hash-set 1 2 3)
  ##> #{1 3 2}
``` 

`vec`: similar to `vector`. It takes a collection

``` bash
(vec (range 3))
  ##> [0 1 2]
``` 

#### Filtering Sequences

`filter`

``` bash
(filter pred coll)
``` 

``` bash
(take 3 (filter even? whole-numbers))
  ##> (2 4 6)
``` 

`take-while`

``` bash
(take-while pred coll)
``` 

``` bash
 #{\a\e\i\o\u}
  ##> #{\a \e \i \o \u}
(def vowel? #{\a\e\i\o\u})
(def consonant? (complement vowel?))
(take-while consonant? "ali-veli")
  ##> ()
(take-while consonant? "grass")
  ##> (\g \r)
``` 

`vowel?`: sets act as functions that look up a value in the set. returns the value if found. so `#{\a\e\i\o\u}`: a function that checks to see whether its argument is a vowel

`complement`: reverses the behavior. 

``` bash
(#{\a\e} "ali")
  ##> nil
(vowel? "ali")
  ##> nil
(#{\a\e} "a")
  ##> nil
(vowel? "a")
  ##> nil
(#{\a} \a)
  ##> \a
(vowel? \a)
  ##> \a
``` 

`drop-while`: opposite of `take-while`

``` bash
(drop-while pred coll)
``` 

``` bash
(drop-while consonant? "ali-veli")
  ##> (\a \l \i \- \v \e \l \i)
(drop-while consonant? "grass")
  ##> (\a \s \s)
``` 

`split-at`: split a collection into two

``` bash
(split-at index coll)
(split-with pred coll)
``` 

``` bash
(split-at 3 (range 4))
  ##> [(0 1 2) (3)]
(split-with #(<= % 2) (range 4))
  ##> [(0 1 2) (3)]
``` 

#### Sequence Predicates

`every?`

``` bash
(every? pred coll)
(every? odd? [1 3 5])
  ##> true
``` 

`some`: returns actual value of the first match

``` bash
(some pred coll)
(some even? [1 2 3])
  ##> true
(some even? [1 3])
  ##> nil
(some identity [nil false 1 nil 2])
  ##> 1
``` 

Check if a sequence contains the value 3:

``` bash
(some #{3} (range 5))
  ##> 3
``` 

``` bash
(not-every? pred coll)
(not-any? pred coll)
``` 

#### Transforming Sequences

`map`

``` bash
(map f coll)
``` 

``` bash
(map #(format "<p>%s</p>" %) ["ali" "veli"])
  ##> ("<p>ali</p>" "<p>veli</p>")
``` 

if map takes multiple collections, then f must be a function of multiple arguments too.

``` bash
(map #(format "<%s>%s</%s>" %1 %2 %1) ["h1" "h2"] ["ali" "veli"])
  ##> ("<h1>ali</h1>" "<h2>veli</h2>")
``` 

`reduce`

``` bash
(reduce f coll)
``` 

`reduce` applies `f` on the first two elements in `coll` and then to the result and the third element.

``` bash
(reduce + (range 1 5))
  ##> 10
(reduce * (range 1 5))
  ##> 24
``` 

`sort`

``` bash
(sort comp? coll)
(sort-by a-fn comp? coll)
``` 

`sort-by` sorts by the result of calling `a-fn` on each element

``` bash
(sort [5 1 3])
  ##> (1 3 5)
(sort-by #(.toString %) [15 1 3])
  ##> (1 15 3)
``` 

`comp` specifies comparison function

``` bash
(sort > [3 15])
  ##> (15 3)
(sort-by :grade > [{:grade 20} {:grade 15}])
  ##> ({:grade 20} {:grade 15})
``` 

List comprehension: granddaddy of all filters and transformations. consists of:

- Input lists
- Placeholder bindings for elements in the input lists
- Predicates on the elements 
- Output form

``` bash
(for [binding-form coll-expr filter-expr? ...] expr)
``` 

this is more general than `map` and `filter`

``` bash
(for [word ["ali" "veli"]]
  (format "<p>%s</p>" word))
  ##> ("<p>ali</p>" "<p>veli</p>")
``` 

Comprehensions use `:when` clause to emulate `filter`

``` bash
(take 5 (for [n whole-numbers :when (even? n)] n))
  ##> (2 4 6 8 10)
``` 

`:while` clause continues only while its predicate holds true:

``` bash
(for [n whole-numbers :while (even? n)] n)
  ##> ()
``` 

Multiple bindings:

``` bash
(for [file "ABCDEFGH"
      rank (range 1 9)]
  (format "%c%d" file rank))
  ##> ("A1" "A2" "A3" ...
``` 

### Lazy and Infinite Sequences

Lazy seqs has benefits:

- Postpone expensive computations
- Work with big data sets
- Delay IO

`doall` force lazy sequence to evaluate

``` bash
(def x (for [i (range 1 3)] (do (println i) i)))
(doall x)
  ##> 1
  ##> 2
  ##> (1 2)
(def x (for [i (range 1 3)] i))
(doall x)
  ##> (1 2)
``` 

`dorun`

``` bash
(def x (for [i (range 1 3)] (do (println i) i)))
(dorun x)
  ##> 1
  ##> 2
  ##> nil
``` 

`dorun` works like `doall` but doesn't keep past elements in memory.

### Clojure Makes Java Seq-able

Collections, regex, file system traversal, xml, relational database results.

#### Java Collections

``` bash
(first (.getBytes "hello"))
  ##> 104
(rest (.getBytes "hello"))
  ##> (101 108 108 111)
``` 

Hastables and Maps:

``` bash
(first (System/getProperties))
``` 

Strings:

``` bash
(first "Hello")
  ##> \H
(rest "Hello")
  ##> (\e \l \l \o)
``` 

To convert it back to string: use `(apply str seq)`

``` bash
(apply str (reverse "hello"))
  ##> "olleh"
``` 

#### Seq-ing Regular Expressions

``` bash
(re-matcher regexp string)
``` 

Don't use this. Better: `re-seq`

``` bash
(re-seq regexp string)
``` 

``` bash
(re-seq #"\w+" "ali veli")
  ##> ("ali" "veli")
``` 

#### Seq-ing the File System

``` bash
(import 'java.io.File)
(.listFiles (File. "."))
(seq (.listFiles (File. ".")))
``` 

``` bash
(map #(.getName %) (seq (.listFiles (File. "."))))
(map #(.getName %) (.listFiles (File. ".")))
  ##> ("project.clj" "LICENSE" "test" ...)
``` 

#### Seq-ing a Stream

``` bash
(require '[clojure.java.io :refer [reader]])
(take 2 (line-seq (reader "src/examples/utils.clj")))
  ##> (";---" "; Excerpted from \"Programming Clojure, Third Edition\",")
``` 

`with-open`: close the resource after using

``` bash
(with-open [rdr (reader "src/examples/utils.clj")]
	(count (line-seq rdr)))
``` 

Ex: count clojure loc

``` bash
(use '[clojure.java.io :only (reader)])
(use '[clojure.string :only (blank?)])
(defn non-blank? [line] (not (blank? line)))

(defn non-svn? [file] (not (.contains (.toString file) ".svn")))

(defn clojure-source? [file] (.endsWith (.toString file) ".clj"))

(defn clojure-loc [base-file]
  (reduce 
   +
   (for [file (file-seq base-file) 
	 :when (and (clojure-source? file) (non-svn? file))]
     (with-open [rdr (reader file)]
       (count (filter non-blank? (line-seq rdr)))))))
``` 

``` bash
(clojure-loc (java.io.File. "/Users/mertnuhoglu/codes/clojure/programming_in_clojure/code/test01"))
  ##> 1991
``` 

### Calling Structure-Specific Functions

#### Functions on Lists

`peek` get first element of a list

`pop` get the remainder

``` bash
(peek '(1 2 3))
  ##> 1
(pop '(1 2 3))
  ##> (2 3)
``` 

`get` value at index

``` bash
(get [:a :b :c] 1)
  ##> :b
``` 

Vectors are functions as well. They work like `get`

``` bash
([1 2 3] 1)
  ##> 2
``` 

`assoc` associates a new value with an index

``` bash
(assoc [0 1 2] 1 :two)
  ##> [0 :two 2]
``` 

`subvec` returns a subvector

``` bash
(subvec avec start end?)
``` 

``` bash
(subvec [1 2 3 4] 1 4)
  ##> [2 3 4]
``` 

You can simulate it with `drop` and `take`

``` bash
(take 3 (drop 1 [1 2 3 4]))
  ##> (2 3 4)
``` 

Vector specific functions are faster.

#### Functions on Maps

``` bash
(keys map)
(vals map)
``` 

``` bash
(keys {:a 1, :b 2})
  ##> (:a :b)
(vals {:a 1, :b 2})
  ##> (1 2)
``` 

`get` return value for a key

``` bash
(get map key default?)
``` 

``` bash
(get {:a 1, :b 2} :a)
  ##> 1
``` 

You can use map directly too:

``` bash
({:a 1, :b 2} :a)
  ##> 1
``` 

Keywords are also functions

``` bash
(:a {:a 1, :b 2})
  ##> 1
``` 

`contains?`

``` bash
(contains? map key)
``` 

``` bash
(def score {:stu nil :joey 100})
(:stu score)
  ##> nil
(contains? score :stu)
  ##> true
``` 

Building new maps:

- `assoc`
- `dissoc`
- `select-keys`
- `merge`

``` bash
(def song {:name "Agnus Dei"
	   :artist "Krzysztof Penderecki"
	   :album "Polish Requiem"
	   :genre "Classical"})
song
  ##> {:name "Agnus Dei", :artist "Krzysztof Penderecki", :album "Polish Requiem", :genre "Classical"}
(assoc song :kind "MPEG")
(dissoc song :genre)
(select-keys song [:name])
  ##> {:name "Agnus Dei"}
(merge song {:size 800 :time 50})
  ##> {:name "Agnus Dei", :artist "Krzysztof Penderecki", :album "Polish Requiem", :genre "Classical", :size 800, :time 50}
``` 

`merge-with` like merge but when two maps have the same key, you can specify how to combine the values

``` bash
(merge-with 
 concat 
 {:flintstone, ["Fred"], :rubble ["Barney"]}
 {:flintstone, ["Wilma"], :rubble ["Betty"]}
 {:flintstone, ["Pebbles"], :rubble ["Bam-Bam"]})
  ##> {:flintstone ("Fred" "Wilma" "Pebbles"), :rubble ("Barney" "Betty" "Bam-Bam")}
``` 

#### Functions on Sets

``` bash
(require '[clojure.set :refer :all])
(def languages #{"java" "c" "d" "clojure"})
(def beverages #{"java" "chai" "pop"})
``` 

`union`

`intersection`

`difference`

`select` subset matching a predicate

``` bash
(union languages beverages)
  ##> #{"d" "clojure" "pop" "java" "chai" "c"}
(difference languages beverages)
  ##> #{"d" "clojure" "c"}
(select #(= 1 (count %)) languages)
  ##> #{"d" "c"}
``` 

Relational algebra operators:

- set union, set difference
- rename, selection, projection
- cross product

Ex: relational database

set: table

map: rows in a table

``` bash
(def compositions 
  #{{:name "The Art of the Fugue" :composer "J. S. Bach"}
    {:name "Musical Offering" :composer "J. S. Bach"}
    {:name "Requiem" :composer "Giuseppe Verdi"}
    {:name "Requiem" :composer "W. A. Mozart"}})
(def composers
  #{{:composer "J. S. Bach" :country "Germany"}
    {:composer "W. A. Mozart" :country "Austria"}
    {:composer "Giuseppe Verdi" :country "Italy"}})
(def nations
  #{{:nation "Germany" :language "German"}
    {:nation "Austria" :language "German"}
    {:nation "Italy" :language "Italian"}})
``` 

``` bash
(rename relation rename-map)
``` 

``` bash
(rename compositions {:name :title})
  ##> #{{:composer "Giuseppe Verdi", :title "Requiem"} {:composer "W. A. Mozart", :title "Requiem"} {:composer "J. S. Bach", :title "The Art of the Fugue"} {:composer "J. S. Bach", :title "Musical Offering"}}
``` 

`select` similar to WHERE in SQL

``` bash
(select pred relation)
``` 

``` bash
(select #(= (:name %) "Requiem") compositions)
  ##> #{{:name "Requiem", :composer "Giuseppe Verdi"} {:name "Requiem", :composer "W. A. Mozart"}}
``` 

`project` similar to SELECT in SQL

``` bash
(project relation keys)
``` 

``` bash
(project compositions [:name])
  ##> #{{:name "The Art of the Fugue"} {:name "Musical Offering"} {:name "Requiem"}}
``` 

List comprehension for cross product in SQL

``` bash
(for [m compositions c composers] (concat m c))
  ##> (([:name "Musical Offering"] [:composer "J. S. Bach"] [:composer "Giuseppe Verdi"] [:country "Italy"]) ([:name "Musical Offering"] [:composer "J. S. Bach"] [:composer "J. S. Bach"] [:country "Germany"]) ...
``` 

`join`: Subset of full cross product based on shared keys

``` bash
(join relation-1 relation-2 keymap?)
``` 

``` bash
(join compositions composers)
  ##> #{{:composer "W. A. Mozart", :country "Austria", :name "Requiem"} {:composer "J. S. Bach", :country "Germany", :name "Musical Offering"} {:composer "Giuseppe Verdi", :country "Italy", :name "Requiem"} {:composer "J. S. Bach", :country "Germany", :name "The Art of the Fugue"}}
``` 

``` bash
(join composers nations {:country :nation})
  ##> #{{:composer "W. A. Mozart", :country "Austria", :nation "Austria", :language "German"} 
  ##> {:composer "J. S. Bach", :country "Germany", :nation "Germany", :language "German"} 
  ##> {:composer "Giuseppe Verdi", :country "Italy", :nation "Italy", :language "Italian"}}
``` 

``` bash
(project
  (join
	  (select #(= (:name %) "Requiem") compositions)
		composers)
	[:country])
  ##> #{{:country "Italy"} {:country "Austria"}}
``` 

## Chapter 4 - Functional Programming

### Functional Programming Concepts

#### Pure Functions

They have no side effects. 

Program output is impure.

Pure functions and immutable data go hand in hand.

``` bash
(defn mystery [input]
  (if input data-1 data-2))
``` 

If `data-1` and `data-2` are not immutable, then `mystery` cannot be pure.

#### Persistent Data Structures

Immutable data is important to both FP and state.

On the state side, reference types require immutable data to implement concurrency guarantees.

Update translates into: create a copy and plus my changes. This uses up memory quickly.

Clojure doesn't do naive "copy everything". All data structures are persistent.

Persistent: Data structures preserve old copies of themselves by sharing structure between older and newer versions.

``` bash
(def a '(1 2))
(def b (cons 0 a))
``` 

``` bash
0 -> 1 -> 2
^    ^
b    a
``` 

#### Laziness and Recursion

FP make use of recursion and laziness.

Functions and expressions are not lazy. Sequences are.

Laziness requires pure functions. 

#### Referential Transparency

You can replace a function call with its result any time.

Benefits:

- Memoization (automatic caching)
- Automatic parallelization

#### Benefits of FP

Easier to write: You don't need any information that is not in front of you (global scope)

Easier to set up the environment to execute a test. There is no environment beyond function's arguments.

Improves reuse because of composability. 

Encapsulation also encourages composability but it creates a firewall. Impure functions violate encapsulation.

#### Guidelines for Use

1. Avoid direct recursion

2. Use `recur`

3. Be lazy with big data (do not recur)

4. Don't realize more of a lazy sequence than you need

5. Know the sequence library

6. Subdivide

### How to be Lazy

First learn recursion as an approach to enumerating sequences of values.

Always avoid stack-consuming recursion. 

#### Tail Recursion

Recursion must come at the tail, that is, at an expression that is a return value of the function. Language can perform tail-call optimization.

To make `fibo` tail recursive you mustn't do any extra work after calling recursed function.

``` bash
(defn tail-fibo [n]
  (letfn [(fib 
	   [current next n] 
	   (if (zero? n)
	     current       
	     (fib next (+ current next) (dec n))))] 
    (fib 0N 1N n))) 
``` 

JVM doesn't perform TCO. 

Workarounds: explicit recursion with `recur`, lazy sequences

#### Self-recursion with recur

``` bash
(defn recur-fibo [n]
  (letfn [(fib 
            [current next n]
            (if (zero? n)
              current
              (recur next (+ current next) (dec n))))] 
    (fib 0N 1N n)))
``` 

We use `recur` instead of `fib` direct call.

``` bash
(recur-fibo 10)
``` 

#### Lazy sequences

`lazy-seq` macro constructs lazy sequences

``` bash
(lazy-seq & body)
``` 

It invokes its body when needed. It caches the result. 

``` bash
(defn lazy-seq-fibo 
  ([] 
     (concat [0 1] (lazy-seq-fibo 0N 1N))) 
  ([a b]
     (let [n (+ a b)]                    
       (lazy-seq                         
	(cons n (lazy-seq-fibo b n)))))) 
``` 

``` bash
(take 10 (lazy-seq-fibo))
  ##> (0 1 1N 2N 3N 5N 8N 13N 21N 34N)
(rem (nth (lazy-seq-fibo) 1000000) 1000)
``` 

opt: use sequence library to make lazy sequences

``` bash
(take 5 (iterate (fn [[a b]] [b (+ a b)]) [0 1]))
  ##> ([0 1] [1 1] [1 2] [2 3] [3 5])
(defn fibo []
 (map first (iterate (fn [[a b]] [b (+ a b)]) [0N 1N])))
(take 5 (fibo))
  ##> (0N 1N 1N 2N 3N)
``` 

#### Coming to Realization

`*print-length*` Configure how many items printer will print

``` bash
(set! *print-length* 10)
(take 50 (fibo))
  ##> (0N 1N 1N 2N 3N 5N 8N 13N 21N 34N ...)
(fibo)
  ##> (0N 1N 1N 2N 3N 5N 8N 13N 21N 34N ...)
``` 

#### Losing Your Head

What happens if you hold a reference to a part of the sequence?

Then all sequence will be realized. To prevent it use: `lazy-cat`

``` bash
(def head-fibo (lazy-cat [0N 1N] (map + head-fibo (rest head-fibo))))
``` 

Losing head of a lazy sequence is a good idea.

### Lazier Than Lazy

``` bash
(defn count-heads-pairs [coll]
  (loop [cnt 0 coll coll] 
    (if (empty? coll) 
      cnt
      (recur 
        (if 
          (= :h (first coll) (second coll)) 
	        (inc cnt)
	        cnt)
	      (rest coll)))))
``` 

``` bash
(count-heads-pairs [:h :h :h :t :h])
  ##> 2
``` 

#### Transforming the Input Sequence

opt01:

Transform the sequence to 2-tuples:

``` bash
(defn by-pairs [coll]
  (let 
    [take-pair 
      (fn [c] (when (next c) (take 2 c)))]
    (lazy-seq                                       
      (when-let 
        [pair (seq (take-pair coll))]
	      (cons pair (by-pairs (rest coll)))))
    ))     
``` 

``` bash
(by-pairs [:h :h :h :t :h])
  ##> ((:h :h) (:h :h) (:h :t) (:t :h))
``` 

``` bash
(defn count-heads-pairs [coll]
  (count 
    (filter 
      (fn [pair] (every? #(= :h %) pair)) 
		  (by-pairs coll))
  ))
``` 

opt02: use sequence library

``` bash
(partition size step? coll)
``` 

``` bash
(partition 2 1 [:h :h :h :t :h])
  ##> ((:h :h) (:h :h) (:h :t) (:t :h))
``` 

`count-if`

``` bash
(def ^{:doc "Count items matching a filter"}
  count-if (comp count filter))
``` 

`comp` composes multiple functions

``` bash
(comp f & fs)
``` 

``` bash
(defn count-runs
  "Count runs of length n where pred is true in coll."
  [n pred coll]
  (count-if #(every? pred %) (partition n 1 coll)))
``` 

``` bash
(count-runs 2 #(= % :h) [:h :h :h :t :h])
  ##> 2
``` 

#### Currying and Partial Application

``` bash
(def ^{:doc "Count runs of length two that are both heads"}
  count-heads-pairs (partial count-runs 2 #(= % :h)))
``` 

`partial`

``` bash
(partial f & partial-args)
``` 

``` bash
(partial count-runs 1 #(= % :h))
(fn [coll] (count-runs 1 #(= % :h) coll)) ; equivalent
``` 

### Recursion Revisited

``` bash
(declare my-odd? my-even?)

(defn my-odd? [n]
  (if (= n 0)
    false
    (my-even? (dec n))))

(defn my-even? [n]
  (if (= n 0)
    true
    (my-odd? (dec n))))
``` 

``` bash
(map my-even? (range 4))
  ##> (true false true false)
``` 

``` bash
(bit-and 3 1)
  ##> 1
(bit-and 3 2)
  ##> 2
(zero? 1)
  ##> false
(zero? 2)
  ##> false
``` 

#### Converting to self-recursion

`parity`: return 0 for even numbers and 1 for odd numbers

``` bash
(defn parity [n]
  (loop 
    [n n par 0]
    (if 
      (= n 0)
      par
      (recur (dec n) (- 1 par)))))
``` 

``` bash
(map parity (range 5))
  ##> (0 1 0 1 0)
``` 

``` bash
(defn my-even? [n] (= 0 (parity n)))
(defn my-odd? [n] (= 1 (parity n)))
``` 

#### Trampolining Mutual Recursion

``` bash
(trampoline f & partial-args)
``` 

``` bash
(trampoline list)
  ##> ()
(trampoline + 1 2)
  ##> 3
``` 

``` bash
(defn my-odd? [n]
  (if (= n 0)
    false
    #(my-even? (dec n)))) 

(defn my-even? [n]
  (if (= n 0)
    true
    #(my-odd? (dec n)))) 
``` 

``` bash
(trampoline my-even? 10000)
  ##> true
``` 

#### Replacing Recursion with Laziness

Laziness is the most used technique for elimination recursion.

``` bash
(defn deeply-nested [n]
  (loop 
    [n n result '(bottom)]
    (if (= n 0)
      result
      (recur (dec n) (list result)))))
``` 

``` bash
(deeply-nested 3)
  ##> ((((bottom))))
``` 

### Eager Transformations

``` bash
(defn square [x] (* x x))

(defn sum-squares-seq [n]
  (vec (map square (range n))))
``` 

``` bash
(defn sum-squares
  [n]
  (into [] (map square) (range n)))
``` 

#### Optimizing Performance

## Chapter 5 - Specifications

### Defining Specs

``` bash
(require '[clojure.spec.alpha :as s])
``` 

``` bash
(s/def name spec)
``` 

`::` auto-resolved keywords. Current namespace is used as the qualifier

`::recipe/ingredient` expand to: `:cookingco.recipe/ingredient`

### Validating Data

``` bash
(s/def :my.app/company-name string?)
(s/valid? :my.app/company-name "Acme") 
  ##> true
``` 

#### Enumerated values

``` bash
(s/def :marble/color #{:red :green :blue})
``` 

``` bash
(s/def ::bowling/ranged-roll (s/int-in 0 11))
``` 

#### Handling nil

``` bash
(s/def ::bowling/company-name-2 (s/nilable string?))
``` 

#### Logical Specs

``` bash
(s/def ::odd-int (s/and int? odd?))
(s/def ::odd-or-42 (s/or :odd ::odd-int :42 #{42}))
``` 

`s/conform` how a value matched a spec

``` bash
(s/conform ::odd-or-42 42)
  ##> [:42 42]
(s/conform ::odd-or-42 19)
  ##> [:odd 19]
``` 

`s/explain` why an invalid value didn't match

``` bash
(s/explain ::odd-or-42 0)
  ##> 0 - failed: odd? at: [:odd] spec: :test01.core/odd-int
  ##> 0 - failed: #{42} at: [:42] spec: :test01.core/odd-or-42
``` 

#### Collection Specs

`s/coll-of`

``` bash
(s/def ::names (s/coll-of string?))
(s/valid? ::names ["Alex" "Stu"])
  ##> true
``` 

Options for `s/coll-of`

`:kind` ex: `vector?` and `set?`

`:into` ex: `[] () #{}` conformed values collect into this

`:count` count for the collection

`:min-count`

`:max-count`

`distinct`

``` bash
(s/def ::my-set (s/coll-of int? :kind set? :min-count 2))
(s/valid? ::my-set #{10 20})
``` 

`s-map-of`: keys and values follow a spec

``` bash
(s/def ::scores (s/map-of string? int?))
(s/valid? ::scores {"Stu" 100 "Alex" 200})
  ##> true
``` 

`s/every` : sample large collections

#### Collection Sampling

#### Tuples

``` bash
(s/def ::point (s/tuple float? float?))
(s/conform ::point [1.3 2.7])
``` 

#### Information Maps

``` bash
{::music/id #uuid ".."
 ::music/artist "Rush"
 ::music/title "Moving"
 ::music/date #inst "1981-02-12"}
``` 

``` bash
(s/def ::music/id uuid?)
(s/def ::music/artist string?)
(s/def ::music/title string?)
(s/def ::music/date inst?)
``` 

``` bash
(s/def ::music/release
  (s/keys :req [::music/id]
	        :opt [::music/artist
					      ::music/title
								::music/date]))
``` 

### Validating Functions

#### Sequences With Structure

Like regex

`s/cat` concatenation of elements

``` bash
(s/def ::cat-example (s/cat :s string? :i int?))
(s/valid? ::cat-example ["abc" 100])
(s/conform ::cat-example ["abc" 100])
  ##> {:s "abc", :i 100}
``` 

`s/alt` alternatives within sequential structure

``` bash
(s/def ::alt-example (s/alt :i int? :k keyword?))
(s/valid? ::alt-example [100])
  ##> true
(s/valid? ::alt-example [:foo])
  ##> true
``` 

#### Repetition Operators

`s/?` 0 or 1

`s/*` 0 or more

`s/+` 1 or more

``` bash
(s/def ::oe (s/cat :odds (s/+ odd?) :even (s/? even?)))
(s/conform ::oe [1 3 5 100])
  ##> {:odds [1 3 5], :even 100}
``` 

#### Variable Argument Lists

Zero or more arguments:

``` bash
(s/def ::println-args (s/* any?))
``` 

Some fixed arguments and a variable argument at the end

``` bash
(clojure.set/intersection #{1 2} #{2 3} #{2 5})
  ##> #{2}
``` 

``` bash
(s/def ::intersection-args 
  (s/cat :s1 set?
	       :sets (s/* set?)))
(s/conform ::intersection-args '[#{1 2} #{2 3} #{2 5}])
  ##> {:s1 #{1 2}, :sets [#{3 2} #{2 5}]}
``` 

``` bash
(s/def ::meta map?)
(s/def ::validator ifn?)
(s/def ::atom-args 
  (s/cat :x any? :options (s/keys* :opt-un [::meta ::validator])))
(s/conform ::atom-args [100 :meta {:foo 1} :validator int?])
  ##> {:x 100, :options {:meta {:foo 1}, :validator #object[clojure.core$int_QMARK_...
``` 

#### Multi-arity Argument Lists

### Specifying Functions

``` bash
(s/def ::rand-args (s/cat :n (s/? number?)))
(s/def ::rand-ret double?)
``` 


# Article: Do Things: A Clojure Crash Course | Clojure for the Brave and True

https://www.braveclojure.com/do-things/

## Special Forms

In the previous section, you learned that function calls are expressions that have a function expression as the operator. The two other kinds of expressions are macro calls and special forms

the main feature that makes special forms “special” is that, unlike function calls, they don’t always evaluate all of their operands

Another feature that differentiates special forms is that you can’t use them as arguments to functions

## Multi-arity

Functions also support arity overloading. This means that you can define a function so a different function body will run depending on the arity

``` bash
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

Arity overloading is one way to provide default values for arguments

``` bash
(defn x-chop
  "Describe the kind of chop you're inflicting on someone"
  ([name chop-type]
     (str "I " chop-type " chop " name "! Take that!"))
  ([name]
     (x-chop name "karate")))
``` 

"karate" is the default value.

## Variable-arity

By including a `rest` parameter. 

`&` is the rest parameter

``` bash
(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))

(favorite-things "Doreen" "gum" "shoes" "kara-te")
; => "Hi, Doreen, here are my favorite things: gum, shoes, kara-te"
``` 

## Destructuring

Binding names to values within a collection.

``` bash
;; Return the first element of a collection
(defn my-first
  [[first-thing]] ; Notice that first-thing is within a vector
  first-thing)

(my-first ["oven" "bike" "war-axe"])
; => "oven"
``` 

Destructuring maps:

opt01:

``` bash
(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.33})
; => Treasure lat: 100
; => Treasure lng: 50
``` 

opt02: short form

``` bash
(defn announce-treasure-location
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))
``` 

`:as` keep access to original map argument:

``` bash
(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))

  ;; One would assume that this would put in new coordinates for your ship
  (steer-ship! treasure-location))
``` 

## Anonymous Functions

`%&`: Rest parameter

``` bash
(#(identity %&) 1 "blarg" :yip)
; => (1 "blarg" :yip)
``` 

## Returning Functions

Returned functions are closures

``` bash
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)
; => 10
``` 

## Ex: symmetrizer function

``` bash
(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])
(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))
``` 

## let

Rest parameters in `let`

``` bash
(def dalmatian-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
(let [dalmatians (take 2 dalmatian-list)]
  dalmatians)
; => ("Pongo" "Perdita")
(let [[pongo & dalmatians] dalmatian-list]
  [pongo dalmatians])
; => ["Pongo" ("Perdita" "Puppy 1" "Puppy 2")]
``` 

## loop

``` bash
(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration))))
; => Iteration 0
; => Iteration 1
; => Iteration 2
; => Iteration 3
; => Iteration 4
; => Goodbye!
``` 

You can do the same thing by recursive functions but this is more verbose:

``` bash
(defn recursive-printer
  ([]
     (recursive-printer 0))
  ([iteration]
     (println iteration)
     (if (> iteration 3)
       (println "Goodbye!")
       (recursive-printer (inc iteration)))))
(recursive-printer)
; => Iteration 0
; => Iteration 1
; => Iteration 2
; => Iteration 3
; => Iteration 4
; => Goodbye!
``` 

## reduce

Process each element in a sequence and build a result.

Implementation:

``` bash
(defn my-reduce
  ([f initial coll]
   (loop [result initial
          remaining coll]
     (if (empty? remaining)
       result
       (recur (f result (first remaining)) (rest remaining)))))
  ([f [head & tail]]
   (my-reduce f head tail)))
``` 

### Ex: symmetrizer with reduce

``` bash
(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))
``` 

reduce is more expressive:

With `loop` you need to read the whole code. 

With `reduce` you know that you are processing elements of collection.


# Article: Clojure in 10 big ideas - Stuart Halloway

https://github.com/stuarthalloway/presentations/blob/master/ClojureInTenBigIdeas-Jun-2017.pdf?raw=true

# Article: Functional-navigational programming in Clojure(Script) with Specter

http://nathanmarz.com/blog/functional-navigational-programming-in-clojurescript-with-sp.html

This has changed fundamental aspects of programming. 

I call it: "functional-navigational programming"

It changed how to structure huge amounts of code. 

Navigators into complex data structures is much more powerful than having getters/setters for individual data structures.

Navigators can be composed. 

This allows to manipulate any data of arbitrary complexity.

``` bash
(def world
  {:people [{:money 129827 :name "Alice Brown"}
            {:money 100 :name "John Smith"}
            {:money 6821212339 :name "Donald Trump"}
            {:money 2870 :name "Charlie Johnson"}
            {:money 8273821 :name "Charlie Rose"}
            ]
   :bank {:funds 4782328748273}}
  )
(->> world
     :people)
     (filter (fn [user] (= (:name user) name)))
     first
     :money
)
``` 

# Article: Clojure, Practicalli

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

``` bash
*clojure-version*
  ##> {:major 1, :minor 9, :incremental 0, :qualifier nil}
``` 

Class path:

``` bash
*compile-path*
  ##> "/Users/mertnuhoglu/codes/clojure/clojure-through-code/target/classes"
``` 

Current namespace:

``` bash
*ns*
  ##> #object[clojure.lang.Namespace 0x209a5cf "user"]
``` 

Last 3 values:

``` bash
(+ 1 2 3)
(+ 1 2 3 4)
(+ 1 2 3 5)
(str *1 *2 *3)
"11106"
``` 

### Reading your Project configuration

``` bash
(slurp "project.clj")
  ##> "(defproject clojure-through-code \"20.1.5-SNAPSHOT\"\n  :description \"Learning Clojure by evaluating code on the fly\"\n  :url \"...
``` 

Tidy up the result:

``` bash
(read-string (slurp "project.clj"))
  ##> (defproject clojure-through-code "20.1.5-SNAPSHOT" :description "Learning Clojure by evaluating code on the fly" :url "
``` 

`nth` nth element

``` bash
(nth (read-string (slurp "project.clj")) 1)
  ##> clojure-through-code
``` 

### Threading macros

Read from left-to-right instead of inside-out.

``` bash
(->
 "./project.clj"
 slurp
 read-string
 (nth 2))
  ##> "20.1.5-SNAPSHOT"
``` 

Thread-last macro:

``` bash
(->>
 (str " This")
 (str " is")
 (str " backwards"))
;; => backwards is This"
``` 

### Show me the docs

doc string

``` bash
(defn fn01
	"this is docstring"
	[] (1))
``` 

`doc` and `source` functions:

You should be in `user` namespace

Or switch back `(ns 'user)`

Or `(use 'cloujure.repl)`

``` bash
(doc doc)
(doc map)
(doc filter)
(doc cons)
``` 

### Assigning Names

When you assign a name to a value, that name is called a symbol.

### Namespace

Using a function from another namespace:

``` bash
(ns my-namespace.core :require [clojure.java.io])
(defn read-the-file [filename]
  (line-seq (clojure.java.io/reader filename)))
(read-the-file "project.clj")
``` 

Use alias:

``` bash
(ns my-namespace.core :require [clojure.java.io :as java-io])
(defn read-the-file [filename]
  (line-seq (java-io/reader filename)))
``` 

No qualifier:

``` bash
(ns my-namespace.core :require [clojure.java.io :refer [reader]])
(defn read-the-file [filename]
  (line-seq (reader filename)))
``` 

Multiple namespaces:

``` bash
(ns duct-test.main
  (:require [clojure.java.io :as io]
            [com.stuartsierra.component :as component]
            [duct.middleware.errors :refer [wrap-hide-errors]]
	))
``` 

External libraries: put into project file:

``` bash
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

``` bash
(if (> 3 2)
  "Higher"
  "Lower")
``` 

When: no else expression

``` bash
    (when (> 3 2)
      "Higher")
``` 

Case: multiple conditions:

``` bash
(case (inc 3)
  1 "Not even close"
  2 "I wish I was that young"
  3 "That was my previous value"
  4 "You sunk my battleship"
  "I dont think you understood the question")

=> "You sunk my battleship"
``` 

cond: multiple conditions

``` bash
(cond
  (= 7 (inc 2)) "(inc 2) is not 7, so this condition is false"
  (= 16 (* 8 2)) "This is the first correct condition so its associated expression is returned"
  (zero? (- (* 8 8) 64)) "This is true but not returned as a previous condition is true"
  :otherwise "None of the above are true")

;; => "This is the first correct condition so its associated expression is returned"
``` 

for comprehension: `:when` or `:while`

``` bash
(for [x (range 10) :when (odd? x)] x)

(for [x (range 10) :while (even? x)] x)

(for [x (range 10)
      y (range 10)]
  [x y])
``` 

while

``` bash
(while (condition) 
  (do something))
``` 

### Data Structures

Common features: immutable, persistent, shared memory, dynamically typed

#### List

``` bash
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

``` bash
(def my-list (list 1 2 3))
``` 

#### Map

``` bash
{:key "value"}
(:key 42)
{:key :value}
{"key" "value"}
("key" :value)
{:a 1 :b 2 :c 3}
{:monday 1 :tuesday 2 :wednesday 3 :thursday 4 :friday 5 :saturday 6 :sunday 7}
``` 

``` bash
(def starwars-characters
   {:luke   {:fullname "Luke Skywarker" :skill "Targeting Swamp Rats"}
    :vader  {:fullname "Darth Vader"    :skill "Crank phone calls"}
    :jarjar {:fullname "JarJar Binks"   :skill "Upsetting a generation of fans"}})
``` 

`get` returns all information

``` bash
(get starwars-characters :luke)
(get (get starwars-characters :luke) :fullname)
``` 

`get-in` returns a specific information

``` bash
(get-in starwars-characters [:luke :fullname])
(get-in starwars-characters [:vader :fullname])

``` 

Use map directly:

``` bash
(starwars-characters :luke)
(:fullname (:luke starwars-characters))
(:skill (:luke starwars-characters))

(starwars-characters :vader)
(:skill (:vader starwars-characters))
(:fullname (:vader starwars-characters))
``` 

Threading macro to shorten code:

``` bash
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

``` bash
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

``` bash
([1 2 3] 1)
``` 

### Set

``` bash
(set `(1 2 3 4))
(set `(1 2 1 2 3 4))

 #{1 2 3 4}
 #{:a :b :c :d}
;; duplicate key error
 #{1 2 3 4 1}
``` 

Lookup

``` bash
(#{:a :b :c} :c)
(#{:a :b :c} :z)
(contains?  #{"Palpatine" "Darth Vader" "Boba Fett" "Darth Tyranus"} "Darth Vader")
``` 

### Naming data structures

``` bash
(def people ["Jane Doe" "Samuel Peeps"])
``` 

Data structures are immutable names are mutable

``` bash
(def a 2)
(def a 3)
``` 

## Using data structures

`concat` concats lists/vectors

``` bash
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

``` bash
(range 4)
(take 4 (range))
``` 

### Destructuring

``` bash
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

``` bash
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

``` bash
(let [{a :a, c :c}  {:a 5 :c 6}]
  [a c])
  ##> [5 6]
(let [{:keys [a c]}  {:a 5 :c 6}]
  [a c])
  ##> [5 6]

``` 

### Mapping functions

``` bash
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

# Book: Living Clojure - Carin Meier

``` bash
'(1 2 "jam")
(first '(:rabbit :b))
(rest '(:rabbit :b))
(rest (rest '(:rabbit :b)))
(cons 5 '())
(cons 5 nil)
(cons 4 (cons 5 nil))
(list 4 5)
'(4 5)
``` 

Vectors

``` bash
(first [1 2])
(rest [1 2])
(nth [1 2] 0)
(last [1 2])
``` 

Collections in Common

``` bash
(count [1 2 3])
(conj [1 2 3] 5)
``` 

Maps

``` bash
(merge {:a 1 :b 2} {:a 3})
  ##> {:a 3, :b 2}
``` 

Sets

``` bash
(clojure.set/union #{1 2} #{2 3})
  ##> #{1 3 2}
(clojure.set/difference #{1 2} #{2 3})
(clojure.set/intersection #{1 2} #{2 3})
``` 

``` bash
(1 #{1 2})
  ##> Exception
(:a #{:a :b})
``` 

``` bash
conj
disj
contains?
``` 

## Symbols

`def` global vars

`let` temporary bindings

## Functions

``` bash
defn
fn
#()
% %1 %2
& args
``` 

## Namespaces

``` bash
(ns ns1)
(def f "hello")
f
ns1/f
``` 

## Flow

``` bash
(class true)
(not true)
(= 2 3)
(not (= 2 3))
(not= 2 3)
(empty? [])
``` 

## The Power of Laziness

``` bash
(take 5 (range))
(class (range 5))
(count (take 5 (range)))
(repeat 3 "a")
(class (take 5 (range)))
(rand-int 10)
(take 5 #(rand-int 10))
(repeatedly 5 #(rand-int 10))
(take 5 (repeatedly #(rand-int 10)))
(take 3 (cycle [1 9]))
``` 

## Recursion

``` bash
(defn alice-is [input]
	(loop [in input
	       out []]
		(if (empty? in)
		  out
			(recur (rest in)
			  (conj out (str "Alice is " (first in)))))))
``` 

## Data Transformations

``` bash
(take 3 (map #(str %) (range)))
(def animal-print (doall (map #(println %) animals)))
``` 

# Book: [Luke_VanderHart,_Ryan_Neufeld]_Clojure_Cookbook.pdf

# Article: Macros by Example - Stepan Parunashvili 

https://m.stopa.io/macros-by-example-6ddbc8f3d93b

## Example 1: nullthrows

### in js

``` bash
function nullthrows(result) {
  if (result === null || result === undefined) {
    throw new Error("uh oh");
  } 
  return result;
}
nullthrows(getUser(db, 'billy'))
// if it's null, throw Exception
  ##> index.html:700 Uncaught Error: uh oh
  ##>     at nullthrows (index.html:700)
  ##>     at someStuff (index.html:1325)
  ##>     ...
``` 

### in clojure

``` bash
(nil-throws (get-user "billy"))
``` 

something like:

``` bash
(defn nil-throws [res]
  (if (nil? res)
    (throw "uh oh")
    res))
``` 

### macro:

macro:

``` bash
(defmacro nil-throws [form]
  `(let [result# ~form] ;; assign the evaluation of form to result#
    (if (nil? result#)
      (throw
        (ex-info "uh oh, we got nil!" {:form '~form})) ;; save form for inspection
      result#)))
``` 

`\`` don't eval it this is the code to return

`~` is like interpolation `${}` in js but for lists

`#` generate some symbol that doesn't interfere with any other in different scope

`'` treat it as list, don't evaluate

``` bash
(nil-throws (get-user "billy"))
``` 

will be replaced with:

``` bash
(let [result# (get-user db "billy")]
  (if (nil? result#)
    (throw (ex-info "uh oh, we got nil!" {:form '(get-user db "billy")})) 
    result#))
``` 

## Example 2: pipe

### in js

``` bash
createBill(addToCart(cart, updatePrice(item, 100)))
``` 

convert to:

``` bash
item |> updatePrice($$, 100) |> addToCart(cart, $$) |> createBill
``` 

or this:

``` bash
|> [
  item, 
  updatePrice($$, 100), // updatePrice(item, 100)
  addToCart(cart, $$), // addToCart(cart, updatePrice(item, 100))
  createBill, // createbill(addToCart(cart, updatePrice(item, 100)))
]
``` 

#### opt01: a pipe function

``` bash
pipe(item, (item) => updatePrice(item, 100))
``` 

Problem: needs anonymous functions

### in clojure

goal:

``` bash
(|> item
    (update-price $$ 100)
    (add-to-cart cart $$)
    create-bill)
``` 

macro:

``` bash
(defmacro |> [form & forms]
  (reduce
    (fn [last-v form]
      (if (seq? form) ;; am I being called: (update-price $$ 100)
        `(let [~(symbol "$$") ~last-v]
           ~form)
        `(~form ~last-v))) ;; or am I just a function: create-bill
    form
    forms))
``` 


# Book: Clojure for the Brave and True

## Introduction 

https://www.braveclojure.com/introduction/

## Chapter 1: Building, Running, and the REPL | Clojure for the Brave and True

``` bash
cd /Users/mertnuhoglu/projects/study/clj/ex/study_clojure/book_clojure_brave
``` 

``` bash
lein new app clojure-boob
``` 

Edit `~/projects/study/clj/ex/study_clojure/book_clojure_brave/clojure-noob/src/clojure_noob/core.clj`

``` bash
(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Teapot"))
``` 

``` bash
cd clojure-noob
lein run
  ##> Teapot
``` 

`lein run` executes `-main` function.

### Building the Clojure Project

``` bash
lein uberjar
``` 



