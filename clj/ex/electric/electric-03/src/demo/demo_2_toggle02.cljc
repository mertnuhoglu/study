(ns demo.demo-2-toggle02
  (:require
    [hyperfiddle.electric :as e]
    [hyperfiddle.electric-dom2 :as dom]
    [hyperfiddle.electric-ui4 :as ui]))

; A stateful app with a server-side counter
; Electric functions are reactive and incrementally maintained,
; which means that when you make a small change to the state,
; the functions will recompute and you'll get a small adjustment to the DOM

#?(:clj (defonce !x (atom true))) ; server state
(e/def x (e/server (e/watch !x))) ; reactive signal derived from reference

(e/defn App []
  (e/client
    (dom/h1 (dom/text "Toggle"))

    (dom/div
      (ui/button (e/fn [] (e/server (swap! !x not)))
        (dom/text "toggle client/server"))
      (dom/code
        (dom/text
          "    (ui/button (e/fn [] (e/server (swap! !x not)))\n      (dom/text \"toggle client/server\"))\n")))

    (dom/div
      (dom/p
        (dom/text "Number type is: "
          (if (e/server x)
            (e/client (pr-str (type 1)))                     ; javascript number type
            (e/server (pr-str (type 1))))))
      (dom/code
        (dom/text
          "    (if (e/server x)\n          (e/client (pr-str (type 1))) \n          (e/server (pr-str (type 1))))")))                 ; java number type

    (dom/div
      (dom/p (dom/text (str "x: " (e/server x))))
      (dom/code
        (dom/text
          "(dom/p (dom/text (str \"x: \" (e/server x)))))))\n")))))
