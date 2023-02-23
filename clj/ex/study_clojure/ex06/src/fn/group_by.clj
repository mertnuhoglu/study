(ns fn.group-by)

; rfr: video/20230223-mert-clj-egzersiz-51.mp4

; (group-by f coll)
; Returns a map of the elements of coll keyed by the result of
; f on each element. The value at each key will be a vector of the
; corresponding elements, in the order they appeared in coll.
;

;; group strings by their length
(group-by count ["a" "as" "asd" "aa" "asdf" "qwer"])
;;=> {1 ["a"], 2 ["as" "aa"], 3 ["asd"], 4 ["asdf" "qwer"]}

(comment
  (def coll ["a" "as" "asd" "aa" "asdf" "qwer"])
  (map count coll)
  ;=> (1 2 3 2 4 4)
  {1 ["a"]
   2 ["as" "aa"]
   3 ["asd"]
   4 ["asdf" "qwer"]}

  (group-by type ["ali" 10 "ayşe" 20 :a :b \n \m])
  ;=> {java.lang.String ["ali" "ayşe"],
  ;    java.lang.Long [10 20],
  ;    clojure.lang.Keyword [:a :b],
  ;    java.lang.Character [\n \m]}
  ,)

;; group integers by a predicate
(group-by odd? (range 10))
;;=> {false [0 2 4 6 8], true [1 3 5 7 9]}

;; group by a primary key
(group-by :user-id [{:user-id 1 :uri "/"}
                    {:user-id 2 :uri "/foo"}
                    {:user-id 1 :uri "/account"}])
;;=> {1 [{:user-id 1, :uri "/"}
;;       {:user-id 1, :uri "/account"}],
;;    2 [{:user-id 2, :uri "/foo"}]}

;; group by multiple criteria
(def words ["Air" "Bud" "Cup" "Awake" "Break" "Chunk" "Ant" "Big" "Check"])
(group-by (juxt first count) words)
;;{[\A 3] ["Air" "Ant"],
;;[\B 3] ["Bud" "Big"],
;;[\C 3] ["Cup"],
;;[\A 5] ["Awake"],
;;[\B 5] ["Break"],
;;[\C 5] ["Chunk" "Check"]}

(comment
  (map (juxt first count) words)
  ;=> ([\A 3] [\B 3] [\C 3] [\A 5] [\B 5] [\C 5] [\A 3] [\B 3] [\C 5])
  (set (map (juxt first count) words))
  ;=> #{[\C 5] [\B 5] [\B 3] [\C 3] [\A 5] [\A 3]}
  ,)

(group-by :category [{:category "a" :id 1}
                     {:category "a" :id 2}
                     {:category "b" :id 3}])
;;{"a" [{:category "a", :id 1} {:category "a", :id 2}],
;; "b" [{:category "b", :id 3}]}

(group-by #(get % :category) [{:category "a" :id 1}
                              {:category "a" :id 2}
                              {:category "b" :id 3}])
;;{"a" [{:category "a", :id 1} {:category "a", :id 2}],
;; "b" [{:category "b", :id 3}]}

(defn my-category [item] (get item :category))
(group-by my-category [{:category "a" :id 1}
                       {:category "a" :id 2}
                       {:category "b" :id 3}])
;;{"a" [{:category "a", :id 1} {:category "a", :id 2}],
;; "b" [{:category "b", :id 3}]}
