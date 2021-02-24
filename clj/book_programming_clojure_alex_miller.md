--- 
title: "Book: Programming Clojure - Alex Miller, Stuart Halloway"
date: 2021-02-15T21:53:41+03:00 
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

ref: `~/codes/clj/programming-clojure/README.markdown`

## ch01: Getting Started

ref: `~/codes/clj/programming-clojure/src/examples/introduction.clj`

### Simplicity and Power

Ex01: Check `; START:blank <url:file:///~/codes/clj/programming-clojure/src/examples/introduction.clj#r=g_11992>`

- no variables
- no mutable state
- no branches
- uses higher-order function instead of for loop
- no need for explicit checking special cases like null and empty string

Ex02: Class in java vs `defrecord`

```clj
(defrecord Person [first-name last-name])
(def foo (->Person "Ali" "Veli"))
(:first-name foo)
```

- immutable
- hashCode and equals correcly and automatically implemented

### Lisp Reloaded

Lisps have a tiny language core. Almost no syntax.

Downside:

- too many parenthses
- lists as the core datatype

Clojure makes Lisp more approachable:

- Generalizes list into an abstraction called sequence
- JVM
- Easy syntax quoting
- Literal syntax for other data structures: vector, map, set
- Optional commas
- No unnecessary parentheses in idiomatic clojure

### Clojure is a Functional Language

- First-class objects
- Immutable data
- Pure functions, no side-effects

- No loops, variables in data transformation
- Thread safe
- Parallizable
- Generic

### Concurrent Programming

STM: protects shared state with transactions similar to databases

```clj
(def accounts (ref #{})) 
(defrecord Account [id balance])
(dosync
	(alter accounts conj (->Account "CLJ" 1000.00)))
```

### Conventions for Parameter Names

		| parameter | usage      |
		| a         | java array |
		| expr      | expression |
		| r         | ref        |
		| agt       | agent      |
		| f         | function   |
		| v         | vector     |
		| coll      | collection |
		| idx       | index      |
		| val       | value      |

## ch02: Exploring Clojure

### Functions

#### Anonymous Functions

Check `; START:indexable-word <url:/Users/mertnuhoglu/codes/clj/programming-clojure/src/examples/exploring.clj#tn=; START:indexable-word>`

Closures: check: `; START:make-greeter <url:/Users/mertnuhoglu/codes/clj/programming-clojure/src/examples/exploring.clj#tn=; START:make-greeter>`

### Vars, Bindings, and Namespaces

Namespace: a collection of symbols that refer to vars

Each var is bound to a value

#### Vars

Root binding: initial value of a var

```clj
(def foo 10)
```

Returning var, not var's value: `(var a-symbol)` or reader macro: `#'a-symbol`

#### Bindings

There are other kinds of bindings in addition to vars. 

Ex: In a function call, argument values bind to parameter names.

Parameter bindings have a lexical scope. That is they are visible only inside the function body.

`let` creates also a set of lexical bindings:

```clj
(let [bindings*] exprs*)
```

#### Destructuring

Ex: A variable is bound to an entire collection. How to access only part of the collection?

Check: `; START:ellipsize <url:/Users/mertnuhoglu/codes/clj/programming-clojure/src/examples/exploring.clj#tn=; START:ellipsize>`

```clj
  (let [[w1 w2 w3] (str/split words #"\s+")]
```

#### Namespaces

Root bindings live in a namespace

`resolve`: returns the var a symbol resolves into

```clj
(resolve 'foo)
```

You need to import `clojure.core` when you move to a new namespace with `in-ns`:

```clj
(clojure.core/use 'clojure.core)
```

### Metadata

Metadata: Data that i orthogonal to the logical value of an object.

All vars have a metadata map: documentation, type, source etc.

```clj
(meta #'str)
;; {:added "1.0",
;;  :ns #object[clojure.lang.Namespace 0x7f1855b7 "clojure.core"],
;;  :name str,
;;  :file "clojure/core.clj",
;;  :static true,
;;  :column 1,
;;  :line 546,
;;  :tag java.lang.String,
;;  :arglists ([] [x] [x & ys]),
;;  :doc
;;  "With no args, returns the empty string. With one arg x, returns\n  x.toString().  (str nil) returns the empty string. With more than\n  one arg, returns the concatenation of the str values of the args."}
```

  | key       | used for                         |
  | :added    | version this function as added   |
  | :tag      | expected argument or return type |
  | :arglists | parameter info                   |
  | :macro    | true for macros                  |

Add your own metadata: `^metadata form`

```clj
(defn ^{:tag String} shout [s] ...
```

