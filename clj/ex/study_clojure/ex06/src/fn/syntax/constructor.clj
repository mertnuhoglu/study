(ns fn.syntax.constructor)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; #trm: constructor: verilen verilerden bir obje inşa eden fonksiyonlara constructor diyoruz
; clojure'da constructor kavramı çok nadiren kullanılır

; fakat java'daki constructor'a benzer kullanım mesela `hash-map` fonksiyonu için düşünülebilir
; constructor da bir fonksiyondur

(hash-map :a 1 :b 2)
;=> {:b 2, :a 1}
; hash-map fonksiyonuna 4 tane veri argümanı verdim
; sonunda bana bir map objesi verdi
; buna constructor diyebiliriz bu yüzden

; ancak literal {} notasyonundaki map objesi hash-map fonksiyonu gibi çalışmaz
{}
;=> {}
; boş bir mape denk gelir
(hash-map)
;=> {}

; fakat sen {} bunu bir fonksiyon olarak kullanırsan, bu get fonksiyonuna denk gelir
({} :a)
;=> nil

(list 1 2 3)
;=> (1 2 3)
'(1 2 3)
;=> (1 2 3)
