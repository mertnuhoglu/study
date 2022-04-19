(ns mertnuhoglu.e01-id-literal-tag)

;; Adapted from: [Reader macros and why you can go wrong with them](http://subhash.github.io/datomic/reader/2015/05/12/reader-macros.html)

(require '[datomic.db/db :as ddb])

(def id (datomic.db/id-literal [:db.part/user 100]))
; error

