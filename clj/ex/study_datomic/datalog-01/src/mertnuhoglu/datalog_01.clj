(ns mertnuhoglu.datalog-01
  (:require [datascript.core :as d]
            [pl.danieljanus.tagsoup :as html]
            [clojure.java.io :as io]
            [clojure.zip :as zip]
            [clojure.string :as string]))

; Copied from: [JUXT Blog - Datalog for trees in Clojure](https://www.juxt.pro/blog/datascript-dom)

(html/parse-string "<html><body class=\"test\"><p>A<b>B</b>C<i>D</i>E</p><p>hohoho</p></body></html>")
;=>
[:html {} [:body {:class "test"} [:p {} "A" [:b {} "B"] "C" [:i {} "D"] "E"] [:p {} "hohoho"]]]

; target data:

[{:dom/tag :html
  :child
  [{:dom/tag :body
    :class "test"
    :child
    [{:dom/tag :p
      :child
      [{:dom/tag :text-node :text "A" :dom/index 0}
       {:dom/tag :b
        :child [{:dom/tag :text-node :text "B" :dom/index 0}]
        :dom/index 1}
       {:dom/tag :text-node :text "C" :dom/index 2}
       {:dom/tag :i
        :child [{:dom/tag :text-node :text "D" :dom/index 0}]
        :dom/index 3}
       {:dom/tag :text-node :text "E" :dom/index 4}]
      :dom/index 0}
     {:dom/tag :p
      :child [{:dom/tag :text-node :text "hohoho" :dom/index 0}]
      :dom/index 1}]
    :dom/index 0}]
  :dom/index 0}]

; datascript schema
(def schema
  {:child {:db/valueType   :db.type/ref
           :db/cardinality :db.cardinality/many}})

(defn- to-entity [node]
  (if-not (string? node)
    (merge
      {:dom/tag (html/tag node)}

      ;;include attributes, but not :id
      (dissoc (html/attributes node) :id)

      ;;id will ne given a special name
      (when-let [id (:id node)] {:dom/id id})

      ;;assoc children -- if present
      (when-let [children (html/children node)]
        {:child children}))

    ;;if it's a string, encode as text-node
    {:dom/tag :text-node
     :text    node}))

(defn dom-zipper [dom]
  (zip/zipper
    (fn [node] (not-empty (:child node))) ;;branch?
    :child ;;children
    (fn [node children] (assoc node :child children)) ;;make-node
    dom))

(defn- replace-node [zipper]
  (let [dom-index (or (some-> zipper zip/left zip/node :dom/index inc) 0)]
    (zip/replace
      zipper
      (-> zipper
        zip/node
        to-entity
        (assoc :dom/index dom-index)))))

(defn dom->transaction [dom]
  (loop [zipper (dom-zipper dom)]
    (if (zip/end? zipper)
      [(zip/root zipper)]
      (recur (zip/next (replace-node zipper))))))

(def small-dom
  (html/parse-string
    "<html><body class=\"test\"><p>A<b>B</b>C<i>D</i>E</p><p>hohoho</p></body></html>"))

small-dom ;=>
[:html {} [:body {:class "test"} [:p {} "A" [:b {} "B"] "C" [:i {} "D"] "E"] [:p {} "hohoho"]]]

(dom->transaction small-dom)
; =>
[{:dom/tag :html,
  :child ({:dom/tag :body,
           :class "test",
           :child ({:dom/tag :p,
                    :child ({:dom/tag :text-node, :text "A", :dom/index 0}
                            {:dom/tag :b, :child ({:dom/tag :text-node, :text "B", :dom/index 0}), :dom/index 1}
                            {:dom/tag :text-node, :text "C", :dom/index 2}
                            {:dom/tag :i, :child ({:dom/tag :text-node, :text "D", :dom/index 0}), :dom/index 3}
                            {:dom/tag :text-node, :text "E", :dom/index 4}),
                    :dom/index 0}
                   {:dom/tag :p, :child ({:dom/tag :text-node, :text "hohoho", :dom/index 0}), :dom/index 1}),
           :dom/index 0}),
  :dom/index 0}]

(def small-conn (d/create-conn schema))

(d/transact small-conn (dom->transaction small-dom))
;=>
;[[1 :child 2 536870913]
; [1 :dom/index 0 536870913]
; [1 :dom/tag :html 536870913]
; [2 :child 3 536870913]
; [2 :child 11 536870913]
; [2 :class "test" 536870913]
; [2 :dom/index 0 536870913]
; [2 :dom/tag :body 536870913]
; ...

; query on the class attribute to find the <body> tag
(d/q '[:find [(pull ?node [*]) ...]
       :where
       [?node :class "test"]]
  @small-conn)
; =>
[{:db/id 2, :child [#:db{:id 3} #:db{:id 11}], :class "test", :dom/index 0, :dom/tag :body}]

; query: find the root
; a01:
(d/q '[:find (pull ?node [:dom/tag]) .
       :where
       [?node _]
       [(missing? $ ?node :_child)]]
  @small-conn)
;=>
#:dom{:tag :html}

; a02: find the root
(d/q '[:find (pull ?node [:dom/tag]) .
       :in $ %
       :where (root ?node)]
  @small-conn
  '[[(root ?node)
     [?node _]
     [(missing? $ ?node :_child)]]])
;=>
#:dom{:tag :html}

(comment

  (def dom small-dom))
  dom
  ;=>
  [:html
  {}
  [:body
    {:class "test"}
    [:p {} "A" [:b {} "B"] "C" [:i {} "D"] "E"]
    [:p {} "hohoho"]]]

  (dom-zipper dom)
  ;=>
  [[:html
    {}
    [:body
     {:class "test"}
     [:p {} "A" [:b {} "B"] "C" [:i {} "D"] "E"]
     [:p {} "hohoho"]]]
   nil]

  (def zipper (dom-zipper dom)

  ; rich comment
  ,)
  
(comment
  ; how does zipper work?

  ; rich comment
  ,)
