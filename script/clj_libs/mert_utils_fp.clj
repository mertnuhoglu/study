;; [[mert_utils_fp.clj]]
(ns mert-utils-fp)

(require '[clojure.java.io :as io]
         '[clojure.string :as str]
         '[cheshire.core :as json]
         '[babashka.fs :as fs]
         '[babashka.process :as p])

