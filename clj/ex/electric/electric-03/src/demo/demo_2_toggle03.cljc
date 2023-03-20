(ns demo.demo-2-toggle03
  (:require
    [hyperfiddle.electric :as e]
    [hyperfiddle.electric-dom2 :as dom]
    [hyperfiddle.electric-ui4 :as ui]))

; Video: mert-cljs-05-electric-reaktif-mantigi.mp4

#?(:clj (defonce !x (atom true)))
(e/def x (e/server (e/watch !x)))
(e/def y (e/server (not x)))
(e/def z (e/server (not y)))
#_(e/def z (e/server (e/watch (not y)))) ; Error

(e/defn App []
  (e/client
    (dom/h1 (dom/text "Effect Chain: x -> y -> z"))

    (dom/div
      (ui/button (e/fn [] (e/server (swap! !x not)))
        (dom/text "toggle")))

    (dom/div
      (dom/p (dom/text (str "x: " (e/server x)))))
    (dom/div
      (dom/p (dom/text (str "y: " (e/server y)))))
    (dom/div
      (dom/p (dom/text (str "z: " (e/server z)))))))
