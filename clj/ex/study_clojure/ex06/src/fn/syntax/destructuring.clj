(ns fn.syntax.destructuring)

; rfr: video/20230217-mert-clj-egzersiz-41.mp4

; [Clojure - Destructuring in Clojure](https://clojure.org/guides/destructuring)

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
