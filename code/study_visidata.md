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

## refcard visidata id=g_11578

		cheatsheet	https://jsvine.github.io/visidata-cheat-sheet/en/
		Help/quit <url:file:///~/projects/study/code/study_visidata.md#r=g_11570>
		Move <url:file:///~/projects/study/code/study_visidata.md#r=g_11568>
		Search <url:file:///~/projects/study/code/study_visidata.md#r=g_11569>
		Sheets: <url:file:///~/projects/study/code/study_visidata.md#r=g_11571>
		Row selection <url:file:///~/projects/study/code/study_visidata.md#r=g_11572>
		Move rows: <url:file:///~/projects/study/code/study_visidata.md#r=g_11573>
		column types: <url:file:///~/projects/study/code/study_visidata.md#r=g_11574>
		Column width: <url:file:///~/projects/study/code/study_visidata.md#r=g_11575>
		Move columns and rows: <url:file:///~/projects/study/code/study_visidata.md#r=g_11576>
		Sorting and Filtering <url:file:///~/projects/study/code/study_visidata.md#r=g_11577>

		VisiData Cheat Sheet <url:file:///~/projects/study/code/study_visidata.md#r=g_11582>
		Editing cells <url:file:///~/projects/study/code/study_visidata.md#r=g_11579>
		Summarizing data <url:file:///~/projects/study/code/study_visidata.md#r=g_11580>
		Creating new columns <url:file:///~/projects/study/code/study_visidata.md#r=g_11581>

		| ^h     | help                          |
		| e      | edit cell                     |
		| +S     | all sheets (sheets sheet)     |
		| ^f     | page forward                  |
		| ^b     | page backward                 |
		| /regex | search in column              |
		| s      | select row                    |
		| #      | integer                       |
		| _      | expand to fit in visible rows |
		| +J +K  | move rows                     |
		| +H +L  | move cols                     |
		| !      | toggle "key" column           |
		| [      | sort ascending                |

## Install

https://visidata.org/install/

opt01:

``` bash
pip3 install visidata
``` 

Install all dependencies:

``` bash
pip3 install openpyxl
pip3 install xlrd
pip3 install lxml
``` 

opt02:

```clojure
pipx install visidata
```

opt03:

```clojure
brew tap saulpw/vd
brew install visidata
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

Help/quit id=g_11570

		| q  | quit       |
		| ^c | abort      |
		| ^q | force quit |
		| ^h | help       |
		| F1 | help       |

Cheatsheet: `https://jsvine.github.io/visidata-cheat-sheet/en/`

Move id=g_11568

		| g  | global (mnemonics) |
		| gj | last row           |
		| gk | first row          |
		| gh | last col           |
		| gl | first col          |
		| ^f | page forward       |
		| ^b | page backward      |

Search id=g_11569

		| /regex  | search in column      |
		| ?regex  | search backwards      |
		| g/regex | search in all columns |
		| g?regex | search backwards all  |
		| n N     | next/prev             |
		| c regex | jump to matching col  |
		| zr 99   | row 99                |
		| zc 99   | col 99                |

## Sheets: id=g_11571

https://jsvine.github.io/intro-to-visidata/basics/understanding-sheets/

- sources sheets: actual data
- derived sheets: ex: frequency table
- metasheet: ex: sheets sheet

Sheets Sheet: Lists all open sheets: `+s`

		| +S    | all sheets (sheets sheet)   |
		| d     | delete sheet                |
		| enter | goto sheet                  |
		| e     | editing mode (rename sheet) |

		| space        | command prompt (command mode) |
		| rename-sheet |                               |

		| q   | close sheet       |
		| gq  | close all sheets  |
		| gS  | goto sheets trash |
		| c-^ | goto last sheet   |

## Rows id=g_11572

		| s        | select row                           |
		| u        | unselect                             |
		| t        | toggle selection                     |
		| gs gu gt | all select/unselect/toggle           |
		| \| term  | select rows with `term`              |
		| \ term   | unselect `term`                      |
		| g..      | all                                  |
		| ,        | select if matches current rows value |
		| g,       | all                                  |

Move rows: id=g_11573

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

Set column types: id=g_11574

		| # | integer  |
		| % | float    |
		| $ | currency |
		| @ | date     |
		| ~ | text     |

Rename columns: `^`

Column width: id=g_11575

		| _     | expand to fit in visible rows       |
		| g_    | all rows                            |
		| z_ 99 | set width to 99                     |
		| -     | hide                                |
		| gv    | unhide all                          |
		| z-    | half width                          |
		| z     | general: narrow scope of the action |

Move columns and rows: id=g_11576
	
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

## Sorting and Filtering id=g_11577

		| !  | toggle "key" column    |
		| [  | sort ascending              |
		| ]  | sort descending             |
		| g] | selected key cols: sort all |

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

## A VisiData Cheat Sheet id=g_11582

