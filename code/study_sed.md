---
title: "Study sed"
date: 2019-07-25T09:51:21+03:00
draft: false
description: ""
tags:
categories: aws
type: post
url:
author: "Mert Nuhoglu"
output: html_document
blog: mertnuhoglu.com
resource_files:
path: ~/projects/study/code/study_sed.md

---

# Examples

## ex01: Delete lines 

https://stackoverflow.com/questions/5410757/delete-lines-in-a-text-file-that-contain-a-specific-string

Edit `/Users/mertnuhoglu/projects/study/code/ex/study_sed/ex01/e01.sh`

Input: `~/projects/study/code/ex/study_sed/ex01/t01.txt`

``` bash
sed '/1/d' t01.txt
``` 

Works.

Edit `~/projects/study/code/ex/study_sed/ex01/e02.sh`

``` bash
sed '/^\d\+$/d' t01.txt
``` 

Doesn't work. It doesn't delete lines.

``` bash
sed '/^\d$/d' t01.txt
``` 

Doesn't work. It doesn't delete lines.

Cause: sed doesn't support `\d` use [[:digit:]]
