(ns fn.reduce-kv)

; rfr: video/20230217-mert-clj-egzersiz-42.mp4
; rfr: video/20230218-mert-clj-egzersiz-43.mp4

; (reduce-kv f init coll)
; Reduces an associative collection. f should be a function of 3
; arguments. Returns the result of applying f to init, the first key
; and the first value in coll, then applying f to that result and the
; 2nd key and value, etc. If coll contains no entries, returns init
; and f is not called. Note that reduce-kv is supported on vectors,
; where the keys will be the ordinals.

; #fkr: Çok kullandığımız önemli fonksiyonlar var: reduce, map, filter, into, remove,
; associative destructuring, vs.
; Önemli olan kavramları, boş zamanlarımda kendi kendime tanımlarım.
; O kavramı tanımlayacak en özlü, kısa ve net tanım ifadesini bulmaya çalışırım.
; Bu özellikle kavramların zihinsel modelini inşa etmek açısından çok işe yarıyor.
; Ayrıca terimlerde çok daha netleşiyor bu sayede.

; İlk bilgisayarı aldığımda 14 yaşımda, şöyle bir şey yapmıştım kendi kendime:
; Klavyedeki tüm tuşları bulundukları pozisyonla beraber kağıda yazdım.
; Sonra onları ezberledim.
; Sonra gördüğüm her kelimeyi (kitap okurken, dolaşırken vs.)
; o zihnimde, ben bu kelimeyi nasıl yazardım, otomatik zihinsel egzersiz yapmaya başladım.
; Bunu bir hafta yapınca, her şey zihnime oturmuştu. Refleks olmuştu her şey.
; Bunun bir başka çağrışım: Mental experiment (Einstein'ın kullandığı bir yöntem)

; Zihinsel model dediğimiz şey, aslında gerçek hayattaki bir olayın, en öz ve sade modelidir.
; #terim: model = gerçek hayattaki bir şeyin temsilidir
; Zihinsel model, bir şeyin özüne inebilmektir. Dolayısıyla minimal hale getirmektir.
; Yaman Barlas'ın anlatımıyla:
; Bir karikatür gibidir.
; Charlie Chaplin'in bir karikatür çizimini alalım:
; https://thumbs.dreamstime.com/z/charlie-chaplin-silhouette-hat-isolated-white-vector-illustration-man-s-hair-mustache-like-vintage-hairdresser-style-137799252.jpg
; Bu resimde çok az detay var
; ama sen bu resme bakar bakmaz, kime ait olduğunu anlıyorsun
; çok az spesifik özelliğin belirtilmesi, senin işaret edilen şeyi anlamanı sağlıyor.
; Ne kadar az spesifik özellikle, sen işaret edilen şeyi anlarsan, o kadar iyi bir model kurmuşsun demektir.
; Einstein: Everything should be made as simple as possible, but not simpler.

; TODO: datomic'teki sözlü Türkçe anlatımın bir benzerini bu clj fonksiyonları için de yapalım

; [reduce-kv - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/reduce-kv)

; e01
;Let's assume you want to apply a function to a vector of maps,
;
;Input: [{:a 1 :b 2} {:a 3 :b 4}]
;
;such that all vals are incremented by 1.
;
;Result: [{:a 2 :b 3} {:a 4 :b 5}]
;
;An easy way to do so is using reduce-kv,

(def vector-of-maps [{:a 1 :b 2} {:a 3 :b 4}])

(defn update-map [m f]
  (reduce-kv
    (fn [m k v]
      (assoc m k (f v)))
    {}
    m))

(map #(update-map % inc) vector-of-maps)
;=> ({:b 3, :a 2} {:b 5, :a 4})

; e02
;; Swap keys and values in a map
(reduce-kv #(assoc %1 %3 %2) {} {:a 1 :b 2 :c 3})
;{1 :a, 2 :b, 3 :c}

(comment
  (assoc {} 1 :a)      ; ilk tur (iterasyon)
  ;=> {1 :a}
  (assoc {1 :a} 2 :b)  ; ikinci tur (iterasyon)
  ;=> {1 :a, 2 :b}
  (assoc {1 :a, 2 :b} 3 :c)  ; 3. tur
  ;=> {1 :a, 2 :b, 3 :c}

  ; ilk tur ve ikinci tur arasındaki argümanlar açısından fark nedir?
  ; ilk turda f'e 3 argüman veriyoruz.
  ; ikinci turda da f'e 3 argüman veriyoruz.
  ; her iki turda da 2. ve 3. argümanlar aynı yerden geliyor
  ; yani sıralı ikililerin k ve v parçaları
  ; fakat ilk argüman birinci tur ile sonraki turlar arasında farklı
  ; ilk turda, ilk argüman init argümanı oluyor
  ; yani reduce-kv çağrısındaki 2. argüman
  ; fakat ikinci ve sonraki turlarda, f'in ilk argümanı, artık init değil
  ; bir önceki turdaki f çağrısının sonucunda dönen değerdir

  ; (reduce-kv f                 init coll           )
  ; (...       #(assoc ...)      {}   {:a 1 :b 2 ...})
  ,)

; e03
;; Swap keys with values, only if values are not empty,
;; while turning values into proper keys

(defn swap [someMap]
  (reduce-kv (fn [m k v]
               (if (empty? v) m (assoc m (keyword v) (name k)))) {} someMap))

(swap {:a "ali" :b "baba"})
;=> {:ali "a", :baba "b"}
