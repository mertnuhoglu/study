(ns specter01
  (:use [com.rpl.specter])
  (:require [clojure.string :as str]))

; Most codes taken from: https://github.com/redplanetlabs/specter/blob/master/README.md

; ex: Increment every even number nested within map of vector of maps id=g11441
;   id:: 0c5942b5-c664-4529-8155-c865bc125751

(def data {:a [{:aa 1 :bb 2}
               {:cc 3}]
           :b [{:dd 4}]})

;; Manual Clojure
(defn map-vals [m afn]
  (->> m (map (fn [[k v]] [k (afn v)])) (into (empty m))))

(map-vals data
  (fn [v]
    (mapv
      (fn [m]
        (map-vals
          m
          (fn [v] (if (even? v) (inc v) v))))
      v)))
;; => {:a [{:aa 1, :bb 3} {:cc 3}], :b [{:dd 5}]}

;; Specter
(transform [MAP-VALS ALL MAP-VALS even?] inc data)
;; => {:a [{:aa 1, :bb 3} {:cc 3}], :b [{:dd 5}]}

(select [MAP-VALS ALL MAP-VALS even?] data)
;; => [2 4]

(select [MAP-VALS ALL MAP-VALS] data)
;; => [1 2 3 4]

(select [MAP-VALS ALL] data)
;; => [{:aa 1, :bb 2} {:cc 3} {:dd 4}]

(select [MAP-VALS] data)
;; => [[{:aa 1, :bb 2} {:cc 3}] [{:dd 4}]]

; ex: Append a sequence of elements to a nested vector id=g11440
;   id:: c24b99b2-846a-41b9-9c6f-acc5a9b33d56

(def data {:a [1 2 3]})

;; Manual Clojure
(update data :a (fn [v] (into (if v v []) [4 5])))
;; => {:a [1 2 3 4 5]}

;; Specter
(setval [:a END] [4 5] data)
;; => {:a [1 2 3 4 5]}

(select [:a END] data)
;; => [[]]

(select [:a] data)
;; => [[1 2 3]]


; ex: Increment the last odd number in a sequence id=g11442
;   id:: 825dfcb5-19f4-49e6-b6d8-0e3615371656


(def data [1 2 3 4])

;; Manual Clojure
(let [idx (reduce-kv (fn [res i v] (if (odd? v) i res)) nil data)]
  (if idx (update data idx inc) data))
;; => [1 2 4 4]

;; Specter
(transform [(filterer odd?) LAST] inc data)
;; => [1 2 4 4]

(select [(filterer odd?) LAST] data)
;; => [3]

(select [(filterer odd?)] data)
;; => [[1 3]]

(transform [(filterer odd?)] identity data)
;; => [1 2 3 4]


; ex: Map a function over a sequence without changing the type or order of the sequence id=g11446
;   id:: baecaf7a-67ce-4bb8-a075-59f63491d9e2

;; Manual Clojure
(map inc data) ;; doesn't work, becomes a lazy sequence
;; => (2 3 4 5)
(into (empty data) (map inc data)) ;; doesn't work, reverses the order of lists
;; => [2 3 4 5]

;; Specter
(transform ALL inc data) ;; works for all Clojure datatypes with near-optimal efficiency
;; => [2 3 4 5]


; ex: Increment all the values in maps of maps: id=g11447
;   id:: 347b5aeb-87b4-40e7-9608-3ca93ad69a30

(transform [MAP-VALS MAP-VALS]
        inc
        {:a {:aa 1} :b {:ba -1}})
;; => {:a {:aa 2}, :b {:ba 0}}


; ex: Increment all the even values for :a keys in a sequence of maps:

(transform 
  [ALL :a even?]
  inc
  [{:a 1} {:a 2}])
;; => [{:a 1} {:a 3}]


; ex: Retrieve every number divisible by 3 out of a sequence of sequences:

