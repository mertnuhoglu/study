---
title: "Study awk"
date: 2019-10-07T09:51:54+03:00 
draft: true
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

path: ~/projects/study/bash/study_awk.md

---

## Article01: Awk - A Tutorial and Introduction - by Bruce Barnett

http://www.grymoire.com/Unix/Awk.html

### ex01

Check `~/projects/study/bash/ex/study_awk/ex01/e01.sh`

``` bash
BEGIN { print "File\tOwner"}
{ print $9, "\t", $3}
END { print " - DONE -" }
``` 

``` bash
chmod +x e01.sh
``` 

``` bash
ls -l | ./e01.sh
  ##> File    Owner
  ##> 
  ##> e01.sh   mertnuhoglu
  ##>  - DONE -
``` 

### ex02: native awk script

Check `~/projects/study/bash/ex/study_awk/ex01/e02.awk`

``` bash
chmod +x e02.awk
ls -l | ./e02.awk
``` 

### ex03: escaping variable dollars

Check `~/projects/study/bash/ex/study_awk/ex01/e03.sh`

``` bash
chmod +x e03.sh
ls -l | ./e03.sh 3 | uniq -c | sort -nr
  ##>    3 mertnuhoglu
  ##>    1
``` 

Check `~/projects/study/bash/ex/study_awk/ex01/e04.sh`

``` bash
chmod +x e04.sh
ls -l | ./e04.sh 
  ##> total
  ##> -rwxr-xr-x
  ##> -rwxr-xr-x
``` 

### Summary of AWK Commands

``` bash
if ( conditional ) statement [ else statement ]
while ( conditional ) statement
for ( expression ; conditional ; expression ) statement
for ( variable in array ) statement
break
continue
{ [ statement ] ...}
variable=expression
print [ expression-list ] [ > expression ]
printf format [ , expression-list ] [ > expression ]
next
exit
``` 

### ex05: for loop printf

Check `~/projects/study/bash/ex/study_awk/ex01/e05.awk`

``` bash
chmod +x e05.awk
./e05.awk
  ##> The square of 1 is 1
  ##> The square of 2 is 4
``` 

### ex06: OFS: Output Field Separator

Check `~/projects/study/bash/ex/study_awk/ex01/e06.awk`

``` bash
chmod +x e06.awk
./e06.awk /etc/passwd
  ##> _timed::266:266:Time Sync Daemon:/var/db/timed:/usr/bin/false
``` 

### ex07: NF: Number of Fields

Check `~/projects/study/bash/ex/study_awk/ex01/e07.awk`

``` bash
chmod +x e07.awk
ls -l | ./e07.awk 
  ##> mertnuhoglu e01.sh
  ##> mertnuhoglu e02.awk
``` 

### ex08: NR: Number of Records = Line number

Check `~/projects/study/bash/ex/study_awk/ex01/e08.awk`

``` bash
chmod +x e08.awk
cat e05.awk | ./e08.awk 
  ##> 11
  ##> 12# do it again, using more concise code
  ##> 13
``` 

### ex09: RS: Record Separator

Check `~/projects/study/bash/ex/study_awk/ex01/e09.awk`

``` bash
chmod +x e09.awk
cat /etc/passwd | ./e09.awk 
  ##> # User Database#
``` 

### ex10: ORS: Output Record Separator

``` bash
ORS="\r\n"
``` 

### ex11: FILENAME: current filename

### ex12: Associative Arrays

``` bash
find . -type f -print | xargs ls -l
``` 

``` bash
chmod +x e12.awk
find . -type f -print | xargs ls -l | ./e12.awk 
  ##> 10 mertnuhoglu
``` 

### printf: formatting output

``` bash
printf ( format);
printf ( format, arguments...);
printf ( format) >expression;
printf ( format, arguments...) > expression;
``` 

#### Escape Sequences

print: terminates the line with ORS character

``` bash
\n	newline
\r	carriage return
\t	horizontal return
``` 

#### Format Specifiers

``` bash
%c	character
%d	decimal integer
%f	floating point
%s	string
``` 

``` bash
printf("%s%s%s%c\n", "\"", "\x22", "\42", 34);
``` 

> Between the "%" and the format character can be four optional pieces of information. It helps to visualize these fields as:
> 
> %<sign><zero><width>.<precision>format

#### File output

``` bash
printf("string\n") > "/tmp/le"
``` 

## Article02: text processing - Replace spaces with comma but not in the whole line - Unix & Linux Stack Exchange

https://unix.stackexchange.com/questions/546083/replace-spaces-with-comma-but-not-in-the-whole-line?atw=1

Problem:

		some name;another thing; random; value value value value value

> I'm trying to replace the spaces that occur after the random; using sed. It's important to keep the spaces that is in some name for example

Solution:

> Using gsub() in awk on the last ;-delimited field:

``` bash
$ awk -F ';' 'BEGIN { OFS=FS } { gsub(" ", ",", $NF); print }' file
some name;another thing; random;,value,value,value,value,value
``` 

# Examples

## getline

### ex01

https://www.gnu.org/software/gawk/manual/html_node/Getline_002fVariable_002fFile.html#Getline_002fVariable_002fFile

## tsv file templating with awk 

Check `~/projects/study/bash/ex/study_awk/ex02/e01.sh`

## variables

Check `~/projects/study/bash/ex/study_awk/ex02/e02.sh`


