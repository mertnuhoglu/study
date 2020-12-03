(ns practicalli.what-time-is-it
  (:require [java-time :as time]))

(defn -main []
  (println "The time according to Clojure java-time is:"
           (time/local-date-time)))

