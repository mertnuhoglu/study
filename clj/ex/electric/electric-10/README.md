# electric-09

Source: https://github.com/hyperfiddle/electric-starter-app

## Usage

1. step: `npm install`

Dev build:

Shell: clj -A:dev -X dev/-main, or repl: (dev/-main)
http://localhost:8080

# Changelog

Chat-Extended

It doesn't work fully.

```
ERROR hyperfiddle.electric: #error {
 :cause nth not supported on this type cljs.core/Symbol
 :via
 [{:type hyperfiddle.electric.FailureInfo
   :message nth not supported on this type cljs.core/Symbol
 :trace
 []}
in remote ( clojure.core/nth auth 0 nil )
in remote reactive (fn [] ...)
in remote case default branch
in remote (case "2" ...)
in remote reactive (defn Focus [] ...) in hyperfiddle/router.cljc line 316
client logged an exception, too
```

## License

Copyright © 2024 Mertnuhoglu

_EPLv1.0 is just the default for projects generated by `clj-new`: you are not_
_required to open source this project, nor are you required to use EPLv1.0!_
_Feel free to remove or change the `LICENSE` file and remove or update this_
_section of the `README.md` file!_

Distributed under the Eclipse Public License version 1.0.
