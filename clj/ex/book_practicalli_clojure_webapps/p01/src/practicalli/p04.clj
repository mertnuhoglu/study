(ns practicalli.p04
  (:gen-class)
  (:require [org.httpkit.server :as server]))

; Code from: [Practicalli: Clojure web server from scratch with deps.edn](https://practicalli.github.io/blog/posts/clojure-web-server-cli-tools-deps-edn/)

(defn handler
  "A function that handles all requests from the server.
  Arguments: `req` is a ring request hash-map
  Return: ring response hash-map including :status :headers and :body"
  [req]
  {:status  200}
  :headers {"Content-Type" "text/html"}
  :body    "Hello Clojure Server world!")

(defn create-server
  "A ring-based server listening to all http requests
  port is an Integer greater than 128"
  [port]
  (server/run-server handler {:port port}))

(comment
  (create-server 8080)
  ,)
