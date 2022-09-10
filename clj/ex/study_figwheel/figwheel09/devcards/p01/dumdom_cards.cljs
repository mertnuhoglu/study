(ns p01.dumdom-cards
  (:require-macros
    [devcards.core :as dc :refer [defcard deftest]]))

(defcard second-card
  (str "hello devcards2"))

