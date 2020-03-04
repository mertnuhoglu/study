--- title: "Study clojure"
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

## map doc

https://clojuredocs.org/clojure.core/map

``` clojure
(map f)
(map f coll)
(map f c1 c2)
(map f c1 c2 c3)
(map f c1 c2 c3 & colls)
``` 

``` clojure
(map inc [1 2 3 4 5])
;;=> (2 3 4 5 6)


;; map can be used with multiple collections. Collections will be consumed
;; and passed to the mapping function in parallel:
(map + [1 2 3] [4 5 6])
;;=> (5 7 9)


;; When map is passed more than one collection, the mapping function will
;; be applied until one of the collections runs out:
(map + [1 2 3] (iterate inc 1))
;;=> (2 4 6)
``` 

``` clojure
;; A useful idiom to pull "columns" out of a collection of collections. 
;; Note, it is equivalent to:
;; user=> (map vector [:a :b :c] [:d :e :f] [:g :h :i])

(apply map vector [[:a :b :c]
                   [:d :e :f]
                   [:g :h :i]])

;;=> ([:a :d :g] [:b :e :h] [:c :f :i])

``` 

## partial doc

https://clojuredocs.org/clojure.core/partial

``` clojure
(partial f)
(partial f arg1)
(partial f arg1 arg2)
(partial f arg1 arg2 arg3)
(partial f arg1 arg2 arg3 & more)
``` 

``` clojure
user=> (def hundred-times (partial * 100))
#'user/hundred-times

user=> (hundred-times 5)
500

user=> (hundred-times 4 5 6)
12000

``` 


## apply doc

https://clojuredocs.org/clojure.core/apply

``` clojure
(apply f args)
(apply f x args)
(apply f x y args)
(apply f x y z args)
(apply f a b c d & args)
``` 

Applies fn f to the argument list formed by prepending intervening arguments to args.

``` clojure
;; If you were to try
(max [1 2 3])
;;=> [1 2 3]

;; You would get '[1 2 3]' for the result. In this case, 'max' has received one
;; vector argument, and the largest of its arguments is that single vector.

;; If you would like to find the largest item **within** the vector, you would need
;; to use `apply`

(apply max [1 2 3])
;;=> 3

;; which is the same as 
(max 1 2 3)
;;=> 3
``` 

## comp doc

https://clojuredocs.org/clojure.core/comp

``` clojure
(comp)(comp f)(comp f g)(comp f g & fs)
``` 

Takes a set of functions and returns a fn that is the composition of those fns. (right-to-left)

``` clojure
; Get 2nd to last element from a list
( (comp second reverse) '("a" 2 7 "b"))   
;;=> 7

(filter (comp not zero?) [0 1 0 2 0 3 0 4])
;;=> (1 2 3 4)

((comp str +) 8 8 8)   
;;=> "24"

(def negative-quotient (comp - /))
;; #'user/negative-quotient

(negative-quotient 8 3)  ;;=> -8/3
``` 

## juxt doc

https://clojuredocs.org/clojure.core/juxt

``` clojure
(juxt f)(juxt f g)(juxt f g h)(juxt f g h & fs)
``` 

Takes a set of functions and returns a fn that is the juxtaposition
of those fns.

((juxt a b c) x) => [(a x) (b x) (c x)]

``` clojure
((juxt :a :b) {:a 1 :b 2 :c 3 :d 4})
;;=> [1 2]
``` 

``` clojure
;; "Explode" a value.

((juxt identity name) :keyword)
;;=> [:keyword "keyword"]

(juxt identity name)
...is the same as:
(fn [x] [(identity x) (name x)])

;; eg. to create a map:

(into {} (map (juxt identity name) [:a :b :c :d]))
;;=> {:a "a" :b "b" :c "c" :d "d"}
``` 

## identity doc

``` clojure
user=> (identity 4)
4
``` 

## name doc

https://clojuredocs.org/clojure.core/name

Returns the name String of a string, symbol or keyword.

``` clojure
;; the name of the keyword is without the ':'
;; "str" will retain the ':'.
(name :x)
;;=> "x"

(name "x")
;;=> "x"

;; returns the symbol name as a string without the namespace.
(name 'x)
;;=> "x"

(name 'user/x)
;;=> "x"
``` 

## pr-str doc

https://clojuredocs.org/clojure.core/pr-str

``` clojure
(pr-str & xs)
``` 

pr to a string, returning it

``` clojure
user=> x
[1 2 3 4 5]


;; Turn that data into a string...
user=> (pr-str x)
"[1 2 3 4 5]"


;; ...and turn that string back into data!
user=> (read-string (pr-str x))
[1 2 3 4 5]
``` 

## repeat doc

https://clojuredocs.org/clojure.core/repeat

``` clojure
(repeat x)(repeat n x)
``` 

Returns a lazy (infinite!, or length n if supplied) sequence of xs.

``` clojure
user=> (take 5 (repeat "x"))
("x" "x" "x" "x" "x")

;; which is the same as:
user=> (repeat 5 "x")
("x" "x" "x" "x" "x")

``` 

## into doc

https://clojuredocs.org/clojure.core/into

``` clojure
; Items are conj'ed one at a time, which puts them at the head of 
; the destination list
user=> (into () '(1 2 3))
(3 2 1)

; This does not happen for a vector, however, due to the behavior of conj:
user=> (into [1 2 3] '(4 5 6))
[1 2 3 4 5 6]
``` 

``` clojure
user=> (into (sorted-map) [ [:a 1] [:c 3] [:b 2] ] )
{:a 1, :b 2, :c 3}
; When maps are the input source, they convert into an unordered sequence 
; of key-value pairs, encoded as 2-vectors
user=> (into [] {1 2, 3 4})
[[1 2] [3 4]]
``` 

## remove doc

https://clojuredocs.org/clojure.core/remove

``` clojure
;; compare to filter

(remove even? (range 10))
;;=> (1 3 5 7 9)

; When coll is a map, pred is called with key/value pairs.
(remove #(> (second %) 100)
       {:a 1
        :b 2
        :c 101
        :d 102
        :e -1})
;;=> ([:a 1] [:b 2] [:e -1])
``` 

### Logs

Nasıl çalışıyor bu:

``` clojure
(remove #(> (second %) 100)
       {:a 1
        :b 2
        :c 101
        :d 102
        :e -1})
;;=> ([:a 1] [:b 2] [:e -1])
``` 

`second` fonksiyonuna argüman olarak {:a 1} {:b 2} gibi key-value pairları paslanıyor olmalı. Fakat bunları tek başına gönderdiğimde `nil` alıyorum:

``` clojure
(second {:a 1})
  ##> nil
``` 

`map` ile çağıralım

``` clojure
(map #(> (second %) 100) {:a 1 :c 101})
  ##> (false true)
``` 

Evet, bu durumda, tek tek gönderiliyor. O zaman, item değil de vector olarak mı gönderiliyor?

``` clojure
(second [:a 1])
  ##> 1
``` 

Evet, map'in öğeleri `{:a 1}` gibi map değil, `[:a 1]` gibi vector.

## update-in

https://clojuredocs.org/clojure.core/update-in

``` clojure
(def p {:name "James" :age 26})
;;=> #'user/p

(update-in p [:age] inc)
;;=> {:name "James", :age 27}
``` 

## assoc-in

https://clojuredocs.org/clojure.core/assoc-in

``` clojure
(assoc-in {:person {:name "Mike"}} [:person :name] "Violet")
; => {:person {:name "Violet"}}

(assoc-in {:person {:name "Mike"}} [:person] "Violet")
; => {:person "Violet"}
``` 

## swap! amap assoc akey nasıl çalışıyor?

``` clojure
(def m (atom {:a 1}))
@m
  ##> {:a 1}
(swap! m assoc :b 2)
  ##> {:a 1, :b 2}
``` 

Acaba sıralama değişince ne oluyor?

``` clojure
(def n {:a 1})
(assoc n :b 2)
  ##> {:a 1, :b 2}
(assoc :b 2 n)
  ##> Error
``` 

`assoc` 2 argüman alıyor. Bu yüzden, `swap!` ile kullanımda ilk argüman otomatikman mevcut atom oluyor. 

## defonce

Like def, but you cannot overwrite it.

``` clojure
user> (defonce foo 5)
  #'user/foo

user> foo
5

;; defonce does nothing the second time
user> (defonce foo 10)
nil

user> foo
5
``` 

## printing a map

``` clojure
(print {:a 1 :b 2})
  ##> {:a 1, :b 2}nil
(clojure.pprint/pprint {:a 1 :b 2})
  ##> {:a 1, :b 2}
``` 

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

``` clojure
(mapv f coll)
       (mapv f c1 c2)
       (mapv f c1 c2 c3)
``` 

``` clojure
(mapv + [1 2] [3 4])
  ##> [4 6]
``` 

## hash-map function

https://clojuredocs.org/clojure.core/hash-map

