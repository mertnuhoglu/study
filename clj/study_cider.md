--- 
title: "Study Cider"
date: 2020-11-25T17:59:28+03:00 
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

# Article: Cider README

[clojure-emacs/cider: The Clojure Interactive Development Environment that Rocks for Emacs](https://github.com/clojure-emacs/cider)

Launch an nREPL server and client:

01. Open a clojure project file

02. `M-x cider-jack-in` or `SPC '`

# Video: F(by) 2016 - Bozhidar Batsov - Speech 2 - Deep Dive into CIDER - YouTube

[F(by) 2016 - Bozhidar Batsov - Speech 2 - Deep Dive into CIDER - YouTube](https://www.youtube.com/watch?v=aYA4AAjLfT0)

# Article: CIDER Docs

[CIDER :: CIDER Docs](https://docs.cider.mx/cider/index.html)

# Video: F(by) 2016 - Bozhidar Batsov - Speech 1 - CIDER: Inside the Brewery - YouTube id=g_11762

[F(by) 2016 - Bozhidar Batsov - Speech 1 - CIDER: Inside the Brewery - YouTube](https://www.youtube.com/watch?v=E0rDMM2EEu0)

	Interactive development environment
		Programs are built in a very incremental manner
	Slime:
		Client
		Server
		Backends
	clojure-mode for slime in 2008
		instant success
		problems:
			required common lisp knowledge
	nrepl in 2010 by chas emerick
		common foundation for clojure development tools
		Load a source file
		Evaluate a form
		Interrupt evaluation
		Read from std input
		Extensible via middleware
		nrepl.el: client
		nrepl: server
	Bozhidar Batsov becomes lead dev in 2013
		Emacs
			CIDER (emacs lisp)
				nrepl client
				connection management
				cider-mode: source files integration
				cider-repl-mode: clojure repl
		nrepl
			cider middleware
				completion
				debugger
				inspsector
				reloading
	CIDER features
		interactive evaluation
		compilation notes
		code completion
		definition lookup
		documentation lookup
		apropos
		value inspector
		command selector
		tracing
		macroexpansion
		debugger
		scratchpad
		minibuffer code evaluation
		javadoc
		jump to java
		jump to resource
		sanity-preserving stacktraces
		clojure.test integration
		namespace browser
		classpath borowser
		dynamic indentation
	Features
		Manual
		eldoc: for variables, macros, symbols
		eldoc takes into account higher order fct
		unified eval fcts: under C-c C-v 
		tracking evaluated functions with blue markers
		see also section in docs
		display spec in doc buffers
		rerun last test with shortcut
	Missing features
		find usages
		refactoring 
		better cljs
		socket repl
	Why CIDER?
		What does IDE do?
			It is a very nice house to start life.
			But ebd gets the same house.
			Emacs gives you bare foundations.
			But you can build your unique house.

[(70) F(by) 2016 - Bozhidar Batsov - Speech 2 - Deep Dive into CIDER - YouTube](https://www.youtube.com/watch?v=aYA4AAjLfT0)

- Tracing function calls:

![Tracing a function](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201126_184106.jpg)

- Jump between functions:

[abo-abo/avy: Jump to things in Emacs tree-style](https://github.com/abo-abo/avy)

- Toggle ns tracing: Cider Interactions > Debug

Trace every function

- Inspector

Inspect expression:

Drill down elements


