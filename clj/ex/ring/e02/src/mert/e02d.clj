(ns mert.e02d
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.reload :refer [wrap-reload]]))

; not working properly

(defn our-middleware [response content-type]
  (assoc-in response [:headers "Content-Type"] content-type))

(defn handler [request]
  (wrap-content-type
    (our-middleware
      {:status  200
       :headers {"Content-Type" "text/plain"}
       :body    "Hello e02c updated2"}
      "text/plain")
    "application/json"))
  

(defn -main []
  (println "server started on port 8080")
  (run-jetty (wrap-reload #'handler) {:port  8080
                                      :join? false}))
  

(comment
  (-main)

  (def request
    {:status  200
     :headers {"Content-Type" "text/plain"}
     :body    "Hello e02c updated2"})
  (handler request)

  (defn handler [request]
    (our-middleware
      {:status  200
       :headers {"Content-Type" "text/plain"}
       :body    "Hello e02c updated2"}
      "text/plain"))
    
  (handler request)

  (defn handler [request]
    (wrap-content-type
      {:status  200
       :headers {"Content-Type" "text/plain"}
       :body    "Hello e02c updated2"}
      "text/plain"))
    
  (handler request))


  
