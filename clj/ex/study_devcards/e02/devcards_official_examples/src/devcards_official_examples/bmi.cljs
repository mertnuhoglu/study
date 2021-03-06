(ns devcards_official_examples.bmi
  (:require
   [sablono.core :as sab :include-macros true]
   [reagent.core :as reagent]
   [clojure.string :as string])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

;; code from the reagent page adapted to plain reagent
(defn calc-bmi [bmi-data]
  (let [{:keys [height weight bmi] :as data} bmi-data
        h (/ height 100)]
    (if (nil? bmi)
      (assoc data :bmi (/ weight (* h h)))
      (assoc data :weight (* bmi h h)))))

(defn slider [bmi-data param value min max]
  (sab/html
   [:input {:type "range" :value value :min min :max max
            :style {:width "100%"}
            :on-change (fn [e]
                         (swap! bmi-data assoc param (.-target.value e))
                         (when (not= param :bmi)
                           (swap! bmi-data assoc :bmi nil)))}]))

(defn bmi-component [bmi-data]
  (let [{:keys [weight height bmi]} (calc-bmi @bmi-data)
        [color diagnose] (cond
                          (< bmi 18.5) ["orange" "underweight"]
                          (< bmi 25) ["inherit" "normal"]
                          (< bmi 30) ["orange" "overweight"]
                          :else ["red" "obese"])]
    (sab/html
     [:div 
      [:h3 "BMI calculator"]
      [:div
       [:span (str "Height: " (int height) "cm")]
       (slider bmi-data :height height 100 220)]
      [:div
       [:span (str "Weight: " (int weight) "kg")]
       (slider bmi-data :weight weight 30 150)]
      [:div
       [:span (str "BMI: " (int bmi) " ")]
       [:span {:style {:color color}} diagnose]
       (slider bmi-data :bmi bmi 10 50)]])))

(defcard bmi-calculator
  "*Code taken from the Reagent readme.*"
  (fn [data-atom _] (bmi-component data-atom))
  {:height 180 :weight 80}
  {:inspect-data true
   :frame true
   :history true})

