---
title: "Book: Clojure Development with Spacemacs & Cider - Practicalli"
date: 2020-12-04T11:46:01+03:00 
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


# Book: Introduction · Clojure development with Spacemacs & Cider - practicalli

https://practicalli.github.io/spacemacs/

## Install

### Configuration File

```clj
git clone git@github.com:practicalli/spacemacs.d.git .spacemacs.d
ln -s /Users/mertnuhoglu/codes/clj/.spacemacs.d /Users/mertnuhoglu/.spacemacs.d
```

### Zooming

[Font size and Zooming · Clojure development with Spacemacs & Cider](https://practicalli.github.io/spacemacs/install-spacemacs/change-font.html)

	| SPC z f | zoom all buffers         |
	| SPC z m | zoom only current buffer |

### Themes

  | SPC T n/p | next theme   |
  | SPC T s   | select theme |

### Enhance Clojure experience · Clojure development with Spacemacs & Cider

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

	| SPC t n | toggle line numbers     |
	| SPC t r | relative line numbering |
	| SPC j l | jump to line (avy)      |

``` bash
dotspacemacs-line-numbers 'relative
``` 

## Spacemacs Basics 

https://practicalli.github.io/spacemacs/spacemacs-basics/

Space bar - main menu: `SPC SPC`

  | M-m       | SPC             |
  | M-RET / , | major mode menu |

Spacemacs Help system

		| Prefix: SPC |                    |                   |
		| h d f       | help on function   | describe-function |
		| h SPC       | help documents     |                   |
		| h d k       | help on keybinding |                   |

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

		| SPC f   | file menu          |
		| SPC f	f | open file anywhere |
		| TAB     | complete filename  |
		| ^h      | parent dir         |
		| ^y      | paste kill ring    |
		| ^j ^k   | down/up            |
		| SPC f	f | create file        |

### Buffer management

		| SPC b	b | list open buffers      |
		| SPC b	. | buffer transient state |
		| n       | cycle next             |
		| N / p   | cycle prev             |
		| k       | kill current buffer    |

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

		| SPC a	r | open ranger  |
		| j/k     |              |
		| h/l     | parent/child |
		| q       | quit         |

## Create a Clojure Project

https://practicalli.github.io/spacemacs/create-a-project/

``` bash
cd /Users/mertnuhoglu/projects/study/emacs/ex/study_emacs
lein new ex01
``` 

### Using emacs eshell

    | SPC SPC eshell | run a terminal                 |
    | , '            | start new repl (cider-jack-in) |
    | , s s          | switch repl/editor             |
    | , s q          | quit repl                      |
    | , e e          | eval prev expression           |
    | , e f          | show expression result         |
    | SPC m s c      | connect to an existing repl    |
    | , s n          | eval namespace of current file |

states:

		i		vim insert state
		^z	emacs state
			,		repl commands

## Clojure Development

### Clojure Projects

https://practicalli.github.io/spacemacs/clojure-projects/

### The REPL id=g_10828

`SPC m` = `,`

Prefix: `,` or `SPC m`

``` 
'				start REPL	cider-jack-in
e f			eval expression	cider-eval-defun-at-point
``` 

#### Start a REPL

``` bash
Prefix: `,` or `SPC m`
'				start REPL	cider-jack-in
s				start REPL menu, selecting type
s I			cljs REPL	cider-jack-in-cljs
``` 

	| , m s | cider-jack-in|

#### Restart REPL

	| , m q r | sesman-restart |

### Evaluating Clojure

``` bash
SPC m
e				eval menu
e e			eval expression before cursor	cider-eval-last-sexp
e w			eval and replace its result	cider-eval-last-sexp-and-replace
e m			expand macro	cider-macroexpand-1
``` 

Inspecting:

``` bash
SPC m or ,
d v			cider-inspector
``` 

REPL Buffer:

``` bash
s s			switch between repl buffer and source code buffer
``` 

	| , d v   | cider-inspector     |
	| , e p f | pretty print result |
	| , e e   | eval prev expr      |

#### Evaluating individual Expressions

  | , e f   | eval top level expr                     |
  | , e e   | eval prev expr                          |
  | SPC v v | select nested expr                      |
  | , e ;   | eval expr and print as comment          |
  | , e w   | replace prev expr with its return value |

#### Macro-expand

	| , e m | cider-macroexpand-1 |
	| , e M | cider-macroexpand-all |

#### Showing Intermediary values - Enlighten mode id=g_11847

`enlighten` minor mode: shows intermediary values during evaluation

	| , T e | cider-enlighten-mode |

Önce `, T e` ile modu aktifleştir. Sonra `, e f` ile eval et.

![enlighten-mode inspection](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201220_163018.jpg)

#### Inspect Clojure values

	| TAB S-TAB | navigate forward/backward               |
	| TAB S-TAB | cider-inspector-next-inspectable-object |
	| RET       | inspect current value                   |
	| RET       | cider-inspector-operate-on-point        |
	| L         | back to parent of nested element        |
	| L         | cider-inspector-pop                     |