``` clojure
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

``` clojure
(map #(hash-map % 0) (seq "abcdefgh"))
  ##> ({\a 0} {\b 0} {\c 0} {\d 0} {\e 0} {\f 0} {\g 0} {\h 0}) 
(apply hash-map (.split "a 1 b 2 c 3" " "))
  ##> {"a" "1", "b" "2", "c" "3"}
``` 

## namespaced keyword notations

``` clojure
user=> #:a{:b :c}
  ##> #:a{:b :c}
user=> {:a/b :c}
  ##> #:a{:b :c}
``` 

## map-indexed function

``` clojure
user=> (map-indexed (fn [idx itm] [idx itm]) "foobar")
([0 \f] [1 \o] [2 \o] [3 \b] [4 \a] [5 \r])
user=> (map-indexed hash-map "foobar")
({0 "f"} {1 "o"} {2 "o"} {3 "b"} {4 "a"} {5 "r"})
``` 

## vec function

``` clojure
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

``` clojure
(hash-map :a 1 :b 2)
  ##> {:b 2, :a 1}
``` 

2. hashmap literal `{}`

``` clojure
{:a 1 :b 2}
  ##> {:a 1, :b 2}
``` 

### Converting other collections to hashmaps

``` clojure
(hash-map [:a 1 :b 2 :c 3])
  ##> ; => IllegalArgumentException No value supplied for key: [:a 1 :b 2 :c 3]
``` 

We need to use `apply`

It destructures a collection before applying a function to it:

``` clojure
(apply + [1 2 3])
  ##> 6
(apply hash-map [:a 1 :b 2 :c 3])
  ##> ; => {:c 3, :b 2, :a 1}
``` 

# Setup Clojure

https://clojure.org/guides/deps_and_cli

``` clojure
brew install clojure
clj
``` 

``` clojure
(+ 2 3)
``` 

Create a deps.edn file to declare the dependency: `~/projects/study/clj/ex/study_clojure/ex01/deps.edn`

``` clojure
cd ~/projects/study/clj/ex/study_clojure/ex01
clj
``` 

``` clojure
(require '[clj-time.core :as t])
(str (t/now))
  ##> "2019-11-01T11:53:40.214Z"
``` 

## Writing a program

Edit `~/projects/study/clj/ex/study_clojure/ex01/src/hello.clj`

``` clojure
clj -m hello
  ##> Hello world, the time is 02:57 PM
``` 

# Setup Leiningen

https://leiningen.org/

``` clojure
wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
mv lein ~/lein
chmod a+x ~/bin/lein
lein
``` 

``` clojure
lein help tutorial
``` 

unset $CLASSPATH variable

``` clojure
unset CLASSPATH
``` 

# Clojure Reference

## Clojure - Vars and the Global Environment

https://clojure.org/reference/vars

Persistent reference to a changing value. 4 ways: Vars, Refs, Agents, Atoms.

`def` creates (and interns) a Var.

If no initial value is supplied, the var is unbound:

``` clojure
user=> (def x)
#'user/x
user=> x
#object[clojure.lang.Var$Unbound 0x14008db3 "Unbound: #'user/x"]
``` 

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

``` clojure
map             ; symbol
+               ; symbol - most punctuation allowed
clojure.core/+  ; namespaced symbol
nil             ; null value
true false      ; booleans
:alpha          ; keyword
:release/alpha  ; keyword with namespace
``` 

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

# Clojure Documentation | Clojure Docs

http://clojure-doc.org/articles/content.html#essentials

## Getting Started

### Trying out the REPL

``` clojure
lein repl
``` 

### Your first project

``` clojure
lein new app proj01
``` 

Check `~/projects/study/clj/ex/study_clojure/ex02/proj01`

``` clojure
cd proj01
lein run
  ##> Retrieving org/clojure/clojure/1.10.0/clojure-1.10.0.pom from central
  ##> Retrieving org/clojure/clojure/1.10.0/clojure-1.10.0.jar from central
  ##> Hello, World!
``` 

### Interactive Development

``` clojure
cd ~/projects/study/clj/ex/study_clojure/ex02/proj01
lein repl
(-main)
  ##> Hello, World!
  ##> nil
``` 

Edit `~/projects/study/clj/ex/study_clojure/ex02/proj01/src/proj01/core.clj`

Reload the code:

``` clojure
(require 'proj01.core :reload)
(-main)
  ##> Hello, World!2
``` 

## Introduction to Clojure | Clojure Documentation | Clojure Docs

http://clojure-doc.org/articles/tutorials/introduction.html

``` clojure
5
"hi"
[1 2 3]
(+ 1 2)
(if true "yes" "no")
(println "hello!")
``` 

Sub-expressions:

``` clojure
(+ 1
	(* 2 3)
	(/ 10 2)
)
  ##> 12
``` 

## Weird Characters

https://clojure.org/guides/weird_characters

``` clojure
  ( … ) - List
  [ … ] - Vector
  { … } - Map
  # - Dispatch character
  #{ … } - Set
  #_ - Discard
  #"…" - Regular Expression
  #(…) - Anonymous function
  #' - Var quote
  ## - Symbolic values
  #inst, #uuid, and #js etc. - tagged literals
  %, %n, %& - Anonymous function arguments
  @ - Deref
  ^ (and #^) - Metadata
  ' - Quote
  ; - Comment
  : - Keyword
  :: - Auto-resolved keyword
  #: and #:: - Namespace Map Syntax
  / - Namespace separator
  \ - Character literal
  $ - Inner class reference
  ->, ->>, some->, cond->, as-> etc. - Threading macros
  ` - Syntax quote
  ~ - Unquote
  ~@ - Unquote splicing
  <symbol># - Gensym
  #? - Reader conditional
  #?@ - Splicing Reader conditional
  *var-name* - "Earmuffs"
  >!!, <!!, >!, and <! - core.async channel macros
  <symbol>? - Predicate Suffix
  <symbol>! - Unsafe Operations
  _ - Unused argument
  , - Whitespace character
  #= Reader eval
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

``` clojure
(function-name param1 param2)
``` 

The function call is simply a list containing the function name and its parameters. A list is reserved for creating callable expressions. 

Creating a list:

``` clojure
(list 1 2 3)
``` 

### Anonymous Functions

Functions that aren't bound to a name.

``` clojure
(fn [arg] (println arg))
``` 

We can call this function by settings it as a first item in a list and its argument as the second.

``` clojure
((fn [arg] (println arg)) "hello")
  ##> hello
``` 

Syntactic sugar for defining anonymous functions using the `#` notation

``` clojure
  #(println %)
``` 

`%` indicates an unnamed argument

If multiple arguments exist:

``` clojure
  #(println %1 %2 %3)
``` 

### Named Functions

Creating global variables using `def`

``` clojure
(def double
  (fn [x] (* 2 x)))
``` 

It accepts a name and the body.

Syntactic sugar for this: `defn`

``` clojure
(defn square [x]
  (* x x))
``` 

The body can consist of multiple expressions:

``` clojure
(defn bmi [height weight]
  (println "height:" height)
  (println "weight:" weight)
  (/ weight (* height height)))
``` 

Clojure uses a single pass compiler. So, the functions must be declared before they are used.

``` clojure
(declare down)

(defn up [n]
  (if (< n 10)
    (down (+ 2 n)) n))

(defn down [n]
  (up (dec n)))
``` 

### Higher-Order Functions

``` clojure
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

``` clojure
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

``` clojure
(reduce + (interpose 5 (map inc (range 10))))
``` 

We want to read them in linear form:

``` clojure
(->> (range 10) (map inc) (interpose 5) (reduce +))
``` 

`->>` works as a pipe. The result of each expression is passed as the last argument of the next expression. 

`->` as first argument.

### Laziness

## Code Structure

``` clojure
(println
  (filter #(= (mod % 2) 0)
    (map #(* % %) (range 1 6))))
``` 

Flattened steps:

``` clojure
(->> (range 1 6)
     (map #(* % %))
     (filter #(= (mod % 2) 0))
     (println))
``` 

Each function returns a new value instead of modifying existing data in place.

Clojure uses persistent data structures that create in-memory revisions of the data.

### Destructuring

``` clojure
(let [[smaller bigger] (split-with #(< % 5) (range 10))]
    (println smaller bigger))

  ##> =>(0 1 2 3 4) (5 6 7 8 9)
``` 

`split-with` returns two elements: numbers less than 5 and greater than 5. They are bound to `smaller` and `bigger` variables.

``` clojure
(defn print-user [[name address phone]]
  (println name "-" address phone))

(print-user ["John" "397 King street, Toronto" "416-936-3218"])
=> "John - 397 King street, Toronto 416-936-3218"
``` 

`print-user` takes 3 arguments: `name`, `address`, and `phone`

``` clojure
(defn print-args [& args]
  (println args))

(print-args "a" "b" "c") => (a b c)
``` 

`print-args` takes variable arguments.

Variable arguments can be destructured too:

``` clojure
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

``` clojure
(let [{foo :foo bar :bar} {:foo "foo" :bar "bar"}]
  (println foo bar))
  ##> foo bar
``` 

``` clojure
(let [{f :foo b :bar} {:foo "hello" :bar "world"}]
  (println f b))
  ##> hello world
``` 

Destructuring nested maps:

``` clojure
(let [{[a b c] :items id :id} {:id "foo" :items [1 2 3]}]
  (println id "->" a b c))
  ##> foo -> 1 2 3
``` 

`:keys` Syntactic sugar for extracting keys from maps:

ex01: long form

``` clojure
(let [{foo :foo bar :bar} {:foo "foo" :bar "bar"}]
  (println foo bar))
  ##> foo bar
``` 

ex05: :keys short form

``` clojure
(let [{:keys [foo bar]} {:foo "foo" :bar "bar"}]
  (println foo bar))
  ##> foo bar
``` 

Error:

``` clojure
(let [:keys [foo bar] {:foo "foo" :bar "bar"}]
  (println foo bar))
  ##> [:keys [foo bar] {:foo "foo", :bar "bar"}] - failed: even-number-of-forms? at: [:bindings] spec: :clojure.core.specs.alpha/bindings
``` 

ex02:

``` clojure
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

``` clojure
(defn login [{:keys [user pass]}]
 (and (= user "bob") (= pass "secret")))

(login {:user "bob" :pass "secret"})
``` 

`:as` Another useful destructuring option allows us to extract some keys while preserving the original map:

ex01:

``` clojure
(defn register [{:keys [id pass repeat-pass] :as user}]
  (cond
    (nil? id) "user id is required"
    (not= pass repeat-pass) "re-entered password doesn't match"
    :else user))
``` 

ex02:

``` clojure
(let [{:keys [a b], :as all} {:a 1, :b 2, :c 3}]
  (println a b all))
  ##> 1 2 {:a 1, :b 2, :c 3}
``` 

### Namespaces

``` clojure
(ns colors)

(defn hex->rgb [[_ & rgb]]
    (map #(->> % (apply str "0x") (Long/decode))
         (partition 2 rgb)))
``` 

Calling a function from another namespace: `:use` or `require`

``` clojure
(ns myns
  (:use colors))

(hex->rgb "#33d24f")
``` 

`:use` makes all variables implicitly available

`:only` restricts imported variables:

``` clojure
(ns myns
  (:use [colors :only [rgb->hex]]))
``` 

`:require` requires variables to be prefixed:

``` clojure
(ns myns (:require colors))

(colors/hex->rgb "#324a9b")
``` 

`:as` alias for imported variables.

``` clojure
(ns myotherns
  (:require [colors :as c]))

(c/hex->rgb "#324a9b")
``` 

### Dynamic Variables

### Polymorphism

#### Multimethods

``` clojure
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

``` clojure
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

``` clojure
#myapp/Person {:first "Fred" :last "Mertz"}
``` 

### built-in tagged elements

Instant in time

``` clojure
#inst "1985-04-12T23:20:50.52Z"
``` 

UUID

---

commens: `;`

# Book: Programming in Clojure - Alex Miller, Stuart Halloway, Aaron Bedra

## Chapter 1 - Getting Started

### Clojure is Elegant

``` clojure
(defn blank? [str]
  (every? #(Character/isWhitespace %) str))
``` 

No for loop. No if branching. No mutable state.

``` clojure
user=> (defrecord Person [first-name last-name])
  ##> user.Person
user=> (def foo (->Person "Aaron" "Bedra"))
  ##> #'user/foo
user=> (:first-name foo)
  ##> "Aaron"
``` 

### Clojure is Lisp Reloaded

Clojure itself is built out of macros such as `defrecord`

``` clojure
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

``` clojure
(cond (= x 10) "equal"
      (> x 10) "more")
``` 

### Clojure is a functional language.

But it is not pure functional like haskell.

- functions as first-class objects
- Data is immutable
- Functions are pure

Ex: 

``` clojure
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

``` clojure
(def accounts (ref #{}))
(defrecord Account [id balance])
(dosync
  (alter accounts conj (->Account "CLJ" 100.00)))
``` 

`ref`: creates a reference to the database

`dosync` updates database in a transaction.

Transaction guarantees thread safety.

### Clojure embraces the JVM

``` clojure
(System/getProperties)
``` 

``` java
"hello".getClass().getProtectionDomain()
``` 

``` clojure
(.. "hello" getClass getProtectionDomain)
``` 

All clojure functions implement `Callable` and `Runnable`

``` clojure
(.start (new Thread (fn [] (println "Hello" (Thread/currentThread)))))
``` 

### Clojure Coding Quick Start

``` clojure
(println "hello world")
(defn hello [name] (str "Hello, " name))
(hello "Stu")
``` 

#### Special Variables

`*1 *2 ...`: result of most recent evaluation

``` clojure
(str *1 " and " *2)
``` 

`(pst)` print stack trace

Load file from REPL:

``` clojure
(load-file "file.clj")
``` 

#### Adding Shared State

Empty set: `#{}`

`conj`: conjoin = add new item = concatenate
 
``` clojure
(conj #{} "Ali")
  ##> -> #{"Ali"}
``` 

Reference types (refs):

- atom

``` clojure
(atom initial-state)
``` 

Name using `def`

``` clojure
(def symbol initial-value?)
``` 

`def` is more general than `defn`

`def` can define functions or data

`atom` creates an atom

``` clojure
(def visitors (atom #{}))
  ##> #'user/visitors
``` 

Update reference using `swap!` function

``` clojure
(swap! r update-fn & args)
``` 

`swap!` applies `update-fn` to reference `r` with optional `args`

``` clojure
(swap! visitors conj "Stu")
  ##> #{"Stu"}
``` 

Check the ref with `deref` or `@`

``` clojure
(deref visitors)
  ##> #{"Stu"}
@visitors
  ##> #{"Stu"}
``` 

Check `~/codes/clojure/programming_in_clojure/code/test01/src/examples/introduction.clj`

``` clojure
(defn hello 
  "Writes hello message to *out*. Calls you by username.
  Knows if you have been here before."
  [username]
  (swap! visitors conj username)
  (str "Hello, " username))
``` 

``` clojure
(hello "Rich")
@visitors
  ##> #{"Stu" "Rich"}
``` 

### Navigating Clojure Libraries

import a namespace (package)

``` clojure
(require 'some-namespace)
(require 'clojure.java.io)
``` 

Leading single quote `'`: it quotes library name

``` clojure
(require 'examples.introduction)
``` 

#### Error: classpath

``` clojure
(require 'examples.introduction)
  ##> Could not locate examples/introduction__init.class, examples/introduction.clj or examples/introduction.cljc on classpath.
``` 

opt01: leiningen

``` clojure
lein new app test01
cd test01
lein repl
``` 

``` clojure
(require 'examples.introduction)
``` 

Now, it works

---


``` clojure
(take 10 examples.introduction/fibs)
  ##> (0 1 1 2 3 5 8 13 21 34)
``` 

`doc`:

``` clojure
### test01.core=> (doc str)
clojure.core/str
([] [x] [x & ys])
  With no args, returns the empty string. With one arg x, returns
  x.toString().  (str nil) returns the empty string. With more than
  one arg, returns the concatenation of the str values of the args.
nil
``` 

``` clojure
(defn hello
	"This is doc string"
	[username]
	(println (str "Hello, " username))
)
``` 

`find-doc`: search for anything

``` clojure
(find-doc "reduce")
``` 

`clojure.repl/source`: source code 

``` clojure
(require '[clojure.repl :refer [source]])
(source identity)
  ##> (defn identity
  ##>   "Returns its argument."
  ##>   {:added "1.0"
  ##>    :static true}
  ##>   [x] x)
  ##> nil
``` 

``` clojure
(instance? java.util.Collection [1 2 3])
  ##> true
``` 

#### Conventions for Parameter Names

Don't use a function's name as parameter name or the function will be shadowed.

## Chapter 2: Exploring Clojure

### Reading Clojure

``` clojure
test01.core=> (+ 2 3)
5
test01.core=> (+ 1 2 3)
6
test01.core=> (+)
0
``` 

Arbitrary-precision numbers: `BigDecimal`: append `M` to a number

``` clojure
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

``` clojure
(quote (1 2 3))
``` 

``` clojure
test01.core=> (quote (1 2 3))
(1 2 3)
test01.core=> (1 2 3)
Execution error (ClassCastException) at test01.core/eval1601 (form-init4977993031106662145.clj:1).
java.lang.Long incompatible with clojure.lang.IFn
``` 

Short form of `quote`

``` clojure
'(1 2 3)
``` 

Sets: unordered collections

- Fast element addition/removal
- Uniqueness
- Check if a set contains a value

Maps: collections of key/value pairs

``` clojure
{"a" 1 "b" 2}
``` 

Any data structure can be a key in a map.

Most common key type is the clojure keyword.

Keyword: begin with colon. They resolve to themselves.

``` clojure
:foo
``` 

If several maps have keys in common, create a record:

``` clojure
(defrecord Book [title author])
``` 

Instantiate that record with `->Book` constructor function:

``` clojure
(->Book "title01" "author01")
  ##> #test01.core.Book{:title "title01", :author "author01"}
``` 

### Strings and Characters

`str`: takes any objects, converts them to strings and concatenates them

``` clojure
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

``` clojure
(true? expr)
(false? expr)
(nil? expr)
(zero? expr)
``` 

Only `nil` is `nil?`, only `true` is `true?`

``` clojure
(true? true)
  ##> true
(true? "foo")
  ##> false
``` 

Find other predicate functions:

``` clojure
(find-doc #"\?$")
``` 

`#"\?$"` is a literal regex.

### Functions

``` clojure
(str "hello" " " "world")
  ##> "hello world"
``` 

A function call is a list whose first element resolves to a function.

Function names are usually hyphenated.

Predicate functions end with a question mark.

``` clojure
(string? "hello")
(keyword? :hello)
(symbol? 'hello)
``` 

To define functions: `defn`

``` clojure
(defn name doc-string? attr-map? [params*] prepost-map? body)
``` 

`attr-map`: associates metadata with function's var

`prepost-map?`: defines pre and postconditions

`&`: variable arity

``` clojure
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

``` clojure
(filter (fn [w] (> (count w) 2)) (str/split "A fine day" #"\W+"))
  ##> ("fine" "day")
``` 

opt02: Using `#(body)`

`#(body)`: shorter form for anonymous functions

``` clojure
(filter #(> (count %) 2) (str/split "A fine day" #"\W+"))
``` 

Parameters named: `%1 %2` final `%&` collects the rest

`%` is also first parameter

opt03: Using `let`

``` clojure
(defn indexable-words [text]
  (let [indexable-word? (fn [w] (> (count w) 2))
		(filter indexable-word? (str/split text #"\W+"))]))
``` 

`let`: defines a new name inside the lexical scope

opt04: create a function dynamically 

``` clojure
(defn make-greeter [greeting-prefix]
  (fn [username] (str greeting-prefix ", " username)))
``` 

You can use `def` to name functions created by `make-greeter`

``` clojure
(def hello-greeting (make-greeter "Hello"))
``` 

Or 

``` clojure
((make-greeter "Howdy") "pardner")
``` 

#### When to use Anonymous Functions

### Vars, Bindings, and Namespaces

A namespace is a collection of names (symbols) that refer to vars.

Each var is bound to a value.

#### Vars

`def` and `defn` defines an object that is stored in var. 

``` clojure
(def foo 10)
  ##> #'test01.core/foo
``` 

This creates a var named `user/foo`

`user/foo` symbol refers to a var that is bound to the value `10`

root binding: The initial value of a var 

Referring to a var directly: `var`

``` clojure
(var a-symbol)
(var foo)
  ##> #'test01.core/foo
``` 

`var`: equivalent reader macro `#'`

``` clojure
 #'foo
  ##> #'test01.core/foo
``` 

Why refer to a var directly?

#### Bindings

``` clojure
(defn triple [number] (* 3 number))
(triple 10)
  ##> 30
``` 

`let` creates a set of lexical bindings

``` clojure
(let [bindings*] exprs*)
``` 

`bindings`: multiple bindings 

Value of `let` expression is the value of last `exprs`

``` clojure
(defn square-corners [bottom left size]
  (let [top (+ bottom size)
	right (+ left size)]
    [[bottom left] [top left] [top right] [bottom right]]))
``` 

#### Destructuring

opt01: binding the complete data structure 

``` clojure
(defn greet-author-1 [author]
  (println "Hello," (:first-name author)))
``` 

Calling it:

``` clojure
(greet-author-1 {:last-name "X" :first-name "Ali"})
  ##> Hello, Ali
``` 

opt02: binding only part of the collection

``` clojure
(defn greet-author-2 [{fname :first-name}]
  (println "Hello," fname))
``` 

``` clojure
(greet-author-2 {:last-name "X" :first-name "Ali"})
  ##> Hello, Ali
``` 

`{fname :first-name}` binds `fname` to `:first-name` of the function argument

Ex: bind only the first two vars in a binding:

``` clojure
(let [[x y] [1 2 3]]
  [x y])
  ##> [1 2]
``` 

Ex: skip elements

``` clojure
(let [[_ _ z] [1 2 3]]
  z)
	##> 3
``` 

Ex: bind both a collection and elements

``` clojure
(let [[x y :as coords] [1 2 3 4]]
  (str "x " x ", y " y " total coords " (count coords)))
  ##> "x 1, y 2 total coords 4"
``` 

Ex: take a string and return first three words

``` clojure
(require '[clojure.string :as str])
(defn ellipsize [words]
  (let [[w1 w2 w3] (str/split words #"\s+")]
    (str/join " " [w1 w2 w3 "..."])))
``` 

``` clojure
(ellipsize "The quick brown fox jumps")
  ##> "The quick brown ..."
``` 

#### Namespaces

Root bindings live in a namespace.

`resolve`: returns var that a symbol will resolve to in the current namespace

``` clojure
(resolve 'foo)
  ##> #'test01.core/foo
``` 

`in-ns`: create/switch namespace

``` clojure
(in-ns 'myapp)
``` 

You should `use` `clojre.core` namespace when you move to a new namespace.

``` clojure
(clojure.core/use 'clojure.core)
``` 

Class names outside `java.lang` must be fully qualified:

``` clojure
java.io.File/separator
  ##> "/"
``` 

`import` allows using short names:

``` clojure
(import '(java.io InputStream File))
  ##> java.io.File
``` 

`import` is for java classes.

`require` and `:as` is for clojure functions

``` clojure
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

``` clojure
(ns name & references)
``` 

`references` can include `:import` `:require` and `:use`

``` clojure
(ns examples.exploring
  (:require [clojure.string :as str])
	(:import (java.io File)))
``` 

#### Metadata

Metadata is data that is orthogonal to the logical value of an object. 

``` clojure
(meta #'str)
  ##> {:added "1.0", :ns #object[clojure.lang.Namespace 0x1caefa76 "clojure.core"], :name str, :file "clojure/core.clj", :static true, :column 1, :line 544, :tag java.lang.String, :arglists ([] [x] [x & ys]), :doc "With no args, returns the empty string. With one arg x, returns\n  x.toString().  (str nil) returns the empty string. With more than\n  one arg, returns the concatenation of the str values of the args."}
``` 

Common metadata keys:

``` clojure
:ns	Namespace
...
``` 

Add your own key/value pairs:

``` clojure
^metadata form
``` 

``` clojure
(defn ^{:tag String} shout [^{:tag String} s] (clojure.string/upper-case s))
``` 

``` clojure
(meta #'shout)
  ##> {:tag java.lang.String, :arglists ([s]), :line 1, :column 1, :file "/private/var/folders/f9/d201s84j0gb95830cjhp09_m0000gq/T/form-init4977993031106662145.clj", :name shout, :ns #object[clojure.lang.Namespace 0x463928b7 "user"]}
``` 

`^Classname`: short form for `^{:tag Classname}`

``` clojure
(defn ^String shout [^String s] (clojure.string/upper-case s))
``` 

#### Calling Java

##### trailing dot for new

``` clojure
(new classname)
``` 

``` clojure
(new java.util.Random)
``` 

`.`: trailing dot. short form for `new`

``` clojure
(java.util.Random.)
``` 

``` clojure
(def rnd (java.util.Random.))
``` 

##### dot syntax

Call methods: using dot special form

``` clojure
(. class-or-instance member-symbol & args)
(. class-or-instance (member-symbol & args))
``` 

``` clojure
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

``` clojure
(. p -x)
  ##> 10
``` 

###### dot dot syntax

https://clojure.org/reference/java_interop#dot

> Macro. Expands into a member access (.) of the first member on the first argument, followed by the next member on the result, etc. For instance:

``` clojure
(.. System (getProperties) (get "os.name"))
;; expands to:
(. (. System (getProperties)) (get "os.name"))
``` 

> but is easier to write, read, and understand. See also the -> macro which can be used similarly:

``` clojure
(-> (System/getProperties) (.get "os.name"))
``` 

##### short form for dot syntax

``` clojure
(.method instance & args)
(.field instance)
(.-field instance)
(Class/method & args)
Class/field
``` 

``` clojure
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

``` clojure
(import (package-symbol & class-name-symbols)*)
``` 

``` clojure
(import '(java.util Random Locale)
  '(java.text MessageFormat))
Random
  ##> java.util.Random
Locale
  ##> java.util.Locale
``` 

`javadoc`

``` clojure
(require '[clojure.java.javadoc :as jdoc])
(jdoc/javadoc java.net.URL)
``` 

### Comments

`;`: create comment

``` clojure
;; this is a comment
``` 

`#_`: ignore line

``` clojure
 #_(println "hello")
``` 

### Flow Control

#### if

``` clojure
(defn is-small? [number]
  (if (< number 100) "yes"))
``` 

``` clojure
(is-small? 50)
  ##> "yes"
``` 

`else` is third argument

``` clojure
(defn is-small? [number]
  (if (< number 100) "yes" "no"))
(is-small? 500)
  ##> "no"
``` 

#### do

`do` is for side effects

``` clojure
(defn is-small? [number]
  (if (< number 100)
    "yes"
    (do 
      (println "Saw a big number" number)
      "no")))
``` 

``` clojure
(is-small? 500)
  ##> Saw a big number 500
  ##> "no"
``` 

#### loop/recur

opt01: use `loop` and `recur`

``` clojure
(loop [bindings*] exprs*)
``` 

`loop` sets a recursion point, which is targeted by `recur`

``` clojure
(recur exprs*)
``` 

``` clojure
(loop [result [] x 5]
	(if (zero? x)
		result
		(recur (conj result x) (dec x))))
``` 

`loop` binds `result` to `[]` and `x` to `5`

`recur` rebinds `x` and `result`

opt02: use function and `recur`

``` clojure
(defn countdown [result x]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))
``` 

``` clojure
(countdown [] 5)
  ##> [5 4 3 2 1]
``` 

opt03: use sequence library

``` clojure
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

``` clojure
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

``` clojure
(first aseq)
``` 

`rest` everything after first item

``` clojure
(rest aseq)
``` 

`cons` construct a new sequence by adding an item to the front:

``` clojure
(cons elem aseq)
``` 

They are declared in interface: `clojure.lang.ISeq`

`seq` return a seq an any seq-able collection:

``` clojure
(seq coll)
``` 

`next`: return seq of item after the first

``` clojure
(next aseq)
``` 

Equivalent to: `(seq (rest aseq))`

``` clojure
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

``` clojure
(seq? (rest [1 2 3]))
  ##> true
``` 

maps as seqs:

``` clojure
(first {:fname "Ali" :lname "Ak"})
  ##> [:fname "Ali"]
(rest {:fname "Ali" :lname "Ak"})
  ##> ([:lname "Ak"])
(cons [:mname "Bey"] {:fname "Ali" :lname "Ak"})
  ##> ([:mname "Bey"] [:fname "Ali"] [:lname "Ak"])
``` 

sets as seqs:

``` clojure
(first #{:the :quick :brown})
  ##> :the
(rest #{:the :quick :brown})
  ##> (:quick :brown)
(cons :fox #{:the :quick :brown})
  ##> (:fox :the :quick :brown)
``` 

To get a reliable order in sets use:

``` clojure
(sorted-set & elements)
``` 

``` clojure
(sorted-set :the :quick :brown)
  ##> #{:brown :quick :the}
``` 

To get a reliable order in maps use:

``` clojure
(sorted-map & elements)
``` 

`conj`: adds elements to a collection

`into`: adds items in a collection to another

``` clojure
(conj coll element & elements)
(into to-coll from-coll)
``` 

``` clojure
(conj '(1 2 3) :a)
  ##> (:a 1 2 3)
(cons :a '(1 2 3))
  ##> (:a 1 2 3)
(into [1 2 3] [:a :b])
  ##> [1 2 3 :a :b]
``` 

The sequence functions always return a seq. 

``` clojure
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

``` clojure
(range start? end? step?)
``` 

``` clojure
(range 10)
(range 10 20)
(range 1 25 2)
(range 0 -1 -0.25)
(range 1/2 4 1)
``` 

`repeat n x`

``` clojure
(repeat 5 1)
  ##> (1 1 1 1 1)
(repeat 3 "x")
  ##> ("x" "x" "x")
``` 

`iterate`: continues forever.

``` clojure
(iterate f x)
``` 

``` clojure
(take 3 (iterate inc 1))
  ##> (1 2 3)
``` 

`take`: first n items 

``` clojure
(take n sequence)
``` 

use case: finite view onto an infinite collection

``` clojure
(def whole-numbers (iterate inc 1))
``` 

`repeat`: lazy, infinite sequence

``` clojure
(repeat x)
``` 

``` clojure
(take 3 (repeat 1))
  ##> (1 1 1)
``` 

`cycle`: cycles a collection infinitely

``` clojure
(cycle coll)
``` 

``` clojure
(take 5 (cycle (range 2)))
  ##> (0 1 0 1 0)
``` 

`interleave`: takes multiple collections. interleaves values from each collection until one of the collections is exhausted.

``` clojure
(interleave & colls)
``` 

``` clojure
(interleave whole-numbers ["a" "b" "c"])
  ##> (1 "a" 2 "b" 3 "c")
``` 

`interpose`: like `interleave` but elements separated by a separator

``` clojure
(interpose separator coll)
``` 

``` clojure
(interpose "," ["a" "b" "c"])
  ##> ("a" "," "b" "," "c")
``` 

use case: to produce output strings with `apply str`

``` clojure
(apply str (interpose "," ["a" "b" "c"]))
  ##> "a,b,c"
``` 

This idiom is done by: `clojure.string/join`

``` clojure
(join separator sequence)
``` 

``` clojure
(require '[clojure.string :refer [join]])
(join \, ["a" "b" "c"])
  ##> "a,b,c"
``` 

Create a collection of that type:

``` clojure
(list & elements)
(vector & elements)
(hash-set & elements)
(hash-map key-1 val-1)
``` 

`set`: similar to `hash-set`. This expects a collection

``` clojure
(set [1 2 3])
  ##> #{1 3 2}
``` 

``` clojure
(hash-set 1 2 3)
  ##> #{1 3 2}
``` 

`vec`: similar to `vector`. It takes a collection

``` clojure
(vec (range 3))
  ##> [0 1 2]
``` 

#### Filtering Sequences

`filter`

``` clojure
(filter pred coll)
``` 

``` clojure
(take 3 (filter even? whole-numbers))
  ##> (2 4 6)
``` 

`take-while`

``` clojure
(take-while pred coll)
``` 

``` clojure
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

``` clojure
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

``` clojure
(drop-while pred coll)
``` 

``` clojure
(drop-while consonant? "ali-veli")
  ##> (\a \l \i \- \v \e \l \i)
(drop-while consonant? "grass")
  ##> (\a \s \s)
``` 

`split-at`: split a collection into two

``` clojure
(split-at index coll)
(split-with pred coll)
``` 

``` clojure
(split-at 3 (range 4))
  ##> [(0 1 2) (3)]
(split-with #(<= % 2) (range 4))
  ##> [(0 1 2) (3)]
``` 

#### Sequence Predicates

`every?`

``` clojure
(every? pred coll)
(every? odd? [1 3 5])
  ##> true
``` 

`some`: returns actual value of the first match

``` clojure
(some pred coll)
(some even? [1 2 3])
  ##> true
(some even? [1 3])
  ##> nil
(some identity [nil false 1 nil 2])
  ##> 1
``` 

Check if a sequence contains the value 3:

``` clojure
(some #{3} (range 5))
  ##> 3
``` 

``` clojure
(not-every? pred coll)
(not-any? pred coll)
``` 

#### Transforming Sequences

`map`

``` clojure
(map f coll)
``` 

``` clojure
(map #(format "<p>%s</p>" %) ["ali" "veli"])
  ##> ("<p>ali</p>" "<p>veli</p>")
``` 

if map takes multiple collections, then f must be a function of multiple arguments too.

``` clojure
(map #(format "<%s>%s</%s>" %1 %2 %1) ["h1" "h2"] ["ali" "veli"])
  ##> ("<h1>ali</h1>" "<h2>veli</h2>")
``` 

`reduce`

``` clojure
(reduce f coll)
``` 

`reduce` applies `f` on the first two elements in `coll` and then to the result and the third element.

``` clojure
(reduce + (range 1 5))
  ##> 10
(reduce * (range 1 5))
  ##> 24
``` 

`sort`

``` clojure
(sort comp? coll)
(sort-by a-fn comp? coll)
``` 

`sort-by` sorts by the result of calling `a-fn` on each element

``` clojure
(sort [5 1 3])
  ##> (1 3 5)
(sort-by #(.toString %) [15 1 3])
  ##> (1 15 3)
``` 

`comp` specifies comparison function

``` clojure
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

``` clojure
(for [binding-form coll-expr filter-expr? ...] expr)
``` 

this is more general than `map` and `filter`

``` clojure
(for [word ["ali" "veli"]]
  (format "<p>%s</p>" word))
  ##> ("<p>ali</p>" "<p>veli</p>")
``` 

Comprehensions use `:when` clause to emulate `filter`

``` clojure
(take 5 (for [n whole-numbers :when (even? n)] n))
  ##> (2 4 6 8 10)
``` 

`:while` clause continues only while its predicate holds true:

``` clojure
(for [n whole-numbers :while (even? n)] n)
  ##> ()
``` 

Multiple bindings:

``` clojure
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

``` clojure
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

``` clojure
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

``` clojure
(first (.getBytes "hello"))
  ##> 104
(rest (.getBytes "hello"))
  ##> (101 108 108 111)
``` 

Hastables and Maps:

``` clojure
(first (System/getProperties))
``` 

Strings:

``` clojure
(first "Hello")
  ##> \H
(rest "Hello")
  ##> (\e \l \l \o)
``` 

To convert it back to string: use `(apply str seq)`

``` clojure
(apply str (reverse "hello"))
  ##> "olleh"
``` 

#### Seq-ing Regular Expressions

``` clojure
(re-matcher regexp string)
``` 

Don't use this. Better: `re-seq`

``` clojure
(re-seq regexp string)
``` 

``` clojure
(re-seq #"\w+" "ali veli")
  ##> ("ali" "veli")
``` 

#### Seq-ing the File System

``` clojure
(import 'java.io.File)
(.listFiles (File. "."))
(seq (.listFiles (File. ".")))
``` 

``` clojure
(map #(.getName %) (seq (.listFiles (File. "."))))
(map #(.getName %) (.listFiles (File. ".")))
  ##> ("project.clj" "LICENSE" "test" ...)
``` 

#### Seq-ing a Stream

``` clojure
(require '[clojure.java.io :refer [reader]])
(take 2 (line-seq (reader "src/examples/utils.clj")))
  ##> (";---" "; Excerpted from \"Programming Clojure, Third Edition\",")
``` 

`with-open`: close the resource after using

``` clojure
(with-open [rdr (reader "src/examples/utils.clj")]
	(count (line-seq rdr)))
``` 

Ex: count clojure loc

``` clojure
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

``` clojure
(clojure-loc (java.io.File. "/Users/mertnuhoglu/codes/clojure/programming_in_clojure/code/test01"))
  ##> 1991
``` 

### Calling Structure-Specific Functions

#### Functions on Lists

`peek` get first element of a list

`pop` get the remainder

``` clojure
(peek '(1 2 3))
  ##> 1
(pop '(1 2 3))
  ##> (2 3)
``` 

`get` value at index

``` clojure
(get [:a :b :c] 1)
  ##> :b
``` 

Vectors are functions as well. They work like `get`

``` clojure
([1 2 3] 1)
  ##> 2
``` 

`assoc` associates a new value with an index

``` clojure
(assoc [0 1 2] 1 :two)
  ##> [0 :two 2]
``` 

`subvec` returns a subvector

``` clojure
(subvec avec start end?)
``` 

``` clojure
(subvec [1 2 3 4] 1 4)
  ##> [2 3 4]
``` 

You can simulate it with `drop` and `take`

``` clojure
(take 3 (drop 1 [1 2 3 4]))
  ##> (2 3 4)
``` 

Vector specific functions are faster.

#### Functions on Maps

``` clojure
(keys map)
(vals map)
``` 

``` clojure
(keys {:a 1, :b 2})
  ##> (:a :b)
(vals {:a 1, :b 2})
  ##> (1 2)
``` 

`get` return value for a key

``` clojure
(get map key default?)
``` 

``` clojure
(get {:a 1, :b 2} :a)
  ##> 1
``` 

You can use map directly too:

``` clojure
({:a 1, :b 2} :a)
  ##> 1
``` 

Keywords are also functions

``` clojure
(:a {:a 1, :b 2})
  ##> 1
``` 

`contains?`

``` clojure
(contains? map key)
``` 

``` clojure
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

``` clojure
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

``` clojure
(merge-with 
 concat 
 {:flintstone, ["Fred"], :rubble ["Barney"]}
 {:flintstone, ["Wilma"], :rubble ["Betty"]}
 {:flintstone, ["Pebbles"], :rubble ["Bam-Bam"]})
  ##> {:flintstone ("Fred" "Wilma" "Pebbles"), :rubble ("Barney" "Betty" "Bam-Bam")}
``` 

#### Functions on Sets

``` clojure
(require '[clojure.set :refer :all])
(def languages #{"java" "c" "d" "clojure"})
(def beverages #{"java" "chai" "pop"})
``` 

`union`

`intersection`

`difference`

`select` subset matching a predicate

``` clojure
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

``` clojure
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

``` clojure
(rename relation rename-map)
``` 

``` clojure
(rename compositions {:name :title})
  ##> #{{:composer "Giuseppe Verdi", :title "Requiem"} {:composer "W. A. Mozart", :title "Requiem"} {:composer "J. S. Bach", :title "The Art of the Fugue"} {:composer "J. S. Bach", :title "Musical Offering"}}
``` 

`select` similar to WHERE in SQL

``` clojure
(select pred relation)
``` 

``` clojure
(select #(= (:name %) "Requiem") compositions)
  ##> #{{:name "Requiem", :composer "Giuseppe Verdi"} {:name "Requiem", :composer "W. A. Mozart"}}
``` 

`project` similar to SELECT in SQL

``` clojure
(project relation keys)
``` 

``` clojure
(project compositions [:name])
  ##> #{{:name "The Art of the Fugue"} {:name "Musical Offering"} {:name "Requiem"}}
``` 

List comprehension for cross product in SQL

``` clojure
(for [m compositions c composers] (concat m c))
  ##> (([:name "Musical Offering"] [:composer "J. S. Bach"] [:composer "Giuseppe Verdi"] [:country "Italy"]) ([:name "Musical Offering"] [:composer "J. S. Bach"] [:composer "J. S. Bach"] [:country "Germany"]) ...
``` 

`join`: Subset of full cross product based on shared keys

``` clojure
(join relation-1 relation-2 keymap?)
``` 

``` clojure
(join compositions composers)
  ##> #{{:composer "W. A. Mozart", :country "Austria", :name "Requiem"} {:composer "J. S. Bach", :country "Germany", :name "Musical Offering"} {:composer "Giuseppe Verdi", :country "Italy", :name "Requiem"} {:composer "J. S. Bach", :country "Germany", :name "The Art of the Fugue"}}
``` 

``` clojure
(join composers nations {:country :nation})
  ##> #{{:composer "W. A. Mozart", :country "Austria", :nation "Austria", :language "German"} 
  ##> {:composer "J. S. Bach", :country "Germany", :nation "Germany", :language "German"} 
  ##> {:composer "Giuseppe Verdi", :country "Italy", :nation "Italy", :language "Italian"}}
``` 

``` clojure
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

``` clojure
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

``` clojure
(def a '(1 2))
(def b (cons 0 a))
``` 

``` clojure
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

``` clojure
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

``` clojure
(defn recur-fibo [n]
  (letfn [(fib 
            [current next n]
            (if (zero? n)
              current
              (recur next (+ current next) (dec n))))] 
    (fib 0N 1N n)))
``` 

We use `recur` instead of `fib` direct call.

``` clojure
(recur-fibo 10)
``` 

#### Lazy sequences

`lazy-seq` macro constructs lazy sequences

``` clojure
(lazy-seq & body)
``` 

It invokes its body when needed. It caches the result. 

``` clojure
(defn lazy-seq-fibo 
  ([] 
     (concat [0 1] (lazy-seq-fibo 0N 1N))) 
  ([a b]
     (let [n (+ a b)]                    
       (lazy-seq                         
	(cons n (lazy-seq-fibo b n)))))) 
``` 

``` clojure
(take 10 (lazy-seq-fibo))
  ##> (0 1 1N 2N 3N 5N 8N 13N 21N 34N)
(rem (nth (lazy-seq-fibo) 1000000) 1000)
``` 

opt: use sequence library to make lazy sequences

``` clojure
(take 5 (iterate (fn [[a b]] [b (+ a b)]) [0 1]))
  ##> ([0 1] [1 1] [1 2] [2 3] [3 5])
(defn fibo []
 (map first (iterate (fn [[a b]] [b (+ a b)]) [0N 1N])))
(take 5 (fibo))
  ##> (0N 1N 1N 2N 3N)
``` 

#### Coming to Realization

`*print-length*` Configure how many items printer will print

``` clojure
(set! *print-length* 10)
(take 50 (fibo))
  ##> (0N 1N 1N 2N 3N 5N 8N 13N 21N 34N ...)
(fibo)
  ##> (0N 1N 1N 2N 3N 5N 8N 13N 21N 34N ...)
``` 

#### Losing Your Head

What happens if you hold a reference to a part of the sequence?

Then all sequence will be realized. To prevent it use: `lazy-cat`

``` clojure
(def head-fibo (lazy-cat [0N 1N] (map + head-fibo (rest head-fibo))))
``` 

Losing head of a lazy sequence is a good idea.

### Lazier Than Lazy

``` clojure
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

``` clojure
(count-heads-pairs [:h :h :h :t :h])
  ##> 2
``` 

#### Transforming the Input Sequence

opt01:

Transform the sequence to 2-tuples:

``` clojure
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

``` clojure
(by-pairs [:h :h :h :t :h])
  ##> ((:h :h) (:h :h) (:h :t) (:t :h))
``` 

``` clojure
(defn count-heads-pairs [coll]
  (count 
    (filter 
      (fn [pair] (every? #(= :h %) pair)) 
		  (by-pairs coll))
  ))
``` 

opt02: use sequence library

``` clojure
(partition size step? coll)
``` 

``` clojure
(partition 2 1 [:h :h :h :t :h])
  ##> ((:h :h) (:h :h) (:h :t) (:t :h))
``` 

`count-if`

``` clojure
(def ^{:doc "Count items matching a filter"}
  count-if (comp count filter))
``` 

`comp` composes multiple functions

``` clojure
(comp f & fs)
``` 

``` clojure
(defn count-runs
  "Count runs of length n where pred is true in coll."
  [n pred coll]
  (count-if #(every? pred %) (partition n 1 coll)))
``` 

``` clojure
(count-runs 2 #(= % :h) [:h :h :h :t :h])
  ##> 2
``` 

#### Currying and Partial Application

``` clojure
(def ^{:doc "Count runs of length two that are both heads"}
  count-heads-pairs (partial count-runs 2 #(= % :h)))
``` 

`partial`

``` clojure
(partial f & partial-args)
``` 

``` clojure
(partial count-runs 1 #(= % :h))
(fn [coll] (count-runs 1 #(= % :h) coll)) ; equivalent
``` 

### Recursion Revisited

``` clojure
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

``` clojure
(map my-even? (range 4))
  ##> (true false true false)
``` 

``` clojure
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

``` clojure
(defn parity [n]
  (loop 
    [n n par 0]
    (if 
      (= n 0)
      par
      (recur (dec n) (- 1 par)))))
``` 

``` clojure
(map parity (range 5))
  ##> (0 1 0 1 0)
``` 

``` clojure
(defn my-even? [n] (= 0 (parity n)))
(defn my-odd? [n] (= 1 (parity n)))
``` 

#### Trampolining Mutual Recursion

``` clojure
(trampoline f & partial-args)
``` 

``` clojure
(trampoline list)
  ##> ()
(trampoline + 1 2)
  ##> 3
``` 

``` clojure
(defn my-odd? [n]
  (if (= n 0)
    false
    #(my-even? (dec n)))) 

(defn my-even? [n]
  (if (= n 0)
    true
    #(my-odd? (dec n)))) 
``` 

``` clojure
(trampoline my-even? 10000)
  ##> true
``` 

#### Replacing Recursion with Laziness

Laziness is the most used technique for elimination recursion.

``` clojure
(defn deeply-nested [n]
  (loop 
    [n n result '(bottom)]
    (if (= n 0)
      result
      (recur (dec n) (list result)))))
``` 

``` clojure
(deeply-nested 3)
  ##> ((((bottom))))
``` 

### Eager Transformations

``` clojure
(defn square [x] (* x x))

(defn sum-squares-seq [n]
  (vec (map square (range n))))
``` 

``` clojure
(defn sum-squares
  [n]
  (into [] (map square) (range n)))
``` 

#### Optimizing Performance

## Chapter 5 - Specifications

### Defining Specs

``` clojure
(require '[clojure.spec.alpha :as s])
``` 

``` clojure
(s/def name spec)
``` 

`::` auto-resolved keywords. Current namespace is used as the qualifier

`::recipe/ingredient` expand to: `:cookingco.recipe/ingredient`

### Validating Data

``` clojure
(s/def :my.app/company-name string?)
(s/valid? :my.app/company-name "Acme") 
  ##> true
``` 

#### Enumerated values

``` clojure
(s/def :marble/color #{:red :green :blue})
``` 

``` clojure
(s/def ::bowling/ranged-roll (s/int-in 0 11))
``` 

#### Handling nil

``` clojure
(s/def ::bowling/company-name-2 (s/nilable string?))
``` 

#### Logical Specs

``` clojure
(s/def ::odd-int (s/and int? odd?))
(s/def ::odd-or-42 (s/or :odd ::odd-int :42 #{42}))
``` 

`s/conform` how a value matched a spec

``` clojure
(s/conform ::odd-or-42 42)
  ##> [:42 42]
(s/conform ::odd-or-42 19)
  ##> [:odd 19]
``` 

`s/explain` why an invalid value didn't match

``` clojure
(s/explain ::odd-or-42 0)
  ##> 0 - failed: odd? at: [:odd] spec: :test01.core/odd-int
  ##> 0 - failed: #{42} at: [:42] spec: :test01.core/odd-or-42
``` 

#### Collection Specs

`s/coll-of`

``` clojure
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

``` clojure
(s/def ::my-set (s/coll-of int? :kind set? :min-count 2))
(s/valid? ::my-set #{10 20})
``` 

`s-map-of`: keys and values follow a spec

``` clojure
(s/def ::scores (s/map-of string? int?))
(s/valid? ::scores {"Stu" 100 "Alex" 200})
  ##> true
``` 

`s/every` : sample large collections

#### Collection Sampling

#### Tuples

``` clojure
(s/def ::point (s/tuple float? float?))
(s/conform ::point [1.3 2.7])
``` 

#### Information Maps

``` clojure
{::music/id #uuid ".."
 ::music/artist "Rush"
 ::music/title "Moving"
 ::music/date #inst "1981-02-12"}
``` 

``` clojure
(s/def ::music/id uuid?)
(s/def ::music/artist string?)
(s/def ::music/title string?)
(s/def ::music/date inst?)
``` 

``` clojure
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

``` clojure
(s/def ::cat-example (s/cat :s string? :i int?))
(s/valid? ::cat-example ["abc" 100])
(s/conform ::cat-example ["abc" 100])
  ##> {:s "abc", :i 100}
``` 

`s/alt` alternatives within sequential structure

``` clojure
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

``` clojure
(s/def ::oe (s/cat :odds (s/+ odd?) :even (s/? even?)))
(s/conform ::oe [1 3 5 100])
  ##> {:odds [1 3 5], :even 100}
``` 

#### Variable Argument Lists

Zero or more arguments:

``` clojure
(s/def ::println-args (s/* any?))
``` 

Some fixed arguments and a variable argument at the end

``` clojure
(clojure.set/intersection #{1 2} #{2 3} #{2 5})
  ##> #{2}
``` 

``` clojure
(s/def ::intersection-args 
  (s/cat :s1 set?
	       :sets (s/* set?)))
(s/conform ::intersection-args '[#{1 2} #{2 3} #{2 5}])
  ##> {:s1 #{1 2}, :sets [#{3 2} #{2 5}]}
``` 

``` clojure
(s/def ::meta map?)
(s/def ::validator ifn?)
(s/def ::atom-args 
  (s/cat :x any? :options (s/keys* :opt-un [::meta ::validator])))
(s/conform ::atom-args [100 :meta {:foo 1} :validator int?])
  ##> {:x 100, :options {:meta {:foo 1}, :validator #object[clojure.core$int_QMARK_...
``` 

#### Multi-arity Argument Lists

### Specifying Functions

``` clojure
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

Arity overloading is one way to provide default values for arguments

``` clojure
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

``` clojure
(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))

(favorite-things "Doreen" "gum" "shoes" "kara-te")
; => "Hi, Doreen, here are my favorite things: gum, shoes, kara-te"
``` 

## Destructuring

Binding names to values within a collection.

``` clojure
;; Return the first element of a collection
(defn my-first
  [[first-thing]] ; Notice that first-thing is within a vector
  first-thing)

(my-first ["oven" "bike" "war-axe"])
; => "oven"
``` 

Destructuring maps:

opt01:

``` clojure
(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.33})
; => Treasure lat: 100
; => Treasure lng: 50
``` 

opt02: short form

``` clojure
(defn announce-treasure-location
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))
``` 

`:as` keep access to original map argument:

``` clojure
(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))

  ;; One would assume that this would put in new coordinates for your ship
  (steer-ship! treasure-location))
``` 

## Anonymous Functions

`%&`: Rest parameter

``` clojure
(#(identity %&) 1 "blarg" :yip)
; => (1 "blarg" :yip)
``` 

## Returning Functions

Returned functions are closures

``` clojure
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)
; => 10
``` 

## Ex: symmetrizer function

``` clojure
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

``` clojure
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

``` clojure
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

``` clojure
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

``` clojure
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

``` clojure
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

``` clojure
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

# Book: Living Clojure - Carin Meier

``` clojure
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

``` clojure
(first [1 2])
(rest [1 2])
(nth [1 2] 0)
(last [1 2])
``` 

Collections in Common

``` clojure
(count [1 2 3])
(conj [1 2 3] 5)
``` 

Maps

``` clojure
(merge {:a 1 :b 2} {:a 3})
  ##> {:a 3, :b 2}
``` 

Sets

``` clojure
(clojure.set/union #{1 2} #{2 3})
  ##> #{1 3 2}
(clojure.set/difference #{1 2} #{2 3})
(clojure.set/intersection #{1 2} #{2 3})
``` 

``` clojure
(1 #{1 2})
  ##> Exception
(:a #{:a :b})
``` 

``` clojure
conj
disj
contains?
``` 

## Symbols

`def` global vars

`let` temporary bindings

## Functions

``` clojure
defn
fn
#()
% %1 %2
& args
``` 

## Namespaces

``` clojure
(ns ns1)
(def f "hello")
f
ns1/f
``` 

## Flow

``` clojure
(class true)
(not true)
(= 2 3)
(not (= 2 3))
(not= 2 3)
(empty? [])
``` 

## The Power of Laziness

``` clojure
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

``` clojure
(defn alice-is [input]
	(loop [in input
	       out []]
		(if (empty? in)
		  out
			(recur (rest in)
			  (conj out (str "Alice is " (first in)))))))
``` 

## Data Transformations

``` clojure
(take 3 (map #(str %) (range)))
(def animal-print (doall (map #(println %) animals)))
``` 

# Article: Macros by Example - Stepan Parunashvili 

https://m.stopa.io/macros-by-example-6ddbc8f3d93b

## Example 1: nullthrows

### in js

``` clojure
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

``` clojure
(nil-throws (get-user "billy"))
``` 

something like:

``` clojure
(defn nil-throws [res]
  (if (nil? res)
    (throw "uh oh")
    res))
``` 

### macro:

macro:

``` clojure
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

``` clojure
(nil-throws (get-user "billy"))
``` 

will be replaced with:

``` clojure
(let [result# (get-user db "billy")]
  (if (nil? result#)
    (throw (ex-info "uh oh, we got nil!" {:form '(get-user db "billy")})) 
    result#))
``` 

## Example 2: pipe

### in js

``` clojure
createBill(addToCart(cart, updatePrice(item, 100)))
``` 

convert to:

``` clojure
item |> updatePrice($$, 100) |> addToCart(cart, $$) |> createBill
``` 

or this:

``` clojure
|> [
  item, 
  updatePrice($$, 100), // updatePrice(item, 100)
  addToCart(cart, $$), // addToCart(cart, updatePrice(item, 100))
  createBill, // createbill(addToCart(cart, updatePrice(item, 100)))
]
``` 

#### opt01: a pipe function

``` clojure
pipe(item, (item) => updatePrice(item, 100))
``` 

Problem: needs anonymous functions

### in clojure

goal:

``` clojure
(|> item
    (update-price $$ 100)
    (add-to-cart cart $$)
    create-bill)
``` 

macro:

``` clojure
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

# Book: Clojure Cookbook - Van der Hart

Ref

		https://github.com/clojure-cookbook/clojure-cookbook
		~/codes/clojure/clojure-cookbook/README.md

## Chapter 01: Primitive Data

### Capitalization of a String

``` clojure
(clojure.string/capitalize "this is a proper sentence.")
;; -> "This is a proper sentence."
(clojure.string/upper-case "loud noises!") 
;; -> "LOUD NOISES!"
(clojure.string/lower-case "COLUMN_HEADER_ONE") 
;; -> "column_header_one"
``` 

### Clean Whitespace in a String

``` clojure
(clojure.string/trim " \tBacon ipsum dolor sit.\n") 
;; -> "Bacon ipsum dolor sit."

;; Collapse whitespace into a single space
(clojure.string/replace "Who\t\nput all this\fwhitespace here?" #"\s+" " ") 
;; -> "Who put all this whitespace here?"
    

;; Replace Windows-style line endings with Unix-style newlines
(clojure.string/replace "Line 1\r\nLine 2" "\r\n" "\n") 
;; -> "Line 1\nLine 2"
``` 

shorter:

``` clojure
(require '[clojure.string :as str])
``` 

### Combine/Join a String

``` clojure
(str "John" " " "Doe")
;; -> "John Doe"

(def lines ["#! /bin/bash\n", "du -a ./ | sort -n -r\n"]) 
(apply str lines)
;; -> "#! /bin/bash\ndu -a ./ | sort -n -r\n"
``` 

Better: `join`

``` clojure
(def food-items ["milk" "butter" "flour" "eggs"]) (clojure.string/join ", " food-items)
;; -> "milk, butter, flour, eggs"
(clojure.string/join [1 2 3 4]) ;; -> "1234"
``` 

Worse alternative: `str` and `interpose`

``` clojure
;; Constructing a CSV from a header string and vector of rows
(def header "first_name,last_name,employee_number\n") (def rows ["luke,vanderhart,1","ryan,neufeld,2"])
(apply str header (interpose "\n" rows))
;; -> "first_name,last_name,employee_number\nluke,vanderhart,1\nryan,neufeld,2"

``` 

### String to Character

``` clojure
(seq "ali")
  ##> (\a \l \i)
``` 

Any function taking a seq converts a string into characters.

``` clojure
(frequencies (clojure.string/lower-case "ali veli"))
  ##> {\a 1, \l 2, \i 2, \space 1, \v 1, \e 1}
``` 

``` clojure
;; Is every letter in a string capitalized?
(defn yelling? [s]
(every? #(or (not (Character/isLetter %))
                   (Character/isUpperCase %))
            s))
(yelling? "LOUD NOISES!") ;; -> true
(yelling? "Take a DEEP breath.") ;; -> false
``` 

### Character to/from Integer

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-05_integer-to-character-conversions.asciidoc`

``` clojure
(int \a)
;; -> 97
(map int "Hello, world!")
;; -> (72 101 108 108 111 44 32 119 111 114 108 100 33)
``` 

``` clojure
(char 97)
;; -> \a
``` 

### Formatting Strings

`str`

``` clojure
(def me {:first-name "Ryan", :favorite-language "Clojure"})
(str "My name is " (:first-name me)
     ", and I really like to program in " (:favorite-language me))
;; -> "My name is Ryan, and I really like to program in Clojure"

(apply str (interpose " " [1 2.000 (/ 3 1) (/ 4 9)]))
;; -> "1 2.0 3 4/9"
``` 

`format`

`%03d`: pad a digit with three zeros

``` clojure
(defn filename [name i]
  (format "%03d-%s" i name)) ; <1>

(filename "my-awesome-file.txt" 42)
;; -> "042-my-awesome-file.txt"
``` 

`%-20s`: left justify string. total width of 20 chars.

``` clojure
;; Create a table using justification
(defn tableify [row]
  (apply format "%-20s | %-20s | %-20s" row)) ; <2>

(def header ["First Name", "Last Name", "Employee ID"])
(def employees [["Ryan", "Neufeld", 2]
                ["Luke", "Vanderhart", 1]])

(->> (concat [header] employees)
     (map tableify)
     (mapv println))
;; *out*
;; First Name           | Last Name            | Employee ID
;; Ryan                 | Neufeld              | 2
;; Luke                 | Vanderhart           | 1
``` 

### Regex Match

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-07_regexp-matching.asciidoc`

`re-find`

``` clojure
(re-find #"\d+" "I've just finished reading Fahrenheit 451")
;; -> "451"
``` 

`re-matches` matches complete string

``` clojure
(re-matches #"\w+" "my-param")
;; -> nil
``` 

### Regex Match: successive matches

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-08_matching-strings.asciidoc`

`re-seq`

``` clojure
(re-seq #"\w+" "My Favorite Things")
;; -> ("My" "Favorite" "Things")
``` 

Matching groups (parantheses): return a vector for each match

``` clojure
(defn mentions [tweet]
  (re-seq #"(@|#)(\w+)" tweet))

(mentions "So long, @earth, and thanks for all the #fish. #goodbyes")
;; -> (["@earth" "@" "earth"] ["#fish" "#" "fish"] ["#goodbyes" "#" "goodbyes"])
``` 

### Regex Replace

``` clojure
(def about-me "My favorite color is green!")
(clojure.string/replace about-me "green" "red")
;; -> "My favorite color is red!"
``` 

### Regex Split

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-10_tokenizing-strings.asciidoc`

``` clojure
(clojure.string/split "HEADER1,HEADER2,HEADER3" #",")
;; -> ["HEADER1" "HEADER2" "HEADER3"]
``` 

### Pluralizing Strings

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-11_inflecting-strings.asciidoc`

``` clojure
(require '[inflections.core :as inf])

(inf/pluralize 1 "monkey")
;; -> "1 monkey"

(inf/pluralize 12 "monkey")
;; -> "12 monkeys"
``` 

### Converting Between Strings, Symbols, and Keywords

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-12_converting-stringlike-data.asciidoc`

string to symbol

``` clojure
(symbol "valid?")
;; -> valid?
``` 

symbol to string

``` clojure
(str 'valid?)
;; -> "valid?"
``` 

keyword to string

``` clojure
(name :triumph)
;; -> "triumph"

;; Or, to include the leading colon:
(str :triumph)
;; -> ":triumph"
``` 

symbol/string to keyword

``` clojure
(keyword "fantastic")
;; -> :fantastic

(keyword 'fantastic)
;; -> :fantastic
``` 

keyword to symbol

``` clojure
(symbol (name :wonderful))
;; -> wonderful
``` 

primary conversion functions

		str
		keyword
		symbol

namespace included:

``` clojure
;; If you only want the name part of a keyword
(name :user/valid?)
;; -> "valid?"

;; If you only want the namespace
(namespace :user/valid?)
;; -> "user"
``` 

### Precision Numbers

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-13_absolute-precision.asciidoc`

exponents

``` clojure
;; Avogadro's number
6.0221413e23
;; -> 6.0221413E23

;; 1 Angstrom in meters
1e-10
;; -> 1.0E-10
``` 

quote promotes numbers to Big types

``` clojure
(* 9999 9999 9999 9999 9999)
;; ArithmeticException integer overflow  clojure.lang.Numbers.throwIntOverflow

(*' 9999 9999 9999 9999 9999)
;; -> 99950009999000049999N
``` 

### Rational Numbers

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-14_working-with-rational-numbers.asciidoc`

``` clojure
(/ 1 3)
;; -> 1/3

(type (/ 1 3))
;; -> clojure.lang.Ratio

(* 3 (/ 1 3))
;; -> 1N
``` 

`rationalize`: converts decimals to rationals

``` clojure
(+ (/ 1 3) 0.3)
;; -> 0.6333333333333333

(rationalize 0.3)
;; -> 3/10

(+ (/ 1 3) (rationalize 0.3))
;; -> 19/30
``` 

### Parsing Numbers

``` clojure
(Integer/parseInt "-42")
;; -> -42

(Double/parseDouble "3.14")
;; -> 3.14
``` 

### Rounding and Truncating

Truncate using `int`

``` clojure
(int 2.0001)
;; -> 2

(int 2.999999999)
;; -> 2
``` 

Round

``` clojure
(Math/round 2.0001)
;; -> 2

(Math/round 2.999)
;; -> 3

;; This is equivalent to:
(int (+ 2.99 0.5))
;; -> 3

``` 

### Fuzzy Comparison

``` clojure
(defn fuzzy= [tolerance x y]
  (let [diff (Math/abs (- x y))]
    (< diff tolerance)))

(fuzzy= 0.01 10 10.000000000001)
;; -> true

(fuzzy= 0.01 10 10.1)
;; -> false
``` 

### Trigonometry

``` clojure
;; Calculating sin(a + b). The formula for this is
;; sin(a + b) = sin a * cos b + sin b cos a
(defn sin-plus [a b]
  (+ (* (Math/sin a) (Math/cos b))
     (* (Math/sin b) (Math/cos a))))

(sin-plus 0.1 0.3)
;; -> 0.38941834230865047
``` 

### Different Bases

`2r101`: `101` in `2` radix

``` clojure
2r101010
;; -> 42

3r1120
;; -> 42

16r2A
;; -> 42

36rABUNCH
;; -> 624567473
``` 

Output:

``` clojure
(Integer/toString 13 2)
;; -> "1101"

(Integer/toString 42 16)
;; -> "2a"

(Integer/toString 35 36)
;; -> "z"
``` 

Partially applying: Change order of args

``` clojure
(defn to-base [radix n]
  (Integer/toString n radix))

(def base-two (partial to-base 2))

(base-two 9001)
;; -> "10001100101001"
``` 


### Simple Statistics

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-20_simple-statistics.asciidoc`

``` clojure
(defn mean [coll]
  (let [sum (apply + coll)
        count (count coll)]
    (if (pos? count)
      (/ sum count)
      0)))

(mean [1 2 3 4])
;; -> 5/2

(mean [1 1.6 7.4 10])
;; -> 5.0

(mean [])
;; -> 0
``` 

``` clojure
(defn median [coll]
  (let [sorted (sort coll)
        cnt (count sorted)
        halfway (quot cnt 2)]
    (if (odd? cnt)
      (nth sorted halfway) ; <1>
      (let [bottom (dec halfway)
            bottom-val (nth sorted bottom)
            top-val (nth sorted halfway)]
        (mean [bottom-val top-val]))))) ; <2>

(median [5 2 4 1 3])
;; -> 3

(median [7 0 2 3])
;; -> 5/2  ; The average of 2 and 3.
``` 

### Random Numbers

``` clojure
(rand)
;; -> 0.0249306187447903

(rand)
;; -> 0.9242089829055088
``` 

`rand-int` integers

``` clojure
;; Emulating a six-sided die
(defn roll-d6 []
  (inc (rand-int 6)))

(roll-d6)
;; -> 1

(roll-d6)
;; -> 3
``` 

`rand-nth` take nth element

``` clojure
(rand-nth [1 2 3])
;; -> 1

(rand-nth '(:a :b :c))
;; -> :c
``` 

For nonsequential collections:

``` clojure
(rand-nth (seq #{:heads :tails}))
;; -> :heads
``` 

`shuffle`: randomly sort

``` clojure
(shuffle [1 2 3 4 5 6])
;; -> [3 1 4 5 2 6]
``` 

### Currency

``` clojure
$ lein try clojurewerkz/money
``` 

``` clojure
(require '[clojurewerkz.money.amounts    :as ma])
(require '[clojurewerkz.money.currencies :as mc])

;; $2.00 in USD
(def two (ma/amount-of mc/USD 2))
two
;; -> #<Money USD 2.00>


(ma/plus two two)
;; -> #<Money USD 4.00>

(ma/minus two two)
;; -> #<Money USD 0.00>

(ma/< two (ma/amount-of mc/USD 2.01))
;; -> true

(ma/total [two two two two])
;; -> #<Money USD 8.00>
``` 

IEEE 754 standard carry a certain imprecision by design

``` clojure
(- 0.23 0.24)
;; -> -0.009999999999999981
``` 

### UUID Unique IDs

``` clojure
(java.util.UUID/randomUUID)
;; -> #uuid "5358e6e3-7f81-40f0-84e5-750e29e6ee05"

(java.util.UUID/randomUUID)
;; -> #uuid "a6f92a6f-f736-468f-9e26-f392852825f4"
``` 

`squuid`: sortable and unique uuid

``` clojure
(def u1 (squuid))
u1
;; -> #uuid "527bf210-dfae-4c73-8b7a-302d3b511f41"

(def u2 (squuid))
u2
;; -> #uuid "527bf219-65f0-4241-a165-c5c541cb98ea"
``` 

### Date and Time

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-25_current-date.asciidoc`

``` clojure
(defn now []
  (java.util.Date.))

(now)
;; -> #inst "2013-04-06T14:33:45.740-00:00"

;; A few seconds later...
(now)
;; -> #inst "2013-04-06T14:33:51.234-00:00"
``` 

unix timestamp

``` clojure
(System/currentTimeMillis)
;; -> 1365260110635
``` 

### Dates as Literals

`#inst`

``` clojure
(def ryans-birthday #inst "1987-02-18T18:00:00.000-00:00")

(println ryans-birthday)
;; *out*
;; #inst "1987-02-18T18:00:00.000-00:00"
``` 

### Parsing Dates

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-28_formatting-dates.asciidoc`

wrapper over joda

``` clojure
$ lein try clj-time
``` 

``` clojure
(require '[clj-time.format :as tf])

;; To parse dates like "02/18/87"
(def government-forms-date (tf/formatter "MM/dd/yy"))

(tf/parse government-forms-date "02/18/87")
;; -> #<DateTime 1987-02-18T00:00:00.000Z>

(def wonky-format (tf/formatter "HH:mm:ss:SS' on 'yyyy-MM-dd"))
;; -> #'user/wonky-format

(tf/parse wonky-format "16:13:49:06 on 2013-04-06")
;; -> #<DateTime 2013-04-06T16:13:49.060Z>
``` 

`DateTime` is joda class

### Formatting Dates

``` clojure
(require '[clj-time.format :as tf])
(require '[clj-time.core :as t])

(tf/unparse (tf/formatters :date) (t/now))
;; -> "2013-04-06"

(def my-format (tf/formatter "MMM d, yyyy 'at' hh:mm"))
(tf/unparse my-format (t/now))
;; -> "Apr 6, 2013 at 04:54"
``` 

#### Convert joda from/to java date instances

java to joda

``` clojure
(require '[clj-time.coerce :as tc])

(tc/from-date (java.util.Date.))
;; -> #<DateTime 2013-04-06T17:03:16.872Z>
``` 

joda to java

``` clojure
(tc/to-date (t/now))
;; -> #inst "2013-04-06T17:03:57.239-00:00"

(tc/to-long (t/now))
;; -> 1365267761585
``` 

### Comparing Dates

Check `~/codes/clojure/clojure-cookbook/01_primitive-data/1-29_comparing-dates.asciidoc`

``` clojure
(defn now [] (java.util.Date.))
(def one-second-ago (now))
(Thread/sleep 1000)

;; Now is greater than (1) one second ago.
(compare (now) one-second-ago)
;; -> 1

;; One second ago is less than (-1) now.
(compare one-second-ago (now))
;; -> -1

;; "Equal" manifests as 0.
(compare one-second-ago one-second-ago)
;; -> 0
``` 

Why not use built-in comparison operators? `<=` `<`

They work with numbers.

`sort` uses `compare`

``` clojure
(def occurrences
  [#inst "2013-04-06T17:40:57.688-00:00"
   #inst "2002-12-25T00:40:57.688-00:00"
   #inst "2025-12-25T11:23:31.123-00:00"])

(sort occurrences)
;; -> (#inst "2002-12-25T00:40:57.688-00:00"
;;     #inst "2013-04-06T17:40:57.688-00:00"
;;     #inst "2025-12-25T11:23:31.123-00:00")
``` 

### Time Interval Between

`interval` from `clj-time`

``` clojure
(require '[clj-time.core :as t])

;; The first step is to capture two dates as an interval
(def since-april-first
  (t/interval (t/date-time 2013 04 01) (t/now)))

;; dt is the interval between April Fools Day, 2013 and today
since-april-first
;; -> #<Interval 2013-04-01T00:00:00.000Z/2013-04-06T20:06:30.507Z>

(t/in-days since-april-first)
;; -> 5

;; Years since the Moon landing
(t/in-years (t/interval (t/date-time 1969 07 20) (t/now)))
;; -> 43

;; Days from Feb. 28 to March 1 in 2012 (a leap year)
(t/in-days (t/interval (t/date-time 2012 02 28)
                       (t/date-time 2012 03 01)))
;; -> 2

;; And in a non-leap year
(t/in-days (t/interval (t/date-time 2013 02 28)
                       (t/date-time 2013 03 01)))
;; -> 1
``` 

### Generate Date and Time Ranges

## Chapter 02

### Create List

``` clojure
'(1 :2 "3")
(list 1 :2 "3")
``` 

Using `list` is better than quoted lists. Because quote prevents evaluation everything inside the list. 

# Article: Clojure Libs and Namespaces: require, use, import, and ns | 8th Light

https://8thlight.com/blog/colin-jones/2010/12/05/clojure-libs-and-namespaces-require-use-import-and-ns.html

## The baseline: require

``` clojure
user=> (clojure.string/split "name,address,city,state,zip,email,phone" #",")
java.lang.ClassNotFoundException: clojure.string (NO_SOURCE_FILE:0)
``` 

Fix: require namespace:

``` clojure
user=> (require 'clojure.string)
``` 

> It’s worth noting here that the name of the lib itself parallels the directory structure, so in order to require clojure.string, you’d need a directory named clojure relative to your classpath, and a file called string.clj within that directory.

Alias:

``` clojure
user=> (require '[clojure.string :as string])
nil
user=> (string/capitalize "foo")
"Foo"
``` 

Quoted vector is shorthand for quoting every individual symbol:

``` clojure
(require ['clojure.string :as 'string])
``` 

Several libraries with same prefix:

``` clojure
(require '(clojure string test))
``` 

> Never fear, the main thing to remember here is that a libspec should be either a quoted symbol or vector. So whenever you want to use something like :as, that thing (the libspec) should be a vector

[Phil Hagelberg's](http://p.hagelb.org/import-indent.html) Advice:

``` clojure
;; brackets imply that all entries should be indented as peers:
  (:import [java.io File]
           [org.apache.maven.artifact.ant
            Authentication DependenciesTask RemoteRepository]))

;; while parens imply that the first element is special and only the
;; ones after it should be indented as peers:
  (:import (java.io File)
           (org.apache.maven.artifact.ant Authentication DependenciesTask
                                          RemoteRepository)))
Generated by Phil Hagelberg using scpaste at Wed Nov 24 20:58:39 2010. PST. (raw)

``` 

Append `:verbose` to see what happens:

``` clojure
 user=> (require '[clojure.string :as string] :verbose)
(clojure.core/load "/clojure/string")
(clojure.core/in-ns 'user)
(clojure.core/alias 'string 'clojure.string)
nil
``` 

## Using other namespaces’ code as though it’s yours: :refer

Namespace information is too verbose. 

Refer to `vars` as if they were ours.

``` clojure
user=> (refer '[clojure.string :refer [split]])
nil
user=> (split "a,b,c" #",")
["a" "b" "c"]
``` 

## Bringing it all together: ns

``` clojure
(ns my-great-project.core
  "This namespace is CRAZY!"
  (:use [clojure.string :only [split join]] :reload)
  (:require clojure.stacktrace
            [clojure.test :as test]
            (clojure template walk) :verbose)
  (:import (java.util Date GregorianCalendar)))
``` 

Note: 

- we use `:` keys
- no symbol quotes

``` clojure
(ns my-great-project.core
  (:require clojure.stacktrace
            clojure.walk))
``` 

# Article: Clojure By Example

http://kimh.github.io/clojure-by-example/

## Control Flow

### If

Note: `[]` is `truthy`

``` clojure
user=> (if (not-empty []) 1 2)
2
user=> (if [] 1 2)
1
``` 

### If-Let

``` clojure
user=> (defn positive-number [numbers]
         (if-let [pos-nums (not-empty (filter pos? numbers))]
           pos-nums
           "no positive numbers"))

user=> (positive-number [-1 -2 1 2])
(1 2)
``` 

### when

There is no `else` branch. Multiple expressions ok.

``` clojure
user=> (when true
         (println "one")
         (println "two"))
one
two
nil
``` 

### condp

``` clojure
user=> (contains? #{1 2} 1)
true
user=> (defn condp-test-2
         [n]
         (condp contains? n
           #{1 2 3} "n is either 1 or 2 or 3"
           "n is not 1 or 2 or 3"))
  #'user/condp-test-2

user=> (println (condp-test-2 2))
n is either 1 or 2 or 3
nil
``` 

### boolean

``` clojure
user> (boolean nil)
false

user> (boolean 0)
true

``` 

## Strings

### str

concatenate:

``` clojure
user=> (str "Good " "morning")
"Good morning"
``` 

instead of string interpolation: use str

``` clojure
user> (let [first "Hirokuni"
            last "Kim"]
            (str "My name is " first " " last))
"My name is Hirokuni Kim"
``` 

### format

``` clojure
user=> (format "My name is %s %s" "Hirokuni" "Kim")
"My name is Hirokuni Kim"
``` 

## Macros

### Defmacro

``` clojure
user=> (defmacro unless [test then]
           "Evaluates then when test evaluates to be falsey"
           (list 'if (list 'not test)
            then))

user=> (unless false (println "false!!"))
false!!
nil
``` 

### macroexpand

``` clojure
user=> (macroexpand '(unless false (println "hi")))
(if (not false) (println "hi"))
``` 

### Syntax-quotes

Fully qualified names

``` clojure
user=> '(dec (inc 1))
(dec (inc 1))

user=> `(dec (inc 1))
(clojure.core/dec (clojure.core/inc 1))
``` 

### Unquotes

``` clojure
user=> '(+ 1 ~(inc 1))
(+ 1 (clojure.core/unquote (inc 1)))

user=> `(+ 1 ~(inc 1))
(clojure.core/+ 1 2)
``` 

### Unquote-Splice

``` clojure
user=> `(+ ~(list 1 2 3))
(clojure.core/+ (1 2 3))

user=> `(+ ~@(list 1 2 3))
(clojure.core/+ 1 2 3)
``` 

## Threading Macros

`->` thread-first. First argument

`->>` thread-last. Last argument

``` clojure
user> (->> ["Japan" "China" "Korea"]
           (map clojure.string/upper-case)
           (map #(str "Hello " %)))
("Hello JAPAN!" "Hello CHINA!" "Hello KOREA!")
``` 

## Atoms

``` clojure
user> (def atom-int (atom 53))
  #'user/atom-int

user> (deref atom-int)
53

user> @atom-int
53

``` 

Set value:

``` clojure
user> (reset! atom-int 35)
35

user> @atom-int
35

user> (reset! atom-int 100)
100

user> @atom-int
100
``` 

Set value using a function:

``` clojure
user> (swap! atom-int
        (fn [current-atom]
            (inc current-atom)))
1

user> (swap! atom-int
        (fn [_]
            "not int"))
"not int"

user> @atom-int
"not int"

``` 

> You can pass a function that takes multiple arguments. The first argument of the function is the current atom.


