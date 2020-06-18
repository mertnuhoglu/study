--- 
title: "Book Notes: Programming in Clojure - Alex Miller, Stuart Halloway, Aaron Bedra"
date: 2020-03-15T21:03:41+03:00 
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
  ;; user.Person
user=> (def foo (->Person "Aaron" "Bedra"))
  ;; #'user/foo
user=> (:first-name foo)
  ;; "Aaron"
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

#### Special Variables id=g_11409

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
  ;; -> #{"Ali"}
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
  ;; #'user/visitors
``` 

Update reference using `swap!` function

``` clojure
(swap! r update-fn & args)
``` 

`swap!` applies `update-fn` to reference `r` with optional `args`

``` clojure
(swap! visitors conj "Stu")
  ;; #{"Stu"}
``` 

Check the ref with `deref` or `@`

``` clojure
(deref visitors)
  ;; #{"Stu"}
@visitors
  ;; #{"Stu"}
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
  ;; #{"Stu" "Rich"}
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
  ;; Could not locate examples/introduction__init.class, examples/introduction.clj or examples/introduction.cljc on classpath.
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
  ;; (0 1 1 2 3 5 8 13 21 34)
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
  ;; (defn identity
  ;;   "Returns its argument."
  ;;   {:added "1.0"
  ;;    :static true}
  ;;   [x] x)
  ;; nil
``` 

``` clojure
(instance? java.util.Collection [1 2 3])
  ;; true
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
  ;; 1.0001M
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

If several maps have keys in common, create a record: id=g_11411

``` clojure
(defrecord Book [title author])
``` 

Instantiate that record with `->Book` constructor function:

``` clojure
(->Book "title01" "author01")
  ;; #test01.core.Book{:title "title01", :author "author01"}
``` 

### Strings and Characters

`str`: takes any objects, converts them to strings and concatenates them

``` clojure
(str 1 2 nil 3)
  ;; "123"
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
  ;; true
(true? "foo")
  ;; false
``` 

Find other predicate functions:

``` clojure
(find-doc #"\?$")
``` 

`#"\?$"` is a literal regex.

### Functions

``` clojure
(str "hello" " " "world")
  ;; "hello world"
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
  ;; Ali and Ayse went out with 2 chaperones.
``` 

### Anonymous Functions

`fn`: define anonymous function

`defn`: define named function

Ex: filter words longer than 2 letters

opt01: Using `fn`

``` clojure
(filter (fn [w] (> (count w) 2)) (str/split "A fine day" #"\W+"))
  ;; ("fine" "day")
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
  ;; #'test01.core/foo
``` 

This creates a var named `user/foo`

`user/foo` symbol refers to a var that is bound to the value `10`

root binding: The initial value of a var 

Referring to a var directly: `var`

``` clojure
(var a-symbol)
(var foo)
  ;; #'test01.core/foo
``` 

`var`: equivalent reader macro `#'`

``` clojure
 #'foo
  ;; #'test01.core/foo
``` 

Why refer to a var directly?

#### Bindings

``` clojure
(defn triple [number] (* 3 number))
(triple 10)
  ;; 30
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
  ;; Hello, Ali
``` 

opt02: binding only part of the collection

``` clojure
(defn greet-author-2 [{fname :first-name}]
  (println "Hello," fname))
``` 

``` clojure
(greet-author-2 {:last-name "X" :first-name "Ali"})
  ;; Hello, Ali
``` 

`{fname :first-name}` binds `fname` to `:first-name` of the function argument

Ex: bind only the first two vars in a binding:

``` clojure
(let [[x y] [1 2 3]]
  [x y])
  ;; [1 2]
``` 

Ex: skip elements

``` clojure
(let [[_ _ z] [1 2 3]]
  z)
	;; 3
``` 

Ex: bind both a collection and elements

``` clojure
(let [[x y :as coords] [1 2 3 4]]
  (str "x " x ", y " y " total coords " (count coords)))
  ;; "x 1, y 2 total coords 4"
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
  ;; "The quick brown ..."
``` 

#### Namespaces id=g_11412

Root bindings live in a namespace.

`resolve`: returns var that a symbol will resolve to in the current namespace

``` clojure
(resolve 'foo)
  ;; #'test01.core/foo
``` 

`in-ns`: create/switch namespace

