(ns fn.remove)

; rfr: video/20230216-mert-clj-egzersiz-40.mp4

; remove filter'ın tam tersi

(filter odd? [1 2 3])
;=> (1 3)
(remove odd? [1 2 3])
;=> (2)

; filter odd?: tekil olanları filtrele, sadece onlar kalsın
; remove odd?: tekil olanları sil, diğerleri kalsın