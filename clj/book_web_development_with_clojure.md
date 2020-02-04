---
title: "Notes from the Book Web Development with Clojure"
date: 2020-01-21T11:16:23+03:00 
draft: false
description: ""
tags:
categories: clojurescript
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

# Project: mount

https://github.com/tolitius/mount

Managing app state

## Why?

Clojure REPL needs to keep application state. `mount` makes the application state reloadable.

Alternative to `component`

## Differences from "component"

https://github.com/tolitius/mount/blob/master/doc/differences-from-component.md#differences-from-component

Rich Hickey:

> Lisps were designed to receive a set of interactions/forms via a REPL, not to compile files/modules/programs etc. This means you can build up a Lisp program interactively in very small pieces, switching between namespaces as you go, etc. It is a very valuable part of the Lisp programming experience. It implies that you can stream fragments of Lisp programs as small as a single form over sockets, and have them be compiled and evaluated as they arrive. It implies that you can define a macro and immediately have the compiler incorporate it in the compilation of the next form, or evaluate some small section of an otherwise broken file

Component requires you to build entire app around its model. Everything is made of components.

Mount lets you create a `defstate` when you need it only.

Component: Everything feels like an object. Most of the functions are methods.

## How

``` bash
(require '[mount.core :refer [defstate]])
``` 

Creating state:

``` bash
(defstate conn :start (create-conn))
``` 

This is a top level being. Use it anywhere:

``` bash
(require '[app.nyse :refer [conn]])
``` 

Ex: We need a connection in `app`

``` bash
(ns app
  (:require [app.nyse :refer [conn]]))
``` 

# Project: component

https://github.com/stuartsierra/component

Alternative to `mount`

It lets software components to have runtime state.

# Book: [Dmitri_Sotnikov]_Web_Development_with_Clojure.pdf

## Introduction

### Why Make Web Apps in Clojure?

Clojure web stack is based on Ring and Compojure libraries.

Ring: base HTTP abstraction library

Compojure: routing on top of it

Why clojure over other options?

Performance and infrastructure: delegated to JVM

Expressive power

Boilerplate in web applications. Django, Rails, Spring address that. 

But there is an inherent cost. You have to memorize what effects any action has.

The opaqueness makes code more difficult to reason about.

Instead of using frameworks, use a number of powerful libraries. We will avoid writing boilerplate while keep the code clear.

This book will give you a solid understanding of the web stack. 

## Chapter 01. Getting Your Feet Wet

### Set Up Environment

Leiningen is similar to Maven. It uses maven repos also Clojars repo.

It downloads any dependencies automatically.

``` bash
cd ~/projects/study/clj/ex/book_web_development_with_clojure/ex01
lein new myapp
``` 

``` bash
cd myapp
``` 

Check `~/projects/study/clj/ex/book_web_development_with_clojure/ex01/myapp/src/myapp/core.clj`

Edit `~/projects/study/clj/ex/book_web_development_with_clojure/ex01/myapp/project.clj`

``` bash
:main myapp.core/foo
``` 

This is the main function. Now we can run app:

``` bash
lein run Must
  ##> Must Hello, World!
``` 

### Build Your First Web App

#### Creating an Application From a Template

Luminus template provides a good base.

By default, Leiningen uses the latest version in the Clojars repo.

To use the same version of template, add this to `~/.lein/profiles.clj`

``` bash
{:user {:plugins [[luminus/lein-template "2.9.9.2"]]}}
``` 

``` bash
cd ~/projects/study/clj/ex/book_web_development_with_clojure/ex01
lein new luminus guestbook +h2
``` 

luminus: template name

guestbook: project name

+h2: have an instance of H2 database 

``` bash
cd guestbook
lein run
``` 

Open localhost:3000

##### Error

``` bash
lein run
``` 

``` bash
Retrieving mvxcvi/arrangement/1.0.0/arrangement-1.0.0.jar from clojars
Exception in thread "main" java.lang.ExceptionInInitializerError
        at java.base/java.lang.J9VMInternals.ensureError(J9VMInternals.java:193)
        at java.base/java.lang.J9VMInternals.recordInitializationFailure(J9VMInternals.java:182)
        at clojure.main.<clinit>(main.java:20)
Caused by: java.lang.IllegalArgumentException: Must hint overloaded method: toArray, compiling:(flatland/ordered/set.clj:19:1)
        at clojure.lang.Compiler.analyzeSeq(Compiler.java:6875)
``` 

