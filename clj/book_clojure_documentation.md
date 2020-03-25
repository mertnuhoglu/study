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
	& rest parameters
``` 

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


