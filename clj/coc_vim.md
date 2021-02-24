---
title: "coc.vim"
date: 2021-01-29T15:06:50+03:00 
draft: false
description: ""
tags:
categories: vim
type: post
url:
author: "Mert Nuhoglu"
output:
  pdf_document:
    highlight: tango

---

# Issues coc.vim id=g_11955

  |:CocDisable| disable autocompletion| 

# Video: Neovim - Setting Up VSCode Intellisense with CoC [LSP] - YouTube

[Neovim - Setting Up VSCode Intellisense with CoC [LSP] - YouTube](https://www.youtube.com/watch?v=OXEVhnY621M)

```vim
CocInstall coc-json coc-snippets coc-vimlsp coc-python
CocInstall coc-spellchecker
CocInstall coc-translator
CocInstall coc-yank
CocInstall coc-markdownlint
```

```vim
CocList commands
CocList extensions
```

Edit settings: `~/projects/private_dotfiles/.config/nvim/coc-settings.json`

```bash
CocConfig
```

# coc-marketplace

[fannheyward/coc-marketplace: coc.nvim extensions marketplace](https://github.com/fannheyward/coc-marketplace)

Settings in: `~/projects/private_dotfiles/.config/nvim/coc-settings.json`

Example configuration in: `": coc.vim {{{ <url:file:///~/projects/vim_repos/my-vim-custom/plugin/my-vim-custom.vim#r=g_11954>`

    
