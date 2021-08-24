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

# Article: Vanilla Clojure vs. Specter vs. transducers id=g11432

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

# Video: Understanding Specter - Clojure's missing piece - rh5J4vacG98 id=g11448

ref: `; Video: Understanding Specter - Clojure's missing piece - rh5J4vacG98  <url:file:///~/projects/study/clj/ex/study_specter/e01/src/specter01.clj#r=g11449>`

Clojure's weaknesses:

Weakness 01. maintaining data structure tight

```clojure
(map inc [1 2 3])
;; => (2 3 4)
```

Returned type is sequence not vector.

opt01: use `mapv`

```clojure
(mapv inc [1 2 3])
;; => [2 3 4]
```

But this doesn't work with sets.

opt02: use specter

```clojure
(transform ALL inc #{1 2 3})
;; => #{2 3 4}
```

Weakness 02. manipulating nested data structures

```clojure
(def data [{:a 1 :b 2} {:c 3}])
```

common way:

```clojure
(defn apply-fn-to-hashmap [f m]
  (into {} (for [[k v] m ] [k (f v)])))
(map #(apply-fn-to-hashmap inc %) data)
(defn inc-even [n]
  (if (even? n) (inc n) n))
(mapv #(apply-fn-to-hashmap inc-even %) data)
```

specter:

```clojure
(transform [ALL MAP-VALS even?] inc data)
```

`get-in` is a navigator example

```clojure
(get-in [:a {:b 1}] [:a :b])
(get-in [:a {:b 1}] [1 :b])
```

explanation: `[ALL MAP-VALS even?]`

```clojure
; START
[{:a 1 :b 2} {:c 3}]

; ALL
{:a 1 :b 2}
{:c 3}

; MAP-VALS
1
2
3

; even?
2

; <navigation complete>

; inc
3

; <reconstruct>

; replay even?
1
3
3

; replay MAP-VALS
{:a 1 :b 3}
{:c 3}

; replay ALL
[{:a 1 :b 3} {:c 3}]
```

use01: query deep data with `select`

# Video: Specter  Powerful and Simple Data Structure Manipulation - Nathan Marz - VTCy_DkAJGk 

Ref: `Video: Specter  Powerful and Simple Data Structure Manipulation - Nathan Marz - VTCy_DkAJGk <url:file:///~/projects/study/clj/ex/study_specter/e01/src/specter01.clj#r=g11450>`

Check `transfer` function in `http://nathanmarz.com/blog/functional-navigational-programming-in-clojurescript-with-sp.html`

```clojure
(defn transfer
  [world from-path to-path amt]
  (let [
        givers (select from-path world)
        receivers (select to-path world)
        total-receive (* amt (count givers))
        total-give (* amt (count receivers))]
    (if (every? #(>= % total-give) givers)
      (->> world
           (transform from-path #(- % total-give))
           (transform to-path #(+ % total-receive))
           )
      (throw (IllegalArgumentException. "Not enough funds!"))
      )))
```

- completely orthogonal to the details of the data structure

