(ns e26)

(require '[clojure.string :as str])

; Tarih: 20230413
; Konu: Hüseyin Code Review: Debug Etme
; rfr: d08.clj
; Video: 20230420-mert-clj-57-d08-refactoring-debug.mp4

; Hüseyin'in Kodu:

; Tarih: 20230413

;Girdi:
;
;```clojure
;{1 {:id 1 :name "ali" :surname "veli"}
; 2 {:id 2 :name "batu" :surname "can"}}
;```
;
;Arama anahtar kelimesi:
;
;```
;"a"
;```
;
;Bu anahtar kelimeyi tüm property'lerde arayın. Eşleşen (match eden) property'lerin objelerini dönün.
;
;Çıktı:
;
;```clojure
;{1 {:id 1 :name "ali" :surname "veli"}
; 2 {:id 2 :name "batu" :surname "can"}}
;```

(comment
  (def x {1 {:id 1 :name "ali" :surname "veli"}
          2 {:id 2 :name "batu" :surname "can"}})
  (def x1 {:id 1 :name "ali" :surname "veli"})

  (defn filter-map-by-str [data s & fields]
    (into (empty data)
      (filter (fn [[_k v]]
                (some #(str/includes? (% v) s) fields)))
      data))
  ;src -> https://stackoverflow.com/questions/75895975/how-to-filter-values-on-maps-and-return-results

  (filter-map-by-str x "a" :id :name :surname)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

  (into (empty x) x)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}
  (filter (fn [[_k v]]
            (some #(str/includes? (% v) "a") '(:id :name :surname)))
    x)
  ;=> ([1 {:id 1, :name "ali", :surname "veli"}]
  ; [2 {:id 2, :name "batu", :surname "can"}])
  (some #(str/includes? % "a") '(:id :name :surname))
  ;=> true
  ; end
  ,)

;; Sıfırdan Debug Edelim Kodu:

(def x {1 {:id 1 :name "ali" :surname "veli"}
        2 {:id 2 :name "batu" :surname "can"}})
(def x1 {:id 1 :name "ali" :surname "veli"})

(defn filter-map-by-str [data s & fields]
  (into (empty data)
    (filter (fn [[_k v]]
              (some #(str/includes? (% v) s) fields)))
    data))

(comment
  (filter-map-by-str x "a" :id :name :surname)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}
  (filter-map-by-str x "ali" :id :name :surname)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}}
  (filter-map-by-str x "batu" :id :name :surname)
  ;=> {2 {:id 2, :name "batu", :surname "can"}}

  ; filter-map-by-str fonksiyonunu debug edeceğiz
  ; önce bu fonksiyonun argümanlarını bizim girdi verilerimizle uyumlu hale getirelim
  (def data x)
  (def s "ali")
  (def fields '(:id :name :surname))

  ; şimdi dışarıdan içeri doğru gidelim
  ; ilk çalışan ifade into ifadesi. onu test edelim.
  (into (empty data)
    (filter (fn [[_k v]]
              (some #(str/includes? (% v) s) fields)))
    data)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}}

  ; şimdi bu ifadeyi bir adım ayıklayalım
  (empty data)
  ;=> {}
  (filter (fn [[_k v]]
            (some #(str/includes? (% v) s) fields)))
  ;=> #object[clojure.core$filter$fn__5889 0x4f3ccad7 "clojure.core$filter$fn__5889@4f3ccad7"]

  ; burada into'ya 3 tane arg vermişiz
  ; 1. arg: {}
  ; 2. arg: filter ifadesi
  ; 3. arg: data

  ; into'nun dokuna bakalım
  (def xform
    (filter (fn [[_k v]]
              (some #(str/includes? (% v) s) fields))))
  (defn xform2 [m]
    (filter
      (fn [[_k v]]
        (some #(str/includes? (% v) s) fields))
      m))
  (xform2 data)
  ;=> ([1 {:id 1, :name "ali", :surname "veli"}])

  ; şimdi buraya bir ara verelim
  ; yukarıdaki kodu transducer olmadan yazalım,
  ; sonra debug işlemimize devam edelim

  ; refactoring:
  ; şimdi transducer dursun, ama filter anonim fonksiyon olmasın
  (defn filter-map-by-str2 [data s & fields]
    (into (empty data)
      xform
      data))
  (filter-map-by-str2 x "ali" :id :name :surname)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}}

  ; refactoring: bir adım daha refactor edelim
  ; xform içindeki filter'ın anonim fonksiyonuna da bir isim verelim
  ; genel kural: anonim fonksiyon yerine isimlendirilmiş fonksiyonlar kullan
  ; genel kural: bir fonksiyonun içinde lokal scope'da olmayan isimleri kullanmayalım
  ;(filter
  ;  (fn [[_k v]]
  ;    (some #(str/includes? (% v) s) fields)))
  ; burada `fields` değişkeni bu anonim fonksiyonun lokal scope'unda bulunmuyor
  ; dolayısıyla burada dışarıya bir bağımlılık var
  ; bu yüzden, bu fonksiyon side-effect içerir
  ; iki sadeleştirme yapalım:
  ; 1. side-effect kaldıralım
  ; 2. anonim fonksiyonlara isim verelim
  (defn f [[_k v] & fields]
    (some #(str/includes? (% v) s) fields))

  ; bu durumda f bir filter pred olarak kullanılamaz
  ; çünkü pred fonksiyonları tek argüman almalıdır

  ; bunun şeklini değiştirelim
  (defn f2 [fields [_k v]]
    (some #(str/includes? (% v) s) fields))

  (def f3
    (partial f2 '(:id :name :surname)))

  ; şimdi xform2 ifadesinin içinde bunu kullanalım
  (defn xform3 [m]
    (filter f3 m))
  (xform3 x)
  ;=> ([1 {:id 1, :name "ali", :surname "veli"}])

  ; bunun çalışmaması lazımdı, çünkü search string argümanını vermemiştim
  ; fakat s ismi daha önceden tanımlandığından, onu kullandı f2 fonksiyonu
  ; şimdi s objesini de argüman olarak paslayalım

  (defn f4 [fields s [_k v]]
    (some #(str/includes? (% v) s) fields))

  (def f5
    (partial f4 '(:id :name :surname) "ali"))
  (defn xform4 [m]
    (filter f5 m))
  (xform4 x)
  ;=> ([1 {:id 1, :name "ali", :surname "veli"}])

  ; şu an tamamen tüm fonksiyonlar yan etkisiz çalışıyor

  (into {} (xform4 data))
  ;=> {1 {:id 1, :name "ali", :surname "veli"}}

  ; şimdi bunların hepsini birleştirelim
  (defn filter-map-by-str3 [data s & fields]
    (let
      [f6 (partial f4 fields s)
       xform5 #(filter f6 %)]
      (into {} (xform5 data))))
  (filter-map-by-str3 x "batu" :id :name :surname)
  ;=> {2 {:id 2, :name "batu", :surname "can"}}

  ; refactoring
  (defn filter-map-by-str4 [data s & fields]
    (let
      [f6 (partial f4 fields s)
       xform5 #(filter f6 %)
       from (xform5 data)]
      (into {} from)))
  (filter-map-by-str4 x "batu" :id :name :surname)
  ;=> {2 {:id 2, :name "batu", :surname "can"}}

  ; şimdi isimlendirmeyi düzeltelim
  (defn g [fields substr [_id x]]
    (some #(str/includes? (% x) substr) fields))
  (defn filter-map-by-str5 [data search & fields]
    (let
      [g2 (partial g fields search)
       from (filter g2 data)]
      (into {} from)))
  (filter-map-by-str5 x "batu" :id :name :surname)
  ;=> {2 {:id 2, :name "batu", :surname "can"}}

  ; q: f6'yı let içinde tanımlamak yerine doğrudan f5'i neden kullanmadık?
  ; ödev egzersizi

  ;end
  ,)

; a02: anonim fonksiyonları ve side-effectleri temizledik:
(defn g [fields substr [_id x]]
  (some #(str/includes? (% x) substr) fields))
(defn filter-map-by-str5 [data search & fields]
  (let
    [g2 (partial g fields search)
     from (filter g2 data)]
    (into {} from)))
(filter-map-by-str5 x "batu" :id :name :surname)

; şimdi bunun debug işlemini yapalım

(comment
  (def data x)
  (def search "batu")
  (def fields '(:id :name :surname))
  (apply filter-map-by-str5 data search fields)
  ;=> {2 {:id 2, :name "batu", :surname "can"}}

  ; dışarıdan içeri gidelim
  ; ilk işlem:
  ;     [g2 (partial g fields search)
  (def g2
    (partial g fields search))
  (filter g2 data)
  ;=> ([2 {:id 2, :name "batu", :surname "can"}])


  ; şimdi filter'ın iterasyonlarına bakalım
  (identity data)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}
  ; 1. iterasyon
  (g2 [1 {:id 1, :name "ali", :surname "veli"}])
  ;=> nil
  ; 2. iterasyon
  (g2 [2 {:id 2, :name "batu", :surname "can"}])
  ;=> true

  ; şimdi g2 fonksiyonunun işleyişini debug edelim
  (g2 [2 {:id 2, :name "batu", :surname "can"}])
  ; denktir:
  (g fields search [2 {:id 2, :name "batu", :surname "can"}])
  ;=> true

  ; şimdi g fonksiyonunu işleyişini debug edelim
  (def x {:id 2, :name "batu", :surname "can"})
  (def substr "batu")
  (some #(str/includes? (% x) substr) fields)
  ;=> true

  ; şimdi some içindeki anonim fonksiyonun işleyişini debug edelim
  (#(str/includes? (% x) substr) :name)
  ;=> true
  (#(str/includes? (% x) substr) :id)
  ;=> false

  ;end
  ,)

; Fonksiyonları siz de bu şekilde debug edin
; Normalde üretim aşamasında programlama yaparken, bu seviyede debug etmeniz gerekmeyecek
; Şu an bunu öğrenmenizi derinleştirmek için yapıyoruz
; Çünkü debug ederken tüm zihninizdeki boşlukları doldurmak zorundasınız.