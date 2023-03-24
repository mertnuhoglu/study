(ns e16
  (:require [clojure.string :as str]))


; Tarih: 20230324
; Konu: Barış'ın Search Map Uygulamasını Debug etmek
; rfr: log_daily_baris > 20230324-SearchMap-Debug-Problemi

(comment
  (def search "")
  (def !db-vector [{:ali "veli"}])
  (defn filter-db [?s db]
    ;Burada filter functionunda bulunan k v sembollerinden k ile ararsak key v ile ararsak
    ;value den arayabiliriz.
    (->> db
      (filter (fn [[k v]] (str/includes? (str/lower-case (str k)) (str/lower-case (str ?s)))))
      (into {})))

  (def filteredMap (filter-db search !db-vector))
  ;nth not supported on this type: PersistentArrayMap

  (->> !db-vector
    (filter (fn [[k v]] (str/includes? (str/lower-case (str k)) (str/lower-case (str search)))))
    (into {}))

  ((fn [[k v]] (str/includes? (str/lower-case (str k)) (str/lower-case (str search)))) {:ali "veli"})
  ;nth not supported on this type: PersistentArrayMap

  ((fn [[k v]]
     (str k ": " v))
   {:ali "veli"})
  ;nth not supported on this type: PersistentArrayMap

  (into [] {:ali "veli"})
  ((fn [[k v]]
     (str k ": " v))
   [[:ali "veli"]])
  ;=> "[:ali \"veli\"]: "

  ((fn [[k v]]
     (str k ": " v))
   [:ali "veli"])
  ;=> ":ali: veli"

  ; end
  ,)

(comment

  (def db
    {"a1" "v1"
     "a2" "v2"
     "b1" "v3"})

  (defn filter-db [?s]
    (->> db
      (filter (fn [[k v]] (str/includes? (str/lower-case (str k)) (str/lower-case (str ?s)))))
      (into {})))

  (identity db)
  (filter-db "a")
  ;=> {"a1" "v1", "a2" "v2"}

  (def ?s "a")
  (->> db
    (filter (fn [[k v]] (str/includes? (str/lower-case (str k)) (str/lower-case (str ?s)))))
    (into {}))
  ;=> {"a1" "v1", "a2" "v2"}

  (filter
    (fn [[k v]] (str/includes? (str/lower-case (str k)) (str/lower-case (str ?s))))
    db)
  ;=> (["a1" "v1"] ["a2" "v2"])

  ((fn
     [[k v]]
     (str/includes? (str/lower-case (str k)) (str/lower-case (str ?s))))
   {"a1" "v1"})
  ;nth not supported on this type: PersistentArrayMap

  ; end
  ,)

(comment
  (def db
    [{:ali "veli"}
     {:bekir "nazmi"}])

  ((fn [m]
     (let [k (first m)
           v (second m)]
       (str k ": " v)))
   {:ali "veli"})
  (def m {:ali "veli"})
  (first m)

  ; end
  ,)

(comment
  (def db
    [{:name "ali" :surname "veli"}
     {:name "bekir" :surname "nazmi"}])

  ((fn [{:keys [name surname]}]
     (str name ": " surname))
   {:name "ali" :surname "veli"})

  (def ?s "a")
  ((fn
     [{:keys [name surname]}]
     (let [k name
           v surname]
       (str/includes? (str/lower-case (str k)) (str/lower-case (str ?s)))))
   {:name "ali" :surname "veli"})

  (defn filter-db [?s]
    (->> db
      (filter
        (fn
          [{:keys [name surname]}]
          (let [k name
                v surname]
            (str/includes? (str/lower-case (str k)) (str/lower-case (str ?s))))))))

  (filter-db "a")
  ;=> ({:name "ali", :surname "veli"})


  ; end
  ,)
