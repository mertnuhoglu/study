--- 
title: "Study clojure"
date: 2019-11-01T14:35:49+03:00 
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

# Quickstart Clojure id=g_11627

Exercises: Clojure By Example - kimh <url:file:///~/projects/study/clj/exercise_with_clojure_examples.md#r=g_10990>

Exercises: Sequences from Reference Doc <url:file:///~/projects/study/clj/exercise_with_clojure_examples.md#r=g_11270>


			~/projects/study/clj/ex/study_clojure/ex06/src/ex_kimh.clj
			~/projects/study/clj/study_fulcro_tutorial.md
			~/projects/study/code/study_intellij_ideavim.md
			~/projects/study/code/study_intellij.md

`~/projects/study/clj/anki_clojure.md`

Repl:

```bash
~/projects/study/clj/study_deps_cli.md
~/projects/study/clj/study_reveal_repl.md
~/projects/study/clj/practicalli_deps_edn.md
~/projects/study/clj/study_babashka_interpreter.md
~/projects/study/clj/study_portal.md
~/projects/study/clj/study_jet.md
~/projects/study/clj/study_klipse_repl.md
~/projects/study/clj/study_lumo_repl.md
~/projects/study/clj/study_closh_shell.md
~/projects/study/clj/study_deep_diff.md
~/projects/study/clj/study_nrepl.md
```

`Quickstart deps.edn <url:file:///~/projects/study/clj/study_deps_cli.md#r=g_11724>`

# Articles

		`~/projects/study/clj/study_deps_cli.md`

# Questions

## reduce-kv id=g_11444

[reduce-kv - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/reduce-kv)

Reduces an associative collection. f should be a function of 3
arguments. Returns the result of applying f to init, the first key
and the first value in coll, then applying f to that result and the
2nd key and value, etc

``` 
(reduce-kv f init coll)
``` 

``` 
;; Swap keys and values in a map
(reduce-kv #(assoc %1 %3 %2) {} {:a 1 :b 2 :c 3})
;; => {1 :a, 2 :b, 3 :c}
``` 

ex: all vals are incremented by 1.

``` 
(def vector-of-maps [{:a 1 :b 2} {:a 3 :b 4}])
(defn update-map [m f] 
  (reduce-kv (fn [m k v] 
    (assoc m k (f v))) {} m))

(map #(update-map % inc) vector-of-maps)
``` 

## keep  id=g_11435

[keep - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/keep)

``` 
;; comparisons among keep, filter, map and for.

(keep #(if (odd? %) %) (range 10))
;;=> (1 3 5 7 9)

(map #(if (odd? %) %) (range 10))
;;=> (nil 1 nil 3 nil 5 nil 7 nil 9)

(for [ x (range 10) :when (odd? x)] x)
;;=> (1 3 5 7 9)

(filter odd? (range 10))
;;=> (1 3 5 7 9)
``` 

## complement id=g_11433

[complement - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/complement)

``` 
(def not-empty? (complement empty?))
;; #'user/not-empty?

(not-empty? [])    ;;=> false
(not-empty? [1 2]) ;;=> true
``` 

## map doc id=g_11276

https://clojuredocs.org/clojure.core/map

``` clojure
(map f)
(map f coll)
(map f c1 c2)
(map f c1 c2 c3)
(map f c1 c2 c3 & colls)
``` 

``` clojure
(map inc [1 2 3 4 5])
;;=> (2 3 4 5 6)


;; map can be used with multiple collections. Collections will be consumed
;; and passed to the mapping function in parallel:
(map + [1 2 3] [4 5 6])
;;=> (5 7 9)


;; When map is passed more than one collection, the mapping function will
;; be applied until one of the collections runs out:
(map + [1 2 3] (iterate inc 1))
;;=> (2 4 6)
``` 

``` clojure
;; A useful idiom to pull "columns" out of a collection of collections. 
;; Note, it is equivalent to:
;; user=> (map vector [:a :b :c] [:d :e :f] [:g :h :i])

(apply map vector [[:a :b :c]
                   [:d :e :f]
                   [:g :h :i]])

;;=> ([:a :d :g] [:b :e :h] [:c :f :i])
``` 

## partial doc id=g_11277

https://clojuredocs.org/clojure.core/partial

