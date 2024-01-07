---
title: "Study IntelliJ"
date: 2020-04-13T12:46:52+03:00 
draft: false
description: ""
tags:
categories: tools, intellij
type: post
url:
author: "Mert Nuhoglu"
output: html_document
blog: mertnuhoglu.com

---

# Issues

## quickstart intellij

	compatible keybindings: my vim and intellij <url:/Users/mertnuhoglu/.ideavimrc#tn=compatible keybindings: my vim and intellij >
	compatible keybindings: spacemacs and intellij <url:file:///~/.ideavimrc#r=g10991>
	compatible keybindings: custom intellij  {{{ <url:file:///~/.ideavimrc#r=g11485>

- ## intellij refcard id=g11014
  id:: 9249847c-a95b-4efb-9d36-b0e842608a04

    | #e / SPC SPC | find action        |
    | ++           | search everywhere  |
    | !u SPC       | quicklist: popups  |
    | !u p         | quicklist: popups  |
    | !u j         | quicklist: clojure |
    | !u k         | quicklist: general |
    | ! SPC        | Quick definition   |
    | f1           | quickdocs          |
    | ^^           | Run anything       |
    | #! SPC, ...  | menus              |

		my customizations <url:file:///~/projects/study/code/study_intellij.md#r=g10996>
		my quick lists <url:file:///~/projects/study/code/study_intellij.md#r=g10995>
		quicklist: select popups <url:file:///~/projects/study/code/study_intellij.md#r=g11320>

## Most important commands:

01. Search Everywhere: `++` `Shift+Shift`

02. Find Action: `#e`

It is like Osx Spotlight.

03. Navigating Tool Windows

`#a `> Tool Windows

Add shortcut for REPL: Preferences > Keymap > Tool Windows > REPL > .`#0`

04. Extend Selection `!up`

Shring Selection `!down`

05. Define a shortcut for a command inside `Find Actions` dialog by `!enter`

https://www.jetbrains.com/help/idea/mastering-keyboard-shortcuts.html

		++		Search Everywhere
		!Enter		intention actions
		#+enter		complete current statement
		!Up/dn		extend/shrink selection

## Get used to these commands:

Switcher `#s`

		| ^+ up/dn | next/prev method |

- ## quicklist: select popups id=g11320
  id:: 88cda924-4c4d-4e72-b43f-398f5464d863

`!+SPC`

		| tool windows               |
		| switcher                   |
		| editor color scheme        |
		| recent files               |
		| recent locations           |
		| quick switch scheme        |
		| menus: file edit window... |
		| quick definition           |
		| file structure             |
		| show context menu          |
		| show context actions       |
		| insert live template       |
		| refactor this              |
		| navigate to                |
		| generate                   |
		| vcs operations             |
		| inspect this               |
		| action list                |

## custom popup menus:

Quick List > .add frequent commands

# my keybindings and customizations

- ## my quick lists id=g10995
  id:: 6a93ca2c-2dc1-48ec-800d-5fced4066339

		| !+space | popups  |
		| !u p    | popups  |
		| !u c    | clojure |

- ## my customizations id=g10996
  id:: 65cd6fcb-c9a9-48dc-be2b-479c768e85c8

ref: `": compatible keybindings: custom intellij  {{{ <url:file:///~/.ideavimrc#r=g11485>`

		| !m w | window menu popup  |
		| !m e | edit menu popup    |
		| !m o | context menu popup |

		| !g r | activate next window |
		| !g e | activate prev window |

		| !f f | go to file   |
		| !f s | go to symbol |

## my REPL commands

`Edit REPL Commands`

		| !s r | restart user ns |

# Tool: Intellimacs: spacemacs like keybindings

Ref: `~/projects/study/code/study_intellij_ideavim.md`

		| ^w o | restore default layout |

- # doc: Intellij Kısayolları 20210408 id=g12289
  id:: 415c0808-4be4-4e5e-90e7-db205837628e

ref: intelij_kisayollari_20210408.m4a

    | gd      | quick definition             | !k    |
    | !+k     | quick documentation          | !h d  |
    | !zc     | collapse                     | zc    |
    | !zm     | collapse recursively         | zm    |
    | !zo     | expand                       | zo    |
    | !zO     | expand recursively           | zO    |
    | ^!e     | next method                  | C-M-e | end-of-defun            | ^+dn |
    | ^!a     | prev method                  | C-M-a | beginning-of-defun      | ^+up |
    | +up/dn  | move forward/backw into sexp |
    | #!up/dn | next/prev occurrence (usage) |
    | !c s    | highlight current scope      | +C s  |
    | !rq     | stop repl                    | #f2   | cider-quit              | ,rq  |
    | !rs     | run repl                     | +f10  | cider-jack-in           | ,'   |
    | !pf     | select in project view       | #f1   | projectile-find-file    |
    | gD      | find usages                  | gd    |
    | gd      | goto declaration/show usages |
    | SPC ıo  | hide all windows/toggle      |
    | gcc     | CommentByLineComment         | !cl   |
    | !tn     | run tests in current ns      | ,tn   | cider-test-run-ns-tests |
    | !mm     | macro expand                 | ,m    | cider-macroexpand-1     |
    | SPC pp  |
		| !bn			| close notification window		|

