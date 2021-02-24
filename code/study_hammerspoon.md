---
title: "Study hammerspoon"
date: 2020-03-11T18:54:32+03:00
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

## hammerspoon: find name of application id=g_11480

opt01:

use bundle id:

For example:

Take zoom.

Find name in `Applications` directory: `zoom.us.app`

Then use: `zoom.us`

```bash
osascript -e 'id of app "zoom.us"'
  ##> us.zoom.xos
osascript -e 'id of app "Microsoft Excel"'
```

in hammerspoon:

```lua
hs.application.launchOrFocus("zoom.us")
```

opt02: Print names of all open apps

[Hammerspoon FAQ](https://www.hammerspoon.org/faq/)

```lua
hs.fnutils.each(hs.application.runningApplications(), function(app) print(app:title()) end)
hs.fnutils.each(hs.application.runningApplications(), function(app) print(app:name()) end)
hs.fnutils.each(hs.application.runningApplications(), function(app) print(app:pid()) end)
```

opt03: Print names of all open windows

```lua
hs.fnutils.each(hs.window.allWindows(), function(win) print(win:title()) end)
hs.window.find('Reveal')
hs.window.find('Reveal'):focus()
```

## Install Spoons

Ref: `Article: dot-hammerspoon/init.org at master · zzamboni/dot-hammerspoon <url:/Users/mertnuhoglu/projects/study/code/study_hammerspoon.md#tn=Article: dot-hammerspoon/init.org at master · zzamboni/dot-hammerspoon>`

Edit `~/projects/private_dotfiles/.hammerspoon/init.lua`

Define once:

``` bash
hs.loadSpoon("SpoonInstall")
Install=spoon.SpoonInstall
```

Install any Spoons declaratively:

``` bash
Install:andUse("MouseCircle",
               {
                 config = {
                   color = hs.drawing.color.x11.rebeccapurple
                 },
                 hotkeys = {
                   show = { hyper, "g" }
                 }
               }
)
```

`SpoonInstall` automatically downloads and installs the spoon.

# Article: zzamboni.org | Getting Started With Hammerspoon

https://zzamboni.org/post/getting-started-with-hammerspoon/

## Why?

Integrates with unix commands, applescript.

Replace mac utilities

Ex:

- keyboard shortcuts for multi-step actions
- replace spotlight. call any lua function.
- edit windows
- translation between languages
- clipboard history

[API Index](http://www.hammerspoon.org/docs/index.html)

## Debugging using console

``` bash
hs.hotkey.bindSpec({ { "ctrl", "cmd", "alt" }, "y" }, hs.toggleConsole)
```

# Article: zzamboni.org | Using Spoons in Hammerspoon

https://zzamboni.org/post/using-spoons-in-hammerspoon/

Spoon: lua modules for hammerspoon

## Ex: mouse spoon

https://github.com/Hammerspoon/Spoons/raw/master/Spoons/MouseCircle.spoon.zip

# Article: Getting Started

## Spoons

http://www.hammerspoon.org/go/#smartreload

# Article: dot-hammerspoon/init.org at master · zzamboni/dot-hammerspoon

https://github.com/zzamboni/dot-hammerspoon/blob/master/init.org

Several plugins

# Tool: Spoon: WindowHalfsAndThirds

http://www.hammerspoon.org/Spoons/WindowHalfsAndThirds.html

Window tiling manager. Alternative to Spectacle

Spectacle shortcuts:

``` bash
#!+a			Fullscreen
!+>			Fullscreen
```

# Tool: Spacehammer

Hammerspoon scripting with Fennel (lisp)

ref: `~/projects/study/code/spacehammer.md`
