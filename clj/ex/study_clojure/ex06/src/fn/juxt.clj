(ns fn.juxt)

; (juxt f)
; (juxt f g)
; (juxt f g h)
; (juxt f g h & fs) #nclk/çok-önemli
; Takes a set of functions and returns a fn that is the juxtaposition
; of those fns.  The returned fn takes a variable number of args, and
; returns a vector containing the result of applying each fn to the
; args
; (left-to-right).
; ((juxt a b c) x) => [ (a x) (b x) (c x)]

;; Extract values from a map, treating keywords as functions.
((juxt :a :b) {:a 1 :b 2 :c 3 :d 4})
;;=> [1 2]

;; "Explode" a value.
((juxt identity name) :keyword)
;;=> [:keyword "keyword"]

(juxt identity name)
;...is the same as:
(fn [x] [(identity x) (name x)])

;; eg. to create a map:
(into {} (map (juxt identity name) [:a :b :c :d]))
;;=> {:a "a" :b "b" :c "c" :d "d"}

;; Get the first character and length of string
((juxt first count) "Clojure Rocks")
;;=> [\C 13]

;; sort list of maps by multiple values
(sort-by (juxt :a :b) [{:a 1 :b 3} {:a 1 :b 2} {:a 2 :b 1}])
;;=> [{:a 1 :b 2} {:a 1 :b 3} {:a 2 :b 1}]
