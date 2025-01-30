(ns mert.datalog-04-03)
(require '[datomic.api :as d])
(def db-uri "datomic:dev://localhost:4334/hello")
(def conn (d/connect db-uri))
;; [[datalog_04_03.clj]]

(def first-movies [{:movie/title "The Goonies"
                    :movie/genre "action/adventure"
                    :movie/release-year 1985}
                   {:movie/title "Commando"
                    :movie/genre "action/adventure"
                    :movie/release-year 1985}
                   {:movie/title "Repo Man"
                    :movie/genre "punk dystopia"
                    :movie/release-year 1984}])

@(d/transact conn first-movies)
; {:db-before datomic.db.Db@ccc2fca3,
;  :db-after datomic.db.Db@e7028ac3,
;  :tx-data
;  [#datom[13194139534317 50 #inst "2025-01-29T10:20:18.107-00:00" 13194139534317 true] #datom[17592186045422 72 "The Goonies" 13194139534317 true] #datom[17592186045422 73 "action/adventure" 13194139534317 true] #datom[17592186045422 74 1985 13194139534317 true] #datom[17592186045423 72 "Commando" 13194139534317 true] #datom[17592186045423 73 "action/adventure" 13194139534317 true] #datom[17592186045423 74 1985 13194139534317 true] #datom[17592186045424 72 "Repo Man" 13194139534317 true] #datom[17592186045424 73 "punk dystopia" 13194139534317 true] #datom[17592186045424 74 1984 13194139534317 true]],
;  :tempids
;  {-9223300668110598129 17592186045422,
;   -9223300668110598128 17592186045423,
;   -9223300668110598127 17592186045424}}
