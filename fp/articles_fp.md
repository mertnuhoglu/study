  <url:file:///~/Dropbox/mynotes/content/articles/articles_fp.md>
	p

# Category Theory

## ref

		<url:file:///~/Dropbox/mynotes/content/articles/articles_fp_reactive.md>

## books

	books
		Introduction to Functional Programming
			<url:file:///~/Dropbox/mynotes/content/books/fp/introduction_to_functional_programming_wadler.md>
		Purescript By Example by Phil Freeman
			<url:file:///~/Dropbox/mynotes/content/books/js/purescript_by_example_phil_freeman.md>
		Frisby's Mostly Adequate Guide to Functional Programming - Brian Lonsdorf
			~/projects/study/js/study_frisby_book_adequate_guide_to_fp.Rmd
		Javascript Allonge
			<url:file:///~/Dropbox/mynotes/content/books/js/book_javascript_allonge.md>
		Learn Js Ecmascript 6 - Egghead
			<url:file:///~/Dropbox/mynotes/content/books/js/book_egghead_learn_js_ecmascript_6.md>
		Frisby Intro to FP - egghead Brian Lonsdorf
			<url:file:///~/Dropbox/mynotes/content/books/fp/frisby_intro_to_fp_egghead.md>
			~/projects/jekyll/mertnuhoglu.com/content/blog/frisby_intro_to_fp_egghead.md

