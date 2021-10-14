---
title: "Studying Pathom"
date: 2021-09-08T14:44:24+03:00 
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

# Pathom 3 User Guide

## Article: Getting Started | Pathom 3 id=g12386

[Getting Started | Pathom 3](https://pathom3.wsscode.com/docs/)

``` clojure
clojure -X:project/new :name main/pathom01
``` 

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_pathom/pathom01/src/main/pathom01.clj`

## Pathom Tutorial - IP Weather | Pathom 3

[Pathom Tutorial - IP Weather | Pathom 3](https://pathom3.wsscode.com/docs/tutorial)

Run: `clj -X:ip-weather :ip '"198.29.213.3"'`

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_pathom/pathom01/src/main/pathom02.clj`

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_pathom/pathom01/src/main/pathom03.clj`

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_pathom/pathom01/src/main/pathom04.clj`

``` clojure
  # some specific IP
clj -X:ip-weather :ip '"198.29.213.3"'
  # => It's currently 8.33C at {:ip "198.29.213.3"}

  # get from your IP
clj -X:ip-weather :ip "\"$(curl -s ifconfig.me)\""
  # => It's currently ??C at {:ip "YOUR_IP"}
``` 

![resolver graph](https://pathom3.wsscode.com/assets/images/index-oir-connected-2e85da1a6cfb398486ea5a061f8a3550.png)

### Resolvers

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_pathom/pathom01/src/main/pathom05.clj`

``` clojure
(def user-birthdays
  {1 1969
   2 1954
   3 1986})

; defresolver is the main option to create new resolvers
(pco/defresolver user-birthday [{:keys [acme.user/id]}]
  {:acme.user/birth-year (get user-birthdays id)})
``` 

Resolvers represent edges in a graph:

	[user/id] - user/birthday -> [user/birth-year]

![graph2](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210909_130445.jpg)

### Built-in Resolvers

[Built-in Resolvers | Pathom 3](https://pathom3.wsscode.com/docs/built-in-resolvers)

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_pathom/pathom01/src/main/builtin_resolver01.clj`

### Using defresolver

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_pathom/pathom01/src/main/pathom06.clj`

### Built-in Resolvers

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_pathom/pathom01/src/main/builtin_resolver01.clj`

### Smart Maps

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_pathom/pathom01/src/main/smart_maps01.clj`

### EQL

[EQL | Pathom 3](https://pathom3.wsscode.com/docs/eql)

# Article: Pathom v2 Documentation  id=g12388

[Introduction | Pathom](https://blog.wsscode.com/pathom/v2/pathom/2.2.0/introduction.html)

``` clojure
clojure -X:project/new :name main/pathom02
``` 

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_pathom/pathom02/deps.edn`

com.wsscode/pathom {:mvn/version "2.4.0"}

## Introduction

ref: `/Users/mertnuhoglu/projects/study/clj/ex/study_pathom/pathom02/src/main/pathom01.clj`

## How it works

ex: `[:answer-to-everything :answer-plus-one]`

Buradaki her bir atributu tek tek alır parser ve reader'ı çağırır.

Reader bir entry alır. 

Entry: propery, join veya ident olabilir.

Pathom, veriyi çıkartmak için bağımlılık çizgesinin (dependency graph) dolaşır. Buna Connect denir. Her resolver, çizgenin bir kenarıdır (edge). 

## Connect

[Pathom Connect | Pathom](https://blog.wsscode.com/pathom/v2/pathom/2.2.0/connect.html)

## Resolvers

ex:

``` clojure
(pc/defresolver person-resolver
  [{:keys [database] :as env} {:keys [person/id]}]
  {::pc/input #{:person/id}
   ::pc/output [:person/first-name :person/age]}
  (let [person (my-database/get-person database id)]
    {:person/age        (:age person)
     :person/first-name (:first-name person)}))
``` 

### Derived Attributes

Hesaplanan atributları resolver içinde tanımlayabilirsin:

``` clojure
(pc/defresolver person-resolver [{:keys [database] :as env} {:keys [person/id]}]
  {::pc/input #{:person/id}
   ::pc/output [:person/first-name :person/last-name :person/full-name :person/age]}
  (let [{:keys [age first-name last-name]} (my-database/get-person database id)]
    {:person/age        age
     :person/first-name first-name
     :person/last-name  last-name
     :person/full-name  (str first-name " " last-name) ; COMPUTED
     ...}))
``` 

Ancak bu durumda, hepsi bir arada olduğundan, performans açısından gereksiz israf olabilir. Mesela elinde first-name ve last-name vardır, sadece full-name atributuna ihtiyaç duyuyorsundur. Yukarıdaki resolver ile hepsi en baştan hesaplanır. Halbuki iki ayrı resolver yapsak, sadece eksik olan atribut hesaplanır:

``` clojure
(pc/defresolver person-resolver [{:keys [database] :as env} {:keys [person/id]}]
  {::pc/input #{:person/id}
   ::pc/output [:person/first-name :person/last-name :person/age]}
  (let [{:keys [age first-name last-name]} (my-database/get-person database id)]
    {:person/age        age
     :person/first-name first-name
     :person/last-name  last-name}))

(pc/defresolver person-name-resolver [_ {:person/keys [first-name last-name]}]
  {::pc/input #{:person/first-name :person/last-name}
   ::pc/output [:person/full-name]}
  {:person/full-name (str first-name " " last-name)})

``` 

