(ns p01.dumdom-cards
  (:require [dumdom.devcards :refer-macros [defcard]]
            [dumdom.core :as dumdom :refer [defcomponent]])
  (:require-macros
    [devcards.core :as dc]))

(dc/defcard second-card
  (str "hello devcards2"))

(defcomponent heading
  :on-render (fn [dom-node val old-val])
  [data]
  [:h2 {:style {:background "#000"}} (:text data)])

(defcomponent page [data]
  [:div
    [heading (:heading data)]
    [:p (:body data)]])

(defcard dumdom-card-02
  [page {:heading {:text "Hello world2"}}
        :body "This is a web page"])
