(ns fn.print)

; rfr: video/20230221-mert-clj-egzersiz-46.mp4

; (print & more)
; Prints the object
; (s) to the output stream that is the current value
; of *out*.  print and println produce output for human consumption.
;

; print fonksiyonu nil döner, herhangi bir değer dönmez
; fakat ekrana yazıyı basar
; ekrana yazı basma dediğimiz şey, bir side-effecttir
; fakat "ali" stringi "ali" stringini döner
; ekrana yazı basmaz. bu side-effect değildir.
(print "ali")
;ali=> nil
; not: bir formun döndüğü değer repl tarafından "=>" sembolünden sonra gösterilir
"ali"
;=> "ali"
(identity "ali")
;=> "ali"

