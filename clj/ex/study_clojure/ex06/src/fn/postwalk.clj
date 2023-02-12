(ns fn.postwalk)

(require '[clojure.walk :refer [postwalk]])

; [postwalk - clojure.walk | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.walk/postwalk)

;;example of removing namespaces from all keys in a nested data structure
(def m {:page/tags [{:tag/category "foo"}]})

(postwalk #(if (keyword? %) (keyword (name %)) %) m)
;=> {:tags [{:category "foo"}]}

