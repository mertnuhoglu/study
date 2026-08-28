;; [[mert_utils_data_structure.clj]]
(ns mert-utils-data-structure)

(require '[clojure.java.io :as io]
         '[clojure.string :as str]
         '[clojure.set :as set]
         '[cheshire.core :as json]
         '[clojure.edn :as edn]
         '[clojure.pprint :refer [pprint]]
         '[babashka.fs :as fs]
         '[babashka.process :as p])

(defn file->set [filename]
  (with-open [rdr (io/reader filename)]
    (->> (line-seq rdr)
         (map str/trim)
         (remove empty?)
         set)))

(defn setops [s1 s2]
  (let [union       (set/union s1 s2)
        inter       (set/intersection s1 s2)
        only-a      (set/difference s1 s2)
        only-b      (set/difference s2 s1)]
    {:counts       {:a (count s1)
                    :b (count s2)
                    :union       (count union)
                    :intersection (count inter)
                    :only-a       (count only-a)
                    :only-b       (count only-b)}
     :sets         {:a       (sort s1)
                    :b       (sort s2)
                    :union   (sort union)
                    :intersection (sort inter)}
     :differences  {:only-in-a only-a
                    :only-in-b only-b}
     :timestamps   {:run-at (str (java.time.LocalDateTime/now))}}))

(defn files2setops [file1 file2]
  (let [s1 (file->set file1)
        s2 (file->set file2)
        rs (setops s1 s2)]
    (assoc rs :files {:a file1 :b file2})))

(defn sort-map [x]
  (cond
    (map? x) (into (sorted-map) (map (fn [[k v]] [k (sort-map v)]) x))
    (sequential? x) (vec (map sort-map x))
    :else x))


