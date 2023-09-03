(ns mert.e20)

; Tarih: 20230607
; Title: Temp idlerle referans verme

(require '[datomic.client.api :as d])

;; Memory storage
(def client (d/client {:server-type :dev-local
                       :storage-dir :mem
                       :system "ci"}))

(d/delete-database client {:db-name "db20"})
(d/create-database client {:db-name "db20"})
(def conn (d/connect client {:db-name "db20"}))

(def schema
  [{:db/ident       :user/name
    :db/doc         "The unique username of a user."
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity}])
(d/transact conn {:tx-data schema})
(def data01
  [{:user/name    "jillosaurus"}
   {:user/name    "jonnyboy"}])
(d/transact conn {:tx-data data01})

(d/q '[:find ?e ?name
       :where [?e :user/name ?name]]
  (d/db conn))
;=> [[96757023244362 "jillosaurus"] [96757023244363 "jonnyboy"]]

(def schema02
  [{:db/ident       :user/friends
    :db/valueType   :db.type/ref
    :db/cardinality :db.cardinality/many}

   {:db/ident       :user/admin?
    :db/valueType   :db.type/boolean
    :db/cardinality :db.cardinality/one}])

(d/transact conn {:tx-data schema02})

(d/transact conn {:tx-data [{:db/id        "user1"
                             :user/name    "hugabug"
                             :user/friends #{"user2"}}

                            {:db/id        "user2"
                             :user/name    "plexus"
                             :user/admin?  true
                             :user/friends #{[:user/name "jillosaurus"]}}]})

(d/q '[:find ?e
       :where [?e :user/friends _]]
  (d/db conn))
;=> [[79164837199948] [79164837199949]]
