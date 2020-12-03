(ns hello
  (:require [hello-time :as ht]))

(defn run [opts]
  (println "Hello world, the time is" (ht/time-str (ht/now))))

