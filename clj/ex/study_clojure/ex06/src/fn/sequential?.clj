(ns fn.sequential?)

; (sequential? coll)
; Returns true if coll implements Sequential

; Standart: herhangi bir fonksiyonun isminin sonunda `?` varsa, o fonksiyon logical değer döner.

; bu fonksiyon kendisine verilen argümanın bir sequence benzeri olup olmadığını dönüyor

(sequential? '(1 2 3))
;true

(sequential? [1 2 3])
;true

(sequential? (range 1 5))
;true

(sequential? '())
;true

(sequential? [])
;true

(sequential? nil)
;false

(sequential? 1)
;false

(sequential? "abc")
;false

(sequential? {:a 2 :b 1})
;false

(sequential? #{1 2 3})
;false

