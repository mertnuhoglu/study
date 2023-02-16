(ns sof.sof08)

; Barış'la Clojure Veri Analizi Çalışmaları
; Tarih: 20230216
; rfr: video/20230216-mert-clj-egzersiz-39.mp4
; rfr: video/20230216-mert-clj-egzersiz-40.mp4

; [idioms - What is the idiomatic way to assoc several keys/values in a nested map in Clojure? - Stack Overflow](https://stackoverflow.com/questions/4495345/what-is-the-idiomatic-way-to-assoc-several-keys-values-in-a-nested-map-in-clojur)

(def person {
             :name {
                    :first-name "John"
                    :middle-name "Michael"
                    :last-name "Smith"}})

; a01: update-in assoc
(update-in person [:name] assoc :first-name "Bob" :last-name "Doe")
; {:name {:middle-name "Michael", :last-name "Doe", :first-name "Bob"}}

; a02: update-in merge
(update-in person [:name] merge {:first-name "Bob" :last-name "Doe"})
; {:name {:middle-name "Michael", :last-name "Doe", :first-name "Bob"}}

; a03: update-in into
(update-in person [:name] into {:first-name "Bob" :last-name "Doe"})
; {:name {:middle-name "Michael", :last-name "Doe", :first-name "Bob"}}

; a04: assoc-in x 2
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
