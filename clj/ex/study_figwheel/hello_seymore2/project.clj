(defproject hello-seymore "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [javax.xml.bind/jaxb-api "2.3.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [cljsjs/react "15.2.1-1"]
                 [cljsjs/react-dom "15.2.1-1"]
                 [sablono "0.7.4"]]
  :plugins [[lein-figwheel "0.5.7"]]
  :clean-targets [:target-path "out"]
  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src"]
              :figwheel true
              :compiler {:main hello-seymore.core } 
             }]
   }
   :figwheel { ;; <-- add server level config here
     :css-dirs ["css"]
   }
)
