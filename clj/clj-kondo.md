--- 
title: "Study clj-kondo"
date: 2021-01-31T12:00:17+03:00 
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

# Index clj-kondo

```clj
#_:clj-kondo/ignore
(inc 1 2 3)
```

# Issues clj-kondo

# Official Documentation clj-kondo

## Editor Integration

[clj-kondo/editor-integration.md at master · clj-kondo/clj-kondo](https://github.com/clj-kondo/clj-kondo/blob/master/doc/editor-integration.md)

### Vim

ref: `Tool: clj-kondo in vim <url:file:///~/projects/study/clj/clojure_in_vim.md#r=g_11964>`

## Usage

Lint from stdin:

```bash
$ echo '(def x (def x 1))' | clj-kondo --lint -
<stdin>:1:8: warning: inline def
```

Lint a file:

```bash
$ echo '(def x (def x 1))' > /tmp/foo.clj
$ clj-kondo --lint /tmp/foo.clj
/tmp/foo.clj:1:8: warning: inline def
```

Lint a directory:

```bash
$ cd /Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06
$ clj-kondo --lint src
src/clj_kondo/test.cljs:7:1: warning: redundant do
src/clj_kondo/calls.clj:291:3: error: Wrong number of args (1) passed to clj-kondo.calls/analyze-calls
```

Lint a project classpath:

```bash
cd /Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06
clj-kondo --lint "$(clojure -Spath)"
```

## Configuration

[clj-kondo/config.md at master · clj-kondo/clj-kondo](https://github.com/clj-kondo/clj-kondo/blob/master/doc/config.md)

Check: `~/projects/study/clj/ex/study_clojure/ex06/src/clj_kondo_linter.clj`

Ignore warnings in an expression:

```bash
#_:clj-kondo/ignore
(inc 1 2 3)
```

Ignore the contents of rich comment forms:

```bash
{:skip-comments true}
```



