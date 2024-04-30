(ns warming.e01)

; [Learn clojure in Y Minutes](https://learnxinyminutes.com/docs/clojure/)
;
(str "string " " concatenation")
(not true)
(+ 1 (- 3 2))

(class 1)
(class 1.)
(class " ")

'(+ 1 2) 
(eval '(+ 1 2))
(identity '(+ 1 2))

(class [1 2])
(class '(1 2))

(list 1 2)
'(1 2)

(coll? '(1 2))
(coll? [1 2])

(seq? '(1 2))
(seq? '[1 2])

(range 3)
(take 3 (range))

; cons: s>j => s to the start
(cons 3 [1 2])
(cons 3 '(1 2))

; conj: s>j => j short time => j fast
(conj [1 2] 3)
(conj '(1 2) 3)

(concat [1 2] '(3 4))

(map inc [1 2 3])
(filter even? [1 2 3])
(reduce conj [] '(3 2 1))

(fn [] "Hello")
((fn [] "Hello"))

(def x 1)
(def f (fn [] "Hello"))
(f)
(defn f2 [] "Hello")

(defn f3 [name] 
  (str "Hello " name))
(f3 "Steve")

(def f4 #(str "Hello " %1))
(f4 "Naima")

(defn f5 
  ([] "Hello")
  ([name] (str "Hello " name)))
(f5 "Koi")
(f5)

(defn variadic-args [& args]
  (str "Number of args: " (count args) " args: " args))
(variadic-args 1 2 3)
(variadic-args "a" "b")

(defn var-regular-args [name & args]
  (str "Hello " name ", # args: " (count args))) 
(var-regular-args "Biki" 1 2)

(class {:a 1})
(class (hash-map :a 1))
(class :a)
(def m1 {"a" 1})
(identity m1)

(def m2 {:a 1})
(identity m2)

(m1 "a")
(m2 :a)
(:a m2)
(:b m2)

(def m3 (assoc m2 :b 2))
(identity m3)
(dissoc m3 :b)

(class #{1 2 3})
(set [1 2 3 1])

(conj #{1 2 3} 4)
(disj #{1 2 3} 1)
(#{1 2 3} 1)
(#{1 2 3} 4)

(if false :a :b)
(if false :a)

(let [a 1 b 2]
  (> a b))

(do 
  (print "hello")
  "World")

(defn fn-has-implicit-do [name]
  (print "Hello " name)
  (str "Welcome " name))
(fn-has-implicit-do "Clara")

(let [name "Gurkel"]
  (print "Hello " name)
  (str "Welcome " name))

(->
  {:a 1 :b 2}
  (assoc :c 3)
  (dissoc :b))

(->>
  (range 4)
  (map inc)
  (filter odd?)
  (into []))

(as-> [1 2 3] input
  (map inc input)
  (nth input 2))

(as-> [1 2 3] input
  (map inc input)
  (nth input 2)
  (conj [4 5] input 6))

(use 'clojure.set)
(intersection #{1 2 3} #{2 3 4})
(difference #{1 2 3} #{2 3 4})

(use '[clojure.set :only [intersection]])

(require 'clojure.string)
(clojure.string/blank? "")
  
(require '[clojure.string :as str])
(str/replace "Hello" #"[a-j]" str/upper-case)

(import java.util.Date)
(Date.)
(. (Date.) getTime)
(.getTime (Date.))
(System/currentTimeMillis)

(import java.util.Calendar)
(doto (Calendar/getInstance)
  (.set 2000 1 1 0 0 0)
  .getTime)

; ## STM
;;;;;;;;;;;;;;;;;;;;;

(def a (atom {}))
(swap! a assoc :a 1)
(swap! a assoc :b 2)
(identity @a)

(def counter (atom 0))
(defn inc-counter []
  (swap! counter inc))
(inc-counter)
(inc-counter)
(identity @counter)
