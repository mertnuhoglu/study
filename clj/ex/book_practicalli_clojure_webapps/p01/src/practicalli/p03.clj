(ns practicalli.p03
  (:gen-class)
  (:require [org.httpkit.server :as server]))

; Code from: [Practicalli: Clojure web server from scratch with deps.edn](https://practicalli.github.io/blog/posts/clojure-web-server-cli-tools-deps-edn/)

(defn create-server
  "A ring-based server listening to all http requests
  port is an Integer greater than 128"
  [port]
  (server/run-server handler {:port port}))

(defn handler
  "A function that handles all requests from the server.
  Arguments: `req` is a ring request hash-map
  Return: ring response hash-map including :status :headers and :body"
  [req]
  {:status  200}
  :headers {}
  :body    "Hello Clojure Server world!")

(defonce ^:private api-server (atom nil))

(defn stop-server
  "Gracefully shutdown the server, waiting 100ms "
  []
  (when-not (nil? @api-server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@api-server :timeout 100)
    (reset! api-server nil)))

(defn -main [& args]
  ;; #' enables hot-reloading of the server
  (reset! api-server (server/run-server #'handler {:port (or (first args) 8080)})))

(comment
  (-main)
  (stop-server)
  ,)
