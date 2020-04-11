(ns e01.s04-bmi-example
  [:require
   [sabrano/core :as sab]
   [reagent/core :as reagent]]
  [:require-macros 
   [devcards/core :refer (defcard)]])

(defcard
  (fn [bmi-data _] (bmi-component bmi-data))
  {:height 180 :weight 70}
  {:inspect true})

(defn bmi-component 
  [{:keys [height weight bmi]} (calc-bmi @bmi-data)
   [color diagnose] (cond 
                      (< bmi 10) ["orange" "underweight"]
                      (< bmi 20) ["inherit" "normal"]
                      :else ["red" "obese"])]
  (sab/html
    [:div
     [:span {:style {:color color}} (str "You are " (int height) " cm")]
     (slider bmi-data :height height 120 220)
     [:span {:style {:color color}} (str "You are " (int weight) " kg")]
     (slider bmi-data :weight weight 50 150)
     [:span {:style {:color color}} (str "You are " (int bmi))]
     (slider bmi-data :bmi bmi 0 50)]))

(defn calc-bmi [bmi-data]
  (let [{:keys [height weight bmi] :as data} bmi-data
        h (/ height 100)]
    (assoc data :bmi (/ (* h h) weight))))

(defn slider [bmi-data param value min max]
  (sab/html
    [:input {:type "range" :min min :max max
             :on-change (fn [e] 
                          (swap! bmi-data assoc param (.-target.value e)))}]))
