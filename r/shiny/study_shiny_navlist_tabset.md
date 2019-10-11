---
title: "Study Shiny navlistPanel and tabsetPanel"
date: 2019-10-11T11:03:19+03:00 
draft: true
description: ""
tags:
categories: r, shiny
type: post
url:
author: "Mert Nuhoglu"
output: html_document
blog: mertnuhoglu.com
resource_files:
path: ~/projects/study/r/shiny/study_shiny_navlist_tabset.md
state: wip

---

# Examples

## ex01

### e01.R: en basit navlistPanel örneği

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e01.R`

``` r
source("e01.R")
``` 

``` r
ui = fluidPage(
  titlePanel("Navlist panel example"),
  navlistPanel(
    "Header",
    tabPanel("First",
      h3("This is the first panel")
    ),
    tabPanel("Second",
      h3("This is the second panel")
    )
  )
)
``` 

### e02.R: fluidRow ekle

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e02.R`

``` r
ui = fluidPage(
	shiny::fluidRow(
		shiny::column( width = 12,
			titlePanel("Navlist panel example"),
			navlistPanel(
				"Header",
				tabPanel("First",
``` 

