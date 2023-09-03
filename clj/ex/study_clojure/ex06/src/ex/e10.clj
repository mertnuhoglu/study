(ns ex.e10)

; Tarih: 20230125
; Barış'la Clojure Egzersizleri
; rfr: video/20230125-mert-clj-egzersiz-11.mp4

; q: Macrolar ne kadar önemli?
; prensip: makro yazmanın birinci kuralı: macro yazmayın.
; neden böyle bir prensip söylüyorlar?
; makrolar debug etmeyi ciddi anlamda zorlaştırıyor.
; normalde programlamayı hep fonksiyonel bir mantıkla düşünüyoruz
; kara kutu (blackbox) gibi
; f(x) = y
; f: x -> y
; bağıntı = fonksiyon
; makrolar bu mantıkla uyuşmuyor
; makro senin f diye ifade ettiğin fonksiyonun şeklini değiştiriyor

; örnek: defn bir macro
(macroexpand '(defn f1 [] 1))
;=> (def f1 (clojure.core/fn ([] 1)))
; defn macrosu aslında def ile ifade ettiğimiz bir formun şeklini değiştiren bir xeymiş
; aslında `defn` `def` fonksiyonunun şekil değiştirmiş bir hali
(defn f1 [] 1)
(def f2
  (fn [] 1))
(f2)
;=> 1
(f1)
;=> 1
; gördüğümüz gibi f1 ve f2 aynı şeyi yapan iki fonksiyon
; fakat def ile yazmak daha uzun olduğundan, biz daha kısa ve öz yazalım diye
; defn diye bir macro tanımlanmış
; ama aslında defn ne yapıyor? run edildiğinde, önce defn formunu def formuna çeviriyor clj compiler
; ondan sonra o def formunu run ediyor
; dolayısıyla yukarıdaki fonksiyonel yapıyı aslında biraz bozuyoruz
; ben doğrudan doğruya bir f fonksiyonunu çalıştırıp onun sonucunu yazmıyorum
; ne yapıyorum? bir m makrosunu çalıştırıp onun sonucunda oluşan formu çalıştırıyorum

; p02: veri düzenleme (edit)
; rfr: assoc_update.clj

;; ## assoc-in

(def users [{:name "James" :age 26}
            {:name "John" :age 43}])

(assoc-in users [1 :age] 44)
;;=> [{:name "James", :age 26} {:name "John", :age 44}]

(get-in users [1 :age])
;=> 43
; q: neden assoc-in ile veriyi update ettiğimiz halde, tekrar okuduğumuzda 43 aldık?
; çünkü clj'daki tüm objeler değişmezdir (immutable)
; alamet-i farikası
; hiçbir veri yapısı ne kadar kompleks olursa olsun, hiçbir zaman değişmez, her zaman sabittir

; q: peki o zaman ben değiştirilmiş users verisine nasıl ulaşacağım?
; yeniden def etmemiz gerekiyor
(def users2
  (assoc-in users [1 :age] 44))
(get-in users2 [1 :age])
;=> 44
; istersen yukarıdaki ismin aynısını tekrar kullanabilirsin
(def users
  (assoc-in users [1 :age] 44))
(get-in users [1 :age])
;=> 44

; peki users mapinde olmayan bir atribut nasıl ekleyeceğiz?
; yine aynı assoc fonksiyonuyla

(def users [{:name "James" :age 26}
            {:name "John" :age 43}])
;; insert the password of the second (index 1) user
(assoc-in users [1 :password] "nhoJ")
;;=> [{:name "James", :age 26} {:password "nhoJ", :name "John", :age 43}]

; polymorphism
; javada her bir fonksiyonu ayrı ayrı tanımlamak gerekir
; bunun bir nedeni de javanın static ve weak typing özelliğinde olması
; muhtemelen çok fazla kelime dağarcığını (vocabulary) genişletmek istemiyor clj yazarları
; çünkü o zaman daha fazla ezberlemek gerekiyor

; mevcut vektörümüze yeni bir öğe nasıl ekleriz?
; aynı yukarıdaki :password atributunu eklemek gibi ekleriz
(assoc-in users [2] {:name "Jack" :age 19})
;;=> [{:name "James", :age 26} {:name "John", :age 43} {:name "Jack", :age 19}]

; nested map oluşturmak sıfırdan
(assoc-in {} [:cookie :monster :vocals] "Finntroll")
; => {:cookie {:monster {:vocals "Finntroll"}}}

(assoc-in {} [1] 80)
;=> {1 80}
(assoc-in {} [1 :a] 90)
;=> {1 {:a 90}}
(assoc-in {} [1 :a :b] 80)
;=> {1 {:a {:b 80}}}
(assoc-in {1 :30} [1] 80)
;=> {1 80}
(assoc-in {2 :30} [1] 80)
;=> {2 :30, 1 80}
(assoc-in {2 :30} [1 :a] 80)
;=> {2 :30, 1 {:a 80}}
#_(assoc-in [] [1] 80)
;Execution error (IndexOutOfBoundsException) at e10/eval6965 (e10.clj:105).
(assoc-in [] [0] 80)
;=> [80]

; p03: basit veritabanı üzerinde bazı denemeler yapalım
(def db-sample {
                :person                  [{:person/id 1 :name "dan" :surname "stone" :joindate "01.06.2022" :experience :experience/starter :loyalitylevel :loyality-level/zero-one-years :worktime :worktime/internship :managers {:manager/id 1}}
                                          {:person/id 2 :name "dave" :surname "jhons" :joindate "04.04.2021" :experience :experience/medior :loyalitylevel :loyality-level/one-two-years :worktime :worktime/full-time :managers {:manager/id 1}}
                                          {:person/id 3 :name "patrick" :surname "square pant" :joindate "09.01.2022" :experience :experience/senior :loyalitylevel :loyality-level/zero-one-years :worktime :worktime/part-time :managers {:manager/id 1}}
                                          {:person/id 9 :name "rich" :surname "hickey" :joindate "04.04.2016" :experience :experience/lead :loyalitylevel :loyality-level/more-than-seven-years :worktime :worktime/part-time}]


                :employees/developerteam [{:frontend [[:person/id 1] [:person/id 2] [:person/id 3]]}]


                :employees/managers      [{
                                           :manager/id 1 :manager/person [:person/id 9]}]



                :relations/experience    {:experience/starter "0-1"
                                          :experience/medior  "1-3"
                                          :experience/senior  "3-6"
                                          :experience/lead    "6+"}


                :relations/loyalitylevel {:loyality-level/zero-one-years        "0-1 year(s)"
                                          :loyality-level/one-two-years         "1-2 year(s)"
                                          :loyality-level/three-five-years      "3-5 year(s)"
                                          :loyality-level/five-seven-years      "5-7 year(s)"
                                          :loyality-level/more-than-seven-years "7+ year(s)"}

                :relations/worktime      {:worktime/full-time  "full time"
                                          :worktime/part-time  "part time"
                                          :worktime/internship "internship"}})

; 9 id'li kişinin ismini değiştirelim
(assoc-in db-sample [:person 3 :name] "dave")
;{:person/id 9,
; :name "dave",
; :surname "hickey",
; :joindate "04.04.2016",
; :experience :experience/lead,
; :loyalitylevel :loyality-level/more-than-seven-years,
; :worktime :worktime/part-time}],

