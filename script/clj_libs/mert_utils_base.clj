;; [[mert_utils_base.clj]]
(ns mert-utils-base)

(require '[clojure.java.io :as io]
         '[clojure.string :as str]
         '[cheshire.core :as json]
         '[babashka.fs :as fs]
         '[babashka.process :as p])

(defn to-string-var [var]
  (pr-str var))

(def globals 
  (json/parse-string (slurp "/Users/mertnuhoglu/prj/private_dotfiles/.config/params/global_params_for_myutils.json") true))
;; (json/parse-string (slurp (str (:args_dir globals) "/" fn)) true))

