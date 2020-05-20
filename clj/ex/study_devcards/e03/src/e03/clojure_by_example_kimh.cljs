(ns e03.clojure-by-example-kimh
  (:require-macros
    [devcards.core :as dc :refer [defcard deftest]])
  (:require [sablono.core :as sab]))

(defcard first
  (sab/html [:div
             [:h1 "hello4"]]))

(defcard nosab
  [:h2 "nosab"])

(defcard
  "# basics
   ```
   \"h\"\n
   true
   (str \"each \" \"line \" \"is a\" \" form\")\n
   ``` ")

(defcard basics
  (sab/html
    [:div
     [:div "h"]
     [:div true]
     [:div (str "each " "line " "is a form")]]))

(defcard
  "
  ```
  (def a-binding \"is an assignment in other languages\")

  ```
  ")

(defcard
  (sab/html
    [:div (def a-binding "is an assignment in other languages")]))


