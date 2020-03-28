(ns devcards_official_examples.headings
  (:require
   #_[om.core :as om :include-macros true]
   [sablono.core :as sab :include-macros true])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

(defcard first-card
  (sab/html [:div
             [:h1 "This is your first devcard!"]]))

(defcard 
  "# Card 1: Markdown Test
   
   This is devcards card. Here I use `Markdown`")

(defcard card-with-name
  "# Card 2: Card With A Name
   
   I set a name to this card. 
   
   You can see it because of devcard config parameter: `heading`"
  {:heading false})

(defcard no-heading-3
  "# Card 3: heading is set to false"
  (sab/html [:div "This card has a name but it is not shown"])
  {}
  {:heading false})

(defcard no-heading-3b
  "# Card 3b: Empty Object of Focus as `()`"
  ()
  {}
  {:heading false})

(defcard no-heading-3c
  "# Card 3c: Empty Object of Focus as `{}`"
  {}
  {}
  {:heading false})

(defcard no-heading-3d
  "# Card 3d: Skipped Object of Focus"
  {}
  {:heading false})

(defcard no-heading-3e
  "# Card 3e: empty div object"
  (sab/html [:div])
  {}
  {:heading false})

(defn main []
  ;; conditionally start the app based on whether the #main-app-area
  ;; node is on the page
  (if-let [node (.getElementById js/document "main-app-area")]
    (.render js/ReactDOM (sab/html [:div "This is working"]) node)))

(main)

;; remember to run lein figwheel and then browse to
;; http://localhost:3449/cards.html

