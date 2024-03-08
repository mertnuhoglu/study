(ns syntax.rich-comment)

;; Tarih: 20230331
;; Video: 20230331-mert-clj-egzersiz-53.mp4

; Clojure'da çoğu fonksiyon, higher-order fonksiyon
; yani başka fonksiyonları birer argüman olarak alıyor
; bu yüzden nasıl çalıştığını anlamak bazen biraz yorucu oluyor.
;
;
(filter odd? [1 2 3])

(comment
  (filter odd? [1 2 3])
  ; => (1 3)
  ; bu şuna denktir
  (odd? 1)
  ;=> true
  (odd? 2)
  ;=> false
  (odd? 3)
  ;=> true
  ; end

  ; result = new List()
  ; for x in [1 2 3]
  ;   if odd(x) then
  ;      result.add(x)
  ;   else
  ;      // do nothing
  ; end
  ,)

(map inc [1 2 3])

(comment
  (map inc [1 2 3])
  ;=> (2 3 4)

  ; şuna denktir:
  ; 1. tur
  (inc 1)
  ;=> 2
  ; 2. tur
  (inc 2)
  ;=> 3
  ; 3. tur
  (inc 3)
  ;=> 4

  ; aslında arg olarak verdiğimiz coll üzerinde iterasyon yapıyoruz
  ; result = new HashMap()
  ; for x in [1 2 3]
  ;   result.add(x)
  ; end
  ,)

(reduce + '(1 2 3))
;=> 6

(comment
  (reduce + '(10 20 40))
  ;=> 70

  ; 1. tur (iteration):
  (+ 10 20)
  ;=> 30
  ; 2. tur
  (+ 30 40)
  ;=> 70

  ; end
  ,)

(reduce + (filter odd? (map #(+ 2 %) (range 0 10)))) ; 35

; Bu ifadeyi deşifre etmek ilk etapta zor olabilir
; Bu durumda şöyle bir şey yaparak, kolayca deşifre edebilirsiniz
; rich comment bloku açın
(comment
  ; 1. adım:
  ; Hizalamayı düzgün yapalım
  (reduce
    +
    (filter
      odd?
      (map
        #(+ 2 %)
        (range 0 10)))) ; 35
  ; bu şekilde ayrı satırlarda hizalarsanız, her bir argümanın ne işe yaradığını görürsünüz

  ; öncelikle reduce kaç argüman alıyor?
  ; 2
  ; clojuredocs'taki hangi imza buna karşılık geliyor?
  ; (reduce f coll)

  ; f should be a function of 2 arguments. If val is not supplied,
  ;returns the result of applying f to the first 2 items in coll, then
  ;applying f to that result and the 3rd item, etc.
  ; f <- +
  ; coll <- (filter
  ;            odd?
  ;              (map
  ;                #(+ 2 %)
  ;               (range 0 10)))
  (filter
    odd?
    (map
      #(+ 2 %)
      (range 0 10)))
  ;=> (3 5 7 9 11)

  ; f <- +
  ; coll <- (3 5 7 9 11)
  (def f +)
  (def coll '(3 5 7 9 11))
  (reduce f coll)
  ;=> 35

  ; şöyle de yazabilirdim:
  (reduce + '(3 5 7 9 11))

  ,)

(comment
  ; şimdi filter ifadesini debug edelim
  (filter
    odd?
    (map
      #(+ 2 %)
      (range 0 10)))

  ; ilk işlemden son işleme doğru debug etmeliyiz
  (range 0 10)
  ;=> (0 1 2 3 4 5 6 7 8 9)

  ; sonra anonim fonksiyonu debug edelim
  #(+ 2 %)
  ;=> #object[syntax.rich_comment$eval4667$fn__4668 0x39f111a8 "syntax.rich_comment$eval4667$fn__4668@39f111a8"]
  ; anonim fonksiyonu nasıl test edeceğim?
  (#(+ 2 %) 0)
  ;=> 2

  (map
    #(+ 2 %)
    (range 0 10))
  ; şuna denktir
  (map
    #(+ 2 %)
    '(0 1 2 3 4 5 6 7 8 9))
  ;=> (2 3 4 5 6 7 8 9 10 11)

  ; 1. tur
  (#(+ 2 %) 0)
  ;=> 2
  ; 2. tur
  (#(+ 2 %) 1)
  ;=> 3
  ; 3. tur
  (#(+ 2 %) 2)
  ;=> 4
  ;...

  ; map'in çıktısı filter'ın ikinci argümanının yerine geçiyor
  (filter
    odd?
    '(2 3 4 5 6 7 8 9 10 11))
  ;=> (3 5 7 9 11)

  ; 1. tur
  (odd? 2)
  ;=> false
  (odd? 3)
  ;=> true
  ;...


  (reduce
    +
    '(3 5 7 9 11))
  ;=> 35

  ; 1. tur
  (+ 3 5)
  ;=> 8
  ; 2. tur
  (+ 8 7)
  ;=> 15
  ; 3. tur
  (+ 15 9)
  ;=> 24
  ; ...

  ; end
  ,)

; Algebra = cebir
; cebir ne anlama geliyor?
; placeholder'ların (yer tutucuların) yerine ilgili değeri koymak anlamına gelir
; bütün debug işlemleri bu mantıkla yapılır

(def xform
  (comp
    (partial filter odd?)
    (partial map #(+ 2 %))))
(reduce + (xform (range 0 10))) ; 35

(comment
  ; (reduce f coll)
  (def f +)
  (def coll (xform (range 0 10)))
  (identity coll)
  ;=> (3 5 7 9 11)


  ; end
  ,)

(comment
  ; xform'u debug edelim
  (def xform
    (comp
      (partial filter odd?)
      (partial map #(+ 2 %))))

  ; a02:
  (xform (range 0 10))
  ;=> (3 5 7 9 11)

  (comp
    (partial filter odd?)
    (partial map #(+ 2 %)))
  ;=> #object[clojure.core$comp$fn__5807 0x183dfa7c "clojure.core$comp$fn__5807@183dfa7c"]
  (xform (range 0 10))
  ; şuna denktir:
  ((comp
     (partial filter odd?)
     (partial map #(+ 2 %)))
   (range 0 10))
  ;=> (3 5 7 9 11)

  ((comp
     (partial filter odd?)
     (partial map #(+ 2 %))))
  ; bu da şuna denktir:
  ; 1. adım:
  ((partial map #(+ 2 %))
   (range 0 10))
  ;=> (2 3 4 5 6 7 8 9 10 11)
  ((partial filter odd?)
   '(2 3 4 5 6 7 8 9 10 11))
  ;=> (3 5 7 9 11)

  ; comp fonksiyonunun yerine yerleştirme yapalım:
  ((partial filter odd?)
   ((partial map #(+ 2 %))
    (range 0 10)))
  ;=> (3 5 7 9 11)

  ((partial map #(+ 2 %))
   (range 0 10))
  ; burada da partial yerine yerleştirme yapalım:
  (map #(+ 2 %) (range 0 10))
  ;=> (2 3 4 5 6 7 8 9 10 11)

  ,)







