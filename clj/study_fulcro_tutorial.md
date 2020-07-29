---
title: "Studying Fulcro Tutorial"
date: 2020-07-20T14:06:18+03:00 
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

## Tutorial: video-series id=g_11458

`~/codes/clojure/fulcro/video-series`

`ssh://git@github.com/fulcrologic/video-series`

`https://github.com/fulcrologic/video-series`

### 01: tag intro

opt01:

```bash
cd ~/codes/clojure/fulcro/video-series
git checkout intro
```

opt02:

```clojure
cd /Users/mertnuhoglu/codes/clojure/fulcro/tutorial_tags/
cd intro
```

### 02: tag initial-state

00. install

```bash
npm install
```

01. Change ports:

Edit `~/codes/clojure/fulcro/tutorial_tags/initial-state/shadow-cljs.edn`

```clojure
 :nrepl    {:port 9001}

 :dev-http {8001 "resources/public"}
```

02. Start app:

```bash
npx shadow-cljs server
```

Start compiling with watcher: `http://localhost:9630/build/main` > `start-watch`

Open: `http://localhost:8002/`

03. Connect to nREPL server 

opt01: terminal:

[ssh - Clojure - how to connect to running REPL process remotely - Stack Overflow](https://stackoverflow.com/questions/52459671/clojure-how-to-connect-to-running-repl-process-remotely)

```bash
lein repl :connect localhost:9001
```

```clojure
(shadow/repl :main)
(js/alert "hi")
```

```clojure
(ns app.client
  (:require
    [com.fulcrologic.fulcro.application :as app]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [com.fulcrologic.fulcro.algorithms.merge :as merge]
    [com.fulcrologic.fulcro.mutations :as m :refer [defmutation]]
    [com.fulcrologic.fulcro.algorithms.data-targeting :as targeting]))
(comp/transact! APP [(make-older {:person/id 1})])
```

opt02: cursive

`Run > Edit Configurations > nREPL Remote > .localhost 9001` 

```clojure
(shadow/repl :main)
(js/alert "hi")
```

opt03: spacemacs cider id=g_11460

[Connect to existing REPL · Clojure development with Spacemacs & Cider](https://practicalli.github.io/spacemacs/external-repl/connect-to-running-repl.html)

Error: mevcut repla bağlanamadım

opt01: kendin repl server oluştur:

`SPC m s c` or `cider-connect`

`SPC m s s`

### 03: tag: dom+react

Open: `http://localhost:8002/`

```clojure
lein repl :connect localhost:9002
```

### 04: tag: full-stack-1

Open: `http://localhost:8003/`

# Fikir: fulcro oop ve frp arasında bir denge kuruyor gibi 20200701  id=g_11459

Fulcro'nun lokal düşünme yaklaşımı, OOP'yi hatırlatıyor. Orada da veri, ilgili objenin içine encapsulate edilir 

Öbür yandan, FRP'nin ve event stream yaklaşımının getirdiği dinamikliği de sağlıyor fulcro. 

Bir mutation olduğunda, bunu akan veri mantığıyla veritabanına düzgün bir şekilde yansıtıyor ve buradan da görsel komponentlere aktarıyor. 

Lokal düşünmenin faydası nedir? Objeye baktığında, bunun ne yaptığını nasıl çalıştığını anlarsın. 

# Tutorial video-series  id=g_11484

## Video: 02-Fulcro – Part 2 - Normalization-HCVzG2BLRwk

[Project files](https://github.com/fulcrologic/video-series)

`defsc`: define stateful component

SPA (APP) is a map.

`comment` block

Cursive > `Send form` ve `Send expression` to repl.

`ui-person` doğrudan tanımlanan bir fonksiyon değil. `comp/factory` tarafından oluşturulan bir fonksiyon.

normalde json hiyerarşik bir veri. normalizasyon içermez. fulcro bunu normalize eder ilişkisel veri modeline uygun bir şekilde

## Video: 03-Fulcro – Part 3 - Initial State and Mutations-KJsFLmkdRig id=g_11456

mutations are meant to be dealt with data lik emanner. when you invoke it, it goes through a transactional processor. 

the remote and local update codes are colocated into single unit of code.

```clojure
(defmutation make-older [{:person/keys [id]}])
```

defmutation looks like a function. 

cursive > resolve > specify > .... then intellij will understand the syntax of it.

```clojure
(defmutation make-older [{:person/keys [id]}]
	(action [{:keys [state]}]))
```

mutations can take a single map.

it has several sections:

```clojure
(defmutation make-older [{:person/keys [id]}]
	(action [{:keys [state]}])
	(remote [env] true)
	(rest [env] true))
```

so this mutation has 3 sections, some remote, some rest section.

```clojure
(defmutation make-older [{:person/keys [id]}]
	(action [{:keys [state]}])
	)

```

action section: what to do locally

you can pass it an environment.

it has a binding to state database.

```clojure
(defmutation make-older [{:person/keys [id]}]
	(action [{:keys [state]}]
		(swap! state update-in [:person/id id :person/age] inc))
	)
```

Check the state of the app:

```clojure
(app/current-state APP)
```

Load intiial data:

```clojure
(merge/merge-component! APP Person {:person/id 1 :person/age 20})
```

we can call mutations as functions and they will return the data:

```clojure
(make-older {:a 1})
;; => (app.client/make-older {:a 1})
```

Key important thing. They return data.

We can backquote it as well

```clojure
`(make-older ~{:a 1})
;; => (app.client/make-older {:a 1})
```

But in fulcro 3 we do it without quoting:

```clojure
(make-older {:a 1})
;; => (app.client/make-older {:a 1})
```

Other thing it does, it registers with a multimethod this symbol. So we get these sections of mutation layer.

To run it we need to submit it as transaction with `transact!`

```clojure
(comp/transact! APP)
```

it submits from the ui an abstract operation that might have a remote and local effects to your application.

```clojure
(comp/transact! APP [(make-older {:person/id 1})])
```

This is actually submitting its data structure as a list of things. So I can put more mutations.

```clojure
(comp/transact! APP [(make-older {:person/id 1}) ...])
```

Transaction changes the state. State tracking happens for some possible remote activity.

This is the state management update story. Because you don't talk about remotes in the UI itself, it makes things much easier.

```clojure
(defsc Person ...
		(dom/button {:onClick #(comp/transact! this [(make-older {:person/id id})])} "Make older"))
```

Or I can treat this transaction as pure data by quoting:

```clojure
		(dom/button {:onClick #(comp/transact! this `[(make-older ~{:person/id id})])} "Make older"))
```

You got your initial state frame. It will never be applied again after app start.

Mutations are sent as data structures.

For convenience, you can make sure that mutation is visible at code level. It can be another namespace. A lot of people put them into a separate namespace. 

## 04-Fulcro – Part 4  - Components, DOM, and React-vNKYl-5x8Ao

To make dom notation more compact:

```clojure
(ns app.client
  (:require
	  [com.fulcrologic.fulcro.dom :as dom :refer [div ul li]]))
```

Now we can get rid of prefixing:

```clojure
	(div
		(div "Name: " name)
		(div "Age: " age)
		(h3 "Cars")
		(ul 
			(map ui-car cars)))
```

Add a [semantic stylesheet](http://semantic-ui.com) to index.html. So we can see css in action.

We give an optional options map for attributes

```clojure
(div {:style {:color "red"}} "Name: " name)
```

short form for class naming:

```clojure
(div :.ui.form)
```

hiccup way: `[:div.ui.form]` short for `{:className "ui form"}`

```clojure
(map div ["hi" "there"])
```

Macros are evaled at compile time. 

We can put labels as in actual forms:

```clojure
(div :.field
  (label "Age: ")
	age)
```

use `{}` even if they are empty to make things a little faster.


## 05-Fulcro – Part 5  - How Rendering Works-JBy_htHxygo

rendering as efficiently as possible

fulcro leverages react's mechanisms

there is an extra layer fulcro adds: data management layer

that is an overhead layer.

1. query
2. output of factories (vdom)
3. react DOM diff

how to improve performance here:

1. query (reduce)
2. output of factories (vdom) (reduce the number that need to run period)
3. react DOM diff (provide stable keys)

Hangi komponentlerin update edildiğini görmek için, log ifadeleri ekliyoruz hepsine:

```clojure
(defsc PersonList
	{...}
	(js/console.log "Render list"))
```

Şimdi bir tane Person objesini (react komponent) değiştirelim bakalım hangi objeleri tekrar render ediyor?

```clojure
(comp/transact! APP [(make-older {:person/id 1})])
```

Bunu çalıştırdığımızda, sadece ilgili Person objesinin değişen verileri render ediliyor. Alt objeler veya diğer objeler render edilmiyor. 

Fakat react istersek, tüm komponentlerin her durumda render edilmesine izin veriyor konfigürasyonla:

```clojure
(defsc PersonList
	{:shouldComponentUpdate (fn [] true)}
```

Bu durumda, transactionı çalıştırdığımızda, `shouldComponentUpdate true` olan tüm komponentler render ediliyor, değişse de değişmese de.

Bu durumda, react yine vdom'da diff alıp, farklılaşma yoksa yeniden render etmiyor. Ama eskisi kadar hızlı değil bu.

Şimdi bir butona bağlayalım `make-older` işlemini:

```clojure
(dom/button :.ui.button {:onClick (fn []
																		(comp/transact! this `[(make-older ~{:person/id id})]))})
```

Bir de 30 yaş üstü Person komponentleri sayıp gösterelim:

```clojure
(defsc PersonList ...
	{:ident (fn [] [:component/id ::person-list])
	 :initial-state {:person-list/people [{...}]}}
  (let [cnt (reduce 
							(fn [c {:person/keys [age]}]
								(if (> age 30)
									(inc c)
									c))
							0
							people)])
		(div :.ui.segment
			(h3 :.ui.header "People")
			(div "over 30: " cnt)))
```

Şimdi "Make Older" butonunu tıkladığımızda bu sayacın yükselmesini bekliyoruz, ama yükselmiyor. Neden?

loglara baktığımızda, `Person` objesi güncellenince, bunun render edildiğini, ama `PersonList` komponentinin render edilmediğini görüyoruz.

Bu durumda `PersonList` komponentininin de render edilmesi için, `:refresh` atributunu kullanmalıyız.

```clojure
(dom/button :.ui.button {:onClick (fn []
																		(comp/transact! this 
																			`[(make-older ~{:person/id id})]
																		  {:refresh [:person-list/people]}))})
```

## 06-Fulcro – Part 6  - Full Stack Part One (Read Description for Optional Content)-F7QzFpo8pA0 id=g_11457

Working with server.

s: First, modify our deps

When we start shadowjs, http server starts.

s: Cursive shortcuts and command:

		| Load file in REPL               | !s l |
		| Switch REPL NS to current file` | !s n |

s: Add new configuration: Local REPL

Run Local REPL > .open `user.clj` > `Load file in REPL`

s: Add new repl commands

`Add new REPL command`

Add new repl command to run with a shortcut shuch as:

`Restart user ns`. Command: `(restart)`

[repl command](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200717120519.jpg)

Assign keyboard shortcut to it: `!s r`

s:

`org.httpkit.server` gives you `run-server` command. 

```clojure
(defn start []
  (let [result (http/run-server middleware {:port 3000})]
    (log/info "Started web server on port 3000")
    (reset! server result)
    :ok))
```

You give to it `middleware`. It is a stack of ring middleware functions. 

Check `~/codes/clojure/fulcro/video-series/src/app/server.clj`

```clojure
(def middleware (-> not-found-handler
                  (wrap-api {:uri    "/api"
                             :parser (fn [query] (async/<!! (parser {} query)))})
                  (fmw/wrap-transit-params)
									...
```

We keep our http server in an atom:

```clojure
(defonce server (atom nil))
```

`start` puts the http server into this atom:

```clojure
    (reset! server result)
```

`stop` stops it.

`restart` calls `stop` then:

```clojure
  (tools-ns/refresh :after 'user/start))
```

This dependency comes from:

```clojure
                               org.clojure/tools.namespace {:mvn/version "0.3.0-alpha4"}
```

This library has the code unit to unload namespaces when you refresh them. This is necessary for cleanful refresh of the server.

Two steps is important:

```clojure
(tools-ns/set-refresh-dirs "src" "dev")
```

```clojure
  (tools-ns/refresh :after 'user/start))
```

unloads and reloads all namespaces.

There is no automatic hotcode reload in server. You need to trigger it when you are ready by invoking `(refresh)`

Ok, we have a server. Now we need to get client talk to the server.

Check `~/codes/clojure/fulcro/video-series/src/app/client.cljs`

We define the `APP` with `fulcro-app` function. And we have a map of `:remotes`

```clojure
(defonce APP (app/fulcro-app {:remotes          {:remote (http/fulcro-http-remote {})}
```

You can have multiple `:remotes`. You give them a keyword such as `:remote`. There is a builtin remote implementation for talking to http and there is an addon library for websocket.

This defaults to contacting the server the web page is loaded from at the `/api` endpoint. This is defined in our server definition:

```clojure
(def middleware (-> not-found-handler
                  (wrap-api {:uri    "/api"
                             :parser (fn [query] (async/<!! (parser {} query)))})
```

We told here to `wrap-api` to listen to `/api` endpoint.

What we send from client is a full query. It tells the server everything it needs. This is rest. Send a query to server gets answered via `parser`

Use same types in frontend and backend:

- BigDecimal
- juxt tick for time

### backend resolvers

#### setup inspect debug tools

s: Fulcro inspect > query

`intellij > restart user ns`

open: `http://localhost:3000/index.html`

open: `chrome > fulcro inspect`

`Chrome > Fulcro inspect > Query > .grey button > click it > reload page` 

`click grey button > it turns to green`

[autocompleted keywords](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200717160804.jpg)

```clojure
[{[:car/id 1] [:car/make :car/model]}]
  ##> {[:car/id 1] {:car/model "Accord", :car/make "Honda"}}
```

[query result](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200717160920.jpg)

This works because of the `car-resolver`:

```clojure
(pc/defresolver car-resolver [env {:car/keys [id]}]
  {::pc/input  #{:car/id}
   ::pc/output [:car/id :car/make :car/model]}
  (get @cars id))
```

Note that body is: `(get @cars id))`

This part is how our backend gets from the database.

#### join query

chrome > fulcro inspect:

```clojure
[{[:person/id 2] [:person/name {:person/cars [:car/make]}]}]
  ##> {[:person/id 2]
  ##>  {:person/cars [{:car/make "Honda"}], :person/name "Sally"}}
```

Note that the join is resolved by your resolver code:

```clojure
  (let [person (-> @people
                 (get id)
                 (update :person/cars (fn [ids] (mapv
                                                  (fn [id] {:car/id id})
                                                  ids))))]
```

#### query with no input: global resolvers

`all-people` is a global resolver. It doesn't require any input:

```clojure
(pc/defresolver all-people-resolver [env {:person/keys [id]}]
  {::pc/output [{:all-people [:person/id]}]}
  {:all-people
   (mapv (fn [i] {:person/id i}) (keys @people))})
```

fulcro inspect:

```clojure
[:all-people [:person/id]]
  ##> {[:person/id] {:person/id nil},
  ##>  :all-people [{:person/id 1} {:person/id 2}]}
[{:all-people [:person/id :person/name]}]
  ##> {:all-people
  ##>  [{:person/name "Bob", :person/id 1}
  ##>   {:person/name "Sally", :person/id 2}]}
[{:all-people [:person/id :person/name
               :person/cars [:car/id]]}]
  ##> {:all-people
  ##>  [{[:car/id] {:car/id nil},
  ##>    :person/cars [{:car/id 2}],
  ##>    :person/name "Bob",
  ##>    :person/id 1}
  ##>   {[:car/id] {:car/id nil},
  ##>    :person/cars [{:car/id 1}], 
  ##>    :person/name "Sally", 
  ##>    :person/id 2}]}
```

You can use global resolvers anywhere:

```clojure
[{:all-people [:person/id :person/name
               :person/cars [:car/id]
               :all-people [:person/id]]}]
```

ex: put time on query results:

```clojure
(pc/defresolver current-system-time [env {:person/keys [id]}]
  {::pc/output [:server/time]}
  {:server/time (java.util.Date.)})
```

```clojure
[{:all-people [:person/age :server/time]}]
  ##> {:all-people
  ##>  [{:server/time #inst "2020-07-17T17:35:42.020-00:00", :person/age 22}
  ##>   {:server/time #inst "2020-07-17T17:35:42.020-00:00", :person/age 26}]}
```

### s: populate client when it starts id=g_11482

```clojure
(defonce APP (app/fulcro-app {:remotes          {:remote (http/fulcro-http-remote {})}
                              :client-did-mount (fn [app]
                                                  (df/load! app :all-people Person
                                                    {:target [:component/id ::person-list :person-list/people]}))}))
```

Open `http://localhost:3000/index.html`

![result](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200720113232.jpg)

Q: `:target` ne işe yarıyor? `::person-list` ne işe yarıyor?

Önce mevcut app durumunu kontrol et: `Chrome > Fulcro Inspect > DB > :component/id`

![Check state](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200720122952.jpg)

Neden `person-list` ile başlıyor buradaki veri?

```clojure
(defsc PersonList [this {:person-list/keys [people]}]
   :ident         (fn [] [:component/id ::person-list])
```

`PersonList`'in `ident`'i bu.

```clojure
(def ui-person-list (comp/factory PersonList))

(defsc Root [this {:root/keys [list]}]
    (ui-person-list list)))

(defn ^:export init []
  (app/mount! APP Root "app"))
```

`PersonList` de `Root` tarafından oluşturulmuş.

state:

```clojure
{:fulcro.inspect.core/app-uuid #uuid "a12ab2d0-42aa-46c9-8eb8-006129d4b788",
 :root/list [:component/id :app.client/person-list],
 :component/id
	 {:app.client/person-list
		{:person-list/people [[:person/id 1] [:person/id 2]]}},
 :com.fulcrologic.fulcro.application/active-remotes #{},
 :car/id
	 {2 {:car/id 2, :car/model "F-150"},
		1 {:car/id 1, :car/model "Accord"}},
 :person/id
	 {1
		{:person/id 1,
		 :person/age 22,
		 :person/name "Bob",
		 :person/cars [[:car/id 2]]},
		2
		{:person/id 2,
		 :person/age 26, 
		 :person/name "Sally", 
		 :person/cars [[:car/id 1]]}}}
```

`:component-id` key, value bir nested map:

```clojure
 :component/id
	 {:app.client/person-list
		{:person-list/people [[:person/id 1] [:person/id 2]]}},
```

Check `Fulcro Inspect > Network`

![Network Request](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200720133656.jpg)

Network requestinde gönderdiği sorgu, komponentin query parametresinde tanımlanıyor:

```clojure
(defsc Person [this {:person/keys [id name age cars] :as props}]
  {:query [:person/id :person/name :person/age {:person/cars (comp/get-query Car)}]
```

![Network Request Details](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200720134052.jpg)

`Send Request` yaparak, bunu test edebilirsin:

![Send Request](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200720134133.jpg)

### s: updating data in backend

![Make Older Network Request](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200720140428.jpg)

`client.cljs`

```clojure
        (dom/button :.ui.button {:onClick (fn []
                                            (comp/transact! this
                                              [(make-older {:person/id id})]
                                              {:refresh [:person-list/people]}))}
```

`make-older` is defined in frontend: `person.cljs`

```clojure
(defmutation make-older [{:person/keys [id] :as params}]
  (action [{:keys [state]}]
    (swap! state update-in [:person/id id :person/age] inc))
  (remote [env] true))
```

This alone doesn't update server side data. 

We need to define it in backend too: `person.clj`

```clojure
(pc/defmutation make-older [env {:person/keys [id]}]
  {::pc/params [:person/id]
   ::pc/output []}
  (swap! people update-in [id :person/age] inc)
  {})
```

Bu ikisi arasındaki bağlantıyı `:remotes` sağlıyor:

```clojure
(defonce APP (app/fulcro-app {:remotes          {:remote (http/fulcro-http-remote {})}
```

Ayrıca frontend tarafında da `remote` fonksiyonunu çağırıyoruz:

```clojure
(defmutation make-older [{:person/keys [id] :as params}]
	...
  (remote [env] true))
```

![Transaction](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200720174642.jpg)

![Transaction Diff](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/20200720174712.jpg)

## 07-Fulcro – Part 7  - Full Stack Operations Over Time-wuOnP5yufxU.mp4

s: run it

```clojure
npm install
npx shadow-cljs server
```

`IntelliJ > Configurations > Local REPL > run`

Open `http://localhost:3000/index.html`

s: intellij ide customizations for fulcro macros

`defsc > Resolve to > fulcro.client.primitives.defsc`

`defmutation` is a multimethod

You can write it as:

```clojure
(defmethod m/mutate `make-older [env]
  {:action (fn [env])
	 :remote (fn [_] true)})
```

Bu şekilde yaparsan, IDE `make-older` sembolünü buraya resolve edemez.

`defmutation > Resolve to > fulcro.client.mutations.defmutation`

`defsc` bir fonksiyon yazmak yerine bir macro:

```clojure
(defn PersonDetail [] ...)
(comp/configure-component! PersonDetail {..})
```

## 08-Fulcro – Part 8  - Reasoning about Loads (over time)-Bistiamcz8Y.mp4

cursive repl:

`Load File in REPL`

```clojure
(df/load! APP [:person/id 1] PersonDetail)
=> #uuid"3a757437-e192-4547-b90b-1083e430a11c"
```

# Restudy Tutorial 20200725 

## initial-state id=g_11486

```clojure
(def ui-person (comp/factory Person {:keyfn :person/id}))
```

What does `:keyfn` do?

Check definition of `factory`

```clojure
(defn factory
  ([class {:keys [keyfn qualifier] :as opts}]
```

Check React [documentation](https://reactjs.org/docs/lists-and-keys.html#keys) on `keys`:

>> Keys help React identify which items have changed, are added, or are removed. Keys should be given to the elements inside the array to give the elements a stable identity:

```clojure
  <li key={number.toString()}>
	...
  <li key={todo.id}>
```

But how does `[keyfn qualifier]` destructure the given arguments?

ref: `opt02: short form <url:file:///~/projects/study/clj/study_clojure.md#r=g_11487>`

