; from: [Practicalli: Consuming APIs from Clojure](https://practicalli.github.io/blog/posts/consuming-apis-with-clojure/)

(ns practicalli.p08
  (:gen-class)
  (:require [clojure.data.json :as json]
    [org.httpkit.client :as client]))

(comment
  ; get a web page
  (client/get "https://practicalli.github.io/blog/")
  ; => #object[org.httpkit.client$deadlock_guard$reify__5290 0x580c4abf {:status :pending, :val nil}]

  ; get returns a promise, we need to deref it to get the value
  (deref (client/get "https://practicalli.github.io/blog/"))
  @(client/get "https://practicalli.github.io/blog/")

  ; scrape the page
  (get
    @(client/get "https://practicalli.github.io/blog/")
    :body)

  @(client/get "http://date.jsontest.com/" )
  ;{:opts {:method :get, :url "http://date.jsontest.com/"},
  ; :body "{
  ;         \"date\": \"04-23-2021\",
  ;         \"milliseconds_since_epoch\": 1619174631189,
  ;         \"time\": \"10:43:51 AM\"
  ;      }
  ;      ",
  ; :headers {:access-control-allow-origin "*",
  ;           :content-length "100",
  ;           :content-type "application/json",
  ;           :date "Fri, 23 Apr 2021 10:43:51 GMT",
  ;           :server "Google Frontend",
  ;           :x-cloud-trace-context "fa7158d2448593810d4d54cd16d3530b"},
  ; :status 200}

  (:body
    @(client/get "http://date.jsontest.com/" {:accept :json}))
  ;=> "{\n   \"date\": \"04-23-2021\",\n   \"milliseconds_since_epoch\": 1619174677055,\n   \"time\": \"10:44:37 AM\"\n}\n"

  (keys
    @(client/get "http://date.jsontest.com/" {:accept :json}))
  ;=> (:opts :body :headers :status)
,)