(ns mert.debug01
  (:require [clojure.pprint :as pp]))

;; Source: [(450) Debugging Clojure with Conjure and Neovim - YouTube](https://www.youtube.com/watch?v=uP9iSEh7dxg)

(defn run [opts]
  (pp/pprint opts)
  (println "Hello" #break (:x opts)))

(comment
  (run {:x 10}))
