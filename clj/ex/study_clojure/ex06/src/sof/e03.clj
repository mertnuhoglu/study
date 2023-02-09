(ns sof.e03)

; [In Clojure is there an easy way to convert between list types? - Stack Overflow](https://stackoverflow.com/questions/5088803/in-clojure-is-there-an-easy-way-to-convert-between-list-types)

(into [] '(1 2 3 4)) ; ==> [1 2 3 4]         "have a lazy list and want a vector"
(into #{} [1 2 3 4]) ; ==> #{1 2 3 4}        "have a vector and want a set"
(into {} #{[1 2] [3 4]}) ; ==> {3 4, 1 2}    "have a set of vectors want a map"
(into #{} [{1 2} {3 4}]) ; ==> #{{1 2} {3 4}} "have a vector of maps want a set of maps"

