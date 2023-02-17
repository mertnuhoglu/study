(ns fn.syntax.multi-variadic-functions)

; [Learn clojure in Y Minutes](https://learnxinyminutes.com/docs/clojure/)

; You can have multi-variadic functions, too
(defn hello3
  ([] "Hello World")
  ([name] (str "Hello " name)))

(hello3 "Jake")
; => "Hello Jake"
(hello3)
; => "Hello World"