## articles fp

	articles fp id=g_10608
		articles fp <url:file:///~/Dropbox/mynotes/content/articles/articles_fp.md#r=g_10608>
		Brian Lonsdorf
			Brian Lonsdorf - Oh Composable World!-SfWR3dKnFIo.mp4
				dot chaining
					rxjs style, pipeline like
				contrast: imperative style
					every line is disconnected
					introducing states
				major contributor to complexity: handling of state
				second: control flow
				third: code size
				dot chaining:
					no control flow, no state
				programming is not math
				function composition
					f . g = x => f(g(x))
				dot chaining is function composition
					str
						.toUpperCase().trim()
					trim(toUpperCase(str))
				we can use math to learn programming
					category theory:
						theory of composition / arrows
				a -> b -> c
					|> F(a) -> F(b) -> F(c)
					/Users/mertnuhoglu/Dropbox/public/img/ss-101.png
					ex
						const contrivedEx1 = str => { 
							const trimmed = str.trim()
							const number = parseInt(trimmed)
							const nextNumber = number + 1
							return String.fromCharCode(nextNumber)
						}
						contrivedEx1(' 64 ' )
					how to dot chain this?
					we put str.trim() into a box
					ex
						const contrivedEx1 = str => 
							[str.trim()]
							.map( trimmed => parseInt(trimmed) )
							.map( number => number + 1 )
							.map( nextNumber => String.fromCharCode(nextNumber))
						}
					ex: use Box
						const Box = x => ({ 
							map: f => Box(f(x)),
							fold: f => f(x)
						})
						const contrivedEx1 = str => 
							Box(str.trim())
							.map( trimmed => parseInt(trimmed) )
							.map( number => number + 1 )
							.fold( nextNumber => String.fromCharCode(nextNumber))
						}
						contrivedEx1(' 64 ' ) // 'A'
				what did we do?
					unified call syntax
					minimized state
					captured assignment
					forced control flow
				complexity causes:  
					assignment +
					loops
					callbacks
					side effects
					branching
					errors
				loops:
					solution: map, filter, reduce
					how to deal with while loops?
						unfold
						ex
							const range = (i, count) =>
								unfold( x => if(x <= count) [x, x+1], i)
							range(5,10)
							// [5,6,7,8,9,10]
				callbacks:
					solution: Promise
					ex:
						Promise(6).then(six => six / 2)
						// Promise(3)
						Promise(6).map(six => six/2)
						// Promise(3)
						Promise(6).chain(six => Promise(six/2))
						// Promise(3)
					ex:
						/Users/mertnuhoglu/Dropbox/public/img/ss-102.png
						-->
						const readFile = futurize(fs.readFile) 
						const writeFile = futurize(fs.writeFile)
						const contrivedEx2 = () =>
							readFile(cfg.json', 'utf-8')
							.map(contents => contents.replace(/8/g, '6'))
							.chain(replaced => writeFile('cfg1.json', replaced))
						contrivedEx2().fork(e => console.error(e),
							r => console.log('success'))
					ex:
						const lib = username =>
							getTweets(username)
							.map(ts => truncateTo130(ts))
							.chain(ts => writeFile('tweets.json', ts))
						lib('@drboolean')
						.chain(f => saveToS3(f))
						.fork(e => console.error(e),
							r => console.log(r))
				Branching and Errors
					type: Either/Or
						just like fork
						if Left, calls first one
						if Right, calls right one
					ex: file read null checks
						/Users/mertnuhoglu/Dropbox/public/img/ss-103.png
						const getConfig = () =>
							Either.try(fs.readFileSync)('cfg.json')
						const contrivedEx3 = () =>
							getConfig()
							.map(JSON.parse)
							.fold(e => 3000, c => c.port)
					ex: null checks
						/Users/mertnuhoglu/Dropbox/public/img/ss-104.png
						const contrivedEx4 = user =>
							fromNullable(user.address) 
							.chain(a => fromNullable(a.match(/../)))
							.chain(zip => fromNullable(cityByZip(zip)))
							.fold(() => "can't find city", city => city)
					features
						seems harder to hack
						safer programs
							because caller needs to decide what do on errors
					html
						Component :: a -> JSX
						const Heading = str =>
							`<h1>${str}</h1>`
						how to compose JSX functions?
							const Comp = g => 
							({
								fold: g,
								contramap: f =>
									Comp(x => g(f(x)))
							})
							const Title = Comp(Heading).contramap(s => s.pageName)
							Title.fold({ pageName: 'Home',
								currentUser: {id: 2, name: "Chris'}})
							// <h1>Home</h1>
					Hoc: higher order components
					map, contramap, concat
						used with react
			A Million Ways to Fold in JS-JZSoPZUoR58.mp4 - Brian Lonsdorf
				agenda
					recursion, corecursion, transducers, monoids, f-algebras
				recursion
					no loops in fp
						because loops mutate and side effects
						they are too low level
						we can capture them in functions
					sum function
						opt1
							var sum = function(xs) {
								if (xs.length === 0) return 0;
								return first(xs) + sum(rest(xs))
							eval:
								1 + sum([2,3])
								1+ 2 + sum([3])
								...
						ES6/2015: tail recursive optimization
							var sum = function(xs) {
								if (xs.length === 0) return 0;
								return go(acc+ first(xs) + rest(xs))
						opt2:
			JS.everywhere(2012) - Monads, Monoids and Make Believe - Brian Lonsdorf-awEGF8giTcE.mp4
				problem
					var social = "49304"
					var current_user = {id: 2, is_admin: true}
					if (current_user.is_admin) social.replace(...)
					# security flaws
				oop solution
		LambdaConf 2015 - How to Learn Haskell in Less Than 5 Years   Chris Allen-Bg9ccYzMbxc.mp4
			Chriss Allen
			good courses:
				cis194
				nidca
			watch
				Stop Treading Water: Learning to Learn
			lessons
				different angles
				take note your pains
			haskellbook.com
			haskell irc channel
			bitemyapp learnhaskell
		Functional programming design patterns by Scott Wlaschin-113588389.mp4
			monads = continuations of functions
				f: one input, two outputs
				how to compose them?
				use monad as an adapter: one input --> two inputs
				bind is the answer
					adaptive block
					one input --> two inputs
				bind
					let bind nextFunction optionInput =
						match optionInput with 
						| Some s -> nextFunction s
						| None -> None
				pyramid of dooms
					# null handling:
						let example input = 
							doSomething input
							|> bind doSomethingElse
							|> bind doAThirdThing
					# task finishing: async callbacks
						let taskBind f task =
							task.WhenFinished (fun taskResult -> f taskResult)
						let taskExample input = 
							startTask input 
							|> taskBind startAnotherTask
							|> taskBind startThirdTask
					# error handling for custom validation
			monoid
				rules
					closure
					associativity
					identity element
				ex
					1 + (2 + 3)
					"" + "alo"
				why useful?
					closure
						converts pairwise operations into reduce
							1 + 2 + 3
							[1, 2, 3] |> List.reduce (+)
					associativity
						i can use: divide and conquer, parallelization, incremental accumulation
						ex: incremental accumulation
							1 + 2 + 3
							(1 + 2 + 3) + 4
				is function composition a monoid?
					/Users/mertnuhoglu/Dropbox/public/img/ss-91.png
					if input/output types are same types
					how to call them?
						endomorphisms
					ex: event sourcing
						event application function:
							Event -> State -> State
											 |\endomorphism/|
							any function containing an endomorphism can be converted into a monoid
							how?
								by partial application of events
								/Users/mertnuhoglu/Dropbox/public/img/ss-92.png
			monads vs. monoids
				monads are actually monoids
			monad laws:
				monoid laws in disguise: closure, associativity, identity
		Haskell Monads in 8 Minutes-gEoruozy3mk.mp4
			bind operator: >>=
				(>>=):: m a -> (a -> m b) -> m b
				m a olarak gelen girdinin m kutusunu çıkartır
				a üzerinde işlem yapıp m b'yi üretir.
				bunu döndürür
			ex: monadif definition of Maybe
				# >>= is infix operator
				data Maybe x = Just x | Nothing
				return x = Just x
				Nothing >>= f = Nothing
				Just x >>= f = f x
				# note: >>= is defined by cases
			ex: bind for Lists
				return x = [x]
				l >>= f = concatMap f l
				# concatMap handles Nothing case
		Haskell Tutorial-02_H3LjqMr8.mp4
			ghci
			:l haskell-tut
			:r
			-- comment
			{- 
			multiline comment
			-}
			import Data.List
			import System.IO
			data types
				maxInt = maxBound :: Int
		Brian Beckman - Don't fear the Monad-ZhuHCtR3xq8.mp4
			composition of functions
			how to do composition with these:
				f: a -> Ma 
					type of return value is M of a (some extra data applied to a)
				g: a -> Ma
				h: a -> Ma
			function combining f g:
				\a -> (fa) >>= \a -> (ga)
				fa, ga return Ma
				but the symmetry is broken due to brackets
				\a -> [ (fa) >>= \a -> (ga) ]
		An Algebraic Approach to Functional Domain Modeling by Debasish Ghosh @ Functional Conf 16-0q-w16pOuyc.mp4
			Functional Domain Modeling  
				Domain Model definition:
					related to problem domain
					how the architect in the problem domain interact 
					we are solution architect
					map problem domain to solution architecture
				Functional Lens
					function composition to build larger domain behavior out of smaller one
				Marius Eriksen: Your Server as a Function
					all twitter servers are structured based on fp
						composed algebraically
					twitter services are functions
				Domain model is a collecton of functions
			Domain Algebra
				domain entities -> algebraic data types
				domain behaviors -> functions [Type] -> Type
				business laws -> invariants (type system or properties)
				monoid + monad 
			Domain Modeling
				Domain Model = ∪_i domain_algebra_i
				domain_algebra = { m[T1,T2,...] | T(i) ∈ Types }
				module m = {f(x) | p(x) ∈ domain_rules }
			ex:
				use case
					1. client places order - flexible format
					2. transform to internal domain model entity and place for execution 
					3. trade and allocate to client accounts
				functions
					def clientOrders: ClientOrderSheet => List[Order]
						input in flexible format
					def execute: Market => Account => Order => List[Execution]
						market: where the trade will be executed
					def allocate: List[Account] => Execution => List[Trade]
				constraints:
					def clientOrders: ClientOrderSheet => List[Order]
					def execute[Account <: BrokerAccount]: Market => Account => Order => List[Execution]
						constrain the type of Account to be of BrokerAccount
							nothing except BrokerAccount can be passed
					def allocate[Account <: TradingAccount]: List[Account] => Execution => List[Trade]
				api
					we haven't yet implemented any type
					we don't know what an Account, Order is
					we have 
						types (domain entities)
						functions
						laws
					this is algebra of the api
					these 3 functions when joined together will give me a trade generation function
				put them into a module
					trait Trading[Account, Trade, ClientOrderSheet, Order, Execution, Market] {
						# parameterized on types
						def clientOrders: ClientOrderSheet => List[Order]
						def execute: Market => Account => Order => List[Execution]
						def allocate: List[Account] => Execution => List[Trade]
						def tradeGeneration(market: Market, broker: Account, clientAccounts: List[Account]) = ???
					# declarations (algebra) of the functions are there only
				algebraic design
					this is algebraic design
					algebra (interface) is the binding contract of the api
					implementation is not part of the algebra
						there can be multiple implementations
				let's do some algebra
					def clientOrders: ClientOrderSheet => List[Order]
					def execute( m: Market, broker: Account): Order => List[Execution]
					def allocate( accounts: List[Account]): Execution => List[Trade]
						output of clientOrders -> input of execute
						output of execute -> input of allocate
				a problem of composition 
					def f: A => List[B]
					def g: B => List[C]
					def h: C => List[D]
					# how to solve it
						they contain effects of aggregation and optionality
						they can be generalized
							def f[M: Monad]: A => M[B]
							def g[M: Monad]: B => M[C]
							def h[M: Monad]: C => M[D]
					# function composition with Effects: algebra for Kleisli
						combinator: andThen
							def andThen[C](f: B => M[C])
						Kleisli class:
							case class Kleisli[M[_], A, B](run: A => M[B]) {
								def andThen[C](f: B => M[C])
									(implicit M: Monad[M]): Kleisli[M, A, C] = Kleisli((a: A) => M.flatMap(run(a))(f)) 
								}
						algebra of Kleisli:
							def clientOrders: Kleisli[ List, ClientOrderSheet, Order]
							def execute( m: Market, broker: Account): Kleisli[ List, Order, Execution]
							def allocate( accounts: List[Account]): Kleisli[ List, Execution, Trade]
				the complete trade generation logic
					def tradeGeneration(
						market: Market,
						broker: Account,
						clientAccounts: List[Account]) = {
						clientOrders andThen
							execute(market, broker) andThen
								allocate(clientAccounts)
						}
					# implementation follows the specification
					# we get ubiquitous language for free
					# no error handling here: they are encapsulated into Kleisli
			custom handling of errors
				how to do it?
				we need to report some to the user
				error handling as an effect
				stacking of effects
					def clientOrders: Kleisli[ List, ClientOrderSheet, Order]
					->
					List --> M[List[_]]
				monad transformers
					ex:
						type Response[A] = String \/ Option[A]
						--->
						type Error[A] = String \/ A
						type Response[A] = OptionT[Error, A]
						# OptionT is a monad transformer
					List Transformer: ListT
					we want to get a list of orders
						type StringOr[A] = String \/ A
						type Valid[A] = ListT[SringOr, A]
						api
							def clientOrders: Kleisli[ List, ClientOrderSheet, Order]
							-->
							def clientOrders: Kleisli[ Valid, ClientOrderSheet, Order]
						a small change in algebra, a huge step for domain model
						domain model can now handle errors and report them
			Algebra of Types
				Kleisli: dependency injection
				List: aggregates
				Disjunction: error accumulation
				Monad
				Monoid
				Future: reactive non-blocking computation
			Domain rules that cannot be handled by types
				property checking tools
				verifiable as properties
				ex
					trait OrderLaw {
						def sizeLaw: Seq[ClientOrder] => Seq[Order] => Boolean =
							{ cos => orders =>
								cos.size == orders.size
							}
		Why Functional Programming Matters by John Hughes at Functional Conf 2016-XrNdvWqxBvA.mp4
			1940s
				fp was minimalist
					ex: who needs booleans
						booleans just makes a choice
						make functions to make choices
							true x y = x
							false x y = y
						define if then else
							ifte bool t e =
								bool t e
					ex: who needs integers
						integer just counts loop iterations
							two f x = f (f x)
							one f x = f x
						to recover a normal integer 
							two (+1) 0
						addition by sequecning loops
							add m n f x = m f (n f x)
						multiplication by nesting loops
							mul m n f x = m (n f) x
					ex: factorial
						fact n = 
							ifte (iszero n)
								one
								(mul n (fact (decr n)))
						iszero n = 
							n (\_ -> false) true
						decr n = 
							n (\m f x -> f (m incr zero))
								zero
								(\x->x)
								zero
					any data structure can be entirely replaced by functions
					Church encodings
						functions can be a foundation for mathematics
					but type checker needs a little help
			McCarth: Lisp
			1965: The Next 700 Programming Languages - P.Landin
				one programming language: ISWIM + 700 libraries
				Laws: tremendously important idea
					not only important to express programs
					it is important to reason about them
					laws/equivalences:
						(maplist f (reverse l)) = (reverse (maplist f l))
						but not always: when there is side-effect
			1978: John Backus: Can Programming Be Liberated from the von Neumann Style
				Fortran: first compiler that generated code better than humans 1977
				manifesto for fp
				wonderful read
					conventional programming languages grow ever enormous, but not stronger (ada in mind)
					inherent defects at the most basic level cause them to be both fat and weak
					they are word at a time programming
						creates bottleneck 
					inability to composition
					lack of mathematical properties
						no laws
			Peter Henderson: Functional Geometry
				Escher's pictures drawn by functions
				picture is a value
				function: inputs pictures and outputs pictures
			ideas of fp
				whole values vs. word by word
				combining forms vs. non-composed functions
				algebra as litmus test (simple laws) vs. nothing
				functions as representations
			Haskell vs. Ada vs. ... - Paul Hudak
				one military problem implemented in many langauges
					haskell: 85 lines
					ada: 1000 lines
			Lazy Evaluation 1976
				idea: the whole value can be infinite
		Philip Wadler and Erik Meijer - On Programming Language Theory and Practice-9SBR_SnrEiI.mp4
		Functors, Applicatives, And Monads In Pictures
			http://adit.io/posts/2013-04-17-functors,_applicatives,_and_monads_in_pictures.html
			any value can be in a context
				context ≅ box
				you put value into a box
					Just 2
					value and context
				when you apply a function to this value
					different results depending on the context
				ex: Functors, Monads, Arrows 
				Maybe data type:
					two related contexts
					Just, Nothing
					data Maybe a = Nothing | Just a
			Functors
				when a value is in a context
					you can't apply a normal function to it:
						(+3) (Maybe 2)
						doesn't work
					you need fmap
					fmap knows how to apply functions to values in a context
						fmap (+3) (Just 2)
						> Just 5
				what is a Functor really?
					Functor is a typeclass
						to make a data type f a functor
							data type needs to define fmap
					code
						class Functor f where
							fmap:: (a->b) -> fa -> fb
					fmap
						takes a function: (a->b) = (+3)
						takes a functor: fa = Just 2
						returns a new functor: fb = Just 5
					note: Maybe is a Functor
						it specifies how fmap applies to Just and Nothing
					code
						instance Functor Maybe where
							fmap func (Just val) = Just (func val)
							fmap func Nothing = Nothing
				analogy: unbox and apply function and box again
				ex:
					fmap (+3) Nothing
					> Nothing
				why useful?
					if there is no Maybe and fmap
					then handling a database record:
						post = Post.find_by_id(1)
						if post
							return post.title
						else
							return nil
						end
					haskell:
						fmap (getPostTitle) (findPost 1)
					or using <$> infix version of fmap:
						getPostTitle <$> (findPost 1)
				ex: apply a function to a list
					fmap (+3) alist
				Lists are functors too:
					instance Functor [] where
						fmap = map
				ex: apply a function to another function
					fmap (+3) (+1)
					# result is composed function
					ex
						import Control.Applicative
						let foo = fmap (+3) (+2)
						foo 10
						> 15
				Functions are functors too:
					instance Functor ((->) r) where
						fmap f g = f . g
			Applicatives
				next step after Functor
					wraps values in a context
					wraps functions in a context too
				ex
					2 --> Just 2
					(+3) --> Just (+3)
				infix op: <*>
				Just (+3) <*> Just 2 == Just 5
				ex: interesting situations
					/Users/mertnuhoglu/Dropbox/public/img/ss-87.png
					[(*2), (+3)] <*> [1,2,3]
					> [2,4,6,4,5,6]
				ex: apply a function that takes two args
					functors can't
						(+) <$> (Just 5)
						> Just (+5)
						Just (+5) <$> (Just 4)
						> error
					applicatives can
						(+) <*> (Just 5)
						> Just (+5)
						Just (+5) <*> (Just 4)
						> Just 8
				ex:
					(*) <$> Just 5 <*> Just 3
					> Just 15
				liftA2 does the same thing:
					liftA2 (*) (Just 5) (Just 3)
					> Just 15
			Monads
				comparison:
					functors apply a function to a boxed value
					applicatives apply a boxed function to a boxed value
					monads apply a function that returns a boxed value to a boxed value
						using >>= bind function
				ex: Maybe is a monad
					suppose half is a function that works on even numbers:
						half x = if even x
							then Just (x `div` 2)
							else Nothing
					so half returns a Maybe (boxed value)
					what if we input a boxed value
						we need to use >>= 
						to shove boxed value into a function
					code
						Just 3 >>= half
						Nothing
						Just 4 >>= half
						Just 2
						Nothing >>= half
						Nothing
				Monad is a typeclass:
					class Monad m where
						(>>=) :: m a -> (a -> m b) -> m b
					where >>= is
						takes a monad: ma = Just 4
						takes a function that returns a monad: (a -> m b) = half
						returns a monad: m b = Just 2
				so Maybe is a Monad:
					instance Monad Maybe where
						Nothing >>= func = Nothing
						Just val >>= func = func val
				@mine: railway oriented programming
					f: takes one input, returns two outputs
					we need an adapter to make f take two inputs
					monad bind is the adapter
						input value: boxed (two inputs)
						returns: boxed (two outputs)
				ex: IO monad
					getLine: takes no arg and gets user input
					getLine :: IO String
					readFile: takes a filename and returns file's contents
					readFile :: FilePath -> IO String
					putStrLn: takes a string and prints it
					putStrLn :: String -> IO ()
					all three take a regular value and return a boxed value
					getLine >>= readFile >>= putStrLn
				do notation: syntactic sugar for monads
					foo = do
						filename <- getLine
						contents <- readFile filename
						putStrLn contents
			conclusion
				functor is a data type. implements Functor
				applicative is a data type. implements Applicative
				monad is a data type. implements Monad
				Maybe implements all three
				differences:
					functors: apply a function to boxed value using fmap or <$>
					applicatives: apply a function to boxed value using <*> or liftA
					monads: apply a function that returns a boxed value to a boxed value using >>= or liftM
		Erlang Factory SF 2016 - Keynote - John Hughes - Why Functional Programming Matters-Z35Tt87pIpg.mp4
			Backus paper
				one word at a time
					ex: scalar product as imperative
						c = 0
						for i = 1 step 1 until n do
							c = c + a[i] x b[i]
					ex: scalar product as functional
						def ScalarProduct =
							(FoldR +) ● (Map ⨯) ● Transpose
			Functional Geometry, Peter Henderson
				principles
					whole values instead of one word at a time
					combining forms 
					algebra as litmus test
						ex
							rot(above(p,q) = beside(rot(p), rot(q))
			The Whole Value can be infinite
				lazy producer-consumer
					ex:
						consumer: search strategy
						producer: search space
					ex: numerical derivative
						limit eps xs = <first element of xs within eps of its predecessor>
					ex: newton-raphson square root
						sqrt a = limit eps (iterate next 1.0)
							where next x = (x + a/x) / 2
					here:
						(iterate next 1.0): sequence (producer)
						limit eps: convergence check (consumer)
		dotJS 2015 - Andre Medeiros - The whole future declared in a var-BfZpr0USIi4.mp4

# Unclassified

	Unclassified
		Introduction to Functional Programming - Eric Elliott
			https://ericelliottjs.com/premium-content/webcast-the-two-pillars-of-js-introduction-to-functional-programming/
			iterating over a list
				you don't need that iteration logic
		what-are-combinators-and-how-are-they-applied-to-programming-projects-practica
			http://programmers.stackexchange.com/questions/117522/what-are-combinators-and-how-are-they-applied-to-programming-projects-practica
		model-view-intent
			http://cycle.js.org/model-view-intent.html
		What is Payload in programming
			http://programmers.stackexchange.com/questions/158603/what-does-the-term-payload-mean-in-programming
			payload distinguishes:
				interesting information from
				overhead to support it
			term's root:
				transportation
					part of the load that pays
			in context of message protocols:
				differentiate:
					protocol overhead from
					actual data
				ex: json web service response
					code
						{
						"status":"OK",
						"data":
								{
										"message":"Hello, world!"
								}
						}
					payload: Hello, world
		what is side effect
			http://programmers.stackexchange.com/questions/40297/what-is-a-side-effect
			side effect = changing something somewhere
			pure function is a function that is
				idempotent
				has no side-effects
		what is idempotent function
			http://stackoverflow.com/questions/1077412/what-is-an-idempotent-operation
			a function that
				has no additional effect if called more than once with same inputs
			in math
				f(f(x)) = f(x)
				x: state of an object
				f: function that may mutate object
		sink and source
			https://en.wikipedia.org/wiki/Sink_(computing)
			sink: end-point or output point
				whereto data is written out
			source
				into which data is put
		ref
			/Users/mertnuhoglu/Movies/youtube/functional/Functional Programming with Java 8-Ee5t_EGjv0A.mp4
			/Users/mertnuhoglu/Movies/youtube/cyclejs/Hot code reloading with Cycle.js-6KD8bYSohFI.mp4
			/Users/mertnuhoglu/Movies/youtube/cyclejs/My adventure with Elm by Yan Cui-cBVXyxt-9_Q.mp4
			/Users/mertnuhoglu/Movies/youtube/cyclejs/What if the user was a function by Andre Staltz at JSConf Budapest 2015-1zj7M1LnJV4.mp4
			/Users/mertnuhoglu/Movies/youtube/cyclejs/Time Travel with Cycle.js-7fA0pVDHGJ0.mp4
			/Users/mertnuhoglu/Movies/youtube/cyclejs/My adventure with Elm by Yan Cui-cBVXyxt-9_Q.mp4
			/Users/mertnuhoglu/Movies/youtube/graphql/Build a GraphQL server for Node.js, using PostgreSQL_MySQL-DNPVqK_woRQ.mp4
		How I learned Haskell
			Benjamin C. Pierce’s Types and Programming Languages. 
				It was the best computer science text book I’ve ever read
			Philip Wadler’s Monads for functional programming 
				clicked my mind and I finally became enlightened.
			Once I understood how I could learn abstractions, the rest was easy
			papers to read
				FunctionaPearl
				A tutorial on the universality and expressiveness of fold
				Monadic Parsing in Haskell
				Monad Transformers and Modular Interpreters
				Data types a la carte
				The Essence of the Iterator Pattern
				The Zipper
			Haskell I know was different from the Haskell that is actually used. Real world Haskell uses lots of GHC extensions which makes me feel it is an entirely different language
			I founded a small Haskell shop this year and started to use Haskell in production. I realized that using Haskell in production was, surprisingly, easier than learning Haskell.
		Paul Chiusano
			Why type systems matter for UX: an example
				http://pchiusano.github.io/2013-09-10/type-systems-and-ux-example.html
				ex: deleting comments in Word
					takes a lot of time to discover how to do
				lots of books to teach how to use those big apps
				i need complex functionality
					but they need be easy to discover
				type systems solve exactly this problem
				ex: Word with type system
					click on a comment
					ask for functions: accepting a List Comment
					choose "delete comments"
			If Haskell is so great, why hasn't it taken over the world? And the curious case of Go.
				https://getpocket.com/redirect?url=https%3A%2F%2Fpchiusano.github.io%2F2017-01-20%2Fwhy-not-haskell.html
				programming is about managing omplexity
					without good tools
					we'd lose control of our programs
				history of programming
					advancements in
						removing barriers to composobality
						building tools that facilitate composition
				stages
					0: composability is most important
					1: it requires atomic units of composition: functions
					2: it is limited by side effects: pure functions
					3: mechanized reasoning simplifies humans to track: static types
					4: it is destroyed at program boundaries. idea of unison
					n: 
				impure functions
					it is possible to form composition 
					but it requires non-local reasoning
				If Haskell (or XYZ) is so great, why hasn’t it taken over?
					opt
						even java has lambdas
						very slowly it does
						others are morons
						it is too hard to learn
						marketing
					composability is destroyed at IO boundaries
						bottleneck is program boundaries
							surface area in contact with outside world
								code devoted to merely getting information
						CRUD apps / boring systems
							have large complex surface areas with outside world
						haskell excel in minimal surface programs:
							compilers
					why go is popular
						it is a better java/c
						that do lots of IO
			CSS is unnecessary
				http://pchiusano.github.io/2014-07-02/css-is-unnecessary.html
				css
					so complex that it is not implemented correctly
					new versions increase complexity
				elm:
					a sane combinator library 
					for describing layout
		references/books
			Functional Programming Jargon
				https://github.com/hemanth/functional-programming-jargon
			When is it NOT preferable to specify your types first?
				http://pchiusano.github.io/2015-10-28/top-down.html
				custom:
					first specify types
					then implement the term
		'Modelica - Component Oriented Modeling of Physical Systems' by Michael Tiller--mvEUuc-sWE.mp4
			sistem dinamiği dsl
			composition
				mevcut modelleri birleştirme
			features  
				declarative
				statically typed
				composable component models
			ex: artillery
				/Users/mertnuhoglu/Dropbox/public/img/ss-97.png
				/Users/mertnuhoglu/Dropbox/public/img/ss-98.png
			industrial robot
				478 components
				4415 variables
			equation sorting
				find equations where all variables are known except one
				bağımlılık ağacı oluşturmak için
				other symbolic manipulations
			model/package management
				impact: package manager
				repository of scientific models
				ex: artillery demo from impact npm
					/Users/mertnuhoglu/Dropbox/public/img/ss-99.png
		@toread
			Three Useful Monads
				http://adit.io/posts/2013-06-10-three-useful-monads.html
			School of Haskell
				https://www.schoolofhaskell.com
			Learn You a Haskell for Great Good!
				http://learnyouahaskell.com/chapters
			Real World Haskell
				http://book.realworldhaskell.org
			http://yogsototh.github.io/Category-Theory-Presentation/
			http://cs.stackexchange.com/questions/3028/is-category-theory-useful-for-learning-functional-programming/7843
		Build a GraphQL server for Node.js, using PostgreSQL_MySQL-DNPVqK_woRQ.mp4
			ex:
				endpoints
					list of posts
					list of authors
				normal way
					list authors
					get post by author id
				problem
					20-30 requests for some authors
					graphql: 1 request
			code
				/Users/mertnuhoglu/Dropbox/public/img/ss-93.png
				/Users/mertnuhoglu/Dropbox/public/img/ss-94.png
				/Users/mertnuhoglu/Dropbox/public/img/ss-95.png
				/Users/mertnuhoglu/Dropbox/public/img/ss-96.png
		PluralSight - Brian Lansdorf Training
		https://vimeo.com/user7981506
		https://github.com/getify/Functional-Light-JS
		Adventure with Types in Haskell - Simon Peyton Jones (Lecture 2)-brE_dyedGm0.mp4
			reuse higher order functions 
				/Users/mertnuhoglu/Dropbox/public/img/ss-100.png
				sum, product in terms of foldr
		Algebraic data types for fun and profit by Clément Delafargue-EPxi546vVHI.mp4
		bind and this - Object Creation in JavaScript P1 - FunFunFunction #43-GhbhD1HR5vk.mp4
		David Peter - Interactive Tests and Documentation via QuickCheck style Declarations - λC 2016-iTSosG7vUyI.mp4
			purescript ile applicative kullanarak ui geliştirme
		Understanding JavaScript Function Invocation and "this"
			http://yehudakatz.com/2011/08/11/understanding-javascript-function-invocation-and-this/
			The Core Primitive
				Function's call method
					core function invocation primitive
				1. Make an argument list (argList) out of parameters 1 through the end
				2. The first parameter is thisValue
				3. Invoke the function with this set to thisValue and the argList as its argument list
				ex
					function hello(thing) {  
						console.log(this + " says hello " + thing);
					}
					hello.call("Yehuda", "world") //=> Yehuda says hello world  
				all other calls are desugaring to this primitive
					desugar: take a syntax and describe it in terms of more basic core primitve
			Simple Function Invocation
				a function invocation like fn(...args) is the same as fn.call(window [ES5-strict: undefined], ...args)
				ex
					hello("world")
					// desugars to:
					hello.call(undefined, "world");  
			Member Functions
				ex: invoke a method as a member of an object
					var person = {  
					name: "Brendan Eich",
					hello: function(thing) {
						console.log(this + " says hello " + thing);
						}
					}
					person.hello("world")
					// desugars to this:
					person.hello.call(person, "world"); 
				ex: attaching object dynamically
					function hello(thing) {  
						console.log(this + " says hello " + thing);
						}
					person = { name: "Brendan Eich" }  
					person.hello = hello;
					person.hello("world") // still desugars to person.hello.call(person, "world")
			Using Function.prototype.bind
				ex
					var person = {  
					name: "Brendan Eich",
					hello: function(thing) {
						console.log(this.name + " says hello " + thing);
						}
					}
					var boundHello = function(thing) { return person.hello.call(person, thing); }
					boundHello("world");  
					# boundHello call still desugars to boundHello.call(window, "world"), we turn right around and use our primitive call method to change the this value back to what we want it to be.
		Understanding "Prototypes" in JavaScript
			http://yehudakatz.com/2011/08/12/understanding-prototypes-in-javascript/
			A Whole New Object
				to create simplest new object:
					var person = Object.create(null)
					// empty object
				why not: var person = {}
				person['name'] // undefined
				if the String is valid identifier, you can use dot form:
					person.name // undefined
				identifier: starts with unicode letter, $, _
					valid identifier: not a reserved word
			Adding values
				what a property is?
					it has a name  and value
					it can be enumerable, configurable, writable
					if enumarable: for (prop in obj)
					if writable: you can replace it
					if configurable: you can delete/change its other attributes
				ex: verbose
					Object.defineProperty(person, 'firstName', {  
						value: "Yehuda",
						writable: true,
						enumerable: true,
						configurable: true
					});
				ex: defineProperty method
					var config = {  
						writable: true,
						enumerable: true,
						configurable: true
					};
					var defineProperty = function(obj, name, value) {  
						config.value = value;
						Object.defineProperty(obj, name, config);
					}
					var person = Object.create(null);  
					defineProperty(person, 'firstName', "Yehuda");  
			Prototypes
				so far: 
					objects as simple pairs of keys and values
					js objects have one additional attribute:
						a pointer to another object
						this pointer: object's prototype
				Object.getPrototypeOf
				Object.create(man) // man is prototype
			Setting Properties
				defining properties using assignment syntax
				ex
					var man = Object.create(person);  
					man['sex'] = "male";
					man.sex
			Object Literals
				ex
					var person = { firstName: "Paul", lastName: "Irish" }  
					// This syntax is approximately sugar for:
					var person = Object.create(Object.prototype);  
					person.firstName = "Paul";  
					person.lastName  = "Irish";  
				Object.prototype has default properties
		bind and this - Object Creation in JavaScript P1 - FunFunFunction #43-GhbhD1HR5vk.mp4
			basic object
				let dog = {
					x: ..
					f: function() { return this.x }
				}
				dog.f()
			v2
				let g = dog.f
				g() // undefined
			v3
				let h = g.bind(dog)
				h()
			v4
				let button = document.getElementById(..)
				button.addEventListener(
					'click',
					dog.f()
				)
				// here this is not dog, it is window
				// because of addEventListener
				// solution:
					dog.f.bind(dog)
		proto_ vs prototype - Object Creation in JavaScript P5 - FunFunFunction #52-DqGwxR_0d1M.mp4
			what is __proto__
				let cat = {x:..}
				let cat2 = {y:..}
				Object.setPrototypeOf(cat2, cat)
				cat2.x
				cat2.__proto__
				# this is a reference to cat
			function
				function Dog() {}
				Dog.prototype.breed = "bull"
				# this for Dog() constructor function
				let a = new Dog()
				a.breed
				Dog.prototype
				# Object with bread
				a.__proto__
				# Object with bread
		What is side effect
			http://softwareengineering.stackexchange.com/questions/40297/what-is-a-side-effect
				modification of some kind of state
				ex
					changing value of a variable
					writing data to disk
					enabling/disabling a UI button
				contrary to what people say
					it does not need to be hidden
					nothing to do with idempotency
				side effect = changing something somewhere
				pure function
					a) idempotent
					b) has no side effects
			http://wiki.c2.com/?SideEffect
				when a function causes
					a non-local change to something
					other than its inputs and declared output
			https://www.johndcook.com/blog/2010/05/18/pure-functions-have-side-effects/
				pure functions are pure at certain level of abstraction
					every function has some side effect
						it uses memory, cpu 
				you can partition stateful and stateless parts
					85% functional purity is possible
		@mine: comparison Functor - Applicative - Monad
			Functor
				map
				value is boxed
				function is normal
				unbox it -> apply f -> box it
				fmap :: (a->b) -> f a -> f b
			Applicative
				lift
				both value and function is boxed
				function returns normal
				lift :: f (a -> b) -> f a -> f b
			Monad
				bind
				value is boxed
				function is normal but returns boxed
				bind :: (a -> f b) -> f a -> f b
		@mine: why do we say: monads are to compose side effects?
			because input functions of functors are normal
			input functions of monads are boxed
		Thirteen ways of looking at a Turtle -  Scott Wlaschin-AG3KuqDbmhM.mp4
			turtle api
				Move aDistance
				Turn anAngle
				PenUp
				PenDown
			oo turtle
				Turtle class
					contains mutable state
						angle, location
					type Turtle() = 
						let mutable currentPosition = initial
						let mutable currentAngle = 0.0<Degrees>
						let mutable currentPenState = initialPen
					member this.Move(distance) =
						...
					member this.Turn(angle)
				usage
					let drawTriangle() =
						let distance = 50.0
						turtle.Move distance
						turtle.Turn 120.0<Degrees>
				benefits
					familiar
				costs
					stateful/black box
					can't easily compose
					hard-coded dependencies
			Abstract data turtle
				data is separate from behavior
				type TurtleState = private {
					mutable position: Position
					..
				module Turtle = 
					let move distance state = ..
					let turn angle state = ...
				usage
					Turtle.move distance turtle
					Turtle.turn 120.0<Degrees> turtle
			Immutable functional
			State monad
				ex: turtle hitting the wall
					/Users/mertnuhoglu/Dropbox/public/img/ss-109.png
				how to keep track of the state in such a situation?
					Move returns a pair
						new state
						actual distance moved
				transforming function
					in:
						TurtleState
						input (angle, distance ...)
					out:
						new TurtleState
						output (output of the function)
					currying
						input -> TurtleState -> (TurtleState, output)
					input --> Turtle function --> State<>
					State<> function wraps State
				usage: state expression
					inside the block state is threaded
					/Users/mertnuhoglu/Dropbox/public/img/ss-110.png
					haskell: do notation
					scala: for comprehension
				benefits
					functions still composable
				costs
					harder to implement
				@mine
					Monad: function normal but returns boxed
					boxed: TurtleFunction, returns State<>
					other arg: f a = State<>
			Error handling
				Turtle function return
					Success
					Failure
				Choice, Sum, Discriminated Union, Either type
				computation expression
			Async turtle
			Batch (commands)
			Actor
				similar to Batch model
				recursive at the end of each command
			Event Sourcing
				how do we persist state?
				we store events, not commands
			Stream Processing
			OO dependency injection
			FP dependency injection
			Interpreter
			Capabilities
		Algebraic data types for fun and profit by Clément Delafargue-EPxi546vVHI.mp4
			ex:
				DnsRecord(ttl, name, 
					AValue(ipv4)
					| AaaaValue(ipv6)
			Haskell
				data DnsRecord
					| CnameRecord Int String String
					| ARecord
			scala
				sealed traid DnsRecord
				case class CnameRecord(..)
					extends DnsRecord
				...
			distributivity
				common members are refactored
					(a*b + a*c)
					= a*(b+c)
			associativity
				order not important
			identity
				Unit type: type with one value
			function
				a -> b
				b^a exponentiation
				number of possible function from a to b
				properties of exponents
					c^(b*a) = (c^b)^a
					(a,b) -> c
					= a -> b -> c
					currying
		Fun never stops. Introduction to Haskell Programming language by Paul Szulc-1jZ7j21g028.mp4
			why fp matters?
				we strive for modularity
				in fp this is easy
					composition of functions
				fun with functions
					hello = "Hello"
					# this is a function definition
					> hello
					"hello"
					it:: [Char]
				fun with arg
					london u = "London " ++ u
					> :t london
					london :: [Char] -> [Char]
				fun3
					pre c str = [c] ++ " " ++ str
					> :t pre
					pre :: Char -> [Char] -> [Char]
			type class
				set of types
				that support some collection of functions
				ex
					Eq
						(==)
						(/=)
					Ord
						< <= > >=
						min, max
					Show
						show :: a -> String
					Read
						read :: String -> a
			Class constraint
				add :: Num a => a -> a -> a
				add a b = a + b
			Fizzbuzz
				/Users/mertnuhoglu/Dropbox/public/img/ss-124.png
			Quicksort
				/Users/mertnuhoglu/Dropbox/public/img/ss-125.png
		Understanding parser combinators - a deep dive - Scott Wlaschin-RDalzi7mhdY.mp4
			what is a parser
				creating parsing recipe
					in: something to match
					f: parsing recipe (parser making)
					out: parser<something>
						recipe to make something
				combinator
					when you combine parser things
					in: parser<thing>
					combined with: combinator
					in: parser<thingb>
					out: parser<c>
				running a parsing recipe
					in: parser<something>
					in: input chars
			why parser combinators
				written in your pl
				easy to create dsl
				fun way to understand functional composition
			v1: parse character A
				ex
					let pcharA input
						if empty(input) then
							(false, "")
						else if input.[0] = 'A' then
							let remaining = input.[1..]
							(true,remaining)
			v2: parse any character
				ex
					let pchar (charToMatch, input) =
						if empty(input) then
							"no more input"
						else
				returns 
					failure case: string
					success case: tuple
					solution: Either type
				code
					type Result<'a> = 
						| Success of 'a
						| Failure of string
				ex
					/Users/mertnuhoglu/Dropbox/public/img/ss-126.png
					/Users/mertnuhoglu/Dropbox/public/img/ss-127.png
				return values are same type
					Failure "no more input"
					Success (charToMatch, remaining)
			v3: returning a function
				current problem:
					charToMatch: we know in advance
					input: we don't know in advance
				goal
					delay working with stuff that i don't know yet
				sol
					return a function for things that i don't know
					/Users/mertnuhoglu/Dropbox/public/img/ss-128.png
					pchar :: Char -> String -> Either (String String) Failure
					pchar charToMatch
					Parser<char>: second function
					type Parser<'a> = Parser of (String -> Result<'a * String>)
				Runner
			Basic Combinators
				what is a combinator
					any function that depends only on its inputs
					a combinator library
						to combine things to make more complex values of same type
					integer + integer = integer
					list ++ list = list // @ is ++ in F#
					Parser ?? Parser = Parser
						what can be a combinator for Parser?
				Basic parser combinators
					Parser andThen Parser => Parser
					Parser orElse Parser => Parser
					Parser map (transformer) => Parser
				AndThen Parser
					/Users/mertnuhoglu/Dropbox/public/img/ss-129.png
					/Users/mertnuhoglu/Dropbox/public/img/ss-130.png
				operators
					.>>. andThen
					<|> orElse
					|>> map
				demo
					/Users/mertnuhoglu/Dropbox/public/img/ss-131.png
					/Users/mertnuhoglu/Dropbox/public/img/ss-132.png
					/Users/mertnuhoglu/Dropbox/public/img/ss-133.png
					/Users/mertnuhoglu/Dropbox/public/img/ss-134.png
			Using reduce to combine parsers
				ex
					[pcharA; pcharB; pcharC] |> List.reduce (.>>.)
					let choice listOfParsers = 
						listOfParsers |> List.reduce (<|>)
					let anyOf listOfChars = 
						listOfChars 
						|> List.map pchar
						|> choice
					let parseLowercase = anyOf ['a'..'z']
				ex: sequencing things. a list of parsers in sequence
			Building a JSON Parser
				code
					type JValue =
						| JString of string
						| JNumber of float
						| JObject of Map<string,JValue>
						| JArray of JValue of list
			Summary
				working with a recipe (aka effect)
					combining recipes before running them
		Monad - FunFunFunction #21-9QveBbn7t_c.mp4
			streams are functors
				so we can map() them
			monads are more powerful functors
				that can also flatMap()
			Promises are monad?
				do they have flatMap()
					yes, it is then()
				ex:
					const promise = fetch(url)
						.then(response => response.json())
						.then(parsedResponse => ...)
				take response.json()
					this doesn't return object literal
					it returns a Promise
					which is then flattened to its value
					then it is passed to the next then()
			ex
				getInPortugese :: String -> Stream
				# note Stream is a monad
				stream
					.flatMap(word => getInPortugese(word))
					.map(word => word.toUpperCase())
					.onValue(word => console.log(word))
				# flatMap's inner function returns a Stream
				# but flatMap flattens it and returns the contained value: String
				# so we can use map() after flatMap()
		Free Monads
			What are free monads
				http://stackoverflow.com/questions/13352205/what-are-free-monads
					Free monads: turning functors into monads
					given functor f
					Free f: is a monad
					you get functions:
						liftFree :: Functor f => f a -> Free f a
						foldFree :: Functor f => (f r -> r) -> Free f r -> r
					liftFree: lets you get into monad
					foldFree: lets you get out of it
					ex: 
						a monoid X
						is a set Y
						with extra structure P
							that says it has an operation (addition) and identity (zero)
						generally:
							if X is a Y with P
							then a free X is a way of
								getting from Y to X
						ex:
							code
								class Monoid m where
									mempty :: m
									mappend :: m -> m -> m
							lists:
								data [a] = [] | a : [a]
							given t, [t] is a monoid
								instance Monoid [t] where
									mempty = []
									mappend = (++)
							so lists are free monoid over sets
		Functional Programming In JavaScript — With Practical Examples (Part 1)
			https://medium.freecodecamp.com/functional-programming-in-js-with-practical-examples-part-1-87c2b0dbc276#.p141hc5yx
			fp
				essentially:
					small reusable functions
					call them one after the other:
						f1.f2.f3
						f1(f2(f3))
				to write in this style
					functions need to follow some rules
			fp challenges
				1. how to handle if-else condition (Either Monad)
				2. Null exceptions (Maybe Monad)
				3. functions are truly reusable, and can be reused anywhere (pure functions, referential transparency)
				4. data is unchanged so that we can reuse data elsewhere (pure functions, immutability)
				5. function taking multiple values, chaining only passes a single value, still make it part of chain (currying, higher order functions)
				6. more
			Fantasy Land Specs and FP Libraries
				specs that explains how each fp function should behave
				/Users/mertnuhoglu/Dropbox/public/img/ss-137.png
				specs as interfaces
				a js class is a Functor if it implements map method
			FL Spec compliant libraries
				monetjs, barely-functional, folktalejs, ramda-fantasy, immutable-ext, fluture
			What Libraries should I Use?
			Ex1: Dealing with Null Checks
				use case: show different webpage depending on user's primary language
				problems:
					primary language can be null
					user can be null
					primary language might not be available in indexURLs
				solution
					imperative
						const getUrlForUser = (user) => {
							if (user == null) { //not logged in
								return indexURLs['en']; //return default page
							}
							if (user.prefs.languages.primary && user.prefs.languages.primary != 'undefined') {
								if (indexURLs[user.prefs.languages.primary]) {//if translation exists,
									return indexURLs[user.prefs.languages.primary];
								} else {
									return indexURLs['en'];
								}
							}
						}
						showIndexPage(getUrlForUser(joeUser));
					fp
						const R = require('ramda');
						const prop = R.prop;
						const path = R.path;
						const curry = R.curry;
						const Maybe = require('ramda-fantasy').Maybe;
						const getURLForUser = (user) => {
								return Maybe(user)//wrap user in a Maybe object 
										.map(path(['prefs', 'languages', 'primary'])) //use Ramda's to grab primary language
										.chain(maybeGetUrl); //pass language to maybeGetUrl &  get url or null Monad
						}
						const maybeGetUrl = R.curry(function(allUrls, language) {//curry to convert this to a single arg func
								return Maybe(allUrls[language]);//return Monad(url | null)
						})(indexURLs);//pass indexURLs instead of accessing globally
						function boot(user, defaultURL) {
							showIndexPage(getURLForUser(user).getOrElse(defaultURL));
						}
						boot(joeUser, 'http://site.com/en'); //'http://site.com/sp'
				Functors
					a class that stores a value and implements map method
					ex: Array
						stores a value
						implements map
					ex: MyFunctor
						const add1 = (a) => a + 1;
						class MyFunctor { //Custom "Functor"
							constructor(value) {
								this.val = value;
							}
							map(fn) {   //Applies function to this.val + returns new Myfunctor
							 return new Myfunctor(fn(this.val));
							}
						}
						//temp is a Functor instance that's storing value 1
						let temp = new MyFunctor(1); 
						temp.map(add1) //-> temp allows us to map "add1"
				Monads
					Functors + ap from Apply + ap and of from Applicative + chain from Chain
					code
						class Monad {
								constructor(val) {
										this.__value = val;
								}
								static of(val) {//Monad.of is simpler than "new Monad(val)
										return new Monad(val);
								};
								map(f) {//Applies the function but returns another Monad!
										return Monad.of(f(this.__value));
								};
								join() { // used to get the value out of the Monad
										return this.__value;
								};
								chain(f) {//Helper func that maps and then gets the value out
										return this.map(f).join();
								};
								 ap(someOtherMonad) {//Used to deal w/ multiple Monads
										return someOtherMonad.map(this.__value);
								}
						}
					specific monads: Maybe, Either
				Maybe Monad
					takes care of null
					if stored value is null, then map doesn't run the function
					it creates an instance of two sub-classes: Just or Nothing
					code 
						check page
		Functional Programming In JavaScript — With Practical Examples (Part 2)
			https://medium.freecodecamp.com/functional-programming-in-js-with-practical-examples-part-2-429d2e8ccc9e#.xzl47s8eo
			çok iyi anlatıyor
		catamorphism
			Reductio and Abstract 'em
				http://www.tomharding.me/2017/02/24/reductio-and-abstract-em/
				you can write every list function with reduceRight
					two caveats
						we have two functions for free:
							const head = ([x, ... xs]) => x
							const cons = (x, xs) => [x, ... xs]
						cons: prepend
					map
						its accumulator is another list
							const map = (f, xs) => xs.reduceRight(
								(acc, x) => cons(f(x), acc), 
								[]
							)
							map(x => x +1)([1,2,3])
					filter
						const filter = (p,xs) => xs.reduceRight(
							(acc, x) => p(x) ? cons(x,acc)
								: acc,
							[]
						)
						filter(x => x<3)([1,2,3,4])
					append
						const append = (x,xs) => xs.reduceRight(
							(acc,h) => cons(h, acc), [x]
						)
						append(4)([1,2,3])
						# reduceRight does nothing
						# it starts with a non-empty accumulator
					concat
						const concat = (xs, ys) =>
							xs.reduceRight(
								(acc, h) => cons(h, acc), ys
							)
						concat([1,2])([3,4])
					reverse
						const reverse = xs => xs.reduceRight(
							(acc, x) => append(x, acc), []
						)
					length
						const length = xs => xs.reduceRight(
							(n, _) => n + 1, 0
						)
					elemAt
						const elemAt = (n, xs) => head(xs.reduce(
							([e,n], x) => [n == 0 ? x : e, n-1],
							[undefined, n]
						))
					reduce
						const reduce = (f, acc, xs) =>
							xs.reduceRight(
								(accF, x) => z => accF(f(z, x)),
								x => x
							)(acc)
				A Strangely Familiar List
					a list cen be expressed as
						[] empty or
						[x, ... xs] a non-empty list
							an item followed by another list
							= linked list
					why we got cons and head?
						they do: construct and destruct
						they are ways to describe the structure of list
				Int-reduce-ing Our Hero
					how reduceRight works:
						[].reduceRight(f, acc) = acc
						[x, ... xs].reduceRight(f, acc) =
							f(xs.reduceRight(f, acc), x)
					why map and filter is less general?
						reduceRight has
							empty and non-empty behavior
							accumulator
						thus free to change shape of the list entirely
					what reduceRight does: catamorphism
						a way of folding a type (list)
						into a value
					if you have access to all possible configurations of your structure
						you can do anything you like
				reduce vs reduceRight
				can reduceRight do anything with lists?
					yes
					catamorphism are called folds
						which implies an unfold
							anamorphism
							ex: function that produces a range
								unfolding a starting number into a list
		Fantas, Eel, and Specification 1: Daggy
			http://www.tomharding.me/2017/03/03/fantas-eel-and-specification/
			daggy: for creating sum types
			two functions: tagged, taggedSum
			tagged
				for creating types with one constructor
				ex
					// Coord :: (Int, Int) -> Coord
					const Coord = daggy.tagged('x', 'y', 'z')
					// Line :: (Coord, Coord) -> Line
					const Line = daggy.tagged('from', 'to')
				resulting structures:
					Coord.prototype.translate = 
						function (x,y,z) {
							return Coord(
								this.x + x,
								this.y + y,
								this.z + z
							)
						}
						const origin = Coord(0,0,0)
						const myLine = Line( origin, origin.translate(2,4,6) )
			taggedSum(constructors)
				think: boolean type
					has two values
					in order to represent Bool
						we need a type with multiple constructors (sum type)
					code
						const Bool = daggy.taggedSum({
							True: [], False: []
						})
				ex: Coord
					const Shape = daggy.taggedSum({
						// Square :: (Coord, Coord) -> Shape
						Square: ['topleft', 'bottomright'],
						// Circle :: (Coord, Number) -> Shape
						Circle: ['centre', 'radius']
					})
				Square, Circle are constructors of Shape type
					Shape.prototype.translate =
						function (x,y,z) {
							return this.cata({
								Square: (topleft, bottomright) =>
									Shape.Square(
										topleft.translate(x,y,z),
										bottomright.translate(..)
								Circle: ..
					Square(Coord(2,2,0), Coord(3,3,0))
						.translate(3,3,3)
				note: Shape isn't constructor, it is type. 
					we attach methods to Shape prototype
					Shape.Square and Shape.Circle are constructors
				thus: to write a method
					it should work in all forms of Shape type
				this.cata: killer feature of Daggy
					catamorphism
					we pass a {constructor: handler} object to cata function
				we can attach methods to Bool:
					const { True, False } = Bool
					Bool.prototype.invert = function() {
						return this.cata({
							False: () => True,
							True: () => False
						})
					}
					// shorthand for Bool.prototype.cata
					Bool.prototype.thenElse = function (then, or) {
						return this.cata({ True: then, False: or})
					}
				note: different constructors can have different numbers and types of arguments
				taggedSum: lets us build types with multiple constructors
				List but not least
		Fantasy-Land
			https://github.com/fantasyland/fantasy-land
			Algebraic JS Specification
			/Users/mertnuhoglu/Dropbox/public/img/ss-138.png
			algebra
				a set of values
				a set of operators
				closed under some laws
			terminology
				value
				equivalent
			prefixed method names
		Composable application architecture with reasonably priced monads-M258zVn4m2M.mp4
			bank transfer
				user is authorized
				has sufficient funds
					log attempt
					raise an error if not
				update both accounts
			different concerns
				authorization
				user interaction
				logging
				handling errors
				persistent storage
			meta-concerns
				each concern independently
					testable
					reusable
					extensible
					reason compositionally
			dependency injection for concerns
				def transfer(Long, Account, Account, User, Authorization, Logger, ErrorHandler, Storage): Unit
				-->
				def transfer(Long, Account, Account, User, context: Authorization with Logger with ErrorHandler with Storage): Unit
				-->
				functional way:
					return a description of what we want to do
						we return a little program for some kind of interpreter
			coproduct
				def transfer(..) List[Instruction]
				where instruction is the coproduct of:
					log 
					fail with error
					authorize user
					read/write storage
				@mine 
					note: lists are like tuples
					coproducts are tuples
					we build tuple of (Log,Fail,Authorize,...)
					then we fold this tuple
					which means we do catamorphism (folding)
					this is pure function, because we return a tuple
						which is a recipe for program
						actual program is evaluated during run time
			ex of such an instruction: for user interaction
				def
					sealed trait Interact[A]
					case class Ask(prompt: String) extends Interact[String]
					case class Tell(msg: String) extends Interact[Unit]
				use: our program a list of interactions
					val prg = 
						List(
							Ask("your name?"),
							Ask("your last name?"),
							Tell("Tell, "),
					--> monads: allow assign things
						# it is a list comprehension
					val prg = for{ 
						x <- Ask("your name?"),
						y <- Ask("your last name?"),
						_ <- Tell("Tell, $x $y"),
					} yield ()
				Monad def
					trait Monad[M[_]] {
						def pure[A](a: A): M[A]
							# Box.of
						def flatMap[A,B](ma: M[A])(f: A => M[B]): M[B]
					}
					# also named: return, bind
				how to implement pure, flatMap for Interact?
					using Free Monads
					program:
						val prg: Free[Interact, Unit] = for{ 
							x <- Ask("your name?"),
							y <- Ask("your last name?"),
							_ <- Tell("Tell, $x $y"),
						} yield ()
					this is syntactic sugar for flatMap:
						val prg: Free[Interact, Unit] = for{ 
							Ask("your name?").flatMap(first =>
							Ask("your last name?").flatMap(last =>
							Tell("Tell, $first $last").map(_ =>())))
						} yield ()
				what does it mean to run a free monad?
					running a free monoid (which is list)
						= running a list
						= folding a list
							@mine:
								(bind(bind(bind ...)
					how do you fold a free monad?
						signature of foldMap
						foldmap[G[_]:Monad](f: F ~> G): G[A]
							i have a transformation from F to G
							i can convert all program into G
							i have list of instructions
								translate them to G language
							then we compose using monadic binds
							as if:
								F: compile time language
								G: run time language
							~>: we can transform F to G
		GOTO 2016 • The Return of Stream I_O • Andre 'Staltz' Medeiros-Tkjg179M-Nc.mp4 id=g_10116
			GOTO 2016 • The Return of Stream I_O • Andre 'Staltz' Medeiros-Tkjg179M-Nc.mp4 <url:file:///~/Dropbox/mynotes/content/articles/articles_fp.md#r=g_10116>
			history of cyclejs
				Erik Meijer: Stream IO: IO System as purely functional interaction
				Pual Hudak 1989: On the Expressiveness of Purely Functional IO Systems
			Haskell
				how to separate logic and effects? 1989
					monadic io
					stream io
					continuation io
				monadic io survived then
				what is monadic io in js?
		Functional Patterns in Domain Modeling — Debasish Ghosh-U0Rk9Knq8Vk.mp4
			abstract data types
			focus on: compositionality
			monoid
				/Users/mertnuhoglu/Dropbox/public/img/ss-141.png
				if any type follows MonoidLaws, that is sufficient to be used as Monoid
			interpretation brings the context
				/Users/mertnuhoglu/Dropbox/public/img/ss-142.png
			existing generic algebra structures: monoid, monad...
			composition with effects
				/Users/mertnuhoglu/Dropbox/public/img/ss-143.png
				lift g into context of monad
				/Users/mertnuhoglu/Dropbox/public/img/ss-145.png
				/Users/mertnuhoglu/Dropbox/public/img/ss-146.png
				/Users/mertnuhoglu/Dropbox/public/img/ss-147.png
				Kleisli already has andThen
				/Users/mertnuhoglu/Dropbox/public/img/ss-148.png
					def clientOrders: Kleisli[List, ClientOrderSheet, Order]
					# means: takes ClientOrderSheet and generates List of Order
					# original was:
						def clientOrders(ClientOrderSheet): List[ Order]
				trade generation logic
					def tradeGeneration(
						market: Market,
						broker: Account,
						clientAccounts: List[Account]) = {
						clientOrders andThen
							execute(market, broker) andThen
								allocate(clientAccounts)
						}
					# implementation follows specification
					# we get ubiquitous language for free
			monad transformers
			free monads
		Essential Scala - Six Core Principles for Learning Scala-J8wUy1XxL5o.mp4
			Algebraic data types
				goal
					translate data descriptions into code
					particularly: model data with logical ors and logical ands
				structure of code follows
					structure of data
				ex:
					a website visitor is
						logged in or
						anonymous
					a logged in user has
						an ID and
						an email address
				@mine
					inheritance / is-a relationship
						corresponds to logical or
					composition / has relationship
						corresponds to logical and
				product types: and
					final case class A(b: B, c:C)
					# A has a B and C
				sum types: or
					sealed trait A
					final case class B() extends A
					final case class C() extends A
					# A is a B or C
				sum and product together
					make algebraic data types
				ex
					sealed trait Visitor {
						id: Id
					}
					final case class Anonymous(id: Id)
						extends Visitor
					final case class User(id: Id, email: Email)
						extends Visitor
				ex
					# a calculation is a success or failure
					sealed trait Calculation
					final case class Success() extends Calculation
					final case class Failure() extends Calculation
				ex
					# a success has an value
					# a failure has an error message
					final case class Success(value: Int) extends Calculation
					final case class Failure(msg: String) extends Calculation
				summary
					code follows from structure of the data
			Structural Recursion
				goal
					transform algebraic data types
				ex
					implement on calculation
					def add(value: Int) : Calculation =???
				two patterns:
					pattern matching
					= polymorphism
				ex
					sealed trait A {
						def doSomething: H = {
							this match {
								case B(d, e) => doB(d, e)
								case C(f, g) => doC(f, g)
							}
						}
					}
					final case class B(d: D, e: E) extends A
					final case class C(f: F, g: G) extends A
				ex: add an Int to a Calculation
					sealed trait Calculation {
						def add(value: Int): Calculation = 
							this match {
								case Success(v) => Success(v + value)
								case Failure(msg) => Failure(msg)
			Sequencing Computation
				what is sequencing
					fp is about transforming values
					A => B => C
						function composition
				three patterns
					fold, map, flatMap
				fold
					A => B
					it works on algebraic data types
					abstraction over structural recursion
				ex
					sealed trait A {
						def doSomething: H = {
							this match {
								case B(d, e) => doB(d, e)
								case C(f, g) => doC(f, g)
					-->
					sealed trait A {
						def fold(doB: (D, E) => H, doC: (F, G) => H): H = {
							this match {
								case B(d, e) => doB(d, e)
								case C(f, g) => doC(f, g)
				ex
					sealed trait Result[A] {
						def fold[B](s: A => B, f: B): B =
							this match {
								Success(v) => s(v)
								Failure() => f
							}
						}
						final case class Success[A](value: A) extends Result[A]
						final case class Failure[A]() extends Result[A]
				fold is a generic transform
					for any algebraic data type
				fold is not always the best choice
					two reasons
						not all data is an algebraic data type
							ex: Future, infinite lists
				ex:
					getting a user from database
						Result[User]
					convert that user to json
						User => Json
						Result[Json]
					/Users/mertnuhoglu/Dropbox/public/img/ss-149.png
					F[A] map -> A -> B -> F[B]
				when to use which for sequencing computations
					map: 
						F[A] map (A=>B) = F[B]
					flatMap
						F[A] flatMap (A=>F[B]) = F[B]
					fold
						unbox, general transformation
			Type Classes
				for ad-hoc polymorphism
			Summary
				scala can be simple
					3 patterns 90% of code
					4 patterns are 99%
		A Million Ways to Fold in JS-JZSoPZUoR58.mp4
			alternative ways to loop
			there is no loops in fp
				because it has 
					low level operations
					side effects
			recursion
				var sum = xs =>
					if (xs.length === 0) return 0
					return first(xs) + sum(rest(xs))
				var reverse = xs =>
					if (xs.length === 0) return []
					return reverse(rest(xs).concat(first(xs))
				commonalities
					base case: 0 []
					recursion: sum(..) reverse(..)
					action: + concat
				how evald
					sum([1,2,3])
						1 + sum([2,3])
						1+(2+sum([3])
						1+(2+(3+sum([])))
						1+(2+(3+0))
					es6
						tail recursive optimization
				tail recursive
					var sum = xs =>
						if (xs.length === 0) return 0
						return first(xs) + sum(rest(xs))
					-->
					var sum = xs =>
						function go(acc, xs) {
							if (xs.length === 0) return acc
							return go(acc+first(xs), rest(xs))
						}
						return go(0, list)
					# nothing left in return call except nested call
				var reduce = function(f, acc, xs) {
					if(xs.length ===0) return acc
					return reduce(f, f(acc, first(xs)), rest(xs))
				}
				reduce is tail recursive
					so we can implement sum, reverse in terms of reduce
				var sum = xs =>
					reduce((acc, x) => x + acc, 0, xs)
				var reverse = xs =>
					reduce((acc, x) => [x].concat(acc), [], xs)
				reduce is very general
					can implement all list functions
					it is a catamorphism
					any loop can be captured with a fold
					apomorphism
					paramorphism
					higher order loops
			corecursion
				called anamorphism
				unfold:
					var unfold = (f, seed) =>
						function go(f, seed, acc) {
							var res = f(seed)
							return res ? go(f, res[1], acc.concat([res[0]])) : acc
						} 
						return go(f, seed, [])
				unfold(x => if(x < 26) [String.fromCharCode(x+65), x + 1], 0)
					equivalent to while loop
				var range = (i, count) =>
					unfold(..)
				var tree = ..
			transducers
				v1
					var map = (f, xs) =>
						reduce((acc, x) => concat(acc, f(x)), [], xs)
				transformation: f(x)
				accumulation: concat, []
				iteration: xs
				reducer: (acc, x) => concat(acc, f(x))
					also named: reducing fct
				v2 
					var mapper = (f,cnct) =>
						(acc, x) => cnct(acc, f(x))
					reduce( mapper(x => x + 1, concat), [], [1,2,3])
					// [2,3,4]
				make filter
					var filterer = (f,cnct) =>
						(acc, x) => f(x) ? cnct(acc, x) : acc
					reduce( filterer(x => x > 1, concat), [], [1,2,3] )
					// [2,3]
				look at signature: 
					(acc, x) 
					cnct(acc, x)
					cnct function has same args and does the same thing as reducer function
						it takes a acc and elem, and returns an acc
					our concat is a reducing function as well
				make copy with concat as reducer
					var copy = xs =>
						reduce(concat, [], xs)
				we can pass other reducing fcts to other reducing fcts
					filterer(x => x > 1, mapper(x => x + 1, concat))
					// (acc, x) => x > 1 ? concat(acc, f(x)) : acc
				this is function composition
				we can make pipeline of transformations
					mapper, filter is called: transducer
					note: reduction is itself in one loop
						not first map, then filter loop
				ex
					reduce( 
						filterer(x => x > 1,
						mapper(x => x + 1, concat)),
						[], [1,2,3])
					// [3,4]
				this does stream fusion
				protocols
					iteration +
					transformation +
					accumulation x
						there is no protocol here
			monoids
				there is a protocol for accumulation 
					it is monoid
				ex: concat has properties
					// left identity
					concat( [], xs ) == xs
					// right identity
					concat( xs, [] ) == xs
					// associativity
					concat(concat(xs, ys), zs) == concat(xs, concat(ys, zs))
				fantasy-land has specification for this
					anything is a monoid if it has
						concat
						identity element
						associativity
				let's make monoids
					list is a monoid
					sum
						constructor
							var _Sum = function(x) { this.val = x }
							var Sum = function(x) { return new _Sum(x) }
						reduce's first two args: reducer, empty
							_Sum.prototype.concat = function(y) { 
								return Sum(this.val + y.val)
							}
							_Sum.prototype.empty = function() { return Sum(0) }
						now we can call reduce/fold with list data
							fold([Sum(1), Sum(2)])
							// Sum(3)
					product
						fold([Product(3), Product(4)])
					max
						fold([Max[12], Max[3])
					all
						fold([All(false), All(true)])
				make foldMap: fold down, and give me the value
					var foldMap = (f,xs) => compose(fold, map(f))(xs)
					var sum = xs => foldMap(Sum, xs).val
					var max = xs => foldMap(Max, xs).val
					var any = xs => foldMap(Any, xs).val
				it can work on any structure
					var tree = Node(Node(Leaf(2), 1, Leaf(3)))
					sum(tree)
					product(tree)
				because of associativity, it can run in parallel
				fold reduces list into one value
					F(a) -> a
					[Sum(a)] -> Sum(a)
					[Product(a)] -> Product(a)
				that is called an algebra
			F-algebra
				code: cata
					var cata = (f, xs) =>
						f( xs.map( ys => cata(f, ys) ) )
				fixed point of a functor
					a functor: anything with map method
					fixed point: returns itself
				ex
					Nil.map = f => Nil
					_Cons.prototype.map = function(f) {
						return Cons(this.head, f(this.tail))
			interpreting program which is a list of instructions
			free monads
		Functional programming patterns for the non-mathematician (cut)-AvgwKjTPMmM.mp4
		Hey Underscore, You're Doing It Wrong!-m3svKOdZijA.mp4
		Top 10 Things to Master for Advanced JavaScript-OIioG0cx0Wo.mp4
			Object constructors
			babel
			closures
			global namespace
			iife, modules
			prototypes
			this keyword
		Gabriel Gonzales - Data science APIs in Haskell - λC Winter Retreat 2017-pXjBcoe3M2s.mp4
		Gabriel Gonzales - Applied category theory and abstract algebra - λC Winter Retreat 2017-WsA7GtUQeB8.mp4
			composability
				similar to unix pipeline
				generalize this to things other than function composition
			overview
				composable values
				practical examples
				composable types
			Monoid
				simplest composable interface
		Anjana Vakil - Immutable data structures for functional JS _ JSConf EU 2017-Wo0qiGPSV-s.mp4
		How Complexity Theory Can Save Your Job - Rob Conery-rYlwiJ0vr_4.mp4
		The Imposter's Syndrome - Rob Conery
			book 30$
		Value Types in Java 8
			Immutables Library
		http://vakila.github.io/blog/katsconf2/
		Ramda By Example
			Ramda By Example, Lesson 1-tN4wyJ9DdtM.mp4
				vim'de hızlı test etmek:
					:nmap ,m :!node %<cr>
				tüm R fonksiyonlarını import etmek
					const R = require('ramda')
					R.map(([k, v]) => global[k] = v, R.toPairs(R))  
				amaç:
					const permissions = {
						'group1-perm1': true,
						'group1-perm2': true,
						'perm3': true,
					}
					-->
					// {
					//  group1: [
					//    { value: 'group1-perm1', checked: true, 'label': 'perm1' }]
					//  ]
					// }
				ex01
					const fn = compose(toPairs)
					--$
					[ ['group1-perm1', true], ...]
				ex02
					const GROUP_LEN = 6
					const inGroup = str => str.indexOf('-') === GROUP_LEN
					const addLabel = chain(ifElse(inGroup, () => 'x', identity), head), append)
					const fn = compose(map(addLabel), toPairs)
					console.log(fn(permissions))
					--$
					[ ['group1-perm1', true, 'x'], ...]
				ex03
					const getLabel = compose(last, splitAt(GROUP_LEN + 1))
					const addLabel = chain(ifElse(inGroup, getLabel, identity), head), append)
					--$
					[ ['group1-perm1', true, 'perm1'], ...]
				ex04
					const KEYS = ['value', 'checked', 'label']
					const fn = compose(map(zipObj(KEYS)), map(addLabel), toPairs)
					--$
					[ {value: 'group1-perm1', checked: true, label: 'perm1'}, ...]
				ex05
					const convert = compose(zipObj(KEYS), addLabel)
					const fn = compose(map(convert), toPairs)
				ex06
					const groupName = obj => obj.label
					const fn = compose(groupBy(groupName), map(convert), toPairs)
					--$
					[ perm1: 
						[{value: 'group1-perm1', checked: true, label: 'perm1'}, 
						 {value: 'group2-perm1', checked: false, label: 'perm1'}]
						...]
				ex07
					const groupName = ifElse(compose(inGroup, prop('value')),
						() => 'group',
						() => 'general'
					)
					/Users/mertnuhoglu/Dropbox/public/img/ss-201.png
				ex08
					const groupName = ifElse(compose(inGroup, prop('value')),
						compose(head, splitAt(GROUP_LEN), prop('value'),
						() => 'general'
					)
					/Users/mertnuhoglu/Dropbox/public/img/ss-202.png
			2-Ramda By Example, Lesson 2 - React Components & JSX-4kVM5oBrCMg.mp4

  next
    rxjs book
      https://github.com/xgrommx/rx-book/
    https://github.com/matthiasn/talk-transcripts/blob/master/Hickey_Rich/SimpleMadeEasy.md
    http://blog.scottlogic.com/2011/05/10/converting-between-jquery-deferred-and-rx-observable.html
    https://medium.freecodecamp.com/functional-programming-in-js-with-practical-examples-part-1-87c2b0dbc276#.p141hc5yx
    https://www.romanzolotarev.com/pagination/
    https://glebbahmutov.com/blog/journey-from-procedural-to-reactive-javascript-with-stops/
    https://github.com/staltz/xstream
    http://nick.balestra.ch/2016/Understanding-the-observable-type/
    https://github.com/snabbdom/snabbdom
    https://github.com/typestyle/typestyle
    https://github.com/staltz/cycle-onionify
    https://css-tricks.com/animated-intro-rxjs/
    https://gist.github.com/staltz/868e7e9bc2a7b8c1f754
    http://thecodebarbarian.com/rest-apis-with-observables.html
    https://glebbahmutov.com/blog/node-server-with-rx-and-cycle/
    https://gist.github.com/srdjan/1d10cbd42a2d695f696dee6b47fdc5e0
    André Staltz (@andrestaltz) - You will learn RxJS at ng-europe 2016-uQ1zhJHclvs.mp4
    https://github.com/hemanth/functional-programming-jargon
    http://docs.folktalejs.org/en/latest/
    https://github.com/getify/You-Dont-Know-JS
    https://james-forbes.com/?/posts/the-perfect-api
    https://github.com/pelotom/burrido#an-example-using-rxjs
    http://buzzdecafe.github.io/content/code/2014/05/16/introducing-ramda
    GOTO 2016 • The Return of Stream I_O • Andre 'Staltz' Medeiros-Tkjg179M-Nc.mp4 <url:file:///~/Dropbox/mynotes/content/articles/articles_fp.md#r=g_10116>
    https://github.com/fantasyland/fantasy-land
    https://medium.com/@drboolean/divide-and-conquer-with-algebraic-structures-14070106fb4#.npns0wivh
    https://jaysoo.ca/2016/01/13/functional-programming-little-ideas/
    http://fr.umio.us/why-ramda/
    https://medium.com/@drboolean/lenses-with-immutable-js-9bda85674780#.spguyj4dw
    https://medium.freecodecamp.com/before-you-bury-yourself-in-packages-learn-the-node-js-runtime-itself-f9031fbd8b69#.au2e7zp5h
    https://edgecoders.com/function-scopes-and-block-scopes-in-javascript-25bbd7f293d7#.vwa3ohhtd
    https://edgecoders.com/javascript-interview-questions-javascript-is-a-prototypal-language-what-do-i-mean-by-this-76937a9aa42a#.s1c50771g
    https://medium.com/@yelouafi/an-almost-functional-approach-to-dynamic-state-b8fc1b15b75c#.e4iywl6rf
    https://hackernoon.com/from-callback-to-future-functor-monad-6c86d9c16cb5#.dvneecffg
    https://medium.com/@drboolean/monoidal-contravariant-functors-are-actually-useful-1032211045c4#.4w0nw9gdm
    https://github.com/google/guava/wiki
    http://blog.ploeh.dk/2017/02/02/dependency-rejection/
    https://martinfowler.com/articles/201701-event-driven.html
    http://wiki.c2.com/?ThereAreExactlyThreeParadigms
    https://know-it-all.io
    https://twitter.com/mikaelbrevik/status/816346741561851904
    https://github.com/wmaurer/cyclejs-fractals 
    Monads, Monoids and Composition w/ Functional JavaScript
      https://www.youtube.com/watch?v=ZQSU4geXAxM
    bartoszmilewski
    [Paul_Chiusano,_Rúnar_Bjarnason]_Functional_Progr(Book4You).pdf
    [Nilanjan_Raychaudhuri]_Scala_in_Action(Book4You).pdf
    https://glebbahmutov.com/blog/monads/
    https://www.schoolofhaskell.com/school/to-infinity-and-beyond/pick-of-the-week/sum-types
    https://robotlolita.me/2013/04/27/the-hikikomoris-guide-to-javascript.html 
    http://docs.folktalejs.org/en/latest/book/index.html#examples
    https://github.com/fantasyland/fantasy-land
    http://raganwald.com/2016/12/27/recursive-data-structures.html
    https://pbs.twimg.com/media/CydL5EYUsAAI-61.jpg:large
    https://github.com/dmitriz/functional-examples/tree/master/examples
    https://medium.com/@benlesh/on-the-subject-of-subjects-in-rxjs-2b08b7198b93#.m3a3pdgkz
    Composable application architecture with reasonably priced monads-M258zVn4m2M.mp4
    https://www.liveedu.tv/evilsoft/lNpLm-javascript-profunctors/Wo1pE-late-nite-data-structures-pair/
    Ladder of FP
      http://lambdaconf.us/downloads/documents/lambdaconf_slfp.pdf
    http://argumatronic.com/noobs.html
    http://ivanjov.com/working-with-http-streams-with-cycle-js/
    https://t.co/VGqvFD6PJv
    https://twitter.com/mikaelbrevik/status/809144392988717056
    https://medium.com/@mikaelbrevik/javascript-proxy-for-functions-as-object-property-accessors-ac623dd9bb39#.5x2mzymsl
      https://twitter.com/mikaelbrevik/status/819969509876318208
      https://twitter.com/mikaelbrevik/status/817492111230717952
      https://twitter.com/mikaelbrevik/status/817101039497256961
      https://github.com/mikaelbr/proxy-fun
    algebraic style composition for functional UIs
      https://github.com/jongold/st
    immutability_changes_everything.pdf
      http://cidrdb.org/cidr2015/Papers/CIDR15_Paper16.pdf
    Bitcoin: A Peer-to-Peer Electronic Cash System
      https://bitcoin.org/bitcoin.pdf
    http://www.haskellforall.com/2016/04/data-is-code.html
    http://www.haskellforall.com/2016/02/from-mathematics-to-map-reduce.html
    http://www.haskellforall.com/2015/10/basic-haskell-examples.html
    http://www.haskellforall.com/2015/10/polymorphism-for-dummies.html

## Brian Lonsdorf

### Brian Lonsdorf - Oh Composable World!-SfWR3dKnFIo.mp4

``` js
const ProfileLink = user =>
  <a href={`/users/${u.id}`}>{u.name}</a>

const ProfileLink = user =>
  a({href: `/users/${user.id}`}, user.name)

const Comp = g =>
({
  fold: g,
  contramap: f =>
    Comp(x => g(f(x)))
})

const Heading = str => h1(`Now Viewing ${str}`);
const Title = Comp(Heading).contramap(s => s.pageName);

Title.fold({ 
  pageName: 'Home',
  currentUser: { id: 2, name: 'Chris' }
});
// <h1>
``` 

`contramap` is the opposite of `map`

`contramap` prepends its input function `f` to the existing function `g` of
`Comp`.

How to do this with usual `map`?

``` js
const s = { pageName: 'Home', currentUser: { id: 2, name: 'Chris' } }
const f = s => s.pageName
const g = Heading

Box(s)
  .map(g)
  .fold(f)

Comp(f)
  .contramap(g)
  .fold(s)
``` 

`concat` function concatenates two components:

``` js
const Comp = g =>
({
  fold: g,
  contramap: f => Comp(x => g(f(x))),
  concat: other => 
    Comp(x => div( g(x) + other.fold(x) ))
})
``` 

``` js
const Link = Comp(ProfileLink).contramap(s => s.currentUser)
const App = Heading.concat(Link)
App.fold(s)
// <div>
//   <h1>Now viewing Home</h1>
//   <a href="/users/22">Chris</a>
// </div>
``` 

`Reducer`

``` js
const Reducer = g =>
({
  fold: g,
  contramap: f => Reducer((acc, x) => g(acc, f(x))),
  map: f => 
    Reducer((acc, x) => f(g(acc, x)))
})
const r = Reducer((acc, x) => acc.concat(x))
  .contramap(x => `The number is ${x}`)
  .map(x => x + '! ')
[1,2,3].reduce(r.fold, '')
// The number is 1! The number is 2! The number is 3!
``` 

