(ns assoc_update)

;; ## assoc-in

(def users [{:name "James" :age 26}
            {:name "John" :age 43}])

(assoc-in users [1 :age] 44)
;;=> [{:name "James", :age 26} {:name "John", :age 44}]

;; insert the password of the second (index 1) user
(assoc-in users [1 :password] "nhoJ")
;;=> [{:name "James", :age 26} {:password "nhoJ", :name "John", :age 43}]

;; Also (assoc m 2 {...}) or (conj m {...})
(assoc-in users [2] {:name "Jack" :age 19})  
;;=> [{:name "James", :age 26} {:name "John", :age 43} {:name "Jack", :age 19}]

;; From http://clojure-examples.appspot.com/clojure.core/assoc-in

(assoc-in {} [:cookie :monster :vocals] "Finntroll")
; => {:cookie {:monster {:vocals "Finntroll"}}}

(get-in {:a {:b 1}} [:a :b])
;;=> 1

(get-in {:cookie {:monster {:vocals "Finntroll"}}} [:cookie :monster])
; => {:vocals "Finntroll"}

(assoc-in {} [1 :connections 4] 2)
; => {1 {:connections {4 2}}}

;; from http://www.braveclojure.com/functional-programming/

;; ## assoc

;; [assoc - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/assoc)

(assoc {} :key1 "value" :key2 "another value")
;;=> {:key2 "another value", :key1 "value"}

;; Here we see an overwrite by a second entry with the same key
(assoc {:key1 "old value1" :key2 "value2" 
        :key1 "value1" :key3 "value3"})
;;=> {:key3 "value3", :key2 "value2", :key1 "value1"}

;; We see a nil being treated as an empty map
(assoc nil :key1 4)
;;=> {:key1 4}

;; 'assoc' can be used on a vector (but not a list), in this way: 
;; (assoc vec index replacement)
(assoc [1 2 3] 0 10)     ;;=> [10 2 3]
(assoc [1 2 3] 2 '(4 6)) ;;=> [1 2 (4 6)]
;; Next, 0 < index <= n, so the following will work
(assoc [1 2 3] 3 10)     ;;=> [1 2 3 10]
;; However, when index > n, an error is thrown
; (assoc [1 2 3] 4 10)
;; java.lang.IndexOutOfBoundsException (NO_SOURCE_FILE:0)

;; From http://clojure-examples.appspot.com/clojure.core/assoc

;;transform a map´s values using reduce and assoc

(defn transform
  [coll]
  (reduce (fn [ncoll [k v]]
            (assoc ncoll k (* 10 v)))
          {}
          coll))

(transform {:a 1 :b 2 :c 3})
;;{:a 10 :b 20 :c 30}

;; You can also change multiple keys at once

(def my-map {:name "Victor" :age 27 :city "Barcelona"})

(assoc my-map :name "Tom" :age 47)

;; {:name "Tom", :age 47, :city "Barcelona"}

;; ## update

;; [update - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/update)

(def p {:name "James" :age 26})
;;=> #'user/p

(update p :age inc)
;;=> {:name "James", :age 27}

;; remember, the value of p hasn't changed because it is immutable!
p
;; {:name "James", :age 26}
(update p :age + 10)
;;=> {:name "James", :age 36}

;; Here we see that the keyed object is 
;; the first argument in the function call.
;; i.e. :age (- 26 10) => 16
(update p :age - 10)
;;=> {:name "James", :age 16}

;; the map in update can be nil, and f will still be applied to nil and 
;; return a value

(def empty-map nil)
#'user/empty-map

(update empty-map :some-key #(str "foo" %))
;;=> {:some-key "foo"}

;; can also use in []

(update [1 2 3] 0 inc)
;;=> [2 2 3]

(update [] 0 #(str "foo" %))
;;=> ["foo"]

;; For a simple change of value we can use constantly
(def hm {:banana 1, :apple 2, :orange 3})

(update hm :banana (constantly 6))
;;=> {:banana 6 :apple 2 :orange 3}

;; Which is equivalent to using "assoc"
(assoc hm :banana 6)
;;=> {:banana 6 :apple 2 :orange 3}

;; ## update-in

;; [update-in - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/update-in)

(def users [{:name "James" :age 26}  {:name "John" :age 43}])
;;=> #'user/users

;; similar to assoc-in but does not simply replace the item.
;; the specified function is performed on the matching item.
;; here the age of the second (index 1) user is incremented.
(update-in users [1 :age] inc)
;;=> [{:name "James", :age 26} {:name "John", :age 44}]

;;You can use update-in in a nested map too, in order to update more than
;;one value:

(def m {:1 {:value 0, :active false}, :2 {:value 0, :active false}})

(update-in m [:1] assoc :value 1 :active true)
;;=>{:1 {:value 1, :active true}, :2 {:value 0, :active false}}

(assoc-in m [:1 :value] 1)
;;=> {:1 {:value 1, :active false}, :2 {:value 0, :active false}}

;; ## conj 

(def m {})
(conj m {:a 1})
(conj {:a 1} {:b 2})

;; ## dissoc

; [dissoc - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/dissoc)

(dissoc {:a 1 :b 2 :c 3}) ; dissoc nothing 
;;=> {:a 1, :b 2, :c 3} 

(dissoc {:a 1 :b 2 :c 3} :b) ; dissoc key :b
;;=> {:a 1, :c 3} 

(dissoc {:a 1 :b 2 :c 3} :d) ; dissoc not existing key
;;=> {:a 1, :b 2, :c 3} 

(dissoc {:a 1 :b 2 :c 3} :c :b) ; several keys at once
;;=> {:a 1} 

;; There is no (dissoc-in) analogous to (get-in) or (assoc-in), but 
;; you can achieve a similar effect using (update-in):

(update-in {:a {:b {:x 3} :c 1}} [:a :b] dissoc :x)
;;=> {:a {:b {}, :c 1}}

;; ## Summary

(def m {:k :v}) ; {:k :v}
(defn f1 [v] :rs)
(defn f2 [arg1 arg2] arg1)

(assoc m :k1 :v1 :k2 :v2) ; {:k :v, :k1 :v1, :k2 :v2}
(assoc-in m [:k] :v3) ; {:k :v3}
(update m :k f1) ; {:k :rs}
(update m :k f2 :v4)
(update-in m p-k f1)
(update-in m p-k f2 arg2)

;; ## ex01

(def m {:sel [{:id 1}]})

(assoc-in m [:sel 1] {:id 2})
;;=> {:sel [{:id 1} {:id 2}]}

(update-in m [:sel] conj {:id 2})
;;=> {:sel [{:id 1} {:id 2}]}

;; wrong:

(update m [:sel] assoc :id 2)
;;=> {:sel [{:id 1}], [:sel] {:id 2}}

(update m :sel assoc :id 2)
; (err) Key must be integer

(update-in m [:sel] cons {:id 2})
;;=> {:sel ([{:id 1}] [:id 2])}

;; end: ex01

;; ## ex02

(def m 
  {:team/id {:explorers 
             #:team{:name "Explorers", :id :explorers, 
                    :players [[:player/id 1] [:player/id 2] [:player/id 3]]},
             :bikers 
             #:team{:name "Bikers", :id :bikers, 
                    :players [[:player/id 4]]}}} ,)

(update-in m [:team/id :explorers :team/players])

