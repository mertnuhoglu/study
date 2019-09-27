---
title: "dplyr: group_by"
date: 2019-09-25T14:09:40+03:00 
draft: true
description: ""
tags:
categories: r dplyr 
type: post
url:
author: "Mert Nuhoglu"
output: rmarkdown::html_document
blog: mertnuhoglu.com
resource_files:
-

---

<style>
  .main-container {
    max-width: 1600px !important;
  }
</style>

## toy data

``` r
td01 = tibble::tribble(
  ~id,   ~key, ~val1, ~val2,
  1,    "a",  2, 10,
  2,    "a",  2, 20,
  3,    "a",  3, 15,
  4,    "a",  3, 25,
  5,    "a",  4, 20,
  6,    "a",  4, 30,
  7,    "b",  3, 30,
  8,    "b",  3, 40,
  9,    "b",  4, 25,
  10,   "b",  4, 35
  ) 
td02 = tibble::tribble(
  ~id,   ~a, ~b,
  1,      5,  9,
  2,      6,  8,
  3,      6,  9
  ) 
td03 = tibble::tribble(
  ~id,   ~a, ~b,
  1,      5,  9,
  2,      6,  8,
  3,      6,  9,
  4,      6,  9,
  5,      6,  8
  ) 
``` 

## multiple group_by

``` r
g01 = td01 %>%
	dplyr::group_by(key, val1) %>%
	dplyr::summarize(min(val2))
  ##>   key    val1 `min(val2)`
  ##>   <chr> <dbl>       <dbl>
  ##> 1 a         2          10
  ##> 2 a         3          15
  ##> 3 a         4          20
  ##> 4 b         3          30
  ##> 5 b         4          25
g02 = g01 %>%
	dplyr::summarize(min(val1))
  ##>   key   `min(val1)`
  ##>   <chr>       <dbl>
  ##> 1 a               2
  ##> 2 b               3
``` 

