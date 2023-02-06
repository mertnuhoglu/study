(ns mert.e11)

; Barış'la Datomic Çalışmaları
; Tarih: 20230206
; rfr: video/20230206-mert-clj-egzersiz-20.mp4

; rfr: [Learn Datalog Today!](https://www.learndatalogtoday.org/chapter/5)

(require '[datomic.client.api :as d])
(use '[mert.e04 :only [conn] :as e04])
(use '[mert.e05 :as e05])
(def db (d/db conn))

; Konu: Predicates
; Predicate kelimesinin Türkçe doğrudan karşılığı yüklem.
; Fakat kullanım anlamı şu: Doğru veya yanlış olabilen ifadeler için kullanılıyor.

; Başka bir deyişle boolean değer döndüren fonksiyonlar birer yüklemdir, bizim açımızdan.
; Ancak clojureda tüm data typelar booleana çevrilebilir.
; In Clojure nil and false are considered "false" and therefore we say they are both "falsey".
; Dolayısıyla herhangi bir data type dönen herhangi bir fonksiyonu,
; bir if ifadesine veya boolean değer bekleyen herhangi bir fonksiyona parametre olarak gönderebilirsin
; Polimorfizm (polymorphism): Statik typelarda her bir fonksiyonun bir signature dediğimiz beklediğimiz data typeları var.
; Eğer bu data typelardan biri değilse fonksiyon çağrısı hata verir.
; Ama eğer bu data typeların subclasslarıysa o zaman buna polimorfizm özelliği denir. Hata vermez uygun bir yönlendirme (dispatch) yapılır.

; Dolayısıyla aslında matematikteki "önerme" terimine denk geliyor.
; Herhangi bir ifade eğer içindeki değişkenlerin değerlerine bağlı olarak doğru veya yanlış olabiliyorsa, bu bir predicatetır.
; Dolayısıyla, biz predicate = önerme = yüklem gibi terimleri birbirine yerine kullanabiliriz.

; Bizim normal clojure fonksiyonlarımızı predicate olarak kullanabiliriz.

; Şu ana kadarki sorgularımızda sorgu kriterlerimizin bulunduğu terimlere "data pattern" diyorduk.
; Mesela:
(d/q
  '[:find (pull ?e [*])
    :where
    [?e :product/name "Kalem"]]
  db)
;=>
;[[{:db/id 92358976733266,
;   :product/name "Kalem",
;   :product/color #:db{:id 74766790688844, :ident :color/red},
;   :product/id 1}]
; [{:db/id 92358976733267,
;   :product/name "Kalem",
;   :product/color #:db{:id 74766790688846, :ident :color/blue},
;   :product/id 2}]]

; Burada `[?e :product/name "Kalem"]` bizim sorgu kriterimizdi ve buna data pattern diyoruz.

; Şimdi bunun yerine bir predicate kullanalım.
; Sorgu: İsmi "K" harfi ile başlayan ürünleri getir:
(d/q
  '[:find (pull ?e [*])
    :where
    [?e :product/name ?name]
    [(.startsWith ?name "K")]]
  db)
;=>
;[[{:db/id 92358976733266,
;   :product/name "Kalem",
;   :product/color #:db{:id 74766790688844, :ident :color/red},
;   :product/id 1}]
; [{:db/id 92358976733267,
;   :product/name "Kalem",
;   :product/color #:db{:id 74766790688846, :ident :color/blue},
;   :product/id 2}]]

; (.startsWith ?name "K")
; bunun Javadaki muadil sentaksı şöyle olur:
; name.startsWith("K")

(d/q
  '[:find (pull ?e [*])
    :where
    [?e :product/name ?name]
    [(= ?name "Kalem")]]
  db)
;=>
;[[{:db/id 92358976733266,
;   :product/name "Kalem",
;   :product/color #:db{:id 74766790688844, :ident :color/red},
;   :product/id 1}]
; [{:db/id 92358976733267,
;   :product/name "Kalem",
;   :product/color #:db{:id 74766790688846, :ident :color/blue},
;   :product/id 2}]]

; `.startsWith` bir clojure fonksiyonu
; `(.startsWith ?name "K")` bu ifadenin düz clojure kullanımı şöyle olurdu:
(def name1 "Kalem")
(.startsWith name1 "K")
;=> true
(def name2 "Defter")
(.startsWith name2 "K")
;=> false

; Başka bir örnek olarak `<` veya `>` gibi operatörleri ele alalım.
; Normalde bunları numerik sayıların kıyaslamasında kullanırız:
(< 3 5) ; ~ 3 < 5
;=> true
(< 5 3)
;=> false

; Bu fonksiyonu da datomic sorguları içinde kullanabiliriz.
; Sorgu: 5'ten daha az adet sipariş edilen işlemlerin kayıtlarını getir.
(d/q
  '[:find (pull ?order [*])
    :where
    [?order :order/size ?size]
    [(< ?size 5)]]
  db)
;=> [[{:db/id 92358976733271, :order/product #:db{:id 92358976733265}, :order/size 4}]]

; Not: SQL'de bunu kısmen yapabilirsiniz.
; Ancak eğer ilgili veritabanı sunucusunun built-in fonksiyonları kullanabilirsiniz.
; Fakat biz herhangi bir Clojure, Java fonksiyonunu kullanabiliriz.
; Dolayısıyla kendimiz de yeni fonksiyon tanımlayabiliriz.

; Kendimiz nasıl fonksiyon tanımlayıp sorgularda kullanabiliriz?
; rfr: [Query Reference | Datomic](https://docs.datomic.com/cloud/query/query-data-reference.html#predicates)
; Değer döndüren ve pure (side-effect üretmeyen) tüm clojure fonksiyonlarını bu şekilde predicate gibi kullanabiliriz.
; Değer döndürmek: Herhangi bir şeyi return etmesi anlamına geliyor.
; Pure = saf fonksiyon ne demek?
; f(x) = x+3
; Matematikteki fonksiyonların tümü saftır.
; Yani o fonksiyonun sonucu (çıktısı), her zaman ve sadece girdiye bağlıdır.
; Mesela f(x) fonksiyonuna arg olarak 2 verirsek, her zaman 5 alırız.
; Bizim clj fonksiyonumuz verilen bir değer için her zaman aynı sonucu dönüyorsa, ona saf fonksiyon deriz.
; Not: Saf (pure) fonksiyonların ne olduğuna dair detaylı örneklerle anlatım için:
; [Functional Programming | Clojure for the Brave and True](https://www.braveclojure.com/functional-programming/)
; Özet olarak, bir fonksiyonun sonucu sadece girdi argümanlarına bağlıysa buna saf fonksiyon denir.

; Çok basit bir saf olmayan fonksiyon örneği:
(def a 3)
(defn f [x]
  (+ x a))
(f 2)
;=> 5
(def a 4)
(f 2)
;=> 6

; Örneğin: `<` fonksiyonuna kendimiz yeni bir isim verip o yeni isimle fonksiyonu predicate olarak kullanalım:
(def test-predicate <)
(test-predicate 3 5)
;=> true
(test-predicate 5 3)
;=> false

; Kendi tanımladığımız `test-predicate` fonksiyonunu sorguda nasıl kullanacağım?
; Standart yukarıdaki fonksiyonlar gibi, tek fark var. Qualified ns ile kullanacağım.
(d/q
  '[:find (pull ?order [*])
    :where
    [?order :order/size ?size]
    [(mert.e11/test-predicate ?size 5)]]
  db)
;=> [[{:db/id 92358976733271, :order/product #:db{:id 92358976733265}, :order/size 4}]]
