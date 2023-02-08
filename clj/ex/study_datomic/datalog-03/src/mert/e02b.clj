(ns mert.e02b)

(require '[datomic.client.api :as d])
(def uri "datomic:dev://localhost:4334/mbrainz-1968-1973")
(def conn (d/connect uri))

; TODO query map için datomic.client.api ile deneme yapalım
; bunun için peer-server çalıştıralım önce
; rfr: /Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-01/src/mertnuhoglu/datomic/datomic_02_assertion.clj
