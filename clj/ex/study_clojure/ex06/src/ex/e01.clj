(ns ex.e01)

; Tarih: 20230109
; Barış'la Clojure Egzersizleri
;; rfr: video/20230113-mert-clj-egzersiz-01.mp4

(defn f1 [x]
  (* 2 x))
(f1 6)

;Loading src/e01.clj... done
;(defn f1 [x]
;  (* 2 x))
;=> #'e01/f1
;(f1 6)
;=> 12
;(f1 6)
;Syntax error compiling at (/private/var/folders/wv/l2dks9ps1fd8kvdk1m7jgcsm0000gp/T/form-init9297108186966908242.clj:1:1).
;Unable to resolve symbol: f1 in this context
;(defn f1 [x]
;  (* 2 x))
;
;=> #'user/f1
;(f1 6)
;=> 12
;(in-ns e01)
;Syntax error compiling at (/private/var/folders/wv/l2dks9ps1fd8kvdk1m7jgcsm0000gp/T/form-init9297108186966908242.clj:1:1).
;Unable to resolve symbol: e01 in this context
;(in-ns 'e01)
;=> #object[clojure.lang.Namespace 0x47018251 "e01"]
;(f1 6)
;=> 12
;f1
;=> #object[e01$f1 0x5cbd721b "e01$f1@5cbd721b"]
;(defn f2 [x]
;  (* 2 x))
;
;=> #'e01/f2
;(f2 6)
;=> 12

(reduce + [])           ;;=> 0
(reduce + [])

(defn myfunc [a b]
  (println "hello " a b))

(myfunc 1 5)
; Anonim fonksiyon:
((fn [a b]
   (println "hello " a b))
 1 5)
; Anonim lambda:
(#(println "hello " %1 %2) 1 5)
