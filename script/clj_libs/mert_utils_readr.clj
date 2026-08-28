;; [[mert_utils_readr.clj]]
(ns mert-utils-readr)

(require '[clojure.java.io :as io]
         '[clojure.string :as str]
         '[cheshire.core :as json]
         '[babashka.fs :as fs]
         '[babashka.process :as p])

(require '[mert-utils-data-structure :refer [sort-map file->set setops files2setops]])

(defn write-file [content file-path]
  (spit file-path content))

(defn write-json [data file-path]
  ;; Sorts keys and pretty-prints using Cheshire. But for outlist, we use non-sorted and then format.
  (let [sorted-data (sort-map data)
        json-text (json/generate-string sorted-data {:pretty true})]
    (write-file json-text file-path)))
    ;; (format-json file-path) ;; Optional if needed elsewhere
    
(defn read-json [file-path]
  (json/parse-string (slurp file-path) true)) ;; true for keyword keys

