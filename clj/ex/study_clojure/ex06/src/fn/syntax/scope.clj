(ns fn.syntax.scope)

; rfr: video/20230218-mert-clj-egzersiz-43.mp4

; (let [bindings*] exprs*)
; binding => binding-form init-expr
;  Evaluates the exprs in a lexical context in which the symbols in
; the binding-forms are bound to their respective init-exprs or parts
; therein.
;
; [let - clojure.core | ClojureDocs - Community-Powered Clojure Documentation and Examples](https://clojuredocs.org/clojure.core/let)
; [Clojure - Special Forms](https://clojure.org/reference/special_forms#let)

(def a 10)

(let [a 5]
  (+ a 3))
;=> 8
; let'in [] bloku içinde LHS RHS mantığı işler
; a <- 5

; scope meselesi var
; neden let'in içinde a'yı 10 değil de 5 olarak aldık?
; çünkü her zaman bir inner scope'da tanımlı isimler, dış scope'daki isimlerden önceliklidir

(let [b 3]
  (+ a b))
;=> 13

; bu kurallar defn için de geçerli
(defn f [a]
  (+ a 3))
(f 6)
;=> 9

; def ile tanımladığımız isimlere global deriz
; let, defn gibi formlarla tanımlanmış bindinglere ise local deriz