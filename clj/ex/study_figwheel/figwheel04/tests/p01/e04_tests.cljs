(ns p01.e04-tests
  (:require [cljs.test :refer-macros [deftest is run-tests]]))

(deftest should-not-pass
  (is (= 1 20)))

(run-tests)
