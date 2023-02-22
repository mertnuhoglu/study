(ns fn.keep-indexed)

; rfr: video/20230222-mert-clj-egzersiz-48.mp4

; (keep-indexed f)
; (keep-indexed f coll)
; Returns a lazy sequence of the non-nil results of
; (f index item). Note,
; this means false return values will be included.  f must be free of
; side-effects.  Returns a stateful transducer when no collection is
; provided.
;