Short form for `:tag` metadata: `^Classname` = `^{:tag Classname}`

You can place metadata last as well:

```clj
(defn shout 
  ([s] (clojure.string/upper-case s))
  {:tag String})
```

Note: args are shifted and `^` is removed

### Calling Java

Check: `(new java.util.Random) <url:/Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/src/java_interoperability.clj#tn=(new java.util.Random)>`

`(. class-or-instance member-symbol & args)`

`(. class-or-instance (member-symbol & args))`

### Comments

`comment` macro: returns nil. But it is still read by Clojure reader. Thus it must be valid code.

`#_` reader macro. Ignore it.

### Flow Control

`if` `do` `loop/recur` are primitive controls.

Check: `; START:if <url:/Users/mertnuhoglu/codes/clj/programming-clojure/src/examples/exploring.clj#tn=; START:if>`

Check: `; From: Programming Clojure, Alex Miller <url:/Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/src/flow_control.clj#tn=; From: Programming Clojure, Alex Miller>`

#### Recur with loop/recur

`loop` works like `let`. It creates `bindings` and then evaluates `exprs`.

`loop` sets a recursion point. It can be repeated by `recur`: `(recur exprs*)`

opt01: Use `loop` as recursion block 

Check: `; loop recur 01 <url:/Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/src/flow_control.clj#tn=; loop recur 01>`

opt02: Use function as recursion block

Check: `; function recur 01 <url:/Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/src/flow_control.clj#tn=; function recur 01>`

Common recursions are provided by sequence library:

##### Where's For Loop?

There is no `for` loop and no direct mutable variables.

`StringUtils.indexOfAny` implementation in clojure:

Check: `; StringUtils.indexOfAny implementation in clojure: <url:file:///~/projects/study/clj/ex/study_clojure/ex06/src/flow_control.clj#r=g_11994>`

FP vs imperative: 

- No for loop
- Branch: 1 vs 4
- Less LOC
- Exits: 1 vs 3
- No variables
- Higher reusability

## ch03: Unifying Data with Sequences

All data structures accessed through a single abstraction: the sequence (or seq)

A seq is a logical list.

Collections that can be viewed as seqs are called seqable.

- all clojure collections
- all java collections
- java arrays and strings
- regex matches
- directory structures
- xml trees
- I/O streams

ref: `~/projects/study/clj/ex/study_clojure/ex06/src/sequence.clj`

`clojure.lang.ISeq` interface: `/Users/mertnuhoglu/codes/clj/clojure/src/jvm/clojure/lang/ISeq.java`

- `(first aseq)`
- `(rest aseq)`
- `(cons elem aseq)`

`(seq coll)`: return a seq on seq-able collection

`(next aseq)` = `(seq (rest aseq))`

## ch04: Functional Programming

### FP Concepts

#### Pure Functions

#### Persistent Data Structures

#### Laziness and Recursion

Sequences are generally lazy.

Lazy techniques imply pure functions. 

#### Referential Transparency

Calls to such functions can be replaced without affecting the behavior of the program.

Benefits:

- Memoization: automatic caching
- Automatic parallelization

Pure functions are referentially transparent by definiton.

### Benefits of FP

- Easier to write and read. Because relevant input and output data is right in front of you. No need to worry about global state.

- Improves reuse. Because you can understand easily. And code can compose.

Composability. OOP suggested encapsulation to create composable code. But it creates a firewall. It doesn't solve the side effects. Impure functions violate encapsulation.

Pure functions are truly encapsulated.

#### Guidelines for Use

01: Avoid direct recursion

02: Use `recur` for small, fixed sequences

03: For large sequences, be lazy. Do not recur. 

04: Don't realize a lazy sequence more than you need.

05: Know the sequence library.

06: Subdivide

### How to Be Lazy

Recursive definitions consist of two parts:

01. Basis
02. Induction: to produce additional members

Recursion types:

- Simple recursion
- Tail recursion
- Lazy sequence

Being lazy is often the right approach.

Ex: Fibonacci numbers

Basis: `F_0`: zero. `F_1`: one.

Induction: For `n > 1`, `F_n` equals `F_{n-1} + F_{n-2}`

#### Tail Recursion

Check: `; 02 Tail Recursion <url:file:///~/projects/study/clj/ex/study_clojure/ex06/src/fp.clj#r=g_12018>`

#### Explicit Self-Recursion

Check: `; 03 Explicit self-recursion with recur  <url:file:///~/projects/study/clj/ex/study_clojure/ex06/src/fp.clj#r=g_12019>`

Difference: 

```clj
(fib next (+ current next) (dec n))))] 
;; ->
(recur next (+ current next) (dec n))))] 
```