``` clojure
(partial f)
(partial f arg1)
(partial f arg1 arg2)
(partial f arg1 arg2 arg3)
(partial f arg1 arg2 arg3 & more)
``` 

``` clojure
user=> (def hundred-times (partial * 100))
  #'user/hundred-times

user=> (hundred-times 5)
500

user=> (hundred-times 4 5 6)
12000

``` 


## apply doc id=g_11278

https://clojuredocs.org/clojure.core/apply

``` clojure
(apply f args)
(apply f x args)
(apply f x y args)
(apply f x y z args)
(apply f a b c d & args)
``` 

Applies fn f to the argument list formed by prepending intervening arguments to args.

``` clojure
;; If you were to try
(max [1 2 3])
;;=> [1 2 3]

;; You would get '[1 2 3]' for the result. In this case, 'max' has received one
;; vector argument, and the largest of its arguments is that single vector.

;; If you would like to find the largest item **within** the vector, you would need
;; to use `apply`

(apply max [1 2 3])
;;=> 3

;; which is the same as 
(max 1 2 3)
;;=> 3
``` 

## comp doc id=g_11279

https://clojuredocs.org/clojure.core/comp

``` clojure
(comp)
(comp f)
(comp f g)
(comp f g & fs)
``` 

Takes a set of functions and returns a fn that is the composition of those fns. (right-to-left)

``` clojure
; Get 2nd to last element from a list
( (comp second reverse) '("a" 2 7 "b"))   
;;=> 7

(filter (comp not zero?) [0 1 0 2 0 3 0 4])
;;=> (1 2 3 4)

((comp str +) 8 8 8)   
;;=> "24"

(def negative-quotient (comp - /))
;; #'user/negative-quotient

(negative-quotient 8 3)  ;;=> -8/3
``` 

## juxt doc id=g_11280

https://clojuredocs.org/clojure.core/juxt

``` clojure
(juxt f)
(juxt f g)
(juxt f g h)
(juxt f g h & fs)
``` 

Takes a set of functions and returns a fn that is the juxtaposition
of those fns.

((juxt a b c) x) => [(a x) (b x) (c x)]

``` clojure
((juxt :a :b) {:a 1 :b 2 :c 3 :d 4})
;;=> [1 2]
``` 

``` clojure
;; "Explode" a value.

((juxt identity name) :keyword)
;;=> [:keyword "keyword"]

(juxt identity name)
...is the same as:
(fn [x] [(identity x) (name x)])

;; eg. to create a map:

(into {} (map (juxt identity name) [:a :b :c :d]))
;;=> {:a "a" :b "b" :c "c" :d "d"}
``` 

## identity doc id=g_11281

``` clojure
user=> (identity 4)
4
``` 

## name doc id=g_11282

https://clojuredocs.org/clojure.core/name

Returns the name String of a string, symbol or keyword.

``` clojure
;; the name of the keyword is without the ':'
;; "str" will retain the ':'.
(name :x)
;;=> "x"

(name "x")
;;=> "x"

;; returns the symbol name as a string without the namespace.
(name 'x)
;;=> "x"

(name 'user/x)
;;=> "x"
``` 

## pr-str doc id=g_11283

https://clojuredocs.org/clojure.core/pr-str

``` clojure
(pr-str & xs)
``` 

pr to a string, returning it

``` clojure
user=> x
[1 2 3 4 5]


;; Turn that data into a string...
(pr-str x)
;; => "[1 2 3 4 5]"


;; ...and turn that string back into data!
(read-string (pr-str x))
;; => [1 2 3 4 5]
``` 

## repeat doc id=g_11284

https://clojuredocs.org/clojure.core/repeat

``` clojure
(repeat x)
(repeat n x)
``` 

Returns a lazy (infinite!, or length n if supplied) sequence of xs.

``` clojure
(take 5 (repeat "x"))
;; => ("x" "x" "x" "x" "x")

;; which is the same as:
(repeat 5 "x")
;; => ("x" "x" "x" "x" "x")

``` 

## into doc id=g_11285

https://clojuredocs.org/clojure.core/into

``` clojure
; Items are conj'ed one at a time, which puts them at the head of 
; the destination list
(into () '(1 2 3))
;; => (3 2 1)

; This does not happen for a vector, however, due to the behavior of conj:
(into [1 2 3] '(4 5 6))
;; => [1 2 3 4 5 6]
``` 

