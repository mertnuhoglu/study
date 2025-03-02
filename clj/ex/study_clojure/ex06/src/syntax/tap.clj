(ns syntax.tap)
;; [[tap.clj]]

; [claude](https://claude.ai/chat/31e50816-4952-42b2-87f3-c3a1fbfd28f2)

;; Basic usage
(tap> :value)  ; Sends value to tap system, returns value

;; (def data [1 2 3])
;; (def process-data (reduce +))
;; (def next-step min)

;; In a thread-first macro
;; (-> data
;;     process-data
;;     (tap>)    ; Observe the value here
;;     next-step)

;; Setting up a tap handler
(add-tap println)  ; Add println as a handler
(tap> "hello")    ; Will print "hello" and return "hello"
;; Not: print conjure logda görünmüyor, ama intellij repl da görünüyor.
(println "hello")

;; Multiple handlers
(add-tap #(println "Handler 1:" %))
(add-tap #(println "Handler 2:" %))
(tap> "test")     ; Both handlers will receive "test"
