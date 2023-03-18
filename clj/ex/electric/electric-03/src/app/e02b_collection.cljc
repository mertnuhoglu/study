(ns app.e02b-collection
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]))

(def students
  [{:name "Felix" :grade "5"}
   {:name "Villow" :grade "10"}])

(e/defn MapTable []
  (e/client

    (dom/table
      (dom/tr
        (dom/th (dom/text "Name"))
        (dom/th (dom/text "Grade")))
      (e/for [{:keys [name grade]} students]
        (dom/tr
         (dom/td (dom/text name))
         (dom/td (dom/text grade))))))

  ,)
