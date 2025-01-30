(ns dev-local.datalog02-ex-12)

; Barış'la Datomic Çalışmaları
; Tarih: 20230206
; rfr: video/20230206-mert-clj-egzersiz-21.mp4

; Konu: Transformation Functions
; rfr: [Learn Datalog Today!](https://www.learndatalogtoday.org/chapter/6)

(require '[datomic.client.api :as d])
(use '[dev-local.e04 :only [conn] :as e04])
(require '[dev-local.e05 :as e05])
(def db (d/db conn))

; Bu konu, predicatelara çok benziyor.
; Predicatelarda biz sorgu kriterlerimizin içinde fonksiyon kullanıyorduk.
; Transformasyon fonksiyonlarındaysa, yine data pattern içinde kullanacağız bu fonksiyonları
; Ama amacımız bu sefer ?x mantıksal değişkenlerimizin içindeki verileri işlemek (transformasyon)

; Mesela hepimiz çarpma işlemini biliriz
; Doğrudan dahili `*` operatörünü kullanalım şimdi
(d/q
  '[:find ?order ?size ?result
    :where
    [?order :order/size ?size]
    [(* ?size 10) ?result]]
  db)
;=> [[96757023244376 6 60] [92358976733271 4 40] [92358976733270 5 50]]

; Eğer kullandığımız fonksiyon clojure.core ns'inin bir üyesiyse, direk ismiyle kullanabiliriz
; Eğer başka bir ns'den geliyorsa, o zaman uzun ismiyle kullanmalıyız.

; Aynı predicatelarda olduğu gibi kendi fonksiyonlarımızı da tanımlayabiliriz
; Saf olması şartı burada da geçerli.
; Uzun isimle kullanmamız gerekiyor
(defn multiply_by [factor1 factor2]
  (* factor1 factor2))
(d/q
  '[:find ?order ?size ?result
    :where
    [?order :order/size ?size]
    [(dev-local.e12/multiply_by ?size 10) ?result]]
  db)
;=> [[96757023244376 6 60] [92358976733271 4 40] [92358976733270 5 50]]
; Dikkat: `multiply_by` fonksiyonunun sonucunu ?result değişkeninin içine koyduk

;| Binding Form | Binds      |
;|--------------|------------|
;| ?a           | scalar     |
;| [?a ?b]      | tuple      |
;| [?a …]       | collection |
;| [ [?a ?b ] ] | relation   |

; Burada transformasyon fonksiyonu scalar (primitif) bir değer döndü.
; Eğer transformasyon fonksiyonunun sonucu tuple, collection, veya relation ise o zaman binding yapmamız gerekir.

; Tuple Bağlama (Binding):
(defn to_tuple [factor1 factor2]
  [(* factor1 factor2) (+ factor1 factor2)])
(to_tuple 2 3)
;=> [6 5]
(d/q
  '[:find ?order ?size ?a1 ?a2
    :where
    [?order :order/size ?size]
    [(dev-local.e12/to_tuple ?size 10) [?a1 ?a2]]]
  db)
;=> [[92358976733271 4 40 14] [96757023244376 6 60 16] [92358976733270 5 50 15]]

; Collection Bağlama (Binding):
(defn to_coll [arg]
  (range arg))
(to_coll 4)
;=> (0 1 2 3)

(d/q
  '[:find ?order ?size ?xs
    :where
    [?order :order/size ?size]
    [(< ?size 5)]
    [(dev-local.e12/to_coll ?size) [?xs ...]]]
  db)
;=> [[92358976733271 4 3] [92358976733271 4 2] [92358976733271 4 1] [92358976733271 4 0]]
; to_coll (0 1 2 3) listesini dönüyor
; ?order ve ?size için de [[92358976733271 4]] değerleri bağlanmıştı
; şimdi bu iki kümenin bir cross joinini alıyoruz. bir tane order objesiyle 4 tane rakamı kombine ediyoruz

