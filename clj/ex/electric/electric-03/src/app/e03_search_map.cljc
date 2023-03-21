(ns app.e03-search-map
  (:require
    [clojure.string :as str]
    [hyperfiddle.electric :as e]
    [hyperfiddle.electric-dom2 :as dom]
    [hyperfiddle.electric-ui4 :as ui]))

(e/defn App []
  (e/client
    (dom/h1 (dom/text "Search Map"))
    (let [!search (atom "")
          search (e/watch !search)]
      (dom/input
        search
        (e/fn [v]
          (println "ali")
          (reset! !search v))
        (dom/props {:type "search"})))))
