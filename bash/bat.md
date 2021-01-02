--- 
title: "Study: bat"
date: 2020-12-29T17:43:31+03:00 
draft: false
description: ""
tags:
categories: bash
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

# Video: Bat: Syntax Highlighting For All Your Needs - YouTube

[Bat: Syntax Highlighting For All Your Needs - YouTube](https://www.youtube.com/watch?v=cCfgNg77gjw&list=PLRjzjpJ02WDPwtQ9gebL3KzEAUVWzC-ib)

`--list-themes`

```bash
--theme="TwoDark"
```

`--generate-config-file`

```bash
bat --generate-config-file
```

Edit `~/projects/private_dotfiles/.config/bat/config`

`--list-languages`, `-L`

```bash
bat --list-languages
```

Add new file extensions:

```bash
--map-syntax "*.Rmd:Markdown"
```

Default style:

```bash
--style=header
--style=numbers
--style=plain
--style="changes,header"

```

# bat README

[sharkdp/bat: A cat(1) clone with wings.](https://github.com/sharkdp/bat#man)

`man` coloring: 

```bash
export MANPAGER="sh -c 'col -bx | bat -l man -p'"
```

## batgrep

wrapper for `ripgrep`

```bash
batgrep ...
```

## batdiff

diff and git wrapper

```bash
batdiff ...
```

## prettybat

`prettier` wrapper for source codes

```bash
prettybat ...
```





