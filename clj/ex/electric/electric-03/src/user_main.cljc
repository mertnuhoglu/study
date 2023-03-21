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
            demo.demo-2-toggle01
            demo.demo-2-toggle02
            demo.demo-2-toggle03
            demo.demo-3-system-properties01
            demo.demo-3-system-properties02
            demo.demo-3-system-properties03
            demo.demo-3-system-properties04
            demo.demo-3-system-properties05
            demo.demo-3-system-properties06
            demo.demo-3-system-properties06b
            demo.demo-3-system-properties06c
            demo.demo-3-system-properties06d
            demo.demo-3-system-properties06e
            app.e03-search-map
            ; end
            ,))


(e/defn NotFoundPage []
  (e/client (dom/h1 (dom/text "Page not found"))))

; todo: macro to auto-install demos by attaching clj metadata to e/defn vars?

(e/defn Pages [page]
  (e/server
    (case page
      `app.e01-html-elements/HtmlElements app.e01-html-elements/HtmlElements
      `app.e02-collection/CollectionTable app.e02-collection/CollectionTable
      `app.e02b-collection/MapTable app.e02b-collection/MapTable
      ::demos/toggle01 demo.demo-2-toggle01/App
      ::demos/toggle02 demo.demo-2-toggle02/App
      ::demos/toggle03 demo.demo-2-toggle03/App
      ::demos/System-Properties01 demo.demo-3-system-properties01/App
      ::demos/System-Properties02 demo.demo-3-system-properties02/App
      ::demos/System-Properties03 demo.demo-3-system-properties03/App
      ::demos/System-Properties04 demo.demo-3-system-properties04/App
      ::demos/System-Properties05 demo.demo-3-system-properties05/App
      ::demos/System-Properties06 demo.demo-3-system-properties06/App
      ::demos/System-Properties06b demo.demo-3-system-properties06b/App
      ::demos/System-Properties06c demo.demo-3-system-properties06c/App
      ::demos/System-Properties06d demo.demo-3-system-properties06d/App
      ::demos/System-Properties06e demo.demo-3-system-properties06e/App
      ::demos/Search-Map app.e03-search-map/App
      `demo.demo-index/Demos demo.demo-index/Demos)))

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
