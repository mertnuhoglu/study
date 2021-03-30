---
title: "Study: Ring Library"
date: 2021-03-29T20:48:20+03:00 
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

---

# Article: README

[ring-clojure/ring: Clojure HTTP server abstraction](https://github.com/ring-clojure/ring)

# Article: Getting Started

[Getting Started · ring-clojure/ring Wiki](https://github.com/ring-clojure/ring/wiki/Getting-Started)

```bash
cd ~/projects/study/clj/ex/ring/e01
lein new hello-world
cd hello-world
lein deps
```

Check `~/projects/study/clj/ex/ring/e01/hello-world/project.clj`

Edit `~/projects/study/clj/ex/ring/e01/hello-world/src/hello_world/core.clj`

```clj
(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})
```

```bash
lein repl
```

At REPL:

```clj
(use 'ring.adapter.jetty)
(use 'hello-world.core)
(run-jetty handler {:port  3000
                       :join? false})
```

Open `http://localhost:3000/`


