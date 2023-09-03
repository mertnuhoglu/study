(ns fulcro.study-fulcro-book
  (:require [portal.api :as p]
            [clojure.java.io :refer [reader] :as io]
            [clojure.edn :as edn]
            [clojure.data :refer [diff]]
            [clojure.pprint :refer [pprint]]
            ))

(comment
  (tap> :hello)
; ex11
  (def edn01
    (io/file "/Users/mertnuhoglu/projects/study/clj/ex/study_fulcro/book/out/ex11_union_example-1_router_tony.edn"))
  edn01
  (def edn02
    (io/file "/Users/mertnuhoglu/projects/study/clj/ex/study_fulcro/book/out/ex11_union_example-1_router_toaster.edn"))

  (def edn01
    (edn/read-string
      (slurp "/Users/mertnuhoglu/projects/study/clj/ex/study_fulcro/book/out/ex11_union_example-1_router_tony.edn")))
  edn01
  (def edn02
    (edn/read-string
      (slurp "/Users/mertnuhoglu/projects/study/clj/ex/study_fulcro/book/out/ex11_union_example-1_router_toaster.edn")))
  (diff edn01 edn02)
  (tap> edn01)
,)

(comment
; ex11
  (r/router-instruction :detail-router [:param/kind :param/id])
,)