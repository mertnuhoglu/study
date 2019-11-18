---
title: "Study visidata"
date: 2019-11-13T13:29:34+03:00 
draft: true
description: ""
tags:
categories: bash, excel
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
resource_files:
path: ~/projects/study/code/study_visidata.md
state: wip

---

# visidata

https://github.com/saulpw/visidata

## Install

https://visidata.org/install/

``` bash
pip3 install visidata
``` 

Install all dependencies:

``` bash
pip3 install openpyxl
pip3 install xlrd
pip3 install lxml
``` 


## Usage

Run 

``` bash
vd
  ##>  directory          | filename           ‖ ext     | size   #| modtime     @‖
  ##>                     | requirements.txt   ‖ txt     |     597 |   2019-11-13 ‖
  ##>  tatoeba/Tatoeba-an…| FETCH_HEAD         ‖         |     122 |   2019-07-22 ‖
  ##>  tatoeba/Tatoeba-an…| exampledeck.csv    ‖ csv     |     544 |   2019-05-08 ‖
``` 

``` bash
vd x.tsv
  ##>  580774    | 1   | a           | bir                | artikel            | I'm a man.         | Ben b>
  ##>  241003    | 2   | about       | hakkında, üzerine  |                    | Think about it.    | Bunu …
  ##>  3374177   | 2   | about       | hakkında, üzerine  |                    | I lied about it.   | Onun …
``` 

# Article: An Introduction to VisiData — An Introduction to VisiData

https://jsvine.github.io/intro-to-visidata/

# Usage Examples

``` bash
vd debug.xlsx
  ##>  sheet   ‖ nRows  #| nCols  #‖
  ##>  ly0     ‖      95 |       4 ‖
  ##>  p0      ‖       4 |       3 ‖
  ##>  cb0     ‖       3 |       3 ‖
  ##>  f0      ‖     343 |       2 ‖
  ##>  area0   ‖    1140 |      14 ‖
``` 

``` bash
  ##>  palette_id   | plength   | pwidth   ‖
  ##>  p7           | 1200     #| 1000    #‖
  ##>  p8           | 1300     #| 1200    #‖
``` 

`F`: histogram

``` bash
  ##>  cardboard_id   ‖ count  #| percent  %| histogram                                         ~‖
  ##>  cb1            ‖    1020 |     33.33 | ************************************************** ‖
  ##>  cb2            ‖    1020 |     33.33 | ************************************************** ‖
  ##>  cb3            ‖    1020 |     33.33 | ************************************************** ‖
``` 

`F1` `^h`: manual
