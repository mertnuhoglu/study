(ns e05)

; Tarih: 20230119
; Barış'la Clojure Egzersizleri
; rfr: video/20230119-mert-clj-egzersiz-05.mp4


(def dbSample {
               :person                  [{:id 1 :name "lodos" :surname "eskioğlu" :joindate "01.06.2022" :experience 1 :loyalitylevel 1 :worktime 3 :managers 10}
                                         {:id 2 :name "alp" :surname "boriçi" :joindate "04.04.2021" :experience 2 :loyalitylevel 2 :worktime 1 :managers 10}
                                         {:id 3 :name "emir" :surname "sürmeli" :joindate "09.01.2022" :experience 3 :loyalitylevel 1 :worktime 2 :managers 10}
                                         {:id 4 :name "deniz" :surname "zengin" :joindate "07.08.2020" :experience 2 :loyalitylevel 2 :worktime 1 :managers 10}
                                         {:id 5 :name "alphan" :surname "kaplan" :joindate "01.03.2022" :experience 1 :loyalitylevel 1 :worktime 3 :managers 10}
                                         {:id 6 :name "barış" :surname "can" :joindate "04.04.2020" :experience 2 :loyalitylevel 2 :worktime 1 :managers 10}
                                         {:id 7 :name "ramazan" :surname "nedim" :joindate "04.04.2020" :experience 3 :loyalitylevel 2 :worktime 1 :managers 10}
                                         {:id 8 :name "tarık" :surname "derkan" :joindate "04.04.2018" :experience 4 :loyalitylevel 3 :worktime 1 :managers 10}
                                         {:id 9 :name "rich" :surname "hickey" :joindate "04.04.2016" :experience 4 :loyalitylevel 5 :worktime 2}
                                         {:id 10 :name "mert" :surname "nuhoglu" :joindate "04.04.2018" :experience 4 :loyalitylevel 4 :worktime 1 :managers 9}]


               :employees/developerteam [{:frontend  [{:id 1} {:id 2} {:id 3} {:id 4}]
                                          :backend   [{:id 5} {:id 6}]
                                          :fullstack [{:id 7}]
                                          :devops    [{:id 8}]}]


               :employees/managers      [{
                                          {:id 1 :person 9} {:id 2 :person 10}}]



               :relations/experience    [{:id 1 :experiencetimeperiod "0-1" :experiencetype "junior"}
                                         {:id 2 :experiencetimeperiod "1-3" :experiencetype "medior"}
                                         {:id 3 :experiencetimeperiod "3-6" :experiencetype "senior"}
                                         {:id 4 :experiencetimeperiod "6+" :experiencetype "lead senior"}]

               :relations/loyalitylevel [{:id 1 :loyalitylevelvalue "0-1 year(s)"}
                                         {:id 2 :loyalitylevelvalue "1-2 year(s)"}
                                         {:id 3 :loyalitylevelvalue "3-5 year(s)"}
                                         {:id 4 :loyalitylevelvalue "5-7 year(s)"}
                                         {:id 5 :loyalitylevelvalue "7+ year(s)"}]

               :relations/worktime      [{:id 1 :worktimetype "full time"}
                                         {:id 2 :worktimetype "part time"}
                                         {:id 3 :worktimetype "internship"}]})

