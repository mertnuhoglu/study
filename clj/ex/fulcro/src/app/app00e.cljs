(ns app.app00e
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [com.fulcrologic.fulcro.application :as app]))

; video: Part 2
; Normalize the tree 02
; 12:20

(defonce app (app/fulcro-app))

(defsc Person [this {:person/keys [id name ] :as props}]
  {:query [:person/id :person/name]
   :ident :person/id}
  (dom/div
    (dom/div "Name: " name)))

(def ui-person (comp/factory Person {:keyfn :person/id}))

(defsc Sample [this {:keys [sample]}]
  {}
  (dom/div
    (ui-person sample)))

(comment
  (in-ns 'app.app00e)
  (app/mount! app Sample "app")
  (reset! (::app/state-atom app)
    {:sample {:person/id 1
              :person/name "Joe"}})

  (app/schedule-render! app)
  ; check web page:
  ; Name: Joe
  (+ 1),)
