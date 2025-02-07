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

(defonce current-app (atom nil))

(defn ^:export init2
  [RootComponent]
  (let [app (app/fulcro-app RootComponent)]
    (reset! current-app app)
    (app/mount! app RootComponent "app")
    (js/console.log "Loaded")))

(defn ^:export init3 [])

(comment
  (def app (app/fulcro-app))
  (app/mount! app ui/Root "app")
  ,)











