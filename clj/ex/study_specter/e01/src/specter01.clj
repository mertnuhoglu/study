(ns clojure_by_example_kimh
  (:use [com.rpl.specter])
  (:require [clojure.string :as str]))

; ex: Increment every even number nested within map of vector of maps id=g_11441

(def data {:a [{:aa 1 :bb 2}
               {:cc 3}]
           :b [{:dd 4}]})

;; Manual Clojure
(defn map-vals [m afn]
  (->> m (map (fn [[k v]] [k (afn v)])) (into (empty m))))

(map-vals data
  (fn [v]
    (mapv
      (fn [m]
        (map-vals
          m
          (fn [v] (if (even? v) (inc v) v))))
      v)))
;; => {:a [{:aa 1, :bb 3} {:cc 3}], :b [{:dd 5}]}

;; Specter
(transform [MAP-VALS ALL MAP-VALS even?] inc data)
;; => {:a [{:aa 1, :bb 3} {:cc 3}], :b [{:dd 5}]}

(select [MAP-VALS ALL MAP-VALS even?] data)
;; => [2 4]

(select [MAP-VALS ALL MAP-VALS] data)
;; => [1 2 3 4]

(select [MAP-VALS ALL] data)
;; => [{:aa 1, :bb 2} {:cc 3} {:dd 4}]

(select [MAP-VALS] data)
;; => [[{:aa 1, :bb 2} {:cc 3}] [{:dd 4}]]

; ex: Append a sequence of elements to a nested vector id=g_11440

(def data {:a [1 2 3]})

;; Manual Clojure
(update data :a (fn [v] (into (if v v []) [4 5])))
;; => {:a [1 2 3 4 5]}

;; Specter
(setval [:a END] [4 5] data)
;; => {:a [1 2 3 4 5]}

(select [:a END] data)
;; => [[]]

(select [:a] data)
;; => [[1 2 3]]


; ex: Increment the last odd number in a sequence id=g_11442


(def data [1 2 3 4])

;; Manual Clojure
(let [idx (reduce-kv (fn [res i v] (if (odd? v) i res)) nil data)]
  (if idx (update data idx inc) data))
;; => [1 2 4 4]

;; Specter
(transform [(filterer odd?) LAST] inc data)
;; => [1 2 4 4]

(select [(filterer odd?) LAST] data)
;; => [3]

(select [(filterer odd?)] data)
;; => [[1 3]]

(transform [(filterer odd?)] identity data)
;; => [1 2 3 4]
