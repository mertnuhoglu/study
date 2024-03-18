(ns ex00.clojure_ref_transducers
  (:require [clojure.string :as str]))

filter odd?

(def xf
  (comp
    (filter odd?)
    (map inc)))

;; transduce

(transduce xf + (range 5)) ; 6
(transduce xf + 100 (range 5)) ; 106

;; eduction

(def iter (eduction xf (range 5)))
(reduce + 0 iter)

;; into

(into [] xf (range 5)) ; [2 4]

;; sequence

(sequence xf (range 5)) ; (2 4)

(reify)

