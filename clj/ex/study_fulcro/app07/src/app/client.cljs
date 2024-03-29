(ns app.client
  (:require
    [com.fulcrologic.fulcro.application :as app]
    [app.ui :as ui]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]))

(defonce app (app/fulcro-app))

(defn ^:export init
  "Shadow-cljs sets this up to be our entry-point function. See shadow-cljs.edn `:init-fn` in the modules of the main build."
  []
  (app/mount! app ui/Root "app")
  (js/console.log "Loaded"))

(defn ^:export refresh
  "During development, shadow-cljs will call this on every hot reload of source. See shadow-cljs.edn"
  []
  ;; re-mounting will cause forced UI refresh, update internals, etc.
  (app/mount! app ui/Root "app")
  ;; As of Fulcro 3.3.0, this addition will help with stale queries when using dynamic routing:
  (comp/refresh-dynamic-queries! app)
  (js/console.log "Hot reload"))

(comment
  (keys app)
  (-> app (::app/state-atom) deref)
  ;{:fulcro.inspect.core/app-id "app.ui/Root",
  ; :friends {:list/label "Friends",
  ;           :list/people [{:person/name "Sally", :person/age 37} {:person/name "Joe", :person/age 22}]},
  ; :enemies {:list/label "Enemies",
  ;           :list/people [{:person/name "Fred", :person/age 11} {:person/name "Bobby", :person/age 55}]},
  ; :fulcro.inspect.core/app-uuid #uuid"339a48f1-7611-457b-aae2-cb4bf2ec73cf"}
,)
