(ns clj_higher_order_funs)

; Functions as Arguments

(defn double-op
  [f a b]
  (* 2 (f a b)))

(def double-*
  (partial double-op *))
(double-* 3 5)

; Function Literals

(def bands [
            {:name "Brown Beaters"   :genre :rock}
            {:name "Sunday Sunshine" :genre :blues}
            {:name "Foolish Beaters" :genre :rock}
            {:name "Monday Blues"    :genre :blues}
            {:name "Friday Fewer"    :genre :blues}
            {:name "Saturday Stars"  :genre :jazz}
            {:name "Sunday Brunch"   :genre :jazz}])

(def rock-bands
  (filter
    (fn [band] (= :rock (:genre band)))
    bands))
; using function literal:
(def rock-bands (filter #(= :rock (:genre %))) bands)
(rock-bands) ; Error
rock-bands

(filter even? (range 5))
bands
(filter nil? bands)
(filter some? bands)
(filter #(= "Friday Fewer" (:name %)) bands)
rock-bands

; Functions Returning Functions and Closures

(defn adder [x]
  (fn [a] (+ x a)))
(def add-five (adder 5)) ; adder is closure
(add-five 100)
