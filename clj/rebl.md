--- 
title: "rebl"
date: 2021-01-20T11:30:53+03:00 
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

- # Quickstart rebl id=g11926
  id:: 02189e60-1f3e-4306-a370-14bb1aa30e40

- # Index rebl id=g11927
  id:: 5be301eb-86a6-4572-9efd-b24e2aea89d2

Emacs integration: `Run REBL for nREPL <url:file:///~/projects/study/clj/rebl.md#r=g11934>`

`Run REBL <url:file:///~/projects/study/clj/rebl.md#r=g11928>`

Examples: `Video: REBL - Stuart Halloway - YouTube <url:file:///~/projects/study/clj/rebl.md#r=g11935>`

# Documentation

## Install rebl

[Cognitect REBL | Datomic](https://docs.datomic.com/cloud/other-tools/REBL.html#installation)

Get REBL by email.

```bash
bash ./install
```

- # Run REBL id=g11928
  id:: ec85d4d3-b6e0-484d-b609-7f327d600ba2

opt01: seancorfield deps:

```bash
cd /Users/mertnuhoglu/codes/clj/dot-clojure
java15
clojure -M:rebl
```

opt02: practicalli deps:

`Run cognitect rebl: <url:file:///~/projects/study/clj/practicalli_deps_edn.md#r=g11925>`

- # Run REBL for nREPL id=g11934
  id:: e98cfc10-df6f-4633-b5f1-8e8d3f6bb773

ref: `Run cognitect rebl: <url:file:///~/projects/study/clj/practicalli_deps_edn.md#r=g11925>`

ref: `seancorfield deps.edn dosyasıyla rebl çalıştırmayı dene 20210120  <url:file:///~/projects/study/logbook/log_20210117.md#r=g11924>`

ref: `https://practicalli.github.io/clojure/alternative-tools/clojure-tools/cognitect-rebl.html#run-rebl-for-nrepl-based-editors`

Check `~/projects/study/clj/ex/rebl/rebl-nrepl-01/deps.edn`

Edit `~/projects/private_dotfiles/.config/clojure/deps.edn`

```clj
  :inspect/rebl15
  {:extra-deps {org.clojure/core.async {:mvn/version "0.6.532"}
                ;; deps for file datafication (REBL 0.9.149 or later)
                org.clojure/data.csv {:mvn/version "0.1.4"}
                org.clojure/data.json {:mvn/version "0.2.7"}
                org.yaml/snakeyaml {:mvn/version "1.25"}
                ;; assumes you've installed the latest Cognitect dev-tools:
                com.cognitect/rebl {:mvn/version "0.9.242"}
                ;; openjfx (remove these if your JDK bundles openjfx):
                org.openjfx/javafx-fxml     {:mvn/version "15-ea+6"}
                org.openjfx/javafx-controls {:mvn/version "15-ea+6"}
                org.openjfx/javafx-swing    {:mvn/version "15-ea+6"}
                org.openjfx/javafx-base     {:mvn/version "15-ea+6"}
                org.openjfx/javafx-web      {:mvn/version "15-ea+6"}}
   :main-opts [ "-m" "cognitect.rebl"]}
```

```clj
java15
clojure -M:lib/cider-nrepl:inspect/rebl15:middleware/nrebl
```

- ## Connect REBL from Emacs Cider id=g11943
  id:: a39dca0d-29d5-476e-b009-d34ba61a4ae6

Emacs: Open  `~/projects/study/clj/ex/rebl/rebl-nrepl-01/deps.edn`

`SPC SPC cider-connect-clj`

Run commands in `~/projects/study/clj/ex/rebl/rebl-examples/src/e01/rebl_examples.clj`

- ## Connect REBL from Intellij Cursive id=g11944
  id:: 0c9470f8-8283-4f23-b730-c2a05deeb40b

```
Configurations > Clojure REPL > Remote
Connection Type: nREPL
Use pot from nREPL file
	Project: .select project
	Use standard port file: checked
```

![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210121_211450.jpg)

- ## Connect REBL from Vim Conjure id=g11945
  id:: 3efd5f99-c530-425c-aba2-3b57affa3fd4

`:ConjureConnect <port>`

- # Video: REBL - Stuart Halloway - YouTube id=g11935
  id:: bb2bd02a-ed07-4a58-9ac4-7df3a7fe1008

[REBL - Stuart Halloway - YouTube](https://www.youtube.com/watch?v=c52QhiXsmyI&t=1347s)

Ex: Meta data for charts

```clj
  :chart (with-meta [1 2 3] {:rebl/xy-chart {:title "My Chart"}})}}
`rkdown_MoveToNextHeader``

Ex: Inspect stacktrace

## Test it

Check `/Users/mertnuhoglu/projects/study/clj/ex/rebl/rebl-examples/deps.edn`

```clj
cd /Users/mertnuhoglu/projects/study/clj/ex/rebl/rebl-examples
java15
clojure -M:lib/cider-nrepl:inspect/rebl15:middleware/nrebl
```

Emacs: `SPC SPC cider-connect-clj`

Run commands in `~/projects/study/clj/ex/rebl/rebl-examples/src/e01/rebl_examples.clj`

