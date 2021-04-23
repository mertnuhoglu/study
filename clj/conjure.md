---
title: "Conjure"
date: 2021-01-28T22:02:44+03:00 
draft: false
description: ""
tags:
categories: clojure
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css

---

# Quickstart

## Refcard conjure keybindings

First check current prefix: `let g:conjure#mapping#prefix = " m" `

  | ConjureConnect [port] | bir nrepla bağlan                 |
  | ,ee                   | eval current form                 |
  | ,eb                   | eval buffer                       |
  | ,em[mark]             | eval form under given `mark`      |
  | ,ew                   | eval word under cursor (peek var) |
  | ,E                    | eval visual selection             |
  | ,lv                   | open log buffer vertical          |
  | K                     | look up documentation             |
  | gd                    | go to definition                  |

# Issues

## Customize keybindings

ref: `<url:~/.vim/bundle/conjure/lua/conjure/config.lua#tn=completion>`

# Article: dave yarwood · Conjuring Clojure in Vim

[dave yarwood · Conjuring Clojure in Vim](https://blog.djy.io/conjuring-clojure-in-vim/)

## prepl

```bash
clojure -X:project/new :name mertnuhoglu/conjure01
cd conjure01
```

Edit `~/projects/study/clj/ex/conjure/conjure01/deps.edn`

```bash
git clone https://gist.github.com/daveyarwood/f890bf1529cb633c04b90ce5d5201d6d
mv f890bf1529cb633c04b90ce5d5201d6d daveyarwood_gist
mkdir -p ~/.config/conjure
cp ~/projects/study/clj/ex/conjure/daveyarwood_gist/00-home-dir-conjure.edn ~/.config/conjure/conjure.edn
mkdir -p ~/.config/clojure/src/dave
cp ~/projects/study/clj/ex/conjure/daveyarwood_gist/03-prepl_server.clj ~/.config/clojure/src/dave/prepl_server.clj
```

```bash
clj -M:prepl-server
```

## error: Can't assign requested address

Fix:

```clj
   :main-opts   ["-m" "dave.prepl-server" "50500"]}
```

# Article: Getting started with Clojure, Neovim and Conjure in minutes id=g_11952

[Getting started with Clojure, Neovim and Conjure in minutes](https://oli.me.uk/getting-started-with-clojure-neovim-and-conjure-in-minutes/)

Deoplete and float-preview

Deoplete and floating

floating floating Deoplete Getting started 

