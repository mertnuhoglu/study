(ns fn.mapv)

; rfr: video/20230221-mert-clj-egzersiz-47.mp4
; rfr: fn/map.clj

; (mapv f coll)
; (mapv f c1 c2)
; (mapv f c1 c2 c3)
; (mapv f c1 c2 c3 & colls)
; Returns a vector consisting of the result of applying f to the
; set of first items of each coll, followed by applying f to the set
; of second items in each coll, until any one of the colls is
; exhausted.  Any remaining items in other colls are ignored. Function
; f should accept number-of-colls arguments.
;

; map her zaman seq döner
; mapv ise map'in döndüğü seq objesini vektöre çevirir:

(mapv inc [1 2 3])
;=> [2 3 4]
; bu aslında şuna birebir  denktir:
[(inc 1) (inc 2) (inc 3)]
;=> [2 3 4]


