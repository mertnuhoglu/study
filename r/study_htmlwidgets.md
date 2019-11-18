---
title: "Study htmlwidgets"
date: 2019-11-10T12:54:34+03:00 
draft: true
description: ""
tags:
categories: r shiny
type: post
url:
author: "Mert Nuhoglu"
output: rmarkdown::html_document
blog: mertnuhoglu.com
resource_files:

---

# htmlwidgets

## Creating a widget

https://www.htmlwidgets.org/develop_intro.html

Check `~/projects/study/r/ex/study_htmlwidgets/ex01/e01.R`

### Ex: sigma

``` bash
cd ~/codes/rr
git clone https://github.com/jjallaire/sigma
cd sigma
R
``` 

Check `~/codes/rr/sigma/README.md`

Usage as `htmlwidget`

``` bash
devtools::load_all()
library(sigma)
sigma(system.file("examples/ediaspora.gexf.xml", package = "sigma"))
``` 

Usage inside `shiny`

Check `~/codes/rr/sigma/inst/examples/ediaspora-shiny.R`

``` bash
source("~/codes/rr/sigma/inst/examples/ediaspora-shiny.R")
shinyApp(ui = ui, server = server)
``` 

#### File layout

``` bash
tree
  ##> .
  ##> ├── R
  ##> │   └── sigma.R
  ##> ├── README.md
  ##> ├── inst
  ##> │   └── htmlwidgets
  ##> │       ├── lib
  ##> │       │   └── sigma-1.0.3
  ##> │       │       ├── README.md
  ##> │       │       ├── plugins
  ##> │       │       │   ├── sigma.parsers.gexf.min.js
  ##> │       │       └── sigma.min.js
  ##> │       ├── sigma.js
  ##> │       └── sigma.yaml
  ##> 
  ##> 7 directories, 23 files
``` 

Note that, all non-R files are inside `inst/htmlwidgets`

#### Dependencies

Dependencies are in `inst/htmlwidgets/lib` directory.

Check `~/codes/rr/sigma/inst/htmlwidgets/sigma.yaml`

#### R binding

Check `~/codes/rr/sigma/R/sigma.R`

``` bash
sigma <- function(gexf, drawEdges = TRUE, drawNodes = TRUE,
                  width = NULL, height = NULL) {
									...
  htmlwidgets::createWidget("sigma", x, width = width, height = height)
}
``` 

`sigma` lets us to create html widget.

But we also define functions for usage inside `shiny`

``` bash
sigmaOutput <- function(outputId, width = "100%", height = "400px") {
  shinyWidgetOutput(outputId, "sigma", width, height, package = "sigma")
}
renderSigma <- function(expr, env = parent.frame(), quoted = FALSE) {
  shinyRenderWidget(expr, sigmaOutput, env, quoted = TRUE)
}
``` 

These are always the same for all widgets.

#### JavaScript binding


