(ns mert.e07)

; Barış'la Datomic Çalışmaları
; Tarih: 20230203
; rfr: video/20230203-mert-clj-egzersiz-16.mp4

; Konu: Parametrik Sorgular (Parameterised Queries)

(require '[datomic.client.api :as d])
(use '[mert.e04 :only [conn db] :as e04])

; Şu ana kadarki sorgularımızda sorgu parametrelerimizi doğrudan sorgu cümlesinin içine yazmıştık.
; Artık bunları parametrik hale getirmek istiyoruz:
(d/q
  '[:find ?e
    :where
    [?e :product/name "Kalem"]]
  db)
;=> [[92358976733263] [92358976733264]]
; 👉
(d/q
  '[:find ?e
    :in $ ?product-name
    :where
    [?e :product/name ?product-name]]
  db "Kalem")
;=> [[92358976733263] [92358976733264]]

; Burada :in cümleciğinde (clause) iki tane argüman var.
; İlk argüman: `$`
; Bu ilk argüman `db` argümanıyla eşleşir.
; İkinci argüman: `?product-name`
; Bu ise "Kalem" argümanıyla eşleşir.

; data pattern (yani sorgu cümleciklerimiz) hep EAV formatında diyorduk.
; aslında daha başka veriler de var. fakat onları ihmal edebiliyoruz.
; normalde data patternın formatı şu şekilde:
; [<database> <entity-id> <attribute> <value> <transaction-id>]
; aslında data pattern içindeki ilk argüman ilgili database objesine denk gelir
; ama burada dikkat edersek: 5 tane argüman içeriyor data pattern
; eğer sen 3 tane argüman gönderirsen: EAV formatını varsayar
; eğer 2 arg gönderirsen: EA varsayar
; eğer 4: DB EAV
; eğer 5 gönderirsen: DB EAV Tx

(d/q
  '[:find ?e
    :in $ ?product-name
    :where
    [$ ?e :product/name ?product-name]]
  db "Kalem")
;=> [[92358976733263] [92358976733264]]

; Bu parametrik sorguların bir fonksiyon içinde kullanımına bakalım
(defn find-product-by-name [product-name]
  (d/q
    '[:find ?e
      :in $ ?product-name
      :where
      [?e :product/name ?product-name]]
    db product-name))
(find-product-by-name "Kalem")
;=> [[92358976733263] [92358976733264]]
(find-product-by-name "Defter")
;=> [[92358976733265] [92358976733266]]

; Parametrik sorgu yapmasaydık, doğrudan sorgu cümleciğinin içine koysaydık parametreleri ne olurdu?
; Çalışmazdı
(defn find-product-by-name-wrong [product-name]
  (d/q
    '[:find ?e
      :where
      [?e :product/name product-name]]
    db))
(find-product-by-name-wrong "Kalem")
;=> []
; Hiçbir şey dönmüyor.
; Neden?
; Çünkü d/q'nun sorgu cümlesi olan argüman aslında escapelenmiş bir formdur.
; Escapelenmiş olan formlar da eval edilmez. Daha sonra başka bir şekilde çalıştırılır.
