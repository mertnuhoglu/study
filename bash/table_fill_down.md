---
title: "Table Text Processing: Fill Down Columns"
date: 2020-09-27T10:25:00+03:00 
draft: false
description: ""
tags:
categories: awk, bash
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
resource_files:
- 

---


# Table Text Processing: Fill Down 20200927  id=g11617

## Best: Fill down = outline tree to linear tree id=g12547

script: `~/projects/study/bash/ex/table_fill_down/fill_down/awk04a.sh`

input: `/Users/mertnuhoglu/projects/study/bash/ex/table_fill_down/fill_down/input04.tsv
`

output: `/Users/mertnuhoglu/projects/study/bash/ex/table_fill_down/fill_down/input04a2.tsv
`

## opt01: awk for fixed width

[awk to Fill Empty Column value with Previous Non-Empty Column value: - Stack Overflow](https://stackoverflow.com/questions/25182580/awk-to-fill-empty-column-value-with-previous-non-empty-column-value)

ref: `~/projects/study/bash/ex/table_fill_down/fill_down/awk01.sh`

It works but it assumes fixed width input.

output:

```txt
20                        1 ABC          1   N   GHIABC       0     CHARGE      
20                        2 ABC          1   N   JKLABC       0     CHARGE      
20                        3 ABC          1   N   MNOABC       0     CHARGE      
20                        4 ABC          1   N   PQRABC       0     CHARGE      
210&&-2                   0 ABC          1   N   DEFABC       0     CHARGE      
```


## opt02: join rows with fk

[bash - Fill empty fields - awk - Stack Overflow](https://stackoverflow.com/questions/51289237/fill-empty-fields-awk)

ref: `~/projects/study/bash/ex/table_fill_down/fill_down/awk02.sh`

output:

```txt
DRR029180	8439	0	10
DRR033615	189965	1	5
DRR033616	118663	3	0
DRR033612	184474	0	20
DRR033613	232882	0	0
```

## opt03: awk with separator

[regex - How to fill down empty cells in a CSV in AWK? - Stack Overflow](https://stackoverflow.com/questions/42911393/how-to-fill-down-empty-cells-in-a-csv-in-awk)

output:

```txt
Chapter 1@This is some text.
Chapter 1@This is some more text.
Chapter 1@This is yet some more text.
Chapter 2@This is some text.
Chapter 2@This is some more text.
Chapter 2@This is yet some more text.
```

ref: `~/projects/study/bash/ex/table_fill_down/fill_down/awk03.sh`

ref: `~/projects/study/bash/ex/table_fill_down/fill_down/awk03b.sh`

### testing with other columns

ref: `~/projects/study/bash/ex/table_fill_down/fill_down/awk03b2.sh`

input: `~/projects/study/bash/ex/table_fill_down/fill_down/input03b2.txt`

```txt
col1@col2
row11@
row12@row22
row13@
```

output:

```txt
col1@col2
row11@col2
row12@row22
row13@row22
```

### test with tab separated fields

ref: `~/projects/study/bash/ex/table_fill_down/fill_down/awk03b3.sh`

### test with otl format input (tab indented lines)

ref: `~/projects/study/bash/ex/table_fill_down/fill_down/awk03b4.sh`

output:

```txt
level1a		
	level2a	
	level2a	level3a
	level2b	
	level2b	level3b
	level2b	level3c
```

### fill down multiple columns in one run:

ref: `~/projects/study/bash/ex/table_fill_down/fill_down/awk03b5.sh`

@todo: bu tam düzgün çalışmıyor

