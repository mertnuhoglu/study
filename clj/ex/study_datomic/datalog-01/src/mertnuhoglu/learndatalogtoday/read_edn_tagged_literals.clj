(ns mertnuhoglu.learndatalogtoday.read-edn-tagged-literals)

(defn read-file [s]
  (read-string (slurp s)))

(def schema (read-file "resources/db/data01.edn"))

schema
;=>
[{:db/id [:db.part/user -100], :person/name "James Cameron", :person/born #inst"1954-08-16T00:00:00.000-00:00"}]

; cannot read tagged literals such as:
; {:db/id #db/id [:db.part/user -100]}
#_(def schema02 (read-file "resources/db/data02.edn"))
;Error: No reader function for tag db/id

(def file "resources/db/data02.edn")
#_(clojure.edn/read-string
    {:readers {'db/id #'datomic.db/id-literal
               'db/fn (fn [x]
                        (assoc (datomic.function/construct x) :fnref nil))}}

    (slurp file))
;=> Attempting to call unbound fn: #'datomic.db/id-literal
