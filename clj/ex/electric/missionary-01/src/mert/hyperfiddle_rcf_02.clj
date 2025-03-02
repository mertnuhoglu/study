(ns mert.hyperfiddle-rcf-02
   (:require [clojure.core.async :refer [chan >! go go-loop <! timeout close!]]
             [hyperfiddle.electric :as e]
             [hyperfiddle.rcf :as rcf :refer [tests tap % with]]
             [missionary.core :as m]))
;; [[hyperfiddle_rcf_02.clj]]
;;
;; Async Tests
;; hyperfiddle_rcf_02_error_run

(hyperfiddle.rcf/enable!)
(rcf/set-timeout! 100)

(tests
  "async tests"
  #?(:clj  (tests
             (future
               (tap 1) (Thread/sleep 10)        ; tap value to queue
               (tap 2) (Thread/sleep 200)
               (tap 3))
             % := 1                               ; pop queue
             % := 2
             % := ::rcf/timeout)
     :cljs (tests
             (defn setTimeout [f ms] (js/setTimeout ms f))
             (tap 1) (setTimeout 10 (fn []))
             (tap 2) (setTimeout 200 (fn []))
             (tap 3)
             % := 1
             % := 2
             % := ::rcf/timeout)))
; (out) ✅✅✅

(tests 
  "electric01"
  (def !x (atom 0))
  (e/run
    (let [x (e/watch !x)])))

(tests
  "electric"
  (def !x (atom 0))
  (def dispose
    (e/run
      (let [x (e/watch !x)
            a (inc x)
            b (inc x)]
        (tap (+ a b)))))
  % := 2
  (swap! !x inc)
  % := 4
  (swap! !x inc)
  % := 6
  (dispose))

(tests
  "core.async"
  (def c (chan))
  (go-loop [x (<! c)]
    (when x
      (<! (timeout 10))
      (tap x)
      (recur (<! c))))
  (go (>! c :hello) (>! c :world))
  % := :hello
  % := :world
  (close! c))

(tests
  "missionary"
  (def !x (atom 0))
  (def dispose ((m/reactor (m/stream! (m/ap (tap (inc (m/?< (m/watch !x)))))))
                (fn [_] #_(prn ::done)) #(prn ::crash %)))
  % := 1
  (swap! !x inc)
  (swap! !x inc)
  % := 2
  % := 3
  (dispose))
