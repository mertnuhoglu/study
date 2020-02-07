---
title: "Study leiningen"
date: 2020-02-07T12:47:23+03:00 
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

# Article: leiningen/TUTORIAL.md at stable · technomancy/leiningen

https://github.com/technomancy/leiningen/blob/stable/doc/TUTORIAL.md

``` bash
lein help
lein help $TASK
lein readme
lein sample
``` 

## Creating a Project

``` bash
lein new app ex01
``` 

Check `~/projects/study/clj/ex/study_leiningen/ex01/project.clj`

## Dependencies

Search Clojars: `lein search $TERM`

Download dependencies: `lein deps`

Artifact id and version: `clojure "1.10.0"`

Group id included: `org.clojure/clojure "1.10.0"`

Clojure libraries: often have the same group-id and artifact-id. Then you can omit group-id.

Repositories: clojars.org and Maven Central.

Additional repos in: `:repositories`

Checkout dependencies: Sometimes your own project depends on another of your projects. 

## JVM Options

``` bash
 :jvm-opts ["-Xmx1g"]
``` 

## Running Code

``` bash
lein repl
``` 

``` bash
(require 'ex01.core)
(ex01.core/-main)
``` 

``` bash
lein run
``` 

