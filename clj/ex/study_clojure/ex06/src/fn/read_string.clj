(ns fn.read-string)

; (read-string s)
; (read-string opts s)
; Reads one object from the string s. Optionally include reader
; options, as specified in read.
;  Note that read-string can execute code
; (controlled by *read-eval*),
; and as such should be used only with trusted sources.
;  For data structure interop use clojure.edn/read-string
;

(read-string "1.1")
;1.1

#_(read-string "1.1.1 (+ 1 1)")
;java.lang.RuntimeException: java.lang.NumberFormatException: Invalid number: 1.1.1 (NO_SOURCE_FILE:0)

(read-string "(+ 1 1)")
;(+ 1 1)

(eval (read-string "(+ 1 1)"))
;2
; rfr: fn/eval.clj

(read-string (prn-str (+ 1 1)))
;2

(+ 11 (read-string "23"))
;34

;; you can think of read-string as the inverse of pr-str
;; turn string into symbols
(read-string "(a b foo :bar)")
;(a b foo :bar)

;;turn symbols into a string
(pr-str '(a b foo :bar))
;"(a b foo :bar)"
