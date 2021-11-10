(ns app.app00g
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [com.fulcrologic.fulcro.application :as app]
    [com.fulcrologic.fulcro.algorithms.merge :as merge]))

; video: Part 2
; Normalize the tree 02
; 15:50

(defonce app (app/fulcro-app))

(defsc Person [this {:person/keys [id name ] :as props}]
  {:query [:person/id :person/name]
   :ident :person/id}
  (dom/div
    (dom/div "Name: " name)))

(def ui-person (comp/factory Person {:keyfn :person/id}))

(defsc Sample [this {:root/keys [person]}]
  {:query [{:root/person (comp/get-query Person)}]}
  (dom/div
    (ui-person person)))

(comment
  (in-ns 'app.app00g)

  (comp/get-query Person)
  ;=> [:person/id :person/name]
  (meta (comp/get-query Person))
  ;=> {:component app.app00g/Person, :queryid "app.app00g/Person"}
  ; metadata of query knows from which component it comes from

  (+ 1),)
