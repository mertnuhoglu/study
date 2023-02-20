(ns fn.keywordize-keys)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; (keywordize-keys m)
; Recursively transforms all map keys from strings to keywords.
;

; #trm: recursive = özyinelemeli
; (defn f [] .. (f ..))
; özyineleme: bir fonksiyonun kendi kendini çağırması
; fakat şu anlamda da kullanılıyor:
; bir hiyerarşik veri yapısına sahipsin diyelim
; onun bir seviyesinde bir işlem yaptın
; o yaptığın işlemi, o veri yapısının tüm alt seviyelerinde yapmaya da özyinelemeli diyoruz
; neden bu kelimeyi burada da kullanıyoruz?
; çünkü o veri yapısı potansiyel olarak sonsuz alt seviyeye sahip olur
; herhangi bir kısıtlama yapılmıyor

(require 'clojure.walk)
(clojure.walk/keywordize-keys {"a" 1 "b" 2})
;;=> {:a 1 :b 2}
