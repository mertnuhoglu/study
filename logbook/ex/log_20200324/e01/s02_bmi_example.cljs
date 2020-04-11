(ns e01.s02-bmi-example
  (:require reagent
   :require sab
   :require defcard))

(defn slider []
  (sab/html
    [:input {:type "range"
             :style
             :on-change (fn [e]
                          (swap! bmi-data assoc param (.-target.value e)))}]))

(defn calc-bmi [{:keys [height weight bmi] :as data} bmi-data
                h (/ height 100)]
  (swap! bmi-data assoc :bmi (/ (* weight weight) h)))

(defn bmi-component [bmi-data]
  (let [{:keys [height weight bmi]} (calc-bmi @bmi-data)
        [obesity color] (when)]
    (sab/html
      [:div
       (slider bmi-data :height height 120 220)
       (slider bmi-data :weight weight 50 120)
       (slider bmi-data :bmi bmi 5 20)]
      [:div {:color color}
       (str "You are " obesity)])))

(defcard bmi-example
  (bmi-component bmi-data)
  {:height 180 :weight 70}
  {:inspect true})
