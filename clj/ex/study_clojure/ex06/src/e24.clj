(ns e24)

; Tarih: 20230407
; Konu: Ahmet'le Çalışma
; Video: 20230407-mert-clj-egzersiz-55-e24-threading-macro-debug.mp4

; Kaynak: file1.clj
;;Tarih: 20230403

#_(def database {:students            [{:student/id  1  :name "ahmet" :surname "caliskan" :number  31065 :hometown "samsun" :study "computerscience" :teachers {:teacher/id 1}}
                                       {:student/id  2  :name  "asli" :surname "dogu" :number  31277 :hometown "canakkale" :study "ındustrialengineering" :teachers {:teacher/id 1}}
                                       {:student/id  3  :name  "melih" :surname "sahinbas" :number  31317 :hometown "rize" :study "computerscience" :teachers {:teacher/id 2}}
                                       {:student/id  4  :name  "faruk" :surname "keskin" :number  31450 :hometown "samsun" :study "materialengineering" :teachers {:teacher/id 3}}]}

    {:teachers          [{:teacher/id 1 :name  "resat" :surname "halici" :experience 13 :hometown "sakarya" :teach "cs201" :faculties {:faculty/id 1}}
                         {:teacher/id 2 :name  "salih" :surname "top" :experience 8 :hometown "düzce" :teach "al102" :faculties {:faculty/id 2}}
                         {:teacher/id 3 :name  "cagri" :surname "erdogan" :experience 18 :hometown "samsun" :teach "hist192" :faculties {:faculty/id 3}}]}

    {:faculties [{:faculty/id 1 :name "fens" :departments 6}
                 {:faculty/id 2 :name "fass" :departments 4}
                 {:faculty/id 3 :name "fman" :departments 1}]})
;Error: Too many arguments to def

; Hatanın sebebi, birden çok map objesine tek bir isim vermeye çalışmışız yukarıda

; a01: Yukarıdaki 3 ayrı map objesini tek bir map içinde birleştir
(def database
  {:students            [{:student/id  1  :name "ahmet" :surname "caliskan" :number  31065 :hometown "samsun" :study "computerscience" :teachers {:teacher/id 1}}
                         {:student/id  2  :name  "asli" :surname "dogu" :number  31277 :hometown "canakkale" :study "ındustrialengineering" :teachers {:teacher/id 1}}
                         {:student/id  3  :name  "melih" :surname "sahinbas" :number  31317 :hometown "rize" :study "computerscience" :teachers {:teacher/id 2}}
                         {:student/id  4  :name  "faruk" :surname "keskin" :number  31450 :hometown "samsun" :study "materialengineering" :teachers {:teacher/id 3}}]
   :teachers          [{:teacher/id 1 :name  "resat" :surname "halici" :experience 13 :hometown "sakarya" :teach "cs201" :faculties {:faculty/id 1}}
                       {:teacher/id 2 :name  "salih" :surname "top" :experience 8 :hometown "düzce" :teach "al102" :faculties {:faculty/id 2}}
                       {:teacher/id 3 :name  "cagri" :surname "erdogan" :experience 18 :hometown "samsun" :teach "hist192" :faculties {:faculty/id 3}}]
   :faculties [{:faculty/id 1 :name "fens" :departments 6}
               {:faculty/id 2 :name "fass" :departments 4}
               {:faculty/id 3 :name "fman" :departments 1}]})

; a02: 3 tane farklı isim verebilirsin her bir map için:
(def students
  {:students            [{:student/id  1  :name "ahmet" :surname "caliskan" :number  31065 :hometown "samsun" :study "computerscience" :teachers {:teacher/id 1}}
                         {:student/id  2  :name  "asli" :surname "dogu" :number  31277 :hometown "canakkale" :study "ındustrialengineering" :teachers {:teacher/id 1}}
                         {:student/id  3  :name  "melih" :surname "sahinbas" :number  31317 :hometown "rize" :study "computerscience" :teachers {:teacher/id 2}}
                         {:student/id  4  :name  "faruk" :surname "keskin" :number  31450 :hometown "samsun" :study "materialengineering" :teachers {:teacher/id 3}}]})
