---
title: "Study Spacemacs"
date: 2020-04-17T12:40:45+03:00 
draft: false
description: ""
tags:
categories: emacs
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
    toc: true
    toc_float: true
state: wip

---

# Index Spacemacs

Ref: `Spacemacs Lvl01 20200418 <url:/Users/mertnuhoglu/projects/study/lvlbook/lvl_20200418.md#tn=Spacemacs Lvl01 20200418>`

ref: `Book: Clojure Development with Spacemacs & Cider - Practicalli <url:file:///~/projects/study/emacs/book_clojure_with_spacemacs_practicalli.md#r=g_11800>`

## spacemacs doom emacs refcard id=g_11015

    | spacemacs | description                  | command                     | doom |
    | SPC SPC   | list and search all commands | M-x                         |      |
    | SPC ?     | search for key bindings      | show keybindings            |
    | C-h k     | search for key binding       | describe-key                | h k  |
    | SPC h d f | help on function             | describe-function           | h f |
    | SPC h SPC | list layers                  |                             |
    | SPC f e d | open ~/.spacemacs            | find-dotfile                |
    | SPC f e R | reload .spacemacs            |                             |
    | SPC f r   | file recent                  | counsel-recentf             |
    | SPC q r   | restart emacs                | restart-emacs               |
    | SPC w d   | buffer kill                  | delete-window               |
    | SPC m     | cider menu                   | ,                           |
    | '         | start REPL                   | cider-jack-in               |
    | e         | eval menu                    |                             |
    | e f       | eval expression inline       | cider-eval-defun-at-point   |
    | e c       | eval expression as comment   | cider-eval-defun-to-comment |
    | s a       | cider-switch-to-repl-buffer  |                             |
    | SPC a	r   | open ranger                  |
    | C-x t 2   | tab-bar-new-tab              |
    | gt gT     | tab-next                     |

vim compatible commands: 

``` 
:g//norm ..
.
``` 

		my keybindings <url:file:///~/projects/study/emacs/spacemacs.md#r=g_11016>
		Custom Keybindings Binding keys - Spacemacs <url:file:///~/projects/study/emacs/spacemacs.md#r=g_10998>
		important tasks <url:file:///~/projects/study/emacs/spacemacs.md#r=g_11017>

## My Customizations

Ref: Bind Command key to Meta and Alt key to itself: <url:/Users/mertnuhoglu/projects/study/emacs/spacemacs.md#tp=Bind Command key to Meta and Alt key to itself:>

Paste: ^y

### my keybindings id=g_11016

Ref: `~/.spacemacs` > `dotspacemacs/user-config`

		| ı kmly   | evil-window-left/down/up/right  |
		| ıc ıs ıv | evil-window-delete/split/vsplit |
		| ıo       | maximize-buffer                 |
		| çç       | evil-yank-line                  |
		| üa       | balance-windows                 |
		| üüs      | evil-write-all                  |

# important tasks id=g_11017

		| SPC T n   | switch to next theme listed in dotspacemacs-themes |
		| SPC f e d | open ~/.spacemacs                                  | find-dotfile     |
		| SPC ?     | search for key bindings                            | show keybindings |
		| SPC SPC   | list and search all commands                       | M-x              |
		| SPC f     | file related commands                              |
		| SPC f t   | neotree file browser                               |
		| SPC q r   | restart emacs                                      |
		| SPC f s   | save file                                          |
		| SPC f S   | save file all                                      |
		| SPC f e R | reload .spacemacs                                  |
		| RET       | jump/goto link                                     |
		| SPC TAB   | switch to other buffer                             |

## Osx Command Meta Key Bindings: id=g_12031