``` clojure
(in-ns 'myapp)
``` 

You should `use` `clojure.core` namespace when you move to a new namespace.

``` clojure
(clojure.core/use 'clojure.core)
``` 

Class names outside `java.lang` must be fully qualified:

``` clojure
java.io.File/separator
  ;; "/"
``` 

`import` allows using short names:

``` clojure
(import '(java.io InputStream File))
  ;; java.io.File
``` 

`import` is for java classes.

`require` and `:as` is for clojure functions

``` clojure
(require 'clojure.string)
(clojure.string/split "Ali,Veli,Ayse" #",")
  ;; ["Ali" "Veli" "Ayse"]
(split "Ali,Veli,Ayse" #",")
  ;; Unable to resolve symbol: split in this context
(require '[clojure.string :as str])
(str/split "Ali,Veli,Ayse" #",")
  ;; ["Ali" "Veli" "Ayse"]
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
  ;; {:added "1.0", :ns #object[clojure.lang.Namespace 0x1caefa76 "clojure.core"], :name str, :file "clojure/core.clj", :static true, :column 1, :line 544, :tag java.lang.String, :arglists ([] [x] [x & ys]), :doc "With no args, returns the empty string. With one arg x, returns\n  x.toString().  (str nil) returns the empty string. With more than\n  one arg, returns the concatenation of the str values of the args."}
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
  ;; {:tag java.lang.String, :arglists ([s]), :line 1, :column 1, :file "/private/var/folders/f9/d201s84j0gb95830cjhp09_m0000gq/T/form-init4977993031106662145.clj", :name shout, :ns #object[clojure.lang.Namespace 0x463928b7 "user"]}
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
  ;; -981148039
(. rnd nextInt 10)
  ;; 8
(def p (java.awt.Point. 10 20))
(. p x)
  ;; 10
(. Math PI)
  ;; 3.141592653589793
``` 

`.` syntax used for:

- calling class/instance/static methods/fields

`-`: prefix for fields only

``` clojure
(. p -x)
  ;; 10
``` 

###### dot dot syntax id=g_11413

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
  ;; 10
(.x p)
  ;; 10
(.-x p)
  ;; 10
(System/lineSeparator)
  ;; "\n"
Math/PI
  ;; 3.141592653589793
