(ns fn.use)

; rfr: fn/require.clj

; [Learn clojure in Y Minutes](https://learnxinyminutes.com/docs/clojure/)

; Use "use" to get all functions from the module
(use 'clojure.set)

; Now we can use set operations
(intersection #{1 2 3} #{2 3 4})
; => #{2 3}
(difference #{1 2 3} #{2 3 4})
; => #{1}

; :only clause
; You can choose a subset of functions to import, too
(use '[clojure.set :only [intersection]])

