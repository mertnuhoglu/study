(ns ex00.types)
         
(deftype A [x y z])

(def a (A. 1 2 3))
(def aa (->A 1 2 3))

[a aa]
;;=> [#object[types.A 0x7a146cb3 "types.A@7a146cb3"]
;;=>  #object[types.A 0x709e2bf7 "types.A@709e2bf7"]]

(type a)
;;=> types.A

(A/getBasis)
;;=> [x y z]

[(.-x a) (.-y aa)]
;;=> [1 2]

; Types are mutable
(set! (.-x a) 19)
(.-x a)
;;=> 19
