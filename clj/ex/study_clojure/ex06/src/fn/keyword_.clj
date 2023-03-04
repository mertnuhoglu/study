(ns fn.keyword?)

; rfr: video/20230217-mert-clj-egzersiz-41.mp4

; (keyword? x)
; Return true if x is a Keyword
;

(keyword? 'x)
;;=> false

(keyword? :x)
;;=> true

(keyword? true)
;;=> false

(keyword? :true)
;;=> true
