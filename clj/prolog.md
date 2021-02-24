--- 
title: "Study: Prolog"
date: 2021-01-08T13:35:03+03:00 
draft: false
description: ""
tags:
categories: 
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

# Install Prolog

[Easy Prolog installation on Windows, Linux, macOS | by Kai Brooks | Medium](https://kaibrooks.medium.com/easy-prolog-installation-on-windows-linux-macos-a5c5e4b3bc45)

```bash
docker pull swipl/swish
docker run -it -p 3050:3050 --name prolog swipl/swish
```

