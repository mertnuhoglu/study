(ns dev-local.datalog02-ex-13)

; Barış'la Datomic Çalışmaları
; Tarih: 20230207
; rfr: video/20230207-mert-clj-egzersiz-22.mp4

; Konu: Aggregates

; [Learn Datalog Today!](https://www.learndatalogtoday.org/chapter/7)
; https://docs.datomic.com/on-prem/query/query.html#aggregates

(require '[datomic.client.api :as d])
(use '[dev-local.e04 :only [conn] :as e04])
(require '[dev-local.e05 :as e05])
(def db (d/db conn))

; SQL'deki MAX, MIN, DISTINCT, AVG gibi fonksiyonlara aggregate fonksiyonları deniyor.

; Tek değer dönen aggregates

; Örnek: Tüm siparişlerin adet miktarlarının ortalamasını bulmak istiyoruz:
; Normalde tüm siparişlerin adetlerini bir çıkartalım
(d/q
  '[:find ?order ?size
    :where
    [?order :order/size ?size]]
  db)
;[[87960930222172 4]
; [87960930222173 5]
; [101155069755482 2]
; [101155069755483 3]
; [92358976733271 4]
; [101155069755481 7]
; [96757023244376 6]
; [92358976733270 5]
; [87960930222175 3]
; [87960930222174 4]]

(d/q
  '[:find ?size
    :where
    [?order :order/size ?size]]
  db)
;=> [[2] [3] [4] [5] [6] [7]]
; Benim beklediğim sonuç şöyleydi:
; 4 5 2 3 4 7 6 5 3 4

(d/q
  '[:find (distinct ?size)
    :where
    [?order :order/size ?size]]
  db)
;=> [[#{7 4 6 3 2 5}]]
; distinct: ayrık bir şekilde sonuçları listeler. tekrarlanan değerleri birleştirir

(d/q
  '[:find (count ?size)
    :where
    [?order :order/size ?size]]
  db)
;=> [[6]]

(d/q
  '[:find (count ?order)
    :where
    [?order :order/size ?size]]
  db)
;=> [[10]]

(d/q
  '[:find (max ?size)
    :where
    [?order :order/size ?size]]
  db)
;=> [[7]]

; https://docs.datomic.com/on-prem/query/query.html#aggregates-returning-a-single-value
; (min ?xs)   en küçük değeri (numerik)
; (max ?xs)   en büyük değeri
; (count ?xs)    kaç tane adet olduğunu verir
; (count-distinct ?xs)   kaç farklı adet olduğunu verir
; (sum ?xs)      toplamını hesaplar
; (avg ?xs)      ortalamasını hesaplar (average)
; (median ?xs)   ortanca değeri verir
; (variance ?xs)   istatistiksel varyansı
; (stddev ?xs)     istatistiksel standart sapmayı verir

; Aggregate kelimesinin anlamı nedir?
; Aggregate, yığın, küme, birleştirmek gibi anlamlarda kullanılan bir kelime.
; Birden çok şeyi toparlamak veya gruplamak gibi bir anlamda düşünülebilir.
; Veri sorgularımızda dönen sonuç kümesindeki tek tek satırları döndürmek yerine,
; bu kümenin tümüne veya alt gruplarına dair bir istatistik üretmek için kullanıyoruz.

; Bu yukarıdaki örnekler, tek bir değer (scalar) dönen aggregate fonksiyonları.
; Bir de koleksiyon dönen aggregate fonksiyonları var.

; https://docs.datomic.com/on-prem/query/query.html#aggregates-returning-collections
; (distinct ?xs)     ayrık öğeleri döner
; (min n ?xs)        min n öğeyi verir
; (max n ?xs)        en büyük n öğeyi verir
; (rand n ?xs)
; (sample n ?xs)     verdiğin kümeden n tane örnek alır

(d/q
  '[:find (max 3 ?size)
    :where
    [?order :order/size ?size]]
  db)
;=> [[[7 6 5]]]

(d/q
  '[:find (sample 3 ?order)
    :where
    [?order :order/size ?size]]
  db)
;=> [[[96757023244376 92358976733270 87960930222173]]]

; Bütün bu aggregate fonksiyonları SQL'daki benzerleriyle aynı işleve sahip.
; Dolayısıyla bunların işlevini anlamak için SQL dokümanlarına da başvurabiliriz.

; Custom Aggregates

