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

### e03.R: fluidRow ayrı bir değişken olsun

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e03.R`

``` bash
outRow = shiny::fluidRow(...)
ui = fluidPage(outRow)
``` 

### e04.R: renderUI kullan

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e04.R`

#### Error: e04.R Error in : $ operator is invalid for atomic vectors

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e04a.R`

``` bash
Warning: Error in : $ operator is invalid for atomic vectors
  108: tag
  107: tags$a
  104: FUN
  103: lapply
  102: buildTabset
  101: shiny::tabsetPanel
  100: shiny::renderUI [e04.R#5]
``` 

``` bash
ui = fluidPage(uiOutput("body"))
server = function(input, output) {
	output$body = shiny::renderUI({
		shiny::tabsetPanel("Header"
			, tabPanel("Master", h3("First panel"))
		)
	})
}
``` 

opt01: tabsetPanel yerine h3 kullan

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e04b.R`

``` bash
server = function(input, output) {
	output$body = shiny::renderUI({
		h3("First panel")
``` 

It works.

opt02: diğer widgetları da kullan

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e04c.R`

``` bash
	output$body = shiny::renderUI({
		h3("First panel")
		#tabsetPanel("Header" , tabPanel("Master", h3("First panel")))
		h3("Second header")
	})
``` 

Error: Only the last widget is shown.

Muhtemelen, `renderUI` tek bir element alıyor.

opt03: `renderUI` içinde `tagList` kullan

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e04d.R`

``` bash
	output$body = shiny::renderUI({
		tagList(
		  h3("First panel")
		  , h3("Second header")
		)
	})
``` 

Düzgün çalışıyor. 

opt04: tabsetPaneli tagList içine koy

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e04e.R`

``` bash
	output$body = shiny::renderUI({
		tagList(
		  shiny::tabsetPanel("Header" , tabPanel("Master", h3("First panel")))
``` 

Yine $ operator hatası.

Hangi durumlarda hata alıyorum:

- tabsetPanel kullanınca, tagList olsun olmasın

Ne durumda düzgün çalışıyor

- iki tane h3 kullanınca, tagList içinde

Hangi durumda çalışıyor ama beklediğimden farklı sonuç ile:

- iki tane h3 kullanınca, tagList olmadan

opt

		tabsetPanel dokunu incele
		renderUI içinde fluidRow kullan önce

opt05: renderUI içinde fluidRow kullan önce

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e04f.R`

``` bash
	output$body = shiny::renderUI({
    shiny::fluidRow(
      shiny::column( width = 12
				, h3("First panel")
				, h3("Second header")
			)
		)
	})
``` 

opt05b: birden çok fluidRow kullan

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e04g.R`

Sadece sonuncuyu gösteriyor yine. 

opt05c: tek column içinde tabsetPanel kullan

Yine $ operator hatasını veriyor. 

opt06: renderUI ve tabsetPanel doklarını oku

opt07: renderUI kullanmadan yapamaz mıyız?

Nereden çıkmıştı bunu ilk kullanış gerekçemiz?

body içine doğrudan herhangi bir objeyi koyamıyor muyuz?

`~/projects/itr/peyman/pmap/doc/study/leaflet_rota_cizimi_20190530.md`

Ref: `Article: Shiny - Build a dynamic UI that reacts to user input <url:/Users/mertnuhoglu/projects/study/r/shiny/study_shiny.Rmd#tn=Article: Shiny - Build a dynamic UI that reacts to user input>`

