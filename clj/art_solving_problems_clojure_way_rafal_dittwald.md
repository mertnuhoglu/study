--- 
title: "Article: Solving Problems the Clojure Way - Rafal Dittwald"
date: 2020-12-20T14:53:28+03:00 
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

[Solving Problems the Clojure Way - Rafal Dittwald - YouTube](https://www.youtube.com/watch?v=vK1DazRK_a0)

Source code: [grierson/bounty: Solving Problems the Clojure Way - Rafal Dittwald in Clojure](https://github.com/grierson/bounty)

Run code:

```bash
clj -M:repl/rebel
```

```clj
(load "cards/core")
```

## How to avoid mutable state, mutations, side-effects 

01: minimize: Try to have less states, mutations, side-effects.

02: concentrate: Keep them all in one place.

03: defer: 

## How to minimize state?

01: derive values, instead of extra state

02: copy data instead of mutate-in-place. because we have immutable data structures.

03: make and pass lambdas around. ex: into map, reduce

04: recurse

## mutable state, mutations, side-effects 

OOP example: 

![OOP example](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210321_204335.jpg)

Mutable state: `this.cards = [1, 2, 3...]`

Mutations: `this.cards.splice(index, 1)`

External side-effects: `console.log('...')`

Imperative example:

![imperative example](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210321_204620.jpg)

Mutable state: `let turn = 0`

Mutations: `arr.splice(index, 1)`

External side-effects: `console.log(..)`

01: Move console.logs and concentrate them in one place:

![move console.logs into one place](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210321_204902.jpg)

02: Make `popRandom` function pure. Remove array mutations.

![move mutation to upper function](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210321_204955.jpg)

Concentrating bad things in one place.

![move mutation to upper function](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210321_205224.jpg)

03: Extract side-effect logic for which message to show:

![Extract pure logic from side-effect logic](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210321_205305.jpg)

04: Combine state vars into one

![combine state vars](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210321_205507.jpg)

![single state var](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210321_205533.jpg)

05: Create `nextState` it takes current state, and gives next state:

![Create nextState](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210321_205709.jpg)

![nextState used](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210321_205734.jpg)

All logic is inside a pure function.

06: Extract side-effect logic for what message to show:

![turnMessage](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210321_205900.jpg)

07: Instead of keeping one state, turn into an array of states.

Append new state to states array.

![Store states in an array](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210321_210102.jpg)

08: Split our program into two steps

First step: Generate the states

Second step: Log those states

![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210321_210325.jpg)

![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210321_210301.jpg)

09: while loop appends the states.

Use recursion for discarding the states and mutations. 

![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210321_210508.jpg)

## Why is this good?

Pure functions

Data driven programming:

A. "data-first" program design, programs as data flows

B. use of "plain data" (vs. typed structs, objects)

C. programming where data (not code) defines some of the control flow:

- code generation from data
- making DSLs with data
- configuration driven programming
- data > functions > macros 

Use data structures to describe the logic. Have other code that manipulates these data structures and do the things we want.

Because data structures are purest thing. They don't even have a behaviour.



