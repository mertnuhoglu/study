(ns sof.sof35)

; [java - Difference between two maps - Stack Overflow](https://stackoverflow.com/questions/3387155/difference-between-two-maps)

;i.e. I am looking for the most efficient way to a write a function like:

;(map-difference
;  {:a 1, :b nil, :c 2, :d 3}
;  {:a 1, :b "Hidden", :c 3, :e 5})
;
;=> {:b nil, :c 2, :d 3, :e nil}

(defn map-difference [m1 m2]
  (loop [m (transient {})
         ks (concat (keys m1) (keys m2))]
    (if-let [k (first ks)]
      (let [e1 (find m1 k)
            e2 (find m2 k)]
        (cond (and e1 e2 (not= (e1 1) (e2 1))) (recur (assoc! m k (e1 1)) (next ks))
              (not e1) (recur (assoc! m k (e2 1)) (next ks))
              (not e2) (recur (assoc! m k (e1 1)) (next ks))
              :else    (recur m (next ks))))
      (persistent! m))))

