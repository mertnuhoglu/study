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

