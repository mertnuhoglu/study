(ns fn.json.read-str)

;clojure.data.json/read-str [string & {:as options}]
;Reads one JSON value from input String. Options are the same as for read.

(require '[clojure.data.json :as json])

(json/read-str "{\"a\":1,\"b\":2}")
;=> {"a" 1, "b" 2}

(json/read-str "{\"a\":1,\"b\":2}" :key-fn keyword)
;;=> {:a 1, :b 2}