; q: burada 3 indeksini doğrudan vermeden yapamaz mıydık?
; maalesef, assoc-in sadece indexler üzerinden hareket eder.
; ancak fulcro formatında oluştursak map veritabanımızı bunu bir nebze kolaylaştırabiliriz

(def db-sample {:person                  [{:person/id 1 :name "dan"}
                                          {:person/id 2 :name "dave"}
                                          {:person/id 3 :name "patrick"}
                                          {:person/id 9 :name "rich"}]

                :employees/developerteam [{:frontend [[:person/id 1] [:person/id 2] [:person/id 3]]}]

                :employees/managers      [{:manager/id 1 :manager/person [:person/id 9]}]})

;-> fulcro formatı
(def db-sample {:person/id {1 {:person/id 1 :name "dan"}
                            2 {:person/id 2 :name "dave"}
                            3 {:person/id 3 :name "patrick"}
                            9 {:person/id 9 :name "rich"}}})
; 9 id'li kişinin ismini değiştirelim
(assoc-in db-sample [:person/id 9 :name] "dave")
;#:person{:id {1 {:person/id 1, :name "dan"},
;              2 {:person/id 2, :name "dave"},
;              3 {:person/id 3, :name "patrick"},
;              9 {:person/id 9, :name "dave"}}}

