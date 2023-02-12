(ns fn.reduce)

(reduce + '(1 2 3))
;=> 6
(reduce + '(1 2 3 4))
;=> 10

(reduce * '(1 2 3))
;=> 6
(reduce * '(1 2 3 4))
;=> 24

; map'e benziyor biraz
(map + '(1 2 3))
;=> (1 2 3)
(map #(+ % 2) '(1 2 3))
;=> (3 4 5)

; peki reduce * işlemini verdiğimiz öğelere nasıl uyguluyordur acaba?
; tahmin edelim:
(* (* 1 2) 3)
;=> 6
(* (* (* 1 2) 3) 4)
;=> 24
; demek ki reduce bizim ona verdiğimiz listedeki öğeleri ikişer ikişer bir fonksiyona uyguluyor
; sonra onun çıktısını listedeki bir sonraki öğeyle birleştirip tekrar aynı fonksiyona uyguluyor

; [reduce - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/reduce)

(reduce + [1 2 3 4 5])  ;;=> 15
(reduce + [])           ;;=> 0
(reduce + [1])          ;;=> 1
(reduce + [1 2])        ;;=> 3
(reduce + 1 [])         ;;=> 1
(reduce + 1 [2 3])      ;;=> 6
