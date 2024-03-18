(ns ex00.specs-functions)

; Code from: Programming Clojure, Alex Miller

(require '[clojure.spec.alpha :as s])

; p01: Sequences with structure id=g12026

; p01.01: s/cat

(s/def ::cat-example (s/cat :s string? :i int?))
(s/valid? ::cat-example ["abc" 100])
;;=> true

; p01.02: s/alt

(s/def ::alt-example (s/alt :i int? ::k keyword?))
(s/valid? ::alt-example [100])
;;=> true
(s/valid? ::alt-example [:foo])
;;=> true
(s/conform ::alt-example [:foo])
;;=> [:specs-functions/k :foo]

; p01.03: Repetition operators: 

; s/+ s/? s/*

(s/def ::oe (s/cat :odds (s/+ odd?) :even (s/? even?)))
(s/conform ::oe [1 3 5 100])
;;=> {:odds [1 3 5], :even 100}

(s/def ::odds (s/+ odd?))
(s/def ::optional-even (s/? even?))
(s/def ::oe2 (s/cat :odds ::odds :even ::optional-even))
(s/conform ::oe2 [1 2 3 77])
;;=> :clojure.spec.alpha/invalid
(s/conform ::oe2 [1 3 5 100])
;;=> {:odds [1 3 5], :even 100}
(s/conform ::oe2 [1 3 5])
;;=> {:odds [1 3 5]}
(s/conform ::oe2 [1 3 77 2])
;;=> {:odds [1 3 77], :even 2}

; p01.04: Variable Argument Lists: s/*

(s/def ::println-args (s/* any?))

(require 'clojure.set)

(clojure.set/intersection #{1 2} #{2 3} #{2 5})
;;=> #{2}

(s/def ::intersection-args
  (s/cat :s1 set?
         :sets (s/* set?)))

(s/conform ::intersection-args '[#{1 2} #{2 3} #{2 5}])
;;=> {:s1 #{1 2}, :sets [#{3 2} #{2 5}]}

(s/def ::intersection-args-2
  (s/* set?))
(s/conform ::intersection-args-2 '[#{1 2} #{2 3} #{2 5}])
;;=> [#{1 2} #{3 2} #{2 5}]

(s/def ::meta map?)
(s/def ::validator ifn?)
(s/def ::atom-args
  (s/cat :x any?
         :options 
         (s/keys* 
           :opt-un
           [::meta ::validator])))
(s/conform ::atom-args [100 :meta {:foo 1} :validator int?])
;;=> {:x 100,
;;  :options
;;  {:meta {:foo 1},
;;   :validator
;;   #object[clojure.core$int_QMARK_ 0x1631d671 "clojure.core$int_QMARK_@1631d671"]})

; p02: Multi-arity Argument Lists

(s/def ::repeat-args
  (s/cat :n (s/? int?) :x any?))
(s/conform ::repeat-args [100 "foo"])
;;=> {:n 100, :x "foo"}
(s/conform ::repeat-args ["foo"])
;;=> {:x "foo"}

; p03: Specifying Functions

(s/def ::rand-args (s/cat :n (s/? number?)))
(s/def ::rand-ret double?)
(s/def ::rand-fn
  (fn [{:keys [args ret]}]
    (let 
      [n (or (:n args) 1)]
      (cond 
        (zero? n) (zero? ret)
        (pos? n) (and (>= ret 0) (< ret n))
        (pos? n) (and (<= ret 0) (> ret n))))))
(s/fdef clojure.core/rand
  :args ::rand-args
  :ret ::rand-ret
  :fn ::rand-fn)        

; p04: Anonymous Functions

(defn opposite [pred]
  (comp not pred))

(s/def ::pred
  (s/fspec
    :args (s/cat :x any?)
    :ret boolean?))
;;=> :specs-functions/pred
(s/fdef opposite
  :args (s/cat :pred ::pred)
  :ret ::pred)

; p05: Instrumenting Functions

; p06: Generative Function Testing


