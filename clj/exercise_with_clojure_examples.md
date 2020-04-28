--- 
title: "Exercises with Clojure Examples"
date: 2020-04-28T18:19:19+03:00 
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


# Exercises with Clojure Examples 

New project in Cursive:

Check `/Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/deps.edn`

## Exercises: Clojure By Example - kimh

https://github.com/kimh/clojure-by-example

Check `~/codes/clojure/clojure-by-example/source/index.html.md`

Check `/Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/src/clojure_by_example_kimh.clj`

## Exercises: Sequences from Reference Doc

https://clojure.org/reference/sequences

Check `~/projects/study/clj/ex/study_clojure/ex06/src/clojure_ref_sequences.clj`

Seq: a logical list

ISeq interface is implemented by many data structures. 

It provides access to its elements as sequences.

seq vs iterator: 

- persistent and immutable
- not stateful cursors

seq vs foreach:

- functions can consume and produce seqs
- thread safe
- can share structure

Seq interface:

``` 
(first coll)
(rest coll)
(cons item seq)
``` 

### Seq library

Most seq functions create transducers if the input collection is omitted.

seq -> seq

shorter seq: distinct filter remove for keep keep-indexed

## Exercises: Transducers from Reference Doc

Ref: `~/projects/study/clj/ex/study_clojure/ex06/src/clojure_ref_transducers.clj`

Composable transformations.

They specify only the transformation. They are decoupled from input or output. 

Reducing function: a function you pass to reduce

It takes an accumulated result and a new input. 

It returns a new accumulated result.

``` 
;; reducing 
acc, input -> acc
;; transducer
(acc, input -> acc) -> (acc, input -> acc)
``` 

Examples:

``` 
(filter odd?)
(map inc)
(take 5)
``` 

Compose with `comp`

``` 
(def xf
  (comp
	  (filter odd?)
		(map inc)
		(take 5)))
``` 

### Using Transducers

`transduce` function:

``` 
(transduce xform f coll)
``` 

`xform`: transducer function

`f`: reducing function

``` 
(def xf (comp (filter odd?) (map inc)))
(transduce xf + (range 5))
;; => 6
(transduce xf + 100 (range 5))
;; => 106
``` 

### Creating Transducers

## Article: Clojure - Reducers - A Library and Model for Collection Processing

https://clojure.org/news/2012/05/08/reducers

Based upon `reduce` and `fold`

### Basics

`map`: takes a function and a collection.

Core of map looks:

``` 
(fn [f1]
  (fn [ret v]
    (f1 ret (f v))))
``` 

It takes a reducing function f1, and returns a new reducing function that calls f1 after applying f to its input.

Mapping is one step of a reduction.

Examples:

Reduce:

``` 
;;red is a reducer awaiting a collection
(def red (comp (r/filter even?) (r/map inc)))
(reduce + (red [1 1 1 2]))
;=> 6
``` 

Collection result. `into` is a reducer too:

``` 
(into [] (r/filter even? (r/map inc [1 1 1 2])))
;=> [2 2 2]
``` 

## Article: Clojure - Anatomy of a Reducer

Reducing function:

``` 
(f acc input) -> acc
``` 

A function xf that transforms a reducing fn:

``` 
(xf reducing) -> reducing
``` 

## Article: Transducers are Coming

https://blog.cognitect.com/blog/2014/8/6/transducers-are-coming

Reducing function transformers: transducers

clojure.com/blog/2012/05/15/anatomy-of-reducer.html

