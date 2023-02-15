(ns fn.str)

;(str) #nclk/çok-önemli
;(str x)
;(str x & ys)
;With no args, returns the empty string. With one arg x, returns
;x.toString().  (str nil) returns the empty string. With more than
;one arg, returns the concatenation of the str values of the args.

; String birleştirmek için bunu kullanıyoruz
; Collection birleştirmek için concat kullanıyoruz

(str "ali" " ve " "veli")
;=> "ali ve veli"

"some string"
"some string"

(str)
;""

(str nil)
;""

;(str 1)
"1"

;(str 1 2 3)
"123"

(str 1 'symbol :keyword)
;"1symbol:keyword"

;; A very common usage of str is to apply it to an existing collection:
(apply str [1 2 3])
;"123"

;; compare it with:
(str [1 2 3])
;"[1 2 3]"
