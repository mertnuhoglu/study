(ns mert-utils-date)

;; [[mert_utils_date.clj]]

(require '[clojure.java.io :as io]
         '[clojure.string :as str]
         '[cheshire.core :as json]
         '[babashka.fs :as fs]
         '[babashka.process :as p])

(import '[java.time LocalDateTime]
        '[java.time.format DateTimeFormatter])

(defn get-dt []
  (let [now (LocalDateTime/now)
        formatter (DateTimeFormatter/ofPattern "yyyyMMdd")]
    (.format now formatter)))

(defn get-time []
  (let [now (LocalDateTime/now)
        formatter (DateTimeFormatter/ofPattern "HHmmss")]
    (.format now formatter)))

(defn get-ts []
  (str (get-dt) "-" (get-time)))

(defn get-date-time-as-kebab-short [] 
  (let [now (LocalDateTime/now)
        formatter (DateTimeFormatter/ofPattern "MMdd-HHmmss")]
    (.format now formatter)))

(defn get-default-fl-pt-args []
  {:flPt ""
   :tmpl {:title ""}
   :ext ""})