opt01:

https://github.com/luminus-framework/luminus/issues/224

``` bash
cd ~/codes/clojure
git clone https://github.com/luminus-framework/luminus
``` 

opt02: 

Edit `~/.lein/profiles.clj`

``` bash
{:user {:plugins [[luminus/lein-template "3.56"]]}}
``` 

``` bash
lein run
``` 

opt03:

In addition to opt02

``` bash
rm -rf guestbook
lein new luminus guestbook +h2
cd guestbook
lein run
``` 

This fixed the problem.

---

#### Refine Your App

##### Managing Database Migrations

We will run `~/projects/study/clj/ex/book_web_development_with_clojure/ex01/guestbook/resources/migrations/20200121114446-add-users-table.up.sql`

Edit it. Stop server. Run:

``` bash
lein run migrate
  ##> 2020-01-21 12:33:47,804 [main] INFO  migratus.core - Starting migrations
  ##> 2020-01-21 12:33:48,091 [main] INFO  migratus.database - creating migration table 'schema_migrations'
  ##> 2020-01-21 12:33:48,128 [main] DEBUG migratus.migrations - Looking for migrations in #object[java.io.File 0xd11d6f9b /Users/mertnuhoglu/projects/study/clj/ex/book_web_development_with_clojure/ex01/guestbook/resources/migrations]
  ##> 2020-01-21 12:33:48,144 [main] INFO  migratus.core - Running up for [20200121114446]
  ##> 2020-01-21 12:33:48,145 [main] INFO  migratus.core - Up 20200121114446-add-users-table
  ##> 2020-01-21 12:33:48,156 [main] DEBUG migratus.migration.sql - found 1 up migrations
``` 

##### Querying to the Database

Check `~/projects/study/clj/ex/book_web_development_with_clojure/ex01/guestbook/resources/sql/queries.sql`

``` bash
--name:save-message!
-- creates a new message
INSERT INTO guestbook
(name, message, timestamp)
VALUES (:name, :message, :timestamp)

--name:get-messages
-- selects all available messages 
SELECT * from guestbook
``` 

`!` indicates that it mutates data.

opt01: Test it in book source code

``` bash
cd /Users/mertnuhoglu/codes/clojure/book_web_development_with_clojure/guestbook
lein run migrate
lein run
``` 

###### Error:

``` bash
lein run
``` 

``` bash
Exception in thread "main" java.lang.ExceptionInInitializerError
        at java.base/java.lang.J9VMInternals.ensureError(J9VMInternals.java:193)
        at java.base/java.lang.J9VMInternals.recordInitializationFailure(J9VMInternals.java:182)
        at clojure.main.<clinit>(main.java:20)
Caused by: java.lang.IllegalArgumentException: Must hint overloaded method: toArray, compiling:(flatland/ordered/set.clj:19:1)
``` 

opt01:

https://github.com/luminus-framework/luminus/issues/224

Edit `/Users/mertnuhoglu/codes/clojure/book_web_development_with_clojure/guestbook/project.clj`

``` bash
                 [compojure "1.5.0"]
								 ->
                 [compojure "1.6.1"]
``` 

``` bash
Retrieving instaparse/instaparse/1.4.8/instaparse-1.4.8.jar from clojars
Exception in thread "main" java.lang.ExceptionInInitializerError
        at java.base/java.lang.J9VMInternals.ensureError(J9VMInternals.java:193)
        at java.base/java.lang.J9VMInternals.recordInitializationFailure(J9VMInternals.java:182)
        at clojure.main.<clinit>(main.java:20)
Caused by: java.lang.IllegalArgumentException: Must hint overloaded method: toArray, compiling:(flatland/ordered/set.clj:19:1)
        at clojure.lang.Compiler.analyzeSeq(Compiler.java:6875)
``` 

opt02:

https://github.com/clj-commons/ordered/pull/37

opt03: revert back to java 10 or 8

https://stackoverflow.com/questions/21964709/how-to-set-or-change-the-default-java-jdk-version-on-os-x

