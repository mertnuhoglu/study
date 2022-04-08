#!/usr/bin/env bb

; [(λ. borkdude) Twitter'da: "Specter 1.1.4 with #babaskha compatibility: (babashka.deps/add-deps '{:deps {com.rpl/specter {:mvn/version "1.1.4"}}}) (require '[com.rpl.specter :as s]) (s/transform [(s/walker number?) odd?] inc {:a 1 :b [1 2 3]}) ;;=> {:a 2, :b [2 2 4]} #clojure Thanks @nathanmarz" / Twitter](https://twitter.com/borkdude/status/1504884873684787209)

(babashka.deps/add-deps '{:deps {com.rpl/specter {:mvn/version "1.1.4"}}})

(require '[com.rpl.specter :as s])

(s/transform [(s/walker number?) odd?] inc {:a 1 :b [1 2 3]}) ;;=> {:a 2, :b [2 2 4]}
