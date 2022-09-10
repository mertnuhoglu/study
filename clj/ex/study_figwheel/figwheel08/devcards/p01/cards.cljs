(ns ^:figwheel-hooks p01.cards
  (:require [devcards.core]))

(enable-console-print!)

(defn render []
  (devcards.core/start-devcard-ui!))

(defn ^:after-load render-on-reload []
  (render))

(render)

