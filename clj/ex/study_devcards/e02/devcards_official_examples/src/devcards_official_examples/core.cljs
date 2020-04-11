(ns devcards_official_examples.core
  (:require
   [devcards_official_examples.headings]
   [devcards_official_examples.mkdn-pprint-source]
   [devcards_official_examples.bmi]
   [devcards_official_examples.bmi01]
   [devcards_official_examples.bmi02]
   [devcards_official_examples.bmi03]
   [devcards_official_examples.bmi04]
   [devcards_official_examples.bmi04b]
   [devcards_official_examples.bmi04c]
   [devcards_official_examples.bmi04d]
   [devcards_official_examples.bmi04e]
   [devcards_official_examples.bmi04f]
   [devcards_official_examples.bmi04g]
   [devcards_official_examples.testing]

   [sablono.core :as sab :include-macros true])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

(defcard first-card
  (sab/html [:div
             [:h1 "This is your first devcard!"]]))

(defn main []
  ;; conditionally start the app based on whether the #main-app-area
  ;; node is on the page
  (if-let [node (.getElementById js/document "main-app-area")]
    (.render js/ReactDOM (sab/html [:div "This is working"]) node)))

(main)

;; remember to run lein figwheel and then browse to
;; http://localhost:3449/cards.html

