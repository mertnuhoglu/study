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

# Video: Colin Fleming - Cursive - A different type of IDE

![keywords and their usages in destructurings](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200413094135.png)

![autocompletion](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200413103557.png)

Note that, str was not in the current namespace yet.

# Video: Introduction to Clojure - Lecture 1 - Part 2

Send current line to REPL: `#+P`

![repl context menu](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200413122801.png)

