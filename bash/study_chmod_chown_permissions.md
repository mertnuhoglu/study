---
title: "Study chmod chown and permissions"
date: 2019-10-19T11:59:06+03:00 
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
path: ~/projects/study/bash/study_chmod_chown_permissions.md
state: wip

---

# Articles

## Article01: Difference Between chmod and chown | Unix Tutorial

https://www.unixtutorial.org/difference-between-chmod-and-chown

`chmod`: change mode. It changes permissions (modes)

`chown`: change owner. It changes the owner of a file.

### chmod examples

Permissions can be given to a:

- user owner of the file = u
- group = g
- others = o
- all = a

Basic permissions are: 

- read = r
- write = w
- execute = x

Permissions can be numbers. In this case, they are the sum total of permissions value:

- read = 4
- write = 2
- execute = 1

Examples

``` bash
chmod u+rw,go+r important.txt
chmod 644 important.txt # equivalent
``` 

``` bash
chmod +x important.sh
chmod a+x important.sh # equivalent
``` 

``` bash
chmod -R 644 important-files/
``` 

Change owner inside important-files folder to greys user from the group admins

``` bash
chown -R greys:admins important-files
``` 