``` clojure
(into (sorted-map) [ [:a 1] [:c 3] [:b 2] ] )
;; => {:a 1, :b 2, :c 3}
; When maps are the input source, they convert into an unordered sequence 
; of key-value pairs, encoded as 2-vectors
(into [] {1 2, 3 4})
;; => [[1 2] [3 4]]
``` 

## remove doc id=g_11286

https://clojuredocs.org/clojure.core/remove

``` clojure
;; compare to filter

(remove even? (range 10))
;;=> (1 3 5 7 9)

; When coll is a map, pred is called with key/value pairs.
(remove #(> (second %) 100)
       {:a 1
        :c 101 })
;;=> ([:a 1] )
``` 

### Logs

Nasıl çalışıyor bu:

``` clojure
(remove #(> (second %) 100)
       {:a 1
        :b 2
        :c 101
        :d 102
        :e -1})
;;=> ([:a 1] [:b 2] [:e -1])
``` 

`second` fonksiyonuna argüman olarak {:a 1} {:b 2} gibi key-value pairları paslanıyor olmalı. Fakat bunları tek başına gönderdiğimde `nil` alıyorum:

``` clojure
(second {:a 1})
;; => nil
``` 

`map` ile çağıralım

``` clojure
(map #(> (second %) 100) {:a 1 :c 101})
;; => (false true)
``` 

Evet, bu durumda, tek tek gönderiliyor. O zaman, item değil de vector olarak mı gönderiliyor?

``` clojure
(second [:a 1])
;; => 1
``` 

Evet, map'in öğeleri `{:a 1}` gibi map değil, `[:a 1]` gibi vector.

## update-in id=g_11287

https://clojuredocs.org/clojure.core/update-in

``` clojure
(def p {:name "James" :age 26})
;;=> #'user/p

(update-in p [:age] inc)
;;=> {:name "James", :age 27}
``` 

## assoc-in id=g_11288

https://clojuredocs.org/clojure.core/assoc-in

``` clojure
(assoc-in {:person {:name "Mike"}} [:person :name] "Violet")
; => {:person {:name "Violet"}}

(assoc-in {:person {:name "Mike"}} [:person] "Violet")
; => {:person "Violet"}
``` 

## swap! amap assoc akey nasıl çalışıyor? id=g_11289

``` clojure
(def m (atom {:a 1}))
@m
;; => {:a 1}
(swap! m assoc :b 2)
;; => {:a 1, :b 2}
``` 

Acaba sıralama değişince ne oluyor?

``` clojure
(def n {:a 1})
(assoc n :b 2)
;; => {:a 1, :b 2}
(assoc :b 2 n)
;; => Error
``` 

`assoc` 2 argüman alıyor. Bu yüzden, `swap!` ile kullanımda ilk argüman otomatikman mevcut atom oluyor. 

## defonce id=g_11290

Like def, but you cannot overwrite it.

``` clojure
(defonce foo 5)
;; => #'user/foo

foo
;; => 5

;; defonce does nothing the second time
(defonce foo 10)
;; => nil

foo
;; => 5
``` 

## printing a map id=g_11291

``` clojure
(print {:a 1 :b 2})
;; => {:a 1, :b 2}nil
(clojure.pprint/pprint {:a 1 :b 2})
;; => {:a 1, :b 2}
``` 

## lisp - Why does Clojure have "keywords" in addition to "symbols"? - Stack Overflow

https://stackoverflow.com/questions/1527548/why-does-clojure-have-keywords-in-addition-to-symbols

> Keywords are symbolic identifiers that evaluate to themselves. They provide very fast equality tests...

> Symbols are identifiers that are normally used to refer to something else. They can be used in program forms to refer to function parameters, let bindings, class names and global vars...

> Keywords are generally used as lightweight "constant strings", e.g. for the keys of a hash-map or the dispatch values of a multimethod. Symbols are generally used to name variable and functions and it's less common to manipulate them as objects

## mapv function id=g_11292

> Returns a vector consisting of the result of applying f to the
> set of first items of each coll, followed by applying f to the set
> of second items in each coll, until any one of the colls is
> exhausted.

Usage:

``` clojure
(mapv f coll)
(mapv f c1 c2)
(mapv f c1 c2 c3)
``` 

``` clojure
(mapv + [1 2] [3 4])
;; => [4 6]
``` 

