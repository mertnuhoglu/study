(ns zipper_ex02)

; Source: [The Art of Tree Shaping with Zippers](http://arnebrasseur.net/talks/2018-clojure-zip-denver/#25)

; Note: Web sitesinde çok güzel tree şemaları var, mantığını anlatan

(require '[clojure.zip :as z])

(def loc (z/vector-zip [[1 2] [3 4]]))

(-> [[1 2] [3 [4 5] 6]]
  z/vector-zip)
;=> [[[1 2] [3 [4 5] 6]] nil]

(-> [[1 2] [3 [4 5] 6]]
  z/vector-zip
  z/down)
;=> [[1 2] {:l [], :pnodes [[[1 2] [3 [4 5] 6]]], :ppath nil, :r ([3 [4 5] 6])}]

(-> [[1 2] [3 [4 5] 6]]
  z/vector-zip
  z/down
  z/right)
;[[3 [4 5] 6]
; {:l [[1 2]],
;  :pnodes [[[1 2] [3 [4 5] 6]]],
;  :ppath nil,
;  :r nil}]

(-> [[1 2] [3 [4 5] 6]]
  z/vector-zip
  z/down
  z/right
  z/down)
;=>
;[3
; {:l [],
;  :pnodes
;  [[[1 2] [3 [4 5] 6]]
;   [3 [4 5] 6]],
;  :ppath
;  {:l [[1 2]],
;   :pnodes [[[1 2] [3 [4 5] 6]]],
;   :ppath nil,
;   :r nil},
;  :r ([4 5] 6)}]

(-> [[1 2] [3 [4 5] 6]]
  z/vector-zip
  z/down
  z/right
  z/down
  z/rightmost)
;[6
; {:l [3 [4 5]],
;  :pnodes
;  [[[1 2] [3 [4 5] 6]]
;   [3 [4 5] 6]],
;  :ppath
;  {:l [[1 2]],
;   :pnodes [[[1 2] [3 [4 5] 6]]],
;   :ppath nil,
;   :r nil},
;  :r nil}]

(-> [[1 2] [3 [4 5] 6]]
  z/vector-zip
  z/down
  z/right
  z/down
  z/rightmost
  z/left)
;[[4 5]
; {:l [3],
;  :pnodes
;  [[[1 2] [3 [4 5] 6]]
;   [3 [4 5] 6]],
;  :ppath
;  {:l [[1 2]],
;   :pnodes [[[1 2] [3 [4 5] 6]]],
;   :ppath nil,
;   :r nil},
;  :r (6)}]

(-> [[1 2] [3 [4 5] 6]]
  z/vector-zip
  z/down
  z/right
  z/down
  z/rightmost
  z/left
  (z/append-child :x))
;[[4 5 :x]
; {:l [3],
;  :pnodes
;  [[[1 2] [3 [4 5] 6]]
;   [3 [4 5] 6]],
;  :ppath
;  {:l [[1 2]],
;   :pnodes [[[1 2] [3 [4 5] 6]]],
;   :ppath nil,
;   :r nil},
;  :r (6),
;  :changed? true}]

(-> [[1 2] [3 [4 5] 6]]
  z/vector-zip
  z/down
  z/right
  z/down
  z/rightmost
  z/left
  (z/append-child :x)
  z/up)
;[[3 [4 5 :x] 6]
; {:l [[1 2]],
;  :pnodes [[[1 2] [3 [4 5] 6]]],
;  :ppath nil,
;  :r nil,
;  :changed? true}]

(-> [[1 2] [3 [4 5] 6]]
  z/vector-zip
  z/down
  z/right
  z/down
  z/rightmost
  z/left
  (z/append-child :x)
  z/up
  z/node)
;=> [3 [4 5 :x] 6]

(-> [[1 2] [3 [4 5] 6]]
  z/vector-zip
  z/down
  z/right
  z/down
  z/rightmost
  z/left
  (z/append-child :x)
  z/root)
;=> [[1 2] [3 [4 5 :x] 6]]

(require '[clojure.java.io :as io]
  '[clojure.xml :as xml])

(def cart-xml
  (-> "cart.xml"
    io/resource
    io/file
    xml/parse))
;=>
;{:tag :cart,
; :attrs nil,
; :content [{:tag :line-items,
;            :attrs nil,
;            :content [{:tag :product, :attrs {:price "2.5", :qty "1", :sku "CH56"}, :content ["Chocolate"]}
;                      {:tag :product, :attrs {:price "1.7", :qty "1", :sku "CA94"}, :content ["Candy"]}
;                      {:tag :discount, :attrs {:amount "10%"}, :content nil}]}
;           {:tag :customer,
;            :attrs nil,
;            :content [{:tag :name, :attrs nil, :content ["Arne"]} {:tag :address, :attrs nil, :content ["Berlin"]}]}]}

(def cart-zipper
  (z/xml-zip cart-xml))
;=>
;[{:tag :cart,
;  :attrs nil,
;  :content
;  [{:tag :line-items,
;    :attrs nil,
;    :content
;    [{:tag :product,
;      :attrs
;      {:price "2.5",
;       :qty "1",
;       :sku "CH56"},
;      :content ["Chocolate"]}
;     {:tag :product,
;      :attrs
;      {:price "1.7",
;       :qty "1",
;       :sku "CA94"},
;      :content ["Candy"]}
;     {:tag :discount,
;      :attrs {:amount "10%"},
;      :content nil}]}
;   {:tag :customer,
;    :attrs nil,
;    :content
;    [{:tag :name,
;      :attrs nil,
;      :content ["Arne"]}
;     {:tag :address,
;      :attrs nil,
;      :content ["Berlin"]}]}]}
; nil]

(-> cart-zipper
  z/down)
;=> .. :tag :line-items ..

(-> cart-zipper
  z/down
  z/down)
;=> [{:tag :product, :attrs {:price "2.5", :qty "1", :sku "CH56"}, :content ["Chocolate"]}] ...

(-> cart-zipper
  z/down
  z/down
  z/down
  (z/replace
    "luxury chocolate"))

(-> cart-zipper
  z/down
  z/down
  z/down
  (z/replace
    "luxury chocolate")
  z/up)

(-> cart-zipper
  z/down
  z/down
  z/down
  (z/replace
    "luxury chocolate")
  z/up
  (z/edit
    assoc-in [:attrs :price] 2.9))

(-> cart-zipper
  z/down
  z/down
  z/down
  (z/replace
    "luxury chocolate")
  z/up
  (z/edit
    assoc-in [:attrs :price] 2.9)
  z/root)

; API Creating Zippers
z/vector-zip
z/xml-zip
z/seq-zip
z/zipper

; API Navigating
z/up
z/down
z/left
z/right
z/leftmost
z/rightmost
z/root

; API Updating
z/insert-left
z/insert-right
z/append-child ;; rightmost
z/insert-child ;; leftmost
z/edit
z/remove
z/replace
z/make-node

; API Inspecting
z/lefts
z/rights
z/children
z/node
z/path
z/branch?

; API Walking
z/next
z/prev
z/end?

; Walking
(nth
  (iterate z/next cart-zipper)
  0)
;=> root

(nth
  (iterate z/next cart-zipper)
  1)
;=> line-items

(nth
  (iterate z/next cart-zipper)
  1)
;=> product price=2.5
; depth-first walking

; Caveats: no boundary checks
(-> [[1 2]]
  z/vector-zip
  z/down ;; [1 2]
  z/down ;; 1
  z/down)
;;=> nil
;; ... oops ...

; Caveats: no boundary checks
#_(if-let [l (z/left loc)]
    (do-something-with l)
    (do-something-else loc))

;Caveats: no way back from :end
(-> [[1 2]]
  z/vector-zip
  z/next ;; [1 2]
  z/next ;; 1
  z/next ;; 2
  z/next ;; :end
  z/prev)
;; => nil
;; ... oops ...

(-> [[1 2]]
  z/vector-zip
  z/down
  z/down
  z/remove)
;=> [[2] {:l [], :pnodes [[[1 2]]], :ppath nil, :r nil, :changed? true}]

(-> [[1 2]]
  z/vector-zip
  z/down
  z/down
  z/remove
  z/remove)
;=> [[] nil]

;Caveats: not a cursor
;A zipper points at a node, not in between nodes.
;Corrolary: a zipper can not enter an empty container.
#_(-> [[1 2]]
    z/vector-zip
    z/down
    z/down
    z/remove
    z/remove
    (z/insert-left 3))
;;=> ???

;Gérard Huet
;A path is like a zipper, allowing one to rip the tree structure down to a
;certain location. It contains its list l of left siblings , its father path p,
;and its list r of right siblings.
;
;path = Top | (left siblings, parent path, right siblings)
;
;A location in the tree adresses a subtree, together with its path
;
;location = (current node, path)