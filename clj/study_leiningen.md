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

# Setup Leiningen

https://leiningen.org/

``` clojure
wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
mv lein ~/lein
chmod a+x ~/bin/lein
lein
``` 

``` clojure
lein help tutorial
``` 

unset `$CLASSPATH` variable

``` clojure
unset CLASSPATH
``` 

# Clojure Reference

## Clojure - Vars and the Global Environment

https://clojure.org/reference/vars

Persistent reference to a changing value. 4 ways: Vars, Refs, Agents, Atoms.

`def` creates (and interns) a Var.

If no initial value is supplied, the var is unbound:

``` clojure
user=> (def x)
  #'user/x
user=> x
  #object[clojure.lang.Var$Unbound 0x14008db3 "Unbound: #'user/x"]
``` 


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

