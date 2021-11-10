(ns app.app00f
  (:require
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [com.fulcrologic.fulcro.application :as app]
    [com.fulcrologic.fulcro.algorithms.merge :as merge]))

; video: Part 2
; Normalize the tree 01
; 14:40

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
  (in-ns 'app.app00f)
  (app/mount! app Sample "app")
  (reset! (::app/state-atom app) {})

  (merge/merge-component! app
    Person {:person/id 1
            :person/name "Joe"})

  (app/current-state app)
  ;=> #:person{:id {1 #:person{:id 1, :name "Joe"}}}

  ; the tree data is put into a table

  (merge/merge-component! app
    Person {:person/id 2
            :person/name "Sally"})

  (app/current-state app)
  ;=> #:person{:id {1 #:person{:id 1, :name "Joe"}, 2 #:person{:id 2, :name "Sally"}}}

  ; this function is accumulative

  (app/schedule-render! app)
  ; check web page:
  ; Name: Joe
  (+ 1),)
