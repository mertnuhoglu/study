--- 
title: "Study babashka clojure interpreter"
date: 2020-11-23T18:55:11+03:00 
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

- # Index babashka  id=g11812
  id:: e35871e0-bf6e-46e7-92b3-ffe879759ca9

	pnt
		Deney: nbb ile repl server  <url:file:///~/projects/study/logbook/log_20221126.md#r=g13591>
		nbb: nodejs için babashka <url:file:///~/prj/study/clj/babashka_interpreter.md#r=g13583>
		20221126
		bb nrepl server <url:file:///~/prj/study/clj/babashka_interpreter.md#r=g11958>
		CLI Arguments/Options <url:file:///~/prj/study/clj/babashka_interpreter.md#r=g13584>
		run portal browser on any edn file <url:file:///~/prj/study/clj/babashka_interpreter.md#r=g13585>
		inline script <url:file:///~/prj/study/clj/babashka_interpreter.md#r=g13586>
		Execute clj script <url:file:///~/prj/study/clj/babashka_interpreter.md#r=g13587>
		Loading and using pods <url:file:///~/prj/study/clj/babashka_interpreter.md#r=g13590>
		Babashka Datalevin (datalog) example <url:file:///~/prj/study/clj/babashka_interpreter.md#r=g13410>
	rtc
		Article: README <url:file:///~/prj/study/clj/babashka_interpreter.md#r=g13588>
		Examples <url:file:///~/prj/study/clj/babashka_interpreter.md#r=g13589>

- ## bb nrepl server id=g11958
  id:: 4aaa2995-755d-4957-9d2c-c7c5e5fd5382

[Quick start: Clojure (babashka) · Olical/conjure Wiki](https://github.com/Olical/conjure/wiki/Quick-start:-Clojure-(babashka))

```bash
echo "5678" > .nrepl-port
bb --nrepl-server 5678
```

- ## CLI Arguments/Options id=g13584
  id:: 80347f80-ba7d-461e-96fa-d9cf1cc25f83

	| -i | bind *input* to a seq of lines      |
	| -I | bind *input* to a seq of EDN values |
	| -e | eval expr                           |
	| -f | eval file                           |

- ## run portal browser on any edn file id=g13585
  id:: ded1268a-693a-4900-9139-69afb20cd6b1

`- Inspect Data Files With Portal On Babashka <url:file:///~/projects/study/clj/lib/babashka_interpreter.md#r=g11752>`

```bash
portal.clj ~/.clojure/deps.edn
```

- ## inline script id=g13586
  id:: 2613fd81-ccbf-4386-b282-3e34d69923d6

`Ex01: inline scripts <url:file:///~/projects/study/clj/lib/babashka_interpreter.md#r=g11814>`

```bash
$ ls | bb -i '(prn *input*)'
$ ls | bb -i '(filter #(-> % io/file .isDirectory) *input*)'
("doc" "resources" "sci" "script" "src" "target" "test")
```

- ## Execute clj script id=g13587
  id:: 66a84a17-8507-412a-bdff-bcd62fe26e35

`Ex02: Execute clj script <url:file:///~/projects/study/clj/lib/babashka_interpreter.md#r=g11813>`

shebang:

```bash
!/usr/bin/env bb
```

- # Article: README id=g13588
  id:: 75413864-8e71-4a25-9f11-4ad1505b86cb

[borkdude/babashka: A Clojure babushka for the grey areas of Bash (native fast-starting Clojure scripting environment)](https://github.com/borkdude/babashka)

- ## Ex01: inline scripts id=g11814
  id:: 422ad707-38c5-426a-a630-ee1d10a71471

Check `~/projects/study/clj/ex/study_babashka_interpreter/bb-01/run.sh`

Read input from shell command:

```bash
$ ls | bb -i '(filter #(-> % io/file .isDirectory) *input*)'
("doc" "resources" "sci" "script" "src" "target" "test")

$ ls | bb -i '(take 2 *input*)'
("CHANGES.md" "Dockerfile")
```

Read edn from stdin:

```bash
$ bb '(vec (dedupe *input*))' <<< '[1 1 1 1 2]'

```

- ## Ex02: Execute clj script id=g11813
  id:: 94c33a73-998b-47d6-a53c-a9341f788357

Check `~/projects/study/clj/ex/study_babashka_interpreter/bb-01/pst.clj`

```bash
chmod +x pst.clj
pst.clj
  ##> 08:07
```

Check `~/projects/study/clj/ex/study_babashka_interpreter/bb-01/fetch_latest_github_tag.clj`

```bash
```

## Help

```bash
bb -h
```

	| -i | bind *input* to a seq of lines      |
	| -I | bind *input* to a seq of EDN values |
	| -e | eval expr                           |
	| -f | eval file                           |

## Installation 

```bash
brew install borkdude/brew/babashka
brew upgrade babashka
```

- # Examples id=g13589
  id:: 35b217a2-dfc3-4223-be4c-56cf5909341a

[babashka/README.md at master · borkdude/babashka](https://github.com/borkdude/babashka/blob/master/examples/README.md)

[Convert project.clj to deps.edn](https://github.com/borkdude/babashka/blob/master/examples/README.md#convert-projectclj-to-depsedn)

[http server](https://github.com/borkdude/babashka/blob/master/examples/http_server.clj)

[sql examples > babashka/babashka-sql-pods: Babashka pods for SQL databases](https://github.com/babashka/babashka-sql-pods)

- Inspect Data Files With Portal On Babashka id=g11752
  id:: 900a5857-6ddf-4f2e-8a29-c7fa0d812cb2

[portal](https://github.com/borkdude/babashka/blob/master/examples/README.md#portal)

```bash
/Users/mertnuhoglu/codes/clj/lib/babashka/examples/portal.clj ~/.clojure/deps.edn
portal.clj ~/.clojure/deps.edn
```

[fzf](https://github.com/borkdude/babashka/blob/master/examples/README.md#fzf)

Check `~/codes/clj/lib/babashka/examples/fzf.clj`

```bash
examples/portal.clj ~/.clojure/deps.edn
cat src/babashka/main.clj | bb examples/fzf.clj
```

# Run console

```sh
rlwrap bb
```


- # Loading and using pods id=g13590
  id:: 557785f2-3eea-4fb6-bc4c-d39ef458b700

```clj
(require '[babashka.pods :as pods])
(pods/load-pod 'org.babashka/buddy "0.1.0")
```


# @next

[babashka/io-flags.md at master · borkdude/babashka](https://github.com/borkdude/babashka/blob/master/doc/io-flags.md)

Differences of inputting between `-i`, `<<<`

- # Babashka Datalevin (datalog) example id=g13410
  id:: 665a9c3f-6e20-490f-a435-9470447ea6dc

[pod-registry/datalevin.clj at master · babashka/pod-registry](https://github.com/babashka/pod-registry/blob/master/examples/datalevin.clj)

rfr: `~/prj/study/clj/ex/study_babashka_interpreter/bb-01/datalevin.clj`

# pnt

- ## nbb: nodejs için babashka id=g13583
  id:: 7ccdaa02-68fb-4180-abda-06d06c119bc8

[babashka/nbb: Scripting in Clojure on Node.js using SCI](https://github.com/babashka/nbb)

```bash
nbb -e '(+ 1 2 3)'
```


