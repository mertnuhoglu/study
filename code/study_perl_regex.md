---
title: "Study perl regex"
date: 2019-10-13T14:23:32+03:00 
draft: true
description: ""
tags:
categories: perl, regex
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
resource_files:
path: ~/projects/study/code/study_perl_regex.md
state: wip

---

# Articles

## Article01: Introduction to Regexes in Perl 5

https://perlmaven.com/introduction-to-regexes-in-perl

### =~ the regex operator


## Article02: 7 of the most useful Perl command line options

https://perlmaven.com/perl-command-line-options

### -n wrap the -e/-E code in a while loop

So

``` bash
perl -n -E 'say if /code/' file.txt
``` 

is the same as

``` bash
while (<>) {
    say if /code/;
}
``` 

> That will go over all the lines of all the files provided on the command line (in this case it is file.txt) and print out every line that matches the /code/ regex.

Check `~/projects/study/code/ex/study_perl_regex/ex01/e01.sh`

``` bash
bash e01.sh
  ##> code line 01
  ##> 
  ##> code line 02
``` 

### -p is like -n with print $_

The -p option is very similar to the -n flag, but it also prints the content of $_ at the end of each iteration.

So we could write:

``` bash
perl -p -E 's/code/foobar/' file.txt
``` 

which would become

``` bash
while (<>) {
    s/code/foobar/
    print;
}
``` 

Check `~/projects/study/code/ex/study_perl_regex/ex01/e02.sh`

``` bash
bash e02.sh
  ##> foobar line 01
  ##> foobar line 02
``` 

### -e execute code on the command line

``` bash
perl -e 'print qq{Hello World\n}'
  ##> Hello World
``` 

### -E execute code on the command line with all the latest features enabled

``` bash
$ perl -E 'say q{Hello World}'
  ##> Hello World
``` 

`say` is a `print` with trailing newline added.

> Internally, it is probably the best to use `q` and `qq` instead of single-quote and double-quote, respectively. That might help reduce the confusion caused by the behavior of the shell and command prompt

### Article03: Command-line Options

https://users.cs.cf.ac.uk/Dave.Marshall/PERL/node161.html

> -0
> -- Lets you specify the record separator ($/) as an octal number. For example, -0055 will cause records to end on a dash. If no number is specified, records will end on null characters. The special value of 00 will place Perl into paragraph mode. And 0777 will force Perl to read the whole file in one shot because 0777 is not a legal character value.

