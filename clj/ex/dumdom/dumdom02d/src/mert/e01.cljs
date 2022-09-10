(ns mert.e01
  (:require [dumdom.core :refer [defcomponent] :as dumdom]
            [clojure.string :as str]
            [clojure.walk :as walk]
            [gadget.inspector :as gadget]))

; Source: [ClojureScript: Fun and productive web development with next level tooling - Christian Johansen - YouTube](https://www.youtube.com/watch?v=yFVk3D76wQw)

(defonce store
  (atom
    {:shopping-list {:week 45
                     :groceries [{:text "banana"}
                                 {:text "Orange"}
                                 {:text "Covid19 test"}]}}))

;; UI Components

(def store2 
  {:value "Pizza"
   :completions ["Banana"
                 "Banndana"
                 "Bazooka"]})

(defcomponent CompletionInput [{:keys [completion 
                                       placeholder
                                       value
                                       onInput]}]
  [:div {:style {:position "relative"}}
   (when completion
     [:input.input {:value completion}])
   [:input.input
    {:placeholder placeholder
     :value value
     :onInput onInput
     :style (when completion
              {:background "transparent"
               :position "absolute"
               :top 0
               :left 0
               :bottom 0
               :right 0})}]])

(defcomponent ListPageComponent [ui-data]
  [:div
   [:div  {:style {:display "flex"}
                 :align-items "center"
                 :margins 20}
    [:h1.h1 {:style {:margin-left 20}}
     (:title ui-data)]]
   [:div {:style {:margin 20}}
    (CompletionInput 
      (let [v (:value store2)]
        {:value v
         :completion (when-not (empty? v) 
                       (->> (:completions @store)
                            (filter #(str/starts-with? % v))
                            first))
         :onInput (fn [e]
                    (swap! store assoc :value (.. e -target -value)))}))]
   [:div {:style {:margin 20}}
    [:ul
     (for [{:keys [text]} (:items ui-data)]
       [:li text])]]])

(defn find-completion [completions v]
  (when-not (empty? v)
    (->> completions
         (filter #(str/starts-with? % v))
         first)))

(defn prepare-ui-data [state]
  {:title (str "Week " (-> state :shopping-list :week))
   :items (-> state :shopping-list :groceries)
   :input {:placeholder "Ingredient"
           :value (:current state)
           :completion (find-completion
                         (:completions state)
                         (:current state))
           :onInput [:save-in-store :current :event/target-val]}})

;; Machinery

(defn render [data] 
  (let [ui-data (prepare-ui-data data)]
    (gadget/inspect "UI Data" ui-data)
    (dumdom/render
      (ListPageComponent ui-data)
      (js/document.getElementById "app"))))

;; Bootup

(render @store)
(gadget/inspect "Store" store)
(add-watch store :render (fn [_ _ _ state]
                           (render state)))

;; ClojureScript

