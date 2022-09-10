(ns mert.e01
  (:require [dumdom.core :refer [defcomponent] :as dumdom]))

; Source: [ClojureScript: Fun and productive web development with next level tooling - Christian Johansen - YouTube](https://www.youtube.com/watch?v=yFVk3D76wQw)

(defonce store
  (atom
    {:shopping-list {:week 45
                     :groceries [{:text "banana"}
                                 {:text "Orange"}
                                 {:text "Covid19 test"}]}}))


(defcomponent ListPageComponent [data]
  [:h1.h1 {:style {:color "red"}} "Hello NDC3"])

(defn render [data] 
  (dumdom/render
    (ListPageComponent data)
    (js/document.getElementById "app")))

(render @store)
(add-watch store :render (fn [_ _ _ state]
                           (render state)))

