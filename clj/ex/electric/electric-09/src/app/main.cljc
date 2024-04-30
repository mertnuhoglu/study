(ns app.main
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.router :as router]
            [hyperfiddle.history2 :as h]
            #?(:cljs [reagent.core :as r])
            #?(:cljs [reagent.dom :as rdom])
            #?(:cljs ["react" :as react])
            #?(:cljs ["react-dom/client" :as ReactDom])
            [app.system-properties :refer [SystemProperties]]
            [app.chat :refer [Chat]]
            [hyperfiddle.electric-dom2 :as dom]))

;; This example adds a Navbar to the Example-05
;;

#?(:cljs (defn create-root
           "See https://reactjs.org/docs/react-dom-client.html#createroot"
           ([node] (create-root node (str (gensym))))
           ([node id-prefix]
            (ReactDom/createRoot node #js {:identifierPrefix id-prefix}))))

#?(:cljs (defn render [root & args]
           (.render root (r/as-element (into [:f>] args)))))

(defmacro with-reagent [& args]
  `(dom/div
     (let [root# (create-root dom/node)]
       (render root# ~@args)
       (e/on-unmount #(.unmount root#)))))

(e/defn navbar []
        (e/client
          (dom/link (dom/props {:rel :stylesheet :href "/assets/css/banner.css"}))
          (dom/nav (dom/props {:class "navbar navbar-expand-lg navbar-dark bg-dark"})
                   (dom/div (dom/props {:class "container-fluid"})
                            (router/link ['.. [::home]] (dom/a (dom/props {:class "navbar-brand"})
                                                               (dom/text "Home")))
                            (dom/div (dom/props {:class "collapse navbar-collapse ", :id "navbarSupportedContent"})
                                     (dom/ul (dom/props {:class "navbar-nav me-auto mb-2 mb-lg-0 "})
                                             (dom/li (dom/props {:class "nav-item"})
                                                     (dom/div (dom/props {:class "nav-link active"})
                                                              (router/link ['.. [::home]] (dom/a  (dom/text "Home")))))
                                             (dom/li (dom/props {:class "nav-item"})
                                                     (dom/div (dom/props {:class "nav-link"})
                                                              (router/link ['.. [::about]] (dom/a  (dom/text "About")))))
                                             (dom/li (dom/props {:class "nav-item"})
                                               (dom/div (dom/props {:class "nav-link"})
                                                 (router/link ['.. [::systemproperties]] (dom/a  (dom/text "System Properties")))))
                                             (dom/li (dom/props {:class "nav-item"})
                                               (dom/div (dom/props {:class "nav-link"})
                                                 (router/link ['.. [::chat]] (dom/a  (dom/text "Chat")))))))))))





(e/defn HomePage []
  (e/client
    (dom/h1 (dom/text "This is HomePage"))))

(e/defn About []
  (e/client
    (dom/h1 (dom/text "This is About"))))

(e/defn Main [ring-request]
  (e/client
    (binding [dom/node js/document.body]
      (dom/h1 (dom/text "electric-09"))
      (navbar.)
      (router/router (router/HTML5-History.)
                     (let [[page x :as route] (ffirst router/route)]
                       (if-not page
                         (router/Navigate!. [[::home]])
                         (router/focus [route]
                                     (case page
                                       ::home (e/server (HomePage.))
                                       ::about (e/server (About.))
                                       ::systemproperties (e/server (SystemProperties.))
                                       ::chat (e/server (Chat.))

                                       (e/client (dom/text "no matching route: " (pr-str page)))))))))))
