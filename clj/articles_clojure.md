--- 
title: "Articles Clojure"
date: 2020-12-20T14:53:28+03:00 
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

# REPL driven development with Clojure using Spacemacs, CIDER and clj-kondo - Practicalli id=g11830

[(112) REPL driven development with Clojure using Spacemacs, CIDER and clj-kondo - YouTube](https://www.youtube.com/watch?v=NDrpclY54E0&list=PLpr9V-R8ZxiCHMl2_dn1Fovcd34Oz45su&index=19)

```bash
clojure -X:project/new :template app :name practicalli/random-function
```

Rich comment:

```clj
_#
(
  ...
)
```

![Auto-complete and documentation](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_155117.jpg)

  | h h       | cider-doc                                         |
  | RET       | goto source code in cider-doc                     |

![Flycheck error message](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_162747.jpg)

  | SPC e L   | goto-flycheck-error-list                          |

![goto-flycheck-error-list](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_162831.jpg)

  | e f       | cider-eval-defun-at-point                         |

![cider-eval-defun-at-point](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_163219.jpg)

  | e ;       | cider-eval-defun-to-comment                       |

![cider-eval-defun-to-comment](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_163352.jpg)

Truncated eval result

```clj
(set! *print-length* 10)
```

![*print-length*](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_204343.jpg)

  | , d v l   | cider-inspect-last-result                         |
  | n p       | next/prev page                                    |
  | RET L     | go deep / go back                                 |

![Value inspector > cider-inspect-last-result](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_204431.jpg)

  | SPC k     | lisp                                              |
  | SPC k w   | wrap (with parens)                                |
  | , d v f   | cider-inspect-defun-at-point                      |

Edit window in `window-transient-state`

  | SPC w .   | window-transient-state                            |

![window-transient-state](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_210925.jpg)

Transient state içindeyken `SPC` ile yeni komutlar açılır:

![SPC inside window-transient-state](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_211253.jpg)

Transient state içindeyken `SPC z` ile zoom için ara transient state açılır:

  | SPC z x   | scale-font-transient-state                        |

![scale-font-transient-state](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_211406.jpg)

Toggle/switch between windows:

  | SPC w TAB | alternate-window                                  |

Toggle/switch between source file and its test file:

  | SPC p a   | projectile-toggle-between-implementation-and-test |

Testleri çalıştırmak için, önce test dosyasındaki tüm varları eval ettik:

  | e b       | cider-eval-buffer                                 |

Sonra da tüm testleri run ettik:

  | t a       | cider-test-run-all-tests                          |

p01: Test runları önce çalışmadı. Debug etmek için önce shell üzerinden çalıştırdık:

  | SPC '     | open shell                                        |

```bash
clojure -M:test/kaocha
```

`test/kaocha` varsayılan `~/deps.edn` içinde tanımlı.

Burada test run aldık. Demek ki, cider tarafıyla ilgili bir sorun var.

Sorunun sebebi: `deps.edn` dosyasında `test` dosyaları tanımlı, ancak bunlar `cider-jack-in` yapılırken cidera yüklenmiyor. Bunları eklemek için:

quit repl: `, m q`

Universal argument: `SPC u`

  | , m q     | sesman-quit                                       |
  | SPC u     | universal-argument                                |

Şimdi tekrar cider-jack-in yapınca, komutu edit edebiliriz.

![-M:test](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_232213.jpg)

Bu opsiyonu sürekli olarak eklemek için:

  | SPC p e   | projectile-edit-dir-locals                        |

Edit `/Users/mertnuhoglu/codes/clj/random-function/.dir-locals.el`

```clj
((clojure-mode . ((cider-clojure-cli-global-options . "-M:test"))))
```

Şimdi cider'ı yeniden başlattığında, otomatik bu argümanı set eder.

Check message buffer:

  | SPC b m   | Messages buffer                                   |
  
![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_233743.jpg)

Tekrar proje dosyana dönmek için: `SPC TAB`

  | SPC TAB   | Last buffer                                       |

Editing a function's arguments shows the function signature in mini buffer:

![Function signature in mini buffer](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201220_111329.jpg)

Hiding some of the stacktraces in error message buffer:

![Hiding stacktraces](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201220_112714.jpg)

Navigate to source code in stacktrace:

![Navigate to source code](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201220_112815.jpg)

Pretty print result:

  |e p f| cider-pprint-eval-defun-at-point|

![cider-pprint-eval-defun-at-point](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201220_114414.jpg)

Expand selected region: `SPC v v`

![er/expand-region](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201220_161445.jpg)

![er/expand-region](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201220_161519.jpg)

Switch to namespace: `,` in evil insert mode in REPL buffer: `ns`

[Namespaces · Clojure development with Spacemacs & Cider](https://practicalli.github.io/spacemacs/navigating-code/namespaces.html)

# Video: "Frameworkless Web Development in Clojure" by Andreas ‘Kungi’ Klein - YouTube id=g11910

["Frameworkless Web Development in Clojure" by Andreas ‘Kungi’ Klein - YouTube](https://www.youtube.com/watch?v=_LghX4oDWcY)

  TOC
    Web programming in Clojure
    HTTP Abstraction
    Routing
    HTML Templating
    Authentication
  Web programming in Clojure
    What I like
      No dominant web frameworks
      No framework dependent paradigms such as angular, react
      Just plain clj data structures
        Leave data alone
      Full power of clj is available
      It is web programming from bottom up
    What do we need normally?
      Typical framework offers:
        Web server / HTTP abstraction
        Middlewares
          Session
          CSRF Protection
        Request Routing
        HTML Templating
        Authentication
        Database abstraction
        Communication with the frontend: json, edn
  Clojure libraries
    HTTP Abstraction: Ring
    Middlewares: Ring
    Routing: Clout / Compojure
    HTML Templating: Hiccup / Enlive
    Authentication: Friend

## HTTP Abstraction: Ring

  Ring: HTTP Abstraction
    What is Ring
      Interface between web servers and web apps
      Similar: Rack, WSGI

How does Ring work?

![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210115_222712.jpg)

Ring / cycle of interactions between client and application:

    HTTP Request -> Adapter -> Ring Request
    Ring Request -> Middleware -> Ring Request
    Ring Request -> Handler (Application) -> Ring Response
    Ring Response -> Middleware -> Ring Response
    Ring Response -> Adapter -> HTTP Response
    HTTP Response -> Client -> HTTP Request

Middleware: Handles session, CSRF, Authentication

Ring example:

```clj
(efn handler [request]
  :status 200
  :headers {"Content-Type" "text/html"}
  :body "Hello")
```

### Request and Responses:

They are maps. How do they look like?

ring.mock library

It creates request and response maps easily

```bash
(mock/request :get "/hello")
; => request map
(handler (mock/request :get "/hello"))
; => response map
```

That is all. It is just converting one map to the other.

## Middleware

It is a higher order function that enhances a handler.

First arg is handler function.

Mw returns a handler function.

```clj
; example handler:
(defn wrap-content-type [handler content-type]
  (fn [request]
    (let [response (handler request)]
      (assoc-in response [:headers "Content-Type"] content-type))))
; usage:
(def app
  (-> handler
    (wrap-content-type "text/html")))
```

## Adapters

Connects ring to jetty.

- Converts servlet request to request map.
- Converts response map to servlet response.

Check: [~/codes/clj/ring/ring-jetty-adapter/src/ring/adapter/jetty.clj](https://github.com/ring-clojure/ring)

```clj
(defn- ^AbstractHandler proxy-handler [handler]
  (proxy [AbstractHandler] []
    (handle [_ ^Request base-request ^HttpServletRequest request response]
      (when-not (= (.getDispatcherType request) DispatcherType/ERROR)
        (let [request-map  (servlet/build-request-map request)
              response-map (handler request-map)]
          (servlet/update-servlet-response response response-map)
          (.setHandled base-request true))))))
```

## Routing / HTTP Matching: Clout and Compojure

Check: [~/codes/clj/clout/README.md](https://github.com/weavejester/clout)

Same syntax as Rails.

```clj
(clout/route-matches
 "/article/:title"
 (mock/request :get "/article/clojure"))

=> {:title "clojure"}
```

Input: Request
Output: Map

Compojure uses it internally.

It puts macro magic around it.

Check: [~/codes/clj/compojure/README.md](https://github.com/weavejester/compojure)

```clj
(defroutes app
  (GET "/" [] "<h1>Hello World</h1>")
  (route/not-found "<h1>Page not found</h1>"))
```

It combines clout routes with ring handler. `app` is a handler function.

## Templating: Hiccup and Enlive

### Hiccup:

Check: [~/codes/clj/hiccup/README.md](https://github.com/weavejester/hiccup)

Hiccup: Literal translation from HTML to clojure vectors and maps.

```clj
user=> (html [:span {:class "foo"} "bar"])
"<span class=\"foo\">bar</span>"
```

Css selector shortcuts:

```clojure
user=> (html [:div#foo.bar.baz "bang"])
"<div id=\"foo\" class=\"bar baz\">bang</div>"
```

### Enlive:

Input: HTML files

Check: [~/codes/clj/enlive/README.md](https://github.com/cgrand/enlive)

Content of `templates/application.html`:

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>This is a title placeholder</title>
  </head>
  <body>
  </body>
</html>
```

```clj
(html/deftemplate main-template "templates/application.html"
  []
  [:head :title] (html/content "Enlive starter kit"))
```

Note: No placeholder syntax in html file.

## Authentication: Friend

Check: [~/codes/clj/friend/README.md](https://github.com/cemerick/friend)

```clj
(def ring-app ; ... assemble routes however you like ...
  )

(def secured-app
  (-> ring-app
    (friend/authenticate {:credential-fn (partial creds/bcrypt-credential-fn users)
                          :workflows [(workflows/interactive-form)]})
    ; ...required Ring middlewares ...
    ))
```

`authenticate` function works as a middleware around `ring-app`.

# Frameworkles Web Development

Benefits:

- Small libraries can be composed easily
- Communication between libraries is through plain old clojure data structures
- You can eliminate some libraries when there is no need

# Video: "Debugging Clojure Code" by Daniel Škarda - YouTube id=g11912

["Debugging Clojure Code" by Daniel Škarda - YouTube](https://www.youtube.com/watch?v=5oEfyeyyguY)

![Explain me this value:](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210118_095230.jpg)

![Tagging data](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210118_095914.jpg)

![Logs as data](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210118_095948.jpg)

- Query logs using clojure:

```bash
(->> (filter #(= (:id %) :foobar)
     (filter :exception @logs)
     (map :args)))
```

- Replay logs without executing

- Compute a difference between executions

# Article: Inside Clojure's Collection Model – Inside Clojure - Alex Miller id=g11990

[Inside Clojure's Collection Model – Inside Clojure](https://insideclojure.org/2016/03/16/collections/)

    Traits and Predicates
      traits: would be defined by clj protocols, but instead defined by interfaces
      Key trait classes in clojure.lang:
        Counted - countable collections
          count()
        Indexed - extends Counted, allows index-based lookup
          nth(int i)
          nth(int i, Object notFound)
        Sequential - marker interface for sequential collections
        Associative (extends ILookup)
          containsKey(Object key)
          entryAt(Object key)
          assoc(Object key, Object val)
          via parent ILookup:
            valAt(Object key)
            valAt(Object key, Object notFound)
        Sorted - marker interface for sorted
        Seqable - for collections that can produce a sequence
          seq()
        Reversible - for collections that can produce a reversed sequence
          rseq()
      Predicates to check type:
        counted? - checks whether instance of Counted
        indexed? - checks whether instance of Indexed
        sequential? - checks whether instance of Sequential
        associative? - checks whether instance of Associative
        sorted? - checks whether instance of Sorted
        reversible? - checks whether instance of Reversible
      Collections
        Colections are implemented in terms of interfaces
        IPersistentVector, which extends Associative, Sequential, Reversible, and Indexed
      Sequences
        Sequences represent a logical immutable collection
        They can be obtained
          from collections
          from a function on demand
          from other sources (strings, arrays, iterators, etc)
        Defined by interfaces:
          ISeq - checked via `seq?` predicate
          Seqable - something that can produce a sequence
        Here’s the twist: 
          ISeq extends IPersistentCollection. 
          So sequences are also treated as (virtual) collections, but not treated as IPersistentLists. 
          This can be confusing because both sequences and lists print with parentheses as if they are lists.

# Video: Clojure The Art of Abstraction - Alex Miller - YouTube id=g11989

[Clojure The Art of Abstraction - Alex Miller - YouTube](https://www.youtube.com/watch?v=bZ23d9DkKek)

    seqs
      seq protocol
        not null
        first
        rest
      Everything is a seq
        List, Set, Vector, Map
        String
        Java Collections
        Tree
        ...
      Lazy seqs
        infinite sequences are ok
    Collection traits
      /Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210214_181904.jpg
    Lists
      '(1 2 3)
      (cons 0 a)
      first
      rest
      count
      nth
    Vectors
      [1 2 3]
      (conj v 4)
      nth
      get
    Maps
      (def m {:a 1})
      (def n (assoc m :c 3))
      (keys n)
      (vals n)
      (get m :a)
      (m :a)
      (:a m)
    Sequence functions
      range
      filter
      reverse
      partition
      map
      reduce
    Functional Kingdom - Steve Yegge
      Javaland:
        Verbs are owned by Nouns.
      FP:
        Nouns and Verbs are equal citizens.
    Data types
      Multimethods
        Dispatching by class
        Dispatching by attribute value (keyword value)
        You can define your hierarchy
      Protocols 
        (defrecord Programmer [language])
        (defprotocol Teacher
          (teach [p])
          (read [p]))
    State
      Software transactional memory
    Macro
      Homoiconic:
        Representation of clojure code is clojure data structures
        You can use all sequence functions on your code before you evaluate them
    Abstractions
      functions: name and manupalet code
      collections: trait-based
      seq: lists of values
      records: data types
      multimethods, protocols: polymorphism
      atoms, refs, agents: state
      macros: syntax, order of evaluation
      namespaces: modularity
      metadata: out-of-band information passing

# Video: What Sucks about Clojure and Why You'll Love It Anyway Chas Emerick - YouTube id=g11991

[What Sucks about Clojure and Why You'll Love It Anyway Chas Emerick - YouTube](https://www.youtube.com/watch?v=wXgdxCFDjD0)

    namespaces
      ns are tough
        clj ns are very strong
        however very complex to learn
        ns load-file require refer refer-clojure use import
      (require ...) vs (:require ...)
      there is no package wildcard import
        no (:import com.x.*)
      (def f ns/g) vs (def f #'ns/g)
        essentially equivalent
        but subtle differences
      (import foo.AType) fails if 'foo hasn't beeen loaded
      no way to build ns incrementally
      all ns operations are risky and side-effecting
        if some transitive dep fails, then you have half-loaded ns
        no way to do it transactional
    declare
      (defn main [args] (helper args))
      (defn helper ..)
      error: helper is not defined yet
      (declare helper) is needed before main
      no forward references
      each top-level form is a compilation unit
    dynamic scope
      (with-foo {..} (f a b c))
      (f {..} a b c)
      every dynamic var is an implicit arg to every fn
    STM
      Using STM effectively is hard
        But it appears easy
        they are overused
      better to be stateless
    Read clojure's source
    JVM
      slow startup time
      library myth
        most libraries not useful for clojure wrapping
          lots of side-effects
          need to subclass an abstract class
      fast
      everybody has it
    AOT Compilation
      ahead-of-time compilation
      it bloats artifacts
      compilation is transitive through all deps
      causes backward- and forward compatibility risk
        even if method signatures don't change
    Parentheses
      macros don't compose well
      macros are too easy
        => too many macros
      non-lispers: no need to write in ASTs in order to modify them
        groovy transforms, scala compiler plugins
        homoiconicity doesn't simplify many aspects of metaprogramming
    Over-enthusiasm
      Using Clojure everywhere
        sexps aren't always superior
          no binary representation
    Big ball of mud
      error stacktrace sometimes lagging
    Learning curve
      How do you discover clj libraries?
      What are the resources and tools to do this effectively?
      Hurdles:
        installation
        documentation
        tools
        distribution
    Bus factors
      is 1 = Rich Hickey

# Article: In Clojure, records are wacky maps | Yehonathan Sharvit id=g12116

[In Clojure, records are wacky maps | Yehonathan Sharvit](https://blog.klipse.tech/clojurescript/2016/04/25/records-wacky-maps.html)
      
Code: `~/projects/study/clj/ex/study_clojure/ex06/src/record.clj`

3 equivalent ways to create a record: `A.`, `->A`, `map->A`

```clj
(defrecord A [x y])
(def a (A. 1 2))
(def aa (map->A {:x 1 :y 2}))
(def aaa (->A 1 2))
[a aa aaa]
```

Differences from maps:

- Not callable as a function
- dissoc: returns a map instead of a record

# Article: Clojurescript defprotocol’s secret | Yehonathan Sharvit

[Clojurescript defprotocol’s secret | Yehonathan Sharvit](https://blog.klipse.tech/clojurescript/2016/04/09/clojurescript-protocols-secret.html)

Code: `~/projects/study/clj/ex/study_clojure/ex06/src/protocol.clj`

# Article: The power and danger of deftype in clojure and clojurescript | Yehonathan Sharvit id=g12117

[The power and danger of deftype in clojure and clojurescript | Yehonathan Sharvit](https://blog.klipse.tech/clojurescript/2016/04/26/deftype-explained.html)

Code: `~/projects/study/clj/ex/study_clojure/ex06/src/types.clj`

persistent data structures implemented in: 

- clj: java
- cljs: clojure

record: collection of fields

type: simpler than record. just constructor and `getBasis`

2 ways to create a type: `A.` constructor or `->A` factory function.


