(ns sof.sof36)

; rfr: video/20230223-mert-clj-egzersiz-52.mp4

; [clojure - Update the values of multiple keys - Stack Overflow](https://stackoverflow.com/questions/9638271/update-the-values-of-multiple-keys)

(def ms [{:a 2 :b 3} {:a 2 :b 5}])
(map #(update-in % [:a] inc) ms)
;({:a 3, :b 3} {:a 3, :b 5})

(comment
  (def f #(update-in % [:a] inc))

  ; 1. tur
  (def m1 (ms 0))
  (identity m1)
  ;=> {:a 2, :b 3}
  (f m1)
  ;=> {:a 3, :b 3}

  ; 2. tur:
  (f (ms 1))
  ;=> {:a 3, :b 5}
  ,)

;Rather than mapping update-in for each key, I'd ideally like some function that operates like this:

;(map #(update-vals % [:a :b] inc) m)
;({:a 3, :b 4} {:a 3, :b 6})

; a01: reduce update-in
;Whenever you need to iteratively apply a fn to some data, reduce is your friend:

(defn update-vals [m ks f]
  (reduce #(update-in % [%2] f) m ks))

(map #(update-vals % [:a :b] inc) ms)
;=> ({:a 3, :b 4} {:a 3, :b 6})

(comment
  (def m1 (ms 0))
  (def m2 (ms 1))
  (def fm #(update-vals % [:a :b] inc))

  ; map'in turları:
  ; map'in 1. turu:
  ; m1 üzerinde işlem yapacağız
  (fm m1)
  ;=> {:a 3, :b 4}

  (def m m1)
  (def ks [:a :b])
  (def f inc)
  (reduce #(update-in % [%2] f) m ks)
  ;=> {:a 3, :b 4}

  ; ks iki öğeden oluştuğundan, reduce içinde bunlarla sırayla iki tur atıyoruz
  (def fr #(update-in % [%2] f))

  ; reduce'un turları:
  ; map'in 1. turu içindeki reduce'un 1. turu:
  (fr m :a)
  ;=> {:a 3, :b 3}
  (fr {:a 3, :b 3} :b)
  ;=> {:a 3, :b 4}
  ,)

; Not: [:a :b] -> ks -> [%2]. Neden arg [] ile paketlenmiş?
; Çünkü reduce ks, döngü sırasında önce ks'ın paketini açar.

; a02: reduce apply update-in
(defn update-vals2 [m ks & args]
  (reduce #(apply update-in % [%2] args) m ks))
(update-vals2 m1 [:a :b] inc)

; reduce fonksiyonun kendimiz yazacak olsaydık, iki öğeli bir coll için şunu yapardık:
(defn reduce2 [reducer init coll]
  (reducer
    (reducer init (first coll))
    (first (rest coll))))
(def es [1 2])
(def init 10)
(def reducer +)
(reduce2 reducer init es)
;=> 13

(comment
  ; 1. tur
  (reducer 10 (first es))
  ;=> 11
  ; 2. tur
  (reducer 11 (first (rest es)))
  ;=> 13
  ,)

; reduce'un gerçek implementasyonu yukarıdaki gibi 2 seviyeli değil, n seviyeli olmalı
; recursive olmalı dolayısıyla