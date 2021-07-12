---
title: "Study himalaya mail client"
date: 2021-05-22T11:48:44+03:00 
draft: true
description: ""
tags:
categories: bash, 
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
state: wip

---

# Setup

Check `~/.config/himalaya/config.toml`

## Password management

Dependencies:

```bash
brew install pass
```

oku: [pass on OSX - Parente's Mindtrove](https://mindtrove.info/password-store-osx/)

Gmail şifresi: app özel şifre olacak: [App Passwords in Google](https://myaccount.google.com/apppasswords)

```bash
pass git init
pass insert email/gmail
pass show email/gmail
```

# Wiki

İlk kullanımdan önce, cli'da `pass show email/gmail` komutunu bir kere çalıtırmalısın.

Message listing

[Usage:msg:list · soywod/himalaya Wiki](https://github.com/soywod/himalaya/wiki/Usage:msg:list)

```bash
himalaya list
himalaya list --page-size 20
himalaya read 42
himalaya read --mime-type html 42
```

## Vim Plugin

[himalaya/vim at master · soywod/himalaya](https://github.com/soywod/himalaya/tree/master/vim)

