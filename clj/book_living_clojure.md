--- 
title: "Book Notes: Living Clojure - Carin Meier"
date: 2020-03-15T21:01:47+03:00 
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


