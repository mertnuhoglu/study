(ns fn.syntax.anonymous-functions)

; rfr: video/20230215-mert-clj-egzersiz-36.mp4

; https://clojure.org/guides/higher_order_functions#_function_literals

; İki tür anonim fonksiyon oluşturma biçimi var:
; fn kullanarak
; literal (sembollerle) kullanarak: #(..)

; e01: fn kullanarak bir anonim fonksiyonu tanımlamak ve çağırmak

; fn kullanarak
(fn [a b]
  (+ a b))
; bu fonksiyonu çağırabilmem için bu fonksiyona bir isim vermem gerekiyor.
; veya doğrudan tanımladığın anda çağıracağım

; isim verelim fonksiyona
(def add-1
  (fn [a b]
    (+ a b)))
(add-1 2 3)
;=> 5

; isim vermeden de bu anonim fonksiyonu çağırabilirdik
((fn [a b] (+ a b)) 2 3)
;=> 5

; e02: function literal kullanarak anonim fonksiyonu tanımlamak ve çağırmak:

; isim verelim fonksiyona
(def add-2
  #(+ %1 %2))
(add-2 2 3)
;=> 5

; isim vermeden de bu anonim fonksiyonu çağırabilirdik
(#(+ %1 %2) 2 3)
;=> 5

; şu ana kadar anonim fonksiyonunların özü
; bu anonim fonksiyonlar genellikle bu gibi durumlarda kullanılmıyor
; genelde anonim fonksiyonlar, üst seviye fonksiyonların (higher-order functions) bir argümanı olarak kullanılır
; üst seviye fonksiyon: argüman olarak bir fonksiyon olan fonksiyonlardır

; Üst seviye fonksiyonlar, FP'nın çıkış noktasıdır.
; Her şey buradan başlıyor.