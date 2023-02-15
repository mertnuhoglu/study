(ns fn.cycle)

; (cycle coll) #nclk/önemli
; Returns a lazy (infinite!) sequence of repetitions of the items in coll.

; Not: lazy ama infinite (sonsuz) seq üretiyor.
; dolayısıyla cycle fonksiyonunu tek başına kullanamazsınız şunun gibi:
#_(cycle "a")
; "a" "a" "a" ....

; bunu sınırlandırmamız lazım
; take kullanırız sınırlandırmak için
; rfr: fn/take.clj #nclk/çok-önemli
(take 3 (cycle ["foo"]))
;=> ("foo" "foo" "foo")

; eğer cycle'a verdiğimiz stringi collection içine koymazsak, stringi collection olarak kabul eder
(take 6 (cycle "foo"))
;=> (\f \o \o \f \o \o)