#### Lazy Seq Iteration

Check: `; 05 Lazy Seq Recursion Using iterate <url:file:///~/projects/study/clj/ex/study_clojure/ex06/src/fp.clj#r=g_12020>`

Use `iterate` instead of writing lazy-seq recursion by yourself.

```clj
(take 5 (iterate (fn [[a b]] [b (+ a b)]) [0 1]))
;;=> ([0 1] [1 1] [1 2] [2 3] [3 5])
```

#### Lazier than Lazy

Check: `; 06 Lazier than Lazy <url:file:///~/projects/study/clj/ex/study_clojure/ex06/src/fp.clj#r=g_12022>`

Check: `; 07 Transforming the Input Sequence <url:file:///~/projects/study/clj/ex/study_clojure/ex06/src/fp.clj#r=g_12021>`

Use `partition` instead of our own `by-pairs`: ``; 07.02 Use partition instead of by-pairs <url:file:///~/projects/study/clj/ex/study_clojure/ex06/src/fp.clj#r=g_12023>``

## ch05: Specifications

### Validating Data 

Check: `~/projects/study/clj/ex/study_clojure/ex06/src/specs_data.clj`

Predicates: `; p02: Predicates <url:file:///~/projects/study/clj/ex/study_clojure/ex06/src/specs_data.clj#r=g_12024>`

```clj
(s/def :my.app/company-name string?)
(s/valid? :my.app/company-name "Acme")
(s/valid? :my.app/company-name 100)
```

Enumerated values

```clj
(s/def :marble/color #{:red :green :blue})
(s/valid? :marble/color :red)
(s/valid? :marble/color :pink)
```

Range Specs:

```clj
(s/def :bowling/ranged-roll (s/int-in 0 11))
(s/valid? :bowling/ranged-roll 10)
```

Handling nil:

```clj
(s/def ::my.app/company-name-2 (s/nilable string?))
(s/valid? ::my.app/company-name-2 nil)
```

Logical specs:

Composite specs from other specs using `s/and` or `s/or`

```clj
(s/def ::odd-int (s/and int? odd?))
(s/valid? ::odd-int 5)
(s/valid? ::odd-int 10)
```

```clj
(s/def ::odd-or-42 (s/or :odd ::odd-int :42 #{42}))
```

`s/conform`: returns the value annotated with information about optional choices. conformed value. 

```clj
(s/conform ::odd-or-42 42)
;-> [:42 42]
(s/conform ::odd-or-42 19)
;-> [:odd 19]
```

`s/explain`: why is a value invalid?

```clj
(s/explain ::odd-or-42 0)
; (out) 0 - failed: odd? at: [:odd] spec: :specs/odd-int
; (out) 0 - failed: #{42} at: [:42] spec: :specs/odd-or-42
```

Collection Specs: `; p07: Collection specs <url:file:///~/projects/study/clj/ex/study_clojure/ex06/src/specs_data.clj#r=g_12025>`

`s/coll-of`: describes lists, vectors, sets, and seqs 

`s/map-of`

```clj
(s/def ::names (s/coll-of string?))
(s/valid? ::names ["Alex" "Stu"])
```

Keyword arguments of `coll-of`:

`:kind`: predicate. ex: `set?`

`:into`: one of `[] () #{}`. values collected into.

`:count`: exact count of collection

`:min-count` and `:max-count`

`:distinct`: unique elements

Collection Sampling:

`s/every` and `s/every-kv`

### Validating Functions

Three specs:

- args spec: describing arguments of the function
- ret spec: describing return value
- fn spec: describing arguments to the return value

It has capabilities:

- instrumentation
- generative testing

Sequences with structure: `; p01: Sequences with structure <url:file:///~/projects/study/clj/ex/study_clojure/ex06/src/specs_functions.clj#r=g_12026>`

`s/cat`

`s/alt`

## ch06: State and Concurrency

Ref: `~/projects/study/clj/ex/study_clojure/ex06/src/state.clj`

4 reference types:

- ref: coordinated, synchronous
- atom: uncoordinated, synchronous
- agent: asynchronus 
- var: thread-local

 Split your models into two layers:

- Functional model that has no mutable state.
- Reference model for mutable state

### Refs and Software Transactional Memory

You must be explicit when you want mutable data by creating a mutable reference to an immutable object.

`(ref initial-state)`

`(deref reference)`

`(ref-set reference new-value)`

`(dosync & exprs)`

- Updates are atomic
- Updates are consistent
- Updates are isolated

Refs: for coordinated access. synchronous

Atoms: for single piece of data. synchronous

Agents: for coordinated access. synchronous
