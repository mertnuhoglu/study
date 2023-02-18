(ns fn.complement)

; rfr: video/20230217-mert-clj-egzersiz-43.mp4
; rfr: fn/not.clj

; (complement f)
; Takes a fn f and returns a fn that takes the same arguments as f,
; has the same effects, if any, and returns the opposite truth value.
;

; complement: tamamlayıcı anlamına gelir
; bir şeyin tamamlayıcısı, onun tam tersidir
; yani verilen fonksiyonun aynısını çalıştırıyor, ama sonucunu mantıksal değer olarak tersine çeviriyor
; yani bir nevi `not` fonksiyonu gibi

(map even? '(1 2 3 4))
;=> (false true false true)

(map (complement even?) '(1 2 3 4))
;=> (true false true false)

; complement illa true/false dönen fonksiyonlarla değil,
; her türlü fonksiyonla çalışabilir
(defn add10 [n] (+ n 10))
(map (complement add10) '(1 2 3 4))
;=> (false false false false)

; not vs complement
; complement arg olarak fonksiyon alır ve fonksiyon döner
; not ise bir değer alır ve değer döner
