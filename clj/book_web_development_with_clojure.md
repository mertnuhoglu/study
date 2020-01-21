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


