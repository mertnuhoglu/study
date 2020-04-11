(ns devcards_official_examples.bmi04b
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
    [:input {:type "range" :min min :max max}]))

(defn bmi-component [bmi-data]
  (let [{:keys [weight height bmi]} (calc-bmi @bmi-data)]
    (sab/html
     [:div 
       [:span (str "Weight: " (int weight) "kg")]
       (slider bmi-data :weight weight 30 220)])))

(defcard bmi-test
  (fn [data-atom _] (bmi-component data-atom))
  {:height 180 :weight 80}
  {:inspect-data true
   :frame true
   :history true})

