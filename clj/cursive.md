---
title: "Studying Cursive"
date: 2020-04-12T20:35:27+03:00 
draft: false
description: ""
tags:
categories: clojurescript, clojure
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

# quickstart cursive id=g11816

    | ^^ repl  | run anything: repl             |
    | #e       | actions command bar            |
    | !u/ç     | popup menu prefix              |
    | !up/çp   | popup menus                    |
    | !uj      | clojure popup menu             |
    | !uç/çç   | popup general menu             |
    | !un/çn   | popup next menu                |
    | !ue/çe   | popup structural editing menu  |
    | !u SPC   | popup move menu                |
    | !eb      | load file in repl              | ,eb |
    | !ed      | send top form to repl          | ,ed |
    | !ee      | send form before caret to repl | ,ee |
    | !rn      | switch repl ns to current file | ,en |
    | !rb      | jump to repl editor            | ,rb |
    | #enter   | repl: submit command           |
    | !++enter | repl: submit command           |

# Index cursive id=g11817

	Shortcuts cursive <url:file:///~/projects/study/clj/cursive.md#r=g12405>
	Getting around: Structural movement <url:file:///~/projects/study/clj/cursive.md#r=g11818>
	Structural editing (paredit) <url:file:///~/projects/study/clj/cursive.md#r=g11819>
	Bir projeyi REPL ile incelemek  <url:file:///~/projects/study/clj/cursive.md#r=g12072>

# Issues

## Bir projeyi REPL ile incelemek  id=g12072

### deps.edn

File > New > Project from existing sources > .select: `deps.edn`

Edit Configurations > Clojure REPL Local > Run with Deps

Which type: `nREPL`

Aliases: `runner`

Run 'REPL'

1. REPL > Load File in REPL

2. REPL > Switch REPL ns to current file


### Leiningen

File > New > Project from existing sources > .select: `~/codes/clj/ataraxy/project.clj`

Edit Configurations > Clojure REPL Local > Run with Leiningen

Run 'REPL'

REPL > Load File in REPL

REPL > Switch REPL ns to current file

# Book: User Guide 

https://cursive-ide.com/userguide/first-repl.html

## First Repl

### New Project

New Project: New Project > Clojure > Leiningen 

project.clj > context menu > Run REPL

### Import an existing project

		File > New > Project from existing sources 
			> .select folder with deps.edn file > Deps (external model) 
				> . Automatically import transitive dependencies
		deps.edn > context menu > Run REPL

## User Interface

Search action by name: #a

Suggested settings:

Bind tab key to `Emacs Tab` action: Settings > Keymap > ."tab" > .Remove `Tab` binding to tab key > Select `Emacs Tab` > Add Keyboard Shortcut > .Tab

`Emacs Tab` indents current line.

## Working with Leiningen

Ex:

``` bash
lein new luminus myapp +cljs +swagger
``` 

- Project name: myapp
- Template: luminus
- Template options: +cljs +swagger

### Quick project import

File > Open > .select `project.clj`

### Viewing your project dependencies

``` bash
lein deps :tree
``` 

Leiningen panel > dependencies

### Running Leiningen tasks

Leiningen panel > .project > Tasks > .select a task > .Run button

## Working with Deps

### Configuring Deps

Settings >..> Clojure Deps

		Use CLI tools > Path to clojure command

## The Cursive REPL

### Local REPLs

### Starting a debug REPL

## Documentation

Parameter Info `#P`

![parameter info](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200413124207.png)

Quick documentation `F1`

![quick documentation](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200413124352.png)

## Structural editing (paredit) id=g11819

Add shortcut for: `Structural Editing` as `#+s`

Surrounding:

![surround selection on typing quote or brace](https://cursive-ide.com/userguide/images/paredit/wrapping-things.png)

Alternative: `Wrap with...`

`Close ... and newline`: 

``` bash
(let [x 10^
			y 20])
``` 

Press `#+0`, cursor goes to:

``` bash
(let [x 10
			y 20]
	 ^)
``` 

		slurp		grow the list
		barf		shrink the list
		raise		move up the list
		splice	insert the list to parent
		split		split the list
		join		unsplit the list

### Getting around: Structural movement id=g11818

Navigate > Structural Movement

![structural movement](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200413133600.png)

![structural movement gif](https://cursive-ide.com/userguide/images/paredit/getting-around.png)

Commands:

		Move Forward		#right
		Move Backward		#left
		Out of Sexp     #+ arrow
		Into Sexp
		Form Down/Up

# Video: Colin Fleming - Cursive - A different type of IDE

![keywords and their usages in destructurings](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200413094135.png)

![autocompletion](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200413103557.png)

Note that, str was not in the current namespace yet.

# Features

	| action     | description        |
	|------------|--------------------|
	| Keywords   | usages of keywords |
	| Namespaces | go to namespace    |

# Video: Colin Fleming - Debugging Clojure Code Wtih Cursive - YouTube

[Colin Fleming - Debugging Clojure Code Wtih Cursive - YouTube](https://www.youtube.com/watch?v=ql77RwhcCK0)

# Shortcuts cursive id=g12405

`custom shortcuts <url:file:///~/projects/study/otl/intellij.otl#r=g12410>`

