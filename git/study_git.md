---
title: "Studying Git Tags"
date: 2018-09-19T09:13:58+03:00 
draft: true
description: ""
tags:
categories: js
type: post
url:
author: "Mert Nuhoglu"
output: html_document
blog: mertnuhoglu.com
path: ~/projects/study/git/study_git_tags.Rmd
wip: true

---

# References

[Git Magic](https://crypto.stanford.edu/~blynn/gitmagic/index.html)
[yogthos cheatsheet](https://github.com/yogthos/cheatsheets/blob/master/git.md)

# Articles

## Article: Git Checkout | freeCodeCamp Guide

https://guide.freecodecamp.org/git/git-checkout/

``` bash
git checkout <specific-commit-id>
git log		# get commit id
``` 

``` bash
git checkout <BRANCH-NAME>
git checkout -B <BRANCH-NAME>		# new branch
``` 

Undo changes in working directory:

``` bash
git checkout -- <FILE-NAME>
``` 

## Article: How to grep (search) committed code in the Git history - Stack Overflow

https://stackoverflow.com/questions/2928584/how-to-grep-search-committed-code-in-the-git-history

To search for commit content (i.e., actual lines of source, as opposed to commit messages and the like), you need to do:

``` bash
git grep <regexp> $(git rev-list --all)
  ##> 7b66205ea8a3629cc23a4bfc5d4ff1dfd63aed53:.Rprofile:rmall = function() { rm(list = ls()) }
  ##> 7b66205ea8a3629cc23a4bfc5d4ff1dfd63aed53:.Rprofile:rt = function() rtags(ofile = 'tags')
``` 

If "Argument list too long" error:

``` bash
git rev-list --all | xargs git grep <expression> 

``` 

Search in subtree. Ex: `lib/util`:

``` bash
git grep <regexp> $(git rev-list --all -- lib/util) -- lib/util
``` 

Search working tree for text:

``` bash
git grep <regexp>
git grep -e <regexp1> [--or] -e <regexp2>							# regexp1 or regexp2
git grep -e <regexp1> --and -e <regexp2>							# and
git grep -l --all-match -e <regexp1> -e <regexp2>			# and. -l: list files
git diff --unified=0 | grep <pattern>									# search changed lines only
git grep <regexp> $(git rev-list --all)								# search all revisions
git grep <regexp> $(git rev-list <rev1>..<rev2>)			# revisions between rev1 and rev2
``` 


