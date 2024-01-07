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

- # figwheel vs shadowcljs vs devcard id=g13302
  id:: 31f42c86-878e-4151-9c4f-e9bb4add682f

- [Pros and cons figwheel vs shadowcljs ? - Clojure Q&A](https://ask.clojure.org/index.php/10403/pros-and-cons-figwheel-vs-shadowcljs) id=g13301

Özet: figwheel daha basit. shadowcljs eğer nodejs kullanacaksan yararlı. figwheel ile başla.

- # figwheel-main test id=g13303
  id:: 7a18eb37-2f65-4702-8650-316f8340f5c2

[bhauman/figwheel-main](https://github.com/bhauman/figwheel-main)

- ## Figwheel Tutorial id=g13304
  id:: e568d216-1505-41b0-bdc5-c72cdad0b62b

[Figwheel Tutorial](https://figwheel.org/tutorial)

- ### figwheel01 Project id=g13306
  id:: 59ebab69-a4ff-4463-8832-fd471e1d99f7

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

- ### naming conventions for function arguments  id=g13307
  id:: 3d04ddcb-efb0-4e4f-8e29-508baa5cb0a4

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

- ### figwheel02 Project  id=g13308
  id:: 0eb26bd0-c7b4-4fda-9dd6-369a67a9cd16

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

- ### figwheel03 project: figwheel configuration dev.cljs.edn id=g13309
  id:: fe5ad234-3763-499a-b96e-8f019fcfe137

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

- ### figwheel04 project: extra-main id=g13310
  id:: ec44889c-5eb8-4223-af64-4f42f3e8b6fb

[Extra Mains | figwheel-main](https://figwheel.org/docs/extra_mains.html)

Test sayfası: `http://localhost:9500/figwheel-extra-main/tests`

Test sonuç raporu: Browser console içinde.

- ### figwheel05 project: devcards id=g13311
  id:: f0d64321-788d-4983-8e83-e05707617466

[tools.deps, figwheel-main, Devcards, and Emacs](https://cjohansen.no/tools-deps-figwheel-main-devcards-emacs/)

rfr:
		
		~/prj/study/clj/ex/study_figwheel/figwheel05/deps.edn
		~/prj/study/clj/ex/study_figwheel/figwheel05/dev.cljs.edn
		~/prj/study/clj/ex/study_figwheel/figwheel05/devcards/p01/cards.cljs

Open `http://localhost:9500/figwheel-extra-main/devcards`

- ### figwheel06 project: custom home page id=g13314
  id:: b5b260c1-0ba2-4a34-9788-ca3519c3dbf0

[Figwheel Tutorial: index.html](https://figwheel.org/tutorial#indexhtml)

rfr:

		~/prj/study/clj/ex/study_figwheel/figwheel06/deps.edn

- ### figwheel07 project: custom home page manipulate dom  id=g13322
  id:: bcd36af4-66ed-4de5-b27c-35996c58c4d7

Check `~/prj/study/clj/ex/study_figwheel/figwheel07/src/hello/cruel_world.cljs`

```clj
(def app-element (js/document.getElementById "app"))

(set! (.-innerHTML app-element) (what-kind?))
```

- ### figwheel08 project: devcards + custom home page id=g13323
  id:: 37b21d77-8354-484a-843e-80c9c0a9e493

		~/prj/study/clj/ex/study_figwheel/figwheel08/devcards/p01/cards.cljs

		~/prj/study/clj/ex/study_figwheel/figwheel08/dev.cljs.edn

```clj
^{:watch-dirs ["src" "devcards"]
  :extra-main-files {:devcards {:main p01.cards}}}
```

Tutorial'daki şu satırları silmelisin. Aksi taktirde target/ yerine bu klasöre js dosyalarını compile eder. Bu durumda index.html'den js dosyasına verdiğin referansı ona göre güncellemen gerekir:

```clj
 :asset-path "/js/dev"
 :output-to "resources/public/js/dev.js"
 :output-dir "resources/public/js/dev"}
```

Open `http://localhost:9500/figwheel-extra-main/devcards`

- ### figwheel09 project: use dumdom components id=g13324
  id:: 3a10b9ad-89e7-4052-87ca-1eb5b995fd91

rfr: `~/prj/study/clj/ex/study_figwheel/figwheel09/src/p01/e01.cljs`

devcards cards: `~/prj/study/clj/ex/study_figwheel/figwheel09/devcards/p01/cards.cljs`

- ### figwheel10 project: dumdom inside devcards id=g13325
  id:: ce043a32-40de-4918-8cd6-c00d143be823

rfr: `~/prj/study/clj/ex/study_figwheel/figwheel10/devcards/p01/dumdom_cards.cljs`

```clj
  (:require [dumdom.devcards :refer-macros [defcard]]...
	...
(defcomponent heading
  :on-render (fn [dom-node val old-val])
  [data]
  [:h2 {:style {:background "#000"}} (:text data)])

(defcomponent page [data]
  [:div
    [heading (:heading data)]
    [:p (:body data)]])

(defcard dumdom-card-02
  [page {:heading {:text "Hello world2"}}
        :body "This is a web page"])
```

- ### dumdom01 project: dumdom + figwheel + devcards id=g13326
  id:: 5e4f610b-0fad-4854-ab3b-88514eb74048

rfr: `~/prj/study/clj/ex/dumdom/dumdom01/deps.edn`

Clone of figwheel10 project.

- ### dumdom02 project: id=g13327
  id:: fcdb2a77-6ace-444f-ac78-bf120bc539a7

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/dumdom/dumdom02/deps.edn`

`~/prj/study/clj/ex/dumdom/dumdom02/src/mert/e01.cljs`

```clj
(defn render [data] 
  (dumdom/render
    [:h1.h1 {:style {:color "red"}} "Hello NDC2"]
    (js/document.getElementById "app")))

(render @store)
```

- #### dumdom02b project: defcomponent + add-watch id=g13328
  id:: 0439aaf0-32a5-4ba9-a48e-e2039b3a701f

`~/prj/study/clj/ex/dumdom/dumdom02b/src/mert/e01.cljs`

```clj
(defcomponent ListPageComponent [data]
  [:h1.h1 {:style {:color "red"}} "Hello NDC3"])

(defn render [data] 
  (dumdom/render
    (ListPageComponent data)
		...
```

```clj
(add-watch store :render (fn [_ _ _ state]
                           (render state)))
```

- #### dumdom02c project: list items id=g13329
  id:: 88f97beb-3ff5-4bc9-aed1-649e6e2511ad

`~/prj/study/clj/ex/dumdom/dumdom02c/src/mert/e01.cljs`

```clj
    [:ul
     (for [{:keys [text]} (:items ui-data)]
       [:li text])]]])
```

- #### dumdom02d project: Auto completion input + gadget inspector id=g13330
  id:: 844a0f9a-b0a4-43ef-9f3d-f58c91c910af

[ClojureScript: Fun and productive web development with next level tooling - Christian Johansen - YouTube](https://www.youtube.com/watch?v=yFVk3D76wQw)

`~/prj/study/clj/ex/dumdom/dumdom02d/devcards/mert/dumdom_cards.cljs`

- ##### Gadget inspector: Bununla datayı gözetleyebilirsin. id=g13336
  id:: 09445e1a-b671-45f3-a9f5-c926ea7e8b99

`~/prj/study/clj/ex/dumdom/dumdom02d/src/mert/e01.cljs`

1. Kodun içine koy:

```clj
(gadget/inspect "Store" store)
```

2. Chrome > Devtools > Gadget

- ### figwheel11 project: conjure setup id=g13337
  id:: 30235c9c-32f5-412f-ba0b-1e52d0e0ee84

`~/prj/study/clj/ex/study_figwheel/figwheel11/deps.edn`

Error: Hata veriyor.

[clojure.md](clojure.md)
[clojure.md](./clojure.md)
