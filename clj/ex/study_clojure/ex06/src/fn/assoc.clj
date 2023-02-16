(ns fn.assoc)

; TODO: daha önce videoda bunları yapmıştık bulup ekleyelim.
; rfr: assoc_update.clj
; rfr: video/20230216-mert-clj-egzersiz-39.mp4
; rfr: fn/assoc-in.clj
; rfr: fn/update.clj

; (assoc! coll key val)
; (assoc! coll key val & kvs)
; When applied to a transient map, adds mapping of key
; (s) to
; val
; (s). When applied to a transient vector, sets the val at index.
; Note - index must be <=
; (count vector). Returns coll.

;; [assoc - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/assoc)

(assoc {} :key1 "value" :key2 "another value")
;;=> {:key2 "another value", :key1 "value"}

;; Here we see an overwrite by a second entry with the same key
(assoc {} :key1 "old value1" :key2 "value2" :key1 "value1" :key3 "value3")
;=> {:key1 "value1", :key2 "value2", :key3 "value3"}

;; We see a nil being treated as an empty map
(assoc nil :key1 4)
;;=> {:key1 4}

;; 'assoc' can be used on a vector (but not a list), in this way:
;; (assoc vec index replacement)
(assoc [1 2 3] 0 10)
;;=> [10 2 3]
(assoc [1 2 3] 2 '(4 6))
;;=> [1 2 (4 6)]
;; Next, 0 < index <= n, so the following will work
(assoc [1 2 3] 3 10)     ;;=> [1 2 3 10]
;; However, when index > n, an error is thrown
#_(assoc [1 2 3] 4 10)
;; java.lang.IndexOutOfBoundsException (NO_SOURCE_FILE:0)

;; From http://clojure-examples.appspot.com/clojure.core/assoc

;;transform a map´s values using reduce and assoc

(defn transform
  [coll]
  (reduce (fn [ncoll [k v]]
            (assoc ncoll k (* 10 v)))
    {}
    coll))

(transform {:a 1 :b 2 :c 3})
;;{:a 10 :b 20 :c 30}

;; You can also change multiple keys at once

(def my-map {:name "Victor" :age 27 :city "Barcelona"})

(assoc my-map :name "Tom" :age 47)

;; {:name "Tom", :age 47, :city "Barcelona"}

