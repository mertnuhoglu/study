--- 
title: "Study Reveal REPL"
date: 2020-11-21T09:53:14+03:00 
draft: false
description: ""
tags:
categories: clojure
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

# Quickstart reveal id=g_11738

# Index reveal repl

`rebel-readline with reveal <url:file:///~/projects/study/clj/reveal_repl.md#r=g_11749>`

```bash
clojure -M:inspect/reveal:repl/rebel
```

```clj
(add-tap ((requiring-resolve 'vlaaad.reveal/ui)))
(tap> {:a 1 :b 2})
```

`Ex01: reveal repl on terminal <url:file:///~/projects/study/clj/reveal_repl.md#r=g_11734>`

```bash
clj -X:reveal
clj -Sdeps '{:deps {vlaaad/reveal {:mvn/version "1.1.163"}}}' -m vlaaad.reveal repl
```

`Ex01: reveal repl on cursive <url:file:///~/projects/study/clj/reveal_repl.md#r=g_11735>`

`Remote connection <url:file:///~/projects/study/clj/reveal_repl.md#r=g_11737>`

# Article:

[Reveal: Read Eval Visualize Loop for Clojure](https://vlaaad.github.io/reveal/)

```clj
:reveal {:extra-deps {vlaaad/reveal {:mvn/version "1.1.163"}}
         :ns-default vlaaad.reveal
         :exec-fn repl}
```

## Ex01: reveal repl on terminal id=g_11734

Check `~/projects/study/clj/ex/study_reveal_repl/reveal-01/deps.edn`

opt01: run alias

```bash
clj -X:reveal
```

opt02: run standalone

```bash
clj -Sdeps '{:deps {vlaaad/reveal {:mvn/version "1.1.163"}}}' -m vlaaad.reveal repl
```

Bir window içinde sonuçları gösterir.

Oklar ve return tuşlarıyla objeleri inceleyebilirsin.

## Editor integration

### Cursive

#### Ex01: reveal repl on cursive id=g_11735

Edit `~/projects/study/clj/ex/study_reveal_repl/reveal-repl-01/deps.edn`

Edit `~/projects/study/clj/ex/study_reveal_repl/reveal-repl-01/Makefile`

```bash
clj -A:reveal
```

Cursive > Run Configuration > New > Clojure REPL Local

```
type: clojure.main
aliases: remote-repl
parameters: -m vlaaad.remote-repl :port 5555
```

## Features

- inspect objects: `tap>`

To inspect an object:

```clj
(tap> {:a 1})
```

- ![Eval on selection](https://vlaaad.github.io/assets/reveal/eval-on-selection.gif)

Ex01: 

01. repl: `(all-ns)`

02. reveal: select last paranthesis

03. reveal: enter > `count`

Ex02: 

01. reveal: select last paranthesis

02. reveal: enter > `(sort-by str *v)`

03. https://vlaaad.github.io/assets/reveal/java-bean.gif

- ![inspect object fields with java-bean](https://vlaaad.github.io/assets/reveal/java-bean.gif)

01. repl: `(ns-interns 'clojure.core)`

02. reveal: select some symbol

03. reveal: enter > `java-bean`

- ![URL and file browser](https://vlaaad.github.io/assets/reveal/browse.gif)

01. repl: `"https://google.com"`

02. reveal: select it > `internal browser`

- ![Doc and source](https://vlaaad.github.io/assets/reveal/doc-and-source.gif)

01. repl: `(the-ns 'cljfx.api)`

02. reveal: select `cljfx.api` > doc

03. reveal: select something in doc > doc

- ![Ref watchers](https://vlaaad.github.io/assets/reveal/watchers.gif)

Ex01:

01. repl: `(def x (atom 1))`

02. reveal: select `#'user/x` > `deref` > `watch latest`

03. repl:

```clj
(dotimes [_ 1000]
	(Thread/sleep 10)
	(swap! x inc))
```

- Charts

Ex01: ![Pie Chart](https://vlaaad.github.io/assets/reveal/pie-chart.gif)

01. repl: `{:a 10 :b 20}`

02. reveal: select `{}` > `view: pie-chart`

Ex02: ![Complex chart](https://vlaaad.github.io/assets/reveal/bar-chart.gif)

```clj
{:me {:apples 10
      :oranges [20 "sale"]}
 :you {:apples 15
       :cucumbers 10}}
```

Ex03: ![Line chart finance](https://vlaaad.github.io/assets/reveal/line-chart.gif)

```bash
(require '[clj-http.client :as http])

(http/request 
	{:method :get
	 :url (str "https://query1.finance.yahoo.com"
						 "/v8/finance/chart/"
						 "MSFT"
						 "?interval=1d&range=1y")
	 :as :json})
```

- Table view

01. repl: `(the-ns 'vlaaad.reveal)`

02. select symbol > `datafy` > select `{}` > `view:table` > select symbol > `doc`

Table keybindings

  | !up/down | sort |
  | ^c       | copy |
	| ka       | klk  | 

Keybindings

  | /      | search                |
  | ^f     | search                |
  | tab    | switch between panels |
  | ^lf/rg | switch tabs           |
  | esc    | close tab             |
	
- Support `deep-diff`

- Contextual actions

  - deref derefable things
	- meta
	- convert java array to vector
	- view color

## Remote connection with prepl id=g_11737

Run server:

```bash
clj -A:reveal \
-J-Dclojure.server.reveal='{:port 5555 :accept vlaaad.reveal/io-prepl}'
```

Connect to server (opens Reveal window for every connection)

```bash
nc localhost 5555
  ##> reveal window appears
```

# rebel-readline with reveal id=g_11749

[Reveal · Practicalli Clojure](http://practicalli.github.io/clojure/clojure-tools/data-browsers/reveal.html)

opt01: use praticalli deps.edn

```bash
clojure -M:inspect/reveal:repl/rebel
```

```clj
(add-tap ((requiring-resolve 'vlaaad.reveal/ui)))
(tap> [1 2 3])
(tap> {:a 1 :b 2})
```

# next

01. remote-prepl: run reveal on production apps

02. ui: check values without window

03. nrepl-based editors

