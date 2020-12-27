--- 
title: "Articles Spacemacs"
date: 2020-12-20T14:56:56+03:00
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

# Migrating from vim id=g_11831

[spacemacs/VIMUSERS.org at master · syl20bnr/spacemacs](https://github.com/syl20bnr/spacemacs/blob/master/doc/VIMUSERS.org)

## Modes vs. states

major-mode

minor-mode

clojure-mode

## Layers

## Transient-states

`SPC <group> .`

## Keybinding conventions

`which-key`

## Running commands

`SPC SPC` opens Helm buffer.

`:` ex commands

## Buffer and window management

### Buffers

`SPC b` prefix

  | SPC b .   | buffer trainsient-state |
  | SPC b d   | kill current buffer     |
  | SPC b b   | search buffers          |
	
Special buffers: `*Messages*`

### Windows

  | SPC w .   | window transient-state  |

### Files

  | SPC f s   | save                    |
  | SPC f f   | create new file         |

## The Help System

  | SPC h d   | prefix                  |
  | SPC h d f | function doc            |
  | SPC h d k | keybinding doc          |
  | SPC <f1>  | search doc              |

## Exploring

  | SPC h SPC | all layers              |
  | SPC ?     | all keybindings         |

## Customization

### .spacemacs file

4 top-level functions:

- `dotspacemacs/init`: runs first. use for settings.
- `dotspacemacs/layers`
- `dotspacemacs/user-init`: used most.
- `dotspacemacs/user-config`

### Emacs Lisp

Variables:

```lisp
(setq variable value)
(setq var1 t
			var2 nil
			var3 '("A" "B"))
```

Keybindings:

```lisp
(define-key evil-normal-state-map (kbd "H") 'previous-buffer) ; for normal mode
(spacemacs/set-leader-keys "bc" 'spacemacs/kill-this-buffer) ; for SPC prefix
```

Functions:

```lisp
(defun func-name (arg1 arg2)
  "docstring"
  ;; Body
  )

;; Calling a function
(func-name arg1 arg1)
```





