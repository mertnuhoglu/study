(ns ex.20250125_exmp_05)
;; [[20250125-exmp-05.clj]]

(cons 1 [2 3]) ; (1 2 3)

(concat [1 2] [3 4]) ; (1 2 3 4)

(concat [1 2] [3] [4]) ; (1 2 3 4)

; Why is lazy-cat a macro whereas related function `concat` is a function? #f/prmp #prg/clj
;   id:: 03d5a40b-6cf7-4b5d-98d3-dfdb1c84854b
;
; concat:
; When you call it, Clojure will first evaluate the arguments you pass to concat.
; While the resulting sequence is lazy, the fact remains that you already had to evaluate the arguments before calling concat.
;
; lazy-cat:
; Macros operate on unevaluated code, so lazy-cat can defer evaluation of its argument forms altogether.
; lazy-cat needs to be a macro to ensure that its argument expressions remain unevaluated and are only realized on demand
;

(mapcat reverse [[3 2] [5 4]]) 
; (2 3 4 5)

(take 3 (cycle ["a" "b"])) 
; ("a" "b" "a")

(interleave [:a :b] [1 2]) 
; (:a 1 :b 2)

(interpose ", " ["ali" "veli"]) 
; ("ali" ", " "veli")

(apply str (interpose ", " ["ali" "veli"]))  
; "ali, veli"


