(ns app.app00
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [com.fulcrologic.fulcro.application :as app]))

; video: Part 2 03:45

(defsc Sample [this props]
  {}
  (dom/div (str props)))

(defonce app (app/fulcro-app))

(comment
  (in-ns 'app.app0)
  (app/mount! app Sample "app")
  ; check web page:

  (reset! (::app/state-atom app) {:a 1})
  (app/schedule-render! app)
  ; check web page: {:a 1}

  (+ 1),)

