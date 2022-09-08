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

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


  - [Article: ClojureScript - Quick Start](#article-clojurescript---quick-start)
    - [Production Builds](#production-builds)
    - [Running ClojureScript on Node.js](#running-clojurescript-on-nodejs)
    - [Node.js REPL](#nodejs-repl)
    - [Dependencies](#dependencies)
    - [Running ClojureScript on Node.js](#running-clojurescript-on-nodejs-1)
    - [Node.js REPL](#nodejs-repl-1)
    - [Dependencies](#dependencies-1)
  - [Project Templates](#project-templates)
    - [Community Resources](#community-resources)
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
- [Tool: mount](#tool-mount)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

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

`--main` invokes a clojure function

On repl:

``` bash
(map inc [1 2 3])
(.getElementById js/document "app")
  ##> #object[HTMLDivElement [object HTMLDivElement]]
``` 

Step 02:

Update `core.cljs`

To reload: on REPL:

``` bash
(require '[cljs_hello.core :as hello] :reload)
(hello/average 20 13)
  ##> 16.5
``` 

Make an error:

``` bash
(ffirst [1])
``` 

Learn options:

``` bash
clj -m cljs.main --help
``` 

### Production Builds

Make an optimized build:

``` bash
clj -m cljs.main --optimizations advanced -c cljs_hello.core

``` 

Check build file: `~/projects/study/clj/ex/study_clojurescript/cljs_hello/main.js`

``` bash
$ ls -l out
.rw-r--r--  95k mertnuhoglu 20 Jan 10:12 main.js
``` 

Run web server (no repl)

``` bash
clj -m cljs.main --serve
``` 

Open localhost:9000

### Running ClojureScript on Node.js

Enable source mapping:

``` bash
npm install source-map-support
``` 

Build node project:

``` bash
clj -m cljs.main --target node --output-to main.js -c cljs_hello.core
``` 

Run:

``` bash
node main.js
  ##> Hello world2!
``` 

### Node.js REPL

This is similar to browser REPL.

``` bash
clj -m cljs.main --repl-env node
``` 

### Dependencies

Ex: React

Edit `~/projects/study/clj/ex/study_clojurescript/cljs_hello/deps.edn`

``` bash
{:deps {org.clojure/clojurescript {:mvn/version "1.10.520"}
        cljsjs/react-dom {:mvn/version "16.2.0-3"}}}
``` 

Edit `~/projects/study/clj/ex/study_clojurescript/cljs_hello/src/cljs_hello/core.cljs`

``` bash
(ns hello-world.core
  (:require react-dom))

(.render js/ReactDOM
  (.createElement js/React "h2" nil "Hello, React!")
  (.getElementById js/document "app"))
``` 

Build and run:

``` bash
clj -m cljs.main -c cljs_hello.core -r
``` 

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

## Project Templates

https://clojurescript.org/guides/project-templates

### Community Resources

https://clojurescript.org/community/resources

https://github.com/magomimmo/modern-cljs

http://clojurescriptmadeeasy.com/

Quick Start · bhauman/lein-figwheel Wiki

https://github.com/bhauman/lein-figwheel/wiki/Quick-Start

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

``` 
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

# figwheel vs shadowcljs vs devcard id=g13302

[Pros and cons figwheel vs shadowcljs ? - Clojure Q&A](https://ask.clojure.org/index.php/10403/pros-and-cons-figwheel-vs-shadowcljs) id=g13301

Özet: figwheel daha basit. shadowcljs eğer nodejs kullanacaksan yararlı. figwheel ile başla.

# figwheel-main test id=g13303

[bhauman/figwheel-main](https://github.com/bhauman/figwheel-main)

## Figwheel Tutorial id=g13304

[Figwheel Tutorial](https://figwheel.org/tutorial)

### figwheel01 Project id=g13306

rfr: `~/prj/study/clj/ex/study_figwheel/figwheel01/deps.edn`

```sh
clj -M -m figwheel.main
```

Open `http://localhost:9500/`

Test in repl:

```clj
cljs.user=> (filter odd? (map inc (range 5)))
(1 3 5)
cljs.user=> (js/alert "ClojureScript!")
nil
```

### naming conventions for function arguments  id=g13307

Source: [figwheel-main | Figwheel Main provides tooling for developing ClojureScript applications](https://figwheel.org/tutorial)

f, g, h - function
n - integer, usually a size
index, i - integer index
x, y - numbers
xs - sequence
m - map
s - string
re - regular expression
coll - a collection
pred - a predicate closure
& more - variable number of arguments

### figwheel02 Project  id=g13308

rfr: `~/prj/study/clj/ex/study_figwheel/figwheel02/deps.edn`

```sh
clj -M -m figwheel.main
```

Open `http://localhost:9500/`

Edit `~/prj/study/clj/ex/study_figwheel/figwheel02/src/p01/e01.cljs`

```clj
cljs.user=> (require 'p01.e01)
nil
cljs.user=> (p01.e01/what-kind?)
"Cruel"
cljs.user=> (require '[p01.e01 :as e01])
cljs.user=> (e01/what-kind?)
"Cruel"
cljs.user=> (js/console.log (e01/what-kind?))
```

Kodda değişiklik yap ve sonra tekrar yükle:

```clj
cljs.user=> (require '[p01.e01 :as e01] :reload)
```

```
cljs.user=> (js/document.getElementById "app")
#object[HTMLDivElement [object HTMLDivElement]]
```

HTML elementleri değiştirelim:

```
(def app-element (js/document.getElementById "app"))
(set! (.-innerHTML app-element) (e01/what-kind?))
```

##### REPL başlatırken kodu ilklendirmek

Yukarıdaki örnekte, biz kendimiz doğrudan `require` ederek kodu yüklemiştik.

Bunun yerine REPL başlarken, otomatik olarak yüklenecek kodu da belirtebiliriz:

```sh
clojure -M -m figwheel.main --compile p01.e01 --repl
```

Figwheel sayesinde, kod değiştiğinde `(require ... :reload)` yapmadan kod otomatik yüklenir.

```clj
cljs.user=> (p01.e01/what-kind?)
"Cruel2"
```

### figwheel03 project: figwheel configuration dev.cljs.edn id=g13309

[setting-up-a-build-with-tools-cli bhauman/figwheel-main](https://github.com/bhauman/figwheel-main#setting-up-a-build-with-tools-cli)

rfr: 

		~/prj/study/clj/ex/study_figwheel/figwheel03/deps.edn
		~/prj/study/clj/ex/study_figwheel/figwheel03/dev.cljs.edn
		~/prj/study/clj/ex/study_figwheel/figwheel03/src/p01/e02.cljs

```sh
clojure -M -m figwheel.main -b dev -r
```

Dikkat: Bu sefer doğrudan browser konsoluna print eder, `print` komutu. 

`-b`: `--build`: build configuration from `dev.cljs.rdn` 

`-r`: `--repl`: REPL should be launched

Alternatif olarak şöyle de çalıştırabilirsin, çünkü `deps.edn` içinde bu konfigürasyon var zaten:

```sh
clojure -A:build-dev
```

### figwheel04 project: extra-main id=g13310

[Extra Mains | figwheel-main](https://figwheel.org/docs/extra_mains.html)

Test sayfası: `http://localhost:9500/figwheel-extra-main/tests`

Test sonuç raporu: Browser console içinde.

### figwheel05 project: devcards id=g13311

[tools.deps, figwheel-main, Devcards, and Emacs](https://cjohansen.no/tools-deps-figwheel-main-devcards-emacs/)

rfr:
		
		~/prj/study/clj/ex/study_figwheel/figwheel05/deps.edn
		~/prj/study/clj/ex/study_figwheel/figwheel05/dev.cljs.edn
		~/prj/study/clj/ex/study_figwheel/figwheel05/devcards/p01/cards.cljs

Open `http://localhost:9500/figwheel-extra-main/devcards`

### figwheel06 project: custom home page id=g13314

[Figwheel Tutorial: index.html](https://figwheel.org/tutorial#indexhtml)

rfr:

		~/prj/study/clj/ex/study_figwheel/figwheel06/deps.edn

