(ns mertnuhoglu.e01-no-dbid-tags
  (:require
    [datomic.api :as d]))

(defn read-file [s]
  (read-string (slurp s)))

(def schema (read-file "resources/db/schema.edn"))
; no literal tags
(def seed-data (read-file "resources/db/data01.edn"))
(def uri "datomic:mem://movies")
(def conn
  (do (d/delete-database uri)
    (d/create-database uri)
    (d/connect uri)))
(d/transact conn schema)
(d/transact conn seed-data)
;#object[datomic.promise$settable_future$reify__6487
;        0x406fc6f7
;        {:status :failed,
;         :val #error{:cause ":db.error/not-an-attribute :db.part/user is not an attribute.",
;                     :data #:db{:error :db.error/not-an-attribute},
;                     :via [{:type java.util.concurrent.ExecutionException,
;                            :message "java.lang.IllegalArgumentException: :db.error/not-an-attribute :db.part/user is not an attribute.",
;                            :at [datomic.promise$throw_executionexception_if_throwable invokeStatic "promise.clj" 10]}
;                           {:type datomic.impl.Exceptions$IllegalArgumentExceptionInfo,
;                            :message ":db.error/not-an-attribute :db.part/user is not an attribute.",
;                            :data #:db{:error :db.error/not-an-attribute},
;                            :at [datomic.error$arg invokeStatic "error.clj" 57]}],}}]
