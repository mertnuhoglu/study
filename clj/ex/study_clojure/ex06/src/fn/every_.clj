(ns fn.every?)

; some ve every birbirine benzer
; some: bir tane öğe bile even? ise true döner
; every: tüm öğeler even? ise true döner
(some even? '(1 2 3 4))
;;=> true
(every? even? '(1 2 3 4))
;=> false

