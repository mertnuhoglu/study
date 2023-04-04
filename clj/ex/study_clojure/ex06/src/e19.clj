(ns e19)

; Tarih: 20230403
; Konu: d01

; girdi:
[{:name "ali" :surname "veli"}
 {:name "batu" :surname "can"}]

; çıktı:
[["ali" "veli"]
 ["batu" "can"]]

(def v
  [{:name "ali" :surname "veli"}
   {:name "batu" :surname "can"}])

; imperatif paradigmada bu tarz problemleri loop ile yapardık
; fp'da loop neredeyse hiç kullanılmaz
; map, filter, reduce fonksiyonları ve bunların türevleri kullanılır

; burada map kullanabiliriz
; map: coll -> coll
; map'in girdi ve çıktı coll'ları aynı öğe sayısından oluşur

; bu tür algoritmaları rich comment yazarak tasarlayalım
; rich commentler kodun içinde dursun, silmeyelim algoritma bitse bile.
; bunlar aynı zamanda dokümantasyon görevi görür çünkü

(comment
  ; map kullanacağız
  ; 1. adım: map'in dokümantasyonuna bakalım
  ; bizim fn/map.clj dosyasındaki örnekleri inceleyelim

  ; (map f coll)
  ; map'in bu formunu kullanacağız
  #_(map f v)
  ; henüz f fonksiyonunu tanımlamadık
  ; önce f fonksiyonunu tanımlayalım

  ; f ne yapacak?
  ; her zaman algoritmalarımızı girdi ve çıktılar üzerinden dizayn edelim
  ; girdi:
  {:name "ali" :surname "veli"}
  ; çıktı:
  ["ali" "veli"]

  ; şimdi bu girdiye bir isim verelim
  (def m1 {:name "ali" :surname "veli"})

  ; henüz hala daha fonksiyonu yazmaya başlamadık
  ; ben şimdi bu m1 hashmapinin değerlerini tek tek alacağım
  ; a01: alternatif 01. doğrudan vals fonksiyonuyla yapalım
  (vals m1)
  ;=> ("ali" "veli")

  ; fakat bu seq (list) formunda, bizim bunu vektör formuna çevirmemiz lazım
  ; standart (en yaygın) yol: into fonksiyonu kullanmak
  (into [] (vals m1))
  ;=> ["ali" "veli"]

  ; şu ana kadar v vektörünün ilk öğesi (item) için data transformasyonu yaptık
  ; aynı transformasyon işlemlerini ikinci ve diğer öğeler için de yapacağız
  ; o zaman bu yaptığımız data transformasyon işlemlerini bir fonksiyonun içine gömeriz (encapsulate)

  (defn f [m]
    (into [] (vals m)))
  (f m1)
  ;=> ["ali" "veli"]

  ; tamam şimdi transformasyonu bir fonksiyon içine gömdük
  ; sıradaki adım:
  ; bu fonksiyonu girdi v vektörünün tüm öğeleri için tekrarlamak olacak
  ; bunu da map fonksiyonuyla yaparız
  (map f v)
  ;=> (["ali" "veli"] ["batu" "can"])

  ; en dıştaki veri yapısını seq'ten vektöre dönüştüreceğiz
  (into [] (map f v))
  ;=> [["ali" "veli"] ["batu" "can"]]

  ; problem çözüldü
  ,)

; q: yukarıdaki (defn f...) formu yerine doğrudan anonim fonksiyon tanımlayıp kullanabilirdik
; hangisi kanonik (canonical) formudur bunların?

; cevap:
; eğer fonksiyon kısaysa (okunması kolaysa) anonim kullanmayı tercih ederiz
; eğer fonksiyonu tek yerde kullanıyorsak anonim kullanabiliriz
; ama başlangıç aşamasında anonim kullanmamanızı tavsiye ederim, anlaması biraz daha zor olduğundan dolayı

; onun da örneğini yapalım
(comment
  (defn f [m]
    (into [] (vals m)))
  ; =>
  (fn [m]
    (into [] (vals m)))

  ; ama bunu başka bir yerde kullanmak istediğimde aynı fonksiyon tanım ifadesini yeniden yazmam gerekecek şimdi
  (into
    []
    (map
      (fn [m]
        (into [] (vals m)))
      v))
  ;=> [["ali" "veli"] ["batu" "can"]]

  ; end
  ,)

; rfr: /Users/mertnuhoglu/prj/study/clj/ex/study_clojure/ex06/src/fn/reduce_kv.clj > zihinsel-model-nedir
; kavramların net tanımlarını bilmek, zihinsel modelimizi güçlendirir
; bu sadece iletişim açısından değil,
; aynı zamanda bizim kendi iç anlayışımız için de çok değerli
; tavsiye:
; kodu okurken, hiçbir zaman zihninizde zamirler kullanmayın
; her zaman okuduğun her ifadeyi, ilgili gramer terimleri neyse, onları kullanarak içinizden okuyun.

; Retrospektif:
; 1. loop kullanmadık
; 2. atom kullanmadık
; 3. map, filter, reduce ve onların türevlerini kullandık
; 4. deklaratif bir program yazdık
; deklaratif vs imperatif
; imperatif programlar: bir işi nasıl yapılacağını adım adım tarif edersin
; deklaratif programlar: bir işin ne yapacağını tarif eder (girdi ve çıktılarını)

; şu ifadeyi bir inceleyelim bu deklaratif perspektiften:
(into
  []
  (map f v))
; burada into fonksiyonuna iki argüman veriyorum
; ilk argüman: boş vektör
; ikinci argüman: bir collection (from collectionı)
; from collectionı şu ifadeyle oluşturulmuş:
(map f v)
; burada v vektörünün tüm öğelerine f fonksiyonunu uygulamaşız
; başka bir deyişle aslında bir mapping (bağıntı) kurmuşuz şu girdi ve çıktı arasında:
; v_i -> f(v_i)
; v'nin her bir elemanını f(v_i)'ye map etmişiz
; peki f ne yapıyor?
(defn f [m]
  (into [] (vals m)))
; bir m hashmap'i alır girdi olarak
; bu hashmap'in değer öğelerini alıyor
; onları into ile bir vektöre dönüştürüyor

; girdi-çıktı mantığı da aslında bildiğimiz matematiksel fonksiyonlarla düşünmek gibidir
; daha cebirsel düşünüyorsun

; q: biz burada matematiksel düşünüyoruz, ama arka planda clojure bunları mekanik (fiziksel) olarak işliyor değil mi?
; aynen öyle.
; clojure'ın compilerı o işi yapıyor.
; dolayısıyla şunu diyebiliriz:
; imperatif programlama yaparken, compiler'ın yapması gereken hesaplama işlerini sen yapıyorsun


(assoc nil :key1 4)
; q01: bu niye nil verince yapıyor?
