(ns mertnuhoglu.learndatalogtoday-fork
  (:require [clojure.edn :as edn]
            [compojure.core :refer [routes GET POST]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [datomic-query-helpers.core :refer [check-query
                                                normalize
                                                pretty-query-string]]
            [datomic.api :as d]
            [fipp.edn :as fipp]
            [hiccup.page :refer [html5]]
            [ring.adapter.jetty :as jetty]
            [taoensso.timbre :as log]))

(defn read-file [s]
  (read-string (slurp s)))

(defn init-db [name schema seed-data]
  (let [uri (str "datomic:mem://" name)
        conn (do (d/delete-database uri)
               (d/create-database uri)
               (d/connect uri))]
    @(d/transact conn schema)
    @(d/transact conn seed-data)
    (d/db conn)))

(def schema (read-file "resources/db/schema.edn"))
(def seed-data (read-file "resources/db/data.edn"))
(def uri "datomic:mem://movies")
(def db (init-db "movies" schema seed-data))

(def chapter 0)
(def exercise 0)

(def dev? true)
(defn read-chapter-data [chapter]
  (->> chapter
    (format "resources/chapters/chapter-%s.edn")
    read-file))
(def read-chapter-data (if dev? read-chapter-data (memoize read-chapter-data)))

(read-chapter-data chapter)
(def ans-input
  (get-in (read-chapter-data chapter) [:exercises exercise :inputs]))

(def whitelist '#{< > <= >= not= = tutorial.fns/age .getDate .getMonth
                  movie-year sequels friends avg min max sum count})
(defn validate [[query & args]]
  (let [syms (check-query query args whitelist)]
    (if (empty? syms)
      (cons (normalize query) args)
      (throw (ex-info (str "Non-whitelist symbol used in query/args: " syms
                        ". The symbol whitelist is " whitelist)
               {:syms syms})))))
(def usr-input
  (edn/read-string
    "[:find ?title\n :where\n [_ :movie/title ?title]\n ]"))

(def ans2
  (validate (map #(or (:correct-value %1) %2) ans-input usr-input)))
;=> ({:find (?title), :where ([_ :movie/title ?title])})

(def ans-query (first ans2))
;=> => {:find (?title), :where ([_ :movie/title ?title])}

(def ans-args (second ans2))
;=> nil

(def ans-result
  (apply d/q ans-query db ans-args))
;=>
#{["Alien"]
  ["First Blood"]
  ["Terminator 2: Judgment Day"]}

(d/q
  ans-query
  db)
;=>
#{["Alien"]
  ["First Blood"]
  ["Terminator 2: Judgment Day"]}

(d/q
  '{:find (?title), :where ([_ :movie/title ?title])}
  db)
;=>
#{["Alien"]
  ["First Blood"]
  ["Terminator 2: Judgment Day"]}
