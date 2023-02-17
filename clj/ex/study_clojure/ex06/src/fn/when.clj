(ns fn.when)

; rfr: video/20230217-mert-clj-egzersiz-41.mp4

; (when test & body)
; Evaluates test. If logical true, evaluates body in an implicit do.
;

(when (= 1 1) true)
;true

(when (not= 1 1) true)
;nil

(if (= 1 1) true nil)
;true

(if (not= 1 1) true nil)
;nil
