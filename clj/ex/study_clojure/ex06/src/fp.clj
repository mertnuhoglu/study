(ns fp)

; Code from: Programming Clojure, Alex Miller

; p01 Simple Recursion

(defn stack-consuming-fibo [n]
  (cond
   (= n 0) 0 ; basis
   (= n 1) 1 ; basis
   :else (+ (stack-consuming-fibo (- n 1))    ; induction
          (stack-consuming-fibo (- n 2)))))     

(stack-consuming-fibo 9)
;;=> 34

; p02 Tail Recursion id=g_12018

(defn tail-fibo [n]
  (letfn [(fib ; <label id="code.tail-fibo.letfn"/>
           [current next n] ; <label id="code.tail-fibo.args"/>
           (if (zero? n)
               current       ; <label id="code.tail-fibo.terminate"/>
               (fib next (+ current next) (dec n))))] ; <label id="code.tail-fibo.recur"/>
    (fib 0N 1N n))) ; <label id="code.tail-fibo.call"/>

(comment
  (+ 1 2)

  (defn f [] (+ 1 2))

  (tail-fibo 3)
  ;;=> 2N

  ,)

; p03 Explicit self-recursion with recur  id=g_12019

(defn recur-fibo [n]
  (letfn [(fib 
            [current next n]
            (if (zero? n)
              current
              (recur next (+ current next) (dec n))))] ; <label id="code.recur-fibo.recur"/>
    (fib 0N 1N n)))

(comment

  (recur-fibo 3)
  ;;=> 2N

  ,)

; p04 Lazy Seq Recursion 01 

(defn lazy-seq-fibo 
  ([] 
   (concat [0 1] (lazy-seq-fibo 0N 1N))) 
  ([a b]
   (let [n (+ a b)]                    
     (lazy-seq 
       (cons n (lazy-seq-fibo b n)))))) 

(comment
  (take 10 (lazy-seq-fibo))
  ;;=> (0 1 1N 2N 3N 5N 8N 13N 21N 34N)

  ,)

; p05 Lazy Seq Recursion Using iterate id=g_12020

(comment
  (take 5 (iterate (fn [[a b]] [b (+ a b)]) [0 1])))
  ;;=> ([0 1] [1 1] [1 2] [2 3] [3 5])

(defn fibo []
  (map first (iterate (fn [[a b]] [b (+ a b)]) [0N 1N])))

(comment
  (take 5 (fibo))
  ;;=> (0N 1N 1N 2N 3N)

  ,)

; p06 Lazier than Lazy id=g_12022

(defn count-heads-pairs [coll]
  (loop [cnt 0 coll coll]
    (if (empty? coll)
      cnt
      (recur 
        (if (= :h (first coll) (second coll))
          (inc cnt)
          cnt)
        (rest coll)))))

(comment
  (count-heads-pairs [:h :t :h :h :h])
  ;;=> 2

  ,)

; p07 Transforming the Input Sequence id=g_12021

(defn by-pairs [coll]
  (let [take-pair 
        (fn [c]
          (when (next c) (take 2 c)))]
    (lazy-seq
      (when-let 
        [pair 
         (seq (take-pair coll))]
        (cons pair 
              (by-pairs (rest coll)))))))

(comment
  (by-pairs [:h :t :h :h :h])
  ;;=> ((:h :t) (:t :h) (:h :h) (:h :h))

  ,)

(defn count-heads-pairs [coll]
  (count 
    (filter 
      (fn [pair] 
        (every? #(= :h %) 
                pair))) 
    (by-pairs coll)))

(comment
  (count-heads-pairs [:h :h])
  ;;=> 1
  (count-heads-pairs [:h :t])
  ;;=> 0
  (count-heads-pairs [:h :h :h])
  ;;=> 2

  ,)

; p07.02 Use partition instead of by-pairs id=g_12023

; (partition size step? coll)

(comment
  (partition 2 [:h :h :h])
  ;;=> ((:h :h))
  (partition 2 [:h :t :h])
  ;;=> ((:h :t))
  (partition 2 [:h :t :t :h :h :h])
  ;;=> ((:h :t) (:t :h) (:h :h))

  ; use step argument
  (partition 2 1 [:h :t :h])
  ;;=> ((:h :t) (:t :h))
  (partition 2 1 [:h :t :t :h :h :h])
  ;;=> ((:h :t) (:t :t) (:t :h) (:h :h) (:h :h))

  ,)

; (comp f & fs)

(def ^{:doc "Count items matching a filter"}
  count-if (comp count filter))

(comment
  (count-if odd? [1 2 3 4 5])
  ;;=> 3
  ,)

(defn count-runs
 "Count runs of length n where pred is true in coll."
  [n pred coll]
  (count-if 
    #(every? pred %) 
    (partition n 1 coll)))

(comment
  (count-runs 
    2
    #(= % :h)
    [:h :t :t :h :h :h])
  ;;=> 2
  
  (count-runs 
    3
    #(= % :h)
    [:h :t :t :h :h :h])
  ;;=> 1

  ,)

; p08 Currying and Partial Application

; (partial f & partial-args)

(def ^{:doc "Count runs of length two that are both heads"}
  count-heads-pairs (partial count-runs 2 #(= % :h)))

(comment
  (count-heads-pairs [:h :t :t :h :h :h])
  ;;=> 2
  ,)

