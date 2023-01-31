(ns e09)

; Tarih: 20230124
; Barış'la Clojure Egzersizleri
; rfr: video/20230124-mert-clj-egzersiz-10.mp4

(def db-sample {
                :person [{:person/id 1 :name "lodos" :surname "eskioğlu" :join-date "01.06.2022" :experience :experience/starter :loyality-level :loyality-level/zero-one-years :work-type :work-type/internship :work-time :worktime/part-time :managers {:manager/id 2}}
                         {:person/id 2 :name "alp" :surname "boriçi" :join-date "04.04.2021" :experience :experience/medior :loyality-level :loyality-level/one-two-years :work-type :work-type/tenure :work-time :work-time/full-time :managers {:manager/id 3}}
                         {:person/id 3 :name "emir" :surname "sürmeli" :join-date "09.01.2022" :experience :experience/senior :loyality-level :loyality-level/zero-one-years :work-type :work-type/tenure :work-time :work-time/part-time :managers {:manager/id 2}}
                         {:person/id 4 :name "deniz" :surname "zengin" :join-date "07.08.2020" :experience :experience/medior :loyality-level :loyality-level/one-two-years :work-type :work-type/tenure :work-time :work-time/full-time :managers {:manager/id 3}}
                         {:person/id 5 :name "alphan" :surname "kaplan" :join-date "01.03.2022" :experience :experience/starter :loyality-level :loyality-level/zero-one-years :work-type :work-type/internship :work-time :work-time/full-time :managers {:manager/id 2}}
                         {:person/id 6 :name "barış" :surname "can" :join-date "04.04.2020" :experience :experience/medior :loyality-level :loyality-level/one-two-years :work-type :work-type/tenure :work-time :work-time/full-time :managers {:manager/id 2}}
                         {:person/id 7 :name "ramazan" :surname "nedim" :join-date "04.04.2020" :experience :experience/senior :loyality-level :loyality-level/one-two-years :work-type :work-type/tenure :work-time :work-time/full-time :managers {:manager/id 2}}
                         {:person/id 8 :name "tarık" :surname "derkan" :join-date "04.04.2018" :experience :experience/lead :loyality-level :loyality-level/three-five-years :work-type :work-type/tenure :work-time :work-time/full-time :managers {:manager/id 2}}
                         {:person/id 9 :name "rich" :surname "hickey" :join-date "04.04.2016" :experience :experience/lead :loyality-level :loyality-level/more-than-seven-years :work-type :work-type/tenure :work-time :work-time/part-time}
                         {:person/id 10 :name "mert" :surname "nuhoglu" :join-date "04.04.2018" :experience :experience/lead :loyality-level :loyality-level/five-seven-years :work-type :work-type/tenure :work-time :work-time/full-time :managers {:manager/id 1}}
                         {:person/id 11 :name "shelby" :surname "syncho" :join-date "04.04.2016" :experience :experience/lead :loyality-level :loyality-level/five-seven-years :work-type :work-type/tenure :work-time :work-time/full-time :managers {:manager/id 2}}]

                :employees/developer-team [{:frontend [[:person/id 1] [:person/id 2] [:person/id 3] [:person/id 4]]
                                            :backend [[:person/id 5] [:person/id 6]]
                                            :fullstack [[:person/id 7]]
                                            :devops [[:person/id 8]]}]

                :employees/managers [{:manager/id 1 :manager/person [:person/id 9]}
                                     {:manager/id 2 :manager/person [:person/id 10]}
                                     {:manager/id 3 :manager/person [:person/id 11]}]


                :relations/experience [:experience/starter
                                       :experience/medior
                                       :experience/senior
                                       :experience/lead]


                :relations/loyality-level [:loyality-level/zero-one-years
                                           :loyality-level/one-two-years
                                           :loyality-level/three-five-years
                                           :loyality-level/five-seven-years
                                           :loyality-level/more-than-seven-years]

                :relations/work-time #{:work-time/full-time :worktime/part-time}
                :relations/work-type #{:work-type/internship :work-type/tenure}})



(defn get-person-by-id "get person by :person/id value" [id]
  (->> db-sample
    (:person)
    (filter #(= (:person/id %1) id))))

(comment
  (get-person-by-id 3))
;=>
;({:loyality-level :loyality-level/zero-one-years,
; :join-date "09.01.2022",
; :name "emir",
; :experience :experience/senior,
; :surname "sürmeli",
; :work-time :work-time/part-time,
; :managers #:manager{:id 2},
; :work-type :work-type/tenure,
; :person/id 3}))

(defn get-manager-person-id-by-manager-id "get a manager name my given manager id" [id]
  (->> db-sample
    (:employees/managers)
    (filter #(= (:manager/id %) id))
    (first)
    (:manager/person)
    (second)))

(comment
  (get-manager-person-id-by-manager-id 2))
;=> 10)

(defn get-person-name-by-id "get a person name by given id" [id]
  (->> db-sample
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
  (->> db-sample
    (:employees/developer-team)
    (first)
    (:frontend)))

(comment
  (get-frontend-developer-team-members-ids))
;=> [[:person/id 1] [:person/id 2] [:person/id 3] [:person/id 4]]

(defn get-person-by-keyword-value-pair "get person by giving keyword and value pair" [kw value]
  (->> db-sample
    (:person)
    (filter #(= (kw %1) value))))

(comment
  (get-person-by-keyword-value-pair :name "lodos"))
;=>
;({:loyality-level :loyality-level/zero-one-years,
; :join-date "01.06.2022",
; :name "lodos",
; :experience :experience/starter,
; :surname "eskioğlu",
; :work-time :worktime/part-time,
; :managers #:manager{:id 2},
; :work-type :work-type/internship,
; :person/id 1}))

(defn get-person-by-name-by-surname "get person surname by given name" [name]
  (->> db-sample
    (:person)
    (filter #(= (:name %1) name))
    (first)
    (:surname)))


(defn get-person-id-by-given-name "get person id by given name" [name]
  (->> db-sample
    (:person)
    (filter #(= (:name %1) name))
    (first)
    (:person/id)))

(get-manager-person-id-by-manager-id 1)
;=> 9

(get-person-by-id 3)
;=>
;({:person/id 3,
; :name "emir",
; :surname "sürmeli",

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
;=> verilen takımdaki kullanıların isimlerini getirip isimlerin karşısına managerlerini yazdıralım.

(comment
  (->> (map second (get-frontend-developer-team-members-ids))
    (map get-person-manager-name-by-person-id)))
;=> ("mert" "shelby" "mert" "shelby")

(defn employee-names [] (->>
                          (map second (get-frontend-developer-team-members-ids))
                          (map get-person-name-by-id)))

(comment
  (employee-names))
;=> ("lodos" "alp" "emir" "deniz")

(defn manager-names [] (->>
                         (map second (get-frontend-developer-team-members-ids))
                         (map get-person-manager-name-by-person-id)))
(comment)
(manager-names)
;=> ("mert" "shelby" "mert" "shelby")

(defn get-employee-name-and-their-managers [] (->>
                                                (map vector (employee-names) (manager-names))
                                                (map #(str "employee/manager: " %))
                                                (print)))
(comment)
(get-employee-name-and-their-managers)
;(employee/manager: [lodos mert] employee/manager: [alp shelby] employee/manager: [emir mert] employee/manager: [deniz shelby])Loaded

(defn get-employee-name-and-their-managers-map [] (->>
                                                    (zipmap (employee-names) (manager-names))
                                                    (map #(str "employee/manager: " %))))
(comment
  (get-employee-name-and-their-managers-map)
;("employee/manager: [\"lodos\" \"mert\"]"
;  "employee/manager: [\"alp\" \"shelby\"]"
;  "employee/manager: [\"emir\" \"mert\"]"
;  "employee/manager: [\"deniz\" \"shelby\"]")
  (zipmap (employee-names) (manager-names))
;=> {"lodos" "mert", "alp" "shelby", "emir" "mert", "deniz" "shelby"}
  (into [] (zipmap (employee-names) (manager-names))))
;=> [["lodos" "mert"] ["alp" "shelby"] ["emir" "mert"] ["deniz" "shelby"]]

; p01:
; [["lodos" "mert"] ["alp" "shelby"] ["emir" "mert"] ["deniz" "shelby"]]
; biz bu datayı şu formata (biçime) çevirmek istiyoruz:
; hedef:
; [{:employee "lodos", :manager "mert"}
;  {:employee "alp", :manager "shelby"}
;  ...]

; bir veri modeli veya veri yapısının biçimini (şekil, shape) değiştirmek işlemine, transformasyon denir.
; transformasyon: biçimini çevirmek anlamına gelir latince/yunancada
; prensip: transformasyon yaparken, önce hedef biçimi koment olarak yaz
; bunu direk yazılı olarak görmek zihninin daha iyi odaklanmasını sağlar
; başına da girdi biçimini yaz
; şablon:
; - girdi biçim
; - hedef biçim

; p02:
; hedef:
; "employee: lodos manager: mert"
(comment
  (->>
    (zipmap (employee-names) (manager-names)))
  ;=> {"lodos" "mert", "alp" "shelby", "emir" "mert", "deniz" "shelby"}

  ; girdi:
  ;=> {"lodos" "mert", "alp" "shelby", "emir" "mert", "deniz" "shelby"}
  ; çıktı:
  ; ["employee: lodos manager: mert"
  ;  "employee: alp manager: shelby"
  ;  ..]
  (->>
    (zipmap (employee-names) (manager-names))
    (map #(str "employee: " (first %) " manager: " (second %))))
  ;("employee: lodos manager: mert"
  ;  "employee: alp manager: shelby"
  ;  "employee: emir manager: mert"
  ;  "employee: deniz manager: shelby")

  ; bu dönen objeyi seqden liste çevirmek için `into` kullanacağız
  (->>
    (zipmap (employee-names) (manager-names))
    (map #(str "employee: " (first %) " manager: " (second %)))
    (into [])))
  ;["employee: lodos manager: mert"
  ; "employee: alp manager: shelby"
  ; "employee: emir manager: mert"
  ; "employee: deniz manager: shelby"]

(map #(vector (first %) (* 2 (second %)))
  {:a 1 :b 2 :c 3})
;;=> ([:a 2] [:b 4] [:c 6])

(into (sorted-map) [ [:a 1] [:c 3] [:b 2]])
;=> {:a 1, :b 2, :c 3}

(zipmap [:a :b :c :d :e] [1 2 3 4 5])
;=> {:a 1, :b 2, :c 3, :d 4, :e 5}


; clojuredaki tüm expressionlara bir form diyoruz.
; expression = ifade
; form: tüm expressionlar birer formdur, ama başka şeyler de form olabilir.
; expression: herhangi bir value değer dönen formlardır
; (+ 5 3) bu form, bir ifadedir. niye çünkü 8 değerini dönüyor.
; (comment) formu içinde kullandığın ifadeler, `Load File` yaptığın vakit veya başka bir deyişle bu dosyayı run ettiğin vakit
; çalıştırılmazlar. neden? çünkü bunlar commenttir. çalıştırılmaması için yazılmıştır.
; dolayısıyla `def` makrosuyla tanımladığımız isim tanımlama formlarını `comment` içine koymayız
; fakat bir def ile tanımladığımız bir fonksiyonu çağırırken (call, invoke, invocation), bunu genellikle comment içine
; koyarız, sonucunun ne olduğunu görmek için.

; parinfer örneği:
(comment)
(get-employee-name-and-their-managers)
; şu an alttaki form comment formu içinde değil
; alttaki ifadenin başına imleçle geliyorum ve iki defa spacee basıyorum
(comment
  (get-employee-name-and-their-managers))
; otomatik commentin içine koydu intellij. bunu yapmasını sağlayan özellik, parinfer (pareditin bir varyantı)
; Parinferin detaylı örneklerle anlatımı: [Parinfer - simpler Lisp editing](https://shaunlebron.github.io/parinfer/)
; Parinfer modunu intellij'de açmak için:
; Go to Settings → Editor → General → Smart Keys → Clojure and choose "Parinfer Smart mode" from the "Structural editing style" dropdown.
; [Cursive (Clojure IDE) supports Parinfer out of the box – Matias Kinnunen](https://mtsknn.fi/blog/cursive-parinfer/)

; as-> makrosunu inceleyelim
(as-> 0 n
  (inc n)  ; n is 0 here passed from first parameter to as->
  (inc n)) ; n is 1 here passed from result of previous inc expression
;;=> 2
(comment
  (def n 0)
  (-> n
    (inc)
    (inc)))
; `macroexpand`
(macroexpand '(as-> 0 n
                (inc n)  ; n is 0 here passed from first parameter to as->
                (inc n)))
;=> (let* [n 0 n (inc n)] (inc n))

; deref açıklaması
; clojureda kullandığımız tüm şu ana kadarki objeler değiştirilemez (immutable) objeydi
; ne anlama geliyor bu?
; yani bu objeyi bir kere oluşturduktan sonra içeriğini değiştiremezsin
; objeye bağladığın ismi değiştirebilirsin, o objenin içeriğini değiştiremezsin
; bunun birçok faydası var
; çok nadir de olsa değiştirilebilir objelere de ihtiyacımız var
; nerede ihtiyacımız oluyor?
; illa ki, bütün yazılımlarda side-effect olmak zorunda
; o yan etkiler için değiştirilebilir objelere ihtiyaç var
; clojureda değiştirilebilir objelerin birkaç türü var:
; bunlardan en yaygın kullanılanı: atom

; a isminde bir atom (obje) oluşturuyorum
(def a (atom 1))
;; => #'user/a

; burada da a objesinin içeriğini çekiyorum
(deref a)
;; => 1

(def b 1)
; normal bir objenin içeriğine erişmek için bir şey yapmama gerek yok
(print b)
;1=> nil
(print (deref a))
;1=> nil

; birkaç farklı değiştirilebilir obje olmasının sebebi,
; concurrency (eş zamanlı erişim) probleminden çıkıyor

; p03:
(def db-sample {
                :person                  [{:person/id 1 :name "dan" :surname "stone" :joindate "01.06.2022" :experience :experience/starter :loyalitylevel :loyality-level/zero-one-years :worktime :worktime/internship :managers {:manager/id 1}}
                                          {:person/id 2 :name "dave" :surname "jhons" :joindate "04.04.2021" :experience :experience/medior :loyalitylevel :loyality-level/one-two-years :worktime :worktime/full-time :managers {:manager/id 1}}
                                          {:person/id 3 :name "patrick" :surname "square pant" :joindate "09.01.2022" :experience :experience/senior :loyalitylevel :loyality-level/zero-one-years :worktime :worktime/part-time :managers {:manager/id 1}}
                                          {:person/id 9 :name "rich" :surname "hickey" :joindate "04.04.2016" :experience :experience/lead :loyalitylevel :loyality-level/more-than-seven-years :worktime :worktime/part-time}]


                :employees/developerteam [{:frontend  [[:person/id 1] [:person/id 2] [:person/id 3]]}]


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


(defn inner-join [[& {from :from alias1 :as}] & clauses]
  (reduce (fn [join-agg [next-table & {:keys [as on]}]]
            (for [a join-agg
                  b next-table
                  :let [res (assoc a as b)]
                  :when (on res)]
              res))
    (map #(hash-map alias1 %) from)
    clauses))
(inner-join
  [:from (get-in db-sample [:employees/developerteam 0 :frontend])
   :as :frontender]
  [(:person db-sample)
   :as :person
   :on (fn [{:keys [frontender person]}]
         (= (second frontender) (:person/id person)))])
;=>
({:person {:person/id 1,
           :name "dan",
           :surname "stone",
           :joindate "01.06.2022",
           :experience :experience/starter,
           :loyalitylevel :loyality-level/zero-one-years,
           :worktime :worktime/internship,
           :managers #:manager{:id 1}},
  :frontender [:person/id 1]}
 {:person {:person/id 2,
           :name "dave",
           :surname "jhons",
           :joindate "04.04.2021",
           :experience :experience/medior,
           :loyalitylevel :loyality-level/one-two-years,
           :worktime :worktime/full-time,
           :managers #:manager{:id 1}},
  :frontender [:person/id 2]}
 {:person {:person/id 3,
           :name "patrick",
           :surname "square pant",
           :joindate "09.01.2022",
           :experience :experience/senior,
           :loyalitylevel :loyality-level/zero-one-years,
           :worktime :worktime/part-time,
           :managers #:manager{:id 1}},
  :frontender [:person/id 3]})

(def b 1)
(pr b)
;1=> nil
(print b)
;1=> nil
(identity b)
;=> 1
(+
  (identity b)
  2)
;=> 3
#_(+
    (pr b) ; nil
    2)
; error
#_(+ nil 2)
; error

; `#_` discard karakteri. bunu hangi formun başına koyarsanız, o form komentlenmiş olur
; [Clojure - Reading Clojure Characters](https://clojure.org/guides/weird_characters#_discard)

; q: identity fonksiyonuna ne gerek var? zaten b'nin kendisi o değere sahip
(identity b)
b
; bu şekilde yapınca "Send to REPL" komutunu veremiyorum.
; çünkü bu komut sadece fonksiyon çağrıları için yapılabiliyor
#_(b)
; bu şekilde yaparsak da olmaz, çünkü bu durumda b'nin bir fonksiyon olması gerekir
; o yüzden hata verir
