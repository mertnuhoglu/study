(ns mertnuhoglu.day-of-datomic.cloud.hello-world)

;; [cognitect-labs/day-of-datomic-cloud](https://github.com/cognitect-labs/day-of-datomic-cloud)
;; rfr: Example: Day of Datomic Cloud Sample Data <url:file:///~/prj/study/clj/articles-datomic.md#r=g13521>

(require '[datomic.client.api :as d])

(def client (d/client {:server-type :dev-local :system "day-of-datomic-cloud"}))
(d/create-database client {:db-name "movies"})
(def conn (d/connect client {:db-name "movies"}))

;; Define a small schema for a movie database:
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

;; Now transact the schema:
(d/transact conn {:tx-data movie-schema})

;; Define some movies to add to the database:
(def first-movies [{:movie/title "The Goonies"
                    :movie/genre "action/adventure"
                    :movie/release-year 1985}
                   {:movie/title "Commando"
                    :movie/genre "action/adventure"
                    :movie/release-year 1985}
                   {:movie/title "Repo Man"
                    :movie/genre "punk dystopia"
                    :movie/release-year 1984}])

;; Now transact the movies:
(d/transact conn {:tx-data first-movies})

;; Get the current value of the database:
(def db (d/db conn))

;; Create a query for all movie titles:
(def all-titles-q '[:find ?movie-title
                    :where [_ :movie/title ?movie-title]])

;; Execute the query with the value of the database:
(d/q all-titles-q db)
;=> [["Commando"] ["The Goonies"] ["Repo Man"]]

