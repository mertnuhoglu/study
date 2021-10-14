--- 
title: "Study Clojure CLI Tools"
date: 2020-11-24T12:32:43+03:00 
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

# Index cli tools

## Import source, doc, apropos and other REPL functions id=g11947

If your repl doesn't have above functions such as `source` or `apropos`, require them:

```clj
(require '[clojure.repl :refer :all])
```

# Article: What are the Clojure Tools? id=g11929

[What are the Clojure Tools?](https://betweentwoparens.com/what-are-the-clojure-tools)

> When I start learning a new language I like to begin by understanding the tooling ecosystem. For me, understanding the tools enables me to confidently focus on learning the language itself

Three tools under `clj`:

- clojure
- deps.edn
- tools.deps.alpha

## clojure

`clj` calls `clojure`.

`clojure` calls this:

```bash
java [java-opt*] -cp classpath clojure.main [init-opt*] [main-opt] [arg*]
```

`clj`: used in development

Why? Because it wraps `clojure` with `rlwrap` tool.

`rlwrap`: adds readline support to `clojure` command. 

## tools.deps.alpha

Responsible for understanding which dependencies your project needs.

- Reads in `deps.edn`
- Resolves deps and their transitive deps
- Builds a classpath

## deps.edn

Specifies dependencies and configurations.

- `:deps`
- `:paths`: where is source code 
- `:aliases`

## Clojure CLI Tools Installer

```bash
brew install clojure/tools/clojure
```

# Article: The Ultimate Guide To Clojure REPLs Guide id=g12457

[Clojure REPLs | The Ultimate Guide To Clojure REPLs Guide](https://lambdaisland.com/guides/clojure-repls/clojure-repls)

ref: `The Ultimate Guide To Clojure REPLs - lambdaisland <url:file:///~/projects/study/clj/articles_clojure.otl#r=g12455>`

Built-in repl: `clojure.main/repl`

```bash
java -jar clojure-1.8.0.jar
```

## REPL functions: id=g11930

	| `clojure.repl`   |                                              |
	| source           | `(source clojure.main/repl)`                 |
	| apropos          | `(apropos "some")`                           |
	| doc              |                                              |
	| dir              | show functions in ns: `(dir clojure.string)` |
	| pst              | print stacktrace                             |
	| find-doc         | search docstrings `(find-doc "join")`        |
	| `javadoc`        |                                              |
	| `clojure.pprint` |                                              |
	| pprint           |                                              |
	| pp               | `(pprint *1)`                                |
	| magic variables  |                                              |
	| *1 *2 *3         | last eval                                    |
	| *e               | last uncaught exception                      |

ref: `Import source, doc, apropos and other REPL functions <url:file:///~/projects/study/clj/clojure_cli_tools.md#r=g11947>`

## Line Editing and History Support with rlwrap

```bash
rlwrap java -jar clojure-1.8.0.jar
```

## Socket REPL id=g11931

Where does a REPL read from or print to?

Standard input and output.

- `STDIN` = `System.in` in Java = `clojure.core/*in*` in clj

What if you hook something else up as input and output stream?

This is what Socket REPL does.

Clojure starts a TCP server and waits for connections.

```bash
java -cp clojure-1.8.0.jar -Dclojure.server.repl="{:port 5555 :accept clojure.core.server/repl}" clojure.main
```

This command starts a regular REPL. To see socket REPL, connect to it with netcat:

```bash
nc localhost 5555
rlwrap nc localhost 5555 ; opt
```

You can set up an SSH tunnel to a REPL server. So you can connect to it from a remote machine.

## Creating Your Own Socket REPL

Ex01:

`wrapple.clj`

```bash
(ns wrapple)

(defn echo-time []
  (println (.toString (java.util.Date.))))
```

Start a socket REPL:

```bash
java -cp clojure-1.8.0.jar:. -Dclojure.server.repl="{:port 5555 :accept wrapple/echo-time}" clojure.main
```

Check it:

```bash
$ nc localhost 5555
Thu Jul 07 16:22:17 CEST 2016
```

Ex02: Make a REPL loop

```bash
(ns wrapple
  (:require [clojure.string :as str]))

(defn prompt-and-read []
  (print "~> ")
  (flush)
  (read-line))

(defn uprepl []
  (loop [input (prompt-and-read)]
    (-> input
        str/upper-case
        println)
    (recur (prompt-and-read))))
```

```bash
java -cp clojure-1.8.0.jar:. -Dclojure.server.repl="{:port 5555 :accept wrapple/uprepl}" clojure.main
```

```bash
$ nc localhost 5555
~> isn't this awesome?
ISN'T THIS AWESOME?
~>
```

## nREPL

nREPL: network REPL

What is its difference from socket REPL?

Socket REPL: stream based. It reads lines from input stream, writes to an output stream.

This is difficult to communicate programmatically.

nREPL: message based. It puts program-to-program communication first. 

## nREPL Message

Request message:

```bash
{:id "10"
 :op "eval"
 :code "(+ 1 1)"
 :ns "user"}
```

Response message:

```bash
{:id "10"
 :value "2"}
```

Built-in operations:

- `eval`
- `interrupt`: an existing eval operation
- `describe`: list of operations
- `load-file`: source file

## nREPL Sessions

Sessions: allow multiple REPL clients to use a single nREPL server.

Session middleware adds three ops:

- `ls-session`: list sessions
- `clone`
- `close`

Each messsage carries a `session-id`

# 
