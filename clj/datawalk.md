--- 
title: "Study datawalk"
date: 2021-01-23T22:53:51+03:00 
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

# datawalk
 
[eggsyntax/datawalk: Interactively explore complex data structures at the REPL with minimum keystrokes](https://github.com/eggsyntax/datawalk)

```bash
clojure -X:project/new :name mertnuhoglu/datawalk-01
clojure -M:project/find-deps -F:merge datawalk
clojure -M:project/find-deps datawalk
```

Check `~/projects/study/clj/ex/datawalk/datawalk-01/deps.edn`

Usage:

```bash
cp deps.edn resources
clojure -M::datawalk:repl/rebel
```

```clj
(require '[datawalk.core :as dc])
(def example-data (read-string (slurp "deps.edn")))
example-data
(dc/repl example-data)

```

Interactive exploration:

```bash
Exploring interactively.
(00. :paths  : [src resources]
 01. :deps   : #:org.clojure{clojure #:mvn{:version 1.10.1}}
 02. :aliases: {:test {:extra-paths [test], :extra-deps #:org.clojure{test.check #:mvn{:version 1.0.0}}}, :runner {:extra
)
[datawalk] > 
2 
2
(00. :test    : {:extra-paths [test], :extra-deps #:org.clojure{test.check #:mvn{:version 1.0.0}}}
 01. :runner  : {:extra-deps #:com.cognitect{test-runner {:git/url https://github.com/cognitect-labs/test-runner, :sha b6
 02. :datawalk: {:extra-deps #:datawalk{datawalk #:mvn{:version 0.1.12}}}
 03. :jar     : {:extra-deps #:seancorfield{depstar #:mvn{:version 1.1.126}}, :main-opts [-m hf.depstar.jar datawalk-01.j
 04. :install : {:extra-deps #:slipset{deps-deploy #:mvn{:version 0.1.1}}, :main-opts [-m deps-deploy.deps-deploy install
 05. :deploy  : {:extra-deps #:slipset{deps-deploy #:mvn{:version 0.1.1}}, :main-opts [-m deps-deploy.deps-deploy deploy 
)
[datawalk] > 
1
1
(00. :extra-deps: #:com.cognitect{test-runner {:git
/url https://github.com/cognitect-labs/test-runner, :sha b6b3193fcc426
 01. :main-opts : [-m cognitect.test-runner -d test]
)
[datawalk] > 
1
1
(00. "-m"
 01. "cognitect.test-runner"
 02. "-d"
 03. "test"
)
[datawalk] > 
3
3
 00. "test"
[datawalk] > 
s
s
Saving current data to saved-data map
 00. "test"
[datawalk] > 
v       
v
Saving the path to current to saved-data map
 00. "test"
[datawalk] > 
q
q
{1 "test", 2 [:aliases :runner :main-opts 3]}
```