[A VisiData Cheat Sheet](https://jsvine.github.io/visidata-cheat-sheet/en/)

Help
	| Ctrl-c     | Abort the current command                               |
	| Ctrl-q     | Force-quit VisiData entirely                            |
	| q          | Quit the current VisiData "sheet"                       |
	| Ctrl-h     | Display the "Quick Reference Guide"                     |
	| z + Ctrl-h | Display list of commands available on the current sheet |
Input / output
	| o + filename          | Open a file                                            |
	| Ctrl-s + filename     | Save current sheet to filename                         |
	| g + Ctrl-s + filename | Save all sheets to filename                            |
	| gY                    | Copy sheet (or selected rows) to clipboard             |
	| gzY                   | Copy column (or selected rows for column) to clipboard |
Metasheets
	| S  | Sheets Sheet             |
	| gS | Sheets Graveyard         |
	| C  | Columns Sheet            |
	| O  | Gobal options sheet      |
	| zO | This-sheet options sheet |
Move cursor ...
	| gj     | to the last row         |
	| gk     | to the first row        |
	| gh     | to the leftmost column  |
	| gl     | to the rightmost column |
	| Ctrl-F | one page down (forward) |
	| Ctrl-B | one page up (backward)  |
Moving via search
	| / + regex  | Search forward in current column  |
	| ? + regex  | Search backward in current column |
	| g/ + regex | Search forward in all columns     |
	| g? + regex | Search backward in all columns    |
	| n          | Move to next matching row         |
	| N          | Move to previous matching row     |
Basic row selection
	| s  | Select the current row                               |
	| u  | Unselect the current row                             |
	| t  | Toggle the current row between selected / unselected |
	| gs | Select all rows                                      |
	| gu | Unselect all rows                                    |
	| gt | Toggle all rows between selected / unselected        |
Advanced row selection
	| bar + regex | Select all rows where regex matches the current column            |
	| \ + regex   | Unselect all rows where regex matches the current column          |
	| g + regex   | Select all rows where regex matches any column                    |
	| g\ + regex  | Unselect all rows where regex matches any column                  |
	| ,           | Select all rows where the current column matches the current cell |
	| g,          | Select all rows where any column matches the current cell         |
	| z  + expr   | Select all rows where expr evaluates to True                      |
	| z\ + expr   | Unselect all rows where expr evaluates to True                    |
Shifting rows / columns
	| J | Move row up       |
	| K | Move row down     |
	| H | Move column left  |
	| L | Move column right |
Setting column types
	|# | Integer |
	|% | Float |
	|$ | Currency |
	|@ | Date |
	|~ | Text |
Renaming columns
	| ^   | Rename current column                                                            |
	| g^  | Set names of all unnamed columns to the values in the current or selected row(s) |
	| gz^ | Set names of all visible columns to the values in the current or selected row(s) |
Resizing columns
	| _      | Adjust the width of current column to fit text in all visible rows |
	| g_     | Adjust the width of all columns to fit text in all visible rows    |
	| z_ + n | Set the current column's width to n characters                     |
	| -      | Hide the current column by setting its width to 0                  |
	| gv     | Unhide all columns                                                 |
	| z-     | Shrink the current column's width in half                          |
Sort rows ...
	| [  | in ascending order of current column  |
	| ]  | in descending order of current column |
	| g[ | in ascending order of key columns     |
	| g] | in descending order of key columns    |
Filtering
	| "     | Create new sheet of selected rows                  |
	| gz"   | Create new sheet with "deep copy" of selected rows |
	| R + n | Create new sheet containing n randomly chosen rows |
Summarizing data id=g_11580
	| F         | Create frequency table of current column                      |
	| gF        | Create frequency table of key columns                         |
	| + + aggr  | Add aggregator to column                                      |
	| z+ + aggr | Calculate one-time aggregation of column                      |
	| I         | Create a "Describe Sheet," with summary stats for each column |
Creating new columns id=g_11581
	| = + expr  | Create a new column from a Python expr evaluated against each row   |
	| : + regex | Create new column(s) by splitting current column on regex           |
	| ; + regex | Create new column(s) by extracting regex groups from current column |
	| '         | Create "frozen" copy of current column, with all cells evaluated    |
Reshaping data
	| W | Create pivot table sheet, with key column(s) as rows and current column as values |
	| M | Create "melted" sheet, with key columns as non-melted values                      |
	| T | Create a transposed sheet, where columns become rows and v.v.                     |  
Editing cells id=g_11579
	| e      | Begin editing current cell                           |
	| Enter  | Finish editing                                       |
	| Ctrl-c | Cancel editing                                       |
	| Ctrl-a | Move to beginning of cell                            |
	| Ctrl-e | Move to end of cell                                  |
	| Ctrl-k | Clear contents from cursor's position to end of line |
Misc.
	| !      | Make current column a "key" column        |
	| Ctrl-r | Reload sheet                              |
	| Ctrl-^ | Toggle between current and previous sheet |
	| Space  | Open long-name command prompt             |

## Examples

ex: Read directory:

```bash
vd .
```

ex: Read json array:

```bash
vd ~/projects/study/problem/sample_data/j01.json
```

ex: Read html tables:

```bash
vd https://developer.mozilla.org/en-US/docs/Learn/HTML/Tables/Basics
```

ex: Read directory of csv files:

```bash
vd ~/projects/study/problem/sample_data
```

Select rows: `s`

Open selected files as sheets: `g<enter>`

# Articles

## Saul Pwanson Visidata Videos id=g_11693

[VisiData demo 2: Graphs - YouTube](https://www.youtube.com/watch?v=Ozap_numsjI)

Make columns float `%` or numeric `#`

Select key column `!`

Select y column `.`

[(7) Saul Pwanson - YouTube](https://www.youtube.com/channel/UCDw36yB-ZXJ_FnqEH7o2HfQ)

###

```bash
cd ~/codes/data
wget https://simplemaps.com/static/data/us-cities/1.7/basic/simplemaps_uscities_basicv1.7.zip
unzip simplemaps_uscities_basicv1.7.zip
```

```bash
vd /Users/mertnuhoglu/codes/data/uscities.csv
```

### Drawing Graphs id=g_11694

[/graph](https://www.visidata.org/docs/graph/)

```bash
wget https://raw.githubusercontent.com/saulpw/visidata/stable/sample_data/StatusPR.csv
vd StatusPR.csv
```

Scenario:

```bash
=Month+Day | Insert a new column with this formula
-          | Hide Month column
-          | Hide Day column
@          | Format "Month+Day" column as Date
^Date      | Rename "Month+Day" column as "Date"
!          | Set it as independent variable (x-axis)
!          | Set "Resource" column as independent variable too
,          | "Location" column "Puerto Rico" value. Select all such values
\number    | "Unit" column. Unselect "number" values.
"          | Filter selected rows
%          | Format "Value" column as float
.          | Plot "Value" column
```

Commands:

```bash
!	set column as x-axis
!	set categorical key column as independent variable
.	dot=plot
```

Interaction with graphs

```bash
| Command(s)     | Operation                                   |
| 1 - 9          | toggles display of each scatterplot layer   |
| h  j  k  l     | moves the cursor                            |
| H  J  K  L     | expands and shrinks the cursor              |
| +  -           | increases/decreases the zoomlevel, centere… |
| zz             | zooms into the cursor                       |
| _ (underscore) | zooms to fit the full extent                |
| s  t  u        | selects/toggles/unselects rows on the sour… |
| gs  gt  gu     | selects/toggles/unselects rows visible as … |
| d              | deletes rows on the source sheet contained… |
| gd             | deletes all rows visible as points on the … |
| Enter          | opens sheet of source rows contained withi… |
| gEnter         | opens sheet of source rows which are visib… |
| v              | toggles the visibility of graph labels      |
```

## Opening Google Sheets in Visidata id=g_11695

Ref: [User Manual](https://readthedocs.org/projects/sphinx-visidata/downloads/pdf/latest/)

Step01: Python client:

```bash
pip3 install google-api-python-client 
```

Step02: Set up OAuth Credentials

[API Python Quickstart](https://developers.google.com/sheets/api/quickstart/python)

```bash
pip3 install --upgrade google-api-python-client google-auth-httplib2 google-auth-oauthlib
```

Step03: Set up the sample

Check `~/projects/study/code/ex/study_visidata/googlesheets01/quickstart.py`

```bash
mv /Users/mertnuhoglu/Downloads/credentials.json ~/projects/study/code/ex/study_visidata/googlesheets01
cd ~/projects/study/code/ex/study_visidata/googlesheets01
python3 quickstart.py
```

### Open Sheets From Visidata

```bash
vd https://docs.google.com/spreadsheets/d/1Wg5xSR7-7ER-fUUM7b2h5wyV9A9cgRvE88Dkxy6MD-0/edit#gid=1729711358
```

## Open R data frame with VisiData

[/loading](https://www.visidata.org/docs/loading/#opening-an-r-data-frame-with-visidata)

```bash
library(rvisidata)
vd(iris)
```

## Convert dataset format

```bash
cd /Users/mertnuhoglu/projects/study/problem/sample_data
vd -b t01.tsv -o t01.csv
```

## How to save and replay a VisiData session

[How to save and replay a VisiData session](https://www.visidata.org/docs/save-restore/)

```bash
01a  | ^D          | save command log to a fn.vd file
01b  | +D          | view CommandLog sheet
01b2 | ^S          | save as .vd file
02   | gq          | quit
03   | vd -p fn.vd | replay
```

## Customizing Visidata

[Customizing Visidata](https://www.visidata.org/docs/customize/)

```bash
| +O   | Options sheet (global) |
| ^H   | manual                 |
| z ^H | Commands sheet         |
```

```bash
vd --skip 2 # global option as argument
```

Persist configuration:

Edit `~/.visidatarc`

```bash
options.num_burgers = 13
```

Add global keybinding:

Edit `~/.visidatarc`

```bash
bindkey(keystroke, longname)
```

Example:

```bash
bindkey('i', 'edit-cell')
```

## Extending VisiData With Plugins 

[Extending VisiData With Plugins — An Introduction to VisiData](https://jsvine.github.io/intro-to-visidata/advanced/extending-visidata/)

Open plugins:

`Space` > `open-plugins` > select plugins > `a` to activate



