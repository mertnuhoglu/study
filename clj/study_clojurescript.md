---
title: "Study ClojureScript"
date: 2020-01-03T12:42:12+03:00 
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

<!-- START doctoc -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

  - [Article: ClojureScript - Quick Start](#article-clojurescript---quick-start)
    - [Running ClojureScript on Node.js](#running-clojurescript-on-nodejs)
    - [Node.js REPL](#nodejs-repl)
    - [Dependencies](#dependencies)
    - [Project Templates](#project-templates)
    - [Community Resources](#community-resources)
  - [Article: Interactive Programming in ClojureScript](#article-interactive-programming-in-clojurescript)
    - [Ex01](#ex01)
    - [Ex02:](#ex02)
  - [Article: Reagent Readme](#article-reagent-readme)
  - [Article: Shadow CLJS User’s Guide](#article-shadow-cljs-users-guide)
    - [3. Usage](#3-usage)
  - [Video: Learn Reagent Free](#video-learn-reagent-free)
    - [01 Dev Setup](#01-dev-setup)
    - [02 App Setup](#02-app-setup)
    - [03 State](#03-state)
    - [04 Creating Components](#04-creating-components)
    - [05 Displaying Gigs](#05-displaying-gigs)
    - [06 List Comprehension](#06-list-comprehension)
    - [07 Add to Order](#07-add-to-order)
- [Tool: mount 2](#tool-mount-2)

<!-- END doctoc -->

## Article: ClojureScript - Quick Start

https://clojurescript.org/guides/quick-start

``` bash
cd ~/projects/study/clj/ex/study_clojurescript
lein new app cljs_hello
  ##> Generating a project called cljs_hello based on the 'app' template.
``` 

Step 01:

Edit `ex/study_clojurescript/cljs_hello/deps.edn`

Edit `ex/study_clojurescript/cljs_hello/src/cljs_hello/core.cljs`

``` bash
clj --main cljs.main --compile cljs_hello.core --repl
``` 

On repl:

``` bash
(map inc [1 2 3])
(.getElementById js/document "app")
  ##> #object[HTMLDivElement [object HTMLDivElement]]
``` 

Step 02:

Update `core.cljs`

on REPL:

``` bash
(require '[cljs_hello.core :as hello] :reload)
(hello/average 20 13)
  ##> 16.5
``` 

Make an error:

``` bash
(ffirst [1])
``` 

Make an optimized build:

``` bash
clj -m cljs.main --optimizations advanced -c cljs_hello.core

``` 

Check build file:

``` bash
$ ls -l out
.rw-r--r--  95k mertnuhoglu 20 Jan 10:12 main.js
``` 

Run web server

``` bash
clj -m cljs.main --serve
``` 

Open localhost:9000

### Running ClojureScript on Node.js

Enable source mapping:

``` bash
npm install source-map-support
``` 

Build:

``` bash
clj -m cljs.main --target node --output-to main.js -c cljs_hello.core
``` 

Run:

``` bash
node main.js
  ##> Hello world!
``` 

### Node.js REPL

``` bash
clj -m cljs.main --repl-env node
``` 

### Dependencies

React a dependency. CLJSJS provides a bundled version. To include it:

``` bash
lein new app cljs_react01
``` 

Edit `ex/study_clojurescript/cljs_react01/deps.edn`

``` bash
        cljsjs/react-dom {:mvn/version "16.2.0-3"}
``` 

Edit `ex/study_clojurescript/cljs_react01/src/cljs_react01/core.cljs`

``` bash
(.render js/ReactDOM
  (.createElement js/React "h2" nil "Hello, React!")
  (.getElementById js/document "app"))
``` 

Builnd and Run:

``` bash
cd /Users/mertnuhoglu/projects/study/clj/ex/study_clojurescript/cljs_react01
clj -m cljs.main -c cljs_react01.core -r
``` 

### Project Templates

https://clojurescript.org/guides/project-templates

### Community Resources

https://clojurescript.org/community/resources

https://github.com/magomimmo/modern-cljs

http://clojurescriptmadeeasy.com/

Quick Start · bhauman/lein-figwheel Wiki

https://github.com/bhauman/lein-figwheel/wiki/Quick-Start

## Article: Interactive Programming in ClojureScript

https://rigsomelight.com/2014/05/01/interactive-programming-flappy-bird-clojurescript.html

### Ex01

``` bash
git clone https://github.com/bhauman/flappy-bird-demo.git
cd flappy-bird-demo
lein figwheel
``` 

http://localhost:3449/index.html

Edit `/Users/mertnuhoglu/codes/clojure/flappy-bird-demo/src/flappy_bird_demo/core.cljs`

### Ex02: 

``` bash
lein new figwheel hello-world
cd hello-world
lein figwheel
``` 

http://localhost:3449/index.html

## Article: Reagent Readme

https://github.com/reagent-project/reagent

``` bash
lein new reagent reagent01
``` 

## Article: Shadow CLJS User’s Guide

https://shadow-cljs.github.io/docs/UsersGuide.html

### 3. Usage

``` bash
shadow-cljs help
``` 

``` bash
cd /Users/mertnuhoglu/codes/clojure/learn-reagent-course-files/giggin
``` 

``` bash
shadow-cljs compile app
shadow-cljs watch app
shadow-cljs cljs-repl app # for watch
``` 



## Video: Learn Reagent Free 

https://www.jacekschae.com/courses/learn-reagent-free/21667-setup/61162-01-dev-setup

### 01 Dev Setup

Shadow CLJS as build tool:

https://shadow-cljs.github.io/docs/UsersGuide.html#_installation

``` bash
npm install --save-dev shadow-cljs
npm install -g shadow-cljs
``` 

Javascript vs ClojureScript

https://medium.freecodecamp.org/here-is-a-quick-overview-of-the-similarities-and-differences-between-clojurescript-and-javascript-c5bd51c5c007

### 02 App Setup

Course Files: https://github.com/jacekschae/learn-reagent-course-files

``` bash
cd /Users/mertnuhoglu/codes/clojure/learn-reagent-course-files
cd giggin
npm install
``` 

opt01:

``` bash
npm run dev
``` 

opt02:

``` bash
shadow-cljs watch app
shadow-cljs cljs-repl app # for watch
``` 

opt03:

``` bash
npx shadow-cljs server
npx shadow-cljs watch app
npx shadow-cljs cljs-repl app
``` 

http://localhost:3000

Check `~/codes/clojure/learn-reagent-course-files/giggin/package.json`

Chapter copy:

``` bash

``` 

### 03 State

Check `~/codes/clojure/learn-reagent-course-files/increments/05-displaying-gigs/src/giggin/core.cljs`

### 04 Creating Components

### 05 Displaying Gigs

Check `~/codes/clojure/learn-reagent-course-files/increments/06-list-comprehension/src/giggin/components/gigs.cljs`

``` bash
   [:div.gigs (map (fn [gig]
                     [:div.gig {:key (:id gig)}
                      [:img.gig__artwork {:src (:img gig) :alt (:title gig)}]
											 ...
                       [:p.gig__desc (:desc gig)]]]) (vals @state/gigs))]])
``` 

### 06 List Comprehension

Check `~/codes/clojure/learn-reagent-course-files/increments/07-add-to-order/src/giggin/components/gigs.cljs`

Use `for comprehension` instead of `map` function.

``` bash
    (for [gig (vals @state/gigs)]
      [:div.gig {:key (:id gig)}
			...
``` 

Destructure `gig` in `for comprehension` as `{:keys [id img title price desc]}`

``` bash
    (for [{:keys [id img title price desc]} (vals @state/gigs)]
      [:div.gig {:key id}
			...
``` 

### 07 Add to Order

Check `~/codes/clojure/learn-reagent-course-files/increments/08-for-over-orders/src/giggin/state.cljs`

Create a new state:

``` bash
(def orders (r/atom {}))
``` 

Add `on-click` function

``` bash
         [:div.btn.btn--primary.float--right.tooltip
           :on-click (fn [] (swap! state/orders update id inc))}
``` 

Connect to repl:

``` bash
(shadow.cljs.devtools.api/nrepl-select :app)
``` 

Get into ns:

``` bash
(ns giggin.state
  (:require [reagent.core :as r]))
``` 

# Tool: mount 

https://github.com/tolitius/mount

