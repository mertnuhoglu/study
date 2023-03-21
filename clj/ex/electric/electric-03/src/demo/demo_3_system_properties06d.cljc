(ns demo.demo-3-system-properties06d
  (:require
    [clojure.string :as str]
    [hyperfiddle.electric :as e]
    [hyperfiddle.electric-dom2 :as dom]
    [hyperfiddle.electric-ui4 :as ui]))

(def db
 {"a1" "v1"
  "a2" "v2"
  "b1" "v3"})

(defn filter-db [?s]
 (->> db
   (filter (fn [[k v]] (str/includes? (str/lower-case (str k)) (str/lower-case (str ?s)))))
   (into {})))

; @CHANGE: def formlarını reader conditional ile cljs tarafına sınırlandıralım
#?(:cljs (def !search (atom "")))
#?(:cljs (def search (e/watch !search)))

(e/defn App []
  (e/client
    (dom/h1 (dom/text "Search"))
    (let [dt (filter-db search)]
      (ui/input search (e/fn [v] (reset! !search v))
        (dom/props {:type "search"}))
      (dom/table
        (e/for-by first [[k v] dt]
          (dom/tr
            (dom/td (dom/text k))
            (dom/td (dom/props {:style {:white-space :nowrap}}) (dom/text v))))))))

(comment
  (identity db)
  (filter-db "a")

  ; end
  ,)
