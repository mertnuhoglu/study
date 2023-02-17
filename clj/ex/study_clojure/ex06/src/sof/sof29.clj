(ns sof.sof29)

; [clojure - Destructure a map in another map? - Stack Overflow](https://stackoverflow.com/questions/4307835/destructure-a-map-in-another-map)

; I have:
(def m
  {:file "filename", :resolution {:width 1280, :height 1024}})

(defn to-directory-name [{{width :width height :height} :resolution}]
  (str width "x" height))

(to-directory-name m)
;=> "1280x1024"
