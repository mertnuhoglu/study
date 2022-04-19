(ns mertnuhoglu.e01-print-def-var
  (:require
    [mertnuhoglu.e01 :as e01]))

; print a def var
(require '[fipp.edn :refer [pprint] :rename {pprint fipp}])
(fipp e01/schema)
;=>
;[{:db/ident :movie/title,
;  :db/valueType :db.type/string,
;  :db/cardinality :db.cardinality/one,
;  :db/id #datomic.db.DbId{:part :db.part/db, :idx -1000072},}]
;  ...

