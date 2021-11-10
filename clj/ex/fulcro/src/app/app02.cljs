(ns app.app02
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]))

(defsc Person [this {:person/keys [name age]}]
  (dom/div
    (dom/p "Name02: " name)
    (dom/p "Age02: " age)))
(def ui-person (comp/factory Person))
(defsc Root [this props]
  (dom/div
    (ui-person {:person/name "Joe" :person/age 23})))
