(ns deep-diff.deep-diff)

(require '[lambdaisland.deep-diff2 :as ddiff])

(defn pretty-print [& args]
  (ddiff/pretty-print (ddiff/diff {:a 1 :b 2} {:a 1 :c 3})))

