(ns fn.keyword)

;(keyword name)(keyword ns name)
;Returns a Keyword with the given namespace and name.  Do not use :
;in the keyword strings, it will be added automatically.

(keyword "com.example" "a")
;=> :com.example/a

(keyword "user" "foo")
;:user/foo