(ns record)

; from: [In Clojure, records are wacky maps | Yehonathan Sharvit](https://blog.klipse.tech/clojurescript/2016/04/25/records-wacky-maps.html)

(defrecord A [x y])
(def a (A. 1 2))
(def aa (map->A {:x 1 :y 2}))
(def aaa (->A 1 2))
[a aa aaa]
;;=> [{:x 1, :y 2} {:x 1, :y 2} {:x 1, :y 2}]

; They are equal
(= a aa aaa)
;;=> true

; They have the same hash code
(map hash [a aa aaa])
;;=> (1172688077 1172688077 1172688077)

; Behave like maps
[(:x a) (get a :z "n/a")]
;;=> [1 "n/a"]
(keys a)
;;=> (:x :y)
(count a)
;;=> 2
(map (fn [[k v]] [k (inc v)]) a)
;;=> ([:x 2] [:y 3])

