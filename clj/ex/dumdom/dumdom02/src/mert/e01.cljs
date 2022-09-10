(ns mert.e01
  (:require [dumdom.core :as dumdom]))

; Source: [ClojureScript: Fun and productive web development with next level tooling - Christian Johansen - YouTube](https://www.youtube.com/watch?v=yFVk3D76wQw)

(defonce store
  (atom
    {:shopping-list {:week 45
                     :groceries [{:text "banana"}
                                 {:text "Orange"}
                                 {:text "Covid19 test"}]}}))

(defn render [data] 
  (dumdom/render
    [:h1.h1 {:style {:color "red"}} "Hello NDC2"]
    (js/document.getElementById "app")))

(render @store)

