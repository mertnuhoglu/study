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

opt04: `tabsetPaneli` `tagList` içine koy

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e04e.R`

``` bash
	output$body = shiny::renderUI({
		tagList(
		  shiny::tabsetPanel("Header" , tabPanel("Master", h3("First panel")))
``` 

Yine `$` operator hatası.

Hangi durumlarda hata alıyorum:

- `tabsetPanel` kullanınca, `tagList` olsun olmasın

Ne durumda düzgün çalışıyor

- iki tane `h3` kullanınca, `tagList` içinde

Hangi durumda çalışıyor ama beklediğimden farklı sonuç ile:

- iki tane `h3` kullanınca, `tagList` olmadan

opt

		tabsetPanel dokunu incele
		renderUI içinde fluidRow kullan önce

opt05: `renderUI` içinde `fluidRow` kullan önce

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

opt05b: birden çok `fluidRow` kullan

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e04g.R`

Sadece sonuncuyu gösteriyor yine. 

opt05c: tek column içinde `tabsetPanel` kullan

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e04h.R`

Yine $ operator hatasını veriyor. 

opt05d: `tabsetPanel` olmadan kullan aynısını

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e04i.R`

Bu durumda çalışıyor, sorun `tabsetPanel` kullanınca oluyor.

opt06: `renderUI` ve `tabsetPanel` doklarını oku

opt07: `renderUI` kullanmadan yapamaz mıyız?

Nereden çıkmıştı bunu ilk kullanış gerekçemiz?

body içine doğrudan herhangi bir objeyi koyamıyor muyuz?

`~/projects/itr/peyman/pmap/doc/study/leaflet_rota_cizimi_20190530.md`

Ref: `Article: Shiny - Build a dynamic UI that reacts to user input <url:/Users/mertnuhoglu/projects/study/r/shiny/study_shiny.Rmd#tn=Article: Shiny - Build a dynamic UI that reacts to user input>`

##### opt08: kaynak kodlarını incele

tabsetPaneli doğrudan ui.R içinde kullan sadece

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e04j.R`

``` bash
<div class="container-fluid">
    <div class="tabbable">
      <ul class="nav nav-tabs shiny-tab-input shiny-bound-input" id="dataset" data-tabsetid="2876">
        <li class="active">
          <a href="#tab-2876-1" data-toggle="tab" data-value="tab1">tab1</a>
        </li>
        <li>
          <a href="#tab-2876-2" data-toggle="tab" data-value="tab2">tab2</a>
        </li>
      </ul>
      <div class="tab-content" data-tabsetid="2876">
        <div class="tab-pane active" data-value="tab1" id="tab-2876-1">
          <h3>Panel1</h3>
        </div>
        <div class="tab-pane" data-value="tab2" id="tab-2876-2">
          <h3>Panel2</h3>
        </div>
      </div>
    </div>
  </div>
``` 

Şimdi de renderUI ile üretilen kodu incele:

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e04i.R`

``` bash
<div class="container-fluid">
    <div id="body" class="shiny-html-output shiny-bound-output"><div class="row">
  <div class="col-sm-12">
    <h3>First panel</h3>
    <h3>Second panel</h3>
  </div>
</div></div>
  </div>
``` 

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e04i2.R`

``` bash
<div class="container-fluid">
    <div id="body" class="shiny-html-output shiny-bound-output"><h3>First panel</h3></div>
  </div>
``` 

Şu kısım farklı ikisi arasında:

``` bash
<div id="body" class="shiny-html-output shiny-bound-output">
``` 

---

opt09: tabsetPanel inside renderUI

https://stackoverflow.com/questions/19470426/r-shiny-add-tabpanel-to-tabsetpanel-dynamically-with-the-use-of-renderui

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e09.R`

Çalışıyor.

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e09a.R`

Aynı hata

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e09b.R`

Hata:

		Warning: Error in buildTabset: Tabs should all be unnamed arguments, but some are named: name, attribs, children

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e09b2.R`

`list()` içine wrap ettim

``` bash
t2 = tabPanel("Master")
t2
  ##> <div class="tab-pane" title="Master" data-value="Master"></div>
t3 = list(t2)
  ##> [[1]]
  ##> <div class="tab-pane" title="Master" data-value="Master"></div>
``` 

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e09c.R`

``` bash
    output$mytabs = renderUI({
			do.call(tabsetPanel, list(tabPanel("Master")))
    })
``` 

Çalışıyor.

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e09d.R`

Aynı hata

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e09e.R`

Garip bir çıktı verdi.

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e09e2.R`

Farklı kombinasyonları denedim.

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e09f.R`

``` bash
    output$mytabs = renderUI({
			tabsetPanel(tabPanel("Master"))
    })
``` 

Çalışıyor.

https://stackoverflow.com/questions/35020810/dynamically-creating-tabs-with-plots-in-shiny-without-re-creating-existing-tabs/

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e10.R`

Çalışıyor.

### Sonuç

Problemin sebebi: `id` argümanını kullanmadığımdan, sıralama ters olduğundan hata veriyormuş.

Bunlar çalışıyor:

``` bash
tabsetPanel(id = "Header", tabPanel("Master"))
tabsetPanel(tabPanel("Master"))
``` 

Bu ise hata veriyor:

``` bash
tabsetPanel("Header", tabPanel("Master"))
``` 

Dokümantasyonunda aslında bu mesele açık anlatılıyordu.

### e11.R: tabs > panel

Check `~/projects/study/r/shiny/ex/study_shiny_navlist_tabset/ex01/e11.R`

