--- 
title: "Study deps.edn and cli"
date: 2020-11-19T13:41:11+03:00 
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

- # Quickstart deps.edn id=g11724
  id:: a085c5fa-d057-4c9f-9194-bf81040d07c8

`New Project <url:file:///~/projects/study/clj/deps_cli.md#r=g11941>`

`Index deps.edn <url:#r=g11723>`

	| man clj                           | clj help                       |
	| clj -A:alias                      | run alias                      |
	| clj -M:repl/rebel                 | run repl with rebel-readline   |
	| clj -Sdescribe                    | print version and environment  |
	| clj -X:fct                        | run (eXecute) function         |
	| clj -M -m qualified_main          | run main/script                |
	| ^x ^d/s/a/e                       | rebel: doc/source/apropos/eval |
	| :deps                             | deps.edn dependencies          |
	| :local/root                       | local deps                     |
	| :git/url                          | github deps                    |
	| -A:find-deps -h                   | find deps help                 |
	| -A:find-deps <dep>                | find dep spec                  |
	| -F:save                           | save dep into deps.edn         |
	| -F:table "text"                   | apropos search                 |
	| -X:project/new create :name <prj> | create new app from template   |

`Article: What are the Clojure Tools? <url:clojure_cli_tools.md#r=g11929>`

Practicalli deps.edn: `~/projects/study/clj/practicalli_deps_edn.md`

`REPL functions: <url:clojure_cli_tools.md#r=g11930>`

`Socket REPL <url:file:///~/projects/study/clj/clojure_cli_tools.md#r=g11931>`

- # Index deps.edn id=g11723
  id:: d6a310c9-11f6-4f90-bfa9-57997c650c1b

- # Issues CLI Tools  id=g11940
  id:: 7bfcd543-6dcb-420c-ad3d-f8b1e3ab8f31

- ## New Project id=g11941
  id:: b5e89103-66a6-4329-be41-40f8d2e64945

```bash
clojure -Tnew app :name mertnuhoglu/datawalk-01
clojure -M:project/find-deps datawalk
cd datawalk-01
clojure -M::datawalk:repl/rebel
```

# Deps and CLI Reference

> -A (for REPL invocation), -X (for function execution), or -M (for clojure.main execution). 

- ## Run main function id=g12070
  id:: 03507dde-c1f4-4ae9-a68e-39f7bd2ff71b

`Run main function: <url:file:///~/projects/study/clj/deps_cli.md#r=g11745>`

```bash
clj -M -m clj-new-01.clj-new-01
```

opt02: `Run a clojure file as script  <url:file:///~/projects/study/clj/deps_cli.md#r=g12069>`

```clj
clojure src/cards/core.clj
```

## Repl

opt01: rebel-readline support

```bash
clj -A:rebel
clj -M:repl/rebel ;; practicalli/deps.edn
```

ref: `Article: rebel-readline README <url:file:///~/projects/study/clj/deps_cli.md#r=g11718>`

	| ^x ^d             | show doc           |
	| ^x ^s             | show source        |
	| ^x ^a             | show apropos       |
	| ^x ^e             | inline eval        |
	| :repl/help        | help               |
	| :repl/keybindings | keybindings        |
	| :repl<tab>        | available commands |

## Check version

```bash
clj -Sdescribe
```

- ## Running functions id=g12071
  id:: 89beddd4-c284-4253-b8e2-1faffdd56e5a

opt01a: `Executing a function <url:file:///~/projects/study/clj/deps_cli.md#r=g11721>`

```bash
clj -X:my-fn
```

opt01b: `Run any function as alias: <url:file:///~/projects/study/clj/deps_cli.md#r=g11746>`

opt02: `Running a main or script <url:file:///~/projects/study/clj/deps_cli.md#r=g11722>`

```bash
clojure -M -m clj-new-01.clj-new-01
```

## Using libraries

opt01: ref: `Using local libraries <url:file:///~/projects/study/clj/deps_cli.md#r=g11719>`

```clojure
{:deps
 {time-lib/time-lib {:local/root "../time-lib"}}}
```

opt02: ref: `Using git libraries <url:file:///~/projects/study/clj/deps_cli.md#r=g11720>`

```clojure
{:deps
 {github-yourname/time-lib
  {:git/url "https://github.com/yourname/time-lib" :sha "04d2744549214b5cba04002b6875bdf59f9d88b6"}}}
```

## Search clojars/maven dependencies:

opt01: `lein`

```bash
lein search java-time
```

opt02: clojars web site

opt03: `find-deps`

```bash
clj -A:find-deps -F:table -l 10 "java time"
clj -A:find-deps java-time
clj -A:find-deps -F:save priority-map
```

ref: `find-deps <url:file:///~/projects/study/clj/deps_cli.md#r=g11716>`

