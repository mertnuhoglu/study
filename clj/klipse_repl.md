
# Quickstart klipse repl

# Index klipse repl

`Ex01: Run klipse repl anywhere <url:file:///~/projects/study/clj/klipse_repl.md#r=g11733>`

```bash
clj -A:klipse
```

`Remote connection <url:file:///~/projects/study/clj/klipse_repl.md#r=g11736>`

```bash
clojure -A:klipse --port 9876
```

# Article: klipse-repl README

[viebel/klipse-repl: Beginners friendly Clojure REPL](https://github.com/viebel/klipse-repl)

## Ex01: Run klipse repl anywhere id=g11733

```bash
clojure -Sdeps "{:deps {viebel/klipse-repl {:mvn/version \"0.2.3\"}}}" -m klipse-repl.main
```

Check `~/projects/study/clj/ex/study_klipse_repl/klipse-01/Makefile`

```bash
make klipse
```

opt02: alias

Check `~/projects/study/clj/ex/study_klipse_repl/klipse-01/deps.edn`

```bash
clj -A:klipse
```

## Features

- limit number of items to be displayed: `--print-length`

```bash
clojure -A:klipse-repl --print-length 10
```

- `rebel-readline` is available in klipse.

	- `tab` autocompletion
	- indentation 
	- coloring

- online documentation link

```bash
(doc inc)
```

- live dependency update

01. update `deps.edn`

02. call `(refresh-classpath)`

03. add deps with `add-deps`

Show classpath: `(classpath)`

Add deps: 

```bash
(add-deps '{funcool/cuerdas {:mvn/version "2.0.5"}})
```

## Remote connection id=g11736

```bash
clojure -A:klipse --port 9876
```

Connect to it:

```bash
nc localhost 9876
```

