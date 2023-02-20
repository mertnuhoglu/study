(ns fn.pop)

; rfr: video/20230220-mert-clj-egzersiz-44.mp4

; (pop coll)    #nclk/orta-seviye
; For a list or queue, returns a new list/queue without the first
; item, for a vector, returns a new vector without the last item. If
; the collection is empty, throws an exception.  Note - not the same
; as next/butlast.
;

; yani coll'dan son eklenmiş öğeyi kaldırıyor

; peek: coll'a son eklenmiş öğeyi döndürür, ama silmez
(peek [1 2 3])
;3
(pop [1 2 3])
;[1 2]
(peek '(1 2 3))
;1
(pop '(1 2 3))
;(2 3)
