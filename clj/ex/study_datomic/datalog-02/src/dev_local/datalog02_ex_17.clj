(ns dev-local.datalog02-ex-17)

; Barış'la Datomic Çalışmaları
; Tarih: 20230207
; rfr: video/20230207-mert-clj-egzersiz-23.mp4

; Konu: Transaction Functions

; [Retract | Datomic](https://docs.datomic.com/cloud/tutorial/retract.html)

(require '[datomic.client.api :as d])
(use '[dev-local.e04 :only [conn] :as e04])
(require '[dev-local.e05 :as e05])
(def db (d/db conn))

; Datomice veri kaydetmenin iki farklı yolu var.
; Şu ana kadar hep düz map objelerini kaydetmiştik.
; Örneğin:
(def product-list
  [{:product/id 5
    :product/name "Silgi"
    :product/color :color/blue}])
(d/transact conn {:tx-data product-list})

; Başka bir yol da :db/add adı verilen transaction function kullanmaktır
; Burada "foo" tempid sayılıyor.
(def product-list-2
  [[:db/add "foo" :product/id 6]
   [:db/add "foo" :product/name "Boya"]
   [:db/add "foo" :product/color :color/red]])
(d/transact conn {:tx-data product-list-2})

(def db (d/db conn))
(d/q
  '[:find (pull ?product [*])
    :where
    [?product :product/id]]
  db)
;=>
;[[{:db/id 92358976733274,
;   :product/name "Silgi",
;   :product/color #:db{:id 74766790688846, :ident :color/blue},
;   :product/id 5}]
; [{:db/id 96757023244370,
;   :product/name "Kalem",
;   :product/color #:db{:id 74766790688844, :ident :color/red},
;   :product/id 1}]
; [{:db/id 96757023244371,
;   :product/name "Kalem",
;   :product/color #:db{:id 74766790688846, :ident :color/blue},
;   :product/id 2}]
; [{:db/id 96757023244372,
;   :product/name "Defter",
;   :product/color #:db{:id 74766790688844, :ident :color/red},
;   :product/id 3}]
; [{:db/id 96757023244373,
;   :product/name "Defter",
;   :product/color #:db{:id 74766790688845, :ident :color/green},
;   :product/id 4}]
; [{:db/id 101155069755483,
;   :product/name "Boya",
;   :product/color #:db{:id 74766790688844, :ident :color/red},
;   :product/id 6}]]

; db/retract
; Eğer herhangi bir atributun değerini değiştirmek veya iptal etmek istiyorsak da iki yol var:
; Yeni düzeltilmiş veriyle objeyi tekrar kaydetmek
(def product-list-3
  [{:product/id 5
    :product/name "Silgili Kalem"
    :product/color :color/blue}])
(d/transact conn {:tx-data product-list-3})
(def db (d/db conn))
(d/q
  '[:find (pull ?product [*])
    :where
    [?product :product/id]]
  db)
;[[{:db/id 92358976733274,
;   :product/name "Silgili Kalem",
;   :product/color #:db{:id 74766790688846, :ident :color/blue},
;   :product/id 5}]]

; Başka bir yol da :db/retract adı verilen transaction function kullanmaktır.
(def product-list-4
  [[:db/retract [:product/id 6] :product/name "Boya"]
   [:db/add "datomic.tx" :db/doc "Ürün ismi yanlış girilmiş"]])
(d/transact conn {:tx-data product-list-4})
(def db (d/db conn))
(d/q
  '[:find (pull ?product [*])
    :where
    [?product :product/id]]
  db)
;[{:db/id 101155069755483, :product/color #:db{:id 74766790688844, :ident :color/red}, :product/id 6}]

; Implicit Retract
; Yukarıdaki örnekte :db/retract yapmak yerine doğrudan doğru veriyi yazarak da eski veriyi temizleyebiliriz
(def product-list-5
  [[:db/add [:product/id 6] :product/name "Pastel Boya"]
   [:db/add "datomic.tx" :db/doc "Ürün ismi yanlış girilmiş"]])
(d/transact conn {:tx-data product-list-5})
(def db (d/db conn))
(d/q
  '[:find (pull ?product [*])
    :where
    [?product :product/id]]
  db)
;[{:db/id 101155069755483,
;  :product/name "Pastel Boya",
;  :product/color #:db{:id 74766790688844, :ident :color/red},
;  :product/id 6}]

; [:db/add ...] ile kullanıma list formu
; {..} ile veri kaydetmeye map formu deniyor
; rfr: https://docs.datomic.com/cloud/tutorial/assertion.html#list-and-map-forms