(def teachers
  {:teachers          [{:teacher/id 1 :name  "resat" :surname "halici" :experience 13 :hometown "sakarya" :teach "cs201" :faculties {:faculty/id 1}}
                       {:teacher/id 2 :name  "salih" :surname "top" :experience 8 :hometown "düzce" :teach "al102" :faculties {:faculty/id 2}}
                       {:teacher/id 3 :name  "cagri" :surname "erdogan" :experience 18 :hometown "samsun" :teach "hist192" :faculties {:faculty/id 3}}]})
(def faculties
  {:faculties [{:faculty/id 1 :name "fens" :departments 6}
               {:faculty/id 2 :name "fass" :departments 4}
               {:faculty/id 3 :name "fman" :departments 1}]})

; a01 biraz daha kolaylık sağlayabilir, ilerideki örneklerin kullanımında.

(def p1
  (->> database
    (:students)
    (filter #(-> (:name %1) (= "asli"))) (first)))

; p1 ismini def ile tanımladığımızdan REPL bize doğrudan değerini (içeriğini) vermez
; değerini görmek için identity içine sararız
(identity p1)
; =>
;{:student/id 2,
; :name "asli",
; :surname "dogu",
; :number 31277,
; :hometown "canakkale",
; :study "ındustrialengineering",
; :teachers #:teacher{:id 1}}

; bu fonksiyonun ne yapmasını bekliyorsun?
; ismi "asli" olan kişinin tüm kv ikililerini çıkarmasını bekliyorsun

; bir fonksiyon beklediğin gibi çalışmıyorsa, onu debug etmen gerekiyor
; debug etmek için de, rich comment blokları içinde adım adım işlem yapman gerekiyor
; rfr: 20230405-mert-clj-egzersiz-54-veri-analiz-problemi-d01.mp4

(comment
  ; bu comment formunun içine rich comment bloku deriz
  ; ilk adım olarak yukarıdaki çağırdığın ifadedeki ilk işlem adımı bulmak gerek
  (def p1
    (->> database
      (:students)  ; 1. adım
      (filter #(-> (:name %1) (= "asli")))  ; 2. adım
      (first)))  ; 3. adım

  ; debug kuralı:
  ; her bir ifadenin sonucunu hemen o ifadenin altına kopyala yapıştır
  ; Literate programming
  ; https://www.google.com/search?q=literate+programming&sourceid=chrome&ie=UTF-8

  ; 1. adımı test edelim
  (:students database)
  ; =>
  ;[{:student/id 1,
  ;  :name "ahmet",
  ;  :surname "caliskan",
  ;  :number 31065,
  ;  :hometown "samsun",
  ;  :study "computerscience",
  ;  :teachers #:teacher{:id 1}}
  ; ...

  ; 1. adımın çıktısı, 2. adımın girdisi olacak, çünkü threading macro ->> böyle çalışır
  ; dolayısıyla 1. adımın çıktısına bir isim verelim
  (def d01 (:students database))

  ; 2. adım
  (filter #(-> (:name %1) (= "asli")) d01)
  ; =>
  ;({:student/id 2,
  ;  :name "asli",
  ;  :surname "dogu",
  ;  :number 31277,
  ;  :hometown "canakkale",
  ;  :study "ındustrialengineering",
  ;  :teachers #:teacher{:id 1}})

  (def d02 (filter #(-> (:name %1) (= "asli")) d01))

  ; 3. adım
  (first d02)
  ; =>
  ;{:student/id 2,
  ; :name "asli",
  ; :surname "dogu",
  ; :number 31277,
  ; :hometown "canakkale",
  ; :study "ındustrialengineering",
  ; :teachers #:teacher{:id 1}}

  ; 2. ve 3. adım arasındaki fark:
  ; d02'de bir listenin içinde bir map vardı
  ; d03'de wrap eden listeyi kaldırdık. sadece map objesini döndük.


  ; end
  ,)


#_(->> database [(:students) 0])
;Wrong number of args passed to keyword: :students

(comment
  (->> database
    [(:students) 0])
  ; bu ifade aslında şuna denktir:

  ([(:students) 0] database)
  ; bu ifade de hatalı, ama yukarıdaki hata şu ifadeden kaynaklanıyor:

  (:students)
  ;Wrong number of args passed to keyword: :students

  ; Hatanın sebebi: :students keyword fonksiyonuna bir map objesini argüman olarak vermen gerekirdi.
  ; eksik argüman var bu fonksiyon çağrısında

  (:students database)
  ; =>
  ;[{:student/id 1,
  ;  :name "ahmet",
  ;  :surname "caliskan",
  ;  :number 31065,
  ;  :hometown "samsun",
  ;  :study "computerscience",
  ;  :teachers #:teacher{:id 1}}

  ; threading macro olmadan önce doğrusunu yazalım
  (get (:students database) 0)
  ; {:student/id 1,
  ; :name "ahmet",
  ; :surname "caliskan",
  ; :number 31065,
  ; :hometown "samsun",
  ; :study "computerscience",
  ; :teachers #:teacher{:id 1}}

  ; threading macro ile bunu yazalım
  (-> database
    (:students)
    (get 0))
  ; {:student/id 1,
  ; :name "ahmet",
  ; :surname "caliskan",
  ; :number 31065,
  ; :hometown "samsun",
  ; :study "computerscience",
  ; :teachers #:teacher{:id 1}}

  ; q: Threading first -> ve threading last ->> macro arasındaki fark ne?
  ; rfr: fn/syntax/threading_macro.clj

  ; end
  ,)

#_(->> database ((:students) 0))
;Wrong number of args passed to keyword: :students

(comment
  (->> database ((:students) 0))
  ; ->
  ; a01: önce macro olmadan yapalım
  (get database :students)
  ; =>
  ;[{:student/id 1,
  ;  :name "ahmet",
  ;  :surname "caliskan",
  ;  :number 31065,
  ;  :hometown "samsun",
  ;  :study "computerscience",
  ;  :teachers #:teacher{:id 1}}

  ; a01.1
  (get (database :students) 0)
  ; =>
  ;{:student/id 1,
  ; :name "ahmet",
  ; :surname "caliskan",
  ; :number 31065,
  ; :hometown "samsun",
  ; :study "computerscience",
  ; :teachers #:teacher{:id 1}}

  ; a01.2
  (get (get database :students) 0)
  ; =>
  ;{:student/id 1,
  ; :name "ahmet",
  ; :surname "caliskan",
  ; :number 31065,
  ; :hometown "samsun",
  ; :study "computerscience",
  ; :teachers #:teacher{:id 1}}

  ; a02: şimdi de threading first macro ile yapalım
  (-> database
    (get :students))
  ; =>
  ;[{:student/id 1,
  ;  :name "ahmet",
  ;  :surname "caliskan",
  ;  :number 31065,
  ;  :hometown "samsun",
  ;  :study "computerscience",
  ;  :teachers #:teacher{:id 1}}

  (-> database
    (get :students)
    (get 0))
  ; =>
  ;{:student/id 1,
  ; :name "ahmet",
  ; :surname "caliskan",
  ; :number 31065,
  ; :hometown "samsun",
  ; :study "computerscience",
  ; :teachers #:teacher{:id 1}}


  ; a03: şimdi de threading last macro ile yapalım
  #_(->> :students
      (get database)
      (get 0))
  ; olmuyor

  ; end
  ,)

(->> database
  (:students)
  (filter #(-> (:name %1) (= "asli"))))
;;=>
;({:student/id 2,
;  :name "asli",
;  :surname "dogu",
;  :number 31277,
;  :hometown "canakkale",
;  :study "ındustrialengineering",
;  :teachers #:teacher{:id 1}})

(->> p1
  (:teachers))
;=> #:teacher{:id 1}

(get p1 :name)
;=> "asli"

(set '(1 1 2 2 3 3 4 4 5 5))
;;=> #{1 4 3 2 5}
;; q3 neden bu sıralamada bir set oluşturuyoruz
; set ve {} (hashmap) objelerinde sıralama garantisi yoktur
; sıralamanın tamamen rassal olacağını varsayabilirsin


