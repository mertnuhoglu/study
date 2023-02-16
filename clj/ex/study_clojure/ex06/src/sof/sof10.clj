(ns sof.sof10)

; [hashmap - clojure convert lazy-seq to hash map - Stack Overflow](https://stackoverflow.com/questions/7034685/clojure-convert-lazy-seq-to-hash-map)

; How do I create a map from a lazySeq?

(def fields [:name :age :color])
(def values ["joe" 32 "red"])
(def record (interleave fields values))
(identity record) ; (:name "joe" :age 32 :color "red")
;; (get mymap :age)
;; 32

; a01:

(apply hash-map record) ; {:age 32, :color "red", :name "joe"}

; a02: Since you actually already have separate keys and values, I would suggest skipping the interleave step and instead writing

(zipmap fields values) ; {:name "joe", :age 32, :color "red"}

; Or if you have your heart set on into, you could

(into {} (map vector fields values)) ; {:name "joe", :age 32, :color "red"}

; a03: This isn't sensible at all, but since the original question wanted to use into with record:

(into {} (map vec (partition 2 record))) ; {:name "joe", :age 32, :color "red"}

