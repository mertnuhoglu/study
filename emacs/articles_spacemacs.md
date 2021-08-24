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

# Migrating from vim id=g11831

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


# Article: Loading in Spacemacs id=g11863

[Loading in Spacemacs](https://gist.github.com/TheBB/367096660b203952c162)

## Files

When evaluated the functions, modes, macros in file becames available. Hence, `loading` a file.

Problem: 

- Loading correct files and in correct order
- Loading lazily

Loading a file: `load-file`

```clj
(load-file "~/elisp/foo.el")
```

## Features

Feature: Symbol that has the same name as the file.

Example: file: `my-feature.el`

```clj
(provide 'my-feature)
```

To load the file:

```clj
(require 'my-feature)
```

01. It checks if feature has already been loaded.
02. If not, it looks for file `my-feature.el`

Load path: Check by `SPC h d v` or `load-path`

Add new path:

```clj
(push "/some/poth/" load-path)
```

## Auto-loading

Auto-loading = lazy loading

## Packages and extensions

Package = extension = set of files (features)

Example: Loading all Helm features:

```clj
(require 'helm)
```

## Use-package

`(use-package helm)` = `(require 'helm)`

Lazy loading: 

```clj
(use-package helm
	:defer t)
```

## Layers

All packages are organized in layers. 

To load: two files needed in layer directory: 

- `packages.el`
- `extensions.el`

They define the following variables:

- `<layer>-pre-extensions`
a list of extensions to include before packages.
- `<layer>-packages`
a list of packages to include.
- `<layer>-post-extensions`
a list of extensions to include after packages.
- `<layer>-excluded-packages`
a list of packages to exclude, even if other layers include them.

include = not necessarily loaded


