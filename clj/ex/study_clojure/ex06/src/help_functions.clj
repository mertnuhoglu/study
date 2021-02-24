(ns help_functions
  (:require [clojure.inspector :as ins]))

(ins/inspect-table [{:a 1 :b 2 :c 3}{:a 4 :b 5 :c 6}])
;; side-effect: opens a window with a table

(defn number-summary
  "Computes a summary of the arithmetic properties of a number, as a data structure."
  [n]
  (let [proper-divisors (into (sorted-set)
                          (filter
                            (fn [d]
                              (zero? (rem n d)))
                            (range 1 n)))
        divisors-sum (apply + proper-divisors)]
    {:n n
     :proper-divisors proper-divisors
     :even? (even? n)
     :prime? (= proper-divisors #{1})
     :perfect-number? (= divisors-sum n)}))
(require '[clojure.pprint :as pp])
(mapv number-summary [5 6 7 12 28 42])
;; [{:n 5,
;;   :proper-divisors #{1},
;;   :even? false,
;;   :prime? true,
;;   :perfect-number? false
;;  {:n 6,
;;   :proper-divisors #{1 2 3},
;;   :even? true,
;;   :prime? false,
;;   :perfect-number? true}
;;  {:n 7,
;;   :proper-divisors #{1},
;;   :even? false,
;;   :prime? true,
;;   :perfect-number? false}
;;  {:n 12,
;;   :proper-divisors #{1 2 3 4 6},
;;   :even? true,
;;   :prime? false,
;;   :perfect-number? false}
;;  {:n 28,
;;   :proper-divisors #{1 2 4 7 14},
;;   :even? true,
;;   :prime? false,
;;   :perfect-number? true}
;;  {:n 42,
;;   :proper-divisors #{1 2 3 6 7 14 21},
;;   :even? true,
;;   :prime? false,
;;   :perfect-number? false})

(pp/print-table (mapv number-summary [6 12 28]))
; (out) | :n | :proper-divisors | :even? | :prime? | :perfect-number? |
; (out) |----+------------------+--------+---------+------------------|
; (out) |  6 |         #{1 2 3} |   true |   false |             true |
; (out) | 12 |     #{1 2 3 4 6} |   true |   false |            false |
; (out) | 28 |    #{1 2 4 7 14} |   true |   false |             true |

(pp/print-table [{:a 1 :b 2 :c 3}{:a 4 :b 5 :c 6}])
; (out) | :a | :b | :c |
; (out) |----+----+----|
; (out) |  1 |  2 |  3 |
; (out) |  4 |  5 |  6 |

(all-ns)

(require '[clojure.java.io :as io])
(ns-publics 'clojure.java.io)
;{default-streams-impl #'clojure.java.io/default-streams-impl,
 ;make-output-stream #'clojure.java.io/make-output-stream,
 ;make-parents #'clojure.java.io/make-parents,}

;; (require '[clojure.repl :refer :all])
(require '[clojure.repl])
(ns-publics 'clojure.repl)
;;=> {source-fn #'clojure.repl/source-fn,
;;=>  doc #'clojure.repl/doc,
;;=>  stack-element-str #'clojure.repl/stack-element-str,
;;=>  find-doc #'clojure.repl/find-doc,
;;=>  dir #'clojure.repl/dir,
;;=>  pst #'clojure.repl/pst,
;;=>  dir-fn #'clojure.repl/dir-fn,
;;=>  source #'clojure.repl/source,
;;=>  set-break-handler! #'clojure.repl/set-break-handler!,
;;=>  root-cause #'clojure.repl/root-cause,
;;=>  demunge #'clojure.repl/demunge,
;;=>  thread-stopper #'clojure.repl/thread-stopper,
;;=>  apropos #'clojure.repl/apropos)

(clojure.repl/source map)
(clojure.repl/doc map)
(clojure.repl/find-doc "create-context")
(clojure.repl/apropos "unmount-renderer")
;; (cljfx.api/unmount-renderer)
(clojure.repl/apropos "map")

(def ns1 
  {:source 'clojure.repl/source
   :doc 'clojure.repl/doc
   :find-doc 'clojure.repl/find-doc
   :apropos 'clojure.repl/apropos
   :dir 'clojure.repl/dir})
ns1
;; {:source clojure.repl/source,
;;  :doc clojure.repl/doc,
;;  :find-doc clojure.repl/find-doc,
;;  :apropos clojure.repl/apropos
*ns*
;; #object[clojure.lang.Namespace 0x7cf00581 "user"]
(ns-aliases 'user)
;; {io #object[clojure.lang.Namespace 0x456cd354 "clojure.java.io"]}
(clojure.repl/dir help_functions)
(clojure.repl/dir clojure.string)
; (out) blank?
; (out) capitalize
; (out) ends-with?


