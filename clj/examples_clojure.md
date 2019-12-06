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

## Helper functions <url:/Users/mertnuhoglu/projects/study/clj/study_clojure.md#tn=Helper functions>

``` bash
(doc +)
(apropos "+")
(find-doc something)
(find-doc #"\?$")
(dir clojure.repl)
(source something)
``` 

## repl

``` bash
(str *1 " result of most recent evaluation")
(pst) ; stack trace
(load-file "file.clj")
``` 

## def var <url:/Users/mertnuhoglu/projects/study/clj/study_clojure.md#tn=def var>

``` bash
(def a-symbol 7)
``` 

## println

``` bash
print
prn 
pr
``` 

## Functions <url:/Users/mertnuhoglu/projects/study/clj/study_clojure.md#tp=Clojure - Learn Clojure - Functions>

``` bash
(defn a-function [params] (str "body"))
``` 

Multi-arity functions 

``` bash
(defn f 
	([] ..)
	([param] ..))
``` 

variadic functions

``` bash
(defn f [p0 & rest] ..)
``` 

anonymous functions

``` bash
(fn [p] (..))
	#(+ 6 %)
	#(+ 6 %1 %2)
``` 

defn vs fn

``` bash
(defn f [] (..))
(def f (fn [] (..)))
``` 

single pass compiler: 

``` bash
(declare down)
``` 

Applying Functions <url:/Users/mertnuhoglu/projects/study/clj/study_clojure.md#tp=Applying Functions>

``` bash
(plotxy shape (first coords) (second coords)))
(apply plotxy shape coords))
``` 

let

``` bash
(let [sym value] (body))
(let [[_ _ z] [1 2 3]]
  z)
(let [[x y :as coords] [1 2 3 4]]
``` 

Java Interop <url:/Users/mertnuhoglu/projects/study/clj/study_clojure.md#tp=Java Interop>

``` bash
(Constructor. "arg")
(.method instance args)
(.-field instance)
(Math/sqrt 25)
``` 

Regex

``` bash
; #"\?$"
(re-seq #"\w+" "ali veli")
``` 

Vectors

``` bash
([1 2 3] 1)
(get vec 0)
(count vec)
(vector 1 2 3)
(conj vec 1 2 3)
(subvec [1 2 3 4] 1 4)
(take 3 (drop 1 [1 2 3 4]))
``` 

Lists

``` bash
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

``` bash
(conj xs :elem)
(disj xs :elem)
(contains? xs :elem)
(into xs ys)
(union languages beverages)
(difference languages beverages)
(select #(= 1 (count %)) languages)
``` 

Maps

``` bash
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

``` bash
(get-in m [:parent :child])
``` 

relational algebra

``` bash
(rename compositions {:name :title})
(select #(= (:name %) "Requiem") compositions)
(project compositions [:name])
(for [m compositions c composers] (concat m c))
(join compositions composers)
(join composers nations {:country :nation})
``` 

flow control

``` bash
(if (true? x) exp-then exp-else)
(do side-eff1 side-eff2 return-exp)
(when (true? x) exp-then)
(cond (cond1) exp1 (cond2) exp2)
(case x 5 exp1 10 exp2)
(dotimes [i 5] side-eff)
(for [i 5] exp)
``` 

lein

``` bash
lein repl
lein new app project01
lein run
``` 

higher-order functions

``` bash
map
filter
reduce
(->> ..)
(-> ..)
``` 

destructuring

``` bash
(let [[smaller bigger] (split-with #(< % 5) (range 10))])
(defn print-user [[name address phone]])
(let [{f :foo} {:foo "val"}] (println f))
(let [{[a b] :items} {:items [1 2]}] ..)
(defn login [{:keys [user]} ..])
(login {:user "bob"})
``` 

## namespaces

``` bash
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

``` bash
(resolve 'foo)	; returns var that foo resolves 
(in-ns 'myapp)	; create/switch namespace
(clojure.core/use 'clojure.core)		; import it after switching
``` 

require clojure import java

``` bash
(require 'a-namespace)
(a-namespace/f ..)
(require '[clojure.string :as str])
(str/split "Ali,Veli,Ayse" #",")
(import '(java.io InputStream File))
``` 

``` bash
(ns examples.exploring
  (:require [clojure.string :as str])
	(:import (java.io File)))
``` 

``` bash
java.io.File/separator		; fully qualified class name
``` 

edn

``` bash
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

``` bash
(defrecord Person [a b])
(def f (->Person "ali" "veli"))
(:a f)
``` 

for comprehension 

``` bash
(for [c compositions :when (= (:name c) "Requiem"] (:composer c))])
``` 

## shared state

atom

``` bash
(atom initial-state)
(def visitors (atom #{}))
(swap! visitors conj "Ali")
(deref visitors)
(@visitors)
``` 

misc

``` bash
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

``` bash
(meta #'str)
``` 

## loop recur

``` bash
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

``` bash
(into [] (take 5 (iterate dec 5)))
  ##> [5 4 3 2 1]
(into [] (drop-last (reverse (range 6))))
  ##> [5 4 3 2 1]
(vec (reverse (rest (range 6))))
  ##> [5 4 3 2 1]
``` 

``` bash
(first aseq)
(rest aseq)
(cons elem aseq)
(seq coll)
(next aseq)
(cons [:mname "Bey"] {:fname "Ali" :lname "Ak"})
(conj coll element & elements)
(into to-coll from-coll)
``` 

``` bash
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

``` bash
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

``` bash
map
reduce
sort
sort-by
``` 

for :when :while

``` bash
(for [word ["ali" "veli"]]
  (format "<p>%s</p>" word))
(take 5 (for [n whole-numbers :when (even? n)] n))
(for [n whole-numbers :while (even? n)] n)
``` 

doall dorun

``` bash
(def x (for [i (range 1 3)] (do (println i) i)))
(doall x)
(dorun x)
``` 

seqing file system

``` bash
(import 'java.io.File)
(seq (.listFiles (File. ".")))
(map #(.getName %) (.listFiles (File. ".")))
``` 

seqing a stream

``` bash
(require '[clojure.java.io :refer [reader]])
(take 2 (line-seq (reader "src/examples/utils.clj")))
(with-open [rdr (reader "src/examples/utils.clj")]
	(count (line-seq rdr)))
``` 

functional transformations

``` bash
(partition 2 1 [:h :h :h :t :h])
(comp f & fs)
(partial f & partial-args)
``` 

## Specs

``` bash
(require '[clojure.spec.alpha :as s])
(s/def name spec)
``` 

## environment

``` bash
*clojure-version*
*compile-path*
*ns*
``` 

project configuration

``` bash
(slurp "project.clj")
(read-string (slurp "project.clj"))
(nth (read-string (slurp "project.clj")) 1)
``` 