; Relation bağlamadan önce, parametrik sorguda relation nasıl bağlanıyordu hatırlayalım
(d/q
  '[:find ?e ?product-name ?color ?product-price
    :in $ [[?product-name ?product-price]]
    :where
    [?e :product/name ?product-name]
    [?e :product/color ?color]]
  db [["Kalem" 120]
      ["Defter" 250]])
;=>
;[[92358976733267 "Kalem" 74766790688846 120]
; [92358976733268 "Defter" 74766790688844 250]
; [92358976733269 "Defter" 74766790688845 250]
; [92358976733266 "Kalem" 74766790688844 120]]

; Relation Bağlama (Binding):
; Önce verilen bir sayı için bir relation dönen bir fonksiyon tanımlayalım
(defn to_rel [arg]
  (take
    (mod arg 7)
    [[:a 1] [:b 2] [:c 3] [:d 4] [:e 5] [:f 6]]))
(to_rel 3)
;=> ([:a 1]
;    [:b 2]
;    [:c 3])
; Bunu bir tablo gibi düşünebiliriz.
; İlk kolon :a :b :c listesinden oluşuyor
; İkinci kolonsa 1 2 3 değerlerinden oluşuyor

(d/q
  '[:find ?order ?size ?a ?b
    :where
    [?order :order/size ?size]
    [(< ?size 5)]
    [(dev-local.e12/to_rel ?size) [[?a ?b]]]]
  db)
;=> [[92358976733271 4 :a 1] [92358976733271 4 :c 3] [92358976733271 4 :b 2] [92358976733271 4 :d 4]]
; Burada iki tane kümenin kartezyen çarpımını aldık
; Sol kümede ([92358976733271 4]) öğesi var. Tek öğeli.
; Sağ kümedeyse ([:a 1] [:b 2] [:c 3] [:d 4])

; Hem transformasyon hem de predicate fonksiyonları Datomic'in avantajları
; SQL ile de yapabilirsin, ama çok daha zordur bunları yapmak.
; q: Çoğu yazılımcı bunları kullanmadığına göre elzem araçlar değil.
; Alternatif yöntemlerle çözülebiliyor olmalı.
; Doğru.
; Alternatif yöntem olarak da düz programlama dili kullanılıyor.
; Çoğu veritabanı yazılımında, veritabanı son derece işlevsiz bir şekilde kullanılıyor.
; Sadece veriyi depolama ve temel sorgular için kullanılıyor.
; Verinin transformasyonu hemen hemen hep programlama tarafında yapılıyor.
; Mesela Java yazılım evlerinde, verinin transformasyonu java ile yapılır, SQL ile yapılmaz.
; Ama aslında Postgres/Oracle gibi veritabanı yazılımlarıyla da transformasyon ve ileri sorgulamalar yapabilirsin.
; Ama çoğu yazılımcı veritabanı uzmanı olmadığından bunları kullanmaz.
; Bunun maliyeti şu olur:
; 1. Performans maliyeti olur. Veritabanında bu işleri yapmak çok daha hızlı olur.
; 2. Bazı veri işleme operasyonlarını programlama tarafında yapmak çok kodlama gerektirir.
; Mesela en basiti SELECT WHERE cümlelerini programlama tarafında yapmak çok zordur.
; For loop yaparsın, if then else yaparsın. Ama bunları kodlamanın mantığını çok zor hale getirir.
; 3. Kod ve veritabanını içiçe karışımı, daha sonra bunların birbirinden bağımsız genişlemesini de zorlaştırır.

; Yazılımcılar geliştirdikleri yazılımın aslında bir enformasyon sistemi olduğunu çoğu zaman unutur.
; Network senkronizasyonu, authentication, güvenlik katmanları ... Ayrı ayrı problemlerdir
; Bir sürü teknolojiyi, bir sürü algoritmayı vs. bilmeni gerektirir.
; Enformasyonu işleme paradigmasına ne kadar çok yaklaşırsak, zihinsel yükümüz o kadar azalır ve kolaylaşır.

