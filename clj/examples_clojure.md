---
title: "Examples clojure"
date: 2019-12-03T20:41:04+03:00 
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


# Index

## repl id=g_11312

``` clojure
(str *1 " result of most recent evaluation")
(pst) ; stack trace
(load-file "file.clj")
``` 

ref: `Helper functions <url:file:///~/projects/study/clj/book_learn_clojure.md#r=g_11313>`

``` clojure
(doc +)
(apropos "+")
(find-doc something)
(find-doc #"\?$")
(dir clojure.repl)
(source something)
``` 

## def var <url:/Users/mertnuhoglu/projects/study/clj/clojure.md#tn=def var>

``` clojure
(def a-symbol 7)
``` 

## println

``` clojure
print
prn 
pr
``` 

## Functions <url:/Users/mertnuhoglu/projects/study/clj/clojure.md#tp=Clojure - Learn Clojure - Functions>

``` clojure
(defn a-function [params] (str "body"))
``` 

Multi-arity functions 

``` clojure
(defn f 
	([] ..)
	([param] ..))
``` 

variadic functions

``` clojure
(defn f [p0 & rest] ..)
``` 

anonymous functions

``` clojure
(fn [p] (..))
	#(+ 6 %)
	#(+ 6 %1 %2)
``` 

defn vs fn

``` clojure
(defn f [] (..))
(def f (fn [] (..)))
``` 

single pass compiler: 

``` clojure
(declare down)
``` 

Applying Functions <url:/Users/mertnuhoglu/projects/study/clj/clojure.md#tp=Applying Functions>

``` clojure
(plotxy shape (first coords) (second coords)))
(apply plotxy shape coords))
``` 

let

``` clojure
(let [sym value] (body))
(let [[_ _ z] [1 2 3]]
  z)
(let [[x y :as coords] [1 2 3 4]]
``` 

Java Interop <url:/Users/mertnuhoglu/projects/study/clj/clojure.md#tp=Java Interop>

``` clojure
(Constructor. "arg")
(.method instance args)
(.-field instance)
(Math/sqrt 25)
``` 

Regex

``` clojure
; #"\?$"
(re-seq #"\w+" "ali veli")
``` 

Vectors

``` clojure
([1 2 3] 1)
(get vec 0)
(count vec)
(vector 1 2 3)
(conj vec 1 2 3)
(subvec [1 2 3 4] 1 4)
(take 3 (drop 1 [1 2 3 4]))
``` 

Lists

``` clojure
'(1 2 3)
(quote (1 2 3))
(first xs)
(rest xs)
(conj xs :elem)
(peek xs)
(pop xs)
(concat [1 2] '(3 4))
``` 

Sets

``` clojure
(conj xs :elem)
(disj xs :elem)
(contains? xs :elem)
(into xs ys)
(union languages beverages)
(difference languages beverages)
(select #(= 1 (count %)) languages)
``` 

Maps

``` clojure
(assoc m :key 0)
(dissoc m :key)
(get m :key)
(m :key)
(:key m)
(:key m default)
(contains m :key)
(keys m)
(vals m)
(select-keys song [:name])
(merge song {:size 800 :time 50})
(merge-with 
 concat 
 {:flintstone, ["Fred"], :rubble ["Barney"]}
 {:flintstone, ["Wilma"], :rubble ["Betty"]}
 {:flintstone, ["Pebbles"], :rubble ["Bam-Bam"]})
``` 

nested maps

``` clojure
(get-in m [:parent :child])
``` 

relational algebra

``` clojure
(rename compositions {:name :title})
(select #(= (:name %) "Requiem") compositions)
(project compositions [:name])
(for [m compositions c composers] (concat m c))
(join compositions composers)
(join composers nations {:country :nation})
``` 

flow control

``` clojure
(if (true? x) exp-then exp-else)
(do side-eff1 side-eff2 return-exp)
(when (true? x) exp-then)
(cond (cond1) exp1 (cond2) exp2)
(case x 5 exp1 10 exp2)
(dotimes [i 5] side-eff)
(for [i 5] exp)
``` 

lein

``` clojure
lein repl
lein new app project01
lein run
``` 

higher-order functions

``` clojure
map
filter
reduce
(->> ..)
(-> ..)
``` 

destructuring

``` clojure
(let [[smaller bigger] (split-with #(< % 5) (range 10))])
(defn print-user [[name address phone]])
(let [{f :foo} {:foo "val"}] (println f))
(let [{[a b] :items} {:items [1 2]}] ..)
(defn login [{:keys [user]} ..])
(login {:user "bob"})
``` 

