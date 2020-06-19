--- 
title: "Study specter"
date: 2020-06-18T12:22:50+03:00 
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

# Article: Functional-navigational programming in Clojure(Script) with Specter 

[Functional-navigational programming in Clojure(Script) with Specter](http://nathanmarz.com/blog/functional-navigational-programming-in-clojurescript-with-sp.html)

# Article: Vanilla Clojure vs. Specter vs. transducers id=g_11432

[Vanilla Clojure vs. Specter vs. transducers](https://gist.github.com/borkdude/5f9a4ae710217e893a9462ff90b6cac3)

ex: qualify each keyword with the namespace (vanilla)

``` 
defn ns-qualify-keys [m]
  (into {}
    (map (fn [[k v]] [(keyword (str *ns*) (name k)) v]))
    m
    ))
``` 

two issues:

1. slow. idiomatic way should be the most performant way.

2. has unintended effect. it returns an unsorted map if you input a sorted map.

basic data structure tranformations missing from clojure:

- transform each key of a map
- transform each value of a map
- transfuorm each value of a sequence
- append to a sequence
- prepend to a sequence
- add anywhere in a sequence
- remove anywhere from a sequence
- update name of a keyword or symbol

## Navigation

`setval` is like `assoc-in` on steroids.

input: path, value, input data

path: for navigating into the data

``` 
(setval [MAP-KEYS NAMESPACE] (str *ns*) any-map)
``` 

`transform` is more general than `setval`

``` 
(transform [ALL :a even?] inc [{:a 1} {:a 2 :b 1} {:a 4}])
;; => [{:a 1} {:a 3, :b 1} {:a 5}]
``` 

specter has two components: (missing pieces in clojure)

- navigator abstraction `defnav`: composes navigators
- set of navigators

uses:

- transforming subvalues in a data structure (as above)
- substructure navigators (most important)

manipulating data via the composition of navigators should be a fundamental skill for all functional programmers. 

### substructure navigators

ex: qualify each keyword with the namespace (specter)

`srange`: navigate to a subsequence of a list/vector

``` 
(setval [:a (srange 2 4)] [] {:a [1 2 3 4 5]})
;; => {:a [1 2 5]}
 
(setval (srange 2 2) [:A :A] '(1 2 3 4 5))
;; => (1 2 :A :A 3 4 5)
 
(transform [:a (srange 1 5)] reverse {:a [1 2 3 4 5 6]})
;; => {:a [1 5 4 3 2 6]}
``` 

`filterer`: navigate using a predicate

``` 
(transform (filterer even?) reverse [1 2 3 4 5 6 7 8 9])
;; => [1 8 3 6 5 4 7 2 9]
``` 

increment the last odd number in a sequence:

``` 
(transform [(filterer odd?) LAST] inc [1 2 3 4 5 6])
;; => [1 2 3 4 6 6]
``` 

## Querying

``` 
(select [ALL :a even?] [{:a 1} {:a 2 :b 1} {:a 4}])
;; => [2 4]
``` 

``` 
(select-any [:a :b :c] {:a {:b {:c 1}}})
;; => 1
``` 

## Navigation is generic and extensible

### ex

``` 
{:players [{:name "Hopper", :funds 100, :games-played 10}
           {:name "Eleven", :funds 6941, :games-played 7}
           {:name "Will", :funds -12, :games-played -8}]
 :bank {:funds 9850}}
(defn player-by-name [name]
  (path :players
        ALL
        #(= (:name %) name)))
(transform [(player-by-name "Hopper") :funds] dec data)
(select-any [(player-by-name "Will") :games-played] data)
``` 

## Recursive navigation

``` 
(def tree [1 [2 [[3]] 4] [[5] 6] [7] 8 [[9]]])
(def TREE-VALUES
  (recursive-path [] p
    (if-path vector?
      [ALL p]
      STAY)))
;; Increment even leaves
(transform [TREE-VALUES even?] inc tree)
;; => [1 [3 [[3]] 5] [[5] 7] [7] 9 [[9]]]
 
;; Get odd leaves
(select [TREE-VALUES odd?] tree)
;; => [1 3 5 7 9]
 
;; Reverse order of even leaves (order based on depth-first search)
(transform (subselect TREE-VALUES even?) reverse tree)
;; => [1 [8 [[3]] 6] [[5] 4] [7] 2 [[9]]]
``` 

# Article: README specter

[redplanetlabs/specter: Clojure(Script)'s missing piece](https://github.com/redplanetlabs/specter)

Check `~/projects/study/clj/ex/study_specter/e01/src/specter01.clj`

## ex: Increment every even number nested within map of vector of maps

``` 
(def data {:a [{:aa 1 :bb 2}
               {:cc 3}]
           :b [{:dd 4}]})

;; Manual Clojure
(defn map-vals [m afn]
  (->> m (map (fn [[k v]] [k (afn v)])) (into (empty m))))

(map-vals data
  (fn [v]
    (mapv
      (fn [m]
        (map-vals
          m
          (fn [v] (if (even? v) (inc v) v))))
      v)))

;; Specter
(transform [MAP-VALS ALL MAP-VALS even?] inc data)
``` 

## ex: Append a sequence of elements to a nested vector

``` 
(def data {:a [1 2 3]})

;; Manual Clojure
(update data :a (fn [v] (into (if v v []) [4 5])))

;; Specter
(setval [:a END] [4 5] data)
``` 

## ex: Increment the last odd number in a sequence

``` 
(def data [1 2 3 4 5 6 7 8])

;; Manual Clojure
(let [idx (reduce-kv (fn [res i v] (if (odd? v) i res)) nil data)]
  (if idx (update data idx inc) data))

;; Specter
(transform [(filterer odd?) LAST] inc data)
``` 


