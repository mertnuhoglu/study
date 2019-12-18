---
title: "Study Emacs"
author: "Mert Nuhoglu"
output:
  html_document: default
    toc: true
    toc_float: true
---

# Tasks

		SPC T n			| switch to next theme listed in dotspacemacs-themes
		SPC f e d		| open ~/.spacemacs
		SPC ?				| search for key bindings
		SPC SPC			| list and search all commands
		SPC f				| file related commands
		SPC f t			| neotree file browser
		SPC q r			| restart emacs
		SPC f s			| save file
		SPC f S			| save file all
		SPC f e R		| reload .spacemacs
		RET					| jump/goto link
		SPC TAB			| switch to other buffer

Bind Command key to Meta and Alt key to itself:

		M-x customize-group ns > Ns Command Modifier = meta

		Alt -> none
		Command -> meta

## Escape to evil normal mode

`C-g C-z` quit

## Check Current Mode

### Article: Emacs: What's Major Mode

http://ergoemacs.org/emacs/emacs_whats_major_mode.html

Ex: turn on shell mode:

`M-x shell` 

Most basic mode: `fundamental-mode`

List all modes

`M-x apropos-command` -mode

## Setup Clojure For Spacemacs

opt03: bir clojure dosyası aç

https://medium.com/@jgolden/four-questions-every-marketplace-startup-should-be-able-to-answer-defb0590e049

opt01: Edit `~/.spacemacs`

Add: `clojure` to `dotspacemacs-configuration-layers`

Restart: SPC q r

## Custom keybindings for emacs

ref: `Binding keys - Spacemacs <url:/Users/mertnuhoglu/projects/study/emacs/study_emacs.md#tp=Binding keys - Spacemacs>`

## Enable smartparens keybindings

İlk kurulumda smartparens kısayolları çalışmıyordu. Bunu öğrenmek için:

`C-h k` `C-M-a` 

Çözüm: opt08 etkin minor modes neler?

Ref: `Fakat bende bu komut aktif değil. Neden? <url:/Users/mertnuhoglu/projects/study/logbook/log_20191205.md#tn=Fakat bende bu komut aktif değil. Neden?>`

https://emacs.stackexchange.com/questions/10785/get-list-of-active-minor-modes-in-buffer

Get list of active minor modes in buffer - Emacs Stack Exchange

``` bash
M-x describe-mode
``` 

.Smartparens

> You can enable pre-set bindings by customizing
> ‘sp-base-key-bindings’ variable.  The current content of
> ‘smartparens-mode-map’ is:

`C-h v` `sp-base-key-bindings` > customize > "Use smartparens set of bindings"

Bu şekilde kısayollar aktifleşti.

## Use non-unicode chars in mode line

``` bash
   dotspacemacs-mode-line-unicode-symbols nil
``` 

## Other 

### 20191213 

`SPC 2` 2 numaralı windowa geçiş yap

`SPC l w` workspace transient state `2` 2 numaralı workspace'e geçiş yap

# Tools

## Rainbow Parantheses for Vim

https://github.com/junegunn/rainbow_parentheses.vim

``` bash
Plug 'junegunn/rainbow_parentheses.vim'
``` 


# Evil Tutor

		SPC h T

		^j ^k down/up by section
		^h ^l	left/right

		:qa! quit
		:wqa save and quit
		M-x evil-tutor-start	| resume tutorial

# Spacemacs

## Install Spacemacs

https://github.com/syl20bnr/spacemacs#macos

``` bash
brew tap d12frosted/emacs-plus
brew install emacs-plus
rm /Applications/Emacs.app
ln -s /usr/local/Cellar/emacs-plus/*/Emacs.app/ /Applications/
``` 

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

# Article: How to Use Emacs, an Excellent Clojure Editor | Clojure for the Brave and True

https://www.braveclojure.com/basic-emacs/

``` bash
cd ~/codes/emacs
git clone https://github.com/flyingmachine/emacs-for-clojure/
``` 

## REPL

		M-x cider-jack-in

# Article: Spacemacs (Vim mode) Cheatsheet

https://simpletutorials.com/c/3036/Spacemacs

		SPC q q					| quit
		SPC z x					| font size
		SPC f f					| open file
		SPC f f					| create file
		SPC f s					| save file
		SPC b b					| reopen file

		g c							| comment out 

# Article: Mastering Eshell - Mastering Emacs

https://www.masteringemacs.org/article/complete-guide-mastering-eshell

## Overview

It doesn't support interactive programs. You need `ansi-term` for this.

It is not bash or zsh. 

