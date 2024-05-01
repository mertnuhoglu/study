(ns dev3
  (:require
    app.main
    #?(:clj [clojure.tools.logging :as log])
    [hyperfiddle.electric :as e]
    [hyperfiddle.rcf :as rcf]))


(comment (-main))                                           ; repl entrypoint

#?(:clj
   (do
     ; lazy load heavy dependencies for faster REPL startup
     (def shadow-start! (delay @(requiring-resolve 'shadow.cljs.devtools.server/start!)))
     (def shadow-stop! (delay @(requiring-resolve 'shadow.cljs.devtools.server/stop!)))
     (def shadow-watch (delay @(requiring-resolve 'shadow.cljs.devtools.api/watch)))
     (def start-server! (delay @(requiring-resolve 'electric-fiddle.server-jetty/start-server!))) ; jetty
     #_(def start-server! (delay @(requiring-resolve 'electric-fiddle.server-httpkit/start-server!))) ; require `:httpkit` deps alias

     (def config
       {:host           "0.0.0.0", :port 8080,
        :resources-path "public"
        :manifest-path  "public/js/manifest.edn"})          ; shadow build manifest

     (declare server)

     (defn -main [& args]
       (alter-var-root #'config #(merge % (first args)))
       (log/info (pr-str config))
       (log/info "Starting Electric compiler and server...") ; run after REPL redirects stdout

       (@shadow-start!)
       (@shadow-watch :dev)
       ; todo block until finished?
       (comment (@shadow-stop!))
       (def server
         (@start-server!
           (fn [ring-req] (e/boot-server {} app.main/Main ring-req))
           config))

       (rcf/enable!))))

#?(:cljs
   (do
     (def electric-entrypoint)
     ; in dev, we setup a merged fiddle config,
     ; fiddles must all opt in to the shared routing strategy

     (defonce reactor nil)

     (defn ^:dev/after-load ^:export start! []
       (set! reactor (electric-entrypoint
                       #(js/console.log "Reactor success:" %)
                       #(js/console.error "Reactor failure:" %)))
       (hyperfiddle.rcf/enable!))

     (defn ^:dev/before-load stop! []
       (when reactor (reactor))                             ; teardown
       (set! reactor nil))))

(comment
  "CI tests"
  (alter-var-root #'hyperfiddle.rcf/*generate-tests* (constantly false))
  (hyperfiddle.rcf/enable!)
  (require 'clojure.test)
  (clojure.test/run-all-tests #"(hyperfiddle.api|user.orders)"))

(comment
  "Performance profiling, use :profile deps alias"
  (require '[clj-async-profiler.core :as prof])
  (prof/serve-files 8082)
  ;; Navigate to http://localhost:8082
  (prof/start {:framebuf 10000000})
  (prof/stop))
