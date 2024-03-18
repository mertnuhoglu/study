--- 
title: "Book Notes: Clojure Documentation"
date: 2020-03-15T21:09:31+03:00 
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

# Clojure Documentation | Clojure Docs

http://clojure-doc.org/articles/content.html#essentials

## Getting Started

### Trying out the REPL

``` clojure
lein repl
``` 

- ### Your first project id=g11399
  id:: 2827cc78-5a42-4243-be75-2f5e826624c4

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

- ### Interactive Development id=g11400
  id:: 0a7cc1d7-93da-4150-987e-5eccec3e3b7c

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

- ## Weird Characters 
  id:: 373ea7e0-e7c8-4d75-a1ec-c664e8d7b5b6
  src:: ; Weird Characters || ((f75edafd-c289-4f43-b376-46d57a23bae1))
	id=g11401

[Weird Characters](https://clojure.org/guides/weird_characters)

|-----------------|-------------------|------------------|
| Purpose         | Java              | Clj              |
|-----------------|-------------------|------------------|
| Instantiation   | new Widget("foo") | (Widget. "foo")  |
| Instance method | rnd.nextInt()     | (.nextInt rnd)   |
| Instance field  | object.field      | (.-field object) |

```clj
; ## ..
```

- `#` - Dispatch character: interpret the next character using a read table
		short for gensym (at the end of a symbol): x#
		`#{set}` - Set
		`#_` - Discard
		`#"re"` - Regular Expression
		`#(…)` - Anonymous function
		`#'` - Var quote
		`##` - Symbolic values
			`##Inf` `##NaN` `##-Inf` 
		`#inst` `#uuid` `#js` etc. - tagged literals
- Data structures: 
  `( … )` - List
  `[ … ]` - Vector
  `{ … }` - Map
  %, %n, %& - Anonymous function arguments
  @ - Deref
  ^ (and #^) - Metadata
	`#^` deprecated in favor of `^`
  `'` - Quote: read but not evaluate
  `;` - Comment
  `:` - Keyword
  `::` - Auto-resolve keyword in current ns
  `#:` and `#::` - Namespace Map Syntax
  / - Namespace separator
  \ - Character literal
  $ - Inner class reference
		`BaseXClient$EventNotifier`

```clj
  ->, ->>, some->, cond->, as-> etc. - Threading macros
  ` - Syntax quote
		backquote grave
		symbols used within a syntax quote are fully resolved with respect to the current namespace
  ~ - Unquote
		tilde
		(def five 5)
		`five ; => user/five
		`~five ; => 5
		`[inc ~(+ 1 five)] ; => [clojure.core/inc 6]
  ~@ - Unquote splicing
		(def a (list 3 4))
		`(1 ~a) ; => (1 (3 4))
		`(1 ~@a) ; => (1 3 4)
  <symbol># - Gensym
  #? - Reader conditional
     #?(:clj  (Clojure expression)
			 :cljs (ClojureScript expression))
  #?@ - Splicing Reader conditional
  *var-name* - "Earmuffs" = special vars ~ dynamic vars
		convention, not a rule
	  *out* *in*
  >!!, <!!, >!, and <! - core.async channel macros
  <symbol>? - Predicate Suffix
		convention
		empty? list?
  <symbol>! - Unsafe Operations
		convention: mutate state
		set! swap!
  _ - Unused argument
		(fn [_ _ arg3] ...)
  , - Whitespace character
  #= Reader eval
		deprecated
``` 

- # edn-format/edn: Extensible Data Notation id=g11402
  id:: fe32328f-c7be-4195-a4a0-45ce03088cb6

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


