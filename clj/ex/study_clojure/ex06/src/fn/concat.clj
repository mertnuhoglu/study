(ns fn.concat)

; (concat) #nclk/çok-önemli
; (concat x)
; (concat x y)
; (concat x y & zs)
; Returns a lazy seq representing the concatenation of the elements in the supplied colls.

(concat [1 2] [3] [4 5 6])
;;=> (1 2 3 4 5 6)

(concat [1 2] [3 4])
;(1 2 3 4)

(into [] (concat [1 2] [3 4]))
;[1 2 3 4]

(concat [:a :b] nil [1 [2 3] 4])
;(:a :b 1 [2 3] 4)

(concat [1] [2] '(3 4) [5 6 7] #{9 10 8})
;(1 2 3 4 5 6 7 8 9 10)
;; The last three elements might appear in a different order.

; string birleştirmek için: str kullanılır. rfr: fn/str.clj
(concat "abc" "def")
;(\a \b \c \d \e \f)

(apply concat '(([1 2]) ([3 4] [5 6]) ([7 8])))
;([1 2] [3 4] [5 6] [7 8])