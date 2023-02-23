(ns sof.sof27)

; Barış'la Clojure Veri Analizi Çalışmaları
; Tarih: 20230223
; rfr: video/20230222-mert-clj-egzersiz-49.mp4
; rfr: video/20230223-mert-clj-egzersiz-50.mp4

; [Idiomatic sequence slice in Clojure - Stack Overflow](https://stackoverflow.com/questions/12069855/idiomatic-sequence-slice-in-clojure)

;In Python, there is a convenient way of taking parts of a list called "slicing":

;a = [1,2,3,4,5,6,7,8,9,10] # ≡ a = range(1,10)
;a[0:3] # get first three elements
; yani 0. öğeden başla, 3. öğeye kadar topla
;a[:3] # get first 3 elements
;a[3:] # get all elements except the first 3
;a[:-3] # get all elements except the last 3
;a[-3:] # get last 3 elements
;a[3:7] # get 4 elements starting from 3rd (≡ from 3rd to 7th exclusive)
;a[3:-3] # get all elements except the first 3 and the last 3

;Playing with clojure.repl/doc in Clojure, I found equivalents for all of them but I'm not sure they are idiomatic.

; #trm: slicing = dilimleme
; bir vektörün tek bir öğesini değil, belirli bir indeks aralığındaki öğelerini döndürmek için kullanırız


(def a (take 10 (iterate inc 1)))
(identity a)
;=> (1 2 3 4 5 6 7 8 9 10)

(def a (range 10))
(identity a)
;=> (0 1 2 3 4 5 6 7 8 9)

(def a (map #(+ 1 %) (range 10)))
(identity a)
;=> (1 2 3 4 5 6 7 8 9 10)

(comment
  ; q: map ile yaptığımız işlemi apply ile de yapabilir miyiz?
  (apply + (range 3))
  ;=> 3
  ; ≣
  ; apply'ın açılmış hali şudur:
  (+ 0 1 2)
  ;=> 3
  ; şu form değil:
  (+ 0 (+ 1 (+ 2)))
  ;=> 3
  ; bu formu reduce üretir

  ; map'in açılmış hali nedir?
  (map + (range 3))
  ;=> (0 1 2)
  ; ≣
  (seq [(+ 0) (+ 1) (+ 2)])
  ;=> (0 1 2)

  ; apply ile map'in farkı?
  ; apply verdiğin coll'ı açıyor ve hepsini birden f fonksiyonuna uyguluyor
  ; map ise verdiğin coll'daki öğeleri dolaşıyor ve hepsine teker teker f uygulayıp sonuçları bir seq içinde biriktiriyor
  ;(map f es) -> ((f e1) (f e2) (f e3) ...)
  ;(apply f es) -> (f e1 e2 e3 ...)

  (map #(+ 1 %) (range 10))
  ;=> (1 2 3 4 5 6 7 8 9 10)
  ,)

(take 3 a)
;=> (1 2 3)
; a vektörünün ilk 3 öğesini alırız

; ancak take her zaman ilk öğeden başlar
; aradaki öğelerden bir dilim almak için ne yapacağız?

; rfr: video/20230223-mert-clj-egzersiz-50.mp4

; #trm: coll'u açmak = explode etmek
(apply + [1 2 3])
; ≣
(+ 1 2 3)
; [1 2 3] -> 1 2 3 açma işlemine explode etmek

; [2:5] 3. öğe ile 5. öğe arasındakileri al [2] [3] [4]
; [:3] baştan başla 3. öğeye kadar al: [0] [1] [2]
; [3:] 3. öğeden sonuncuya kadar al: [3] [4] ... [n]
; bu python'un notasyonu olmakla beraber R'da veya benzer dilde de bu notasyon kullanılır

(drop 3 a)  ; [3:]
;=> (4 5 6 7 8 9 10)
; ilk 3 öğe hariç hepsini alıyoruz:

(take (- (count a) 3) a)  ; [:-3]
;=> (1 2 3 4 5 6 7)
; son 3 öğe hariç hepsini aldık

(count a)
;=> 10
(take 7 a)
;=> (1 2 3 4 5 6 7)

(drop (- (count a) 3) a) ; [-3:]
;=> (8 9 10)

(drop 7 a)

(drop 3 (take 7 a))
;=> (4 5 6 7)
; 3:7 dilimine denk geliyor

(comment
  (take 7 a)
  ;=> (1 2 3 4 5 6 7)
  ; önce ilk 7 öğeyi aldık

  (drop 3 '(1 2 3 4 5 6 7))
  ;=> (4 5 6 7)
  ; sonra bunlardan ilk 3'ünü sildik
  ; [3:7] dilimine denk gelmiş oldu [3] [4] [5] [6]
  ,)

(drop 3 (take (- (count a) 3) a))
;=> (4 5 6 7)

; a01: count yerine `take-last` veya `drop-last` kullan

(def a (take 10 (iterate inc 1)))
(identity a)
;=> (1 2 3 4 5 6 7 8 9 10)

(take 3 a) ; başta 3 öğeyi al
;=> (1 2 3)
(drop 3 a) ; baştan 3 öğeyi sil
;=> (4 5 6 7 8 9 10)
(drop-last 3 a) ; sondan 3 öğeyi sil
;=> (1 2 3 4 5 6 7)
(take-last 3 a) ; sondan 3 öğeyi al
;=> (8 9 10)

(drop 3 (take 7 a)) ; get 4 elements starting from 3
(drop 3 (drop-last 3 a)) ; get all elements except the first and the last 3

; içiçe işlemler için thread macro:
(->> a
  (take 7)
  (drop 3)) ; get 4 elements starting from 3
(->> a
  (drop-last 3)
  (drop 3)) ; get all elements except the first and the last 3

; a02: vektörler için subvec kullan

(def a (vec (range 1 (inc 10))))
(identity a)
;=> (1 2 3 4 5 6 7 8 9 10)

(subvec a 0 3) ; [0:3]
; [1 2 3]

(subvec a 3) ; [3:]
; [4 5 6 7 8 9 10]
; 3. argümanı vermemiş burada
; bu yüzden sonuna kadar git anlamına geliyor

(subvec a 0 (- (count a) 3))
; [1 2 3 4 5 6 7]

(- (count a) 3)
; 7

; q: vektöre öğeler baştan eklendiği (prepend) için oradan yapılan işlemler daha mı hızlı olur?
; hayır. vektör içinde herhangi bir öğeye erişim, O(1) anlık bir operasyondur
; ister baştan, ister sondan.
; neden böyle?
; çünkü elimizde bir indeks var.
; bunların hepsi aslında bellekteki bir konuma (place) erişmek anlamına geliyor
; bir vektör verdik, bir de o vektörün 3. öğesine eriş dedik.
; şimdi  sen başlangıç anında, o vektörün bellekteki adresini biliyorsun:
; 0x9009934 [10 324  4394 232 354]
; şimdi sen o vektörün her bir öğesine ayrılan bellek alanı miktarını da biliyorsun
; her bir öğeye 10 byte ayrılıyor diye varsayalım
; dolayısıyla sen 3. öğeye ulaşmak istiyorsan, biliyorsun 30 byte ilerlemen gerekir baştan
; baştaki adresi de bildiğin için
; baştaki adrese 30 byte eklersin, 3. öğenin adresine ulaşmış olursun

(subvec a (- (count a) 3)) ; [7:]
; [8 9 10]

(subvec a 3 (+ 3 4)) ; [3:7]
; [4 5 6 7]

(subvec a 3 (- (count a) 3)) ; [3:7]
; [4 5 6 7]

; a03: code smell

; Slicing a sequence is a bit of a "code smell" - a sequence in general is designed for sequential access of items.
;
; If you are going to do a lot of slicing / concatenation,
; there are much better data structures available,
; in particular checkout the RRB-Tree vector implementation:

; #trm: code smell = kod kokusu = "kötü" kod

; sequencelar, imlementasyon olarak birer listedir
; listelerde indeks var mı? yok
; dolayısıyla sen slicing yaparsan liste üzerinde, sırayla tek tek baştan başlayarak bütün öğeleri dolaşması gerekir
; o yüzden slicing işlemleri yapman gerekiyorsa, liste kullanmamayı düşün, vektör kullanmayı düşün
; performans açısından çok daha hızlı: O(1) ile O(n) arasında inanılmaz bir fark vardır
; O(1): tek işlemde yapıyorsun
; O(n): sahip olduğun veri miktarı kadar işlem yapıyorsun (veriyle orantılı artar)