## hash-map function id=g_11293

https://clojuredocs.org/clojure.core/hash-map

``` clojure
(hash-map)
;; => {}
{}
;; => {}
(hash-map :key1 1, :key1 2) ; overwrites repeated keys
;; => {:key1 2}
(hash-map :key1 'val1)
;; => {:key1 val1}
(hash-map [:compound :key] nil) ; compound key
;; => {[:compound :key] nil} 
``` 

``` clojure
(map #(hash-map % 0) (seq "abcdefgh"))
;; => ({\a 0} {\b 0} {\c 0} {\d 0} {\e 0} {\f 0} {\g 0} {\h 0}) 
(apply hash-map (.split "a 1 b 2 c 3" " "))
;; => {"a" "1", "b" "2", "c" "3"}
``` 

## namespaced keyword notations id=g_11294

``` clojure
  #:a{:b :c}
;; => #:a{:b :c}
{:a/b :c}
;; => #:a{:b :c}
``` 

## map-indexed function id=g_11295

``` clojure
(map-indexed (fn [idx itm] [idx itm]) "foobar")
;; => ([0 \f] [1 \o] [2 \o] [3 \b] [4 \a] [5 \r])
(map-indexed hash-map "foobar")
;; => ({0 "f"} {1 "o"} {2 "o"} {3 "b"} {4 "a"} {5 "r"})
``` 

## vec function id=g_11296

``` clojure
(vec '(1 2 3))
;; => [1 2 3]

(vec [1 2 3])
;; => [1 2 3]

(vec #{1 2 3})
;; => [1 3 2]

(vec {:a 1 :b 2 :c 3})
;; => [[:c 3] [:b 2] [:a 1]]

(vec '())
;; => []

(vec nil)
;; => []
``` 


## Setup Clojure

https://clojure.org/guides/deps_and_cli

``` clojure
brew install clojure
clj
``` 

``` clojure
(+ 2 3)
``` 

Create a deps.edn file to declare the dependency: `~/projects/study/clj/ex/study_clojure/ex01/deps.edn`

``` clojure
cd ~/projects/study/clj/ex/study_clojure/ex01
clj
``` 

``` clojure
(require '[clj-time.core :as t])
(str (t/now))
;; => "2019-11-01T11:53:40.214Z"
``` 

## Writing a program id=g_11297

Edit `~/projects/study/clj/ex/study_clojure/ex01/src/hello.clj`

``` clojure
clj -m hello
;; => Hello world, the time is 02:57 PM
``` 

# Articles

## Article: Freecodecamp Clojure

### Hashmaps id=g_11298

https://guide.freecodecamp.org/clojure/hashmaps/

Two ways to construct:

1. constructor function

``` clojure
(hash-map :a 1 :b 2)
;; => {:b 2, :a 1}
``` 

2. hashmap literal `{}`

``` clojure
{:a 1 :b 2}
;; => {:a 1, :b 2}
``` 

#### Converting other collections to hashmaps id=g_11299

``` clojure
(hash-map [:a 1 :b 2 :c 3])
;; => ; => IllegalArgumentException No value supplied for key: [:a 1 :b 2 :c 3]
``` 

We need to use `apply`

It destructures a collection before applying a function to it:

``` clojure
(apply + [1 2 3])
;; => 6
(apply hash-map [:a 1 :b 2 :c 3])
;; => ; => {:c 3, :b 2, :a 1}
``` 

## Article: Do Things: A Clojure Crash Course | Clojure for the Brave and True

https://www.braveclojure.com/do-things/

### Special Forms id=g_11300

In the previous section, you learned that function calls are expressions that have a function expression as the operator. The two other kinds of expressions are macro calls and special forms

the main feature that makes special forms “special” is that, unlike function calls, they don’t always evaluate all of their operands

Another feature that differentiates special forms is that you can’t use them as arguments to functions

### Multi-arity id=g_11301

Functions also support arity overloading. This means that you can define a function so a different function body will run depending on the arity

``` clojure
(defn multi-arity
  ;; 3-arity arguments and body
  ([first-arg second-arg third-arg]
     (do-things first-arg second-arg third-arg))
  ;; 2-arity arguments and body
  ([first-arg second-arg]
     (do-things first-arg second-arg))
  ;; 1-arity arguments and body
  ([first-arg]
     (do-things first-arg)))
``` 

