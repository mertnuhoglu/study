(ns ex.e22)

; Tarih: 20230405
; Konu: d01 problemi
; Video: 20230405-mert-clj-egzersiz-54-veri-analiz-problemi-d01.mp4

; girdi:
[{:name "ali" :surname "veli"}
 {:name "batu" :surname "can"}]

; çıktı:
[["ali" "veli"]
 ["batu" "can"]]

; Dikkat ederseniz, girdi ve çıktı objeleri komentlenmemiş.
; Neden? Çünkü clojure'daki tüm data structurelar aynı zamanda geçerli birer clojure ifadesidir (expresion).

; İlk olarak girdi objemize bir isim verelim.
; yani def ile bir tanımlama yapalım.
; Hatırlatma: def imperatif dillerdeki assignment işlemine (statement) denk gelir.

(def v
  [{:name "ali" :surname "veli"}
   {:name "batu" :surname "can"}])

; java'da yapsaydık:
; List v = new ArrayList()    // assignment => def
; v.add(new HashMap().add("name", "ali").add("surname", "veli"))

; Dikkat:
; imperatif paradigmada bu tarz problemleri loop (for, while) ile yapardık
; FP'da loop neredeyse hiç kullanılmaz
; Onların yerine kendi içinde loop özelliği olan fonksiyonlar kullanırız:
; Başlıca fonksiyonlar: map, filter, reduce fonksiyonları ve bunların türevleri kullanılır

; Aslında tüm bu döngüsel (iteratif) fonksiyonlar, aslında reduce'un türevleridir.
; Phillip Wadler, Introduction to Functional Programming kitabı bu türetmeleri detaylı anlatır.

; Bunlarla ilgili bizim önceki videolarımızı ve örnek kodlarımızı bulabilirsiniz:
; fn/map.clj
; fn/reduce.clj
; fn/filter.clj

; burada map kullanabiliriz
; map: coll -> coll   (signature, type signature, imza)
; f:   (in) -> (out)
; map, girdi olarak coll alır. çıktı olarak da coll verir.
; map'in girdi ve çıktı coll'ları aynı öğe sayısından oluşur

; #stnd: bu tür data transformasyon algoritmalarını rich comment yazarak tasarlayalım
; rich commentler kodun içinde dursun, siz kodu bitirseniz bile, yazdığınız rich commentleri silmeyin.
; bunlar aynı zamanda dokümantasyon görevi görür çünkü
; Bu yaklaşıma da Literate Programming yaklaşımı deniyor.
; Bunun da kaynağı: Donald Knuth. TeX, Latex onun eserleri.

; rich comment bir comment bloku
(comment
  ; burada istediğim debug işlemlerini yapabilirim
  (def x "merhaba")
  ;=> #'e22/x
  ; anlamı: e22/x diye bir isim oluşturdum. bu ismi sana dönüyor.
  (str x " hello")
  ;=> "merhaba hello"

  (def x2 "hello")
  ; end
  ,)

; rich comment'in dışına çıktığımızda, comment'in içindeki hiçbir şeye erişemeyiz.
; scope'ları birbirinden farklı.
#_(str x2)
;Unable to resolve symbol: x2 in this context

; bunun bize faydası şu:
; kodu yazarken, comment bloklarını kodun içinde muhafaza edebiliriz.
; bu comment blokundaki ürettiğimiz isimler asla dışarıdaki isimlerle çatışmaz
; çünkü clj o dosyayı load/eval ederken, comment bloklarının içlerini eval etmez.

(comment
  ; map kullanacağız
  ; neden map kullanıyoruz?
  ; çünkü girdi ve çıktı veri yapılarımız birbirine benzer şekilde
  ; girdimiz: iki öğeli bir vektör
  ; çıktımız: iki öğeli bir vektör
  ; fark nerede?
  ; girdideki: öğeler birer map
  ; çıktıdaki: öğeler birer vektör

  ; 1. adım: map'in dokümantasyonuna bakalım
  ; bizim fn/map.clj dosyasındaki örnekleri inceleyelim

  ; (map f coll)
  ; map'in bu formunu kullanacağız
  #_(map f v)

  ; henüz f fonksiyonunu tanımlamadık
  ; önce f fonksiyonunu tanımlayalım

  (map inc [1 2 3])
  ;=> (2 3 4)
  ; bu aslında şuna birebir  denktir:
  '((inc 1) (inc 2) (inc 3))
  ;=> (2 3 4)

  ; aslında map şöyle bir şey yapar:
  ; coll'daki öğelere x1 x2 ... diyelim
  ; x1 -> f(x1)
  ; x2 -> f(x2)
  ; ...
  ; tüm sonuçları bir liste olarak dönüyor
  ; (f(x1) f(x2) ... f(x_n))
  ; x1'i f(x1)'e dönüştürüyor: buna biz mapping diyoruz
  ; x1'i f(x1)'e map ediyoruz.
  ; map etmek: bağlamak demek

  ; girdi:
  ;[{:name "ali" :surname "veli"}
  ; {:name "batu" :surname "can"}]

  ; çıktı:
  ;[["ali" "veli"]
  ; ["batu" "can"]]

  ; ilk adımımız ne olacak?
  ; girdi coll'daki ilk öğeyi alalım
  ; {:name "ali" :surname "veli"}
  ; çıktı coll'daki ilk öğeye çevirelim
  ; ["ali" "veli"]
  ; ilk etapta sadece bu problemi çözelim
  ; burada girdimize x objesi diyelim
  ; x -> f(x)
  ; x = {:name "ali" :surname "veli"}
  ; f(x) = ["ali" "veli"]

  ; f ne yapacak?
  ; her zaman algoritmalarımızı girdi ve çıktılar üzerinden dizayn edelim
  ; girdi:
  {:name "ali" :surname "veli"}
  ; çıktı:
  ["ali" "veli"]

  ; şimdi bu girdiye bir isim verelim
  (def x {:name "ali" :surname "veli"})

  ; henüz hala daha fonksiyonu yazmaya başlamadık
  ; ben şimdi bu x hashmapinin değerlerini tek tek alacağım
  ; a01: alternatif 01. doğrudan vals fonksiyonuyla yapalım
  (vals x)
  ;=> ("ali" "veli")

  ; fakat bu seq (list) formunda, bizim bunu vektör formuna çevirmemiz lazım
  ; data structure'lar arasındaki dönüşümleri into fonksiyonuyla yaparız
  ; standart (en yaygın) yol: into fonksiyonu kullanmak
  (into [] (vals x))
  ;=> ["ali" "veli"]

  ; şu ana kadar v vektörünün ilk öğesi (item) için data transformasyonu yaptık
  ; aynı transformasyon işlemlerini ikinci ve diğer öğeler için de yapacağız
  ; o zaman bu yaptığımız data transformasyon işlemlerini bir fonksiyonun içine gömeriz (encapsulate)

  (defn f [m]
    (into [] (vals m)))
  (f x)
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

(defn f [m]
  (into [] (vals m)))
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