Bind Command key to Meta and Alt key to itself:

		M-x customize-group > ns > Ns Command Modifier = super

		Alt -> none
		Command -> super (böylece #v komutunu kullanabilirsin)

## Setup Clojure For Spacemacs

opt03: bir clojure dosyası aç

https://medium.com/@jgolden/four-questions-every-marketplace-startup-should-be-able-to-answer-defb0590e049

opt01: Edit `~/.spacemacs`

Add: `clojure` to `dotspacemacs-configuration-layers`

Restart: SPC q r

# Problems

## Emacs cider içinde Ctrl-P `^P` ile önceki komutlara nasıl erişilir?

`C-c M-p`
 
## Create new tab id=g_11916

[emacs-from-scratch/Emacs-Tips-06.org at 82f03806d90eb356b815cf514d10b6d863a2cbdc · daviwil/emacs-from-scratch](https://github.com/daviwil/emacs-from-scratch/blob/82f03806d90eb356b815cf514d10b6d863a2cbdc/show-notes/Emacs-Tips-06.org)

		| tab-bar-mode | enable tab bar  |
		| C-x t 2      | tab-bar-new-tab |
		| gt gT        | tab-next        |

## Help komutları

		| SPC SPC   | list and search all commands | M-x                         |
		| SPC ?     | search for key bindings      | show keybindings            |
		| C-h k     | search for key binding       | describe-key                |
		| SPC h d f | help on function             | describe-function           |

01: `describe-key` = `C-h k`

Örneğin: `C-h k (`: Bu vim'deki `:verbose map (` komutuna karşılık gelir.

## How to navigate functions in elisp files? id=g_11810

How to navigate to next defun in `.spacemacs`?

    | SPC s j | counsel-imenu |

![counsel-imenu](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201126_214953.jpg)

# Quickstart Cider

`SPC m` = `,`

Prefix: `SPC m` or `,`

		| SPC m | cider menu                  | ,                          |
		| '     | start REPL                  | cider-jack-in              |
		| s i   | start REPL                  | cider-jack-in              |
		| e     | eval menu                   |                            |
		| e f   | eval expression inline      | cider-eval-defun-at-point  |
		| e ;   | eval expression as comment  |                            |
		| s     | repl menu (send)            |                            |
		| s n   | send ns form to repl        | cider-send-ns-form-to-repl |
		| s f   | cider-send-function-to-repl |                            |
		| s a   | cider-switch-to-repl-buffer |                            |

Ref: `The REPL <url:file:///~/projects/study/emacs/spacemacs.md#r=g_10828>`

# Cider REPL

		, s a			switch between REPL and file

# Spacemacs

## Install Spacemacs

https://github.com/syl20bnr/spacemacs#macos

``` bash
brew tap d12frosted/emacs-plus
brew install emacs-plus
rm /Applications/Emacs.app
ln -s /usr/local/Cellar/emacs-plus/*/Emacs.app/ /Applications/
``` 

## OSX Layer

[OSX layer](https://www.spacemacs.org/layers/+os/osx/README.html)

`command` is set to `super`. `option` is set to `meta`.

## QuickStart Spacemacs

https://www.spacemacs.org/doc/QUICK_START

### 1 Configuration layers

`configuration layer`: self-contained unit

`~/.spacemacs` controls which layers to load

configuration layer: a directory with `packages.el` file

`packages.el` defines packages to be downloaded

### 4 Learning Spacemacs

``` bash
dotspacemacs-editing-style 'vim
``` 

Leader keys

		SPC	space key
		,

Evil tutor

		SPC h T


# Spacemacs DOCUMENTATION Manual

https://www.spacemacs.org/doc/DOCUMENTATION.org

## Core Pillars

Mnemonic: 

		a applications
		b	buffer
		e errors
		f files
		g git
		h help
		j	jump/join/split
		m major-mode-cmd
		p	project
		s	search
		T UI toggles
		w windows
		z zoom

Discoverable: displays key bindings, query system

Consistent

Crowd-configured

## Highlighted feature

Modal editing like vim

Integrate with evil (vim) states

Helpful community: gitter and irc

## Update and Rollback

Automatic updates: every startup

Update packages: `RET` on `Update Packages` in startup page. 

# Spacemacs Help Documents

		SPC h SPC		| spacemacs manuals

# syl20bnr docs

## Spacemacs conventions

https://github.com/syl20bnr/spacemacs/blob/master/doc/CONVENTIONS.org

		| SPC m   | reserved for major mode keybindings |
		| SPC o   | reserved for user                   |
		| SPC m o | reserved for user                   |
		| ,       | SPC m                               |
		| SPC m g | goto menu (navigation)              |
		| SPC m c | confirm                             |
		| SPC m e | eval menu                           |
		| SPC m s | send to repl                        |
		| SPC d   | debug menu                          |
		| SPC m h | header menu for markup languages    |
		| SPC m i | insert common elements for markups  |
		| SPC m x | text manipulation for markups       |
		| g hjkl  | go up/next/prev/down heading        |
		| M-hjkl  | move element up/next/prev/down      |
		| SPC m t | table menu for markups              |
		| SPC m t | test menu                           |
		| SPC m T | major mode toggles                  |
		| SPC tT  | global toggles                      |
		| SPC m r | refactoring menu                    |
		| SPC m = | format menu                         |
		| SPC h   | help menu                           |
		
Major mode prefix: `SPC m`

User prefix: `SPC o`
		
Navigation:

Code navigation prefix: `SPC m g`


### Key bindings conventions

## Migrating from Vim

https://github.com/syl20bnr/spacemacs/blob/master/doc/VIMUSERS.org

### Transient States

		SPC <group> .

#### Markup

		SPC m h				| header bindings
		SPC m i				| insert ...

### Running commands

		:							| ex commands
		SPC SPC				| emacs commands

### Buffer and window management

		SPC b b				| buffer
		SPC b d				| buffer kill

^W komutlarının karşılıkları:

		| SPC w s | window split    |
		| SPC w j | window navigate |
		| SPC w d | buffer kill     |

---

Files

		| SPC f f | file open    | counsel-find-file |
		| SPC f r | file recent  | counsel-recentf   |
		| SPC f s | file save :w | save-buffer       |

Help

		| SPC h d f | doc function   |
		| SPC h d k | doc keybinding |
		| SPC <F1>  | search doc     |

Exploring

		| SPC h SPC | list layers      |
		| SPC ?     | list keybindings |
		
## Customization

`.spacemacs` file

4 functions:

		dotspacemacs/layers
		dotspacemacs/init
		dotspacemacs/user-init
		dotspacemacs/user-config

### Emacs Lisp

Setting variable value:

		(setq variable value)

Keybindings

		(define-key map new-keybinding function)

Functions

		(defun func-name (args) body)

Layers

Installing a package

		dotspacemacs-additional-packages

Loading packages

		(use-package package-name :defer t)

# Article: Spacemacs - First Impressions From an Emacs Driven Developer | jr0cket

http://jr0cket.co.uk/2015/08/spacemacs-first-impressions-from-an-emacs-driven-developer.html

## Navigating with Helm

Emacs: `M-x command-name`

Spacemacs: `SPC ...`

Commands are grouped:

		S	spelling
		T	themes
		a	applications
		b	buffers
		f	files
		g	git

Helm: incremental completion and selection

## Other features

numbered buffers: `SPC <number>`

smartparens:

smooth scrolling

# Article: Spacemacs + Clojure

https://joyheron.com/post/sketchnote/clojure-meetup/2017/08/17/spacemacs.html

		M-m p t				| project tree file manager
		,							| clojure specific menu
		M-m b n				| switch between buffers
		M-x SPC				| list of all commands
		SPC SPC				| list of all commands (spacemacs)

`SPC` replaces `M-x` prefix in Spacemacs.

Start repl:

		M-x cider-jack-in		| opt01
		
opt02:

``` bash
lein repl
M-x cider-connect
``` 

Install in `~/.lein/profiles.clj`

https://github.com/clojure-emacs/cider-nrepl

``` bash
:plugins [[cider/cider-nrepl "0.15.0"]]
``` 

Evaluating

`C-c C-k` evaluates the whole namespace

`C-c C-e` evaluates inline

`C-c C-p` evaluates and prints 

# article: spacemacs (vim mode) cheatsheet

https://simpletutorials.com/c/3036/spacemacs

		spc q q					| quit
		spc z x					| font size
		spc f f					| open file
		spc f f					| create file
		spc f s					| save file
		spc b b					| reopen file

		g c							| comment out 

# Article: Migrating from Vim

https://www.spacemacs.org/doc/VIMUSERS.html

## Basic

### 3.1 Terms

Modes vs States

vim: visual mode, insert mode

emacs: evil-insert-state

minor-mode

Layers:

layers similar to vim plugins

Transient-states

run similar commands without repeatedly pressing <Leader> key

pattern: `<Leader> <group> .`

`q` exit

### Keybinding conventions

`<Leader>`: `SPC`

`SPC m`: keybindings for language specific commands

### Buffer and window management

buffers: same in vim and emacs.

		SPC b					| keybindings for buffers
		SPC b	b 			| create a buffer / search open buffers
		SPC b	n 			| next buffer 
		SPC b	p 			| prev buffer 

# Configuration layers

Ex: `python` layer provides auto-completion, syntax checking packages.

## Custom Keybindings Binding keys - Spacemacs id=g_10998

ref: `Custom keybindings for emacs <url:/Users/mertnuhoglu/projects/study/emacs/emacs.md#tn=Custom keybindings for emacs>`

`https://github.com/syl20bnr/spacemacs/blob/master/doc/DOCUMENTATION.org#binding-keys`

opt01: SPC h SPC > Spacemacs > Binding keys

		(global-set-key (kbd "C-]") 'forward-char)

opt02: vim editing style

		(define-key evil-insert-state-map (kbd "C-]") 'forward-char)

opt03: behind a leader key (after SPC)

Most keybindings are defined this way

		(spacemacs/set-leader-keys "C-]" 'forward-char)

		(spacemacs/set-leader-keys "ar" 'ranger)

Bu durumda `SPC a r` tuşuna bağlanıyor komut.

opt04: behind a leader key + in a major mode

		(spacemacs/set-leader-keys-for-major-mode 'emacs-lisp-mode "C-]" 'forward-char)

opt05: nested keys (after SPC)

		(spacemacs/declare-prefix "]" "bracket-prefix")
		(spacemacs/set-leader-keys "]]" 'double-bracket-command)

That is: `SPC ] ]`

## 6. Configuration layers

### 6.3 Configure packages

#### 6.3.2 Without a layer

https://www.spacemacs.org/doc/DOCUMENTATION.html#orgheadline21

Edit `~/.spacemacs` under `dotspacemacs-additional-packages`

``` bash
(defun dotspacemacs/layers ()
  "Configuration Layers declaration..."
  (setq-default
   ;; ...
   dotspacemacs-additional-packages '(llvm-mode dts-mode)
   ;; ...
   ))
``` 

### 8 Concepts

#### 8.1 Editing Styles

#### 8.2 States

10 states

		normal	orange
		insert	green
		visual	gray
		motion	purple
		emacs	blue
		replace	chocolate
		hybrid	blue
		evilified	light brown
		lisp	pink
		iedit	red
		iedit-insert	red

### 14 Commands

#### 14.1.2 Executing Vim and Emacs ex/M-x commands

		ex command	:
		Emacs M-x		SPC SPC

#### 14.2 Reserved for user

		SPC o
		SPC m o

Ex:

``` bash
(spacemacs/set-leader-keys "oc" 'org-capture)
``` 

This binds `SPC o c` to run org mode capture 

#### 14.3 Completion

Helm:

Return (escape) to helm minibuffer: `SPC w b`

Helm transient state: like vim unite/fzf

		s-M-SPC

#### 14.4 Discovering

Which-key:

Help buffer displayed: `SPC`

Getting help (introspection)

		SPC h d
			b	bindings
			c	current character
			f	function
			k	key
			K	keymap

Other help key bindings:

		SPC h SPC			| spacemacs documentation using helm
		SPC h i  			| search in info
		SPC h m  			| search man pages

Available Layers

		SPC h SPC			| help-spacemacs-help

New packages

		package-list-packages

New packages (opt): Paradox

		SPC a k				| launch paradox
		f k						| filter keywords
		s							| star
		S *						| sort by star

Toggles

		SPC h t				| helm-spacemacs-help-toggles

# Article: A Package in a league of its own: Helm

http://tuhdo.github.io/helm-intro.html

