(ns devcards_official_examples.bmi01
  (:require 
    [sablono.core :as sab :include-macros true]
    [reagent.core :as reagent]
    [clojure.string :as string])
  (:require-macros
    [devcards.core :as dc :refer [defcard deftest]]))

(defn slider 
  [bmi-data param value min max]
  (sab/html
    [:div
      [:input {:type "range" :min min :max max
               :style {:width "100%"}
               :on-change (fn [e]
                            (swap! bmi-data assoc param (.-target.value e)))}]]))

     
(defn calc-bmi [bmi-data]
  (let [{:keys [height weight bmi] :as data} bmi-data
        h (/ height 100)]
    (assoc data :bmi (/ weight (* h h)))))

(defn bmi-component
  [bmi-data]
  (let
    [{:keys [height weight bmi]} (calc-bmi @bmi-data)]
    ;[{:keys [height weight bmi]} (calc-bmi {:height 170 :weight 80 :bmi 10})]
    (sab/html 
      [:div
       [:span (str "Your height: " (int height))]
       (slider bmi-data :height height 120 220)
       [:span (str "Your weight: " (int weight))]
       (slider bmi-data :weight weight 50 150)
       [:span (str "Your bmi " (int bmi))]
       (slider bmi-data :bmi bmi 10 50)])))

(defcard bmi-ornegi
  "bmi örneğini kendi başına yazmayı deneme s06a"
  (fn [bmi-data _] (bmi-component bmi-data))
  {:height 180 :weight 70}
  {:inspect-data true
   :frame true
   :history true})


