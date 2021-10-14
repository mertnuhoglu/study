(ns user
  (:require [portal.api :as p]
            [clojure.java.io :refer [reader] :as io]
            [clojure.edn :as edn]
            [clojure.data :refer [diff]]
            [clojure.pprint :refer [pprint]]))

;; Start Portal inspector on REPL start

;; Open a portal inspector window
(def portal (p/open))

;; Add portal as a tap> target
(p/tap)

(comment
  (tap> :hello)
  (-> @portal first)
  (swap! portal keys)
  ;; Clear all values in the portal inspector window
  (p/clear)

  ;; Close the inspector
  (p/close)
  ,)

;; End of rich comment block
