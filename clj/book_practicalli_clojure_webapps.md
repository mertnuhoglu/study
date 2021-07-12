---
title: "Study Book: Practicalli Clojure Webapps"
date: 2021-04-19T10:41:45+03:00 
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

[Introduction · Practicalli Clojure WebApps](https://practicalli.github.io/clojure-webapps/)

[(350) Practicalli Clojure WebApps - YouTube](https://www.youtube.com/playlist?list=PLpr9V-R8ZxiCe9p9tFk24ChNSpGfanUbT)

## Introduction

> Clojure does not focus on the classic framework approach like Rails or Spring, for a very good reason. Frameworks are design decisions others have made without knowing the context of the current problem at hand, so there is no guarantee on how many of those decisions are relevant

## Application Servers

[Application servers · Practicalli Clojure WebApps](https://practicalli.github.io/clojure-webapps/app-servers/)

### Simple restart id=g_12109

```bash
cd ~/projects/study/clj/ex/book_practicalli_clojure_webapps
clojure -X:project/new :name practicalli/p01
```

Check `~/projects/study/clj/ex/book_practicalli_clojure_webapps/p01/src/practicalli/p02.clj`

Running options:

opt01: inside repl:

```clj
(require '[practicalli.p02] :verbose)
(in-ns 'practicalli.p02)
(def app-server-instance (-main 8888))
```

Open `localhost:8888`

`p02 p05 p06` work properly.

opt02: inside conjure:

Eval buffer `,eb`

Eval: `(def app-server-instance (-main 8888))`

### Write a basic test

[Practicalli: Clojure web server from scratch with deps.edn](https://practicalli.github.io/blog/posts/clojure-web-server-cli-tools-deps-edn/)

Check `~/projects/study/clj/ex/book_practicalli_clojure_webapps/p01/test/practicalli/p02_test.clj`

Run tests:

```bash
clj -A:test:runner
```

### Use associated destructuring for multiple arguments

Opsiyonel argümanlar iletmek:

```clj
(defn -main
  "Start a httpkit server with a specific port
  #' enables hot-reload of the handler function and anything that code calls"
  [& {:keys [ip port]
      :or   {ip   "0.0.0.0"
             port 8000}}]
	...
```

Kullanımı:

```clj
(-main :port 8888)
```

## Practicalli: Clojure Webapp routing and APIs with JSON id=g_12110

[Practicalli: Clojure Webapp routing and APIs with JSON](https://practicalli.github.io/blog/posts/webapp-routes-with-json/)

Edit `~/projects/study/clj/ex/book_practicalli_clojure_webapps/p01/src/practicalli/p07.clj`

		http://localhost:8000/player/jenny-jetpack
		http://localhost:8000/scores

## Practicalli: Consuming APIs from Clojure id=g_12111

[Practicalli: Consuming APIs from Clojure](https://practicalli.github.io/blog/posts/consuming-apis-with-clojure/)

Edit `~/projects/study/clj/ex/book_practicalli_clojure_webapps/p01/src/practicalli/p08.clj`

## Getting Started — seancorfield/next.jdbc id=g_12113

[Getting Started — seancorfield/next.jdbc 1.1.569](https://cljdoc.org/d/seancorfield/next.jdbc/1.1.569/doc/getting-started)

Edit `~/projects/study/clj/ex/book_practicalli_clojure_webapps/p01/src/practicalli/p09.clj`

## Clojure and SQLite – Yet Another Blog in Statistical Computing

[Clojure and SQLite – Yet Another Blog in Statistical Computing](https://statcompute.wordpress.com/2018/03/12/clojure-and-sqlite/)

Edit `~/projects/study/clj/ex/book_practicalli_clojure_webapps/p01/src/practicalli/p10.clj`

## Using java.jdbc | Clojure Documentation | Clojure Docs

[Using java.jdbc | Clojure Documentation | Clojure Docs](http://clojure-doc.org/articles/ecosystem/java_jdbc/home.html)

## Practicalli: Bank Account

[Bank Account TDD style · Practicalli Clojure](https://practicalli.github.io/clojure-staging/clojure-spec/projects/bank-account/)

Yaptığın değişiklikleri görmek için: ilgili fonksiyonları eval etmelisin yeniden repl içinde.

## seancorfield/usermanager-example: A little demo web app in Clojure, using Component, Ring, Compojure, Selmer (and a database)

[seancorfield/usermanager-example: A little demo web app in Clojure, using Component, Ring, Compojure, Selmer (and a database)](https://github.com/seancorfield/usermanager-example)

Check `/Users/mertnuhoglu/codes/clj/prj/web/usermanager-example/README.md`

