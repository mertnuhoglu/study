(ns mertnuhoglu.meander-specter-comparison01)

; [(4) specter v.s. meander v.s hand-crafted code when restructuring data : Clojure](https://www.reddit.com/r/Clojure/comments/izh9o4/specter_vs_meander_vs_handcrafted_code_when/)

(def data [{:id "ID 1" :a 1 :pick-me/b nil :pick-me/c 3}
           {:id "ID 2" :a 5 :pick-me/b 6 :pick-me/c nil}])

; expected output

{"ID 1" {:c 3}, "ID 2" {:b 6}}

; a01: plain clojure

(defn manual-code [data]
  (->> data
    (map (fn [{:keys [id] :as m}]
           (let [v (some (fn [[k v]]
                              (when (and (= (namespace k) "pick-me")
                                         (some? v))
                                {(-> k name keyword) v}))
                          m)]
             [id v])))
    (into {})))

(manual-code data)

; a02: specter

(require '[com.rpl.specter :refer :all])

(into {}
  (traverse
    [ALL
     (collect-one :id)
     (transformed
       [ALL
        (multi-path
          [LAST nil? (terminal-val NONE)]
          [FIRST
           (if-path [NAMESPACE (pred= "pick-me")]
             [NAMESPACE (terminal-val nil)]
             (terminal-val NONE))]
          )]
       identity)]
    data))

; a03: meander

(require '[meander.epsilon :as me])

(me/rewrite data
  [{:id !id (me/keyword "pick-me" !k) (me/some !v)} ...]
  {& ([!id {(me/keyword !k) !v}] ...)})


; a04: plain clojure 2

(reduce-kv
  (fn [m k [{:pick-me/keys [b c]}]]
    (assoc m k (if b
                 {:b b}
                 {:c c})))
  {}
  (group-by :id data))
