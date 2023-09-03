(ns ex.e06)

; Tarih: 20230119
; Barış'la Clojure Egzersizleri
; rfr: video/20230119-mert-clj-egzersiz-06.mp4

(def dbSample {
               :person                  [{:person/id 1 :name "lodos" :surname "eskioğlu" :joindate "01.06.2022" :experience :experience/starter :loyalitylevel :loyality-level/zero-one-years :worktime :worktime/internship :managers {:manager/id 10}}
                                         {:person/id 2 :name "alp" :surname "boriçi" :joindate "04.04.2021" :experience :experience/medior :loyalitylevel :loyality-level/one-two-years :worktime :worktime/full-time :managers {:manager/id 10}}
                                         {:person/id 3 :name "emir" :surname "sürmeli" :joindate "09.01.2022" :experience :experience/senior :loyalitylevel :loyality-level/zero-one-years :worktime :worktime/part-time :managers {:manager/id 10}}
                                         {:person/id 4 :name "deniz" :surname "zengin" :joindate "07.08.2020" :experience :experience/medior :loyalitylevel :loyality-level/one-two-years :worktime :worktime/full-time :managers {:manager/id 10}}
                                         {:person/id 5 :name "alphan" :surname "kaplan" :joindate "01.03.2022" :experience :experience/starter :loyalitylevel :loyality-level/zero-one-years :worktime :worktime/internship :managers {:manager/id 10}}
                                         {:person/id 6 :name "barış" :surname "can" :joindate "04.04.2020" :experience :experience/medior :loyalitylevel :loyality-level/one-two-years :worktime :worktime/full-time :managers {:manager/id 10}}
                                         {:person/id 7 :name "ramazan" :surname "nedim" :joindate "04.04.2020" :experience :experience/senior :loyalitylevel :loyality-level/one-two-years :worktime :worktime/full-time :managers {:manager/id 10}}
                                         {:person/id 8 :name "tarık" :surname "derkan" :joindate "04.04.2018" :experience :experience/lead :loyalitylevel :loyality-level/three-five-years :worktime :worktime/full-time :managers {:manager/id 10}}
                                         {:person/id 9 :name "rich" :surname "hickey" :joindate "04.04.2016" :experience :experience/lead :loyalitylevel :loyality-level/more-than-seven-years :worktime :worktime/part-time}
                                         {:person/id 10 :name "mert" :surname "nuhoglu" :joindate "04.04.2018" :experience :experience/lead :loyalitylevel :loyality-level/five-seven-years :worktime :worktime/full-time :managers {:manager/id 10}}]


               :employees/developerteam [{:frontend  [{:person/id 1} {:person/id 2} {:person/id 3} {:person/id 4}]
                                          :backend   [{:person/id 5} {:person/id 6}]
                                          :fullstack [{:person/id 7}]
                                          :devops    [{:person/id 8}]}]


               :employees/managers      [{
                                          {:manager/id 1 :person/id 9}
                                          {:manager/id 2 :person/id 10}}]



               :relations/experience    {:experience/starter {:experiencetimeperiod "0-1"}
                                         :experience/medior  {:experiencetimeperiod "1-3"}
                                         :experience/senior  {:experiencetimeperiod "3-6"}
                                         :experience/lead    {:experiencetimeperiod "6+"}}

               :relations/loyalitylevel {:loyality-level/zero-one-years        "0-1 year(s)"
                                         :loyality-level/one-two-years         "1-2 year(s)"
                                         :loyality-level/three-five-years      "3-5 year(s)"
                                         :loyality-level/five-seven-years      "5-7 year(s)"
                                         :loyality-level/more-than-seven-years "7+ year(s)"}

               :relations/worktime      {:worktime/full-time  "full time"
                                         :worktime/part-time  "part time"
                                         :worktime/internship "internship"}})

; refactoring01: gereksiz bir veriyi kaldırmak

