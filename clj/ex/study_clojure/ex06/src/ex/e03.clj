(ns ex.e03)

; Tarih: 20230117
; Barış'ın Clojure örnek alıştırmaları:
; rfr: video/20230117-mert-clj-egzersiz-03.mp4

(comment
  ;örnek bir map oluşturalım ve içerisine bir vector ekleyelin.
  (def mapExp {
               :anil        "sahbaz" :onur "sultan sahbaz" :baris "ring" :efe "katyusha"
               :onur/server ["alpha" "secura" "roxa"]
               :levelSystem [{:onur/level 19 :baris/level 99 :anil/level 33 :efe/level 6}]})

  ;e1.0- en basitinden başlayarak key kullanaraktan "anil" isimli kullanın valuesunu getirelim.
  (mapExp :anil)
  ;=> "sahbaz"
  ;e2.0- value değerini kullanaraktan "anil" isimli kullanın valuesunu getirelim.
  (:anil mapExp)
  ;=> "sahbaz"
  ;("sahbaz" mapExp)
  ;=>Execution error (ClassCastException) çünkü sadece keywordler value çağırabilir.


  ;e3.0- value değeri verilen bir kullanıcının key value pairi döndürelim.
  (filter
    #(= (second %) "ring")
    mapExp)
  ;=> ([:baris "ring"])
  (filter
    #(= (second %) "sultan sahbaz")
    mapExp)

  ;e4.0- value değeri verilen kullanıcının sadece key değerini döndürelim.
  ;burada sadece keys functionını ekleyerek gelen key value parinin keyini görebiliriz.
  (keys (filter
          #(= (second %) "sultan sahbaz")
          mapExp))
  ;=> nil
  ;e5.0- levelSystem value değeri verilen key value çiftini döndürelim.
  (keys (filter
          #(= (second %) 99) (get-in mapExp [:levelSystem 0])))
  ;=> nil
  (keys (filter
          #(>(second %) 9) (get-in mapExp [:levelSystem 0])))
  ;=> nil
  (keys (filter
          #(<(second %) 55) (get-in mapExp [:levelSystem 0])))
  ;=> (:onur/level :anil/level :efe/level)

  ; p01: Data structure incelemesi

  (def mapExp {
               :anil        "sahbaz" :onur "sultan sahbaz" :baris "ring" :efe "katyusha"
               :onur/server ["alpha" "secura" "roxa"]
               :levelSystem [{:onur/level 19 :baris/level 99 :anil/level 33 :efe/level 6}]}
    ,))

; Yukarıdaki kodları inceleyelim.

; önce veri modellemesi konusuna girelim
; çünkü veri modellemesini yaparsak, programlama tarafında iş yükümüz ciddi anlamda azalır
; fp'ciler der ki: veri yapısı, algoritmadan çok daha önemli
; sen eğer veri yapısını doğru belirlersen, algoritman otomatikman ortaya çıkar

; :anil        "sahbaz" :onur "sultan sahbaz" :baris "ring" :efe "katyusha"
; şimdi burada bir ikili listesi görüyorum
; ikili = two-tuple
; ikiliyle key-value pair arasında çok temel bir fark var
; kv pairlarda: her bir key, farklı bir atribut gibi düşünülmeli
; fakat ikili listesi olunca, aslında her bir ikilide aynı iki tane atribut bulunduğunu varsayarız.

; isim-soyisim listesi düşünelim
; kv pair olarak yaparsak:
; {:ali "kaymazcı" :veli "bozacı"}
; peki bunu ikili olarak yaparsak ne olur:
; ["ali" "kaymazcı"] ["veli" "bozacı"]
; aslında biz burada bir tablo olarak düşünürüz bunu sezgisel olarak
; | isim   | soyadı   |
; | ali    | kaymazcı |
; | veli   | bozacı   |
; bu yapı, ikili listesi olarak modellenebilir. her bir satır bir ikili olur.
; veya, her bir satırı bir map objesiyle modelleriz.
; map = kv pair listesi
; {:isim "ali" :soyadı "kaymazcı"}
; {:isim "veli" :soyadı "bozacı"}
; bu şekilde yaparsak uygun

; ama şu şekilde yaparsak sıkıntılı:
; :ali "kaymazcı" :veli "bozacı"

; neden ikinci format sıkıntılı?
; 1. veriye erişimi zorlaştırırız
; senin her bir farklı kişi için farklı key kullandığından, o keylerin hepsini önden biliyor olman lazım
; 2. standardizasyon
; ilk formatta tüm farklı kişiler aynı veri modelini kullanıyoruz
; yani bir map objesi olacak, o map objesinin bir atributu :isim diğer atributu :soyadı olacak
; fakat ikinci formatta aslında bir standart veri modelimiz yok bile
; aynı atributu kullanmıyoruz çünkü her seferinde
; :ali "kaymazcı" :veli "bozacı"
; bir kişi için kullandığımız key (atribut) :ali
; öbür kişi için kullandığımız atribut :veli

(def mapExp {
             :players [{:adi "anil" :soyadi "sahbaz"}
                       {:adi "onur" :soyadi "sultan sahbat"}
                       {:adi "baris" :soyadi "ring"}
                       {:adi "efe" :soyadi "katyusha"}]})

; bu şekilde olunca bu veri yapısını kolaylıkla tarif edebilirim:
; bana bir map ver
; bu mapin içinde bir players listesi olsun
; bu players listesinin her bir öğesi bir başka map olsun
; bu mapin de :adi ve :soyadi diye iki tane atributu olsun

; a02:
; bu mapin içinde bir players listesi olsun
; bu players listesinin her bir öğesi bir playerın ismini ve soyadını içersin

(def mapExp {
             :players [{:adi "anil" :soyadi "sahbaz"}
                       {:adi "onur" :soyadi "sultan sahbat"}
                       {:adi "baris" :soyadi "ring"}
                       {:adi "efe" :soyadi "katyusha"}]
             :servers ["alpha" "secura" "roxa"]
             :levels [{:adi "onur" :level 2}
                      {:adi "baris" :level 15}]})

; q01: bir vektörün içindeki maplere numerik indeksle erişmek yerine bir keyword indeksle erişsek olur mu?
; a01: fulcro denilen bir framework. onun kullandığı standart bu yöntemi takip ediyor.
; her bir map içinde bir id tanımlıyor önce
; sonra o id'yi o mapin indeksi yapıyor.
{1 {:id 1 :adi "anil" :soyadi "sahbaz"}
 2 {:id 2 :adi "onur" :soyadi "sultan sahbat"}
 3 {:id 3 :adi "baris" :soyadi "ring"}
 4 {:id 4 :adi "efe" :soyadi "katyusha"}}

; p02: Mevcut veri yapımızda `levels` verisi aslında gereksiz yere bir tekrar içeriyor
(def mapExp {
             :players [{:adi "anil" :soyadi "sahbaz"}
                       {:adi "onur" :soyadi "sultan sahbat"}
                       {:adi "baris" :soyadi "ring"}
                       {:adi "baris" :soyadi "kırmızı"}
                       {:adi "efe" :soyadi "katyusha"}]
             :servers ["alpha" "secura" "roxa"]
             :levels [{:adi "onur" :level 2}
                      {:adi "baris" :level 15}]})
; ->
(def mapExp {
             :players [{:adi "anil" :soyadi "sahbaz" :level 2}
                       {:adi "onur" :soyadi "sultan sahbat" :level 2}
                       {:adi "baris" :soyadi "ring" :level 15}
                       {:adi "baris" :soyadi "kırmızı"}
                       {:adi "efe" :soyadi "katyusha"}]
             :servers ["alpha" "secura" "roxa"]})
; level bilgisini ayrı bir yerde tutmamıza gerek yok

; p03: Referans verilen bir veri yapısı oluşturalım
(def mapExp {
             :players [{:adi "anil" :soyadi "sahbaz" :level 2}
                       {:adi "onur" :soyadi "sultan sahbat" :level 2}
                       {:adi "baris" :soyadi "ring" :level 15}
                       {:adi "baris" :soyadi "kırmızı"}
                       {:adi "efe" :soyadi "katyusha"}]
             :servers [{:id 1 :name "alpha" :difficulty :hard} {:id 2 :name "secura" :difficulty :easy} {:id 3 :name "roxa" :difficulty :medium}]})
; şimdi her bir oyuncu kendine bir veya birden çok server seçmiş olsun
; hangi zorluk seviyesinde oynacaksa
; şimdi bu oyuncularla serverlar arasındaki ilişkiyi nasıl kuralım?

; a01: her bir oyuncunun hangi serverlarda oynayabileceğini tutalım
{:adi "baris" :soyadi "ring" :level 15 :player/servers [1 3]}
{:adi "baris" :soyadi "kırmızı" :player/servers [2]}

; q02: neden isme referans vermedik?
; a01: yarın bir gün o serverın ismi değişebilir
; a02: bunun daha genel hali. genel olarak identifier (kimlik bilgisi) suni olmalı (surrogate). yani herhangi bir gerçek dünya anlamı içermemeli.
; neden içermemeli?
; çünkü herhangi bir şeyin gerçek dünyada bir anlamı varsa, yarın bir gün o anlam değişebilir
; fakat bir şeyin bir özelliğinin değişmesi, o şeyin kendisinin değişmesini gerektirmez
; öze (zati) ait nitelikler (essential attributes)
; arızi nitelikler (accidental attributes)

; a02: referans edilen objeyi direk referans eden objenin içine koymak (kötü bir çözüm)
{:adi "onur" :soyadi "sultan sahbat" :level 2 :player/servers [{:name "alpha" :difficulty :hard}
                                                               {:name "secura" :difficulty :easy}]}

; q03: Neden bu şekilde yapmanın maliyetleri var?
; a01: Bu şekilde yaparsak veriyi duplike (tekrar) etmiş oluruz.
; q04: Duplikasyon neden zararlı veya kaçınılması gereken bir şey?
; a01: Ekstra data tutuyoruz. Karmaşıklık artıyor.
; a02: Bir veri değiştiği anda birçok yerde değiştirmemiz gerekiyor.
; Her şeyden daha önemli olan esas maliyet bu.
; Bu birçok yazılımdaki temel bugların altında yatan en büyük faktör.
; Ne kadar çok duplikasyon yaparsan, yazılımını o kadar çok öngörülemeyecek hataya maruz bırakırsın.

; Bu tip duplikasyonları insanlar hızlıca işi teslim etmek için genelde temizlemiyor.
; Bu durumda giderek yazılım daha rigid (zor değiştirilebilir) hale geliyor.
; Buna da teknik borç (technical debt) diyoruz.

(def mapExp {
             :players [{:adi "anil" :soyadi "sahbaz" :level 2 :player/servers [2 3]}
                       {:adi "onur" :soyadi "sultan sahbat" :level 2 :player/servers [1 3]}
                       {:adi "baris" :soyadi "ring" :level 15 :player/servers [2 3]}
                       {:adi "baris" :soyadi "kırmızı" :player/servers [1]}
                       {:adi "efe" :soyadi "katyusha" :player/servers [2]}]
             :servers [{:id 1 :name "alpha" :difficulty :hard} {:id 2 :name "secura" :difficulty :easy} {:id 3 :name "roxa" :difficulty :medium}]})

; e01: "anil" isimli kullanıcının soyadını bulalım.
(->> mapExp
  (:players)
  (filter #(= (:adi %1) "anil")))
;=> ({:adi "anil", :soyadi "sahbaz", :level 2, :player/servers [2 3]})
(->> mapExp
  (:players)
  (filter #(= (:adi %1) "anil"))
  (first)
  (:soyadi))
;=> "sahbaz"

; neden burada (first) ile çağrı yaptık?
; çünkü map objesi bir seq içine konulmuş

; q05: sequence'ın türkçedeki karşılığı nedir?
; sequence = list gibi düşünebiliriz
; clojureda iki tane liste benzeri veri yapısı var:
; list
; vektör
; arasındaki fark neydi?
; meşhur CS müfredatında Data Structures dersinde anlatılan bir şey
; list dediğimiz yapı, linked list denir genellikle
; şöyle bir yapı:
; bir obje var. bu objeyi ben bir [] içinde göstereyim
; [5]
; bu 5 sayısından sonra mesela başka bir sayı gelsin
; [5] -> [9]
; bundan sonra da başka bir sayı geliyor
; [5] -> [9] -> [13]
; bu yapının implementasyonu nasıl oluyor?
; aslında Linked List objede iki tane atribut tutuyorsun
; bir tanesi mevcut öğenin değeri oluyor
; öbürü de bir sonraki öğenin adresi oluyor
; [5, x904934]
; bellekte x904934 adresinde [9, x390] diye bir obje var diyelim
; bu objenin de ikinci öğesi bir sonraki listedeki öğeyi tutuyor
; x390 adresinde de [13, nil]
; bu sonuncu öğe olduğu için bunun next item adresi nil/null olabilir
; eğer sen bir listeyi bu şekilde implemente edersen, senin maliyetin ve faydan ne olur?
; böyle bir yapıda, herhangi bir yere veri eklemek çok kolay. çok maliyetsiz.
; fakat maliyeti: istediğimiz herhangi bir öğeye erişmek çok maliyetli
; çünkü ortalamada listenin tüm öğelerinin yarısı kadar öğeyi dolaşmamız gerekiyor.
; liste büyüdükçe bizim bir öğeyi arama bulma maliyetimiz artar

; vektörse, indekslenmiş bir liste
; 5 9 12
; bu vektörün ikinci öğesine erişmek istiyorsan anında erişebiliyorsun
; herhangi bir öğesine erişmen anlık oluyor
; vektörün herhangi bir öğesine anında erişebiliyoruz
; vektörün son elemanı hariç herhangi bir yere ekleme yapmak maliyetli
; arada bir yere ekleme yaptığında, bütün öğelerin indekslerini değiştirmen gerekiyor.

; vektör aslında bir nevi map gibi düşünülebilir
; ama mapin keyleri her zaman numerik ve ardışık olacak şekilde.
; örnek:
; 5 9 12 vektörü aslında şöyle bir maptir:
; 0 5
; 1 9
; 2 12

; en sade ve jenerik yapı linked list olduğundan, ki biz buna sadece list diyoruz clojureda
; clojure en temelde her şeyi list ile başlatır
; ki zaten clojuredaki her bir ifade aslında bir listtir
; (+ 5 3)
; aslında bu ifade bir listtir
; listin üç elemanı vardır
; ilk elemanı: +
; ondan sonra gelen 5
; ondan sonra gelen 3

; clojure bu list yapısının jenerik olarak en genel yapı olmasından dolayı, tüm collection fonksiyonlarının bir jenerik interfaceini yapıyor
; bu interfacedeki fonksiyonlar da genellikle seq (sequence) dönüyor
; aslında bu seq dediğimiz interface de aslında bir list gibi düşünülebilir

; genel bir type hiyerarşisi tanımlayalım:
; hayvanlar canlıdır
; bitkiler de canlıdır
; canlı olmak o zaman bu hayvan ve bitki typelarının bir ortak interfacei olabilir
; seq, list, vektör, map vs. hepsinin ortak interfacei olur.

; seq interface üç tane fonksiyondan oluşur:
; [Clojure - Sequences](https://clojure.org/reference/sequences)
; first, rest, cons
; bu üç fonksiyonu destekleyen tüm typelar aynı zamanda bir seqdir.
; örneğin: map alalım
(def m {:a 1 :b 2})
(first m)
;=> [:a 1]
(rest m)
;=> ([:b 2])
(cons [:c 3] m)
;=> ([:c 3] [:a 1] [:b 2])

; tekrar başa dönelim
(->> mapExp
  (:players)
  (filter #(= (:adi %1) "anil")))
;=> ({:adi "anil", :soyadi "sahbaz", :level 2, :player/servers [2 3]})
; dikkat edersek, filter fonksiyonu bir seq objesi dönüyor
; nereden anlıyoruz
; çünkü `()` içine wrap edilmiş dönen değer
; `()` bizde seq (veya list) anlamına geliyor

; o yüzden biz önce o seqdeki ilk öğeyi almamız gerekiyor
; ki altındaki mape erişebilelim
(->> mapExp
  (:players)
  (filter #(= (:adi %1) "anil"))
  (first))
;=> {:adi "anil", :soyadi "sahbaz", :level 2, :player/servers [2 3]}

; rest ile sonraki öğelere de erişebiliriz
(def xs [3 5 7 11])
(rest xs)
;=> (5 7 11)
(rest (rest xs))
;=> (7 11)
(rest (rest (rest xs)))
;=> (11)
(first (rest (rest (rest xs))))
;=> 11

; q: yukarıdaki listeden 7 öğesini çekmenin yolu nedir?
; kolay bir yolu yok
; filter fonksiyonuyla bütün öğeleri tek tek dolaşmamız gerekiyor
(filter #(= %1 7) xs)
;=> (7)

(->> mapExp
  (:players)
  (filter #(= (:adi %1) "anil"))
  (first)
  (:soyadi))
;=> "sahbaz"
