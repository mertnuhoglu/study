;(ns cljs_hello.core)

;(ffirst [1])
;(println "Hello world2!")
;(ffirst [1])

;(defn average [a b]
  ;(/ (+ a b) 2.0))

(ns cljs_hello.core
  (:require react-dom))

(.render js/ReactDOM
  (.createElement js/React "h2" nil "Hello, React!")
  (.getElementById js/document "app"))
