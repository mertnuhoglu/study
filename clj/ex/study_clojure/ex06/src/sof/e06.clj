(ns sof.e06)

; [clojure - Remove nil values from a map? - Stack Overflow](https://stackoverflow.com/questions/3937661/remove-nil-values-from-a-map)

(def record {:a 1 :b 2 :c nil})
(merge (for [[k v] record :when (not (nil? v))] {k v}))
; ({:a 1} {:b 2})

; I would like to have:
{:a 1, :b 2}

; a01
(apply merge (for [[k v] record :when (not (nil? v))] {k v})) ; {:a 1, :b 2}

; a02: kısa
(into {} (filter second record)) ; {:a 1, :b 2}

; Dont remove false values:
(into {} (remove (comp nil? second) record)) ; {:a 1, :b 2}

; a03: dissoc
; Using dissoc to allow persistent data sharing instead of creating a whole new map:
(apply dissoc
  record
  (for [[k v] record :when (nil? v)] k)) ; {:a 1, :b 2}

; a04: nested maps
; Here is one that works on nested maps:

(require '[clojure.walk :refer [postwalk]])
(defn remove-nils
  [m]
  (let [f (fn [[k v]] (when v [k v]))]
    (postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))
(remove-nils record) ; {:a 1, :b 2}
(remove-nils {:a 1 :b {:c 2 :d nil}}) ; {:a 1, :b {:c 2}}

; a05
(into {} (remove (fn [[k v]] (nil? v)) {:a 1 :b 2 :c nil})) ; {:a 1, :b 2}

; shorter
(into {} (remove #(nil? (val %)) {:a true :b false :c nil})) ; {:a true, :b false}

(into {} (filter #(not (nil? (val %))) {:a true :b false :c nil})) ; {:a true, :b false}

; a06: select-keys

(select-keys record (for [[k v] record :when (not (nil? v))] k)) ; {:a 1, :b 2}

; a07: reduce-kv

(reduce-kv 
  (fn [m key value]
     (if (nil? value)
       (dissoc m key)
       m))
  record
  record) ; {:a 1, :b 2}

; a08: hem map hem vector üzerinde çalışır:

(defn compact
  [coll]
  (cond
    (vector? coll) (into [] (filter (complement nil?) coll))
    (map? coll) (into {} (filter (comp not nil? second) coll))))

(compact record) ; {:a 1, :b 2}

; a09: reduce ile

(reduce (fn [m [k v]] (if (nil? v) m (assoc m k v))) {} record) ; {:a 1, :b 2}

; sırayı korumak için dissoc
(reduce (fn [m [k v]] (if (nil? v) (dissoc m k) m)) record record) ; {:a 1, :b 2}
