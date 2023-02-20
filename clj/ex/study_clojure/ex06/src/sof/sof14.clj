(ns sof.sof14)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; [Clojure apply vs map - Stack Overflow](https://stackoverflow.com/questions/2311528/clojure-apply-vs-map)

; e01:
;Let's imagine an function F and a vector. So,

(def F identity)

(apply F [1 2 3 4 5])

;translates to

(F 1 2 3 4 5)

;which means that F has to be at best case variadic.

;While

(map F [1 2 3 4 5])

;translates to

[(F 1) (F 2) (F 3) (F 4) (F 5)]

; #trm: apply = splatting = unpacking = explode a vector in a function call
; There is name for what apply does in some other dynamic programming languages, it's called splatting or unpacking

; e02: map is lazy by default. make it eager:

; #trm: lazy = hemen hazır olmayan, çağrıldıkça hazırlanan verilere lazy denir
; eager = mevcutta hazır olan
; ne zaman lazy veri yapılarını veya fonksiyonları kullanırız?
; eğer yapacağımız veri işlemesi çok uzun sürme ihtimali varsa
; veya gelen verinin sonsuz veya çok uzun ihtimali varsa
; veya gelen verinin aslında hepsini işlememiz gerekmiyorsa

;Try wrapping the map expression in a dorun:
; dorun: kendi içindeki tüm fonksiyon çağrılarını eager hale getiriyor #nclk/orta-öncelik
; doseq: eğer çalıştırdığın komutlar side-effect içeriyorsa, doseq içinde çağırmalısın #nclk/orta-öncelik

(def foundApps
  {:some-key :some-val})
(dorun (map println foundApps))

;Also, since you're doing it just for the side effects, it might be cleaner to use doseq instead:

(doseq [fa foundApps]
  (println fa))


