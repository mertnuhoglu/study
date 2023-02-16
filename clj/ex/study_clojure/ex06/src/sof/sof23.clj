(ns sof.sof23)

; [Create a list from a string in Clojure - Stack Overflow](https://stackoverflow.com/questions/4836768/create-a-list-from-a-string-in-clojure)

; a01
(seq "aaa")
;(\a \a \a)

(map #(Character/getNumericValue %) "123")
;(1 2 3)

; a02: you wanted a list of different characters
(frequencies "lazybrownfox")
;{\a 1, \b 1, \f 1, \l 1, \n 1, \o 2, \r 1, \w 1, \x 1, \y 1, \z 1}
(apply str (keys (frequencies "lazybrownfox")))
;"abflnorwxyz"

; a03:
(apply str (set "lazybrownfox"))
;"abflnorwxyz"
