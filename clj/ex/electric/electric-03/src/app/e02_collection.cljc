(ns app.e02-collection
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]))

(e/defn CollectionTable []
  (e/client

    (dom/h1 (dom/text "Table Rows as a Collection"))

    (dom/table
      (dom/tr
        (dom/th (dom/text "th 1"))
        (dom/th (dom/text "th 2")))
      (dom/tr
        (dom/td (dom/text "td 1.1"))
        (dom/td (dom/text "td 1.2")))
      (e/for [k [10 30]]
        (dom/tr
         (dom/td (dom/text k))
         (dom/td (dom/text (str "td " k)))))

      ; for comprehension ile bir elementi programatik olarak çoğaltabiliriz
      (dom/p (dom/text "div çoğaltma:"))
      (e/for [k [10 20]]
        (dom/div (dom/text k)))

      (dom/p (dom/text "Şuna denktir:"))
      (dom/div (dom/text 10))
      (dom/div (dom/text 20))))

  ,)
    ; end,))

(comment
  (map #(* 2 %) [10 30 50])

  (defn generate-row [data1 data2]
    (e/client
      (dom/tr
        (dom/td (dom/text data1))
        (dom/td (dom/text data2)))))
  (generate-row 20 30))

  ; end,)
