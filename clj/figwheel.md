---
title: "Study Figwheel"
date: 2020-01-20T11:22:12+03:00 
draft: false
description: ""
tags:
categories: clojurescript, figwheel
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

# Study Figwheel id=g13305

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [Article: Quick Start · bhauman/lein-figwheel Wiki](#article-quick-start-%C2%B7-bhaumanlein-figwheel-wiki)
  - [Error:](#error)
  - [Creating React counter](#creating-react-counter)
    - [Resetting atom state](#resetting-atom-state)
  - [Reloadable side-effect](#reloadable-side-effect)
  - [Refactor components](#refactor-components)
  - [Auto-reloading CSS](#auto-reloading-css)
    - [Error](#error)
  - [Using the REPL](#using-the-repl)
    - [Error](#error-1)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Article: Quick Start · bhauman/lein-figwheel Wiki

https://github.com/bhauman/lein-figwheel/wiki/Quick-Start

``` bash
mkdir hello_seymore
cd hello_seymore
touch project.clj
touch index.html
mkdir -p src/hello_seymore
touch src/hello_seymore/core.cljs
``` 

Edit `~/projects/study/clj/ex/study_figwheel/hello_seymore/src/hello_seymore/core.cljs`

Edit `~/projects/study/clj/ex/study_figwheel/hello_seymore/project.clj`

Run

``` bash
lein figwheel
``` 

Some clojure libraries are installed into `~/.m2`

Then figwheel compiles `hello_seymore.core` and tries to start a repl (But it won't start yet)

### Error:

``` bash
lein figwheel
``` 

``` bash
Exception in thread "main" java.lang.ClassNotFoundException: javax.xml.bind.DatatypeConverter, compiling:(cljs/util.cljc:1:1)
``` 

Solutions:

opt01: A workaround would be adding --add-modules java.xml.bind to the javac compilation

https://github.com/bhauman/lein-figwheel/issues/612

https://stackoverflow.com/questions/43574426/how-to-resolve-java-lang-noclassdeffounderror-javax-xml-bind-jaxbexception-in-j/43574427#43574427

https://www.deps.co/blog/how-to-upgrade-clojure-projects-to-use-java-11/

For JDK 11, add dependency:

``` bash
[javax.xml.bind/jaxb-api "2.4.0-b180830.0359"]
``` 

Error:

``` bash
Could not transfer artifact javax.xml.bind:jaxb-api:jar:2.4.0-b180830.0359 from/to central (https://repo1.maven.org/maven2/): GET request of: javax/xml/bind/jaxb-api/2.4.0-b180830.0359/jaxb-api-2.4.0-b180830.0359.jar from central failed
Could not transfer artifact javax.xml.bind:jaxb-api:jar:2.4.0-b180830.0359 from/to clojars (https://repo.clojars.org/): repo.clojars.org: nodename nor servname provided, or not known
``` 

opt01a: Change version

``` bash
[javax.xml.bind/jaxb-api "2.3.2"]
``` 

Error:

``` bash
Could not find artifact javax.xml.bind:jaxb-api:jar:2.3.2 in central (https://repo1.maven.org/maven2/)
``` 

opt01b: Doğru versiyonu bul

https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api/2.3.0

``` bash
;; https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api
[javax.xml.bind/jaxb-api "2.3.0"]
``` 

---

### Continue After Error

``` bash
$ ls
out  target               index.html  project.clj
src  figwheel_server.log  main.js
``` 

Check `~/projects/study/clj/ex/study_figwheel/hello_seymore/main.js`

The last lines loads the code that connects to the figwheel server:

``` bash
document.write("<script>if (typeof goog != \"undefined\") { goog.require(\"figwheel.connect.build_dev\"); }</script>");
document.write('<script>goog.require("hello_seymore.core");</script>');
``` 

Figwheel server communicates with the application that runs in the browser.

To run the program, we need an HTML file:

Edit `~/projects/study/clj/ex/study_figwheel/hello_seymore/index.html`

``` bash
    <script src="main.js" type="text/javascript"></script>
``` 

Run figwheel:

``` bash
lein figwheel
``` 

Now open from filesystem: `file:///Users/mertnuhoglu/projects/study/clj/ex/study_figwheel/hello_seymore/index.html`

After opening `index.html`, repl is enabled too in terminal.

``` bash
Prompt will show when Figwheel connects to your application
To quit, type: :cljs/quit
dev:cljs.user=> (+ 1 2)
3
``` 

REPL can affect browser:

``` bash
(js/alert "Am I connected to Figwheel?")
``` 

Open browser's dev tools

Update `~/projects/study/clj/ex/study_figwheel/hello_seymore/src/hello_seymore/core.cljs`

![Browser console logs updated](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200326222514.png)

Browser console is updated immediately:

``` bash
Hey Seymore sup?!
core.cljs?rel=1579510410022:3 Hey Seymore Mert
utils.cljs?rel=1579509860396:71 ("../hello_seymore/core.js")
``` 

Code that triggers desired side-effects is called "reloadable code"

Check logs:

``` bash
tail -f figwheel_server.log
``` 

### Creating React counter

``` bash
cp -R hello_seymore hello_seymore2
cd hello_seymore2
lein clean
``` 

Edit `~/projects/study/clj/ex/study_figwheel/hello_seymore2/project.clj`

``` bash
                  [cljsjs/react "15.2.1-1"]
                  [cljsjs/react-dom "15.2.1-1"]
                  [sablono "0.7.4"]
``` 

Edit `~/projects/study/clj/ex/study_figwheel/hello_seymore2/index.html`

``` bash
    <div id="app"></div> <!-- add this line -->
``` 

Edit `~/projects/study/clj/ex/study_figwheel/hello_seymore2/src/hello_seymore/core.cljs`

Run 

``` bash
lein figwheel
``` 

Note 01: We use `defonce` for state variables:

``` bash
(defonce app-state (atom { :likes 0}))
``` 

This keeps the state after reloading the web page.

Note 02: We use `add-watch` to reload after any updates in `app-state`

``` bash
(add-watch app-state :on-change (fn [_ _ _ _] (render!)))
``` 

See https://clojuredocs.org/clojure.core/add-watch

#### Resetting atom state

``` bash
(def app-state (atom { :likes 0 }))
``` 

This will be reset to 0 everytime the code is reloaded.

``` bash
(defonce app-state (atom { :likes 0 }))
``` 

This will keep the state during reloads.

### Reloadable side-effect

``` bash
(add-watch app-state :on-change (fn [_ _ _ _] (render!)))
``` 

`on-change` listener is reloaded side-effect.

### Refactor components

Edit `~/projects/study/clj/ex/study_figwheel/hello_seymore2/src/hello_seymore/components.cljs`

Move `like-seymore` to this new file.

Changes will be updated in browser.

### Auto-reloading CSS

Edit `~/projects/study/clj/ex/study_figwheel/hello_seymore2/project.clj`

``` bash
   :figwheel { ;; <-- add server level config here
     :css-dirs ["css"]
   }
``` 

Edit `~/projects/study/clj/ex/study_figwheel/hello_seymore2/index.html`

``` bash
    <link href="css/style.css" rel="stylesheet" type="text/css"> 
``` 

Restart figwheel

#### Error

``` bash
lein figwheel
``` 

``` bash
Figwheel: Starting CSS Watcher for paths  ["css"]
clojure.lang.ExceptionInfo: Error in component :css-watcher in system com.stuartsierra.component.SystemMap calling #'com.stuartsierra.component/start {:reason :com.stuartsierra.component/component-function-threw-exception, :function #'com.stuartsierra.component/start, :
``` 

Solution:

Create the file `css/style.css`

---

Edit `~/projects/study/clj/ex/study_figwheel/hello_seymore2/css/style.css`

``` bash
  background-color: red;
``` 

Change css file. The browser will be updated automatically.

### Serving assets with Figwheel's built-in webserver

Move assets into `resources/public`

``` bash
cp -R hello_seymore2 hello_seymore3
cd hello_seymore3
lein clean
``` 

``` bash
mkdir -p resources/public
mv index.html css resources/public
``` 

Edit `~/projects/study/clj/ex/study_figwheel/hello_seymore3/project.clj`

Put output compiled cljs into `resources/public`

``` bash
:clean-targets ^{:protect false} [:target-path "out" "resources/public/cljs"] 
:cljsbuild {
						:builds [{:id "dev"
											:source-paths ["src"]
											:figwheel true
											:compiler {:main hello-seymore.core 
																 ;; add the following 
																 :asset-path "cljs/out"
																 :output-to  "resources/public/cljs/main.js"
																 :output-dir "resources/public/cljs/out"}}]} 
``` 

Now, edit `~/projects/study/clj/ex/study_figwheel/hello_seymore3/resources/public/index.html`

``` bash
<script src="cljs/main.js" type="text/javascript"></script>
``` 

Open http://localhost:3449/index.html

### Using the REPL

Better repl: `rlwrap` from https://github.com/hanslub42/rlwrap

``` bash
rlwrap lein figwheel
``` 

``` bash
(in-ns 'hello-seymore.core)
(reset! app-state {:likes 10000})
``` 

#### Error

``` bash
(in-ns 'hello-seymore.core')
(reset! app-state {:likes 10000})
``` 

``` bash
  #object[Error Error: No protocol method IReset.-reset! defined for type undefined: ]
Error: No protocol method IReset.-reset! defined for type undefined:
``` 

Sebep: Yanlış namespace yazmışım.

---

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

## Article: Official figwheel README

https://github.com/bhauman/lein-figwheel

### Learning ClojureScript

> If you are brand new to ClojureScript it is highly recommended that you do the ClojureScript Quick Start first. If you skip this you will probably suffer.

> There is a lot to learn when you are first learning ClojureScript, I recommend that you bite off very small pieces at first. Smaller bites than you would take when learning other languages like JavaScript and Ruby.

> Please don't invest too much time trying to set up a sweet development environment, there is a diverse set of tools that is constantly in flux and it's very difficult to suss out which ones will actually help you. If you spend a lot of time evaluating all these options it can become very frustrating. If you wait a while, and use simple tools you will have much more fun actually using the language itself

### Usage

`:dependencies` into `project.clj`

``` bash
[org.clojure/clojure "1.9.0"]
[org.clojure/clojurescript "1.10.238"]
``` 

`:plugins`

``` bash
[lein-figwheel "0.5.18"]
``` 

`:cljsbuild`

``` bash
:cljsbuild {
	:builds [ { :id "example" 
							:source-paths ["src/"]
							:figwheel true
							:compiler {  :main "example.core"
													 :asset-path "js/out"
													 :output-to "resources/public/js/example.js"
													 :output-dir "resources/public/js/out" } } ]
}
``` 

# Article: Running figwheel in a Cursive Clojure REPL

https://github.com/bhauman/lein-figwheel/wiki/Running-figwheel-in-a-Cursive-Clojure-REPL

``` 
cd ~/projects/study/clj/ex/study_figwheel/figwheel_cursive
lein new figwheel figwheel-test
cd $_
``` 

Check `~/projects/study/clj/ex/study_figwheel/figwheel_cursive/figwheel-test/project.clj`

``` 
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.520"]
                 [org.clojure/core.async  "0.4.500"]
                 [figwheel-sidecar "0.5.18"]]


  :plugins [
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src" "script"]
``` 

Check `~/projects/study/clj/ex/study_figwheel/figwheel_cursive/figwheel-test/script/repl.clj`

Intellij: Create a repl configuration:

Run > Edit configurations > + > Clojure REPL > Local REPL > .name `repl` > select `use clojure.main` > .parameters: `script/repl.clj`

Run `repl`




