---
title: "Study xsv"
date: 2019-10-09T09:35:07+03:00 
draft: true
description: ""
tags:
categories: bash, xsv, csv
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
path: ~/projects/study/code/study_xsv.md
state: wip

---

# Articles

## Article01: README.md

https://github.com/BurntSushi/xsv

### Commands

		cat
		count
		fixlengths: formatting csv by padding/truncating values
		flatten: view one record in long format
		fmt: reformat with different delimiters
		frequency: frequency tables
		headers: show headers
		index: create an index
		input: read csv with different escaping rules
		join: inner, outer, cross joins
		partition: 
		sample
		reverse
		search
		select
		slice
		sort
		split
		stats
		table: show any csv table with elastic tabs

		Common options:
				-h, --help             Display this message
				-o, --output <file>    Write output to <file> instead of stdout.
				-d, --delimiter <arg>  The field delimiter for reading CSV data.
                           Must be a single character. (default: ,)
### Help

``` bash
xsv fmt --help
``` 

Check `~/projects/study/code/ex/study_xsv/ex01/e01.sh`

``` bash
xsv cat rows iris.csv iris.csv
``` 

``` bash
xsv count iris.csv
  ##> 150
``` 

``` bash
xsv slice -i 3 iris.csv
  ##> Sepal.Length,Sepal.Width,Petal.Length,Petal.Width,Species
  ##> 6.2,3.4,5.4,2.3,virginica

xsv slice -i 3 iris.csv | xsv flatten
  ##> Sepal.Length  6.2
  ##> Sepal.Width   3.4
  ##> Petal.Length  5.4
  ##> Petal.Width   2.3
  ##> Species       virginica
``` 

``` bash
xsv fmt -t '\t' iris.csv
  ##> Sepal.Length    Sepal.Width     Petal.Length    Petal.Width     Species
  ##> 5.1     3.5     1.4     0.2     setosa
  ##> 4.9     3       1.4     0.2     setosa
``` 

``` bash
xsv frequency iris.csv
  ##> field,value,count
  ##> Sepal.Length,4.9,1
  ##> Sepal.Width,3,3
  ##> Sepal.Width,3.4,1
``` 

``` bash
xsv headers iris.csv
  ##> 1   Sepal.Length
  ##> 2   Sepal.Width
  ##> 3   Petal.Length
  ##> 4   Petal.Width
  ##> 5   Species
``` 

``` bash
curl -LO https://burntsushi.net/stuff/worldcitiespop.csv
xsv headers worldcitiespop.csv
  ##> 1   Country
  ##> 2   City
  ##> 3   AccentCity
  ##> 4   Region
  ##> 5   Population
  ##> 6   Latitude
  ##> 7   Longitude
``` 

``` bash
xsv stats worldcitiespop.csv --everything | xsv table
  ##> field       type     sum                 min                    max              min_length  max_length  mean                stddev              median              mode        cardinality
  ##> Country     Unicode                      ad                     cn               2           2                                                                       af          43
  ##> City        Unicode                       estancia jokho pekhe  Ðuzaj            2           84                                                                      santa rosa  396982
  ##> AccentCity  Unicode                       Estancia Jokho Pekhe  Üçüncü Mahmudlu  2           84                                                                      Santa Rosa  399311
  ##> Region      Unicode                      00                     BD               0           2                                                   13                  02          95
  ##> Population  Integer  374269749           13                     10021437         0           8           62016.52841756412   300270.25907841505  12896                           5599
  ##> Latitude    Float    9584264.351632003   -54.933333             82.483333        1           12          19.851252996310613  25.15265180172531   30.842358500000003  50.8        148199
  ##> Longitude   Float    16267147.938570838  -172.0833333           167.966667       1           14          33.69306786723125   55.75055728925547   25.25               90.6666667  154842
``` 

``` bash
head -n 100 worldcitiespop.csv > worldcitiespop100.csv 
``` 

``` bash
xsv index worldcitiespop100.csv
xsv stats worldcitiespop100.csv --everything | xsv table
``` 

`slice`: last ten records

``` bash
xsv count worldcitiespop100.csv
  ##> 99
xsv slice worldcitiespop100.csv -s 89 | xsv table
  ##> Country  City           AccentCity     Region  Population  Latitude    Longitude
  ##> ad       vila           Vila           03                  42.5333333  1.5666667
  ##> ad       vixesarri      Vixesarri      06                  42.4833333  1.4666667
  ##> ad       xixerella      Xixerella      04                  42.55       1.4833333
  ##> ae       abu dabi       Abu Dabi       01                  24.466667   54.366667
  ##> ae       abu dhabi      Abu Dhabi      01      603687      24.466667   54.366667
  ##> ae       abu hail       Abu Hail       03                  25.285475   55.329611
  ##> ae       abu zabi       Abu Zabi       01                  24.466667   54.366667
  ##> ae       abu zabye      Abu Zabye      01                  24.466667   54.366667
  ##> ae       abu zabyo      Abu Zabyo      01                  24.466667   54.366667
  ##> ae       ad dharbaniya  Ad Dharbaniya  05                  25.783333   55.933333
``` 

Some cities don't have Population. How pervasive is that?

``` bash
xsv frequency worldcitiespop100.csv --limit 5
  ##> field,value,count
  ##> Country,ad,92
  ##> Country,ae,7
  ##> City,l'aldosa,2
  ##> City,sertes,1
  ##> City,segudet,1
  ##> City,pas de la casa,1
  ##> City,pal,1
  ##> Region,06,25
  ##> Region,04,23
  ##> Region,02,18
  ##> Region,03,9
  ##> Region,07,8
  ##> Population,(NULL),91
  ##> Population,3292,1
  ##> Population,15854,1
  ##> Population,2553,1
  ##> ...
``` 

Sample 10 cities that must have Population

``` bash
xsv search -s Population '[0-9]' worldcitiespop100.csv \
	| xsv select Country,AccentCity,Population worldcitiespop100.csv | xsv sample 10 > sample10.csv
``` 

Join with countrynames.csv

``` bash
curl -LO https://gist.githubusercontent.com/anonymous/063cb470e56e64e98cf1/raw/98e2589b801f6ca3ff900b01a87fbb7452eb35c7/countrynames.csv
xsv headers countrynames.csv
  ##> 1   Abbrev
  ##> 2   Country
xsv join --no-case  Country sample10.csv Abbrev countrynames.csv | xsv table
  ##> Country  AccentCity  Population  Abbrev  Country
  ##> ad       Llumeneres              AD      Andorra
  ##> ad       Certers                 AD      Andorra
  ##> ad       Anyós                   AD      Andorra
  ##> ad       Sornàs                  AD      Andorra
``` 

Select columns:

``` bash
xsv join --no-case  Country sample10.csv Abbrev countrynames.csv \
	| xsv select 'Country[1],AccentCity,Population' \
	| xsv table
  ##> Country  AccentCity  Population
  ##> Andorra  Llumeneres  
  ##> Andorra  Certers     
	##> ...
``` 

Remove columns:

``` bash
	xsv select '!Country[1],AccentCity,Population' 
``` 

---

``` bash
xsv index iris.csv
ls -la
  ##> -rw-r--r--  1 mertnuhoglu  staff  174 Oct  9 10:03 iris.csv
  ##> -rw-r--r--  1 mertnuhoglu  staff   56 Oct  9 10:11 iris.csv.idx
``` 