{:relations/worktime {:worktime/full-time  "full time"
                      :worktime/part-time  "part time"
                      :worktime/internship "internship"}}

; Burada bir redundancy (bolluk) gereksizlik var
; keyword ile o kw'ün açıklaması birbirinin tıpatıp aynısı.
; dolayısıyla string açıklamayı kaldırabiliriz

{:relations/worktime [:worktime/full-time :worktime/part-time :worktime/internship]}

; con: bu şekilde yapınca diğer yapılardan bir farklılık yapmış olduk
; diğer hepsinde ref verdiğimiz enumları map ile implemente etmişken, burada vektör ile yapmış olduk

; prensip: veri modellemesi yaparken, her zaman öncelikli kriter olarak, minimum gereken veri yapısı neyse onu kullanmak olsun
; bir primitif (primitive: char, int, string, enum vs.)
; primitif veri tipleri, collection veri tiplerinden daha basittir
; collection veri tipleri arasında da basitlik sıralaması var:
; en basit list (sequence) ve set
; sonra vektör
; sonra map

; refactoring02: koleksiyon veri tipini sadeleştirme
; bizim buradaki durumumuzda map olmasını gerektirecek bir durum var mı?
; map olacaksa, bir property (atribut) olmalı
; herhangi bir atribut tutmuyoruz
; vektör olmasına gerek var mı?
; vektör olması için de bir sıralama olmalı
; çok yok gibi sanki
; list mi olsun yoksa set mi?
; eğer collectionın öğelerinin tekil (unique) olması şartı varsa, set olmalı
; yoksa list olmalı
; bizde doğal olarak bütün kategorik değer kümeleri (enum) tekillik şartı var
; tekillik şartını ya set ile sağlayabiliriz ya da map ile sağlayabiliriz
; çünkü map'te de key'ler tekrarlanamadığından, tekillik şartı sağlanabilir

