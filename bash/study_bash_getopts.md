---
title: "Study bash getopts"
date: 2019-02-18T22:04:58+03:00 
draft: true
description: ""
tags:
categories: bash
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
resource_files:
- 
path: ~/projects/study/bash/study_bash_getopts.md
state: wip

---

## Article01: Bash getopts builtin command help and examples

https://www.computerhope.com/unix/bash/getopts.htm

`getpots` parses short options such as `-d` `-n` but not `--verbose`

### Example: e01.sh

Check `~/projects/study/bash/ex/study_bash_getopts/ex01/e01.sh`

``` bash
./e01.sh
  ##> Hi!

./e01.sh -n Ali
  ##> Hi, Ali!

./e01.sh -t 2
  ##> Hi!
  ##> Hi!
``` 

### syntax

``` bash
while getopts ":n:t:" options; do              
``` 

``` bash
  case "${options}" in                         # 
    n)                                         # If the option is n,
      NAME=${OPTARG}                           # set $NAME to specified value.
      ;;
``` 

We have two options: -n NAME and -t TIMES

``` bash
getopts "a:pZ:" optname
``` 

`p` doesn't take argument. `a` and `Z` take arguments.
