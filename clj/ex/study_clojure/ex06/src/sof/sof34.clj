(ns sof.sof34)

; Barış'la Clojure Veri Analizi Çalışmaları
; Tarih: 20230223
; rfr: video/20230223-mert-clj-egzersiz-52.mp4

; [sequence - Idiomatic way to iterate through all pairs of a collection in Clojure - Stack Overflow](https://stackoverflow.com/questions/4053845/idiomatic-way-to-iterate-through-all-pairs-of-a-collection-in-clojure)

; Elimizde bir koleksiyon var, çeşitli öğelerden oluşan
; Bu öğelerin tüm ikili kombinasyonlarını oluşturmak istiyoruz

;(all-pairs seq)
;
;(all-pairs '(a b c d)) => ([a b] [a c] [a d] [b c] [b d] [c d])
(def input '(a b c d))

; a01
(require '[clojure.math.combinatorics :as combo])
(vec (map vec (combo/combinations '(a b c d) 2)))
;=> [[a b] [a c] [a d] [b c] [b d] [c d]]

(vec (map vec (combo/combinations '(a b c d) 3)))
;=> [[a b c] [a b d] [a c d] [b c d]]

(combo/combinations '(a b c) 2)
;=> ((a b) (a c) (b c))
(map vec (combo/combinations '(a b c) 2))
;=> ([a b] [a c] [b c])

; a02: when lazy-cat for
(defn all-pairs [coll]
  (when-let [s (next coll)]
    (lazy-cat
      (for [e s] [(first coll) e])
      (all-pairs s))))
(all-pairs input)
;=> ([a b] [a c] [a d] [b c] [b d] [c d])

(comment
  (def coll input)
  (def s (next coll))
  (identity coll)
  ;=> (a b c d)
  (identity s)
  ;=> (b c d)
  (for [e s] [(first coll) e])
  ;=> ([a b] [a c] [a d])

  ; yukarıda neden lazy-cat kullanmış olabiliriz?
  ; çünkü recursive bir çağrı var ya kendi kendisine, bu tip durumlarda lazy kullanmak iyi bir şeydir

  ; recursive bir çağrı yapıyorsak, şuna her zaman dikkat ederiz:
  ; her çağrıda gönderdiğimiz argümanı küçültmemiz gerekir.
  ; aksi taktirde sonsuza kadar sürecek bir durum ortaya çıkabilir

  ; end
  ,)

; #stnd: genellikle `e` ismini "element" anlamında yani bir coll'ın bir öğesi anlamında kullanıyoruz
; s -> seq (list)
; e -> elem
; es -> elements
; m -> map
; f -> function
; `l` ismini genellikle kullanmayız; çünkü 1 ile `l` ve `I` birbirine çok benzer
; i -> index anlamında. for looplarda indeks olarak kullanırız
; k v -> [key value]
; coll -> collection

; a02b:
(defn all-pairs2 [coll]
  (let [x (first coll)
        xs (next coll)]
    (when xs
      (lazy-cat
        (map (fn [e] [x e]) xs)
        (all-pairs2 xs)))))
(all-pairs2 input)
;=> ([a b] [a c] [a d] [b c] [b d] [c d])

; all-pairs içindeki for yerine burada map kullandık

; a03:
(defn all-pairs3 [coll]
  (loop [[x & xs] coll
         result []]
    (if (nil? xs)
      result
      (recur xs (concat result (map #(vector x %) xs))))))

(comment
  ; [x & xs] coll
  ; burada destructuring yapılmış
  ; coll'un ilk öğesi -> x
  ; coll'un rest öğeleri -> xs
  (def x (first coll))
  (def xs (rest coll))
  (identity xs)
  ;=> (b c d)

  ; kombinasyon burada yapılıyor:
  (map #(vector x %) xs)
  ;=> ([a b] [a c] [a d])

  ; not:
  ; burada birikimli argüman: (concat ...)
  ; her turda büyüyen bir veri
  ; bitme koşulunun argümanı: xs
  ; her turda küçülen bir veri
  ,)

; a04

(defn all-pairs4 [s]
  (filter
    #(< (first %) (second %))
    (for [i s j s] [i j])))
(all-pairs4 [10 20 30])
;=> ([10 20] [10 30] [20 30])

#_(all-pairs4 input)
;class clojure.lang.Symbol cannot be cast to class java.lang.Number (clojure.lang.Symbol is in unnamed module of loader 'app'); java.lang.Number is in module java.base of loader 'bootstrap')

