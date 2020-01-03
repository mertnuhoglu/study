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

## Article: ClojureScript - Quick Start

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

Step 02:

Update `core.cljs`

Run on REPL:

``` bash
(require '[cljs_hello.core :as hello] :reload)
(hello/average 20 13)
  ##> 16.5
``` 

Step 03: React Dependency

``` bash
lein new app cljs_react01
``` 

Edit `ex/study_clojurescript/cljs_react01/deps.edn`

Edit `ex/study_clojurescript/cljs_react01/src/cljs_react01/core.cljs`

Run:

``` bash
clj -m cljs.main -c cljs_react01.core -r
``` 

