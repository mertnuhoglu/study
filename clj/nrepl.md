--- 
title: "Study nrepl"
date: 2020-11-24T09:32:59+03:00 
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

# Article: Documentation

[nREPL :: nREPL](https://nrepl.org/nrepl/index.html)

## Overview

Tools use nrepl when they need to evaluate clojure code in remote environments.

## Why nREPL?

Designed for:

- Application developers: Remote debugging, updating deployed appliactions

- Toolmakers: Introspecting running environments

## Server

[nREPL Server :: nREPL](https://nrepl.org/nrepl/usage/server.html)

`clj` uses nrepl's cli: `nrepl.cmdline`

Run nrepl server in headless mode:

`deps.edn`

```clj
:aliases {:nREPL
					{:extra-deps
					  {nrepl/nrepl {:mvn/version "0.8.3"}}}}
```

```bash
clj -R:nREPL -m nrepl.cmdline
```

Add nrepl middleware to nrepl profile:

```clj
:aliases {:nREPL
					{:extra-deps
					  {nrepl/nrepl {:mvn/version "0.8.3"
						 cider/piggieback {...}}}}}
```

```bash
clj -R:nREPL -m nrepl.cmdline --middleware "[cider.piggieback/wrap-cljs-repl]"
```

Options: `--help`

	| -i/--interactive | start nrepl with client |
	| -c/--connect     |                         |
	| b/--bind ADDR    |                         |

Options in `deps.edn`

```clj
:main-opts ["-m" "nrepl.cmdline"
            "-b" "0.0.0.0"
            "-p" "12345"
            "--middleware" "[cider.nrepl/cider-middleware,refactor-nrepl.middleware/wrap-refactor]"]
```

Run with Leiningen:

```bash
lein repl 
lein repl :headless
```

Lein uses nrepl client `REPL-y`

### Embedding nREPL

Host a REPL server inside your application. Benefits: debuggging, sanity-checking, code patching.

nREPL provides a socket based server.

```clj
=> (require '[nrepl.server :refer [start-server stop-server]])
nil
=> (defonce server (start-server :port 7888))
='user/server
```

## Clients

# Article: JUXT Blog - Overview of the nREPL

[JUXT Blog - Overview of the nREPL](https://juxt.pro/blog/nrepl)

## Client/Server

`lein repl` makes:

1. starts a server by `tools.nrepl/start-serve`
2. starts REPL-y, a terminal nREPL client

`CIDER.el` is another client. But it also has nREPL server extension: `CIDER-nREPL`

## Transport Layer

Transport: communicating with nREPL, for sending & receiving messages.

Default transport: bencode over tcp. 

Bencode: encoding format designed by Bittorrent.

Example bencode:

```
;; A number, i prefix, e marks the end
i10e ;; 10

;; A string, <length>: prefix. Length is bytes (for unicode)
4:spam ;; "spam"

;; A list, l prefix, e marks the end, elements nest
li1ei2ei3ee ;; [1 2 3]

;; An associative array (hash map)
d3:foo3:bari1e4:spame ;; {"foo" "bar", 1 "spam"}
```

## Messages

Message: a map

Message types: request, response

Common keys in request:

- id: Responses should include it
- op: What to do. `eval` is common operation
- session: id that relates to a set of thread bindings.

Example unencoded request message:

```
{:op "eval" :code "(println (+ 1 2))" :id "some-unique-id"}
```

Response messages to the above request:

```
{:id "some-unique-id" :out "3"}
{:id "some-unique-id" :value "nil" :status ["done"]}
```

## Message Handling with tools.nrepl

Middleware: Handles messages

tools.nrepl: most popular implementation of nREPL

`:transport` key is added to messages as they are handled.





