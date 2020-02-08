---
title: "Studying Reagent"
date: 2020-02-05T21:00:41+03:00 
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

# Article: Up and running with ClojureScript in 2018 - By Rory Gibson

``` bash
mkdir -p ex02/src/ex02
cd ex02
mkdir -p resources/public
``` 

Create `~/projects/study/clj/ex/study_reagent/ex02/deps.edn`

Create `~/projects/study/clj/ex/study_reagent/ex02/dev.cljs.edn`

Create `~/projects/study/clj/ex/study_reagent/ex02/resources/public/index.html`

Create `~/projects/study/clj/ex/study_reagent/ex02/resources/public/styles.css`

Create `~/projects/study/clj/ex/study_reagent/ex02/src/ex02/core.cljs`

``` bash
clojure -A:fig:build
``` 

This starts the web server. Opens http://localhost:9500/. And prompts a REPL.

Hot-reload is enabled.

Update `~/projects/study/clj/ex/study_reagent/ex02/resources/public/styles.css`

## Studying code:

### core.cljs

Check `~/projects/study/clj/ex/study_reagent/ex02/src/ex02/core.cljs`

#### `^:figwheel-hooks`

[Clojure - Reading Clojure Characters](https://clojure.org/guides/weird_characters)

[^ (and #^) - Metadata](https://clojure.org/guides/weird_characters#_and_metadata)

`^` is the metadata marker. 

``` bash
user=> (def ^:debug five 5)
#'user/five
user=> (meta #'five)
{:ns #<Namespace user>, :name five, :column 1, :debug true, :line 1, :file "NO_SOURCE_PATH"}
``` 

# Discussion: Routing with re-frame/Reagent : Clojure

https://www.reddit.com/r/Clojure/comments/a6lokt/routing_with_reframereagent/

reitit: start here. It works on both backend and frontend. 

# Article: Reagent: Minimalistic React for ClojureScript

http://reagent-project.github.io/

``` bash
``` 

# Article: reagent-project/reagent: A minimalistic ClojureScript interface to React.js

https://github.com/reagent-project/reagent

http://reagent-project.github.io/

## Usage

``` bash
lein new reagent ex01
``` 

Check `~/projects/study/clj/ex/study_reagent/ex01/project.clj`

``` bash
lein run
``` 

### Logs

#### Error

``` bash
$ lein new reagent myproject

java.lang.NullPointerException: null
``` 

https://github.com/Day8/re-frame-template/issues/63

opt01: Does lein work in general?

``` bash
lein new compojure tmp
``` 

opt02: Edit `~/.lein/profiles.clj`

Remove this line:

``` bash
{:user {:plugins [[luminus/lein-template "3.56"]]}}
``` 

This fixed the issue.

#### Error: lein run: clojure-future-spec 

``` bash
$ lein run

Retrieving clojure-future-spec/clojure-future-spec/1.9.0/clojure-future-spec-1.9.0.jar from clojars
java.lang.NullPointerException: null
 at clojure.core$name.invokeStatic (core.clj:1595)
``` 

https://github.com/technomancy/leiningen/issues/2383

opt01:

``` bash
lein repl
``` 

Same error

``` bash
lein version
``` 

Same error

opt02: leinı tekrar yükle

opt03: profile dosyasını temizle

``` bash
mv ~/.lein ~/.lein0
``` 

This fixed the issue.

### Simple Component

``` bash
(defn simple-component []
  [:div
   [:p "I am a component!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]])
``` 

``` bash
(def mount-target
  [:div#app
	 ..
   (simple-component)
	 ...
``` 

### hello-component

``` bash
(defn hello-component [name]
  [:p "Hello, " name "!"])

(defn simple-component []
  [:div
   (hello-component "mert")
``` 

Error: Bracket ile çalışmıyor, hata veriyor. Parantez yapmam gerekiyor. 

# Projects

## Project: simple

``` bash
cd ~/codes/clojure/reagent/examples/simple
lein figwheel
``` 

Check `~/codes/clojure/reagent/examples/simple/src/simpleexample/core.cljs`

Open `example.html`

### Project: ex03 copied from simple

``` bash
cp -R ~/codes/clojure/reagent/examples/simple ex03
``` 

Check `~/projects/study/clj/ex/study_reagent/ex03/project.clj`

Burada `hello-component` koyalım:

Edit `~/projects/study/clj/ex/study_reagent/ex03/src/simpleexample/core.cljs`

``` bash
(defn hello-component [name]
  [:p "Hello, " name "!"])

(defn simple-component []
  [:div
   [hello-component "mert"]
   [:p "I am a component!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]])

(defn simple-example []
  [:div
	 ...
   [simple-component]])
``` 

Burada bracket çalışıyor.