{:relations/worktime #{:worktime/full-time :worktime/part-time :worktime/internship}}

; refactoring03: birbirinden bağımsız kavramları ayrıştırdık (separation of concerns)
; q01: bir öğrenci stajyer, hem parttime çalışabilir hem fulltime. bunu nasıl ele alacağız?
; bu problem, worktime enum kümesinin tanımıyla ilgili değil
; onun kullanımıyla ilgili bir problem
; yani ona ref verirken, o refi tutan objeyi nasıl bir veri yapısında saklayacağımızla ilgili

{:person [{:person/id 1 :name "lodos" :worktime :worktime/internship}]}
; bu örnekte ilgili çalışan eleman, sadece tek bir worktime değeri alabilir
; birden çok değer alması için, bu atributu collection olması lazım
{:person [{:person/id 1 :name "lodos" :worktime [:worktime/internship
                                                 :worktime/part-time]}]}

; q02: ancak bir kişinin internship olup olmaması part-time veya full-time olmasıyla bağımsız bir durum
; değil
; dolayısıyla aslında biz burada iki farklı meseleyi (concern) tek bir kategorinin içine koymuş olduk
; buna biz orthogonality (dikeylik) problemi diyoruz
; eğer iki mesele birbirinden tamamen bağımsızsa, bu iki mesele birbirine dikeydir diyoruz
; neden dikeylik kelimesini kullanmış olabiliriz?
; bu geometrik projeksiyon olayı var oradan gelen bir kavram
; geometrik dönüşümlerde (transformasyonlarda)
; veri modellemesi de bir yönüyle kartezyen uzaydaki gibi boyutlar tanımlayıp onlar üzerinde varlıklarımızı anlamlandırıyoruz
; bizim boyutlarımız, atributlarımız oluyor
; :relations/worktime aslında bizim anlam uzayımızın (veri modelimizin) bir boyutu
; bu boyutların birbirine dikey olmasını isteriz
; bu boyutların (atributların) arasında bağımsızlık olmasını isteriz
; yani bir boyuttaki (atribut) bir değeri değiştirdiğimizde, başka boyutlarda herhangi bir değişiklik olmamasını bekleriz
; bunun için de birbirinden bağımsız atributlar tanımlamalıyız

; yukarıdaki örnekte `internship` kavramıyla `full-time` ve `part-time` kavramları arasında bir bağımsızlık ilişkisi var
; yani aslında biz kavramları içiçe sokmuş olduk
; ikinci bir atribut tanımlamalıyız:
; `internship` aslında çalışma tipiyle alakalı
; bir eleman ya stajyer olarak ya da kadrolu (tenure) olarak çalışabilir
; bu ikisi çalışma zamanından bağımsız.
; yani bir kadrolu eleman ft da çalışabilir pt da çalışabilir
; aynı şey stajyer için de geçerli
; dolayısıyla iki tane atribut tanımlamalıyım

{:relations/worktime  #{:worktime/full-time :worktime/part-time}
 :relations/work-type #{:work-type/internship :work-type/tenure}}

; bu durumda bir elemanın bu ilgili atributları nasıl olacak?
{:person [{:person/id 1
           :name      "lodos"
           :worktime  :worktime/full-time
           :work-type :work-type/internship}]}

; bunun bir faydası da ilgili atributları collection olarak tutmamıza da gerek kalmadı
; neden şöyle yapmıyorum:?
{:person [{:person/id 1
           :name      "lodos"
           :worktime  [:worktime/full-time :worktime/part-time]}]}
; bu şekilde yaparsak: bir kişi hem ft çalışıyor hem pt çalışıyor anlamına gelir
; bir kişi hem ft hem pt çalışabilir mi?
; mümkün değil
; çünkü bunlardan birisi varsa diğerinin olmaması gerekir
; buna biz mutually exclusive (birbirini dışlayan) veri tipi diyoruz
; her türlü kategorik değer kümesini, birbirini dışlayan bir küme olarak tanımlamaya çalışalım
; eğer bunu yaparsan, otomatikman dikeyliği elde edersin

; refactoring = sadeleştirme
; refactoring ile ya bir veri modelini ya da bir algoritmayı daha sade bir şekilde tanımlıyoruz
; ama fonksiyonalite olarak yeni bir şey eklemiyoruz

; prensip: sadeleştirmeye her zaman vakit bulmaya çalışalım
; ne kadar aciliyetlerimiz olursa olsun, sadeleştirme yapmaya değecektir
; ne kadar sadeleştirme yaparsan, o kadar temiz bir veri modeli veya program elde ederiz
; bu da bizim teknik borcumuzu azaltmamızı sağlar
; boşluğa ne gelir?
; a) geliştirme maliyetleri
; b) hata oranımızı
; c) okunaklığımızı artırır
; d) standardı artırır
; e) teknik borcumuzu (technical debt)

; teknik borç kavramı da aslında TKY (toplam kalite yönetimi) = TQM (total quality management) alanındaki
; buradaki `waste` yani israf kavramına denk gelir
; Edwards Deming
; Taichi Ohno
; Womack
; Goldratt
; endüstriyel süreçlerde her zaman olabildiğince israftan arındırılmış süreç etmeyi önerirler
; israftan arındırılmış demek, bizim için teknik borçlardan temizlenmiş demek

; todo: bir tane sade bir tane de müsrif veri yapısı örneği yapıp, maliyetlerini kıyaslayan bir vaka çalışması yapalım

; refactoring04:
; referanslarla ilgili de bir iyileştirme yapabiliriz

{:employees/developerteam [{:frontend  [{:person/id 1} {:person/id 2} {:person/id 3} {:person/id 4}]
                            :backend   [{:person/id 5} {:person/id 6}]
                            :fullstack [{:person/id 7}]
                            :devops    [{:person/id 8}]}]}

; fulcronun veri yapısına baktığımızda, referanslarda map yerine tuple kullandıklarını görmüştük
{:person/id  {1 {:person/id 1 :person/fname "Jo" :person/address [:address/id 11]}}
 :address/id {11 {:address/id 11 :address/street "Elm Street 7"}}}

