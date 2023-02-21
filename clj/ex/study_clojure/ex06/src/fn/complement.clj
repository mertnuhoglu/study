(ns fn.complement)

; rfr: video/20230218-mert-clj-egzersiz-43.mp4
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
; complement arg olarak fonksiyon alır ve sonuç olarak fonksiyon döner
; not fonksiyonu ise bir değer alır ve değer döner

; rfr: video/20230220-mert-clj-egzersiz-45.mp4

; q: complement neden fonksiyon dönüyor?
; çünkü üst seviye (higher order) fonksiyonlara argüman olarak gönderebilmemiz için, fonksiyon dönmesi gerekirdi
; (map f)

; q: complement bir fonksiyon değil de değer dönseydi, nasıl bir yapıda veya kullanım biçiminde olurdu?
; (complement f) imzası böyle
; burada herhangi bir değer argümanı yok
; fakat biliyoruz ki, bu fonksiyon verilen değer argümanına bağlı olarak, bazen true, bazen falsy bir değer dönüyor
; dolayısıyla bu fonksiyonun çağrılmasının sonucu aslında değer argümanına bağlı
; fakat imzasında değer argümanı almadığından, çağrıldığı vakit her zaman aynı sonucu dönmek zorunda
; (complement even?)
; bu ifade her zaman aynı sonucu döner.
; ((complement even?) 3) -> true
; ((complement even?) 4) -> false
(complement even?)
;=> #object[clojure.core$complement$fn__5669 0x5a110253 "clojure.core$complement$fn__5669@5a110253"]
; fn__5669 bir anonim fonksiyon anlamına gelir
; herhangi true/false türü bir değer değil
((complement even?) 3)
;=> true
((complement even?) 4)
;=> false

