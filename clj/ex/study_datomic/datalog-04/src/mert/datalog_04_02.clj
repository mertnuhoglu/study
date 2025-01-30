(ns mert.datalog-04-02)
;; [[datalog_04-02.clj]]

(require '[datomic.api :as d])
(def db-uri "datomic:dev://localhost:4334/hello")
(def conn (d/connect db-uri))

(def movie-schema [{:db/ident :movie/title
                    :db/valueType :db.type/string
                    :db/cardinality :db.cardinality/one
                    :db/doc "The title of the movie"}

                   {:db/ident :movie/genre
                    :db/valueType :db.type/string
                    :db/cardinality :db.cardinality/one
                    :db/doc "The genre of the movie"}

                   {:db/ident :movie/release-year
                    :db/valueType :db.type/long
                    :db/cardinality :db.cardinality/one
                    :db/doc "The year the movie was released in theaters"}])

@(d/transact conn movie-schema)
; {:db-before datomic.db.Db@a89243a2,
;  :db-after datomic.db.Db@eb09de80,
;  :tx-data
;  [#datom[13194139534316 50 #inst "2025-01-29T08:48:27.976-00:00" 13194139534316 true] #datom[72 10 :movie/title 13194139534316 true] #datom[72 40 23 13194139534316 true] #datom[72 41 35 13194139534316 true] #datom[72 62 "The title of the movie" 13194139534316 true] #datom[73 10 :movie/genre 13194139534316 true] #datom[73 40 23 13194139534316 true] #datom[73 41 35 13194139534316 true] #datom[73 62 "The genre of the movie" 13194139534316 true] #datom[74 10 :movie/release-year 13194139534316 true] #datom[74 40 22 13194139534316 true] #datom[74 41 35 13194139534316 true] #datom[74 62 "The year the movie was released in theaters" 13194139534316 true] #datom[0 13 72 13194139534316 true] #datom[0 13 73 13194139534316 true] #datom[0 13 74 13194139534316 true]],
;  :tempids
;  {-9223300668110598132 72,
;   -9223300668110598131 73,
;   -9223300668110598130 74}}


