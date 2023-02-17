(ns fn.syntax.destructuring)

; rfr: video/20230217-mert-clj-egzersiz-41.mp4

; [Clojure - Destructuring in Clojure](https://clojure.org/guides/destructuring) #nclk/çok-önemli

; Part 1: Sequential Destructuring (Ardışık Parçalama)

; normalde destructuring olmadan assignmentları nasıl yaparız?
(def my-line [[5 10] [10 20]])
; [5 10] benim birinci noktamın koordinatları
; 5 x koordinatı
; 10 y koordinatı
; [10 20] de benim ikinci noktamın koordinatları
; geometride bir düz doğruyu (veya çizgiyi) iki tane noktanın koordinatlarını vererek tanımlayabiliriz

; şimdi o koordinatları yukarıdaki my-line objesinden çekip print etmek istiyoruz
(let [p1 (first my-line)     ; ilk noktamızı çekiyoruz
      p2 (second my-line)    ; ikinci noktayı çektik
      x1 (first p1)          ; ilk noktanın x koordinatını çekiyoruz
      y1 (second p1)         ; ilk noktanın y koordinatını çekiyoruz
      x2 (first p2)
      y2 (second p2)]
  (println "Line from (" x1 "," y1 ") to (" x2 ", " y2 ")"))
;= "Line from ( 5 , 10 ) to ( 10 , 20 )"

(comment
  (def p1 (first my-line))
  (identity p1)
  ;=> [5 10]
  (second my-line)
  ;=> [10 20]
  (first p1)
  ;=> 5
  (second p1)
  ;=> 10
  ,)

; çok uzun bir yöntem
; biz tek seferde x1 y1 x2 y2 koordinat değerlerine ulaşabiliriz
; bunun için destructuring kullanacağız

;= Using the same vector as above
(let [[p1 p2] my-line    ; tek işlemde, my-line vektörünü parçalarına ayırdım
      [x1 y1] p1         ; tek işlemde p1 vektörünü parçalarına ayırdım
      [x2 y2] p2]        ; tek işlemde p2 vektörünü parçalarına ayırdım
  (println "Line from (" x1 "," y1 ") to (" x2 ", " y2 ")"))
;= "Line from ( 5 , 10 ) to ( 10 , 20 )"

; Not: x1 y1 vs. primitif değerler olduğundan daha fazla parçalarına ayıramam
; #terim: destructuring = parçalarına ayırmak
; Peki hangi objelerin parçası vardır?
; Bileşke objelerin (aggregate) parçaları vardır
; Collection objeleri bu tarz bileşke objelerdir.

; q: my-line 2 tane alt obje yerine 3 tane alt objeden oluşsaydı nasıl yapacaktık?
; cevap: 2 yerine 3 tane değişkene atama yapacaktık
(def my-line-3 [[5 10] [10 20] [15 30]])
(let [[p1 p2 p3] my-line-3
      [x1 y1] p1
      [x2 y2] p2
      [x3 y3] p3]
  (println "Line from (" x1 "," y1 ") through (" x2 ", " y2 ") to (" x3 ", " y3 ")"))
;Line from ( 5 , 10 ) through ( 10 ,  20 ) to ( 15 ,  30 )

; q: Destructuring için yeni bir fonksiyon yazmıyoruz?
; Evet, destructuring ile bir bileşke objenin alt parçalarını tek işlemde bir grup değişkene (isme) atama işlemi sadece.

; Part 2: Associative Destructuring (İlişkili Parçalama)

; Şu ana kadar yaptığımız destructuring sadece sequential objeler içindi
; Yani vektör ve list
; Maplerde destructuring biraz daha farklı yapılıyor
; Clojure destructuring is broken up into two categories, sequential destructuring and associative destructuring

; #terim: associative (data structures) = birleşmeli veya ilişkili veri yapıları
; LinkedList'ler de bir ardışıklık (sequential) özelliği var
; (10 20 30)
; Vektörlerde bir ardışıklık var, ama aynı zamanda bir indeks de var
; [10 20 30]
; 0 -> 10
; 0 indeksine 10 değerini ilişkilendirmişiz (associate etmişiz)
; 1 indeksine 20 değerini ...
; Maplerde de benzer bir durum var
; {:a 1 :b 2}
; :a anahtarına 1 değerini bağlamışız
; :b anahtarına 2 değerini ilişkilendirmişiz
; Not: Vektör hem sequential, hem associative data structure
; Map: sadece associative
; List: sadece sequential

; Ardışık veri yapılarını parçalarına ayırırken, ardışık bir şekilde parçalara isimler veriyoruz
; [p1 p2 p3] my-line-3

; İlişkilendirilmiş veri yapılarında bunu yapamayız, çünkü oradaki sıralamayı bilemeyiz

; Önce destructing olmadan normal yöntemle parçalara ayıralım

(def m {:a 1 :b 2})
(let [x (:a m)
      y (:b m)]
  (str "a değeri: " x " b değeri: " y))
;=> "a değeri: 1 b değeri: 2"

; Structured Lisp Editing: slurp, barf, move vs. düzenleme komutları
; Slurp/barf: [Paredit, a Visual Guide - Calva User Guide](https://calva.io/paredit/)

; Şimdi fonksiyon kullanmadan doğrudan destructuring ile parçalara ayıralım
(let [{x :a
       y :b} m]
  (str "a değeri: " x " b değeri: " y))
;=> "a değeri: 1 b değeri: 2"

; Her zaman LHS (sol taraf) atama yapılan taraf
; x = (:a m)
; y = (:y m)
; bunu bu şekilde yazmıyoruz
; {x :a
;  y :b} m

; Default değer vermek için :or clause'unu kullanıyoruz
(let [{x :a
       y :b
       z :c, :or {z 10}} m]
  (str "a değeri: " x " b değeri: " y " c değeri: " z))
;=> "a değeri: 1 b değeri: 2 c değeri: 10"

; birden çok sembole default değer verebilirsin
(let [{x :d
       y :b
       z :c, :or {z 10 x 5}} m]
  (str "a değeri: " x " b değeri: " y " c değeri: " z))
;=> "a değeri: 5 b değeri: 2 c değeri: 10"

; :as clause tüm map objesine erişim sağlıyor
(let [{x :a
       y :b :as z} m]
  (str "a değeri: " x " b değeri: " y " tüm map: " z))
;=> "a değeri: 1 b değeri: 2 tüm map: {:a 1, :b 2}"

; bu map'lerin keywordlerle parçalanarak atanması olayı çok fazla yaygın
; o yüzden daha özlü yazmayı sağlamak için bir yol daha var:
; :keys cümleciği
(let [{:keys [a b]} m]
  (str "a değeri: " a " b değeri: " b))

; not: :keys kullandığın durumda sembollerin isimleri map'in anahtar isimleriyle aynı olmalı
(let [{:keys [x y]} m]
  (str "a değeri: " x " b değeri: " y))
;=> "a değeri:  b değeri: "
; burada hiçbir şey gelmedi, çünkü x ve y diğer anahtarlarımız yok map içinde

; q: :keys tam olarak ne yapıyor?

(identity m)
;=> {:a 1, :b 2}
(let [{:keys [a b]} m]
  (str "a değeri: " a " b değeri: " b))

; böyle yapmak yerine doğrudan get vs. ile yapsak daha kolay olmaz mı?
; keys kullanmadan yapsak şöyle olurdu:
(let [{a :a
       b :b} m]
  (str "a değeri: " a " b değeri: " b))
;=> "a değeri: 1 b değeri: 2"
; eğer destructuring de kullanmayalım dersen şöyle olurdu:
(let [a (:a m)
      b (:b m)]
  (str "a değeri: " a " b değeri: " b))
;=> "a değeri: 1 b değeri: 2"
; eğer keyword fonksiyonlarını bile kullanmayalım sadece get fonksiyonuyla yapalım dersek?
(let [a (get m :a)
      b (get m :b)]
  (str "a değeri: " a " b değeri: " b))
;=> "a değeri: 1 b değeri: 2"

; q: bir mapi dolaşırken onun anahtarlarını bilemeyiz, onu nereden bulacağız?
; Genel durum bu olmaz
; Genelde veri analizinde, mapin anahtarlarını biliriz
; Bunun sebebi de, öntanımlı bir veri modeli kullanmamızdan kaynaklanır
; Ama bilemediğimiz nadir jenerik fonksiyon yazmamız gerekebilir
; O durumlarda bile yine destructuring yapmak gerekecek
; Meta model üzerinden varsayımlarda bulunabiliriz verinin yapısına dair

; rfr: video/20230217-mert-clj-egzersiz-42.mp4

; Şu ana kadarki destructuring örneklerini hep `let` formu üzerinde yaptık
; Başka formlarda da destructuring çalışır
; Özellikle de `defn` içinde çok kullanılır
; Aynı mantıkla çalışır orada da.
; Bir fonksiyona gönderdiğimiz argümanlar, aynı let içindeki gibi parçalarına ayrıştırılabilir destructuring ile.

; q: RHS'da 3 parçalı bir vektör var. LHS'daysa 2 tane sembol var.
; Bu durumda 3. parça ne olur acaba?

(def ps [1 2 3])
(let [[p1 p2] ps]
  (str "p1: " p1 " p2: " p2))
;=> "p1: 1 p2: 2"
; Hiç sorunsuz parçaladı

; q: RHS 3 öğeli bir vektör. LHS 2 sembol. Fakat ben ilk öğeyle, son öğeyi istiyorum.
(let [[p1 _ p3] ps]
  (str "p1: " p1 " p3: " p3))
;=> "p1: 1 p3: 3"

; q: RHS 4 öğeli. LHS 2 sembol. 1. ve 4. öğeleri istiyorum.
(def ps4 [1 2 3 4])
(let [[p1 _ _ p4] ps4]
  (str "p1: " p1 " p4: " p4))
;=> "p1: 1 p4: 4"

; q: Vektör associative bir veri yapısı olduğuna göre, map'in key'leri gibi vektörün indekslerini kullanabilir miyiz destructuring için?
; map'te şöyle yapıyoruz:
(let [x (:a m)
      y (:b m)]
  (str "a değeri: " x " b değeri: " y))

(:a m)  ; keyword as a function
;=> 1
#_(0 ps)  ; index as a function çalışmaz
;class java.lang.Long cannot be cast to class clojure.lang.IFn (java.lang.Long is in module java.base of loader 'bootstrap'); clojure.lang.IFn is in unnamed module of loader 'app')
'(0 ps)
;=> (0 ps)

(m :a)  ; map as a function
;=> 1
(ps 0)  ; index as a function?
;=> 1

(let [p1 (ps 0)
      p2 (ps 1)]
  (str "p1: " p1 " p2: " p2))
;=> "p1: 1 p2: 2"

; map'in anahtarı keyword yerine string veya numerik olunca çalışır mı?
(def m3 {"a" 1 "b" 2})
#_("a" m)  ; string as a function hata verir
;class java.lang.String cannot be cast to class clojure.lang.IFn (java.lang.String is in module java.base of loader 'bootstrap'); clojure.lang.IFn is in unnamed module of loader 'app')
(def m4 {0 1 1 2})
#_(0 m4)   ; numeric as a function hata verir
;class java.lang.String cannot be cast to class clojure.lang.IFn (java.lang.String is in module java.base of loader 'bootstrap'); clojure.lang.IFn is in unnamed module of loader 'app')

; mapin anahtarları numerik/string olunca destructuring çalışır mı?
(let [{x "a"
       y "b"} m3]
  (str "a değeri: " x " b değeri: " y))
;=> "a değeri: 1 b değeri: 2"

(let [{x 0
       y 1} m4]
  (str "a değeri: " x " b değeri: " y))
;=> "a değeri: 1 b değeri: 2"

; map'te bu model çalışıyorsa, vektörde de çalışabilir
(let [{x 0
       y 1} ps]
  (str "a değeri: " x " b değeri: " y))
;=> "a değeri: 1 b değeri: 2"

; çok büyük bir vektör üzerinden bunu deneyelim
(def ps100 (range 0 100))
(identity ps100)
;95
;96
;97
;98
;99

(let [{x 50
       y 90} ps100]
  (str "a değeri: " x " b değeri: " y))
;=> "a değeri: 51 b değeri: 91"