``` bash
/usr/libexec/java_home -V
  ##> Matching Java Virtual Machines (4):
  ##>     11.0.5, x86_64:     "AdoptOpenJDK (OpenJ9) 11"  /Library/Java/JavaVirtualMachines/adoptopenjdk-11-openj9.jdk/Contents/Home
  ##>     1.8.0_91, x86_64:   "Java SE 8" /Library/Java/JavaVirtualMachines/jdk1.8.0_91.jdk/Contents/Home
  ##>     1.8.0_77, x86_64:   "Java SE 8" /Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home
  ##>     1.8.0_60, x86_64:   "Java SE 8" /Library/Java/JavaVirtualMachines/jdk1.8.0_60.jdk/Contents/Home
  ##> 
  ##> /Library/Java/JavaVirtualMachines/adoptopenjdk-11-openj9.jdk/Contents/Home
``` 

``` bash
  #export JAVA_HOME=`/usr/libexec/java_home -v 11`
export JAVA_HOME=`/usr/libexec/java_home -v 1.8.0_60`
``` 

This fixed the problem.

---

###### How to connect to repl on port 7000

https://stackoverflow.com/questions/52459671/clojure-how-to-connect-to-running-repl-process-remotely

``` bash
lein repl :connect localhost:7000
``` 

---

##### Querying to the Database

``` bash
;;switch to the namespace 
(use 'guestbook.db.core)

;;check if we have any existing data 
(get-messages)
;;output: ()

;;create a test message 
(save-message! {:name "Bob"
	:message "Hello World" 
	:timestamp (java.util.Date.)})
;;output 1

;;check that the message is saved correctly 
(get-messages)
  ##> ({:timestamp #inst "2015-01-18T16:22:10.010000000-00:00"
  ##> 	:message "Hello World" :name "Bob"
  ##> 	:id 1})
``` 

###### How it works

It uses `conman` for connection management. 

`conman` uses `hugsql` to generate sql.

Check https://github.com/luminus-framework/conman

`resources/sql/queries.sql` contains:

``` bash
-- :name create-user! :! :n
-- :doc creates a new user record
INSERT INTO users
(id, first_name, last_name, email, pass)
VALUES (:id, :first_name, :last_name, :email, :pass)

-- :name get-user :? :1
-- :doc retrieve a user given the id.
SELECT * FROM users
WHERE id = :id

-- :name get-all-users :? :*
-- :doc retrieve all users.
SELECT * FROM users
``` 

Then, we create a connection using this `sql` file.

``` bash
(ns myapp.db
  (:require [mount.core :refer [defstate]]
            [conman.core :as conman]))

(def pool-spec
  {:jdbc-url "jdbc:postgresql://localhost/myapp?user=user&password=pass"})

(defstate ^:dynamic *db*
          :start (conman/connect! pool-spec)
          :stop (conman/disconnect! *db*))

(conman/bind-connection *db* "sql/queries.sql")
``` 

`*db*` is a dynamic variable. It keeps the connection. 

`bind-connection` is a macro. It accepts a connection var and a string (name of sql file).

`mount` manages app state


##### Creating Tests

Check `~/codes/clojure/book_web_development_with_clojure/guestbook/test/clj/guestbook/test/db/core.clj`

``` bash
lein test
``` 

##### Defining HTTP Routes

Check `~/codes/clojure/book_web_development_with_clojure/guestbook/src/clj/guestbook/routes/home.clj`

``` bash
(defroutes home-routes
  (GET "/" [] (home-page))
  (POST "/message" request (save-message! request))
  (GET "/about" [] (about-page)))
``` 

`/` routes to `home-page`. It renders `home.html`

``` bash
(defn home-page []
  (layout/render
    "home.html"
    {:messages (db/get-messages)}))
``` 

That passes a map of parameters `:messages`

They are used inside `~/codes/clojure/book_web_development_with_clojure/guestbook/resources/templates/home.html`

``` bash
            {% for item in messages %}
            <li>
                <time>{{item.timestamp|date:"yyyy-MM-dd HH:mm"}}</time>
                <p>{{item.message}}</p>
                <p> - {{item.name}}</p>
            </li>
            {% endfor %}
``` 

