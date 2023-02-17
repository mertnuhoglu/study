(ns fn.pr-str)

; (pr-str & xs)
; pr to a string, returning it
;

(def x [1 2 3 4 5])
(identity x)
;[1 2 3 4 5]

;; Turn that data into a string...
(pr-str x)
;"[1 2 3 4 5]"

;; ...and turn that string back into data!
(read-string (pr-str x))
;[1 2 3 4 5]
