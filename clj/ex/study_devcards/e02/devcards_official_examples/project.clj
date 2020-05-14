(defproject devcards_official_examples "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"
  
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [devcards "0.2.3"]
                 [sablono "0.7.4"]
                 [figwheel-sidecar "0.5.18"] ; cursive

                 ;; need to specify this for sablono
                 ;; when not using devcards
                 [cljsjs/react "16.4.1-0"]
                 [cljsjs/react-dom "16.4.1-0"]
                 [reagent "0.8.1"]]
                 

  :plugins [
            ;[lein-figwheel "0.5.9"] ; cursive
            [lein-cljsbuild "1.1.5" :exclusions [org.clojure/clojure]]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "target"]
  
  :source-paths ["src" "script"]

  :cljsbuild {
              :builds [{:id "devcards"
                        :source-paths ["src"]
                        :figwheel { :devcards true  ;; <- note this
                                   ;; :open-urls will pop open your application
                                   ;; in the default browser once Figwheel has
                                   ;; started and complied your application.
                                   ;; Comment this out once it no longer serves you.
                                   :open-urls ["http://localhost:3449/cards.html"]}
                        :compiler { :main       "devcards_official_examples.core"
                                    :asset-path "js/compiled/devcards_out"
                                    :output-to  "resources/public/js/compiled/devcards_official_examples_devcards.js"
                                    :output-dir "resources/public/js/compiled/devcards_out"
                                    :source-map-timestamp true}}
                       {:id "dev"
                        :source-paths ["src"]
                        :figwheel true
                        :compiler {:main       "devcards_official_examples.core"
                                   :asset-path "js/compiled/out"
                                   :output-to  "resources/public/js/compiled/devcards_official_examples.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :source-map-timestamp true}}
                       {:id "prod"
                        :source-paths ["src"]
                        :compiler {:main       "devcards_official_examples.core"
                                   :asset-path "js/compiled/out"
                                   :output-to  "resources/public/js/compiled/devcards_official_examples.js"
                                   :optimizations :advanced}}]}

  :figwheel { :css-dirs ["resources/public/css"]}

  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.2"]
                                  [figwheel-sidecar "0.5.9"]
                                  [com.cemerick/piggieback "0.2.1"]]
                   ;; need to add dev source path here to get user.clj loaded
                   :source-paths ["src" "dev"]
                   ;; for CIDER
                   ;; :plugins [[cider/cider-nrepl "0.12.0"]]
                   :repl-options {; for nREPL dev you really need to limit output
                                  :init (set! *print-length* 50)
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
