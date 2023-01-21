(ns e07)

; Tarih: 20230120
; Barış'la Clojure Egzersizleri
; rfr: video/20230120-mert-clj-egzersiz-07.mp4

; p01: Foreign key ne anlama geliyor map türü veri yapılarında?

; normalde fk kavramını ilişkisel veritabanlarında kullanıyoruz?

; bir öğrenci verisinin flat tablo formatındaki gösterimi:
; | öğrenci_id   | isim          | şehir      | alınan_ders   |
; |------------  |-------------  |----------  |-------------  |
; | 101          | Ali Niyazi    | Bursa      | MAT101        |
; | 102          | Veli Şimşek   | Kütahya    | FİZİK101      |
; | 103          | Ayşe Olgun    | İstanbul   | MAT101        |
; | 104          | Elif Şimşek   | Trabzon    | MAT101        |

; aynı öğrenci verisinin map/hiyerarşik/ağaç (tree) formatındaki gösterimi
{:Öğrenci [{:öğrenci_id 101 :isim "Ali Niyazi"}
           {:öğrenci_id 102 :isim "Veli Şimşek"}]}

; todo: tabular formattaki veriyle hiyerarşik ağaç formatındaki verinin artı ve eksilerini kıyaslayalım

; yani aslında bir tablo birebir olarak mape dönüştürülebilir ve tersi geçerlidir
; peki fk'leri nasıl uyguluyoruz map formatında?

{:Ders [{:ders_id "MAT101" :isim "Matematiğe Giriş"}
        {:ders_id "FİZ101" :isim "Fiziğe Giriş"}]}

; bu Ders kayıtlarına Öğrenci mapinden ref vermemiz gerekiyor

{:Öğrenci [{:öğrenci_id 101 :isim "Ali Niyazi" :alınan_ders "MAT101"}
           {:öğrenci_id 102 :isim "Veli Şimşek" :alınan_ders "FİZ101"}]}

; şimdi burada `alınan_ders` bizim FK atributumuz oluyor.
; fakat e06 dersinde ne demiştik, FK değerlerini düz primitif olarak tutmayalım,
; ilgili tablonun ismini de koyalım bir tuple olarak (fulcro standardı)

{:Öğrenci [{:öğrenci_id 101 :isim "Ali Niyazi" :alınan_ders [:ders_id "MAT101"]}
           {:öğrenci_id 102 :isim "Veli Şimşek" :alınan_ders [:ders_id "FİZ101"]}]}

; biz şu ana kadar bir öğrencinin bir tane ders alabileceğini varsaydık
; bu kısıtı kaldıralım
; n tane ders alabilsin
{:Öğrenci [{:öğrenci_id 101 :isim "Ali Niyazi" :alınan_ders [[:ders_id "FİZ101"]
                                                             [:ders_id "MAT101"]]}
           {:öğrenci_id 102 :isim "Veli Şimşek" :alınan_ders [[:ders_id "FİZ101"]]}]}
; FK hala `alınan_ders`

; dünkü örneğimize bakalım
{:employees/developerteam [{:frontend [[:person/id 1] [:person/id 2] [:person/id 3] [:person/id 4]]}]}
; burada `:frontend` aslında developerteam tablosundan person tablosundaki n tane kayıda verilen bir ref atributu
; dolayısıyla bu bir FK atributudur

; ilişkisel ile map arasındaki tek fark, fk atributundaki tuple değerlerinin içinde karşı taraftaki tablonun pk kolonunun isminin bulunması
; `:person/id` diye belirtiyoruz
; aslında ilişkisel veritabanında bu karşı tablo kolonunun ismi belirtiliyor
; ama biz onu görmüyoruz
; onu constraint olarak tanımlıyorlar bir defa, sonra her satırda tekrarlamıyorlar

; örneğin SQL olarak yukarıdaki örneği yapsaydık şöyle bir DDL cümlesi yazardık:
;CREATE TABLE Öğrenci (
;                      öğrenci_id int NOT NULL,
;                      isim TEXT,
;                      ders_id int,
;                      PRIMARY KEY (öğrenci_id),
;                      FOREIGN KEY (alınan_ders) REFERENCES Ders (ders_id))
;;

; şimdi veri erişimiyle ilgili problemleri yapmaya devam edelim.
; dün burada kalmıştık:
; q01: verili bir elemanın yöneticisinin ismini alalım

