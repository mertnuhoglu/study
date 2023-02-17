(ns fn.keyword)

; rfr: video/20230217-mert-clj-egzersiz-41.mp4

; (keyword name)(keyword ns name)
; Returns a Keyword with the given namespace and name.  Do not use :
; in the keyword strings, it will be added automatically.

(keyword "a")
;=> :a

(keyword "com.example" "a")
;=> :com.example/a

(keyword "user" "foo")
;:user/foo