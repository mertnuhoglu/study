(ns mert.e02f
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.util.io :refer [string-input-stream]]))
            

(comment

  (defn test-handler [request]
    {:status 200
     :headers {}
     :body (string-input-stream "handler")})

  (def handler (wrap-resource test-handler "/mert/assets"))
  (def request {:request-method :get, :uri "/foo.html"})
  (handler request)
  ;=>
  ;{:status 200,
  ; :headers {"Content-Length" "3", "Last-Modified" "Fri, 09 Apr 2021 16:15:15 GMT"},
  ; :body #object[java.io.File
  ;               0x91db737f
  ;               "/Users/mertnuhoglu/projects/study/clj/ex/ring/e02/resources/mert/assets/foo.html"]}
  (:body (handler request))
  (slurp (:body (handler request)))
  ;=> "foo"

  (def request {:request-method :get, :uri "/bars.txt"})
  (slurp (:body (handler request)))

  (def handler (wrap-params identity))
  (def req {:query-string "foo=bar&biz=bat%25"})
  (handler req)
  ;=>
  ;{:query-string "foo=bar&biz=bat%25",
  ; :form-params {},
  ; :params {"foo" "bar", "biz" "bat%"},
  ; :query-params {"foo" "bar", "biz" "bat%"}}

  (def req {:query-string "foo=bar"
            :headers      {"content-type" "application/x-www-form-urlencoded"}
            :body         (string-input-stream "biz=bat%25")})
  (handler req)
  ;=>
  ;{:query-string "foo=bar",
  ; :headers {"content-type" "application/x-www-form-urlencoded"},
  ; :body #object[java.io.ByteArrayInputStream 0xd881502 "java.io.ByteArrayInputStream@d881502"],
  ; :form-params {"biz" "bat%"},
  ; :params {"biz" "bat%", "foo" "bar"},
  ; :query-params {"foo" "bar"}}

  (def response {:headers {}})
  (def handler  (wrap-content-type (constantly response)))
  (handler {:uri "/foo/bar.png"})
  ;=> {:headers {"Content-Type" "image/png"}}


  ,)
