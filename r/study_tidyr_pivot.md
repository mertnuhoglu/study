---
title: "Study tidyr pivot_wider and pivot_longer"
date: 2017-12-23T18:27:56+03:00 
draft: draft
description: ""
tags:
categories: r dplyr
type: post
url:
author: "Mert Nuhoglu"
output: html_document
blog: datascience.mertnuhoglu.com
resource_files:
-
---

<style>
  .main-container {
    max-width: 1600px !important;
  }
</style>

``` {r set-options}
options(width = 150)
options(max.print = 30)
``` 

```{r}
library(dplyr, warn.conflicts = F)
library(tidyr, warn.conflicts = F)
```

Aggregation (dplyr::summarise) functions and window functions (dplyr::lead, lag, top_n etc.) operate on rows only. Aggregation functions output single row from an input of a group of rows. Window functions output one row per input row.

gather, spread, separate, unite are different. They reshape data that is they work not only on rows, but also on columns. Their input and output have some effect on columns in addition to rows. So, they change the shape of the data.

There is a great [visual cheatsheet on data wrangling by RStudio](https://www.rstudio.com/wp-content/uploads/2015/02/data-wrangling-cheatsheet.pdf)

## gather = pivot_longer

https://rdrr.io/github/tidyverse/tidyr/man/pivot_longer.html

``` r
vignette("pivot")
``` 

``` bash
relig_income
  ##> # A tibble: 18 x 11
  ##>    religion `<$10k` `$10-20k` `$20-30k` `$30-40k` `$40-50k` `$50-75k`
  ##>    <chr>      <dbl>     <dbl>     <dbl>     <dbl>     <dbl>     <dbl>
  ##>  1 Agnostic      27        34        60        81        76       137
  ##>  2 Atheist       12        27        37        52        35        70
  ##>  3 Buddhist      27        21        30        34        33        58
relig_income %>% 
  pivot_longer(-religion, names_to = "income", values_to = "count")
  ##> # A tibble: 180 x 3
  ##>    religion income             count
  ##>    <chr>    <chr>              <dbl>
  ##>  1 Agnostic <$10k                 27
  ##>  2 Agnostic $10-20k               34
``` 

### pivot_longer documentation examples

https://tidyr.tidyverse.org/reference/pivot_longer.html

``` bash
# See vignette("pivot") for examples and explanation

# Simplest case where column names are character data
relig_income
  ##> # A tibble: 18 x 11
  ##>    religion `<$10k` `$10-20k` `$20-30k` `$30-40k` `$40-50k` `$50-75k` `$75-100k`
  ##>    <chr>      <dbl>     <dbl>     <dbl>     <dbl>     <dbl>     <dbl>      <dbl>
  ##>  1 Agnostic      27        34        60        81        76       137        122
  ##>  2 Atheist       12        27        37        52        35        70         73
pivot_longer(-religion, names_to = "income", values_to = "count")
  ##> # A tibble: 180 x 3
  ##>    religion income             count
  ##>    <chr>    <chr>              <dbl>
  ##>  1 Agnostic <$10k                 27
  ##>  2 Agnostic $10-20k               34
  ##>  3 Agnostic $20-30k               60

billboard
  ##> # A tibble: 317 x 79
  ##>    artist track date.entered   wk1   wk2   wk3   wk4   wk5   wk6   wk7   wk8
  ##>    <chr>  <chr> <date>       <dbl> <dbl> <dbl> <dbl> <dbl> <dbl> <dbl> <dbl>
  ##>  1 2 Pac  Baby… 2000-02-26      87    82    72    77    87    94    99    NA
  ##>  2 2Ge+h… The … 2000-09-02      91    87    92    NA    NA    NA    NA    NA
billboard %>%
 pivot_longer(
   cols = starts_with("wk"),
   names_to = "week",
   names_prefix = "wk",
   values_to = "rank",
   values_drop_na = TRUE
 )
  ##> # A tibble: 5,307 x 5
  ##>    artist  track                   date.entered week   rank
  ##>    <chr>   <chr>                   <date>       <chr> <dbl>
  ##>  1 2 Pac   Baby Don't Cry (Keep... 2000-02-26   1        87
  ##>  2 2 Pac   Baby Don't Cry (Keep... 2000-02-26   2        82
  ##>  3 2 Pac   Baby Don't Cry (Keep... 2000-02-26   3        72
  ##>  4 2 Pac   Baby Don't Cry (Keep... 2000-02-26   4        77
  ##>  5 2 Pac   Baby Don't Cry (Keep... 2000-02-26   5        87
  ##>  6 2 Pac   Baby Don't Cry (Keep... 2000-02-26   6        94
  ##>  7 2 Pac   Baby Don't Cry (Keep... 2000-02-26   7        99
  ##>  8 2Ge+her The Hardest Part Of ... 2000-09-02   1        91
  ##>  9 2Ge+her The Hardest Part Of ... 2000-09-02   2        87
  ##> 10 2Ge+her The Hardest Part Of ... 2000-09-02   3        92
  ##> # … with 5,297 more rows

# Multiple variables stored in colum names
who %>% pivot_longer(
  cols = new_sp_m014:newrel_f65,
  names_to = c("diagnosis", "gender", "age"),
  names_pattern = "new_?(.*)_(.)(.*)",
  values_to = "count"
)
  ##> # A tibble: 405,440 x 8
  ##>    country     iso2  iso3   year diagnosis gender age   count
  ##>    <chr>       <chr> <chr> <int> <chr>     <chr>  <chr> <int>
  ##>  1 Afghanistan AF    AFG    1980 sp        m      014      NA
  ##>  2 Afghanistan AF    AFG    1980 sp        m      1524     NA
  ##>  3 Afghanistan AF    AFG    1980 sp        m      2534     NA
  ##>  4 Afghanistan AF    AFG    1980 sp        m      3544     NA
  ##>  5 Afghanistan AF    AFG    1980 sp        m      4554     NA
  ##>  6 Afghanistan AF    AFG    1980 sp        m      5564     NA
  ##>  7 Afghanistan AF    AFG    1980 sp        m      65       NA
  ##>  8 Afghanistan AF    AFG    1980 sp        f      014      NA
  ##>  9 Afghanistan AF    AFG    1980 sp        f      1524     NA
  ##> 10 Afghanistan AF    AFG    1980 sp        f      2534     NA
  ##> # … with 405,430 more rows
# Multiple observations per row
anscombe
  #>    x1 x2 x3 x4    y1   y2    y3    y4
  ##> 1  10 10 10  8  8.04 9.14  7.46  6.58
  ##> 2   8  8  8  8  6.95 8.14  6.77  5.76
  ##> 3  13 13 13  8  7.58 8.74 12.74  7.71
  ##> 4   9  9  9  8  8.81 8.77  7.11  8.84
  ##> 5  11 11 11  8  8.33 9.26  7.81  8.47
  ##> 6  14 14 14  8  9.96 8.10  8.84  7.04
  ##> 7   6  6  6  8  7.24 6.13  6.08  5.25
  ##> 8   4  4  4 19  4.26 3.10  5.39 12.50
  ##> 9  12 12 12  8 10.84 9.13  8.15  5.56
  ##> 10  7  7  7  8  4.82 7.26  6.42  7.91
  ##> 11  5  5  5  8  5.68 4.74  5.73  6.89anscombe %>%
 pivot_longer(everything(),
   names_to = c(".value", "set"),
   names_pattern = "(.)(.)"
 )
  ##> # A tibble: 44 x 3
  ##>    set       x     y
  ##>    <chr> <dbl> <dbl>
  ##>  1 1        10  8.04
  ##>  2 2        10  9.14
  ##>  3 3        10  7.46
  ##>  4 4         8  6.58
  ##>  5 1         8  6.95
  ##>  6 2         8  8.14
  ##>  7 3         8  6.77
  ##>  8 4         8  5.76
  ##>  9 1        13  7.58
  ##> 10 2        13  8.74
  ##> # … with 34 more rows
``` 

## gather: gather columns into key-value pairs: longer

**Usage**

    gather(data, key = "key", value = "value", ..., na.rm = FALSE, convert = FALSE, factor_key = FALSE)

Input: multiple columns. Output: two columns as key-value columns and multiple rows. 

### gather example 01a

``` R
library(tidyverse)
d0 = data_frame(
	a = c(1,1,2,2,3),
	b = c(5,6,5,8,9)
)
  ##>       a     b
  ##>   <dbl> <dbl>
  ##> 1     1     5
  ##> 2     1     6
  ##> 3     2     5
  ##> 4     2     8
  ##> 5     3     9
gather(d0, "var", "value")
  ##>    var   value
  ##>    <chr> <dbl>
  ##>  1 a         1
  ##>  2 a         1
  ##>  3 a         2
  ##>  4 a         2
  ##>  5 a         3
  ##>  6 b         5
  ##>  7 b         6
  ##>  8 b         5
  ##>  9 b         8
  ##> 10 b         9
``` 

### gather example 01b

``` R
library(tidyverse)
d0 = data_frame(
	a = c(1,1,2,2,3),
	b = c(5,6,5,8,9),
	c = letters[1:5]
)
  ##>       a     b c
  ##>   <dbl> <dbl> <chr>
  ##> 1     1     5 a
  ##> 2     1     6 b
  ##> 3     2     5 c
  ##> 4     2     8 d
  ##> 5     3     9 e
gather(d0, "var", "value")
  ##>    var   value
  ##>    <chr> <chr>
  ##>  1 a     1
  ##>  2 a     1
  ##>  3 a     2
  ##>  4 a     2
  ##>  5 a     3
  ##>  6 b     5
  ##>  7 b     6
  ##>  8 b     5
  ##>  9 b     8
  ##> 10 b     9
  ##> 11 c     a
  ##> 12 c     b
  ##> 13 c     c
  ##> 14 c     d
  ##> 15 c     e
``` 

### gather example 01c

``` R
library(tidyverse)
d0 = data_frame(
	a = c(1,1,2,2,3),
	b = c(5,6,5,8,9),
	c = letters[1:5]
)
  ##>       a     b c
  ##>   <dbl> <dbl> <chr>
  ##> 1     1     5 a
  ##> 2     1     6 b
  ##> 3     2     5 c
  ##> 4     2     8 d
  ##> 5     3     9 e
gather(d0, "var", "value", -c)
  ##>    c     var   value
  ##>    <chr> <chr> <dbl>
  ##>  1 a     a         1
  ##>  2 b     a         1
  ##>  3 c     a         2
  ##>  4 d     a         2
  ##>  5 e     a         3
  ##>  6 a     b         5
  ##>  7 b     b         6
  ##>  8 c     b         5
  ##>  9 d     b         8
  ##> 10 e     b         9
``` 

### gather example 01d

``` R
library(tidyverse)
d0 = data_frame(
	a = c(1,1,2,2,3),
	b = c(5,6,5,8,9),
	c = letters[1:5]
)
  ##>       a     b c
  ##>   <dbl> <dbl> <chr>
  ##> 1     1     5 a
  ##> 2     1     6 b
  ##> 3     2     5 c
  ##> 4     2     8 d
  ##> 5     3     9 e
gather(d0, key = "var", value = "value", -c)
  ##>    c     var   value
  ##>    <chr> <chr> <dbl>
  ##>  1 a     a         1
  ##>  2 b     a         1
  ##>  3 c     a         2
  ##>  4 d     a         2
  ##>  5 e     a         3
  ##>  6 a     b         5
  ##>  7 b     b         6
  ##>  8 c     b         5
  ##>  9 d     b         8
  ##> 10 e     b         9
gather(d0, key = "var", value = "value", c(a,b))
  ##>    c     var   value
  ##>    <chr> <chr> <dbl>
  ##>  1 a     a         1
  ##>  2 b     a         1
  ##>  3 c     a         2
  ##>  4 d     a         2
  ##>  5 e     a         3
  ##>  6 a     b         5
  ##>  7 b     b         6
  ##>  8 c     b         5
  ##>  9 d     b         8
  ##> 10 e     b         9
gather(d0, key = "var", value = "value", a)
  ##>       b c     var   value
  ##>   <dbl> <chr> <chr> <dbl>
  ##> 1     5 a     a         1
  ##> 2     6 b     a         1
  ##> 3     5 c     a         2
  ##> 4     8 d     a         2
  ##> 5     9 e     a         3
``` 

### gather example 01

``` r
set.seed(1)
stocks <- data.frame(
  time = as.Date('2009-01-01') + 0:1,
  X = rnorm(2, 0, 1),
  Y = rnorm(2, 0, 2)
)
stocks
  ##>         time          X         Y
  ##> 1 2009-01-01 -0.6264538 -1.671257
  ##> 2 2009-01-02  0.1836433  3.190562
```

``` r
stocks %>% gather(stock, price, -time)
  ##>         time stock      price
  ##> 1 2009-01-01     X -0.6264538
  ##> 2 2009-01-02     X  0.1836433
  ##> 3 2009-01-01     Y -1.6712572
  ##> 4 2009-01-02     Y  3.1905616
```

`stock` and `price` are `key` - `value` pairs.

Note that, `X` and `Y` are actually not variables. `stock` is the variable. 

### gather example 02

``` r
mini_iris <- iris[c(1, 51), c(1,2,5)]
mini_iris
  ##>    Sepal.Length Sepal.Width    Species
  ##> 1           5.1         3.5     setosa
  ##> 51          7.0         3.2 versicolor
``` 

`Sepal.Length` and `Sepal.Width` are not variables. They are `key` values. `values` for these keys are `5.1`, `3.5` etc.

``` r
tidyr::gather(mini_iris, key = flower_att, value = measurement,
              Sepal.Length, Sepal.Width)
  ##>      Species   flower_att measurement
  ##> 1     setosa Sepal.Length         5.1
  ##> 2 versicolor Sepal.Length         7.0
  ##> 3     setosa  Sepal.Width         3.5
  ##> 4 versicolor  Sepal.Width         3.2
```

``` r
gathered_iris = mini_iris %>% 
  tidyr::gather(key = flower_att, value = measurement, -Species)
gathered_iris
  ##>      Species   flower_att measurement
  ##> 1     setosa Sepal.Length         5.1
  ##> 2 versicolor Sepal.Length         7.0
  ##> 3     setosa  Sepal.Width         3.5
  ##> 4 versicolor  Sepal.Width         3.2
```

## spread = pivot_wider

### pivot_wider documentation example

https://tidyr.tidyverse.org/reference/pivot_wider.html

``` bash
# See vignette("pivot") for examples and explanation

fish_encounters
  ##> # A tibble: 114 x 3
  ##>    fish  station  seen
  ##>    <fct> <fct>   <int>
  ##>  1 4842  Release     1
  ##>  2 4842  I80_1       1
  ##>  3 4842  Lisbon      1

fish_encounters %>%
  pivot_wider(names_from = station, values_from = seen)
  ##> # A tibble: 19 x 12
  ##>    fish  Release I80_1 Lisbon  Rstr Base_TD   BCE   BCW  BCE2  BCW2   MAE   MAW
  ##>    <fct>   <int> <int>  <int> <int>   <int> <int> <int> <int> <int> <int> <int>
  ##>  1 4842        1     1      1     1       1     1     1     1     1     1     1
  ##>  2 4843        1     1      1     1       1     1     1     1     1     1     1
  ##> 19 4865        1     1      1    NA      NA    NA    NA    NA    NA    NA    NA

	# Fill in missing values
fish_encounters %>%
  pivot_wider(
    names_from = station,
    values_from = seen,
    values_fill = list(seen = 0)
  )
  ##> # A tibble: 19 x 12
  ##>    fish  Release I80_1 Lisbon  Rstr Base_TD   BCE   BCW  BCE2  BCW2   MAE   MAW
  ##>    <fct>   <int> <int>  <int> <int>   <int> <int> <int> <int> <int> <int> <int>
  ##>  1 4842        1     1      1     1       1     1     1     1     1     1     1
  ##>  2 4843        1     1      1     1       1     1     1     1     1     1     1
  ##> 19 4865        1     1      1     0       0     0     0     0     0     0     0

us_rent_income
  #> # A tibble: 104 x 5
  #>    GEOID NAME       variable estimate   moe
  #>    <chr> <chr>      <chr>       <dbl> <dbl>
  #>  1 01    Alabama    income      24476   136
  #>  2 01    Alabama    rent          747     3

  # Generate column names from multiple variables
us_rent_income %>%
  pivot_wider(names_from = variable, values_from = c(estimate, moe))
  ##> # A tibble: 52 x 6
  ##>    GEOID NAME                 estimate_income estimate_rent moe_income moe_rent
  ##>    <chr> <chr>                          <dbl>         <dbl>      <dbl>    <dbl>
  ##>  1 01    Alabama                        24476           747        136        3
  ##>  2 02    Alaska                         32940          1200        508       13

warpbreaks
  #>    breaks wool tension
  #> 1      26    A       L
  #> 2      30    A       L
  #> 3      54    A       L

  # Can perform aggregation with values_fn
warpbreaks <- as_tibble(warpbreaks[c("wool", "tension", "breaks")])
warpbreaks
  #> # A tibble: 54 x 3
  ##>    wool  tension breaks
  ##>    <fct> <fct>    <dbl>
  ##>  1 A     L           26
  ##>  2 A     L           30

warpbreaks %>%
  pivot_wider(
    names_from = wool,
    values_from = breaks,
    values_fn = list(breaks = mean)
  )
  ##> # A tibble: 3 x 3
  ##>   tension     A     B
  ##>   <fct>   <dbl> <dbl>
  ##> 1 L        44.6  28.2
  ##> 2 M        24    28.8
  ##> 3 H        24.6  18.8
``` 

### ex02: oyuncak problemde çok kolondan values_from

``` bash
t0 = tibble::tribble(
  ~frm_id, ~ccl, ~ccw, ~axis,
  1, 2, 0, "ver",
  2, 0, 1, "hor"
  ) 
t1 = t0 %>%
	tidyr::pivot_wider(names_from = "axis", values_from = c("ccl", "ccw"), values_fill = list(ccl = 0, ccw = 0))
  ##>   frm_id ccl_ver ccl_hor ccw_ver ccw_hor
  ##>    <dbl>   <dbl>   <dbl>   <dbl>   <dbl>
  ##> 1      1       2       0       0       0
  ##> 2      2       0       0       0       1
``` 

Bu durumda satır sayısı değişmiyor. 

`frm_id` gibi ayrı kolon olmadan yapalım:

``` bash
t0 = tibble::tribble(
  ~ccl, ~ccw, ~axis,
  2, 0, "ver",
  0, 1, "hor"
  ) 
t1 = t0 %>%
	tidyr::pivot_wider(names_from = "axis", values_from = c("ccl", "ccw"), values_fill = list(ccl = 0, ccw = 0))
  ##>   ccl_ver ccl_hor ccw_ver ccw_hor
  ##>     <dbl>   <dbl>   <dbl>   <dbl>
  ##> 1       2       0       0       1
``` 

Bu durumda, satırları birleştirdi. Gerçek istediğimiz sonuç bu.

``` bash
t0 = tibble::tribble(
  ~cpl_id, ~ccl, ~ccw, ~axis,
  1, 2, 0, "ver",
  1, 0, 1, "hor"
  ) 
t1 = t0 %>%
	tidyr::pivot_wider(names_from = "axis", values_from = c("ccl", "ccw"), values_fill = list(ccl = 0, ccw = 0))
  ##>   cpl_id ccl_ver ccl_hor ccw_ver ccw_hor
  ##>    <dbl>   <dbl>   <dbl>   <dbl>   <dbl>
  ##> 1      1       2       0       0       1
``` 

Bu durumda da, satırları birleştirdi. Gerçek istediğimiz sonuç bu.

### spread: ungather key-value pair across multiple columns: wider

``` r
gathered_iris %>%
  tidyr::spread(flower_att, measurement)
  ##>      Species Sepal.Length Sepal.Width
  ##> 1     setosa          5.1         3.5
  ##> 2 versicolor          7.0         3.2
```

### Error: spread fails when there are redundant columns

``` r
s1 = tibble::tribble(
  ~id,   ~key, ~value,
  1,   "a",  2,
  1,   "b",  3,
  2,   "a",  2
  ) %>%
  tidyr::spread(key, value)
s1
  ##> # A tibble: 2 x 3
  ##>      id     a     b
  ##> * <dbl> <dbl> <dbl>
  ##> 1     1     2     3
  ##> 2     2     2    NA
```

``` r
library(dplyr, warn.conflicts = F)
s2 = tibble::tribble(
  ~id,   ~key, ~value, ~amount
  1,   "a",  2, 10,
  1,   "b",  3, 20,
  2,   "a",  2, 15
  ) %>%
  tidyr::spread(key, value)
  ##> Error: <text>:4:3: unexpected numeric constant
  ##> 3:   ~id,   ~key, ~value, ~amount
  ##> 4:   1
  ##>      ^
```

### Error: spread fails when there is no id column

``` r
  ##>    saat   durum
  ##>    <drtn> <chr>
  ##>  1 09:19  G
  ##>  2 10:38  C
  ##>  3 10:43  G
d0 %>%
	tidyr::spread(durum, saat)
``` 

		Error: Each row of output must be identified by a unique combination of keys.
		Keys are shared for 81 rows:
		Do you need to create unique ID with tibble::rowid_to_column()?
		Call `rlang::last_error()` to see a backtrace

Fix:

``` r
d0 %>%
	dplyr::mutate(rowno = (1 + dplyr::row_number()) %/% 2) %>%
	tidyr::spread(durum, saat)
  ##>    rowno C      G
  ##>    <dbl> <drtn> <drtn>
  ##>  1     1 10:38  09:19
  ##>  2     2 12:51  10:43
``` 

## separate: separate one column into several

**Usage**

    separate(data, col, into, sep = "[^[:alnum:]]+", remove = TRUE, convert = FALSE, extra = "warn", fill = "warn", ...)

``` r
tb = tibble::tribble(
  ~year,   ~demo, ~n,
  2015 ,   "m04",  2,
  2015 ,   "f04",  3,
  2015 ,   "m05",  1,
  2015 ,   "f05",  0
  )
sep_tb = tb %>%
  tidyr::separate(demo, c("sex", "age"), 1)
sep_tb
  ##> # A tibble: 4 x 4
  ##>    year   sex   age     n
  ##> * <dbl> <chr> <chr> <dbl>
  ##> 1  2015     m    04     2
  ##> 2  2015     f    04     3
  ##> 3  2015     m    05     1
  ##> 4  2015     f    05     0
```

Note: `spread` is similar to `separate` because both reshape one column into multiple columns. But `spread` takes as input two columns: `key-value` whereas `separate` takes as input only one column.

## unite: unseparate several columns into one

**Usage**

    unite(data, col, ..., sep = "_", remove = TRUE)

``` r
sep_tb %>%
  tidyr::unite(demo, sex, age, sep = "")
  ##> # A tibble: 4 x 3
  ##>    year  demo     n
  ##> * <dbl> <chr> <dbl>
  ##> 1  2015   m04     2
  ##> 2  2015   f04     3
  ##> 3  2015   m05     1
  ##> 4  2015   f05     0
```

### unnest: unnest a list column

A column can be a list or dataframe. `unnest` converts multiple values in a row into multiple rows.

**Usage**

    unnest(data, ..., .drop = NA, .id = NULL, .sep = NULL)

``` {r}
df1 = tibble::tribble(
  ~x,    ~y,
   1,   "a",
   2, "d,e"
)
``` 

``` r
df2 = df1 %>%
  transform(y = strsplit(y, ","))
str(df2)
  #> 'data.frame':    2 obs. of  2 variables:
  #>  $ x: num  1 2
  #>  $ y:List of 2
  #>   ..$ : chr "a"
  #>   ..$ : chr  "d" "e"
```

Note that, y column contains a list of character vectors.

``` r
df2 %>%
  unnest(y)
  #>   x y
  #> 1 1 a
  #> 2 2 d
  #> 3 2 e
```

Alternatively use `unnest` directly:

``` {r}
df2 = df1 %>%
  tidyr::unnest(y = strsplit(y, ","))
df2
  ##>       x y
  ##> 1     1 a
  ##> 2     2 d
  ##> 3     2 e
```

### nest: reverse of unnest

``` {r}
df3 = df2 %>%
  nest(y)
str(df3)
```

### unnest and group_by: take last element in each group

``` {r}
ef1 = data.frame( a = c("ali,veli", "can,cin" ) )
ef1
``` 

I want to get the last word in `a` column for each row.

``` r
ef2 = ef1 %>%
  dplyr::mutate( b = stringr::str_split(a, ",") ) %>%
  tidyr::unnest(b) %>%
  dplyr::group_by(a) 
ef2
  ##> # A tibble: 4 x 2
  ##> # Groups:   a [2]
  ##>          a     b
  ##>     <fctr> <chr>
  ##> 1 ali,veli   ali
  ##> 2 ali,veli  veli
  ##> 3  can,cin   can
  ##> 4  can,cin   cin
``` 

``` r
ef2 %>%
  dplyr::filter(row_number()==n())
  ##> # A tibble: 2 x 2
  ##> # Groups:   a [2]
  ##>          a     b
  ##>     <fctr> <chr>
  ##> 1 ali,veli  veli
  ##> 2  can,cin   cin
``` 

What does `row_number()==n()` mean? Let's check what `row_number()` and `n()` produce by themselves?

``` r
ef2 %>%
  dplyr::mutate(row = row_number())
  ##> # A tibble: 4 x 3
  ##> # Groups:   a [2]
  ##>          a     b   row
  ##>     <fctr> <chr> <int>
  ##> 1 ali,veli   ali     1
  ##> 2 ali,veli  veli     2
  ##> 3  can,cin   can     1
  ##> 4  can,cin   cin     2

ef2 %>%
  dplyr::mutate(row = n())
  ##> # A tibble: 4 x 3
  ##> # Groups:   a [2]
  ##>          a     b   row
  ##>     <fctr> <chr> <int>
  ##> 1 ali,veli   ali     2
  ##> 2 ali,veli  veli     2
  ##> 3  can,cin   can     2
  ##> 4  can,cin   cin     2
```

