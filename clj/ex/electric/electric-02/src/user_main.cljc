(ns user-main
  (:require contrib.uri ; data_readers
            contrib.ednish
            [hyperfiddle.api :as hf]
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.history :as history]
            [demo.demo-index :as demos]

            demo.demo-1-hello-world
            demo.demo-2-toggle
            demo.demo-3-system-properties
            demo.demo-4-chat
            demo.demo-4-chat-extended
            demo.demo-4-webview
            demo.demo-5-todomvc
            demo.demo-todomvc-composed
            demo.demo-6-two-clocks
            demo.demo-explorer
            ;wip.demo-explorer2
            demo.demo-10k-dom-elements
            demo.demo-svg
            demo.todos-simple
            demo.seven-gui-1-counter
            demo.seven-gui-2-temperature-converter
            demo.seven-gui-4-timer
            demo.seven-gui-5-crud
            demo.demo-scrollview
            demo.demo-color
            demo.tic-tac-toe
            demo.blinker
            ;wip.demo-branched-route
    #_wip.hfql
            ;wip.tag-picker
            ;wip.teeshirt-orders

    ; this demo require `npm install`
    #_user.demo-reagent-interop

    ; these demos require extra deps alias
    #_wip.dennis-exception-leak
    #_wip.demo-stage-ui4
    #_wip.datomic-browser))


(e/defn NotFoundPage []
  (e/client (dom/h1 (dom/text "Page not found"))))

; todo: macro to auto-install demos by attaching clj metadata to e/defn vars?

(e/defn Pages [page]
  (e/server
    (case page
      `demo.demo-index/Demos demo.demo-index/Demos
      `demo.demo-index/Secrets demo.demo-index/Secrets
      `demo.demo-1-hello-world/HelloWorld demo.demo-1-hello-world/HelloWorld
      `demo.blinker/Blinker demo.blinker/Blinker
      ;::demos/hfql-teeshirt-orders wip.teeshirt-orders/App
      `demo.demo-explorer/DirectoryExplorer demo.demo-explorer/DirectoryExplorer
      ;::demos/explorer2 wip.demo-explorer2/App
      ;::demos/demo-10k-dom-elements demo.demo-10k-dom-elements/App ; todo too slow to unmount, crashes
      ;::demos/router-recursion wip.demo-branched-route/App
      ;::demos/tag-picker wip.tag-picker/App
      ::demos/toggle demo.demo-2-toggle/App
      ::demos/system-properties demo.demo-3-system-properties/App
      ::demos/chat demo.demo-4-chat/App
      ::demos/chat-extended demo.demo-4-chat-extended/App
      ::demos/webview demo.demo-4-webview/App
      ::demos/todos-simple demo.todos-simple/Todo-list ; css fixes
      ::demos/todomvc demo.demo-5-todomvc/App
      ::demos/todomvc-composed demo.demo-todomvc-composed/App
      ::demos/color demo.demo-color/App
      ::demos/two-clocks demo.demo-6-two-clocks/App
      ::demos/infinite-scroll demo.demo-scrollview/Demo
      ::demos/seven-guis-counter demo.seven-gui-1-counter/Counter
      ::demos/seven-guis-temperature-converter demo.seven-gui-2-temperature-converter/App
      ::demos/seven-guis-timer demo.seven-gui-4-timer/Timer
      ::demos/seven-guis-crud demo.seven-gui-5-crud/App
      ::demos/tic-tac-toe demo.tic-tac-toe/App
      `demo.demo-svg/SVG demo.demo-svg/SVG
      ;`demo.demo-reagent-interop/ReagentInterop (when react-available demo.demo-reagent-interop/ReagentInterop)
      ;::demos/dennis-exception-leak wip.dennis-exception-leak/App2
      ;::demos/demo-stage-ui4 wip.demo-stage-ui4/Demo
      ;`wip.datomic-browser/DatomicBrowser wip.datomic-browser/DatomicBrowser
      NotFoundPage)))

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
