(ns ex00.ex00.async02
  (:require [clojure.core.async :as async :refer [go <! go-loop timeout]]))

;; [Intro to Core Async | Lesson 25 | Learn ClojureScript](https://www.learn-clojurescript.com/section-4/lesson-25-intro-to-core-async/)

(go (println "hello process"))
; (out) hello process
; #object[clojure.core.async.impl.channels.ManyToManyChannel 0x662571f0 "clojure.core.async.impl.channels.ManyToManyChannel@662571f0"]

(comment
  (go (loop [])
    (let [val (<! in-ch)]                                    ;; <1>
      (when (pred? val)                                      ;; <2>
        (>! out-ch val)))                                    ;; <3>
    (recur))
  ; 1. Read a value from in-ch
  ; 2. Test the value with pred?
  ; 3. Write the value to out-ch

  (go-loop []
    (<! (timeout 100))
    (println "Hello from process 1")
    (recur))
  ; (out) Hello from process 1
  ; (out) Hello from process 1
  ; (out) Hello from process 1

  (go-loop []
    (<! (timeout 250))
    (println "Hello from process 2")
    (recur))
  ,)

