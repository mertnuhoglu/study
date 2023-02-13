(ns sof.e05)

; [How to Iterate over Map Keys and Values in Clojure? - Stack Overflow](https://stackoverflow.com/questions/6685916/how-to-iterate-over-map-keys-and-values-in-clojure)

; Map öğelerini dolaşmak için:
(seq {:foo 1 :bar 2}) ; ([:foo 1] [:bar 2])

; for ve destructuring ile map öğeleri işlenebilir:
(for [[k v] {1 2 3 4}] (+ k v))  ; (3 7)

(map (fn [[k v]] (identity [k v])) {:a 1 :b 2}) ; ([:a 1] [:b 2])

; [clojure - Return first item in a map/list/sequence that satisfies a predicate - Stack Overflow](https://stackoverflow.com/questions/10192602/return-first-item-in-a-map-list-sequence-that-satisfies-a-predicate)

; a01
(defn find-first
  [f coll]
  (first (filter f coll)))

(find-first #(= % 1) [3 4 1]) ; 1

; a02
(some #{1} [1 2 3 4]) ; 1
; How it works: #{1} is a set literal. A set is also a function evaluating to its arg if the arg is present in the set and to nil otherwise

(->> [nil nil nil nil 42 nil nil] (some identity)) ; 42

; [What is the idiomatic way to prepend to a vector in Clojure? - Stack Overflow](https://stackoverflow.com/questions/4095714/what-is-the-idiomatic-way-to-prepend-to-a-vector-in-clojure)

; Prepending to a list is easy:

(conj '(:bar :baz) :foo)
; (:foo :bar :baz)

; Appending to a vector is easy:

(conj [:bar :baz] :foo)
; [:bar :baz :foo]

; How do I (idiomatically) prepend to a vector, while getting back a vector? This does not work as it returns a seq, not a vector:

(cons :foo [:bar :baz])
; (:foo :bar :baz)

; This is ugly (IMVHO):

(apply vector (cons :foo [:bar :baz]))
; [:foo :bar :baz]

; Vectors are not designed for prepending. You have only O(n) prepend:

(into [:foo] [:bar :baz])
; [:foo :bar :baz]


; [data structures - Representing A Tree in Clojure - Stack Overflow](https://stackoverflow.com/questions/1787708/representing-a-tree-in-clojure)

; What would be an idiomatic way to represent a tree in Clojure? E.g.:

;      A
;     / \
;    B   C
;   /\    \
;  D  E    F

'(A (B (D) (E)) (C (F)))

; [Clojure: Semi-Flattening a nested Sequence - Stack Overflow](https://stackoverflow.com/questions/5232350/clojure-semi-flattening-a-nested-sequence)

(([1 2]) ([3 4] [5 6]) ([7 8]))

; Which I know is not ideal to work with. I'd like to flatten this to ([1 2] [3 4] [5 6] [7 8]).

; flatten doesn't work: it gives me (1 2 3 4 5 6 7 8).

(apply concat '(([1 2]) ([3 4] [5 6]) ([7 8])))
; => ([1 2] [3 4] [5 6] [7 8])

; [How to repeat string n times in idiomatic clojure way? - Stack Overflow](https://stackoverflow.com/questions/5433691/how-to-repeat-string-n-times-in-idiomatic-clojure-way)

; a01: idiomatic
(apply str (repeat 3 "foo"))
; "foofoofoo"

; a02: idiomatic
(repeat 3 "foo")
; ("foo" "foo" "foo")

; a03: uzun yol
(map (fn [n] "foo") (range 3))
; ("foo" "foo" "foo")

; a04: protocol ile örnek

; And one more fun alternative using protocols:
(defprotocol Multiply (* [this n]))

; Next the String class is extended:
(extend String Multiply {:* (fn [this n] (apply str (repeat n this)))})

; So you can now 'conveniently' use:
(* "foo" 3) ; "foofoofoo"

; a05:

(clojure.string/join (repeat 3 "str"))
; "strstrstr"

(format "%1$s%1$s%1$s" "str")
; "strstrstr"

(reduce str (take 3 (cycle ["str"])))
; "strstrstr"

(reduce str (repeat 3 "str"))
; "strstrstr"

(reduce #(.concat %1 %2) (repeat 3 "str"))
; "strstrstr"



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

