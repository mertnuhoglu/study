;; [[mert_utils_io_serialize.clj]]
(ns mert-utils-io-serialize)

(require '[clojure.java.io :as io]
         '[clojure.string :as str]
         '[cheshire.core :as json]
         '[babashka.fs :as fs]
         '[babashka.process :as p])


