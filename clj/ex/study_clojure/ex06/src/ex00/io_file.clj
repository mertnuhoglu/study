(ns ex00.io_file)

;; Prerequisite: Run portal browser first

(require '[clojure.java.io :refer [reader]])

(with-open 
  [rdr (reader "deps.edn")] 
  (count (line-seq rdr)))

(require '[clojure.edn :as edn])

(edn/read-string (slurp "deps.edn"))
(tap> (edn/read-string (slurp "deps.edn")))

