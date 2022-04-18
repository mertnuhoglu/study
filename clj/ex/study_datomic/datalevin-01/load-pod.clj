#!/usr/bin/env bb

; [juji-io/datalevin: A simple, fast and versatile Datalog database](https://github.com/juji-io/datalevin)

(require '[babashka.pods :as pods])
(pods/load-pod 'huahaiy/datalevin "0.6.6")
