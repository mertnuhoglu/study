(ns peer.peer01)

; [Peer Getting Started | Datomic](https://docs.datomic.com/pro/peer/peer-getting-started.html)

(require '[datomic.api :as d])

(def db-uri "datomic:mem://hello")

(d/create-database db-uri)

(def conn (d/connect db-uri))

(identity conn)

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
;{:db-before datomic.db.Db,
; @77650a50 :db-after,
; datomic.db.Db @2d0b07dc,
; :tx-data [#datom[13194139534312 50 #inst"2024-02-19T19:40:49.007-00:00" 13194139534312 true]
;           #datom[72 10 :movie/title 13194139534312 true]
;           #datom[72 40 23 13194139534312 true]
;           #datom[72 41 35 13194139534312 true]
;           #datom[72 62 "The title of the movie" 13194139534312 true]
;           #datom[73 10 :movie/genre 13194139534312 true]
;           #datom[73 40 23 13194139534312 true]
;           #datom[73 41 35 13194139534312 true]
;           #datom[73 62 "The genre of the movie" 13194139534312 true]
;           #datom[74 10 :movie/release-year 13194139534312 true]
;           #datom[74 40 22 13194139534312 true]
;           #datom[74 41 35 13194139534312 true]
;           #datom[74 62 "The year the movie was released in theaters" 13194139534312 true]
;           #datom[0 13 72 13194139534312 true]
;           #datom[0 13 73 13194139534312 true]
;           #datom[0 13 74 13194139534312 true]],
; :tempids {-9223301668109598134 72, -9223301668109598133 73, -9223301668109598132 74}}

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
;{:db-before datomic.db.Db,
; @2d0b07dc :db-after,
; datomic.db.Db @93f56945,
; :tx-data [#datom[13194139534313 50 #inst"2024-02-19T19:41:54.343-00:00" 13194139534313 true]
;           #datom[17592186045418 72 "The Goonies" 13194139534313 true]
;           #datom[17592186045418 73 "action/adventure" 13194139534313 true]
;           #datom[17592186045418 74 1985 13194139534313 true]
;           #datom[17592186045419 72 "Commando" 13194139534313 true]
;           #datom[17592186045419 73 "action/adventure" 13194139534313 true]
;           #datom[17592186045419 74 1985 13194139534313 true]
;           #datom[17592186045420 72 "Repo Man" 13194139534313 true]
;           #datom[17592186045420 73 "punk dystopia" 13194139534313 true]
;           #datom[17592186045420 74 1984 13194139534313 true]],
; :tempids {-9223301668109598131 17592186045418,
;           -9223301668109598130 17592186045419,
;           -9223301668109598129 17592186045420}}

(def db (d/db conn))

(def all-movies-q '[:find ?e :where [?e :movie/title]])

(d/q all-movies-q db)
;=> #{[17592186045418] [17592186045419] [17592186045420]}

(def all-titles-q '[:find ?movie-title :where [_ :movie/title ?movie-title]])

(d/q all-titles-q db)
;=> #{["Commando"] ["The Goonies"] ["Repo Man"]}

(def all-titles-q '[:find ?movie-title :where [_ :movie/title ?movie-title]])

(d/q all-titles-q db)
;=> #{["Commando"] ["The Goonies"] ["Repo Man"]}

(def titles-from-1985 '[:find ?title :where [?e :movie/title ?title]
                        [?e :movie/release-year 1985]])

(d/q titles-from-1985 db)
;=> #{["Commando"] ["The Goonies"]}

(def all-data-from-1985 '[:find ?e ?title ?year ?genre
                          :where [?e :movie/title ?title]
                          [?e :movie/release-year ?year]
                          [?e :movie/genre ?genre]
                          [?e :movie/release-year 1985]])

(d/q all-data-from-1985 db)
;=> #{[17592186045419 "Commando" 1985 "action/adventure"] [17592186045418 "The Goonies" 1985 "action/adventure"]}

(d/q '[:find ?e :where [?e :movie/title "Commando"]] db)

(def commando-id (ffirst (d/q '[:find ?e :where [?e :movie/title "Commando"]] db)))

@(d/transact conn [{:db/id commando-id :movie/genre "future governor"}])
;=>
;{:db-before datomic.db.Db,
; @93f56945 :db-after,
; datomic.db.Db @d1e06a7,
; :tx-data [#datom[13194139534317 50 #inst"2024-02-19T19:45:26.461-00:00" 13194139534317 true]
;           #datom[17592186045419 73 "future governor" 13194139534317 true]
;           #datom[17592186045419 73 "action/adventure" 13194139534317 false]],
; :tempids {}}

(d/q all-data-from-1985 db)
;=> #{[17592186045419 "Commando" 1985 "action/adventure"] [17592186045418 "The Goonies" 1985 "action/adventure"]}

(def db (d/db conn))

(d/q all-data-from-1985 db)
;=> #{[17592186045419 "Commando" 1985 "future governor"] [17592186045418 "The Goonies" 1985 "action/adventure"]}

(def old-db (d/as-of db 13194139534313))

(d/q all-data-from-1985 old-db)
;=> #{[17592186045419 "Commando" 1985 "action/adventure"] [17592186045418 "The Goonies" 1985 "action/adventure"]}

(def hdb (d/history db))

(d/q '[:find ?genre
       :where [?e :movie/title "Commando"]
       [?e :movie/genre ?genre]] hdb)
;=> #{["action/adventure"] ["future governor"]}
