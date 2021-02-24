---
title: "Study Docker"
date: 2021-01-08T12:22:35+03:00 
draft: true
description: ""
tags:
categories: 
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css

---

# Docker Install

## opt01: brew install docker

[A complete one-by-one guide to install Docker on your Mac OS using Homebrew | by Yuta Fujii | Crowdbotics | Medium](https://medium.com/crowdbotics/a-complete-one-by-one-guide-to-install-docker-on-your-mac-os-using-homebrew-e818eb4cfc3)

```bash
$ brew install docker docker-machine
$ brew install --cask virtualbox
-> need password
-> possibly need to address System Preference setting
$ docker-machine create --driver virtualbox default
$ docker-machine env default
$ eval "$(docker-machine env default)"
$ docker run hello-world
$ docker-machine stop default
```

```bash
To have launchd start docker-machine now and restart at login:
  brew services start docker-machine
Or, if you don't want/need a background service you can just run:
  docker-machine start
```


