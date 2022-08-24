(ns require_functions)

;(ns help_functions
;  (:require [clojure.inspector :as ins]))

(use '[clojure.string :only (split)])

(require '[clojure.tools.trace :refer [trace, dotrace]])

(require '[clojure.pprint :as pp])

(require '[clojure.repl])
(require '[clojure.repl :refer :all])

; inside code:
;(ns ex01
;  {:import java.io.File}
;  {:require [clojure.java.io :as io]
;   [clojure.core.protocols :as p])

