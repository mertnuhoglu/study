(ns fn.let)

; rfr: video/20230215-mert-clj-egzersiz-37.mp4

; (let [bindings*] exprs*) #nclk/çok-önemli
; binding => binding-form init-expr
;  Evaluates the exprs in a lexical context in which the symbols in
; the binding-forms are bound to their respective init-exprs or parts
; therein.

(def digits [1 2 3])

(let [d digits]
  d)
;=> [1 2 3]

; for ile benzerlik var
(for [d digits] d)
;=> (1 2 3)

; let'in de for'un da ilk argümanı [] içinde ifade edilen bağlama (binding) ifadeleri
; yani lokal scope'da çağıracağımız isimleri belirtiyoruz
; digits ise bağlamak istediğiniz veri. dışarıdan geliyor.
; lokal scope'da d'yi kullanabiliriz.

; for ile let binding'leri arasındaki fark:
; imperatif notasyonla yazalım:
; let kullanımı: (tüm objeyi d'ye bağlıyorsun)
; d <- digits
; for kullanımı: (sırayla tek tek öğelerini bağlıyorsun)
; d <- digits[0]
; d <- digits[1]
; d <- digits[2]