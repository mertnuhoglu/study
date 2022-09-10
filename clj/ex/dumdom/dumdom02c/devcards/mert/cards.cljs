(ns ^:figwheel-hooks mert.cards
  (:require [devcards.core]
            [mert.dumdom-cards])
  (:require-macros
    [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

(defcard first-card
  (str "hello devcards"))

(defn render []
  (devcards.core/start-devcard-ui!))

(defn ^:after-load render-on-reload []
  (render))


(render)


