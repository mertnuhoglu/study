(ns simpleexample.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

(defn timer-component []
  (let [seconds-elapsed (r/atom 0)]
    (fn []
      (js/setTimeout #(swap! seconds-elapsed inc) 1000)
      [:div
       "Seconds Elapsed: " @seconds-elapsed])))

(def click-count (r/atom 0))

(defn counting-component []
  [:div
   "The atom " [:code "click-count"] " has value: "
   @click-count ". "
   [:input {:type "button" :value "Click me!"
            :on-click #(swap! click-count inc)}]])

(defn atom-input [value]
  [:input {:type "text"
           :value @value
           :on-change #(reset! value (-> % .-target .-value))}])

(defn shared-state []
  (let [val (r/atom "foo")]
    (fn []
      [:div
       [:p "The value is now: " @val]
       [:p "Change it here: " [atom-input val]]])))

(defn simple-example []
  [:div
   [:p "Merhaba"]
   [counting-component]
   [timer-component]
   [shared-state]
   ])

(defn ^:export run []
  (rdom/render [simple-example] (js/document.getElementById "app")))