Arity overloading is one way to provide default values for arguments

``` clojure
(defn x-chop
  "Describe the kind of chop you're inflicting on someone"
  ([name chop-type]
     (str "I " chop-type " chop " name "! Take that!"))
  ([name]
     (x-chop name "karate")))
``` 

"karate" is the default value.

### Variable-arity id=g_11302

By including a `rest` parameter. 

`&` is the rest parameter

``` clojure
(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))

(favorite-things "Doreen" "gum" "shoes" "kara-te")
; => "Hi, Doreen, here are my favorite things: gum, shoes, kara-te"
``` 

### Destructuring id=g_11303

Binding names to values within a collection.

``` clojure
;; Return the first element of a collection
(defn my-first
  [[first-thing]] ; Notice that first-thing is within a vector
  first-thing)

(my-first ["oven" "bike" "war-axe"])
; => "oven"
``` 

Destructuring maps:

opt01:

``` clojure
(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.33})
; => Treasure lat: 100
; => Treasure lng: 50
``` 

opt02: short form id=g_11487

``` clojure
(defn announce-treasure-location
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))
``` 

`:as` keep access to original map argument:

``` clojure
(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))

  ;; One would assume that this would put in new coordinates for your ship
  (steer-ship! treasure-location))
``` 

### anonymous functions id=g_11304

`%&`: Rest parameter

``` clojure
(#(identity %&) 1 "blarg" :yip)
; => (1 "blarg" :yip)
``` 

### returning functions id=g_11305

Returned functions are closures

``` clojure
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)
; => 10
``` 

### Ex: symmetrizer function

``` clojure
(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])
(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))
``` 

### let id=g_11307

Rest parameters in `let`

``` clojure
(def dalmatian-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
(let [dalmatians (take 2 dalmatian-list)]
  dalmatians)
; => ("Pongo" "Perdita")
(let [[pongo & dalmatians] dalmatian-list]
  [pongo dalmatians])
; => ["Pongo" ("Perdita" "Puppy 1" "Puppy 2")]
``` 

### loop id=g_11308

``` clojure
(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration))))
; => Iteration 0
; => Iteration 1
; => Iteration 2
; => Iteration 3
; => Iteration 4
; => Goodbye!
``` 

You can do the same thing by recursive functions but this is more verbose:

``` clojure
(defn recursive-printer
  ([]
    (recursive-printer 0))
  ([iteration]
    (println iteration)
    (if (> iteration 3)
      (println "Goodbye!")
      (recursive-printer (inc iteration)))))
(recursive-printer)
; => Iteration 0
; => Iteration 1
; => Iteration 2
; => Iteration 3
; => Iteration 4
; => Goodbye!
``` 

### reduce id=g_11306

Process each element in a sequence and build a result.

Implementation:

``` clojure
(defn my-reduce
  ([f initial coll]
   (loop [result initial
          remaining coll]
     (if (empty? remaining)
       result
       (recur (f result (first remaining)) (rest remaining)))))
  ([f [head & tail]]
   (my-reduce f head tail)))
``` 

#### Ex: symmetrizer with reduce

``` clojure
(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))
``` 

reduce is more expressive:

With `loop` you need to read the whole code. 

With `reduce` you know that you are processing elements of collection.


## Article: Clojure in 10 big ideas - Stuart Halloway

https://github.com/stuarthalloway/presentations/blob/master/ClojureInTenBigIdeas-Jun-2017.pdf?raw=true

## Article: Functional-navigational programming in Clojure(Script) with Specter id=g_11311

http://nathanmarz.com/blog/functional-navigational-programming-in-clojurescript-with-sp.html

This has changed fundamental aspects of programming. 

I call it: "functional-navigational programming"

It changed how to structure huge amounts of code. 

Navigators into complex data structures is much more powerful than having getters/setters for individual data structures.

Navigators can be composed. 

This allows to manipulate any data of arbitrary complexity.

``` clojure
(def world
  {:people [{:money 129827 :name "Alice Brown"}
            {:money 100 :name "John Smith"}
            {:money 6821212339 :name "Donald Trump"}
            {:money 2870 :name "Charlie Johnson"}
            {:money 8273821 :name "Charlie Rose"}
            ]
   :bank {:funds 4782328748273}}
  )
(->> world
     :people)
     (filter (fn [user] (= (:name user) name)))
     first
     :money
)
``` 

