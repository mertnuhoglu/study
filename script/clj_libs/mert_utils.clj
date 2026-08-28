;; refer: Single utils module containing several submodules   || ((21ffe1f3-f09e-4427-b7de-e0c1b7eae99d))
;; [grok](https://grok.com/share/bGVnYWN5_e245f35c-0624-4bec-a9d1-75edb47d9864)
;;
;; [[mert_utils_base.clj]]
;; [[mert_utils_data_structure.clj]]
;; [[mert_utils_date.clj]]
;; [[mert_utils_fp.clj]]
;; [[mert_utils_fpath.clj]]
;; [[mert_utils_fpath_dir.clj]]
;; [[mert_utils_io.clj]]
;; [[mert_utils_text.clj]]
;; [[mert_utils_text_bbl.clj]]
;; [[mert_utils_io_logging.clj]]
;; [[mert_utils_io_serialize.clj]]
;; [[mert_utils_readr.clj]]
;; [[mert_utils_debug.clj]]
;; [[mert_utils_tfrm.clj]]
;; [[edn2json]]

(ns mert-utils
  (:require
    [mert-utils-base :as base]
    [mert-utils-data-structure :as ds]
    [mert-utils-date :as dt]
    [mert-utils-fp :as fp]
    [mert-utils-fpath :as fpath]
    [mert-utils-fpath-dir :as fpath-dir]
    [mert-utils-io :as io]
    [mert-utils-text :as text]
    [mert-utils-text-bbl :as text-bbl]
    [mert-utils-io-logging :as io-logging]
    [mert-utils-io-serialize :as io-srl]
    [mert-utils-readr :as readr]
    [mert-utils-debug :as dbg]
    [mert-utils-tfrm :as tfrm]))
    
(require '[clojure.java.io :as io]
         '[clojure.string :as str]
         '[cheshire.core :as json]
         '[babashka.fs :as fs]
         '[babashka.process :as p])

;; ───────────────────────────────────────────────
;; mert-utils-base
;; ───────────────────────────────────────────────
(def to-string-var base/to-string-var)
(def globals base/globals)

;; ───────────────────────────────────────────────
;; mert-utils-data-structure
;; ───────────────────────────────────────────────
(def files2setops    ds/files2setops)
(def file->set       ds/file->set)
(def setops          ds/setops)
(def sort-map        ds/sort-map)

;; ───────────────────────────────────────────────
;; mert-utils-date
;; ───────────────────────────────────────────────
(def get-dt                      dt/get-dt)
(def get-time                    dt/get-time)
(def get-ts                      dt/get-ts)
(def get-date-time-as-kebab-short dt/get-date-time-as-kebab-short)

;; ───────────────────────────────────────────────
;; mert-utils-fpath
;; ───────────────────────────────────────────────
(def nm-w-ts-post                fpath/nm-w-ts-post)
(def nm-w-dt                     fpath/nm-w-dt)
(def nm-w-dt-pre-ts-post         fpath/nm-w-dt-pre-ts-post)

;; ───────────────────────────────────────────────
;; mert-utils-io-logging
;; ───────────────────────────────────────────────
(def append-file                 io-logging/append-file)
(def write-runlog                io-logging/write-runlog)

;; ───────────────────────────────────────────────
;; mert-utils-readr
;; ───────────────────────────────────────────────
(def write-file                  readr/write-file)
(def write-json                  readr/write-json)
(def read-json                   readr/read-json)
    

