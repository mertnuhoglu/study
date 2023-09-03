(ns ex.e08)

; Tarih: 20230120
; Barış'la Clojure Egzersizleri
; rfr: video/20230121-mert-clj-egzersiz-09.mp4

(def dbSample {
               :person                   [{:person/id 1 :name "lodos" :surname "eskioğlu" :join-date "01.06.2022" :experience :experience/starter :loyality-level :loyality-level/zero-one-years :work-type :work-type/internship :work-time :worktime/part-time :managers {:manager/id 2}}
                                          {:person/id 2 :name "alp" :surname "boriçi" :join-date "04.04.2021" :experience :experience/medior :loyality-level :loyality-level/one-two-years :work-type :work-type/tenure :work-time :work-time/full-time :managers {:manager/id 2}}
                                          {:person/id 3 :name "emir" :surname "sürmeli" :join-date "09.01.2022" :experience :experience/senior :loyality-level :loyality-level/zero-one-years :work-type :work-type/tenure :work-time :work-time/part-time :managers {:manager/id 2}}
                                          {:person/id 4 :name "deniz" :surname "zengin" :join-date "07.08.2020" :experience :experience/medior :loyality-level :loyality-level/one-two-years :work-type :work-type/tenure :work-time :work-time/full-time :managers {:manager/id 2}}
                                          {:person/id 5 :name "alphan" :surname "kaplan" :join-date "01.03.2022" :experience :experience/starter :loyality-level :loyality-level/zero-one-years :work-type :work-type/internship :work-time :work-time/full-time :managers {:manager/id 2}}
                                          {:person/id 6 :name "barış" :surname "can" :join-date "04.04.2020" :experience :experience/medior :loyality-level :loyality-level/one-two-years :work-type :work-type/tenure :work-time :work-time/full-time :managers {:manager/id 2}}
                                          {:person/id 7 :name "ramazan" :surname "nedim" :join-date "04.04.2020" :experience :experience/senior :loyality-level :loyality-level/one-two-years :work-type :work-type/tenure :work-time :work-time/full-time :managers {:manager/id 2}}
                                          {:person/id 8 :name "tarık" :surname "derkan" :join-date "04.04.2018" :experience :experience/lead :loyality-level :loyality-level/three-five-years :work-type :work-type/tenure :work-time :work-time/full-time :managers {:manager/id 2}}
                                          {:person/id 9 :name "rich" :surname "hickey" :join-date "04.04.2016" :experience :experience/lead :loyality-level :loyality-level/more-than-seven-years :work-type :work-type/tenure :work-time :work-time/part-time}
                                          {:person/id 10 :name "mert" :surname "nuhoglu" :join-date "04.04.2018" :experience :experience/lead :loyality-level :loyality-level/five-seven-years :work-type :work-type/tenure :work-time :work-time/full-time :managers {:manager/id 1}}]

               :employees/developer-team [{:frontend  [[:person/id 1] [:person/id 2] [:person/id 3] [:person/id 4]]
                                           :backend   [[:person/id 5] [:person/id 6]]
                                           :fullstack [[:person/id 7]]
                                           :devops    [[:person/id 8]]}]

               :employees/managers       [{:manager/id 1 :manager/person [:person/id 9]}
                                          {:manager/id 2 :manager/person [:person/id 10]}]


               :relations/experience     [:experience/starter
                                          :experience/medior
                                          :experience/senior
                                          :experience/lead]


               :relations/loyality-level [:loyality-level/zero-one-years
                                          :loyality-level/one-two-years
                                          :loyality-level/three-five-years
                                          :loyality-level/five-seven-years
                                          :loyality-level/more-than-seven-years]

               :relations/work-time      #{:work-time/full-time :worktime/part-time}
               :relations/work-type      #{:work-type/internship :work-type/tenure}})



(defn get-person-by-id "get person by :person/id value" [id]
  (->> dbSample
       (:person)
       (filter #(= (:person/id %1) id))))

(comment
  (get-person-by-id 3))
  ;=>
  ;({:loyality-level :loyality-level/zero-one-years,
  ;  :join-date "09.01.2022",
  ;  :name "emir",
  ;  :experience :experience/senior,
  ;  :surname "sürmeli",
  ;  :work-time :work-time/part-time,
  ;  :managers #:manager{:id 2},
  ;  :work-type :work-type/tenure,
  ;  :person/id 3}))

(defn get-manager-person-id-by-manager-id "get a manager name my given manager id" [id]
  (->> dbSample
       (:employees/managers)
       (filter #(= (:manager/id %) id))
       (first)
       (:manager/person)
       (second)))

(comment
  (get-manager-person-id-by-manager-id 2))
  ;=> 10)

(defn get-person-name-by-id "get a person name by given id" [id]
  (->> dbSample
       (:person)
       (filter #(= (:person/id %1) id))
       (first)
       (:name)))

(comment
  (get-person-name-by-id 3))
  ;=> "emir")

(defn get-person-manager-name-by-person-id "get a person's manager name by given person id" [id]
  (-> id
       (get-person-by-id)
       (first)
       (:managers)
       (:manager/id)
       (get-manager-person-id-by-manager-id)
       (get-person-name-by-id)))

(comment
  (get-person-manager-name-by-person-id 3))
;=> "mert"

(defn get-frontend-developer-team-members-ids "get frontend developer team members references" []
  (->> dbSample
       (:employees/developer-team)
       (first)
       (:frontend)))

(comment
  (get-frontend-developer-team-members-ids))
;=> [[:person/id 1] [:person/id 2] [:person/id 3] [:person/id 4]]

(defn get-person-by-keyword-value-pair "get person by giving keyword and value pair" [kw value]
  (->> dbSample
       (:person)
       (filter #(= (kw %1) value))))

(comment
  (get-person-by-keyword-value-pair :name "lodos"))
  ;=>
  ;({:loyality-level :loyality-level/zero-one-years,
  ;  :join-date "01.06.2022",
  ;  :name "lodos",
  ;  :experience :experience/starter,
  ;  :surname "eskioğlu",
  ;  :work-time :worktime/part-time,
  ;  :managers #:manager{:id 2},
  ;  :work-type :work-type/internship,
  ;  :person/id 1}))

(defn get-person-by-name-by-surname  "get person surname by given name" [name]
  (->> dbSample
       (:person)
       (filter #(= (:name %1) name))
       (first)
       (:surname)))


(defn get-person-id-by-given-name "get person id by given name" [name]
  (->> dbSample
       (:person)
       (filter #(= (:name %1) name))
       (first)
       (:person/id)))

(get-manager-person-id-by-manager-id 1)
;=> 9

(get-person-by-id 3)
;=>
;({:person/id 3,
;  :name "emir",
;  :surname "sürmeli",

(get-person-name-by-id 9)
;=> "rich"

; q: bir vektör döndük. bu vektördeki her bir öğe için bir getter fonksiyonu çağırmak istiyoruz. nasıl yaparız?

(comment
  (get-frontend-developer-team-members-ids))
  ;=> [[:person/id 1] [:person/id 2] [:person/id 3] [:person/id 4]]

; biz burada dönen her bir person/id için o kişinin ismini almak istiyoruz
; map fonksiyonu ile bunu yapacağız
; önce bir mapin genel kullanımına bakalım
(comment
  (map inc [1 2 3 4 5]))
;=> (2 3 4 5 6)
; ne yaptık burada?
; verdiğimiz kümedeki her bir öğeyi `inc` fonksiyonuna argüman olarak gönderdik
; hepsinin sonucunu da birleştirip bir küme olarak döndük

; zaten kelime anlamı olarak map, to map veya mapping fiilinden geliyor
; mapping de türkçedeki bağıntı anlamına geliyor
; bağıntı ise matematikte herhangi bir kümedeki bir öğeye başka bir kümeden başka bir öğeyi bağlamak anlamına gelir
; yani ben ilk kümedeki (domain set veya tanım kümesi) 1 öğesine, ikinci kümedeki (codomain set veya sonuç kümesi)
; 2 öğesini bağlıyorum
; map fonksiyonu, bu iki kümeyi birbirine bağlamamızı sağlayan bir üst seviye fonksiyon
; bu iki kümeyi bağlarken de, bir ara fonksiyon kullanıyor
; bu ara fonksiyon da `inc` fonksiyonu

; fp'deki en temel kavram veya araçlardan bir tanesi `map` fonksiyonu
; reduce, into, filter gibi başka fonksiyonlar da var
; en genel ve işlevsel fonksiyon: reduce fonksiyonudur
; reduce fonksiyonuyla diğer bütün fonksiyonları türetebilirsin

; fp'de: bu tür fonksiyonlara higher-order (üst seviye) fonksiyon diyoruz
; bir fonksiyonu argüman olarak alan fonksiyonlara, üst seviye fonksiyon denir
; matematikte düşünecek olursak, mesela şöyle bir fonksiyonumuz olsun:
; f(x) = 2x = y
; buradaki f düz bir fonksiyon
; ∫f(x) = x^2
; burada integral fonksiyonu, üst seviye bir fonksiyon olmuş olur
; neden? çünkü integral fonksiyonu, kendisi argüman olarak başka bir fonksiyon almaktadır

(comment
  (map second (get-frontend-developer-team-members-ids)))
;=> (1 2 3 4)

(comment
  (->> (map second (get-frontend-developer-team-members-ids))
    (map get-person-name-by-id)))
;=> ("lodos" "alp" "emir" "deniz")
; burada frontend yazılımcılarının isimlerini almış olduk

; şimdi bir de bu frontend yazılımcılarının müdürlerinin isimlerini bulalım
(comment
  (->> (map second (get-frontend-developer-team-members-ids))
    (map get-person-manager-name-by-person-id)))
;=> ("mert" "mert" "mert" "mert")

; ödev:
; 1. exercism, rich4clojure vs. egzersizleri bir inceleyelim
; 2. veri üzerinde manipülasyonları deneyelim. mesela bir kişinin yöneticisini nasıl değiştirirsin?
; assoc, update, assoc-in, update-in
; 3. reduce temel örneklerine bakabilirsin

; genel olarak örnek data oluştururken minimal örnek verisi oluşturulım. gerçekçi olmasına gerek yok.