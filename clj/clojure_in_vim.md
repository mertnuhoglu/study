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

# clojure_in_vim
  id:: daaaca30-6faf-44c4-9034-6cf27d278ebd

- ## [[f/ndx]]
  - Index: Clojure in Vim || ((619584b0-7558-4663-9946-15a7c29dd962))
  - Issues || ((9a28408f-a130-4472-b2c6-a0506b09088b))
  - Linter hangi eklentilerle kontrol ediliyor? || ((4b8ae701-e83f-4cfb-8775-af7d9c18bd57))
  - Show Documentation in Conjure Floating Window || ((35f50d3b-9518-4698-89e7-c17b20a20fde))
  - Keybindings Conjure || ((03ad4151-305e-4e43-a0c1-88e3c2bc473f))
  - Connect to REPL in Conjure || ((2d2e0e11-e2f8-46de-ad40-44eac38b54a3))
  - Article: Writing Clojure in Vim || ((6a458b99-bd65-439b-a33b-1bff3988bc70))
  - REPL Tool: fireplace.vim || ((0d2ba8a9-6a60-40a7-a630-a7c003025bf0))
  - Article: Getting started with Clojure, Neovim and Conjure in minutes || ((818f69e0-e04c-448e-9ac6-260618b07eb7))
  - Tool: ConjureSchool  || ((4edf3e37-e602-4b87-adca-160ce0adbc80))
  - Use Conjure to Evaluate Clojure || ((c181dc4b-bdef-49bc-a689-81f33c4c4f65))
  - Tool: vim-sexp || ((2087ff43-4150-4fd6-befd-30ab37b64b97))
  - Tool: clojure-lsp || ((b12b1d28-0b4c-4a61-bdf0-30db0ac7765c))
  - Clients || ((d3d97c0d-9290-458b-a245-aa54d4ce737a))
  - Settings || ((fdd84538-be98-4743-86fa-439eafcb0afe))
  - Issues || ((fea2067d-7b49-475e-b763-5daac7b5acc4))
  - Tool: clj-kondo in vim || ((64a76ee2-ea1c-4262-b87c-ffa77cc0cbe2))
  - Vanilla way || ((d1c44faf-103d-4922-a562-82736bfd0415))

# f/pnt

- ## Index: Clojure in Vim id=g11843
  id:: 619584b0-7558-4663-9946-15a7c29dd962

	Connect to REPL in Conjure || ((2d2e0e11-e2f8-46de-ad40-44eac38b54a3))
	Keybindings Conjure || ((03ad4151-305e-4e43-a0c1-88e3c2bc473f))
	Tool: ConjureSchool  || ((4edf3e37-e602-4b87-adca-160ce0adbc80))
	Tool: vim-sexp || ((2087ff43-4150-4fd6-befd-30ab37b64b97))

	| Disable linter: #r=g11965 | call lsp#disable_diagnostics_for_buffer() |

- # Issues
  id:: 9a28408f-a130-4472-b2c6-a0506b09088b

- ## Linter hangi eklentilerle kontrol ediliyor?
  id:: 4b8ae701-e83f-4cfb-8775-af7d9c18bd57

İki tane:

01: `^` işaretli linter hataları: `.vim/pack`

02: `>>` işareti linter hataları:

```bash
Plug 'neoclide/coc.nvim', {'branch': 'release'}
Plug 'prabirshrestha/vim-lsp'
Plug 'mattn/vim-lsp-settings'
```

- ## Show Documentation in Conjure Floating Window id=g11957
  id:: 35f50d3b-9518-4698-89e7-c17b20a20fde

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

- ## Keybindings Conjure id=g11845
  id:: 03ad4151-305e-4e43-a0c1-88e3c2bc473f

	": conjure {{{  || ((9eb72a62-fd74-444e-b2ea-a89f8bc90115))
	" conjure eval mappings || ((896b249a-bd3f-412e-9466-b9950c54d20b))
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

- ## Connect to REPL in Conjure id=g11844
  id:: 2d2e0e11-e2f8-46de-ad40-44eac38b54a3

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

- ## Article: Writing Clojure in Vim id=g11839
  id:: 6a458b99-bd65-439b-a33b-1bff3988bc70

[Writing Clojure in Vim](https://thoughtbot.com/blog/writing-clojure-in-vim)

01: paredit yerine Tim Pope'un vim-sexp eklentisini kullan.

[tpope/vim-sexp-mappings-for-regular-people: vim-sexp mappings for regular people](https://github.com/tpope/vim-sexp-mappings-for-regular-people)

- ## REPL Tool: fireplace.vim id=g11840
  id:: 0d2ba8a9-6a60-40a7-a630-a7c003025bf0

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

- ## Article: Getting started with Clojure, Neovim and Conjure in minutes id=g11841
  id:: 818f69e0-e04c-448e-9ac6-260618b07eb7

[Getting started with Clojure, Neovim and Conjure in minutes](https://oli.me.uk/getting-started-with-clojure-neovim-and-conjure-in-minutes/)

Conjure tutorial:

```bash
curl -fL conjure.fun/school | sh
```

- ## Tool: ConjureSchool  id=g11842
  id:: 4edf3e37-e602-4b87-adca-160ce0adbc80

```vim
:ConjureSchool
```

- ## Use Conjure to Evaluate Clojure
  id:: c181dc4b-bdef-49bc-a689-81f33c4c4f65

```bash
clojure -Sdeps '{:deps {nrepl {:mvn/version "0.8.3"} cider/cider-nrepl {:mvn/version "0.25.4"}}}' -m nrepl.cmdline --middleware '["cider.nrepl/cider-middleware"]'
```

- ## Tool: vim-sexp id=g11846
  id:: 2087ff43-4150-4fd6-befd-30ab37b64b97

ref: `~/projects/study/clj/vim_sexp.md`

- ## Tool: clojure-lsp id=g11956
  id:: b12b1d28-0b4c-4a61-bdf0-30db0ac7765c

[clojure-lsp/clojure-lsp: Language Server (LSP) for Clojure](https://github.com/clojure-lsp/clojure-lsp)

- ## Clients
  id:: d3d97c0d-9290-458b-a245-aa54d4ce737a

[Clients - Clojure LSP](https://clojure-lsp.github.io/clojure-lsp/clients/)

ref: `~/projects/private_dotfiles/.config/nvim/coc-settings.json`

ref: `~/projects/private_dotfiles/.config/.lsp/settings.json`

- ## Settings
  id:: fdd84538-be98-4743-86fa-439eafcb0afe

[Settings - Clojure LSP](https://clojure-lsp.github.io/clojure-lsp/settings/)

ref: `~/.lsp/config.edn`

- ## Issues
  id:: fea2067d-7b49-475e-b763-5daac7b5acc4

- Disable linter: id=g11965
  id:: 9690fc29-c4c3-4fb6-a1e0-a4f95db82694

```vim
call lsp#disable_diagnostics_for_buffer()
```

- ## Tool: clj-kondo in vim id=g11964
  id:: 64a76ee2-ea1c-4262-b87c-ffa77cc0cbe2

[clj-kondo/editor-integration.md at master · clj-kondo/clj-kondo](https://github.com/clj-kondo/clj-kondo/blob/master/doc/editor-integration.md)

- ## Vanilla way
  id:: d1c44faf-103d-4922-a562-82736bfd0415

Run as vim ex command:

```vim
compiler clj-kondo
make
```

Also check: `" automatic linting clojure files <url:file:///~/projects/vim_repos/my-vim-custom/plugin/my-vim-custom.vim#r=g11963>`

ref: `~/.vim/compiler/clj-kondo.vim`


