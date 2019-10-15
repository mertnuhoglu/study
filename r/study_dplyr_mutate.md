---
title: "Study dplyr mutate"
date: 2019-10-15T10:10:41+03:00 
draft: false
description: ""
tags:
categories: r 
type: post
url:
author: "Mert Nuhoglu"
output: rmarkdown::html_document
blog: mertnuhoglu.com
resource_files:
-

---

# Articles

## Article01: Data Wrangling Part 2: Transforming your columns into the right shape

https://suzan.rbind.io/2018/02/dplyr-tutorial-2/

``` r
library(tidyverse)

glimpse(msleep)
#> Observations: 83
#> Variables: 11
#> $ name         <chr> "Cheetah", "Owl monkey", "Mountain beaver", "Greate…
#> $ genus        <chr> "Acinonyx", "Aotus", "Aplodontia", "Blarina", "Bos"…
```

### `mutate` with `ifelse()`

``` bash
msleep %>%
  select(name, brainwt) %>%
  mutate(brainwt2 = ifelse(brainwt > 4, NA, brainwt)) %>%
  arrange(desc(brainwt))

## # A tibble: 83 x 3
##    name             brainwt brainwt2
##    <chr>              <dbl>    <dbl>
##  1 African elephant   5.71    NA    
##  2 Asian elephant     4.60    NA    
##  3 Human              1.32     1.32 
##  4 Horse              0.655    0.655
``` 

### mutate several columns at once

#### mutate_all()

Pass a function to apply across all columns

``` bash
msleep %>%
  mutate_all(tolower)
``` 

If the function needs arguments, there are two options:

- Make a function up front
- Make a function on the fly by wrapping inside `funs()` or via `~`

Example: mess everything with `\n`

``` r
msleep_ohno <- msleep %>%
  mutate_all(~paste(., "  /n  "))

msleep_ohno[,1:4]

## # A tibble: 83 x 4
##    name                                genus                vore    order
##    <chr>                               <chr>                <chr>   <chr>
##  1 "Cheetah   /n  "                    "Acinonyx   /n  "    "carni~ "Carn~
##  2 "Owl monkey   /n  "                 "Aotus   /n  "       "omni ~ "Prim~
``` 

``` bash
msleep_corr <- msleep_ohno %>%
  mutate_all(~str_replace_all(., "/n", "")) %>%
  mutate_all(str_trim)

msleep_corr[,1:4]

## # A tibble: 83 x 4
##    name                       genus       vore  order       
##    <chr>                      <chr>       <chr> <chr>       
##  1 Cheetah                    Acinonyx    carni Carnivora   
##  2 Owl monkey                 Aotus       omni  Primates    
``` 

#### mutate_if(): mutate some columns

- Select columns you want to mutate
- Pass the mutation function

``` bash
msleep %>%
  select(name, sleep_total:bodywt) %>%
  mutate_if(is.numeric, round)

## # A tibble: 83 x 7
##    name             sleep_total sleep_rem sleep_cycle awake brainwt bodywt
##    <chr>                  <dbl>     <dbl>       <dbl> <dbl>   <dbl>  <dbl>
##  1 Cheetah                12.0      NA          NA    12.0       NA  50.0
##  2 Owl monkey             17.0       2.00       NA     7.00       0   0   
``` 

#### mutate_at(): select columns with names

``` bash
msleep %>%
  select(name, sleep_total:awake) %>%
  mutate_at(vars(contains("sleep")), ~(.*60))

## # A tibble: 83 x 5
##    name                       sleep_total sleep_rem sleep_cycle awake
##    <chr>                            <dbl>     <dbl>       <dbl> <dbl>
##  1 Cheetah                            726      NA         NA    11.9
##  2 Owl monkey                        1020     108         NA     7.00
``` 

#### rename_at(): Change column names after mutation

``` bash
msleep %>%
  select(name, sleep_total:awake) %>%
  mutate_at(vars(contains("sleep")), ~(.*60)) %>%
  rename_at(vars(contains("sleep")), ~paste0(.,"_min"))

## # A tibble: 83 x 5
##    name                sleep_total_min sleep_rem_min sleep_cycle_min awake
##    <chr>                         <dbl>         <dbl>           <dbl> <dbl>
##  1 Cheetah                         726          NA             NA    11.9
##  2 Owl monkey                     1020         108             NA     7.00
``` 

