	<url:file:///~/Dropbox/mynotes/content/articles/articles_fp_ct.md>

## books

	Category Theory for the Sciences 
		<url:file:///~/Dropbox/mynotes/content/books/fp/book_category_theory_for_sciences.md>
	Conceptual Mathematics- A First Introduction to Categories.pdf
		<url:file:///~/Dropbox/mynotes/content/books/fp/conceptual_mathematics_a_first_introduction_to_categories_lawvere.md>

## articles

	socratica - abstract algebra series
		What is Abstract Algebra? (Modern Algebra)
			it doesn't look like normal algebra
			history
				1700s
				people know 1,2,3,4th degree equations
				what about higher degrees?
					galois: answered the question
						to do so, he defined groups
				gauss: discovered modular arithmetics
					similar to groups
				euclidean geometry was challenged
					groups were found to be useful to study geometry
				generalized groups
				would this approach work elsewhere?
					new abstract objects were discovered:
						rings, fields, vector spaces, modules
				it took years to find correct definitions
					too specific → not very useful
					too general → kind of boring
					eventually right definitions were discovered
			usefulness continously grows
				not in maths only
			abstract algebra also called
				modern algebra
				algebra (math pros)
		Abstract Algebra: The definition of a Group - Socratica
			https://www.youtube.com/watch?v=QudbrUcVPxk
			generality makes groups powerful
			start with arithmetics
				subtraction is addition in disguise:
					7 - 4 = 7 + (-4)
				division is multiplication:
					9 / 5 = 9 * (1/5)
				so there are 2 operations actually:
					addition
					multiplication
					inverses: negatives, reciprocals
					when there is inverse, you have identity:
						zero, one
				we are ready for group definition
			group definition
				set of elements (more abstract than numbers)
				operation: *
				closed under operation (result of each operation is an element of the set as well)
				inverse: each element has an inverse
				identity exists
				associative: without it you cannot solve simple operations
		Abstract Algebra: Motivation for the definition of a group
			https://www.youtube.com/watch?v=yHq_yzYZV6U
			why these properties?
				ex: define a group for integers under addition
					if we are going to generalize algebra (abstract it)
					let's solve equations:
						x + 3 = 5
							add negatives (inverses)
						(x+3) + (-3) = 5 + (-3)
						(x+3) + (-3) = 2
							closed under +
						x + (3 + (-3)) = 2
							associativity (regrouped the terms)
						x = 2
							(3 + (-3)) = 0
							identity element
					simplest definition that allows us to solve equations:
						inverse
						closed under +
						identity
						associativity
		Abstract Algebra: Group or Not Group? (Integer edition)
			check if
				closed operation
				identity element
				inverses
				associativity
			integers under +
			even integers under +
			odd integers under + (not group)
		Homomorphisms (Abstract Algebra) - Socratica
			https://www.youtube.com/watch?v=cYzp5IWqCsg&t=173s
			homomorphism: way to compare two groups for structural similarities
				function between two groups
					which preserves group structures
			ex:
				G, H: groups
				G *
				H +
				x,y ∈ G
				x*y = z
				function: maps G to H
					f: G → H
					x ↣ f(x)
					y ↣ f(y)
					z ↣ f(z)
				purpose: find a structural similarity between two groups
					x * y = z
					⇒  
					f(x) + f(y) = f(z)
					⇒ 
					f(x) + f(y) = f(x*y)
						definition of homomorphism
						a way to compare two groups
			ex:
				G = R under +
					abelian, identity = 0
				H = R+ under * 
					abelian, identity = 1
				here is homomorphism:
					define: f: G → H
						x ↣ e^x
					to make sure:
						f(x+y) = f(x) * f(y)
						e^(x+y) = e^x * e^y
			ex:
	Turning math inside-out - John D. Cook
		https://www.johndcook.com/blog/2016/10/13/turning-math-inside-out/
		mathematical objects are usually defined internally 
			ex:
				cartesian product P of A and B
					defined to be set of all ordered pairs (a,b) where a ∈ A and b ∈ B
				definition of P depends on elements of A and B but not on any other sets
		category theory turns this inside-out
			operations are not defined in terms of elements of objects
			it defines things by how they act, not their inner workings
			definition of the product depends on all objects in that category:
				contains the phrase: "such that for any other object X ..."
		benefit:
			you can talk about everything that acts like a product
				whether it's
					products of sets, fields, groups, 
				because your definition doesn't depend on details unique to one category
	math3ma - category theory series
		What is Category Theory Anyway?
			http://www.math3ma.com/mathema/2017/1/17/what-is-category-theory-anyway
			category theory:
				different than other branches
				common gene that unites them
				bridges between realms
			transporting the problem to a different realm
				you can see the problem in a different light
				each has objects
					set theory has sets
					group theory has groups
					topology has topological spaces
				that can relate to each other in sensible ways (composition and associativity)
					/Users/mertnuhoglu/Dropbox/public/img/ss-39.png
					sets relate via functions
					groups relate via homomorphisms
					topological spaces relate via continuous functions
			category is
				any collection of objects
				that can relate to each other via morphisms
				in sensible ways, like composition and associativity
			this is a template for all of mathematics
				/Users/mertnuhoglu/Dropbox/public/img/ss-40.png
			it is all about relationships
				ct strips away a lot of detail
					not concerned 
						with the elements in your set
						whether group is solvable
					how can it be useful?
				attention is diverted from actual objects to the relationships between them (morphisms)
					relationships between categories = functors
					relationships between those relationships = natural transformations
					/Users/mertnuhoglu/Dropbox/public/img/ss-41.png
			why category theory?
				if you love learning about connections between seemingly unrelated things
			references
				If you'd like more motivation, I highly recommend Barry Mazur's wonderful article When is one thing equal to some other thing? 
				And for a friendly, gentle introduction to categories, you may enjoy Conceptual Mathematics: A First Introduction to Categories by William Lawvere and Stephen Schanuel.
				Here is an article by John D. Cook in which he talks about the (usual, Cartesian) product from a categorical perspective--notice the emphasis on relationships!
		What is a Category? Definition and Examples
			http://www.math3ma.com/mathema/2017/1/23/what-is-a-category
			what is a category?
				category C consists of some data that satisfy certain properties
					data
						a class of objects: x,y,z,...
						a set of morphisms between pairs of objects:
							[x] f → [y]
								f is a morphism from x to y
							hom_C(x,y)
								set of all such morphisms
						a composition rule (transitivity)
							given
								[x] f → [y]
								[y] g → [z]
							then
								[x] g·f → [z]
					properties
						each object x has an identity morphism (reflexivity)
							[x] id_x → [x]
							which satisfies
								id_y · f = f = f · id_x ∀ [x] f → [y]
						composition is associative (associativity)
							(h·g)·f = h·(g·f) whenever
							[x] f → [y] g → [z] h → [w]
					/Users/mertnuhoglu/Dropbox/public/img/ss-43.png
			proving whether something is a category
				ex: a poset
					poset: ordered sets
					every poset P forms a category
					objects: elements of P
					morphism: x → y whenever x ≤ y
					composition holds because of transitivity:
						if x ≤ y and y ≤ z ⇒  x ≤ z
							∴ [x] g·f → [z]
					identity morhrism property holds because of reflexivity:
						x ≤ x is always true
							∴ [x] id_x → [x]
					associativity 
				ex: a group
					group G can be viewed as a category: called BG
						with a single object: ●
						morphism:
							[●] g → [●] ∀ g ∈ G
						composition holds since G is closed under the group operation
				ex: 
		What is a Functor?
			http://www.math3ma.com/mathema/2017/1/31/what-is-a-functor-part-1
			functor: is like an arrow/morphism between two categories
				...every sufficiently good analogy is yearning to become a functor. - John Baez
			what is a functor?
				F: C → D
					C,D: categories
					F: functor
				the data
					an object F(x) ∈ D ∀ x ∈ C
					a morphism [F(x)] F(f) → [F(y)] in D ∀ morphism [x] f → [y] in C
				properties
					F respects composition
						ie. F(g · f) = F(g) · F(f) in D when g and f are composable morphisms in C
					F sends identities to identities
						ie. F(id_x) = id_{F(x)}
			ex: a functor between groups
	When is one thing equal to some other thing? - Barry Mazur
		http://abel.math.harvard.edu/~mazur/preprints/when_is_one.pdf
		the awkwardness of equality
			equality is a slippery notion
				because
					objects don't tell us directly when two of them are to be considered equal
			the issue is old:
				general question of abstraction
					as separating what we want from what we are presented with
				aristotle:
					if we want to think about whiteness
					we must separate it from white horse, white house, ...
			category theory offers its own formulation of equivalence as opposed to equality
				to determine a mathematical object up to canonical isomorphism
			formal systems remain our lingua franca
				axiomatic systems as standard
		category theory as balancng act rather than foundations
			two great modern formulas for packaging entire mathematical theories
				formal system
					david hilbert
					@other
						calculus: Calculus (plural calculi) is also used for naming some methods of calculation or theories of computation, such as propositional calculus, calculus of variations, lambda calculus, and process calculus.
						A formal system or logical calculus is any well-defined system of abstract thought based on the model of mathematics. A formal system need not be mathematical as such; for example, Spinoza's Ethics imitates the form of Euclid's Elements.[clarification needed]. Spinoza employed Euclidiean elements such as "axioms" or "primitive truths", rules of inferences etc. so that a calculus can be built using these.
				category theory
					samuel eilenberg, saunders maclane
				have different intents
			formal system
				contains
					all mechanics and vocabulary necessary to discuss proofs
			category
				quite sparse in its vocabulary
				most succinct 
				captures the essence of a theory:
					objects of the theory
					allowable transformations between them
					a composition law: how to compose two transformations 
				cannot provide us anything like "foundations"
					it plays a fine balancing role
		example: the category of sets
			category of sets
				is proto-type example
			set theory consists of
				repertoire of elements: a,b,...
				repertoire of sets: X,Y...
				relation of containment
					x ∈ X
				mappings f: X → Y
					each mapping f: ∀ x ∈ X a unique f(x) ∈ Y
				guarantee that 
					if f: X → Y and g: Y → Z
					then the composition: g · f: X → Z exists
					and (g · f)(x) = g(f(x))
				redundant:
					identity mapping 
						1_X: X → X
					composition rule is associative
						(h·g)·f = h·(g·f)
			bare set theory:
				stripped down even further by forgetting elements and containment relations
				what is left?
					objects of the theory (repertoire (class) of sets)
					set of mappings
					composition rule
						respects identity + associativity
		class as a library with strict rules for taking out books
			class:
				collection of objects
				with some restrictions on which subcollections can we deem sets and thereby operate on
		category 
			a category C is given by the following:
				a class of things called the objects of C 
					denoted: Ob(C)
				given any two objects X,Y of C
					a set denoted Mor_C(X,Y)
						as the set of transformations from X to Y
						referred as morphisms from X to Y
						denoted: f: X → Y
				given any three objects X,Y,Z of C and morphisms f:X → Y and g:Y → Z
					a law that tells us how to compose these morphisms to get a morphism:
						g·f: X → Z
					the rule that associates to such a pair (f,g) the composition:
						(f,g) ↣ g·f
							sort of multiplication law
				requires also:
					identity element 1_X in Mor_C(X,X) wrt composition law exists
						∀ f:X → Y we have f·1_X = f
						∀ e:V → X we have 1_X·e = 3
					composition law is associative
			concept of category is multi purpose
				sets: sets, mappings of sets
				topological spaces: eponymous ones, continous maps
				algebraic categories: 
					groups, homomorphisms
					rings, ring homomorphisms
				every branch package their entities in this format
				ct: more descriptive 
					a template for any theory
					theory should have nouns and verbs
						objects, morphisms
						and a notion of composition for morphisms
		equality versus isomorphism
			isomorphism is replacement for equality
			isomorphism:
				f: A → B between A,B of category C
				is a morphism that can be undone
					ie. there is another morphism:
						g: B → A as inverse of f
					composition gf: A → A is identity morphism 1_A
						fg: B → B is identity morphism 1_B
				if X = Y in C is irrelevant
					instead ask: a specific isomorphism from X to Y
			note: specific isomorphism not simply existence of isomorphisms 
		an example of categorical vocabulary: initial obejcts
		defining the natural numbers an initial object
		light, shadow, dark
		representing one theory in another
			a mapping of entire category C to category D
			such a mapping should
				send basic features (objects, morphisms) of C to corresponding features of D
				relate composition law of morphisms in C to the corresponding law for morphisms of D
			such a mapping: functor from C to D
			denoted: --> 
		mapping one functor to another
			morphism of functors
				given two functors:
					F,G: C --> D
				a morphism of functors:
					μ: F → G
					for each object U of C, a morphism:
						μ_U: F(U) → G(U)
					in category D which respects the structures involved
				ex:
					for any functor: F: C --> D
					we have identity morphism of F to itself
						[F] 1_F → [F]
			isomorphism of functors
				isomorphism of F to G
					F: C-->D
					G: C-->D
				is a morphism of functors
					μ: F → G
				for which there is a morphism of functors going the other way
					ν: G → F
				st
					ν · μ: F → F
					μ · ν: G → G
					are equal to respective identity morphisms of functors
		an object "as" a functor from the theory in which it lives to set theory
			given an object X ∈ category C
				we shall define a specific functor F_X
				that encodes the essence of the object X
			F_X maps C to S of sets
			definition:
				functor F_X assigns Y ∈ C 
					F_X(Y) := Mor_C(X,Y)
	Intuition on group homomorphisms - stackexchange
		http://math.stackexchange.com/questions/242348/intuition-on-group-homomorphisms
			if h:A→B isomorphism
				then, B is just A "in disguise"
				they are equivalent
				ie. isomorphisms are the maps that preserve the structure exactly
			homomorphisms preserve some of the structure
				ex: ℤ  to ℤ2 
	Why Category Theory Matters - Robb Seaton
		http://rs.io/why-category-theory-matters/
		—John Baez
			I hope most mathematicians continue to fear and despise category theory, so I can continue to maintain a certain advantage over them.
		why does ct matter?
		what ct is about?
			study of mathematical structures
				study of
					things
					mappings (translations) between them
			throws out all specific properties of objects 
			focuses only on their translations
			can say something about anything
		benefits of ct over set theory
			too complicated
			artificial: axioms are product of search for a paradox-free foundation
			ct pros:
				connect disparate fields
					rosetta stone
					physics, topology, logic, computation
					benefit of the abstraction
						by throwing away all the details
						object's structure reveals itself
					ex:
						analytic geometry:
							geometry can be translated into cartesian coordinates
							[P] f → [ℝ^2]
		applications of ct
			haskell
			spreadsheet application 
			study grammar
			neural networks
			systems of interacting agents
			emergent systems in biology
			"it clears away trivial problems. it puts hard problems in clear relief."
	Yoneda Lemma - ncatlab
		opposite category
			https://ncatlab.org/nlab/show/opposite+category
			for a category C:
				C^{op}: category obtained by reversing the direction of all its morphisms
		presheaf
			https://ncatlab.org/nlab/show/presheaf
			prefsheaf on a category C is a functor
				F: C^{op} → Set
		contravariant functors
			like a functor but reverses directions of the morphisms
		groupoid
			small groupoid: small category where all morphisms are isomorphisms
		small category
			a category that has 
				small set of objects
				small set of morphisms
				ie. internal category in the category Seto		small set
			some foundations: a set but not a proper class
			other foundations
		proper class
			a set that is too big to be a set
		yoneda lemma
			set of morphisms from a representable presheaf h_C into an arbitrary presheaf F
	Mathematics Primer - Jeremy Kun
		Why there is no Hitchhiker’s Guide to Mathematics for Programmers
			https://jeremykun.com/2013/02/08/why-there-is-no-hitchhikers-guide-to-mathematics-for-programmers/
			think of someone say:
				programming is useful
				i want to learn it
				but i don't want to write code
				code execution is abhorring
			think of some programmer say:
				math is useful
				i want to learn
				but won't write any original proofs
				i just want to use theorems others have proved
			math can be hard for programmers why?
			travelling far and wide
				working programmer doesn't have time to learn from bottom to top
			what is it realy, that people have such a hard time learning?
				most complaints:
					notation and abstraction
					main obstacle: familiarity with basic methods of proof
				proof methods:
					direct implication
					proof by contradiction
					contrapositive
					induction
					they are fundamental
				most math is built up by chaining absolutely trivial statements
					if it is not completely trivial, then it's probably not true
			mathematical syntax
		Primers
			https://jeremykun.com/primers/
			Methods of Proof
		Methods of Proof - Direct Implication
			https://jeremykun.com/2013/02/16/methods-of-proof-direct-implication/
			Recalling the basic definitions of sets
				ways to define sets
					list its elements
						X = {1,4,9,16,225}
					set builder notation (list comprehension)
						X = {n^2: n = 1...5}
			Subsets and Direct Implication
			ex:
				definition:
					f is a function in a programming language
					f is injective if diifferent inputs to f produce different outputs
				proposition:
					if f,g are injective functions which compose
					then g · f defined by g·f(x) = g(f(x)) is injective
		Methods of Proof — Contrapositive
			https://jeremykun.com/2013/02/22/methods-of-proof-contrapositive/
			functions as sets
				how to define functions mathematically?
				desired properties:
					every input must have an output
					every input can only correspond to one output (deterministic)
				opt1: subsets of size two
					{{x,y}:x∈A, y ∈ B}
						y = f(x) means {x,y} is a member of the function
					fails: when A = B because input and output cannot be distinuished in sets
				opt2: ordered tuples of size two
					(a,b)
					one can define ordered tuples in terms of sets:
						(a,b) = {a,{a,b}}
						{(x,y):x∈A, y ∈ B}
							y = f(x) means (x,y) is a member of the function
						note: input and output terms are just an interpretation
						common names:
							maps: functions
							map: special kind of function
								continuous function (homomorphism)
			injections
				definition: f: A → B is an injection if when a ≠  a' then f(a) ≠  f(a')
				ex: inclusion function
					if A ⊂ B ⇒  i: A → B defined by i(a) = a
						i is canonical function representing subset relationship
				ex: multiplication by two
					f: ℕ → ℕ 
					f(x) = 2x
				injectivity:
					we place set A inside another set B without changing nature of A
						as if different names for elements of A
					bad habit of implicitly identifying a set with its image
						if f: A → B is injective function
							one can view A as the same things as f(A) ⊂ B
						ex:
							suppose X is the set of all colors in a computer
							Y: set of finite hexa numbers
							injective map: X → Y sending each color to its 6 digit hex representation
							lazy mathematician says:
								we might say X ⊂ Y
						precise way to formulate this claim:
							if g,g': X → Y
							then ∀ h: Y → Y 
								st. h·g is precisely the map g'
			proof by contrapositive
				/Users/mertnuhoglu/Dropbox/public/img/ss-47.png
				ex:
					if X does not have property A, then Y does not have property B
					ex: to prove f: X → Y is injective we must prove:
						if x is not equal to y, then f(x) is not equal to f(y)
					we can write "p implies q" as "not q implies not p"
						if Y has property B then X has property A
			Examples and Exercises
				ex: show that f(x) = 7x - 4 is injective
					contrapositively prove: if f(x)
					we need to show:
						if f(a) = f(a') ⇒  a = a'
					by algebra:
						f(a) = f(a')
						7a - 4 = 7a' - 4
						7a = 7a'
						a = a'
				this example is importante
					because if we tried to prove it directly
					we couldn't because ≠  doesn't work the same way
					ex: if a ≠  b and b ≠ c, this doesn't imply a ≠ c
				ex: show that composition of two injective functions is injective
				ex: if A,B are finite sets and f:A → B is an injection, then A has at most as many elements as B
		Methods of Proof — Contradiction
			Impossibility and an Example Proof by Contradiction
				surprising: main line of attack to prove something is impossible
					is to assume it is possible
				ex: n people. each friendship is mutual.
					show that at least two people have same number of friends
			A Reprise on Truth Tables, and More Examples
				if p and !q is impossible, then p → q is T
			A Few More Words on Functions and Sets
				surjection:
					f:X → Y is surjective
					for all y ∈ Y there exists x ∈ X: f(x) = y
				bijection:
					if f:X → Y is surjective and injective
				if f is bijective
					then X and Y have the same number of elements
				if X and Y have same size, one can make a bijection between them
				definition: cardinality
					X,Y have same cardinality if there exists a bijection f:X → Y
					if there is a bijection f: ℕ → X, then X is countably infinite cardinality
					if no such bijection exists and X is not finite, then X is uncountably infinite
				notation:
					↣ maps to arrow: define a function without giving it a name (like lambda)
			Diagonalization, and a Non-Trivial Theorem
				theorem: there is no bijection between set of real numbers and set of natural numbers
					non-trivial theorem
						trivial: doesn't mean easy
							means: a result follows naturally
							it comes from applying the definitions and basic methods of proof
					proof by diagonalization argument
		Methods of Proof — Induction
			Proving Statements About All Natural Numbers
			Formal Nonsense
			Variations on Induction, and Connections to Dynamic Programming
	Programmers go bananas
		https://jaortega.wordpress.com/2006/03/17/programmers-go-bananas/
		my learning experience of programming
			started with C, C++ and complex data structures
			the more complex, the more i learned
			it changed when reading SICP and FP
			most vivid: gradual discovery of the power of simplicity
			scip: everything was a list
				power comes from recursion
			relationship between categories and recursive programming: Erik Meijer FP with Bananas
			isomorphism
				two systems are similar 
				searching for commonality = looking for concepts or abstractions
					which is what mathematics and programming is about
					commonality -> unification
				definition of category contains: isomorphic objects
					a -> b and b -> a
					composing both: identity 
					we say: a and b are isomorphic
						you can transform one into the other and back
					generic way of equality
					not only objects can be transformed
						categories as well: by functor mapping
						to reveal the common structure between two disjoint categories
					functor
						consists of two functions
							1: maps each object between C1 and C2
							2: maps each arrow between C1 and C2
							these functions must preserve arrow composition
						definition
							/Users/mertnuhoglu/Dropbox/public/img/ss-48.png
							C, C' categories with O, O' and A, A'
							F: C -> C' consists of two functions Fo,Fa:
								Fo: O -> O'
									Fo(a) ∈ O' for every a ∈ O
								Fa: A -> A'
									Fa(f) ∈ A' for every f ∈ A
								st.  
									1. if f: a -> b in C
										then Fa(f): Fo(a) -> Fo(b) in C'
									2. Fa(fg) = Fa(f) Fa(g)
									3. F must preserve identities
					category isomorphism
						F is from C to C'
						if a second functor exists from C' to C
					meta categories: recursiveness
						functors let us go meta
							define a category whose objects are categories and arrows are functors
			Categories and programming languages
				haskell:
					floor:: Real -> Int
				if we identify
					arrows with categorical ones
					language types can be objects of a category
				ex: haskell
					id:: a -> a
					id x = x
					f:: b -> c
					g:: a -> b
					fg:: a -> b -> c
					fg = f . g
				associativity of arrow composition 
					is ensured by referential transparency
						ie. no side effects
						the order doesn't matter with side effect free functions
				any statically typed language can be modelled as a category
					objects = types
					arrows = functions of arity one
				ex: Scheme
					(define (compose f g) (lambda (x) (f (g x))))
				functions with multiple arguments: by currying
					ex: Scheme
						(define (add x y) (+ x y))
						(define (add x) (lambda (y) (+ x y)))
						((add 3) 4)
					ex: Haskell for currying
						add x y = x + y
						add:: Int -> (Int -> Int)
				functions with no args:
					use Unit or 1 
						() in Haskell
						void in C
					constants (eg True, 4.2):
						True is an 
							1 -> Boolean
					Unit type is terminal object in CT 
				using the tools to explore language constructs
					ex: functors let us explore lists and functions on them
						language lets us to create
						its category contains objects (types) of "list of" kind
							ex: [Int] IBoolean]
						we can construct a new sub-category CL
							by considering list types as its objects and functions as its arrows
						notation:
							Fo(a) = [a]
							Fa(f: a -> b) = f': [a] -> [b]
						Fa: map() 
						lifting: going from f to f'
							an operator that lifts a function to a new one in Cl:
								(define (lift f) (lambda (lst) (map f lst)))
							it needs to respect composition:
								(lift (compose f g)) = (compose (lift f) (lift g))
							ex: does this property hold?
								consider next(): returns successor integer
								(lift next) maps a list of integers to a list of their successors
								(lift prev) similar
								(compose next prev) is identity
								∴ (lift (compose next prev)) is identity too
					only condition for a functor: identities are mapped to identities and composition is preserved
			definition of 
	Category Theory series - Jeremy Kun
		Categories, What’s the Point?
			https://jeremykun.com/2013/04/16/categories-whats-the-point/
			ct concepts and their benefits seem magical
				ex: monad to simulate mutation of immutable data
			ct as modern language
				silly to program in binary
					we abstracted common constructions
				revolution in mathematics:
					integration of ct
					enough mathematicians agreed ct is useful
			ct as an organizing principle
				language designers and consumers have different tastes
					ex: multiple assignment in single statement 
						a,b=7,False
						syntactic sugar
						working programmer: convenient
				ct researchers vs users
					until Grothendieck: non-ct users didn't see the value of ct
				benefits	
					way to organize information
						particularly concept of universal property
						most important math structures can be phrased in terms of universal properties
						ex:
							why are polynomials so ubiquitous
							answer: they satisfy a universal property which makes them canonical extensions of certain computations
						more and more definitions are phrased in terms of universal constructions
							there are onlny two universal properties: product and sum
						viewing the problem abstractly allows 
							one to prove all these theorems simultaneously
			ct as a tool to gain more knowledge
				helps one to make connections between structures and computation
					right solution falls into your lap
			what we'll do with categories
		Introducing Categories
			https://jeremykun.com/2013/04/24/introducing-categories/
			definition of a category
		Categories as Types
			https://jeremykun.com/2013/05/04/categories-as-types/
			everything in category needs to be a type in ML
				object and morphism is a type
				composition function:
					compose: 'arrow * 'arrow -> 'arrow
				morphisms:
					source: 'arrow -> 'object 
					target: 'arrow -> 'object 
				identity morphism:
					identity: 'object -> 'arrow
				so in ML we have the datatype:
					datatype ('object, 'arrow)Category =
						category of ('arrow -> 'object) *
							('arrow -> 'object) *
							('object -> 'arrow) *
							('arrow * 'arrow -> 'arrow)
			Categories as Values
				ML doesn't support homogeneous lists natively
					we will have category of Set
					call it Set_a
						category whose objects are finite sets
						whose elements have type a
						whose morphisms are set-functions
				define an object in this category as the ML type:
					'a Set
				arrow as a datatype
					datatype 'a SetMap = setMap of ('a Set) * ('a -> 'a) * ('a Set)
		Universal Properties
			https://jeremykun.com/2013/05/24/universal-properties/
			universal properties is the most important concept in category theory
			there two universal properties:
				initial
				final
			definition: initial final
				initial: 
					an object A in category C
					if for every object B 
					there is a unique morphism: A -> B
				final:
					an object Z in category C
					if for every object B 
					there is a unique morphism: B -> Z
				if an object satisfies either of these it is called universal
				if both: zero object
			existence of a unique morphism
				= relevant Hom set is singleton
			ex: in C_Set
				single element set is final, but not initial
				all singleton sets are isomorphic in C_Set
				empty set is initial
			proposition:
				if A, A' are initial => A ≅ A'
				if Z, Z' are final => Z ≅ Z'
		The Universal Properties of Map, Fold, and Filter
			https://jeremykun.com/2013/09/30/the-universal-properties-of-map-fold-and-filter/
			why do people like fp?
				more elegant
				what is it?
					higher-order functions: map, fold, filter
					unifying framework for reasoning about programs
				how unifying are they?
					this post: characterizations of these functions in terms of universal properties
					fold is the most universal of the three
					its generalization gives a characterizatino of compound data types
				what is universal property?
					a precise mathematical statement that it is either an initial or final object in some category
					or the unique morphism determined by such
			Map, Filter, and Fold
				map :: [A] → [B]
				definition in ML:
					fun map( _ , nil) = nil
						| map( f, (head::tail)) = f(head) :: map(f, tail)
				filter :: [A] → [A]
					fun filter( _ , nil)
				fold :: [A] → B
					fun fold( _, v, nil) = v
						| fold(f, v, (head::tail)) = f(head, fold(f, v, tail))
				we can define map and filter in terms of fold:
					fun map(f, L) = fold((fn x, xs ⇒  (f(x):xs)), [], L)
			Free Monoids and Map
				monoid:
					a set M 
					an associative binary operation denoted by multiplication
					an identity element 
				monoid homomorphism between two monoids M, N:
					a function f: M → N
					st f(xy) = f(x) f(y)
				monoids with monoid homomorphisms form a category
				simple monoids are everywhere:
					Strings with concatenation
						empty string: identity
					lists with list concatenation
						identity: empty list
						monoid homomorphism: length function
							homomorphism because
								length of a concatenation of two lists is sum of their lengths
					integers with addition
						zero: identity
						but list and string are different
						for numbers: n + (-n) = 0
							for lists and strings to get empty list: both inputs should be empty
							this property: free monad
				definition:
					C category
					given set A
					free object over A: denoted F(A)
						an object of C which is universal wrt set-maps A → B for any object B in C
		Racket or How I Learned to Love Functional Programming
			https://jeremykun.com/2011/10/02/a-taste-of-racket/
			Lists
				list = empty
					| (x list)
				(): denotes a pair
					first thing in the pair: x
					second: another list
				constructed with: cons
				(cons 7 (cons 8 empty))
					paradigm: linked list
				shorthand for list construction:
					(list 8 9)
				shorthand using "quote" macro:
					'(1 2 3 "hello" my-symbol!)
				access to elements: first, rest
				(first my-list)
				(rest my-list)
			Double List and SubList
				ex: take a list and double each number
					(define (double-list my-list)
						(if (empty? my-list) empty
							(cons (* 2 (first my-list))
								(double-list (rest my-list)))))
					testing:
						(double-list empty)
						(double-list '(1 2 3))
					generalize it:
						(define (muultiply-list my-list n)
							(if (empty? my-list) empty
								(cons (* n (first my-list))
									(multiply-list (rest my-list)))))
					subtract 7 from each element
						(define (sub-list my-list n)
							(if (empty? my-list) empty
								(cons (- n (first my-list))
									(sub-list (rest my-list)))))
					generalize both: Map
			Map
				(define (map f my-list)
					(if (empty? my-list) empty
						(cons (f (first my-list))
							(map f (rest my-list)))))
				redefine old functios:
					(define (double x) (* 2 x))
					(define (double-list2 my-list) (map double my-list))
				using lambda expressions
					(map (λ (x) (* 2 x)) my-list)
			Fold and Filter
				Fold: reduces a list to a single value
					(define (fold f val my-list) 
						(if (empty? my-list) val
							(fold f
								(f val (first my-list))
								(rest my-list))))
				summing function:
					(define (sum my-list) (fold + 0 my-list))
				multiplying function:
					(define (prod my-list) (fold * 1 my-list))
				filtering
					(define (filter select? my-list)
						(if (empty? my-list) empty
							(let ([elt (first my-list)]
								[the-rest (rest my-list)])
							(if (select? elt)
								(cons elt (filter select? the-rest))
								(filter select? the-rest)))))
					ex: filter positive numbers
						(filter (λ (x) (> x 0)) my-list)
			A Brighter, More Productive World
				ex: euler problem 67
					find max sum of paths from top to base of a triangular grid of numbers
					100 rows → 2^99 paths
	Category Theory for the Working Hacker by Philip Wadler-V10hzjgoklA.mp4
		Curry Howard isomorphism = Propositions as Types
			natural deduction ≅ lambda calculus
			this pairing is general
			Logic ≅ Programming Languages
			Gentzen's formal dedection
			Church's lambda calculus
			Howard: they are the same
				after 40 years
			now: this is obvious
			every good idea will be discovered twice
		using programming languages that is 
			invented: java, c
			discovered: haskell, lisp
		ct: secret sauce under curry howard
			underlies monad, functor etc.
			C': Category
		product
			what is it?
				a record with fields
					named: first, second
			java
				public class Product<A,B>
					A fst
					B snd
					A getFst()
					B getSnd()
			haskell type rules:
				M: term
				A: type
				Γ: all free variables and types
					environment
			ct
				f: C -> A
				g: C -> B
				fst: AxB -> A
				snd: AxB -> B
				<f,g>: C -> AxB
					any arrow you put here must be <f,g>
					this is a universality condition
				two ways to go from C to A
					<f,g>;fst = f
					<f,g>;snd = g
				why would i want to know this?
		sum
			A+B is either an A or a B
			java
				interface Sum<A,B>
		in pl where everything terminates it is easy to implement: product and sum
	Category Theory, The essence of interface-based design - Erik Meijer-JMP6gI5mLHc.mp4
		Lamport: programmers should use more math like other engineers
		why are programmers afraid of math?		
			math books
				lingo weird
				bottom up way teaching
					learn everything before applying it
					programming: reverse
						self learner programmers: they didn't learn big-o, data structures etc.
					ex: linear algebra
						matrix multiplication
							mechanics = assembly programming
							logic = fp of linear functions
		ct
			abstract nonsense
				study of mathematical theories without regard to their content
					similar to: programming to interfaces
					sound like design patterns
				curry howard isomorphism
					a type is a theorem
					program is a proof for that theorem
		what is ct?
			category = programming language
			object = type
			morphism = static function 
			category 3:
				f: A -> B
				g: B -> C
				g·f: A -> C
		category theory = interface based modelling
			what tuples are according to ct
				Let C be a programming language with types X1, X2
				they call it product
				X1xX2
					projection functions: fst, snd
				how do you get an instance of tuple?
					constructor function or factory function
					<f1,f2> or f1△ f2
				scala:
					<f1,f2>: C -> (X1,X2)
		objects?
			most objects don't represent real world objects
			even person object is something different
			methods as objects in java
				using reflection
					it is super complex
					it is cope out
					with lambdas methods are normal objects
		exponentials
			how do we represent methods as objects
				ct has exponentials for that
			Let C be a category with binary products
				therefore we started with tuples
				to have binary products, we need tuples
			Y and Z are objects of C
			exponential object Z^Y can be defined as a universal morphism from the functor -xY to Z
				what is functor?
				why this notation of exponential make sense?
					Y -> Z
					there are Z^Y possible functions
					ex: Y is Unit or void
						Z: bool
						how many functions?
							Y -> Z
								void -> true
								void -> false
								2^1
							in java: Z -> Y
								println it
								make a http request
								so methods are not functions 
								because they allow side effects that don't show up in signature
			greek
				L: language that supports tuples
				A,B types of L
				function type A -> B 
					can be defined as a factory method from functor -xA to B
					functor -xA in L maps types C to CxA and methods m to mxid
		functor
			what is a functor
			greek decoded
				L: language
				functor C[_] is a a generic type
					that associates to each type A an instantiated type C[A]
					and has a method: map(f: A->B):C[B]
						st for all cs: C[A] the following conditions hold:
							cs.map(id) = cs
							cs.map(a->f(g(a)) = cs.map(g).map(f)
							for all function f: B -> C, g: A -> B
						ie. functors preserve identity and composition
				ex:
					f goes from int to String
					then map goes from List[int] to List[String]
				ex: composition
					f: int -> String
					g: String -> bool
					map(fg) goes from List[int] to List[bool] directly
					map(f).map(g) goes from List[int] to List[String] then to List[bool]
			functor goes between two categories
				then functor goes between two programming languages
			but endofunctors work within one category
		exponentials
			commuting diagram
			translate to equations
				currying in haskell
			an instance method is a static method that takes this pointer as first argument
				so instance method is a morphism
				from tuple AxB to C
			greek decoded
				A type 
					B -> C
				together with a method 
					apply(b:B):C
				is a function type
				if 
					for any type A and method m on A
					there is a factory method
						(a:A)::m :B -> C
					st.
						(C)a::m.apply(b) = (C) a.m(b)
			this is method object (lambda)
				so exponential objects are lambdas
			a::m.apply(b) = a.m(b)
		hom-sets
			Hom(XxY, Z) ≅ Hom(X, Z^Y)
			ie. you can curry and uncurry
			Hom(A,B) is the set of all morphisms from A to B
		Monads
			from exponentials we go to monads
	Bartosz Milewski  - Truth about Types (Lambda Days 2016)-dgrucfgv2Tw.mp4
		ct is nothing but composition
		what is that thing that is being composed?
		we know why we do composable programs
		nature in quantum scale is not decomposable
		Truth
			logic: truth value
				intro rules that define values
				there is no assumption here
				truth is always true
			types: Unit type
				():()
					value is ()
					type is ()
			Category: terminal object
		Proofs
			logic: proof of proposition A
			types: type A is inhabited
				types are propositions
				Γ ├ x: A
				type A is not empty
					there is some element of type A
			category: morphism from terminal object
				1 -> A
		category theory
			objects have no structure
				we don't care with their content
				we only care with their arrows
			initial object
				if it exists
				an object that has exactly one arrow to all other objects
				in Set:
					empty set
					unique function
						takes an element from empty set
						and goes to target object
						a function that doesn't do anything
					absurd:: Void -> a
					Void is the uninhabited type
			terminal object
				opposite of initial object
				an object that has exactly one arrow from all other objects
				in Set
					Singleton set
					Unit type () with one element ()
					unit :: a -> ()
					unit _ -> ()
					is there any other singleton set
						if there are they are isomorphic
			universal constructions
				like initial, terminal object
				using universal constructions you can define things like products
				product (elimination)
					tuples in pl
					ct: c is a product of a and b
						you fix a and b
						c is a product of a and b
						there must be a projection from c to a and b
						c::(a,b) 
							here a is type
						p (a,b) = a
							here a is value
						q (a,b) = b
					logic: and (conjunction elimination)
						a ^ b -> a
							this is a morphism from c to a
						a ^ b -> b
							this is a morphism from c to b
					Set: cartesian product, pairs of elements
				product (universality)
					there can be several projection functions from c to a,b
						but there is unique product morphism
					there can be several other c' objects with projections to a,b
					but product is the best candidate
					any other candidate (c',p',q') uniquely factorizes through (c,p,q)
						they are then not elementary because they can be composed from m,p,q
					m::c'->c
					q'=p·m
					q'=q·m
					not every category has products
					logic:
						conjunction intro:
							if a follows from c'
							and b follows from c'
							a^b follows from c'
								note a^b = c
						uniqueness of product morphism translated into logic
						introduction and elimination are opposites of each other
				function object
					that is not a morphism
					a set of morphisms between two objects correspond to function a->b
					but it is a set (Hom-set), not an object
						it is outside of the category
					but if there is an object that corresponds to set of morphisms
						there is one
						it is called function object
					you need to first have products
						a function that takes two arguments
					ct:
						eval: (a=>b)xa -> b
							eval takes two arguments: function a=>b and a
					logic: 
						modus ponens
						( (a->b) ^ a ) -> b
					currying
				negation
					an operator that takes A and returns not A
					we don't need a special construction for this
					there is an arrow from A to Void
					why is this negation?
						if A is true, then A to Void must be false
							A is true means A is inhabited. 
							there are elements of A
							if I try to construct A -> Void, it is impossible because there is no element in Void
								I cannot map an element to nothing
						if A is not inhabited (false, then Void -> Void is id_{Void}
					thus: negation is a function object
			cartesian closed category (ccc)
				combining these three universal properties:
					has all products (cartesian)
					has all function objects (exponentials) (closed)
					has terminal object (nullary product)
						like empty tuple
			Curry-Howard-Lambek isomorphism
				Lambek: CCC is a model for simply typed lambda calculus
					objects are types
					morphisms are terms 
					environment Γ is a product of judgments a:A
						variables in lambda calculus
					empty environment is ():()
		logical universes
			classical: true, false
				Hilbert
				Gödel proved that there are statements that are neither true nor false
			intuitionistic: true, false, define "is"
			intuitionistic logic
				no LEM (law of excluded middle)
					LEM: 
						A or not A is true (classical)
						in intuitionistic logic, A is grey area
						"or" corresponds to sum type
						"or" means:
							A | (A->Void) not provable
								not provable in lambda calculus
								there is no function that produces this
						double negation cannot be eliminated
							lambda:
								(A->Void)->Void not the same as A
				curry howard: lambda calculus is equivalent to intuitionistic logic
			Goedel Gentzen
				classical logic can be embedded in intuitionistic logic
					classical logic = intutionistic + LEM
					there is a correspondence between two
					you need to transform 
						a proposition in classical logic that is provable
						into some other proposition in intuitionistic that does not use LEM
			Continuations
				what does this transformation mean?
			Yoneda's Lemma
	Category Theory for Programmers - Bartosz Milevski
		Category Theory for Programmers: The Preface
			this book is written for you
				this is not unfounded nonsens
			benefits of ct
				treasure of extremely useful programming ideas
					haskell programmers benefit this resource
					slowly entering into other languages 
					speed this learning process
				different kinds of math
					ct is particularly well suited for programmers
					composition is at the root
						it is part of the definition of category itself
						composition is essence of programming
				secret weapon: physicist
					amazing advances using informal reasoning is common in physics
		Natural Transformations
			https://bartoszmilewski.com/2015/04/07/natural-transformations/
				functors: mappings between categories that preserve their structure
					@mine
						int->List functorunu düşün
						int üzerinde g f fonksiyonlarını çalıştırman gerek
						bunları List üzerinde de çalıştırabilirsin, çünkü composition yapısı aynen korunur
						functor işte bu işe yarıyor
					analogy:
						we are modeling one category inside another
						source category: model for target category
				ways of embedding one category in another
					collapse the whole category into one object
					map every object to a different object
					natural transformations: help us compare them
						mappings of functors
						special mappings that preserve their functorial nature
				ex:
					F, G are functors between C, D categories
					a ∈ F
					F a, G a ∈ D
					a mapping of functors F,G
						should map F a to G a
					not any artificial connection between objects
						it is natural to use existing connections, namely morphisms
					natural transformation: is a selection of morphisms
						for every object a
						it picks one morphism from F a to G a
					α: natural transformation
					this morphism is called the component of α at a or α_a
						α_a :: F a -> G a
						note: a object is in C while α_a morphism is in D
					if no morphism between F a and G a, then there can be no natural transformation between F and G
				functors map morphisms as well
					what is a natural tranformation then with these mappings?
				ex: f: a -> b in C
					/Users/mertnuhoglu/Dropbox/public/img/ss-88.png
					it is mapped to two morphisms: F f, G f in D
						F f :: F a -> F b
						G f :: G a -> G b
					natural transformation α provides two additional morphisms that complete the diagram:
						α_a :: F a -> G a
						α_b :: F b -> G b
					naturality condition holds for any f:
						G f · α_a = α_b · F f
					if F f is invertible, naturality determines α_b in terms of α_a
						α_b = (G f) · α_a · (F f)⁻¹
					because of naturality condition, natural transformation maps morphisms to commuting squares:
						/Users/mertnuhoglu/Dropbox/public/img/ss-89.png
						F f :: F a -> F b
						G f :: G a -> G b
						α_a :: F a -> G a
						α_b :: F b -> G b
				Polymorphic Functions
					endofunctors: correspond to type constructors that map types to types
						they also map functions to functions
							using fmap
					to construct a natural transformation
						start with object (type) a
						F maps it to F a (types)
						G maps it to G a
						component of natural transformation: alpha at a
							is a function from F a to G a:
							alpha_a :: F a -> G a
						natural transformation is a polymorphic function defined for all types a:
							alpha :: forall a . F a -> G a
							you can also write:
								alpha :: F a -> G a
					naturality condition:
						it doesn't matter whether we modify items (unboxed values) first, by fmap and repackage later 
						or repackage first, then modify the items in new box
						reboxing and fmapping are orthogonal
					ex: natural transformation in haskell	
						between list functor and Maybe functor
						return head of the list if the list is non-empty
						code
							safeHead :: [a] -> Maybe a
							safeHead [] = Nothing
							safeHead (x:xs) = Just x
						this is a function polymorphic in a
							works for any type a
							example for parametric polymorphism
						verify the naturality condition 
							fmap f . safeHead = safeHead . fmap f
							two cases:
								empty list
									fmap f (safeHead []) = fmap f Nothing = Nothing
									safeHead (fmap f []) = safeHead [] = Nothing
								non-empty list
									fmap f (safeHead (x:xs)) = fmap f (Just x) = Just ( f x)
									safeHead (fmap f (x:xs)) = safeHead (f x : fmap f xs) = Just (f x)
								implementation of fmap for lists:
									fmap f [] = []
									fmap f (x:xs) = f x : fmap f xs
								for Maybe:
									fmap f Nothing = Nothing
									fmap f (Just x) = Just (f x)
				Beyond Naturality
					a parametrically polymorphic function
						between two functors
						is always a natural transformation
					all algebraic data types are functors
						thus any polymorphic function between them
						is a natural transformation
		Parametricity: Money for Nothing and Theorems for Free - Bartosz Milewski
			https://bartoszmilewski.com/2014/09/22/parametricity-money-for-nothing-and-theorems-for-free/
			parametricity arguments: free theorems about polymorphic functions
			Motivation
				function: takes list of any type, returns same type
				r:: [a] -> [a]
				what can this function do?
					it can't do anything type-specific
					can do: arrange, duplicate, remove
					actually it depends on kind of polymorphism
					haskell supports: parametric polymorphism
				parametric polymorphism
					function acts on all types uniformly
				ex: what happens when map any function of type:
					f:: a -> b
					over a list of a
					you can apply map either before or after acting it with r
						r (map f as) = map f (r as)
					can we make a formal argument to prove it?
			Let's Argue (Denotational) Semantics
				Bool: two element set of True and False
				Integer: set of integers
				composite types: can be defined set theoretically
					pair type: a cartesian product of two sets
				list of a: set of lists with elements from set a
				function type a->b: set of functions between two sets
				for parametric polymorphism
					you need
						defining functions on types
						ie. family of types that is parametrized by another type
						haskell: calls them type constructors
				ex: given a, produce pairs (a,a)
					Λa . (a, a)
					note: capital lambda for defining functions on types
						lowercase lambda on values
				Axiom of Choice (AoC) from set theory
					if you have a set of sets
					then there exists a set of samples 
						created by picking one element from each set  
				function kinds
					between types (sets)
					on types 
						assigns sets to sets
						called set constructor
						ex: take any set a and return a cartesian product of this set with itself
				ex:
					take type a, return set of function from a to a
						for every type a: an actual function whose type is (a->a)
					in parametric polymorphism
						you use the same code for all types
				this uniformity (one formula for all types)
					restricts set of polymorphic functions
					is the source of free theorems
			Preservation of Relations
				original example:
					r :: forall a. [a] -> [a]
					defines a family of functions
						parametrized by type a
				free theorem rewritten as:
					r_b (map f as) = map f (r_a as)
					f :: a -> b
				correspondence between elements can be more general
					more general than a function: a relation
					relation: a subset of cartesian product of a and b
				key insight of Reynolds:
					you can abstract the shape of a data structure
						by defining relations between values
					ex:
						(1,7) and (Red,Blue)
						two pairs have same shape
							we relate 1 to Red, 7 to Blue
						(2,2) and (Black,White)
							(2<=>Black), (2<=>White)
							relation doesn't have to be functional
					/Users/mertnuhoglu/Dropbox/public/img/ss-90.png
					ex: lists as and bs are related by a function f
						bs = map f as
						free theorem:
							r_b bs = map f (r_a as)
						ie. (r_b bs) and (r_a as) are related through f
					r may change shape of list, but it never touches the values in it
	Categorical databases - David I. Spivak
		http://math.mit.edu/~dspivak/informatics/talks/CTDBIntroductoryTalk
		goal
			there is fundamental connection between databases and categories
		pros of relational dbs
			underlying mathematics
			distinction between 
				domain knowledge
				relational model
		you're not really using the rleational model
			current implementations have departed from relational formalism
				tables may not be relational (duplicates from a query)
				nulls (and labeled nulls) are common
			no guidance for schema mappings and data migration
		category theory gives better description
			ct describes better dbms
				puts functional dependencies and foreign keys front and center
				allows non-relational tables
				labeled nulls and semi-structured data fit
			all columns are the same type of thing
			guidance for schema mapping and data migration
			deeply integrate programming and data
			theorems in ct can be used in databases
		what is category theory
			since 1940s, ct has revolutionized math
			like set theory and logic, but more principles-based
			new foundation for mathematics
			invented to build bridges between disparate branches of math
		branching out
			branched out of math and into physics, linguistics, and materials science
			much success in theory of programming languages
			pure ct concept monad has extended functional programming
		schemas are categories, categories are schemas
			connection is simple and strong
			reason: categories and schemas do the same thing
				schema: gives a framework for modeling a situation
					tables and attributes
				ct: this is precisely what a category does
					objects, arrows
				they both model how entities within a given context interact
			schema = category
		plan of the talk
			show the connection between database and ct
			schema evolution
			connection to programming language theory
			understand rdf
		what is a category
			a category models entities of a certain sort and the relationships between them
			/Users/mertnuhoglu/Dropbox/public/img/ss-28.png
			think of it like a graph
				nodes = entities
				arrows = relationships
			some paths can be declerad equivalent to others
				ex: 
					j;k ≅ i;i
					f;g ≅ f;h
		example
			/Users/mertnuhoglu/Dropbox/public/img/ss-30.png
			how to interpret this?
				self email is an email from a person = self email is an email to a person
			such business rules can be encoded into the category
		what is the essence of structure?
			how to organize math?
			essential features in common throughout math
			ex:
				objects arrows paths path-equivalence
				things, tasks, processes, "sameness of outcome"
				primary keys, foreign keys, paths of FKs, path equations
		definition of a category I: constituents
			a category C consists:
				a set Ob(C): set of objects of C
					tables in dbms
					objects x ∈ Ob(C) is written as ●x
				a set Arr(C): set of arrows of C and two functions:
					src, tgt: Arr(C) -> Ob(C)
					assigning	to each arrow: its source and its target object
					arraws = foreign keys from source table to target table
					an arrow f ∈ Arr(C) is written as [●x] f-> [●y]
						where x = src(f), y = tgt(f)
					define a path in C to be a finite "head-to-tail" sequence of arrows in C
						ex: [●x] f-> [●y] g-> [●z]
				a notion of equivalence for paths, denoted ≅
		definition of a category II: rules
			these constituents must satisfy the requirements:
				if p ≅ q => src(p) = src(q)
				if p ≅ q => tgt(p) = tgt(q)
				suppose two paths for b ↣ c
					/Users/mertnuhoglu/Dropbox/public/img/ss-31.png
					if p ≅ q then for any extensions m, n:
						m;p ≅ m;q
						p;n ≅ q;n
		what does equivalence of paths mean?
			arrows = foreign keys
			a path p: [●a] ↣ [●b] = following fk from table a to table b
			following a path = take any record in table a, and return a record in table b
			two paths p,q: [●a] ↣ [●b] equivalent if they return same record in b for any record in a
			in typical db practices:
				equivalent paths are avoided by cutting one of the paths (normalization)
					this is considered good design
					however it costs pain
					ct has this concept built in
		the category of Sets
			/Users/mertnuhoglu/Dropbox/public/img/ss-32.png
			[●A] f-> [●B]
				the objects of Set represent sets
				the arrows in Set represent functions
				a path represents a sequence of composable functions
				two paths are equivalent if their compositions are the same
		different category: an ordered set
			an ordered set is a set S together with a notion of ≤ satisfying
				a ≤ a ∀ a ∈ S
				if a ≤ b and b ≤ c, then a ≤ c
			given some ordered set S, we can build a corresponding category S':
				Ob(S') = S
				one arrow a → b if a ≤ b
				no arrows a → b if a ≰ b
				all pairs of paths are equivalent
		functors: mappings between categories
			think of a category: as a directed graph
				where certain paths are equivalent
			functor: a graph mapping that is required to respect equivalence of paths
			definition: a functor F: C → D consists of
				a function Ob(C) → Ob(D)
				a function Arr(C) → Path(D)
				st. F
					respects sources and targets
					respects equivalences of paths
		Functors to Set
			A category C is a system 
				of objects and arrows
				and an equivalence relation on its paths
			A functor C → D
				a mapping that preserves these structures
			Set is the category
				whose objects are sets
				whose arrows are functions
				where paths are equivalent 
					if they compose to the same function
			if C is category, then a functor I:C → Set is:
				/Users/mertnuhoglu/Dropbox/public/img/ss-33.png
				functor is a mapping (like a function) but not between sets it is between categories
					st. structure is preserved
		What is a database?
			database consists of: tables and relationships
			rows of a table: records or tuples
			columns: attributes
			an attribute: may be "pure data" or a "key"
				table may have "foreign key columns" that link to other tables
				a foreign key of table A links into the primary key of table B
			a schema may have "business rules"
		Foreign Keys and business rules
			ex:
				Employee
				|id|first|last|mgr|dpt|
				Department
				|id|name|secr|
			some business rules as integrity constraints:
				manager of an employee E must be in the same department as E
				secretary of a department D must be in D
		Data columns as foreign keys
			data type: a 1 column table
			ex:
				String
					|id|
					|a|
					|b|
					|..|
					|z|
					|aa|
					|..|
				Integer
					|id|
					|0|
					|1|
					|..|
					|10|
					|11|
					|..|
			so even data columns can be considered as foreign keys (to respective 1-column tables)
			conclusion: each column in a table is a key
				one primary
				rest foreign
		Example again
			/Users/mertnuhoglu/Dropbox/public/img/ss-34.png
				C: Category
					[Employee]
					[Department]
					[String1]
					[String2]
					[String3]
					[Employee] Dpt → [Department]
					[Department] Secr → [Employee]
					[Employee] Mgr → [Employee]
					[Employee] First → [String1]
					[Employee] Last → [String2]
					[Department] Name → [String3]
					Mgr;Dpt ≅ Dpt
					Secr;Dpt ≅ id_{Department}
		Database schema as a category
			each object x in C is a table
			each arrow x → y is a column of table x
			id column of a table is the trivial path on that object
			declaring business rules:
				Mgr;Dpt ≅ Dpt
				is declaring the path equivalence
		Schema = Category, Instance = Set-valued functor
			A functor I: C → Set consists of
				a set for each object of C
				a function for each arrow of C st
					the declared equations hold
			ie. I fills the schema with compatible data
			categorical database can also be called functional databases
		Data as a set-valued functor
			/Users/mertnuhoglu/Dropbox/public/img/ss-35.png
			for each table x,
				the set I(x): its set of rows
		Summary
			schema is a custom category
			functors I: C → Set are instances
			what about functors F: C → D between schemas?
		Changes
			changing the schema (schema mappings)
			changing the data (updates)
		Changes in schema
			from schema C to schema D
			over time we may have:
				C = [C0] F0 → [C1] F1 → ... F_{n-1} → [Cn] = D
			migrate data from C to D and vice versa
			migrate queries against C to queries against D and vv
		Composing functors
			Suppose F, G are functors:
				F: C → D
				G: D → E
			what is their composition: C → E?
		Changes in data
			Suppose
				C be a schema
				I,J: C → Set be two instances
			A natural transformation: u: I → J consists of:
				for each object (table) T ∈ Ob(C), we get a map of record sets:
					u_T: I(T) → J(T)
				for each arrow (foreign key) f: T → T', we get data consistency:
					J(f) · u_T = u_T' · I(f)
		category of instances
			given schema C:
				category of instances on C: C-Set
					objects of C-Set: functors (instances) I:C → Set
					arrows: natural transformations (progressive updates)
					paths: sequences of progressive updates
					paths are equivalent if they result in the same mapping
				category C-Set is a topos
					it has 
						internal language
						logic supporting typed lambda calculus
		data migration
			let C,D different schemas
			functor between them: F:C → D
				a schema mapping
			given schema mapping
				canonically transfer instances on C to instances on D
				ie.
					given F: C → D
					we need functors:
						C-Set → D-Set
						D-Set → C-Set
		what a functor C-Set → D-Set means
			objects: instances on C and D associated
			arrows: for every progressive update on C-instance
				there is a corresponding progressive update on the associated D-instance
			path equivalences
		the "easy" migration functor, △
			given a schema mapping (functor)
				F:C->D
			we can transform instances on D to instances on C as follows:
				/Users/mertnuhoglu/Dropbox/public/img/ss-37.png
				given I: D → Set
					[C] F → [D] I → [Set]
				get F;I C → Set
					[C] F;I → [Set]
			it preserves updates
			thus we have a functor:
				△ _F: D-Set → C-Set
		how △ _F works
			/Users/mertnuhoglu/Dropbox/public/img/ss-38.png
		compare the informatica picture
		so many kinds of functors
			functors in 3 different contexts:
				as instances: I:C → Set
				as schema mappings: F: C → D
				on instance categories: △ _F: D-Set → C-Set
			definition of functor: holds in each case
			functors: a reusable abstraction
				because of simplicity of its definition
		adjoints
			some functors X->Y have a special partner Y->X called an adjoint
			meaning:
				we can invert a data migration D-Set → C-Set in 2 ways:
					for progressive updates
					for regressive updates
			these migration functors are like updatable views
			they are canonical or universal
		adjoint migration functors, Σ and Π
			given schema mapping F: C → D
				we have functor △ _F: D-Set → C-Set given by composition
				it has two adjoints:
					sum-oriented adjoint: Σ_F: C-Set → D-Set
					product-oriented adjoint: Π_F: C-Set → D-Set
			thus given a schema mapping F, three functors emerge for the instance categories:
				△ _F, Σ_F, Π_F
			they correspond
				△ _F project, 
				Σ_F union, 
				Π_F join
		product-oriented push-forward Π_F makes joins
		
	ICFP 2012. Philip Wadler: Propositions as Sessions.
		curry-howard correspondence
			propositions as types
			proofs as programs
			normalisation of proofs as evaluation of programs
		curry-howard is robust
			howard showed many equivalencies:
				intuitionistic natural deduction = simply typed lambda calculus
				quantification over propositions = polymorphism
				quantification over individuals = dependent types
				modal logic = monads
				classical intutionistic embedding = continuation passing style
				??? = process calculus
		curry-howard for concurency
			propositions = sesion types
			proofs = processes
			cut elimination = communication
		lines of development
			girard, 1987
	[Wadlerfest 2016] Philip Wadler - A List of Successes that has not yet Changed the World-vWeDcH4aZh0.mp4
		a tale of two theorems
			girard reynold isomorphism
				girard's representation theorem
					every function can be proved total in Peano arithmetic can be represented in second order lambda calculus
					projection: proofs -> terms
					projection function that converts proofs into terms
				reynold's abstraction theorem
					terms in second order lambda calculus take related arguments to related results for a suitable notion of logical relation
					embedding: terms -> proofs
			curry howard isomorphism
			girar-reynolds isomorphism
			call b
