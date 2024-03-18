(ns ex00.state)

; Code from: Programming Clojure, Alex Miller

; p01: Refs and Software Transactional Memory

; p01.01: (ref initial-state)

(def current-track (ref "Mars"))

; p01.02: (deref reference)

(deref current-track)
;;=> "Mars"

(identity @current-track)
;;=> "Mars"

; p01.03: (ref-set reference new-value)

(ref-set current-track "Venus")
; (err) Execution error (IllegalStateException) at state/eval9210 (REPL:21).

; p01.04: (dosync & exprs)

(dosync (ref-set current-track "Venus"))
;;=> "Venus"

; p02: (alter ref update-fn & args...)

(defrecord Message [sender text])
(->Message "Ali" "Hi")
;;=> {:sender "Ali", :text "Hi"}

(def messages (ref ()))

; bad 
(defn naive-add-message [msg]
  (dosync (ref-set messages (cons msg @messages))))

(defn add-message [msg]
  (dosync (alter messages conj msg)))

(add-message (->Message "user1" "hi"))
(add-message (->Message "user2" "hola"))
;;=> ({:sender "user2", :text "hola"} {:sender "user1", :text "hi"})

; p02.02: (commute ref update-fn & args...)
; updates are commutative (in any order)

(defn add-message-commute [msg]
  (dosync (commute messages conj msg)))

; p03: Validation to refs
; (ref initial-state options*)
; options:
;   :validator validate-fn
;   :meta metadata-map

(defn valid-message? [msg]
  (and (:sender msg) (:text msg)))
(def validate-message-list #(every? valid-message? %))
(def messages (ref () :validator validate-message-list))

(add-message "not valid message")
; (err) Execution error (IllegalStateException) at state/add-message (REPL:42).

(identity @messages)
;;=> ()

(add-message (->Message "user1" "hi"))
;;=> ({:sender "user1", :text "hi"})

; p03: (atom initial-state options?)

(def current-track (atom "Mars"))

(deref current-track)
;;=> "Mars"

(identity @current-track)
;;=> "Mars"

; p03.02: (reset! an-atom newval)

(reset! current-track "Credo")
;;=> "Credo"

(reset! current-track {:title "t01" :composer "c01"})
;;=> {:title "t01", :composer "c01"}

; p03.03: (swap! an-atom f & args)

(swap! current-track assoc :title "t02")
;;=> {:title "t02", :composer "c01"}

; p04: (agent initial-state)

(def counter (agent 0))

; p04.02: (send agent update-fn & args)
; similar to commute

(send counter inc)
;;=> #<Agent@b7d9577: 1>

(deref counter)
;;=> 1
