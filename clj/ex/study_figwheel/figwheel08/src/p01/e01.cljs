(ns p01.e01)

(enable-console-print!)

(prn "hello world!")

(defn what-kind? []
  "Hello Figwheel")

(def app-element (js/document.getElementById "app"))

(set! (.-innerHTML app-element) (what-kind?))
