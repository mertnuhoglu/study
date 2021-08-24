---
title: "Studying Fulcro"
date: 2020-04-11T10:13:07+03:00 
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

# index fulcro

Başlangıç için örnek alınacak proje: `Tutorial: video-series <url:file:///~/projects/study/clj/fulcro_tutorial.md#r=g11458>`

Video anlatımı: `Tutorial video-series  <url:file:///~/projects/study/clj/fulcro_tutorial.md#r=g11484>`

Başlangıç kılavuzu: `Article: Fulcro Developers Guide Getting Started <url:file:///~/projects/study/clj/fulcro.md#r=g11472>`

## fulcro refcard

```clojure
npm install
```

# Article: Fulcro Developers Guide Getting Started id=g11472

[Fulcro Developers Guide Getting Started](http://book.fulcrologic.com/#_getting_started)

```clojure
mkdir app
cd app
mkdir -p src/main src/dev resources/public src/app
npm init
npm install shadow-cljs react react-dom --save
```

Check `ex/study_fulcro/app/deps.edn`

Check `ex/study_fulcro/app/shadow-cljs.edn`

Check `ex/study_fulcro/app/resources/public/index.html`

Check `ex/study_fulcro/app/src/app/client.cljs`

```bash
npx shadow-cljs server
```

opt02:

```bash
npx shadow-cljs start
```

Open `localhost:8000`

Open `localhost:9630` > Builds > main

Intellij > Cursive > Run Configurations > Remote Clojure REPL > `Host = localhost, Port = 9000, nREPL` > Run

cursive repl:

```clojure
(shadow/repl :main)
(js/alert "hi")
```

# Article: Demand-driven architecture: Relay, Falcor, Om.Next - Shaun Robinson - Medium id=g11451

[Demand-driven architecture Relay, Falcor, Om.Next - Shaun Robinson - Medium](https://medium.com/@env/demand-driven-development-relay-falcor-om-next-75818bd54ea1)

react is just a view rendering library — one that says: given data x, you’ll efficiently render ui y. Flux and Redux provide two patterns for managing the data backing React components, but they don’t address reconciliation with the server-side.

An approach to declaring and reconciling data between an application client and server

Render down, query up: Parent components render their children, passing data down, but build their own demand up from child’s component’s demand

Data store agnostic — the query demand is in the language of the app, not the DB, so any can be used and interchanged

Demand language also deals with mutations, specified, like queries, as data.

Client cache and reconciliation allows it to fetch only what it needs when the UI demand changes as the user moves through the product

# Article: Val on Programming - DataScript as a Lingua Franca for domain modeling id=g11453

[Val on Programming - DataScript as a Lingua Franca for domain modeling](https://vvvvalvalval.github.io/posts/2018-07-23-datascript-as-a-lingua-franca-for-domain-modeling.html)

assertions about domain model as datascript writes -> datascript database -> generate system components (datomic schema, rest endpoints, graphql schema etc.)

Domain model assertions:

```clojure
(def user-model
  [{:twitteur.entity-type/name :twitteur/User
    :twitteur.schema/doc "a User is a person who has signed up to Twitteur."
    :twitteur.entity-type/attributes
    [{:twitteur.attribute/name :user/id
      :twitteur.schema/doc "The unique ID of this user."
      :twitteur.attribute/ref-typed? false
      :twitteur.attribute.scalar/type :uuid
      :twitteur.attribute/unique-identity true}
```

DataScript database value is not a hidden implementation detail here: the database is the API. 

Prior work:

- Database DMLs (Data Modeling Languages) e.g in SQL: you describe the shape of your data, and sometimes can query it.
- ORMs (Object-Relational Mappers) like ActiveRecord / Hibernate, and more generally class-based frameworks: you represent your 'model' as a class and use class annotations or various metaprogramming features to make your Domain Model assertions
- API schemas, like GraphQL schemas for GraphQL, OpenAPI for REST and WSDL for SOAP, also rely on a data representation of some part of your Domain Model

## Plumbing-first vs Domain-first

2 approaches to developing software: plumbing-first and domain-first.

Plumbing-first consists of programming by starting with 'mechanical' components - HTTP routes, database queries, etc. - shaping them until the program's behaviour meets the requirements of the Domain.

A plumbing-first approach makes for early successes, and is generally a good approach when the Domain is not well-known or very simple. 

## You're in the business of framework-authoring

By 'framework', I really mean a set of programmatically-enforced decisions about application architecture.

# Article: The Benefits of Fulcro id=g11454

[The Benefits of Fulcro](https://fulcro.fulcrologic.com/benefits.html)

In a nutshell: Fulcro eliminates a lot of incidental complexity.

It allows you to think about rendering as a pure function of data, which then allows you to think about the clean evolution of your data model from one state to the next through mutations on that data model (atomic steps that complete one operation)

Most asynchrony evaporates! Callbacks? We (mostly) don’t need them.
Reason about the data model as a pure graph of data (mostly separate from the UI).
The model makes CQRS pretty easy to add for both auditing and performance

# Links: Fulcro links and resources - fulcro

[Fulcro links and resources - fulcro](https://www.reddit.com/r/fulcro/comments/dtzhsm/fulcro_links_and_resources/)

# Project: crudless-todomvc- Example TodoMVC powered by fulcro, pathom, and hasura

[crudless-todomvc- Example TodoMVC powered by fulcro, pathom, and hasura](https://github.com/codonnell/crudless-todomvc)

`/Users/mertnuhoglu/codes/clojure/fulcro/crudless-todomvc`

# Video: Why Build Solutions with Fulcro - Tom Toor - PMbGhgVf9Do id=g11455

React:

```clojure
VIEW = F(PROPS)
```

Problem: `props` becomes very large tree.

opt01: Higher Order Functions, Reagent, and Friends

splitting UI into multiple states.

```clojure
view_1 = f_1(state_1)
view_2 = f_2(state_2)
...
view_n = f_n(state_n)
```

Problem: state pools overlap

[state explosion](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20200624_094637.jpg)

Example: data pools

[data pools](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20200624_094844.jpg)

Account information and People information overlap.

We need to sync the overlapped data always.

opt01: Re-frame, Redux, and Friends

Took multiple states and unified into global state.

Problem: Data is still denormalized.

Also left out: rest of the stack (full stack operation)

- no unified network interactions
- graphs of side-effects: 
- accidental data duplication, arbitrary callbacks
- no method for: how ui data co-exists with full-stack data

Apps need cohesive full-stack method. 

- Event-centric systems

we say: reducing coupling is good.

ex: delete item -> local ui change -> server request -> response -> event ...

Problem: chains of logic are hard to follow

Problem: implicit coupling

ex: A - - > B

But we don't see this dependency explicitly.

Network interactions work totally independently. No coordination.

Observations:

- Most of the logic is on the client but we are in a distributed system. We need to solve these problems. Client always has a big copy of data. Transactional semantics 
- Collaborative development always.
- We are remote from the real database. It should be part of the system.
- Data needs that don't match server schema
- operations with some client some server implementation

Like: we are denying we are in the distributed system. 

updating dom is easy compared to this stuff.

## Om Next and Fulcro

Om Next pioneering ideas:

- co-locating identity and query on ui components
- auto-normalizing client database
- reified mutations

Fulcro: 

- materialized views
- pathom for server query processing
- well-defined I/O method

what is?

- to reify: to instantiate, to make it concrete
- colocating query: query is in the same location as component's view. departure from the common mvc. [GraphQL colocation in React a growing trend](https://react-etc.net/entry/graphql-colocation-in-react-a-growing-trend)

## We were missing a real database

Most of logic in on frontend

Why is data stored in adhoc way?

How much database?

Imagine on server side: we don't need database. This is what we do in the client.

We need to:

- normalize data in client
- query engine to read it from data store
- queue sequential updates
- indexing (nice to have)

Ex: Data Normalization:

[data normalization](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20200624_122400.jpg)

[data normalization](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20200624_122427.jpg)

It eliminates overlapping data pool issues.

Update it right there.

Difficulty: how to get to this data model?

opt01: schema declaration as in server

Convert json tree to database

How 01: co-locate query and identity (ident)

[co-located query and identity](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20200624_122635.jpg)

app state = client database

decorate ui components with identity and query information.

How 02: Queries composed just like UI components

[explicit load](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20200624_122922.jpg)

This lets us unify 

server response can contain duplicate data.

Fulcro normalizes the data automatically.

Fulcro query engine reads all data requested and give it to ui components.

## How to give data to UI components from client database

How much query engine?

- datalog or sql: overkill. performance cost too much.
- query for props and simple joins: yes
- aggregation/sorting/pagination: yes & no
- complex joins: no
- data shaping: yes

EDN query language: EQL

Server data source: GraphQL

Convert EQL data structures to GraphQL strings in server side.

Cons of GraphQL:

- complex
- cannot flatten
- no namespaces
- only entry from root

Thus we opted for Pathom.

- EQL query engines.
- Flexible: global subgraphs or local, not just root
- Flattening results
- Fan out flat data

[eql and pathom](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20200624_123848.jpg)

## Pathom Synergy

- any database
- runs in client too
- wraps any data source (rest, graphql, spring based)

## Client mutations

No events, instead reified mutations

```clojure
`(delete-item {:item/id 1})
```

Can be grouped into transactions:

```clojure
`[(f) (g) (h)]
```

Abstract representation of a mutation

Record, replay, audit support

State update: transact this. 

ex: user clicks delete button

```clojure
(let [handler #(transact! this `[(delete-item {:item/id ~id})])]
  (div {:onClick handler} "Delete"))
```

`delete-item` definition:

```clojure
(defmutation delete-item
	[{:keys [item/id]}]
	(action [{:keys [state]}]
	  (swap! state dissoc :item/id id)))
```

We can have on success, on error handlers for server:

```clojure
(defmutation delete-item
	[{:keys [item/id]}]
	(action [{:keys [state]}]
	  (swap! state dissoc :item/id id))
	(remote [_] :http-api)
	(ok-action [_] (log/info "success!"))
	(error-action [_] (log/error "error!")))
```

On server we define mutation with the same name again:

```clojure
(defmutation delete-item
	[{:keys [item/id]}]
	(action [{:keys [conn]}]
		@(d/transact conn [[:db.fn/retractEntity id]])))
```

If the server is expected to return something, client defines it:

```clojure
(defsc Session,,,)
(defmutation login!
  [{:keys [username password]}]
	(remote [env] (returning env Session)))
```

```clojure
state* = f(state)   ;; mutation/load
;; render:
props = QueryEngine(UI-Components, state*)   ;; query
view = ui(props)    ;; react
```

## State Machines

```clojure
(defstatemachine login
  {::uism/actor-names #{:dialog :form}
	 ::uism/aliases {:visible? [:dialog :ui/active?]
	                 :login-enabled? [:form :ui/login-enabled?]}
	 ::uism/states {,,,}})
```

# Book: Fulcro Developers Guide

[Fulcro Developers Guide](http://book.fulcrologic.com/)

get state:

```clojure
dev:cljs.user=> (com.fulcrologic.fulcro.application/current-state app.application/app)
```

query:

So, a data tree like this:

```clojure
{:friends
 {:list/label "Friends",
  :list/people
  [{:person/name "Sally", :person/age 32}
   {:person/name "Joe", :person/age 22}]},
 :enemies
 {:list/label "Enemies",
  :list/people
  [{:person/name "Fred", :person/age 11}
   {:person/name "Bobby", :person/age 55}]}}
```

would have a query that looks like this:

```clojure
[{:friends  ; JOIN
    [ :list/label
      {:list/people ; JOIN
         [:person/name :person/age]}]}]
```

### 3.13. Parsing Queries

- resolvers
- mutations

resolvers: 

- expect some inputs
- declare what they can produce

context stored in `env` + some optional inputs

ex: resolver

```clojure
(pc/defresolver person-resolver [env input]
  {::pc/input #{:person/id}
	 ::pc/output [:person/name]}
	(let [name (get-name-from-database (:person/id input))]
	  {:person/name name}))
```

it declares: "given a `:person/id` as input, I can produce their name".

inputs are written as a set: `#{:person/id}`

output is written as an EQL query

body must return an EQL response that matches the shape of the declared `::pc/output`
 






# Logs 20200630 

## Article: Fulcro vs. Stock Om Next

[Fulcro vs. Stock Om Next — fulcrologic/fulcro 2.8.13](https://cljdoc.org/d/fulcrologic/fulcro/2.8.13/doc/fulcro-vs-stock-om-next)

- local reasoning
	- no hand-building ui state
- refactoring ui: move the component and recompose locally
- running part of ui in devcards is trivial
- data loading as first-class citizen
- predefined ways for mutation and read
- network interaction and data merging is part of the system
- generating ui from app state

## Discussion: Fulcro Developers Guide - Hacker News

[Fulcro Developers Guide: Single-page full-stack web applications in clj/cljs | Hacker News](https://news.ycombinator.com/item?id=19520272)

- Fulcro inspect plugin: history viewer
- Workspaces

Aggregate shapes defined at query time, not at insert time, it's mostly about moving immutable Datoms around not trees that are hard to reconfigure & reuse like nested JSON

I'd suggest looking into the feature set of Datomic, and then ask yourself what would happen if you ran with those concepts in the front end? - Fulcro

- manage state easily
- multiple remotes (graphql incl)
- rest remotes
- server side rendering (SSR)
- any npm deps 
- client and server mutations on the same file (via clojure reader macros)

- refactoring a big project without a type system is very hard and error-prone

This is true BUT data is easy to refactor and Clojure is really about Data. Clojure's big picture is a principled mental framework for reasoning about data

Hyperfiddle poses the question: is it possible to express sophisticated database applications out of just data

## Discussion: What are paradigm differences between different front-end ClojureScript libraries? - Community Center / Beginners - ClojureVerse

[What are paradigm differences between different front-end ClojureScript libraries? - Community Center / Beginners - ClojureVerse](https://clojureverse.org/t/what-are-paradigm-differences-between-different-front-end-clojurescript-libraries/2832/3)

Om Next and Fulcro, on the other hand, are big paradigm shifts. They use co-located queries to keep components relatively self contained. Components just say what they need. They’re able to take a tree of data and convert it to a normalized database using idents.

But then you’ve tied two far-flung parts of your application together. Using Om Next or Fulcro allow you to think more locally within your components.

for me reading the fulcro docs was similar to learning clojure, plenty of “AHA, wow” moments

## Article: Evaluating Fulcro (or web development tools in general)

[fulcro by fulcrologic](https://fulcro.fulcrologic.com/evaluation.html)

I would encourage you not to just look at superficial criteria like “is it easy to get started?” Those concerns rarely affect your long-term success. Look more deeply. Try writing a simple full-stack application in each (or at least look for a full-stack example). Many things that are easy to get started with devolve into code with constructs that are fragile, difficult to reason about, and are hard to navigate (such as chains of asynchrony that can be defined by delarations in any file).

Often the most difficult thing to do when bugs happen in the field is figure out what went wrong. Fulcro can be configured to allow users to send a bug report that includes the history of their UI, which can be replayed (with timestamps) by the developer and even debugged against!

UI Easy to Visually Regression Test: with devcards

Eliminates sources of complexity like Controllers, library event systems (signals), etc. Local reasoning helps here.

# Örnek Projeler id=g11471

## cursive support for fulcro macros id=g11481

![resolve defresolver as defn](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20200717_163411.jpg)

[custom macros](https://cursive-ide.com/userguide/macros.html)

