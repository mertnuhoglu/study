--- 
title: "Book Notes: Clojure By Example"
date: 2020-03-15T21:11:37+03:00 
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

# Book: Clojure By Example

http://kimh.github.io/clojure-by-example/

## Control Flow

### If

Note: `[]` is `truthy`

``` clojure
user=> (if (not-empty []) 1 2)
2
user=> (if [] 1 2)
1
``` 

### If-Let

``` clojure
user=> (defn positive-number [numbers]
         (if-let [pos-nums (not-empty (filter pos? numbers))]
           pos-nums
           "no positive numbers"))

user=> (positive-number [-1 -2 1 2])
(1 2)
``` 

### when

There is no `else` branch. Multiple expressions ok.

``` clojure
user=> (when true
         (println "one")
         (println "two"))
one
two
nil
``` 

### condp

``` clojure
user=> (contains? #{1 2} 1)
true
user=> (defn condp-test-2
         [n]
         (condp contains? n
           #{1 2 3} "n is either 1 or 2 or 3"
           "n is not 1 or 2 or 3"))
  #'user/condp-test-2

user=> (println (condp-test-2 2))
n is either 1 or 2 or 3
nil
``` 

### boolean

``` clojure
user> (boolean nil)
false

user> (boolean 0)
true

``` 

## Strings

### str

concatenate:

``` clojure
user=> (str "Good " "morning")
"Good morning"
``` 

instead of string interpolation: use str

``` clojure
user> (let [first "Hirokuni"
            last "Kim"]
            (str "My name is " first " " last))
"My name is Hirokuni Kim"
``` 

### format

``` clojure
user=> (format "My name is %s %s" "Hirokuni" "Kim")
"My name is Hirokuni Kim"
``` 

## Macros

### Defmacro

``` clojure
user=> (defmacro unless [test then]
           "Evaluates then when test evaluates to be falsey"
           (list 'if (list 'not test)
            then))

user=> (unless false (println "false!!"))
false!!
nil
``` 

### macroexpand

``` clojure
user=> (macroexpand '(unless false (println "hi")))
(if (not false) (println "hi"))
``` 

### Syntax-quotes

Fully qualified names

``` clojure
user=> '(dec (inc 1))
(dec (inc 1))

user=> `(dec (inc 1))
(clojure.core/dec (clojure.core/inc 1))
``` 

### Unquotes

``` clojure
user=> '(+ 1 ~(inc 1))
(+ 1 (clojure.core/unquote (inc 1)))

user=> `(+ 1 ~(inc 1))
(clojure.core/+ 1 2)
``` 

### Unquote-Splice

``` clojure
user=> `(+ ~(list 1 2 3))
(clojure.core/+ (1 2 3))

user=> `(+ ~@(list 1 2 3))
(clojure.core/+ 1 2 3)
``` 

## Threading Macros

`->` thread-first. First argument

`->>` thread-last. Last argument

``` clojure
user> (->> ["Japan" "China" "Korea"]
           (map clojure.string/upper-case)
           (map #(str "Hello " %)))
("Hello JAPAN!" "Hello CHINA!" "Hello KOREA!")
``` 

## Atoms

``` clojure
user> (def atom-int (atom 53))
  #'user/atom-int

user> (deref atom-int)
53

user> @atom-int
53

``` 

Set value:

``` clojure
user> (reset! atom-int 35)
35

user> @atom-int
35

user> (reset! atom-int 100)
100

user> @atom-int
100
``` 

Set value using a function:

``` clojure
user> (swap! atom-int
        (fn [current-atom]
            (inc current-atom)))
1

user> (swap! atom-int
        (fn [_]
            "not int"))
"not int"

user> @atom-int
"not int"

``` 

> You can pass a function that takes multiple arguments. The first argument of the function is the current atom.



