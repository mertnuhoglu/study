#!/usr/bin/env bb

(require '[babashka.pods :as pods])
;; downloads and installs jar files 
(pods/load-pod 'org.babashka/buddy "0.1.0")

;; use the pod
(require '[pod.babashka.buddy.core.hash :as hash])
(hash/md5 "foo")