(select 
  [ALL ALL #(= 0 (mod % 3))]
  [[6 2] [3]])
;; => [6 3]


; ex: Increment the last odd number in a sequence:


(transform 
  [(filterer odd?) LAST]
  inc
  [2 1 4])
;; => [2 2 4]


; ex: Remove nils from a nested sequence:


(setval [:a ALL nil?] NONE {:a [1 2 nil]})
;; => {:a [1 2]}


; ex: Remove key/value pair from nested map:


(setval [:a :b :c] NONE {:a {:b {:c 1}}})
;; => {:a {:b {}}}


; ex: Remove key/value pair from nested map, removing maps that become empty along the way:


(setval [:a (compact :b :c)] NONE {:a {:b {:c 1}}})
;; => {}


; ex: Increment all the odd numbers between indices 1 (inclusive) and 3 (exclusive):


(transform [(srange 1 3) ALL odd?] inc [0 1 2 3])
;; => [0 2 2 3]


; ex: Replace the subsequence from indices 1 to 3:


(setval (srange 1 3) [:a :b] [0 1 2 3])
;; => [0 :a :b 3]


; ex: Concatenate the sequence [:a :b] to every nested sequence of a sequence:


(setval [ALL END] [:a] [[1] '(1 2)])
;; => [[1 :a] (1 2 :a)]


; ex: Get all the numbers out of a data structure, no matter how they're nested:


(select (walker number?)
        {2 [1] :a 4})
;; => [2 1 4]


; ex: Navigate with string keys:


(select ["a" "b"]
        {"a" {"b" 10}})
;; => [10]


; ex: Reverse the positions of all even numbers between indices 0 and 4:


(transform [(srange 0 4) (filterer even?)]
  reverse
  [0 1 2 3 4])
;; => [2 1 0 3 4]


; ex: Append [:c :d] to every subsequence that has at least two even numbers:

(setval [ALL
         (selected? (filterer even?) (view count) (pred>= 2))
         END]
        [:c]
        [[1 2 4] [6]])
;; => [[1 2 4 :c] [6]]


; Video: Understanding Specter - Clojure's missing piece - rh5J4vacG98  id=g11449
;   id:: f23048d2-dbbf-4287-9b46-6102fa023bd9

; ex01

(mapv inc [1 2 3])
;; => [2 3 4]

(transform ALL inc #{1 2 3})
;; => #{2 3 4}

; ex02

(def data [{:a 1 :b 2} {:c 3}])

; clojure way:
(defn apply-fn-to-hashmap [f m]
  (into {} (for [[k v] m ] [k (f v)])))
(map #(apply-fn-to-hashmap inc %) data)
;; => ({:a 2, :b 3} {:c 4})
(defn inc-even [n]
  (if (even? n) (inc n) n))
(mapv #(apply-fn-to-hashmap inc-even %) data)
;; => [{:a 1, :b 3} {:c 3}]

; specter:
(transform [ALL MAP-VALS even?] inc data)
;; => [{:a 1, :b 3} {:c 3}]

; get-in is a navigator
(get-in {:a {:b 3}} [:a :b])
;; => 3
(get-in [:a {:b 1}] [1 :b])
;; => 1

; use01: query deep data with `select`
(def data {:res [{:group 1
                  :catlist [{:cat 1
                             :points [{:point 1
                                       :start "13"
                                       :stop "13.25"}
                                      {:point 2
                                       :start "14" 
                                       :stop "14.25"}]}]}]})
(select [:res ALL ] data)
;; => [{:group 1, :catlist [{:cat 1, :points [{:point 1, :start "13", :stop "13.25"} {:point 2, :start "14", :stop "14.25"}]}]}]
(select [:res ALL :catlist ALL ] data)
;; => [{:cat 1, :points [{:point 1, :start "13", :stop "13.25"} {:point 2, :start "14", :stop "14.25"}]}]
(select [:res ALL :catlist ALL :points ALL ] data)
;; => [{:point 1, :start "13", :stop "13.25"} {:point 2, :start "14", :stop "14.25"}]

; use02: update with `transform`

(def data {:res [{:group 1 
                  :as [{:a 1} {:a 2}]}]})
(transform [:res ALL :as]
           #(reverse (sort-by :stop %))
           data)
;; => {:res [{:group 1, :as ({:a 2} {:a 1})}]}

; use03: update with value `setval`

; opt01: single value
(setval [:res ALL :group] 2 data)
;; => {:res [{:group 2, :as [{:a 1} {:a 2}]}]}
(transform [:res ALL :group] (constantly 2) data)
;; => {:res [{:group 2, :as [{:a 1} {:a 2}]}]}

; opt02: a collection
(setval [:res FIRST :as FIRST]
        {:a "X"}
        data)
;; => {:res [{:group 1, :as [{:a "X"} {:a 2}]}]}

; opt03: insert new value
(setval [:res FIRST :as AFTER-ELEM]
        {:a "Z"}
        data)
;; => {:res [{:group 1, :as [{:a 1} {:a 2} {:a "Z"}]}]}
(setval [:res FIRST :as (before-index 1)]
        {:a "Z"}
        data)
;; => {:res [{:group 1, :as [{:a 1} {:a "Z"} {:a 2}]}]}


; use04: walking entire data
(select [(walker number?)] data)
;; => [1 1 2]
(select [(walker string?)] data)
;; => []
(select [(walker keyword?)] data)
;; => [:res :group :as :a :a]


; use05: parsing
(def data {:res [{:group 1 
                  :as [{:a "13:30"} {:a "14:45"}]}]})
(defn parse [time] (str/split time #"\:"))
(defn unparse [split-time] (str/join ":" split-time))

(parse "3.5")
;; => ["3" "5"]
(unparse ["3" "5"])
;; => "3.5"

(select [:res ALL :as ALL :a] data)
;; => ["13:30" "14:45"]
(select [:res ALL :as ALL :a (parser parse unparse)] data)
;; => [["13" "30"] ["14" "45"]]
(setval [:res ALL :as ALL :a (parser parse unparse) LAST] "00" data)
;; => {:res [{:group 1, :as [{:a "13:00"} {:a "14:00"}]}]}


; Article: [List of Navigators · redplanetlabs/specter Wiki](https://github.com/redplanetlabs/specter/wiki/List-of-Navigators)

                                        ; AFTER-ELEM

(setval AFTER-ELEM 3 [1 2])
;; => [1 2 3]

                                        ; ALL

(select ALL [0 1 2])
;; => [0 1 2]
(select ALL (list 0 1 2))
;; => [0 1 2]
(select ALL {:a :b, :c :d})
;; => [[:a :b] [:c :d]]
(transform ALL identity {:a :b})
;; => {:a :b}
(setval [ALL nil?] NONE [0 1 nil 2])
;; => [0 1 2]

                                        ; ALL-WITH-META: same as ALL

(meta (select ALL ^{:purpose "count"} [0 1 2]))
;; => nil
(transform ALL-WITH-META inc ^{:purpose "count"} [0 1 2])
;; => [1 2 3]
(meta (transform ALL-WITH-META inc ^{:purpose "count"} [0 1 2]))
;; => {:purpose "count"}


                                        ; ATOM

(def a (atom 0))
;; => #'clojure_by_example_kimh/a
(select-one ATOM a)
;; => 0
(swap! a inc)
;; => 1
(select-one ATOM a)
;; => 1
(transform ATOM inc a)
(deref a)
;; => 2

; BEFORE-ELEM

(setval BEFORE-ELEM 3 [1 2])
;; => [3 1 2]


                                        ; BEGINNING

(setval BEGINNING '(0 1) (range 3 6))
;; => (0 1 3 4 5)

                                        ; DISPENSE

(select [ALL VAL] (range 3))
;; => [[0 0] [1 1] [2 2]]
(transform [ALL VAL] + (range 3))
;; => (0 2 4)
(transform [ALL VAL DISPENSE] + (range 3))
;; => (0 1 2)

                                        ; END

(setval END '(5) (range 3))
;; => (0 1 2 5)

                                        ; FIRST
(select-one FIRST (range 3))
;; => 0

                                        ; INDEXED-VALS

(select [INDEXED-VALS] [5 6 7])
;; => [[0 5] [1 6] [2 7]]

                                        ; LAST

(select-one LAST (range 3))
;; => 2

                                        ; MAP-KEYS

(select [MAP-KEYS] {:a 3 :b 4})
;; => [:a :b]

                                        ; MAP-VALS

(select MAP-VALS {:a :b, :c :d})
;; => [:b :d]
(select [MAP-VALS MAP-VALS] {:a {:b :c}})
;; => [:c]

                                        ; META

(select-one META (with-meta {:a 0} {:meta :data}))
;; => {:meta :data}

                                        ; NAME

(select [NAME] :key)
;; => ["key"]
(select [MAP-KEYS NAME] {:a 3 :b 4})
;; => ["a" "b"]
(setval [MAP-KEYS NAME] "q" {'a/b 3})
;; => #:a{q 3}

; NAMESPACE

(select [ALL NAMESPACE] [::test ::fun])
;; => ["specter01" "specter01"]
(setval [ALL NAMESPACE] "a" [::test])
;; => [:a/test]

                                        ; NIL->LIST

(select-one NIL->LIST nil)
;; => ()
(select-one NIL->LIST :foo)
;; => :foo

                                        ; VALS
(select [VAL ALL] (range 3))
;; => [[(0 1 2) 0] [(0 1 2) 1] [(0 1 2) 2]]
(transform [VAL ALL] (fn [coll x] (+ x (count coll))) (range 3))
;; => (3 4 5)

                                        ; before-index

(select-any (before-index 0) [1 2 3])
;; => :com.rpl.specter.impl/NONE
(setval (before-index 0) :a [1 2 3])
;; => [:a 1 2 3]

                                        ; codewalker


                                        ; collect

(select-one [(collect ALL) FIRST] (range 3))
;; => [[0 1 2] 0]

                                        ; collect-one

(select-one [(collect-one FIRST) LAST] (range 3))
;; => [0 2]
(select [(collect-one FIRST) LAST] (range 3))
;; => [[0 2]]
(select [(collect-one FIRST) ALL] (range 3))
;; => [[0 0] [0 1] [0 2]]
(transform [(collect-one :b) :a] + {:a 2, :b 3})
;; => {:a 5, :b 3}

                                        ; comp-paths

(select (comp-paths ALL LAST) {:a 1 :b 2})
;; => [1 2]
; equivalent to:
(select [MAP-VALS] {:a 1 :b 2})
;; => [1 2]
                                        ; compact

(setval [1 (compact 0)] NONE [1 [2] 3])
;; => [1 3]
(setval [:a :b (compact :c)] NONE {:a {:b {:c 1}}})
;; => {:a {}}

                                        ; filterer

(select-one (filterer even?) (range 3))
;; => [0 2]

                                        ; if-path

(select (if-path (must :d) :a) {:a 0, :d 1})
;; => [0]
(select (if-path (must :d) :a :b) {:a 0, :b 1})
;; => [1]

                                        ; keypath

(select-one (keypath :a) {:a 0})
;; => 0
(select [ALL (keypath :a) (nil->val :boo)] [{:a 0} {:b 1}])
;; => [0 :boo]

                                        ; map-key

(select [(map-key :a)] {:a 2 :b 3})
;; => [:a]
(setval [(map-key :a)] :c {:a 2 :b 3})
;; => {:b 3, :c 2}

                                        ; multi-path

(select (multi-path :a :b) {:a 0, :b 1, :c 2})
;; => [0 1]
(select (multi-path (filterer odd?) (filterer even?)) (range 7))
;; => [[1 3 5] [0 2 4 6]]

                                        ; must

(select-one (must :a) {:a 0})
;; => 0
(select-one (must :a) {:b 0})
;; => nil

                                        ; nil->val

(select-one (nil->val :a) nil)
;; => :a
(select-one (nil->val :a) :b)
;; => :b

                                        ; nthpath

(select [(nthpath 0)] [1 2 3])
;; => [1]
(setval [(nthpath 0)] NONE [1 2 3])
;; => [2 3]
(select [(nthpath 0 1)] [[5 7] 1])
;; => [7]

                                        ; parser

                                        ; pred

(select [ALL (pred even?)] (range 3))
;; => [0 2]

(select [ALL (pred> 2)] [1 2 3])
;; => [3]

                                        ; putval

(transform [:a :b (putval 3)] + {:a {:b 0}})
;; => {:a {:b 3}}

                                        ; not-selected?

                                        ; regex-nav

(select (regex-nav #"t") "test")
;; => ["t" "t"]
(select [:a (regex-nav #"t")] {:a "tx"})
;; => ["t"]

                                        ; selected?

(select [ALL (selected? even?)] (range 3))
;; => [0 2]
(select [ALL (selected? [(must :a) even?])] [{:a 0} {:a 1} {:a 2} {:a 3}])
;; => [{:a 0} {:a 2}]
(select [ALL
         (selected? :money #(>= % 10))]
        [{:id 1 :money 15} {:id 2 :money 5}])
;; => [{:id 1, :money 15}]

                                        ; set-elements
(select [(set-elem 3)] #{3 4 5})
;; => [3]

                                        ; srange

(select-one (srange 2 4) (range 5))
;; => [2 3]


; Article: [List of Macros · redplanetlabs/specter Wiki](https://github.com/redplanetlabs/specter/wiki/List-of-Macros)

; collected?
(select [ALL (collect-one FIRST) LAST (collected? [k] (= k :a))] {:a 0 :b 1})
;; => [[:a 0]]
;; => [[:a 0]]

; multi-transform



; path ex: storing paths https://github.com/redplanetlabs/specter/wiki/List-of-Macros#path
(def p0 (path even?))
(select [ALL p0] (range 3))
;; => [0 2]


; Video: Specter  Powerful and Simple Data Structure Manipulation - Nathan Marz - VTCy_DkAJGk id=g11450
;   id:: 73ab3122-7036-4518-8697-2392d3338b46

                                        ; ex: add a value to a nested set

(def d0
  {:a #{2 3}
   :b #{4 5}})
   
(def d1
  {
   :b #{4 5}})
   

                                        ; common

(update
 d0
 :a
 (fn [s]
   (if s (conj s 1) #{1})))
;; => {:a #{1 3 2}, :b #{4 5}}

(update
 d1
 :a
 (fn [s]
   (if s (conj s 1) #{1})))
;; => {:b #{4 5}, :a #{1}}

; opt01: specter

(transform
 :a
 (fn [s] (if s (conj s 1) #{1}))
 d0)
;; => {:a #{1 3 2}, :b #{4 5}}

(transform
 :a
 (fn [s] (if s (conj s 1) #{1}))
 d1)
;; => {:b #{4 5}, :a #{1}}

                                        ; opt02: nil handling

(transform
 [:a NIL->SET]
 (fn [s] (if s (conj s 1) #{1}))
 d0)
;; => {:a #{1 3 2}, :b #{4 5}}

(transform
 [:a NIL->SET]
 (fn [s] (conj s 1))
 d1)
;; => {:b #{4 5}, :a #{1}}

; opt03: replace value
; navigate to empty subset and replace it with new value

(transform
 [:a (subset #{})]
 (fn [_] #{1})
 d1)
;; => {:b #{4 5}, :a #{1}}

                                        ; opt04: replace value

(setval
 [:a (subset #{})]
 #{1}
 d1)
;; => {:b #{4 5}, :a #{1}}










