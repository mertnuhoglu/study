(ns fn.flatten)

; (flatten x) #nclk/çok-önemli
;Takes any nested combination of sequential things (lists, vectors,
;etc.) and returns their contents as a single, flat lazy sequence.
;(flatten nil) returns an empty sequence.

; [flatten - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/flatten)

(flatten [1 [2 3]])
;(1 2 3)

(flatten '(1 2 3))
;(1 2 3)

; burada liste içinde vektör içinde liste var, onu da tek seviye seq haline getiriyor
(flatten '(1 2 [3 (4 5)]))
;(1 2 3 4 5)

(flatten nil)
()

; Attention with stuff which is not a sequence

; primitife de boş dönüyor
(flatten 5)
()

(flatten {:name "Hubert" :age 23})
()

; Workaround for maps

; seq içine map koyarsan, mapi seq haline çeviriyor
(flatten (seq {:name "Hubert" :age 23}))
;(:name "Hubert" :age 23)

; seq haline çevrilince artık key-value eşleştirmesi kayboluyor:
(:name (flatten (seq {:name "Hubert" :age 23})))
;=> nil
(second (flatten (seq {:name "Hubert" :age 23})))
;=> "Hubert"
