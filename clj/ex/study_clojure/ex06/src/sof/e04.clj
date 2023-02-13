(ns sof.e04)

; Barış'la Clojure Veri Analizi Çalışmaları
; Tarih: 20230213
; rfr: video/20230213-mert-clj-egzersiz-33.mp4

; [In Clojure is there an easy way to convert between list types? - Stack Overflow](https://stackoverflow.com/questions/5088803/in-clojure-is-there-an-easy-way-to-convert-between-list-types)

(into [] '(1 2 3 4)) ; ==> [1 2 3 4]         "have a lazy list and want a vector"
(into #{} [1 2 3 4]) ; ==> #{1 2 3 4}        "have a vector and want a set"
(into {} #{[1 2] [3 4]}) ; ==> {3 4, 1 2}    "have a set of vectors want a map"
(into #{} [{1 2} {3 4}]) ; ==> #{{1 2} {3 4}} "have a vector of maps want a set of maps"

; #nclk/çok-önemli: into
; #tpc/conversion

; #stnd: Çok kullandığım etiketleri (tag) kısaltarak kullanmayı tercih ediyorum.
; Sebebi şu: böylece "standart" kelimesinin normal kullanımıyla, etiket kullanımını ayırmış oluyorum.
; Kısaltmayla arama yaptığımda direk çıkıyor.
; 2. Böylece sınırlı sayıda etiket tutuyorum. Ve o etiketleri de standartlaşmış oluyorum.
; Etiketlemenin en büyük sıkıntısı, çok fazla etiket olabiliyor zaman içinde.
; O zaman da bu sistemin kullanışlılığı azalıyor.

; #stnd: Öncelik etiketleri için #nclk/ namespaceini kullanalım.
; Konu açıklamaları için de #tpc/ ns'sini kullanalım.
; nclk: öncelik
; tpc: topic (konu başlığı) anlamına gelir

(into [] '(1 2 3 4)) ; ==> [1 2 3 4]         "have a lazy list and want a vector"

; lazy list ne demek? #nclk/önemli #zihinsel-model/sezgisel
; her şeyin lazy'si olabilir
; lazy: bir veri yapısının içindeki öğelerin, yavaş yavaş gelmesi anlamına gelir
; yukarıda bütün öğeler en baştan hazır, o yüzden bu bir lazy list değil
; mesela bir veritabanından sorgu yaptık ve bir resultset objesi döndü
; bu resultset objesi genellikle lazy olur
; yani en baştan o objenin içindeki öğeler hazır değildir, zaman içinde gelir
; faydası: performans
; şöyle düşün: 100 M kayıttan oluşan bir resultset aldın
; bu senin belleğini tamamen kaplar
; ama eğer lazy bir resultset olursa, o zaman sadece ihtiyaç duyduğunda o bilgi sana gelir
; anında belleğini işgal etmez

(into {} #{[1 2] [3 4]}) ; ==> {3 4, 1 2}    "have a set of vectors want a map"
; #nclk/çok-önemli

; burada bir setin içinde ikililer (2-tuple) var.
; bu ikililer set'ini map'e çeviriyoruz
; o ikililerin birinci elemanı key oluyor
; ikinci elemanı value oluyor

; #nclk/önemli #zihinsel-model/sezgisel
; set ve map'in öğelerinin sıralaması bilinmez
; dolayısıyla sıralama önemliyse, düz set veya düz map kullanmamalısın
; sorted-set veya sorted-map kullanmalısın

; Veri yapılarını ve algoritmaları öğrenmek için animasyonlu görsel eğitimler çok işimizi kolaylaştırır
; Bu hem daha sezgisel bir anlayış oluşturmamızı sağlar
; rfr: [visualising data structures and algorithms through animation - VisuAlgo](https://visualgo.net/en)
; Akademik tarafına ağırlık vermek isteyen insanlar, paradoksal bir şekilde görsel tarafı ihmal eder
; animasyonlarla değil, işin mekanik algoritmasıyla öğrenmeye çalışır
; görsel şekilde öğrenmek hem daha kolaydır, zihinsel modellerini daha iyi geliştirir
; Benoit Mandelbrott: herhangi bir denklem gördüğüm ilk yaptığım şey onu zihnimde görselleştiririm

; a02: into yerine her bir veri yapısının kendi constructor fonksiyonu da bulunur
; Terim: constructor bir objeyi oluşturmak için kullanılan standart fonksiyon

;For vectors there is the vec function

(vec '(1 2 3))
;[1 2 3]

;For lazy sequences there is the lazy-seq function

(lazy-seq [1 2 3])
;(1 2 3)

;For converting into sets, there is the set function

(set [{:a :b, :c :d} {:a :b} {:a :b}])
;#{{:a :b} {:a :b, :c :d}}

; Literal notasyon:
; Veri yapılarını istersek constructor fonksiyonuyla oluşturursun, istersen literal notasyonla
[1 2 3] ; literal notasyon örneği
(vec '(1 2 3)) ; constructor örneği

; map için: hash-map fonksiyonu
(hash-map :a 1 :b 2)
;=> {:b 2, :a 1}

; tarihsel olarak map kelimesinden önce daha ziyade hash kelimesi eskiden daha çok kullanılırdı

(hash-map :a :a)
;=> {:a :a}

; aynı keyden iki tane yaparsak ne olur?
(hash-map :a 1 :a 2)
;=> {:a 2}

#_{:a 1 :a 2}
;Duplicate key: :a
; demek ki, hash-map ile `{}` birbirinin aynı fonksiyonlar değil.

; #stnd: Bir fonksiyonun nasıl çalıştığını anlamanın en iyi yolu, farklı senaryolarda deneme yanılma yapmaktır.
; Aslında öğrenmenin en iyi yolu, genel olarak, deneme-yanılma yöntemidir.
; Bilimsel deney yöntemi (empirism, ampirizm, objective empirism, ...)
; Bilimsel deney dediğimiz şey, deneme-yanılmanın daha sistemli ve kurallı bir halidir.
; Ama özünde ikisi de aynı öze dayanır.
; Karl Popper'ın onun bahsettiği bir yaklaşıma dayanır. Falsifiability (yanlışlanabilir) deneyler yapmak.
