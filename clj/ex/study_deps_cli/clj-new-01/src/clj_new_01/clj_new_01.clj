(ns clj-new-01.clj-new-01
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn my-fn [& args]
  (println args)
  (println "Hello, World! from my-fn"))

(defn my-fn-no-arg []
  (println "Hello, World! from my-fn"))
