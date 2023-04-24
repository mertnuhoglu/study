(ns fn.into)

; Video: 20230420-mert-clj-57-d08-refactoring-debug.mp4

; into: #nclk/çok-önemli #tpc/conversion
; []
; [to]
; [to from]
; [to xform from]
; Returns a new coll consisting of to-coll with all of the items of from-coll conjoined. A transducer may be supplied.
; ikinci argümandaki (from) öğeleri ilk argümandaki veri yapısının içine koyar

; When maps are the input source, they convert into an unordered sequence
; of key-value pairs, encoded as 2-vectors
(into [] {1 2, 3 4})
;[[1 2] [3 4]]
; from argümanındaki öğeleri, to argümanındaki data structure objesinin içine koyuyor

; Items are conj'ed one at a time, which puts them at the head of
; the destination list
(into () '(1 2 3))
;(3 2 1)

; #çok-önemli

; q: burada listi tekrar listin içine koymuşuz. neden?
; çıktı (3 2 1) girdi (1 2 3).
; sırayı tersine çevirmek için demek ki
; bu tip çözümlere hack veya trick denir
; bir şeyi yapmanın beklenmedik bir yolu.

(into {:x 4} [{:a 1} {:b 2} {:c 3}])
;{:x 4, :a 1, :b 2, :c 3}

; into'nun 4. kullanım şekli, transducer kullanıyor
(def xform
  (map #(+ 2 %)))

(into [-1 -2] xform (range 10))
;=> [-1 -2 2 3 4 5 6 7 8 9 10 11]

; şuna denktir:
(map #(+ 2 %) (range 10))
;=> (2 3 4 5 6 7 8 9 10 11)

(into [-1 -2] '(2 3 4 5 6 7 8 9 10 11))
;=> [-1 -2 2 3 4 5 6 7 8 9 10 11]


