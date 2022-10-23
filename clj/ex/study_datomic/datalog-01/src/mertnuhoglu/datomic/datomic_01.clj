(ns mertnuhoglu.datomic.datomic-01)

; Copied from: [Connect to a Database | Datomic](https://docs.datomic.com/on-prem/getting-started/connect-to-a-database.html)

; Step01: Run datomic server first:

; cd /Users/mertnuhoglu/codes/clj/lib/datomic-pro-${VERSION}
; bin/run -m datomic.peer-server -h localhost -p 8998 -a myaccesskey,mysecret -d hello,datomic:mem://movies

; Step02: Connect to datomic server

(require '[datomic.client.api :as d])

(def cfg {:server-type :peer-server
          :access-key "myaccesskey"
          :secret "mysecret"
          :endpoint "localhost:8998"
          :validate-hostnames false})
;#'user/cfg

(def client (d/client cfg))
;#'user/client
(def conn (d/connect client {:db-name "movies"}))
;#'user/conn

conn
; =>
{:db-name "hello",
 :database-id "5a381758-6e47-4504-aa08-07067b5c241a",
 :t 1008,
 :next-t 1009,
 :type :datomic.client/conn}

; Step03: Define data schema
; [Transact a Schema | Datomic](https://docs.datomic.com/on-prem/getting-started/transact-schema.html)

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

; Step04: Save the schema into datomic
; transact the schema
(d/transact conn {:tx-data movie-schema})
;=>
#_{:db-before {:database-id "6239a257-b8cd-4599-a1d1-13d0fad9b2aa",
               :db-name "hello",
               :t 66,
               :next-t 1000,
               :type :datomic.client/db},
   :db-after {:database-id "6239a257-b8cd-4599-a1d1-13d0fad9b2aa",
              :db-name "hello",
              :t 1000,
              :next-t 1001,
              :type :datomic.client/db},
   :tx-data [#datom[13194139534312 50 #inst"2022-03-22T10:27:40.660-00:00" 13194139534312 true]
             #datom[72 10 :movie/title 13194139534312 true]
             #datom[72 40 23 13194139534312 true]
             #datom[72 41 35 13194139534312 true]
             #datom[72 62 "The title of the movie" 13194139534312 true]
             #datom[73 10 :movie/genre 13194139534312 true]
             #datom[73 40 23 13194139534312 true]
             #datom[73 41 35 13194139534312 true]
             #datom[73 62 "The genre of the movie" 13194139534312 true]
             #datom[74 10 :movie/release-year 13194139534312 true]
             #datom[74 40 22 13194139534312 true]
             #datom[74 41 35 13194139534312 true]
             #datom[74 62 "The year the movie was released in theaters" 13194139534312 true]
             #datom[0 13 72 13194139534312 true]
             #datom[0 13 73 13194139534312 true]
             #datom[0 13 74 13194139534312 true]],
   :tempids {-9223301668109598134 72, -9223301668109598133 73, -9223301668109598132 74}}

; Step05: Create data
; [Transact Data | Datomic](https://docs.datomic.com/on-prem/getting-started/transact-data.html)

(def first-movies [{:movie/title "The Goonies"
                    :movie/genre "action/adventure"
                    :movie/release-year 1985}
                   {:movie/title "Commando"
                    :movie/genre "action/adventure"
                    :movie/release-year 1985}
                   {:movie/title "Repo Man"
                    :movie/genre "punk dystopia"
                    :movie/release-year 1984}])

; Step06: Save the data into datomic

(d/transact conn {:tx-data first-movies})
;=>
#_{:db-before {:database-id "6239a257-b8cd-4599-a1d1-13d0fad9b2aa",
               :db-name "hello",
               :t 1000,
               :next-t 1001,
               :type :datomic.client/db},
   :db-after {:database-id "6239a257-b8cd-4599-a1d1-13d0fad9b2aa",
              :db-name "hello",
              :t 1001,
              :next-t 1005,
              :type :datomic.client/db},
   :tx-data [#datom[13194139534313 50 #inst"2022-03-22T10:53:42.201-00:00" 13194139534313 true]
             #datom[17592186045418 72 "The Goonies" 13194139534313 true]
             #datom[17592186045418 73 "action/adventure" 13194139534313 true]
             #datom[17592186045418 74 1985 13194139534313 true]
             #datom[17592186045419 72 "Commando" 13194139534313 true]
             #datom[17592186045419 73 "action/adventure" 13194139534313 true]
             #datom[17592186045419 74 1985 13194139534313 true]
             #datom[17592186045420 72 "Repo Man" 13194139534313 true]
             #datom[17592186045420 73 "punk dystopia" 13194139534313 true]
             #datom[17592186045420 74 1984 13194139534313 true]],
   :tempids {-9223301668109598131 17592186045418,
             -9223301668109598130 17592186045419,
             -9223301668109598129 17592186045420}}

; Step07: Query the data
; [Query the Data | Datomic](https://docs.datomic.com/on-prem/getting-started/query-the-data.html)

; get current database value
(def db (d/db conn))

db
;=>
{:t 1001, :next-t 1005, :db-name "hello", :database-id "6239a257-b8cd-4599-a1d1-13d0fad9b2aa", :type :datomic.client/db}

; query -> :find + :where
; args -> data sources
(def all-movies-q '[:find ?e
                    :where [?e :movie/title]])
; all-movies-q: is a var that holds query definition
; :find: what you want returned from the query
; ?e: logic variable that will be bound within :where clause
; :where: list of vectors
; [?e :movie/title]: bind the id of each entity that has an attribute called :movie/title to logic variable ?e
; read query: find me the ids of all entities which have an attribute called :movie/title

; run query
(d/q all-movies-q db)
;=> [[17592186045418] [17592186045419] [17592186045420]]

; Step07b: Query 02
; query: return titles of the movies
(def all-titles-q '[:find ?movie-title
                    :where [_ :movie/title ?movie-title]])
; ?movie-title: named value that you bind to an attribute :movie/title
; _: we are not interested in the entity id itself, just the existence of the :movie/title attribute
; read query: find all movie titles from any entity that has an attribute :movie/title and assign the title to a logic variable called ?movie-title

(d/q all-titles-q db)
;=> [["Commando"] ["The Goonies"] ["Repo Man"]]

; Step07c: Query 03
; query: return titles of movies released in 1985
; two clauses:
; 1. one to bind tho :movie/title attribute
; 2. one to filter by :movie/release-year
; Two clauses have to be joined
; Joins are created implicitly by the presence of the same logic variable `?e` in multiple clauses
(def titles-from-1985 '[:find ?title
                        :where [?e :movie/title ?title]
                        [?e :movie/release-year 1985]])
