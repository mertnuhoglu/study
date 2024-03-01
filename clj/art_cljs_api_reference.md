---
title: "Study: CLJS API Reference"
date: 2021-01-21T18:20:14+03:00 
draft: false
description: ""
tags:
categories: clojurescript
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

# art_cljs_api_reference

[CLJS API](https://cljs.github.io/api/)

	Syntax
		Literals
			number literal
			"" string
			#"" regex
			\ character
			: keyword
			:: keyword
			symbol literal
		Special Symbols
			true
			false
			nil
		Symbolic Values
			##NaN
			##Inf
			##-Inf
		Collections
			[] vector
			() list
			{} map
			#{} set
			#:{} namespaced map
			#::{} namespaced map
		Commenting
			; comment
			#! shebang
			#_ ignore
		Function
			#() function
			% arg
			& rest
		Destructuring
			destructure []
			destructure {}
		Conventions
			? predicate
			! impure
			*earmuffs* -- dynamic vars
			_ unused
			whitespace
			, comma
		Symbol Resolution
			. dot
			/ namespace slash
				[/ namespace slash](https://cljs.github.io/api/syntax/namespace)
				left side of / in a keyword or symbol is a namespace.
					foo/bar
					:foo/bar
			right side completes the reference to the symbol
			js/ namespace
			Math/ namespace
		Tagged Literals
			# tagged literal
			#js literal
			#inst literal
			#uuid literal
			#queue literal
		Quoting
			' quote
			` syntax quote
			~ unquote
			~@ unquote splicing
			# auto-gensym
		Vars
			^ meta
			#' var
			@ deref
		Reader Conditionals 
			#? reader conditional
			#?@ reader conditional splicing
		Misc
			# dispath
			#<> unreadable
			#= eval
	Compiler Options
		Entries	
			:main
			:modules
		Preloads
			:preloads
			:browser-repl
		Output
		Source map
		Translation
		Libs
			:libs
		Node.js
			:target
		Externs
		Names
		Closure
		Spec
		Report
		Watch
		Bootstrap
		Uncategorized
	REPL Options
		:analyze-path
		:port
		:src
	cljs.core
		Compiled
			*clojurescript-version*
			*target*





