(ns fn.partial)

; rfr: video/20230221-mert-clj-egzersiz-47.mp4

; (partial f)          #nclk/orta-seviye
; (partial f arg1)
; (partial f arg1 arg2)
; (partial f arg1 arg2 arg3)
; (partial f arg1 arg2 arg3 & more)
; Takes a function f and fewer than the normal arguments to f, and
; returns a fn that takes a variable number of additional args. When
; called, the returned function calls f with args + additional args.
;

; FP'nin hem tarihini hem de bu alandaki önemli başlıkların arasındaki ilişkileri öğrenmek için:
; ["Propositions as Types" by Philip Wadler - YouTube](https://www.youtube.com/watch?v=IOiZatlZtGU)

; partial: adına baktığımızda kısmi anlamına gelir
; şöyle düşünebiliriz bunu:
; bir f fonksiyonunun argümanlarının bir kısmını veriyorum
; ve bana bir türetilmiş fonksiyon döner
; f(2, 3)
; g <- f(2) ; konfigüre ettim f fonksiyonunu
; g kısmi fonksiyonunu veriyor
; g(3) == f(2, 3)

(def sum +)
(sum 5 3)
;=> 8
(defn add5 [b]
  (sum 5 b))
(add5 3)
;=> 8

; bu örnekteki add5 fonksiyonu argümanları kısmi olarak tanımlanmış sum fonksiyonunun bir örneğidir
; sum fonksiyonu 2 argüman alıyordu
; add5 fonksiyonu tek argüman alıyor
; add5 fonksiyonu aslında sum fonksiyonunun 5 ile konfigüre edilmiş halidir

; bunu bu şekilde yapmak yerine partial kullanarak da yapabilirdik:

(def add5b
  (partial sum 5))
(add5b 3)
;=> 8

; q: add5 tanımlamada defn kullandık, add5b tanımlamada def kullandık. neden?
; sebebi şu: partial fonksiyonu bir üst seviye fonksiyon. bu fonksiyon arg olarak fonksiyon alır,
; ve sonuç olarak da fonksiyon döner.
; dolayısıyla partial fonksiyon döndüğü için, direk ona bir isim vermem yeterli.
; o yüzden def ile o fonksiyona isim vermemiz, yeterli fonksiyonu tanımlamamız için.



