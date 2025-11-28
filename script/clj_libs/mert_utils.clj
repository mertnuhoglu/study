(ns mert-utils)

(require '[clojure.java.io :as io]
         '[clojure.string :as str]
         '[cheshire.core :as json]
         '[babashka.fs :as fs]
         '[babashka.process :as p])

(import '[java.time LocalDateTime]
        '[java.time.format DateTimeFormatter])

;; [grok](https://grok.com/share/bGVnYWN5_26b127ba-adc2-432d-a3b3-ab3538b183a0)

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

(defn append-file [content file-path]
  (with-open [w (io/writer file-path :append true)]
    (.write w (str content "\n"))))

(defn to-string-var [var]
  (pr-str var))

(def globals 
  (json/parse-string (slurp "/Users/mertnuhoglu/prj/private_dotfiles/.config/params/global_params_for_myutils.json") true))
;; (json/parse-string (slurp (str (:args_dir globals) "/" fn)) true))

(defn get-default-fl-pt-args []
  {:flPt ""
   :tmpl {:title ""}
   :ext ""})

(defn write-file [content file-path]
  (spit file-path content))

(defn sort-map [x]
  (cond
    (map? x) (into (sorted-map) (map (fn [[k v]] [k (sort-map v)]) x))
    (sequential? x) (vec (map sort-map x))
    :else x))

(defn write-json [data file-path]
  ;; Sorts keys and pretty-prints using Cheshire. But for outlist, we use non-sorted and then format.
  (let [sorted-data (sort-map data)
        json-text (json/generate-string sorted-data {:pretty true})]
    (write-file json-text file-path)))
    ;; (format-json file-path) ;; Optional if needed elsewhere
    
(defn read-json [file-path]
  (json/parse-string (slurp file-path) true)) ;; true for keyword keys

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

(defn write-runlog [content]
  (let [fpath (:runlog_txt globals)
        c2 (str (get-ts) " type: " (type content) "\n" (to-string-var content))]
    (append-file c2 fpath)))