; Kendi özel aggregate fonksiyonumuzu yazmak da Predicate veya Transformation Fonksiyonlarıyla benzer şekilde.
; mode fonksiyonu bir listedeki en çok kullanılan öğeyi dönsün.
(defn mode
  [vals]
  (->> (frequencies vals)
    (sort-by (comp - second))
    ffirst))
(mode '(10 10 10 20 30 20))
;=> 10
(mode '(1 2 2 2 2 3))
;=> 2
(mode '(1 2 3 3 2 3))
;=> 3

; Bu mode fonksiyonuyla dönen bir result set (bir sorgunun sonucunda dönen küme) içindeki en çok tekrarlanan öğeyi döner.

; Veri tabanımıza yeni sipariş kayıtları girelim
(def order-list-5
  [{:order/product [:product/id 2] :order/size 4}
   {:order/product [:product/id 3] :order/size 5}
   {:order/product [:product/id 1] :order/size 4}
   {:order/product [:product/id 2] :order/size 3}])
(d/transact conn {:tx-data order-list-5})
(def db (d/db conn))
(d/q
  '[:find (pull ?order [*])
    :where
    [?order :order/size ?size]]
  db)
;=>
;[[{:db/id 87960930222172, :order/product #:db{:id 92358976733267}, :order/size 4}]
; [{:db/id 87960930222173, :order/product #:db{:id 92358976733268}, :order/size 5}]
; [{:db/id 87960930222174, :order/product #:db{:id 92358976733266}, :order/size 4}]
; [{:db/id 87960930222175, :order/product #:db{:id 92358976733267}, :order/size 3}]
; [{:db/id 92358976733270, :order/product #:db{:id 92358976733263}, :order/size 5}]
; [{:db/id 92358976733271, :order/product #:db{:id 92358976733265}, :order/size 4}]
; [{:db/id 96757023244376, :order/product #:db{:id 92358976733266}, :order/size 6}]
; [{:db/id 101155069755481, :order/product #:db{:id 92358976733267}, :order/size 7}]
; [{:db/id 101155069755482, :order/product #:db{:id 92358976733268}, :order/size 2}]
; [{:db/id 101155069755483, :order/product #:db{:id 92358976733269}, :order/size 3}]]

(d/q
  '[:find ?order ?size
    :where
    [?order :order/size ?size]]
  db)
;=>
;[[87960930222172 4]
; [87960930222173 5]
; [101155069755482 2]
; [101155069755483 3]
; [92358976733271 4]
; [101155069755481 7]
; [96757023244376 6]
; [92358976733270 5]
; [87960930222175 3]
; [87960930222174 4]]

(d/q
  '[:find (dev-local.e13/mode ?size)
    :where
    [?order :order/size ?size]]
  db)
; beklediğimiz gibi çalışmadı
; aslında dönmesi gereken değer 4 çünkü 3 farklı siparişte 4 adet bulunuyor

; olması gereken sonucu kendimiz clojure fonksiyonlarıyla bulalım
(map second (d/q
              '[:find ?order ?size
                :where
                [?order :order/size ?size]]
              db))
;=> (4 5 2 3 4 7 6 5 3 4)

(mode
  (map second (d/q
                '[:find ?order ?size
                  :where
                  [?order :order/size ?size]]
                db)))
;=> 4

(->>
  (d/q
    '[:find ?order ?size
      :where
      [?order :order/size ?size]]
    db)
  (map second)
  (mode))
;=> 4

; GROUP BY
; https://docs.datomic.com/cloud/query/query-data-reference.html#aggregate-example

; SQL'de bu aggregate fonksiyonları genelde her zaman GROUP BY ile birlikte kullanılır
; datomicde `GROUP BY` ifadesini kullanmayız. farklı bir formatta bunu yaparız.

; [:find ?a (min ?b) (max ?b) ?c (sample 12 ?d)
; ?a ve ?c'ye göre gruplar

(d/q
  '[:find ?size (count ?size)
    :where
    [?order :order/size ?size]]
  db)


; düz clojure ile yapmayı deneyelim
(map second (d/q
              '[:find ?order ?size
                :where
                [?order :order/size ?size]]
              db))
;=> (4 5 2 3 4 7 6 5 3 4)
; TODO: https://www.google.com/search?q=clojure+vector+items+group+by+count&sourceid=chrome&ie=UTF-8
