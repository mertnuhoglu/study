(ns core
  (:require [clojure.string :as str]))

(defn sq [a]
  (* a (+ 3 a)))

(sq 2)

true
\H
#".*"
(#".*")
:keyw
:key-weird?#+-
[2 3 5]
{:a 1 :b 2}

(str/trim " ali vx ")

(def names (slurp "/usr/share/dict/propernames"))

(def names2
  (str/split-lines
    (slurp "/usr/share/dict/propernames")))

(:some-thing)

(ali-veli)

(count
  (map name [:a :b :c]))

(let [x 10 y 20]
  test)
