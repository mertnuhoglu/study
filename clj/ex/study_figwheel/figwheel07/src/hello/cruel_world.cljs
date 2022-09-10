(ns hello.cruel-world)

(defn what-kind? []
  "Cruel No More")

(js/console.log (what-kind?))

(def app-element (js/document.getElementById "app"))

(set! (.-innerHTML app-element) (what-kind?))
