(ns mertnuhoglu.mbrainz.setup)

(use '[datomic.api :only [q db] :as d])
; (def uri "datomic:mem://movies")
(def uri "datomic:dev://localhost:4334/mbrainz-1968-1973")
(d/create-database uri)
(def conn (d/connect uri))
