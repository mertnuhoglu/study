(ns ex02.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (POST "/" [name] (str name " loves coderanch.com"))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes api-defaults))

