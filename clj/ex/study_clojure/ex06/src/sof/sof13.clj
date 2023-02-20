(ns sof.sof13)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; [Clojure: Convert hash-maps key strings to keywords? - Stack Overflow](https://stackoverflow.com/questions/9406156/clojure-convert-hash-maps-key-strings-to-keywords)

;The problem is this data comes back with strings for keys, for ex:

;({"name" "Tylenol", "how" "instructions"})

;When I need it to be:

;({:name "Tylenol", :how "instructions"})

; a01: clojure.walk

(require 'clojure.walk)
(clojure.walk/keywordize-keys {"name" "Tylenol", "how" "instructions"})
;=> {:name "Tylenol", :how "instructions"}

; This will walk the map recursively as well so it will "keywordize" keys in nested map too

; a02: keyword

(def m {"name" "Tylenol", "how" "instructions"})
(into {}
  (for [[k v] m]
    [(keyword k) v]))
;=> {:name "Tylenol", :how "instructions"}

(keyword "foo")
;=> :foo

; a03: clojure kaynak kodunu okuyup ilgili fonksiyonu kendi amacına göre yeniden yazmak:
; yukarıdaki işlemin tam tersini yapalım
; yani keyword olan keyleri stringe çevirelim
; bunun için de clojure'un mevcut kodunu temel alabiliriz

;It's worth peeking at the source code of clojure.walk/keywordize-keys:

(defn keywordize-keys
  "Recursively transforms all map keys from strings to keywords."
  [m]
  (let [f (fn [[k v]] (if (string? k) [(keyword k) v] [k v]))]
    (clojure.walk/postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))

;The inverse transform is sometimes handy for java interop:

(defn stringify-keys-2
  [m]
  (let [f (fn [[k v]] (if (keyword? k) [(name k) v] [k v]))]
    (clojure.walk/postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))

; a04: json/read-str

;Perhaps it is worth noting that, if the incoming data is json and you are using clojure.data.json,
; you can specify both a key-fn and a value-fn for manipulating results on parsing the string (docs) -

(require '[clojure.data.json :as json])

(json/read-str "{\"a\": 1,\"b\": 2}" :key-fn keyword)
;;=> {:a 1, :b 2}

(json/read-str "{\"a\": 1,\"b\": 2}" :key-fn #(keyword "com.example" %))
;;=> {:com.example/a 1, :com.example/b 2}

; a04: zipmap

(defn modify-keys [f m] (zipmap (map f (keys m)) (vals m)))
(modify-keys keyword {"name" "Tylenol", "how" "instructions"})
; {:how "instructions", :name "Tylenol"}

(comment
  (def f keyword)
  (map f (keys m))
  ;=> (:name :how)
  (vals m)
  ;=> ("Tylenol" "instructions")
  ,)