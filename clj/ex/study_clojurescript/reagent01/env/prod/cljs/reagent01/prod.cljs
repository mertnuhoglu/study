(ns reagent01.prod
  (:require [reagent01.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
