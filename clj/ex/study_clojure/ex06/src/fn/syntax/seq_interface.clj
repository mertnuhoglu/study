(ns fn.syntax.seq-interface)

; rfr: video/20230215-mert-clj-egzersiz-38.mp4
;
; [Clojure - Sequences](https://clojure.org/reference/sequences)

; herhangi bir objenin sequence olması için Seq interface'ini destekliyor olması lazım.
; bu interface de 3 tane fonksiyondan oluşur:
;(first coll)
;(rest coll)
;(cons item seq)

; bir liste ve vektör bunu destekler
; peki map destekler mi?
(def m {:a 1 :b 2 :c 3})
(first m)
;=> [:a 1]
(rest m)
;=> ([:b 2] [:c 3])
(cons [:d 4] m)
;=> ([:d 4] [:a 1] [:b 2] [:c 3])

; demek ki mapler de bir seq'tir

; bir verinin tam olarak Sequence olmasını seq? ile kontrol ederiz
(seq? [1 2])
;=> false
(seq? '(1 2))
;=> true

(def v [1 2])
(first v)
;=> 1
(rest v)
;=> (2)
(cons 3 v)
;=> (3 1 2)
