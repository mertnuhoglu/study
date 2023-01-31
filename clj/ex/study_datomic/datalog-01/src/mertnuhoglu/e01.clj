(ns mertnuhoglu.e01)

; Barış'la Datomic Egzersizleri
; Tarih: 20230131
; rfr: video/20230131-mert-clj-egzersiz-13.mp4

; Çalışma sıralaması:
; Norbert Wojtowicz - Modeling your domain (Lambda Days 2016) - YouTube <url:file:///~/prj/study/logbook/log_20220927.md#r=g13409>
;	[Learn Datalog Today!](http://www.learndatalogtoday.org/)
; Lab: Assertion Tutorial <url:file:///~/prj/study/clj/datomic_lab_assertion_tutorial.md#r=g13506>
;	Datomic Kod Okumalarından Notlar  <url:file:///~/projects/study/logbook/log_20221119.md#r=g13532>
;	/Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic/datomic_01b.clj
;	Connect to Sample Database <url:file:///~/prj/study/clj/articles-datomic.md#r=g13507>
;	Datomic Documentation <url:file:///~/prj/study/logbook/log_20221107.md#r=g13500>
;	REBL ile datomic verilerini dolaşma  <url:file:///~/prj/study/clj/datomic.otl#r=g13523>
;	Lab: Assertion Tutorial <url:file:///~/prj/study/clj/articles-datomic.md#r=g13506>
;	Code - Assertion and Read Tutorial <url:file:///~/prj/study/clj/articles-datomic.md#r=g13509>
;	Example: Day of Datomic Cloud Sample Data <url:file:///~/prj/study/clj/articles-datomic.md#r=g13521>
;	Article: mbrainz tutorial <url:file:///~/prj/study/clj/articles-datomic.md#r=g12871>
;	/Users/mertnuhoglu/projects/study/clj/ex/study_datomic/datalog-01/README.md

; Önce yukarıdaki sıralamayla giderseniz daha pratik olur öğrenmek zannediyorum

; benim kod örneklerinden datomic_01b.clj en kolay başlangıç örneği olabilir
; burada diğer veritabanı sunucularından farklı olarak,
; ayrı bir prosesste bir veritabanı sunucusu başlatmanıza gerek yok
; örneğin, mysql ile çalışırken önce mysql sunucusunu başlatırsın (server)
; sonra kendi kodundan bu sunucuya bağlanırsın (client)

; datomic'te de böyle yapabilirsin
; alternatif olarak, doğrudan programın içinde de başlatabilirsin datomic sunucusunu 
; sunucu olarak da memory storage denilen bir seçenek var
; bununla hiç uğraşmadan direk veritabanı sunucusunu başlatabiliyorsun program içinde

; önce kurulum için:
; [Local Dev and CI with dev-local | Datomic](https://docs.datomic.com/cloud/dev-local.html)

;  '[:find ?sku
;    :where
;    [?e :inv/sku "SKU-42"]
;    [?e :inv/color ?color]
;    [?e2 :inv/color ?color]
;    [?e2 :inv/sku ?sku]]
; bu sorgu cümlesini okuyalım
;    [?e :inv/sku "SKU-42"]
; sku = SKU-42 olan objeleri (satır, entity) bu entityleri ?e değişkenine koy
;    [?e :inv/color ?color]
; bu bulduğun ?e objelerinin color değerini bul ve bu değeri ?color değişkenine koy
;    [?e2 :inv/color ?color]
; bu bulduğun ?color (blue) rengine sahip olan diğer objeleri bul ve bunları ?e2 değişkenine koy
;    [?e2 :inv/sku ?sku]]
; bu bulduğun objelerin sku değerlerini bul ve ?sku değişkenine koy
;  '[:find ?sku
; bu ?sku değişkenindeki değerleri listele

; [e a v]
; [entity attribute value]
; entity: her bir satırın id değeri oluyor
; attribute: bir kolona denk geliyor
; value: bir entitynin bir kolonunun değeri oluyor
; e :inv/color :inv/size :inv/sku :inv/type)
; 0 :red       :small    "SKU-0"  :shirt
; e: 0
; a: :inv/color
; v: :red

;    [?e :inv/sku "SKU-42"]
; bu sorgu cümleciğiyle ben şu satırı buldum:
; 42 :blue :large "SKU-42" :dress
; ben buradaki 42 id değerini ?e değişkeni içine koyuyorum
; ?e = 42
;    [?e :inv/color ?color]
; bu 42 satırının :inv/color atributundaki değerini buluyorum?
; :blue
; ?color = :blue
; bu değeri ?color değişkeni içine koyuyorum
;    [?e2 :inv/color ?color]
; şimdi bu cümlecik aslında şu şekle geliyor:
;    [?e2 :inv/color :blue]
; çünkü ?color = :blue olarak daha önceden belirlenmiş durumda
; q: madem ?color değişkeni içini doldurduk, neden ?e2 diye yeni bir değişken oluşturuyoruz?
; ?e2 yerine :color değişkenini kullansam o zaman şöyle olurdu
;    [?e2 :inv/color ?color]
; =>
;    [:blue :inv/color ?x]
; datalog sorgu cümleciklerinin ilk değeri entity id olmalı. fakat :blue bir entity id değil.

;    [?e2 :inv/color ?color]
; ?color = :blue olduğundan, datomic bu sorgu cümleciğini şuna çevirir:
;    [?e2 :inv/color :blue]
; bu sorgu cümleciği ne anlama geliyor?
; :inv/color atributunun değeri :blue olan tüm satırları bul ve bu satırların entity id'lerini ?e2 içine koy

; başındaki `?` bunun bir değişken olduğu anlamına geliyor

