(ns fn.drop-last)

; rfr: video/20230223-mert-clj-egzersiz-50.mp4
; rfr: fn/take.clj
; rfr: fn/take-last.clj
; rfr: fn/drop.clj
; rfr: fn/drop-last.clj

; (drop-last coll)
; (drop-last n coll)
; Return a lazy sequence of all but the last n
; (default 1) items in coll
;

; sondan n tanesini sil

(drop-last 1 [1 2 3 4])
;=> (1 2 3)

(drop-last [1 2 3 4])
;=> (1 2 3)

(drop-last -1 [1 2 3 4])
;=> (1 2 3 4)

(drop-last 0 [1 2 3 4])
;=> (1 2 3 4)

(drop-last 5 [1 2 3 4])
;=> ()
