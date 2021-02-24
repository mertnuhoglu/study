---
title: "Study kitty"
date: 2020-03-05T14:31:58+03:00 
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

## kitty themes

```bash
cd /Users/mertnuhoglu/.config/kitty/kitty-themes/themes
kitty @ set-colors -a /Users/mertnuhoglu/.config/kitty/kitty-themes/themes/Monokai_Pro.conf
kitty @ set-colors -a Solarized_Dark_Higher_Contrast.conf
```

## kitty refcard id=g_11648

		https://sw.kovidgoyal.net/kitty/#other-keyboard-shortcuts
		/Users/mertnuhoglu/projects/private_dotfiles/.config/kitty/kitty.conf
		Layout management <url:file:///~/projects/private_dotfiles/.config/kitty/kitty.conf#r=g_11649>

		^+u	unicode

## pencere boyutlarını ayarla: sol kolon geniş, sağ kolon dar

https://sw.kovidgoyal.net/kitty/#layouts

Edit `~/projects/private_dotfiles/.config/kitty/kitty_session.conf`

``` bash
enabled_layouts tall:bias=70;full_size=2
``` 

> This will have 2 instead of a single tall window, that occupy 70% instead of 50% of available width. bias can be any number between 10 and 90.

## using option key in kitty

https://stackoverflow.com/questions/11876485/how-to-disable-typing-special-characters-when-pressing-option-key-in-mac-os-x

In Terminal.app, with "Use Option as meta key" enabled, alt+o sends ESC + o.

https://github.com/kovidgoyal/kitty/issues/123

Alt most definitely does send escape. Try running

		cat

in the terminal and typing

		alt+s

You get

		^[s

Or if you are on OS X, IIRC the OS converts Alt + key into unicode characters. A bit of googling will show you how to get OS X to stop doing that

Fix:

``` bash
macos_option_as_alt right
``` 

Şimdi bu bindkey çalışıyor:

``` bash
bindkey '^[ ' autosuggest-accept
``` 

Note: right option key is used above.

### window tiling: horizontal/vertical split

Ref: `#: Window management {{{ <url:/Users/mertnuhoglu/projects/private_dotfiles/.config/kitty/kitty.conf#tn=#: Window management {{{>`

split komutu yok. Onun yerine layout ile çalışmak gerekiyor.

Window oluşturma: `#enter`

		# enter
		^+enter

Layouts:

		Fat
		Grid
		Horizontal
		Tall: benim hep kullandığım stil
		Stack: only one window

		^+l		switch layout

## image viewer: icat

``` bash
kitty +kitten icat image.jpeg
alias icat="kitty +kitten icat"
``` 

## command mode line

tmux'taki gibi command mode yok. Ancak command line interface var. Böylece programlanabilir.

https://sw.kovidgoyal.net/kitty/remote-control.html

Bu komutla kitty shell açılır. Bunun içinden kitty'yi yönetebilirsin.

``` bash
kitty @ 
``` 

### başka bir kitty terminaline komut göndermek

Bunun için kitty'yi `listen-on` ile başlatmalısın.

``` bash
kitty --listen-on xxx
``` 

Daha sonra shelle bağlanmak için:

``` bash
kitty @ --to xxx
``` 

### error: cmd+m hide ediyor (minimize)

https://superuser.com/questions/1043596/mac-osx-remove-hide-window-keyboard-shortcut

Ancak kitty Application olarak görünmüyor listede. Neden?

Yeri burası: `/Users/mertnuhoglu/Applications/kitty.app/`

## Setup kitty for ranger

https://github.com/ranger/ranger/pull/1077

``` bash
man ranger
``` 

Edit `~/.config/ranger/rc.conf`

``` bash
set preview_images_method kitty
``` 

## kitty config ile başlatma

Edit `~/.config/kitty/kitty.conf`

``` bash
kitty --config ~/.config/kitty/kitty.conf
``` 

https://sw.kovidgoyal.net/kitty/#configuring-kitty

``` bash
layout tall
cd ~
title Testing
launch zsh
title Testing 2
map f2 pipe @ansi overlay less +G -R
``` 

## Debug keyboard shortcuts

``` bash
kitty --debug-keyboard
``` 

## Text selection with keyboard

``` bash
map cmd+x launch --stdin-source=@screen_scrollback --type=overlay /usr/local/bin/nvim -Rmn -
``` 

Note: Kendi ayarlarımda `#!+ı` kullanıyorum.

# Articles

## Kitty – a fast, featureful, GPU based terminal emulator | Hacker News

https://news.ycombinator.com/item?id=17915829

> 2 important features it had over Alacritty: proper underline rendering (Alacritty just draws underscores) and text selection with Shift+Mouse


## Documentation: kitty - the fast, featureful, GPU based terminal emulator — kitty 0.16.0 documentation

https://sw.kovidgoyal.net/kitty/#quickstart

### Article: Launching programs in new windows/tabs — kitty 0.16.0 documentation

https://sw.kovidgoyal.net/kitty/launch.html

kitty shell içinde:

``` bash
launch /usr/local/bin/nvim
``` 

#### Article: Controlling kitty from scripts or the shell — kitty 0.16.0 documentation

https://sw.kovidgoyal.net/kitty/remote-control.html

opt01: only works in the same kitty terminal

``` bash
kitty -o allow_remote_control=yes -o enabled_layouts=tall
``` 

Add to `kitty.conf`

``` bash
allow_remote_control yes
``` 

Now, in the new kitty window, enter the command:

``` bash
kitty @ new-window --title Output --keep-focus cat
``` 

> More usefully, you can pipe the output of a command running in one window to another window, for example:

``` bash
ls | kitty @ send-text --match title:Output --stdin
``` 

> This will show the output of ls in the output window instead of the current window. You can use this technique to, for example, show the output of running make in your editor in a different window

opt02: works from anywhere:

``` bash
kitty -o allow_remote_control=yes --listen-on unix:/tmp/mykitty
``` 

``` bash
kitty @ --to unix:/tmp/mykitty ls
``` 

### startup sessions

https://sw.kovidgoyal.net/kitty/#startup-sessions

opt01: başlangıçta parametre ver

``` bash
kitty --session <SESSION_FILE>
``` 

``` bash
kitty --session ~/projects/private_dotfiles/.config/kitty/kitty_session.conf
``` 

opt02: conf dosyasında belirt session dosyasını

``` bash
startup_session ~/projects/private_dotfiles/.config/kitty/kitty_session.conf
``` 

### Hints

			^+e				choose any urls to open 
			^+p f			choose any file path

### Tabs and Windows

https://sw.kovidgoyal.net/kitty/#windows

		[Tab] 1-n [Window]

shortcuts

		^+ up/dn			scroll up/dn
		# up/dn				scroll up/dn
		^+h						scroll pager
		#t						new tab
		#w						close tab
		^+ rg/lf			next/prev tab

### Multiple copy/paste buffers

https://sw.kovidgoyal.net/kitty/#multiple-copy-paste-buffers

``` bash
map f1 copy_to_buffer a
map f2 paste_from_buffer a
``` 

### Marks

https://sw.kovidgoyal.net/kitty/marks.html

Mark text based on regex.

Ex: highlight the word ERROR

``` bash
map f3 toggle_marker text 1 ERROR
``` 

Dynamically create markers

``` bash
map f4 create_marker
map f5 remove_marker
``` 

### Fonts

https://sw.kovidgoyal.net/kitty/conf.html#conf-kitty-fonts


		
