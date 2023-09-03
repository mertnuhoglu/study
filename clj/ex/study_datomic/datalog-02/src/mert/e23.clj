(ns mert.e23)

(require '[datomic.client.api :as d])

; Tarih: 20230608
; Title: Hata: not-a-data-function

(def client (d/client {:server-type :dev-local
                       :storage-dir :mem
                       :system "ci"}))

(d/delete-database client {:db-name "db23"})
(d/create-database client {:db-name "db23"})

(def conn (d/connect client {:db-name "db23"}))
(def db (d/db conn))

(def schema
  [{:db/ident       :dogum_yeri
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one}])
(d/transact conn {:tx-data schema})
(def data {:dogum_yeri "Ankara"})
;Execution error (ExceptionInfo) at datomic.core.error/raise (error.clj:55).
;:db.error/not-a-data-function Not a data function: :dogum_yeri
(def data [{:dogum_yeri "Ankara"}])
(d/transact conn {:tx-data data})

(d/q
  '[:find ?e
    :where
    [?e :dogum_yeri "Ankara"]]
  db)
;Execution error (ExceptionInfo) at datomic.core.error/raise (error.clj:55).
;:db.error/not-a-data-function Not a data function: :dogum_yeri3

(def db (d/db conn))
(d/q
  '[:find ?e
    :where
    [?e :dogum_yeri "Ankara"]]
  db)

; Problemin sebebi:
; Datomic'de connection objesiyle db (veritabanı) objesi birbirinden farklıdır.
; connection objesi her zaman size en güncel veritabanı objesini döner.
; Ancak veritabanı objesi (db) yaratıldığı andaki veritabanının durumunu yansıtır.
; db objesi ilk yaratıldığında henüz daha `:dogum_yeri` atributu yoktu
; Bu yüzden sorgulama ilk başta hata verdi.
; İkinci sorgulamada db'yi yeni baştan çektik. En güncel veritabanı durumundan çektik.
; Bu yüzden orada hata vermedi.