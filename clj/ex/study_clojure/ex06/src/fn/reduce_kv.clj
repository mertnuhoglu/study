(ns fn.reduce-kv)

; [reduce-kv - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/reduce-kv)

; e01
;Let's assume you want to apply a function to a vector of maps,
;
;Input: [{:a 1 :b 2} {:a 3 :b 4}]
;
;such that all vals are incremented by 1.
;
;Result: [{:a 2 :b 3} {:a 4 :b 5}]
;
;An easy way to do so is using reduce-kv,

(def vector-of-maps [{:a 1 :b 2} {:a 3 :b 4}])

(defn update-map [m f]
  (reduce-kv
    (fn [m k v]
      (assoc m k (f v)))
    {}
    m))

(map #(update-map % inc) vector-of-maps)
;=> ({:b 3, :a 2} {:b 5, :a 4})

; e02
;; Swap keys and values in a map
(reduce-kv #(assoc %1 %3 %2) {} {:a 1 :b 2 :c 3})
;{1 :a, 2 :b, 3 :c}

; e03
;; Swap keys with values, only if values are not empty,
;; while turning values into proper keys

(defn swap [someMap]
  (reduce-kv (fn [m k v]
               (if (empty? v) m (assoc m (keyword v) (name k)))) {} someMap))

(swap {:a "ali" :b "baba"})
;=> {:ali "a", :baba "b"}
