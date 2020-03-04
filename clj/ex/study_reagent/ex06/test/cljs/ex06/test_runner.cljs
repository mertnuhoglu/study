(ns ex06.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [ex06.core-test]
   [ex06.common-test]))

(enable-console-print!)

(doo-tests 'ex06.core-test
           'ex06.common-test)
