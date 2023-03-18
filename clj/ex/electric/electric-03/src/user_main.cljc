(ns user-main
  (:require contrib.uri ; data_readers
            contrib.ednish
            [hyperfiddle.api :as hf]
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.history :as history]
            [demo.demo-index :as demos]

            app.e01-html-elements
            app.e02-collection
            app.e02b-collection
            ; end
            ,))


(e/defn NotFoundPage []
  (e/client (dom/h1 (dom/text "Page not found"))))

; todo: macro to auto-install demos by attaching clj metadata to e/defn vars?

(e/defn Pages [page]
  (e/server
    (case page
      `demo.demo-index/Demos demo.demo-index/Demos
      `app.e01-html-elements/HtmlElements app.e01-html-elements/HtmlElements
      `app.e02-collection/CollectionTable app.e02-collection/CollectionTable
      `app.e02b-collection/MapTable app.e02b-collection/MapTable)))

(e/defn Main []
  (binding [history/encode contrib.ednish/encode-uri
            history/decode #(or (contrib.ednish/decode-path % hf/read-edn-str)
                              [`demo.demo-index/Demos])]
    (history/router (history/HTML5-History.)
      (set! (.-title js/document) (str (clojure.string/capitalize (name (first history/route))) " - Hyperfiddle"))
      (binding [dom/node js/document.body]
        (dom/pre (dom/text (contrib.str/pprint-str history/route)))
        (let [[page & args] history/route]
          (e/server (new (Pages. page #_args))))))))
