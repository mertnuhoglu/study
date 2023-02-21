(ns fn.and)

; rfr: video/20230221-mert-clj-egzersiz-46.mp4

; (and)      #nclk/çok-önemli
; (and x)
; (and x & next)
; Evaluates exprs one at a time, from left to right. If a form
; returns logical false
; (nil or false), and returns that value and
; doesn't evaluate any of the other expressions, otherwise it returns
; the value of the last expr.
; (and) returns true.
;

(and true false)
;=> false
(and false true)
;=> false

; false sonucu alınır alınmaz o sonucu döner ve sonraki ifadeleri (exprs) çalıştırmaz
(and false (print "ali"))
;=> false
; burada "ali" print etmedi
; tersi sıralamada yapsak print ederdi:
(and (print "ali") false)
;ali=> nil

; and ilk false değeri dönen formu çalıştırır, ondan sonraki formları çalıştırmaz
; #trm: s-expressionlar yani (..) bunlar birer form veya "..." bu da bir form
; biçim demek.

; aşağıdaki iki argüman da truthy olduğu için, ikisinin de çalışması lazım
(and
  (do (println "1") "ali")
  (do (println "2") "ayşe"))
;1
;2
;=> "ayşe"

; şimdi ilk argüman false döndüğü için, ikinci argümandaki ifadeyi hiç çalıştırmayacak
(and
  (do (println "1") false)
  (do (println "2") "ayşe"))
;1
;=> false

; q: peki mantık olarak ilk false'dan sonraki formları and hiç çalıştırmaz acaba?
; and'in amacı ne?
; kendisine verilen argümanların hepsi true ise true döner
; en az bir tanesi bile false ise false döner
; Dolayısıyla ilk false argümanı tespit ettiği anda, ondan sonraki argümanların değerine bakmasına gerek yoktur
; Çünkü döneceği değer değişmeyecektir artık

(and false false)
;=> false
(and false true)
;=> false

; Bu bize şöyle bir trick uygulama imkanı sağlıyor:
; Eğer and'e gönderdiğimiz argümanlardan bir kısmı yoğun işlem gerektiriyorsa, en sona koyarız
; Böylece boşu boşuna hesaplama yaptırmamış oluruz.

; and ve or birer makrodur:
(macroexpand '(and false true))
;(let*
;  [and__5514__auto__ false]
;  (if
;    and__5514__auto__
;    (clojure.core/and true)
;    and__5514__auto__))
