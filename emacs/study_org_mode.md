---
title: "Study org-mode"
date: 2019-11-27T15:56:17+03:00 
draft: false
description: ""
tags:
categories: emacs
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
resource_files:
path: ~/projects/study/emacs/study_org_mode.md
state: wip

---

# Article: Org Mode Tutorial

https://orgmode.org/worg/org-tutorials/orgtutorial_dto.html#orgdaf1965

Check `~/projects/study/emacs/ex/study_org_mode/ex01/e01.org`

# Article: Org-mode tutorials | Pragmatic Emacs

http://pragmaticemacs.com/org-mode-tutorials/

http://pragmaticemacs.com/emacs/org-mode-basics-structuring-your-notes/

Check `~/projects/study/emacs/ex/study_org_mode/ex01/e02.org`

## Org-mode basics II: use simple tables in your notes | Pragmatic Emacs

Check `~/projects/study/emacs/ex/study_org_mode/ex01/e03.org`

# Article: Org-babel-clojure

https://orgmode.org/worg/org-contrib/babel/languages/ob-doc-clojure.html#orgae59571

opt01: evaluate emacs lisp

``` bash
#+BEGIN_SRC emacs-lisp
  (message "Yeah!")
#+END_SRC
``` 

`C-c C-c` evaluates code.

opt02: evaluate clojure

`M-x cider-jeck-in RET`

``` bash
#+begin_src clojure :results silent
  (+ 1 4)
#+end_src

#+begin_src clojure :results value
  [ 1 2 3 4]
#+end_src

#+RESULTS:
: nil[1 2 3 4]
``` 

## Example: Incanter Graph

Edit `/Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex04/project.clj`

``` bash
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [incanter "1.5.4"]]
``` 

``` bash
lein deps
``` 

Embedding graph:

``` bash
#+CAPTION: A basic x-y line plot
#+NAME: fig:xy-line
[[./incanter-xy-line.pdf]]
``` 

## Export to HTML

## Session Evaluation

### Specify namespace


# Post: org babel - Quickly insert source blocks in org mode - Emacs Stack Exchange

https://emacs.stackexchange.com/questions/12841/quickly-insert-source-blocks-in-org-mode

