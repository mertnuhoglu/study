(ns fn.cons)

; (cons x seq)
; Returns a new seq where x is the first element and seq is
;   the rest.
;

;; prepend 1 to a list
(cons 1 '(2 3 4 5 6))
;;=> (1 2 3 4 5 6)

;; notice that the first item is not expanded
(cons [1 2] [4 5 6])
;;=> ([1 2] 4 5 6)

; q: cons ve conj arasındaki ayrımı nasıl hatırlayabiliriz?
; cons her zaman seq döner. con(seq) gibi
; liste seq'dir.
; listeye yeni öğeler baştan eklenir.
; 1. conj ise her zaman seq dönmez.
; 2. her zaman en başa da ekleme olmaz.
; 3. cons x öğesini seqe ekler. bu yüzden argüman sırası: cons x seq
; conj ise tam tersi. conj coll x