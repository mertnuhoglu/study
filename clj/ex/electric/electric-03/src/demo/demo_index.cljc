(ns ;^:dev/always ; force rebuild here? We don't understand why
  demo.demo-index
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.history :as router])) ; for link only

(def pages
  [`app.e01-html-elements/HtmlElements
   `app.e02-collection/CollectionTable
   `app.e02b-collection/MapTable])

(e/defn Demos []
  (e/client
    (dom/h1 (dom/text "Demos — Electric Clojure/Script"))
    (dom/p (dom/text "See source code in src-docs."))
    (e/for [k pages]
      (dom/div (router/link [k] (dom/text (name k)))))))