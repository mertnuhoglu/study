---
title: "Study Shiny Panel and Page"
date: 2020-05-08T04:16:39+03:00 
draft: true
description: ""
tags:
categories: r, shiny
type: post
url:
author: "Mert Nuhoglu"
output: html_document
blog: mertnuhoglu.com

---

# Examples

## ex01

### e01.R: fluidPage > navlistPanel > tabPanel

Check `~/projects/study/r/shiny/ex/study_panel_page/ex01/e01.R`

``` r
source("e01.R")
``` 

### e02.R: fluidPage > sidebarLayout > mainPanel > tabPanel

Check `~/projects/study/r/shiny/ex/study_panel_page/ex01/e02.R`

Its look is broken. Tabs are not correct.

### e02b.R: fluidPage > sidebarLayout > mainPanel > navlistPanel > tabPanel

Check `~/projects/study/r/shiny/ex/study_panel_page/ex01/e02b.R`

Now this looks correct. But `navlistPanel` lays tab headers vertically.

### e02c.R: fluidPage > sidebarLayout > mainPanel > tabsetPanel > tabPanel

Check `~/projects/study/r/shiny/ex/study_panel_page/ex01/e02c.R`

This looks better. `tabsetPanel` lays tab headers horizontally.

### e02d.R: fluidPage > mainPanel > tabsetPanel > tabPanel

Check `~/projects/study/r/shiny/ex/study_panel_page/ex01/e02d.R`

Removed `sidebarLayout`. Tabs cover the whole page.

### e02e.R: fluidPage > tabsetPanel > tabPanel

Check `~/projects/study/r/shiny/ex/study_panel_page/ex01/e02e.R`

Removed `mainPanel`. 

### e03.R: nested tabsetPanel: tabsetPanel > tabPanel > tabsetPanel > tabPanel

Check `~/projects/study/r/shiny/ex/study_panel_page/ex01/e03.R`

Nested `tabsetPanel`

