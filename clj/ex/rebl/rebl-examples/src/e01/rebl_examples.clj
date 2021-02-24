(ns e01.rebl-examples
  (:import java.io.File)
  (:require [clojure.java.io :as io]
            [clojure.core.protocols :as p]))

(defn foo
  "I don't do a whole lot."
  [x]
  (prn x "Hello, World!")
  (str x " hello"))

(clojure-version)

(System/getProperty "user.dir")

(+ 1 2)

{:a 1}

{:web
 {:game-night (io/as-url "https://github.com/clojureconj/clojureconj2018/wiki/Board-Game-Night")}}

{:data
 {:scalar "Hello"
  :powers [0 1 4 9 16]
  :keyed-pairs {:a [[1 2] [-3 5]] :b [[4 8]]}
  :tuples [[1 2] [3 4] [5 6]]
  :code '(defn foo [x] "Hello World")
  :bigger (repeatedly 100 (fn [] {:alpha (rand-int 100)
                                  :beta (rand-int 100)}))
  :chart (with-meta [1 2 3] {:rebl/xy-chart {:title "My Chart"}})}}

;{:datafy
 ;:throwable (ex-info "Boom" {:a 1 :b 2})
 ;:ref (atom (range 100))
 ;:spec (s/keys :req-un [::a ::b])}
