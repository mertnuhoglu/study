(ns logic-tutorials.episode1
  (:refer-clojure :exclude [==]))

(defn lvar
  ([] (lvar ""))
  ([nm] (gensym (str nm "_"))))

(defn lvar? [v]
  (symbol? v))

(defn walk [s u]
  (if-let [pr (get s u)]
    (if (lvar? pr)
      (recur s pr)
      pr)
    u))

(defn unify [s u v]
  (let [u (walk s u)
        v (walk s v)]
    (cond
      (and (lvar? u)
           (lvar? v)
           (= u v)) s
      (lvar? u) (assoc s u v)
      (lvar? u) (assoc s v u)
      :else (and (= u v) s))))

(defn == [a b]
  (fn [s]
    (if-let [v (unify s a b)]
      [v]
      [])))

(comment
 (lvar "foo")
;; => foo_14417

 (walk {} (lvar "s"))
;; => s_14420

 (unify {} (lvar "s") 42)
;; => {s_5751 42}

 (let [state (unify {} (lvar "s") 42)
       v (lvar "s")]
  (walk state v))
;; => s_12714

 (let [s (lvar "s")]
   (walk s {s 42}))
;; => {s_5757 42}

 (let [s (lvar "s")
       v (lvar "v")]
   (walk {s v v 42} s));; => 42

 (unify {} (lvar "s") (lvar "v"));; => {s_14448 v_14449}
 (unify {} 1 2);; => false
 (unify {} 1 1);; => {}
 (== 1 2)
;; => #function[logic-tutorials.episode1/==/fn--14468]
 ((== 1 2) {});; => []
 ((== (lvar "s") (lvar "v") ) {}));; => [{s_14482 v_14483}]

