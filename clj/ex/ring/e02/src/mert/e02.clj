(ns mert.e02
  (:use ring.adapter.jetty))

(defn handler [request]
  {:status  200
   :headers {"Content-Type" "text/plain"}
   :body    "Hello"})

(run-jetty handler {:port 8080})

(comment
  (def request
    {:status  200
     :headers {"Content-Type" "text/plain"}
     :body    "Hello"})
  (handler request)
  ,)