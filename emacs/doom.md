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

- ## Doom Refcard  id=g12039
  id:: 5c5038b5-b954-4bd3-83f0-7abeb5fe3b73

	| SPC h t | load-theme |

ref: `spacemacs doom emacs refcard <url:file:///~/projects/study/emacs/spacemacs.md#r=g11015>`

    | spacemacs | description                  | command                     | doom  |
    | SPC SPC   | list and search all commands | M-x                         | SPC : |
    | SPC ?     | search for key bindings      | show keybindings            |
    | C-h k     | search for key binding       | describe-key                | h k   |
    | SPC h d f | help on function             | describe-function           | h f   |

  | SPC h r r | reload config                   | doom/reload                       |
  | SPC p p   | open project                    | counsel-projectile-switch-project |
  | SPC f f   | open file in current file's dir | counsel-find-file                 |
  | SPC f p   | open doom config files          | doom/find-file-in-private-config  |
  | SPC o p   | toggle sidebar file explorer    | +treemacs/toggle                  |
  | g s SPC   | match any chars (sneak gibi)    |                                   |

cider - clojure

  | SPC p p | open project                           | counsel-projectile-switch-project                  |
  | ,'      | start REPL                             | cider-jack-in                                      |
  | ,rB     | switch ns                              | +clojure/cider-switch-to-repl-buffer-and-switch-ns |
  | ,h      | help menu                              |
  | ,hc     | cider-clojuredocs                      |
  | ,pP     | cider-pprint-eval-last-sexp-to-comment |

ref: `Dired  <url:file:///~/projects/study/emacs/doom.md#r=g12041>`

  | +     | create a directory        |
  | d     | mark to delete a file     |
  | m     | mark a file               |
  | U     | unselect                  |

ref: `org mode <url:file:///~/projects/study/emacs/doom.md#r=g12042>`

  | TAB                | collapse/expand fold              |
  | !k/j               | move headlines up/dn (same level) |
  | !h !l              | move up/down (raise/nest) level   |
  | [[Headline Title]] | link to headline title            |
  | file::Title        | link to title                     |

ref: `Magit <url:file:///~/projects/study/emacs/doom.md#r=g12043>`

  | SPC g g | status of repository    | magit-status         |
  | ?       | context help in magit   |                      |
  | z a     | git diff                | magit-section-toggle |
  | s       | stage changes           | magit-stage          |
  | c       | commit                  | magit-commit         |

# Issues

## smartparens config

ref: smartparens custom mappings <url:file:///~/projects/private_dotfiles/.config/doom/config.el#r=g12112>

- ## Bir projeyi REPL ile incelemek id=g12073
  id:: a129884a-1328-44fa-a33f-edaf1eaeae20

01: open project: `SPC p p`

  | SPC p p   | open project                    | counsel-projectile-switch-project |

02: start repl: `,'`

    | '         | start REPL                   | cider-jack-in               |

03: switch to ns: `,rB`

		| rB | switch ns | +clojure/cider-switch-to-repl-buffer-and-switch-ns |

## cider help fonksiyonlarını kullanmak

01: Önce mevcut dosyanın ns'sini eval et

02: Sonra `cider-clojuredocs` veya `cider-doc` fonksiyonları bulunduğun sembol üzerinde çalışır hale gelir.

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

- ## Osx Command Meta Key Bindings id=g12032
  id:: 60779afa-a029-4f1e-8a0a-fbae06cfe1a1

Left option ise sembol değiştirici olarak algılanıyor. Right option, option olarak algılanıyor.

ref: `Osx Command Meta Key Bindings: <url:file:///~/projects/study/emacs/spacemacs.md#r=g12031>`

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

- # Doom Documentation id=g12033
  id:: 64c135f6-d301-4379-ba6a-506df932250c

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

Multiple emacs: `Multiple Emacs Setups/Installations in Osx <url:file:///~/projects/study/emacs/emacs.md#r=g12029>`

Edit `~/.config/doom/init.el`

```clj
doom sync
```

Then on doom: `SPC h r r` = `doom/reload`

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