## Create a new project: clj-new

```bash
clojure -X:new create :name clj-new-01/clj-new-01
```

ref: `clj-new: Generate new projects based on templates  <url:file:///~/projects/study/clj/deps_cli.md#r=g11717>`

# Article: Clojure - Deps and CLI Guide

[Clojure - Deps and CLI Guide](https://clojure.org/guides/deps_and_cli)

## Running a REPL

ex01:

Run repl:

```bash
cd ~/projects/study/clj/ex/study_deps_cli/run_repl
clj
```

```clojure
(+ 2 3)
```

ex02: Use a library

Edit `~/projects/study/clj/ex/study_deps_cli/repl_deps/deps.edn`

```edn
{:deps
 {clojure.java-time/clojure.java-time {:mvn/version "0.3.2"}}}
```

```bash
clj
  ##> Downloading: ...
```

```clojure
(require '[java-time :as t])
(str (t/instant))
```

## Writing a program

Edit `~/projects/study/clj/ex/study_deps_cli/deps-hello-world/deps.edn`

Edit `~/projects/study/clj/ex/study_deps_cli/deps-hello-world/src/hello.clj`

Edit `~/projects/study/clj/ex/study_deps_cli/deps-hello-world/run.sh`

Main file:

```bash
clj -X hello/run
  ##> Hello world, the time is 10:53 PM
```

`-X` executes an entry function.

- ## Using local libraries id=g11719
  id:: c87032c3-e760-4e9b-bf72-dd0623821682

Create a local library:

Check `~/projects/study/clj/ex/study_deps_cli/time-lib/deps.edn`

Check `~/projects/study/clj/ex/study_deps_cli/time-lib/src/hello_time.clj`

Create main client:

Check `~/projects/study/clj/ex/study_deps_cli/deps-local-libraries/deps.edn`

```clojure
{:deps
 {time-lib/time-lib {:local/root "../time-lib"}}}
```

Check `~/projects/study/clj/ex/study_deps_cli/deps-local-libraries/src/hello.clj`

```clojure
(ns hello
  (:require [hello-time :as ht]))
```

```bash
clj -X hello/run
```

- ## Using git libraries id=g11720
  id:: 46831089-91ac-4d2b-b3d8-cc82096b92e3

```clojure
{:deps
 {github-yourname/time-lib
  {:git/url "https://github.com/yourname/time-lib" :sha "04d2744549214b5cba04002b6875bdf59f9d88b6"}}}
```

## Include a test source directory

Adding extra paths to the classpath. 

Add an alias `:test` that includes extra relative source path:

Check `~/projects/study/clj/ex/study_deps_cli/extra-paths/deps.edn`

```clojure
{:deps
 {org.clojure/core.async {:mvn/version "1.3.610"}}

 :aliases
 {:test {:extra-paths ["test"]}}}
```

```bash
clj -A:test -Spath
  ##> test:src:/Users/mertnuhoglu/.m2/repository/clojure/java-time/clojure.java-time/0.3.2/clojure.java-time-0.3.2.jar:
```

## Add optional dependency

Adding a dependency to your classpath:

```clojure
{:aliases
 {:bench {:extra-deps {criterium/criterium {:mvn/version "0.4.4"}}}}}
```

Use it as `-A:bench`

```bash
clj -A:bench
```

## Include a local jar on disk

A jar that is not present in maven repository such as a database driver jar.

```clojure
{:deps
 {db/driver {:local/root "/path/to/db/driver.jar"}}}
```

# Article: Clojure - Deps and CLI Reference

[Clojure - Deps and CLI Reference](https://clojure.org/reference/deps_and_cli)

clj: for repl

clojure: for other uses

## Start a repl:

```bash
clj
```

- ## Executing a function id=g11721
  id:: cbeab27a-7c2c-449a-96c4-bc0f10a2c6ae

Ex: A function that takes a map: 

```bash
clojure [clj-opt*] -X[:aliases] [a/fn] [kpath v]*
```

`-X` is configured with an arg map:

```clojure
;; deps.edn
{:aliases
 {:my-fn
  {:exec-fn my.qualified/fn
   :exec-args {:my {:data 123}
               :config 456}}}}
```

To invoke it, pass alias to `-X`

```bash
clj -X:my-fn
```

Overriding an arg:

```bash
clj -X:my-fn :config 789
```

Execute an arbitrary function:

```bash
clj -X my.qualified/fn :config 789
```

- ## Running a main or script id=g11722
  id:: d1920c11-1fd2-4cd1-be2a-62a5c7da971d

`-M` calls a `-main` function or a clojure script

```bash
clojure [clj-opt*] -M[:aliases] [main-opts]
```

ref: `clj-new: Generate new projects based on templates  <url:file:///~/projects/study/clj/deps_cli.md#r=g11717>`

```bash
clojure -M -m clj-new-01.clj-new-01
```

- # find-deps id=g11716
  id:: 6a906127-9558-4a24-a9c4-42907f01b28b

[hagmonk/find-deps: tools.deps friendly utility for searching clojars and maven](https://github.com/hagmonk/find-deps)

Usage:

```bash
clj -A:find-deps -h
clj -A:find-deps http-kit
  ##> {:deps {http-kit/http-kit {:mvn/version "2.5.0"}}}
clj -A:find-deps http-kit tools.logging priority-map
  ##> {:deps
  ##>  {http-kit/http-kit {:mvn/version "2.5.0"},
  ##>   org.clojure/tools.logging {:mvn/version "1.1.0"},
  ##>   org.clojure/data.priority-map {:mvn/version "1.0.0"}}}
```

## Install

opt01: global install

Edit `~/.clojure/deps.edn`

```bash
{:aliases {:find-deps {:extra-deps
                         {find-deps
                            {:git/url "https://github.com/hagmonk/find-deps",
                             :sha "6fc73813aafdd2288260abb2160ce0d4cdbac8be"}},
                       :main-opts ["-m" "find-deps.core"]}}}
```

opt02: local install

Edit `~/projects/study/clj/ex/study_deps_cli/find-deps-01/deps.edn`

## Add dependency to deps.edn

Check `~/projects/study/clj/ex/study_deps_cli/find-deps-01/Makefile`

```bash
clj -A:find-deps -F:save priority-map
```

Check `~/projects/study/clj/ex/study_deps_cli/find-deps-01/deps.edn`

## String search

```bash
clj -A:find-deps -F:table -l 10 "apache kafka"
  ##> |                              :lib | :version |
  ##> |-----------------------------------+----------|
  ##> |      org.apache.kafka/kafka-tools |    1.1.0 |
  ##> |      org.apache.kafka/kafka_2.9.2 |  0.8.2.2 |
  ##> |      org.apache.kafka/kafka_2.9.1 |  0.8.2.2 |
```

## API use in REPL

Check `~/projects/study/clj/ex/study_deps_cli/find-deps-repl/deps.edn`

```bash
cd ~/projects/study/clj/ex/study_deps_cli/find-deps-repl
clj -A:rebel
```

```bash
(use 'find-deps.core)
(print-deps "http-kit")
  ##> |              :lib | :version |
  ##> |-------------------+----------|
  ##> | http-kit/http-kit |    2.3.0 |
```



- # Article: rebel-readline README id=g11718
  id:: 8eb5bf3e-62ab-416e-bbbb-ed2bc71b6edd

[bhauman/rebel-readline: Terminal readline library for Clojure dialects](https://github.com/bhauman/rebel-readline)

## Keybinding rebel-readline

	| ^c         | abort current line             |
	| ^d         | quit                           |
	| ^tab       | word completion or code indent |
	| ^x ^d      | show doc                       |
	| ^x ^s      | show source                    |
	| ^x ^a      | show apropos                   |
	| ^x ^e      | inline eval                    |
	| :repl/help | help                           |
	| :repl<tab> | available commands             |

Note: `doc` `apropos` functions don't work with rebel. You need to require `clojure.repl` for them to work.

```clj
(require '[clojure.repl :refer :all])
```

## Help rebel-readline

# Article: New Clojure REPL Experience With Clj Tools and Rebel Readline | jr0cket

[New Clojure REPL Experience With Clj Tools and Rebel Readline | jr0cket](http://jr0cket.co.uk/2018/07/New-Clojure-REPL-experience-with-Clj-tools-and-rebel-readline.html)

## Example 01: rebel-readline

Check `~/projects/study/clj/ex/study_deps_cli/rebel-readline-01/deps.edn`

```clojure
 :aliases {:rebel {:extra-deps {com.bhauman/rebel-readline {:mvn/version "0.1.4"}}
                   :main-opts  ["-m" "rebel-readline.main"]}}
```

Check `~/projects/study/clj/ex/study_deps_cli/rebel-readline-01/run.sh`

```bash
clojure -A:rebel
```

## rebel-readline

Run directly:

```bash
clojure -Sdeps "{:deps {com.bhauman/rebel-readline {:mvn/version \"0.1.4\"}}}" -m rebel-readline.main
```

Install in deps.edn:

```clojure
 :aliases {:rebel {:extra-deps {com.bhauman/rebel-readline {:mvn/version "0.1.4"}}
                   :main-opts  ["-m" "rebel-readline.main"]}}
```

```bash
clojure -A:rebel
```

Note: Doesn't work with `clj` (no problem with my setup)

## Example: lein project

```bash
lein new app rebel-readline-lein
```

opt01: `project.clj`

Edit `~/projects/study/clj/ex/study_deps_cli/rebel-readline-lein/project.clj`

```clj
 :dependencies [ [com.bhauman/rebel-readline "0.1.4"]]
 :aliases {"rebl" ["trampoline" "run" "-m" "rebel-readline.main"]}
```

```bash
lein rebl
```

opt02: `~/.lein/profiles.clj`

Then you can run `lein rebl` globally

## What version of Clojure CLI tools are installed?

```bash
clj -Sdescribe
  ##> {:version "1.10.1.727"
  ##>  :config-files ["/usr/local/Cellar/clojure/1.10.1.727/deps.edn" "/Users/mertnuhoglu/.config/clojure/deps.edn" ]
  ##>  :config-user "/Users/mertnuhoglu/.config/clojure/deps.edn"
```

## deps.edn

Top-level keys: `:deps` `:paths` `:aliases`

Global configurations: `~/.clojure/deps.edn`

Edit `~/.clojure/deps.edn`

## Calling main function

Check `~/projects/study/clj/ex/study_deps_cli/main-function/deps.edn`

Check `~/projects/study/clj/ex/study_deps_cli/main-function/src/practicalli/what_time_is_it.clj`

```clojure
(defn -main []
  (println "The time according to Clojure java-time is:"
           (time/local-date-time)))
```

```bash
clojure -m practicalli.what-time-is-it
```

- # clj-new: Generate new projects based on templates  id=g11717
  id:: ec7ae3c9-5978-49c9-83b8-d23b649f0c63

Using practicalli deps:

```bash
clojure -X:project/new :name e01/rebl-examples
rebl-examples
├── CHANGELOG.md
├── LICENSE
├── README.md
├── deps.edn
├── doc
│   └── intro.md
├── pom.xml
├── resources
├── src
│   └── e01
│       └── rebl_examples.clj
└── test
    └── e01
        └── rebl_examples_test.clj
```

[seancorfield/clj-new: Generate new projects based on clj, Boot, or Leiningen Templates!](https://github.com/seancorfield/clj-new)

Setup: Edit `~/projects/private_dotfiles/.config/clojure/deps.edn`

```clojure
            :new {:extra-deps {seancorfield/clj-new
                               {:mvn/version "1.1.228"}}
                  :ns-default clj-new
                  :exec-args {:template "app"}}}
```

Usage:

```bash
clojure -X:new create :name myname/myapp
```

## Ex01:

Check `~/projects/study/clj/ex/study_deps_cli/clj-new-01/deps.edn`

```bash
clojure -X:new create :name clj-new-01/clj-new-01
```

Run:

```bash
cd clj-new-01
clojure -M -m clj-new-01.clj-new-01
```

Run tests:

```bash
clojure -M:test:runner
```

Check test scripts: `~/projects/study/clj/ex/study_deps_cli/clj-new-01/test/clj_new_01/clj_new_01_test.clj`

- ### Run main function: id=g11745
  id:: 09d17dc3-015f-4ee5-b6ec-ce17231c5246

```bash
clj -M -m clj-new-01.clj-new-01
```

- ### Run any function as alias: id=g11746
  id:: b5e19821-d4e6-4dc3-aa31-28cfc3e66b93

Check: `deps.edn > aliases > :my-fn <url:/Users/mertnuhoglu/projects/study/clj/ex/study_deps_cli/clj-new-01/deps.edn#tn=:my-fn>`

Check: `my-fn definition: <url:/Users/mertnuhoglu/projects/study/clj/ex/study_deps_cli/clj-new-01/src/clj_new_01/clj_new_01.clj#tn=(defn my-fn [& args]>`

Run:

```bash
clj -X:my-fn
```

Note: You have to put rest args even if you don't use them when you use `:aliases`

```clj
(defn my-fn [& args]	 ;; ok
(defn my-fn-no-arg []  ;; error
```

You can skip `:exec-args` in `:aliases`

```bash
clj -X:my-fn2			;; ok
```

- ### Run a clojure file as script  id=g12069
  id:: 57a10623-30ec-4a9b-8cef-20be5e9a5550

[ubuntu - How to run a Clojure File? - Stack Overflow](https://stackoverflow.com/questions/30445590/how-to-run-a-clojure-file)

[command line - How can I run a .clj Clojure file I created? - Stack Overflow](https://stackoverflow.com/questions/7656523/how-can-i-run-a-clj-clojure-file-i-created/7657016)

Ex: `/Users/mertnuhoglu/projects/rafal_dittwald_solving_problems_the_clojure_way`

opt01: From repl:

```bash
clj -M:repl/rebel
```

```clj
(load "cards/core")
```

opt02: From terminal directly:

```clj
clojure src/cards/core.clj
```

opt03: Use babashka:

```clj
bb src/cards/core.clj
```

