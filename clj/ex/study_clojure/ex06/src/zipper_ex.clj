(ns zipper_ex
  (:require
    [clojure.java.io :as io]
    [clojure.zip :as zip]
    [clojure.string :as string]))

; Source: [zipper - clojure.zip | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.zip/zipper)

; (zipper branch? children make-node root)

; Creates a new zipper structure.
;  branch? is a fn that, given a node, returns true if can have
; children, even if it currently doesn't.
;  children is a fn that, given a branch node, returns a seq of its
; children.
;  make-node is a fn that, given an existing node and a seq of
; children, returns a new branch node with the supplied children.
; root is the root node.

(use 'clojure.pprint)
(def p pprint)

(def z [[1 2 3] [4 [5 6] 7] [8 9]])
;=> #'user/z

(def zp (zip/zipper vector? seq (fn [_ c] c) z))
;=> #'user/zp

zp
; [[[1 2 3] [4 [5 6] 7] [8 9]] nil]

(p (-> zp zip/down))
; [[1 2 3]
; {:l [],
;   :pnodes [[[1 2 3] [4 [5 6] 7] [8 9]]],
;   :ppath nil,
;   :r ([4 [5 6] 7] [8 9])}]

(first (-> zp zip/down))
; [1 2 3]

(p (-> zp zip/down zip/right))
; [[4 [5 6] 7]
; {:l [[1 2 3]],
;   :pnodes [[[1 2 3] [4 [5 6] 7] [8 9]]],
;   :ppath nil,
;   :r ([8 9])}]

(first (-> zp zip/down zip/right))
; [4 [5 6] 7]

(p (-> zp zip/down zip/right zip/down zip/right))
; [[5 6]
; {:l [4],
;   :pnodes [[[1 2 3] [4 [5 6] 7] [8 9]] [4 [5 6] 7]],
;   :ppath
;   {:l [[1 2 3]],
;   :pnodes [[[1 2 3] [4 [5 6] 7] [8 9]]],
;   :ppath nil,
;   :r ([8 9])},
;   :r (7)}]

(p (-> zp zip/down zip/right zip/down zip/right zip/down))
; [5
; {:l [],
;   :pnodes [[[1 2 3] [4 [5 6] 7] [8 9]] [4 [5 6] 7] [5 6]],
;   :ppath
;   {:l [4],
;   :pnodes [[[1 2 3] [4 [5 6] 7] [8 9]] [4 [5 6] 7]],
;   :ppath
;   {:l [[1 2 3]],
;     :pnodes [[[1 2 3] [4 [5 6] 7] [8 9]]],
;     :ppath nil,
;     :r ([8 9])},
;   :r (7)},
;   :r (6)}]

(p (-> zp zip/down zip/right zip/down zip/right (replace "hello")))
; ["hello"
; {:changed? true,
;   :l [4],
;   :pnodes [[[1 2 3] [4 [5 6] 7] [8 9]] [4 [5 6] 7]],
;   :ppath
;   {:l [[1 2 3]],
;   :pnodes [[[1 2 3] [4 [5 6] 7] [8 9]]],
;   :ppath nil,
;   :r ([8 9])},
;   :r (7)}]

(p (-> zp zip/down zip/right zip/down zip/right (replace "hello") zip/up))
; [(4 "hello" 7)
; {:changed? true,
;   :l [[1 2 3]],
;   :pnodes [[[1 2 3] [4 [5 6] 7] [8 9]]],
;   :ppath nil,
;   :r ([8 9])}]

(p (-> zp zip/down zip/right zip/down zip/right (replace "hello") zip/up zip/root))
; ([1 2 3] (4 "hello" 7) [8 9])

