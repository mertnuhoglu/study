(ns mert.e02e
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.reload :refer [wrap-reload]]))

(comment
  (def request
    {:status  200
     :headers {"Content-Type" "text/plain"}
     :body    "Hello e02c updated2"})
  (def handler identity)
  (handler request)

  (wrap-content-type request)
  ((wrap-content-type request) request)
  (def handler [response]
    (wrap-content-type response))


  ,)
