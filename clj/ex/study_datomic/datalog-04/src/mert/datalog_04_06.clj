(ns mert.datalog-04-06)
(require '[datomic.client.api :as d])
(def cfg {:server-type :datomic-local
          :system "datomic-samples"})
(def client (d/client cfg))
;; [[datalog_04_06.clj]]

;; Konu: Client API Tutorial
;;   id:: f480298c-023d-458c-8f47-4538b6cf0895
;; [Client API Tutorial | Datomic](https://docs.datomic.com/client-tutorial/client.html)

(d/create-database client {:db-name "movies"})
(def conn (d/connect client {:db-name "movies"}))

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

(d/transact conn {:tx-data movie-schema})
; {:db-before
;  #datomic.core.db.Db{:id "movies", :basisT 6, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil},
;  :db-after
;  #datomic.core.db.Db{:id "movies", :basisT 7, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil},
;  :tx-data
;  [#datom[13194139533319 50 #inst "2025-01-29T11:02:59.871-00:00" 13194139533319 true]],
;  :tempids {}}

(def first-movies [{:movie/title "The Goonies"
                    :movie/genre "action/adventure"
                    :movie/release-year 1985}
                   {:movie/title "Commando"
                    :movie/genre "thriller/action"
                    :movie/release-year 1985}
                   {:movie/title "Repo Man"
                    :movie/genre "punk dystopia"
                    :movie/release-year 1984}])

(d/transact conn {:tx-data first-movies})
; {:db-before
;  #datomic.core.db.Db{:id "movies", :basisT 7, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil},
;  :db-after
;  #datomic.core.db.Db{:id "movies", :basisT 8, :indexBasisT 0, :index-root-id nil, :asOfT nil, :sinceT nil, :raw nil},
;  :tx-data
;  [#datom[13194139533320 50 #inst "2025-01-29T11:03:18.138-00:00" 13194139533320 true] #datom[101155069755468 73 "The Goonies" 13194139533320 true] #datom[101155069755468 74 "action/adventure" 13194139533320 true] #datom[101155069755468 75 1985 13194139533320 true] #datom[101155069755469 73 "Commando" 13194139533320 true] #datom[101155069755469 74 "thriller/action" 13194139533320 true] #datom[101155069755469 75 1985 13194139533320 true] #datom[101155069755470 73 "Repo Man" 13194139533320 true] #datom[101155069755470 74 "punk dystopia" 13194139533320 true] #datom[101155069755470 75 1984 13194139533320 true]],
;  :tempids {}}

(def db (d/db conn))

(def all-titles-q '[:find ?movie-title 
                    :where [_ :movie/title ?movie-title]])
(d/q all-titles-q db) 
; [["Commando"] ["The Goonies"] ["Repo Man"]]
