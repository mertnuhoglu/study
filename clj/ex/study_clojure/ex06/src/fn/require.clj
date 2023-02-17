(ns fn.require)

; rfr: fn/use.clj

; [Learn clojure in Y Minutes](https://learnxinyminutes.com/docs/clojure/)

; Use require to import a module
(require 'clojure.string)

(clojure.string/blank? "")
; => true

; :as clause

(require '[clojure.string :as str])
(str/replace "This is a test." #"[a-o]" str/upper-case)
; => "THIs Is A tEst."
; (#"" denotes a regular expression literal)

; ns içinde :require
; bu şekilde modülleri quote etmene gerek yok
(ns test
  (:require
    [clojure.string :as str]
    [clojure.set :as set]))


