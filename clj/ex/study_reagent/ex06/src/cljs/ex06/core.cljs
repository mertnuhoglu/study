(ns ex06.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello Chestnut2!"}))

(defn greeting []
  [:div
   [:h1 (:text @app-state)]
   [:div "selam 3"]
   [:div (:text @app-state)]
   ]
  )

(defn render []
  (reagent/render [greeting] (js/document.getElementById "app")))
