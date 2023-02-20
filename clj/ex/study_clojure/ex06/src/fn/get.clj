(ns fn.get)

; TODO: daha önce videoda bunları yapmıştık bulup ekleyelim.
; rfr: e02.clj
; rfr: video/20230216-mert-clj-egzersiz-39.mp4
; rfr: fn/get-in.clj
; rfr: fn/update.clj

; (get map key)
; (get map key not-found)
; Returns the value mapped to key, not-found or nil if key not present.
;

; #terim: key = attribute = property

(def m1
  {:k1 :v1
   :k2 :v2})
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

; şimdi ben Ms tablosundaki bir satırı (map objesini) almak istiyorum
(get Ms 0)
;=> {:k1 :v1, :k2 :v2}
(get Ms 1)
;=> {:k1 :v3, :k2 :v4}

; peki ben birinci satırdaki :k1 değerini nasıl alırım?
; a01: ardışık get çağrılarıyla (threading macro)
(->
  (get Ms 0)
  (get :k1))
;=> :v1
; önce Ms'in ilk elamanını alıyorum (0 ile), sonra dönen map objesinin :k1 propertysini çekiyorum

; a02: nested (içiçe) get çağrılarıyla
; aynı şeyi threading macro olmadan da yapabilirim
(get (get Ms 0) :k1)
;=> :v1

; a03: get yerine doğrudan keywordü bir fonksiyon olarak kullanabiliriz
; get fonksiyonunu hiç kullanmayabilirdik
(-> Ms
  (get 0)
  (:k1))
;=> :v1

; (:k1) ifadesinde :k1 aslında hem bir keyword, aynı zamanda bir fonksiyon işlevi görüyor
; şuna denk aslında:
; keywordün kendisini bir fonksiyon olarak kullanmış olduk:
(:k1 m1)
;=> :v1
(:k1 {:k1 :v1, :k2 :v2})
;=> :v1

; a04: get yerine doğrudan map objesini bir fonksiyon olarak kullanabiliriz
; map'lerin her kw'ü kendisi de aslında kendi içindeki ilgili kw'ün değerine erişim sağlayan bir getter fonksiyonudur
; bunun tam tersi de geçerli
; yani map de bir fonksiyon olarak kullanılabilir
(m1 :k1)
;=> :v1

; ama m1 (mapi fonksiyon olarak) kullanırsak, o zaman threading macro'yu kullanarak yukarıdaki gibi Ms'ten ilgili değeri çekemiyoruz
(-> Ms
  (get 0) ; => m1 objesini döndürür
  (m1))   ; bu şuna benzer: (m1 m1) dolayısıyla nil döner.
;=> nil

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

;; ---
;; 'get' is not the only option
(def my-map {:a 1 :b 2 :c 3})

;; maps act like functions taking keys
(my-map :a)
;;=> 1

;; even keys (if they are keywords) act like functions
(:b my-map)
;;=> 2

; q: neden bir map'i key ile çağırıp ilgili propertynin değerini alabiliyoruz, ama değer ile çağırıp key'i alamıyoruz?
; cevap: map'in keyleri tekildir, ama değerleri tekil değildir.
; genel olarak bir fonksiyonun tanımı şudur:
; bir fonksiyon kendisine verilen argümanlar için her zaman aynı sonucu dönmek zorundadır
; bunun için de tanım kümesindeki her bir öğe, sonuç kümesinde tek bir öğeye bağlanmış olmalı.
; {:a 1 :b 1}
; :a -> 1
; 1 -> :a veya :b

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; Map'ler nasıl ki bir get fonksiyonu olarak kullanılabiliyor, aynı şekilde set'ler de kullanılabilir
(def t #{:a :b :c})
; aşağıdaki 3 fonksiyon çağrısı birbirine denktir:
(get t :a)
;=> :a
(t :a)
;=> :a
(:a t)
;=> :a

; q: `(get t :a)` :a dönüyor. Zaten :a'yı arg olarak vermiştik. Bu fonksiyon nerede işimize yarayabilir?
; olmayana ergi yapalım
(t :d)
;=> nil
; bir öğenin ilgili kümede bulunup bulunmadığını anlamamızı sağlar
; Java'daki contains() fonksiyonuna denk gelir