``` 

##### import

``` clojure
(import (package-symbol & class-name-symbols)*)
``` 

``` clojure
(import '(java.util Random Locale)
  '(java.text MessageFormat))
Random
  ;; java.util.Random
Locale
  ;; java.util.Locale
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
  ;; "yes"
``` 

`else` is third argument

``` clojure
(defn is-small? [number]
  (if (< number 100) "yes" "no"))
(is-small? 500)
  ;; "no"
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
  ;; Saw a big number 500
  ;; "no"
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
  ;; [5 4 3 2 1]
``` 

opt03: use sequence library

``` clojure
(into [] (take 5 (iterate dec 5)))
  ;; [5 4 3 2 1]
(into [] (drop-last (reverse (range 6))))
  ;; [5 4 3 2 1]
(vec (reverse (rest (range 6))))
  ;; [5 4 3 2 1]
``` 

#### Where's My for Loop?

There is no `for` loop.

`StringUtils.indexOfAny` implementation in clojure:

``` clojure
(defn indexed [coll] (map-indexed vector coll))
(indexed "abcde")
  ;; ([0 \a] [1 \b] [2 \c] [3 \d] [4 \e])
(defn index-filter [pred coll]
  (when pred 
    (for [[idx elt] (indexed coll) :when (pred elt)] idx)))
(index-filter #{\a \b} "abcdbbbb")
  ;; (0 1 4 5 6 7)
(defn index-of-any [pred coll]
  (first (index-filter pred coll)))
(index-of-any #{\z \a} "zzabyccdxx")
  ;; 0
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
  ;; 1
(rest '(1 2 3))
  ;; (2 3)
(cons 0 '(1 2 3))
  ;; (0 1 2 3)
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
  ;; true
``` 

maps as seqs:

``` clojure
(first {:fname "Ali" :lname "Ak"})
  ;; [:fname "Ali"]
(rest {:fname "Ali" :lname "Ak"})
  ;; ([:lname "Ak"])
(cons [:mname "Bey"] {:fname "Ali" :lname "Ak"})
  ;; ([:mname "Bey"] [:fname "Ali"] [:lname "Ak"])
``` 

sets as seqs:

``` clojure
(first #{:the :quick :brown})
  ;; :the
(rest #{:the :quick :brown})
  ;; (:quick :brown)
(cons :fox #{:the :quick :brown})
  ;; (:fox :the :quick :brown)
``` 

To get a reliable order in sets use:

``` clojure
(sorted-set & elements)
``` 

``` clojure
(sorted-set :the :quick :brown)
  ;; #{:brown :quick :the}
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
  ;; (:a 1 2 3)
(cons :a '(1 2 3))
  ;; (:a 1 2 3)
(into [1 2 3] [:a :b])
  ;; [1 2 3 :a :b]
``` 

The sequence functions always return a seq. 

``` clojure
(list? (rest [1 2 3]))
  ;; false
(seq? (rest [1 2 3]))
  ;; true
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
  ;; (1 1 1 1 1)
(repeat 3 "x")
  ;; ("x" "x" "x")
``` 

`iterate`: continues forever.

``` clojure
(iterate f x)
``` 

``` clojure
(take 3 (iterate inc 1))
  ;; (1 2 3)
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
  ;; (1 1 1)
``` 

`cycle`: cycles a collection infinitely

``` clojure
(cycle coll)
``` 

``` clojure
(take 5 (cycle (range 2)))
  ;; (0 1 0 1 0)
``` 

`interleave`: takes multiple collections. interleaves values from each collection until one of the collections is exhausted.

``` clojure
(interleave & colls)
``` 

``` clojure
(interleave whole-numbers ["a" "b" "c"])
  ;; (1 "a" 2 "b" 3 "c")
``` 

`interpose`: like `interleave` but elements separated by a separator

``` clojure
(interpose separator coll)
``` 

``` clojure
(interpose "," ["a" "b" "c"])
  ;; ("a" "," "b" "," "c")
``` 

use case: to produce output strings with `apply str`

``` clojure
(apply str (interpose "," ["a" "b" "c"]))
  ;; "a,b,c"
``` 

This idiom is done by: `clojure.string/join`

``` clojure
(join separator sequence)
``` 

``` clojure
(require '[clojure.string :refer [join]])
(join \, ["a" "b" "c"])
  ;; "a,b,c"
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
  ;; #{1 3 2}
``` 

``` clojure
(hash-set 1 2 3)
  ;; #{1 3 2}
``` 

`vec`: similar to `vector`. It takes a collection

``` clojure
(vec (range 3))
  ;; [0 1 2]
``` 

#### Filtering Sequences

`filter`

``` clojure
(filter pred coll)
``` 

``` clojure
(take 3 (filter even? whole-numbers))
  ;; (2 4 6)
``` 

`take-while`

``` clojure
(take-while pred coll)
``` 

``` clojure
 #{\a\e\i\o\u}
  ;; #{\a \e \i \o \u}
(def vowel? #{\a\e\i\o\u})
(def consonant? (complement vowel?))
(take-while consonant? "ali-veli")
  ;; ()
(take-while consonant? "grass")
  ;; (\g \r)
``` 

`vowel?`: sets act as functions that look up a value in the set. returns the value if found. so `#{\a\e\i\o\u}`: a function that checks to see whether its argument is a vowel

`complement`: reverses the behavior. 

``` clojure
(#{\a\e} "ali")
  ;; nil
(vowel? "ali")
  ;; nil
(#{\a\e} "a")
  ;; nil
(vowel? "a")
  ;; nil
(#{\a} \a)
  ;; \a
(vowel? \a)
  ;; \a
``` 

`drop-while`: opposite of `take-while`

``` clojure
(drop-while pred coll)
``` 

``` clojure
(drop-while consonant? "ali-veli")
  ;; (\a \l \i \- \v \e \l \i)
(drop-while consonant? "grass")
  ;; (\a \s \s)
``` 

`split-at`: split a collection into two

``` clojure
(split-at index coll)
(split-with pred coll)
``` 

``` clojure
(split-at 3 (range 4))
  ;; [(0 1 2) (3)]
(split-with #(<= % 2) (range 4))
  ;; [(0 1 2) (3)]
``` 

#### Sequence Predicates

`every?`

``` clojure
(every? pred coll)
(every? odd? [1 3 5])
  ;; true
``` 

`some`: returns actual value of the first match

``` clojure
(some pred coll)
(some even? [1 2 3])
  ;; true
(some even? [1 3])
  ;; nil
(some identity [nil false 1 nil 2])
  ;; 1
``` 

Check if a sequence contains the value 3:

``` clojure
(some #{3} (range 5))
  ;; 3
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
  ;; ("<p>ali</p>" "<p>veli</p>")
``` 

if map takes multiple collections, then f must be a function of multiple arguments too.

``` clojure
(map #(format "<%s>%s</%s>" %1 %2 %1) ["h1" "h2"] ["ali" "veli"])
  ;; ("<h1>ali</h1>" "<h2>veli</h2>")
``` 

`reduce`

``` clojure
(reduce f coll)
``` 

`reduce` applies `f` on the first two elements in `coll` and then to the result and the third element.

``` clojure
(reduce + (range 1 5))
  ;; 10
(reduce * (range 1 5))
  ;; 24
``` 

`sort`

``` clojure
(sort comp? coll)
(sort-by a-fn comp? coll)
``` 

`sort-by` sorts by the result of calling `a-fn` on each element

``` clojure
(sort [5 1 3])
  ;; (1 3 5)
(sort-by #(.toString %) [15 1 3])
  ;; (1 15 3)
``` 

`comp` specifies comparison function

``` clojure
(sort > [3 15])
  ;; (15 3)
(sort-by :grade > [{:grade 20} {:grade 15}])
  ;; ({:grade 20} {:grade 15})
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
  ;; ("<p>ali</p>" "<p>veli</p>")
``` 

Comprehensions use `:when` clause to emulate `filter`

``` clojure
(take 5 (for [n whole-numbers :when (even? n)] n))
  ;; (2 4 6 8 10)
``` 

`:while` clause continues only while its predicate holds true:

``` clojure
(for [n whole-numbers :while (even? n)] n)
  ;; ()
``` 

Multiple bindings:

``` clojure
(for [file "ABCDEFGH"
      rank (range 1 9)]
  (format "%c%d" file rank))
  ;; ("A1" "A2" "A3" ...
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
  ;; 1
  ;; 2
  ;; (1 2)
(def x (for [i (range 1 3)] i))
(doall x)
  ;; (1 2)
``` 

`dorun`

``` clojure
(def x (for [i (range 1 3)] (do (println i) i)))
(dorun x)
  ;; 1
  ;; 2
  ;; nil
``` 

`dorun` works like `doall` but doesn't keep past elements in memory.

### Clojure Makes Java Seq-able

Collections, regex, file system traversal, xml, relational database results.

#### Java Collections

``` clojure
(first (.getBytes "hello"))
  ;; 104
(rest (.getBytes "hello"))
  ;; (101 108 108 111)
``` 

Hastables and Maps:

``` clojure
(first (System/getProperties))
``` 

Strings:

``` clojure
(first "Hello")
  ;; \H
(rest "Hello")
  ;; (\e \l \l \o)
``` 

To convert it back to string: use `(apply str seq)`

``` clojure
(apply str (reverse "hello"))
  ;; "olleh"
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
  ;; ("ali" "veli")
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
  ;; ("project.clj" "LICENSE" "test" ...)
``` 

#### Seq-ing a Stream

``` clojure
(require '[clojure.java.io :refer [reader]])
(take 2 (line-seq (reader "src/examples/utils.clj")))
  ;; (";---" "; Excerpted from \"Programming Clojure, Third Edition\",")
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
  ;; 1991
``` 

### Calling Structure-Specific Functions

#### Functions on Lists

`peek` get first element of a list

`pop` get the remainder

``` clojure
(peek '(1 2 3))
  ;; 1
(pop '(1 2 3))
  ;; (2 3)
``` 

`get` value at index

``` clojure
(get [:a :b :c] 1)
  ;; :b
``` 

Vectors are functions as well. They work like `get`

``` clojure
([1 2 3] 1)
  ;; 2
``` 

`assoc` associates a new value with an index

``` clojure
(assoc [0 1 2] 1 :two)
  ;; [0 :two 2]
``` 

`subvec` returns a subvector

``` clojure
(subvec avec start end?)
``` 

``` clojure
(subvec [1 2 3 4] 1 4)
  ;; [2 3 4]
``` 

You can simulate it with `drop` and `take`

``` clojure
(take 3 (drop 1 [1 2 3 4]))
  ;; (2 3 4)
``` 

Vector specific functions are faster.

#### Functions on Maps

``` clojure
(keys map)
(vals map)
``` 

``` clojure
(keys {:a 1, :b 2})
  ;; (:a :b)
(vals {:a 1, :b 2})
  ;; (1 2)
``` 

`get` return value for a key

``` clojure
(get map key default?)
``` 

``` clojure
(get {:a 1, :b 2} :a)
  ;; 1
``` 

You can use map directly too:

``` clojure
({:a 1, :b 2} :a)
  ;; 1
``` 

Keywords are also functions

``` clojure
(:a {:a 1, :b 2})
  ;; 1
``` 

`contains?`

``` clojure
(contains? map key)
``` 

``` clojure
(def score {:stu nil :joey 100})
(:stu score)
  ;; nil
(contains? score :stu)
  ;; true
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
  ;; {:name "Agnus Dei", :artist "Krzysztof Penderecki", :album "Polish Requiem", :genre "Classical"}
(assoc song :kind "MPEG")
(dissoc song :genre)
(select-keys song [:name])
  ;; {:name "Agnus Dei"}
(merge song {:size 800 :time 50})
  ;; {:name "Agnus Dei", :artist "Krzysztof Penderecki", :album "Polish Requiem", :genre "Classical", :size 800, :time 50}
``` 

`merge-with` like merge but when two maps have the same key, you can specify how to combine the values

``` clojure
(merge-with 
 concat 
 {:flintstone, ["Fred"], :rubble ["Barney"]}
 {:flintstone, ["Wilma"], :rubble ["Betty"]}
 {:flintstone, ["Pebbles"], :rubble ["Bam-Bam"]})
  ;; {:flintstone ("Fred" "Wilma" "Pebbles"), :rubble ("Barney" "Betty" "Bam-Bam")}
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
  ;; #{"d" "clojure" "pop" "java" "chai" "c"}
(difference languages beverages)
  ;; #{"d" "clojure" "c"}
(select #(= 1 (count %)) languages)
  ;; #{"d" "c"}
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
  ;; #{{:composer "Giuseppe Verdi", :title "Requiem"} {:composer "W. A. Mozart", :title "Requiem"} {:composer "J. S. Bach", :title "The Art of the Fugue"} {:composer "J. S. Bach", :title "Musical Offering"}}
``` 

`select` similar to WHERE in SQL

``` clojure
(select pred relation)
``` 

``` clojure
(select #(= (:name %) "Requiem") compositions)
  ;; #{{:name "Requiem", :composer "Giuseppe Verdi"} {:name "Requiem", :composer "W. A. Mozart"}}
``` 

`project` similar to SELECT in SQL

``` clojure
(project relation keys)
``` 

``` clojure
(project compositions [:name])
  ;; #{{:name "The Art of the Fugue"} {:name "Musical Offering"} {:name "Requiem"}}
``` 

List comprehension for cross product in SQL

``` clojure
(for [m compositions c composers] (concat m c))
  ;; (([:name "Musical Offering"] [:composer "J. S. Bach"] [:composer "Giuseppe Verdi"] [:country "Italy"]) ([:name "Musical Offering"] [:composer "J. S. Bach"] [:composer "J. S. Bach"] [:country "Germany"]) ...
``` 

`join`: Subset of full cross product based on shared keys

``` clojure
(join relation-1 relation-2 keymap?)
``` 

``` clojure
(join compositions composers)
  ;; #{{:composer "W. A. Mozart", :country "Austria", :name "Requiem"} {:composer "J. S. Bach", :country "Germany", :name "Musical Offering"} {:composer "Giuseppe Verdi", :country "Italy", :name "Requiem"} {:composer "J. S. Bach", :country "Germany", :name "The Art of the Fugue"}}
``` 

``` clojure
(join composers nations {:country :nation})
  ;; #{{:composer "W. A. Mozart", :country "Austria", :nation "Austria", :language "German"} 
  ;; {:composer "J. S. Bach", :country "Germany", :nation "Germany", :language "German"} 
  ;; {:composer "Giuseppe Verdi", :country "Italy", :nation "Italy", :language "Italian"}}
``` 

``` clojure
(project
  (join
	  (select #(= (:name %) "Requiem") compositions)
		composers)
	[:country])
  ;; #{{:country "Italy"} {:country "Austria"}}
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
  ;; (0 1 1N 2N 3N 5N 8N 13N 21N 34N)
(rem (nth (lazy-seq-fibo) 1000000) 1000)
``` 

opt: use sequence library to make lazy sequences

``` clojure
(take 5 (iterate (fn [[a b]] [b (+ a b)]) [0 1]))
  ;; ([0 1] [1 1] [1 2] [2 3] [3 5])
(defn fibo []
 (map first (iterate (fn [[a b]] [b (+ a b)]) [0N 1N])))
(take 5 (fibo))
  ;; (0N 1N 1N 2N 3N)
``` 

#### Coming to Realization

`*print-length*` Configure how many items printer will print

``` clojure
(set! *print-length* 10)
(take 50 (fibo))
  ;; (0N 1N 1N 2N 3N 5N 8N 13N 21N 34N ...)
(fibo)
  ;; (0N 1N 1N 2N 3N 5N 8N 13N 21N 34N ...)
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
  ;; 2
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
  ;; ((:h :h) (:h :h) (:h :t) (:t :h))
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
  ;; ((:h :h) (:h :h) (:h :t) (:t :h))
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
  ;; 2
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
  ;; (true false true false)
``` 

``` clojure
(bit-and 3 1)
  ;; 1
(bit-and 3 2)
  ;; 2
(zero? 1)
  ;; false
(zero? 2)
  ;; false
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
  ;; (0 1 0 1 0)
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
  ;; ()
(trampoline + 1 2)
  ;; 3
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
  ;; true
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
  ;; ((((bottom))))
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
  ;; true
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
  ;; [:42 42]
(s/conform ::odd-or-42 19)
  ;; [:odd 19]
``` 

`s/explain` why an invalid value didn't match

``` clojure
(s/explain ::odd-or-42 0)
  ;; 0 - failed: odd? at: [:odd] spec: :test01.core/odd-int
  ;; 0 - failed: #{42} at: [:42] spec: :test01.core/odd-or-42
``` 

#### Collection Specs

`s/coll-of`

``` clojure
(s/def ::names (s/coll-of string?))
(s/valid? ::names ["Alex" "Stu"])
  ;; true
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
  ;; true
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
  ;; {:s "abc", :i 100}
``` 

`s/alt` alternatives within sequential structure

``` clojure
(s/def ::alt-example (s/alt :i int? :k keyword?))
(s/valid? ::alt-example [100])
  ;; true
(s/valid? ::alt-example [:foo])
  ;; true
``` 

#### Repetition Operators

`s/?` 0 or 1

`s/*` 0 or more

`s/+` 1 or more

``` clojure
(s/def ::oe (s/cat :odds (s/+ odd?) :even (s/? even?)))
(s/conform ::oe [1 3 5 100])
  ;; {:odds [1 3 5], :even 100}
``` 

#### Variable Argument Lists

Zero or more arguments:

``` clojure
(s/def ::println-args (s/* any?))
``` 

Some fixed arguments and a variable argument at the end

``` clojure
(clojure.set/intersection #{1 2} #{2 3} #{2 5})
  ;; #{2}
``` 

``` clojure
(s/def ::intersection-args 
  (s/cat :s1 set?
	       :sets (s/* set?)))
(s/conform ::intersection-args '[#{1 2} #{2 3} #{2 5}])
  ;; {:s1 #{1 2}, :sets [#{3 2} #{2 5}]}
``` 

``` clojure
(s/def ::meta map?)
(s/def ::validator ifn?)
(s/def ::atom-args 
  (s/cat :x any? :options (s/keys* :opt-un [::meta ::validator])))
(s/conform ::atom-args [100 :meta {:foo 1} :validator int?])
  ;; {:x 100, :options {:meta {:foo 1}, :validator #object[clojure.core$int_QMARK_...
``` 

#### Multi-arity Argument Lists

### Specifying Functions

``` clojure
(s/def ::rand-args (s/cat :n (s/? number?)))
(s/def ::rand-ret double?)
``` 



