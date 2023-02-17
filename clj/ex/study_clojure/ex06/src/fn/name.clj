(ns fn.name)

; rfr: video/20230217-mert-clj-egzersiz-41.mp4

; (name x)
; Returns the name String of a string, symbol or keyword.
;

;; the name of the keyword is without the ':'
;; "str" will retain the ':'.
(name :x)
;;=> "x"

(name "x")
;;=> "x"

;; returns the symbol name as a string without the namespace.
(name 'x)
;;=> "x"

(name 'user/x)
;;=> "x"

;; throws an error for invalid types, no nil punning
#_(name nil)
;;=> Error: Doesn't support name:

#_(name 2)
;;=> Error: Doesn't support name: 2