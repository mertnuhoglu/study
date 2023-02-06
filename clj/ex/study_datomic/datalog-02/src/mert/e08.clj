(ns mert.e08)

; Barış'la Datomic Çalışmaları
; Tarih: 20230203
; rfr: video/20230203-mert-clj-egzersiz-17.mp4

; Konu: Parametrik Sorgularda Destructuring ve Binding

; Source: [Learn Datalog Today!](https://www.learndatalogtoday.org/chapter/3)

(require '[datomic.client.api :as d])
(use '[mert.e04 :only [conn] :as e04])
(use '[mert.e05 :as e05])

; En son yaptığımız parametrik sorguya bakalım:
(d/q
  '[:find ?e
    :in $ ?product-name
    :where
    [$ ?e :product/name ?product-name]]
  db "Kalem")
; Bu güzel
; Burada parametre olarak gönderdiğimiz değer primitif bir değer.
; Fakat bizim clojureda fonksiyonlara sadece primitif tipte değerler değil, kompozit (bileşik) türde değerler de gönderebiliyoruz.
; Tuples/Vector/List: [.. ..]
; Map
; Bunların bir kombinasyonu olabilir

; Bu tarz bir değer argüman olarak gönderileceği vakit destructuring yapılması gerekiyor.
; Klasik clojure destructuring sentaksı gibi, ama biraz farklılıklar var.
; [Clojure - Destructuring in Clojure](https://clojure.org/guides/destructuring)
; [Clojure Destructuring Tutorial and Cheat Sheet](https://gist.github.com/john2x/e1dca953548bfdfb9844)

; Tuple:
(d/q
  '[:find ?e
    :in $ [?product-name ?color]
    :where
    [?e :product/name ?product-name]
    [?e :product/color ?color]]
  db ["Kalem" :color/red])
;=> [[92358976733263]]
; Yani hem Kalem hem de kırmızı renkli olan varlıkları sorguladık

; Collection:
(d/q
  '[:find ?e
    :in $ [?product-name ...]  ; 👈 ... sembolü
    :where
    [?e :product/name ?product-name]]
  db ["Kalem" "Defter"])
;=> [[92358976733265] [92358976733266] [92358976733263] [92358976733264]]
; Kalem veya Defter olan varlıkları sorgulamış olduk

(d/q
  '[:find ?e
    :in $ [?color ...]
    :where
    [?e :product/color ?color]]
  db [:color/red :color/blue :color/green])
;=> [[92358976733265] [92358976733266] [92358976733263] [92358976733264]]

; SQL karşılığı:
; SELECT *
; FROM Product p
; WHERE p.color IN ("red", "blue", "green")

; Relations
(d/q
  '[:find ?e ?product-name ?color ?product-price
    :in $ [[?product-name ?product-price]]
    :where
    [?e :product/name ?product-name]
    [?e :product/color ?color]]
  db [["Kalem" 120] ["Defter" 250]])
;=>
;[[92358976733266 "Defter" 96757023244362 250]
; [92358976733263 "Kalem" 96757023244361 120]
; [92358976733264 "Kalem" 96757023244363 120]
; [92358976733265 "Defter" 96757023244361 250]]

; Relations - a set of tuples - are the most interesting and powerful of input types, since you can join external relations with the datoms in your database.
; Burada veritabanımızda olmayan yeni bir bilgiyi (product-price) sorgu sonuçlarımıza otomatikman join ettik.

; q: Neden böyle bir şey yapalım?
; En temellere geri dönelim.
; Bizim olayımız nedir kurumsal veya veritabanı bir yazılım yaparken, temel amacımız ne?
; Mesela Layermark'ın yaptığı yazılımları düşünelim.
; DCWater bir firmanın saha operasyonlarının yönetilmesi için yazılım sistemleri yapıyordu.
; Bu tarz sistemlere biz hep yazılım diyoruz, ama aslında doğru isim yazılım değil.
; Doğru isim, o yazılımın ne için kullanıldığını veya amacını tarif etmeli.
; Saha Operasyonlarının Yönetim Bilgi Sistemi
; Enformasyon Yönetim Sistemi
; Information Management System
; Information Technology > Yazılım
; Bizim esas amacımız, yazılım geliştirmek değil aslında.
; Bizim esas amacımız, insanların ihtiyaç duydukları bilgileri yönetmesini sağlamak.
; Esas: Bilgi Yönetim Sistemi
; Enformasyondan ve o enformasyonu yönetmekten bahsediyoruz.

; Problem şu ki, hiçbir veritabanı insanların tüm ihtiyacını karşılamaz.
; İlla ki bir yerde başka veritabanlarıyla veya başka bilgilerle birleştirmeniz gerekir, elinizdeki verileri.
; Dolayısıyla her zaman bizim enformasyon sistemimiz aslında başka sistemlerin bir alt parçası olacaktır.
; Dolayısıyla harici enformasyonla mevcut veritabanındaki verileri birleştirmek her zaman ihtiyaç duyulacak bir şeydir.

; join external relations
; relation: kelimesini görünce aklınıza tablo gelsin.
;

; Bindings

; rfr: Bindings <url:file:///~/prj/study/clj/datomic_documentation_rtc.md#r=g13542>
; rfr: [Bindings](https://docs.datomic.com/on-prem/query/query.html?search=Tuple%20Binding#bindings)

; Şimdi bu parametrelerin sorgu değişkenlerine bağlanması işine binding diyoruz.
(d/q
  '[:find ?e ?product-name ?color ?product-price
    :in $ [[?product-name ?product-price]]
    :where
    [?e :product/name ?product-name]
    [?e :product/color ?color]]
  db [["Kalem" 120] ["Defter" 250]])
; Örneğin burada
; "Kalem" değerini ?product-name değişkenine bağlıyoruz
; 120 değerini ?product-price değişkenine bağlıyoruz

;| Binding Form | Binds      |
;|--------------|------------|
;| ?a           | scalar     |
;| [?a ?b]      | tuple      |
;| [?a …]       | collection |
;| [ [?a ?b ] ] | relation   |

; İlk yaptığımız bağlama scalardı: ?a
(d/q
  '[:find ?e
    :in $ ?product-name
    :where
    [$ ?e :product/name ?product-name]]
  db "Kalem")
;=> [[92358976733266] [92358976733267]]

; Tuple: [?a ?b]
(d/q
  '[:find ?e
    :in $ [?product-name ?color]
    :where
    [?e :product/name ?product-name]
    [?e :product/color ?color]]
  db ["Kalem" :color/red])
;=> [[92358976733266]]

; Collection: [?a ...]
(d/q
  '[:find ?e
    :in $ [?product-name ...]  ; 👈 ... sembolü
    :where
    [?e :product/name ?product-name]]
  db ["Kalem" "Defter"])
;=> [[92358976733266] [92358976733267] [92358976733268] [92358976733269]]

; Relation: [ [?a ?b] ]
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
