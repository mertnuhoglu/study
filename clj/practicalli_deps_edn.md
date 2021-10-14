--- 
title: "practicalli deps.edn"
date: 2020-11-22T20:13:59+03:00 
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

# Article: README

[practicalli/clojure-deps-edn: A collection of useful configuration and aliases for deps.edn based projects](https://github.com/practicalli/clojure-deps-edn)

## Install

```bash
git clone https://github.com/mertnuhoglu/clojure-deps-edn clojure
```

Updating:

```bash
clojure -M:project/outdated
```

## Usage

### Run reveal data repl:

```bash
clojure -M:inspect/reveal:repl/rebel
```

### Run cognitect rebl: id=g11925

```bash
clojure -M:inspect/rebl-java8
clojure -M:inspect/rebl
```

Error: Current version (0.9.242) throws exception.

Fix:

```bash
java8
clojure -M:inspect/rebl-java8
```

Yine hata veriyor, ama en azından çalışıyor.

#### Run REBL for nREPL based editors CIDER and Calva id=g11932

ref: `Run REBL for nREPL <url:file:///~/projects/study/clj/rebl.md#r=g11934>`

### Run portal data repl: id=g11751

[Portal · Practicalli Clojure](http://practicalli.github.io/clojure/clojure-tools/data-browsers/portal.html)

Note: Bazı projelerin altında çalışmayabiliyor. Şurada çalışıyor: `/Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06`

```bash
clojure -M:inspect/portal-web
```

```clj
(require '[portal.web :as portal])
(portal/open)
(portal/tap)
(tap> {:accounts [{:name "jen" :email "jen@jen.com"} {:name "sara" :email "sara@sara.com"}]})
```




