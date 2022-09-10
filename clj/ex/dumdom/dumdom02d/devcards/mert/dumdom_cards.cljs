(ns mert.dumdom-cards
  (:require [dumdom.devcards :refer-macros [defcard]]
            [dumdom.core :as dumdom :refer [defcomponent]]
            [clojure.string :as str])
  (:require-macros
    [devcards.core :as dc]))

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

(defcard basic-input
  (CompletionInput {}))

(defcard input-with-placeholder
  (CompletionInput 
    {:placeholder "Type here"}))

(defcard input-with-value
  (CompletionInput 
    {:placeholder "Type here"
     :value "Banana"}))

(defcard input-with-completion
  (CompletionInput 
    {:value "Or"
     :completion "Orange"}))

(defcard input-with-live-completion
  (fn [store] 
    (CompletionInput 
      (let [v (:value store)]
        {:value v
         :completion (when-not (empty? v) 
                       (->> (:completions @store)
                            (filter #(str/starts-with? % v))
                            first))
         :onInput (fn [e]
                    (swap! store assoc :value (.. e -target -value)))})))
  {:value "Pizza"
   :completions ["Banana"
                 "Banndana"
                 "Bazooka"]})
