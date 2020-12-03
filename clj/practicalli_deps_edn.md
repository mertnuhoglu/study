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

### Run cognitect rebl:

```bash
clojure -M:inspect/rebl-java8
clojure -M:inspect/rebl
```

Error: Current version (0.9.242) throws exception.

### Run portal data repl: id=g_11751

[Portal · Practicalli Clojure](http://practicalli.github.io/clojure/clojure-tools/data-browsers/portal.html)

```bash
clojure -M:inspect/portal-web
```

```clj
(require '[portal.web :as portal])
(portal/open)
(portal/tap)
(tap> {:accounts [{:name "jen" :email "jen@jen.com"} {:name "sara" :email "sara@sara.com"}]})
```




