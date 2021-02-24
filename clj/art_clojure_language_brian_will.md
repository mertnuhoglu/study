--- 
title: "Study: The Clojure Language by Brian Will"
date: 2021-11-04T14:20:02+03:00 
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

[The Clojure Language - YouTube](https://www.youtube.com/playlist?list=PLAC43CFB134E85266)

    fp: functional programming
      fp: referential transparency
      fp vs ip: mutable state
      why?
        to become referentially transparent
        = pure functions
        = no side effects
        => fct can be understood in isolation
      how to program without mutable state?
        by creating new data
        persistent collections: share memory
      what about I/O?
        inherently mutable
        haskell, scala: use monads
          sneak impure code into pure
        clj: doesn't enforce purity
      fp goal: constraining mutable state
        not eliminating it
      ms (mutable state) bw executable threads
        solution: reference types
        refs sync threads wo (without) blocks
      runs on JVM
        no inheritance
        no encapsulation
        yes: polymorphism
    AST: source text represented as an object or data structure
      reader: source -> AST (reader data)
      AST: language constructs sa. stmt, fct
      reader data: std data types sa nums, lists
      source --reader-> reader data --evaluator-> running code
      evaluator: very transparent
        follows a few rules
      symbol: foo
      keyword: :foo
      list: (1 2 3)
        linked list: no random access
      vector: [1 2 3]
        tree: random efficient access
      map: {"foo" 1}
      mutable ref: var
      map of symbols to vars: namespace
        mutable map
        has a symbol
        organized as global ns
        clojure.core ns
          operators such as +, - are symbols not built-in operators
    Reader and Evaluator
      source_field value dependent_field
      symbols and lists: special treatment
      cat
        resolves to value of held in vars in ns
        lists call functions, macros, special forms
      (foo 51 bar)
        if foo is macro
          evaluator passes args (`bar`) unevaluated
            ie. pass reader data itself
          macro call returns reader data
            it gets inserted in place of macro call list
          that reader data is evaluated
            macro can return another macro too
        if first symbol doesn't resolve to a var
          before throwing exception:
            exvaluator checks if it matches 16 special form names
    Special Forms
      def special form
        assigns a value to a var in current ns
      fn special form
        returns a function object
        last expression returns
        symbols in fn are resolved to vars, not values of the vars
          why?
          so they can change behavior on runtime
          = monkey patching = live patching
        (fn [x & y])
          `& y` : receives 0+ args as list
        functions are lexically scoped
          = in nested fcts: syms are resolved inside out
      do: runs multiple expr in order
        why?
        put multiple expr when only one expr is expected
          ex: if form
          (if (cond) (do (step01) (step02)))
      let: creates local variables by binding values to syms
        (let [bindings] body)
        let forms are lexically scoped
          = inner let bindings override outer bindings
          (let [x 7]
            (let [x 9]))
      recur
        we don't need while or for loops
        problem: each call requires new memory on call stack
        tail call elimination: no need to store local vars
        recur only allowed in tail position
        tail position (transitively):
          (if (..)
            (recur (..) (..)))
      loop 
        creates local bindings for symbols
        creates recursion point for recur form
          where new values bound
        (loop [bindings] body)
        why loop instead of fn?
          recursion wo creating one-off fct
        (loop [bindings]
          (recur ..))
      quote 
        (quote elem)
        (foo (quote bar))
        =
        (foo !bar)
      throw and try
        general
          (throw exception)
          (try body catch-clauses)
          (catch class exception-body body)
        ex
          (try
            (foo)
            (catch BadThing alice
              (bar alice)
            (finally 
              (bob))))
      var
        (var sym)
        (var foo/bar)
      new
        (new class args)
        (new java.io.File "myfile.dat")
      . (dot)
        (. class static_field)
        (. instance field)
        (. (anInstanceReturningFct) aField)
        (. inst (method args))
      set!
        (set! dot-form expr)
        (set! (. anInstance aField) 3)
    Factorial Function
    Fizz-Buzz Problem
    Collections
      Collection Functions
        count
        type?
          list?
          vector?
          map?
        contains?
        conj
          conj
          assoc
          dissoc
          merge
        get
          get
          pop
          peek
          first
          rest
      Sequence Interface
        seq
        keys
        vals
        cons
      Lazy sequence
        range
        repeat
        repeatedly
        iterate
        cycle
        lazy-seq macro
      Sequence Functions
        core
          next
          nth
          apply
          map
          reduce
          reductions
        filter
          filter
          remove
          take
          take-nth
          take-last
          concat
        interleave
          interleave
          interpose
          distinct
          reverse
          flatten
          sort
    Namespace
      user namespace
      clojure.core
      in-ns
        (in-ns 'a-namespace)
      4 kinds of mappings
        symbol-to-var (interned)
          (def bar 4)
          bar -> var01 -> 4
          sym -> var -> value
        symbol-to-var (referred)
          (clojure.core/refer 'foo)
          (clojure.core/refer 'foo :rename {alice bob})
            ; foo/alice -> bob
        symbol-to-class (import macro)
          (clojure.core/import java.util.Date)
        symbol-to-namespace (alias fct)
          (clojure.core/alias 'super 'an-alias)
          =>
          (super/foo) = (an-alias/foo)
      load
        (load "some/file")
      require
        (require 'clojure.java.io)
        ; loads clojure/java/io.clj
        after require ns returns back unlike load fct
        (require '(clojure.java [io :as bar]))
      use
        like require but also refers it
        (use 'clojure.java.io)
      ns macro
        bundles in-ns require use import functions
        (ns foo.bar
          (:require clojure.contrib.sql)
          (:use clojure.test)
          (:import java.util.Date java.util.Timer))
    metadata
      (with-meta [1 2] {:so_meta true})
      (def x (with-meta [1 2] {:so-meta true}))
      =
      (meta- x) ;=> {:so-meta true}
      =
      (def x ^{:so-meta true} [1 2])
    arities
      fct can have multiple bodies if they have different arity
      (def foo
        (fn
          ([a b] ...)
          ([a b c] ...)))
    destructuring
      vectors
      maps
        :keys
      nested destructuring
        vectors
        maps
    set
      hash-set
      sorted-set
    get alternatives
      collection objects are fct
        ([8 5 13] 1) ;=> 5
      keywords are fct
        (:y {:y 5}) ;=> 5
    #(fct-body)
      short for fct
      #(print "hi")
    #{set-vals}
    #'var
      #'x
    ^sym
      short for metadata map:
      ^foo
      =
      ^{:tag foo}
      used to convey type information
    ^keyword
      short for
      ^{kw true}
      ^:foo
      =
      ^{:foo true}
		defmacro
			don't write macros
				run only at compile time
				strange things can happen
				confuse other readers
			gensym fct
				generates unique sym
				(gensym) ; G_x sym x: number
				(gensym "foo") ; foox
			fully-qualified syms
		polymorphism
			clj discards 
				encapsulation (all fields are accessible)
				inheritance (none in types)
				but it keeps polymorphism
			protocol: defprotocol
				defines a set of method signatures
				equivalent to java interfaces
				types must implement all protocol methods
			create types
				deftype: for data structure
					mutable fields
				defrecord: for domain data
					immutable fields
					map interface
						each field is key-value pair
			defprotocol
				(defprotocol name docstring method-signatures)
				method-signatures
					(method-name parameter-lists method-docstring)
				(defprotocol Roger "sth"
					(foo [this] [this a b] "sth") ; multiple arities
					(bar [this] "sth"))
				this: by convention
					the object which implements the protocol
			defrecord
				(defrecord name fields specs)
				(defrecord Nadine [x y z] ; fields
					Roger ; protocol implemented
					(foo [this]
						(do-stuff y))
					...)
				=> a new Nadine java class is created in ns
				(def adam (new Nadine 3 "hi" false)) ; new special form
				(foo adam 7 2) ; instance adam: adam.foo(7, 2)
				(. adam x) ; adam.x field access
				(get adam :x) ; adam.x as keyword
			defprotocol = interface
			defrecord = class
			map fct returns a new record
				(assoc adam :x 5) ; new Nadine record {:x 5 :y "hi" :z false}
				(assoc adam :a 5) ; new field added {:x 5 :y "hi" :z false :a 5}
				(conj adam [:a 5]) ; same
				dissoc returns a regular map not instance of record
				(dissoc adam :x) ; map {..}
			deftype
				same syntax
			reify macro
				returns a one-off instance of anonymous type with no fields
				(reify
					Roger
					(foo [this] ..))
				can access local bindings of enclosing context
			extend-type macro
				extend methods in libraries without changing their type definition
				(extend-type Nadine
					Albert
					(baz [this a b]
						(sth a b))
					Philip ; additional protocol
					..)
				can add additional protocols to existing type/record
		Reference types
			reference type = mutable data structures
			refs, atomms, agents, vars
			vars
				objects mapped to syms and ns
				purpose: hold top-level code objects in global vars
				assigned new values with `def` form
				reassignment is ok
				replacing existing function: useful for
					debugging
					live patching
				cost: no mitigation for mutable state management problems
					fix: other reference types
					ref, atom, agent: impose discipline on updating mutable state
			dynamic vars
				(def ^{:dynamic true} *x* 3) ; dynamic var mapped to sym *x*
				(defn foo []
					(print *x*))
				(foo) ; 3
				(binding [*x* 5] (foo)) ; 5
				(foo) ; 3
			deref
				(def a (atom 3))
				(def b (agent 7))
				(def c (ref 5))
				(deref a) ; 3
				(deref b) ; 7
				(deref c) ; 5
			update methods differ: swap! send-off alter
				(swap! a + 4 1)
				(send-off b + 4 1)
				(alter c + 4 1) ; exception
				(dosync
					(alter c + 4 1))
				update is guaranteed to be atomic
				if another thread changes reference's value
					then update starts over
				ex: 
					a = 20
					(swap! a + 4 1) ; meanwhile a becomes 23
					(swap! a + 4 1) ; 28
			ref vs atom
				ref consistency bw multiple references
				swap! reruns when atom is updated by another thread
				doswap reruns when any ref inside is updated by another thread
				(def x (ref 3))
				(def y (ref 7))
				(dosync
					(alter x + (deref y)))
				when any of x or y is updated outside, dosync reruns
			ref agents
				agents differ from atoms
				(def a (agent 9))
				(send-off a + 4 1) ; returns a
				send-off: async (delayed), returns the agent
				swap: sync run, returns value


## Rock paper scissors game

ref: `~/codes/clj/snake-clojure/src/snake_clojure/app.clj`

```clj
(loop []
	(play-hand)
	(recur))

(defn play-hand []
	(let [comp-guess (rand-nth ["r" "p" "s"])
				player-guess (get-guess)
				winner (winner comp-guess player-guess)]
		(println "comp: " comp-guess)
		(println "player: " player-guess)
		(cond
			(= player-guess nil) (pr "invalid")
			(= winner 0) (pr "tie")
			...)
		))

(defn get-guess []
	(pr "input: ")
	(let [guess (read-line)]
		(if (get {"r" true "p" true} guess) guess)))
```

## tic-tac-toe game

ref: `~/codes/clj/tictactoe-cli/src/tictactoe_cli/core.clj`

https://github.com/practicalli/tictactoe-cli

```clj
(defn triples [board]
	(concat
		(partition-all 3 board)
		(list
			(take-nth 3 board) ; 1st col
			(take-nth 3 (drop 1 board)) ; 2nd col
			(take-nth 3 (drop 2 board)) ; 3rd col
			(take-nth 4 board) ; diagonal
			(take-nth 2 (drop-last 2 (drop 2 b)) ; 2nd diag)))) 

(defn triple-winner? [triple]
	(if (every? #{:x} triple) :x
		(if (every? #{:o} triple) :o)))
; if all triple elems are :x it returns :x

(declare triples) ; var is created

(defn winner? [board]
	(first (filter #{:x :o} (map triple-winner? (triples board)))))

(defn full-board? [board]
	(every? #{:x :o} board)

(defn print-board ...)
```

```clj
(comment
  (identity starting-board)
  ;; [1 2 3 4 5 6 7 8 9]

  (def board starting-board)
  (triples board)
  ;; ((1 2 3) (4 5 6) (7 8 9) (1 4 7) (2 5 8) (3 6 9) (1 5 9) (3 5 7))

  (partition-all 3 board)                       ; the rows of the board
  ;; ((1 2 3) (4 5 6) (7 8 9))

  (list
   (take-nth 3 board)                           ; first column
   (take-nth 3 (drop 1 board))                  ; second column
   (take-nth 3 (drop 2 board))                  ; third column
   (take-nth 4 board)                           ; top-left to bottom-right diagonal
   (take-nth 2 (drop-last 2 (drop 2 board)))) ; top-right to bottom-left diagonal
  ;; ((1 4 7) (2 5 8) (3 6 9) (1 5 9) (3 5 7))
  (take-nth 2 (drop-last 2 (drop 2 board)))
  ;; (3 5 7)
  (drop 2 board)
  ;; (3 4 5 6 7 8 9)

  (every? #{:x :o} board)
  ,)
```



