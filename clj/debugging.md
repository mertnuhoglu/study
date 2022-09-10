---
title: "Study Debugging"
date: 2022-09-09T12:51:25+03:00 
draft: false
description: ""
tags:
categories: 
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

# Study Debugging id=g13333

## Conjure + nREPL CIDER Debugger id=g13334

Source: [(450) Debugging Clojure with Conjure and Neovim - YouTube](https://www.youtube.com/watch?v=uP9iSEh7dxg)

`/Users/mertnuhoglu/prj/study/clj/ex/debugging/debug01/deps.edn`

### Quickstart

Run: `clojure -M:repl/rebel-nrepl`

Connect to Repl inside Conjure.

Run in Vim: `:ConjureCljDebugInit`

Put `#break` and reload the code.

Eval the code.

Everything pauses.

### Commands to interact with debugger

Vim: `:ConjureCljDebugInput [input]`

		; Inputs: continue, locals, inspect, trace, here, continue-all, next, out, inject, inspect-prompt, quit, in, eval

```
:ConjureCljDebugInput locals
=>
; CIDER debugger
; Class: clojure.lang.PersistentArrayMap
; Contents: 
;   opts = { :x 10 }
```

## debug02 project: figwheel 

rfr: `/Users/mertnuhoglu/prj/study/clj/ex/debugging/debug02/deps.edn`

## ClojureScript Debugging id=g13335

ClojureScripte nrepl ile bağlanabilirsin. REPL'dan browser'a komut gönderebilirsin. Ama debug edemezsin.

1. Debug etmek için, tek yol repl'a kopyala yapıştır ile komutları çalıştırmak.

`in-ns` ile dosyaları yükleyemezsin. Kopyala yapıştır ile bir cljs dosyasındaki kodları gönderebilirsin ancak.

2. Ayrıca `js/console.log` ile de debug yapabilirsin.

3. Ancak Source Map özelliğiyle browser'ın kendi debuggerıyla cljs kodlarını debug edebilirsin: [ClojureScript - Source maps](https://clojurescript.org/reference/source-maps)

4. Gadget aracıyla Chrome Devtools altından veriyi gözetleyebilirsin: rfr: <url:file:///~/prj/study/clj/clojurescript.md#r=g13336>


