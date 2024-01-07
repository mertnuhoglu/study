--- 
title: "Study Jet: Transform between JSON, EDN"
date: 2020-11-23T20:55:23+03:00 
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

# jet

# Article: README

[borkdude/jet: CLI to transform between JSON, EDN and Transit, powered with a minimal query language.](https://github.com/borkdude/jet)

## Install

```bash
brew install borkdude/brew/jet
brew upgrade jet
```

Check `~/projects/study/clj/ex/study_jet/jet-01/e01.sh`

```bash
echo '{"a": 1}' | jet --from json --to edn
  ##> {"a" 1}

echo '{"a": 1}' | jet --from json --keywordize --to edn
  ##> {:a 1}

echo '{"my key": 1}' | jet --from json --keywordize '#(keyword (str/replace % " " "_"))' --to edn
  ##> {:my_key 1}

echo '{"a": 1}' | jet --from json --to transit
  ##> ["^ ","a",1]

echo '[{:a {:b 1}} {:a {:b 2}}]' \
| jet --from edn --to edn --query '(filter (= [:a :b] #jet/lit 1))'
  ##> [{:a {:b 1}}]
```

Get the latest commit SHA and date for a project from Github:

```bash
curl -s https://api.github.com/repos/borkdude/clj-kondo/commits \
| jet --from json --keywordize --to edn \
--query '[0 {:sha :sha :date [:commit :author :date]}]'
  ##> {:sha "bde8b1cbacb2b44ad2cd57d5875338f0926c8c0b", :date "2019-08-05T21:11:56Z"}
```

Get raw output from query rather than wrapped in quotes

```bash
echo '{"a": "hello there"}' | jet --from json --keywordize --query ":a" --to edn
  ##> "hello there"

echo '{"a": "hello there"}' | jet --from json --keywordize --query ":a symbol" --to edn
  ##> hello there
```

