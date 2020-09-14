---
title: "Study sc-im"
date: 2020-05-15T15:05:26+03:00 
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

---

# FINAL:

Use `visidata` instead of scim. 

`visidata` çok daha kullanışlı:

- excel desteği
- düzenleme ve kaydetme özelliği

# tool: sc-im

https://github.com/andmarti1424/sc-im

``` 
brew tap nickolasburr/pfa
brew install sc-im
``` 

``` 
scim
``` 

https://github.com/andmarti1424/sc-im/wiki/SCIM-command-examples

		| :help        | help manual             |
		| < \ >        | insert text             |
		| e            | edit numeric (vi)       |
		| E            | edit text (vi)          |
		| x            | remove                  |
		| b w          | go back/forward cell    |
		| =@avg(b1:b4) | formula                 |
		| fl fh        | format width right/left |
		| gb0          | goto cell b0            |
		| ^f ^b        | page dn/up              |
		| ir ic        | insert row/col          |
		| dr yr pr     | delete/yank/put row     |
		| dc yc pc     | delete/yank/put col     |
		| dd yd pd     | delete/yank/put cell    |
		| Zr Zc        | zap (hide) row/col      |
		| sr sc        | show row/col            |
		| ma           | mark as 'a              |
		| ca           | copy from 'a            |
		| W <file.txt> | write plain text        |
		| P <file.sc>  | write .sc file          |
		| G <file.sc>  | read .sc file           |
		
https://raw.githubusercontent.com/andmarti1424/sc-im/freeze/src/doc

		| :load {file}  | read file .sc .csv .xlsx |
		| :wq           | save quit                |
		| :e csv {file} | export as csv            |
		| :e {format}   | mkd csv txt xlsx         |

Ex: 

Check `/Users/mertnuhoglu/projects/study/problem/sample_data/t01.tsv`

``` 
:load /Users/mertnuhoglu/projects/study/problem/sample_data/t01.tsv
``` 

## Error: XLSX export support not compiled in

``` 
:e xlsx t01.xlsx
``` 

### Article: Ubuntu with XLSX import & export · andmarti1424/sc-im Wiki

[Ubuntu with XLSX import & export · andmarti1424/sc-im Wiki](https://github.com/andmarti1424/sc-im/wiki/Ubuntu-with-XLSX-import-&-export)

```clojure
$ sudo apt-get install bison libncurses5-dev libncursesw5-dev libxml2-dev libzip-dev

$ git clone https://github.com/jmcnamara/libxlsxwriter.git
$ cd libxlsxwriter/
$ make
$ sudo make install
```

mac uyarlaması:

https://stackoverflow.com/questions/31805431/how-to-install-bison-on-mac-osx

```bash
brew install bison
brew link bison --force
libncurses5-dev libncursesw5-dev libxml2-dev libzip-dev
brew install ncurses
  ##> brew install homebrew/dupes/ncurses
```

```clojure
sudo update_dyld_shared_cache


```


