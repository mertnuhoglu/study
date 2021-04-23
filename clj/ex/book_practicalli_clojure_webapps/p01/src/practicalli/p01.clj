(ns practicalli.p01
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [compojure.core :refer [defroutes GET]]))


;; Routing

(defroutes app
  (GET "/" [] {:status 200 :body "App Server Running"}))


;; System

(defn app-server-start
  [port]
  #_(jetty/run-jetty (site #'app) {:port port :join? false}))


(defn -main [& [port]]
  (let [port (Integer. (or port
                           (System/getenv "PORT")
                           8888))]
    (app-server-start port)))


;; REPL driven development

(comment

  (def app-server-instance (-main 8888))
  (.stop app-server-instance)
  ,)

