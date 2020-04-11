(ns devcards_official_examples.bmi04
  (:require 
    [sablono.core :as sab]
    [reagent.core :as reagent])
  (:require-macros
    [devcards.core :as dc :refer [defcard]]))

(defn calc-bmi [bmi-data]
  (let [{:keys [height weight bmi]} bmi-data]
    {:weight weight}))

(defn bmi-component [bmi-data]
  (let [{:keys [height weight bmi]} (calc-bmi @bmi-data)]
    (sab/html
      [:div
       [:span (str "Your weight: " (int weight))]
       [:input]])))
               
(defcard bmi-test
  "Sıfırdan tekrar bakmadan bmi örneğini üretmeyi deneme"
  (fn [bmi-data _] (bmi-component bmi-data))
  {:height 180 :weight 80}
  {:inspect-data true
   :frame true
   :history true})
  
