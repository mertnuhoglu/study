(ns ex02.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (POST "/e01" [name] (str name " loves coderanch.com"))
  (POST "/e02" request (str (:form-params request)))
  (POST "/e03" request (str (:params request)))
  (POST "/e04" {{value :name} :form-params} (str "My name is: " value))
  (POST "/e05" {value :name} (str "My name is: " value))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes api-defaults))

