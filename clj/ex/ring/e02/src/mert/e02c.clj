(ns mert.e02c
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :refer [wrap-reload]]))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello e02c updated2"})

(defn -main []
  (println "server started on port 3000")
  (run-jetty (wrap-reload #'handler) {:port 8080
                                      :join? false})
  )

(comment
  (-main))