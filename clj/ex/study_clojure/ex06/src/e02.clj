(ns e02)

; Tarih: 20230117

; isimlendirme standartları:
; m ~ map
; k ~ key
; v ~ value
(def m1
  {:k1 :v1
   :k2 :v2})

; biz edn olarak objeleri saklıyoruz
; edn objeleri, bizim map veya vector objelerimize denk gelir

; bizim klasik sql/tabular veri formatımızda bu map veri yapılarını nasıl eşleştiririz?
; yukarıdaki map objesini şu şekilde tabloda tutabiliriz:
; [m1]
; k1 | k2
; -------
; v1 | v2

; dolayısıyla her bir map objesi bir tablodaki bir satıra denk gelir.
; peki birden çok satır varsa nasıl modelleyeceğiz map objeleriyle?
; iki tane map objemiz olması lazım
; bu iki map objesini de bir vektör içine koyarız

; çünkü aslında bir tablo aslında çok sayıda satırdan (record) oluşan bir listedir.
; birinci satır, ikinci satır gibi
; dolayısıyla tablonun satırları aslında bir vektörün öğeleri gibidir
; fakat tablonun her satırında n tane atribut bulunabilir
; bu her satırdaki n tane atributu da bir key-value pair listesi olarak düşünebiliriz
; bu da bir map objesine denk gelir
; çünkü her map objesi aslında bir kv ikililerinden (2-tuple) bir listedir

; info kutusu:
; programlama dilleri iki farklı temel paradigmadan geliyor
; bir tanesi, daha matematiksel tanımlanmış diller
; ikincisi, daha mekanik (execution) tarafından çıkan diller
; birinci tarz: church languages deniyor. Lisp, Church'ün kendi notasyonu (lambda algebra), ML, Haskell...
; ikinci tarz: von neumann veya turing denilen ilk bilgisayar mucitlerinin uyguladığı diller
; imperatif diller ikinci tarz diller oluyor
; makine dili, assembly, algol, pascal, java, scala, ruby, js

; aslında şöyle bir tabloyu oluşturmak istiyoruz:
; [Ms]
; k1 | k2
; -------
; v1 | v2
; v3 | v4

(def m2
  {:k1 :v3
   :k2 :v4})
(def Ms
  [m1 m2])

; isimlendirme standardı:
; x: herhangi bir obje olsun
; xs: o objenin bir listesi olur
(print Ms)
;; => [{:k1 :v1, :k2 :v2}
;;     {:k1 :v3, :k2 :v4}]

; şimdi ben Ms tablosundaki bir satırı almak istiyorum
(get Ms 0)
;=> {:k1 :v1, :k2 :v2}
(get Ms 1)
;=> {:k1 :v3, :k2 :v4}

; peki ben birinci satırdaki :k1 değerini nasıl alırım?
(->
  (get Ms 0)
  (get :k1))
;=> :v1

; aynı şeyi threading macro olmadan da yapabilirim
(get (get Ms 0) :k1)
;=> :v1

; get fonksiyonunu hiç kullanmayabilirdik
(->
  (get Ms 0)
  (:k1))
;=> :v1

; burada keywordün kendisini bir fonksiyon olarak kullanmış olduk:
(:k1 m1)
;=> :v1
(:k1 {:k1 :v1, :k2 :v2})
;=> :v1

; map'lerin her kw'ü kendisi de aslında kendi içindeki ilgili kw'ün değerine erişim sağlayan bir getter fonksiyonudur
; bunun tam tersi de geçerli
; yani map de bir fonksiyon olarak kullanılabilir
(m1 :k1)
;=> :v1

; bu özellikler sayesinde threading macro yapısını daha sade ve okunabilir hale getirebilirim:
(-> Ms
  (get 0)
  (:k1))
;=> :v1
; şimdi bu ifadeyi şöyle okumamız lazım:
; ilk olarak ben bir data pipeline tanımlıyorum
; bu data pipeline Ms objesiyle başlıyor ki Ms de bir tablo
; 1. işlem: bu tablonun ilk satırını okumak
; bu ilk satırın sonucunu bir sonraki işleme girdi olarak veriyorum
; 2. işlem: bu gelen girdinin (ki bu da bir map objesi) :k1 atributunu okuyorum
; bunun da çıktısı tüm pipelineın çıktısı olur

; bu şekilde son derece lineer ve sade bir ifade haline geldi

; bir de tüm bu işlemleri tek bir fonksiyonla da yapabilirdik
; get-in fonksiyonuyla
(get-in Ms [0 :k1])
;=> :v1
(get-in Ms [1 :k1])
;=> :v3

; şu ana kadarki tüm sorgulamalarımız, indeks üzerinden yapılıyordu (ya vektör indeksi, ya da map indeksi (keyword))
; peki değer üzerinden sorgulama bulma nasıl yapılıyor?
; filter fonksiyonu kullanıyoruz

(filter
  (fn [v] (= 3 v))
  [3 5 7])
;=> (3)

(filter
  (fn [p] (:k1 p))
  {:k1 1 :k2 2})
;=> ()

(filter
  #(:k1 %)
  {:k1 1 :k2 2})
;=> ()

(filter
  #(= (:k1 %) 1)
  {:k1 1 :k2 2})
;=> ()

(filter #(> (second %) 100)
  {:a 1
   :b 2
   :c 101
   :d 102
   :e -1})
;;=> ([:c 101] [:d 102])

(filter #(= (second %) 1)
  {:a 1
   :b 2})
;=> ([:a 1])

(second [:a :b :c])
;=> :b

(filter
  #(= (second %) 1)
  {:k1 1 :k2 2})
;=> ([:k1 1])

(filter #(= (second %) 1)
  {:a 1
   :b 2
   :c 1})
;=> ([:a 1] [:c 1])

(:k1 [:k1 1])
;=> nil
(:k1 {:k1 1})
;=> 1

; filter fonksiyonu map'in her bir kv ikilisini bir vektör ikilisi olarak içindeki pred fonksiyonuna gönderiyor
; dikkat: bir vektör ikilisi olduğundan, bir mapteki gibi key fonksiyonu çalışmıyor

; destructuring yaparak bunu çözebiliriz:
(filter
  (fn [[k v]] (= v 1))
  {:k1 1 :k2 2})
;=> ([:k1 1])

; Ms tablosunda :k1 atributu 1 olan satırları bulalım
; yukarıdaki örnekte tek bir map objesi üzerinde yaptık
; şimdi çok sayıda map objesi üzerinde bir sorgulama yapmalıyız
(print Ms)
;[{:k1 :v1, :k2 :v2} {:k1 :v3, :k2 :v4}]
(filter
  (fn [m] (= (:k1 m) :v1))
  Ms)
;=> ({:k1 :v1, :k2 :v2})
(->> Ms
  (filter
    (fn [m] (= (:k1 m) :v1))))
;=> ({:k1 :v1, :k2 :v2})

; get-in ile bunu nasıl yapardık?
(filter
  (fn [m] (= (get-in m [:k1]) :v1))
  Ms)
;=> ({:k1 :v1, :k2 :v2})
; bunun okunması ve yazması daha zor, gerek yok
