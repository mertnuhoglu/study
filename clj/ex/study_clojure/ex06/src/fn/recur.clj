(ns fn.recur)

; Evaluates the exprs in order, then, in parallel, rebinds the bindings of
; the recursion point to the values of the exprs. See
; http://clojure.org/special_forms for more information.

; [recur - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/recur)

; A loop that sums the numbers 10 + 9 + 8 + ...

(loop [sum 0
       cnt 10]
  (if (= cnt 0)
    sum
    (recur (+ cnt sum) (dec cnt))))

(comment
  ; recur'ün ilk argümanı loop'un ilk argümanına
  ; ...      2.  argümanı ...     2.  argümanına bağlanır
  ;(recur (+ cnt sum) (dec cnt))
  ; ↓     ↓           ↓
  ; loop  sum         cnt

  ; 1. tur:
  ; sum <- 0
  ; cnt <- 10
  ; 2. tur
  ; sum <- 10
  ; cnt <- 9
  ; 3. tur
  ; sum <- 19
  ; cnt <- 8
  ; ...
  ; end
  ,)

; [Clojure - Special Forms](https://clojure.org/reference/special_forms#recur)

; Birkaç kural var:
; #trm: recursion point: recur'ün içinde olduğu loop veya function formu
; #trm: tail-position: bir fonksiyonun en son ifadesi
; recur sadece tail-positionda kullanılabilir

; Yazım stili:
; recur ve reduce birbirine benzer:
; reduce imzası: (reduce f init coll)
; reduce'un her turunda, coll bir öğe küçülür
; coll tamamen bitince, reduce döngüsü de biter
; coll'un sıfırlanması, döngünün bitme koşuludur
; reduce'un her turunda, birikimli bir ara sonuç üretilir
; bu ara sonuç f'in ilk argümanı olarak aktarılır
; bu ara sonuca birikimli argüman (aggregate) diyelim

; recur'de de bitme koşulu ve birikimli argüman bulunur
; yukarıdaki örnekte:
; bitme koşulu: (= cnt 0)
; birikimli argüman: sum
; bütün recur döngülerinde, bir bitme koşulu ve bir de birikimli argüman olmalıdır
; recur çağrılırken, bu ikisini ona göre tanımlarsın:
; (recur (+ cnt sum) (dec cnt))
;         ↓           ↓
;        sum         cnt
;    birikimli arg  bitme koşulu
; not: birikimli argümanda (+ ..) yaparak, ara sonucu biriktiriyorsun
; bitme koşulunda (dec) yaparak giderek sıfıra yaklaşıyorsun