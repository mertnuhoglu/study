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

Check `~/gdrive/mynotes/content/fork/intellij_actionlist.txt`

```vim
source ~/.ideavimrc
```


## Tool: Intellimacs id=g_10992

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

		b b		Recent files	Recent files	#e
		b d		Buffer delete (close tab)	Close	#w
		b n		Next tab
		b p		Prev tab
		b x		Close window	Close Active Tab	^+f4

		c p		Comment paragraph	comment with block comment
		c l		Comment line	Comment with Line Comment	#/	cc

		e l		List errors
		e n		next error	Next Highlighted Error	F2
		e p		prev error	Previous Highlighted Error	+F2

		f e d		Settings
		f e R		reload ideavimrc
		f b			show bookmarks	Show Bookmarks	#F3
		f F			Find files	Go to File	#+o
		f r			Recent files	Recent files	#e
		f y y		Copy file path	Copy Paths	#+c

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
		R s			select configuration and run	Run...	^!r

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

