---
title: "Study broot"
date: 2020-03-03T21:21:39+03:00 
draft: true
description: ""
tags:
categories: bash, 
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
state: wip

---

# broot: cli replacement for ls and tree

https://dystroy.org/broot/documentation/installation/

``` bash
cargo install broot
brew install broot
``` 


### Verbs

`Space` then `verb`

		edit
		create
		view


### Customizing Configuration

https://dystroy.org/broot/documentation/configuration/

`/Users/mertnuhoglu/Library/Preferences/org.dystroy.broot/conf.toml`

``` bash
mkdir -p /Users/mertnuhoglu/broot
mv /Users/mertnuhoglu/Library/Preferences/org.dystroy.broot/conf.toml /Users/mertnuhoglu/broot
ln -s /Users/mertnuhoglu/broot/conf.toml /Users/mertnuhoglu/Library/Preferences/org.dystroy.broot/conf.toml
``` 

### Tricks

Deep fuzzy cd

``` bash
~/projects/study master*
$ dcd brave

~/projects/study/clj/ex/study_clojure/book_clojure_brave master*
``` 

### Usage

Regex

``` bash
/pattern
/pattern/i
``` 

Toggles

``` bash
:sizes
:perm
:h # hidden files
:gi # use .gitignore
:files # just folders
``` 

Quitting

``` bash
^q
:q
``` 

# Video: (353) Broot Is A Better Way To Navigate Directories - YouTube

``` bash
br --sizes
``` 

Commands

``` bash
filename rm # remove file
dir cd # cd to dir
``` 

ls like:

``` bash
br -dp
``` 

print tree on screen

``` bash
pt
``` 

