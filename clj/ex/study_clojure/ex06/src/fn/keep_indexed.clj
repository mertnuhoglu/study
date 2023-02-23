(ns fn.keep-indexed)

; rfr: video/20230222-mert-clj-egzersiz-49.mp4

; (keep-indexed f)   #nclk/önemli
; (keep-indexed f coll)
; Returns a lazy sequence of the non-nil results of
; (f index item). Note,
; this means false return values will be included.  f must be free of
; side-effects.  Returns a stateful transducer when no collection is
; provided.
;

(keep-indexed #(if (odd? %1) %2) [:a :b :c :d :e])
;(:b :d)

; keep-indexed iki argüman alıyor
; 1. arg: f
; 2. arg: coll
; bu coll'un her bir öğesi için, o öğenin indeksini ve kendisini f fonksiyona paslıyor
; (f index item) şeklinde her bir öğe için bir fonksiyon çağrısı yapıyor
; sonuçları da seq içinde toplayıp dönüyor

(comment
  (def f #(if (odd? %1) %2))
  ; 1. tur:
  (f 0 :a)
  ;=> nil
  ; 2. tur:
  (f 1 :b)
  ;=> :b
  (f 2 :c)
  ;=> nil
  ; ...
  ; nil dışı öğeleri toplayıp seq içinde dönüyor

  ,)


