(ns fn.hash-map)

{:a 1}
(hash-map :a 1)

; bir mapin içindeki öğelerden herhangi birini key ile aratıp değerini alabiliyoruz
(:a {:a 1})
;=> 1
; burada dikkat edersen `:a` keywordü aslında bir fonksiyon olarak kullanılmış oldu
; bunun tam tersine hash-map'i de fonksiyon olarak kullanabiliriz

({:a 1} :a)
;=> 1

