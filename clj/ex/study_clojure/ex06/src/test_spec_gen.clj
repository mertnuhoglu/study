(ns test_spec_gen
  (:require [clojure.spec.alpha :as spec]
    [clojure.spec.gen.alpha :as spec-gen]))

(spec/def ::first-name string?)

(comment

  (spec-gen/sample (spec/gen ::first-name)))
