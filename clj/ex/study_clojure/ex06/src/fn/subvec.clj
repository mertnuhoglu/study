(ns fn.subvec)

; rfr: video/20230223-mert-clj-egzersiz-50.mp4

; (subvec v start)
; (subvec v start end)
; Returns a persistent vector of the items in vector from
; start (inclusive) to end (exclusive).  If end is not supplied,
; defaults to (count vector). This operation is O (1) and very fast, as
; the resulting vector shares structure with the original and no
; trimming is done.
;

; python'daki slicing operator gibi çalışıyor

(subvec [1 2 3 4 5 6 7] 2 4)
;[3 4]
; 2. indeksten 4. indekse kadar çektik
; dilimledik yani
; [2] [3] (indeks anlamında)

(def a (vec (range 1 (inc 10))))
(identity a)
;=> (1 2 3 4 5 6 7 8 9 10)

(subvec a 3) ; [3:]
; [4 5 6 7 8 9 10]
; 3. argümanı vermemiş burada
; bu yüzden sonuna kadar git anlamına geliyor