Equivalent: use `funs(min = ...)` instead of two lines above:

``` bash
msleep %>%
  select(name, sleep_total:awake) %>%
  mutate_at(vars(contains("sleep")), funs(min = .*60))

## # A tibble: 83 x 8
##    name            sleep_total sleep_rem sleep_cycle awake sleep_total_min
##    <chr>                 <dbl>     <dbl>       <dbl> <dbl>           <dbl>
##  1 Cheetah               12.1     NA          NA     11.9              726
##  2 Owl monkey            17.0      1.80       NA      7.00            1020
``` 

### Discrete columns (columns with string/categorical values)

#### recode(): rename current values

``` bash
msleep %>%
  mutate(conservation2 = recode(conservation,
                        "en" = "Endangered",
                        "lc" = "Least_Concern",
                        "domesticated" = "Least_Concern",
                        .default = "other")) %>%
  count(conservation2)

## # A tibble: 4 x 2
##   conservation2     n
##   <chr>         <int>
## 1 Endangered        4
## 2 Least_Concern    37
## 3 other            13
## 4 <NA>             29
``` 

#### mutate with ifelse: creating new discrete column

``` bash
msleep %>%
  select(name, sleep_total) %>%
  mutate(sleep_time = ifelse(sleep_total > 10, "long", "short")) 

## # A tibble: 83 x 3
##    name                       sleep_total sleep_time
##    <chr>                            <dbl> <chr>     
##  1 Cheetah                          12.1  long      
##  2 Owl monkey                       17.0  long      
``` 

#### case_when(): Multiple levels 

``` bash
msleep %>%
  select(name, sleep_total) %>%
  mutate(sleep_total_discr = case_when(
    sleep_total > 13 ~ "very long",
    sleep_total > 10 ~ "long",
    sleep_total > 7 ~ "limited",
    TRUE ~ "short")) %>%
  mutate(sleep_total_discr = factor(sleep_total_discr, 
                                    levels = c("short", "limited", 
                                               "long", "very long")))

## # A tibble: 83 x 3
##    name                       sleep_total sleep_total_discr
##    <chr>                            <dbl> <fctr>           
##  1 Cheetah                          12.1  long             
##  2 Owl monkey                       17.0  very long        
``` 

Multiple columns:

``` bash
msleep %>%
  mutate(silly_groups = case_when(
    brainwt < 0.001 ~ "light_headed",
    sleep_total > 10 ~ "lazy_sleeper",
    is.na(sleep_rem) ~ "absent_rem",
    TRUE ~ "other")) %>%
  count(silly_groups)

## # A tibble: 4 x 2
##   silly_groups     n
##   <chr>        <int>
## 1 absent_rem       8
## 2 lazy_sleeper    39
## 3 light_headed     6
## 4 other           30
``` 

### Splitting and merging columns

Dataset:

``` bash
(conservation_expl <- read_csv("conservation_explanation.csv"))

## # A tibble: 11 x 1
##    `conservation abbreviation`                  
##    <chr>                                        
##  1 EX = Extinct                                 
##  2 EW = Extinct in the wild                     
##  3 CR = Critically Endangered                   
##  4 EN = Endangered                              
##  5 VU = Vulnerable                              
##  6 NT = Near Threatened                         
``` 

Split columns by using `tidyr::separate()`

``` bash
(conservation_table <- conservation_expl %>%
  separate(`conservation abbreviation`, 
           into = c("abbreviation", "description"), sep = " = "))

## # A tibble: 11 x 2
##    abbreviation description                            
##  * <chr>        <chr>                                  
##  1 EX           Extinct                                
##  2 EW           Extinct in the wild                    
##  3 CR           Critically Endangered                  
``` 

Merge columns with `unite`

``` bash
conservation_table %>%
  unite(united_col, abbreviation, description, sep=": ")

## # A tibble: 11 x 1
##    united_col                                  
##  * <chr>                                       
##  1 EX: Extinct                                 
##  2 EW: Extinct in the wild                     
``` 

