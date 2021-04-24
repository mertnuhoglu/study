; from: [Practicalli: Clojure Webapp routing and APIs with JSON](https://practicalli.github.io/blog/posts/webapp-routes-with-json/)

(ns practicalli.p07
  (:gen-class)
  (:require
    [org.httpkit.server :as server]
    [compojure.core :refer [defroutes GET POST]]
    [compojure.route :refer [not-found]]
    [ring.util.response :refer [response]]
    [ring.handler.dump :refer [handle-dump]]
    [clojure.data.json :as json]))

(def http-response-code
  {:OK  200
   :BAD 404})

(defn hello-html
  [req]
  {:status  (:OK http-response-code)
   :headers {"Content-Type" "text/html"}
   :body    "<h1>Hello Clojure Server world!</h1>"})

(defn hello-world
  [_]
  (response "Hello clojure world"))

(defn scores
  "Returns the current scoreboard as JSON"
  [_]
  (println "Calling the scoreboard handler...")
  {:headers {"Content-type" "application/json"}
   :status  (:OK http-response-code)
   :body    (json/write-str {:players
                             [{:name "johnny-be-doomed" :high-score 1000001}
                              {:name "jenny-jetpack" :high-score 23452345}]})})

(def scoreboard
  {:players
   [{:name "johnny-be-doomed" :high-score 1000001}
    {:name "jenny-jetpack" :high-score 23452345}
    {:name "fred" :high-score 23452345}]})

(defn player-score
  "Returns the current scoreboard as JSON"
  [request]
  (println "Calling the player handler...")
  (let [player (get-in request [:route-params :name])]
    {:headers {"Content-type" "application/json"}
     :status  (:OK http-response-code)
     :body    (json/write-str
                (filter
                  (fn [player-entry]
                    (= player (:name player-entry)))
                  (get scoreboard :players)))}))

(defroutes webapp
  (GET "/" [] hello-html)
  (GET "/hello-response" [] hello-world)
  (GET "/request-info" [] handle-dump)
  (GET "/scores" [] scores)
  (GET "/player/:name" [] player-score)
  (not-found "<h1>Not found</h1>"))

(def server (atom nil))

(defn stop-server
  []
  (when-not (nil? @server)
    (println "INFO: Gracefully shutting down server...")
    (@server :timeout 100)
    (reset! server nil)))

(defn -main
  [& {:keys [ip port]
      :or   {ip   "0.0.0.0"
             port 8000}}]
  (println "INFO: Starting httpkit server - listening on: " (str "http://" ip ":" port))
  (reset! server (server/run-server #'webapp {:port port})))

(defn restart-server
  []
  (stop-server)
  (-main))

(comment
  (restart-server)
  (-main :port 8080),)

