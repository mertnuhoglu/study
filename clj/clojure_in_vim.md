---
title: "Clojure in Vim"
date: 2020-12-20T14:53:28+03:00
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
state: wip

---

# Index: Clojuri in Vim id=g_11843

`Connect to REPL in Conjure <url:file:///~/projects/study/clj/clojure_in_vim.md#r=g_11844>`

`Keybindings Conjure <url:file:///~/projects/study/clj/clojure_in_vim.md#r=g_11845>`

# Issues

## Keybindings Conjure id=g_11845

  | ,eb     | evaluate buffer                 | eval (buf)             |
  | ,ee     | evaluate inner expression       | eval (current-form)    |
  | ,er     | evaluate top expression         | eval (root-form)       |
  | ,e!     | evaluate exp and replace result | eval (root-form)       |
  | ,ls ,lt | open log buffer                 | eval (replace-form)    |
  | "cp     | paste result register           |                        |
  | ,emf    | evaluate exp at mark f          | eval (marked-form [f]) |
	| ,ew     | evaluate word                   | eval (word)            |
	| ,E      | evaluate visual selection       | eval (selection)       |
	| ,Eiw    | evaluate given motion           | eval (selection)       |

Source: `:ConjureSchool`

## Connect to REPL in Conjure id=g_11844

01: Run nREPL server

```bash
cd /Users/mertnuhoglu/projects/study/clj/ex/articles_clojure/e01
clj -Sdeps '{:deps {nrepl {:mvn/version "0.7.0"} cider/cider-nrepl {:mvn/version "0.25.2"}}}' \
    -m nrepl.cmdline \
    --middleware '["cider.nrepl/cider-middleware"]' \
    --interactive
```

02: Open clj file

Open `~/projects/study/clj/ex/articles_clojure/e01/deps.edn`

Now, conjure connects to nREPL server.

Open `~/projects/study/clj/ex/articles_clojure/e01/src/fireplace/e01.clj`

03: Evaluate lines

ref: `Tool: ConjureSchool  <url:file:///~/projects/study/clj/clojure_in_vim.md#r=g_11842>`

# Article: Writing Clojure in Vim id=g_11839

[Writing Clojure in Vim](https://thoughtbot.com/blog/writing-clojure-in-vim)

01: paredit yerine Tim Pope'un vim-sexp eklentisini kullan.

[tpope/vim-sexp-mappings-for-regular-people: vim-sexp mappings for regular people](https://github.com/tpope/vim-sexp-mappings-for-regular-people)

# REPL Tool: fireplace.vim id=g_11840

```bash
cd ~/projects/study/clj/ex/articles_clojure
clojure -X:project/new :name fireplace/e01
```

Edit `~/projects/study/clj/ex/articles_clojure/e01/deps.edn`

```edn
  :nREPL
  {:extra-deps {nrepl/nrepl                {:mvn/version "0.8.3"}
                cider/cider-nrepl          {:mvn/version "0.25.4"}
                com.bhauman/rebel-readline {:mvn/version "0.1.4"}}
   :main-opts  ["-m" "nrepl.cmdline"
                "--middleware" "[cider.nrepl/cider-middleware]"
                "-p" "55555"
                "-i"
                "-f" "rebel-readline.main/-main"]}
```

```bash
clojure -M:nREPL
```

# Article: Getting started with Clojure, Neovim and Conjure in minutes id=g_11841

[Getting started with Clojure, Neovim and Conjure in minutes](https://oli.me.uk/getting-started-with-clojure-neovim-and-conjure-in-minutes/)

Conjure tutorial:

```bash
curl -fL conjure.fun/school | sh
```

# Tool: ConjureSchool  id=g_11842

```vim
:ConjureSchool
```

## Use Conjure to Evaluate Clojure

```bash
clojure -Sdeps '{:deps {nrepl {:mvn/version "0.8.3"} cider/cider-nrepl {:mvn/version "0.25.4"}}}' -m nrepl.cmdline --middleware '["cider.nrepl/cider-middleware"]'
```

# Tool: vim-sexp id=g_11846

ref: `~/projects/study/clj/vim_sexp.Rmd`

