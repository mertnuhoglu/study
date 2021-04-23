(ns practicalli.p05
  (:gen-class)
  (:require [org.httpkit.server :as app-server]
            [compojure.core :refer [defroutes GET]]))


;; Routing

(defroutes app
  (GET "/" [] {:status 200 :body "App Server Running"}))


;; System

(defonce app-server-instance (atom nil))


(defn app-server-stop
  "Gracefully shutdown the server, waiting 100ms"
  []
  (when-not (nil? @app-server-instance)
    (@app-server-instance :timeout 100)
    (reset! app-server-instance nil)
    (println "INFO: Application server shutting down...")))


(defn app-server-start
  "Start the application server and run the application"
  [port]
  (println "INFO: Starting server on port: " port)

  (reset! app-server-instance
          (app-server/run-server #'app {:port (Integer/parseInt port)})))

(defn -main
  "Start the application server on a speicific port"
  [& [port]]
  (let [port (Integer. (or port (System/getenv "PORT") 8888))]
    (app-server-start port)))


(defn app-server-restart
  "Convenience function to stop and start the application server"
  []
  (app-server-stop)
  (-main))


;; REPL driven development

(comment

  ;; Re/Start application server
  (app-server-restart)

  ;; Shutdown server
  (app-server-stop))



