(ns metadata)

; Code from: Programming Clojure - Alex Miller

(meta #'str)
;; {:added "1.0",
;;  :ns #object[clojure.lang.Namespace 0x7f1855b7 "clojure.core"],
;;  :name str,
;;  :file "clojure/core.clj",
;;  :static true,
;;  :column 1,
;;  :line 546,
;;  :tag java.lang.String,
;;  :arglists ([] [x] [x & ys]),
;;  :doc
;;  "With no args, returns the empty string. With one arg x, returns\n  x.toString().  (str nil) returns the empty string. With more than\n  one arg, returns the concatenation of the str values of the args."}

(defn ^{:tag String} shout [s]
  (clojure.string/upper-case s))

(meta #'shout)
;; {:tag java.lang.String,
;;  :arglists ([s]),
;;  :line 16,
;;  :column 1,
;;  :file
;;  "/Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/src/metadata.clj",
;;  :name shout,
;;  :ns #object[clojure.lang.Namespace 0x59afaa6 "metadata"]}

(defn ^String shout [s]
  (clojure.string/upper-case s))

(meta #'shout)
;; {:tag java.lang.String,
;;  :arglists ([s]),
;;  :line 31,
;;  :column 1,
;;  :file
;;  "/Users/mertnuhoglu/projects/study/clj/ex/study_clojure/ex06/src/metadata.clj",
;;  :name shout,
;;  :ns #object[clojure.lang.Namespace 0x59afaa6 "metadata"]}

; metadata placed as last line
(defn shout 
  ([s] (clojure.string/upper-case s))
  {:tag String})


