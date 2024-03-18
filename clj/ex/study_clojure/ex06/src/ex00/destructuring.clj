(ns ex00.destructuring)

; destructuring id=g11408
;   id:: 2c91e0d4-4735-4bfb-8247-edf684efcdff
; ref: Destructuring <url:file:///~/projects/study/clj/book_clojure_practicalli.md#r=g11407>

(let [[a b & c :as d] [1 2 3 4]] [a b])
;; => [1 2]

(let [[a b & c :as d] [1 2 3 4]] [c])
;; => [(3 4)]

(let [[a b & c :as d] [1 2 3 4]] [d])
;; => [[1 2 3 4]]

(let [{a :a, c :c}  {:a 5 :c 6}]
  [a c])
;; [5 6]
(let [{a :a, :as m} {:a 2 :b 3}]
  [a m])
;; => [2 {:a 2, :b 3}]

; variadic function (multi-arity + optional args) id=g_11358

(defn f [x & xs]
  (str x xs))
;; => #'user/f
(f 1 2 3)
;; => "1(2 3)"

; keyword arguments (optional args) id=g13267
;   id:: 794d9112-3554-4f60-99f9-6581f303076a

(defn manage-pc
  [{os :os} & {:keys [scan upgrade]
               :or {scan true upgrade false}}]
  (printf "os: %s\nscan: %s\nupgrade: %s\n"
    os scan upgrade))

(manage-pc {:os "windows"} :scan true :upgrade "maybe")
;; os: windows
;; scan: true
;; upgrade: maybe

(defn foo [a b & {:keys [x y]}]
  (println a b x y))
(foo "A" "B")  ;; => A B nil nil
(foo "A" "B" :x "X")  ;; => A B X nil
(foo "A" "B" :x "X" :y "Y")  ;; => A B X Y

(defn foo [& {:as m}]
  (println m))
(foo :x "X" :y "Y") ;; => {:y Y, :x X}

;; keeping original value with :as id=g13268
;;   id:: bb981b9a-e80d-4744-9cb5-ce6a443d8d6f

; with vector
(defn test [[x y :as v]]
  {:x x :y y :v v})

(test [1 2 3 4])
; =>  {:x 1 :y 2 :v [1 2 3 4]}

; with hash-map
(defn test2 [{x :x y :y :as m}]
    {:x x :y y :m m})

(test2 {:x 1 :y 2 :z 3})
; => {:x 1 :y 2 :m {:x 1 :y 2 :z 3}}

; destructuring id=g_11408
; ref: Destructuring <url:file:///~/projects/study/clj/book_clojure_practicalli.md#r=g_11407>

(let [[a b & c :as d] [1 2 3 4]] [a b])
;; => [1 2]

(let [[a b & c :as d] [1 2 3 4]] [c])
;; => [(3 4)]

(let [[a b & c :as d] [1 2 3 4]] [d])
;; => [[1 2 3 4]]

(let [{a :a, c :c}  {:a 5 :c 6}]
  [a c])
;; [5 6]
(let [{a :a, :as m} {:a 2 :b 3}]
  [a m])
;; => [2 {:a 2, :b 3}]

;; namespaced keywords id=g13269
;;   id:: a92833f2-e9db-40e2-8521-431a0959ea6c

; ref: [Clojure - Destructuring in Clojure](https://clojure.org/guides/destructuring)

(def human {:person/name "Franklin"
            :person/age 25
            :hobby/hobbies "running"})
(let [{:keys [hobby/hobbies]
       :person/keys [name age]
       :or {age 0}} human]
  (println name "is" age "and likes" hobbies))
;= Franklin is 25 and likes running
