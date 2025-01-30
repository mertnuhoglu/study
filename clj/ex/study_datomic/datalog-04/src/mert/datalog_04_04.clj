(ns mert.datalog-04-04)
(require '[datomic.api :as d])
(def db-uri "datomic:dev://localhost:4334/hello")
(def conn (d/connect db-uri))
;; [[datalog_04_04.clj]]

(def db (d/db conn)) 

(def all-movies-q '[:find ?e 
                    :where [?e :movie/title]])

(d/q all-movies-q db) ; #{[17592186045422] [17592186045423] [17592186045424]}

(def all-titles-q '[:find ?movie-title 
                    :where [_ :movie/title ?movie-title]])

(d/q all-titles-q db) ; #{["Commando"] ["The Goonies"] ["Repo Man"]}

(def titles-from-1985 '[:find ?title 
                        :where [?e :movie/title ?title] 
                               [?e :movie/release-year 1985]])

(d/q titles-from-1985 db) ; #{["Commando"] ["The Goonies"]}

(def all-data-from-1985 '[:find ?title ?year ?genre 
                          :where [?e :movie/title ?title] 
                                 [?e :movie/release-year ?year] 
                                 [?e :movie/genre ?genre] 
                                 [?e :movie/release-year 1985]])

(d/q all-data-from-1985 db) ; #{["The Goonies" 1985 "action/adventure"] ["Commando" 1985 "action/adventure"]}
