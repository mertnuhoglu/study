(ns fn.map)

; rfr: video/20230221-mert-clj-egzersiz-47.mp4

; (map f)
; (map f coll)
; (map f c1 c2)
; (map f c1 c2 c3)
; (map f c1 c2 c3 & colls)
; Returns a lazy sequence consisting of the result of applying f to
; the set of first items of each coll, followed by applying f to the
; set of second items in each coll, until any one of the colls is
; exhausted.  Any remaining items in other colls are ignored. Function
; f should accept number-of-colls arguments. Returns a transducer when
; no collection is provided.
;

; map fonksiyonu temelde iki argüman alır
; (map f coll)
; f fonksiyonunu coll'ın her bir öğesine uygular
; bütün bu sonuçları ardarda dizer ve seq olarak döner

(map inc [1 2 3])
;=> (2 3 4)
; bu aslında şuna birebir  denktir:
'((inc 1) (inc 2) (inc 3))
;=> (2 3 4)

(map inc [1 2 3 4 5])
;;=> (2 3 4 5 6)


;; map can be used with multiple collections. Collections will be consumed
;; and passed to the mapping function in parallel:
(map + [1 2 3] [4 5 6])
;;=> (5 7 9)
; ≣ (equivalent)
[(+ 1 4) (+ 2 5) (+ 3 6)]
;=> [5 7 9]
; tabi vektör değil, sonuç da list olacak


