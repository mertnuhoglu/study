(ns mert.e04
  (:use ring.middleware.session
        ring.util.response
        ring.adapter.jetty))

(defn handler [{session :session, uri :uri}]
  (let [n (session :n 1)]
    (if (= uri "/")
      (-> (response (str "You have visited " n " times"))
        (content-type "text/plain")
        (assoc-in [:session :n] (inc n)))
      (-> (response "Page not found")
        (status 404)))))

(def app
  (-> handler wrap-session))

(comment
  (run-jetty app {:port 8080})

  (def handler2 (wrap-session handler))
  (def req {:uri "/"})
  (handler2 req)
  (handler2 req)
  ,)

