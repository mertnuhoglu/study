(ns mert.e01
  (:require [dumdom.core :refer [defcomponent] :as dumdom]))

; Source: [ClojureScript: Fun and productive web development with next level tooling - Christian Johansen - YouTube](https://www.youtube.com/watch?v=yFVk3D76wQw)

(defonce store
  (atom
    {:shopping-list {:week 45
                     :groceries [{:text "banana"}
                                 {:text "Orange"}
                                 {:text "Covid19 test"}]}}))


(defcomponent ListPageComponent [ui-data]
  [:div
   [:div  {:style {:display "flex"}
                 :align-items "center"
                 :margins 20}
    [:h1.h1 {:style {:margin-left 20}}
     (:title ui-data)]]
   [:div {:style {:margin 20}}
    [:ul
     (for [{:keys [text]} (:items ui-data)]
       [:li text])]]])

(defn prepare-ui-data [state]
  {:title (str "Week " (-> state :shopping-list :week))
   :items (-> state :shopping-list :groceries)})

(defn render [data] 
  (dumdom/render
    (ListPageComponent (prepare-ui-data data))
    (js/document.getElementById "app")))

(render @store)
(add-watch store :render (fn [_ _ _ state]
                           (render state)))

