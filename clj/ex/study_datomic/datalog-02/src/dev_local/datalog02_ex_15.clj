(ns dev-local.datalog02-ex-15)

; Barış'la Datomic Çalışmaları
; Tarih: 20230207
; rfr: video/20230207-mert-clj-egzersiz-23.mp4

; Konu: Pull API

; [Datomic Pull | Datomic](https://docs.datomic.com/on-prem/query/pull.html)

(require '[datomic.client.api :as d])
(use '[dev-local.e04 :only [conn] :as e04])
(require '[dev-local.e05 :as e05])
(def db (d/db conn))

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

; pull içinde `[*]` ifadesi ilgili varlıkların tüm atribut ve değerlerini otomatikman almamızı sağlıyor.
; bunu biraz daha incelterek istediğimiz gibi ayarlayabiliriz
; mesela belli atribut değerlerini çekebiliriz

(d/q
  '[:find (pull ?order [:db/id :order/size])
    :where
    [?e :product/name "Kalem"]
    [?e :product/color :color/red]
    [?order :order/product ?e]]
  db)
;=> [[{:db/id 87960930222174, :order/size 4}]
;    [{:db/id 96757023244376, :order/size 6}]]

; referans atributlarını da yine aynı şekilde çekebiliriz
(d/q
  '[:find (pull ?order [:db/id :order/product])
    :where
    [?e :product/name "Kalem"]
    [?e :product/color :color/red]
    [?order :order/product ?e]]
  db)
;=>
;[[{:db/id 87960930222174, :order/product #:db{:id 92358976733266}}]
; [{:db/id 96757023244376, :order/product #:db{:id 92358976733266}}]]

; Ancak ref atributlarının entity idlerini bize dönüyor.
; Biz entity id yerine aslında o ref varlığın :product/name gibi primitif atributlarını görmek istiyoruz
; Bu durumda ne yapacağız?

(d/q
  '[:find (pull ?order [:db/id {:order/product [:product/name]}])
    :where
    [?e :product/name "Kalem"]
    [?e :product/color :color/red]
    [?order :order/product ?e]]
  db)
;=>
;[[{:db/id 87960930222174, :order/product #:product{:name "Kalem"}}]
; [{:db/id 96757023244376, :order/product #:product{:name "Kalem"}}]]

; Bir de :product/color atributunu görmek istersek ne yaparız?
(d/q
  '[:find (pull ?order [:db/id {:order/product [:product/name :product/color]}])
    :where
    [?e :product/name "Kalem"]
    [?e :product/color :color/red]
    [?order :order/product ?e]]
  db)
;=>
;[[{:db/id 87960930222174, :order/product #:product{:name "Kalem", :color #:db{:id 74766790688844, :ident :color/red}}}]
; [{:db/id 96757023244376, :order/product #:product{:name "Kalem", :color #:db{:id 74766790688844, :ident :color/red}}}]]

; q: Eğer başka bir varlığa daha ref olsaydı o zaman ne yapacaktık?
; onun için de ayrı bir map daha ekleyecektik
; (pull ?order [:db/id {:order/product [:product/name :product/color]} {:order/manufacturer [:manufacturer/name]}])

; Yukarıda :product/color içindeki 2 atributu da getirdi.
; Biz sadece rengin ismini yani :color/red gibi değerleri görmek istiyoruz.
(d/q
  '[:find (pull ?order [:db/id {:order/product [:product/name {:product/color [:db/ident]}]}])
    :where
    [?e :product/name "Kalem"]
    [?e :product/color :color/red]
    [?order :order/product ?e]]
  db)
;=>
;[[{:db/id 87960930222174, :order/product #:product{:name "Kalem", :color #:db{:ident :color/red}}}]
; [{:db/id 96757023244376, :order/product #:product{:name "Kalem", :color #:db{:ident :color/red}}}]]
;
; Bu şekilde pull api sayesinde nested bir map sonucu dönebiliyoruz

; pull fonksiyonunu q sorgu fonksiyonunun dışında da kullanabiliriz:

(d/pull
  db
  [:product/color :product/name]
  [:product/id 3])
;=> #:product{:color #:db{:id 74766790688844, :ident :color/red}, :name "Defter"}

