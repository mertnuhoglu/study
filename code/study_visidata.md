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

``` 
wget https://jsvine.github.io/intro-to-visidata/_downloads/83e70cf67e909f3ac177575439e5f3c5/faa-wildlife-strikes.csv
vd faa-wildlife-strikes.csv
``` 

Frequency Table (Histogram): `+F`

``` 
 OPERATOR           ‖ count  #| percent  %| histogram                                         ~‖
 UNKNOWN            ‖   23076 |     31.42 | ************************************************** ‖
 SOUTHWEST AIRLINES ‖    7752 |     10.55 | ****************                                   ‖
 BUSINESS           ‖    5868 |      7.99 | ************                                       ‖
``` 

Help/manual: `F1` or `^h`

		| q  | quit       |
		| ^c | abort      |
		| ^q | force quit |
		| ^h | help       |

Cheatsheet: `https://jsvine.github.io/visidata-cheat-sheet/en/`

Move

		| g  | global (mnemonics) |
		| gj | last row           |
		| gk | first row          |
		| gh | last col           |
		| gl | first col          |
		| ^f | page forward       |
		| ^b | page backward      |

Search

		| /regex  | search in column      |
		| ?regex  | search backwards      |
		| g/regex | search in all columns |
		| g?regex | search backwards all  |
		| n N     | next/prev             |
		| c regex | jump to matching col  |
		| zr 99   | row 99                |
		| zc 99   | col 99                |

## Sheets:

https://jsvine.github.io/intro-to-visidata/basics/understanding-sheets/

- sources sheets: actual data
- derived sheets: ex: frequency table
- metasheet: ex: sheets sheet

Sheets Sheet: Lists all open sheets: `+s`

		| d     | delete sheet                |
		| enter | goto sheet                  |
		| e     | editing mode (rename sheet) |

		| space        | command prompt (command mode) |
		| rename-sheet |                               |

		| q   | close sheet       |
		| gq  | close all sheets  |
		| gS  | goto sheets trash |
		| c-^ | goto last sheet   |

## Rows

		| s        | select row                           |
		| u        | unselect                             |
		| t        | toggle selection                     |
		| gs gu gt | all select/unselect/toggle           |
		| \| term  | select rows with `term`              |
		| \ term   | unselect `term`                      |
		| g..      | all                                  |
		| ,        | select if matches current rows value |
		| g,       | all                                  |

ex: col: STATE > `|` > .TX 

		| +j | move row up       |
		| +k | move row down     |
		| e  | edit cell         |
		| ^c | cancel editing    |
		| ^a | beginning of line |
		| ^e | end               |
		| ^k | clear cell        |

## Columns

Properties:

- Name
- Width
- Type

Columns sheet (view all columns): `+c`

Set column types:

		| # | integer  |
		| % | float    |
		| $ | currency |
		| @ | date     |
		| ~ | text     |

Rename columns: `^`

Width:

		| _     | expand to fit in visible rows       |
		| g_    | all rows                            |
		| z_ 99 | set width to 99                     |
		| -     | hide                                |
		| gv    | unhide all                          |
		| z-    | half width                          |
		| z     | general: narrow scope of the action |

Move columns and rows:
	
		| +H +L | move cols |
		| +J +K | move rows |

Specify key columns: `!`

- pinned to left-hand side
- special treatment: when joining sheets

Manipulating inside Columns sheet:

		| +J +K | moving cols        |
		| e     | edit col names     |
		| s     | select columns     |
		| g$    | selected: set col types to $ |
		| ge 99 | selected: set width to 99    |

## Sorting and Filtering

		| [               | sort ascending              |
		| ]               | sort descending             |
		| g]              | selected key cols: sort all |

Filtering:

opt01: select rows > `"` 

opt02: frequency table > select rows > enter

opt02: frequency table > select rows > q > "

## Summarizing Data

https://jsvine.github.io/intro-to-visidata/basics/summarizing-data/

Multi-column frequencies: columns > ! > gF

aggregators: min max avg mean median q3 q4 sum distinct count keymax

add an aggregator to a column: col > + > .select aggregator

Ex: goto `COST_REPAIRS` col > `#` > `+` > `sum` > goto `AIRPORT` col > `+F`

``` 
 AIRPORT    ‖ count  #| sum_COST_REPAIRS  #‖
 UNKNOWN    ‖    8424 |           17931422 ‖
 DENVER INT…‖    2756 |            9033148 ‖
 DALLAS/FOR…‖    2392 |             927920 ‖
``` 

one-off calculation:

Ex: goto col > `z+` > `sum`

Summarizing all columns `Describe Sheet`: `+I`

## Excel Spreadsheets

Auto-populate column names: 

