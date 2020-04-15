---
title: "Study IntelliJ IdeaVim"
date: 2020-04-15T10:44:55+03:00 
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

## IdeaVim settings: .ideavimrc

Check `https://github.com/danidiaz/miscellany/blob/master/windows/.ideavimrc`

Edit `~/.ideavimrc`

Action list: https://gist.github.com/zchee/9c78f91cc5ad771c1f5d

``` bash
:actionlist [pattern]
``` 

## Tool: Intellimacs

Spacemacs key bindings for IntelliJ

https://github.com/MarcoIeni/intellimacs

### Install

``` bash
git clone https://github.com/MarcoIeni/intellimacs ~/.intellimacs
``` 

Edit `~/.ideavimrc`

``` bash
" Selected Intellimacs modules
source ~/.intellimacs/spacemacs.vim
source ~/.intellimacs/extra.vim
source ~/.intellimacs/major.vim
source ~/.intellimacs/hybrid.vim

" My own vim commands
nnoremap Y y$

" Add/edit actions
nnoremap <leader>gl    :action Vcs.Show.Log<CR>
vnoremap <leader>gl    :action Vcs.Show.Log<CR>
``` 

Scroll menus: Keymap > Editor Actions > Down / Up > Set to: J / K

### Key Bindings

https://github.com/MarcoIeni/intellimacs/blob/master/docs/KEYBINDINGS.org

		SPC SPC		Find Actions
		SPC	Tab		Last buffer
		SPC	!			Terminal window
		SPC *			Find usages
		SPC ;;		Comment lines
		SPC ?			Show keybindings

SPC: prefix

		b b		Recent files
		b d		Buffer delete (close tab)
		b n		Next tab
		b p		Prev tab
		b x		Close window

		c p		Comment paragraph
		c l		Comment line

		e l		List errors
		e n		next error

		f e d		Settings
		f e R		reload ideavimrc
		f b			show bookmarks
		f F			Find files
		f r			Recent files
		f y y		Copy file path

		i s			insert snippet

		s e			rename symbol
		s h			highlight current word
		s P			find usages
		s p			search in project

		T t			toggle distraction free mode

		w v			split vertically
		w s			split horizontally
		w j			go to window down ...

		gd			go to declaration
		zm			collapse all folds
		M-x			execute an action (Find actions)

		m =			reformat code

		m g i		go to implementation
		m g g		go to declaration

		b 0			go to first tab
		b $			go to last tab
		b j			go to prev tab
		b k			go to next tab

		f N			create new element

		h a			show action list
		j s			jump to symbol
		j e			jump to element in current file

		R c			run class
		R k			kill application
		R s			seect configuration and run

		s E			search everywhere
		s f			search in current file
		s r			replace in current file

## tool: KJump motion plugin for IntelliJ IdeaVim

Check `~/.ideavimrc`

``` bash
let mapleader = " "
nmap <leader>kf :action KJumpAction<cr>
nmap <leader>kj :action KJumpAction.Word0<cr>
nmap <leader>kl :action KJumpAction.Line<cr>
``` 

