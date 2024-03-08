(ns syntax.transducer)

; Video: 20230420-mert-clj-57-d08-refactoring-debug.mp4

(def xform
  (map #(+ 2 %)))

; normalde map fonksiyonunu şu şekilde kullanırdık:
(map #(+ 2 %) [0 1 2])
;=> (2 3 4)

; peki ben map fonksiyonuna sadece anonim fonksiyonu verir, girdi verisi vermezsem ne olur?
(map #(+ 2 %))
;=> #object[clojure.core$map$fn__5862 0x43aa6948 "clojure.core$map$fn__5862@43aa6948"]

; bu dönen obje, aslında bir başka anonim fonksiyondur
; fonksiyon bir veri olmadığından, repl onu ancak onun ismiyle gösterebilir
; anonim fonksiyonların da ismi yoktur. o yüzden onlara böyle jenerik isim verir clojure:
; fn__xxxx şeklinde

; bu dönen anonim fonksiyona bir isim veriyorum burada:
(def xform
  (map #(+ 2 %)))
; xform ismini verdim

; xform'u artık bir fonksiyon gibi kullanabilirim

(defn xform2 [data]
  (map #(+ 2 %) data))
(xform2 [0 1 2])
;=> (2 3 4)

(comment
  (xform [0 1 2])
  ;=> #object[clojure.core$map$fn__5862$fn__5863 0x13e1b45b "clojure.core$map$fn__5862$fn__5863@13e1b45b"]
  ; şuna denktir
  ((map #(+ 2 %)) [0 1 2])
  ;=> #object[clojure.core$map$fn__5862$fn__5863 0x13e1b45b "clojure.core$map$fn__5862$fn__5863@13e1b45b"]

  ((fn [data] (map #(+ 2 %) data)) [0 1 2])
  ;=> (2 3 4)

  ;end
  ,)

; Rich Hickey'in Transducer videosuna bakabilirsiniz ek bilgi için

; Transducer'ın sezgisel tanımı:
; Bizim map, filter gibi veri işleme fonksiyonlarımız var
; Bunlara eğer herhangi bir girdi data vermezsek sadece inner fonksiyon arg verirsek, bir transducer alırız
; Bu transducer'ları da yine başka higher-order fonksiyonlara arg olarak paslayabiliriz
