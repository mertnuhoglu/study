(ns mert.e06)

; Author: Barış Can Çalış
; Egzersiz
; rfr: video/20230203-mert-clj-egzersiz-16.mp4

(require '[datomic.client.api :as d])

(def client (d/client {:server-type :dev-local
                       :storage-dir :mem
                       :system "ci"}))
(d/create-database client {:db-name "opel"})
(def conn (d/connect client {:db-name "opel"}))

(def db (d/db conn))

(d/transact
  conn
  {:tx-data [{:db/ident :color/red}
             {:db/ident :color/grey}
             {:db/ident :color/metalic-color}]})

(d/transact
  conn
  {:tx-data [{:db/ident :condition/new}
             {:db/ident :condition/used}
             {:db/ident :condition/broken}]})

(def schema-1
  [{:db/ident :product/model
    :db/valueType :db.type/string
    :db/unique :db.unique/identity
    :db/cardinality :db.cardinality/one}
   {:db/ident :product/motor
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :product/ic_donanim
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :product/dis_donanim
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :product/guvenlik_donanim
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :product/color
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}
   {:db/ident       :product/condition
    :db/valueType   :db.type/ref
    :db/cardinality :db.cardinality/one}])

(d/transact conn {:tx-data schema-1})

(def opel-corsa
  [{:product/model "essential"
    :product/motor "1.0 75 HP manuel"
    :product/ic_donanim "bardaklık"
    :product/dis_donanim "16' çelik jantlar, metalik boya"
    :product/guvenlik_donanim "maalesef güvenli değil"
    :product/color :color/grey
    :product/condition :condition/broken}
   {:product/model "edition"
    :product/motor "1.2 Turbo 100 HP"
    :product/ic_donanim "yol bilgisayarı, bardaklık"
    :product/dis_donanim "16' çelik jantlar, metalik boya, cift renk kasa"
    :product/guvenlik_donanim "eh öldürmez süründürür"
    :product/color :color/red
    :product/condition :condition/used}
   {:product/model "elegance"
    :product/motor "1.2 Turbo 100 HP"
    :product/ic_donanim "çocuk kilidi, yol bilgisayarı, bardaklık"
    :product/dis_donanim "17' çelik jantlar, metalik boya, cift renk kasa, panaromik cam tavan"
    :product/guvenlik_donanim "maalesef güvenli değil"
    :product/color :color/metalic-color
    :product/condition :condition/new}
   {:product/model "ultimate"
    :product/motor "1.2 Turbo 100 HP"
    :product/ic_donanim "çocuk kilidi, yol bilgisayarı, bardaklık, anahtarsız çalıştırma, kör nokta uyarı sistemi"
    :product/dis_donanim "17' çelik jantlar, metalik boya, cift renk kasa, panaromik cam tavan, keskin sportif detaylar"
    :product/guvenlik_donanim "güvenli sayılır"
    :product/color :color/metalic-color
    :product/condition :condition/new}])
(d/transact conn {:tx-data opel-corsa})
@(def db (d/db conn))

(def order-schema
  [{:db/ident :order/product
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}
   {:db/ident :product/condition
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}
   {:db/ident :order/size
    :db/valueType :db.type/long
    :db/cardinality :db.cardinality/one}])
(d/transact conn {:tx-data order-schema})

(defn get-entity-id-by-car-model [model-string]
  (ffirst (d/q
            '[:find ?e
              :where
              [?e :product/model "elegance"]]
            db)))


(get-entity-id-by-car-model "elegance")

(defn order-a-car-by-entity-id [car-entity-id condition-ref order-size-int]
  [{:order/product car-entity-id
    :product/condition condition-ref
    :order/size order-size-int}])



(d/transact conn {:tx-data (order-a-car-by-entity-id (get-entity-id-by-car-model "elegance") :condition/new 2)})
