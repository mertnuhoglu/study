(ns sof.e04)

; The order of combinators is like you write it with threading macro (natural order)

; Compared to composing calls to the old map, filter, reduce etc. you get better performance because you don't need to build intermediate collections between each step, and repeatedly walk those collections.

; [In Clojure is there an easy way to convert between list types? - Stack Overflow](https://stackoverflow.com/questions/5088803/in-clojure-is-there-an-easy-way-to-convert-between-list-types)

(into [] '(1 2 3 4)) ; ==> [1 2 3 4]         "have a lazy list and want a vector"
(into #{} [1 2 3 4]) ; ==> #{1 2 3 4}        "have a vector and want a set"
(into {} #{[1 2] [3 4]}) ; ==> {3 4, 1 2}    "have a set of vectors want a map"
(into #{} [{1 2} {3 4}]) ; ==> #{{1 2} {3 4}} "have a vector of maps want a set of maps"

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

