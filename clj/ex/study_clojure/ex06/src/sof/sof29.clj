(ns sof.sof29)

; rfr: video/20230223-mert-clj-egzersiz-50.mp4

; [clojure - Destructure a map in another map? - Stack Overflow](https://stackoverflow.com/questions/4307835/destructure-a-map-in-another-map)

; I have:
(def m
  {:file "filename", :resolution {:width 1280, :height 1024}})

(defn to-directory-name [{{width :width height :height} :resolution}]
  (str width "x" height))

(to-directory-name m)
;=> "1280x1024"

(comment
  (defn f1 [{r :resolution}]
    (identity r))
  (f1 m)
  ;=> {:width 1280, :height 1024}

  (defn f2 [{{w :width h :height} :resolution}]
    (str w " x " h))
  (f2 m)
  ;=> "1280 x 1024"

  ,)

; burada iki tane zorlayıcı durum var gibi:
; 1. içiçe 2 seviyede destructuring yapılmış
; 2. keyword isimleriyle sembol isimleri birbirinin tıpatıp aynısı
; #stnd: (tavsiye) mümkün olduğunca sembol isimlerini kısa ve eğer lokal sembollerse mümkünse bir iki harfli yapın
; faydaları:
; 1. lokal isimler zaten aslında bağlamlarıyla anlaşılmalı. kendi kendilerini tarif etmelerine gerek yoktur.
; 2. keywordlerle sembollerin karışması da engellenir
; 3. isim üretmek genelde zordur. o beladan kurtarmış olur bizi.
; zaten lokal scope'da kullandığımız için, çok anlamlı isim olmalarına da gerek yok.

; neden java dünyasında isimler çok uzundur?
; Robert Martin'in Clean Code kitabı, Martin Fowler'in de Refactoring kitabı da benzer şeyi önerir
; Kodun kendi kendini açıklamasını tavsiye ederler.
; Komentleri (açıklamaları) bir code smell (kötü alışkanlık) olarak tarif ederler.
; Derler ki, kod ne yaptığını kendi başına açıklamalaı, komente ihtiyaç duymamalı.
; Koment kodun ne yaptığını değil, kodun maksadını (gerekçesini) açıklamalı.
; Bu yüzden varlıklara uzun isim verirler, kendi kendilerini açıklasın diye.
; Java dünyasında varlıklar hep genellikle global scope'da tanımlandığı için bu mantıklı.
; Fakat clojure dünyasında tam tersine global scope'da çok fazla ortak kullanım yapılmaz.
; Lokal scope'daki şeylerin de, uzun isme ihtiyacı yoktur, çünkü bağlamını okumak çok zor değildir.