Now, take `/message` route.

It calls `save-message!`

``` bash
  (POST "/message" request (save-message! request))
``` 

It passes `request` to `save-message!`

``` bash
(defn save-message! [{:keys [params]}]
  (db/save-message!
   (assoc params :timestamp (java.util.Date.)))
  (response/found "/"))
``` 

The function takes `params` key from the `request`. This key contains a map of parameters.

The keys of the map are `name` and `message` in the html form:

``` bash
                <input class="form-control"
                       type="text"
                       name="name"
                       value="" />
            </p>
            <p>
                Message:
                <textarea class="form-control"
                          rows="4"
                          cols="50"
                          name="message"></textarea>
``` 

##### Validating Input

`Bounder` library handles input validation.

It provides two functions: `bouncer.core/validate` and `bouncer.core/valid?`

They accept a map containing the paramaters and validators.

Check `~/codes/clojure/book_web_development_with_clojure/guestbook-validation/src/clj/guestbook/routes/home.clj`

``` bash
(defn validate-message [params]
  (first
   (b/validate
    params
    :name v/required
    :message [v/required [v/min-count 10]])))
``` 

##### Running Standalone

Package application for deployment

``` bash
lein uberjar
``` 

``` bash
export DATABASE_URL="jdbc:h2:./guestbook_dev.db" 
java -jar target/guestbook.jar
``` 

## Chapter 02: Clojure Web Stack

Rails/Django: monolithic framework. It contains everything you need.

Clojure: choose libraries and put them together

Leining templates: generate boilerplate 

This book uses Luminus template. It already has chose some libraries. 

Core: Ring/Compojure stack.

Ring: API for handling HTTP requests

Compojure: routing to bind request-handler functions to specific URIs.

### Route Requests with Ring

Ring: abstracts HTTP into an API. It is similar to WSGI.

Common way: Run clojure applications standalone using an embedded HTTP server (Immutant, Jetty)

### Creating a Web Server

``` bash
lein new ring-app
``` 

Check `~/codes/clojure/book_web_development_with_clojure/ring-app/project.clj`

``` bash
lein run
``` 

``` bash
  :main ring-app.core)
``` 

Check `~/codes/clojure/book_web_development_with_clojure/ring-app/src/ring_app/core.clj`

``` bash
(defn -main []
  (jetty/run-jetty
      (-> #'handler wrap-nocache wrap-reload wrap-formats)
      {:port 3000
       :join? false}))
``` 

### Adding Functionality with Middleware

Middleware wraps handlers to modify the processing of the request. 

``` bash
(defn wrap-nocache [handler]
  (fn [request]
    (-> request
        handler
        (assoc-in [:headers "Pragma"] "no-cache"))))
``` 

The wrapper accepts the handler function and returns a new handler. 

### Extend Ring

``` bash
lein repl
``` 

``` bash
(response/continue)
  ##> {:status 100, :headers {}, :body ""}
(response/ok "<html><body><h1>hello world</h1></body></html>")
  ##> {:status 200, :headers {}, :body "<html><body><h1>hello world</h1></body></html>"}
``` 

`wrap-restful-format` is a middleware wrapper. 

``` bash
(defn wrap-formats [handler]
  (wrap-restful-format
    handler
    {:formats [:json-kw :transit-json :transit-msgpack]}))
``` 

This lets requests containing JSON.

``` bash
curl -H "Content-Type: application/json" -X POST -d '{"id":1}' localhost:3000/json {"result":1}
  ##> {"result":1}
``` 

### Define the Routes with Compojure

Compojure is built on top of Ring.

It lets to bind handlers with a URL and HTTP method.

``` bash
(defn response-handler [request]
  (response/ok
    (str "<html><body> your IP is: "
         (:remote-addr request)
         "</body></html>")))

(def handler 
	(compojure/routes
		(compojure/GET "/" request response-handler)))
``` 

Parsing URI segments:

``` bash
(compojure/defroutes handler
 (compojure/GET "/" request response-handler)
 (compojure/GET "/:id" [id] (str "<p>the id is: " id "</p>" ))
 (compojure/POST "/json" [id] (response/ok {:result id})))
``` 

http://localhost:3000/ali will print `the id is: ali`