- # Video: Emacs Doom - Zaiste id=g12040
  id:: fb169687-91a4-4ae3-9717-252901fae1d7

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

- ## Dired  id=g12041
  id:: 1a6737b4-df88-40a2-8af9-2916f6dd00f5

[(245) Emacs Doom E03 - A short intro to Dired - YouTube](https://www.youtube.com/watch?v=oZSmlAAbmYs&list=PLhXZp00uXBk4np17N39WvB80zgxlZfVwj&index=3)

Open `dired` with `SPC .` and select a directory.

  | +     | create a directory        |
  | d     | mark to delete a file     |
  | x     | execute (delete marked)   |
  | U     | unselect                  |
  | -     | upper dir                 |
  | ENTER | inner dir                 |
  | SPC . | create a file             |
  | o     | order by name/date        |
  | M     | modify file modes         |
  | O     | change owner              |
  | `*/`  | select all dirs           |
  | t     | toggle selection          |
  | U     | unselect all              |
  | m     | mark a file               |
  | ^W v  | split vertical            |
  | ^W w  | switch windows            |
  | C     | copy file to other window |
  | R     | move file to other window |
  | i     | insert mode               |

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

- ## org mode id=g12042
  id:: 384ab2d2-817b-4e70-9b42-cd542182f93d

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

- ## Magit id=g12043
  id:: 8f3ca3b3-1cef-44dd-979e-2bdd735aedf5

[(245) Emacs Magit - Getting Started - Emacs Doom 18 - YouTube](https://www.youtube.com/watch?v=7ywEgcbaiys&list=PLhXZp00uXBk4np17N39WvB80zgxlZfVwj&index=18)

  | SPC g g | status of repository    | magit-status         |
  | ?       | context help in magit   |                      |
  | TAB     | git diff                | magit-section-toggle |
  | z a     | git diff                | magit-section-toggle |
  | s       | stage changes           | magit-stage          |
  | c       | commit                  | magit-commit         |
  | C-c C-c | save and commit changes | with-editor-finish   |

## Deft mode
 
- # Doom Cider Refcard id=g12074
  id:: 1461ca3b-9c93-4ddd-bd21-48ac1a53230e

  | ,dd | cider-debug-defun-at-point                        |
  | ,e  | eval menu                                         |
  | ,eb | cider-eval-buffer                                 |
  | ,ed | cider-eval-defun-at-point                         |
  | ,ee | cider-eval-last-sexp                              |
  | ,ef | cider-eval-defun-at-point                         |
  | ,c  | cider-connect-clj                                 |
  | ,g  | goto menu
  | ,gb | cider-pop-back                                    | return to your pre-jump location |
  | ,gg | cider-find-var                                    | go to symbol definition          |
  | ,gn | cider-find-ns                                     | go to namespace                  |
  | ,h  | help menu                                         |
  | ,ha | cider-apropos                                     |
  | ,hc | cider-clojuredocs                                 |
  | ,hd | cider-doc                                         |
  | ,hn | cider-find-ns                                     |
  | ,i  | inspect menu                                      |
  | ,ie | cider-enlighten-mode                              | display values of locals         |
  | ,ii | cider-inspect                                     |
  | ,p  | print menu                                        |
  | ,pP | cider-pprint-eval-last-sexp-to-comment            |
  | ,r  | repl menu                                         |
  | ,rb | cider-swith-to-repl-buffer                        |
  | ,rB | clojure/cider-switch-to-repl-buffer-and-switch-ns |
  | ,rl | cider-load-buffer                                 |
  | ,rn | cider-repl-set-ns                                 |

Tüm cider-mode komutlarını görmek için `C-c` kullan. Ayrıca menülerden `CIDER Interactions` ile de dolaşabilirsin.

  | C-c       | cider menu         |
  | C-c C-v n | cider-eval-ns-form | mevcut nsyi eval et                                    |
  | C-c C-? r | cider-xref-fn-refs | bu sembole verilen refleri bul                         |
  | C-c M-s   | cider-selector     | en son cider dosyasına / ilgili bufferlara gitmek için |
  |           | cider-classpath    | classpath içindeki varlıkları dolaş                    |
