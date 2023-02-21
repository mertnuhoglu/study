(ns fn.for)

; rfr: video/20230215-mert-clj-egzersiz-37.mp4
; rfr: sof20.clj
; rfr: fn/doseq.clj

; (for seq-exprs body-expr)  #nclk/çok-önemli
; List comprehension. Takes a vector of one or more
;  binding-form/collection-expr pairs, each followed by zero or more
;  modifiers, and yields a lazy sequence of evaluations of expr.
;  Collections are iterated in a nested fashion, rightmost fastest,
;  and nested coll-exprs can refer to bindings created in prior
;  binding-forms.  Supported modifiers are: :let [binding-form expr ...],
;  :while test, :when test.
;

(def digits [1 2 3])
(for [d digits] d)
;=> (1 2 3)

; aynısını map ile yapsak nasıl olurdu?
(map identity digits)
;=> (1 2 3)

; identity kullanmasak nasıl yapardık map ile?
(map (fn [e] e) digits)
;=> (1 2 3)

((fn [e] e) 5)
;=> 5

(map (fn [e] (+ e 1)) digits)
;=> (2 3 4)

; bunu tekrar for ile yapmaya çalışalım
(for [e digits] (+ e 1))
;=> (2 3 4)

; for iki argüman alır
; ilk argüman: seq-exprs
; yani bir dizi expression alır
; bunu bracket içinde ifade ederiz
; çünkü bracket bir vektör (seqtir).

; ikinci argüman: nihai döneceğimiz işlemleri tarif eden body-expr

; ilk argümanı: let argümanlarını binding ile tanımlıyorduk, onun gibi düşünebilirsiniz

(def coll1 [:a :b])
(def coll2 [1 2 3])
(for [e1 coll1
      e2 coll2]
  {e1 e2})
;=> ({:a 1} {:a 2} {:a 3} {:b 1} {:b 2} {:b 3})
; Java'daki nested for loop gibi çalışır, ama biraz daha farklı.
; nasıl çalışıyor?
; coll1 bunun öğelerini sırayla e1'e bağlıyoruz
; coll2 öğelerini sırayla e2'ye bağlıyoruz
; ve bunların kombinasyonlarını sırayla çalıştırıyoruz
; coll1 içindeki öğeleri sırayla e1'e bağla ve her biri için tekrarla:
;    coll2 içindeki öğeleri sırayla e2'ye bağla ve her biri için tekrarla:
;        {e1 e2} bunu nihai sonuç olarak döneceğimiz listeye ekle
;
; başka bir deyişle
; 1. tur:
; :a değerini e1'e bağla
; 1 değerini e2'ye bağlıyorum
; {:a 1} objesini yield ediyorum
; bu yield edilmiş objeyi result listesine ekliyorum
;
; 2. tur:
; :a değerini e1'e bağla
; 2 değerini e2'ye bağlıyorum
; {:a 2} objesini yield ediyorum
; bu yield edilmiş objeyi result listesine ekliyorum

; bunu map ile tam olarak yapamayız, çünkü kartezyen çarpım (cartesian product) yapıyoruz
#_(map #({%1 %2}) coll1 coll2)
(map (fn [e1 e2] {e1 e2}) coll1 coll2)
;=> ({:a 1} {:b 2})
; nested loop şeklinde çalışmıyor
; tek seviyeli bir döngü şeklinde çalışıyor

; 1. tur:
; :a değerini e1'e bağla
; 1 değerini e2'ye bağla
; {:a 1} objesini yield et

; 2. tur:
; :b değerini e1'e bağla
; 2 değerini e2'ye bağla
; {:b 2} objesini yield et

; 3. tur:
; e1'e bağlanacak değer kalmadı
; o yüzden yield ettiğin tüm objeleri bir seq içinde birleştir ve return et

; tek bir seviyeli bir for döngüsü:
; Map result = new HashMap()
; for (i = 0; i < length(coll1); i++) {
;   e1 = coll1[i]
;   e2 = coll2[i]
;   result.add({e1, e2})
; }

; nested loop olan for comprehension:
; Map result = new HashMap()
; for (i = 0; i < length(coll1); i++) {
;   e1 = coll1[i]
;   for (j = 0; j < length(coll2); j++) {
;     e2 = coll2[j]
;     result.add({e1, e2})
;   }
; }

; Terim: cartesian product (kartezyen çarpım)
; İki farklı kümedeki öğelerin, birbiriyle sıralı kombinasyonu anlamına gelir.

; e03:
(def coll3 '([1 2 3] [20 30]))
(for [e3 coll3
      f3 e3]
  (identity f3))
;=> (1 2 3 20 30)
; for i ...
;   e3 = coll3[i]
;   for j ...
;      f3 = e3[j]
;      yield(f3)

; şöyle isimlendirelim bunu:
; iki seviyeli bir ağaç yapısına sahibiz
; bunu şöyle modellesek daha uygun olur:
;     coll3             -- tree   - ağaç
;      /    \
;   [1 2 3]  [20 30]    -- branch - dal
;  /  |  \     /  \
; 1   2   3   20   30   -- leaf   - yaprak
; iki daldan oluşan bir ağaç yapısına sahibiz
(def tree '([1 2 3] [20 30]))
(for [branch tree
      leaf branch]
  (identity leaf))

(for [branch tree]
  (identity branch))
; ([1 2 3] [20 30])

; e04:
(def bs '([{1 2}] [{2 3}] [{4 5}]))

(for [subcoll bs, item subcoll] item)
;=> ({1 2} {2 3} {4 5})

; for i ...            -- bir seviye aşağı iniyoruz
;   e3 = coll3[i]
;   for j ...          -- bir seviye aşağı iniyoruz
;      f3 = e3[j]
;      yield(f3)       -- bir seviye kaplama yapıyoruz

(def bs [[{1 2}] [{2 3}] [{4 5}]])

(for [subcoll bs, item subcoll] item)
;=> ({1 2} {2 3} {4 5})

; rfr: video/20230216-mert-clj-egzersiz-40.mp4

;; :when
(for [x [1 2 3]] (+ x 10))
;=> (11 12 13)

; yukarıdaki işlemi sadece tek sayılar için yapalım
(for [x [1 2 3] :when (odd? x)] (+ x 10))
;=> (11 13)
; tekil sayıya ulaşınca, onu atlarız

;; :while
(for [x [1 2 3] :while (odd? x)] (+ x 10))
;=> (11)
; ilk tekil sayıya ulaşır ulaşmaz, for döngüsünü sonlandırdık
