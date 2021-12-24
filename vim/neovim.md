---
title: "Study neovim"
date: 2021-05-22T14:02:10+03:00 
draft: false
description: ""
tags:
categories: bash, 
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com

---

# Article: A guide to using Lua in Neovim id=g12288

[nanotee/nvim-lua-guide: A guide to using Lua in Neovim](https://github.com/nanotee/nvim-lua-guide)

See: `:help config` or <vimhelp:config>

Check `~/.config/nvim/init.lua`

	neovim lua guide
		ref
			[nanotee/nvim-lua-guide: A guide to using Lua in Neovim](https://github.com/nanotee/nvim-lua-guide)
		meta-accessors
			vim.o: behaves like :set
			vim.go: behaves like :setglobal
			vim.bo: behaves like :setlocal for buffer-local options
			vim.wo: behaves like :setlocal for window-local options
