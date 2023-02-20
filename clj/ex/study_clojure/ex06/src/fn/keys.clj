(ns fn.keys)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; [keys - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/keys)

;(keys map)
;Returns a sequence of the map's keys, in the same order as (seq map).

(keys {:a 1 :b 2})
;=> (:a :b)

(keys {:keys :and, :some :values})
;;=> (:keys :some)

(keys {})
;;=> nil

(keys nil)
;;=> nil
