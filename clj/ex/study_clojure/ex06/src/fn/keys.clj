(ns fn.keys)

; [keys - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/keys)

;(keys map)
;Returns a sequence of the map's keys, in the same order as (seq map).

(keys {:keys :and, :some :values})
;;=> (:keys :some)

(keys {})
;;=> nil

(keys nil)
;;=> nil
