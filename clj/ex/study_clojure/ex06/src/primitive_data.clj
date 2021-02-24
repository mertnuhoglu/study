(ns primitive-data)

(identity 0N)
;;=> 0N

(= 0N 0)
;;=> true

(== 0N 0)
;;=> true

(= 0N 0.0)
;;=> false

(== 0N 0.0)
;;=> true

(type 0N)
;;=> clojure.lang.BigInt

(type 0.0)
;;=> java.lang.Double

(type 0)
;;=> java.lang.Long

