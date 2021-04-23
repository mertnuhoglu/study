---
title: "Study: Compojure Library"
date: 2021-04-15T09:41:11+03:00 
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

---

# Getting Started

opt01: lein

```bash
cd /Users/mertnuhoglu/projects/study/clj/ex/compojure/e01
lein new compojure hello-world
cd hello-world
lein ring server-headless
```

opt02: standalone

```bash
lein ring uberjar
java -jar path/to/hello-world-0.1.0-standalone.jar
```

# Test Codes

Check `/Users/mertnuhoglu/projects/study/clj/ex/compojure/e01/hello-world/src/mert/ring_study.clj`



