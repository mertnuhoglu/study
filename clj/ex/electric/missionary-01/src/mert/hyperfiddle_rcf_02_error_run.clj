(ns mert.hyperfiddle-rcf-02-error-run
   (:require 
             [hyperfiddle.electric :as e]
             [hyperfiddle.rcf :as rcf :refer [tests tap % with]]))
;; spcs: Error: `hyperfiddle.electric/run` `hyperfiddle.rcf` || ((475098b8-97d4-4f91-9e17-5ca1e2d9c4d8))             

(def !x (atom 0))
(e/run
  (let [x (e/watch !x)]))
; (err) Unexpected error (NullPointerException) macroexpanding hyperfiddle.electric/local at (src/mert/hyperfiddle_rcf_02_error_run.clj:9:1).
; (err) Cannot invoke "java.util.concurrent.Future.get()" because "fut" is null

