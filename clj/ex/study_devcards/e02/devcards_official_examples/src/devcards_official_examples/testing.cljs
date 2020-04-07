(ns devcards_official_examples.testing
  (:require
   [sablono.core :as sab :include-macros true]
   [reagent.core :as reagent]
   [clojure.string :as string]
   [cljs.test :as t :include-macros true :refer-macros [testing is]])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

(deftest cljs-test-integration
  "## Here are some example tests"
  (testing "testing context 1"
    (is (= (+ 3 4 55555) 4) "This is the message arg to an 'is' test")
    (is (= (+ 1 0 0 0) 1)
        "This should work")
    (is (= 1 3))              
    (is false)
    (is (throw "errors get an extra red line on the side")))
  "Top level strings are interpreted as markdown for inline documentation."
  (t/testing "testing context 2"
    (is (= (+ 1 0 0 0) 1))        
    (t/is (= (+ 3 4 55555) 4))
    (t/is false)
    (t/testing "nested context"
      (is (= (+ 1 0 0 0) 1))        
      (t/is (= (+ 3 4 55555) 4))
      (t/is false))))

