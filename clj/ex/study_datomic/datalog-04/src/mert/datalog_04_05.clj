(ns mert.datalog-04-05)
(require '[datomic.api :as d])
(def db-uri "datomic:dev://localhost:4334/hello")
(def conn (d/connect db-uri))
;; [[datalog_04_05.clj]]

(def db (d/db conn)) 
(def all-data-from-1985 '[:find ?title ?year ?genre 
                          :where [?e :movie/title ?title] 
                                 [?e :movie/release-year ?year] 
                                 [?e :movie/genre ?genre] 
                                 [?e :movie/release-year 1985]])
(def commando-id 
  (ffirst (d/q '[:find ?e 
                 :where [?e :movie/title "Commando"]] 
                db)))
@(d/transact conn [{:db/id commando-id :movie/genre "future governor"}])

(d/q all-data-from-1985 db) ; #{["The Goonies" 1985 "action/adventure"] ["Commando" 1985 "action/adventure"]}
(def db (d/db conn)) 
(d/q all-data-from-1985 db) ; #{["Commando" 1985 "future governor"] ["The Goonies" 1985 "action/adventure"]}

(def old-db (d/as-of db 13194139534317))
(d/q all-data-from-1985 old-db) ; #{["The Goonies" 1985 "action/adventure"] ["Commando" 1985 "action/adventure"]}

(def hdb (d/history db))

(d/q '[:find ?genre
       :where [?e :movie/title "Commando"]
              [?e :movie/genre ?genre]] hdb) ; #{["action/adventure"] ["future governor"]}