## Commands

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

# Article: Introduction · Clojure development with Spacemacs & Cider

https://practicalli.github.io/spacemacs/

Layers:

## Enhance Clojure experience · Clojure development with Spacemacs & Cider

https://practicalli.github.io/spacemacs/install-spacemacs/enhance-clojure-experience.html

Available layers

		SPC h SPC		| list layers
		TAB / RET		| doc of layer

Smartparens 

Evil Safe Structural Editing
 
		SPC m T s		| enable evil safe editing

Enable for all modes:

``` bash
(spacemacs/toggle-evil-safe-lisp-structural-editing-on-register-hooks)
``` 

Open Maximised / Full Screen 

``` bash
dotspacemacs-fullscreen-at-startup t

dotspacemacs-maximized-at-startup t
``` 

Line Numbers

		SPC t n
		SPC t r		| relative line numbering

		SPC j l		| jump to line

``` bash
dotspacemacs-line-numbers 'relative
``` 

## Spacemacs Basics 

Space bar - main menu: `SPC SPC`

Spacemacs Help system

		SPC h SPC		| help documents
		SPC h d f		| help on function
		SPC h d k		| help on keybinding

### Emacs Basics

		File
		Buffer
		Window
		Frame
		Status
		Minibuffer

#### Working with windows

		SPC 1			| jump to window 1
		SPC w			| open window menu
		SPC w .		| window transient menu
		SPC w 2		| 2 window layout
		SPC w m		| maximize window
		SPC w d		| delete window 

Golden ration

### Vim Basics 

## Working with Projects 

### File menu

		SPC f	  		| file menu
		SPC f	f 		| open file anywhere
			TAB				| complete filename
			^h				| parent dir
			^y				| paste kill ring
			^j ^k			| down/up
		SPC f	f 		| create file

### Buffer management

		SPC b	b 		| list open buffers
		SPC b	. 		| buffer transient state
			n					| cycle next
			N / p			| cycle prev
			k					| kill current buffer

### Projectile menu

A project can be:

- .git directory
- a clojure leiningen project
- a .projectile file

		SPC /				| search in project
		SPC p				| project menu
		SPC p	f 		| open file in project
		SPC f	f 		| open file in folder
		SPC p	b 		| open buffer in project
		SPC p	a 		| toggle between test/code files
		SPC p	% 		| search + replace text in project

### Layouts

		SPC l	  		| layout transient state


### File managers

#### Ranger

		SPC a	r 		| open ranger
			j/k
			h/l				| parent/child
			q					| quit

## Create a Clojure Project

https://practicalli.github.io/spacemacs/create-a-project/

``` bash
cd /Users/mertnuhoglu/projects/study/emacs/ex/study_emacs
lein new ex01
``` 

### Using emacs eshell

		SPC SPC eshell 		| run a terminal
		, '  							| start new repl (cider-jack-in)
		, s s							| switch repl/editor
		, s q							| quit repl
		, e e							| eval prev expression
		, e f							| show expression result
		SPC m s c					| connect to an existing repl
		, s n							| eval namespace of current file

states:

		i		vim insert state
		^z	emacs state
			,		repl commands

# Spacemacs DOCUMENTATION Manual

https://www.spacemacs.org/doc/DOCUMENTATION.org

## Core Pillars

Mnemonic: 

		b	buffer
		p	project
		s	search
		h help
		a applications
		e errors
		f files
		g git
		j	jump/join/split
		m major-mode-cmd
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

# Configuration layers

Ex: `python` layer provides auto-completion, syntax checking packages.

## Binding keys - Spacemacs

ref: `Custom keybindings for emacs <url:/Users/mertnuhoglu/projects/study/emacs/study_emacs.md#tn=Custom keybindings for emacs>`

https://github.com/syl20bnr/spacemacs/blob/master/doc/DOCUMENTATION.org#binding-keys

