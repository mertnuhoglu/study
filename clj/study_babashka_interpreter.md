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

# Article: README

[borkdude/babashka: A Clojure babushka for the grey areas of Bash (native fast-starting Clojure scripting environment)](https://github.com/borkdude/babashka)

## Ex01:

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

## Ex02: Execute clj script

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

# Examples

[babashka/README.md at master · borkdude/babashka](https://github.com/borkdude/babashka/blob/master/examples/README.md)

[Convert project.clj to deps.edn](https://github.com/borkdude/babashka/blob/master/examples/README.md#convert-projectclj-to-depsedn)

[http server](https://github.com/borkdude/babashka/blob/master/examples/http_server.clj)

[sql examples]([babashka/babashka-sql-pods: Babashka pods for SQL databases](https://github.com/babashka/babashka-sql-pods))

- Inspect Data Files With Portal On Babashka id=g_11752

[portal](https://github.com/borkdude/babashka/blob/master/examples/README.md#portal)

```bash
cd /Users/mertnuhoglu/codes/clj/babashka
examples/portal.clj ~/.clojure/deps.edn
```

[fzf](https://github.com/borkdude/babashka/blob/master/examples/README.md#fzf)

Check `~/codes/clj/babashka/examples/fzf.clj`

```bash
examples/portal.clj ~/.clojure/deps.edn
cat src/babashka/main.clj | bb examples/fzf.clj
```

# @next

[babashka/io-flags.md at master · borkdude/babashka](https://github.com/borkdude/babashka/blob/master/doc/io-flags.md)

Differences of inputting between `-i`, `<<<`

