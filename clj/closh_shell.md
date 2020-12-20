--- 
title: "Study closh shell"
date: 2020-11-23T13:10:58+03:00 
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

# Index closh

@mine: Don't use this. bb and other tools are better.

# Shell Scripting Made Simple with Clojure - Jakub Dundalek

[clojureD 2020: "Shell Scripting Made Simple with Clojure" by Jakub Dundalek - YouTube](https://www.youtube.com/watch?v=1it_wBCYBf8&t=1606s)

Bash good parts:

- unix philosophy: compose programs

Bash bad parts:

- accidental complexity during evolution
- many small tools with overlap
- operates on unstructured text
- breaks on files with spaces

Check `~/projects/study/clj/ex/study_closh_shell/closh-01/e01.closh`

Change prompt:

```clj
$ (defn closh-prompt [] "$$ ")
$$
```

Regular commands supported: git, ls, date, redirections `>` etc.

```clj
$ (def x :kw)
#'user/x
$ (str "hello " x)
"hello :kw"
$ echo (+ 2 3)
5
```

Bash redirects and conditionals work:

```bash
date > tmp.txt
cat tmp.txt
```

Error: `>` redirection fails silently.
