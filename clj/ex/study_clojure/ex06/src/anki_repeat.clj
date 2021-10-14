(ns anki_repeat)

;; # update-in

(def p {:name "James" :age 26})
;;=> #'user/p

(update p :age inc)
;;=> {:name "James", :age 27}

(assoc {} :a 1)
;;=> {:a 1}

(def users [{:name "James" :age 26}  {:name "John" :age 43}])
;;=> #'user/users

(update-in users [1 :age] inc)
;;=> [{:name "James", :age 26} {:name "John", :age 44}]

(assoc-in users [1 :age] 44)
;;=> [{:name "James", :age 26} {:name "John", :age 44}]

(update-in {:a {:b {:x 3} :c 1}} [:a :b] dissoc :x)
;;=> {:a {:b {}, :c 1}}

(get-in {:a {:b 1}} [:a :b])
;;=> 1

;; # reduce

(reduce + [1 2])        ;;=> 3

(reduce conj #{} [:a :b :c])
;; => #{:a :c :b}

(reduce into {} [{:dog :food} {:cat :chow}])
;;=> {:dog :food, :cat :chow}

;; # require

(require '[ns01 :refer [fn01, fn02]])

(require '[ns01 :as alias01])

(require '[ns01])

(ns ns02
    (:require [ns03 :as alias03]))

;; # cli

; clojure -X:project/new :name mertnuhoglu/project-01

;; # atom

(def a (atom 1))
;; => #'user/a

(deref a)
;; => 1

(reset! a 2)
;; => 2

(swap! a #(inc %))
;; => 3

(reset! a {})
;;=> {}

(swap! a assoc :k 1)
;;=> {:k 1}

