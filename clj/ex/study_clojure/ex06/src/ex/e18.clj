(ns ex.e18
  (:require [clojure.string :as str]))



; Tarih: 20230403

; D07 problemini rich comment formunda debug etmek

;Girdi:
;
;```clojure
;{1 {:id 1 :name "ali" :surname "veli"}
; 2 {:id 2 :name "batu" :surname "can"}}
;```
;
;Arama kriteri:
;
;```
;"ca"
;```
;
;Bu anahtar kelimenin geçtiği `surname` property'sine sahip objeleri bulun.
;
;Çıktı:
;
;```clojure
;{2 {:id 2 :name "batu" :surname "can"}}

(def m {1 {:id 1 :name "ali" :surname "veli"}
        2 {:id 2 :name "batu" :surname "can"}})

;-------------------------------------------d06------------------------------------------------

(defn filter-db-by-surname2 [substring db]
  (reduce-kv (fn [m k {:keys [id name surname] :as v}]
               (if (str/includes? (str/lower-case v)
                     (str/lower-case substring))
                 (assoc m k v)
                 m))
    {} db))

(filter-db-by-surname2 "a" m)
;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

(comment
  ; 1. adım: filter-db-by-surname2 fonksiyonunu genişletelim (içini açalım)
  (def substring "a")
  (def db m)
  (reduce-kv (fn [m k {:keys [id name surname] :as v}]
               (if (str/includes? (str/lower-case v)
                     (str/lower-case substring))
                 (assoc m k v)
                 m))
    {} db)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

  ; 2. adım: reduce-kv çağrısını hizalayalım temiz bir şekilde
  (reduce-kv
    (fn [m k {:keys [id name surname] :as v}]
      (if
        (str/includes? (str/lower-case v)
          (str/lower-case substring))
        (assoc m k v)
        m))
    {}
    db)

  ; 3. adım: anonim fonksiyona isim verelim
  (def f
    (fn [init k {:keys [id name surname] :as v}]
      (if
        (str/includes? (str/lower-case v)
          (str/lower-case substring))
        (assoc init k v)
        init)))

  (identity db)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}
  ; 4. adım: iterasyonları tek tek debug edicez

  ; 1. iterasyon
  (def init {})
  (def k 1)
  (def v (db k))
  (f init k v)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}}

  ; 2. iterasyon
  (def init {1 {:id 1, :name "ali", :surname "veli"}})
  (def k 2)
  (def v (db k))
  (f init k v)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

  ; end
  ,)

;-------------------------------------------d05------------------------------------------------

(defn filter-db-by-surname [substring db]
  (into {}
    (filter (fn [[_ {:keys [id name surname] :as v}]]
              (str/includes? (str/lower-case v)
                (str/lower-case substring))))
    db))

(filter-db-by-surname "a" m)
;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

(comment
  ; 1. adım: filter-db-by-surname fonksiyonunu genişletelim (içini açalım)
  (def substring "a")
  (def db m)
  (identity db)
  (into
    {}
    (filter (fn [[_ {:keys [id name surname] :as v}]]
              (str/includes? (str/lower-case v)
                (str/lower-case substring))))
    db)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

  ; 2. adım: filter fonksiyonunu debug edelim
  ; hizalayalım argümanları ayrı ayrı
  (filter
    (fn [[_ {:keys [id name surname] :as v}]]
      (str/includes? (str/lower-case v)
        (str/lower-case substring))))

  ; 3. adım: anonim fonksiyona pred ismi verelim
  (def pred
    (fn [[_ {:keys [id name surname] :as v}]]
      (str/includes? (str/lower-case v)
        (str/lower-case substring))))
  ; 4. adım: pred fonksiyonunun içindeki anonim fonksiyonu kaldırabiliriz
  (defn pred [[_ {:keys [id name surname] :as v}]]
    (str/includes? (str/lower-case v)
      (str/lower-case substring)))

  (filter pred db)
  (def coll db)
  (identity coll)
  ; 5. adım: filter çağrısının iterasyonlarına parçalayalım
  ; 1. iterasyon
  (first coll)
  ;=> [1 {:id 1, :name "ali", :surname "veli"}]
  (pred (first coll))
  ;=> true

  ; 2. iterasyon
  (first (rest coll))
  ;=> [2 {:id 2, :name "batu", :surname "can"}]
  (pred (first (rest coll)))
  ;=> true

  ; end
  ,)

(comment
  ; q: destructuring işlemi debug edilebilir mi?
  (defn pred [[_ {:keys [id name surname] :as v}]]
    (str/includes? (str/lower-case v)
      (str/lower-case substring)))

  (pred (first coll))
  (first coll)
  ;=> [1 {:id 1, :name "ali", :surname "veli"}]
  (let
    [[_ {:keys [id name surname] :as v}] (first coll)]
    (str "id: " id " name: " name))
  ;=> "id: 1 name: ali"

  (let
    [[_ {:keys [id name surname] :as v}] [1 {:id 1, :name "ali", :surname "veli"}]]
    (str "id: " id " name: " name))

  ; end
  ,)