(defn getPersonById [id] (->> dbSample
                           (:person)
                           (filter #(= (:id %1) id))))

(defn getPersonByKeywordValuePair [kw value]
  (->> dbSample
    (:person)
    (filter #(= (kw %1) value))))

(->> dbSample
  (:person)
  (filter #(= (:name %1) "tarık"))
  (first)
  (:surname))


(->> dbSample
  (:person)
  (filter #(= (:name %1) "barış"))
  (first)
  (:id))

(->> dbSample
  (:employees/developerteam)
  (first)
  (:frontend)
  (filter #(:id %)))

(defn x [idNum] (->> dbSample
                  (:employees/developerteam)
                  (first)
                  (:devops)
                  (filter #(= (:id %) idNum))
                  (first)
                  (:id)))

; p01: :id keywordleri farklı yerlerde kullanılınca karışıyor gibi. nasıl çözelim?
; namespace ile ön bir ek koyalım:

; :id -> :person/id olsun

(def dbSample {
               :person                  [{:person/id 1 :name "lodos" :surname "eskioğlu" :joindate "01.06.2022" :experience 1 :loyalitylevel 1 :worktime 3 :managers 10}
                                         {:person/id 2 :name "alp" :surname "boriçi" :joindate "04.04.2021" :experience 2 :loyalitylevel 2 :worktime 1 :managers 10}
                                         {:person/id 3 :name "emir" :surname "sürmeli" :joindate "09.01.2022" :experience 3 :loyalitylevel 1 :worktime 2 :managers 10}
                                         {:person/id 4 :name "deniz" :surname "zengin" :joindate "07.08.2020" :experience 2 :loyalitylevel 2 :worktime 1 :managers 10}
                                         {:person/id 5 :name "alphan" :surname "kaplan" :joindate "01.03.2022" :experience 1 :loyalitylevel 1 :worktime 3 :managers 10}
                                         {:person/id 6 :name "barış" :surname "can" :joindate "04.04.2020" :experience 2 :loyalitylevel 2 :worktime 1 :managers 10}
                                         {:person/id 7 :name "ramazan" :surname "nedim" :joindate "04.04.2020" :experience 3 :loyalitylevel 2 :worktime 1 :managers 10}
                                         {:person/id 8 :name "tarık" :surname "derkan" :joindate "04.04.2018" :experience 4 :loyalitylevel 3 :worktime 1 :managers 10}
                                         {:person/id 9 :name "rich" :surname "hickey" :joindate "04.04.2016" :experience 4 :loyalitylevel 5 :worktime 2}
                                         {:person/id 10 :name "mert" :surname "nuhoglu" :joindate "04.04.2018" :experience 4 :loyalitylevel 4 :worktime 1 :managers 9}]

               :employees/developerteam [{:frontend  [{:person/id 1} {:person/id 2} {:person/id 3} {:person/id 4}]
                                          :backend   [{:person/id 5} {:person/id 6}]
                                          :fullstack [{:person/id 7}]
                                          :devops    [{:person/id 8}]}]

               :employees/managers      [{
                                          {:id 1 :person 9} {:id 2 :person 10}}]

               :relations/experience    [{:id 1 :experiencetimeperiod "0-1" :experiencetype "junior"}
                                         {:id 2 :experiencetimeperiod "1-3" :experiencetype "medior"}
                                         {:id 3 :experiencetimeperiod "3-6" :experiencetype "senior"}
                                         {:id 4 :experiencetimeperiod "6+" :experiencetype "lead senior"}]

               :relations/loyalitylevel [{:id 1 :loyalitylevelvalue "0-1 year(s)"}
                                         {:id 2 :loyalitylevelvalue "1-2 year(s)"}
                                         {:id 3 :loyalitylevelvalue "3-5 year(s)"}
                                         {:id 4 :loyalitylevelvalue "5-7 year(s)"}
                                         {:id 5 :loyalitylevelvalue "7+ year(s)"}]

               :relations/worktime      [{:id 1 :worktimetype "full time"}
                                         {:id 2 :worktimetype "part time"}
                                         {:id 3 :worktimetype "internship"}]})

; p02: experience objelerine
; obje deyince herhangi bir map objesini kastediyorum.
; neden obje ile map kavramını birbiri yerine kullanabiliriz?

; js/java gibi dillerdeki objelerin temel özelliği neydi?
; obje = data + fonksiyon (method)
; obje belirli propertyleri (data) olan ve belli fonksiyonları destekleyen bir yapıdır

; map nedir peki?
; key value pairlarının bir kümesi

; peki bizim key-value pairlarımız aslında objenin property-value pairlarına denk gelmiyor mu?
; bir objenin propertysi = bir mapin keyi diye düşünebiliriz
; bu ikisi yerine atribut diyoruz
; tablonun satırı = bir obje = bir map
; tablonun tamamı = class = Map type
; tablonun bir satırı (record) = bir classın instanceı (obje) = bir map

; objelerden bahsedince, birkaç kavramı ayırt etmemiz gerekiyor
; iki tür obje tipinden bahsedebiliriz:
; 1. entity objeleri
; 2. value objeleri

; entity = varlık
; value = değer
; varlık objeleri, sahip olduğu tüm datadan (niteliklerden) bağımsız olarak bir kimliği olan şeylerdir.
; örnek: bir üniversite yönetim sistemini düşünelim
; bir ÜYS'de öğrenci ve öğretmenlerin kayıtları bulunsun
; bir öğrencinin ismi mahkeme kararıyla değişmiş olsun
; bu durumda ben o öğrencinin eski ismi ve yeni ismi için iki farklı kayıt mı oluştururum?
; hayır. direk o öğrencinin kaydında eski ismini update ederiz
; dolayısıyla aslında öğrencinin ismi değişse, varlık olarak öğrenci aynı kalır.
; öğrencinin hangi atributu değişirse değişsin o öğrenci her zaman aynı kimliğe sahiptir.
; öğrencini eski ismi: ayşe. yeni ismi: fatma.

; bir de paradan bahsedelim
; 100 liram var
; 100 lirayı bir obje olarak tutuyorum
; daha sonra diyorum ki, benim artık 200 liram var.
; şimdi 100 lira objesiyle 200 lira objesi birbirinin aynısı mı?
; yukarıdaki durumda ayşe ismi değişse bile fatma ile aynı kişi
; buradaki durumda 100 lira ile 200 lira farklı objeler
; bu para objesinin değerini değiştirdiğin vakit aslında farklı bir objeden bahsediyor olursun
; ayşe/fatma'nın bir kimliği var
; paranın kimliği yok.

; eğer bir şeyin sahip olduğu niteliklerden bağımsız olarak kimliği varsa (öz olarak), o bir varlık objesidir
; eğer bir şey sadece nitelikleriyle (property = atribut) tanımlanıyorsa, o bir değer objesidir

; genel olarak enum türü atributlar, aslında değer objeleridir
; enum: kategorik değer kümeleri

; kümeler var
; kümelerin bazıları varlıkları içeriyor
; bazıları metrik türü değerler içeriyor -> değer objesi (nicel sayısal bir küme)
; bazıları kategorik türde değerler içeriyor -> değer objesi (nitel bir küme)

; kategorik türdeki değer kümeleri:
; bir varlığa benziyor bir yönüyle
; ama bir varlıktan farklı olarak, iki farklı enum objesinin aynılığı/farklılığı sadece değerine bağlı

; bütün bu kavramlar, veri modellemesinde kullandığımız temel kavramlar olduğundan önemli
; varlık objelerini -> veritabanında tablolarla modelleriz
; değer objeleri için -> özel tablo tanımlamayız
; kategorik değerler (enumlar) için -> belki bir tane tablo tanımlanabilir, o da jenerik bir tablo olmalı ve kimlikli varlıklar gibi değerlendirmeyiz

{:relations/experience [{:id 1 :experiencetimeperiod "0-2" :experiencetype "starter"}
                        {:id 2 :experiencetimeperiod "1-3" :experiencetype "medior"}
                        {:id 3 :experiencetimeperiod "3-6" :experiencetype "senior"}
                        {:id 4 :experiencetimeperiod "6+" :experiencetype "lead senior"}]}

; eğer her bir experience objesinin varlığı o objenin değerlerinin değişmesiyle farklılaşabiliyorsa
; bunu varlık olarak tanımlamayalım
; varlık olmayan objelere de :id koymayalım
; farklı bir implementasyon yapalım

; problem çözmede uygulayacağımız birkaç genel yaklaşım prensibi var
; prensip01: hiçbir çözüm mutlak olarak düşünülmemeli
; her çözümün artı ve eksileri olur
; dolayısıyla biz her çözümün artı ve eksilerini araştırmaya açık olalım
; zaman içinde uygulayacağımız best praktisler (standart uygulamalar) değişebilir
; prensip02: bir probleme çözüm ararken mümkünse her zaman en az 2-3 tane alternatif üretmeye çalışalım
; aklımıza ilk gelen çözümle yetinmemeye çalışalım
; en az 2 hatta 3 alternatifimiz olsun
; altenatifleri uygulamak zorunda değiliz
; ama en azından sadece oraya not düşmek için bile olsa o alternatifleri bir not düşelim (yazalım)
; çünkü bu şekilde yaklaşırsak, otomatikman daha açık zihinli olmaya kendimizi programlarız
; standart: her bir alternatifin başına a01 a02 gibi bir ön başlık koyalım
; bu tarz ön başlıklar bizi bu disiplini edinmeye de teşvik eder

; bir disiplini alışkanlık haline getirmeyi kolaylaştıracak yöntemlerimiz olmalı
; standart: kullandığımız her tür problem çözme yaklaşımına bir isim verebilirsek çok iyi olur
; genel olarak her türlü kavramlaştırmaya bir isim vermeye çalışalım
; zihinde bir soyutlama oluşturmasını sağlıyor.
; bu da kalıcılaşmayı sağlıyor o kavramsallaştırmanın
; ayrıca sen o artık ismi bildiğinden, o yöntem üzerine bir şey inşa edeceğin vakit
; tekrar sıfırdan başlamazsın. artık o yöntemi bir veri olarak kabul edip üstüne başka bir yöntem inşa edebilirsin
; ayrıca takım içindeki iletişimi kolaylaştırır

; p02b: bu kategorik objeleri nasıl implemente ederiz map veri yapılarını kullanarak?

; a01: :id dışında bir identifier atribut kullanalım :category gibi
; a02: keyword tanımlayabiliriz.
; çünkü zaten clojureda standart olarak enum kullanılmıyor hiçbir yerde
; onun yerine keyword kullanılıyor.
; a03: jenerik bir map (tablo) oluşturabiliriz tüm enumlar için

; birkaç tane seçenek çıkardıktan sonra, bu seçeneklerden yatkın olduğun
; bir tanesinin en azından ufaktan bir implementasyonunu yapalım

; parantez: genel olarak yaklaşım yöntemlerimiz ve kullandığımız isimler çok önemli
; usul problemleri her zaman diğer problemlerden daha önceliklidir
; prensip03: kelimelerin türkçelerini mi yoksa ingilizcelerini mi kullanacağız?
; benim yaklaşımım şu:
; öncelikle tüm ingilizce kavramların türkçe karşılıklarını bulmaya çalışalım
; bulabiliyorsak, mümkünse her ikisini birden karıştırarak kullanalım
; böylece hepimiz hem ingilizcelerine hem türkçelerine hakim olalım
; çünkü türkçe, gerçekten düşünme dilimiz olduğundan, eğer türkçe kavramları bilirsek,
; çok daha iyi problem çözme kapasitesine sahip oluruz
; triangle mı üçgen mi?
; amerikalı için bile triangle yabancı bir kelimedir, sezgisel değildir
; üçgen sezgisel bir kelimedir. üçgeni bilen bir insan daha önce duymamış olsa bile beşgeni anlayabilir
; ama amerikalı triangle'dan sonra pentagonun ne anlama geldiğini bilemez
; bütün bunlarla birlikte ister istemez bazı terimlerin de düzgün karşılıklarını bulamıyoruz
; örneğin: implementasyon. icra etme, uygulama diyebilirsin.
; uygulamaya alma kelimesini biz başka birçok anlamda da kullandığımızdan bu sefer de karıştırma problemi olabiliyor

; a02 yöntemiyle kategorik objeleri keyword olarak nasıl uygulayabiliriz?
; hemen bir çözüm denemesi yapalım

{:relations/experience {:experience/starter {:experiencetimeperiod "0-2" :experiencetype "starter"}
                        :experience/medior {:experiencetimeperiod "1-3" :experiencetype "medior"}
                        :experience/senior {:experiencetimeperiod "3-6" :experiencetype "senior"}
                        :experience/lead {:experiencetimeperiod "6+" :experiencetype "lead senior"}}}

; bu şekilde yaparsak, bunlara referans verirken nasıl vereceğiz?

{:person/id 9 :name "rich" :experience 4}
; ->
{:person/id 9 :name "rich" :experience :experience/starter}

; artı (pro) ve eksilerini (con) değerlendirelim
; con: vektör yerine map kullanınca, maliyet getirir
; pro: veri alışverişi kolaylaşıyor
; prensip05: genel olarak programlama yaparken, okunabilirlik kriterini en başa koyabiliriz
; yani bir kodu daha sonra okuduğumuzda bunu anlamamızı kolaylaştıran yöntemler,
; diğer faydalara göre daha önemlidir diyebiliriz
; dolayısıyla bir çözüm yaklaşımının artı ve eksilerini değerlendirirken okunabilirlik kriteri ön planda olsun
; pro: çok daha okunabilir veya kolay anlaşılabilir bir veri oluyor

; bu arada string olarak :experiencetype tutmamıza gerek kalmadı
; zaten keywordün kendisi o metni içeriyor

{:relations/experience {:experience/starter {:experiencetimeperiod "0-2"}
                        :experience/medior {:experiencetimeperiod "1-3"}
                        :experience/senior {:experiencetimeperiod "3-6"}
                        :experience/lead {:experiencetimeperiod "6+"}}}

; standart: property veya genel olarak isimlendirmede boşluk yerine ne kullanacağız?
; clojure dünyasında en çok kullanılan notasyon: `-` işareti oluyor

{:relations/experience {:experience/starter {:experience-time-period "0-2"}
                        :experience/medior {:experience-time-period "1-3"}
                        :experience/senior {:experience-time-period "3-6"}
                        :experience/lead {:experience-time-period "6+"}}}

; böylece okunabilirliği biraz daha artırmış oluruz
; buna kebab-case yazım standardı deniyor