opt01: SPC h SPC > Spacemacs > Binding keys

		(global-set-key (kbd "C-]") 'forward-char)

opt02: vim editing style

		(define-key evil-insert-state-map (kbd "C-]") 'forward-char)

opt03: behind a leader key (after SPC)

		(spacemacs/set-leader-keys "C-]" 'forward-char)

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

# Article: Emacs: M-x customize Tutorial

http://ergoemacs.org/emacs/emacs_custom_system.html

Textual GUI system for setting user preferences.

		M-x customize

Ref: `49.1 Easy Customization Interface <url:/Users/mertnuhoglu/projects/study/emacs/study_emacs.md#tn=49.1 Easy Customization Interface>`

# Article: Emacs Manual

## 49.1 Easy Customization Interface

Settings

Most `settings` are `customizable variables` also called `user options`

`M-x customize` opens `customization buffer` 

### 49.1.1 Customization Groups

Master group: `Emacs`

Hierarchical groups. 

# Spacemacs Help Documents

		SPC h SPC		| spacemacs manuals

## Migrating from Vim

### Transient States

		SPC <group> .

## Spacemacs conventions

		SPC m				| major mode keybindings
		
### Key bindings conventions

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

		SPC w s				| window split 
		SPC w j				| window navigate
		SPC w d				| buffer kill

---

Files

		SPC f f				| file open
		SPC f r				| file recent
		SPC f s				| file save :w

Help

		SPC h d f				| doc function 
		SPC h d k				| doc keybinding
		SPC <F1>				| search doc

Exploring

		SPC h SPC				| list layers
		SPC ?    				| list keybindings
		
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

# Article: Helm Manual

https://github.com/emacs-helm/helm/wiki

minibuffer

completion window

Use RET instead of TAB for completion

# Article: A Package in a league of its own: Helm

http://tuhdo.github.io/helm-intro.html

Eğer seçilecek çok sayıda öğe varsa:

- C-SPC	mark candidates
- C-c C-i insert into current buffer

# Article: which-key

https://github.com/justbur/emacs-which-key

A minor mode

It displays available key bindings after prefix like `C-x`

# Article: Emacs Mini Manual (PART 1) - THE BASICS

http://tuhdo.github.io/emacs-tutor.html

## Appendix

### Why Emacs

A development platform. A virtual machine that interprets its own code. 

``` bash
(defun hello ()
  (interactive)
	(message "Hello"))
``` 

### Emacs history

Lisp Machine

"The Ghost in the Lisp Machine"

## Programming

Jump to any source file

Autocompletion

Showing function arguments

Comment multiple lines

Debugger GDB

Diff between current and latest revision

Magit: 

Live grep

Select any file quickly

PDF Reader

## Why this guide?

### A bit of history

### Swap Control and Capslock

## Concepts

### Command

Any key calls `self-insert-command`

2 types of functions:

- Normal functions
- Commands: interactive

`M-x` is `execute-extended-command`

### Emacs Key Notation

``` bash
C-
M-
S-
DEL
RET
SPC
ESC
TAB
``` 

Prefix key: `C-x r l`. Here `C-x r` is prefix.

List all commands with a prefix: Append `C-h` 

Ex: `C-x r C-h` list all commands after `C-x r`

`C-g` executes `keyboard-quit`. It cancels any key sequence.

### Keys are easy to remember

`C-x` for default and global bindings

`C-c` for users to customize

`C-u` for altering behaviors of commands

`C-<number>` for repeating command

### Ask for help

#### Built-in help

`C-h m` runs `describe-mode`

`C-h w` runs `where-is` to get keybindings of a command

`C-h c` runs `describe-key-briefly` opposite of `where-is`

`C-h k` runs `describe-key` opposite of `where-is` including documentation

`C-h e` runs `view-echo-area-messages`

`C-h v` runs `describe-variable` also allows customization.

`C-h C-h` runs `help-for-help`

`<Prefix> C-h` lists all commands with `<Prefix>`

#### Apropos

https://www.gnu.org/software/emacs/manual/html_node/emacs/Apropos.html

apropos pattern: a word, a list of words, or a regex

Ex: Answer to: What are the commands for working with files?

`C-h a` runs `apropos-command`: search for commands

`M-x apropos` runs : search for functions and variables

#### Info

`M-x info` `C-h i` info manual

`M-x info-emacs-manual` `C-h r` info manual

#### Man

`M-x man` man of a unix command

`M-x woman`

### Point

### Opening Files

`M-x find-` TAB

`M-x find-file` TAB

`C-x C-f` runs `find-file`

Select a path `~/.spacemacs` > `M-x find-file-at-point` or `g f`. The file is opened.

### Ido mode

Interactively Do Things

`M-x ido-mode`

## Bookmark

`C-x r m` runs `bookmark-set`

`C-x r b` runs `bookmark-jump`

`C-x r l` runs `bookmark-bmenu-list`

Mnemonics:

`r` for Return to somewhere

`b` switch Buffer

## Motion 

## Navigating pairs

`C-m-f` runs `forward-sexp`

# Article: Smartparens Emacs and Pairs

https://ebzzry.io/en/emacs-pairs/


## Navigation

`C-M-a` runs `sp-beginning-of-sexp`

starts and ends

		sp-beginning-of-sexp
		sp-end-of-sexp

traversing lists

		sp-down-sexp
		sp-up-sexp
		sp-backward-down-sexp
		sp-backward-up-sexp

block movements

		sp-forward-sexp
		sp-backward-sexp

top-level traversal

		sp-next-sexp
		sp-previous-sexp

free-form movements

		sp-backward-symbol
		sp-forward-symbol

wrapping

		sp-wrap-round
		sp-wrap-curly
		sp-wrap-cancle
		sp-wrap-square
		sp-unwrap-sexp
		sp-backward-unwrap-sexp

slurp (expand) and barf (contract)

		sp-forward-slurp-sexp
		sp-forward-barf-sexp
		sp-backward-slurp-sexp
		sp-backward-barf-sexp
		
swapping

		sp-transpose-sexp

killing

		sp-kill-sexp
		sp-kill-hybrid-sexp
		sp-backward-kill-sexp

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

# Book: GNU Emacs Manual

### 23.2 Minor Modes

https://www.gnu.org/software/emacs/manual/html_node/emacs/Minor-Modes.html

Multiple minor modes can be active. 

Some are enabled in certain buffers. Some are global.

### 1.3 Mode Line

https://www.gnu.org/software/emacs/manual/html_node/emacs/Mode-Line.html#Mode-Line

### 14.6 View mode

https://www.gnu.org/software/emacs/manual/html_node/emacs/View-Mode.html

A minor mode. Scan buffer by sequential screenfuls.

`M-x view-buffer`

# Topic: smartparens

## Article: smartparens wiki

### Home · Fuco1/smartparens Wiki

https://github.com/Fuco1/smartparens/wiki

It is minor mode.

### Installation

``` bash
(smartparens-global-mode 1)
``` 

### Default configuration

`M-x customize-group`

### Quick tour · Fuco1/smartparens Wiki

https://github.com/Fuco1/smartparens/wiki/Quick-tour

## Article: smartparens manual

### Automatic escaping — Smartparens 1.10.1 documentation

https://smartparens.readthedocs.io/en/latest/automatic-escaping.html

### Pair management — Smartparens 1.10.1 documentation

To define a new pair

### Permissions — Smartparens 1.10.1 documentation

#### Pre and post action hooks

pre-handlers: before action

post-handler: after action

# Book: Practical Emacs Tutorial

http://ergoemacs.org/emacs/emacs.html

## Article: Emacs: Run Shell in Emacs

http://ergoemacs.org/emacs/emacs_unix.html

`M-x shell`

## Emacs Keys Basics

		C-x C-f						| find-file
		C-x C-s						| save-buffer
		C-x k  						| kill-buffer

Finding commands and shortcuts

		M-x describe-function
		M-x describe-key

## Emacs: How to Define Keys

Normal yolu:

``` bash
(global-set-key (kbd "M-a") 'backward-char)
``` 

Fakat her seferinde restart etmemek için deneme yapma yolu:

``` bash
M-x eval-last-sexp
``` 

Unset (remove) a keybinding

``` bash
(global-set-key (kbd "C-b") nil)
``` 

List current major mode's keys:

		M-x describe-mode

List all keybindings

		M-x describe-bindings

Keys to avoid

		C-?
		F1
		C-h
		ESC
		C-[
		C-S-letter
		C-m
		C-i

Good key choices

## A Curious Look at Emacs's One Thousand Keybindings

`C-h` help related

`C-x 8` non-ascii input

`C-x prefix` frequent global commands

`C-c prefix` frequent mode-specific commands

`view-mode` 

`C-M` to navigate/edit lisp code

## Emacs Advanced Tips

Search text

## Emacs Less-known Tips

list lines containing a string

		M-x list-matching-lines
		M-x delete-matching-lines

sort lines

		M-x sort-lines
		M-x reverse-region

multiple clipboards

no restarting emacs when add to init file

		.select code > M-x eval-region
		M-x eval-buffer
		M-x load-file

apps

		M-x calendar
		M-x calc
		M-x dired
		M-x list-colors-display
		M-x shell
		q

lisp inline

		.select line
		M-x eval-region

irc

		M-x irc

## Emacs: File Manager, dired

`M-x dired`

# Article: Lisp editing - WikEmacs

http://wikemacs.org/wiki/Lisp_editing#With_evil-mode

## With evil-mode

`=ip`				Indent an s-expression

evil-smartparens

`dd`		delete an sexp

evil-cleverparens

expand-region

`SPC v` or `er/expand-region`

