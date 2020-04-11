(ns devcards_official_examples.bmi04c
  (:require
   [sablono.core :as sab :include-macros true]
   [reagent.core :as reagent]
   [clojure.string :as string])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

(defn calc-bmi [bmi-data]
  (let [{:keys [height weight bmi]} bmi-data]
    bmi-data))

(defn slider [bmi-data param value min max]
  (sab/html
    [:input {:type "range" :min min :max max
             :style {:width "100%"}}]))

(defn bmi-component [bmi-data]
  (let [{:keys [weight height bmi]} (calc-bmi @bmi-data)]
    (sab/html
     [:div 
      [:div
       [:span (str "Height: " (int height) "kg")]
       (slider bmi-data :height height 120 220)]
      [:div
       [:span (str "Weight: " (int weight) "kg")]
       (slider bmi-data :weight weight 30 220)]
      [:div
       [:span (str "Bmi " (int bmi) "kg")]
       (slider bmi-data :bmi bmi 10 50)]])))

(defcard bmi-test
  (fn [data-atom _] (bmi-component data-atom))
  {:height 180 :weight 80}
  {:inspect-data true
   :frame true
   :history true})

