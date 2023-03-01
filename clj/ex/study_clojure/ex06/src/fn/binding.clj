(ns fn.binding)

; rfr: fn/syntax/vars_bindings.clj

; (binding bindings & body)
; binding => var-symbol init-expr
;  Creates new bindings for the
; (already-existing) vars, with the
; supplied initial values, executes the exprs in an implicit do, then
; re-establishes the bindings that existed before.  The new bindings
; are made in parallel
; (unlike let); all init-exprs are evaluated
; before the vars are bound to their new values.
;

; Daha önce biz diyorduk ki, clojure'daki tüm objeler değişmezdir
; Fakat biraz bu bir detay var.
; Galiba bu binding bu değişebilir objeler yapmak için kullanılıyor

; [clojure and ^:dynamic - Stack Overflow](https://stackoverflow.com/questions/11730828/clojure-and-dynamic)

