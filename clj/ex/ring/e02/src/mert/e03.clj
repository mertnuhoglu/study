(ns mert.e03
  (:require [ring.middleware.params :refer [wrap-params]])
  (:use ring.middleware.params
    ring.util.response
    ring.adapter.jetty))

; code from: [Ring parameter example](https://gist.github.com/weavejester/598020)

(defn page [name]
  (str "<html><body>"
    (if name
      (str "Nice to meet you, " name "!")
      (str "<form>"
        "Name: <input name='name' type='text'>"
        "<input type='submit'>"
        "</form>"))
    "</body></html>"))

(defn handler [{{name "name"} :params}]
  (-> (response (page name))
    (content-type "text/html")))

(def app
  (-> handler wrap-params))

(comment
  (run-jetty app {:port 8080})

  (def req {:query-string "name=ali"})
  (handler req)
  ;=>
  ;{:status 200,
  ; :headers {"Content-Type" "text/html"},
  ; :body "<html><body><form>Name: <input name='name' type='text'><input type='submit'></form></body></html>"}
  (def handler2 (wrap-params handler))
  (handler2 req)
  ;=> {:status 200, :headers {"Content-Type" "text/html"}, :body "<html><body>Nice to meet you, ali!</body></html>"}

 ,)
