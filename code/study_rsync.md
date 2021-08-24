---
title: "Study rsync"
date: 2020-05-13T18:27:32+03:00 
draft: true
description: ""
tags:
categories: bash, rsync
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
path: ~/projects/study/code/study_rsync.md
state: wip

---

## rsync examples

Check `~/projects/study/code/ex/study_rsync/e01/e01.sh`

``` 
cd ~/projects/study/code/ex/study_rsync/e01
``` 

If trailing slash in source, then it creates source dir in target:

Trailing slash in dest makes no difference.

``` 
mkdir d01
touch d01/f.txt
rsync d01 d02 # no effect
mkdir d03
rsync d01 d03 # no effect
rsync d01/ d04 # no effect
rsync -av d01 d05 # creates d01/ inside d05/
  ##> d05/d01/f.txt
rsync -av d01/ d06 # copies d01/*
  ##> d06/f.txt
mkdir d07
rsync -av d01 d07 # creates d01/ inside
rsync -av d01 d09/ # same 
mkdir d08
rsync -av d01/ d08 # copies d01/*
rsync -av d01/ d10/ # same 
``` 

### rsync --delete id=g10932

``` 
mkdir d11
touch d11/g.txt
rsync -av --delete d01/ d11 
  ##> deleting g.txt
  ##> ./
  ##> f.txt
mkdir d12
touch d12/g.txt
rsync -av --delete d01 d12
ls d12
  ##> d01  g.txt
``` 

## References

`rsync <url:file:///~/projects/study/bash/examples_bash.Rmd#r=g10931>`

Trailing slash in rsync: `~/projects/study/bash/trailing_slash_on_directory.md`

## Article01: The Many Uses of Rsync

https://mediatemple.net/blog/tips/many-uses-rsync/

### why rsync instead of cp?

- rsync only copies the delta (difference)

- possible to compress the data 

### Examples

``` bash
rsync -av /path/to/directory1/ path/to/directory2/
``` 

Important: final slash `/` in the file paths. `directory1` will not be created in `directory2`

``` bash
rsync -av /path/to/directory1 path/to/directory2/
``` 

Now, `directory1` will be created in `directory2`

Flags:

- `-a` copies files recursively, preserves attributes
- `-v` verbose output
- `--delete` deletes any files in the destination that aren't at the source
- `-h` `--help`

### rsync with cron to create scheduled backups

Create a new job

``` bash
crontab -e
``` 

Scheduling format:

		* * * * * command

Asterisks:

		minute 0-59
		hour 0-24
		day 1-7
		month 1-12
		weekday 0-6

``` bash
15 23 * * 1 rsync -av path1/ path2/
``` 

15th minute of 23rd hour of the first day of each week.

cron schedule expressions tool: https://crontab.guru

### Sending data to remote host

``` bash
rsync -avz path/to/local/directory1 user@123.456.789.234:/path/to/remote/directory2/ 
``` 


