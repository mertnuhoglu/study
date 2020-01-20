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

Now, repl is enabled too in terminal.

``` bash
Prompt will show when Figwheel connects to your application
To quit, type: :cljs/quit
dev:cljs.user=> (+ 1 2)
3
``` 

REPL can affect browser:

``` bash
=> (js/alert "Am I connected to Figwheel?")
nil
``` 

Open browser's dev tools

Update `~/projects/study/clj/ex/study_figwheel/hello_seymore/src/hello_seymore/core.cljs`

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

