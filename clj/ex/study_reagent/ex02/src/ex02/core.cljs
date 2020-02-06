(ns ^:figwheel-hooks ex02.core
    (:require [reagent.core :as r]))
(defn my-component []
  [:p "My first React component"])
(defn ^:export main []
  (r/render 
    [my-component]
    (.getElementById js/document "app")))
(main)
