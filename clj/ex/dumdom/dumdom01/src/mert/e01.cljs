(ns mert.e01)

(require '[dumdom.core :as dumdom :refer [defcomponent]])

(defcomponent heading
  :on-render (fn [dom-node val old-val])
  [data]
  [:h2 {:style {:background "#000"}} (:text data)])

(defcomponent page [data]
  [:div
    [heading (:heading data)]
    [:p (:body data)]])

(dumdom/render
 [page {:heading {:text "Hello world"}
        :body "This is a web page"}]
 (js/document.getElementById "app"))

