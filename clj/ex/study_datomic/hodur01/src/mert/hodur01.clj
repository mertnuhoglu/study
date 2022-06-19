(ns mert.hodur01)

(require '[hodur-engine.core :as hodur])

(def meta-db (hodur/init-schema
              '[Person
                [^String first-name
                  ^String last-name]]))

(require '[hodur-datomic-schema.core :as hodur-datomic])

(def meta-db (hodur/init-schema
              '[^{:datomic/tag-recursive true}
                Person
                [^String first-name
                  ^String last-name]]))

(def datomic-schema (hodur-datomic/schema meta-db))

datomic-schema
; [#:db{:ident :person/first-name,
;       :valueType :db.type/string,
;       :cardinality :db.cardinality/one}
;  #:db{:ident :person/last-name,
;       :valueType :db.type/string,
;       :cardinality :db.cardinality/one}]
