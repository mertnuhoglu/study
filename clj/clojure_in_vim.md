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

# Index: Clojure in Vim id=g11843

	Connect to REPL in Conjure <url:#r=g11844>
	Keybindings Conjure <url:#r=g11845>
	Tool: ConjureSchool  <url:file:///~/projects/study/clj/clojure_in_vim.md#r=g11842>
	Tool: vim-sexp <url:file:///~/projects/study/clj/clojure_in_vim.md#r=g11846>

	| Disable linter: #r=g11965 | call lsp#disable_diagnostics_for_buffer() |

# Issues

## Linter hangi eklentilerle kontrol ediliyor?

İki tane:

01: `^` işaretli linter hataları: `.vim/pack`

02: `>>` işareti linter hataları:

```bash
Plug 'neoclide/coc.nvim', {'branch': 'release'}
Plug 'prabirshrestha/vim-lsp'
Plug 'mattn/vim-lsp-settings'
```

## Show Documentation in Conjure Floating Window id=g11957

Put in `~/.vimrc`

```vim
set keywordprg=:call\ <SID>show_documentation()
...
    call CocActionAsync('doHover')
```

01: `,eb` Evaluate buffer

02: `K`

Now, hover over any clojure function and press `K`

It works on any word as well.

## Keybindings Conjure id=g11845

	conjure <url:file:///~/projects/vim_repos/my-vim-custom/plugin/my-vim-custom.vim#r=g12847>
	conjure eval mappings <url:file:///~/projects/vim_repos/my-vim-custom/plugin/my-vim-custom.vim#r=g12840>
	let g:conjure#mapping#prefix = " m"
	lua require('conjure.eval')['current-form']()
	let g:conjure#mapping#eval_root_form = "ed"
  | ,eb     | evaluate buffer                 | eval (buf)             |
  | ,ee     | evaluate inner expression       | eval (current-form)    |
  | ,ed     | evaluate top expression         | eval (root-form)       |
  | ,e!     | evaluate exp and replace result | eval (root-form)       |
  | ,ls ,lt | open log buffer                 | eval (replace-form)    |
  | "cp     | paste result register           |                        |
  | ,emf    | evaluate exp at mark f          | eval (marked-form [f]) |
	| ,ew     | evaluate word                   | eval (word)            |
	| ,E      | evaluate visual selection       | eval (selection)       |
	| ,Eiw    | evaluate given motion           | eval (selection)       |

Source: `:ConjureSchool`

## Connect to REPL in Conjure id=g11844

opt01: run nREPL server with global deps.edn alias settings

```bash
clojure -M:repl/rebel-nrepl
clojure -M:inspect/reveal-nrepl
``` 

opt02: run nREPL server with custom arguments

01: Run nREPL server

```bash
cd /Users/mertnuhoglu/projects/study/clj/ex/articles_clojure/e01
clj -Sdeps '{:deps {nrepl {:mvn/version "0.9.0-beta2"} cider/cider-nrepl {:mvn/version "0.26.0"}}}' \
    -m nrepl.cmdline \
    --middleware '["cider.nrepl/cider-middleware"]' \
    --interactive
```

02: Open clj file

Open `~/projects/study/clj/ex/articles_clojure/e01/deps.edn`

Now, conjure connects to nREPL server.

Open `~/projects/study/clj/ex/articles_clojure/e01/src/fireplace/e01.clj`

03: Evaluate lines

ref: `Tool: ConjureSchool  <url:#r=g11842>`

# Article: Writing Clojure in Vim id=g11839

[Writing Clojure in Vim](https://thoughtbot.com/blog/writing-clojure-in-vim)

01: paredit yerine Tim Pope'un vim-sexp eklentisini kullan.

[tpope/vim-sexp-mappings-for-regular-people: vim-sexp mappings for regular people](https://github.com/tpope/vim-sexp-mappings-for-regular-people)

# REPL Tool: fireplace.vim id=g11840

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

# Article: Getting started with Clojure, Neovim and Conjure in minutes id=g11841

[Getting started with Clojure, Neovim and Conjure in minutes](https://oli.me.uk/getting-started-with-clojure-neovim-and-conjure-in-minutes/)

Conjure tutorial:

```bash
curl -fL conjure.fun/school | sh
```

# Tool: ConjureSchool  id=g11842

```vim
:ConjureSchool
```

## Use Conjure to Evaluate Clojure

```bash
clojure -Sdeps '{:deps {nrepl {:mvn/version "0.8.3"} cider/cider-nrepl {:mvn/version "0.25.4"}}}' -m nrepl.cmdline --middleware '["cider.nrepl/cider-middleware"]'
```

# Tool: vim-sexp id=g11846

ref: `~/projects/study/clj/vim_sexp.md`

# Tool: clojure-lsp id=g11956

[clojure-lsp/clojure-lsp: Language Server (LSP) for Clojure](https://github.com/clojure-lsp/clojure-lsp)

## Clients

[Clients - Clojure LSP](https://clojure-lsp.github.io/clojure-lsp/clients/)

ref: `~/projects/private_dotfiles/.config/nvim/coc-settings.json`

ref: `~/projects/private_dotfiles/.config/.lsp/settings.json`

## Settings

[Settings - Clojure LSP](https://clojure-lsp.github.io/clojure-lsp/settings/)

ref: `~/.lsp/config.edn`

## Issues

Disable linter: id=g11965

```vim
call lsp#disable_diagnostics_for_buffer()
```

# Tool: clj-kondo in vim id=g11964

[clj-kondo/editor-integration.md at master · clj-kondo/clj-kondo](https://github.com/clj-kondo/clj-kondo/blob/master/doc/editor-integration.md)

## Vanilla way

Run as vim ex command:

```vim
compiler clj-kondo
make
```

Also check: `" automatic linting clojure files <url:file:///~/projects/vim_repos/my-vim-custom/plugin/my-vim-custom.vim#r=g11963>`

ref: `~/.vim/compiler/clj-kondo.vim`


