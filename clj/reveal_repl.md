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

- # Quickstart reveal id=g11738
  id:: d1c095fe-65f2-4cd9-91ee-40207a5ff09c

# Index reveal repl

`reveal with emacs cider <url:file:///~/projects/study/clj/reveal_repl.md#r=g11949>`

```bash
clojure -M:inspect/reveal-nrepl
```

`rebel-readline with reveal <url:file:///~/projects/study/clj/reveal_repl.md#r=g11749>`

```bash
clojure -M:inspect/reveal:repl/rebel
```

```clj
(add-tap ((requiring-resolve 'vlaaad.reveal/ui)))
{:a 1 :b 2}
(tap> *1)
```

`Navigation <url:file:///~/projects/study/clj/reveal_repl.md#r=g11918>`

`Ex01: reveal repl on terminal <url:file:///~/projects/study/clj/reveal_repl.md#r=g11734>`

```bash
clj -X:reveal
clj -Sdeps '{:deps {vlaaad/reveal {:mvn/version "1.1.163"}}}' -m vlaaad.reveal repl
```

`Ex01: reveal repl on cursive <url:file:///~/projects/study/clj/reveal_repl.md#r=g11735>`

`Remote connection <url:file:///~/projects/study/clj/reveal_repl.md#r=g11737>`

# Article: README: Reveal: Read Eval Visualize Loop for Clojure

[Reveal: Read Eval Visualize Loop for Clojure](https://vlaaad.github.io/reveal/)

```clj
:reveal {:extra-deps {vlaaad/reveal {:mvn/version "1.1.163"}}
         :ns-default vlaaad.reveal
         :exec-fn repl}
```

- ## Ex01: reveal repl on terminal id=g11734
  id:: 04750453-06d0-4c86-b577-6f0b96c12e71

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

[Reveal: Read Eval Visualize Loop for Clojure](https://vlaaad.github.io/reveal/#cursive)

- #### Ex01: reveal repl on cursive id=g11735
  id:: 916bd762-d139-4949-9051-5d63ab7a3b4f

Edit `~/projects/study/clj/ex/study_reveal_repl/reveal-repl-01/deps.edn`

Edit `~/projects/study/clj/ex/study_reveal_repl/reveal-repl-01/Makefile`

```bash
clj -A:reveal -J-Dclojure.server.repl='{:port 5555 :accept vlaaad.reveal/repl}'
make reveal
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

- ## Remote connection with prepl id=g11737
  id:: 9c7b2f3a-e1d6-4325-96df-3130c6851cad

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

- ## Navigation id=g11918
  id:: 3ea0f74e-8b96-494e-a406-ba7fb8372a17

  | space enter           | open context menu                 |
  | tab                   | switch between output and results |
  | results panel         |                                   |
  | #-> #<-               | switch tabs                       |
  | #up                   | tree view                         |
	| esc                   | close tab                         |
	| structural navigation |                                   |
	| !<-                   | up in tree                        |
	| !up !dn               | next/prev                         |
	| !Home !End            | first/last                        |

- # rebel-readline with reveal id=g11749
  id:: 119082f9-043b-4681-880a-f9816c0ddea9

[Reveal · Practicalli Clojure](http://practicalli.github.io/clojure/clojure-tools/data-browsers/reveal.html)

opt01: use praticalli deps.edn

```bash
clojure -M:inspect/reveal:repl/rebel
```

```clj
(add-tap ((requiring-resolve 'vlaaad.reveal/ui)))
(tap> [1 2 3])
(tap> {:a 1 :b 2})
{:a 1 :b 2}
(tap> *1)
```

# next

01. remote-prepl: run reveal on production apps

02. ui: check values without window

03. nrepl-based editors

# reveal by itself 

[Reveal · Practicalli Clojure](https://practicalli.github.io/clojure/clojure-tools/data-browsers/reveal.html)

```bash
clojure -M:inspect/reveal
```

- # reveal with emacs cider id=g11949
  id:: 04c7013b-46f9-4fbd-9819-3eb8246ab77c

[Using Reveal with nrepl Editors](https://practicalli.github.io/clojure/clojure-tools/data-browsers/reveal.html#using-reveal-with-nrepl-editors)

```bash
clojure -M:inspect/reveal-nrepl
```

Emacs: `cider-connect`

- # Video: Reveal REPL and data browser for Clojure by practicalli - YouTube id=g11917
  id:: c418ce61-47e6-4e53-a237-30425b5ae9ba

[Reveal REPL and data browser for Clojure - YouTube](https://www.youtube.com/watch?v=1jy09_16EeY)

```bash
cd ~/codes/clj/banking-on-clojure-spec
clojure -A:test:repl-reveal
```

Emacs: 

```emacs
:e ~/codes/clj/banking-on-clojure-spec/deps.edn
cider-connect
```

Repl:

```clj
(require 'practicalli.banking-specifications)
(in-ns 'practicalli.banking-specifications)
(spec-gen/sample (spec/gen ::customer-details))
```

![Reveal data browser loads the generated data:](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210119_112458.jpg)


