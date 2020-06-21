---
title: "Studying Fulcro"
date: 2020-04-11T10:13:07+03:00 
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

# Article: Fulcro Developers Guide Getting Started

[Fulcro Developers Guide Getting Started](http://book.fulcrologic.com/#_getting_started)

```clojure
mkdir app
cd app
mkdir -p src/main src/dev resources/public src/app
npm init
npm install shadow-cljs react react-dom --save
```

Check `ex/study_fulcro/app/deps.edn`

Check `ex/study_fulcro/app/shadow-cljs.edn`

Check `ex/study_fulcro/app/resources/public/index.html`

Check `ex/study_fulcro/app/src/main/app/client.cljs`

```bash
npx shadow-cljs server
```

opt02:

```bash
npx shadow-cljs start
```


