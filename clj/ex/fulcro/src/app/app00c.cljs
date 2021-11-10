(ns app.app00c
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [com.fulcrologic.fulcro.application :as app]))

; video: Part 2
; 06:43

(defonce app (app/fulcro-app))

(defsc Person [this {:person/keys [id name] :as props}]
  {}
  (dom/div
    (dom/div "Name" name)))

(def ui-person (comp/factory Person {:keyfn :person/id}))

(defsc Sample [this {:keys [sample]}]
  {}
  (dom/div
    (ui-person sample)))

(comment
  (in-ns 'app.app0)
  (app/mount! app Sample "app")
  (reset! (::app/state-atom app)
    {:sample {:person/id 1
              :person/name "Joe"}})
  (app/schedule-render! app)
  ; check web page: Name: Joe
  (+ 1),)

