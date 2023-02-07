(ns mert.e16)

; Konu: Reverse Lookup (Ters Referans)

; https://docs.datomic.com/on-prem/query/pull.html#reverse-lookup

(require '[datomic.client.api :as d])
(use '[mert.e04 :only [conn] :as e04])
(require '[mert.e05 :as e05])
(def db (d/db conn))

; Forward navigation
; Siparişten Ürüne ulaşabiliyoruz :order/product referansıyla
(d/q
  '[:find (pull ?order [:db/id {:order/product [:product/name]}])
    :where
    [?order :order/product ?e]]
  db)
;=>
;[[{:db/id 87960930222172, :order/product #:product{:name "Kalem"}}]
; [{:db/id 87960930222173, :order/product #:product{:name "Defter"}}]
; [{:db/id 87960930222174, :order/product #:product{:name "Kalem"}}]
; [{:db/id 87960930222175, :order/product #:product{:name "Kalem"}}]
; [#:db{:id 92358976733270}]
; [#:db{:id 92358976733271}]
; [{:db/id 96757023244376, :order/product #:product{:name "Kalem"}}]
; [{:db/id 101155069755481, :order/product #:product{:name "Kalem"}}]
; [{:db/id 101155069755482, :order/product #:product{:name "Defter"}}]
; [{:db/id 101155069755483, :order/product #:product{:name "Defter"}}]]

; Sipariş varlıklarının içinde :order/product atributu bulunuyor. Bu da ilgili Product varlığına ref içeriyor.
; Peki bunun tam tersi yapılamaz mı?
; Yani elimde bir Product varken, buna referans veren Order objesine erişebilir miyim pull api içinden?

; Teorik olarak mümkün olmalı.
; Ancak bu özellik normalde bildiğimiz programlama dillerinin map interfacelerinde bulunmaz.
; Datomic şöyle bir sentaks getirir: :order/_product dedin mi, ters yönde referans olarak kabul eder bunu.

(d/q
  '[:find (pull ?product [*])
    :where
    [?product :product/id]]
  db)
;=>
;[[{:db/id 92358976733266,
;   :product/name "Kalem",
;   :product/color #:db{:id 74766790688844, :ident :color/red},
;   :product/id 1}]
; [{:db/id 92358976733267,
;   :product/name "Kalem",
;   :product/color #:db{:id 74766790688846, :ident :color/blue},
;   :product/id 2}]
; [{:db/id 92358976733268,
;   :product/name "Defter",
;   :product/color #:db{:id 74766790688844, :ident :color/red},
;   :product/id 3}]
; [{:db/id 92358976733269,
;   :product/name "Defter",
;   :product/color #:db{:id 74766790688845, :ident :color/green},
;   :product/id 4}]]
; Bu şekilde veritabanımızdaki tüm Product kayıtlarını çektik.
; Şimdi bu Product objelerinin her biri hangi siparişlerde bulunuyorsa, onları da görmek istiyoruz.

(d/q
  '[:find (pull ?product [:product/id :product/name :order/_product])
    :where
    [?product :product/id]]
  db)
;=>
;[[{:product/id 1, :product/name "Kalem", :order/_product [#:db{:id 87960930222174} #:db{:id 96757023244376}]}]
; [{:product/id 2,
;   :product/name "Kalem",
;   :order/_product [#:db{:id 87960930222172} #:db{:id 87960930222175} #:db{:id 101155069755481}]}]
; [{:product/id 3, :product/name "Defter", :order/_product [#:db{:id 87960930222173} #:db{:id 101155069755482}]}]
; [{:product/id 4, :product/name "Defter", :order/_product [#:db{:id 101155069755483}]}]]

; İlginç nokta şu:
; Aslında Product'tan Order'a referans yok.
; Ama sanki varmış gibi :order/product referansını tersten kullandık

; implementasyon tarafında pull api kullanmasak doğrudan data patternlarla da yapabilirdik
(d/q
  '[:find ?product ?order
    :where
    [?product :product/id ?pid]
    [?order :order/product ?product]]
  db)
;=>
;[[92358976733268 87960930222173]
; [92358976733267 87960930222172]
; [92358976733266 87960930222174]
; [92358976733267 87960930222175]
; [92358976733266 96757023244376]
; [92358976733268 101155069755482]
; [92358976733267 101155069755481]
; [92358976733269 101155069755483]]

; Bu formatta sorgulayınca her bir Product'ın içinde bulunduğu Siparişi getiriyor
; Ama flat (tabular) formatta getirdi
; İlkinde pull API'daysa nested map formatta getirmişti.

; Mesela her ürünün içinde bulunduğu siparişlerin miktarlarını bulalım
(d/q
  '[:find (pull ?e [:product/id {:order/_product [:order/size]}])
    :where
    [?e :product/name]]
  db)
;=>
;[[{:product/id 1, :order/_product [#:order{:size 4} #:order{:size 6}]}]
; [{:product/id 2, :order/_product [#:order{:size 4} #:order{:size 3} #:order{:size 7}]}]
; [{:product/id 3, :order/_product [#:order{:size 5} #:order{:size 2}]}]
; [{:product/id 4, :order/_product [#:order{:size 3}]}]]
