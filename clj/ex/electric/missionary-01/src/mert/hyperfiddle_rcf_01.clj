(ns mert.hyperfiddle-rcf-01
  (:require [hyperfiddle.rcf]
            [hyperfiddle.rcf :refer [tests tap %]]))
;; [[hyperfiddle_rcf_01.clj]]
;;
;; spcs: Study: Hyperfiddle Rcf Test Library `prg/clj` || ((5d5fbd8b-f8ec-470c-af12-fef7f18f5a1e))

(hyperfiddle.rcf/enable!)

(tests
  "equality"
  (inc 3) := 4)
; (out) ✅

(tests
  "equality"
  (inc 1) := 2

  "wildcards"
  {:a :b, :b [2 :b]} := {:a _, _ [2 _]}

  "unification"
  {:a :b, :b [2 :b]} := {:a ?b, ?b [2 ?b]}

  "unification on reference types"
  (def x (atom nil))
  {:a x, :b x} := {:a ?x, :b ?x}
  
  "multiple tests on one value"
  (def xs [:a :b :c])
  (count xs) := 3
  (last xs) := :c
  (let [xs (map identity xs)]
    (last xs) := :c
    (let [] (last xs) := :c))

  "exceptions"
  (assert false "boom") :throws java.lang.AssertionError

  (tests
    "nested tests (is there a strong use case?)"
    1 := 1)

  (tests
    "REPL bindings work"
    (keyword "a") := :a
    (keyword "b") := :b
    (keyword "c") := :c
    *1 := :c
    *2 := :b
    *3 := :a
    *1 := :c                   ; inspecting history does not affect history

    (keyword "d") := :d
    *1 := :d
    *2 := :c
    *3 := :b
    (symbol *2) := 'c          ; this does affect history
    (symbol *2) := 'd))
; (out) ✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅
