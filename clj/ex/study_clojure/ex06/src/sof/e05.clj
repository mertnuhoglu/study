(ns sof.e05)

; [functional programming - What's the one-level sequence flattening function in Clojure? - Stack Overflow](https://stackoverflow.com/questions/10723451/whats-the-one-level-sequence-flattening-function-in-clojure)

(def bs [[[1 2]] [[2 3]] [[4 5]]])

(apply concat bs) ; ([1 2] [2 3] [4 5])

(for [subcoll bs, item subcoll] item) ; ([1 2] [2 3] [4 5])

(mapcat identity bs) ; ([1 2] [2 3] [4 5])

(mapcat seq bs) ; ([1 2] [2 3] [4 5])

; a0n: özel durum: eğer ilk öğe primitifse apply concat hata verir
#_(apply concat [1 [2 3] [4 [5]]])

(defn flatten-one-level [coll]  
  (mapcat  #(if (sequential? %) % [%]) coll))

(flatten-one-level [1 [2 3] [4 [5]]]) ; (1 2 3 4 [5])

; [idioms - What is the idiomatic way to assoc several keys/values in a nested map in Clojure? - Stack Overflow](https://stackoverflow.com/questions/4495345/what-is-the-idiomatic-way-to-assoc-several-keys-values-in-a-nested-map-in-clojur)

(def person {
             :name {
                    :first-name "John"
                    :middle-name "Michael"
                    :last-name "Smith"}})

(update-in person [:name] assoc :first-name "Bob" :last-name "Doe")
; {:name {:middle-name "Michael", :last-name "Doe", :first-name "Bob"}}

(update-in person [:name] merge {:first-name "Bob" :last-name "Doe"})
; {:name {:middle-name "Michael", :last-name "Doe", :first-name "Bob"}}

(update-in person [:name] into {:first-name "Bob" :last-name "Doe"})
; {:name {:middle-name "Michael", :last-name "Doe", :first-name "Bob"}}

(-> person 
    (assoc-in [:name :first-name] "Bob")
    (assoc-in [:name :last-name]  "Doe"))
; {:name {:middle-name "Michael", :last-name "Doe", :first-name "Bob"}}

; update-in does recursive assocs on your map. In this case it's roughly equivalent to:
(assoc person :name 
             (assoc (:name person) 
                    :first-name "Bob" 
                    :last-name "Doe")) 
; {:name {:first-name "Bob", :middle-name "Michael", :last-name "Doe"}}

(def foo {:bar {:baz {:quux 123}}})

(assoc foo :bar 
             (assoc (:bar foo) :baz 
                    (assoc (:baz (:bar foo)) :quux 
                           (inc (:quux (:baz (:bar foo)))))))
; {:bar {:baz {:quux 124}}}

(update-in foo [:bar :baz :quux] inc)
; {:bar {:baz {:quux 124}}}

