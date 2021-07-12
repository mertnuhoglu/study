---
title: "Study lf"
date: 2020-12-10T11:41:52+03:00 
draft: true
description: ""
tags:
categories: bash, 
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css

---

# Issues

## toggle preview kısayolu oluştur id=g_11823

```bash
map zp set preview!
```

## dosya silme delete file 

```bash
:delete
y
```

# My Customizations

Check: `zshrc: lf <url:file:///~/.zshrc#r=g_11820>`

# Official Tutorial

[Tutorial · gokcehan/lf Wiki](https://github.com/gokcehan/lf/wiki/Tutorial)

  | j/k       | down/up                           |
  | ^u/^d     | half-up/half-down                 |
  | ^b/^f     | page-up/page-down                 |
  | h/l       | updir/open                        |
  | gg/G      | top/bottom                        |
  | space/v/u | toggle/invert/unmark selection    |
  | y/d/p/c   | copy/cut/paste/clear              |
  | :         | read builtin command              |
  | $         | shell                             |
  | %         | shell-pipe run cmd and pipe input |
  | !         | shell-wait wait before returning  |
  | &         | shell-async run in background     |
  | / ?       | search/search-back                |
  | n/N       | search-next/search-prev           |
  | z         | toggle options                    |
  | s         | change sortby/info options        |
  | e         | launch editor                     |
  | i         | launch pager                      |
  | w         | launch shell                      |

## Custom Configuration

Check `~/.config/lf/lfrc`

  | af | fzf                    |
  | az | fasd                   |
  | Y  | copy path: $f > pbcopy |


# Official Documentation

[lf - GoDoc](https://godoc.org/github.com/gokcehan/lf)

  | r     | rename                                    |
  | f     | find (with starting letter)               |
  | F/;/, | find-back/find-next/find-prev             |
  | m/'/" | mark-save/mark-load/mark-remove bookmarks |

## Previewer

```bash
set previewer ~/go/bin/pistol
```

## Icons

Install [Nerd Fonts](https://www.nerdfonts.com/font-downloads)

```bash
brew tap homebrew/cask-fonts &&
brew cask install font-<FONT NAME>-nerd-font

```

```bash
brew install --cask font-fira-code-nerd-font
brew install --cask font-fira-mono-nerd-font
brew install --cask font-jetbrains-mono-nerd-nerd-font
brew install --cask font-inconsolata-nerd-font
```

## Bookmarks

[Bookmarks for directories · Issue #76 · gokcehan/lf](https://github.com/gokcehan/lf/issues/76)



