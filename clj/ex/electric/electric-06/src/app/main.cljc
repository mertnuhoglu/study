(ns app.main
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.router :as router]
            [hyperfiddle.history2 :as h]
            [hyperfiddle.electric-dom2 :as dom]))

;; Here we have router function
;; But we don't have any navbar like links
;; We enter each page's url manually

(e/defn HomePage []
  (e/client
    (dom/h1 (dom/text "This is HomePage"))))

(e/defn About []
  (e/client
    (dom/h1 (dom/text "This is About"))))

(e/defn Main [ring-request]
  (e/client
    (binding [dom/node js/document.body]
      (dom/h1 (dom/text "electric-05"))
      (router/router (router/HTML5-History.)
                     (let [[page x :as route] (ffirst router/route)]
                       (if-not page
                         (router/Navigate!. [[::home]])
                         (router/focus [route]
                                     (case page
                                       ::home (e/server (HomePage.))
                                       ::about (e/server (About.))

                                       (e/client (dom/text "no matching route: " (pr-str page)))))))))))
