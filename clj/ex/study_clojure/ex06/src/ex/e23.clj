(ns ex.e23)

; Tarih: 20230407
; Konu: Barış'la postwalk debug

(def my-map {1 {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}})

(def my-vec (into [] (for [m (vals my-map) [k v] m] [(name k) v])))

(defn filter-vec [coll search-str]
  (->> coll
       (clojure.walk/postwalk
         (fn [form]
           (let [form (if (vector? form)
                        (vec (keep identity form))
                        form)]
             (cond
               (and (vector? form)
                    (every? vector? form)) form
               (and (vector? form)
                    (not (every? string? form))) nil
               (and (vector? form)
                    (every? string? form)
                    (->> form
                         (some (fn [str-vec]
                                 (-> str-vec
                                     clojure.string/lower-case
                                     (clojure.string/includes? search-str)))))) form
               :else form))))
       (keep identity)
       vec))

(identity my-vec)
;=> [["id" 1] ["name" "ali"] ["surname" "veli"] ["id" 2] ["name" "batu"] ["surname" "can"]]
(filter-vec my-vec "a")
;=> [["name" "ali"] ["surname" "veli"] ["name" "batu"] ["surname" "can"]]

(comment
  (identity my-vec)
  ;=> [["id" 1] ["name" "ali"] ["surname" "veli"] ["id" 2] ["name" "batu"] ["surname" "can"]]

  ; filter-vec oldukça uzun bir fonksiyon
  ; ilk etapta bunun argümanlarının isimleriyle bizim gönderdiğimiz girdi değişkenlerini uyumlu hale getirelim
  ; 1. adım:
  (def coll my-vec)
  (def search-str "a")

  ; filter-vec içindeki ilk çağrı (invocation), postwalk fonksiyonunun çağrısı
  ; fakat bu oldukça uzun bir ifade
  ; içinde uzun bir anonim fonksiyon var
  ; biz ilk etapta bu anonim fonksiyonu daha okunabilir bir hale getirelim
  ; önce ona bir isim verelim

  (defn f [form]
    (let [form (if (vector? form)
                 (vec (keep identity form))
                 form)]
      (cond
        (and (vector? form)
          (every? vector? form)) form
        (and (vector? form)
          (not (every? string? form))) nil
        (and (vector? form)
          (every? string? form)
          (->> form
            (some (fn [str-vec]
                    (-> str-vec
                      clojure.string/lower-case
                      (clojure.string/includes? search-str)))))) form
        :else form)))

  ; search-str objesi f içinde kullanılmış, ama argüman olarak gelmiyor, dışarıdan geliyor
  ; dolayısıyla f fonksiyonu şu an side-effect içeriyor

  ; threading-macro'nun ilk ifadesi bu:
  (clojure.walk/postwalk f coll)
  ;=> [["name" "ali"] ["surname" "veli"] ["name" "batu"] ["surname" "can"]]

  ; postwalk imzası:
  ; (postwalk f form)
  ; dolayısıyla bizim coll objemiz form ismini alıyor, postwalk içinde
  ; biz de aynı şekilde isimlendirelim
  (def form coll)

  (f form)
  ;=> [["id" 1] ["name" "ali"] ["surname" "veli"] ["id" 2] ["name" "batu"] ["surname" "can"]]

  ; q: (f form) form objesini birebir döndü. Halbuki içinde search-str ile filtreleme yapması gerekiyordu. neden böyle oldu?

  (comment
    ; f fonksiyonunu debug edelim
    ; f'in ilk işlemi, let ile yeni bir isim tanımlamak
    ;     (let [form (if (vector? form)
    ;                 (vec (keep identity form))
    ;                 form)]
    ; burada sorun şu: yeni isim mevcut isimle aynı
    ; dolayısıyla yeni isim eski ismi overwrite ediyor
    ; bu istemediğimiz bir şey
    ; çünkü anlaşılmayı zorlaştırır
    ; biz burada yeni isim için form2 diyelim
    (def form2
      (if (vector? form)
        (vec (keep identity form))
        form))
    (identity form2)
    ;=> [["id" 1] ["name" "ali"] ["surname" "veli"] ["id" 2] ["name" "batu"] ["surname" "can"]]

    ; şimdi (cond ..) ifadesini inceleyelim
    (cond
      (and (vector? form2) (every? vector? form2)) form2
      (and (vector? form2) (not (every? string? form2))) nil
      (and (vector? form2) (every? string? form2)
        (->> form2
          (some (fn [str-vec]
                  (-> str-vec
                    clojure.string/lower-case
                    (clojure.string/includes? search-str)))))) form2
      :else form2)

    ; burada 3 farklı koşul durumu var, bizdeki form2 objesi bunlardan hangisini tutuyor?
    (vector? form2)
    ;=> true
    (every? vector? form2)
    ;=> true
    (not (every? string? form2))
    ;=> true
    (every? string? form2)
    ;=> false

    ; demek ki, hem 1. dal (branch) hem 2. dal doğru. Fakat ilk doğru olan, sonucu belirler. dolayısıyla birinci dal
    ; yani form2'nin birebir kendisi döner
    ; bu yüzden (f form) form'un aynısını dönüyor
    (f form)
    ;=> [["id" 1] ["name" "ali"] ["surname" "veli"] ["id" 2] ["name" "batu"] ["surname" "can"]]

    ; end
    ,)

  ; 1. iterasyon:
  (f form)
  ;=> [["id" 1] ["name" "ali"] ["surname" "veli"] ["id" 2] ["name" "batu"] ["surname" "can"]]

  ; buraya kadarki kısım postwalk'ın ilk iterasyonu
  ; şimdi form'un alt objelerini alıp, bunlara f fonksiyonunu uygulayacak

  ; 2. iterasyon
  (f ["id" 1])
  ;=> nil
  (clojure.walk/postwalk f coll)

  (comment
    ; postwalk
    ; end
    ,)
  ; end
  ,)
