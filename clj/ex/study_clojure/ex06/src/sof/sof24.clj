(ns sof.sof24)

; rfr: video/20230222-mert-clj-egzersiz-48.mp4

; [Idiomatic clojure map lookup by keyword - Stack Overflow](https://stackoverflow.com/questions/7034803/idiomatic-clojure-map-lookup-by-keyword)

(def m {:a 1 :b 2})

; a01:
(:a m)
;=> 1
; bu şekilde hotspot (Java uygulamalarını çalıştıran yazılım) daha iyi kodu optimize eder,
; çünkü :color sabittir, my-car ise değil

; a02:
(m :a)
;=> 1

; olur da verdiğim m map objesi eğer nilse, bu durumda hata verir
#_(nil :a)
;ERROR: can't call nil
(:a nil)
;=> nil
; ama (:a nil) formundaki kullanım hata vermez

;For me, the most compelling reason: (:color nil) returns nil, whereas (nil :color) throws an Exception. –

(:a nil)
;nil
#_(nil :a)
;ERROR: can't call nil

; a03

;From the library coding standards:
;
;Use keyword-first syntax to access properties on objects:
; #trm: property (object) = attribute (object, tablo) = column (tablo) = key (map)
; aslında bu terimlerin hepsi birbirine denktir
; dolayısıyla birbiri yerine de geçer çoğu zaman

; eğer obje gibi kullandığımız bir mapin propertylerine erişmek istiyorsak, keyword fonksiyonu kullan

;(:property object-like-map)

;Use collection-first syntax to extract values from a collection (or use get if the collection might be nil).

; eğer collection gibi kullandığımız bir mapin değerlerine erişmek istiyorsan, map fonksiyonu kullan

;(collection-like-map key)
;(get collection-like-map key)

; q: collection gibi map ile obje gibi map arasındaki fark ne?
; obje deyince genelde şunu anlarız: bir tablodaki bir satır gibi bir şey
; collection deyince genelde, bir liste gibi bir şey anlarız

; collection gibi map'e bir örnek: fulcro tarzı veritabanı örnekleri buna girer:
(def students
  {101 {:name "ali" :id 101}
   102 {:name "ayşe" :id 102}
   103 {:name "nazım" :id 103}})
(students 102)
;=> {:name "ayşe", :id 102}
; bu map bir nevi collection gibidir (liste)
; bu mapin her bir öğesi, bir listedeki bir satır gibidir

(def student
  {:name "ali" :id 101})
; objeye benzeyen bir map örneği
(:name student)
;=> "ali"

; I interpreted an "object like map" as a map with predefined keys and their properties such as having
; {:first first, :last last, :age age } being a Person object,
; while having a map of people's names that were read from a database as keys and
; their information as values as an example of a "collection like map"

; a04: pro keyword: Works better for threading and mapping over collections of objects

;(-> my-map
;  :alpha
;  fn-on-alpha
;  :beta
;  fn-on-beta
;  :gamma
;
;> (def map-collection '({:key "values"} {:key "in"} {:key "collection"}))
;> (map :key map-collection)
;  ("values" "in" "collection")

; a05: pro map: Does not throw error when key is non-keyword or nil
;
({:a "b"} nil)
;  nil
;> (nil {:a "b"})
;  ERROR: can't call nil
({"a" "b"} "a")
;  "b"
;> ("a" {"a" "b"})
;  ERROR: string cannot be cast to IFn

; a06: pro map: Consistency with list access in Clojure

; #trm: consistency = uyumluluk = tutarlılık
;
([:a :b :c] 1)
;  :b
;(1 [:a :b :c])
;  ERROR: long cannot be cast to IFn

; a07: pro map: Similarity to other forms of object access
;
;java>         my_obj  .alpha  .beta  .gamma  .delta
;clj >     ((((my-map  :alpha) :beta) :gamma) :delta)
;clj > (get-in my-map [:alpha  :beta  :gamma  :delta])
;cljs> (aget   js-obj  "alpha" "beta" "gamma" "delta")

; a08: pro get: NEVER causes error if arg1 is map/vector/nil and arg2 is key/index/nil
;
(get nil :a)
;  nil
(get nil nil)
;  nil
(get {:a "b"} nil)
;  nil
(get {:a "b"} :q)
;  nil
(get [:a :b :c] nil)
;  nil
(get [:a :b :c] 5)
;  nil

; a09: pro get: Consistency in form with other Clojure functions
;
(get {:a "b"} :a)
;  :b
(contains? {:a "b"} :a)
;  true
(nth [:a :b :c] 1)
;  :b
(conj [:a :b] :c)
;  [:a :b :c]

; a10: pro get: Get-in can be used for nested access with a single call
;
;> (get-in my-map [:alpha  :beta  :gamma  :delta])
;> (aget   js-obj  "alpha" "beta" "gamma" "delta")