; q: otomatik fulcro formatına çevirme
(def users [{:id 1 :email "michael.lawson@reqres.in" :first_name "Michael"}
            {:id 2 :email "lindsay.ferguson@reqres.in"}])
;bu fonksiyon ile herhangi bir vektörün elementlerini mapa dönüştürürüz.
(reduce (fn [acc {:keys [id] :as user}]
          (into acc {id user})) {} users)
;=> {1 {:id 1, :email "michael.lawson@reqres.in", :first_name "Michael"}, 2 {:id 2, :email "lindsay.ferguson@reqres.in"}}

; assoc fonksiyonu
(assoc {} :a 1 :b 2)
;=> {:a 1, :b 2}
(assoc-in {} [:a] 1)
;=> {:a 1}
(->
  (assoc-in {} [:a] 1)
  (assoc-in [:b] 2))
;=> {:a 1, :b 2}

; nested bir veri yapısı üzerinde işlem yaparken assoc-in
; flat veri yapısı üzerinde işlem yaparken assoc kullanmak daha pratik

; flat = düz. içiçeliği olmayan anlamına geliyor

; standart: indeks kelimesini keyword anlamında kullanalım map objelerinde
; mesela
;=> {:a 1, :b 2}
; burada :a ve :b indeks olarak isimlendirelim (keyword'e ek olarak)
; vektör ve map ikisi de associative data structure olarak geçiyor
; vektörde indekse göre değerler bağlanıyor (associate) ediliyor
; mapdeyse keyworde göre
; dolayısıyla keyword de bir indeks görevi görüyor aslında
; vektör: [3 5 7]
; 0 -> 3
; 1 -> 5
; 2 -> 7
; map: {0 3, 1 5, 2 7}
; aslında aynı şey

; assoc-in ile yaptığımız gibi assoc ile de
; yeni öğe ekleyebiliyoruz
; mevcut bir öğeyi update edebiliyoruz

; assoc - update
; assoc-in - update-in
; benzeşirler
; assoc fonksiyonlarında doğrudan değeri veriyoruz
; update fonksiyonlarındaysa bir fonksiyon veriyoruz

(def p {:name "James" :age 26})
;;=> #'user/p

(assoc p :age 27)
;;=> {:name "James", :age 27}
(update p :age inc)
;;=> {:name "James", :age 27}
; 27 yerine kullanacağım update fonksiyonu olan `inc` fonksiyonunu yazdım
; bir nevi şöyle çalıştı:
(get p :age)
;=> 26
; bu 26 değerini inc fonksiyonuna gönderdim
(inc 26)
;=> 27
; bunu da update ettim

#_(inc [20 30])
;class clojure.lang.PersistentVector cannot be cast to class java.lang.Number (clojure.lang.PersistentVector is in unnamed module of loader 'app'); java.lang.Number is in module java.base of loader 'bootstrap')
(map inc [20 30])
;=> (21 31)

(def p [{:name "James" :age 26}
        {:name "Bill" :age 30}])
(update-in p [0 :age] inc)
;=> [{:name "James", :age 27} {:name "Bill", :age 30}]

; update-in assoc-in deki gibi nested bir indeksleme mekanizmasıyla çalışıyor
; update is assoc'taki gibi flat bir veri yapısı üzerinde çalışıyor
(assoc-in p [0 :age] 27)
;=> [{:name "James", :age 27} {:name "Bill", :age 30}]

; map
; filter
; update update-in
; assoc assoc-in
; get get-in
; reduce
; into

; matematikte her şeyi minimum aksiyoma indirmek diye bir hedef var
; tüm geometri 5 tane aksiyoma indirebiliyorsun
; yani 5 tane varsayımı kabul ettiğin anda, tüm geometrik kuralları ve teorimler türetebiliyorsun
; benzer fp da lambda algebrasına dayandığından
; her şeyi minimum varsayım kuralından türetebilmek gibi bir hedef var
; tüm lisp programlama dilini 30 satırda falan yazabiliyorsun
; homoiconicity
