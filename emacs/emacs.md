---
title: "Study Emacs"
date: 2020-01-17T12:40:22+03:00 
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

# Quickstart Emacs

		SPC b d						| kill-this-buffer

# Issues

## Outline

		which-key
			install
				M-x package-install which-key
			setup: enable
				M-x which-key-mode
		Install Emacs Package
			ref
				[Emacs: Install Package with ELPA/MELPA](http://ergoemacs.org/emacs/emacs_package_system.html)
			doc
				ELPA: package repository
				MELPA: ELPA compatible repository (larger)
				edit `~/.emacs.default/init.el`
				M-x list-packages
				M-x package-install
		major mode nasıl değiştirilir?
			M-x emacs-lisp-mode
		mode nasıl öğrenilir?
			describe-function kısayol doklarında yazar
		reload config
			[init file - How to reload .emacs.d/ configuration without restart - Emacs Stack Exchange](https://emacs.stackexchange.com/questions/41218/how-to-reload-emacs-d-configuration-without-restart)
			M-x eval-buffer
			, e b
		SPC SPC mapping to M-x
			[SPC SPC to M-X in Doom : emacs](https://www.reddit.com/r/emacs/comments/hpwlyq/spc_spc_to_mx_in_doom/)
			(map! :leader
						:desc "M-x" "SPC" #'counsel-M-x)
			You don't use the :prefix because you already used :leader If you are using evil
		menülere nasıl erişilir?
			[How do I get to the menu in Emacs in console mode? - Stack Overflow](https://stackoverflow.com/questions/191312/how-do-i-get-to-the-menu-in-emacs-in-console-mode)
			F10
			M-x menu-bar-open
			!+Enter
		doom module vs emacs package
			[doom-emacs/modules.org at develop · hlissner/doom-emacs](https://github.com/hlissner/doom-emacs/blob/develop/docs/modules.org)
				Doom module = Spacemacs layer
				module = bundle of packages, configuration, commands
					enabled by adding into `doom!` block in `init.el`
		doom grep nasıl yapılır?
			SPC s p

## Evaluate an elisp expression

[](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lisp-Interaction.html)

01: `M-x lisp-interaction-mode`

opt: `C-x b` select buffer `*scratch*`

[Re-open *scratch* buffer - Emacs Stack Exchange](https://emacs.stackexchange.com/questions/20/re-open-scratch-buffer)

02: Go to the sexp. `eval-print-last-sexp`

## Multiple Emacs Setups/Installations in Osx id=g12029

https://medium.com/dev-genius/how-to-set-up-multiple-custom-emacs-configurations-for-one-user-using-chemacs-102cdd617677

[plexus/chemacs2: Emacs version switcher, improved](https://github.com/plexus/chemacs2)

```bash
[ -f ~/.emacs ] && mv ~/.emacs ~/.emacs.bak
[ -d ~/.emacs.d ] && mv ~/.emacs.d ~/.emacs.default
git clone https://github.com/plexus/chemacs2.git ~/.emacs.d
```

Check `~/.config/chemacs/profiles.el`

### Spacemacs install

01: Sıfır bir spacemacs kurulumu yap bir klasöre:

```bash
git clone https://github.com/syl20bnr/spacemacs ~/spacemacs
git clone git@github.com:practicalli/spacemacs.d.git ~/.spacemacs.practicalli.d
```

02: Edit `~/.config/chemacs/profiles.el`

```clj
 ("default" . ((user-emacs-directory . "~/spacemacs")
               (env . (("SPACEMACSDIR" . "~/.spacemacs.practicalli.d")))))
```

Run:

```clj
emacs --with-profile default0
emacs --with-profile default
```

### Doom installation

01:

[Add support to doom · Issue #5 · plexus/chemacs](https://github.com/plexus/chemacs/issues/5)

```bash
git clone https://github.com/hlissner/doom-emacs ~/doom-emacs
mkdir ~/.config/doom
cp ~/doom-emacs/init.example.el ~/.config/doom/init.el
touch ~/.config/doom/config.el
~/doom-emacs/bin/doom install
```

02: Edit `~/.config/chemacs/profiles.el`

```clj
("doom" . ((user-emacs-directory . "~/doom-emacs")
           (env . (("DOOMDIR" . "~/.config/doom")))))
```

Run:

```clj
emacs --with-profile doom
emacs --with-profile default
```

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

## Custom keybindings for emacs

ref: `Binding keys - Spacemacs <url:/Users/mertnuhoglu/projects/study/emacs/spacemacs.md#tp=Binding keys - Spacemacs>`

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

# Article: How to Use Emacs, an Excellent Clojure Editor | Clojure for the Brave and True

https://www.braveclojure.com/basic-emacs/

``` bash
cd ~/codes/emacs
git clone https://github.com/flyingmachine/emacs-for-clojure/
``` 

## REPL

		M-x cider-jack-in

# Article: Mastering Eshell - Mastering Emacs

https://www.masteringemacs.org/article/complete-guide-mastering-eshell

## Overview

It doesn't support interactive programs. You need `ansi-term` for this.

It is not bash or zsh. 

## Commands

# Article: Emacs: M-x customize Tutorial

http://ergoemacs.org/emacs/emacs_custom_system.html

Textual GUI system for setting user preferences.

		M-x customize

Ref: `49.1 Easy Customization Interface <url:/Users/mertnuhoglu/projects/study/emacs/emacs.md#tn=49.1 Easy Customization Interface>`

# Article: Emacs Manual

## 49.1 Easy Customization Interface

Settings

Most `settings` are `customizable variables` also called `user options`

`M-x customize` opens `customization buffer` 

### 49.1.1 Customization Groups

Master group: `Emacs`

Hierarchical groups. 

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
		C-x 4 0						| kill-buffer-and-window
		SPC b d						| kill-this-buffer

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

# Article: The Emacs Guru Guide to Key Bindings – Wilfred Hughes id=g12045

[The Emacs Guru Guide to Key Bindings – Wilfred Hughes::Blog](http://www.wilfred.me.uk/blog/2018/01/06/the-emacs-guru-guide-to-key-bindings/)

Mnemonic Key Bindings: Remember name of function

  | eXecute-extended-command | M-x |
  | Next-line                | C-n |
  | Previous-line            | C-p |
  | Forward-char             | C-f |
  | Backward-char            | C-b |
  | iSearch-forward          | C-s |

Organised Key Bindings: consistent pattern

Movement by element:

  | forward-char | C-f   |
  | forward-word | M-f   |
  | forward-sexp | C-M-f |

Movement to the end:

	| move-end-of-line | C-e |
	| forward-sentence | M-e |
	| end-of-defun | C-M-e |

Transpose:

	| transpose-chars | C-t |
	| transpose-words | M-t |
	| transpose-sexps | C-M-t |

Killing text:

	| kill-line | C-k |
	| kill-sentence | M-k |
	| kill-sexp | C-M-k |

Discovering Key Bindings:

	| C-h k <?> | describe-keybinding |
	| C-h l | what command ran recently |

Article: keyboard shortcuts - Learning emacs - useful mnemonics? - Stack Overflow id=g12046

[keyboard shortcuts - Learning emacs - useful mnemonics? - Stack Overflow](https://stackoverflow.com/questions/2677300/learning-emacs-useful-mnemonics/38317797)

	| C-k | Kill a line |
	| C-y | Yank a line |
	| C-s | Search |
	| C-h | Help |
	| C-t | Transpose |
	| C-p | Previous line |
	| C-a | A of line |

[(1) Are there any mnemonics to remember Emacs key-bindings? : emacs](https://www.reddit.com/r/emacs/comments/ebg42i/are_there_any_mnemonics_to_remember_emacs/)

	| i | indent |
	| o | open | 
	| q | quote |
	| r | reverse search |
	| x | eXtended |

eXtended commands:

	| f | file |
	| d | directory |
	| b | buffer |
	| r | rectangle/register |

M is bigger than C (word > character). Note W ~ M, and C ~ Character. 

C-M is bigger than M. 

  | C-x | control frame                |
  | C-v | moves down = v pointing down |
  | M-v | moves up: M flips C          |

[Learning Emacs Key Bindings](https://yiufung.net/post/emacs-key-binding-conventions-and-why-you-should-try-it/)

  | C-x        | for essentials          |
  | C-c        | for user and major mode |
  | C-c char   | for user                |
  | C-c C-char | for major mode          |
  | C-g C-h    | reserved                |

