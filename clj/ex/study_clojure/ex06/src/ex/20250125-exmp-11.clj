(ns ex.20250125_exmp_11
  (:require [clojure.core :refer [seque]]))
;; [[20250125-exmp-11.clj]]

; Clojure: Give an example for seque function #prg/clj #f/prmp
;   id:: d87729ee-172e-49ad-8b9f-48b2e8d703bc


; [o1](https://chatgpt.com/c/679791c1-f058-8012-9c80-456cb26c6d59)

(defn slow-inc [x]
  ;; Simulate a slow operation
  (Thread/sleep 100)
  (inc x))

(defn demo-seque []
  (let [input  (range 10)
        ;; '2' is the buffer size for the queue
        ;; the collection after that is our "producer" of data
        output (seque 2 (map slow-inc input))]
    (doseq [n output]
      (println "Got:" n))))

;; Usage:
(demo-seque)
;; => prints incremented values from 1 to 10 with a slight delay
; (out) Got: 1
; (out) Got: 2
; (out) Got: 3
; (out) Got: 4
; (out) Got: 5
; (out) Got: 6
; (out) Got: 7
; (out) Got: 8
; (out) Got: 9
; (out) Got: 10
nil

