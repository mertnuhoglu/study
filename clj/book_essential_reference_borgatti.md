--- 
title: "Book Notes: Clojure: The Essential Reference - Renzo Borgatti"
date: 2021-01-25T00:26:32+03:00 
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

---

Book source code: `/Users/mertnuhoglu/projects/csl-book-examples/README.md`

[reborg/csl-book-examples: "Clojure: The Essential Reference" book examples by chapter.](https://github.com/reborg/csl-book-examples)

```bash
clojure -X:project/new :name ex/ex01
clojure -M:project/find-deps datawalk
```

opt01:
 
```bash
cd ~/projects/study/clj/ex/book_essential_reference_borgatti/ex01
java15
clojure -M:lib/cider-nrepl:inspect/rebl15:middleware/nrebl
clojure -M:inspect/reveal-nrepl
```

opt02:

```bash
cd ex01
clojure -M::datawalk:repl/rebel
clojure -M::repl/rebel
```

Connect from Editors:

Open `~/projects/study/clj/ex/book_essential_reference_borgatti/ex01/deps.edn`

Open `~/projects/csl-book-examples/KnowYourTools/MakingYourDevelopmentLifeEasier/2.clj`

Check rich comment: `~/projects/study/clj/ex/book_essential_reference_borgatti/ex01/KnowYourTools/MakingYourDevelopmentLifeEasier/3.clj`

Check also: `~/projects/study/clj/ex/study_clojure/ex06/src/book_essential_reference_borgatti.clj`

- ## clojure.repl help functions id=g11959
  id:: 9d547dc1-3716-47f0-80bb-0d0b18a16666

rfr: Help functions: `~/projects/study/clj/ex/study_clojure/ex06/src/help_functions.clj`

```clj
clojure.inspector/inspect-table
(ins/inspect-table [{:a 1 :b 2 :c 3}{:a 4 :b 5 :c 6}])

clojure.pprint/print-table
(pp/print-table [{:a 1 :b 2 :c 3}{:a 4 :b 5 :c 6}])


(clojure.repl/source map)
(clojure.repl/doc map)
(clojure.repl/find-doc "create-context")
(clojure.repl/apropos "unmount-renderer")
(clojure.repl/apropos "map")
(clojure.repl/dir clojure.string)

*ns*
(clojure.core/all-ns)
(clojure.core/ns-publics 'clojure.java.io)
(clojure.core/ns-aliases 'user)
```

Require functions: `~/projects/study/clj/ex/study_clojure/ex06/src/require_functions.clj`

```clj
(use '[clojure.string :only (split)])
```

- ## Debug Functions id=g11960
  id:: a9857cc8-aa95-4948-9890-062cfe7f51c6

Debug functions: `~/projects/study/clj/ex/study_clojure/ex06/src/debug_functions.clj`

```bash
clojure -M:trace:inspect/reveal-nrepl
```

[Debugging in Clojure? - Stack Overflow](https://stackoverflow.com/questions/2352020/debugging-in-clojure)

## ch01

Usually developers don’t pay explicit attention to the functions in the standard library, assuming knowledge will somewhat increase while studying the features of the language.

Options to study:

- Read a function from this book every day
- Look at official docs first, try them in REPL, search github
- Corner cases where function breaks
- Read source of the function, esp core ns

## ch02: Creating and Manipulating Functions

### 2.1 Function Definition

#### 2.1.1 defn and defn-

Features:

- destructuring
- multiple arities
- type hinting
- `:pre` `:post` conditions (via `fn`)

`defn` is a macro.

Use `macroexpand` to understand how it works:

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Functiondefinition/defnanddefn-/3.clj`

##### Informal Grammar

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Functiondefinition/defnanddefn-/4.clj`

`<term>`: terminal terms. no further expansion

`[]`: vectors

`()`: lists

`OR`: possible choices

`:=>`: term can be further expanded into other terms

`..`: repetitions

`?`: optional

##### Metadata Placement

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Functiondefinition/defnanddefn-/5.clj`

Reserved keys: `:doc :tag :pre :post`

Boolean values can be shortened with: `^:`

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Functiondefinition/defnanddefn-/6.clj`

```clj
(defn ^:bench profile-me [ms] ; <1>
```

##### Documenting

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Functiondefinition/defnanddefn-/7.clj`

```clj
(clojure.repl/doc hello)       ; <2>
(:doc (meta #'hello))          ; <4>
```

##### Pre/Post Conditions

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Functiondefinition/defnanddefn-/8.clj`

#### 2.1.2 fn

Reader macro for function literal: `#()`

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Functiondefinition/fn/2.clj`

Grammar: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Functiondefinition/fn/3.clj`

```clj
  (fn <name>? arities)
```

`name` is optional. Used for recursion.

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Functiondefinition/fn/4.clj`

`body` is implicitly wrapped in a `do` block.

##### Named Recursion

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Functiondefinition/fn/5.clj`

Multiple arities are used to make `fibo` tail-recursive.

```clj
		 ...
     (recur (+ a b) a (dec cnt)))))
```

##### Fn and Destructuring

No destructuring with function literal syntax

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Functiondefinition/fn/6.clj`

```clj
    (map (fn [[k [k' f]]] ...)      ; <2>
```

With no destructuring: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Functiondefinition/fn/7.clj`

```clj
           (let [k (first rules)
                 k' (first (second rules))
                 f (second (second rules))]
```

### 2.2 Higher Order Functions

#### 2.2.1 fnil

Decorates an `f` function. Returns default value when an argument is `nil`

```clj
(fnil
  ([f default1])
  ([f default1 default2])
  ([f default1 default2 default3]))
```

Example: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/fnil/2.clj`

Ex: `~/codes/clj/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/fnil/5.clj`

```clj
(def greetings
  (fnil string/replace "Nothing to replace" "Morning" "Evening"))
```

#### 2.2.2 comp

`((comp f1 f2 f3) x)` = `(f1 (f2 (f3 x)))`

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/comp/2.clj`

```clj
((comp inc +) 2 2) ; <1>
;; 5
```

No arguments = `identity` function. Thus, no need to deal with empty list of arguments.

Input: All functions take one argument except the last one.

Related: `sequence`

#### 2.2.3 complement

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/complement/3.clj`

Note: Everything has an extended boolean meaning.

Filter items that is not contained in a set:

```clj
  (filter (complement #{:a}) [:a :b])
  ; (:b)
```

`complement` allows to make a function from a negated function. This is not possible with `not`

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/complement/5.clj`

```clj
(def turning-right?
  (complement turning-left?)) ; <1>
```

Definition of `remove` uses `complement`: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/complement/6.clj`

```clj
(defn remove [pred coll] ; <1>
   (filter (complement pred) coll))
```

#### 2.2.4 constantly

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/constantly/2.clj`

```clj
  (constantly 10)
  ; #object[clojure.core$constantly$fn__5672 0x662138f6 "clojure.core$constantly$fn__5672@662138f6"]
  ((constantly 10) nil)
  ; 10
  ((constantly 10) :a)
  ; 10
```

Note: `reduce` sayesinde bir mapi stateful olarak işleyebiliyoruz. Eğer `reduce` olmasaydı, her `update` işlemi birbirinden bağımsız bir map dönerdi. `reduce` sayesinde aynı map objesinde yaptığımız update işlemleri kaydedilip, statei koruyabiliyoruz.

```clj
    (reduce update-note note fns)))                             ; <3>
```

Use `constantly` in test mocks/stubs: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/constantly/3.clj`

```clj
  (with-redefs [third-party-service (constantly {:b "x"})] ; <3>
```

#### 2.2.5 identity

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/identity/4.clj`

01: Idiomatic use of `mapcat` with `identity` to transform a `map` into a `sequence`.

```clj
(mapcat identity {:a 1 :b 2 :c 3}) ; <1>
;; (:a 1 :b 2 :c 3)
```

02: Use `identity` to provide a function when one is required, but it shouldn't produce any effect: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/identity/5.clj`

```clj
(filter identity [0 1 2 false 3 4 nil 5])         ; <3>
;; (0 1 2 3 4 5)
```

Alternative: `nil?` with `filter`

03: Use `identity` with `some`: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/identity/6.clj`

```clj
  (some identity [2 3])
  ; 2
```

04: Use `identity` with `partition-by` to group consecutive elements in a sequence: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/identity/7.clj`

```clj
  (> (->> (:sentence s)
          (partition-by identity) ; <2>
					(map count))
```

05: `constantly` accepts any number of args. `identity` accepts always one.

#### 2.2.6 juxt

`juxt` takes multiple functions. Returns a function that runs all these functions. It juxtaposes the return values of all these functions into a single vector.

01: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/juxt/2.clj`

```clj
((juxt first second last) (range 10)) ; <1>
;; [0 1 9]
```

02: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/juxt/3.clj`

```clj
    ((juxt up down left right) cell))) ; <4>
```

03: Keep some unaltered version of a value along with its transformations. Use `juxt` with `identity`: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/juxt/4.clj`

```clj
(map (juxt count identity) words) ; <1>
```

04: Use `juxt` to extract values from a map: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/juxt/6.clj`

```clj
(->> post
  ((juxt :count :normal-title)) ; <1>
  (join " "))                   ; <2>
```

05: Use `juxt` with `sort-by` or `group-by` to sort by multiple attributes: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/juxt/7.clj`

```clj
(sort-by (juxt count str) ["wd5" "aba" "yp" "csu" "nwd7"]) ; <1>
```

Check `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/juxt/8.clj`

```clj
(->> t
	(group-by group-criteria)
	(map (fn [[k v]] {k (map sort-criteria v)})))
```

- `comp`: Both compose multiple function into one. 

```clj
((comp f g) x) 
=
(f (g x))

((juxt f g) x)
=
[(f x) (g x)]
```

- `select-keys` and `get-in` is better to filter a map. But `juxt` is good as a `select-values`

- `zipmap` is similar to create pairs from a sequence: `(map (juxt :k identity) maps)`

Different: `(zipmap (map :k maps) maps)`

#### 2.2.7 memfn

memfn allows Java instance methods to be passed as arguments to Clojure functions.

01: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/memfn/2.clj`

```clj
(map (memfn toUpperCase) ["keep" "calm" "and" "drink" "tea"]) ; <1>
;; ("KEEP" "CALM" "AND" "DRINK" "TEA")

(map toUpperCase ["keep" "calm" "and" "drink" "tea"]) ; <2>
;; RuntimeException: Unable to resolve symbol: toUpperCase
```

02: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/memfn/4.clj`

Note: static java methods don't require `memfn`:

```clj
      (map #(Duration/between % start))     ; <2>
      (map (memfn toMillis)))))             ; <3>
```

03: Multiple argument java methods: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/memfn/6.clj`

```clj
(map (memfn indexOf ch) ["abba" "trailer" "dakar"] ["a" "a" "a"])
;; (0 2 1)
```

First argument is implicit.

#### 2.2.8 partial

01: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/partial/2.clj`

```clj
(def incrementer (partial + 1)) ; <1>
(incrementer 1 1) ; <2>
;; 3
```

Contract:

```clj
(partial
  ([f])
  ([f arg1])
```

`f` is a function of at least one arg.

02: Similar to `fn` or `#()` but `partial` is more restrictive: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/partial/4.clj`

```clj
(let [f (partial str "thank you ")] (f "all!")) ; <1>
;; "thank you all!"

(let [f #(str %1 "thank you " %2)]  (f "A big " "all!")) ; <2>
;; "A big thank you all!"
```

03: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/partial/5.clj`

Here `partial` requires less paranthesis than `fn`

It allows a function to be configurated with some argument.

04: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/partial/6.clj`

#### 2.2.9 every-pred and some-fn

01: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/every-predandsome-fn/2.clj`

02: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/every-predandsome-fn/5.clj`

`every-pred` benefit: no need to pass argument to `and`

```clj
  (filter
    (every-pred some? string? not-empty symmetric?)
    coll))
```

03: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/every-predandsome-fn/8.clj`

`some-fn`: similar to `or`

```clj
(def spam? ; <4>
  (some-fn any-unwanted-word? any-link? any-blacklisted-sender?))
```

Note: Why is the name `some-fn` instead of `some-pred?`?

A predicate is a function that returns a boolean value. `some-fn` returns the first matching value. But `every-pred` returns a boolean value always.

04: `every-fn` is similar to both `some-fn` an `every-pred`

`~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/every-predandsome-fn/10.clj`

This can also be calculated with `juxt`: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Higherorderfunctions/every-predandsome-fn/10.clj`

```clj
(map (juxt contains-two? is-7-long?) (vector "guestimate" "artwork" "threefold")) ; <1>
;; ([nil false] ["two" true] [nil false])
```

### 2.3 Threading Macros

#### 2.3.1 -> thread first macro or thrush operator

Composes a list of operations.

Arg: An expresiond and a list of forms.

01: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Threadingmacros/->/2.clj`

```clj
(macroexpand '(-> {:a 2} :a inc))
;; (inc (:a {:a 2}))
```

02: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Threadingmacros/->/7.clj`

Use `->` like `identity`

```clj
  (map #(-> {:count 1 :item %}) items) ; <4>
```

03: T-combinator (thread macro) cannot be used with nested functions: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Threadingmacros/->/11.clj`

```clj
(macroexpand-1 '(-> 1 (fn [x] (inc x))))
;; (fn 1 [x] (inc x))
```

See also:

01: Other flavors of thread macros: `->>` `as->` `some->` `some->>` `cond->` `cond->>`

`->>`: thread last macro

`as->`:

`get-in`: for nested associative data structure:

```clj
  (-> {:a 1 :b {:c "c"}} :b :c)
  ;; "c"
  (get-in {:a 1 :b {:c "c"}} [:b :c])
  ;; "c"
```

#### 2.3.2 ->> Thread last macro

01: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Threadingmacros/->>/2.clj`

#### 2.3.3 cond-> and cond->>

01: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Threadingmacros/cond->/2.clj`

```clj
(let [x \c]             ; <1>
  (cond-> x             ; <2>
    (char? x) int       ; <3>
    (char? x) inc       ; <4>
    (string? x) reverse ; <5>
    (= \c x) (/ 2)))    ; <6>

;; -> 50
```

02: Use in conditional forms where the true branch transforms the input while the false branch leaves it untouched.

```clj
(let [x "123"] (if (string? x) (Integer. x) x)) ; <1>
(let [x "123"] (cond-> x (string? x) Integer.)) ; <2>
```

03: Use `cond->` to process tree data from json/xml as maps: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Threadingmacros/cond->/4.clj`

```clj
  (cond-> m
          true (assoc-in [:k3 :j1] "default")                     ; <1>
          (same-initial? m) (assoc :same true)                    ; <2>
          (map? (:k2 m)) (assoc :k2 (apply str (vals (:k2 m)))))) ; <3>
```

04: Sequences use `cond->>` because they take input data as last argument: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Threadingmacros/cond->>/4.clj`

```clj
(defn process [signals {:keys [boost? bypass? interpolate? noise? cutoff?]}] ; <1>
  (cond->> signals
    (< (count signals) 10) (map inc)                                         ; <2>
    interpolate? (mapcat range)                                              ; <3>
    bypass? (filter bypass?)                                                 ; <4>
    noise? (random-sample noise?)                                            ; <5>
    cutoff? (take-while #(< % cutoff?))))                                    ; <6>

(process signals {:bypass? even? :interpolate? true :noise? 0.5 :cutoff? 200})
```

#### 2.3.4 some-> and some->>

01: Use for null checks: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Threadingmacros/some->andsome->>/2.clj`

```clj
(-> {:a 1 :b 2} :c inc) ; <1>
;; NullPointerException

(some-> {:a 1 :b 2} :c inc) ; <2>
;; nil
```

02: Use for env variable configuration: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Threadingmacros/some->andsome->>/3.clj`

```clj
(defn system-port []
  (or (some-> (System/getenv "PORT") Integer.) ; <1>
      4444))

(system-port) ; <2>
;; 4444
```

03: Use with `re-seq`: No need for guards agains nil: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Threadingmacros/some->andsome->>/4.clj`

#### 2.3.5 as->

Similar to `->` but allows precise placement of the pipe argument: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Threadingmacros/as->/2.clj`

```clj
(as-> {:a 1 :b 2 :c 3} x   ; <1>
  (assoc x :d 4)           ; <2>
  (vals x)                 ; <3>
  (filter even? x)         ; <4>
  (apply + x))
;; 6
```

Macroexpanding `as->`: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Threadingmacros/as->/3.clj`

```clj
(let [x {:a 1, :b 2, :c 3}
      x (assoc x :d 4)
			...
```

03: Destructuring with piped placeholder: `~/projects/csl-book-examples/Creatingandmanipulatingfunctions/Threadingmacros/as->/5.clj`

```clj
(let [point {:x "15.1" :y "84.2"}]
  (as-> point {:keys [x y] :as <$>} ; <1>
    (update <$> :x #(Double/valueOf %))
    (update <$> :y #(Double/valueOf %))
    (assoc <$> :sum (+ x y)) ; <2>
    (assoc <$> :keys (keys <$>)))) ; <3>
```

## 2.4 Function Execution

### 2.4.1 apply