## Article: Macros by Example - Stepan Parunashvili

https://m.stopa.io/macros-by-example-6ddbc8f3d93b

### Example 1: nullthrows

#### in js

``` clojure
function nullthrows(result) {
  if (result === null || result === undefined) {
    throw new Error("uh oh");
  } 
  return result;
}
nullthrows(getUser(db, 'billy'))
// if it's null, throw Exception
;; => index.html:700 Uncaught Error: uh oh
;; =>     at nullthrows (index.html:700)
;; =>     at someStuff (index.html:1325)
;; =>     ...
``` 

#### in clojure

``` clojure
(nil-throws (get-user "billy"))
``` 

something like:

``` clojure
(defn nil-throws [res]
  (if (nil? res)
    (throw "uh oh")
    res))
``` 

#### macro:

macro:

``` clojure
(defmacro nil-throws [form]
  `(let [result# ~form] ;; assign the evaluation of form to result#
    (if (nil? result#)
      (throw
        (ex-info "uh oh, we got nil!" {:form '~form})) ;; save form for inspection
      result#)))
``` 

`\`` don't eval it this is the code to return

`~` is like interpolation `${}` in js but for lists

`#` generate some symbol that doesn't interfere with any other in different scope

`'` treat it as list, don't evaluate

``` clojure
(nil-throws (get-user "billy"))
``` 

will be replaced with:

``` clojure
(let [result# (get-user db "billy")]
  (if (nil? result#)
    (throw (ex-info "uh oh, we got nil!" {:form '(get-user db "billy")})) 
    result#))
``` 

### Example 2: pipe

#### in js

``` clojure
createBill(addToCart(cart, updatePrice(item, 100)))
``` 

convert to:

``` clojure
item |> updatePrice($$, 100) |> addToCart(cart, $$) |> createBill
``` 

or this:

``` clojure
|> [
  item, 
  updatePrice($$, 100), // updatePrice(item, 100)
  addToCart(cart, $$), // addToCart(cart, updatePrice(item, 100))
  createBill, // createbill(addToCart(cart, updatePrice(item, 100)))
]
``` 

##### opt01: a pipe function

``` clojure
pipe(item, (item) => updatePrice(item, 100))
``` 

Problem: needs anonymous functions

#### in clojure

goal:

``` clojure
(|> item
    (update-price $$ 100)
    (add-to-cart cart $$)
    create-bill)
``` 

macro:

``` clojure
(defmacro |> [form & forms]
  (reduce
    (fn [last-v form]
      (if (seq? form) ;; am I being called: (update-price $$ 100)
        `(let [~(symbol "$$") ~last-v]
           ~form)
        `(~form ~last-v))) ;; or am I just a function: create-bill
    form
    forms))
``` 

## Article: Clojure Libs and Namespaces: require, use, import, and ns | 8th Light

https://8thlight.com/blog/colin-jones/2010/12/05/clojure-libs-and-namespaces-require-use-import-and-ns.html

### The baseline: require

``` clojure
user=> (clojure.string/split "name,address,city,state,zip,email,phone" #",")
java.lang.ClassNotFoundException: clojure.string (NO_SOURCE_FILE:0)
``` 

Fix: require namespace:

``` clojure
user=> (require 'clojure.string)
``` 

> It’s worth noting here that the name of the lib itself parallels the directory structure, so in order to require clojure.string, you’d need a directory named clojure relative to your classpath, and a file called string.clj within that directory.

Alias:

``` clojure
user=> (require '[clojure.string :as string])
nil
user=> (string/capitalize "foo")
"Foo"
``` 

Quoted vector is shorthand for quoting every individual symbol:

``` clojure
(require ['clojure.string :as 'string])
``` 

Several libraries with same prefix:

``` clojure
(require '(clojure string test))
``` 

> Never fear, the main thing to remember here is that a libspec should be either a quoted symbol or vector. So whenever you want to use something like :as, that thing (the libspec) should be a vector

[Phil Hagelberg's](http://p.hagelb.org/import-indent.html) Advice:

``` clojure
;; brackets imply that all entries should be indented as peers:
  (:import [java.io File]
           [org.apache.maven.artifact.ant
            Authentication DependenciesTask RemoteRepository]))

;; while parens imply that the first element is special and only the
;; ones after it should be indented as peers:
  (:import (java.io File)
           (org.apache.maven.artifact.ant Authentication DependenciesTask
                                          RemoteRepository)))
Generated by Phil Hagelberg using scpaste at Wed Nov 24 20:58:39 2010. PST. (raw)

``` 

Append `:verbose` to see what happens:

``` clojure
 user=> (require '[clojure.string :as string] :verbose)
(clojure.core/load "/clojure/string")
(clojure.core/in-ns 'user)
(clojure.core/alias 'string 'clojure.string)
nil
``` 

### Using other namespaces’ code as though it’s yours: :refer

Namespace information is too verbose. 

Refer to `vars` as if they were ours.

``` clojure
user=> (refer '[clojure.string :refer [split]])
nil
user=> (split "a,b,c" #",")
["a" "b" "c"]
``` 

### Bringing it all together: ns

``` clojure
(ns my-great-project.core
  "This namespace is CRAZY!"
  (:use [clojure.string :only [split join]] :reload)
  (:require clojure.stacktrace
            [clojure.test :as test]
            (clojure template walk) :verbose)
  (:import (java.util Date GregorianCalendar)))
``` 

Note: 

- we use `:` keys
- no symbol quotes

``` clojure
(ns my-great-project.core
  (:require clojure.stacktrace
            clojure.walk))
``` 

## Article: Learn X in Y minutes clojure

https://learnxinyminutes.com/docs/clojure/

Check `~/projects/study/clj/ex/study_clojure/learnclojure.clj`

## Article: Clojure - Datatypes: deftype, defrecord and reify

https://clojure.org/reference/datatypes

### Basics

datatype features: `deftype` `defrecord` `reify`

Used for defining implementations

`reify`: use for instances of the implementations

Abstractions defined by `protocols` or interfaces.

datatype provides 

- a host type (anonymous in `reify`)
- some structure (implicit closure in `reify`)
- optional in-type implementations

### deftype and defrecord

They generate compiled bytecode dynamically.

Similar to `defstruct`. Differences:

- unique class
- proper type

`deftype` vs `defrecord`:

- `deftype` has only constructor as function
- `defrecord`: implementation of a persisten map
- `deftype` supports mutable fields

### Why have both deftype and defrecord?

OO programs fall into two distinct categories:

- classes of programming domain such as String, collections
- classes of application domain such as Employee, Student

For example, `employee.getName()`. You cannot take a generic approach to information processing. 

It results in an explosion of needless specificity.

Information is hidden behind class-specific micro-langauges.

By using defrecord:

- generically manipulable information
- type-driven polymorphism
- structural efficiencies of fields

### reify

`reify` :

- defines an anonymous type
- creates an instance of that type
- Similar to anonymous inner classes in java

## Article: Clojure - Clojure 1.7 is now available

https://clojure.org/news/2015/06/30/clojure-17

### Transducers:

- into: collect results of transformation
- sequence: incrementally compute
- transduce: immediately compute
- eduction: delay computation
- core.async: through a channel

Existing sequence functions now have a new arity (one fewer argument).

## Article: Clojure - Clojure Governance and How It Got That Way

## Article: Clojure - Clojure core.async Channels

https://clojure.org/news/2013/06/28/clojure-clore-async-channels

Good programs stop communicating with one another. 

This is done via queues: between producers and consumers/processors.

In JVM: 

- thread per queue -> limited queues
- no block waiting

In JS:

- no threads and no queues

People run away from threads to events/callbacks.

Events complect flow of control. FRP, Rx don't change their fundamental nature.

Objectives of core.async

- Independent threads: via queue-like channels
- Support both threads and thread pools

### History

Hoare's Communicating Sequential Processes (CSP) -> occam, java csp, go language

@idea: queues ~ stocks. channels ~ flows.

### go blocks and IOC threads

`go` is a macro

Actors?

-  Couple producer with consumer

## Article: Getting Started · clojure/core.async Wiki

https://github.com/clojure/core.async/wiki/Getting-Started

Check `/Users/mertnuhoglu/projects/study/clj/ex/study_clojure/channel01/deps.edn`

Check `~/projects/study/clj/ex/study_clojure/channel01/src/async01.clj`

https://github.com/clojure/core.async/wiki/Pub-Sub



