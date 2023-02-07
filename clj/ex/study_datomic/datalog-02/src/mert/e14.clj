(ns mert.e14)

; Barış'la Datomic Çalışmaları
; Tarih: 20230207
; rfr: video/20230207-mert-clj-egzersiz-22.mp4

; Konu: Rules

; [Learn Datalog Today!](https://www.learndatalogtoday.org/chapter/8)
; https://docs.datomic.com/on-prem/query/query.html#rules-grammar
; https://docs.datomic.com/on-prem/query/query.html#rules

(require '[datomic.client.api :as d])
(use '[mert.e04 :only [conn] :as e04])
(require '[mert.e05 :as e05])
(def db (d/db conn))

; Şu ana kadar öğrendiğimiz 3 farklı fonksiyon tipi var:
; 1. Predicate Functions -> sorgulama kriterleri olarak kullanıyorduk, boolean/truthy değerler döner
; 2. Transformation Functions -> data pattern içinde kullanıyorduk, bir değişkenin değerlerini manipüle ediyoruz
; 3. Aggregate Functions -> result set içindeki gruplar üzerinden istatistikler toplamak için

; Rules ise yazdığımız sorgu kriterlerini (data pattern) tekrar kullanabilmemizi sağlayan bir yapı
; Şimdi Rules dediğimiz şey ile bunlara bir dördüncüsünü ekleyeceğiz.
; Datalog sorgu cümleciklerini (data patterns) tekrar kullanmamızı sağlayan bir yapı, Rules.

; Örnek. Daha önceden aşağıdaki gibi bir sorgu yapmıştık.
(d/q
  '[:find (pull ?order [*])
    :where
    [?e :product/name "Kalem"]
    [?e :product/color :color/red]
    [?order :order/product ?e]]
  db)
;=>
;[[{:db/id 87960930222174, :order/product #:db{:id 92358976733266}, :order/size 4}]
; [{:db/id 96757023244376, :order/product #:db{:id 92358976733266}, :order/size 6}]]

; Bu yukarıdaki sorgu kriterlerini tekrar tekrar yazmak istemediğimizden bunlara bir isim verip, o ismi kullanmak istiyoruz.
; Buradaki sorgu cümleciklerini tekrar kullanılabilir bir yapı içine alabiliriz.
(def rules '[[(kirmizi-kalem ?e)
              [?e :product/name "Kalem"]
              [?e :product/color :color/red]]])
; Not: '[[ önce quote sonra vektör sonra vektör
; sonra kendi tanımladığım bir sembol ismi: kirmizi-kalem
; ondan sonra bizim tekrar kullanacağımız sorgu kriterleri geliyor.

; aynı db gibi bir argüman olarak q fonksiyonuna rules verisini gönderiyorum
; ayrıca :in cümleciğine `%` sembolünü koyuyorum
; ondan sonra data pattern içinde yukarıda tanımladığım `kirmizi-kalem` sembolünü kullanıyorum
(d/q
  '[:find (pull ?e [*])
    :in $ %
    :where
    (kirmizi-kalem ?e)]
  db rules)
;=>
;[[{:db/id 92358976733266,
;   :product/name "Kalem",
;   :product/color #:db{:id 74766790688844, :ident :color/red},
;   :product/id 1}]]

; Not: :in cümleciğine hem $ (db) hem de % (rules) ekledik

; Aynı rules kümesi içinde, bir kuralın altında birden çok sorgu ifadesi koyabiliriz
; Bu durumda datomic hepsini `or` ile birleştirir
(def rules '[[(kirmizi-veya-mavi-kalem ?e)
              [?e :product/name "Kalem"]
              [?e :product/color :color/red]]
             [(kirmizi-veya-mavi-kalem ?e)
              [?e :product/name "Kalem"]
              [?e :product/color :color/blue]]])

(d/q
  '[:find (pull ?e [*])
    :in $ %
    :where
    (kirmizi-veya-mavi-kalem ?e)]
  db rules)
;=>
;[[{:db/id 92358976733266,
;   :product/name "Kalem",
;   :product/color #:db{:id 74766790688844, :ident :color/red},
;   :product/id 1}]
; [{:db/id 92358976733267,
;   :product/name "Kalem",
;   :product/color #:db{:id 74766790688846, :ident :color/blue},
;   :product/id 2}]]
