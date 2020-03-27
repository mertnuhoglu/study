(ns hello-seymore.components
  (:require [sablono.core :as sab]))

(defn like-seymore [data]
  (sab/html 
    [:div
     [:h1 "Seymore's likes: " (:likes @data)]
     [:div 
      [:a 
       {:href "#"
        :onClick #(swap! data update-in [:likes] inc)}
       "Thumbs up"]]]))
