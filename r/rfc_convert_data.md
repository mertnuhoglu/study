---
title: "Refcard: Convert Data Structures in R"
date: 2021-07-04T13:36:08+03:00 
draft: false
description: ""
tags:
categories: r
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

## generate test data

```r
m <- list(a = 1, b = 2)
  ##> List of 2
  ##>  $ a: num 1
  ##>  $ b: num 2
```

```r
m <- lapply(1:3, function(i) {
	rep(glue::glue("elem_{i}"), i)
})
  ##> List of 3
  ##>  $ : chr "elem_1"
  ##>  $ : chr [1:2] "elem_2" "elem_2"
  ##>  $ : chr [1:3] "elem_3" "elem_3" "elem_3"
```


### convert list to dataframe

### opt02:

```r
m <- lapply(1:3, function(i) {
	rep(glue::glue("elem_{i}"), i)
})
  ##> List of 3
  ##>  $ : chr "elem_1"
  ##>  $ : chr [1:2] "elem_2" "elem_2"
  ##>  $ : chr [1:3] "elem_3" "elem_3" "elem_3"
lapply(seq_along(m), function(i) {
	tibble::tibble(i = i, val = m[[i]])
}) %>% dplyr::bind_rows()
  ##> # A tibble: 6 x 2
  ##>       i val   
  ##>   <int> <chr> 
  ##> 1     1 elem_1
  ##> 2     2 elem_2
  ##> 3     2 elem_2
  ##> 4     3 elem_3
  ##> 5     3 elem_3
  ##> 6     3 elem_3
```

### opt01:

https://stackoverflow.com/questions/4227223/r-list-to-data-frame

``` r
j0
  ##> $results
  ##> $results[[1]]
  ##> $results[[1]]$address_components
  ##> $results[[1]]$address_components[[1]]
  ##> $results[[1]]$address_components[[1]]$long_name
  ##> [1] "Denizli"
  ##> 
  ##> $results[[1]]$address_components[[1]]$short_name
  ##> [1] "Denizli"
  ##> 
  ##> $results[[1]]$address_components[[1]]$types
  ##> [1] "locality"  "political"
  ##> 
  ##> 
  ##> $results[[1]]$address_components[[2]]
  ##> $results[[1]]$address_components[[2]]$long_name
  ##> [1] "Kumkısık"
  ##> 
  ##> $results[[1]]$address_components[[2]]$short_name
  ##> [1] "Kumkısık"
  ##> 
  ##> $results[[1]]$address_components[[2]]$types
  ##> [1] "administrative_area_level_4" "political"
j1 = j0$results[[1]]$address_components 
df = data.frame(matrix(unlist(j1), nrow=length(j1), byrow=T))
``` 


