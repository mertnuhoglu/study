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

## Exercises: Clojure By Example - kimh id=g_10990

https://github.com/kimh/clojure-by-example

Check `~/codes/clojure/clojure-by-example/source/index.html.md`

Check `/Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/src/ex_kimh.clj`

## Exercises: Sequences from Reference Doc id=g_11270

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

``` clojure
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

``` clojure
;; reducing 
acc, input -> acc
;; transducer
(acc, input -> acc) -> (acc, input -> acc)
``` 

Examples:

``` clojure
(filter odd?)
(map inc)
(take 5)
``` 

Compose with `comp`

``` clojure
(def xf
  (comp
	  (filter odd?)
		(map inc)
		(take 5)))
``` 

### Using Transducers

`transduce` function:

``` clojure
(transduce xform f coll)
``` 

`xform`: transducer function

`f`: reducing function

``` clojure
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

``` clojure
(fn [f1]
  (fn [ret v]
    (f1 ret (f v))))
``` 

It takes a reducing function f1, and returns a new reducing function that calls f1 after applying f to its input.

Mapping is one step of a reduction.

Examples:

Reduce:

``` clojure
;;red is a reducer awaiting a collection
(def red (comp (r/filter even?) (r/map inc)))
(reduce + (red [1 1 1 2]))
;=> 6
``` 

Collection result. `into` is a reducer too:

``` clojure
(into [] (r/filter even? (r/map inc [1 1 1 2])))
;=> [2 2 2]
``` 

## Article: Clojure - Anatomy of a Reducer

Reducing function:

``` clojure
(f acc input) -> acc
``` 

### Transforming Reducing Functions

A function xf that transforms a reducing fn:

``` clojure
(xf reducing) -> reducing
``` 

Core collection functions can be expressed such a transformation. 

For example, core of `map`:

``` clojure
(defn mapping [f]
  (fn [f1]
    (fn [result input]
      (f1 result (f input)))))
``` 

Note, `f` is `inc` and `f1` is `reducing` function in example:

`(mapping inc)` is an `xf`

`(xf +)` is another reducing function `+'`

``` clojure
(reduce + 0 (map inc [1 2 3 4]))
;;becomes
(reduce ((mapping inc) +) 0 [1 2 3 4])
``` 

`((mapping inc) +)` Here we operate on the reducing operation rather than the collection.

### Reducers

`map` should take and return logical collections. 

A collection is something that is reducible.

`reduce` uses a protocol `CollReduce` to ask the collection to reduce itself.

``` clojure
(reduce + 0 (map inc [1 2 3 4]))
;;becomes
(reduce + 0 (reducer [1 2 3 4] (mapping inc)))
``` 

Objective: reducer based code should have same shape as seq based code.

``` clojure
(defn rmap [f coll]
  (reducer coll (mapping f)))

(defn rfilter [pred coll]
  (reducer coll (filtering pred)))

(defn rmapcat [f coll]
  (reducer coll (mapcatting f)))

(reduce + 0 (rmap inc [1 2 3 4]))
;=> 14

(reduce + 0 (rfilter even? [1 2 3 4]))
;=> 6

(reduce + 0 (rmapcat range [1 2 3 4 5]))
;=> 20
``` 

## Article: Transducers are Coming

https://clojure.org/news/2014/08/06/transducers-are-coming

Reducing function transformers: transducers

``` 
;;reducing function signature
whatever, input -> whatever
;;transducer signature
(whatever, input -> whatever) -> (whatever, input -> whatever)
``` 

``` 
;;look Ma, no collection!
(map f)
``` 

returns a 'mapping' transducer. 

You can build a 'stack' of transducers using ordinary function composition (comp):

``` 
(def xform (comp (map inc) (filter even?)))
(->> aseq (map inc) (filter even?))
``` 

`comp` is similar to `->>` but independent of source of inputs

### Transducers in action

``` 
(sequence xform data) ; lazy and only one sequence
(transduce xform + 0 data) ; a loop
(into [] xform data) 
(iteration xform data)
(chan 1 xform) ; through a core.async channel
``` 

## Article: Clojure - Higher Order Functions

https://clojure.org/guides/higher_order_functions

Check `/Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/src/clj_higher_order_funs.clj`

## Docs: Partial

https://clojuredocs.org/clojure.core/partial

## cljs and js comparison

https://gist.github.com/jacekschae/ddffcdcd981ecf80dbe66fbef8b54719

Check `/Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/src/cljs_js_comparison.cljs`

