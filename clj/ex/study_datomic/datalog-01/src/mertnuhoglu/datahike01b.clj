(ns mertnuhoglu.datahike01b
  (:require [datahike.api :as dh]))

; Adapted from:
; [replikativ/datahike: A durable Datalog implementation adaptable for distribution.](https://github.com/replikativ/datahike)
; [datahike.api — io.replikativ/datahike 0.4.1491](https://cljdoc.org/d/io.replikativ/datahike/0.4.1491/api/datahike.api#datoms)

(def cfg {:store {:backend :mem :id "schemaless"} :schema-flexibility :read})
(dh/create-database cfg)
(def db (dh/connect cfg))

; Add referenced data
(dh/transact db [{:db/ident :name
                  :db/type :db.type/string
                  :db/cardinality :db.cardinality/one}
                 {:db/ident :likes
                  :db/type :db.type/string
                  :db/index true
                  :db/cardinality :db.cardinality/many}
                 {:db/ident :friends
                  :db/type :db.type/ref
                  :db/cardinality :db.cardinality/many}])

(dh/transact db [{:db/id 4 :name "Ivan"}
                 {:db/id 4 :likes "fries"}
                 {:db/id 4 :likes "pizza"}
                 {:db/id 4 :friends 5}])


(dh/transact db [{:db/id 5 :name "Oleg"}
                 {:db/id 5 :likes "candy"}
                 {:db/id 5 :likes "pie"}
                 {:db/id 5 :likes "pizza"}])

(dh/datoms @db {:index :eavt})
;=>
(#datahike/Datom[1 :db/cardinality :db.cardinality/one 536870913 true]
 #datahike/Datom[1 :db/ident :name 536870913 true]
 #datahike/Datom[1 :db/type :db.type/string 536870913 true]
 #datahike/Datom[2 :db/cardinality :db.cardinality/many 536870913 true]
 #datahike/Datom[2 :db/ident :likes 536870913 true]
 #datahike/Datom[2 :db/index true 536870913 true]
 #datahike/Datom[2 :db/type :db.type/string 536870913 true]
 #datahike/Datom[3 :db/cardinality :db.cardinality/many 536870913 true]
 #datahike/Datom[3 :db/ident :friends 536870913 true]
 #datahike/Datom[3 :db/type :db.type/ref 536870913 true]
 #datahike/Datom[4 :friends 5 536870914 true]
 #datahike/Datom[4 :likes "fries" 536870914 true]
 #datahike/Datom[4 :likes "pizza" 536870914 true]
 #datahike/Datom[4 :name "Ivan" 536870914 true]
 #datahike/Datom[5 :likes "candy" 536870915 true]
 #datahike/Datom[5 :likes "pie" 536870915 true]
 #datahike/Datom[5 :likes "pizza" 536870915 true]
 #datahike/Datom[5 :name "Oleg" 536870915 true]
 #datahike/Datom[536870913 :db/txInstant #inst"2022-04-18T07:15:33.913-00:00" 536870913 true]
 #datahike/Datom[536870914 :db/txInstant #inst"2022-04-18T07:15:38.693-00:00" 536870914 true]
 #datahike/Datom[536870915 :db/txInstant #inst"2022-04-18T07:15:40.465-00:00" 536870915 true])

; Cardinality many attributes are returned sequences:
(:likes (dh/entity @db 4))
;=> #{"fries" "pizza"}

;Reference attributes are returned as another entities:
(:friends (dh/entity @db 4)) ; => {:db/id 5}

;References can be walked backwards by prepending _ to name part of an attribute:
(:_friends (dh/entity @db 5)) ; => [{:db/id 4}]
;(:ns/_ref (entity db 2)) ; => [{:db/id 1}]

;Unlike entity, returns plain Clojure map (not lazy).
(dh/pull @db [:db/id, :name, :likes, {:friends [:db/id :name]}] 4)
;=>
{:db/id 4, :name "Ivan", :likes ["fries" "pizza"], :friends [{:db/id 5, :name "Oleg"}]}

;Same as pull, but accepts sequence of ids and returns sequence of maps.
(dh/pull-many @db [:db/id :name] [4 5])
;=>
[{:db/id 4, :name "Ivan"} {:db/id 5, :name "Oleg"}]

;Query as parameter with additional args:
(dh/q
 '[:find ?value
   :where [_ :likes ?value]]
 #{[1 :likes "fries"]
   [2 :likes "candy"]
   [3 :likes "pie"]
   [4 :likes "pizza"]})
;=> #{["fries"] ["pie"] ["pizza"] ["candy"]}

;Or query passed in arg-map:
(dh/q {:query '[:find ?value
                :where [_ :likes ?value]]
       :offset 2
       :limit 1
       :args [#{[1 :likes "fries"]
                [2 :likes "candy"]
                [3 :likes "pie"]
                [4 :likes "pizza"]}]})
; => #{["fries"] ["candy"] ["pie"] ["pizza"]}

;Or query passed as map of vectors:
(dh/q
 '{:find [?value] :where [[_ :likes ?value]]}
 #{[1 :likes "fries"]
   [2 :likes "candy"]
   [3 :likes "pie"]
   [4 :likes "pizza"]})
; => #{["fries"] ["candy"] ["pie"] ["pizza"]}

;Or query passed as string
(dh/q
 {:query "[:find ?value :where [_ :likes ?value]]"
  :args [#{[1 :likes "fries"]
           [2 :likes "candy"]
           [3 :likes "pie"]
           [4 :likes "pizza"]}]})
; => #{["fries"] ["candy"] ["pie"] ["pizza"]}

(dh/schema @db)
;=>
{:name #:db{:id 1, :ident :name, :cardinality :db.cardinality/one},
 :likes #:db{:ident :likes, :index true, :cardinality :db.cardinality/many, :id 2},
 :friends #:db{:ident :friends, :cardinality :db.cardinality/many, :id 3}}
