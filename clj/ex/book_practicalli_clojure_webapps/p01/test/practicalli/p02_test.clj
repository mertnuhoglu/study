(ns practicalli.p02-test
  (:require [clojure.test :refer [deftest is testing]]
            [practicalli.p02 :as SUT]))

(deftest handler-test
  (testing "response to events"
    (is (= 200 (:status (SUT/app {}))))))
