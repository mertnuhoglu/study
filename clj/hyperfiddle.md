---
title: "Study hyperfiddle"
date: 2019-11-01T14:35:49+03:00 
draft: false
description: ""
tags:
categories: datomic, hyperfiddle
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
resource_files:
path: ~/projects/study/clj/hyperfiddle.md
state: wip

---

# Articles 

## Article: hyperfiddle-metamodel

https://gist.github.com/dustingetz/654e502340070280ab9744723a8ae250

### CRUD UI Definition

It extends datomic pull syntax. 

Creating new record:

``` bash
((hf/new {:hf/tx :dustingetz/register}))
[:db/id
 :dustingetz/email
 :dustingetz/name
 {:dustingetz/gender
  [:db/ident
	 {:dustingetz/shirt-sizes
	  [:db/ident]}]}
 {:dustingetz/shirt-size
  [:db/ident]}]
``` 

/Users/mertnuhoglu/Pictures/screenshots/20191112141630.png

### Queries

## Article: Hyperfiddle REST Fest - Dusting Getz

http://www.dustingetz.com/:hyperfiddle-rest-fest-2018/

Following links and submitting forms. 

References in the database and links in a web page are almost the same thing.

You want to make exactly one request for reading data. 

Also you want to transact only once when doing a specific point in a graph.

Graphql is easy but not simple. 

Typical problem: Why do I see yesterday's data? Graphql makes it worse because it increases the distance between application logic and database.

What about writes?

Datomic works like git.

On the inside, it's a log.

You can distribute the queries without any loss of consistency.

Immutability in the database is the fundamental feature you need.

## Article: Tutorial: 5-minute blog - Dustin Getz

http://www.hyperfiddle.net/:tutorial!simple-blog

HH


 




