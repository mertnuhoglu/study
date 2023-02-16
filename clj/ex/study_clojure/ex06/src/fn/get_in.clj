(ns fn.get-in)

; TODO: daha önce videoda bunları yapmıştık bulup ekleyelim.
; rfr: e02.clj
; rfr: video/20230216-mert-clj-egzersiz-39.mp4
; rfr: fn/assoc-in.clj
; rfr: fn/get.clj

; (get-in m ks)
; (get-in m ks not-found)
; Returns the value in a nested associative structure,
; where ks is a sequence of keys. Returns nil if the key
; is not present, or the not-found value if supplied.

; rfr: fn/get.clj
(def m1
  {:k1 :v1
   :k2 :v2})
(def m2
  {:k1 :v3
   :k2 :v4})
(def Ms
  [m1 m2])

; Ms'in ilk öğesindeki mapi al ve o mapin içindeki :k1 propertysinin değerini al:
(-> Ms
  (get 0)
  (:k1))

(macroexpand '(-> Ms
                (get 0)
                (:k1)))
;=> (:k1 (get Ms 0))

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

