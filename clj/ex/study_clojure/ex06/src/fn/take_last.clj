(ns fn.take-last)

; rfr: video/20230223-mert-clj-egzersiz-50.mp4
; rfr: fn/take.clj
; rfr: fn/take-last.clj
; rfr: fn/drop.clj
; rfr: fn/drop-last.clj

; (take-last n coll)
; Returns a seq of the last n items in coll.  Depending on the type
; of coll may be no better than linear time.  For vectors, see also subvec.
;

(take-last 2 [1 2 3 4])
;(3 4)

(take-last 2 [4])
;(4)

(take-last 2 [])
;nil

(take-last 2 nil)
;nil

(take-last 0 [1])
;nil

(take-last -1 [1])
;nil