### Accessing Request Parameters

Print all request parameters:

``` bash
(GET "/foo" request (interpose ", " (keys request)))
  ##> :ssl-client-cert, :remote-addr, :scheme ...
``` 

Extract form parameters:

``` bash
(GET "/:foo" {{value :name} :params} 
	(str "The value of name is " value))
``` 

#### Logs: form parameters

``` bash
curl localhost:3000/15
curl -d '{"id":1}' localhost:3000/params/test
curl localhost:3000/params/test
curl localhost:3000/params/15
curl localhost:3000/
http localhost:3000/
  ##> <html><body> your IP is: 0:0:0:0:0:0:0:1</body></html>
http localhost:3000/params/15
  ##>     "foo": 1
``` 

``` bash
 (compojure/GET "/params/:foo" {{value :name} :params} 
   (response/ok {:foo 1})))
``` 

##### curl yanıtı hemen görünüp kayboluyor

``` bash
curl localhost:3000/
``` 

Yanıt görünüyor, ama hemen sonra temizleniyor. 

opt03: curl alternatiflerini dene

``` bash
wget localhost:3000/
http localhost:3000
  ##> HTTP/1.1 200 OK
  ##> Content-Length: 54
  ##> Date: Tue, 04 Feb 2020 08:28:02 GMT
  ##> Pragma: no-cache
  ##> Server: Jetty(9.2.10.v20150310)
  ##> 
  ##> <html><body> your IP is: 0:0:0:0:0:0:0:1</body></html>
``` 

En iyisi httpie kullanalım.

#### Denemeler yap GET parameters ile

opt01:

``` bash
 (compojure/GET "/params/:foo" {{value :name} :params} 
   (response/ok {:foo 1})))
``` 

``` bash
http localhost:3000/params/15
  ##>     "foo": 1
``` 

opt02:

``` bash
 (compojure/GET "/params2/:foo" {{value :name} :params} 
   (response/ok {:foo value}))
``` 

``` bash
http -f localhost:3000/params2 name='ali' 
``` 

Yanıt yok.

opt03: Tüm parametreleri bastır

``` bash
(GET "/foo" request (interpose ", " (keys request)))
``` 

``` bash
http -f localhost:3000/foo name='ali' 
``` 

Yanıt yok.

opt04: 

``` bash
(GET "/foo" request (interpose ", " (keys request)))
``` 

``` bash
http localhost:3000/foo
  ##> <p>the id2 is: foo</p>
``` 

opt05: sıralamayı değiştir

``` bash
 (compojure/GET "/foo" request (interpose ", " (keys request)))
 (compojure/GET "/:id" [id] (str "<p>the id2 is: " id "</p>" ))
``` 

``` bash
http localhost:3000/foo
  ##>     "ssl-client-cert",
  ##>     ", ",
  ##>     "protocol",
  ##>     ", ",
``` 

Tamam bu şekilde print etti.

``` bash
http localhost:3000/printall/foo 
  ##> all params
http -f localhost:3000/printall/foo name='ali' 
	##> empty
http localhost:3000/printall/foo name='ali' 
	##> empty
``` 

Yine boş dönüyor.

opt06: POST kabul et

``` bash
 (compojure/POST "/printall/foo" request (interpose ", " (keys request)))
``` 

``` bash
http -f localhost:3000/printall/foo name='ali' 
  ##> all params
``` 

``` bash
 (compojure/POST "/params2/:foo" {{value :name} :params} 
   (response/ok {:foo value}))
``` 

``` bash
http -f localhost:3000/params2/test name='ali' 
  ##>     "foo": null
``` 

opt07: params mapini print et

opt07.1: 

``` bash
 (compojure/POST "/params3/:foo" request
   (clojure.pprint/pprint request))
``` 

``` bash
http -f localhost:3000/params3/test name='ali' 
	##> empty
``` 

opt07.2: önceki örnek üzerinden devam et

``` bash
 (compojure/POST "/params4/:foo" request
   (interpose ", " (vals request)))
``` 

``` bash
http -f localhost:3000/params4/test name='ali' 
  ##> <p>Problem accessing /params4/test. Reason:
  ##> <pre>    Cannot JSON encode object of class: class org.eclipse.jetty.server.HttpInputOverHTTP: HttpInputOverHTTP@6467588c</pre></p>
``` 

