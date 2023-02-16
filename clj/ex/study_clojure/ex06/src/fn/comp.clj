(ns fn.comp)

; rfr: video/20230216-mert-clj-egzersiz-40.mp4

; (comp)
; (comp f)
; (comp f g)
; (comp f g & fs)
; Takes a set of functions and returns a fn that is the composition
; of those fns.  The returned fn takes a variable number of args,
; applies the rightmost of fns to the args, the next
; fn
; (right-to-left) to the result, etc.

; comp: composition kelimesinden türer
; composition ne demektir?
; kompozisyon: bileşke, parçalardan oluşan bir bütün
; compose etmek: parçaları birleştirmek

; FP'nin vaatlerinden bir tanesi şudur:
; Farklı yazdığınız modülleri birleştirebilmenizi sağlar
; Reusability sağlar
; Abstraction sağlar
; Daha üst seviyede kavramlarla konuşabilmenizi sağlar.

(def f +)
(defn g [a]
  (* a 2))
(f 3 2)
;=> 5
(g 3)
;=> 6

(g (f 3 2))
;=> 10

; g(f(x)) = gof
; gof diye bir fonksiyon düşün bu aslında önce verilen argümana f'yi uygular, sonra g'yi uygular
; compose gof birleştirme o operatörüne compose etmek deriz

((comp nil? second) [:a 1])
;=> false
((comp nil? second) [:a nil])
;=> true
; dolayısıyla bu compose çağrıları aslında şuna denk:
(nil? (second [:a 1]))
;=> false
(nil? (second [:a nil]))
;=> true
