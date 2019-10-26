---
title: "Trailing slash on a Directory"
date: 2019-10-19T17:54:22+03:00 
draft: true
description: ""
tags:
categories: bash
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
resource_files:
- 

path: ~/projects/study/bash/trailing_slash_on_directory.md
state: wip

---

# Articles 

## Article01: filenames - When should I use a trailing slash on a directory? - Unix & Linux Stack Exchange

https://unix.stackexchange.com/questions/50499/when-should-i-use-a-trailing-slash-on-a-directory

``` bash
mv some_file foo
``` 

vs.

``` bash
mv some_file foo/
``` 

> If foo doesn't exist, the first will rename some_file to foo, rather than the intended foo/some_file; the second will complain, which is what you want.

> If foo does exists but isn't a directory, the first can clobber the foo file; again, the second will complain.

> For the most part, a trailing / means “treat this as a directory, dereference the symlink if it is one, complain if it isn't a directory”. This behavior is mandated for utilities specified by POSIX. Rsync's source argument is a notable exception

> foo/ is like foo/., so if foo is a symlink to a directory, foo/ is a directory (not a symlink), and if foo is not a directory or a symlink to a directory, then you get a ENOTDIR error for anything trying to access foo/.

`rsync` exception: trailing slash changes behavior only in source

These are equivalent:

``` bash
rsync -av /src/foo /dest
rsync -av /src/foo/ /dest/foo
``` 

They create `foo` directory under target `dest` directory.
