--- 
title: "Articles: Logic Programming"
date: 2021-01-03T00:41:21+03:00 
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

# Logic Programming - Episode 1 - Intro - YouTube

[Logic Programming - Episode 1 - Intro - YouTube](https://www.youtube.com/watch?v=7CWEPRKOwgI)

```clj
clojure -X:project/new :name logic-tutorials/episode1
```

Check `~/projects/study/clj/ex/art_logic_programming/episode1/deps.edn`

# miniKanren - Dan Friedman and William Byrd - YouTube id=g_11871

[miniKanren - Dan Friedman and William Byrd - YouTube](https://www.youtube.com/watch?v=5Q9x16uIsKA)

# Introduction to Logic Programming with Clojure - Ambrose Bonnaire-Sergeant - YouTube

[Introduction to Logic Programming with Clojure - Ambrose Bonnaire-Sergeant - YouTube](https://www.youtube.com/watch?v=irjP8BO1B8Y)

	Pure functions
		functions have one value = deterministic
		one pattern: input and output args
		Therefore sometimes functions are inappropriate
			Ex: 4 has two square roots: +2 and -2
			Ex: 1/0 has no result
		These problems can be overcome with relations
	Relations
		Relations: generalized functions
			Any number of results = non-deterministic
			patterns can be different for each call
				args are not only for inputs, it is also for outputs
		Mathematical definition:
			`X r Y` is true if X and Y satisfy relation `r`
			Ex: `X < Y`
				`<` relation can be:
					generator of infinite set of all (X,Y) pairs
					predicate that can be applied to (X,Y) pairs
					generator, given X will give Y
					generator, given Y will give X
	Converting a Function to a Relation
		Relation return true if the relation is true, and false if the relation is false
			@mine: if a relation returns true, then a relation is also a function.
		Convert the return value to an arg:
			(cons 1 [2]) ; => [1 2]
			->
			(conso 1 [2] [1 2]) ; => true
	Ex: conso
		conso: a predicate if all arguments are ground values (not variables)
			(conso head tail result) 
				returns true if `(cons head tail)` equals `result`
			(conso 1 [2] [1 2]) ; => true
			(conso 1 [] [1 2]) ; => false
		conso: a generator if one argument is a variable
			`solve` introduces a logic variable `x`
				returns a list of all `x` values that satisfy the relation
			(solve 1 [x]
				(conso 1 [2] x))
			;=> ([1 2])
			Here, [1 2] is a solution for x
			(solve 1 [x]
				(conso 1 x [1 2]))
			;=> ([2])
			`(solve 1)` means give me 1 result
		sqrto: a relation that can generate multiple results
			(solve 2 [x])
				(sqrto 4 x)
			;=> (2 -2)
	Logic Language Implementation
		Execution strategy: implemented as a search
	Execution Strategy: Branches
		Branch: a choice point
		A choice point: groups a set of alternative statements
		Executing a choice point:
			Pick an alternative statement
			Follow it
			If it is found to be wrong, pick another one
	Execution Strategy: Failure
		How do we say that a branch is wrong?
	Execution Strategy: Leaf Nodes
	Encapsulated Search
		One way to implement
		Relational program: Program consisting purely of relations
		Control which choices are made
			Search strategy: depth-first, breadth-first, ...
			Number of results
		Protect branches from side-effects of other branch executions
			So we can go back in time and undo any bindings to logic variables
		Functional Approach: an implementation way
			Protects from the effects of choices
				by representing state by substitutions 
					means: setting state via functions instead of having global state
					substitution: list of identity value pairs
						ex: variable `v` has value 1
						Think of substitution as current world
			How do we get these states?
				with goals
			Goals: next state functions
				Function of: Substitution -> LazyList Substitution
					Take current substitution
					Return many substitutions
				Relations implemented as goals
			Control which choices are made
				by using monadic strategies
	Introducing core.logic
		features
			non-deterministic
			Uses substitutions
			Uses goals 
			Quieries are run, not solved
			If a variable is unbound then it is represented by _.0, _.1, ...
		Fundamental Goals
			succeed: a no-op
			fail: current branch is wrong
		Unification
			Very important goal
			Unification answers the question:
				What must the world look like for the left and right arguments to be equal?
				ex: 
					(run 1 [q]
						(== 1 q))
					;=> (1)
		Initialising Logic Variables
			How do we introduce more than one logic variable?
				fresh
			fresh: similar to let
				One logic variable is `q` query logic variable
			(run 1 [q]
				(fresh [v1]
					(== v1 1)
					(== q v1)))
			;=> (1)
		Choice Points
			How do we define a choice point?
			`conde` is similar to Scheme's `cond`
				But it can have 0+ answers
			(conde
				(<question1> <answer1> <answer..>)
				(<question2> <answer1> <answer..>)
				(<question3> ))

# Article: Home · frenchy64/Logic-Starter Wiki id=g_11896

[Home · frenchy64/Logic-Starter Wiki](https://github.com/frenchy64/Logic-Starter/wiki)
			
```bash
git clone https://github.com/frenchy64/Logic-Starter
cd /Users/mertnuhoglu/codes/clj/Logic-Starter
lein trampoline run -m clojure.main
```

# Video: Programming Languages: The Logic Programming Paradigm - 1 - YouTube id=g_11897

[Programming Languages: The Logic Programming Paradigm - 1 - YouTube](https://www.youtube.com/watch?v=Z62rQwe8MSI)

![Algorithm = Logic + Control](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210112_115853.jpg)

Logic: What must be done

Control: How the desired solution is found

![Example problem: Arrange 1s, 2s, ...](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210112_115936.jpg)

![Solution](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210112_120005.jpg)

```bash
sol(Ls) :=
	list_of_27(Ls),
	sublist([9,_,_,_,_,_,_,_,_,_9,_,_,_,_,_,_,_,_,_9], Ls),
	...
```

# Video: Challenges for Logic Programming - Steve Miner - YouTube

[Challenges for Logic Programming - Steve Miner - YouTube](https://www.youtube.com/watch?v=y6WKr9j76kw)

