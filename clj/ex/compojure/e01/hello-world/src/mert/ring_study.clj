(ns mert.ring-study
  (:require
    [compojure.core :refer :all]
    [compojure.route :as route]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
    [clojure.test :refer :all]
    [ring.mock.request :as mock]
    [compojure.response :as response]
    [compojure.coercions :as coercions]
    [clout.core :as clout]
    [compojure.core :refer :all]))

(comment

  ((GET "/foo" [x y]
     (is (= x "bar"))
     (is (= y "baz"))
     nil)
   (-> (mock/request :get "/foo")
     (assoc :params {:x "bar", :y "baz"})))
  (let [req (-> (mock/request :get "/foo")
              (assoc :params {:y "bar"}))]
    ((GET "/:x" [x :as r]
       (is (= x "foo"))
       (is (= (dissoc r :params :route-params :compojure/route)
             (dissoc req :params)))
       nil)
     req))

  ((GET "/foo/:x" [x] (str x))
   (mock/request :get "/foo/bar"))
  ;=> {:status 200, :headers {"Content-Type" "text/html; charset=utf-8"}, :body "bar"}
  (mock/request :get "/foo/bar")
  ;=>
  ;{:protocol "HTTP/1.1",
  ; :server-port 80,
  ; :server-name "localhost",
  ; :remote-addr "localhost",
  ; :uri "/foo/bar",
  ; :scheme :http,
  ; :request-method :get,
  ; :headers {"host" "localhost"}}
  (GET "/foo/:x" [x] (str x))
  ;=> #function[compojure.core/wrap-route-matches/fn--8418]

  ; GET bir fonksiyon dönüyor
  ; bu fonksiyona bir request argümanı aktarıyoruz
  ; bu fonksiyon da response dönüyor
  ; dolayısıyla GET aslında bir handler fonksiyonu dönüyor

  ; şu alttaki GET çağrılarının hepsi bir handler döndürüyor
  (GET "/foo" [x y]
    (is (= x "bar"))
    (is (= y "baz"))
    nil)
  (GET "/foo" [x y]
    nil)
  (GET "/foo" [x y])
  ; aralarındaki fark ne?

  ((GET "/foo/:x" [x] (str x))
   (mock/request :get "/foo/bar"))
,)

(GET "/foo/:x" [x] (str x))
((GET "/foo/:x" [x] (str x))
 (mock/request :get "/foo/bar"))
