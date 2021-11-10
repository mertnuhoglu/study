(ns app.app00d
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [com.fulcrologic.fulcro.application :as app]))

; video: Part 2
; 07:17

(defonce app (app/fulcro-app))

(defsc Car [this {:car/keys [id model] :as props}]
  {}
  (dom/div
    "Model " model))

(def ui-car (comp/factory Car {:keyfn :car/id}))

(defsc Person [this {:person/keys [id name cars] :as props}]
  {}
  (dom/div
    (dom/div "Name: " name)
    (dom/h3 "Cars")
    (dom/ul
      (map ui-car cars))))

(def ui-person (comp/factory Person {:keyfn :person/id}))

(defsc Sample [this {:keys [sample]}]
  {}
  (dom/div
    (ui-person sample)))

(comment
  (in-ns 'app.app00d)
  (app/mount! app Sample "app")
  (reset! (::app/state-atom app)
    {:sample {:person/id 1
              :person/name "Joe"
              :person/cars [{:car/id 22
                             :car/model "escort"}]}})
  (app/schedule-render! app)
  ; check web page:
  ; Name: Joe
  ; Cars
  ; Model escort
  (+ 1),)
