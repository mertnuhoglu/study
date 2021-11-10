(ns app.app00b
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [com.fulcrologic.fulcro.application :as app]))

; video: Part 2
; 05:45

(defsc Sample [this props]
  {}
  (dom/div (str props)))

(defonce app (app/fulcro-app))


(defsc Person [this {:person/keys [id name] :as props}]
  {}
  (dom/div
    (dom/div "Name" name)))

(def ui-person (comp/factory Person {:keyfn :person/id}))

(comment
  (in-ns 'app.app00b)
  (app/mount! app Sample "app")

  (reset! (::app/state-atom app)
    {:sample {:person/id 1
              :person/name "Joe"}})
  (app/schedule-render! app)
  ; check web page: {:sample {:person/id 1, :person/name "Joe"}}
  (+ 1),)