(def db {:person                  [{:person/id 1 :name "lodos" :surname "eskioğlu" :managers {:manager/id 1}}
                                   {:person/id 2 :name "alp" :surname "boriçi" :managers {:manager/id 1}}
                                   {:person/id 3 :name "emir" :surname "sürmeli" :managers {:manager/id 1}}]

         :employees/developerteam [{:frontend  [[:person/id 1] [:person/id 2] [:person/id 3] [:person/id 4]]
                                    :backend   [[:person/id 5] [:person/id 6]]
                                    :fullstack [[:person/id 7]]
                                    :devops    [[:person/id 8]]}]

         :employees/managers      [{:manager/id 1 :manager/person [:person/id 3]}]})

; mesela lodosun yöneticisi kimdir?

; s01: önce veritabanımızdaki (db) :person tablosundaki tüm kayıtları çekelim
(->> db
  (:person))
;=>
[{:person/id 1, :name "lodos", :surname "eskioğlu", :managers #:manager{:id 1}}
 {:person/id 2, :name "alp", :surname "boriçi", :managers #:manager{:id 1}}
 {:person/id 3, :name "emir", :surname "sürmeli", :managers #:manager{:id 1}}]

; bu person tablosundaki kayıtlardan ismi "lodos" olan kaydı bulalım
(->> db
  (:person)
  (filter #(-> (:name %1)
             (= "lodos"))))
;=> ({:person/id 1, :name "lodos", :surname "eskioğlu", :managers #:manager{:id 1}})

; fakat dönen değer yani person objesi aslında bir seq objesi içine sarılmış durumdadır
; dolayısıyla bizim o seqin içindeki person objesini (mapini) çekmemiz lazım

(->> db
  (:person)
  (filter #(-> (:name %1)
             (= "lodos")))
  (first))
;=> {:person/id 1, :name "lodos", :surname "eskioğlu", :managers #:manager{:id 1}}

; şimdi bu person objesine bir isim (global bir variable tanımlamak) verelim.
; bunun için `def` fonksiyonunu kullanıyoruz
; artık isim verince, ben bu objeye o isimle kolayca erişebilir olacağım
(def p1
  (->> db
    (:person)
    (filter #(-> (:name %1)
               (= "lodos")))
    (first)))
(identity p1)
;=> {:person/id 1, :name "lodos", :surname "eskioğlu", :managers #:manager{:id 1}}

; şimdi p1 objesinin (yani başka bir deyişle lodos kişisinin) yöneticilerini bulalım
(->> p1
  (:managers))
;=> #:manager{:id 1}
; bunun sonucunda bir map döndü
; bu objenin içinden ilgili referans verilen satırın değerini almak istiyorum:

(->> p1
  (:managers)
  (:manager/id))
;=> 1

; buna da bir isim verelim
(def searched_manager (->> p1
                        (:managers)
                        (:manager/id)))

; şimdi :employees/managers tablosundaki ilgili satırı alalım (1 değerli satırı)
; bunun için önce bu tabloya bir isimle erişebilir olalım
(def ms (:employees/managers db))
(print ms)
; [#:manager{:id 1, :person [:person/id 3]}]

; parantez:
; burada clojure standart temsil formatıyla namespace edilmiş mapleri gösteriyor
; [#:manager{:id 1, :person [:person/id 3]}]
; bu aslında şu formatla birebir aynı anlama geliyor:
; [{:manager/id 1, :manager/person [:person/id 3]}]
; içerideki tüm kw'lerin ns'i aynı olunca, onu dışarı alıyor, tekrarlamamak için
; bununla ilgili daha detaylı referans için:
; [Clojure - Reading Clojure Characters](https://clojure.org/guides/weird_characters)
; #: and #:: - Namespace Map Syntax

(filter
  #(->
     (:manager/id %1)
     (= searched_manager))
  ms)
; => (#:manager{:id 1, :person [:person/id 3]})

(->
  (filter
    #(->
       (:manager/id %1)
       (= searched_manager))
    ms)
  (first)
  (:manager/person)
  (second))
;=> 3

; buna da bir isim verelim
(def smid
  (->
    (filter
      #(->
         (:manager/id %1)
         (= searched_manager))
      ms)
    (first)
    (:manager/person)
    (second)))

(def ps (-> db
          (:person)))


(->> ps
  (filter
    #(->
       (:person/id %1)
       (= smid)))
  (first)
  (:name))
;=> "emir"

; print yerine identity fonksiyonuyla değişkenlerin değerlerini inceleyebiliriz
(identity smid)
