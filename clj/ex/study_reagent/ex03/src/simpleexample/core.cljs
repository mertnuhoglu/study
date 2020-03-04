(ns simpleexample.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

(defonce timer (r/atom (js/Date.)))

(defonce time-color (r/atom "#f34"))

(defonce time-updater (js/setInterval
                       #(reset! timer (js/Date.)) 1000))

(defn greeting [message]
  [:h1 message])

(defn clock []
  (let [time-str (-> @timer .toTimeString (clojure.string/split " ") first)]
    [:div.example-clock
     {:style {:color @time-color}}
     time-str]))

(defn color-input []
  [:div.color-input
   "Time color: "
   [:input {:type "text"
            :value @time-color
            :on-change #(reset! time-color (-> % .-target .-value))}]])

(defn hello-component [name]
  [:p "Hello, " name "!"])

(defn simple-component []
  [:div
   [hello-component "mert"]
   [:p "I am a component!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]])

(defn lister [items]
  [:ul
   (for [item items]
     ^{:key item} [:li "Item " item])])

(defn lister-user []
  [:div
   "Here is a list:"
   [lister (range 3)]])

(defn simple-example []
  [:div
   [greeting "Hello world2, it is now"]
   [clock]
   [color-input]
   [simple-component]
   [lister-user]])

(defn ^:export run []
  (rdom/render [simple-example] (js/document.getElementById "app")))
