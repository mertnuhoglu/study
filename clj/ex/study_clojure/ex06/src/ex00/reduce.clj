(ns ex00.reduce)

; [reduce - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/reduce)

(reduce + [1 2 3 4 5])  ;;=> 15
(reduce + [])           ;;=> 0
(reduce + [1])          ;;=> 1
(reduce + [1 2])        ;;=> 3
(reduce + 1 [])         ;;=> 1
(reduce + 1 [2 3])      ;;=> 6

;; Converting a vector to a set:

(reduce conj #{} [:a :b :c])
;; => #{:a :c :b}

;; Create a word frequency map out of a large string s.

;; `s` is a long string containing a lot of words :)
(def s "some words some ideas")
(reduce #(assoc %1 %2 (inc (%1 %2 0)))
        {}
        (re-seq #"\w+" s))
;;=> {"some" 2, "words" 1, "ideas" 1}

; (This can also be done using the `frequencies` function.)

;; Calculate primes until 100

(reduce
  (fn [primes number]
    (if (some zero? (map (partial mod number) primes))
      primes
      (conj primes number)))
  [2]
  (take 100 (iterate inc 3)))
;;=> [2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97]

;; Add one collection to another (combining sequences is done with cons):
(reduce conj [1 2 3] [4 5 6])
;;=> [1 2 3 4 5 6]

(reduce #(cons %2 %1) [1 2 3] [4 5 6])
;;=> '(6 5 4 1 2 3)

;; Combine a vector of collections into a single collection 
;; of the type of the first collection in the vector.
(reduce into [[1 2 3] [:a :b :c] '([4 5] 6)])
;;=> [1 2 3 :a :b :c [4 5] 6]

;; The flatten function can be used to completely fuse 
;; all of the items of a nested tree into a single sequence.
;; Sometimes all that is needed is to fuse the first level
;; of a tree. This can be done with 'reduce' and 'into'.
(reduce into [] '([] [[10 18]] [[8 18]] [[10 12]] [[0 -6]] [[2 6]]))
;;=> [[10 18] [8 18] [10 12] [0 -6] [2 6]]

;; Some functions update a collection with a single item.
;; A number of functions have a 'more' argument which lets
;; them work over collections.
;; These functions can benefit 'reduce' which lets them work 
;; a collection of items...
(into {} {:dog :food})
;;=> {:dog :food, :cat :chow}

(reduce into {} [{:dog :food} {:cat :chow}])
;;=> {:dog :food, :cat :chow}

;; The reduction will terminate early if an intermediate result uses the 
;; `reduced` function.

(defn limit [x y] 
  (let [sum (+ x y)] 
    (if (> sum 10) (reduced sum) sum)))

(reduce + 0 (range 10))
;; => 45

(reduce limit 0 (range 10))
;; => 15

;; This will generate the first 100 Fibonacci numbers
;; (size of (range) + 2):

(reduce 
  (fn [a b] (conj a (+' (last a) (last (butlast a)))))  
  [0 1]                      
  (range 10))
;;=> [0 1 1 2 3 5 8 13 21 34 55 89]

;; Update map entries:
(defn update-map-entries [m e]
     (reduce #(update-in %1 [(first %2)] (fn [_] (last %2))) m e))

(update-map-entries {:a 1 :b 2 :c 3} {:a 5 :b 9})
;; {:a 5, :b 9, :c 3}
(update-map-entries {:a 1 :b 2 :c 3} {:a 5 :b 9 :d 8})
;; {:a 5, :b 9, :c 3, :d 8}

;; Flatten values in a map.
(reduce
  (fn [flattened [k v]]
    (clojure.set/union flattened v))
  #{}
  {:e #{:m :f}, :c #{:f}, :b #{:c :f}, :d #{:m :f}, :a #{:c :f}})

;; => #{:m :c :f}

;; A simple factorial function using reduce:

(defn fact
  [x] 
  (reduce * (range 1 (inc x))))

(fact 5)
;;=> 120

;; Reduce over maps by destructuring keys:
(def x {:a 1 :b 2})

(reduce (fn [p [k v]]
          (into p {k (+ 1 v)}))
        {} ; First value for p
        x)

;; => {:a 2, :b 3}

;;reduce with side effects
;;given a collection return a new collection

(def initial-coll [1 2 3 4 5])

(defn byten
  [coll]
  (reduce (fn [new-coll unit]
            (into new-coll [(* 10 unit)]))
          []
          coll))

(byten initial-coll)
;;[10 20 30 40 50]

;; A practical example of mapping over values
;; in a hash-map with the `upper-case` function:

(reduce
 (fn [acc [k v]]
   (assoc acc k (clojure.string/upper-case v)))
 {}
 {:a "aaaaaaa" :b "bbbbbbb"})

;; => {:a "AAAAAAA", :b "BBBBBBB"}

(reduce #(str %1 %2) "" ["Woody" "Potato" "Buzz"])
;;=> "WoodyPotatoBuzz"

;; or

(reduce (fn [accumulator current-item] (str accumulator current-item)) "" ["Woody" "Potato" "Buzz"])
;;=> "WoodyPotatoBuzz"

;; ## reduce-kv

; [reduce-kv - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/reduce-kv)

