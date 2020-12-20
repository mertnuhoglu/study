--- 
title: "Study deep-diff2"
date: 2020-11-22T13:16:17+03:00 
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

# Quickstart deep-diff2 id=g_11739

- `Ex01: diff <url:file:///~/projects/study/clj/deep_diff.md#r=g_11747>`

`(ddiff/diff {:a 1 :b 2} {:a 1 :c 3})`

# Article: deep-diff2 README

[lambdaisland/deep-diff2: Deep diff Clojure data structures and pretty print the result](https://github.com/lambdaisland/deep-diff2)

## Ex01: `diff` id=g_11747

Check `~/projects/study/clj/ex/study_deep_diff/deep-diff-01/src/deep_diff.clj`

```clj
  (ddiff/pretty-print (ddiff/diff {:a 1 :b 2} {:a 1 :c 3}))
```

```bash
clj -X:pretty-print
  ##> {:a 1, +:c 3, -:b 2}
```

# deep-diff with reveal id=g_11748

Check `~/projects/study/clj/ex/study_reveal_repl/reveal-deep-diff-01/deps.edn`

```bash
(def m1 {:a 1 :b 2})
(def m2 {:a 1 :c 3})
```

