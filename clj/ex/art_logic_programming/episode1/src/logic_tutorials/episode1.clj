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

 (walk {} 42)
;; => 42

 (let [s {}
       u (lvar "s")
       v 42]
   (cond
     (and (lvar? u)
          (lvar? v)
          (= u v)) s
     (lvar? u) (assoc s u v)
     (lvar? u) (assoc s v u)
     :else (and (= u v) s)))
;; => {s_14168 42}
;; ![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20210102_211802.jpg)

 (let [s {}
       u (lvar "s")
       v 42]
   (assoc s u v))
;; => {s_14659 42}

 (unify {} (lvar "s") 42)
;; => {s_5751 42}

 (let [state (unify {} (lvar "s") 42)
       v (lvar "s")]
  (walk state v))
;; => s_12714

 (let [s (lvar "s")]
   (walk s {s 42}))
;; => {s_5757 42}

 (let [s (lvar "s")]
   (walk {s 42} {s 42}))
;; => {s_14677 42}

 (let [s (lvar "s")]
   (walk {s 42} s))
;; => 42

 (let [s (lvar "s")]
   (walk {s 42} {s 1}))
;; => {s_14689 1}

 (let [s (lvar "s")
       v (lvar "v")]
   (walk {s v v 42} s));; => 42

 (let [v (lvar "v")
       t {v 42}] 
   (if-let [pr (get t v)]
     (when-not (lvar? pr)
       pr)
     v))
;; => 42

 (unify {} (lvar "s") (lvar "v"));; => {s_14448 v_14449}
 (unify {} 1 2);; => false
 (unify {} 1 1);; => {}
 (== 1 2)
;; => #function[logic-tutorials.episode1/==/fn--14468]
 ((== 1 2) {});; => []
 ((== (lvar "s") (lvar "v") ) {}));; => [{s_14482 v_14483}]

