(ns mert.hodur02-visualizer
  (:require [hodur-engine.core :as engine]
            [hodur-visualizer-schema.core :as visualizer]))

;; run with:
;; clojure -m hodur-visualizer-schema.main mert.hodur02-visualizer

(def meta-db (engine/init-schema
              '[Person2
                [^String first-name
                 ^String last-name
                 ^Gender gender]

                ^:enum
                Gender
                [MALE FEMALE IRRELEVANT]]))

(-> meta-db
    visualizer/schema
    visualizer/apply-diagram!)