; burada referansı map değil tuple olarak kullanmışlar fulcroda
[:address/id 11]

; aslında tuple da değil, bir vektör
; çünkü cljda tuple için literal bir notasyon yok
; parantez: literal kelimesi ne anlama geliyor?
; mesela javada bir set oluşturmak istersek nasıl tanımlarız?
; Set s = new HashSet()
; s.add("ali")
; s.add("veli")
; şimdi clojureda nasıl yapıyoruz?
; (def s #{"ali" "veli"})
; `#{..}` bu notasyon otomatikman bize bir `new HashSet()` çağrısı ve ardından da `add` metotlarını çağırmamızı sağlıyor
; buna literal notasyon diyoruz
; dilin genel fonksiyon çağrılarını yapmak yerine, sembollerle hızlı yazım yapmak gibi bir şey

[:address/id 11]
; bu örnekte aslında bir tuple değil vektör var
; çünkü clj tuple için bir literal notasyon sağlamıyor bize
; tuple ile vektör arasındaki fark ne?
; n-tuple da olabilir 3-tuple da olabilir
; sıralı diziler her zaman öntanımlı öğe sayısına sahiptir
; sıralı ikili dediğimde her zaman iki öğeden oluşan bir diziden bahsederim
; ama vektörlerin öntanımlı bir öğe sayısı yoktur
; öğe sayısı = cardinality (kardinalite) diyoruz
; bir kümenin içindeki tüm öğelerin adediten kardinalite denir
; renkler = {sarı, kırmızı, yeşil}
; renkler kümesinin kardinalitesi 3'tür
; kardinalite finite (sonlu) olabilir, infinite (sonsuz) olabilir
; kardinalite tekil (single) olabilir, çoğul (multiple) olabilir
; en çok kullanılan tuple da tahminli, 2-tuple yani ikili dizilerdir
; clj'da özel notasyon olmadığından vektörler kullanıyoruz
; vektöre aslında sınırsız sayıda eleman eklememizin önünde herhangi bir engel yok
; dil bizi bu açıdan kısıtlamaz
; buradaki kısıtlamayı bizim kendi programlama disiplini içinde uygulamamız lazım
; biz kendi kendimize bir convention (programlama standardı) belirleyeceğiz
; diycez ki: arkadaş biz referansları her zaman ikili dizilerle tanımlıyoruz
; bunun için de vektör kullanıyoruz
[:address/id 11]
; fulcrocular da böyle bir programlama standardı tanımlamışlar
; tüm referansları ikili dizilerle tanımlıyorlar
(def t1 [:address/id 11])
(first t1)
; => :address/id
(second t1)
; => 11

; q: bir vektörün içinde sadece iki tane öğe bulundurursak biz buna ikili dizi diyoruz doğru mu?
; her vektör için geçerli değil bu
; biz kendi kendimize bir standart koyuyoruz
; diyoruz ki, arkadaş biz referansları her zaman ikili dizilerle tanımlayacağız
; hepimiz bu kurala uyuyoruz yazılımcılar olarak
; hepimiz bu kurala uyduğumuz için, bir t1 ikilisi aldığımda, ben biliyorum ki,
; bunun ilk öğesi, fk atributunun ismidir
; ikinci öğesi de, fk değeridir

; q: peki biz bu ref objelerini map olarak tutmak yerine tuple olarak tutunca ne artı eksimiz var?
[:address/id 11]
(def m1 {:address/id 11})
; p01: map olunca, key fonksiyonuyla çağırarak değerine erişebiliriz. bu bir artı gibi görünüyor.
; p02: en sade veri yapısı neyse onu kullan, ekstra bir kısıt koyma, prensibimiz vardı
; map veri yapısı otomatikman bir ön tanımlı schema varsayımı oluşturur
; yani bir yazılımcı map görünce, şunu varsayar:
; ha demek ki, her zaman burada bu kw kullanılması bekleniyormuş
; tuple olunca böyle bir varsayımda bulunmazsın
; dolayısıyla daha dinamik bir şekilde bu objeye erişmeye çalışırsın
; mesela `first` fonksiyonuyla erişmek daha dinamik erişim şekli
; ama sen `(:address-id m1)` şeklinde erişirsen, baştan zaten o kw'ü kullanman gerekir

; refactoring05: tüm referansların bir fk'sı olsun
{:employees/managers [{:manager/id 1 :person/id 9}
                      {:manager/id 2 :person/id 10}]}
; ->
{:employees/managers [{:manager/id 1 :manager/person {:person/id 9}}
                      {:manager/id 2 :manager/person {:person/id 10}}]}
; ilk durumda fk atributunun ismi, pk atributunun ismiyle bir
; tek bir nitelik kullanılmış kısa olsun diye
; fakat ikinci durumda, bir fk atributu var
; bir de o fk atributunun referans verdiği tablonun pk atributu var

; örnek düzeltilmiş hali:
{:employees/developerteam [{:frontend  [{:person/id 1} {:person/id 2} {:person/id 3} {:person/id 4}]
                            :backend   [{:person/id 5} {:person/id 6}]
                            :fullstack [{:person/id 7}]
                            :devops    [{:person/id 8}]}]

 :employees/managers      [{
                            {:manager/id 1 :person/id 9}
                            {:manager/id 2 :person/id 10}}]}
; ->
{:employees/developerteam [{:frontend  [[:person/id 1] [:person/id 2] [:person/id 3] [:person/id 4]]
                            :backend   [[:person/id 5] [:person/id 6]]
                            :fullstack [[:person/id 7]]
                            :devops    [[:person/id 8]]}]

 :employees/managers      [{:manager/id 1 :manager/person [:person/id 9]}
                           {:manager/id 2 :manager/person [:person/id 10]}]}

; q: bu referansları nasıl kullanacağız?

(def db {:person                  [{:person/id 1 :name "lodos" :surname "eskioğlu" :managers {:manager/id 1}}
                                   {:person/id 2 :name "alp" :surname "boriçi" :managers {:manager/id 1}}
                                   {:person/id 3 :name "emir" :surname "sürmeli" :managers {:manager/id 1}}]

         :employees/developerteam [{:frontend  [[:person/id 1] [:person/id 2] [:person/id 3] [:person/id 4]]
                                    :backend   [[:person/id 5] [:person/id 6]]
                                    :fullstack [[:person/id 7]]
                                    :devops    [[:person/id 8]]}]

         :employees/managers      [{:manager/id 1 :manager/person [:person/id 3]}]})

; q01: verili bir elemanın yöneticisinin ismini alalım
; mesela lodosun yöneticisi kimdir?
(->> db
  (:person))
;=>
[{:person/id 1, :name "lodos", :surname "eskioğlu", :managers #:manager{:id 1}}
 {:person/id 2, :name "alp", :surname "boriçi", :managers #:manager{:id 1}}
 {:person/id 3, :name "emir", :surname "sürmeli", :managers #:manager{:id 1}}]

(->> db
  (:person)
  (filter #(-> (:name %1)
             (= "lodos"))))
;=> ({:person/id 1, :name "lodos", :surname "eskioğlu", :managers #:manager{:id 1}})

(->> db
  (:person)
  (filter #(-> (:name %1)
             (= "lodos")))
  (first))
;=> {:person/id 1, :name "lodos", :surname "eskioğlu", :managers #:manager{:id 1}}

(def p1
  (->> db
    (:person)
    (filter #(-> (:name %1)
               (= "lodos")))
    (first)))
(print p1)
;=> {:person/id 1, :name "lodos", :surname "eskioğlu", :managers #:manager{:id 1}}

(->> p1
  (:managers))
;=> #:manager{:id 1}
(->> p1
  (:managers)
  (:manager/id))
;=> 1
