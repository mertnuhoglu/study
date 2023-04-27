(ns e27
  (:require
    [clojure.string :as str]))


; Konu: d11
; Tarih: 20230427

;  Girdi:
;
;  {1 {:id 1 :name "ali" :surname "veli"}
;   2 {:id 2 :name "batu" :surname "can"}}
;
;  Arama anahtar kelimesi:
;
;  "a"
;
;  Bu anahtar kelimeyi tüm property'lerde arayın. Eşleşen property'leri aşağıdaki formda dönün:
;
;  Çıktı:
;
;  [[1 "ali"] [2 "batu"] [2 "can"]]

(def x {1 {:id 1 :name "ali" :surname "veli"}
        2 {:id 2 :name "batu" :surname "can"}})
(def x1 {:id 1 :name "ali" :surname "veli"})

(comment
  (filter (fn [t] (str/includes? (second t) "a")) x1)
  ;=> ([:name "ali"])

  (defn f1 [s]
    (fn [t]
      (str/includes? (second t) s)))

  (filter (f1 "a") x1)
  ;=> ([:name "ali"])

  (vals x)
  ;=> ({:id 1, :name "ali", :surname "veli"} {:id 2, :name "batu", :surname "can"})

  (map
    #(filter (f1 "a") %)
    (vals x))
  ;=> (([:name "ali"]) ([:name "batu"] [:surname "can"]))

  (def x2
    [[1 "ali"] [1 "veli"]])
  (filter (f1 "a") x2)
  ;=> ([1 "ali"])

  ; x1 objesinin şeklini değiştirmeliyim

  ; end
  ,)