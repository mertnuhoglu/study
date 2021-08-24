---
title: "Study Devcards"
date: 2020-04-30T16:28:24+03:00 
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


- [Video: Literate interactive coding - Devcards' by Bruce Hauman-G7Z_g2fnEDg](#video-literate-interactive-coding---devcards-by-bruce-hauman-g7z_g2fnedg)
  - [Devcards: it is like rmarkdown notebook](#devcards-it-is-like-rmarkdown-notebook)
- [Article: Official README](#article-official-readme)
  - [Quick Start](#quick-start)
  - [Examples - devcards (Quick Trial)](#examples---devcards-quick-trial)
    - [Testing examples](#testing-examples)
      - [API Documentation](#api-documentation)
        - [Devcard Options](#devcard-options)
  - [Usage](#usage)
- [Examples](#examples)
  - [devcards'ın örneklerini yeniden üretme](#devcards%C4%B1n-%C3%B6rneklerini-yeniden-%C3%BCretme)
    - [src: devcards'ın dokundaki örnekleri buraya aktaralım id=log_0002](#src-devcards%C4%B1n-dokundaki-%C3%B6rnekleri-buraya-aktaral%C4%B1m-idlog_0002)
      - [Mevcut örneği de çalıştır eş zamanlı](#mevcut-%C3%B6rne%C4%9Fi-de-%C3%A7al%C4%B1%C5%9Ft%C4%B1r-e%C5%9F-zamanl%C4%B1)
      - [Multiple Pages](#multiple-pages)
    - [src: kendim bakmadan bmi örneğini üretmeye çalışayım 20200408  id=log_0004](#src-kendim-bakmadan-bmi-%C3%B6rne%C4%9Fini-%C3%BCretmeye-%C3%A7al%C4%B1%C5%9Fay%C4%B1m-20200408--idlog_0004)
  - [src: reframe ile bmi örneğini uygula id=log_0008](#src-reframe-ile-bmi-%C3%B6rne%C4%9Fini-uygula-idlog_0008)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# Video: Literate interactive coding - Devcards' by Bruce Hauman-G7Z_g2fnEDg

Two problems:

1. continous browser reloading

2. developing inside the main application (as main avenue of feedback)

Problem:

- code, reload, manipulate, verify cycle

- UI coding = endless tweaking

Solution:

- hot code reloading

figwheel sohlves this

## Devcards: it is like rmarkdown notebook

visual namespaces: contain code examples to navigate

`defcard` macro

![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture//20200305101536.png)

React components rendered in notebook.

![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture//20200305101628.png)

Print data:

![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture//20200305101835.png)

Test UI component and undo/play:

![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture//20200305102020.png)

Benefits:

- encourages independent components

- Flexible dev environment

- validation document: check behavior of individual components

- reference document: how to use code

# Article: Official README

https://github.com/bhauman/devcards

## Quick Start

``` bash
cd ~/projects/study/clj/ex/study_devcards/e01
lein new devcards hello-world
cd hello-world
lein figwheel
``` 

Open http://localhost:3449/cards.html

Edit `~/projects/study/clj/ex/study_devcards/e01/hello-world/src/hello_world/core.cljs`

## Examples - devcards (Quick Trial)

``` bash
git clone https://github.com/bhauman/devcards.git
``` 

``` bash
cd ~/codes/clojure/devcards
lein figwheel
``` 

Open http://localhost:3449/devcards/index.html

Check `/Users/mertnuhoglu/codes/clojure/devcards/project.clj`

### Testing examples

Ex: `devdemos.core`

Check: `~/codes/clojure/devcards/example_src/devdemos/core.cljs`

#### API Documentation

Check `~/codes/clojure/devcards/example_src/devdemos/defcard_api.cljs`

React element:

``` bash
(defcard react-example (sab/html [:h3 "Example: Rendering a ReactElement"]))
``` 

Atoms:

``` bash
(defonce observed-atom
  (let [a (atom {:time 0})]
    (js/setInterval (fn [] (swap! observed-atom update-in [:time] inc)) 1000)
    a))

(defcard atom-observing-card observed-atom {} {:history false})
``` 

React element with state:

``` bash
(defonce first-example-state (atom {:count 55}))

(defcard example-counter
  (fn [data-atom owner]
    (sab/html [:h3 "Example Counter w/Shared Initial Atom: " (:count @data-atom)]))
  first-example-state)

(defcard example-incrementer
  (fn [data-atom owner]
    (sab/html [:button {:onClick (fn [] (swap! data-atom update-in [:count] inc)) } "increment"]))
  first-example-state)

``` 

##### Devcard Options

Inspect data:

``` bash
    :inspect-data false ;; whether to display the data in the card atom
``` 

## Usage

Step 1: Add `:dependencies` into `project.clj`

``` bash
[org.clojure/clojurescript "1.10.238"]
[devcards "0.2.5"]
``` 

Step 2: Make an HTML file to host devcards

Ex: `resources/public/cards.html` such as `~/projects/study/clj/ex/study_devcards/e01/hello-world/resources/public/cards.html`

``` bash
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset="UTF-8">
    <link href="/css/example.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    <script src="/js/compiled/example.js" type="text/javascript"></script>
  </body>
</html>
``` 

# Examples

## sıfır proje 

``` 
cd ~/projects/study/clj/ex/study_devcards
lein new devcards e03
cd e03
lein figwheel
``` 

## devcards'ın örneklerini yeniden üretme id=g10843

``` bash
cd ~/projects/study/clj/ex/study_devcards
mkdir e02
cd $_
lein new devcards devcards_official_examples
cd devcards_official_examples
lein figwheel
``` 

Open http://localhost:3449/cards.html

Check `/Users/mertnuhoglu/projects/study/clj/ex/study_devcards/e02/devcards_official_examples/project.clj`

### src: devcards'ın dokundaki örnekleri buraya aktaralım id=log_0002

trg: `devcards'ın dokundaki örnekleri buraya aktaralım  <url:file:///~/projects/study/logbook/log_20200324.md#r=log_0003>`

#### Mevcut örneği de çalıştır eş zamanlı

Yeni bir port ayarla

Edit `/Users/mertnuhoglu/codes/clojure/devcards/project.clj`

``` bash
                     :server-port 3451}
``` 

Open http://localhost:3451/devcards/index.html

#### Multiple Pages

`~/projects/study/clj/ex/study_devcards/e02/devcards_official_examples/src/devcards_official_examples/core.cljs`

Bunun içinde diğer ns'leri require edeceğiz:

``` bash
(ns devcards_official_examples.core
  (:require
   [devcards_official_examples.headings]
``` 

Bu `headings` sayfasını `core.cljs` sayfasında gösterecek:

`~/projects/study/clj/ex/study_devcards/e02/devcards_official_examples/src/devcards_official_examples/headings.cljs`

### src: kendim bakmadan bmi örneğini üretmeye çalışayım 20200408  id=log_0004

		done: trg: kendim bakmadan bmi örneğini üretmeye çalışayım 20200408   <url:file:///~/projects/study/logbook/log_20200324.md#r=log_0005>

## src: reframe ile bmi örneğini uygula id=log_0008

		done: trg: reframe ile bmi örneğini uygula 20200409   <url:file:///~/projects/study/logbook/log_20200324.md#r=log_0009>


