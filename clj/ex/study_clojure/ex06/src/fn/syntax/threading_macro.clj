(ns fn.syntax.threading-macro)

; Tarih: 20230407
; Video: 20230407-mert-clj-egzersiz-55-e24-threading-macro-debug.mp4


(def m {:a {:b 3}})

(-> m
  :a
  :b)
;; => 3

(comment
  ; yukarıdaki ifade aslında düz yazılacak (makro kullanmadan) olsa şöyle yazılırdı:
  (:a m)
  ;=> {:b 3}

  (:b {:b 3})
  ;=> 3

  ; threading macro sırayla adım adım bir iş yapmanın kolay tarifidir
  ; eğer bunu normal şekilde macro kullanmadan yazsak içiçe çağrılar şeklinde yazmamız gerekirdi

  (:b (:a m))
  ;=> 3

  ; genel olarak içiçe yapılan çağrıları (function invocation) okumak zordur
  ; bunun yerine lineer bir şekilde sırayla okumak daha kolaydır
  ; o yüzden threading macro kullanırız

  ,)

; Threading macro'nun da iki türü var:
; 1. first threading macro: `->`
; 2. last threading macro: `->>`
; fark:
; ilki, ara sonuçları, bir sonraki fonksiyonun ilk argümanı olarak paslar
; ikincisi, ara sonuçları, son argüman olarak paslar

(comment
  (->> m
    :a
    :b)
  ; şuna denktir:
  (:b (:a m))
  ; bu örnekte iki macro birbirinin aynı çıktıyı verir
  ; çünkü keyword fonksiyonları tek bir argüman alır

  ; birden çok argüman alan fonksiyonlarda farklılık ortaya çıkar

  ; mesela get fonksiyonu gibi

  (-> m
    (get :a)
    (get :b))
  ;=> 3

  (->> m
    (get :a)
    (get :b))
  ;=> nil

  ; neden -> ile çalıştı ->> ile çalışmadı?
  ; ilki şuna denktir:
  (get m :a)
  ;=> {:b 3}
  (get {:b 3} :b)
  ;=> 3

  ; ikincisi şuna denktir:
  (get :a m)
  ;=> nil
  (get :b nil)
  ;=> nil


  ; end
  ,)

; Genel bilgi:
; Threading macro, programlamada data pipeline veya piping denilen standart patternlardan birisidir
; Bütün dillerde benzer şeyler vardır, ama isimleri farklıdır
; Unix'te bash kullanırken: pipe operator vardır
; Aynı işlemi yapar.