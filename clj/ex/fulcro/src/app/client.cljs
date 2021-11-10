(ns app.client
  (:require
    [holyjak.fulcro-exercises.impl :refer [hint config-and-render! show-client-db] :as impl]
    [com.fulcrologic.fulcro.application :as app]
    [app.app01 :as app01]
    [app.app02 :as app02]
    [app.app03 :as app03]
    [app.app04 :as app04]
    [app.app05 :as app05]
    [app.app06 :as app06]
    [app.app07 :as app07]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]))

(defonce app (app/fulcro-app))

(def RootComponent
  ;app01/Root
  ;app02/Root
  ;app03/Root
  ;app04/Root
  ;app05/Root
  app06/Root)

(defn ^:export init
  "Shadow-cljs sets this up to be our entry-point function. See shadow-cljs.edn `:init-fn` in the modules of the main build."
  []
  (app/mount! app RootComponent "app"),

  (js/console.log "Loaded"))

(defn refresh []
  (app/mount! app
    (app/root-class app)
    "app")
  ;; As of Fulcro 3.3.0, this addition will help with stale queries when using dynamic routing:
  (comp/refresh-dynamic-queries! app))

(comment

  (in-ns 'app.client)
  (app/mount! app app02/Root "app"),
  (app/mount! app app03/Root "app"),
  (app/mount! app app04/Root "app"),
  (app/mount! app app05/Root "app"),
  (app/mount! app app06/Root "app")

  (keys app)
  ;(:com.fulcrologic.fulcro.application/id
  ;  :com.fulcrologic.fulcro.application/state-atom
  ;  :com.fulcrologic.fulcro.application/config
  ;  :com.fulcrologic.fulcro.application/algorithms
  ;  :com.fulcrologic.fulcro.application/runtime-atom)

  (app/mount! app app06/Root "app")
  (-> app (::app/state-atom) deref)
  ;{:fulcro.inspect.core/app-id "app.app06/Root",
  ; :friends #:list{:label "Friends", :people [#:person{:name "Sally", :age 32} #:person{:name "Joe", :age 22}]},
  ; :enemies #:list{:label "Enemies", :people [#:person{:name "Fred", :age 11} #:person{:name "Bobby", :age 55}]},
  ; :fulcro.inspect.core/app-uuid #uuid"1c7decd1-0b2b-4f82-a776-8627ffc39b40"}


  (+ 1 2),)
