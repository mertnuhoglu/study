(ns async01
  (:require [clojure.core.async :as async]))

; https://github.com/clojure/core.async/wiki/Getting-Started

(def a-channel (async/chan 1))

(async/>!! a-channel "Hello")

(async/<!! a-channel)

; https://github.com/clojure/core.async/wiki/Pub-Sub

(def input-chan (async/chan))
(def our-pub (async/pub input-chan :msg-type))

(async/>!! input-chan {:msg-type :greeting :text "hello"})

(def output-chan (async/chan))
(async/sub our-pub :greeting output-chan)

(async/go-loop []
  (let [{:keys [text]} (async/<! output-chan)]
    (println text)
    (recur)))

(async/>!! input-chan {:msg-type :greeting :text "hi2"})
