(ns fn.syntax.recursion)

; rfr: video/20230223-mert-clj-egzersiz-52.mp4

; [Clojure - Special Forms](https://clojure.org/reference/special_forms#recur)

; #trm: recursion = özyineleme
; yineleme ne demek biliyoruz
; özyineleme: kendi kendini tekrarlama anlamına gelir

; özyineleme, clojure'da iki şekilde yapılabilir:
; 1. bir fonksiyon kendi kendini çağırabilir
; 2. loop bloku kendi kendini çağırabilir
; rfr: fn/loop.clj

(loop [x 10]
  (when (> x 1)
    (println x)
    (recur (- x 2))))
;10
;8
;6
;4
;2

; ≣
; bunun aynısını bir fonksiyon tanımlayarak da yapabiliriz
(defn f [x]
  (when (> x 1)
    (println x)
    (recur (- x 2))))
(f 10)
;10
;8
;6
;4
;2

; recur ifadesini görünce, clojure otomatik olarak
; ya loop blokunun ilk formuna geri dönüyor
; ya da içinde bulunduğu fonksiyonun ilk formuna geri dönüyor

; recur yerine doğrudan fonksiyonun ismini de kullanabilirdik
(defn f2 [x]
  (when (> x 1)
    (println x)
    (f2 (- x 2))))
(f2 10)
;10
;8
;6
;4
;2

; imperatif dillerde recursion çok kötü bir kodlama yöntemidir
; fp dillerinde recursion olmadan while loop yapamazsın
; recursion bir sürü problemi çözer

; Dikkat: recur her zaman bir fonksiyonun en son formu olmalı
; tail position denir buna.
; Yani fonksiyonun kuyruğunda yer almalı

; kuyrukta yer almazsa ne olur?
#_(defn f3 [x]
    (when (> x 1)
      (println x)
      (recur (- x 2))
      (println "tail")))
;Can only recur from tail position

; q: neden kuyrukta yer alması zorunlu?
; stack optimizasyonundan dolayı
; yani her fonksiyon çağrıldığında hafızada tutulan sınırlı bir alan var. buna stack deniyor
; burada o fonksiyonun içinde bulunduğu ortama ait bilgiler tutuluyor
; özyinelemeli bir çağrıda potansiyel olarak sınırsız çağrı imkanı olduğundan,
; bir süre stack alanı tükenir
; eğer stack optimizasyonu yapılmazsa
; stack optimizasyonuyla: tüm önceki stacktrace'ler silinir

(defn f4 [x]
  (when (> x 1)
    (println x)
    (f4 (- x 2))
    (println "tail")))
(f4 10)
;10
;8
;6
;4
;2
;tail
;tail
;tail
;tail
;tail
; eğer recur ile recursion yapmazsan, clojure seni kuyruk pozisyonunda bulunmaya zorlamıyor
; dolayısıyla potansiyel bir yazılım hatası yapabilirsin
; bu yüzden recur kullanmak gerekiyor.
