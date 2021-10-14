(ns edn
  (:require [cheshire.core :refer :all]
            [clojure.edn :as edn]),)

; [clojure-cookbook/4-14_read-write-clojure-data-structures.asciidoc at master · clojure-cookbook/clojure-cookbook](https://github.com/clojure-cookbook/clojure-cookbook/blob/master/04_local-io/4-14_read-write-clojure-data-structures.asciidoc)

; write clojure map as edn
(spit "out/data.edn" (pr-str [:a :b :c]))

; convert edn to json

(let [json (->
             (slurp "/Users/mertnuhoglu/projects/study/clj/ex/study_fulcro/glam/out/db_admin_project.edn")
             (edn/read-string)
             (generate-string {:pretty true}))]
  (spit "/Users/mertnuhoglu/projects/study/clj/ex/study_fulcro/glam/out/db_admin_project.json" json))

(comment
  (slurp "/Users/mertnuhoglu/projects/myrepo/db_settings.edn")
  (edn/read-string
    (slurp "/Users/mertnuhoglu/projects/myrepo/db_settings.edn"))
  (->
    (slurp "/Users/mertnuhoglu/projects/myrepo/db_settings.edn")
    (edn/read-string)
    (generate-string {:pretty true}))

  (let [json (->
               (slurp "/Users/mertnuhoglu/projects/study/clj/ex/study_fulcro/glam/out/db_settings.edn")
               (edn/read-string)
               (generate-string {:pretty true}))]
    (spit "/Users/mertnuhoglu/projects/study/clj/ex/study_fulcro/glam/out/db_settings.json" json)),)

; diff two edn data id=g12450
(require '[clojure.data :refer [diff]])
(require '[clojure.pprint :refer [pprint]])

(comment
  (def edn01
    (->
      (slurp "/Users/mertnuhoglu/projects/study/clj/ex/study_fulcro/glam/out/db_settings.edn")
      (edn/read-string)))
  (def edn02
    (->
      (slurp "/Users/mertnuhoglu/projects/study/clj/ex/study_fulcro/glam/out/db_admin_project.edn")
      (edn/read-string)))
    
  (def diffs
    (diff edn01 edn02))
  (def diff01
    (nth diffs 0))
  (type diffs)
  (spit "/Users/mertnuhoglu/projects/study/clj/ex/study_fulcro/glam/out/diff01.edn" (pr-str diff01))
  (spit "/Users/mertnuhoglu/projects/study/clj/ex/study_fulcro/glam/out/diff01.edn" (pprint diff01))
  (spit "/Users/mertnuhoglu/projects/study/clj/ex/study_fulcro/glam/out/diff01.edn" (with-out-str (pprint diff01)))
  (tap> diff01)

  (tap> [{:a 1} {:a 2}])
  ,)

; [dakrone/cheshire: Clojure JSON and JSON SMILE (binary json format) encoding/decoding](https://github.com/dakrone/cheshire)

;; generate some json
;; write some json to a stream
(generate-stream {:foo "bar" :baz 5} (clojure.java.io/writer "/tmp/foo"))

;; generate some SMILE
;; generate some JSON with Dates
;; the Date will be encoded as a string using
;; the default date format: yyyy-MM-dd'T'HH:mm:ss'Z'
(generate-string {:foo "bar" :baz (java.util.Date. 0)})

;; generate some JSON with Dates with custom Date encoding
(generate-string {:baz (java.util.Date. 0)} {:date-format "yyyy-MM-dd"})

;; generate some JSON with pretty formatting
(generate-string {:foo "bar" :baz {:eggplant [1 2 3]}} {:pretty true})
;; {
;;   "foo" : "bar",
;;   "baz" : {
;;     "eggplant" : [ 1, 2, 3 ]
;;   }
;; }

;; generate JSON escaping UTF-8
(generate-string {:foo "It costs £100"} {:escape-non-ascii true})
;; => "{\"foo\":\"It costs \\u00A3100\"}"

;; generate JSON and munge keys with a custom function
(generate-string {:foo "bar"} {:key-fn (fn [k] (.toUpperCase (name k)))})
;; => "{\"FOO\":\"bar\"}"

