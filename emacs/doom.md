---
title: "Study Doom Emacs"
date: 2021-02-25T16:46:25+03:00 
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

# Index Doom

## Doom Refcard  id=g_12039

ref: `spacemacs doom emacs refcard <url:file:///~/projects/study/emacs/spacemacs.md#r=g_11015>`

# Issues

## Word wrapping

  | SPC t w | visual-line-mode |

## Relative line numbering

```el
(setq display-line-numbers-type 'relative)
```

  | SPC t l | relative line numbering | doom/toggle-line-numbers |

## Change keybindings doom

opt01: in a major-mode

```bash
(map! :map clojure-mode-map 
      :localleader 
      "ef" #'cider-eval-defun-at-point)
```

`SPC m e f` is bound to this function.

## Use comma for major mode

```clj
(setq doom-localleader-key ",")
```

## Osx Command Meta Key Bindings id=g_12032

Left option ise sembol değiştirici olarak algılanıyor. Right option, option olarak algılanıyor.

ref: `Osx Command Meta Key Bindings: <url:file:///~/projects/study/emacs/spacemacs.md#r=g_12031>`

opt01:

[macOS left option key not triggering meta · Issue #3952 · hlissner/doom-emacs](https://github.com/hlissner/doom-emacs/issues/3952)

Edit `~/.config/doom/config.el`

```clj
(setq mac-command-modifier      'super
      ns-command-modifier       'super
      mac-option-modifier       'none
      ns-option-modifier        'none
      mac-right-option-modifier 'meta
      ns-right-option-modifier  'meta
      mac-left-option-modifier 'none
      ns-left-option-modifier  'none)
```

# Doom Documentation id=g_12033

[doom-emacs/index.org at develop · hlissner/doom-emacs](https://github.com/hlissner/doom-emacs/blob/develop/docs/index.org)

Configuration framework. 

  | SPC h d h | Doom Documentation |
  | SPC h d s | Search Documentation |

## Install

Best: emacs-mac

```bash
brew tap railwaycat/emacsmacport
brew install emacs-mac --with-modules
ln -s /usr/local/opt/emacs-mac/Emacs.app /Applications/Emacs.app
```

Multiple emacs: `Multiple Emacs Setups/Installations in Osx <url:file:///~/projects/study/emacs/emacs.md#r=g_12029>`

Edit `~/.config/doom/init.el`

```clj
doom sync
```

```bash
doom doctor
```

bin/doom utility:

```bash
bin/doom help
doom sync: installs packages, deletes orphaned packages
doom upgrade
doom env: regenerates envvar file
doom doctor
doom purge
```

## Package Management

[doom-emacs/getting_started.org at develop · hlissner/doom-emacs](https://github.com/hlissner/doom-emacs/blob/develop/docs/getting_started.org#package-management)

Check `~/.config/doom/packages.el`

```clj
(package! flycheck-clj-kondo)
```

```bash
doom sync
```

# Video: Emacs Doom - Zaiste id=g_12040

## Buffers, Windows

[(245) Emacs Doom E04 - Buffers, Windows and Basic Navigation - YouTube](https://www.youtube.com/watch?v=AL1ypvJ5yAQ)

  | SPC b b   | switch buffer (workspace)      | +ivy/switch-workspace-buffer |
  | SPC b B   | switch buffer (all)            | ivy-switch-buffer            |
  | SPC <     | switch buffer (all)            | ivy-switch-buffer            |
  | SPC < SPC | show additional hidden buffers |                              |
  | SPC b k   | kill buffer                    | kill-current-buffer          |
  | SPC b N   | new buffer                     | evil-buffer-new              |
  | SPC f s   | save buffer                    | save-buffer                  |
  | ^w >      | larger window (width)          |                              |
  | ^w +      | larger window (height)         |                              |

## Projects

[(245) Emacs Doom E02 - Projects with Projectile, File Explorer with Treemacs & EShell - YouTube](https://www.youtube.com/watch?v=Rx3wRl5d-J0&list=PLhXZp00uXBk4np17N39WvB80zgxlZfVwj&index=2)

  |         | add new projects                | projectile-discover-projects-in-directory |
  | SPC p p | open project                    | counsel-projectile-switch-project         |
  | SPC SPC | open file in current project    | +ivy/projectile-find-file                 |
  | SPC f p | open doom config files          | doom/find-file-in-private-config          |
  | SPC f f | open file in current file's dir | counsel-find-file                         |
  | SPC .   | open file in current file's dir | counsel-find-file                         |
  | SPC o p | toggle sidebar file explorer    | +treemacs/toggle                          |
  | SPC o e | open shell terminal             | +eshell/toggle                            |
  | SPC o E | open shell terminal (full)      | +eshell/here                              |

## Dired  id=g_12041

[(245) Emacs Doom E03 - A short intro to Dired - YouTube](https://www.youtube.com/watch?v=oZSmlAAbmYs&list=PLhXZp00uXBk4np17N39WvB80zgxlZfVwj&index=3)

Open `dired` with `SPC .` and select a directory.

  | +     | create a directory    |
  | d     | mark to delete a file |
  | x     | execute (delete marked) |
  | U | unselect |
  | -     | upper dir             |
  | ENTER | inner dir             |
  | SPC . | create a file         |
  | o | order by name/date|
  | M | modify file modes | 
  | O | change owner |
  | `*/` | select all dirs |
  | t | toggle selection |
  | U | unselect all | 
  | m | mark a file |
  | ^W v | split vertical |
  | ^W w | switch windows |
  | C | copy file to other window | 
  | R | move file to other window |
  | i | insert mode |

## evil-snipe

  | f / t | one character match |
  | s | two character match |

## avy

[(245) Emacs Doom E07: Moving around the screen with Avy - YouTube](https://www.youtube.com/watch?v=zar4GsOBU0g&list=PLhXZp00uXBk4np17N39WvB80zgxlZfVwj&index=7)

  | g s SPC | match any chars | 

p01: Include all windows in search:

`SPC h v` `describe-variable` > `avy-all-windows` > Toggle

[Doom Emacs Zaiste Programming Tutorial](https://www.ianjones.us/zaiste-programming-doom-emacs-tutorial#org388daa2)

p02: Remove a word: 

`g s space` > select-one-letter > `x` > select-the-removal-spot

p03: ispell correct:

`g s space` > select-one-letter > `i` 

p04: yank a word:

`g s space` > select-one-letter > `y` > select-the-target-spot

## evil-multiedit

[(245) Emacs Doom E08: Multiple cursor in Emacs with evil-multiedit - YouTube](https://www.youtube.com/watch?v=zXdT5jY_ui0&list=PLhXZp00uXBk4np17N39WvB80zgxlZfVwj&index=8)

## org mode

[(245) Emacs Doom E09: Org Mode, Basic Outlines - YouTube](https://www.youtube.com/watch?v=CV2IFpSW2sE&list=PLhXZp00uXBk4np17N39WvB80zgxlZfVwj&index=9)

  | `*` SPC  | headline level 1                  |
  | `**` SPC | headline level 2                  |
  | TAB      | collapse/expand fold              |
  | +TAB     | cycle collapse/expand fold        |
  | ^RET     | put another headline              |
  | !up/dn   | move headlines up/dn (same level) |
  | !k/j   | move headlines up/dn (same level) |
  | !h !l | move up/down (raise/nest) level |

[(245) Emacs Doom E10: Org Mode - Links, Hyperlinks and more - YouTube](https://www.youtube.com/watch?v=BRqjaN4-gGQ&list=PLhXZp00uXBk4np17N39WvB80zgxlZfVwj&index=10)

  | [[Headline Title]] | link to headline title|
  | , l l | insert link |
  | file::10 | link to line 10 |
  | file::Title | link to title |
  | `[[elisp:(+ 2 2)][2 + 2]]` | elisp expression |

```clj
+ Link to file:[[file:e04.org][e04.org]]
+ Link to title in a file: [[file:e04.org::*About Org Mode][About Org Mode]]
+ Link to file (edit it with `, l l`): [[file:e04.org::Chapter 1][Chapter 1]]
+ Link to line in a file: [[file:e04.org::10][line 10]]

expression: [[elisp:(+ 2 2)][2 + 2]]

Show [[elisp:org-agenda][My Agenda]]

Shell command: [[shell:ls *.org][ls]]
```

## Magit

[(245) Emacs Magit - Getting Started - Emacs Doom 18 - YouTube](https://www.youtube.com/watch?v=7ywEgcbaiys&list=PLhXZp00uXBk4np17N39WvB80zgxlZfVwj&index=18)

  | SPC g g | status of repository  | magit-status         |
  | ?       | context help in magit |                      |
  | TAB     | git diff              | magit-section-toggle |
  | z a     | git diff              | magit-section-toggle |
  | s | stage changes | magit-stage |

 