(d/q titles-from-1985 db)
;=> [["Commando"] ["The Goonies"]]

(def all-data-from-1985 '[:find ?title ?year ?genre
                          :where [?e :movie/title ?title]
                          [?e :movie/release-year ?year]
                          [?e :movie/genre ?genre]
                          [?e :movie/release-year 1985]])
(d/q all-data-from-1985 db)
;=> [["The Goonies" 1985 "action/adventure"] ["Commando" 1985 "action/adventure"]]
; Note: return value contains multiple tuples
; Shape of the relation returned is defined by your :find specification

; Step08: Query historical data

; [See Historic Data | Datomic](https://docs.datomic.com/on-prem/getting-started/see-historic-data.html)

; Step01: Change value of an entity:

(d/q '[:find ?e
       :where [?e :movie/title "Commando"]]
  db)
;=> [[17592186045419]]

; bind the entity id to local variable
(def commando-id
  (ffirst (d/q '[:find ?e
                 :where [?e :movie/title "Commando"]]
            db)))

; change value of the entity:
(d/transact conn {:tx-data [{:db/id commando-id :movie/genre "future governor"}]})
;=>
{:db-before {:database-id "6239a257-b8cd-4599-a1d1-13d0fad9b2aa",
             :db-name "hello",
             :t 1001,
             :next-t 1005,
             :type :datomic.client/db},
 :db-after {:database-id "6239a257-b8cd-4599-a1d1-13d0fad9b2aa",
            :db-name "hello",
            :t 1005,
            :next-t 1006,
            :type :datomic.client/db},
 :tx-data [#datom[13194139534317 50 #inst"2022-03-22T11:16:43.444-00:00" 13194139534317 true]
           #datom[17592186045419 73 "future governor" 13194139534317 true]
           #datom[17592186045419 73 "action/adventure" 13194139534317 false]],
 :tempids {}}

; check value:
(d/q all-data-from-1985 db)
;=> [["The Goonies" 1985 "action/adventure"] ["Commando" 1985 "action/adventure"]]
; no change here because we queried the old database value db

(def db (d/db conn))
(d/q all-data-from-1985 db)
;=> [["The Goonies" 1985 "action/adventure"] ["Commando" 1985 "future governor"]]

; get old database value using a time basis
(def old-db (d/as-of db 1004))
(d/q all-data-from-1985 old-db)
;=> [["The Goonies" 1985 "action/adventure"] ["Commando" 1985 "action/adventure"]]

; get all the values a given attribute has held over time
(def hdb (d/history db))
(d/q '[:find ?genre
       :where [?e :movie/title "Commando"]
       [?e :movie/genre ?genre]]
  hdb)
;=> [["action/adventure"] ["future governor"]]

