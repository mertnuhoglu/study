(ns ex.20250125_exmp_22)
;; [[20250125-exmp-22.clj]]

(with-open [rdr (clojure.java.io/reader "/etc/passwd")]
  (count (line-seq rdr))) ; 138

(re-seq #"\d" "version 3.2")  ; ("3" "2")

(tree-seq seq? identity '((1 2 (3)) (4))) ; (((1 2 (3)) (4)) (1 2 (3)) 1 2 (3) 3 (4) 4)

(def f (clojure.java.io/file "/Users/mertnuhoglu/prj/myrepo/")) ; #'ex.20250125_exmp_22/f
(def fs (file-seq f)) ; #'ex.20250125_exmp_22/fs
(first fs) ; #object[java.io.File 0x33cb8b34 "/Users/mertnuhoglu/prj/myrepo"]
(take 3 fs)
; (#object[java.io.File 0x33cb8b34 "/Users/mertnuhoglu/prj/myrepo"]
;  #object[java.io.File 0x96ab792 "/Users/mertnuhoglu/prj/myrepo/mynotes"]
;  #object[java.io.File 0x1b051bd2 "/Users/mertnuhoglu/prj/myrepo/mynotes/personal"])

(take 3 (iterator-seq (.iterator (.keySet (java.lang.System/getProperties))))) 
; ("java.specification.version" "sun.jnu.encoding" "java.class.path")

(enumeration-seq (java.util.StringTokenizer. "ali veli")) ; ("ali" "veli")
