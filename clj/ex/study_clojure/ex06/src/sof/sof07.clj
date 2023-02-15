(ns sof.sof07)

; Barış'la Clojure Veri Analizi Çalışmaları
; Tarih: 20230215
; rfr: video/20230215-mert-clj-egzersiz-37.mp4
; rfr: video/20230215-mert-clj-egzersiz-38.mp4

; [functional programming - What's the one-level sequence flattening function in Clojure? - Stack Overflow](https://stackoverflow.com/questions/10723451/whats-the-one-level-sequence-flattening-function-in-clojure)

(def bs '([[1 2]] [[2 3]] [[4 5]]))

; a01: apply concat
(apply concat bs) ; ([1 2] [2 3] [4 5])

; a02: for (list) comprehension
(for [subcoll bs, item subcoll] item) ; ([1 2] [2 3] [4 5])

; rfr: video/20230215-mert-clj-egzersiz-38.mp4

; a03:
(mapcat identity bs) ; ([1 2] [2 3] [4 5])

(mapcat seq bs) ; ([1 2] [2 3] [4 5])

; FP dillerinde, fonksiyonlar birinci sınıf vatandaşları. first class citizens.
; aynı objeler (veriler) gibi başka fonksiyonlara arg olarak paslanabilir ve işlenebilir.

; a0n: özel durum: eğer ilk öğe primitifse apply concat hata verir
#_(apply concat [1 [2 3] [4 [5]]])

(defn flatten-one-level [coll]
  (mapcat  #(if (sequential? %) % [%]) coll))

(flatten-one-level [1 [2 3] [4 [5]]]) ; (1 2 3 4 [5])

; q: bu mapcat ifadesini threading macro ile yazsak olur mu?

(defn flatten-one-level-2 [coll]
  (->> coll
    (mapcat
      #(if
         (sequential? %)
         %
         [%]))))
(flatten-one-level-2 [1 [2 3] [4 [5]]]) ; (1 2 3 4 [5])

; eğer yukarıdaki gibi [%] ile paketlemeseydik primitif değeri, o zaman hata alırız.
#_(mapcat identity [1 [2 3] [4 [5]]])
;Don't know how to create ISeq from: java.lang.Long
; 1 primitif değerini paketleyince aslında şunu çalıştırmış oluyoruz:
(mapcat identity [[1] [2 3] [4 [5]]])
; mapcat kendisine verilen öğeleri önce paketinden çıkartıyor, sonra f fonksiyonunu (identity) uyguluyor
; fakat primitif bir değerin paketi olmadığı için, onu paketinden çıkartamıyor, o yüzden hata veriyor
