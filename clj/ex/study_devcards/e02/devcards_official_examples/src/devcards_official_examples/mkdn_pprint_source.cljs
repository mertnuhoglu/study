(ns devcards_official_examples.mkdn-pprint-source
  (:require
   #_[om.core :as om :include-macros true]
   [sablono.core :as sab :include-macros true])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

(defn sample-function [] "returned")

(defcard
  "## Any Available Source

  Because the `mkdn-pprint-source` makes use of the `cljs.repl` to get the
  source code for an object, we can use it to display the source code for any
  object accessible to our current namespace. For example:"

  (dc/mkdn-pprint-source sample-function))

