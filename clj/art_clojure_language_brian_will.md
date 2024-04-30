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

# art_clojure_language_brian_will

[The Clojure Language - YouTube](https://www.youtube.com/playlist?list=PLAC43CFB134E85266)

	fp: functional programming - brian will
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
		[source] --reader-> [reader data] --evaluator-> [running code]
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
		symbols and lists get special treatment
		symbols resolve into the values of the var associated with that symbol
		a symbol containing slash is called fully qualified
			part before the slash specifies namespace 
			dog/cat
				var mapped to symbol cat in the dog namespace
		cat
			resolves to a var mapped to a symbol in current ns
			lists call functions, macros, special forms
		(foo 51 bar)
			evaluator treats a list first as a function call
			the function that gets invoked is stored in the var resolved from symbol foo
			the number 51 and the object resolved from symbol bar are arguments
			if the var resolved from symbol foo does not store a function, exception is thrown
			a macro is a special kind of function that is invoked in compile time, not during execution
			if foo is macro
				evaluator passes args (`bar`) unevaluated
					ie. pass reader data itself
				macro call returns reader data
					it gets inserted in place of macro call list
				that reader data is evaluated
					macro can return another macro too
			if first symbol doesn't resolve to a var
				before throwing exception:
					evaluator checks if it matches 16 special form names
					if so list is evaluated according to the unique rules of these form names
	Special Forms
		def special form
			assigns a value to a var in current ns
			x = 9
			(def x 9)
			(def x 10)
		fn special form
			returns a function object
			last expression returns
			neden semboller var'lara map edilmiş, doğrudan var'ların refere ettikleri valuelar yerine?
				bu aslında fonksiyonel purityyi ihlal ediyor
				böylece kod runtimeda update edilebilir
				buna monkey patching veya live patching deniyor.
			symbols in fn are resolved to vars, not values of the vars
				why?
				so they can change behavior on runtime
				= monkey patching = live patching
			(fn [x & y])
				`& y` : receives 0+ args as list
				variadic arguments
			functions are lexically scoped
				means: in nested fcts, syms are resolved inside out
		defn foo = def foo (fn...)
		if special form
				(if condition expression2 expression3)
				eğer condition false veya nil dışında bir değerse,
					o zaman exp2 evaluated
					değilse exp3 evaluated
		do: runs multiple expr in order
			why?
			put multiple expr when only one expr is expected
				ex: if form
				(if (cond) (do (step01) (step02)))
		let: creates local variables by binding values to syms
			(let [bindings] body)
			son ifadeyi döner
			bindinglerle body'yi ayırt etmek için, bracket içine konulur
			let forms are lexically scoped
				= inner let bindings override outer bindings
				(let [x 7]
					(let [x 9]))
				interior let, enclosing let bindinglerini override eder
		recur
			genel olarak while veya for döngülerine gerek yoktur. 
				çünkü recursion ile istediğimiz tür bir döngüyü yapabiliriz.
				sorun: her yeni çağrı, call stack'te bellek işgal eder
				ancak fonksiyon hemen yanıtı dönerse, compiler call stackteki belleği temizleyebilir
				fakat jvm'den dolayı bu mümkün değil.
				ancak explicit bir şekilde recur special form ile bu mümkün.
				bu form tail pozisyonda kullanılmalı
			we don't need while or for loops
			problem: each call requires new memory on call stack
			tail call elimination: no need to store local vars
			recur only allowed in tail position
			tail position (transitively):
				(if (..)
					(recur (..) (..)))
		loop 
			let gibi local bindingler oluşturur
				farkı: recursion point oluşturur recur form için
				her recursion sırasında tekrar local bindingler yapılır
			creates local bindings for symbols
			creates recursion point for recur form
				where new values bound
			(loop [bindings] body)
			why loop instead of fn?
				recursion wo creating one-off fct
			(loop [bindings]
				(recur ..))
		quote 
			unevaluated olarak bir elemanı döner
				özellikle makrolarda kullanılır
				bazı fonksiyonlar da symbol ve list argümanları bekleyebilir
					bu durumda quote ile wrap ederiz bu argümanları
				(quote elem) formu yerine 'elem formu da aynı anlama gelir
			(quote elem)
			(foo (quote bar))
			=
			(foo 'bar)
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
			bir sembol bir ns içinde resolve edildiğinde, bağlı olduğu var'ın değerini döndürür
				eğer var'ın kendisini döndürmek istiyorsak, var special formunu kullanırız
				bunun da literal kısa yazım şekli #'sym şeklinde
				çok fazla kullanılan bir şey değildir
			(var sym)
			(var foo/bar)
		class symbol access
			Classname
			Classname$NestedClassName
		new
			(new class args)
			(new java.io.File "myfile.dat")
		. (dot)
			java object fieldını veya java methodunun sonucunu döndürür
			(.instanceMember instance args*)
			(.instanceMember Classname args*)
			(.-instanceField instance)
			(Classname/staticMethod args*)
			Classname/staticField
			(. class static_field)
			(. instance field)
			(. (anInstanceReturningFct) aField)
			(. inst (method args))
		.. (dot dot)
			(.. instance-expr member+)
			(.. Classname-symbol member+)
			ex:
				(.. System (getProperties) (get "os.name"))
				(. (. System (getProperties)) (get "os.name"))
				(-> (System/getProperties) (.get "os.name"))
		set!
			(set! dot-form expr)
			(set! (. anInstance aField) 3)
		doto
			(doto (new java.util.HashMap) (.put "a" 1) (.put "b" 2))
			-> {a=1, b=2}
	Factorial Function
	Fizz-Buzz Problem
	Collections
		Collection Functions
			count
				(count coll)
			type?
				list?
				vector?
				map?
			contains?
				(contains? map k)
				(contains? vec idx)
				always returns false for lists
			conj
				conj
					(conj map [k v])
					(conj coll elem)
					'conjoin': returns new collection with an added element or key-value pair
					'conjoin': eklenen conj
				assoc
					(assoc map k v)
					(assoc vec idx x)
					'associate': returns new collection with added/modified value for given key or index
				dissoc
					(dissoc map k)
					'associate': returns new map in which a key has been removed
				merge
					(merge map1 map2)
					'associate': returns new map that combines the key-value pairs of one or more maps
			get
				get
					(get map k)
					(get vec idx)
					returns value for a given key or index in a collection
					always returns nil for a list
				pop
					(pop vec)
					(pop lst)
					returns a new collection without the first element/key-value pair
					doesn't work on maps
				peek
					(peek vec)
					(peek lst)
					returns the element/key-value pair that would be removed by pop
		sequence
			a sequence is an interface that supports two operations:
				first
				rest
			Sequence types implement the interface clojure.lang.Iseq
			Seqable types have operations that produce sequences.
			seq function returns a sequence from a collection
			Why is a vector itself not a sequence but a list is?
			Because of efficiency issues. 
			Uygulamada bir collectionın doğrudan sequence mı yoksa seqable mı olduğu çok önemli değil
				çünkü first ve rest fonksiyonları zaten otomatik olarak seq fonksiyonunu çağırıp collectionı sequence'a çeviriyor
				(first {3 5}) => (first (seq {3 5}))
			Sequence interfaceinin temel amacı: collectionlar üzerinde loop yapabilmek
				ve bunu hem yüksek performanslı, hem de stateless yapabilmek
		Sequence Interface
			seq
				(seq coll)
			keys
				(keys map)
			vals
				(vals map)
			cons
				meaning: construct a list.
					now cons as noun: a list like data structure
				cons bir sequence type. bir değer tutar bir de başka bir sequencea referans tutar
					cons fonksiyonu yeni bir cons oluşturur iki argüman alarak: bir değer bir de sequence
					eğer ikinci argüman nil ise, o zaman cons bir list döner
				a cons is a sequence type which holds a value and a reference to another sequence
					cons function creates a new cons given a value and a sequence
					when its second argument is nil, cons returns a list
				cons bir sequence olduğundan, başka cons'lara ref verebilir böylece bir zincir (chain) oluşturabilir
					liste benziyor, ancak farkı: list elementleri sadece başka list elementlerine ref verebilir, sequencelarına
					consun ikinci elemanı ise başka sequencelara da ref verebilir
					böylece lazy sequence kullanımına imkan tanır
		Lazy sequence
			lazy sequence generates its elements only as needed
				tüm elemanlarını baştan oluşturmaz, ihtiyaç olunca getirir
				bir kullanımı: sonsuz listeler oluşturmak
				ancak sonsuz kullanımlı sequence kullanmak programı dondurabilir dikkatli olunmazsa
			standart lazy sequence oluşturan fonksiyonlar:
				range
					(range start end step)
					returns a lazy sequence of integers 
				repeat
					(repeat n x)
					(repeat 3 "ali")
					returns an infinite lazy sequence that repeats a value
				repeatedly
					(repeatedly n f)
					(repeatedly 3 rand)
					returns an infinite lazy sequence of values generated by calls to a given function
				iterate
					(iterate f arg)
					(iterate dec 2)
					like repeatedly, but each call to the function takes the previous element as argument
				cycle
					(cycle coll)
					returns an infinite lazy sequence tht repeats the elements of a collection
				lazy-seq macro
					(lazy-seq body)
					Herhangi bir lazy sequence oluşturmak için bunu kullanırız
					lazy-seq bir body argümanı alır. 
					makro bunu bir fonksiyona çevirir ve her çağrıldığında bir daha bunu eval eder
					basit bir örnek:
						(def x (lazy-seq '(1 2 3 4)))
					Fakat bu gerçekte kullanışlı bir örnek değil.
					Gerçekçi bir örnek özyinelemeli bir fonksiyon ve cons içerir.
					Ex01:
						(defn zeroes [] (lazy-seq (cons 0 (zeroes))))
						alternatif ifade şekli:
						(defn zeroes [] (cons (lazy-seq 0 (zeroes))))
		Sequence Functions
			core
				next
					like rest, but returns nil for sequences of zero or one elements
					(next s)
				nth
					like get, but works on sequences
					(nth s n)
				apply
					invokes a function with arguments taken from a sequence
					(apply f s)
				map
					returns a lazy sequence produced by invoking a function with arguments from sequences
					(map f s1 s2)
				reduce
					returns result of invoking a function on successive pairs of elements
					(reduce f s)
					(reduce f agg s)
				reductions
					returns a lazy sequence of the intermediate values that would be produced by reduce
			filter
				filter
					returns a lazy sequence of elements of another sequence for which a condition function returns true
					(filter cond s)
				remove
					like filter; but removes those matching elements and filters the rest
					(remove cond s)
				take
					returns a lazy sequence of the first n elements of a sequence
					(take n s)
				take-last
					returns a lazy sequence of the last n elements of a sequence
					(take-last n s)
				take-nth
					returns a lazy sequence of every nth elements of a sequence
					(take-nth n s)
				concat
					returns a lazy sequence that concatenates together multiple sequences
					(concat s1 s2)
			interleave
				interleave
					returns a lazy sequence that takes elements from multiple collections round-robin
					(interleave coll1 coll2 coll3)
				interpose
					returns a lazy sequence in which a value is inserted in between elements of a sequence
					(interpose x coll)
				distinct
					returns a lazy sequence in which only the first occurrence of any value is retained
					(distinct coll)
				reverse
					returns a list of the elements of a sequence in reverse order
					(reverse coll)
				flatten
					returns a lazy sequence containing the elements of a sequence and its nested sequences
					(flatten nested-coll)
				sort
					returns a non-lazy sequence of the elements of another sequence in sorted order
					(sort f coll)
	Namespace
		Her namespace bir sembolle isimlendirilir. 
			Her namespace sembolleri varlara map eder.
			Belli bir anda sadece bir ns mevcut ns olarak kullanılır evaluator tarafından.
			Program başlangıcında mevcut ns user'dır. User ns clojure.core içindeki tüm elemanları içerir.
		in-ns
			in-ns ns'yi değiştirir. eğer ns yoksa, onu oluşturur.
			(in-ns 'foo)
		bir ns 4 tür bağıntı (mapping) içerir:
			symbol-to-var (interned) <- def special form
			symbol-to-var (referred) <- refer function
				(clojure.core/refer 'foo)
				referred olunca, diğer ns'deki semboller taşınmıyor, sadece onlar erişilebilir oluyor
				böylece fully qualify etmeden de sembollere erişebiliriz.
				eğer refer kullanmazsak, fully qualify etmemiz gerekir.
				eğer refer edilen sembol mevcut ns içinde varsa, o zaman :rename etmek gerekir:
				(clojure.core/refer 'foo :rename (alice))
			symbol-to-class <- import macro
				(clojure.core/import java.util.Date)
			symbol-to-namespace <- alias function
				(clojure.core/alias 'newname 'originalname)
				(newname/foo)
		Read-Eval: bir  dosyayı load fonksiyonuyla yükleriz
			(load "bar/baz")
			java classpath'deki bar klasörü içindeki baz.clj dosyasını yükler ve eval eder
		user namespace
		clojure.core
		in-ns
			(in-ns 'a-namespace)
		4 kinds of mappings
			symbol-to-var (interned)
				(def bar 4)
				bar -> var -> 4
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
		.
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
		^{:kw true}
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
		create types id=g12115
			id:: 9b66271a-dfd0-4f75-89f0-e06ffd348a87
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
		defrecord id=g12114
			id:: 800a642e-9804-461d-b196-a480c6656f17
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
		refs, atoms, agents, vars
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



