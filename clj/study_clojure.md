---
title: "Study datomic"
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
blog: mertnuhoglu.com
resource_files:
path: ~/projects/study/clj/study_datomic.md
state: wip

---

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

#### def

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

## Clojure Coding Quick Start

``` bash
(println "hello world")
(defn hello [name] (str "Hello, " name))
(hello "Stu")
``` 

### Special Variables

`*1 *2 ...`: result of most recent evaluation

``` bash
(str *1 " and " *2)
``` 

`(pst)` print stack trace

Load file from REPL:

``` bash
(load-file "file.clj")
``` 

### Adding Shared State

 

