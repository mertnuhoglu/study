(ns figwheel.nrepl
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [figwheel.main.api :as fig]
            [nrepl.server :as server]))


(def port-file ".nrepl-port")
(def nrepl-port 7888)


(defonce nrepl-server (atom nil))


(defn nrepl-handler []
  (require 'cider.nrepl)
  (ns-resolve 'cider.nrepl 'cider-nrepl-handler))


(defn start-nrepl-server! []
  (reset! nrepl-server
          (server/start-server :port nrepl-port
                               :handler (nrepl-handler)))
  (println "CIDER nREPL server started on port" nrepl-port)
  (spit port-file nrepl-port))


(defn stop-nrepl-server! []
  (when (not (nil? @nrepl-server))
    (server/stop-server @nrepl-server)
    (println "CIDER nREPL server on port" nrepl-port "stopped")
    (reset! nrepl-server nil)
    (io/delete-file port-file true)))


(defn parse-command-args [args]
  (if (nil? args)
    {}
    (->> args
         (reduce (fn [as a]
                   (if (string/starts-with? a "-")
                     (conj as a true)
                     (conj (pop as) a)))
                 [])
         (apply hash-map))))


(defn -main [& args]
  (let [switches (parse-command-args args)
        build (switches "-b")]
    (start-nrepl-server!)
    (-> (new java.io.File port-file) (.deleteOnExit))
    (fig/start {:rebel-readline false :mode :serve} build)
    (fig/cljs-repl build)))
