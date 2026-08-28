(ns mert-utils-io-logging)
;; [[mert_utils_io_logging.clj]]

(require '[clojure.java.io :as io]
         '[clojure.string :as str]
         '[cheshire.core :as json]
         '[babashka.fs :as fs]
         '[babashka.process :as p])

(require '[mert-utils-base :refer [to-string-var globals]]
         '[mert-utils-date :refer [get-dt get-time get-ts get-date-time-as-kebab-short]])

(defn append-file [content file-path]
  (with-open [w (io/writer file-path :append true)]
    (.write w (str content "\n"))))

(defn write-runlog [content]
  (let [fpath (:runlog_txt globals)
        c2 (str (get-ts) " type: " (type content) "\n" (to-string-var content))]
    (append-file c2 fpath)))

