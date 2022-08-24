(ns clojurekoans02)

;; Source: [functional-koans/clojure-koans: A set of exercises for learning Clojure](https://github.com/functional-koans/clojure-koans)

(require '[clojure.string :as string])

(subs "Hello World" 6 11)

(string/join '(1 2 3))

(map nil? [:a :b nil :c :d])