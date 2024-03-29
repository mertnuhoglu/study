---
title: "Study ranger"
date: 2020-03-05T14:31:32+03:00 
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

# Index id=g11809

		| ~/.config/ranger/rc.conf | configuration of keybindings |
		| ?k                       | help for keybindings         |
		| yp                       | yank path                    |
		| m<any>                   | mark bookmark                |
		| '<any>                   | goto bookmark                |
		| ^f                       | fzf search                   |
		| zp                       | toggle preview               |
		| yy/dd                    | put file into reg            |
		| pp                       | move/copy file to            |
		| space                    | select file                  |
		| o abc...                 | order/sort by basename,...   |

# Official User Guide · ranger/ranger Wiki

https://github.com/ranger/ranger/wiki/Official-user-guide

``` bash
ranger
``` 

Vim-like shortcuts

			hjkl		navigation
			yy			copy file
			pp			paste file
			dd			cut file
			:				command

mc

			Alt-number		go to tab n
			Tab						next tab

other shortcuts

			?k			keybindings
			^n			new tab

## Configuration Files

			~/.config/ranger/rc.conf
			~/.config/ranger/commands.py

# fzf integration

ref: `fzf integration <url:file:///~/projects/private_dotfiles/.config/ranger/rc.conf#r=g_11592>`


