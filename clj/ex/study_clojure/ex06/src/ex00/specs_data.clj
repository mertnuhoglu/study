(ns ex00.specs-data)

; Code from: Programming Clojure, Alex Miller

(require '[clojure.spec.alpha :as s])

(defn scale-ingredient [ingredient factor]
  (update ingredient :quantity * factor))

; p01: (s/def name spec)

(s/def ::ingredient (s/keys :req [::name ::quantity ::unit]))

; p02: Predicates id=g12024

(s/def :my.app/company-name string?)
(s/valid? :my.app/company-name "Acme")
;;=> true
(s/valid? :my.app/company-name 100)
;;=> false

; p03: Enumerated Values

(s/def :marble/color #{:red :green :blue})
(s/valid? :marble/color :red)
;;=> true
(s/valid? :marble/color :pink)
;;=> false

; p04: Range Specs

(s/def :bowling/ranged-roll (s/int-in 0 11))
(s/valid? :bowling/ranged-roll 10)
;;=> true
(s/valid? :bowling/ranged-roll 12)
;;=> false

; p05: Handling nil

(s/def :my.app/company-name-2 (s/nilable string?))
(s/valid? :my.app/company-name-2 nil)
;;=> true
(s/valid? :my.app/company-name-2 3)
;;=> false

; p06: Logical specs

(s/def ::odd-int (s/and int? odd?))
(s/valid? ::odd-int 5)
;;=> true
(s/valid? ::odd-int 10)
;;=> false

(s/def ::odd-or-42 (s/or :odd ::odd-int :42 #{42}))
(s/conform ::odd-or-42 42)
;;=> [:42 42]
(s/conform ::odd-or-42 19)
;;=> [:odd 19]

(s/explain ::odd-or-42 0)
; (out) 0 - failed: odd? at: [:odd] spec: :specs/odd-int
; (out) 0 - failed: #{42} at: [:42] spec: :specs/odd-or-42

; p07: Collection specs id=g12025

(s/def ::names (s/coll-of string?))
(s/valid? ::names ["Alex" "Stu"])
;;=> true
(s/valid? ::names #{"Alex" "Stu"})
;;=> true
(s/valid? ::names '("Alex" "Stu"))
;;=> true

(s/def ::my-set (s/coll-of int? :kind set? :min-count 2))
(s/valid? ::my-set #{10 20})
;;=> true

; p08: Collection Sampling

; p09: Tuples

(s/def ::point (s/tuple float? float?))
(s/conform ::point [1.3 2.7])
;;=> [1.3 2.7]

; p10: Information Maps

{:music/id #uuid "5358e6e3-7f81-40f0-84e5-750e29e6ee05"
 :music/artist "Vivaldi"
 :music/title "Four Seasons"
 :music/date #inst "2020-06-15T14:27:36.415-00:00"}

(s/def :music/id uuid?)
(s/def :music/artist string?)
(s/def :music/title string?)
(s/def :music/date inst?)

(s/def :music/release
  (s/keys :req [:music/id]
          :opt [:music/artist
                :music/title
                :music/date]))

{:id #uuid "5358e6e3-7f81-40f0-84e5-750e29e6ee05"
 :artist "Vivaldi"
 :title "Four Seasons"
 :date #inst "2020-06-15T14:27:36.415-00:00"}

(s/def :music/release-unqualified
  (s/keys :req-un [:music/id]
          :opt-un [:music/artist]
                :music/title
                :music/date))