## namespaces

``` clojure
(ns colors)
(ns myns (:use colors))
(f ..)
(ns myns (:use [colors :only [f]]))
(f ..)
(ns myns (:require colors))
(colors/f ...)
(ns myns (:require [colors :as c]))
(c/f ..)
``` 

resolve

``` clojure
(resolve 'foo)	; returns var that foo resolves 
(in-ns 'myapp)	; create/switch namespace
(clojure.core/use 'clojure.core)		; import it after switching
``` 

require clojure import java

``` clojure
(require 'a-namespace)
(a-namespace/f ..)
(require '[clojure.string :as str])
(str/split "Ali,Veli,Ayse" #",")
(import '(java.io InputStream File))
``` 

``` clojure
(ns examples.exploring
  (:require [clojure.string :as str])
	(:import (java.io File)))
``` 

``` clojure
java.io.File/separator		; fully qualified class name
``` 

edn

``` clojure
\c \newline
"string in double quotes with \n \t"
true false
nil
namespace/foo
100.09M
(a b c)
  #inst "1985-04-12T23:20:50.52Z"
  #uuid
``` 

record

``` clojure
(defrecord Person [a b])
(def f (->Person "ali" "veli"))
(:a f)
``` 

for comprehension 

``` clojure
(for [c compositions :when (= (:name c) "Requiem"] (:composer c))])
``` 

## shared state

atom

``` clojure
(atom initial-state)
(def visitors (atom #{}))
(swap! visitors conj "Ali")
(deref visitors)
(@visitors)
``` 

misc

``` clojure
(every? #(Character/isWhitespace %) str))
(instance? java.util.Collection [1 2 3])
(str 1 2 nil 3)
(true? expr)
(false? expr)
(nil? expr)
(zero? expr)
(string? "hello")
(keyword? :hello)
(symbol? 'hello)
``` 

meta

``` clojure
(meta #'str)
``` 

## loop recur

``` clojure
(loop [result [] x 5]
	(if (zero? x)
		result
		(recur (conj result x) (dec x))))
(loop [i 10]
	(if (zero? i)
		result
		(recur (dec i))))
``` 

## sequence library

``` clojure
(into [] (take 5 (iterate dec 5)))
  ##> [5 4 3 2 1]
(into [] (drop-last (reverse (range 6))))
  ##> [5 4 3 2 1]
(vec (reverse (rest (range 6))))
  ##> [5 4 3 2 1]
``` 

``` clojure
(first aseq)
(rest aseq)
(cons elem aseq)
(seq coll)
(next aseq)
(cons [:mname "Bey"] {:fname "Ali" :lname "Ak"})
(conj coll element & elements)
(into to-coll from-coll)
``` 

``` clojure
(range 10)
(range 10 20)
(repeat 3 "x")
(iterate f x)
(take 3 (iterate inc 1))
(take 5 (cycle (range 2)))
(interleave whole-numbers ["a" "b" "c"])
(interpose "," ["a" "b" "c"])
(apply str (interpose "," ["a" "b" "c"]))
(require '[clojure.string :refer [join]])
(join \, ["a" "b" "c"])
``` 

``` clojure
take-while
drop-while
split-at
split-with
every?
some
identity
not-every?
not-any?
``` 

``` clojure
map
reduce
sort
sort-by
``` 

for :when :while

``` clojure
(for [word ["ali" "veli"]]
  (format "<p>%s</p>" word))
(take 5 (for [n whole-numbers :when (even? n)] n))
(for [n whole-numbers :while (even? n)] n)
``` 

doall dorun

``` clojure
(def x (for [i (range 1 3)] (do (println i) i)))
(doall x)
(dorun x)
``` 

seqing file system

``` clojure
(import 'java.io.File)
(seq (.listFiles (File. ".")))
(map #(.getName %) (.listFiles (File. ".")))
``` 

seqing a stream

``` clojure
(require '[clojure.java.io :refer [reader]])
(take 2 (line-seq (reader "src/examples/utils.clj")))
(with-open [rdr (reader "src/examples/utils.clj")]
	(count (line-seq rdr)))
``` 

functional transformations

``` clojure
(partition 2 1 [:h :h :h :t :h])
(comp f & fs)
(partial f & partial-args)
``` 

## Specs

``` clojure
(require '[clojure.spec.alpha :as s])
(s/def name spec)
``` 

## environment

``` clojure
*clojure-version*
*compile-path*
*ns*
``` 

project configuration

``` clojure
(slurp "project.clj")
(read-string (slurp "project.clj"))
(nth (read-string (slurp "project.clj")) 1)
``` 