`params3` ve `params4` arasındaki fark ne?

Muhtemelen `vals` yapınca dönen valuelardan bir tanesi en azından string değil. O yüzden encode edemiyor. 

opt07.3: objeleri nasıl print ederiz? pprint ile dene

opt07.3.1:

``` bash
 (compojure/POST "/params5/:foo" request
   (clojure.pprint/pprint (keys request)))
``` 

``` bash
http -f localhost:3000/params5/test name='ali' 
	##> empty
``` 

opt07.3.2: tüm request objesini print et

``` bash
 (compojure/POST "/params6/:foo" request
   (clojure.pprint/pprint request))
``` 

``` bash
http -f localhost:3000/params6/test name='ali' 
	##> empty
``` 

Çıktıyı clojure serverda veriyor. Client tarafında değil.

``` bash
{:ssl-client-cert nil,
 :protocol "HTTP/1.1",
 :remote-addr "0:0:0:0:0:0:0:1",
 :params {:foo "test"},
``` 

opt07.3.4: print etmek yerine mapi stringe çevir

opt07.3.4.1: serialize 

``` bash
(defn serialize [m sep] (apply str (concat (interpose sep (vals m)) ["\n"])))
...
 (compojure/POST "/params7/:foo" request
   (serialize request ","))
``` 

``` bash
http -f localhost:3000/params7/test name='ali' 
  ##> ,HTTP/1.1,0:0:0:0:0:0:0:1,{:foo "test"},{:foo "test"},{"accept" "*/*", "user-agent" "HTTPie/0.9.9", "connection" "keep-alive", "host" "localhost:3000", "accept-encoding" ...
``` 

opt07.3.4.2: str

``` bash
 (compojure/POST "/params8/:foo" request
   (str request))
``` 

``` bash
http -f localhost:3000/params8/test name='ali' 
  ##> {:ssl-client-cert nil, :protocol "HTTP/1.1", :remote-addr "0:0:0:0:0:0:0:1", :params {:foo "test"}, :route-params {:foo "test"}, :headers {"accept" "*/*", "user-agent" "HTTPie/0.9.9", "connection" "keep-alive", "host" "localhost:3000", "accept-encoding" "gzip, deflate", "content-length" "8", "content-type" "application/x-www-form-urlencoded; charset=UTF-8"}, :server-port 3000, :content-length 8, :compojure/route [:post "/params8/:foo"], :content-type "application/x-www-form-urlencoded; charset=UTF-8", :character-encoding "UTF-8", :uri "/params8/test", :server-name "localhost", :query-string nil, :body #object[org.eclipse.jetty.server.HttpInputOverHTTP 0x324cef23 "HttpInputOverHTTP@324cef23"], :scheme :http, :request-method :post}
``` 

opt07.3.4.3: sadece params mapini çek

``` bash
 (compojure/POST "/params9/:foo" request
   (str (:params request)))
``` 

``` bash
http -f localhost:3000/params9/test name='ali' 
  ##> {:foo "test"}
``` 

Güzel, ama form parametreleri nerede?

opt08:

``` bash
 (compojure/POST "/params10/:foo" [name message] (println name message))
``` 

``` bash
http -f localhost:3000/params10/test name='ali' 
	##> empty
``` 

opt09:

``` bash
 (compojure/POST "/params11/:foo" [foo message] (println foo message))
``` 

``` bash
http -f localhost:3000/params11/test name='ali' 
	##> empty
``` 

opt10:

``` bash
 (compojure/POST "/params12/:foo" {{value :name} :params} 
   (str "The value of name is " value))
``` 

``` bash
http -f localhost:3000/params12/test name='ali' 
  ##> The value of name is
http -f localhost:3000/params12/test foo='ali' 
  ##> The value of name is
``` 

opt11:

``` bash
 (compojure/POST "/params13/:foo" {{value :foo} :params} 
   (str "The value of name is " value))
``` 

``` bash
http -f localhost:3000/params13/test name='ali' 
  ##> The value of name is test
``` 

Yani `:params` form parametreleri `-f` değil. `GET` request parametreleri `:foo`

opt12:

