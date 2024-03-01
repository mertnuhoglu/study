(ns mert.e02b
  (:use ring.adapter.jetty))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello"})

(defn -main []
  (run-jetty handler {:port 8080}))
  
