(ns practicalli.p02
  (:gen-class)
  (:require [org.httpkit.server :as app-server]
            [compojure.core :refer [defroutes GET]]))


;; Routing

(defroutes app
  (GET "/" [] {:status 200 :body "App Server Running"}))


;; System

(defn app-server-start
  "Start the application server and run the application"
  [port]
  (println "INFO: Starting server on port: " port)
  (app-server/run-server #'app {:port port}))

(defn -main
  "Start the application server on a speicific port"
  [& [port]]
  (let [port (Integer. (or port (System/getenv "PORT") 8888))]
    (app-server-start port)))


;; REPL driven develpment

(comment

  (def app-server-instance (-main 8888))
  (app-server-instance :timeout 100))