https://stackoverflow.com/questions/32284500/how-to-get-all-the-params-of-a-post-request-with-compojure/32284770

``` bash
 (compojure/POST "/params14/:foo" request 
   (str (:form-params request)))
``` 

``` bash
http -f localhost:3000/params14/test first='ali' 
	##> empty
``` 

Acaba `-f` kesin gönderiyor mu form parametrelerini?

``` bash
curl -X POST -F 'first=ali' localhost:3000/params14/test
	##> empty
``` 

``` bash
curl -d 'first=ali' localhost:3000/params14/test
	##> empty
``` 

Acaba compojure tarafında bir şeyi yanlış yapıyor olabilir miyim?

opt13:

https://coderanch.com/t/667352/languages/Unable-find-read-form-parameters

``` bash
 (POST /params15/:foo" [name] 
   (str name " is me."))
``` 

``` bash
curl -d 'name=ali' localhost:3000/params15/test
  ##> <p>Problem accessing /params15/test. Reason:
  ##> <pre>    java.lang.RuntimeException: Invalid token: /params15/:foo, compiling:(ring_app/core.clj:54:22)</pre></p>
``` 

opt14: :foo kullanma

``` bash
 (POST /params16" [name] 
   (str name " is me."))
``` 

``` bash
curl -d 'name=ali' localhost:3000/params16/test
	##> empty
``` 

opt15: diğer her şeyi silelim

``` bash
lein new compojure ex02
``` 

``` bash
lein ring server-headless
``` 

Edit `~/projects/study/clj/ex/book_web_development_with_clojure/ex02/src/ex02/handler.clj`

``` bash
(defroutes app-routes
  (GET "/" [] "Hello World")
  (POST "/" [name] (str name " loves coderanch.com"))

(def app
  (wrap-defaults app-routes api-defaults))
``` 

``` bash
curl -d name=Tim localhost:3000
  ##> ali loves coderanch.com
``` 

##### Error: anti-forgery token

``` bash
curl -d name=Tim localhost:3000
  ##> <h1>Invalid anti-forgery token</h1>
curl localhost:3000
http -f localhost:3000 name='ali' 
  ##> <h1>Invalid anti-forgery token</h1>
``` 

opt01: https://stackoverflow.com/questions/33132131/unable-to-complete-post-request-in-clojure

Use `api-defaults` instead of `site-defaults`

##### Logs

Edit `~/projects/study/clj/ex/book_web_development_with_clojure/ex02/project.clj`

``` bash
  :main ex02.handler
``` 

``` bash
lein run
``` 

#### Farklı denemeler yap

Check `~/projects/study/clj/ex/book_web_development_with_clojure/ex02/src/ex02/handler.clj`

opt01: `:form-params`

``` bash
  (POST "/e02" request (str (:form-params request)))
``` 

``` bash
http -f localhost:3000/e02 name='ali' 
  ##> {"name" "ali"}
``` 

opt02: `:params`

``` bash
  (POST "/e03" request (str (:params request)))
``` 

``` bash
http -f localhost:3000/e03 name='ali' 
  ##> {"name" "ali"}
``` 

opt03: destructuring

``` bash
  (POST "/e04" {{value :name} :form-params} (str "My name is: " value))
``` 

``` bash
http -f localhost:3000/e04 name='ali' 
  ##> My name is:
``` 

opt04: destructuring 2

``` bash
  (POST "/e05" {value :name} (str "My name is: " value))
``` 

``` bash
http -f localhost:3000/e05 name='ali' 
  ##> My name is:
``` 

#### Final

Check `~/projects/study/clj/ex/book_web_development_with_clojure/ex02/src/ex02/handler.clj`

``` bash
(defroutes app-routes
  (GET "/" [] "Hello World")
  (POST "/e01" [name] (str name " loves coderanch.com"))

(def app
  (wrap-defaults app-routes api-defaults))
``` 

``` bash
curl -d name=ali localhost:3000/e01
http -f localhost:3000/e01 name='ali' 
  ##> ali loves coderanch.com
``` 

---

Destructure some of form parameters and create a map from the rest:

``` bash
[x y & z]
x -> "foo"
y -> "bar"
z -> {:v "baz", :w "qux"}
``` 

