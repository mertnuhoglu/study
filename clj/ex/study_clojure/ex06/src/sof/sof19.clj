(ns sof.sof19)

; [functional programming - Clojure length of sequence - Stack Overflow](https://stackoverflow.com/questions/8632495/clojure-length-of-sequence)

(count '(1 2 3))
;=> 3
(count [1 2 3])
;=> 3

; a02: alength for java arrays

; alength works on Java™ arrays, such as a String[] or Integer[],

(def x '(1 2 3))
(def xa (to-array x))
(class x)
;clojure.lang.PersistentList
(class xa)
;[Ljava.lang.Object;
(alength xa)
;3