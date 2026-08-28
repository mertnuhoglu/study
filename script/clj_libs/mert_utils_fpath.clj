(ns mert-utils-fpath)
;; [[mert_utils_fpath.clj]]

(require '[clojure.java.io :as io]
         '[clojure.string :as str]
         '[cheshire.core :as json]
         '[babashka.fs :as fs]
         '[babashka.process :as p])

(require '[mert-utils-date :refer [get-dt get-time get-ts get-date-time-as-kebab-short]])

(def rgx
  {
   :FileExtension (re-pattern "\\.\\w+$")
   :Dt (re-pattern "\\b20[0-3]\\d{5}\\b")
   :TsInFlName (re-pattern "(-\\d{4}-\\d{6}\\.)")})

(defn nm-w-ts-post [m]
  ;; exmp: [[d20250710-bb-exmp-nm-w-dt.clj]]
  (let [current-filename (fs/file-name (:flPt m))
        ts-rgx (:TsInFlName rgx)
        cfname (if (re-find ts-rgx current-filename)
                 (str/replace current-filename ts-rgx "-@@@.")
                 (str/replace current-filename #"\.(\w+)$" "-@@@.$1"))
        timestamp (get-date-time-as-kebab-short)
        new-fname (str/replace cfname "@@@" timestamp)]
    new-fname))

(defn nm-w-dt [m]
  (let [cflnm (fs/file-name (:flPt m))
        dt-rgx (:Dt rgx)
        date-in-cflnm (re-find dt-rgx cflnm)
        new-dt (get-dt)
        cflnm2 (if date-in-cflnm
                 (str/replace cflnm date-in-cflnm new-dt)
                 (str new-dt "-" cflnm))]
    cflnm2))

(defn nm-w-dt-pre-ts-post [m]
  (let [w-date (nm-w-dt m)
        fl-pt (fs/parent (:flPt m))
        new-fl-pt (str (fs/path fl-pt w-date))
        m-upd (assoc m :flPt new-fl-pt)
        w-ts (nm-w-ts-post m-upd)]
    w-ts))

