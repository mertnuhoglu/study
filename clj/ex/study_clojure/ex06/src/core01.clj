(ns core01
  (:require [clojure.string :as str]))

;; printing https://clojuredocs.org/clojure.core/pr id=g_11321

(println "ali")
(print "ali")
(prn "ali")
(pr "ali")

(println [1 2 3])
(prn [1 2 3])

(pr ['a :b "\n" \space "c"])
;; [a :b "\n" \space "c"]nil

(print ['a :b "\n" \space "c"])
;; [a :b
;;  c]nil


; variadic function id=g_11358

(defn f [x & xs]
  (str x xs))
;; => #'user/f
(f 1 2 3)
;; => "1(2 3)"

; slurp: read file url id=g_11404
; https://clojuredocs.org/clojure.core/slurp

(spit "tmp.txt" "test")
(slurp "tmp.txt")

; Threading macro to shorten code

(-> {:a {:b 3}}
    :a
    :b)
;; => 3

; destructuring id=g_11408
; ref: Destructuring <url:file:///~/projects/study/clj/book_clojure_practicalli.md#r=g_11407>

(let [[a b & c :as d] [1 2 3 4]] [a b])
;; => [1 2]

(let [[a b & c :as d] [1 2 3 4]] [c])
;; => [(3 4)]

(let [[a b & c :as d] [1 2 3 4]] [d])
;; => [[1 2 3 4]]

(let [{a :a, c :c}  {:a 5 :c 6}]
  [a c])
;; [5 6]
(let [{a :a, :as m} {:a 2 :b 3}]
  [a m])
;; => [2 {:a 2, :b 3}]

; record id=g_11410
; ref: If several maps have keys in common, create a record: <url:file:///~/projects/study/clj/book_programming_in_clojure.md#r=g_11411>

(defrecord Book [title author])
(->Book "title01" "author01")
;; => #user.Book{:title "title01", :author "author01"}

; complement id=g_11434
; ref: complement <url:file:///~/projects/study/clj/study_clojure.md#r=g_11433>)

(def not-empty? (complement empty?))
(not-empty? [])    ;;=> false
(not-empty? [1 2]) ;;=> true

; keep  id=g_11436
; ref: 	keep  <url:file:///~/projects/study/clj/study_clojure.md#r=g_11435>

(keep #(if (odd? %) %) (range 4))
;;=> (1 3 5 7 9)

(map #(if (odd? %) %) (range 4))
;;=> (nil 1 nil 3 nil 5 nil 7 nil 9)

(for [ x (range 4) :when (odd? x)] x)
;;=> (1 3 5 7 9)

(filter odd? (range 4))
;;=> (1 3 5 7 9)

; reduce-kv
; ref: reduce-kv <url:file:///~/projects/study/clj/study_clojure.md#r=g_11444>

(reduce-kv #(assoc %1 %3 %2) {} {:a 1 :b 2})
;; => {1 :a, 2 :b}

(def data [{:a 1 :b 2} {:a 3}])
(defn update-map [m f] 
  (reduce-kv 
    (fn [m k v] 
      (assoc m k (f v))) 
    {} m))

(map #(update-map % inc) data)
;; => ({:a 2, :b 3} {:a 4})

(reduce-kv 
  (fn [m k v] 
    (assoc m k (inc v))) 
  {} {:a 1 :b 2})
;; => {:a 2, :b 3}

; find the last odd number's index
; note: acc is the last odd number's index
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) nil {2 3})
;; => 2
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) nil {1 2, 2 3})
;; => 2
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) nil {1 2, 2 3, 3 4})
;; => 2
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) nil {1 2, 2 3, 3 4, 4 5})
;; => 4
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) [] {2 3})
;; => 2
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) [] {1 2, 2 3, 3 4})
;; => 2
(reduce-kv (fn [acc i v] (if (odd? v) i {})) [] {2 3})
;; => 2
(reduce-kv (fn [acc i v] (if (odd? v) i {})) [] {2 3, 3 4})
;; => {}
(reduce-kv (fn [acc i v] (if (odd? v) acc i)) [] {2 3, 3 4})
;; => 3
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) [] {2 3, 3 4})
;; => 2
(reduce-kv (fn [acc i v] (if (odd? v) i acc)) nil {2 4})
;; => nil


; for

; apply a function to hashmap entries:
(def data [{:a 1 :b 2} {:c 3}])
(let [m data] m)
;; => [{:a 1, :b 2} {:c 3}]
(let [[k v] data] [k v])
;; => [{:a 1, :b 2} {:c 3}]
(for [[k v] data] [k v]) ; error
(for [[k v] data] (inc v)) ; error
(for [[k v] (first data)] [k v]) 
;; => ([:a 1] [:b 2])
(for [[k v] (first data) ] [k (inc v)])
;; => ([:a 2] [:b 3])

(mapv #(for [[k v] %] [k (inc v)]) data)
;; => [([:a 2] [:b 3]) ([:c 4])]

(defn apply-fn-to-hashmap [f m]
  (for [[k v] m] [k (inc v)]))
(mapv #(apply-fn-to-hashmap inc %) data)
;; => [([:a 2] [:b 3]) ([:c 4])]

(defn apply-fn-to-hashmap [f m]
  (into {} (for [[k v] m] [k (inc v)])))
(mapv #(apply-fn-to-hashmap inc %) data)
;; => [{:a 2, :b 3} {:c 4}]

(defn inc-even [n]
  (if (even? n) (inc n) n))
(defn apply-fn-to-hashmap [f m]
  (into {} (for [[k v] m] [k (inc-even v)])))
(mapv #(apply-fn-to-hashmap inc-even %) data)
;; => [{:a 1, :b 3} {:c 3}]