goto row > `g^` will name all columns with the values in this row

		| ^s | save      |
		| gY | copy rows |

## Creating Columns

- Expressions (functions)
- Split text in column
- Split with regex

		| =  | create an expression column    |
		| =1 | expression column with value 1 |

ex: `HEIGHT >= 100`

``` 
 | HEIGHT#| HEIGHT >= 100   | SPEED#| SPECIES > 
 |       !| False           |      !| Hawks     
 |   1500 | True            |      !| Unknown … 
``` 

Split: goto col > `:` > enter splitting pattern 

Capture: goto col > `;` > regex

## Joining Sheets

s01. mark cols as `keys`
s02. goto `Sheets Sheet` > select sheets to join
s03. `&` > enter type of join

### Ex: Birds

https://jsvine.github.io/intro-to-visidata/practical/distinctive-birds/

s01. Create filtered sheet

goto `SPECIES` col > `|` > enter: `unknown` > `gt` > `"`

s02. Rename filtered sheet

`space` > enter `rename-sheet` > enter `known_species`

s03. Count number of collisions per state

goto `STATE` col > `+F`

s04. 

goto `count` col > `^` > enter `state_total`

`_` > hide `percent` and `histogram` cols by `-`

``` 
 STATE ‖ state_total#‖
 TX    ‖        4670 ‖
       ‖        4428 ‖
 CA    ‖        3391 ‖
``` 

s05. Count number of collisions per state and species

`+S` > `known_species` sheet > `STATE` col > `!`

`SPECIES` col > `!` > `gF` 

Creates a frequency table by `STATE` and `SPECIES`

Hide other cols

s04. Prepare table for joining

Key columns must be the same for joining:

unkey `SPECIES` col: `!`

s05. join tables

`+S` > `known_species_STATE-SPECIES_freq` > `s`

`+S` > `known_species_STATE_freq` > `s`

`&` > enter `inner`

``` 
 STATE ‖ SPECIES               | count  #| state_total#‖
 TX    ‖ Mourning dove         |     984 |        4670 ‖
 TX    ‖ Rock pigeon           |     339 |        4670 ‖
 TX    ‖ Killdeer              |     260 |        4670 ‖
``` 

s06. calculate state level percentages

`gl` > `=` > `count * 100 / state_total`

`^` > enter `pct_of_state` > `enter` > `%` > `_` > `]`

``` 
 STATE ‖ SPECIES               | count  #| state_total#| pct_of_state%‖
 NS    ‖ Striped skunk         |       1 |           1 |       100.00 ‖
 AB    ‖ Canada goose          |       1 |           2 |        50.00 ‖
 AB    ‖ Perching birds (y)    |       1 |           2 |        50.00 ‖
``` 

s07. Filter rows with at least 20 collisions

`z|` > enter `count >= 20` > `enter` > `"`

``` 
 STATE ‖ SPECIES               | count  #| state_total#| pct_of_state%‖
 PI    ‖ Yellow bittern        |      72 |         173 |        41.62 ‖
 CO    ‖ Horned lark           |    1117 |        2914 |        38.33 ‖
``` 

## Reshaping Data

https://jsvine.github.io/intro-to-visidata/intermediate/reshaping-data/

### Pivot tables:

s01. Make `SPECIES` col a key: goto `SPECIES` > `!`

s02. `c` > `REMAINS_COLLECTED` > `enter`

s03. `+w` creates pivot table

``` 
 SPECIES            ‖ REMAINS_COLLECTED_#| REMAINS_COLLECTED_count_1  #| Total_count  #‖
 Unknown bird       ‖               6677 |                         393 |          7070 ‖
 Owls               ‖                 36 |                          23 |            59 ‖
 Hawks              ‖                202 |                         106 |           308 ‖
``` 

`g_` auto-adjust col widths

### Melting datasets

Melting = wide to long format

Use `!` to keep a col unmelted

Use `-` to hide cols in melted Sheet

`+M`

Ex: 

`!` on `OPERATOR` and `ATYPE`

`-` on all other except `STATE` and `AIRPORT`

``` 
 OPERATOR           | ATYPE          ‖ Variable   | Value
 BUSINESS           | PA-28          ‖ STATE      | FL
 BUSINESS           | PA-28          ‖ AIRPORT    | VERO BEACH MUNICIPAL
``` 

### Transposing columns

`+T` transpose sheet

It keeps `key` columns' values as new column headers

# Examples

Check: `~/projects/study/code/ex/study_visidata/e01/t01.tsv`

``` bash
vd ~/projects/study/code/ex/study_visidata/e01/t01.tsv
  ##>  col1   | col2   ‖
  ##>  100    | 200    ‖
  ##>  101    | 201    ‖
``` 

`F1` `^h`: manual

